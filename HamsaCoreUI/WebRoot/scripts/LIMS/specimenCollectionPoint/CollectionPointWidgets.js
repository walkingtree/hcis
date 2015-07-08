Ext.namespace("LIMS.specimenCollectionPoint");

LIMS.specimenCollectionPoint.CollectionPointWidgets =  Ext.extend(Object,{
	constructor : function(){
	
		this.collectionPointIdTxf = new Ext.form.TextField({
			name : 'collectionPointId',
			fieldLabel : limsMsg.collectionPointId,
			anchor : '80%',
			allowBlank : false,
			required : true
		});	
		
		this.collectionPointNameTxf = new Ext.form.TextField({
			name : 'collectionPointName',
			fieldLabel : limsMsg.collectionPointName,
			anchor : '80%',
			allowBlank : false,
			required : true
		});	

		this.areaCoveredTxf = new Ext.form.TextField({
			name : 'areaCovered',
			fieldLabel : limsMsg.areaCovered,
			anchor : '40%'
		});	
	
		this.contactPersonNameTxf = new Ext.form.TextField({
			name : 'contactPerson',
			fieldLabel : limsMsg.contactPerson,
			anchor : '80%'
		});	
		
		this.localityTxf = new Ext.form.TextField({
			name : 'locality',
			fieldLabel : limsMsg.locality,
			anchor : '80%'
		});
		
		this.countryCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'country',
			fieldLabel : limsMsg.country,
			anchor : '80%',
			store : loadCountryCbx() 
		});
		
		this.stateCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'state',
			fieldLabel : limsMsg.state,
			anchor : '80%',
			store : loadStateForSelectedCountryCbx() 
		});
	
		this.streetTxf = new Ext.form.TextField({
			name : 'street',
			fieldLabel : limsMsg.street,
			anchor : '80%'
		});
	
		this.cityTxf = new Ext.form.TextField({
			name : 'city',
			fieldLabel : limsMsg.city,
			anchor : '80%'
		});
	

		this.phoneNumberTxf = new Ext.form.NumberField({
			name : 'phoneNumber',
			fieldLabel : limsMsg.phoneNbr,
			anchor : '80%',
			allowBlank : false,
			required : true
			
		});
	
		this.mobileNumberField = new Ext.form.NumberField({
			name : 'mobileNumber',
			fieldLabel : limsMsg.mobileNumber,
			anchor : '80%'
		});
	
		this.emailIdTxf = new Ext.form.TextField({
			name : 'emailId',
			fieldLabel : limsMsg.emailId,
			anchor : '80%',
			vtype : 'email'
		});
		
		this.faxNumberField = new Ext.form.NumberField({
			name : 'faxNumber',
			fieldLabel : limsMsg.faxNumber,
			anchor : '80%'
		});
		
	
	},
	
	getCollectionPointIdTxf : function(){
		return this.collectionPointIdTxf;
	},
	
	getCollectionPointNameTxf : function(){
		return this.collectionPointNameTxf;
	},
	
	getAreaCoveredTxf : function(){
		return this.areaCoveredTxf;
	},
	
	getContactPersonNameTxf : function(){
		return this.contactPersonNameTxf;
	},
	
	getLocalityTxf : function(){
		return this.localityTxf;
	},
	
	getCountryCbx : function(){
		return this.countryCbx;
	},
	
	getStateCbx : function(){
		return this.stateCbx;
	},
	
	getStreetTxf : function(){
		return this.streetTxf;
	},
	
	getCityTxf : function(){
		return this.cityTxf;
	},
	
	getPhoneNumberTxf : function(){
		return this.phoneNumberTxf;
	},
	
	getMobileNumberField : function(){
		return this.mobileNumberField;
	},
	
	getEmailIdTxf : function(){
		return this.emailIdTxf;
	},
	
	getFaxNumberField : function(){
		return this.faxNumberField;
}

});