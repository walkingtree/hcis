Ext.namespace("LIMS.technique");

LIMS.technique.TechniqueListGridPanel = Ext.extend(Ext.form.FormPanel, {
//    title : limsMsg.techniqueList,
    layout : 'fit',
    border : false,
    frame : false,
	width:'98%',
	height:350,
	initComponent : function() {
        this.record = Ext.data.Record.create( [ 
	        { name : 'techniqueId', mapping : 'techniequeReagentId', type :'int'},
	        { name : 'name', mapping : 'name'},
	        { name : 'isTechnique', mapping : 'isTechnique'},
	        { name : 'description', mapping : 'description'}
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
			header : limsMsg.lblTechniqueId,
			dataIndex : 'techniqueId',
			width : 150
		}, {
			header : limsMsg.lblTechniqueName ,
			dataIndex : 'name',
			width : 150
		}, {
			header : limsMsg.lblTechReagent,
			dataIndex : 'isTechnique',
			width : 200,
			renderer : function(value) {
				if (value === 'Y')
					return limsMsg.lblTechnique;
				else {
					return limsMsg.lblReagent;
				}
			}
		}, {
			header : limsMsg.lblTechniqueDesc,
			dataIndex : 'description',
			width : 200
		} ];
       
       this.toolBar = new utils.GridToolBar();
//	   this.toolBar.add( new Ext.Toolbar.Separator());
       
	   this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.record),
        	proxy: new Ext.data.DWRProxy(LabConfigManager.getTechniquReagents, true)
		});
		
		this.pagingBar = new Ext.WTCPagingToolbar({
			store : this.dataStore,
			displayMsg : limsMsg.displayingTechniqueMsg,
			emptyMsg : limsMsg.noTechinqueMsg
		});
		
       this.gridPnl = new Ext.grid.GridPanel({
		  frame : true,
		  border : false,
          tbar : this.toolBar,
		  height : 300,
          stripeRows :true,
          autoScroll :true,
          store : this.dataStore,
          columns : this.gridColumns,
          viewConfig : {forceFit :true},
          remoteSort :true,
          sm: this.gridChkSelcModl,
          bbar:this.pagingBar
//          sortInfo: {field: 'scheduleName', direction: 'ASC'}
      });
      
      this.gridPnl.on('cellclick', function( thisGrid, rowIndex, colIndex, e){
      		this.setGridButtonsState( thisGrid.getSelectionModel() );
      },this);
      
      this.gridPnl.on('render',function(){
		this.loadData();      
	  },this);

      Ext.applyIf(this, {
	 	  items: [{
	                  columnWidth : 1,
	                  items :[this.gridPnl]
	             }
				]            
      });

      LIMS.technique.TechniqueListGridPanel.superclass.initComponent.apply(this, arguments);
    },
    
    loadData : function( config ){
		this.gridPnl.getStore().removeAll();
		if( !Ext.isEmpty( config )){
			this.gridPnl.getStore().load({params:{start:0, limit:10}, arg:[ null,
			                                                                config[limsMsg.techniqueName],
																			config.isTechnique,
																			config.labTest,
																			]});
		}else{
			this.gridPnl.getStore().load({params:{start:0, limit:10}, arg:[ null, null,null,null]});
		}
		this.setGridButtonsInitialState();
    },

    getReset : function(){
    	this.gridPnl.getStore().removeAll();
    },
    
    getToolbar : function(){
    	return this.toolBar;
    },
    getSelection : function(){
    	return this.gridPnl.getSelectionModel().getSelections();
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
