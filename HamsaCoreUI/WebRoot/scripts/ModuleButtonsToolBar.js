ModuleButtonsToolBar= Ext.extend(Ext.Toolbar, {
	border : false,
    initComponent : function() {
        Ext.applyIf(this, {
            items :['->', 
	     	 	{
		            xtype: 'label',
		            text:getWelcomeText() // this method in utils.js file
		        },'-',
				{
					xtype:'panel',
					frame: true,
					border: false,
					items:[
						{
							html:'<div  onclick="changePasswordHandler()" style="text-align:center;cursor:pointer;">' +
		            			'<a href="#">Change Password</a></div>'
						}
					]
					
				},
				{
					xtype:'panel',
					frame: true,
					border: false,
					items:[
						{
							html:'<div  onclick="logoutHandler()" style="text-align:center;cursor:pointer;">' +
		        				'<a href="#">Log out</a></div>'
						}
					]
					
				}
		    ]           
        });
        ModuleButtonsToolBar.superclass.initComponent.apply(this, arguments);
    }
});
Ext.reg('module-buttons-toolbar', ModuleButtonsToolBar);