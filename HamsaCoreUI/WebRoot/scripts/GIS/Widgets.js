Ext.namespace("GIS");

GIS.widgets = Ext.extend(Object,{
	constructor : function( config ){
		if( Ext.isEmpty(config) ){
			config = {};
		}
		
		this.stateStoreForCountry = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getStateWithCountry, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, 
		    	Ext.data.Record.create([
				  {name: "code", type: "string"},
				  {name: "description", type: "string"}
				])
			),
		    remoteSort: true
	 	 });
		
		this.treatmentTypeCbx = new Ext.form.OptComboBox({
			hiddenName :'treatmentType',
			store : loadTreatmentTypeCbx(),
			mode :'local',
			displayField :'description',
			valueField :'code',
			triggerAction :'all',
			fieldLabel :msg.TreatmentType,
			anchor :'99%',
			emptyText :'select treatment type',
			forceSelection :true
		});
		this.countryCbx = new Ext.form.OptComboBox({
			hiddenName :'country',
			store : loadCountryCbx(),
			mode :'local',
			displayField :'description',
			forceSelection :true,
			valueField :'code',
			triggerAction :'all',
			fieldLabel :msg.country,
			anchor :'99%',
			forceSelection :true,
			emptyText :'select Country',
			listeners : {
				select : function(comp, record, index) {
					if(record.data.code == null){
						this.stateCbx.disable();
					} else {
						this.stateCbx.enable();
					}
					if (this.stateStoreForCountry.getTotalCount() > 0) {
						this.stateStoreForCountry.removeAll();
					}
					this.stateStoreForCountry.load({params:{start:0, limit:9999}, arg:[record.data.code]});
					
				},
				blur : function(comp) {
					if(Ext.isEmpty(comp.getRawValue())){
						this.stateCbx.disable();
					} else {
						this.stateCbx.enable();
					}
				},
				scope : this
			}
		});
		this.stateCbx = new Ext.form.OptComboBox({
			hiddenName :'state',
			store : this.stateStoreForCountry,
			mode :'local',
			displayField :'description',
			valueField :'code',
			triggerAction :'all',
			fieldLabel :msg.state,
			anchor :'99%',
			forceSelection :true,
			emptyText :'select State'
		});
//		this.cityCbx = new Ext.form.ComboBox({
//			hiddenName :'state',
//			store : [],
//			mode :'local',
//			displayField :'description',
//			valueField :'code',
//			triggerAction :'all',
//			fieldLabel :msg.state,
//			anchor :'99%',
//			forceSelection :true
//		});
		this.treatmentReasonTxf = new Ext.form.TextField({
			fieldLabel :msg.treatmentReason,
		    name :'treatmentReason',
			anchor :'98%'
		});
		this.toDate=new Ext.form.WTCDateField({
			fieldLabel: msg.toDate,
			name: 'toDate',
			anchor:'98%'
		})
		this.fromDate=new Ext.form.WTCDateField({
			fieldLabel: msg.fromDate,
			name: 'fromDate',
			anchor:'98%'
		})
		
	},
	getTreatmentTypeCbx : function(){
		return this.treatmentTypeCbx;
	},
	getCountryCbx : function(){
		return this.countryCbx;
	},
	getStateCbx : function(){
		return this.stateCbx;
	},
//	getcityCbxCbx : function(){
//		return this.cityCbx;
//	},
	getTreatmentReasonTxf : function(){
		return this.treatmentReasonTxf;
	},
	getToDate : function(){
		return this.toDate;
	},
	getFromDate : function(){
		return this.fromDate;
	}
	
});