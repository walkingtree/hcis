Ext.namespace("LIMS.requisitionOrder.manage");

LIMS.requisitionOrder.manage.RequisitionOrderSearchGrid = Ext.extend(Ext.grid.GridPanel, {
	title : limsMsg.srchRequiGrid ,
    initComponent : function() {
    	var mainThis = this;
    	
    	var actionArray = new Array();
    	var action = {				
    			iconCls:'cross_icon',
				tooltip:'Cancel',
				hideIndex:'hideCancel'
    		}
    	actionArray.push(action);
    	
    	this.action = new GridRowActions( actionArray );
    	
        this.record = Ext.data.Record.create( [
         	{ name : 'requisitionNbr',mapping : 'requisitionOrderNbr' }, 
	        { name : 'patientName'},
		    { name : 'patientId' },
		    { name : 'doctorName'},
		    { name : 'createdDate' },
		    { name : 'totalCharges'},
		    { name : 'status', mapping : 'status', convert:getDescription},
		    { name : 'statusCode', mapping : 'status', convert:getCode},
		    { name : 'referenceType', mapping : 'referenceType'},
		    {
		    	name: 'hideCancel',
		    	mapping : 'status',
		    	convert : function(status, rec){
		    		var stausCode = getCode( status );
		    		if( stausCode === limsMsg.TEST_REQUISITION_STATUS_CANCELLED || stausCode === limsMsg.TEST_REQUISITION_STATUS_COMPLETED ){
		    			return true ;
		    		}
		    		else
		    		{
		    			return false ;
		    		}
		    	}	
		    }
         ]);

 		this.gridChk = new Ext.grid.CheckboxSelectionModel({			
	 		listeners:{
				rowselect : function( selectionModel, rowIndex, record){
// 					if( selectionModel.getSelections().length == 1){
//	 					mainThis.toolBar.editBtn.enable();
//	 					mainThis.toolBar.viewBtn.enable();
//	 					mainThis.toolBar.createReceiptBtn.enable();
// 					}
// 					else{
 						mainThis.toolBar.editBtn.disable();
 						mainThis.toolBar.viewBtn.disable();
 						mainThis.toolBar.createReceiptBtn.disable();
// 					}
				},
				rowdeselect : function( selectionModel, rowIndex, record){
//					if( selectionModel.getSelections().length == 1){
//						mainThis.toolBar.editBtn.enable();
//						mainThis.toolBar.viewBtn.enable();
//						mainThis.toolBar.createReceiptBtn.enable();
// 					}
// 					else{
 						mainThis.toolBar.editBtn.disable();
 						mainThis.toolBar.viewBtn.disable();
 						mainThis.toolBar.createReceiptBtn.disable();
// 					}
				}
			}
	 	});

        this.gridColumns = [
			this.gridChk ,
	           { header :limsMsg.requisitionNbr, dataIndex :'requisitionNbr', width : 100 }, 
	           { header :limsMsg.patientName , dataIndex :'patientName', width : 200 }, 
	           { header :limsMsg.patientId, dataIndex :'patientId', width : 150 }, 
	           { header :limsMsg.referringDoctorName , dataIndex :'doctorName', width : 100 }, 
	           { header :limsMsg.requisitionDate , dataIndex :'createdDate', width : 100 }, 
	           { header :limsMsg.referenceType , dataIndex :'referenceType', width : 100 }, 
	           { header :limsMsg.status , dataIndex :'status', width : 100 },
	           { header :limsMsg.totalCharges , dataIndex :'totalCharges', width : 100 ,align : 'center'}, 
	           this.action
	    ];
          
		this.requisitionDataStore = new Ext.data.Store({
			proxy :new Ext.data.DWRProxy(RequisitionOrderManager.getRequisitionOrders, true),
			reader :new Ext.data.ListRangeReader( {id :'id',totalProperty :'totalSize'}, this.record),
			remoteSort :true	//
		
		});
		
	this.pagingBar = new Ext.WTCPagingToolbar({
                store: this.requisitionDataStore,
                displayMsg: msg.pagingbarDisplayMsg,
		        emptyMsg: limsMsg.requisitionSrchGridMsg
    });
       
	   this.toolBar = new LIMS.requisitionOrder.manage.RequisitionOrderGridToolbar();
	   
       this.gridPnl = new Ext.grid.GridPanel({
	        viewConfig : {forceFit :true},
	        plugins : this.action,
	        remoteSort :true,
	      	frame : true,
	    	border : false,
	    	height : 320,
	    	tbar:this.toolBar,
	    	stripeRows :true,
	    	autoScroll :true,
	    	sm : this.gridChk,
	    	bbar : this.pagingBar,
	    	store : this.requisitionDataStore,
	    	columns : this.gridColumns,
	    	listeners : {
	        	cellclick : function( thisGrid, rowIndex, columnIndex, eventObj ){
	        		mainThis.setGridButtonState( thisGrid.getSelectionModel() );
	        	}
	        }
      });
       
       this.gridPnl.getStore().on('load',function(){
    	   	this.toolBar.editBtn.disable();
    	   	this.toolBar.viewBtn.disable();
    	   	this.toolBar.createReceiptBtn.disable();
       },this);
      
      Ext.applyIf(this, {
	 	  items: [{
	                  columnWidth : 1,
	                  items :[this.gridPnl]
	             }
			]            
      });

      LIMS.requisitionOrder.manage.RequisitionOrderSearchGrid .superclass.initComponent.apply(this, arguments);
    },
    
    getPanel : function(){
    	return this.gridPnl;
    },
    getSelection : function(){
    	return this.gridPnl.getSelectionModel().getSelections();
    },
    loadGrid : function( config ){
    	if( Ext.isEmpty(config) ){
	    	this.requisitionDataStore.removeAll();
	    	this.requisitionDataStore.load({params:{start:0, limit:10}, arg:[null, 
                                                                             null,
                                                                             null,
                                                                             null,
                                                                             null,
                                                                             null,
                                                                             null,
                                                                             null,
                                                                             null]});
    	}
    	else{
    		this.requisitionDataStore.removeAll();
	    	this.requisitionDataStore.load({params:{start:0, limit:10}, arg:[config.patientName, 
                                                                             config.patientId,
                                                                             config.referenceType,
                                                                             config.doctorName,
                                                                             config.doctorId,
                                                                             config.requisitionFromDate,
                                                                             config.requisitionToDate,
                                                                             config.testName,
                                                                             config.testStatus]});
    	}
    },
    setGridButtonState : function( selectionModel ){
    	var selectedRows = selectionModel.getSelections();	
		this.toolBar.editBtn.disable();
		this.toolBar.viewBtn.disable();
		this.toolBar.createReceiptBtn.disable();
		if( !Ext.isEmpty( selectedRows )){
			var row = selectedRows[0];
			var statusCode = row.data.statusCode;
			var referenceType = row.data.referenceType;
			if( selectedRows.length == 1){
				if( statusCode === limsMsg.TEST_REQUISITION_STATUS_CREATED ){
					if( referenceType === configs.referenceTypeForOPD ||
							referenceType === configs.PatientRegistrationType){// edit button will be enable if referenceType is OPD or DIR.
						this.toolBar.editBtn.enable();
					}
					this.toolBar.createReceiptBtn.enable();
					this.toolBar.viewBtn.enable();
				}
				else if( statusCode === limsMsg.TEST_REQUISITION_STATUS_COMPLETED ){
					this.toolBar.createReceiptBtn.enable();
					
				}
				
				this.toolBar.viewBtn.enable();
			}
    	}	
    }
});

