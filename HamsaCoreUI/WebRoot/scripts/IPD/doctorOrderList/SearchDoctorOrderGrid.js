Ext.namespace("IPD.doctorOrderList");

function showAddEditDoctorOrderWindow(config) {
	config.specificPnl ={ };
	var doctorOrderPanel = new IPD.addOrder.DoctorOrder(config);
//	var manageDoctor = Ext.getCmp('doctor-management');
//	Ext.getCmp('manage-doctor-order');
	var attributepanel = null;
	
	if( config.mode == 'edit' && !Ext.isEmpty(config.attributeList) && config.attributeList.length > 0 ){
		config.specificPnl.attributepanel = new IPD.addOrder.SpecificOrderPanel({data:config.attributeList,
															  mode : 'edit'});
		doctorOrderPanel.specificPanelForOrderType.add(config.specificPnl.attributepanel.getPanel().show());
		doctorOrderPanel.getLayout();
	}
	
	var panelToAdd = doctorOrderPanel.getPanel(config);
	panelToAdd.frame=true;
	panelToAdd.title = config.title;
	panelToAdd.closable = true;
	panelToAdd.height = 420;

	
	var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
	
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
	
	if(config.mode == 'edit'){
	doctorOrderPanel.loadData(config);
	}

	if(config.source == configs.appointment){
		doctorOrderPanel.setDefaultValues(config);
	}
	if( !Ext.isEmpty(config.specificPnl.attributepanel)){
		config.specificPnl.attributepanel.setData( config.values.orderSpecificAttributes );
	}
	
	doctorOrderPanel.getFocus(config);
	var object;
	if( !Ext.isEmpty( config.mainThis)){
		object = config.mainThis.getPanel()
	}
	
	Ext.ux.event.Broadcast.subscribe('closeDoctorOrderAddWindow',function(){
//		layout.getCenterRegionTabPanel().remove( panel, true );
		Ext.ux.event.Broadcast.publish('loadDoctorOrderGrid');
	},this,null,object);

}

IPD.doctorOrderList.SearchDoctorOrderGrid = Ext.extend(Object, {
	constructor : function(config) {
		var mainThis = this;
		this.doctorOrderDetailRecord = Ext.data.Record.create([
			{ name: 'seqNbr',mapping: 'seqNbr'},
			{ name: 'admissionNbr', mapping:'admissionNbr' },
			{ name: 'patientId', mapping:'patientId',convert:numberToString },
	        { name: 'patientName',  mapping:'patientName'},
	        { name: 'orderType', mapping:'doctorOrderType',convert:getDescription},
	        { name: 'orderGroup', mapping:'doctorOrderGroup',convert:getDescription},
	        { name: 'template', mapping: 'doctorOrderTemplate',convert:getDescription},
	        { name: 'orderDate', mapping: 'orderCreationDate'},
	        { name: 'createdBy', mapping: 'createdBy'},
	        { name: 'status', mapping:'doctorOrderStatus',convert:getDescription},
	        { name: 'statusCd', mapping:'doctorOrderStatus',convert:getCode},
	        { name: 'orderTypeCd', mapping:'doctorOrderType',convert:getCode},
	        { name: 'orderGroupCd', mapping:'doctorOrderGroup',convert:getCode},
	        { name: 'templateCd', mapping: 'doctorOrderTemplate',convert:getCode},
	        { name: 'orderDictation', mapping: 'orderDictation'},
	        { name: 'doctorId', mapping: 'doctorId',convert:numberToString},
	        { name: 'doctorName', mapping: 'doctorName'},
	        { name: 'orderDesc', mapping: 'orderDesc'},
	        { name: 'doctorOrderNbr', mapping: 'doctorOrderNbr'},
	        { name: 'doctorOrderDetailsList'},
	        { name: 'orderSpecificAttributes', mapping: 'orderSpecificAttributes'},
	    	{ 	name: 'hideApproved', 
	    		mapping:'doctorOrderStatus', 
	    		convert: function(val, rec){
	    			if (val.code === configs.orderStatusCreated ) 
	    				{return false;}
	    			else {return true;}
	    		}
    		},
	    	{ 	name: 'hideDisapproved', 
	    		mapping:'doctorOrderStatus', 
	    		convert: function(val, rec){
	    			if (val.code === configs.orderStatusCreated ) 
	    				{return false;}
	    			else {return true;}
	    		}
    		},
	    	{ 	name: 'hideCancel', 
	    		mapping:'doctorOrderStatus', 
	    		convert: function(val, rec){
	    			if (val.code === configs.orderStatusCreated ) 
	    				{return false;}
	    			else {return true;}
	    		}
    		},
	    	{ 	name: 'hideReamrks', 
	    		mapping:'doctorOrderStatus', 
	    		convert: function(val, rec){
	    			if (val.code === configs.orderStatusApproved || val.code === configs.orderStatusDisapproved || val.code === configs.orderStatusCanceled || val.code === configs.statusCompleted ) 
	    				{return false;}
	    			else {return true;}
	    		}
    		}
    		
        ]);
										
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: 'doctorOrderNbr', totalProperty:'totalSize'}, this.doctorOrderDetailRecord),
        	proxy: new Ext.data.DWRProxy(OrderManager.findDoctorOrders, true),
        	remoteSort: true
		});
		
		this.pagingBar = new Ext.WTCPagingToolbar({
			store : this.dataStore,
			displayInfo : true,
			displayMsg : 'Displaying orders {0} - {1} of {2}'
		});
			
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
//					mainThis.deleteBtn.enable();
					if( selectionModel.getSelections().length > 1){
						mainThis.editBtn.disable();
					}else{
						if(record.data.statusCd == configs.orderStatusApproved || record.data.statusCd == configs.orderStatusCreated)
							mainThis.editBtn.enable();
					}
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					if( selectionModel.getSelections().length == 1){
						var rec = selectionModel.getSelections();
						if(rec[0].data.statusCd == configs.orderStatusApproved || rec[0].data.statusCd == configs.orderStatusCreated)
							mainThis.editBtn.enable();
					}else if( selectionModel.getSelections().length > 1){
						mainThis.editBtn.disable();
//						mainThis.deleteBtn.enable();
					}else{
//						mainThis.deleteBtn.disable();
						mainThis.editBtn.disable();
					}
				}
			}
		});

		this.addBtn = new Ext.Button({
			iconCls : 'add_btn',
			text : 'Add',
			scope:this,
			handler : function() {
				showAddEditDoctorOrderWindow({
					width:'90%',
					title: 'Add Doctor Order',
					height:'80%',
					hideServiceSelectionFieldSet:false,
					mainThis : mainThis
				});
			}
		});
		
		this.editBtn = new Ext.Button({
			iconCls : 'edit_btn',
			text : 'Edit',
			scope:this,
			disabled: true,
			handler : function() {
				if(this.infoGrid.getSelectionModel().getSelections().length > 0){
					var selectedRow = this.infoGrid.getSelectionModel().getSelections()[0].data;
					OrderManager.getOrderTypeHasAttribute(selectedRow.orderTypeCd, 0 , 999 , 'ASC' ,function( list ){        
						showAddEditDoctorOrderWindow({
							width: '90%',
							title: 'Edit Doctor Order',
							height:'80%',
							hideServiceSelectionFieldSet:true,
							mode:'edit',
							values:selectedRow,
							isFromOrderList : true,
							attributeList : list.data,
							mainThis : mainThis
						});
					});
				}	
			}
		});
