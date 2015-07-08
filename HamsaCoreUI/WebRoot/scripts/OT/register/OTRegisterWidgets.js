Ext.namespace("OT.register");

OT.register.OTRegisterWidgets =  Ext.extend(Object,{
	constructor : function(){
	
		this.patientNameTxf= new Ext.form.TextField({
			name : 'patientName',
			fieldLabel : otMsg.patientName,
			anchor : '90%'
		});
		
		this.doctorNameTxf= new wtccomponents.WTCComboBox({
			hiddenName : 'doctorName',
			fieldLabel : otMsg.doctorName,
			anchor : '90%',
			store : loadDoctorsCbx()
		});
		
		this.otCodeCbx = new wtccomponents.WTCComboBox({
			fieldLabel : otMsg.otNamelbl,
			mode : 'local',
			store : loadOperationTheatreCbx(),
			displayField : 'description',
			valueField : 'code',
			triggerAction : 'all',
			anchor:'90%',
			hiddenName : 'otCode'
		});
		
		this.surgeryCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'surgery',
			fieldLabel : otMsg.surgeryName,
			anchor : '90%',
			store : loadSurgicalTypeServiceCbx()
		});
		
		this.bookigFromTxf= new Ext.form.WTCDateField({
			name : 'bookingFrom',
			fieldLabel : otMsg.fromDate,
			anchor : '90%'
		});
	
		this.bookigToTxf= new Ext.form.WTCDateField({
			name : 'bookingTo',
			fieldLabel : otMsg.toDate,
			anchor : '90%'
		});
		
		this.referenceTypeCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'referenceType',
			fieldLabel : otMsg.referenceType,
			anchor : '90%',
			store : loadReferenceTypeCbx()
		});
		
		this.referenceNumberCbx = new Ext.form.TextField({
			name : 'referenceNbr',
			fieldLabel : otMsg.referenceNumber,
			anchor : '90%'
		});
		
		this.otBookingNoTxf= new Ext.form.TextField({
			name : 'bookingNbr',
			fieldLabel : otMsg.otBookingNo,
			anchor : '90%'
		});
		
	},
	
	getPatientNameTxf : function(){
		return this.patientNameTxf;
	},
	
	getDoctorNameTxf : function(){
		return this.doctorNameTxf;
	},
	
	getOtCodeCbx : function(){
		return this.otCodeCbx;
	},
	
	getSurgeryCbx : function(){
		return this.surgeryCbx;
	},
	
	getBookigToTxf : function(){
		return this.bookigToTxf;
	},
	
	getBookigFromTxf : function(){
		return this.bookigFromTxf;
	},
	
	getReferenceTypeCbx : function(){
		return this.referenceTypeCbx;
	},
	
	getReferenceNumberCbx : function(){
		return this.referenceNumberCbx;
	},
	
	getOtBookingNoTxf : function(){
		return this.otBookingNoTxf;
	}
	
	
});