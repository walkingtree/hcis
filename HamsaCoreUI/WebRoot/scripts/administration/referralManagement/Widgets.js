Ext.namespace("administration.referralManagement");

administration.referralManagement.Widgets = Ext.extend(Object,{

	constructor: function(){

		this.uniqueNameTxf = new Ext.form.TextField({
				name : 'referralName',
				fieldLabel : 'Name',
				anchor:'95%',
				required:true,
				allowBlank: false
		});
		
		this.uniqueNameTxfForSearch = new Ext.form.ComboBox({
			hiddenName : 'referralNameForSearch',
			fieldLabel : 'Name',
			anchor:'95%',
			mode : 'local',
			store : getReferralNameForType(),
			displayField : 'description',
			valueField : 'description',
			triggerAction : 'all' 
			
		});
		

		this.referralTypeCombo = new Ext.form.ComboBox({
				fieldLabel : 'Referral type',
				mode : 'local',
				store : getReferralTypes(),
				displayField : 'description',
				//value : this.refTypeStore.getAt(0).get('desc'),
				valueField : 'code',
				triggerAction : 'all',
				hiddenName : 'referralType',
				anchor : '95%',
				forceSelection : true,
				required : true,
				allowBlank : false
		});
		
		this.referralTypeComboForSearch = new Ext.form.ComboBox({
				fieldLabel : 'Referral type',
				mode : 'local',
				store : getReferralTypes(),
				displayField : 'description',
				//value : this.refTypeStore.getAt(0).get('desc'),
				valueField : 'code',
				triggerAction : 'all',
				hiddenName : 'referralTypeForSearch',
				anchor : '95%',
				forceSelection : true
		});
		


		this.qualificationTxf = new Ext.form.TextField({
				name : 'qualification',
				fieldLabel : 'Qualification',
				anchor : '95%'
		});
		
		this.preferredTimeTxf = new Ext.form.TextField({
				name : 'preferredTime',
				fieldLabel : 'Preferred time',
				anchor : '95%'
		});

		this.addressTxf = new Ext.form.TextField({
				name : 'address',
				fieldLabel : 'Address',
				required:true,
				allowBlank: false,
				anchor:'95%'
		});

		this.cityTxf = new Ext.form.TextField({
				fieldLabel : 'City / District',
				name : 'city',
				required:true,
				allowBlank: false,
				anchor : '95%'
		});


		this.stateStoreForCountry = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getStateWithCountry, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, Ext.data.Record.create([
							  {name: "code", type: "string"},
							  {name: "description", type: "string"}
							])),
		    remoteSort: true
	 	 });

		this.stateCombo = new Ext.form.ComboBox({
				hiddenName : 'state',
				fieldLabel : 'State',
				mode : 'local',
				store : this.stateStoreForCountry,
				valueField : 'code',
				displayField : 'description',
				triggerAction : 'all',
				forceSelection : true,
				disabled : true,
				anchor : '95%'
		});


		this.countryCombo = new Ext.form.ComboBox({
				hiddenName : 'country',
				fieldLabel : 'Country',
				mode : 'local',
				store : loadCountryCbx(),
				valueField : 'code',
				displayField : 'description',
				triggerAction : 'all',
				forceSelection :true,
				required:true,
				allowBlank:false,
				anchor : '95%',
				listeners : {
					select : function(comp, record, index) {
						if(record.data.code == null){
							this.stateCombo.disable();
						} else {
							this.stateCombo.enable();
						}
						if (this.stateStoreForCountry.getTotalCount() > 0) {
							this.stateStoreForCountry.removeAll();
						}
						this.stateCombo.clearValue();
						this.stateStoreForCountry.load({params:{start:0, limit:9999}, arg:[record.data.code]});
						
					},
					blur : function(comp) {
						if(Ext.isEmpty(comp.getRawValue())){
							this.stateCombo.disable();
						} else {
							this.stateCombo.enable();
						}
					},
					scope : this
				}
		});	

		this.pinCodeNbrFld = new Ext.form.NumberField({
				name : 'pinCode',
				fieldLabel : 'Pin code',
				anchor:'95%'
		});

		this.phoneNbrTxf = new Ext.form.TextField({
				name : 'phoneNbr',
				fieldLabel : 'Phone number',
				anchor : '95%'
		});

		this.mobileNbrTxf = new Ext.form.TextField({
				name : 'mobileNbr',
				fieldLabel : 'Mobile number',
				anchor : '95%'
		});

		this.faxNbrTxf = new Ext.form.TextField({
				name : 'faxNbr',
				fieldLabel : 'Fax number',
				anchor : '95%'
		});

		this.emailAddressTxf = new Ext.form.TextField({
				name : 'emailAddress',
				fieldLabel : 'Email address',
				vtype:'email',
				anchor:'95%'
		});
		
		this.fromDateTimeField = new Ext.form.WTCDateTimeField({
			name :'fromDateTimeField',
			fieldLabel : 'From date',
			anchor : '95%',
			required : true,
			allowBlank : false,
			listeners:{
				change:function( comp ){
					if(!Ext.isEmpty(comp.getValue())){
						this.toDateTimeField.setMinValue( comp.getValue() );
					}
				},
				scope:this
			}
		});
		
		this.toDateTimeField = new Ext.form.WTCDateTimeField({
			name :'toDateTimeField',
			fieldLabel : 'To date',
			anchor : '95%',
			listeners:{
				change:function( comp ){
					if(!Ext.isEmpty(comp.getValue())){
						this.fromDateTimeField.setMaxValue( comp.getValue() );
					}
				},
				scope:this
			}
		})
			
	},
	
	getReferralTypeCombo : function(){
		return this.referralTypeCombo;
	},
	
	getReferralTypeComboForSearch : function(){
		return this.referralTypeComboForSearch;
	},
	
	getUniqueNameTxfForSearch : function(){
		return this.uniqueNameTxfForSearch
	},
	
	getUniqueNameTxf : function(){
		return this.uniqueNameTxf;
	},
	
	getQualificationTxf : function(){
		return this.qualificationTxf;
	},
	
	getPreferredTimeTxf : function(){
		return this.preferredTimeTxf;
	},
	
	getAddressTxf : function(){
		return this.addressTxf;
	},

	getCityTxf : function(){
		return this.cityTxf;
	},

	getCountryCombo : function(){
		return this.countryCombo;
	},

	getStateCombo : function(){
		return this.stateCombo;
	},
	
	getPinCodeNbrFld : function(){
		return this.pinCodeNbrFld;
	},

	getPhoneNbrTxf : function(){
		return this.phoneNbrTxf;
	},

	getMobileNbrTxf : function(){
		return this.mobileNbrTxf;
	},

	getFaxNbrTxf : function(){
		return this.faxNbrTxf;
	},

	getEmailAddressTxf : function(){
		return this.emailAddressTxf;
	},

	getButtonsPanel : function(){
		return this.buttonsPanel;
	},
	
	getFromDateTimeField : function(){
		return this.fromDateTimeField
	},
	
	getToDateTimeField : function(){
		return this.toDateTimeField
	},
	getStateStoreForCountry : function(){
		return this.stateStoreForCountry;
	}
	
});
