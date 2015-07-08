Ext.namespace("OPD.billing");

OPD.billing.AssignedServiceCardPanel = Ext.extend(Object,{
	constructor : function(){
		
		this.serviceGroupCbx = new wtccomponents.WTCComboBox({
	 		fieldLabel : opBillingMsg.serviceGroup,
	 		hiddenName : 'serviceGroup',
	 		store : loadAddServiceGroupCbx()
	 	});
	 	
	 	this.serviceGroupCbx.on('select', function( comp, record, index ){
	 		this.serviceForGroupCbx.getCombo().clearValue();
	 		this.serviceForGroupCbx.serviceTypeStore.load({params:{start:0, limit:9999}, arg:[record.data.code]});
	 
	 	},this);
	 	
	 	this.servicePackageCbx = new wtccomponents.WTCComboBox({
	 		fieldLabel : opBillingMsg.servicePackage,
	 		hiddenName : 'servicePackage',
	 		store : loadServicePackageCbx()
	 	});
	 	
	 	this.individualServiceCbx = new OPD.billing.ServiceCombo({anchor:'98%'});
	 	this.serviceForGroupCbx = new OPD.billing.ServiceCombo({anchor:'98%'});
	 	
	 	this.individualServiceCbx.serviceTypeStore.load({params:{start:0, limit:9999}, arg:[""]});
	 	var mainThis = this;

	 	this.labTestServiceCbx = new wtccomponents.WTCComboBox({
	 		hiddenName : 'testName',
	 		fieldLabel : 'Test name',
	 		anchor : '80%',
	 		store : loadLabTestServiceCbx()
	 	})
	 	
	 	this.serviceTypeCardPanel = new Ext.Panel({
			width:'100%',
			activeItem:0,
			deferredRender:false,
			renderHidden:true,
			layout:'card',
	 		items:[
	 			{
	 				layout:'form',
	 				labelWidth:85,
	 				items:this.individualServiceCbx.getCombo()
	 			},
	 			{
	 				layout : 'column',
	 				defaults : {columnWidth:.5},
	 				items : [
	 					{
	 						layout : 'form',
	 						labelWidth:85,
	 						items : this.serviceGroupCbx
	 					},
	 					{
	 						layout : 'form',
	 						labelWidth:85,
	 						items : this.serviceForGroupCbx.getCombo()
	 					}
	 				]
	 			},
	 			{
	 				layout:'form',
	 				labelWidth:102,
	 				items:this.servicePackageCbx
	 			},
	 			{
	 				layout:'form',
	 				labelWidth:85,
	 				items:this.labTestServiceCbx
	 			}
	 		]
	 	});
	},
	getPanel : function() {
		return this.serviceTypeCardPanel;
	},
	getServiceRecForServiceCode : function(code){
		var allRecords = this.individualServiceCbx.serviceTypeStore.getRange();
		for(var i=0;i<allRecords.length;i++){
			if(allRecords[i].data.code == code){
				return allRecords[i].data; 
			}
		}
	},
	getServiceRecForTestServiceCode : function( code ){
		var allRecords = this.labTestServiceCbx.store.getRange();
		for(var i=0;i<allRecords.length;i++){
			if(allRecords[i].data.code == code){
				return allRecords[i].data; 
			}
		}
	},
	
	getServicePackageRecForServicePackageCode : function(code){
		var packageStore =loadServicePackageCbx();
		var allRecords = packageStore.getRange();
		for(var i=0;i<allRecords.length;i++){
			if(allRecords[i].data.code == code){
				return allRecords[i].data; 
			}
		}
	},
	loadData: function(data){
		if(data.packageInd==configs.packageIndicator){
			this.servicePackageCbx.setValue(data.servicePackageCode);
		}else{
			this.individualServiceCbx.getCombo().setValue(data.serviceCode);
		}
	},
	resetData: function(){
		this.serviceGroupCbx.setValue('');
		this.servicePackageCbx.setValue('');
		this.individualServiceCbx.getCombo().setValue('');
		this.serviceForGroupCbx.getCombo().setValue('');
		this.labTestServiceCbx.setValue('');
	}
});
