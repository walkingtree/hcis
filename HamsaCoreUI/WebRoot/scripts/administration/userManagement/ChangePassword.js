Ext.namespace("administration.userManagement");

administration.userManagement.ChangePassword = Ext.extend(Object,{
	constructor:function(){
		this.appUser = CookieUtil.get('appuser');
		var userInfo = this.appUser.split(',');
		var newPassword = null;
		this.oldPasswordTxf = new Ext.form.TextField({
			fieldLabel:userMsg.oldpassword,
			anchor:'100%',
			name:'oldPassword',
			inputType:'password'
		});
		this.newPasswordTxf = new Ext.form.TextField({
			fieldLabel:userMsg.newpassword,
			anchor:'100%',
			name:'newPassword',
			inputType:'password',
			listeners:{
				blur:function(comp){
					newPassword=comp.getValue();	
				}
			}
		});
		this.confirmPasswordTxf = new Ext.form.TextField({
			fieldLabel:userMsg.confirmpassword,
			anchor:'100%',
			name:'confirmPassword',
			inputType:'password',
			listeners:{
				blur: function(comp) {
					if(newPassword != comp.getValue()){
						Ext.Msg.show({
		         			msg:userMsg.invalidpassword,
		         			buttons: Ext.Msg.OK,
		         			icon: Ext.MessageBox.INFO
						});
					}
				}
			}
		});
		this.mainPanel = new Ext.form.FormPanel({
			layout:'column',
			frame:true,
			defaults:{
				columnWidth:1,
				border:false,
				labelWidth:125
			},
			items:[
				{
					layout:'form',
					items:this.oldPasswordTxf
				},
				{
					layout:'form',
					items:this.newPasswordTxf
				},
				{
					layout:'form',
					items:this.confirmPasswordTxf
				}
			],
			buttons:[
				{
					text:userMsg.update,
					scope:this,
					handler:function(){
						var values = this.mainPanel.getForm().getValues();
						user ={
							userId:userInfo[0]
						}
						UserManagementController.changePassword(user,values['newPassword'],function(authorisedUser){
								Ext.Msg.show({
					         			msg:userMsg.updatepasswordsuccessmsg,
					         			 buttons: Ext.Msg.OK,
					         			 icon: Ext.MessageBox.INFO,
					         			 fn:function(btn){
											Ext.ux.event.Broadcast.publish('closeChangePasswordWindow');

					         			 }
								});
						});
					}
				},
				{
					text:userMsg.cancel,
					scope:this,
					handler:function(){
					this.mainPanel.getForm().reset();
					Ext.ux.event.Broadcast.publish('closeChangePasswordWindow');
					}
				}
			]
		});
		this.changePasswordWindow = new Ext.Window({
			closeAction:'hide',
			resizable:false,
			draggable:false,
			title:userMsg.changepassword,
			width:'25%',
			height:'50%',
			items: this.mainPanel
		});
	},
	getPanel:function(){
		return this.changePasswordWindow;
	}
});