Ext.namespace("LIMS.specimenCollectionPoint.manage"); 

LIMS.specimenCollectionPoint.manage.CollectionPointSearchGrid = Ext.extend(Ext.Panel, {
    initComponent : function() {
    	var mainThis = this;
    	
    	this.gridToolbar = new utils.GridToolBar();
    	this.gridToolbar.deleteBtn.hide();
    	this.gridToolbar.reportBtn.hide();
    	
        this.record = Ext.data.Record.create( [
         	{ name : 'collectionPointId',mapping : 'collectionPointId' }, 
	        { name : 'collectionPointName', mapping : 'name'},
	        { name : 'areaCovered', mapping : 'areaCovered'},
	        { name : 'city', mapping : 'contactDetails.city'},
	        { name : 'phonNbr', mapping : 'contactDetails.phoneNumber'},

	        
         ]);

 		this.gridChk = new Ext.grid.CheckboxSelectionModel({
 			listeners : {
 				rowselect : function(){
 					mainThis.gridToolbar.editBtn.disable();
 				},
 				rowdeselect : function(){
 					mainThis.gridToolbar.editBtn.disable();
 				}
 			}
 		});

        this.gridColumns = [
			this.gridChk ,
	           { header :limsMsg.collectionPointId, dataIndex :'collectionPointId', width : 100 }, 
	           { header :limsMsg.collectionPointName , dataIndex :'collectionPointName', width : 200 }, 
	           { header :limsMsg.areaCovered , dataIndex :'areaCovered', width : 200 }, 
	           { header :limsMsg.city , dataIndex :'city', width : 200 }, 
	           { header :limsMsg.phoneNbr , dataIndex :'phonNbr', width : 200 } 
	    ];
          
		this.collectionPointDataStore = new Ext.data.Store({
			proxy :new Ext.data.DWRProxy(CollectionPointManager.getCollectionPoint, true),
			reader :new Ext.data.ListRangeReader( {id :'id',totalProperty :'totalSize'}, this.record),
			remoteSort :true	//
		
		});
		
		this.pagingBar = new Ext.WTCPagingToolbar({
            store: this.collectionPointDataStore,
            displayMsg: limsMsg.pagingbarDisplayMsg,
	        emptyMsg: limsMsg.collectionPointSearchGridMsg
		});

		this.collectionPointDataStore.on('load',function(){
			this.gridToolbar.editBtn.disable();
		},this);
	       
		this.gridPnl = new Ext.grid.GridPanel({
	        viewConfig : {forceFit :true},
	        remoteSort :true,
	      	frame : true,
	    	border : false,
	    	height : 320,
	    	bbar : this.pagingBar,
	    	tbar:this.gridToolbar,
	    	stripeRows :true,
	    	autoScroll :true,
	    	sm : this.gridChk,
	    	store : this.collectionPointDataStore,
	    	columns : this.gridColumns,
			listeners:{
				 cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
				 	mainThis.setGridButtonsState( thisGrid.getSelectionModel() );
				 }
			}

		});
		
		this.on('render',function(){
			this.loadGrid();
		},this);
	       
		Ext.applyIf(this, {
			items: [
		        {
					columnWidth : 1,
					items :[this.gridPnl]
		        }
			]            
		});
	
		LIMS.specimenCollectionPoint.manage.CollectionPointSearchGrid.superclass.initComponent.apply(this, arguments);
    },
    
    loadGrid : function( paramData ){
    	if(!Ext.isEmpty( paramData )){
	    	this.gridPnl.getStore().load({params:{start:0, limit:10}, arg:[
	           paramData['collectionPointName'],
	           paramData['collectionPointId'],
	           paramData['associatedLab'],
	           paramData['areaCovered'],
	           paramData['city']
	         ]});
    	}
    	else{
    		this.gridPnl.getStore().load({params:{start:0, limit:10}, arg:[null,null,null,null,null]});
    	}
    },
    
    getSelectedData : function(){
    	return this.gridPnl.getSelectionModel().getSelections();
    },
    
    reset : function(){
    	this.gridPnl.getStore().removeAll();
    	this.gridPnl.getStore().load({params:{start:0, limit:10}, arg:[null,null,null,null,null]});
    },
    
    setGridButtonsState : function( selectionModel ){
    	
    	this.gridToolbar.editBtn.disable();
    	
    	var selectedRowCount = selectionModel.getCount();
    	
    	if( selectedRowCount === 1){
    		this.gridToolbar.editBtn.enable();
    	}
    	else{
    		this.gridToolbar.editBtn.disable();
    	}
    }
});