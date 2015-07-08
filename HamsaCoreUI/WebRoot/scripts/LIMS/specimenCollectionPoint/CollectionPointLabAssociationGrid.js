Ext.namespace("LIMS.specimenCollectionPoint"); 

LIMS.specimenCollectionPoint.CollectionPointLabAssociationGrid = Ext.extend(Ext.Panel, {
    initComponent : function() {
    	var mainThis = this;
    	
        this.record = Ext.data.Record.create( [
         	{ name : 'labId',mapping : 'code' }, 
	        { name : 'labName', mapping : 'description'},
         ]);

 		this.gridChk = new Ext.grid.CheckboxSelectionModel();

        this.gridColumns = [
			this.gridChk ,
	           { header :limsMsg.labId, dataIndex :'labId', width : 100 }, 
	           { header :limsMsg.laboratoryName , dataIndex :'labName', width : 200 } 
	    ];
          
		this.labDataStore = new Ext.data.Store({
			proxy :new Ext.data.DWRProxy(LimsReferenceDataManager.getLabs, true),
			reader :new Ext.data.ListRangeReader( {id :'id',totalProperty :'totalSize'}, this.record),
			remoteSort :true	//
		
		});
		
	       
		this.gridPnl = new Ext.grid.GridPanel({
			title : limsMsg.gridDefaultTitle,
	        viewConfig : {forceFit :true},
	        remoteSort :true,
	      	frame : true,
	    	border : false,
	    	height : 200,
	    	width : '95%',
	    	tbar:this.toolBar,
	    	stripeRows :true,
	    	autoScroll :true,
	    	sm : this.gridChk,
	    	store : this.labDataStore,
	    	columns : this.gridColumns
		});
	       
		Ext.applyIf(this, {
			items: [
		        {
					columnWidth : 1,
					items :[this.gridPnl]
		        }
			]            
		});
	
		LIMS.specimenCollectionPoint.CollectionPointLabAssociationGrid.superclass.initComponent.apply(this, arguments);
    },
    
    setTitle : function( title ){
    	return this.gridPnl.setTitle( title );
    },
    
    loadStore : function(){
    	this.labDataStore.load({params:{start:0, limit:10}, arg:[]});
    },
    
    getData : function(){
    	return this.gridPnl.getSelectionModel().getSelections();
    },
    
    setData : function( recordDataList ){
    	
    	if( !Ext.isEmpty( recordDataList )){
    		for( var i = 0 ; i< recordDataList.length ; i++ ){
    			
    			this.gridPnl.getStore().add( recordDataList[i])
    		}
    	}	
    },
    
    removeData : function( recordDataList ){
    	
    	if( !Ext.isEmpty( recordDataList )){
			for( var i = 0 ; i < recordDataList.length ; i++){
				this.gridPnl.getStore().remove( recordDataList[i] );
			}
    	}
    	else{
    		this.gridPnl.getStore().removeAll();
    	}
    },
    
    getStore : function(){
    	return this.gridPnl.store;
    },
    
    filterAvailableTestResults : function(associtedLabList){
    	this.associtedLabList = associtedLabList;
    	this.gridPnl.getStore().on('load',function(thisStore , record , options){
    		for( var i=0 ; i < this.associtedLabList.length ; i++){
				var indexNbr = this.gridPnl.getStore().find( 'labId', this.associtedLabList[i].code );
				var record = this.gridPnl.getStore().getAt( indexNbr );
				
				thisStore.remove( record );
    		}
    	},this);
    },
    
    setDataForAssociatedGrid : function( recordDataList ){
    	
    	var recordType = this.gridPnl.store.recordType;
    	
    	if( !Ext.isEmpty( recordDataList )){
    		for( var i = 0 ; i< recordDataList.length ; i++ ){
	    		var gridRecord = new recordType({
	    			labId : recordDataList[i].code,
	    			labName : recordDataList[i].description
    	    	});
	    		
	    		this.gridPnl.store.add( gridRecord );
    		}
    	}	
    }

    
    
});