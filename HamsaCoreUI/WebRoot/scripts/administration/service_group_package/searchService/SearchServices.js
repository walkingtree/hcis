Ext.namespace("administration.service_group_package.searchService");


function showAddEditServiceWindow(config, mainThis) {

	var configServicePanel = new administration.service_group_package.addService.ConfigureService(config);
	var panelToAdd = configServicePanel.getPanel();
	panelToAdd.frame=true;
	panelToAdd.title = config.title; 
	panelToAdd.closable = true;
	
	
	var panel = layout.getCenterRegionTabPanel().add( panelToAdd );
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
				
	if( config.mode == svcAndGrpAndPkgMsg.addMode ){
			configServicePanel.setDefaultValues();
	}
	
	if(config.mode == svcAndGrpAndPkgMsg.editMode){
		configServicePanel.loadData(config);
	}
	Ext.ux.event.Broadcast.publish('getFocusInConfigureService');
	
	Ext.ux.event.Broadcast.subscribe('closeConfigureServiceWindow', function(){
//		var toBeRemovePanel = configServicePanel.getPanel().ownerCt;
//		
//		layout.getCenterRegionTabPanel().remove( toBeRemovePanel, true );
		layout.getCenterRegionTabPanel().setActiveTab( mainThis.searchServicesPanel.ownerCt );
		layout.getCenterRegionTabPanel().doLayout();
		
		Ext.ux.event.Broadcast.publish('reloadSearchServicesGrid');
	},this,null,mainThis.getPanel());
}

