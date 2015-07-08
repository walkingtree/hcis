Ext.namespace("LIMS.requisitionOrder.manage");

/**
 * All the fields are read only
 */
LIMS.requisitionOrder.manage.BasicDetailWidgets = Ext.extend(Object,{
	constructor : function(){
		
		this.patientNameTxf = new Ext.form.TextField({
			name : 'patientName',
			fieldLabel : limsMsg.patientName,
			anchor : '90%',
			readOnly:true

		});

		this.genderTxf = new Ext.form.TextField({
			name : 'gender',
			fieldLabel : limsMsg.gender,
			anchor : '90%',
			readOnly:true

		});

		this.dobTxf = new Ext.form.TextField({
			name : 'dob',
			fieldLabel : limsMsg.dob,
			anchor : '90%',
			readOnly:true

		});

		this.streetTxf = new Ext.form.TextField({
			name : 'street',
			fieldLabel : limsMsg.street,
			anchor : '90%',
			readOnly:true

		});

		this.cityTxf = new Ext.form.TextField({
			name : 'city',
			fieldLabel : limsMsg.city,
			anchor : '90%',
			readOnly:true

		});

		this.countryTxf = new Ext.form.TextField({
			name : 'country',
			fieldLabel : limsMsg.country,
			anchor : '90%',
			readOnly:true

		});

		this.stateTxf = new Ext.form.TextField({
			name : 'state',
			fieldLabel : limsMsg.state,
			anchor : '90%',
			readOnly:true

		});

		this.pinCodeTxf = new Ext.form.TextField({
			name : 'pinCode',
			fieldLabel : limsMsg.pinCode,
			anchor : '90%',
			readOnly:true

		});

		this.phoneNbrTxf = new Ext.form.TextField({
			name : 'phoneNbr',
			fieldLabel : limsMsg.phoneNbr,
			anchor : '90%',
			readOnly:true

		});

		this.emailIdTxf = new Ext.form.TextField({
			name : 'emailId',
			fieldLabel : limsMsg.emailId,
			anchor : '90%',
			readOnly:true

		});

		this.contactPersonTxf = new Ext.form.TextField({
			name : 'contactPerson',
			fieldLabel : limsMsg.contactPerson,
			anchor : '90%',
			readOnly:true

		});
		this.relationshipTxf = new Ext.form.TextField({
			name : 'relationship',
			fieldLabel : limsMsg.relationship,
			anchor : '90%',
			readOnly:true

		});
	},
	
	getPatientNameTxf : function(){
		return this.patientNameTxf;
	},

	getGenderTxf : function(){
		return this.genderTxf;
	},

	getDOBTxf : function(){
		return this.dobTxf;
	},

	getStreetTxf : function(){
		return this.streetTxf;
	},

	getCityTxf : function(){
		return this.cityTxf;
	},

	getCountryTxf : function(){
		return this.countryTxf;
	},

	getStateTxf : function(){
		return this.stateTxf;
	},

	getPinCodeTxf : function(){
		return this.pinCodeTxf;
	},

	getPhoneNbrTxf : function(){
		return this.phoneNbrTxf;
	},

	getEmailIdTxf : function(){
		return this.emailIdTxf;
	},

	getContactPersonTxf : function(){
		return this.contactPersonTxf;
	},

	getRelationshipTxf : function(){
		return this.relationshipTxf;
	}
	
});