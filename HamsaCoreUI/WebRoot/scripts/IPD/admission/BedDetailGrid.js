Ext.namespace("IPD.admission");

IPD.admission.BedDetailGrid = Ext.extend(Object, {

	constructor : function() {
		
		this.bedDetailRecord = Ext.data.Record.create([
			{ name: 'seqNbr', mapping:'seqNbr' },
			{ name: 'bedNumber', mapping:'bedNumber' },
			{ name: 'nursingUnitCode', mapping:'nursingUnit.code' },
			{ name: 'nursingUnit', mapping:'nursingUnit.description' },
	        { name: 'bedStatusCode',  mapping:'bedStatus.code'},
	        { name: 'bedStatusDesc', mapping:'bedStatus.description'},
	        { name: 'bedTypeCode', mapping:'bedType.code'},
	        { name: 'bedTypeDesc', mapping: 'bedType.description'},
	        { name: 'roomNbrCode', mapping: 'roomNbr.code'},
	        { name: 'roomNbr', mapping: 'roomNbr.description'},
	        { name: 'floorNbr', mapping: 'floorNbr'},
	        { name: 'siteName', mapping: 'siteName'},
	        { name: 'bedDesc',mapping:'description'},
	        { name: 'dailyCharge',mapping:'dailyCharge',convert:numberToString},
	        { name: 'hourlyCharge',mapping:'hourlyCharge',convert:numberToString},
	        { name: 'depositAmount',mapping:'depositAmount',convert:numberToString},
	        { name:'specialFeatureAvailabilityList'}
	    ]);
		
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: 'bedNumber', totalProperty:'totalSize'}, this.bedDetailRecord),
        	proxy: new Ext.data.DWRProxy(BedManager.findBeds, true),
        	sortInfo: {field: 'bedNumber', direction: "ASC"},
        	remoteSort: true
		});
		
		var gridColumns = [
			{ header : 'Bed number', dataIndex : 'bedNumber', width : 100, sortable: true }, 
			{ header : 'Floor', dataIndex : 'floorNbr', width :70, sortable: true }, 
			{ header : 'Room', dataIndex : 'roomNbr', width :70, sortable: true }, 
			{ header : 'Unit', dataIndex : 'nursingUnit', width : 70, sortable: true }, 
			{ header : 'Bed type', dataIndex : 'bedTypeDesc', width : 70, sortable: true }, 
			{ header : 'Deposit amount', dataIndex : 'depositAmount', width : 100, sortable: true }, 
			{ header : 'Daily charge', dataIndex : 'dailyCharge', width : 100, sortable: true }, 
			{ header : 'Hourly charge', dataIndex : 'hourlyCharge', width :90, sortable: true }, 
			{ header : 'Status', dataIndex : 'bedStatusDesc', width : 70, sortable: true } 
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
	
	getPanel : function() {
		return this.infoGrid;
	}
});