//		this.deleteBtn = new Ext.Button({
//			iconCls : 'delete_btn',
//			text : 'Delete',
//			disabled: true,
//			scope:this,
//			handler:function(){
//					Ext.Msg.show({
//							title : 'Delete order',
//							msg : 'Are you sure you want to delete the selected order(s)?',
//							buttons : Ext.Msg.YESNOCANCEL,
//							fn : function(buttonId, text, opt) {
//								if (buttonId == "yes") {
//									var selectedRow = mainThis.infoGrid.getSelectionModel().getSelections();
//									var orderList = new Array();
//									for(var i = 0; i < selectedRow.length; i++){
//										orderList.push(selectedRow[i].data.doctorOrderNbr);
//									}
//									
//									OrderManager.removeDoctorOrderList(orderList,function(isDeleted){
//										if(!Ext.isEmpty(isDeleted) && isDeleted){
//											   mainThis.dataStore.reload();
//										}
//									});
//								}
//							},
//							animEl : 'elId',
//							icon : Ext.MessageBox.QUESTION
//					});
//			}
//		});
		
		
		this.action = new Ext.ux.grid.RowActions({
			 header:'Actions'
			,keepSelection:true
			,actions:[
				{
					iconCls:'approve-icon',
					tooltip:'Approve',
					hideIndex:'hideApproved'
				},
				{
					iconCls:'disapprove-icon',
					tooltip:'Disapprove',
					hideIndex:'hideDisapproved'
				},
				{
					iconCls:'cross_icon',
					tooltip:'Cancel',
					hideIndex:'hideCancel'
				},
				{
					iconCls:'view-icon',
					tooltip:'View remarks',
					hideIndex:'hideReamrks'
				}
				
			]
		});
		
		
		this.action.on({
			action:function(grid, record, action, row, col) {
				var config = {
					orderNumber : record.data.doctorOrderNbr,
					personId : authorisedUser.userName,
					patientName : record.data.patientName,
					patientId : record.data.patientId
				}
			
				if (action === 'approve-icon') {
					config.newStatusCode = configs.orderStatusApproved;
					config.okBtnText = msg.approveText;
					config.isRequired = false; 
					mainThis.getRemarksPanel( config );
				}
			
				if (action === 'disapprove-icon') {
					config.newStatusCode = configs.orderStatusDisapproved;
					config.okBtnText = msg.disApproveText; 
					mainThis.getRemarksPanel( config );
				}
				
				if (action === 'cross_icon') {
					config.newStatusCode = configs.orderStatusCanceled;
					config.okBtnText = msg.cancelText; 
					config.isRequired = false; 
					mainThis.getRemarksPanel( config );
				}

				if (action === 'view-icon') {
					config.mode = 'infoMode';
					OrderManager.getOrderRemarks(record.data.doctorOrderNbr, record.data.statusCd , function( remarks ){
						config.remarks = remarks;
						var doctorOrderRemarksPanel = new IPD.doctorOrderList.DoctorOrderRemarksPanel(config);
						mainThis.remarksWindow = new Ext.Window({
							title: 'Remarks for ' + config.patientName +"(" + config.patientId +")",
							items:doctorOrderRemarksPanel.getPanel(),
							frame:true,
							height:'40%',
							width:'40%'
						});
						mainThis.remarksWindow.show();
						
					})
				}
				
				Ext.ux.event.Broadcast.subscribe('closeDoctorOrderRemarksWindow',function(){
					mainThis.remarksWindow.close();
				}, this, null, mainThis.remarksWindow)
				
			}
			
		});
		
		
		
		this.infoGrid = new Ext.grid.GridPanel({
				frame : true,
				stripeRows : true,
				height : 320,
				width : '100%',
				autoScroll : true,
				plugins : this.action,
				border : true,
				viewConfig: {forceFit:true},
				store : this.dataStore,
				bbar : this.pagingBar,
				sm:this.gridChk,
				tbar : new Ext.Toolbar({items:[this.addBtn, '-', this.editBtn]}),
				listeners:{
					 cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
							var selectedRecord = thisGrid.getStore().getAt(rowIndex).data;
							if( thisGrid.getSelectionModel().getSelections().length == 1 ){
								var record = thisGrid.getSelectionModel().getSelections();
								if(record[0].data.statusCd == configs.orderStatusApproved || record[0].data.statusCd == configs.orderStatusCreated)
								mainThis.editBtn.enable();
							}else{
								mainThis.editBtn.disable();
							}
					},
					celldblclick:function(thisGrid, rowIndex, columnIndex, eventObj){
					}
				},
				columns : [this.gridChk, {
							header : 'Order number',
							dataIndex : 'doctorOrderNbr',
							width : 40,
							sortable: true
						}, {
							header : 'Admission number',
							dataIndex : 'admissionNbr',
							width : 50,
							sortable: true
						}, {
							header : 'Patient Id',
							dataIndex : 'patientId',
							width : 80,
							sortable: true
						}, {
							header : 'Patient name',
							dataIndex : 'patientName',
							width : 50,
							sortable: true
						}, {
							header : 'Order type',
							dataIndex : 'orderType',
							width : 50,
							sortable: true
						}, {
							header : 'Order group',
							dataIndex : 'orderGroup',
							width : 50,
							sortable: true
						}, {
							header : 'Template',
							dataIndex : 'template',
							width : 50,
							sortable: true
						}, {
							header : 'Order date',
							dataIndex : 'orderDate',
							renderer: Ext.util.Format.dateRenderer('d/m/Y'),
							width : 80,
							sortable: true
						}, {
							header : 'Ordered by',
							dataIndex : 'doctorName',
							width : 50,
							sortable: true
						}, {
							header : 'Status',
							dataIndex : 'status',
							width : 80,
							sortable: true
						},
						this.action
					]
			});
			
			Ext.ux.event.Broadcast.subscribe('loadDoctorOrderGrid', function(){
				if(this.infoGrid.getStore().data.items.length == 0){
					this.infoGrid.getStore().load({params:{start:0, limit:10}, arg:[null, null, null, null, null, null, null ]});
				}else{
					this.infoGrid.getStore().removeAll();
					this.infoGrid.getStore().reload();
				}
//				this.deleteBtn.disable(); 
				this.editBtn.disable();
			},this,null,mainThis.getPanel());
	},
	getPanel : function() {
		return this.infoGrid;
	},
	search: function(searchCriteria) {
		this.dataStore.removeAll();
		this.dataStore.load({params:{start:0, limit:10}, arg:[searchCriteria['admissionNbr'],
																searchCriteria['patientId'],
																searchCriteria['patientName'],
																searchCriteria['status'],
																searchCriteria['orderType'],
																getStringAsWtcDateFormat(searchCriteria['orderDateFrom']),
																getStringAsWtcDateFormat(searchCriteria['orderDateTo'])
															   ]});
	},
	getRemarksPanel : function( config ){
		var doctorOrderRemarksPanel = new IPD.doctorOrderList.DoctorOrderRemarksPanel(config);
		this.remarksWindow = new Ext.Window({
			title: 'Remarks for ' + config.patientName +"(" + config.patientId +")",
			items:doctorOrderRemarksPanel.getPanel(),
			frame:true,
			height:'40%',
			width:'40%'
		});
		this.remarksWindow.show();
		this.remarksWindow.on('close',function(){
			if( !Ext.isEmpty( loadAdmReqNoCbx() )){
				loadAdmReqNoCbx().load({params:{start:0, limit:9999}, arg:[]});
			}
	 },this);
	}
});