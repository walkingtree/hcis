

 Login = Ext.extend(Object,{
	constructor: function() {
		this.rememberme = CookieUtil.get('rememberMe');
		if(this.rememberme!= null){
			var remembermeInfo = this.rememberme.split(','); 
		}
		this.userNameTxf = new Ext.form.TextField({
			fieldLabel:'Username',
			anchor:'90%',
			name:'userName',
			value:this.rememberme!=null?remembermeInfo[0]:''
		});
		
		this.passwordTxf = new Ext.form.TextField({
			fieldLabel:'Password',
			anchor:'90%',
			name:'password',
			inputType:'password',
			value:this.rememberme!=null?remembermeInfo[1]:''
		});
		this.rememberChk = new Ext.form.Checkbox({
			boxLabel:'Remember me',
			name:'rememberMe',
			labelSeparator:' ',
			checked:this.rememberme!=null?true:false
		});
		
		this.loginPanel = new Ext.form.FormPanel({
			layout:'column',
			frame:false,
			border:false,
		    style:'padding:5px;',
			labelAlign:'left',
			defaults:{
				columnWidth:1,
				border:false
			},
			items:[
				{
					layout:'form',
					items:this.userNameTxf
				},
				{
					layout:'form',
					items:this.passwordTxf
				},
				{
					layout:'form',
					items:this.rememberChk
				}
			],
			buttons:[
				{
					text:userMsg.login,
					iconCls:'login-icon',
					scope:this,
					handler: function() {
						var loginWindow = this.loginWindow;
						var values = this.loginPanel.getForm().getValues();
						handleLoginBtn(loginWindow, values);
					}
				}
			]
		});
		
		this.bubblePanel = new Ext.ux.BubblePanel({
			layout:'form',
	        autoWidth:true,
		    height:'100%',
		    items:this.loginPanel
	     });
	     
		this.loginWindow = new Ext.Window({
			closable:false,
			resizable:false,
			draggable:false,
			title:'Login',
			width:'35%',
			height:'30%',
			items: this.bubblePanel
		});
	},
	getPanel: function() {
		return this.loginWindow.show();
	}
});
	
function handleLoginBtn( loginWindow, values ) {
	if(values['rememberMe'] == 'on'){
		var rememberMeValue =values['userName']+","+values['password'];
		CookieUtil.set(configuration.remembermename,rememberMeValue);
	}else{
		this.rememberme = CookieUtil.get('rememberMe');
		if(this.rememberme!=null){
			CookieUtil.unset(configuration.remembermename);
		}
	}
	UserManagementController.validateUser( values['userName'],values['password'],function(authorisedUser){
						if(authorisedUser!=null) {
							loginWindow.close();
							var value = authorisedUser.userId+","+authorisedUser.firstName+" "+authorisedUser.lastName+","+
										authorisedUser.role.description+","+authorisedUser.role.id+","+authorisedUser.password;
							CookieUtil.set(configuration.name,value,configuration.path);
//							CookieUtil.set(configuration.name,value);			
//							var layout = new UserManagementLayout();
//							layout.getPanel().render(Ext.getBody());
//							var homePage = new Home();
//							homePage.getPanel().render(Ext.getBody());
							window.location =configuration.loginLocation
							
						} else {
							Ext.Msg.show({
								msg:'invalid username and password!',
								buttons:Ext.Msg.OK
							});
						}
					});
		
}
	
	
