Ext.namespace("login");

login.LoginForm = Ext.extend(Ext.form.FormPanel, {
	border : false,
	layout : 'column',
	frame : false,
	labelAlign : 'left',
	labelWidth: 0.01,
 	initComponent : function() {

		this.rememberme = CookieUtil.get('rememberMe');
		if(this.rememberme !== null){
			var remembermeInfo = this.rememberme.split(','); 
		}
		
		this.userNameTxf = new Ext.form.TextField({
			fieldLabel:'',
			emptyText:'Username',
			anchor:'98%',
			name:'userName',
			value:this.rememberme !== null ? remembermeInfo[0] : ''
		});
		
		this.passwordTxf = new Ext.form.TextField({
			fieldLabel:'',
			emptyText:'Password',
			anchor:'98%',
			name:'password',
			inputType:'password',
			value:this.rememberme !== null ? remembermeInfo[1] : ''
		});

		this.rememberChk = new Ext.form.Checkbox({
			boxLabel:'Remember me',
			name:'rememberMe',
			labelSeparator:' ',
			checked:this.rememberme !== null ? true : false
		});

		Ext.applyIf(this, {
            items: [
            	{
					layout:'form',
					columnWidth:1,
					border : false,
					style : 'padding-top:8px;',
					items:this.userNameTxf
				},{
					layout:'form',
					columnWidth:1,
					border : false,
					items:this.passwordTxf
				},{
					layout:'form',
					columnWidth:0.8,
					border : false,
					labelAlign:'left',
					labelWidth:0.01,
					items:this.rememberChk
				},{
					layout:'form',
					xtype:'button',
					text:userMsg.login,
					columnWidth:0.2,
					style : 'padding-top:8px; padding-right:5px;',
					iconCls:'login-icon',
					border : false,
					scope:this,
					handler: function() {
						var values = this.getForm().getValues();
						var userName = this.userNameTxf.getValue();
						var password = this.passwordTxf.getValue();
						
						if(Ext.isEmpty(userName)){
							error("Please enter username..!" , "Loging failed ..!" );
						}else if(Ext.isEmpty(password)){
							error("Please enter password..!" , "Loging failed ..!" );
						}else{
							this.handleLoginBtn(values);
						}
					}
				}
			]            
        });
		
		this.passwordTxf.enableKeyEvents = true;
		
		this.passwordTxf.on('keydown',function( comp, e ){
			
			//If enter key is pressed then do the back-end call
			if( e.browserEvent.keyCode == "13"){
				
				var values = this.getForm().getValues();
				this.handleLoginBtn(values);
			}}, this);
		
        login.LoginForm .superclass.initComponent.apply(this, arguments);
	},
	
	handleLoginBtn : function(values){
		frontPage.getEl().mask('Connecting...');//start masking to login screen
		if( this.rememberChk.getValue() ){
				var rememberMeValue =values['userName']+","+values['password'];
				CookieUtil.set(configuration.remembermename,rememberMeValue);
		} else {
				this.rememberme = CookieUtil.get('rememberMe');
				if(this.rememberme !== null){
					CookieUtil.unset(configuration.remembermename);
				}
		}
		UserManagementController.validateUser( values['userName'],values['password'],function(authorisedUser){
			if(authorisedUser !== null) {
				var value = authorisedUser.userId+","+authorisedUser.firstName+" "+authorisedUser.lastName+","+
							authorisedUser.role.description+","+authorisedUser.role.id+","+authorisedUser.password;
				CookieUtil.set(configuration.name,value,configuration.path);
				window.location = configuration.loginLocation;
			} else {
				Ext.Msg.show({
					msg:'invalid username and password!',
					buttons:Ext.Msg.OK
				});
			}
		});	
	}
	
});

Ext.reg('login-form', login.LoginForm);
