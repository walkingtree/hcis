Ext.namespace("IPD.admission");

IPD.admission.UnbilledUsageGrid = Ext.extend(Object, {
	constructor : function(config) {
		Ext.apply(this, config);
		
		this.record = Ext.data.Record.create([
			{ name: "subprocessName"},
			{ name: "subprocessAmount"}
		]);
		
		this.dataStore = new Ext.data.Store({
			data:[],
			reader: new Ext.data.ArrayReader({id:'id'}, this.record)
		});
		
		this.columns = [
			{
			header : "Subprocess name",
			dataIndex : "subprocessName",
			align:'center',
			width:350,
			sortable: true
		}, {
			header : 'Net amount (Rs.)',
			dataIndex : "subprocessAmount",
			renderer: convertToAmount,
			align:'center',
			width:350,
			sortable: true
		}];
	    
	    this.infoGrid = new Ext.grid.GridPanel({
			frame : false,
			stripeRows : true,
			width:'100%',
			height: 300,
			autoScroll : true,
			border : false,
			store : this.dataStore,
			columns : this.columns,
			viewConfig: {forceFit : true}
		});
	},
	getPanel : function(){
		return this.infoGrid;
	},
	loadGrid : function( arnNo ){
		var mainThis = this;
		
		if (this.infoGrid.getStore().getCount() > 0) {
			this.infoGrid.getStore().removeAll();
		}
		
		var record = this.infoGrid.getStore().recordType;
		
		ServiceManager.getUbilledAssignedServices(
			configs.referenceTypeForIPD,
			arnNo,
			function(billDataBM){
				if(billDataBM!= null && !Ext.isEmpty(billDataBM)){
					
					var billDetailsMap = billDataBM.billDetailsMap;
					
					if( billDetailsMap!=null && !Ext.isEmpty(billDetailsMap) ){
						addSubprocessToGrid( billDetailsMap.PACKAGE );
						addSubprocessToGrid( billDetailsMap.SERVICE );
					}
				}
			});
			
			function addSubprocessToGrid( subprocess ){
				if( subprocess != null && !Ext.isEmpty(subprocess)){
					var subprocessRecord = new record({
						subprocessName: subprocess.subProcessName,
						subprocessAmount: subprocess.subProcessTotals
					});
					mainThis.infoGrid.getStore().add(subprocessRecord);
				}
			}
		this.infoGrid.doLayout();
	}
})