Ext.namespace("administration.checkList"); 

administration.checkList.CheckListSearchGrid = Ext.extend(Ext.grid.GridPanel, {
    initComponent : function(){
	
	
        this.record = Ext.data.Record.create([
         	{ name : 'name' }, 
	        { name : 'checkListId'},
	        { name : 'checkListType', mapping : 'checkListType', convert : getDescription},
	        { name : 'type', mapping : 'checkListType', convert : getCode},
	        { name : 'createdDtm'}
         ]);

 		this.gridChk = new Ext.grid.CheckboxSelectionModel({
	 		listeners:{
				rowselect : function( selectionModel, rowIndex, record){
		 			this.gridToolar.getEditBtn().disable();
		 			this.gridToolar.getDeleteBtn().disable();
		 			this.setGridButtonState();
	 			},
				rowdeselect : function( selectionModel, rowIndex, record){
		 			this.gridToolar.getEditBtn().disable();
		 			this.gridToolar.getDeleteBtn().disable();
		 			this.setGridButtonState();
				},
				scope : this
 			},
 			scope : this
 		});

        this.gridColumns = [
			this.gridChk ,
	           { header :"Check List Id", dataIndex :'checkListId', width : 50 }, 
	           { header :"Name", dataIndex :'name'} ,
	           { header :"Type", dataIndex :'checkListType', width : 100 }, 
	           { header :"Creation Date", dataIndex :'createdDtm', width : 50 } 
	    ];
          
		this.store = new Ext.data.Store({
			proxy :new Ext.data.DWRProxy(CheckListManager.getCheckList, true),
			reader :new Ext.data.ListRangeReader( {id :'id',totalProperty :'totalSize'}, this.record),
			remoteSort :true
		
		});
		
		this.pagingBar = new Ext.WTCPagingToolbar({
            store: this.store,
            displayMsg: msg.pagingbarDisplayMsg,
	        emptyMsg: otMsg.otSrchGridMsg
		});
		
		this.gridToolar = new utils.GridToolBar();
		this.gridToolar.getReportBtn().hide();
		
    	this.on('cellclick',function(thisGrid, rowIndex, columnIndex, eventObj){
    		this.setGridButtonState();
    	},this);

		
		this.gridToolar.getAddBtn().on('click',function(){
			this.addBtnClicked();
		},this);
		
		this.gridToolar.getEditBtn().on('click',function(){
			this.editBtnClicked();
		},this);
		
		this.gridToolar.getDeleteBtn().on('click',function(){
			this.delBtnClicked();
		},this);
		
		
		
		this.getStore().on('load',function(thisGrid){
			this.setGridButtonState();
		},this);
		
		Ext.applyIf(this,{
	        viewConfig : {forceFit :true},
		    remoteSort :true,
		  	frame : false,
			border : false,
			height : 320,
			stripeRows :true,
			autoScroll :true,
			sm : this.gridChk,
			columns : this.gridColumns,
			tbar : this.gridToolar,
			bbar : this.pagingBar

		});
		
		administration.checkList.CheckListSearchGrid.superclass.initComponent.apply(this, arguments);
    },
    
    reset : function(){
    	this.getStore().removeAll();
    },
    
    addBtnClicked : function(){
    	var otSearchPanel = this.initialConfig.otSearchPanel;
    	
    	var panel = new administration.checkList.configure.ConfigureCheckList({mode : 'add',grid:this});
    		
		panel.title = 'Configure Check List';
		panel.frame = true;
		panel.closable = true;
		panel.height = 420;
		
		var activeTab =	layout.getCenterRegionTabPanel().add(panel);
		
		layout.getCenterRegionTabPanel().setActiveTab( activeTab );
		layout.getCenterRegionTabPanel().doLayout();

    },
    
    editBtnClicked : function(){
    	
    	var otSearchPanel = this.initialConfig.otSearchPanel;
    	
    	var selectedRow = this.getSelectedRows();
    	var config = {
    		checkListId :selectedRow[0].data.checkListId ,
    		checkListName: selectedRow[0].data.name ,
			type:selectedRow[0].data.type
    	};
    	


		var panel = new administration.checkList.configure.ConfigureCheckList({mode : 'edit', checkListId : config.checkListId});
		
		panel.title = otMsg.editOperationTheatre;
		panel.frame = true;
		panel.closable = true;
		panel.height = 420;
		
		panel.loadData( config );
		
		var activeTab =	layout.getCenterRegionTabPanel().add( panel );
		
		layout.getCenterRegionTabPanel().setActiveTab( activeTab );
		layout.getCenterRegionTabPanel().doLayout();
    	
    },
    
    delBtnClicked : function(){
    	var mainThis = this;
    	var selectedRows = this.getSelectedRows();
    	var otIdList = new Array();
    	for( var i = 0 ; i < selectedRows.length ; i++){
    		otIdList.push(selectedRows[i].data.checkListId );
    	}
    	
    	CheckListManager.removeCheckList(otIdList,function(){
    		mainThis.loadGrid();
    	});
    	
    },
    actionClicked : function(grid, record, action, row, col){
    	if( action === ""){
    		
    	}
    	else if( action === ""){
    		
    	}
    	else if( action === ""){
    		
    	}
    	
    },
    
    getSelectedRows : function(){
    	return this.getSelectionModel().getSelections();
    },
    
    loadGrid : function( config ){
    	if( Ext.isEmpty( config )){
    		config ={};
    	}
    	if( !Ext.isEmpty( this.getStore() )){
    		this.getStore().load({params:{start:0, limit:10}, arg:[config.checkListName, 
                                                               config.type,
                                                               config.groupName,
                                                               config.role,
                                                               ]});
    	}
    	
    },
    
    setGridButtonState : function(){
		this.gridToolar.getEditBtn().disable();
		this.gridToolar.getDeleteBtn().disable();

    	var selectedRows = this.getSelectedRows();
    	if( selectedRows.length === 1){
    		this.gridToolar.getEditBtn().enable();
    		this.gridToolar.getDeleteBtn().enable();
    	}
    	else if( selectedRows.length > 1){
    		this.gridToolar.getDeleteBtn().enable();
    	}
    }
});