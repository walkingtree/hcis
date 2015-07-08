Ext.namespace("OT.manageOT"); 

OT.manageOT.OTSearchGrid = Ext.extend(Ext.grid.GridPanel, {
    initComponent : function(){
	
	    this.action = new Ext.ux.grid.RowActions({
			 header:'Actions'
			,keepSelection:true
			,actions:[
				{
					iconCls:'discharge-icon',
					tooltip:otMsg.releasePatient,
					hideIndex:'hideReleasePatient'
				},
				{
					iconCls:'user-add-icon',
					tooltip:otMsg.admitPatient,
					hideIndex:'hideAdmitPatient'
				},
				{
					iconCls:'view-icon',
					tooltip:otMsg.viewBookingAvailabilityDetail,
					hideIndex:'hideViewBookingAvailabilityDetail'
				}
			]
		});
	
        this.record = Ext.data.Record.create([
         	{ name : 'name',mapping : 'name' }, 
	        { name : 'otId', mapping : 'otId'},
	        { name : 'bedNumber', mapping : 'bedNumber'},
	        { name : 'coordinator', mapping : 'coordinator', convert : getDescription},
	        { name : 'coordinatorId', mapping : 'coordinator', convert : getCode},
	        { 
		    	name : 'hideReleasePatient',
	    		mapping:'name', 
	    		convert: function(val, rec){
	    			return false;
		    	}
		    },
		    { 
		    	name : 'hideAdmitPatient',
	    		mapping:'name', 
	    		convert: function(val, rec){
	    			return false;
		    	}
		    },
		    { 
		    	name : 'hideViewBookingAvailabilityDetail',
	    		mapping:'name', 
	    		convert: function(val, rec){
	    			return false;
		    	}
		    }
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
	           { header :otMsg.otName, dataIndex :'name', width : 100 }, 
	           { header :otMsg.otStatus, dataIndex :''} ,
	           { header :otMsg.lblBedNbr, dataIndex :'bedNumber', width : 100 }, 
	           { header :otMsg.lblCoordinator, dataIndex :'coordinator', width : 100 }, 
//	           this.action
	    ];
          
		this.store = new Ext.data.Store({
			proxy :new Ext.data.DWRProxy(OTManager.getOTDetail, true),
			reader :new Ext.data.ListRangeReader( {id :'id',totalProperty :'totalSize'}, this.record),
			remoteSort :true	//
		
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
		
		this.action.on('action',function(grid, record, action, row, col){
			this.actionClicked(grid, record, action, row, col);
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
//			plugins : this.action,
			bbar : this.pagingBar

		});
		
		OT.manageOT.OTSearchGrid.superclass.initComponent.apply(this, arguments);
    },
    
    reset : function(){
    	this.getStore().removeAll();
    },
    
    addBtnClicked : function(){
    	var otSearchPanel = this.initialConfig.otSearchPanel;
    	
    	var panel = new OT.manageOT.ConfigureOT({mode : 'add', otSearchPanel : otSearchPanel});
    		
		panel.title = otMsg.addOperationTheatre;;
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
			otCode: selectedRow[0].data.otId ,
			otName:selectedRow[0].data.name,
			bedNbr:selectedRow[0].data.bedNumber,
			coordinator:selectedRow[0].data.coordinatorId
    	};
    	
    	OTManager.getAssociatedSurgeryForOT( selectedRow[0].data.otId , function( otDetailBM ){

    		var panel = new OT.manageOT.ConfigureOT({mode : 'edit', otSearchPanel : otSearchPanel});
    		
    		panel.title = otMsg.editOperationTheatre;
    		panel.frame = true;
    		panel.closable = true;
    		panel.height = 420;
    		
    		panel.loadData( config );
    		panel.loadSurgeryAssoGrid( otDetailBM.otSurgeryAssoBMList );
    		
    		var activeTab =	layout.getCenterRegionTabPanel().add( panel );
    		
    		layout.getCenterRegionTabPanel().setActiveTab( activeTab );
    		layout.getCenterRegionTabPanel().doLayout();
    		
    	});
    	
    },
    
    delBtnClicked : function(){
    	var mainThis = this;
    	var selectedRows = this.getSelectedRows();
    	var otIdList = new Array();
    	for( var i = 0 ; i < selectedRows.length ; i++){
    		otIdList.push(selectedRows[i].data.otId )
    	}
    	
    	OTManager.removeOTList(otIdList, getAuthorizedUserInfo().userName, function(){
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
    		this.getStore().load({params:{start:0, limit:10}, arg:[config.otId, 
                                                               config.otName,
                                                               config.bedNumber,
                                                               config.surgeryCode,
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