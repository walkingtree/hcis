Ext.namespace("administration.service_group_package.addServiceGroup");

administration.service_group_package.addServiceGroup.SelectedServicesGrid = Ext.extend(Object, {
	constructor : function(config) {
		
		var mainThis = this;
		
		this.svcRecord = Ext.data.Record.create([
	     	{name: 'serviceCode', mapping:'serviceCode'},
	      	{name: 'serviceName', mapping:'serviceName'},
	      	{name: 'serviceStatus', mapping:'serviceStatus.code'},
			{name: 'serviceStatusDesc', mapping:'serviceStatus.description'},
			{name: 'serviceCharge'}
	       ]);
        
		this.slctdSvcsDataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.svcRecord)
		});

		this.gridChk = new Ext.grid.CheckboxSelectionModel();
		
		var slctdSvcsGridColumns = 
						 [  
							 this.gridChk, 
							 {header : svcAndGrpAndPkgMsg.code, dataIndex : 'serviceCode', width : 120, sortable: true},
							 {header : svcAndGrpAndPkgMsg.name, dataIndex : 'serviceName', width : 250, sortable: true},
							 {header : svcAndGrpAndPkgMsg.charge, dataIndex : 'serviceCharge', width : 120, align:'right', sortable: true},
							 {header : svcAndGrpAndPkgMsg.status, dataIndex : 'serviceStatusDesc', width : 120, sortable: true} 
						  ];
						
		this.slctdSvcsGrid = new Ext.grid.GridPanel({
				title : svcAndGrpAndPkgMsg.selectedServices,		
				stripeRows : true,
				height : 275,
				autoScroll : true,
				border : false,
				frame:true,				
				store : this.slctdSvcsDataStore,
				sm:this.gridChk,
				remoteSort:true,
				viewConfig:{
					forceFit : true
				},
				columns : slctdSvcsGridColumns
			});
	},
	
	getSlctdSvcsGridPanel : function() {
		return this.slctdSvcsGrid;
	},
	getSelectedRows: function(){
		return this.slctdSvcsGrid.getSelectionModel().getSelections();
	}, 
	addEntryToGrid: function(row){
		this.slctdSvcsGrid.getStore().add(row);
	},
	removeEntryToGrid: function(row){
		this.slctdSvcsGrid.getStore().remove(row);
	},
	getData: function(){
		var storeData = this.slctdSvcsGrid.getStore().getRange();
		if( storeData.length > 0 ){
			var serviceSummaryBMList= new Array();
			for(var i =0; i < storeData.length; i++){
				var data = storeData[i].data;
				var serviceSummaryBM ={
					serviceCode: data.serviceCode,
					serviceName: data.serviceName,
					serviceStatus:{code: data.serviceStatus,description: data.serviceStatusDesc },
					serviceCharge:data.serviceCharge
				}
				serviceSummaryBMList.push(serviceSummaryBM);
			}
			return serviceSummaryBMList;
		}else{
			return null; 
		}
	}
});