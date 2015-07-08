CenterRegionTabPanel = Ext.extend(wtccomponents.WTCTabPanel, {
	resizeTabs:true,
    minTabWidth: 115,
    tabWidth:170,
    enableTabScroll:true,
    defaults: {autoScroll:true},
    autoScroll : true,
    border : false ,
    frame: true,
    height: screen.height - 205,
    //plugins:  [new Ext.ux.TabCloseMenu()],
    activeTab : 0,
    initComponent : function() {
    	this.closable = true;
    	this.layoutOnTabChange = true;
        this.on('tabchange', function( tabPanel, tab ){
        	tabPanel.setHeight( screen.height - 205 );
        	tab.setHeight(  screen.height - 245  );
        },this);
        
        Ext.applyIf(this, {
        items: [//new dashboard.DashBoardPanel(),
                new wtccomponents.WorkFlow(),
	            {
	            	xtype : 'reports-panel'
	            }]
        });
        
        
        CenterRegionTabPanel.superclass.initComponent.apply(this, arguments);
    }
});

Ext.reg('center-region-tab-panel', CenterRegionTabPanel);
