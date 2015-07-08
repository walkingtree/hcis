Ext.namespace("administration.service_group_package.PriceDetailsPanel");

administration.service_group_package.priceUpdate.PriceDetailsPanel = Ext.extend(Ext.form.FormPanel, {
    layout : 'fit',
    border : false,
    frame : false,
	width:'100%',
	height:350,
	monitorValid : true,
	initComponent : function() {

	//If passed config contains 'serviceCode' then store it for further use
	this.serviceCode = "";
	this.defaultServicePrice = 0;
	
		if( !Ext.isEmpty(this.initialConfig)){
			
			this.serviceCode = this.initialConfig.serviceCode;
		}
	    
		this.newEntryCount = 0;//Count of total newly added record entry
		
		this.record = Ext.data.Record.create( [ 
	        { name : 'serviceCode'},
	        { name : 'serviceCharge'},
	        { name : 'effectiveFromDt'},
	        { name : 'effectiveToDt'},
	        { name : 'createdDt' },
	        { name : 'isNewlyAdded' },//marker flag to check whether this item is added manually, for UI only  
	        { name :  'processed' },
	        { name : 'hideRemove',type: 'boolean',	mapping:'processed', 
		    		convert: function(val, rec){
		    			if ( val == 'Y' ){
		    					return true;
		    				}else{
		    					return false;
		    				}
		    		}
			},
			{ name : 'hideApplyNow',type: 'boolean',mapping:'processed',  
	    		convert: function(val, rec){
    			if ( rec.processed == 'Y' || rec.effectiveFromDt > new Date() ){
    					return true;
    				}else{
    					return false;
    				}
    		}
	
				
			}
    	]);

	 	this.gridChkSelcModl = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
	 		
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					
				},
				scope:this
			}
		});
		
	 	this.action = new Ext.ux.grid.RowActions( {
			header : 'Actions',
			keepSelection : true,
			widthSlope: 50,
			actions : [
			{
				iconCls : 'cross_icon',
				tooltip : 'Delete this entry',
				align	: 'center',
				hideIndex:'hideRemove'
			},
			{
				iconCls : 'accept-icon',
				tooltip : 'Apply now',
				align	: 'center',
				hideIndex:'hideApplyNow'
			}
			]
	 	});
	 	
        this.gridColumns = [
            
			{
				header : 'Effective From*',
				dataIndex : 'effectiveFromDt',
				width : 50,
				editor : new Ext.form.WTCDateField({
							required:true,
							allowBlank:false,
							minValue : new Date()
						})
			},{
				header : 'Service Charge*' ,
				dataIndex : 'serviceCharge',
				width : 50,
				editor : new Ext.form.NumberField({
							required:true,
							allowBlank:false,
							minValue:0
							})
			}, {
				header : 'Effective To',
				dataIndex : 'effectiveToDt',
				width : 50
			},{
				header : 'Creation Date',
				dataIndex : 'createdDt',
				width : 50
			},this.action ];
       
       this.toolBar = new Ext.Toolbar();// new utils.GridToolBar();
       
       
    	    
       this.addBtn = new Ext.Button({
				        text: 'Add',
				        scope : this,
				        iconCls:'add_btn'
        });

       this.buttonsPanel = new utils.ButtonsPanel(); 
       
       this.toolBar.add( this.addBtn );
       this.toolBar.add('-');
       this.toolBar.add( this.buttonsPanel );
       this.toolBar.add('-');
       
       
//	   this.dataStore = new Ext.data.Store( {
//			data : [],
//			reader : new Ext.data.ArrayReader( {
////				id : 'labTestAttribuId'
//			}, this.record)
//		});
		

	   this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.record),
        	proxy: new Ext.data.DWRProxy(ServiceManager.getServicePriceDetail, true)
		});
	   
			
		
       this.gridPnl = new Ext.grid.EditorGridPanel({
		  frame : false,
		  border : false,
          tbar : this.toolBar,
		  height : 310,
          stripeRows :true,
          autoScroll :true,
          store : this.dataStore,
          columns : this.gridColumns,
          clicksToEdit : 1,
          viewConfig : {forceFit :true},
          remoteSort :true,
          sm: this.gridChkSelcModl,
          plugins:[ this.action ]
       });

//Handler Starts--------------------------------
       
       this.gridPnl.on('cellclick', function( thisGrid, rowIndex, colIndex, e){
      		this.setGridButtonsState( thisGrid.getSelectionModel() );
       },this);
      
