Ext.namespace("administration.service_group_package.searchServiceGroup");

function showAddEditServiceGroupWindow( config, mainThis ) {

	var manageService = Ext.getCmp('service-management');
	
	var configServiceGroupPanel = new administration.service_group_package.addServiceGroup.ConfigureServiceGroup(config);
	var panelToAdd = configServiceGroupPanel.getPanel();
	panelToAdd.frame=true;
	panelToAdd.title = config.title; 
	panelToAdd.closable = true;
	panelToAdd.height = 420;

	
	var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
	
	Ext.ux.event.Broadcast.publish('loadAvaliableServiceGrid');
	
	// in case of modify service group we are setting the values.
	if(config.mode == svcAndGrpAndPkgMsg.editMode){
		configServiceGroupPanel.loadData( config );
	};
	Ext.ux.event.Broadcast.publish('getFocusInConfigureServiceGroup');
	
	Ext.ux.event.Broadcast.subscribe('closeConfigureServiceGroupWindow', function(){
//		var toBeRemovePanel = configServiceGroupPanel.getPanel().ownerCt;
//		
//		layout.getCenterRegionTabPanel().remove( toBeRemovePanel, true );
		layout.getCenterRegionTabPanel().setActiveTab( mainThis.searchServiceGroupsPanel );
		layout.getCenterRegionTabPanel().doLayout();
		
		Ext.ux.event.Broadcast.publish('reloadSearchServiceGroupsGrid');
	},this , null ,mainThis.getPanel());
}

