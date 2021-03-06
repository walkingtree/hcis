Ext.namespace("OT.confgureSurgery"); 

OT.configureSurgery.AssociatedOTGrid = Ext.extend(Ext.grid.GridPanel, {
    initComponent : function() {
    	
        this.record = Ext.data.Record.create( [
         	{ name : 'otCode',mapping : 'code' }, 
	        { name : 'otName', mapping : 'description'},
         ]);

 		this.gridChk = new Ext.grid.CheckboxSelectionModel({
 			listeners:{
 				rowselect : function( selectionModel, rowIndex, record){
		 			this.gridToolbar.getDeleteBtn().disable();
		 			this.setGridButtonState();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
		 			this.gridToolbar.getDeleteBtn().disable();
		 			this.setGridButtonState();
				},
				scope : this
 			},
 			scope : this
 		});

        this.gridColumns = [
			this.gridChk ,
	           { header :otMsg.otCode, dataIndex :'otCode', width : 100 }, 
	           { header :otMsg.lblOtName , dataIndex :'otName', width : 200 } 
	    ];
          
		this.store = new Ext.data.Store({
			proxy :new Ext.data.DWRProxy(LimsReferenceDataManager.getLabs, true),
			reader :new Ext.data.ListRangeReader( {id :'id',totalProperty :'totalSize'}, this.record),
			remoteSort :true	//
		
		});
		
		this.gridToolbar = new utils.GridToolBar();
		this.gridToolbar.getAddBtn().hide();
		this.gridToolbar.getReportBtn().hide();
		this.gridToolbar.getEditBtn().hide();
		

//		this.title = otMsg.associatedService;
	    this.remoteSort =true;
	  	this.frame = false;
		this.border = false;
		this.height = 200;
		this.width = '100%';
		this.stripeRows =true;
		this.autoScroll =true;
		this.columns = this.gridColumns;
		this.tbar = this.gridToolbar;
		this.viewConfig = {forceFit :true};
		this.sm = this.gridChk;
		
		this.gridToolbar.getEditBtn().on('click',function(){
			this.editBtnClicked();
		},this);
		
		this.gridToolbar.getDeleteBtn().on('click',function(){
			this.delBtnClicked();
		},this);
		
    	this.on('cellclick',function(thisGrid, rowIndex, columnIndex, eventObj){
    		this.setGridButtonState();
    	},this);
    	
		this.getStore().on('load',function(thisGrid){
			this.setGridButtonState();
		},this);
	       
		OT.configureSurgery.AssociatedOTGrid.superclass.initComponent.apply(this, arguments);
    },
    
    reset : function(){
    	this.getStore().removeAll();
    },
    
    editBtnClicked : function(){
    	var selectedRows = this.getSelectedRows();
    	var surgeryCode = selectedRows[0].data.surgeryCode;
    	this.initialConfig.getSurgeryCbx().setValue( surgeryCode );
    	this.getStore().remove( selectedRows[0] );
    },
    
    delBtnClicked : function(){
    	var dataForDeletion = this.getSelectedRows();
		for( var i = 0 ; i < dataForDeletion.length ; i++){
			this.getStore().remove( dataForDeletion[i] );
		}
		
		this.gridToolbar.getDeleteBtn().disable();
    },
    getSelectedRows : function(){
    	return this.getSelectionModel().getSelections();
    },
    
    getData : function(){
    	var otList = this.getStore().getRange();
    	return otList;
    },
    
    loadGrid : function( gridDataList ){
    	var recordType = this.getStore().recordType;
    	if( !Ext.isEmpty( gridDataList )){
    		for( var i = 0 ; i < gridDataList.length ; i++ ){
    			var otRecord = new recordType({
    				otCode : gridDataList[i].otName.code,
    				otName : gridDataList[i].otName.description
    			});
    			
    			this.getStore().add( otRecord );
    		}
    	}
    },
    
    setGridButtonState : function(){
		this.gridToolbar.getDeleteBtn().disable();

    	var selectedRows = this.getSelectedRows();
    	if( selectedRows.length >= 1){
    		this.gridToolbar.getDeleteBtn().enable();
    	}
    }

});