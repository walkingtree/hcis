Ext.namespace("administration.service_group_package.addServiceGroup");

administration.service_group_package.addServiceGroup.AvailableServicesGrid = Ext.extend(Object, {
	constructor : function(config) {
		
		var mainThis = this;
		
		this.svcRecord = Ext.data.Record.create([
	     	{name: 'serviceCode', mapping:'serviceCode'},
	      	{name: 'serviceName', mapping:'serviceName'},
	      	{name: 'serviceStatus', mapping:'serviceStatus', convert: getCode},
			{name: 'serviceStatusDesc', mapping:'serviceStatus', convert: getDescription},
			{name: 'serviceCharge', mapping:'serviceCharge', convert: convertToAmount}
	       ]);
        
		this.avlblSvcsDataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.svcRecord),
        	proxy: new Ext.data.DWRProxy(ServiceManager.getServiceSummaryBMforGroup, true)
		});
		
		this.gridChk = new Ext.grid.CheckboxSelectionModel();
		
		var avlblSvcsGridColumns = 
						 [  
							 this.gridChk, 
							 {header : svcAndGrpAndPkgMsg.code, dataIndex : 'serviceCode', width : 120, sortable: true},
							 {header : svcAndGrpAndPkgMsg.name, dataIndex : 'serviceName', width : 230, sortable: true} 
						  ];
						
		this.avlblSvcsGrid = new Ext.grid.GridPanel({
				title : svcAndGrpAndPkgMsg.availableServices,
				stripeRows : true,
				height : 275,
				frame:true,
				autoScroll : true,
				border : false,
				store : this.avlblSvcsDataStore,
				sm:this.gridChk,
				remoteSort:true,
				viewConfig:{
					forceFit : true
				},
				columns : avlblSvcsGridColumns
		});
		Ext.ux.event.Broadcast.subscribe('loadAvaliableServiceGrid', function(){
			mainThis.loadGridPanel();
		}, this, null, mainThis.getAvlblSvcsGridPanel());
		
		if( !Ext.isEmpty( config ) && (config.isPopup == false ||Ext.isEmpty(config.isPopup) ) ){
			this.avlblSvcsGrid.on('render', function(){
				mainThis.loadGridPanel();
			},this);
		}
		
	},
	
	getAvlblSvcsGridPanel : function() {
		return this.avlblSvcsGrid;
	},
	
	loadGridPanel: function( ){
		// initially for getting all general avaliable services
		this.avlblSvcsDataStore.load({params:{start:0, limit:999}, 
								  arg:[ configs.defaultServiceGroup ]});	
	},
	
	getSelectedRows: function(){
		return this.avlblSvcsGrid.getSelectionModel().getSelections();
	},
	addEntryToGrid: function(row){
		this.avlblSvcsGrid.getStore().add(row);
	},
	removeEntryToGrid: function(row){
		this.avlblSvcsGrid.getStore().remove(row);
	},
	
	filterAvaliableServices: function( serviceSummaryList ){
		this.avlblSvcsGrid.getStore().on('load', function(){
			var recordType = this.avlblSvcsGrid.getStore().recordType;
			var avaliableStoreData = this.avlblSvcsGrid.getStore().getRange();
				if( avaliableStoreData.length > 0 ){
					for( var i = 0; i < serviceSummaryList.length; i++ ){
						for(var j = 0; j<avaliableStoreData.length; j++){
							if(avaliableStoreData[j].data.serviceCode == serviceSummaryList[i].serviceCode){
								this.removeEntryToGrid( avaliableStoreData[j] );
							}
						}
					}
				}
			this.avlblSvcsGrid.getStore().events['load'].clearListeners();	
		},this);
		
			
	}
});