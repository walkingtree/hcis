Ext.namespace("LIMS.requisitionOrder.manage");

LIMS.requisitionOrder.manage.RequisitionOrderDetailGrid = Ext.extend(Ext.grid.GridPanel, {
	title : limsMsg.requiDetails ,
	
    initComponent : function() {
 
		var mainThis = this;
		
		this.record = Ext.data.Record.create( [
         	{ name : 'requisitionOrderNbr' }, 
	        { name : 'testName', convert : getCode},
	        { name : 'testDesc',mapping : 'testName', convert : getDescription},
		    { name : 'testDate' },
		    { name : 'charges'},
		    { name : 'serviceUID'},
		    { name : 'statusCode' ,mapping : 'status',convert : getCode },
		    { name : 'status' ,convert : getDescription },
		    { name : 'type'},
		    { 
		    	name : 'hideSampleCollected',
	    		mapping:'statusCode', 
	    		convert: function(val, rec){
		    	
		    			return mainThis.getSpecimenColctBtnValue(rec.type, rec.status.code);
		    	}
		    },
		    { 
		    	name : 'hideTestPerformed',
	    		mapping:'statusCode', 
	    		convert: function(val, rec){
		    	
		    	return mainThis.getTestPerformedBtnValue(rec.type, rec.status.code);
	    		}
		    },
		    { 
		    	name : 'hideEnterTestResult',
	    		mapping:'statusCode', 
	    		convert: function(val, rec){
		    	return mainThis.getEnterResultBtnValue(rec.type, rec.status.code);
	    		}
		    },
		    { 
		    	name : 'hideApprove',
	    		mapping:'statusCode', 
	    		convert: function(val, rec){
		    	return mainThis.getApproveBtnValue(rec.type, rec.status.code);
	    		}
		    },
		    { 
		    	name : 'hideDisapprove',
	    		mapping:'statusCode', 
	    		convert: function(val, rec){
		    	return mainThis.getDisapproveBtnValue(rec.type, rec.status.code);
		    	}	
		    },
		    {
		    	name : 'hideView',
		    	mapping:'statusCode',
		    	convert: function(val, rec){
		    	return mainThis.getViewBtnValue(rec.type, rec.status.code);
		    	}
		    }
         ]);

	 	this.gridChk = new Ext.grid.CheckboxSelectionModel();
	 	
		this.action = new Ext.ux.grid.RowActions({
			 header:'Actions'
			,keepSelection:true
			,widthSlope : 50
			,actions:[
				{
					//specimen collected
					iconCls:'bottom-arrow-icon',
					tooltip:'Sample Collected',
					hideIndex:'hideSampleCollected'
				},
				{
					//test performed
					iconCls:'accept-icon',
					tooltip:'Test performed',
					hideIndex:'hideTestPerformed'
				},
				{	//enter edit test 
					iconCls:'report-icon',
					tooltip:'Enter/edit test result',
					hideIndex:'hideEnterTestResult'
				},
//				{
//					iconCls:'approve-icon',
//					tooltip:'Approve',
//					hideIndex:'hideApprove'
//				},
//				{
//					iconCls:'disapprove-icon',
//					tooltip:'Disapprove',
//					hideIndex:'hideDisapprove'
//				},
				{
					iconCls:'view-icon',
					tooltip:'View result',
					hideIndex:'hideView'
				},
			]
		});
	 	

        this.gridColumns = [
			this.gridChk ,
//	           { header :limsMsg.number , dataIndex :'requisitionOrderNbr', width : 100 }, 
	           { header :limsMsg.testName, dataIndex :'testDesc' }, 
	           { header :limsMsg.testDate, dataIndex :'testDate' }, 
	           { header :limsMsg.charges, dataIndex :'charges', width : 100 }, 
	           { header :limsMsg.status , dataIndex :'status', width : 100 },
	           this.action
	    ];
          
		this.dataStore = new Ext.data.Store({
			proxy :new Ext.data.DWRProxy( RequisitionOrderManager.getRequisitionOrderDetail, true ),
			reader :new Ext.data.ListRangeReader( {id :'id',totalProperty :'totalSize'}, this.record),
			remoteSort :true	//RequisitionOrderDetail
		
		});
		
		this.pagingBar = new Ext.WTCPagingToolbar({
	                store: this.dataStore,
	                displayMsg: msg.pagingbarDisplayMsg,
			        emptyMsg: limsMsg.requisitionDetailGridMsg
	    });
		
	   
		this.gridPnl = new Ext.grid.GridPanel({
	        viewConfig : {forceFit :true},
	        remoteSort :true,
	      	frame : true,
	    	border : false,
	    	height : 315,
	    	tbar:this.toolBar,
	    	stripeRows :true,
	    	autoScroll :true,
	    	sm : this.gridChk,
	    	bbar : this.pagingBar,
	    	store : this.dataStore,
	    	columns : this.gridColumns,
	    	plugins : this.action
		});
      
		Ext.applyIf(this, {
			items: [
			   {
					columnWidth : 1,
					items :[this.gridPnl]
	           }
			]            
      });

      LIMS.requisitionOrder.manage.RequisitionOrderDetailGrid .superclass.initComponent.apply(this, arguments);
    },
    
    getPanel : function(){
    	return this.gridPnl;
    },
    
    loadData : function( config ){
    	
    	if( this.gridPnl.getStore() != null ){
    		
    		if( Ext.isEmpty(config)){
    			config = { };
    			this.gridPnl.getStore().reload();
    		}
    		else{
    			this.gridPnl.getStore().removeAll();
    			this.gridPnl.getStore().load({params:{start:0, limit:10}, arg:[config.requisitionOrderNbr]});
    		}
    	}
    },
    
    getSelection : function(){
    	return this.gridPnl.getSelectionModel().getSelections();
    },
      
    getEnterResultBtnValue : function( type  , statusCode){
    	
    	
    	if ( type === limsMsg.laboratory ) {
			
    		if ( statusCode === limsMsg.SERVICE_STATUS_TEST_PERFORMED) {
				return false;
			} 
		}
    	
    	return true;
    },
    
    getSpecimenColctBtnValue : function( type  , statusCode){
    	
    	if ( type === limsMsg.laboratory ) {
			
    		if ( statusCode === limsMsg.SERVICE_STATUS_REQUESTED ||
    			 statusCode === limsMsg.SERVICE_STATUS_DISAPPROVED ) {
				return false;
			}
		}
    	return true;
    },
   
    getTestPerformedBtnValue : function( type  , statusCode){
    	
    	if ( type === limsMsg.laboratory ) {
			
    		if ( statusCode === limsMsg.SERVICE_STATUS_REQUESTED || 
    			 statusCode === limsMsg.SERVICE_STATUS_SPECIMEN_COLLECTED ||
    			 statusCode === limsMsg.SERVICE_STATUS_DISAPPROVED) {
				return false;
			} 
		}
    	return true;
    },
  
    getApproveBtnValue : function( type  , statusCode){
    	
    	if ( type === limsMsg.laboratory ) {
			
    		if ( statusCode === limsMsg.SERVICE_STATUS_RESULT_ENTERED) {
				return false;
			}
		}
    	return true;
    },
    
    getDisapproveBtnValue : function( type  , statusCode){
    	
    	if ( type === limsMsg.laboratory ) {
			
    		if ( statusCode === limsMsg.SERVICE_STATUS_RESULT_ENTERED) {
				return false;
			} 
		}
    	return true;
    },
    
    getViewBtnValue : function( type  , statusCode){
    	
    	if ( type === limsMsg.laboratory ) {
			
    		if ( statusCode === limsMsg.SERVICE_STATUS_RESULT_ENTERED ||
    			 statusCode === limsMsg.SERVICE_STATUS_DISAPPROVED ||
    			 statusCode === limsMsg.SERVICE_STATUS_APPROVED) {
				return false;
			} 
		}
    	return true;
    },
    
    getRecordArray : function(){
    	return this.recordArray;
    }
});

