Ext.namespace("login");

login.TitleAndLoginForm = Ext.extend(Ext.Panel, {
	border : false,
    layout : 'column',
    
 	initComponent : function() {
	   this.loginForm = new login.LoginForm();
	   Ext.applyIf(this, {
 	        items: [
				{	
	        		border : false,
	        		columnWidth:0.7,
	        		style : 'padding-left:20px;',
					html: '<font color="#3B5998" face="calibri" size="10"> <b>HaMSa<b/> </font>' + 
						  '<p><font color="#3B5998" face="calibri" size="2">empowering people and healthcare providers</p></font></div>'				
				},{
	        		columnWidth:0.3,
	        		border : false,				
	        		xtype:'login-form'
	        	}
	        ]            
	   });
       login.TitleAndLoginForm.superclass.initComponent.apply(this, arguments);
	}
});

Ext.reg('title-login-form', login.TitleAndLoginForm);