//      this.gridPnl.on('render',function(){
//		this.loadData();      
//	  },this);

       this.action.on( 'action', function(grid, record, action, row, col) {
	
				if (action === 'cross_icon') {
	
					//If this particular row is added newly  
					//them just remove it from the grid, otherwise
					//call back end method
					
					
					if( !record.data.isNewlyAdded ){
						
						ServiceManager.removeServicePirceDetail(
								record.data.serviceCode,
								record.data.effectiveFromDt, {
									callback : function() {
									this.resetGrid();
								},
								scope : this
								});
					}else{
						
						//Decrease the new entry count
						if(this.newEntryCount > 0){
							
							this.newEntryCount = this.newEntryCount -1;
						} 
					}
					grid.store.remove(record);
				}else if( action === 'accept-icon'){
					

					
					ServiceManager.processServicePriceDetail(
							record.data.serviceCode,
							record.data.effectiveFromDt,
							{
								callback : function() {
								Ext.ux.event.Broadcast.publish('reloadSearchServicesGrid');
								if(!Ext.isEmpty( this.initialConfig.chargeNbrField )){
									this.initialConfig.chargeNbrField.enable();
									this.initialConfig.chargeNbrField.setValue( record.data.serviceCharge );
									this.initialConfig.chargeNbrField.disable();
								}
								this.ownerCt.close();
							},
							scope : this
							});
					
				}
			},this);
      
       this.addBtn.on('click', function(){
    	   
    	   	var record = this.dataStore.recordType;
    	   	
    	   	var details = new record({
				serviceCode    	: this.serviceCode,
				serviceCharge	: this.defaultServicePrice,
				effectiveFromDt : new Date(),
				createdDt		: new Date(),
				effectiveToDt	: null,
		        hideRemove		: false,
		        hideApplyNow	: true,
		        isNewlyAdded	: true,
		        processed		: 'N'

			});
			this.dataStore.add( details );
			
			//Increase the new entry count
			
			this.newEntryCount ++;
			
			//TODO: set focus to the first editable field 
			//this.gridPnl.colModel.getCellEditor(0,this.dataStore.data.items.length).focus(true);
			
		},this);
      
       
       this.buttonsPanel.getResetBtn().on("click",function(){
    	   
    	   this.resetGrid();
    	   this.newEntryCount = 0;
       },this);
       
       
       this.on("clientvalidation", function(thisForm, isValid) {
			if (isValid && this.newEntryCount > 0){
				this.buttonsPanel.getSaveBtn().enable();
			} else {
				this.buttonsPanel.getSaveBtn().disable();
			}
		}, this);
       
       
       this.buttonsPanel.getSaveBtn().on('click',function(){
    	   
    	   var modifiedRecords = this.gridPnl.getStore().data.items;
    	   
    	   this.saveButtonClicked(modifiedRecords);
        
       },this);
       
       
       
//Handler ends----------------------------------------------------      
       
       Ext.applyIf(this, {
	 	  items: [{
	                  columnWidth : 1,
	                  items :[this.gridPnl]
	             }
				]            
       });

       administration.service_group_package.priceUpdate.PriceDetailsPanel.superclass.initComponent.apply(this, arguments);
    },

    
    loadGridData: function( serviceCode, currentPrice ){
		if( !Ext.isEmpty( serviceCode )){
			
			this.defaultServicePrice = currentPrice || this.defaultServicePrice;
			
			this.gridPnl.getStore().load({params:{start:0, limit:10}, arg:[ serviceCode ]});
		}
		
     },
     
    resetGrid : function(){
    	 
    	 this.gridPnl.getStore().reload();
    },
    
    /**
     * save only those records which are newly added
     */
    saveButtonClicked : function( record ){
    	
		if( !Ext.isEmpty( record ) &&  record.length > 0 ){
			
			var ServicePriceDetailBMList = new Array();
			
			for( var i = 0; i<record.length; i++ ){
				
				if( record[i].data.isNewlyAdded ){
					
					var servicePriceDetailBM = {
							
							serviceCode   : record[i].data.serviceCode,
							serviceCharge :record[i].data.serviceCharge,
							effectiveFromDt :record[i].data.effectiveFromDt,
							createdBy 		:getAuthorizedUserInfo().userName
					};
					
					
					ServicePriceDetailBMList.push(servicePriceDetailBM );
				}
			}
			ServiceManager.createServicePriceDetailEntry( ServicePriceDetailBMList,{
															callback:function(){
																this.ownerCt.destroy();
															},
															callbackScope:this
														});
		}
	
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
	},
	setGridButtonsInitialState: function(){
	}
	
	
});
