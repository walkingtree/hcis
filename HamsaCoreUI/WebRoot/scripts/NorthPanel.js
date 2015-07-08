NorthPanel = Ext.extend(Ext.Panel, {
	border : false,
	frame:false,
    initComponent : function() {
        Ext.applyIf(this, {
            items: [
	            {
	            	xtype : 'module-buttons-toolbar'
	            }
			]            
        });
        NorthPanel.superclass.initComponent.apply(this, arguments);
    }
});

Ext.reg('north-panel', NorthPanel);
