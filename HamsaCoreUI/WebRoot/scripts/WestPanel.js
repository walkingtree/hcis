WestPanel = Ext.extend(Ext.Panel, {
    collapsible : false,
    collapsed : false,
    split : true,
    border : false,
    layout:'anchor',
    layoutConfig : {
        animate:true
    },
    initComponent : function() {
        Ext.applyIf(this, {
            items: [{
	                xtype : 'modules-tree-panel',
	                border : false,
	                autoScroll : true
	            }
/*	            ,{
		            xtype : 'latest-activities-grid-panel',
	                border : false,
	                autoScroll : true
	            }
*/			]            
        });
        WestPanel.superclass.initComponent.apply(this, arguments);
    },
    
    getModulesTreePanel : function(){
    	return this.items.items[0];
    }
    
    
});

Ext.reg('west-panel', WestPanel);
