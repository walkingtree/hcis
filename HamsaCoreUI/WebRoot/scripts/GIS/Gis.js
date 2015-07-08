
Ext.namespace('GIS');
GIS.gis = Ext.extend(Ext.Panel, {
    height: 525,
 	border : false ,
    autoScroll : true,
    layout:'border',
 	
    initComponent : function() {
    
    	this.mapObj = new Ext.ux.GMapPanel({
		  	region: 'center',
			zoomLevel:5,
			gmapType: 'map',
			setCenter:{
				lat: '22.440830',
				'long': '78.448330'
			}
	  	});
		
        this.northPanel = new Ext.Panel({
            region:'north',
            height : 25,
            layout:'fit',
            items :[
            	
		    ]
        });
        
//        this.eastPanel = new Ext.Panel({
//	        title: 'Navigation',
//            region:'east',
//		    width: 230,
//		    collapsible: true,
//		    collapsed: false,
//            items :[
//            	{
////		            xtype: 'east-panel'
//		    	}
//		    ]
//        });
        
//         this.southPanel = new Ext.Panel({
//	        title: 'Navigation',
//            region:'south',
//		    width: 230,
//		    collapsible: true,
//		    collapsed: false,
//            items :[
//            	{
//            		
////		            xtype: 'south-panel'
//		    	}
//		    ]
//        });
        
        this.westPanel = new GIS.westpanel();
        this.westPanel = new Ext.Panel({
	        title: msg.criteria,
            region:'west',
		    width: 150,
		    collapsible: true,
		    collapsed: false,
		    layout:'fit',
            items :[
            		this.westPanel
//		            xtype: 'west-panel'
		    	    ]
        });

        this.centerPanel = new Ext.Panel({
        	//title: 'Main Content',
        	border : false,
        	frame : true,
		    autoScroll : true,
		    height:350,
            region:'center',
            layout:'fit',
            items :[ this.mapObj ]
        });

//        Ext.applyIf(this, {
//            layout: 'border',
//            items: [
////             	this.eastPanel,
////            	this.northPanel, 
//            	this.westPanel, 
//            	this.centerPanel
////            	this.southPanel
//            ]            
//        });
        
        
        Ext.apply( this,{
        	items :[
//            	this.eastPanel,
//            	this.northPanel, 
            	this.westPanel, 
            	this.centerPanel
//            	this.southPanel
            ]
        
        });
       
        GIS.gis.superclass.initComponent.apply(this, arguments);
    },
    getPanel : function(){
    	return this;
    }
});