administration.service_group_package.searchService.SearchServices = Ext.extend(Object, {
	constructor : function(config) {
		Ext.QuickTips.init();
		
		var mainThis = this;
		this.widgets = new administration.service_group_package.searchService.Widgets();
		
		this.serviceRecord = Ext.data.Record.create([
	     	{name: "serviceCode", mapping:'serviceCode'},
	      	{name: "serviceName", mapping:'serviceName'},
	      	{name: "serviceDesc", mapping:'serviceDesc'},
	      	{name: "serviceGroup", mapping:'serviceGroup', convert: getCode},	
	      	{name: "serviceType", mapping:'serviceType', convert: getCode},
	      	{name: "serviceTypeDesc", mapping:'serviceType', convert: getDescription},
	      	{name: "serviceGroupDesc", mapping:'serviceGroup', convert: getDescription},
	      	{name: "department", mapping:'department', convert: getCode},	      		      	
	      	{name: "departmentDesc", mapping:'department', convert: getDescription},
	      	{name: "serviceStatus", mapping:'serviceStatus', convert: getCode},
	      	{name: "statusDesc", mapping:'serviceStatus', convert: getDescription},
	      	{name: "serviceCharge", mapping:'serviceCharge', convert:convertToAmount},
	      	{name: "serviceProcedure", type: "string", mapping:'serviceProcedure'},
	      	{name: "effectiveFromDate", type: "date"},
	      	{name: "effectiveToDate", type: "date"},
	      	{name: "suspendedFromDt", type: "date"},
	      	{name: "suspendedToDt", type: "date"},
	      	{name:'depositAmount', mapping:'depositAmount'}
			]);

		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.serviceRecord),
        	proxy: new Ext.data.DWRProxy(ServiceManager.findServices, true)
		});

		this.pagingBar = new Ext.WTCPagingToolbar({
				store : this.dataStore,
				displayMsg : svcAndGrpAndPkgMsg.displayingServicesMsg,
				emptyMsg : svcAndGrpAndPkgMsg.noServicesMsg
			});
		
		
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.editTemplateBtn.disable();
					mainThis.deleteBtn.enable();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.editTemplateBtn.disable();
					mainThis.deleteBtn.disable();
				}
			}
		});
		
		this.action = new Ext.ux.grid.RowActions( {
							header : 'Actions',
							keepSelection : true,
							widthSlope: 50,
							actions : [ {
								iconCls : 'view-icon',
								tooltip : 'View price details',
								align:'right'
							}

							]
						});
		
		this.action.on({
			action:function(grid, record, action, row, col) {
			
			if (action === 'view-icon') {
					var priceDetailsPanel = new administration.service_group_package.priceUpdate.PriceDetailsPanel(
							{ serviceCode : record.data.serviceCode });
					
					priceDetailsPanel.loadGridData(record.data.serviceCode,record.data.serviceCharge);
					
					mainThis.priceDetailWindow = new Ext.Window({
						title: 'Price details for ' + record.data.serviceName,
						items:priceDetailsPanel,
						frame:true,
						height:'40%',
						width:'50%'
					});
					mainThis.priceDetailWindow.show();
			}
		}
		});
		
		var gridColumns = 
						[this.gridChk, {
							header : svcAndGrpAndPkgMsg.code,
							dataIndex : 'serviceCode',
							width : 125,
							sortable: true
						}, {
							header : svcAndGrpAndPkgMsg.name,
							dataIndex : 'serviceName',
							width : 200,
							sortable: true
						},{
							header : svcAndGrpAndPkgMsg.serviceGroup,
							dataIndex : 'serviceGroupDesc',
							width : 100,
							sortable: true
						},{
							header : svcAndGrpAndPkgMsg.department,
							dataIndex : 'departmentDesc',
							width : 150,
							sortable: true
						},{
							header : 'Service Type',
							dataIndex : 'serviceTypeDesc',
							width : 125,
							sortable: true
						},{
							header : svcAndGrpAndPkgMsg.status,
							dataIndex : 'statusDesc',
							width : 75,
							sortable: true
						},{
							header : svcAndGrpAndPkgMsg.charge,
							dataIndex : 'serviceCharge',
							width : 75,
							align:'right',
							sortable: true
						},this.action];
						
		
		this.addBtn = this.widgets.getAddBtn();
		
		this.addBtn.on('click', function(){
			this.addButtonClicked();
		},this);
		
		this.editBtn = this.widgets.getEditBtn();
		
		
		
		this.editBtn.on('click', function(){
			this.editButtonClicked();
		},this);
		
		this.deleteBtn =this.widgets.getDeleteBtn();
		
		this.deleteBtn.on('click', function(){
			this.deleteButtonClicked();
		},this);
		
		this.editTemplateBtn = this.widgets.getEditTemplateBtn();
		
		this.widgets.getEditTemplateBtn().on('click',function(){
			this.editTemplateBtnClicked();
		},this);
		
		var gridButtonsBar = [
					'-',this.addBtn,
					'-',this.editBtn,
//					'-',this.deleteBtn,
					'-',this.editTemplateBtn ];
		
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
				columns : gridColumns,
				plugins:[ this.action ]
			});

		this.searchBtn = this.widgets.getSearchBtn();
		this.resetBtn = this.widgets.getResetBtn();
		
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
		
		this.searchBtn.on('click',function(comp, eventObj){
			
			this.searchButtonClicked();
		},this);
		
		this.resetBtn.on('click',function(comp, eventObj){
			
			this.resetButtonClicked();
		},this);
	    
	    this.searchServicesPanel = new Ext.form.FormPanel({
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
							items:this.widgets.getServiceCodeTxf()
						},{
						    items:this.widgets.getServiceNameTxf()
					    },{
						    items:this.widgets.getServiceDescTxf()
					    }]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : .33
						},
						items: [{
							items:this.widgets.getServiceGroupCombo()
						},{
						    items:this.widgets.getDepartmentCombo()
					    },{
						    items:this.widgets.getStatusCombo()
					    }]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : .33
						},
						items: [{
							items:this.widgets.getCreatedAfterDtFld()
						},{
							items:this.widgets.getCreatedBeforeDtFld()
						},{
							items:this.widgets.getProcedureTxf()
						}]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : .33
						},
						items: [{
							items:this.widgets.getChargesFromNbrField()
						},{
							items:this.widgets.getChargesToNbrField()
						},{
							items:this.buttonPanel
						}]
					},
					this.infoGrid
			    ]
		});
		Ext.ux.event.Broadcast.subscribe('reloadSearchServicesGrid', function(){
			if(mainThis.infoGrid.getStore().data.length > 0){
				mainThis.infoGrid.getStore().reload();
			}
			mainThis.editBtn.disable();
			mainThis.editTemplateBtn.disable();
			mainThis.deleteBtn.disable();
		},this , null , mainThis.getPanel());
		
		Ext.ux.event.Broadcast.subscribe('getFocusForFirstElementInSearchServices', function(){
			mainThis.widgets.getServiceCodeTxf().focus()
		}, this,null, mainThis.getPanel());
		
		this.infoGrid.on("render", function(thisForm){
			this.searchButtonClicked();
		}, this);
		
		this.searchServicesPanel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);

	
	},
	
	getPanel : function() {
		return this.searchServicesPanel;
	},
	
	setGridButtonsState : function(selectionModel){
		var selectedRows = selectionModel.getSelections();
		this.editBtn.disable();
		this.editTemplateBtn.disable();
		this.deleteBtn.disable();
		if( !Ext.isEmpty( selectedRows[0])){
			var serviceType = selectedRows[0].data.serviceType;
		}
		if( selectedRows.length == 1){
			this.editBtn.enable();
			if( serviceType === svcAndGrpAndPkgMsg.serviceTypeLaboratory ){
				this.editTemplateBtn.enable();
			}	
			this.deleteBtn.enable();
			
		} else if( selectedRows.length > 1){
			this.editBtn.disable();
			if( serviceType === svcAndGrpAndPkgMsg.serviceTypeLaboratory ){
				this.editTemplateBtn.disable();
			}
			this.deleteBtn.enable();
		}
	},
	
	addButtonClicked : function(){
		showAddEditServiceWindow ({
			title: svcAndGrpAndPkgMsg.addService,
			mode:svcAndGrpAndPkgMsg.addMode,
			isPopUp: true
		}, this);
	},
	
	editButtonClicked : function(){
		var rowSelectedForEdit = this.infoGrid.getSelectionModel().getSelections()[0].data;
		showAddEditServiceWindow ({
			title: svcAndGrpAndPkgMsg.editService,
			mode:svcAndGrpAndPkgMsg.editMode,
			selectedRow : rowSelectedForEdit,
			isPopUp: true
		}, this);
	},
	
	deleteButtonClicked: function(){
		var selectedRows = this.infoGrid.getSelectionModel().getSelections();
		Ext.Msg.show({
		   msg: svcAndGrpAndPkgMsg.deleteServiceMsg,
		   buttons: Ext.Msg.YESNO,
		   fn: function( btnValue ){
		   		if( btnValue == configs.yes){
		   			if(selectedRows.length>0){
						var servicesList = new Array();
						for(var i = 0;i<selectedRows.length;i++){
							var rowdata = selectedRows[i].data;
							servicesList.push(rowdata.serviceCode);
						}
						ServiceManager.removeServices(servicesList, function(){
							Ext.ux.event.Broadcast.publish('reloadSearchServicesGrid');
							loadServicesCbx().reload();
						});
					}
		   		}
		   },
		   icon: Ext.MessageBox.QUESTION
		});
	},
	
	searchButtonClicked : function(){
   		var valuesMap = this.searchServicesPanel.getForm().getValues();
   		this.infoGrid.store.removeAll();
    		
		this.infoGrid.store.load({params:{start:0, limit:this.pagingBar.pageSize}, 
								  arg:[
  							   		   valuesMap['serviceName'],
  							   		   valuesMap['serviceCode'],
  							   		   valuesMap['serviceGroup'],
  							   		   valuesMap['department'], 
  							   		   valuesMap['serviceDesc'], 
  							   		   valuesMap['serviceProcedure'],
  							   		   valuesMap['status'],
  							   		   getStringAsWtcDateFormat( valuesMap['createdBefore'] ),
  							   		   getStringAsWtcDateFormat( valuesMap['createdAfter'] ),
  							   		   valuesMap['chargesFrom'],
  							   		   valuesMap['chargesTo']
  							   		   ]
  								});	
		this.editBtn.disable();
		this.editTemplateBtn.disable();
		this.deleteBtn.disable();
	},
	resetButtonClicked: function(){
		this.searchServicesPanel.getForm().reset();
		this.searchButtonClicked();
	},
	editTemplateBtnClicked : function(){
		
		var mainThis = this;
		
		var testCode  = this.infoGrid.getSelectionModel().getSelections()[0].data.serviceCode;
		
		TestTemplateManager.getWidgetsForTemplate( testCode ,function( testTemplateBM ){
			var activeTab;
			if( !Ext.isEmpty( testTemplateBM )){
				
				var configureTestTemplate = new LIMS.testTemplate.configure.ConfigureTestTemplate({testTemplateBM : testTemplateBM,
																								   testCode : testCode ,
																								   isEditTemplate : true});
				activeTab = layout.getCenterRegionTabPanel().add({
					frame:true,
					title : limsMsg.editTestTemplate, 
					closable : true,
					height : 430,
					items : [configureTestTemplate]
				});
				
				layout.getCenterRegionTabPanel().setActiveTab( activeTab );
			}
			Ext.ux.event.Broadcast.subscribe('closeAddEditTemplate', function(){
				layout.getCenterRegionTabPanel().remove( activeTab , true);
				layout.getCenterRegionTabPanel().setActiveTab( mainThis.searchServicesPanel.ownerCt );
				layout.getCenterRegionTabPanel().doLayout();
			},this, null, mainThis.getPanel());
		});
	}
	
});