administration.service_group_package.searchServiceGroup.SearchServiceGroups = Ext.extend(Object, {
	constructor : function(config) {
		
		Ext.QuickTips.init();
		
		var mainThis = this;
		
		this.serviceGroupRecord = Ext.data.Record.create([
	     	{name: "serviceGroupCode", mapping:'serviceGroupCode'},
	      	{name: "groupName", mapping:'groupName'},
	      	{name: "parentGroup", mapping:'parentGroup', convert: getCode},	      		      	
	      	{name: "parentGroupDesc", mapping:'parentGroup', convert: getDescription},
	     	{name: "serviceSummaryList", mapping:'serviceSummaryList'}
			]);

		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.serviceGroupRecord),
        	proxy: new Ext.data.DWRProxy(ServiceManager.findServiceGroup, true)
		});

		this.pagingBar = new Ext.WTCPagingToolbar({
				store : this.dataStore,
				displayMsg : svcAndGrpAndPkgMsg.displayingServiceGroupsMsg,
				emptyMsg : svcAndGrpAndPkgMsg.noServiceGroupsMsg
			});
		
		
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.deleteBtn.enable();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.deleteBtn.disable();
				}
			}
		});
		
		var gridColumns = 
						[this.gridChk, {
							header : svcAndGrpAndPkgMsg.code,
							dataIndex : 'serviceGroupCode',
							width : 225,
							sortable: true
						}, {
							header : svcAndGrpAndPkgMsg.name,
							dataIndex : 'groupName',
							width : 400,
							sortable: true
						},{
							header : svcAndGrpAndPkgMsg.parentGroup,
							dataIndex : 'parentGroupDesc',
							width : 400,
							sortable: true
						}];
						
		this.addBtn = new Ext.Toolbar.Button({
			iconCls : 'add_btn',
			text : svcAndGrpAndPkgMsg.btnAdd,
			scope:this,
			tooltip: svcAndGrpAndPkgMsg.addServiceGroup,
			handler : function() {
				mainThis.addButtonClicked();
			}
		});
		
		this.editBtn = new Ext.Toolbar.Button({
			iconCls : 'edit_btn',
			text : svcAndGrpAndPkgMsg.btnEdit,
			scope:this,
			disabled:true,
			tooltip: svcAndGrpAndPkgMsg.editServiceGroup,
			handler : function() {
				mainThis.editButtonClicked();
			}
		});
		
		this.deleteBtn = new Ext.Toolbar.Button({
			iconCls : 'delete_btn',
			text : svcAndGrpAndPkgMsg.btnDelete,
			scope:this,
			tooltip: svcAndGrpAndPkgMsg.deleteServiceGroup,
			disabled:true,
			handler:function(){
				mainThis.deleteButtonClicked();
			}
		});
		
		var gridButtonsBar = [
					'-',this.addBtn,
					'-',this.editBtn,
					'-',this.deleteBtn,
					'-'];
		
		this.infoGrid = new Ext.grid.GridPanel({
				frame : false,
				stripeRows : true,
				height: 300,
				autoScroll : true,
				border : false,
				store : this.dataStore,
				bbar : this.pagingBar,
				sm:this.gridChk,
				remoteSort:true,
				viewConfig:{
					forceFit : true
				},
				listeners:{
					cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
						mainThis.setGridButtonsState(thisGrid.getSelectionModel());
					} 
				},
				tbar : gridButtonsBar,
				columns : gridColumns
			});

		this.serviceGroupCodeTxf = new Ext.form.TextField({
							fieldLabel: svcAndGrpAndPkgMsg.code,
							name:'serviceGroupCode',
							anchor:'90%'
		});
		
		this.serviceGroupNameTxf = new Ext.form.TextField({
							fieldLabel: svcAndGrpAndPkgMsg.name,
							name:'groupName',
							anchor:'90%'
		});
				
		this.parentGroupCombo = new Ext.form.OptComboBox({
							fieldLabel: svcAndGrpAndPkgMsg.parentGroup,
							mode:'local',
							store: loadAddServiceGroupCbx(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							hiddenName:'parentGroup',
							anchor:'90%'
		});

		this.serviceNameCombo = new Ext.form.OptComboBox({
							fieldLabel: svcAndGrpAndPkgMsg.serviceName,
							mode:'local',
							store: loadServicesCbx(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							hiddenName:'serviceName',
							anchor:'90%'
		});		

		this.searchBtn = new Ext.Button({
	        text: svcAndGrpAndPkgMsg.btnSearch,
	    	iconCls : 'search_btn',
	    	tooltip: svcAndGrpAndPkgMsg.searchServiceGroup,
	    	handler: function() {
	    		mainThis.searchButtonClicked();
	    	},
	    	scope: this
	    });
	    
	     this.resetBtn = new Ext.Button({
			text:svcAndGrpAndPkgMsg.btnReset,
			iconCls : 'cancel_btn',
			scope:this,
			tooltip: svcAndGrpAndPkgMsg.resetSearchCreteria

		});

	    this.buttonPanel = new Ext.Panel({
			buttonAlign:'right',
			border:false,
			autoHeight: true,
			header: false,
			buttons:[
				this.searchBtn,
				this.resetBtn 
				]
		});	
		
		this.resetBtn.on('click',function(comp, eventObj){
			
			this.resetButtonClicked();
		},this);
		
	    this.searchServiceGroupsPanel = new Ext.form.FormPanel({
				frame : true,
				autoHeight : true,
				border : false,
				monitorValid: true,
				items: [
					{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : .33						
						},
						items: [{
							items: this.serviceGroupCodeTxf
						},{
						    items: this.serviceGroupNameTxf
					    },{
							items: this.parentGroupCombo
						}]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : .33
						},
						items: [{
							items:this.serviceNameCombo
						},{
							columnWidth : .64,
							items: this.buttonPanel
						}]
					},
					this.infoGrid
			    ]
		});
		
		Ext.ux.event.Broadcast.subscribe('reloadSearchServiceGroupsGrid', function(){
			if(mainThis.infoGrid.getStore().data.length > 0){
				mainThis.infoGrid.getStore().reload();
			}
			mainThis.editBtn.disable();
			mainThis.deleteBtn.disable();
		},this , null , mainThis.getPanel());
		
		Ext.ux.event.Broadcast.subscribe('getFocusForFirstElementInSearchSvcGrp', function(){
			mainThis.serviceGroupCodeTxf.focus();
		},this , null , mainThis.getPanel());

	
		this.infoGrid.on("render", function(thisForm){
			this.searchButtonClicked();
		}, this);
		
		this.searchServiceGroupsPanel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);

		
	},
	
	getPanel : function() {
		return this.searchServiceGroupsPanel;
	},
	
	setGridButtonsState : function(selectionModel){
		var selectedRows = selectionModel.getSelections();
		this.editBtn.disable();
		this.deleteBtn.disable();
		if( selectedRows.length == 1){
			this.editBtn.enable();
			this.deleteBtn.enable();
			
		} else if( selectedRows.length > 1){
			this.editBtn.disable();
			this.deleteBtn.enable();
		}
	},
	
	addButtonClicked : function(){
		showAddEditServiceGroupWindow({
			title: svcAndGrpAndPkgMsg.addServiceGroup,
			mode:svcAndGrpAndPkgMsg.addMode,
			isPopUp: true
		}, this);
	},
	
	editButtonClicked : function(){
		var rowSelectedForEdit = this.infoGrid.getSelectionModel().getSelections()[0].data;
		showAddEditServiceGroupWindow({
			title: svcAndGrpAndPkgMsg.editServiceGroup,
			mode:svcAndGrpAndPkgMsg.editMode,
			selectedRow : rowSelectedForEdit,
			isPopUp: true
		}, this);
	},
	deleteButtonClicked: function(){
		var selectedRows = this.infoGrid.getSelectionModel().getSelections();
		Ext.Msg.show({
		   msg: svcAndGrpAndPkgMsg.deleteServiceGroupMsg,
		   buttons: Ext.Msg.YESNO,
		   fn: function( btnValue ){
			   	if( btnValue == configs.yes ){
			   		if(selectedRows.length > 0){
						var servicesGroupCodeList = new Array();
						for(var i = 0;i<selectedRows.length;i++){
							var rowdata = selectedRows[i].data;
							servicesGroupCodeList.push(rowdata.serviceGroupCode);
						}
						ServiceManager.removeServiceGroups(servicesGroupCodeList, function(){
							Ext.ux.event.Broadcast.publish('reloadSearchServiceGroupsGrid');
						   	loadAddServiceGroupCbx().reload();
						});
					}
			   	}
		   },
		   icon: Ext.MessageBox.QUESTION
		});
	},
	
	searchButtonClicked : function(){
   		var valuesMap = this.searchServiceGroupsPanel.getForm().getValues();
   		this.infoGrid.getStore().removeAll();
    		
		this.infoGrid.getStore().load({params:{start:0, limit:this.pagingBar.pageSize}, 
								  arg:[
								  	   valuesMap['serviceGroupCode'], 
  							   		   valuesMap['groupName'],
								  	   valuesMap['parentGroup'], 
  							   		   valuesMap['serviceName']
  							   		   ]
  								});	
		this.setGridButtonsState(this.infoGrid.getSelectionModel());
	},
	resetButtonClicked: function(){
		this.searchServiceGroupsPanel.getForm().reset();
		this.searchButtonClicked();
	}
	
});