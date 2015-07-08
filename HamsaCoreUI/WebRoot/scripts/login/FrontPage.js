Ext.namespace("login");

login.FrontPage = Ext.extend(Ext.Viewport, {
 	border : false ,
    autoScroll : true,
    autoHeight : true,
	frame : false,
    initComponent : function() {
    
        this.northPanel = new Ext.Panel({
        	border : false,
            region:'north',
            frame : false,
            height : 100,
            items :[{
		            xtype: 'title-login-form'
		            //html: '<div"><img src="images/facebook.jpg" width="100%"  height="100%"/>'
		           }
		    ]
        });

        this.centerPanel = new Ext.Panel({
        	border : false,
        	frame : false,
		    autoScroll : true,
		    autoHeight : true,
            region:'center',
            //height : 400,
            items :[{html: '<div"><img src="images/facebook.jpg" width="100%"  height="100%"/>'}]
        });

        Ext.applyIf(this, {
            layout: 'border',
			border : false,
            items: [
            	this.northPanel,
            	this.centerPanel
            ]            
        });

        login.FrontPage.superclass.initComponent.apply(this, arguments);
    }
});

