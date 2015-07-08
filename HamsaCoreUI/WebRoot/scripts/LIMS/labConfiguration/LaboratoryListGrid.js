Ext.namespace('LIMS.labConfiguration');

LIMS.labConfiguration.LaboratoryListGrid = Ext.extend(Ext.Panel, {
    title : 'Laboratory List',
    layout : 'fit',
    border : false,
    frame : false,
    height:350,
	width:'98%',
	initComponent : function() {
        this.record = Ext.data.Record.create( [ 
	        { name : 'labId', mapping : 'labId'},
	        {name : 'hospitalName',mapping: 'hospital' ,convert:getDescription} ,
	        {name : 'hospitalCode',mapping: 'hospital' ,convert:getCode} ,
	        { name : 'labType', mapping : 'labType',convert:getDescription},
	        { name : 'labName', mapping : 'labName'},
	        { name : 'businessName', mapping : 'businessName'},
	        { name : 'branchName', mapping : 'branchName'},
	        { name : 'labOperatorID', mapping : 'labOperatorID'},
	        { name : 'directionFromKnownPlace', mapping : 'directionFromKnownPlace'},
	        { name : 'street', mapping : 'contactDetail.street'},
	        {name : 'locality',mapping: 'contactDetail.locality' } ,
	        { name : 'city', mapping : 'contactDetail.city'},
	        { name : 'emailID', mapping : 'contactDetail.email'},
	        { name : 'phoneNumber', mapping : 'contactDetail.phoneNumber'},
	        { name : 'mobileNumber', mapping : 'contactDetail.mobileNumber'},
	        { name : 'faxNumber', mapping : 'contactDetail.faxNumber'},
	        { name : 'countryCode',convert:function(a,rec){ 
	        			
	        			if(!Ext.isEmpty(rec.contactDetail.country)){
	        				return rec.contactDetail.country.code;
	        			}
	        		}},
	        { name : 'country', mapping : 'country', convert:getDescription},
	        { name : 'state', convert:function(a,rec){ 
	    			if(!Ext.isEmpty(rec.contactDetail.state)){
	    				return rec.contactDetail.state.description;
	    			}
    		}},
	        { name : 'stateCode',convert:function(a,rec){ 
    			
    			if(!Ext.isEmpty(rec.contactDetail.state)){
    				return rec.contactDetail.state.code;
    			}
    		}},
	        
	        
    	]);

	 	this.gridChk = new Ext.grid.CheckboxSelectionModel({
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
		
        this.gridColumns = [
			this.gridChk ,
	           { header :limsMsg.labId,
				 	dataIndex :'labId', width : 150 }, 
				{ header :limsMsg.hospitalName,
					 dataIndex :'hospitalName', width : 150 }, 
	           { header :limsMsg.laboratoryType,
					 dataIndex :'labType', width : 200 }, 
	           { header :limsMsg.laboratoryName,
						dataIndex :'labName', width : 200 }, 
	           { header :limsMsg.businessName,
	        	   dataIndex :'businessName', width : 200 },
	           { header :limsMsg.branchName,
	        		   dataIndex :'branchName', width : 200 },
	           { header :limsMsg.labOperatorID, 
	        				  dataIndex :'labOperatorID', width : 200 },
	         { header :limsMsg.directionFromKnownPlace,
	    	        		  dataIndex :'directionFromKnownPlace', width : 200 }
	           
	    ];
       
//	   this.viewDetailsBtn = new Ext.Button({
//	   		text: limsMsg.viewDetails,
//	   		iconCls:'view-icon',
//	   		disabled: true
//	   });
        
       this.toolBar = new utils.GridToolBar();
//	   this.toolBar.add( this.viewDetailsBtn );
//	   this.toolBar.add( new Ext.Toolbar.Separator());
       
	   this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.record),
        	proxy: new Ext.data.DWRProxy(LabDetailManager.getLabDetail, true)
		});
		
		this.pagingBar = new Ext.WTCPagingToolbar({
			store : this.dataStore,
			displayMsg : limsMsg.displayingLabDetailMsg,
			emptyMsg : limsMsg.noLabDetailMsg
		});
		
       this.gridPnl = new Ext.grid.GridPanel({
		  frame : true,
		  border : false,
          tbar : this.toolBar,
		  height : 300,
		  width:'100%',
          stripeRows :true,
          autoScroll :true,
          store : this.dataStore,
          columns : this.gridColumns,
          viewConfig : {forceFit :true},
          remoteSort :true,
          sm: this.gridChk,
          bbar:this.pagingBar,
          sortInfo: {field: 'LabId', direction: 'ASC'}
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
	                  height: 200,
	                  items :[this.gridPnl]
	             }
			]            
      });

      LIMS.labConfiguration.LaboratoryListGrid.superclass.initComponent.apply(this, arguments);
    },
    loadData : function( config ){
		this.gridPnl.getStore().removeAll();
		if( !Ext.isEmpty( config )){
			this.gridPnl.getStore().load({params:{start:0, limit:10}, arg:[ 
			                                                                config.labId,
			                                                                config.hospitalName,
																			config.labType,
																			config.labName,
																			config.businessName,
																			config.branchName,
																			config.labOperatorID,
																			config.directionFromKnownPlace
//																			config.locality,
//																			config.city,
//																			config.state,
//																			config.country,
//																			config.phoneNumber,
//																			config.mobileNumber,
//																			config.emailID,
//																			config.faxNumber
																			]});
		}else{
			this.gridPnl.getStore().load({params:{start:0, limit:10}, arg:[ null, null,null,null,null,null,null,null]});
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
//    getViewDetailsBtn: function(){
//    	return this.viewDetailsBtn;
//    }
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

//Ext.reg('laboratorys-list-grid-panel', LIMS.LabConfiguration.LaboratoryListGrid);
