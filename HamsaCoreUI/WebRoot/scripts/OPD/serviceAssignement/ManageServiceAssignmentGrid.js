Ext.namespace("OPD.serviceAssignement");

OPD.serviceAssignement.ManageServiceAssignmentGrid = Ext.extend(Object,{
	constructor: function(){
		
		Ext.QuickTips.init();
		
		this.widgets = new OPD.serviceAssignement.Widgets();
		var mainThis = this;
		
		this.assignedServiceRecord = Ext.data.Record.create([
		 	{ name : "serviceCode", mapping:'service', convert: getCode },
		 	{ name : "serviceName" , mapping:'service', convert: getDescription },
		 	{ name : "packageCode", mapping:'servicePackage', convert: getCode},
		 	{ name : "packageName" ,mapping:'servicePackage', convert: getDescription},
		 	{ name : "serviceStatusCode", mapping:'assignedServiceStatus', convert: getCode },
		 	{ name : "serviceStatusDesc", mapping:'assignedServiceStatus', convert: getDescription },
		 	{ name : "requestedUnits", mapping:'requestedUnits'},
		 	{ name : "renderedUnits", mapping:'renderedUnits' },
		 	{ name : 'canceledUnits', mapping:'canceledUnits'},
		 	{ name : "billedUnits", mapping:'billedUnits' },
		 	{ name : "lastBillNbr" },
		 	{name : "serviceOrderNumber", mapping:"serviceOrderNumber"},
		 	{name : 'packageAsgmId', mapping: 'packageAsgmId'},
		 	{ name: 'serviceDate', mapping:'serviceDate'},
		 	{ name: 'patientId', mapping:'patientId'},
		 	{ name: 'serviceUid', mapping:'serviceUid'},
		 	{ name:'referenceNumber', mapping:'referenceNumber'},
		 	{ name:'packageAsgmId', mapping:'packageAsgmId'},
		 	{ name:'serviceTypeCode', mapping:'serviceType', convert : getCode },
		 	{ name:'referenceType', mapping:'referenceType'},
		 	{ name:'doctorId', mapping:'doctorId'},
		 	{ name:'patientName', mapping:'patientName'},
		 	{ name:'hideSubmitted', type:'boolean', mapping:'packageAsgmId', 
		 		convert: function(value, rec){
		 			if( value != null){
		 				return true;
		 			}else{
		 				return false;
		 			}	
		 	}}
		 	
		]);	
		
		this.rowActions = new Ext.ux.grid.RowActions({
			header:'Actions',
			autoWidth:false,
			hideMode:'display',
			keepSelection:true,
			actions:[],
			groupActions:[{
				iconCls:'accept-icon',
				qtip:'select All',
				hideIndex:'hideSubmitted',
				align:'left'
			}],
			callbacks:{
				'icon-plus':function(grid, record, action, row, col) {
				 }
			 }
		});
		
	  	this.rowActions.on({
			groupaction:function(grid, records, action, groupId) {
				if(action == 'accept-icon'){
					var checkBoxSelectionModel = grid.getSelectionModel();
					if( records.length > 0 && checkBoxSelectionModel.isIdSelected( records[0].id ) ){
						for( var i = 0; i<records.length; i++){
							var startIndex = grid.getStore().find('packageAsgmId',records[i].data.packageAsgmId);
							checkBoxSelectionModel.deselectRange( startIndex, startIndex+records.length );
						}
					}else{
						checkBoxSelectionModel.clearSelections();
						checkBoxSelectionModel.selectRecords(records);
					}
				}
				
			 }
		});
		
		this.dataStore = new Ext.data.GroupingStore({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.assignedServiceRecord),
        	proxy: new Ext.data.DWRProxy(ServiceManager.findAssignedServices, true),
        	groupField:'packageAsgmId',
        	sortInfo: {field: 'serviceUid', direction: "ASC"},
        	remoteSort: true
		});

		this.pagingBar = new Ext.WTCPagingToolbar({
				store : this.dataStore,
				displayMsg : mngSvcAsgntMsg.GridDisplayInfo,
				emptyMsg : mngSvcAsgntMsg.pagingBarEmptyMsg
			});
			
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					mainThis.renderBtn.disable();
					mainThis.cancelBtn.enable();
					mainThis.viewAssignmentsBtn.disable();
					mainThis.deleteBtn.enable();
					mainThis.replaceBtn.disable();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					mainThis.renderBtn.disable();
					mainThis.cancelBtn.disable();
					mainThis.viewAssignmentsBtn.disable();
					mainThis.deleteBtn.disable();
					mainThis.replaceBtn.disable();
				}
			}
		});
		
		this.renderBtn = this.widgets.getRenderBtn();
		
		this.cancelBtn = this.widgets.getCancelBtn();
		
		this.deleteBtn = this.widgets.getDeleteBtn();
		
		this.bookOTBtn = new Ext.Button({
			text: "Book OT",
			scope : this,
			iconCls : 'add_btn',
			disabled : true
		});
		
		// these two buttons are commented. because we dont have any kind of functionality for this.
		// the replace button, In the case of in- patient we want to allow the assignment of services by using doctor order only.
		// In case of out-patient we can assign services from consultaion details window and op billing time.
		// so  there is no need to provide a facility to add service(s) to patient form manage assignment screen.
		this.viewAssignmentsBtn = this.widgets.getViewAssignmentsBtn();
		this.replaceBtn = this.widgets.getReplaceBtn();
		
		var gridButtons = [
			'-',
			this.renderBtn,
			'-',
//			this.viewAssignmentsBtn,
//			'-',
//			this.replaceBtn,
//			'-',
			this.cancelBtn,
			'-',
//			this.deleteBtn,
//			'-'
			this.bookOTBtn,
			'-'
		
		];

		var gridColumns =[
			this.gridChk,
			{ header: mngSvcAsgntMsg.patientId, dataIndex : "patientId", sortable: true, width: 100 },
			{ header: mngSvcAsgntMsg.serviceCode, dataIndex : "serviceCode", sortable: true, width: 100 },
		 	{ header: mngSvcAsgntMsg.serviceName, dataIndex : "serviceName", sortable: true, width: 100  },
		 	{ header: mngSvcAsgntMsg.packageName, dataIndex : "packageName", sortable: true, width: 100  },
		 	{ header: mngSvcAsgntMsg.serviceStatus, dataIndex : "serviceStatusDesc", sortable: true, width: 100  },
		 	{ header: mngSvcAsgntMsg.requestedUnits, dataIndex : "requestedUnits",align:'right', sortable: true, width: 100  },
		 	{ header: mngSvcAsgntMsg.renderedUnits, dataIndex : "renderedUnits",align:'right', sortable: true, width: 100  } ,
		 	{ header: mngSvcAsgntMsg.billedUnits, dataIndex : "billedUnits", align:'right',sortable: true, width: 100  },
		 	{ header: mngSvcAsgntMsg.canceledUnits, dataIndex : "canceledUnits",align:'right', sortable: true, width: 100  },
		 	{ header: mngSvcAsgntMsg.lastBillNbr, dataIndex : "lastBillNbr", sortable: true, width: 100  },
		 	{ header: mngSvcAsgntMsg.serviceDate, dataIndex : "serviceDate", sortable: true, 
		 		renderer :Ext.util.Format.dateRenderer('d/m/Y'),width: 100  },
	 		{ header: mngSvcAsgntMsg.packageAssignementId, dataIndex : "packageAsgmId", sortable: true, width: 100 },
	 		{ header: "Appointment number",hidden:true, dataIndex : "referenceNumber", sortable: true, width: 100  },
	 		this.rowActions
		];		
		
		this.infoGrid = new Ext.grid.GridPanel({
			frame : false,
			stripeRows : true,
			height : 350,
			width : '100%',
			style : 'padding-right: 5px',
			autoScroll : true,
			plugins:[this.rowActions],
			viewConfig:{forceFit: true},
			view: new Ext.grid.GroupingView({
		        viewConfig:{forceFit:true},
		        hideGroupedColumn:true,
		        enableGroupingMenu: false,
		        // custom grouping text template to display the number of items per group
		        groupTextTpl: '{[getGroupText(values.text)]}({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
		    }),
			border : true,
			store : this.dataStore,
			bbar : this.pagingBar,
			tbar : gridButtons,
			columns : gridColumns,
			sm: this.gridChk,
			listeners:{
				cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
					mainThis.setGridButtonsState(thisGrid.getSelectionModel());
				} 
			}
		});
			
		Ext.ux.event.Broadcast.subscribe('reloadManageAssignedServiceGrid', function(){
			if(mainThis.infoGrid.getStore().data.length > 0){
				mainThis.infoGrid.getStore().reload();
			}
			mainThis.renderBtn.disable();
			mainThis.deleteBtn.disable();
			mainThis.viewAssignmentsBtn.disable();
			mainThis.replaceBtn.disable();
			mainThis.cancelBtn.disable();
			mainThis.bookOTBtn.disable();
		}, this, null, mainThis.getPanel());
		
		Ext.ux.event.Broadcast.subscribe('clearSelectionsInManageAssignedServiceGrid', function(){
			mainThis.infoGrid.getSelectionModel().clearSelections();
			
			mainThis.renderBtn.disable();
			mainThis.deleteBtn.disable();
			mainThis.viewAssignmentsBtn.disable();
			mainThis.replaceBtn.disable();
			mainThis.cancelBtn.disable();
			mainThis.bookOTBtn.disable();
		}, this, null, mainThis.getPanel());

	},
	getPanel: function(){
		return this.infoGrid;
	},
	getSelectedRows: function(){
		return this.infoGrid.getSelectionModel().getSelections();
	},
	setGridButtonsState : function(selectionModel){
		var selectedRows = selectionModel.getSelections();
		
		this.renderBtn.disable();
		this.deleteBtn.disable();
		this.viewAssignmentsBtn.disable();
		this.replaceBtn.disable();
		this.cancelBtn.disable();
		this.bookOTBtn.disable();
		
		if( selectedRows.length == 1){
			if( selectedRows[0].data.serviceTypeCode === "SURGICAL"){
				this.bookOTBtn.enable();
			}
			if( (selectedRows[0].data.serviceStatusCode != mngSvcAsgntMsg.ASSIGNED_SERVICE_STATUS_RENDERED) &&
				(selectedRows[0].data.serviceStatusCode != mngSvcAsgntMsg.ASSIGNED_SERVICE_STATUS_CANCELED)&&
				(selectedRows[0].data.serviceStatusCode != mngSvcAsgntMsg.ASSIGNED_SERVICE_STATUS_DISAPPROVED)){
					this.renderBtn.enable();
			}
			this.deleteBtn.enable();
			this.viewAssignmentsBtn.enable();
			this.replaceBtn.enable();
			this.cancelBtn.enable();
			
		} else if( selectedRows.length > 1){
			this.renderBtn.disable();
			this.viewAssignmentsBtn.disable();
			this.replaceBtn.disable();
			this.cancelBtn.enable();
			this.deleteBtn.enable();
			this.bookOTBtn.disable();
		}
	}
});
 function getGroupText ( text ){
 	if(text.substring(text.indexOf(':')+1) == ' null'){
 	
 		var arrStr = text.split(':');
 		return 'Individual services: '
 	}else{
 		return text;
 	}
 }