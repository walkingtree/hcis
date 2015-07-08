Ext.namespace("LIMS.labTest.configure");

LIMS.labTest.configure.TestAttributeGridPanel = Ext.extend(Ext.form.FormPanel, {
    layout : 'fit',
    border : false,
    frame : false,
	width:'98%',
	height : 350,
	initComponent : function() {
        this.record = Ext.data.Record.create( [ 
	        { name : 'labTestAttributeId'},
	        { name : 'labTestAttributeName'},
	        { name : 'type'},
	        { name : 'minValue'},
	        { name : 'maxValue' },
	        { name : 'isMandatory'}
    	]);

	 	this.gridChkSelcModl = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					 this.toolBar.getEditBtn().disable();
					this.toolBar.getDeleteBtn().enable();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					this.toolBar.getEditBtn().disable();
					this.toolBar.getDeleteBtn().disable();
				},
				scope:this
			}
		});
		
        this.gridColumns = [ this.gridChkSelcModl, {
			header : limsMsg.lblAttributeName ,
			dataIndex : 'labTestAttributeName',
			width : 50
		}, 
		{
			header : limsMsg.lblAttributeType,
			dataIndex : 'type',
//			renderer: function( value, meta,rowIndex,colIndex,store){
//				if( !Ext.isEmpty( value ) ){
//					return value.description;
//				}
//				return '';
//			},
			width : 50
		}, {
			header : limsMsg.lblMinValue,
			dataIndex : 'minValue',
			editor:new Ext.form.NumberField(),
			width : 50
		}, {
			header : limsMsg.lblMaxVal,
			dataIndex : 'maxValue',
			editor:new Ext.form.NumberField(),
			width : 50
		},{
			header : limsMsg.lblIsMendatory,
			dataIndex : 'isMandatory',
			width : 50
		} ];
       
       this.toolBar = new utils.GridToolBar();
       this.toolBar.getAddBtn().hide();
       
	   this.dataStore = new Ext.data.Store( {
			data : [],
			reader : new Ext.data.ArrayReader( {
				id : 'labTestAttribuId'
			}, this.record)
		});
		
//
//	   this.dataStore = new Ext.data.Store({
//			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.record),
//        	proxy: new Ext.data.DWRProxy(LabConfigManager.getTechniquReagents, true)
//		});
//	   
		this.pagingBar = new Ext.WTCPagingToolbar({
			store : this.dataStore
		});
		
       this.gridPnl = new Ext.grid.EditorGridPanel({
		  frame : false,
		  border : false,
          tbar : this.toolBar,
		  height : 150,
          stripeRows :true,
          clicksToEdit: 1,
          autoScroll :true,
          store : this.dataStore,
          columns : this.gridColumns,
          viewConfig : {forceFit :true},
          remoteSort :true,
          sm: this.gridChkSelcModl
      });
      
      this.gridPnl.on('cellclick', function( thisGrid, rowIndex, colIndex, e){
      		this.setGridButtonsState( thisGrid.getSelectionModel() );
      },this);
      
//      this.gridPnl.on('render',function(){
//		this.loadData();      
//	  },this);

      Ext.applyIf(this, {
	 	  items: [{
	                  columnWidth : 1,
	                  items :[this.gridPnl]
	             }
				]            
      });

      LIMS.labTest.configure.TestAttributeGridPanel.superclass.initComponent.apply(this, arguments);
    },
    
    getData : function(){

		var tmpList = new Array();
		var storeValues = this.gridPnl.getStore().data.items;
		for(var i =0; i<storeValues.length;i++){
			var values = storeValues[i].data;
			var labTestAttributeAssocBM ={
					labTestAttribute:{ code:values.labTestAttributeId},
					minValue:values.minValue,
					maxValue:values.maxValue,
					isMandatory:values.isMandatory
			};
			tmpList.push( labTestAttributeAssocBM );
		}
		return tmpList;
     
    },
    
    loadGridData: function( list ){
    	
    	if( !Ext.isEmpty( this.getGrid().getStore() )){
			this.getGrid().getStore().removeAll();
		}
    	
		if( !Ext.isEmpty( list )){
			var record = this.getGrid().getStore().recordType;
			for( var i = 0; i<list.length; i++ ){
				var data = new record({
					labTestAttributeId : list[i].labTestAttribute.code,
					labTestAttributeName : list[i].labTestAttribute.description,
					type : list[i].attributeType,
					minValue : list[i].minValue,
					maxValue : list[i].maxValue,
					isMandatory : list[i].isMandatory
				});
				
				this.getGrid().getStore().add( data );
			}
		}
		
     },

    getReset : function(){
    	this.gridPnl.getStore().removeAll();
    },
    
    getToolBar : function(){
    	return this.toolBar;
    },
    getSelection : function(){
    	return this.gridPnl.getSelectionModel().getSelections();
    },
    
    getGrid : function(){
     	return this.gridPnl;
     },
     
     deleteRow: function(){
     	var selections =  this.getSelectionModel().getSelections();
     	if( selections.length >0 ){
     		for( var i = 0; i<selections.length; i++ ){
     			this.getStore().remove( selections[i] );
     		}
     	}
     },
     
    setGridButtonsState : function(selectionModel){
		var selectedRows = selectionModel.getSelections();
		this.toolBar.getEditBtn().disable();
		this.toolBar.getDeleteBtn().disable();
		if( selectedRows.length == 1){
			this.toolBar.getEditBtn().enable();
			this.toolBar.getDeleteBtn().enable();
			
		} else if( selectedRows.length > 1){
			this.toolBar.getEditBtn().disable();
			this.toolBar.getDeleteBtn().enable();
		}
	},
	setGridButtonsInitialState: function(){
		this.toolBar.getEditBtn().disable();
		this.toolBar.getDeleteBtn().disable();
	}
	
	
});
