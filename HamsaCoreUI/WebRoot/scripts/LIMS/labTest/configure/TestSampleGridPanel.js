Ext.namespace("LIMS.labTest.configure");

LIMS.labTest.configure.TestSampleGridPanel = Ext.extend(Ext.form.FormPanel, {
//    title : limsMsg.techniqueList,
    layout : 'fit',
    border : false,
    frame : false,
	width:'98%',
	height:350,
	initComponent : function() {
        this.record = Ext.data.Record.create( [ 
	        { name : 'specimenId',  type :'int'},
	        { name : 'specimenName'},
	        { name : 'unit'},
	        { name : 'quantity' },
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
			header : limsMsg.lblSpecimen ,
			dataIndex : 'specimenName',
			width : 50
		}, {
			header : limsMsg.lblSpecimenQtty,
			dataIndex : 'quantity',
			width : 50
		}, {
			header : limsMsg.lblUnit,
			dataIndex : 'unit',
			width : 50
		}, {
			header : limsMsg.lblIsMendatory,
			dataIndex : 'isMandatory',
			width : 50
		} ];
       
       this.toolBar = new utils.GridToolBar();
 	   this.toolBar.getAddBtn().hide();
       
	   this.dataStore = new Ext.data.Store( {
			data : [],
			reader : new Ext.data.ArrayReader( {
				id : 'specimenId'
			}, this.record)
		});
		
		
		
       this.gridPnl = new Ext.grid.GridPanel({
		  frame : false,
		  border : false,
          tbar : this.toolBar,
		  height : 150,
          stripeRows :true,
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
      
      Ext.applyIf(this, {
	 	  items: [{
	                  columnWidth : 1,
	                  items :[this.gridPnl]
	      }]            
      });

      LIMS.labTest.configure.TestSampleGridPanel.superclass.initComponent.apply(this, arguments);
    },
    
    getData : function(){

		var tmpList = new Array();
		var storeValues = this.gridPnl.getStore().data.items;
		for(var i =0; i<storeValues.length;i++){
			var values = storeValues[i].data;
			var labTestSpecimenAssocBM ={
					labSpecimen:{ code:values.specimenId},
					quantity:values.quantity,
					unit:values.unit,
					isMandatory:values.isMandatory
			};
			tmpList.push( labTestSpecimenAssocBM );
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
					specimenId : list[i].labSpecimen.code,
					specimenName : list[i].labSpecimen.description,
					quantity : list[i].quantity,
					unit : list[i].unit,
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
