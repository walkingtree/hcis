ApplicationLayout = Ext.extend(Ext.Viewport, {
	width : '100%',
 	border : false ,
    autoScroll : true,
    autoHeight : true,
 	
    initComponent : function() {
    
        this.northPanel = new Ext.Panel({
            region:'north',
            height : 25,
            items :[{
		            xtype: 'north-panel'
		    	}
		    ]
        });
        
        this.westPanel = new Ext.Panel({
	        title: 'Navigation',
            region:'west',
		    width: 230,
		    collapsible: true,
		    collapsed: false,
            items :[{
		            xtype: 'west-panel'
		    	}
		    ]
        });

		this.centerRegionTabPanel = new CenterRegionTabPanel();
        this.centerPanel = new Ext.Panel({
        	//title: 'Main Content',
        	border : false,
        	frame : true,
		    autoScroll : true,
		    height:'100%',
            region:'center',
            layout:'anchor',
            items :[this.centerRegionTabPanel]
        });

        Ext.applyIf(this, {
            layout: 'border',
            items: [
            	this.northPanel, 
            	this.westPanel, 
            	this.centerPanel
            ]            
        });

        ApplicationLayout.superclass.initComponent.apply(this, arguments);
    },
    
    getCenterRegionTabPanel : function(){
    	return this.centerRegionTabPanel;
    }
});

