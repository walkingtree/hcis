Ext.namespace("LIMS.requisitionOrder.manage");

LIMS.requisitionOrder.manage.RequisitionOrderSearchPanel = Ext.extend(Ext.Panel,{
	title : limsMsg.srchRequiOrder,
	layout : 'form',
	initComponent : function(){
	
		var mainThis = this;
	
		this.requisitionOrderSrchCriteria = new LIMS.requisitionOrder.manage.RequisitionOrderSearchCriteria();
		
		this.requisitionOrderSrchGrid = new LIMS.requisitionOrder.manage.RequisitionOrderSearchGrid();
		
		this.searchButtonsPanel = new utils.SearchButtonsPanel();
		
		this.requisitionOrderSrchGrid.toolBar.viewBtn.on("click",function(){
			this.viewBtnClicked();
		},this);
		
		this.requisitionOrderSrchGrid.toolBar.addBtn.on("click",function(){
			this.addBtnClicked();
		},this);
		
		this.requisitionOrderSrchGrid.toolBar.editBtn.on("click",function(){
			this.editBtnClicked();
		},this);
		
		this.requisitionOrderSrchGrid.toolBar.createReceiptBtn.on('click',function(){
			this.createReceiptBtnClicked();
		},this);
		
		this.requisitionOrderSrchGrid.action.on({
			action : function( grid, record, action, row, col ){
				if( action == 'cross_icon'){
					RequisitionOrderManager.cancelRequisitionOrder( record.data.requisitionNbr , getAuthorizedUserInfo().userName ,function(){
						var recordArray = grid.store.reload();
					});
				}
				
			}
		});

		
		this.searchButtonsPanel.searchBtn.on('click',function(){
			this.searchBtnClicked();
		},this);
		
		this.searchButtonsPanel.resetBtn.on('click',function(){
			this.resetBtnClicked();
		},this);
		
		
		
		this.on('render',function(){
			this.requisitionOrderSrchGrid.loadGrid();
		},this);
		
		this.requisitionOrderSrchGrid.toolBar.deleteBtn.on('click',function(){
			this.deleteBtnClicked();
		},this);
		
		this.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
		
		Ext.applyIf(this, {
			items : [
			     this.requisitionOrderSrchCriteria.getPanel(),
			     this.searchButtonsPanel,
			     this.requisitionOrderSrchGrid.getPanel()
			]
		});
		
		LIMS.requisitionOrder.manage.RequisitionOrderSearchPanel.superclass.initComponent.apply(this , arguments);
	},
	
	getPanel : function(){
		return this;
	},
	addBtnClicked : function(){
		var addRequisitionOrder = new OPD.billing.OpBillingPanel();
		
		var panelToAdd = addRequisitionOrder.getPanel();
		
		panelToAdd.frame=true;
		panelToAdd.title = limsMsg.addRequisitionOrder; 
		panelToAdd.closable = true;
		panelToAdd.height = 420;
		
		var activeTab = layout.getCenterRegionTabPanel().add(panelToAdd);
		
		layout.getCenterRegionTabPanel().setActiveTab( activeTab );
		addRequisitionOrder.assignedServicePnl.assignedServiceDetailsPnl.ServiceTypeSelectionGroup.setValue(4);
		layout.getCenterRegionTabPanel().doLayout();		
		
	},
	
	editBtnClicked : function(){
		var tmpThis = this;
		var requisitionOrderDetailData = this.requisitionOrderSrchGrid.getSelection();
		var selectedData = requisitionOrderDetailData[0].data;
		
		var requisitionOrderNbr = parseInt( selectedData.requisitionNbr );
		
		ServiceManager.getAssignedServicesAndPackages( requisitionOrderNbr , false ,function( assignedServiceAndPackageBM ){
			var editRequisitionOrder = new OPD.billing.OpBillingPanel();
			
			var panelToAdd = editRequisitionOrder.getPanel();
			
			editRequisitionOrder.isFromRequisition = limsMsg.isFromRequsition;
			editRequisitionOrder.newPatientChbx.hide();
			editRequisitionOrder.referenceNbr = assignedServiceAndPackageBM.referenceNumber;
			editRequisitionOrder.referenceType = assignedServiceAndPackageBM.referenceType;
			
			editRequisitionOrder.serviceOrderNumber = assignedServiceAndPackageBM.serviceOrderNumber;
			editRequisitionOrder.assignedServicePnl.assignedServiceGridPnl.onRender( assignedServiceAndPackageBM );
			
			
			editRequisitionOrder.onRender( assignedServiceAndPackageBM.patientId ,limsMsg.isFromRequsition );

			editRequisitionOrder.patientIdNrF.disable();
			panelToAdd.frame=true;
			panelToAdd.title = limsMsg.editRequisitionOrder; 
			panelToAdd.closable = true;
			panelToAdd.height = 420;

			
			var activeTab = layout.getCenterRegionTabPanel().add(panelToAdd);
			
			layout.getCenterRegionTabPanel().setActiveTab( activeTab );
			layout.getCenterRegionTabPanel().doLayout();
			
			Ext.ux.event.Broadcast.subscribe('closeOPBillingPnl',function(){
//				layout.getCenterRegionTabPanel().remove( activeTab , true );
				layout.getCenterRegionTabPanel().setActiveTab( tmpThis );
//				layout.getCenterRegionTabPanel().doLayout();
				tmpThis.requisitionOrderSrchGrid.loadGrid();
			},this, null , tmpThis);
			

		});
		
		
	},
	
	viewBtnClicked : function(){
		
		var requisitionOrderDetailData = this.requisitionOrderSrchGrid.getSelection();
		
		var requisitionOrderNbr = parseInt( requisitionOrderDetailData[0].data.requisitionNbr );
		
		RequisitionOrderManager.getPatientRequisitionDetail( requisitionOrderNbr , function( patientRequisitionBM ){
			var requisitionDetailView = new LIMS.requisitionOrder.manage.RequisitionOrderDetailView();
			
			
			var activeTab = layout.getCenterRegionTabPanel().add({
				frame:true,
				title : limsMsg.requiDetails + requisitionOrderNbr, 
				closable : true,
				height : 420,
				items : [requisitionDetailView.getPanel()]
			});
			
			requisitionDetailView.setData( patientRequisitionBM.patientLiteBM );
			
			layout.getCenterRegionTabPanel().setActiveTab( activeTab );
			layout.getCenterRegionTabPanel().doLayout();
			
			//Bhavesh
			//requisitionDetailView.detailGrid.loadGridData( patientRequisitionBM.requisitionOrderDetailBM );
			
			requisitionDetailView.detailGrid.loadData( {requisitionOrderNbr:requisitionOrderNbr});
			
		});
		
	},
	
	createReceiptBtnClicked : function(){
		var selectedRecord = this.requisitionOrderSrchGrid.getSelection();
		receiptConfig = {selectedAccountHolderId : selectedRecord[0].data.patientId,makeReadOnly : true };
		
		showReceiptWindow( receiptConfig );
	},
	
	searchBtnClicked : function(){
		var searchCriteria = this.requisitionOrderSrchCriteria.getForm().getValues();
		var requisitionFromDate = getStringAsWtcDateFormat(searchCriteria['requisitionFromDate']) ;
		var requisitionToDate = getStringAsWtcDateFormat( searchCriteria['requisitionToDate'] );
		var patientId = Ext.isEmpty( searchCriteria['patientId'] ) ? null :parseInt(searchCriteria['patientId']);
		var doctorId = Ext.isEmpty( searchCriteria['doctorId'] ) ? null :parseInt(searchCriteria['doctorId']);
		this.requisitionOrderSrchGrid.requisitionDataStore.removeAll();
		this.requisitionOrderSrchGrid.requisitionDataStore.load({params:{start:0, limit:10}, arg:[searchCriteria['patientName'], 
		                                                                               patientId,
		                                                                               searchCriteria['referenceType'],
		                                                                               searchCriteria['doctorName'],
		                                                                               doctorId,
		                                                                               requisitionFromDate,
		                                                                               requisitionToDate,
		                                                                               searchCriteria['testName'],
		                                                                               searchCriteria['testStatus']]});
		
		
	},
	resetBtnClicked : function(){
		this.requisitionOrderSrchCriteria.reset();
		this.requisitionOrderSrchGrid.loadGrid();
	},
	
	deleteBtnClicked : function(){
		var requisitionOrderDetailData = this.requisitionOrderSrchGrid.getSelection();
		var requisitionOrderNbrList = new Array();
		
		for( var i =0 ; i<requisitionOrderDetailData.length ; i++){
			
			requisitionOrderNbrList.push(requisitionOrderDetailData[i].data.requisitionNbr);
		}
		
		RequisitionOrderManager.removeRequisitionOrder( requisitionOrderNbrList , 
														getAuthorizedUserInfo().userName,
														function(){
			
		});
	}
});