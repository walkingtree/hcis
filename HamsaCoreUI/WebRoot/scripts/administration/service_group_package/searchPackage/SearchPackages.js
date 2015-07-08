Ext.namespace("administration.service_group_package.searchPackage");


function showAddEditPackageWindow(config, mainThis ) {

	var configPackagePanel = new administration.service_group_package.addPackage.ConfigurePackage( config );
	var panelToAdd = configPackagePanel.getPanel();
	panelToAdd.frame=true;
	panelToAdd.title = config.title; 
	panelToAdd.closable = true;
	panelToAdd.height = 420;
	

	var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
		
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
	
	if( config.mode == svcAndGrpAndPkgMsg.editMode ){
		configPackagePanel.loadData(config);
	}
	Ext.ux.event.Broadcast.subscribe('closeConfigurePackageWindow', function(){
//		var toBeRemovePanel = configPackagePanel.getPanel().ownerCt;
//		
//		layout.getCenterRegionTabPanel().remove( toBeRemovePanel, true );
		layout.getCenterRegionTabPanel().setActiveTab( mainThis.searchPackagesPanel.ownerCt );
		layout.getCenterRegionTabPanel().doLayout();
		
		Ext.ux.event.Broadcast.publish('reloadSearchPackagesGrid');
	},this,null,mainThis.getPanel());
}

