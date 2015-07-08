TitlePanel= Ext.extend(Ext.Panel, {
	border : false,
    initComponent : function() {
        Ext.applyIf(this, {
            items :[
	            {					
					html: '<div style="height:45; width:45; padding-left:25px;"><img src="images/WTClogo.png" height=45 width=45/>'				
				}
		    ]           
        });
        TitlePanel.superclass.initComponent.apply(this, arguments);
    }
});
Ext.reg('title-panel', TitlePanel);