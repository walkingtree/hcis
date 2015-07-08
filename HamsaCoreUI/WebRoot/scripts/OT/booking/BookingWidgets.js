Ext.namespace("OT.booking");

OT.booking.BookingWidgets =  Ext.extend(Object,{
	constructor : function(){
		
		this.patientIdTxf= new Ext.form.NumberField({
			name : 'patientId',
			fieldLabel : bookingMsg.patientId,
			anchor : '98%',
			required : true,
			allowBlank : false
		});
	
		this.patientNameTxt = new Ext.form.TextField({
			name : 'name',
			fieldLabel : bookingMsg.patientName,
			anchor : '90%'
		});
		
		this.referenceNbrTxf = new Ext.form.TextField({
			name : 'referenceNbr',
			fieldLabel : bookingMsg.referenceNbr,
			anchor : '90%',
			required : true,
			allowBlank : false
		});

		this.referenceTypeTxf = new Ext.form.TextField({
			name : 'referenceType',
			fieldLabel : bookingMsg.referenceType,
			anchor : '90%',
			required : true,
			allowBlank : false

		});

		this.fromTimeTmf = new Ext.form.DateField({
			name : 'fromTime',
			fieldLabel : bookingMsg.fromTime,
			anchor : '100%',
//			value : new Date(),
			minValue : new Date().clearTime(),
			required : true,
			allowBlank : false
		});

		this.fromTimeField = new Ext.form.TimeField({
			name : 'fromTimeField',
//			fieldLabel : bookingMsg.fromTime,
			anchor : '69%',
			allowBlank : false
//			value : new Date().format("g:i A")
		});

		this.toTimeTmf = new Ext.form.DateField({
			name : 'toTime',
			fieldLabel : bookingMsg.toTime,
			minValue : new Date().clearTime(),
			anchor : '100%',
			required : true,
			allowBlank : false

//			value : new Date()	
		});

		this.toTimeField = new Ext.form.TimeField({
			name : 'toTimeField',
//			fieldLabel : bookingMsg.toTime,
			anchor : '69%',
			allowBlank : false
//			value : new Date().format("g:i A")
		});
		
		this.surgeryCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'surgery',
			fieldLabel : bookingMsg.surgery,
			anchor : '90%',
//			displayField : 'code',
			store : loadSurgeryStore(),
			required : true,
			allowBlank : false

		});
		

		this.otCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'ot',
			fieldLabel : "OT",
			anchor : '90%',
//			displayField : 'code',
			store : loadOperationTheatreCbx(),
			required : true,
			allowBlank : false

		});
		
		
		this.surgeonCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'surgeon',
			fieldLabel : otMsg.surgeon,
			anchor : '98%',
			store : loadDoctorsCbx(),
			required : true,
			allowBlank : false

		});
	},
	
	getPatientIdTxf : function(){
		return this.patientIdTxf;
	},
	
	getPatientNameTxf : function(){
		return this.patientNameTxt;
	},
	
	getSurgeryCbx : function(){
		return this.surgeryCbx;
	},
	
	getSurgeonCbx : function(){
		return this.surgeonCbx;
	},
	
	getReferenceNbrTxf : function(){
		return this.referenceNbrTxf;
	},
	
	getReferenceTypeTxf : function(){
		return this.referenceTypeTxf;
	},
	
	getFromTimeTmf : function(){
		return this.fromTimeTmf;
	},
	
	getToTimeTmf : function(){
		return this.toTimeTmf;
	},
	
	getOtCbx : function(){
		return this.otCbx;
	},
	
	getFromTimeField : function(){
		return this.fromTimeField;
	},
	
	getToTimeField : function(){
		return this.toTimeField;
	}
	
	

});