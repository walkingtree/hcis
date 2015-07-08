
Ext.namespace("dashboard");

/**
 * this class meant for complete layout for dash board. 
 */
dashboard.DashBoardPanel = Ext.extend(Ext.Panel,{
	initComponent: function(){
		
		this.groupTools = [
			{
			    id:'gear',
			    handler: function(){
			        Ext.Msg.alert('Message', 'The Settings tool was clicked.');
			    }
			},{
			    id:'close',
			    handler: function(e, target, panel){
			        panel.ownerCt.remove(panel, true);
			    }
           }
		];
		/**
		 *  this class is meant for crating portal object, which contains the  different port lets .
		 */
		this.portalPanel = new Ext.ux.Portal({
            title: dashBoardMessages.dashboard,
            tabTip: 'Dashboard tabtip',
            defaults:{ columnWidth:.5},
            items:[{
                style:'padding:10px 0 10px 10px',
                items:[{
                    title: 'Grid in a Portlet',
                    layout:'fit',
                    tools: this.groupTools,
                    items: new SampleGrid([0, 2, 3])
                },{
                    title: 'Another Panel 1',
                    tools: this.groupTools,
                    items: new SampleGrid([0, 2, 3])
                }]
            },{
                style:'padding:10px 0 10px 10px',
                items:[{
                    title: 'Panel 2',
                    tools: this.groupTools,
                    items: new SampleGrid([0, 2, 3])
                },{
                    title: 'Another Panel 2',
                    tools: this.groupTools,
                    items: new SampleGrid([0, 2, 3])
                }]
            },{
                style:'padding:10px',
                items:[{
                    title: 'Panel 3',
                    tools: this.groupTools,
                    items: new SampleGrid([0, 2, 3])
                },{
                    title: 'Another Panel 3',
                    tools: this.groupTools,
                    items: new SampleGrid([0, 2, 3])
                }]
            }]                    
        
		});
		
		this.addGadgetBtn = new Ext.Button({
			text:dashBoardMessages.addGadget,
			scope:this,
			handler:function( comp ){
	    		var window = new Ext.Window({
	    			title : dashBoardMessages.availableGadgets,
	    			height:'50%',
	    			width:'80%',
	    			resizable: false,
	    			items:new dashboard.AvailableGadgetLayout()
	    		}); 
	    		window.show();
	    	}
		});
		this.addCategoryBtn = new Ext.Button({
			text: dashBoardMessages.addCategory
		});
		
		this.addCategoryBtn.on('click', function( comp ){
			
		}, this);
		
		this.toolbar =  new Ext.Toolbar({
			items:[ this.getCategoryBtn(),'->',this.getAddGadgetBtn()]
		});
		
		/**
		 *  this class is meant for creating group tabs. this mainly using for managing new categories.
		 */
		this.groupTabs = new Ext.ux.GroupTabPanel({
			tabWidth: 130,
			activeGroup: 0,
			items: [{
				mainItem: 1,
				items: [{
					title: 'Tickets',
	                layout: 'fit',
	                iconCls: 'x-icon-tickets',
	                tabTip: 'Tickets tabtip',
	                style: 'padding: 10px;',
					items: [new SampleGrid([0,1,2,3,4])]
				}, 
				this.getPortalPanel(),
				{
					title: 'Subscriptions',
	                iconCls: 'x-icon-subscriptions',
	                tabTip: 'Subscriptions tabtip',
	                style: 'padding: 10px;',
					layout: 'fit',
					items: [{
						xtype: 'tabpanel',
						activeTab: 1,
						items: [{
							title: 'Nested Tabs',
							items: new SampleGrid([0, 2, 3])
						}]	
					}]	
				}, {
					title: 'Users',
	                iconCls: 'x-icon-users',
	                tabTip: 'Users tabtip',
	                style: 'padding: 10px;',
	                items: new SampleGrid([0, 2, 3])		
				}]
	        }, {
	            expanded: true,
	            items: [{
	                title: 'Configuration',
	                iconCls: 'x-icon-configuration',
	                tabTip: 'Configuration tabtip',
	                style: 'padding: 10px;',
	                items: new SampleGrid([0, 2, 3])
	            }, {
	                title: 'Email Templates',
	                iconCls: 'x-icon-templates',
	                tabTip: 'Templates tabtip',
	                style: 'padding: 10px;',
	                items: new SampleGrid([0, 2, 3])
	            }]
	        }]
		
		});
		
		Ext.apply(this,{
			title:dashBoardMessages.dashboard,
		    layout:'fit',
		    items:[ this.getGroupTabsPanel() ],
		    tbar:this.toolbar
		});
		
		dashboard.DashBoardPanel.superclass.initComponent.apply( this, arguments );
	},
	getGroupTabsPanel: function(){
		return this.groupTabs;
	},
	getPortalPanel: function(){
		return this.portalPanel;
	},
	getAddGadgetBtn : function(){
		return this.addGadgetBtn;
	},
	getCategoryBtn : function(){
		return this.addCategoryBtn;
	}
});
