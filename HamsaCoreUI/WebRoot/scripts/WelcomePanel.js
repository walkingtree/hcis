WelcomePanel= Ext.extend(Ext.Panel, {
	initComponent : function() {
        layout:'column',
		Ext.applyIf(this, {
			columnWidth :.03,
        	items :[
            	 {
		            xtype: 'label',
		            text:getWelcomeText() // this method in utils.js file
		         },
            	{
		            html:'<div  onclick="changePasswordHandler()" style="text-align:center;cursor:pointer;">' +
		            		'Change Password</div>'
		        }, 
		        {
		            xtype: 'tbseparator'
		        }, 
		        {
		        	html:'<div  onclick="logoutHandler()" style="text-align:center;cursor:pointer;">' +
		        			'Log out</div>'
		        }
		    ]           
        });
        ModuleButtonsToolBar.superclass.initComponent.apply(this, arguments);
    }
});
Ext.reg('welcomePanel', WelcomePanel);

function changePasswordHandler(){
	var changePassword = new administration.userManagement.ChangePassword();
	var window = changePassword.getPanel();
	window.show();
	Ext.ux.event.Broadcast.subscribe('closeChangePasswordWindow',function(){
		window.close();
	}, this, null, window);
}

function logoutHandler(){
	CookieUtil.unset(configuration.name,configuration.path);
	window.location =configuration.loginLocation;
}

//{
//            		layout:'column',
//            		columnWidth:.45,
//            		style:'padding-left:30px',
//            		items:[
//            			{
//				            html:'<div  onclick="changePasswordHandler()" style="text-align:center;cursor:pointer;">' +
//				            		'Change Password</div>'
//				        }, 
//				        {
//				            xtype: 'tbseparator'
//				        }, 
//				        {
//				        	html:'<div  onclick="logoutHandler()" style="text-align:center;cursor:pointer;">' +
//				        			'Log out</div>'
//				        }
//            		]
//            	}
//
