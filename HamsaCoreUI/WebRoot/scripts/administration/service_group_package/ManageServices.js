
Ext.namespace("wtc.hcis.service_group_package");

wtc.hcis.service_group_package.ManageServices = Ext.extend(Ext.Panel, {
	id: 'service-management',
	initComponent : function() {
	 	this.searchService = new wtc.hcis.service_group_package.SearchServices.SearchServices();
		Ext.apply(this, {
			border:false,
			width:'100%',
			height:600,
			frame:true,
			autoScroll : true,
			layout: 'card',
			activeItem: 0,
			deferredRender:false,
			items: [{
						autoScroll: true,
						title: 'Manage services',
						items:this.searchService.getPanel()
					}]
		});
		
		
		
		
		this.tbar = new Ext.Toolbar({
			items: [{
				xtype :'tbbutton',
				text :'Services',
				menu : [ {
				text: 'New',
				scope : this,
				handler: function() {
					if (!Ext.isEmpty(this.configureService )) {
						this.remove(this.layout.activeItem);
					}
					this.configureService = new wtc.hcis.service_group_package.AddService.ConfigureService({
						mode: svcAndGrpAndPkgMsg.addMode,
						isPopUp : false
					});

					var pnl = new Ext.Panel({
						autoScroll: true,
						title: 'Add service',
						items: this.configureService.getPanel()
					});
					this.add(pnl);
					this.layout.setActiveItem(pnl.getId());
					this.layout.activeItem.doLayout();
					Ext.ux.event.Broadcast.publish('getFocusInConfigureService');
					//Ext.ux.event.Broadcast.publish('setDefaultValuesInService');
					
				}
			}, {
				text: 'Manage',
				scope : this,
				handler: function() {
					this.layout.setActiveItem(0);
					if (!Ext.isEmpty( this.searchService )) {
						this.remove(this.layout.activeItem);
					}
					this.searchService = new wtc.hcis.service_group_package.SearchServices.SearchServices();
					var pnl = new Ext.Panel({
						autoScroll: true,
						title:'Manage services',
						items:this.searchService.getPanel() 
					});
					this.insert(0, pnl);
					this.layout.setActiveItem(pnl.getId());
					this.layout.activeItem.doLayout();
					Ext.ux.event.Broadcast.publish('reloadSearchServicesGrid');
					Ext.ux.event.Broadcast.publish('getFocusForFirstElementInSearchServices');
				}
			}]
			},'-',{
				xtype :'tbbutton',
				text :'Service groups',
				menu : [ {
					text: 'New',
					scope : this,
					handler: function() {
						if (!Ext.isEmpty( this.configureServiceGroup )) {
							this.remove(this.layout.activeItem);
						}
						this.configureServiceGroup = new wtc.hcis.service_group_package.ConfigureServiceGroup({
							mode: svcAndGrpAndPkgMsg.addMode,
							isPopUp : false
						});
	
						var pnl = new Ext.Panel({
							autoScroll: true,
							title: 'Add service group',
							items: this.configureServiceGroup.getPanel()
						});
						this.add(pnl);
						this.layout.setActiveItem(pnl.getId());
						this.layout.activeItem.doLayout();
						Ext.ux.event.Broadcast.publish('getFocusInConfigureServiceGroup');
						Ext.ux.event.Broadcast.publish('loadAvaliableServiceGrid');
						
					}
				}, {
					text: 'Manage',
					scope : this,
					handler: function() {
						if (!Ext.isEmpty(this.searchServiceGroup )) {
							this.remove(this.layout.activeItem);
						}
						this.searchServiceGroup = new wtc.hcis.service_group_package.SearchServiceGroups();
						var pnl = new Ext.Panel({
							autoScroll: true,
							title: 'Manage service groups',
							items: this.searchServiceGroup .getPanel()
						});
						this.add(pnl);
						this.layout.setActiveItem(pnl.getId());
						this.layout.activeItem.doLayout();
						Ext.ux.event.Broadcast.publish('reloadSearchServiceGroupsGrid');
						Ext.ux.event.Broadcast.publish('getFocusForFirstElementInSearchSvcGrp');
					}
				}]
			},'-',{
				xtype :'tbbutton',
				text :'Packages',
				menu : [ {
					text: 'New',
					scope : this,
					handler: function() {
						if (!Ext.isEmpty( this.configurePackage )) {
							this.remove(this.layout.activeItem);
						}
						this.configurePackage = new wtc.hcis.service_group_package.ConfigurePackage({
							mode: svcAndGrpAndPkgMsg.addMode,
							isPopUp : false
						});
	
						var pnl = new Ext.Panel({
							title: 'Add package',
							items: this.configurePackage.getPanel(),
							//height:500,
							autoScroll : true
						});
						this.add(pnl);
						this.layout.setActiveItem(pnl.getId());
						this.layout.activeItem.doLayout();
						Ext.ux.event.Broadcast.publish('getFocusInConfigurePackage');
						Ext.ux.event.Broadcast.publish('setDefaultValues');
					}
				}, {
					text:  'Manage',
					scope : this,
					handler: function() {
						if (!Ext.isEmpty( this.searchPackage )) {
							this.remove(this.layout.activeItem);
						}
						this.searchPackage = new wtc.hcis.service_group_package.SearchPackages();
						var pnl = new Ext.Panel({
							autoScroll: true,
							title: 'Manage packages',
							items:this.searchPackage.getPanel()
						});
						this.add(pnl);
						this.layout.setActiveItem(pnl.getId());
						this.layout.activeItem.doLayout();
						Ext.ux.event.Broadcast.publish('reloadSearchPackagesGrid');
						Ext.ux.event.Broadcast.publish('getFocusFirstElementInSearchPackages');
						
					}
				}]
			} ,'-',{
				xtype :'tbbutton',
				text :'Service assignments',
				menu : [ {
					text:  'Manage',
					scope : this,
					handler: function() {
						if (!Ext.isEmpty( this.manageServiceAssignment )) {
							this.remove(this.layout.activeItem);
						}
						this.manageServiceAssignment = new wtc.hcis.manageServiceAssignments.ManageServiceAssignment();
						var pnl = new Ext.Panel({
							autoScroll: true,
							title: 'Manage service assignments',
							items:this.manageServiceAssignment.getPanel()
						});
						this.add(pnl);
						this.layout.setActiveItem(pnl.getId());
						this.layout.activeItem.doLayout();
						Ext.ux.event.Broadcast.publish('getFocusForFirstElementInManageServiceAssignment');
						
					}
			}]
			}]
		});
	wtc.hcis.service_group_package.ManageServices.superclass.initComponent.apply(this, arguments); 
	},
	onRender : function() {
		wtc.hcis.service_group_package.ManageServices.superclass.onRender.apply(this, arguments);
	}
});