administration.service_group_package.searchPackage.SearchPackages = Ext.extend(Object, {
	constructor : function(config) {
		
		Ext.QuickTips.init();
		
		var mainThis = this;
		
		this.packageRecord = Ext.data.Record.create([
	     	{name: "packageId", mapping:'packageId'},
	      	{name: "name", mapping:'name'},
	      	{name: "packageCharge", mapping:'effectiveCharge', convert:convertToAmount},
	      	{name: "servicePackageStatus", mapping:'servicePackageStatus', convert:getCode},
	      	{name: "servicePackageStatusDesc", mapping:'servicePackageStatus', convert:getDescription},
	      	{name: "chargeOverrideLevel", mapping:'chargeOverrideLevel'},
	      	{name: "chargeOverrideLevelLbl", mapping:'chargeOverrideLevel', convert: getChargeOverrideLevelLbl },
	      	{name:'effectiveFromDt', mapping: 'effectiveFromDt'},
	      	{name:'effectiveToDt', mapping:'effectiveToDt'}
			]);

		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.packageRecord),
        	proxy: new Ext.data.DWRProxy(ServiceManager.findServicePackages, true)
		});

		this.pagingBar = new Ext.WTCPagingToolbar({
				store : this.dataStore,
				displayMsg : svcAndGrpAndPkgMsg.displayingServicePackagesMsg,
				emptyMsg : svcAndGrpAndPkgMsg.noServicePackageMsg
			});
		this.pagingBar.refresh.handler = function(){
			mainThis.refreshH( mainThis.pagingBar );
		}
		
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
							dataIndex : 'packageId',
							width : 80,
							sortable: true
						}, {
							header : svcAndGrpAndPkgMsg.name,
							dataIndex : 'name',
							width : 230,
							sortable: true
						},{
							header : svcAndGrpAndPkgMsg.effectiveCharge,
							dataIndex : 'packageCharge',
							width : 100,
							align:'right',
							sortable: true
						},{
							header : svcAndGrpAndPkgMsg.status,
							dataIndex : 'servicePackageStatusDesc',
							width : 80,
							sortable: true
						},{
							header : svcAndGrpAndPkgMsg.effectiveFrom,
							dataIndex : 'effectiveFromDt',
							renderer : Ext.util.Format.dateRenderer('d/m/Y'),
							width : 80,
							sortable: true
						},{
							header : svcAndGrpAndPkgMsg.effectiveTo,
							dataIndex : 'effectiveToDt',
							renderer : Ext.util.Format.dateRenderer('d/m/Y'),
							width : 70,
							sortable: true
						},{
							header : svcAndGrpAndPkgMsg.priceOverrideLevel,
							dataIndex : 'chargeOverrideLevelLbl',
							width : 115,
							sortable: true
						}];
						
		this.addBtn = new Ext.Toolbar.Button({
			iconCls : 'add_btn',
			text : svcAndGrpAndPkgMsg.btnAdd,
			scope:this,
			tooltip: svcAndGrpAndPkgMsg.addServicePackage,
			handler : function() {
				mainThis.addButtonClicked();
			}
		});
		
		this.editBtn = new Ext.Toolbar.Button({
			iconCls : 'edit_btn',
			text : svcAndGrpAndPkgMsg.btnEdit,
			scope:this,
			tooltip: svcAndGrpAndPkgMsg.editServicePackage,
			disabled:true,
			handler : function() {
				mainThis.editButtonClicked();
			}
		});
		
		this.deleteBtn = new Ext.Toolbar.Button({
			iconCls : 'delete_btn',
			text : svcAndGrpAndPkgMsg.btnDelete,
			scope:this,
			disabled:true,
			tooltip: svcAndGrpAndPkgMsg.deleteServicePackage,
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
//				loadMask: true,
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

		this.packageCodeTxf = new Ext.form.TextField({
							fieldLabel:svcAndGrpAndPkgMsg.code,
							name:'packageId',
							anchor:'90%'
		});
		
		this.packageNameTxf = new Ext.form.TextField({
							fieldLabel:svcAndGrpAndPkgMsg.name,
							name:'name',
							anchor:'90%'
		});
				
		this.packageDescTxf = new Ext.form.TextField({
							fieldLabel:svcAndGrpAndPkgMsg.description,
							name:'description',
							anchor:'90%'
		});

		this.serviceCombo = new Ext.form.OptComboBox({
							fieldLabel:svcAndGrpAndPkgMsg.serviceName,
							mode:'local',
							store: loadServicesCbx(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							hiddenName:'serviceName',
							anchor:'90%'
		});

		this.chargeOverrideLevelCombo = new Ext.form.OptComboBox({
							fieldLabel:svcAndGrpAndPkgMsg.overrideLevel,
							mode:'local',
							store: loadChargeOverrideTypeCbx(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							hiddenName:'chargeOverrideLevel',
							anchor:'80%'
		});

		this.servicePackageStatusCombo = new Ext.form.OptComboBox({
							fieldLabel:svcAndGrpAndPkgMsg.status,
							mode:'local',
							store: loadPackageServiceStatusCbx(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							hiddenName:'servicePackageStatus',
							anchor:'80%'
		});		
		
		this.createdAfterDtFld = new Ext.form.WTCDateField({
							fieldLabel:svcAndGrpAndPkgMsg.createdAfter,
							name:'createdAfter',
							anchor:'80%'
		});
		
		this.createdBeforeDtFld = new Ext.form.WTCDateField({
							fieldLabel:svcAndGrpAndPkgMsg.createdBefore,
							name:'createdBefore',
							anchor:'80%'
		});
		
		this.effectiveFromDtFld = new Ext.form.WTCDateField({
							fieldLabel:svcAndGrpAndPkgMsg.effectiveFrom,
							name:'effectiveFrom',
							anchor:'80%'
		});
		
		this.effectiveToDtFld = new Ext.form.WTCDateField({
							fieldLabel:svcAndGrpAndPkgMsg.effectiveTo,
							name:'effectiveTo',
							anchor:'80%'
		});
		
		this.searchBtn = new Ext.Button({
	        text: svcAndGrpAndPkgMsg.btnSearch,
	        xtype: 'button',
	    	iconCls : 'search_btn',
	    	tooltip: svcAndGrpAndPkgMsg.searchServicePackage,
	    	handler: function() {
	    		this.searchButtonClicked();
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
		
	    this.searchPackagesPanel = new Ext.form.FormPanel({
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
							columnWidth : .33,
							labelWidth:100						
						},
						items: [{
							items:this.packageCodeTxf 
						},{
						    items:this.packageNameTxf
					    },{
						    items:this.packageDescTxf
					    }]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : .33,
							labelWidth:100
						},
						items: [{
							items:this.serviceCombo
						},{
						    items:this.effectiveFromDtFld
					    },{
						    items:this.effectiveToDtFld
					    }]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : .33,
							labelWidth:100
						},
						items: [{
							items:this.createdAfterDtFld
						},{
							items:this.createdBeforeDtFld
						},{
						    items:this.servicePackageStatusCombo
					    }]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : .33,
							labelWidth:100
						},
						items: [{
							items: this.chargeOverrideLevelCombo
						}]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : 1,
							labelWidth:100
						},
						items: this.buttonPanel
					},
					this.infoGrid
			    ]
		});
		Ext.ux.event.Broadcast.subscribe('reloadSearchPackagesGrid', function(){
			if(mainThis.infoGrid.getStore().data.length > 0){
				mainThis.infoGrid.getStore().reload();
			}
			mainThis.setGridButtonsState(mainThis.infoGrid.getSelectionModel());
		},this , null, mainThis.getPanel());
		
		Ext.ux.event.Broadcast.subscribe('resetBtnSearchPackages', function(){
			mainThis.resetButtonClicked();
		},this , null, mainThis.getPanel());
		
		this.initialListeners();
		
		this.infoGrid.on("render", function(thisForm){
			this.searchButtonClicked();
		}, this);
		
		this.searchPackagesPanel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
		
		Ext.ux.event.Broadcast.subscribe('getFocusFirstElementInSearchPackages', function(){
			mainThis.packageCodeTxf.focus();
		}, this, null, mainThis.getPanel());
		
	},
	
	getPanel : function() {
		return this.searchPackagesPanel;
	},
	initialListeners: function(){
		
		this.effectiveFromDtFld.on('change', function(dateToBeSet) {
   			if(!Ext.isEmpty(dateToBeSet.getValue())){
   				this.effectiveToDtFld.setMinValue(dateToBeSet.getValue());
   			}
		}, this);

		this.effectiveToDtFld.on('change', function(dateToBeSet) {
   			if(!Ext.isEmpty(dateToBeSet.getValue())){
   				this.effectiveFromDtFld.setMaxValue(dateToBeSet.getValue());
   			}
		}, this);

		this.createdAfterDtFld.on('change', function(dateToBeSet) {
   			if(!Ext.isEmpty(dateToBeSet.getValue())){
   				this.createdBeforeDtFld.setMinValue(dateToBeSet.getValue());
   			}
		}, this);

		this.createdBeforeDtFld.on('change', function(dateToBeSet) {
   			if(!Ext.isEmpty(dateToBeSet.getValue())){
   				this.createdAfterDtFld.setMaxValue(dateToBeSet.getValue());
   			}
		}, this);
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
		var mainThis = this;
	 	var config ={
			title: svcAndGrpAndPkgMsg.addPackage,
			mode:svcAndGrpAndPkgMsg.addMode,
			isPopUp: true
		};
		showAddEditPackageWindow( config,mainThis );
	},
	
	editButtonClicked : function(){
		var mainThis = this;
		var rowSelectedForEdit = this.infoGrid.getSelectionModel().getSelections()[0].data;
		var packageId = rowSelectedForEdit.packageId;
		ServiceManager.getServicePackageDetail( packageId, function( servicePackageBM ){
			 	var config ={
					title: svcAndGrpAndPkgMsg.editPackage,
					mode:svcAndGrpAndPkgMsg.editMode,
					servicePackageBM : servicePackageBM,
					isPopUp: true
				};
				showAddEditPackageWindow( config,mainThis );
		});
	},
	
	deleteButtonClicked: function(){
		var selectedRows = this.infoGrid.getSelectionModel().getSelections();
		Ext.Msg.show({
						   msg: svcAndGrpAndPkgMsg.deleteServicePackageMsg,
						   buttons: Ext.Msg.YESNO,
						   fn: function( btnValue ){
							   if( btnValue == configs.yes){
								   	if( selectedRows.length > 0 ){
										var packagesList = new Array();
										for( var i = 0;i<selectedRows.length;i++ ){
											var rowdata = selectedRows[i].data;
											packagesList.push( rowdata.packageId );
										}
										ServiceManager.removeServicePackages(packagesList, function(){
											 	Ext.ux.event.Broadcast.publish('reloadSearchPackagesGrid');
											   	loadServicePackageCbx().reload();
										});
									}
							   }
						   },
						   icon: Ext.MessageBox.QUESTION
						});
		
	},
	
	searchButtonClicked : function(){
   		var valuesMap = this.searchPackagesPanel.getForm().getValues();
   		this.infoGrid.store.removeAll();
		this.infoGrid.store.load({params:{start:0, limit:this.pagingBar.pageSize}, 
								  arg:[
								  	   valuesMap['name'], 
  							   		   valuesMap['packageId'],
  							   		   valuesMap['servicePackageStatus'],
								  	   valuesMap['description'],
								  	   valuesMap['chargeOverrideLevel'], 
  							   		   valuesMap['serviceName'],
								  	   getStringAsWtcDateFormat( valuesMap['createdAfter'] ),
								  	   getStringAsWtcDateFormat( valuesMap['createdBefore'] ),
								  	   getStringAsWtcDateFormat( valuesMap['effectiveFrom'] ),
								  	   getStringAsWtcDateFormat( valuesMap['effectiveTo'] )
  							   		   ]
  								});	
		this.setGridButtonsState(this.infoGrid.getSelectionModel());
	},
	resetButtonClicked: function(){
		this.searchPackagesPanel.getForm().reset();
		this.searchButtonClicked();
	},
	refreshH: function( pagingBar ){
		this.dataStore.reload({params:{start: pagingBar.cursor,limit:pagingBar.pageSize,myparams:'val'}});
	}
	
});