Ext.namespace("IPD.admission");

IPD.admission.ServiceDetailGrid = Ext.extend(Object,{
	
	constructor: function(){
		
		this.assignedServiceRecord = Ext.data.Record.create([
		 	{ name : "serviceCode", mapping:'service', convert: getCode },
		 	{ name : "serviceName" , mapping:'service', convert: getDescription },
		 	{ name : "packageCode", mapping:'servicePackage', convert: getCode},
		 	{ name : "packageName" ,mapping:'servicePackage', convert: getDescription},
		 	{ name : "serviceStatusCode", mapping:'assignedServiceStatus', convert: getCode },
		 	{ name : "serviceStatusDesc", mapping:'assignedServiceStatus', convert: getDescription },
		 	{ name : "requestedUnits", mapping:'requestedUnits'},
		 	{ name : "renderedUnits", mapping:'renderedUnits' },
		 	{ name : 'canceledUnits', mapping:'canceledUnits'},
		 	{ name : "billedUnits", mapping:'billedUnits' },
		 	{ name : "lastBillNbr" },
		 	{ name : "serviceOrderNumber", mapping:"serviceOrderNumber"},
		 	{ name : 'packageAsgmId', mapping: 'packageAsgmId'},
		 	{ name : 'serviceDate', mapping:'serviceDate'},
		 	{ name : 'patientId', mapping:'patientId'},
		 	{ name : 'serviceUid', mapping:'serviceUid'},
		 	{ name : 'packageAsgmId', mapping:'packageAsgmId'},
		 	{ name : 'hideSubmitted', type:'string'}
		 	
		]);	

		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.assignedServiceRecord),
        	proxy: new Ext.data.DWRProxy(ServiceManager.findAssignedServices, true),
        	sortInfo: {field: 'serviceUid', direction: "ASC"},
        	remoteSort: true
		});


		var gridColumns =[
			{ header: mngSvcAsgntMsg.serviceCode, dataIndex : "serviceCode", sortable: true, width: 75 },
		 	{ header: mngSvcAsgntMsg.serviceName, dataIndex : "serviceName", sortable: true, width: 75  },
		 	{ header: mngSvcAsgntMsg.packageName, dataIndex : "packageName", sortable: true, width: 75  },
		 	{ header: mngSvcAsgntMsg.serviceStatus, dataIndex : "serviceStatusDesc", sortable: true, width: 75  },
		 	{ header: mngSvcAsgntMsg.requestedUnits, dataIndex : "requestedUnits", sortable: true, width: 75  },
		 	{ header: mngSvcAsgntMsg.renderedUnits, dataIndex : "renderedUnits", sortable: true, width: 75  } ,
		 	{ header: mngSvcAsgntMsg.billedUnits, dataIndex : "billedUnits", sortable: true, width: 75  },
		 	{ header: mngSvcAsgntMsg.canceledUnits, dataIndex : "canceledUnits", sortable: true, width: 75  },
		 	{ header: mngSvcAsgntMsg.lastBillNbr, dataIndex : "lastBillNbr", sortable: true, width: 75  },
		 	{ header: mngSvcAsgntMsg.serviceDate, dataIndex : "serviceDate", sortable: true, renderer :Ext.util.Format.dateRenderer('d/m/Y'),width: 75  },
	 		{ header: mngSvcAsgntMsg.packageAssignementId, dataIndex : "packageAsgmId", sortable: true, width: 75 }
		];		

		this.infoGrid = new Ext.grid.GridPanel({
				stripeRows : true,
				width : '100%',
				height : 300,
				autoScroll:true,
			    viewConfig:{
			    	forceFit: true
			    },
				store : this.dataStore,
				columns : gridColumns
		});
	},

	getPanel: function(){
		return this.infoGrid;
	}
});