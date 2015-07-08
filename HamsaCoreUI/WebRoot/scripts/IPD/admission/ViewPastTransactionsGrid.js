Ext.namespace("IPD.admission");

IPD.admission.ViewPastTransactionsGrid = Ext.extend(Object, {

	constructor : function(config) {

		if(Ext.isEmpty(config)){
			config = {};
		}

		this.record = Ext.data.Record.create([
			{ name: 'transactionId', mapping:'transactionId'},
			{ name: 'transactionDate', mapping:'transactionDate'},
			{ name: 'transactionType', mapping:'transactionType'},
			{ name: 'transactionAmt', mapping:'amount'},
			{ name: 'remainingAmt', mapping:'remngAmount'}
//			{ name: 'transactonRemarks', mapping:'transactonRemarks'}
		]);
		
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: ' ', totalProperty:'totalSize'}, this.record),
        	proxy: new Ext.data.DWRProxy(AdmissionOrder.getPaymentReceipt, true),
        	sortInfo: {field: 'transactionDate', direction: "ASC"},
        	remoteSort: true
		});
		
		var gridColumns = [
			{ header : 'Transaction id', dataIndex : 'transactionId',width : 100},
			{ header : 'Transaction date', dataIndex : 'transactionDate', width : 120, sortable: true, renderer : Ext.util.Format.dateRenderer('d/m/Y') },
			{ header : 'Transaction type', dataIndex : 'transactionType', width : 135, sortable: true},
			{ header : 'Transaction amount', dataIndex : 'transactionAmt', width : 150, renderer : convertToAmount, align : 'right' },
			{ header : 'Remainging amount', dataIndex : 'remainingAmt', width : 150, renderer : convertToAmount, align : 'right' }
//			{ header : 'Transaction remarks', dataIndex : 'transactonRemarks' ,width : 560}
		];

		this.infoGrid = new Ext.grid.GridPanel({
			stripeRows : true,
			width : '100%',
			height : 300,
			autoScroll:true,
			viewConfig :{
				forceFit : true
			},
			store : this.dataStore,
			columns : gridColumns
		});
		
	},
	
	loadGrid : function( arnNo , billingId ){
		if (this.dataStore.getTotalCount() > 0) {
			this.dataStore.removeAll();
		}
		this.dataStore.load({
			params : {start : 0,limit : 9999},
			arg : [ arnNo, billingId ]
		});	
	},
	
	getPanel : function() {
		return this.infoGrid;
	}
});