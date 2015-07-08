Ext.namespace("IPD.admission");

IPD.admission.DoctorOrderGrid = Ext.extend(Object, {

	constructor : function() {
		
		this.doctorOrderDetailRecord = Ext.data.Record.create([
			{ name: 'doctorOrderNbr',mapping: 'doctorOrderNbr'},
			{ name: 'admissionNbr', mapping:'admissionNbr' },
			{ name: 'patientId', mapping:'patientId',convert:numberToString },
	        { name: 'patientName',  mapping:'patientName'},
	        { name: 'orderType', mapping:'doctorOrderType',convert:getDescription},
	        { name: 'orderGroup', mapping:'doctorOrderGroup',convert:getDescription},
	        { name: 'template', mapping: 'doctorOrderTemplate',convert:getDescription},
	        { name: 'orderDate', mapping: 'orderCreationDate'},
	        { name: 'createdBy', mapping: 'createdBy'},
	        { name: 'status', mapping:'doctorOrderStatus',convert:getDescription},
	        { name: 'statusCd', mapping:'doctorOrderStatus',convert:getCode},
	        { name: 'orderTypeCd', mapping:'doctorOrderType',convert:getCode},
	        { name: 'orderGroupCd', mapping:'doctorOrderGroup',convert:getCode},
	        { name: 'templateCd', mapping: 'doctorOrderTemplate',convert:getCode},
	        { name: 'orderDictation', mapping: 'orderDictation'},
	        { name: 'doctorId', mapping: 'doctorId',convert:numberToString},
	        { name: 'doctorName', mapping: 'doctorName'},
	        { name: 'orderDesc', mapping: 'orderDesc'},
	        { name: 'doctorOrderNbr', mapping: 'doctorOrderNbr'},
	        { name: 'doctorOrderDetailsList'}
        ]);
										
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: 'doctorOrderNbr', totalProperty:'totalSize'}, this.doctorOrderDetailRecord),
        	proxy: new Ext.data.DWRProxy(OrderManager.findDoctorOrders, true),
        	sortInfo: {field: 'seqNbr', direction: "ASC"},
        	remoteSort: true
		});
		
		var gridColumns = [
			{ header : 'Order No.', dataIndex : 'doctorOrderNbr', /*width : 40,*/ sortable: true}, 
			{ header : 'Order type', dataIndex : 'orderType', /*width : 90,*/ sortable: true }, 
			{ header : 'Order group', dataIndex : 'orderGroup', /*width : 100,*/ sortable: true }, 
			{ header : 'Template', dataIndex : 'template', /*width : 100,*/ sortable: true }, 
			{ header : 'Order date', dataIndex : 'orderDate', renderer: Ext.util.Format.dateRenderer('d/m/Y'), /*width : 80,*/ sortable: true }, 
			{ header : 'Ordered by', dataIndex : 'doctorName',/* width : 110,*/ sortable: true }, 
			{ header : 'Status', dataIndex : 'status',/* width : 80,*/ sortable: true }
		];
		
		this.infoGrid = new Ext.grid.GridPanel({
				//frame:true,
				stripeRows : true,
				width : '100%',
				height : 300,
				autoScroll : true,
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