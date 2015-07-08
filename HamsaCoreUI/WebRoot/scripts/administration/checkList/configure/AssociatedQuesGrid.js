Ext.ns("administration.checkList.configure"); 

administration.checkList.configure.AssociatedQuesGrid = Ext.extend(Ext.grid.GridPanel, {
    initComponent : function() {
    	
        this.record = Ext.data.Record.create( [
           	{name  : 'checkListDetailId'},
         	{ name : 'question'}, 
	        { name : 'role',mapping: 'role', convert:getCode},
	        { name : 'roleDesc', mapping: 'role', convert:getDescription},
	        { name : 'group'}
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
	           { header :checkListMsg.lblQues, dataIndex :'question', width : 100 }, 
	           { header :checkListMsg.lblRole, dataIndex :'roleDesc', width : 100 }, 
	           { header :checkListMsg.lblGroup, dataIndex :'group', width : 100 } 
	    ];
          
		this.store = new Ext.data.Store({
			proxy :new Ext.data.DWRProxy(CheckListManager.getCheckListDetails, true),
			reader :new Ext.data.ListRangeReader( {id :'id',totalProperty :'totalSize'}, this.record),
			remoteSort :true	//
		
		});
		
		this.gridToolbar = new utils.GridToolBar();
		this.gridToolbar.getAddBtn().hide();
		this.gridToolbar.getReportBtn().hide();
		

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
			this.setGridButtonState();
		},this);
		
		this.gridToolbar.getDeleteBtn().on('click',function(){
			this.delBtnClicked();
		},this);
		
    	this.on('cellclick',function(thisGrid, rowIndex, columnIndex, eventObj){
    		this.setGridButtonState();
    	},this);
    	
		administration.checkList.configure.AssociatedQuesGrid.superclass.initComponent.apply(this, arguments);
    },
    
    reset : function(){
    	this.getStore().removeAll();
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
    	var questionList = this.getStore().getRange();
    	return questionList;
    },
    
    loadGrid : function( config ){
    	if( Ext.isEmpty( config )){
    		config ={};
    	}
    	this.getStore().load({params:{start:0, limit:999}, arg:[config.checkListId]});
    },
    
    setGridButtonState : function(){
		this.gridToolbar.getDeleteBtn().disable();
		this.gridToolbar.getEditBtn().disable();
		
    	var selectedRows = this.getSelectedRows();
    	if( selectedRows.length >= 1){
    		this.gridToolbar.getDeleteBtn().enable();
    		
    		if(selectedRows.length == 1){
    			this.gridToolbar.getEditBtn().enable();
    		}
    	}
    }

    
});