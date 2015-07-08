Ext.namespace("OT.booking");

//
// Widgets to be used to Booking slot searching
OT.booking.OTBookingWidgets =  Ext.extend(Object,{
	constructor : function(){
		
		this.bookigFromTxf= new Ext.form.WTCDateField({
			name : 'bookingFrom',
			fieldLabel : otMsg.bookingFrom,
			anchor : '90%',
			required:true,
			allowBlank:false
		});
	
		this.bookigToTxf= new Ext.form.WTCDateField({
			name : 'bookingTo',
			fieldLabel : otMsg.bookingTo,
			anchor : '90%'
		});

		this.surgeonCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'doctorName',
			fieldLabel : otMsg.surgeon,
			anchor : '90%',
			displayField : 'description',
			valueField : 'code',
			store : loadDoctorsCbx() 
		});
		
		this.surgeryCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'surgery',
			fieldLabel : otMsg.lblSurgery,
			anchor : '45%',
			store : loadSurgicalTypeServiceCbx(),
			displayField : 'description',
			valueField : 'code',
			required:true,
			allowBlank:false
		});
		
		this.otCodeCbx = new wtccomponents.WTCComboBox({
			fieldLabel : otMsg.otCodelbl,
			mode : 'local',
			store : loadOperationTheatreCbx(),
			displayField : 'description',
			valueField : 'code',
			triggerAction : 'all',
			anchor:'90%'
		});
		
		this.bookBtn = new Ext.Button({
	        text: 'Book',
	        scope : this,
	        iconCls : 'save_btn',
	        disabled : true
        });
		
		this.totalNumberField= new Ext.form.NumberField({
			name : 'total',
			fieldLabel : otMsg.lblTotal,
			anchor : '48%'
		});
		
		this.totalOTNumberField= new Ext.form.NumberField({
			name : 'totalOT',
			fieldLabel : otMsg.otTotalTime,
			anchor : '48%'
		});
		
	},
	
	getBookBtn : function(){
		return this.bookBtn;
	},
	
	getOtCodeCbx : function(){
		return this.otCodeCbx;
	},
	
	getSurgeryCbx : function(){
		return this.surgeryCbx;
	},
	
	getBookigFromTxf : function(){
		return this.bookigFromTxf;
	},
	
	getBookigToTxf : function(){
		return this.bookigToTxf;
	},
	
	getSurgeonCbx : function(){
		return this.surgeonCbx;
	},
	
	getTotalNumberField : function(isNewComp){
		if(isNewComp){
			this.totalNumberField= new Ext.form.NumberField({
				name : 'total',
				fieldLabel : otMsg.lblTotal,
				anchor : '48%'
			});
			return this.totalNumberField;
			}else{
				return this.totalNumberField;
			}
	},
	
	getTotalOTNumberField : function(isNewComp){
		if(isNewComp){
			this.totalOTNumberField= new Ext.form.NumberField({
				name : 'totalOT',
				fieldLabel : otMsg.otTotalTime,
				anchor : '48%'
			});
			return this.totalOTNumberField;
			}else{
				return this.totalOTNumberField;
			}
	}
	

});