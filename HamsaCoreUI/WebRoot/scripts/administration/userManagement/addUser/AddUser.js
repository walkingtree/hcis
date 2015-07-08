
Ext.namespace("administration.userManagement.addUser");
Ext.QuickTips.init();

administration.userManagement.addUser.AddUser = new Ext.extend(Object,{
	constructor: function(config ){
		
		Ext.apply( this, config );
		
		this.saveBtn = new Ext.Button({
			text:userMsg.save,
			iconCls: 'save_btn',
   			hidden:config.flag?true:false,
   			tooltip:userMsg.saveuser,
   			scope:this,
   			handler:function(){
   				this.saveButtonHandler(  );
   			}
		});
		
		this.resetBtn = new Ext.Button({
			text:userMsg.reset,
			hidden:config.flag?true:false,
			iconCls: 'cancel_btn',
	  		scope:this,
	  		handler:function() {
	  			this.mainPanel.getForm().reset();
	  		}
	  		
		});
		
		this.cancelBtn = new Ext.Button({
			text:userMsg.cancel,
			iconCls: 'cross_icon',
	  		hidden:config.flag?true:false,
	  		scope:this,
	  		handler: function() {
	  		this.mainPanel.ownerCt.close();	
	  		}
		});
		
		this.userNameTxf = new Ext.form.TextField({
			anchor:'45%',
			fieldLabel:userMsg.usrename,
			name:'userName',
			allowBlank :false,
			required:true
		});
		
		this.effectiveFromDt = new Ext.form.WTCDateField({
			anchor:'60%',
			fieldLabel:userMsg.effectivefrom,
			allowBlank :false,
			required:true,
			value: new Date(),
			name:'effectiveFrom',
			readOnly:config.flag?true:false
		});
		
		this.effectiveToDt = new Ext.form.WTCDateField({
			anchor:'60%',
			fieldLabel:userMsg.effectiveto,
			name:'effectiveTo',
			readOnly:config.flag?true:false
			
		});
		
		this.mainPanel = new Ext.form.FormPanel({
			layout:'column',
			buttonAlign:'right',
			frame:true,
			monitorValid: true,
			defaults:{
				columnWidth:.5
			},
			items:[
				{
				layout:'form',
				border:false,
				columnWidth:1,
				items:this.userNameTxf
				},
				{
					layout:'form',
					border:false,
					items:[
						{
							xtype:'textfield',
							anchor:'90%',
							fieldLabel:userMsg.firstname,
							allowBlank :false,
							required:true,
							name:'firstName',
							readOnly:config.flag?true:false
						}
					]
				},
				{
					layout:'form',
					border:false,
					labelWidth:120,
					items:[
						{
							xtype:'textfield',
							anchor:'90%',
							fieldLabel:userMsg.lastname,
							name:'lastName',
							readOnly:config.flag?true:false
						}
					]
				},
				{
					layout:'form',
					border:false,
					hidden: this.mode== 'edit'? true : false,
					items:[
						{
							xtype:'textfield',
							anchor:'90%',
							fieldLabel:userMsg.password,
							allowBlank :false,
							required:true,
							name:'password',
							inputType:'password',
							readOnly:config.flag?true:false
						}
					]
				},
				{
					layout:'form',
					border:false,
					labelWidth:120,
					hidden: this.mode== 'edit'? true : false,
					items:[
						{
							xtype:'textfield',
							anchor:'90%',
							fieldLabel:userMsg.confirmpassword,
							allowBlank :false,
							required:true,
							name:'confirmPassword',
							inputType:'password',
							readOnly:config.flag?true:false,
							listeners:{
								blur: function(comp) {
									var values = comp.ownerCt.ownerCt.getForm().getValues();
									if(values['password'] != values['confirmPassword']){
										Ext.Msg.show({
						         			msg:userMsg.invalidpassword,
						         			buttons: Ext.Msg.OK,
						         			icon: Ext.MessageBox.INFO
										});
									}
								}
							}
						}
					]
				},
				{
					layout:'form',
					border:false,
					items:this.effectiveFromDt
				},
				{
					layout:'form',
					border:false,
					labelWidth:120,
					items:this.effectiveToDt
				},
				{
					layout:'form',
					border:false,
					items:[
						{
							xtype:'textfield',
							anchor:'90%',
							fieldLabel:userMsg.housename,
							name:'houseName',
							readOnly:config.flag?true:false
						}
					]
				},
				{
					layout:'form',
					border:false,
					labelWidth:120,
					items:[
						{
							xtype:'textfield',
							anchor:'90%',
							fieldLabel:userMsg.street,
							name:'street',
							readOnly:config.flag?true:false
						}
					]
				},
				{
					layout:'form',
					border:false,
					items:[
						{
							xtype:'combo',
							anchor:'70%',
							store:UserManagerloadCountryCbx(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							mode:'local',
							fieldLabel:userMsg.country,
							name:'country',
							forceSelection:true,
							readOnly:config.flag?true:false,
							listeners:{
								select: function(combo, record,index) {
									userManagerStateStore.removeAll();
									userManagerStateStore.load({params:{start:0, limit:8}, arg:[record.data.code]});
								}
							}
						}
					]
				},
				{
					layout:'form',
					border:false,
					labelWidth:120,
					items:[
						{
							xtype:'combo',
							anchor:'70%',
							fieldLabel:userMsg.stateprovince,
							name:'state',
							mode:'local',
							store:UserManagerloadStateCbx(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							forceSelection:true,
							readOnly:config.flag?true:false
						}
					]
				},
				{
					layout:'form',
					border:false,
					items:[
						{
							xtype:'textfield',
							anchor:'50%',
							fieldLabel:userMsg.zipcode,
							name:'zipCode',
							readOnly:config.flag?true:false
						}
					]
				},
				{
					layout:'form',
					border:false,
					labelWidth:120,
					items:[
						{
							xtype:'textfield',
							anchor:'90%',
							fieldLabel:userMsg.city,
							name:'city',
							readOnly:config.flag?true:false
						}
					]
				},
				{
					layout:'form',
					border:false,
					items:[
						{
							xtype:'textfield',
							anchor:'90%',
							fieldLabel:userMsg.email,
							name:'email',
							vtype:'email',
							readOnly:config.flag?true:false
						}
					]
				},
				{
					layout:'form',
					border:false,
					labelWidth:120,
					items:[
						{
							xtype:'textfield',
							anchor:'90%',
							fieldLabel:userMsg.contactnumber,
							name:'contactNumber',
							readOnly:config.flag?true:false
						}
					]
				},
				{
					layout:'form',
					border:false,
					items:[
						{
							xtype:'combo',
							fieldLabel:userMsg.role,
							mode:'local',
							store:UserManagerloadRoleCbx(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							anchor:'70%',
							name:'role',
							forceSelection:true,
							allowBlank :false,
							required:true,
							readOnly:config.flag?true:false
						}
					]
				
				}
			],
			buttons:[
				this.saveBtn,
				this.resetBtn
//				this.cancelBtn
			]
		});
		
		this.mainPanel.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.saveBtn.enable();
			} else {
				this.saveBtn.disable();
			}
		}, this);
		
		if( this.mode == userMsg.addMode && this.isPopup == false ){
			this.cancelBtn.hide();
		}
		if( this.mode == userMsg.editMode ){
			this.userNameTxf.disable();
		}
		this.initialListeners();
		
		
	},
	getPanel: function() {
		return this.mainPanel;
	},
	saveButtonHandler: function(  ){
		var formPnl = this.mainPanel;
		var values = this.mainPanel.getForm().getValues();
		var mainThis = this;
		var pass1 = values.password;
		var pass2 = values.confirmPassword;
		if(pass1 != pass2){
			Ext.Msg.show({
				msg : 'Invalid password'
			});
		}
		else
		{
			if(!formPnl.getForm().isValid()){
			alertError(userMsg.mandatoryfields);
		}else {
			user ={
				 userId:values['userName'],
				 password:values['password'],
				 firstName:values['firstName'],
				 lastName:values['lastName'],
				 roleLevelInd:'A',
				 address:values['houseName']+','+values['street'],
				 zipcode:values['zipCode'],
				 state:{code:values['state']},
				 country:{code:values['country']},
				 contactNumber:values['contactNumber'],
				 emailAddress:values['email'],
				 city:values['city'],
				 startDtm:getStringAsWtcDateFormat(values['effectiveFrom']),
				 endDtm:getStringAsWtcDateFormat(values['effectiveTo']),
				 role:{id:values['role']}	
			 };
			authorisedUser ={
				userId:authorisedUser.userId,
				password:authorisedUser.password,
				role:{id:authorisedUser.roleId}
			};
			if(this.mode == userMsg.addMode ){
				UserManagementController.saveUser(user,function(){
					if( mainThis.isPopup ){
						layout.getCenterRegionTabPanel().remove( formPnl.ownerCt , true );
						Ext.ux.event.Broadcast.publish('closeUserWindow');
					}else{
						formPnl.getForm().reset();
					}
				});
			}
			
			if(this.mode == userMsg.editMode ){
				var userName = this.userNameTxf.getValue();
				user.userId = userName;
				UserManagementController.modifyUser(user,authorisedUser,function(){
					if( mainThis.isPopup ){
						layout.getCenterRegionTabPanel().remove( formPnl.ownerCt , true );
						Ext.ux.event.Broadcast.publish('closeUserWindow');
					}else{
						formPnl.getForm().reset();
					}			
				});
			}	
		}
			
		}
	},
	initialListeners: function(){
		this.effectiveFromDt.on('change', function( comp ){
			this.effectiveToDt.setMinValue( comp.getValue() );
		}, this);
		
		this.effectiveToDt.on('change', function( comp ){
			this.effectiveFromDt.setMaxValue( comp.getValue() );
		}, this);
	},
	setDefaultValues: function(){
		this.effectiveFromDt.setValue( new Date() );
		this.effectiveToDt.setMinValue( new Date() );
		this.userNameTxf.focus();
	}
});