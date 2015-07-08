Ext.namespace("administration.manageEntity.manage");

/*
 *  In this we create a grid panel and used this one in EntityList for searching the Entity details
 */

administration.manageEntity.manage.EntityGridPanel = Ext.extend(Ext.Panel, {
    layout : 'fit',
    border : false,
    frame : false,
	width:'98%',
	initComponent : function() {
        this.record = Ext.data.Record.create( [ 
	        { name : 'entityId', mapping : 'entityId'},
	        { name : 'name', mapping : 'name'},
	        { name : 'typeCode', mapping : 'typeCode',convert:getDescription},
	        { name : 'typeCodeDesc', mapping : 'typeCode',convert:getCode},
	        { name : 'isPermanent', mapping : 'isPermanent'},
	        { name : 'genderDescription' ,mapping : 'gender',convert:getDescription},
	        { name : 'genderCode' ,mapping : 'gender',convert:getCode},
	        { name : 'saluation', mapping : 'saluation',convert:getCode},
	        { name : 'contactDetailsBM', mapping : 'contactDetailsBM'},
	        { name : 'dob', mapping : 'dob'},
	        { name : 'image', mapping : 'image'},
	        { name : 'knownLanguages', mapping : 'knownLanguages'},
	        { name : 'qualification', mapping : 'qualification'},
	        { name : 'joiningDt', mapping : 'joiningDt'},
	        { name : 'experience', mapping : 'experience'},
	        { name : 'referredBy', mapping : 'referredBy'},
	        { name : 'image', mapping : 'image'}
    	]);

	 	this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					 this.toolBar.getEditBtn().disable();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					this.toolBar.getEditBtn().disable();
				},
				scope:this
			}
		});
		
        this.gridColumns = [
			this.gridChk ,
	           { header :"Id", dataIndex :'entityId', width : 150 }, 
	           { header :"Name", dataIndex :'name', width : 200 }, 
	           { header :"Type", dataIndex :'typeCode', width : 200 }, 
	           { header :"Gender", dataIndex :'genderDescription', width : 200 },
	           { header :"Permanent?", dataIndex :'isPermanent', width : 200 } 
	    ];
       
	   this.viewDetailsBtn = new Ext.Button({
	   		text: entityMsg.viewDetails,
	   		iconCls:'view-icon',
	   		disabled: true
	   });
       this.toolBar = new utils.GridToolBar();
	   this.toolBar.add( new Ext.Toolbar.Separator());
       
	   this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.record),
        	proxy: new Ext.data.DWRProxy(EntityManager.getEntities, true)
		});
		
		this.pagingBar = new Ext.WTCPagingToolbar({
			store : this.dataStore,
			displayMsg : entityMsg.displayingEntityMsg,
			emptyMsg : entityMsg.noEntityMsg
		});
		
       this.gridPnl = new Ext.grid.GridPanel({
		  frame : false,
		  border : false,
          tbar : this.toolBar,
		  height : 300,
          stripeRows :true,
          autoScroll :true,
          store : this.dataStore,
          columns : this.gridColumns,
          viewConfig : {forceFit :true},
          remoteSort :true,
          sm: this.gridChk,
          bbar:this.pagingBar
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

      administration.manageEntity.manage.EntityGridPanel.superclass.initComponent.apply(this, arguments);
    },
    loadData : function( config ){
		this.gridPnl.getStore().removeAll();
		if( !Ext.isEmpty( config )){
			this.gridPnl.getStore().load({params:{start:0, limit:10}, arg:[null,
			                                                               config.entityName,
																			config.entityGender,
																			config.entityType,
																			null,null,null,null,null
																			]});
		}else{
			this.gridPnl.getStore().load({params:{start:0, limit:10}, arg:[ null, null,null,null,null,null,null,null,null]});
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
		if( selectedRows.length == 1){
			this.toolBar.getEditBtn().enable();
			
		} else if( selectedRows.length > 1){
			this.toolBar.getEditBtn().disable();
		}
	},
	setGridButtonsInitialState: function(){
		this.toolBar.getEditBtn().disable();
	},
	
	reloadGrid : function(){
		this.gridPnl.getStore().reload();
	}
});

Ext.reg('entity-grid-panel', administration.manageEntity.manage.EntityGridPanel);
