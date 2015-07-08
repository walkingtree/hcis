Ext.namespace('LIMS.labConfiguration');

LIMS.labConfiguration.Widgets = Ext.extend(Object,{
	constructor : function(){

		this.hospitalNameCbx = new Ext.form.ComboBox({
			hiddenName :'hospitalName',
			store : loadHospitalNameStoreCbx(),
			mode :'local',
			displayField :'description',
			valueField :'code',
			triggerAction :'all',
			fieldLabel :limsMsg.hospitalName,
			anchor :'94%',
			forceSelection :true
		});
		
		this.labTypeCbx = new Ext.form.ComboBox({
			hiddenName :'labType',
			store : loadLabTypeStoreCbx(),
			mode :'local',
			displayField :'description',
			valueField :'code',
			triggerAction :'all',
			fieldLabel :limsMsg.laboratoryType,
			required:true,
			allowBlank: false,
			anchor :'94%',
			emptyText :'select laboratory type',
			forceSelection :true
		});
		
		this.labIdTxf = new Ext.form.TextField({
			name: 'labId',
			fieldLabel : limsMsg.labId,
			allowBlank: false,
			required:true,
			anchor:'94%'
		});
		
		this.labNameTxf = new Ext.form.TextField({
			name: 'labName',
			fieldLabel : limsMsg.laboratoryName,
			allowBlank: false,
			required:true,
			anchor:'94%'
		});
		
		this.businessNameTxf = new Ext.form.TextField({
			name: 'businessName',
			fieldLabel : limsMsg.businessName,
			anchor:'94%'
		});
		
		this.branchNameTxf = new Ext.form.TextField({
			name: 'branchName',
			fieldLabel : limsMsg.branchName,
			anchor:'94%'
		});
		
		this.directionFromKnownPlaceTxf = new Ext.form.TextField({
			name: 'directionFromKnownPlace',
			fieldLabel : limsMsg.directionFromKnownPlace,
			anchor:'94%'
		});
		
		this.labOperatorIDTxf = new Ext.form.TextField({
			name: 'labOperatorID',
			fieldLabel : limsMsg.labOperatorID,
			anchor:'94%'
		});
		
		this.isLabInsideHospitalChk = new Ext.form.Checkbox({
			boxLabel: limsMsg.isLabinsidehospital,
			labelSeperator :'',
			name: 'islabinsidehospital',
			checked:false
		});
		
		this.streetTxf = new Ext.form.TextField({
			name: 'street',
			fieldLabel : limsMsg.street,
			anchor:'94%'
		});
		
		this.localityTxf = new Ext.form.TextField({
			name: 'locality',
			fieldLabel : limsMsg.locality,
			anchor:'94%'
		});
		
		this.cityTxf = new Ext.form.TextField({
			name: 'city',
			fieldLabel : limsMsg.city,
			anchor:'94%'
		});
		
		this.emailIDTxf = new Ext.form.TextField({
			name: 'emailID',
			fieldLabel : limsMsg.emailID,
			anchor:'94%'
		});
		
		this.phoneNumberTxf = new Ext.form.TextField({
			name: 'phoneNumber',
			fieldLabel : limsMsg.phoneNumber,
			anchor:'94%'
		});
		
		this.mobileNumberTxf = new Ext.form.TextField({
			name: 'mobileNumber',
			fieldLabel : limsMsg.mobileNumber,
			anchor:'94%'
		});
		
		this.faxNumberTxf = new Ext.form.TextField({
			name: 'faxNumber',
			fieldLabel : limsMsg.faxNumber,
			anchor:'94%'
		});
		
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
		
		this.countryCbx = new Ext.form.ComboBox({
			hiddenName :'country',
			store : loadCountryCbx(),
			mode :'local',
			displayField :'description',
//			forceSelection :true,
			valueField :'code',
			triggerAction :'all',
			fieldLabel : limsMsg.country,
			anchor :'94%',
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
					this.stateCbx.clearValue();
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
		
		this.stateCbx = new Ext.form.ComboBox({
			hiddenName :'state',
			store : this.stateStoreForCountry,
			mode :'local',
			displayField :'description',
			valueField :'code',
			triggerAction :'all',
			fieldLabel :limsMsg.state,
			anchor :'94%',
//			forceSelection :true,
			emptyText :'select State'
		});
//		
//		this.addBtn = new Ext.Toolbar.Button({
//			iconCls : 'add_btn',
//			text : limsMsg.btnAdd,
//			scope: this
//		});
//		
		this.saveBtn = new Ext.Button({
			text: "Save",
			scope:this
		});

		this.resetBtn = new Ext.Button({
			text : "Reset",
			scope: this
		});
		
	},
	getHospitalNameCbx: function(){
		
		return this.hospitalNameCbx;
	},

	getLabTypeCbx: function(required){
		

		if(required=== false){
			this.labTypeCbx.allowBlank=true,
			this.labTypeCbx.required=false;
		}
		return this.labTypeCbx;
	},

	getLabIdTxf: function(required){

		if(required === false){
			this.labIdTxf.allowBlank=true,
			this.labIdTxf.required=false;
		}
		return this.labIdTxf;
	},
		
	getLabNameTxf: function(required){

		if( required === false){
			this.labNameTxf.allowBlank=true,
			this.labNameTxf.required=false;
		}
		return this.labNameTxf;
	},

	getBusinessNameTxf: function(){
		return this.businessNameTxf;
	},

	getBranchNameTxf : function(){
		return this.branchNameTxf;
	},
	
	getDirectionFromKnownPlaceTxf : function(){
		return this.directionFromKnownPlaceTxf;
	},
	
	getLabOperatorIDTxf : function(){
		return this.labOperatorIDTxf;
	},
	
	getIsLabInsideHospitalChk : function(){
		return this.isLabInsideHospitalChk;
	},
	
	getStreetTxf : function(){
		return this.streetTxf;
	},
	
	getLocalityTxf : function(){
		return this.localityTxf;
	},
	
	getCityTxf : function(){
		return this.cityTxf;
	},
	
	getEmailIDTxf : function(){
		return this.emailIDTxf;
	},
	
	getPhoneNumberTxf : function(){
		return this.phoneNumberTxf;
	},
	
	getMobileNumberTxf : function(){
		return this.mobileNumberTxf;
	},
	
	getFaxNumberTxf : function(){
		return this.faxNumberTxf;
	},
	
	getCountryCbx : function(){
		return this.countryCbx;
	},
	
	getStateCbx : function(){
		return this.stateCbx;
	}
	
//	getAddBtn:function(){
//		return this.addBtn;
//	}
	
});
		