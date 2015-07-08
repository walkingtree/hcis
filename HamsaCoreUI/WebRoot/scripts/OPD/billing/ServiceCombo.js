Ext.namespace("OPD.billing");
OPD.billing.ServiceCombo = new Ext.extend(Object, {
	constructor : function(config){
		
		if(Ext.isEmpty(config)){
			config ={};
		}
		this.serviceTypeStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(ServiceManager.getServiceSummaryBMforGroup, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, Ext.data.Record.create([
											  {name: "code", mapping:'serviceCode',type: "string"},
											  {name: "description",mapping:'serviceName', type: "string"},
											  {name: "serviceCharge",mapping:'serviceCharge'}]
				)),
		    remoteSort: true
	  	});
	  	
		this.serviceCbx = new Ext.form.ComboBox({
			fieldLabel : opBillingMsg.serviceName,
			hiddenName : 'serviceName',
			store : this.serviceTypeStore ,
			mode :'local',
			displayField : 'description',
			valueField : 'code',
			triggerAction : 'all',
			anchor:config.anchor,
			forceSelection : true
		});
	},
	getCombo : function(){
		return this.serviceCbx;
	}
});