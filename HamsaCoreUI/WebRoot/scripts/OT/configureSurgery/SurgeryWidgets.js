Ext.namespace("OT.configureSurgery");

OT.configureSurgery.SurgeryWidgets = Ext.extend(Object,{
	constructor :  function(){
	
		this.otNameCbx= new wtccomponents.WTCComboBox({
			name : 'otName',
			fieldLabel : otMsg.lblOtName,
			anchor : '90%',
			store : loadOperationTheatreCbx() 
		});
	
		this.surgeryTypeCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'surgeryType',
			fieldLabel : otMsg.lblSurgeryType,
			anchor : '90%',
			store : loadSurgeryServiceTypeCbx(),
			allowBlank : false,
			required : true
		});
		
	
		this.specialityCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'speciality',
			fieldLabel : otMsg.lblSpeciality,
			anchor : '90%',
			store : loadEspecialityCbx(), 
			allowBlank : false,
			required : true
		});
		
	
		this.totalNumberField= new Ext.form.NumberField({
			name : 'total',
			fieldLabel : otMsg.lblTotal,
			anchor : '100%'
		});

		this.surgeonRefreshmentTimeTxf= new Ext.form.TextField({
			name : 'surgeonRefreshmentTime',
			fieldLabel : otMsg.surgeonRefreshmentTime,
			anchor : '50%'
		});
		
	},
	
	getOtNameCbx : function(){
		return this.otNameCbx;
	},
	getSurgeryTypeCbx : function(){
		return this.surgeryTypeCbx;
	},
	getSpecialityCbx : function(){
		return this.specialityCbx;
	},
	getTotalNumberField : function(){
		return this.totalNumberField;
	},
	getSurgeonRefreshmentTimeTxf : function(){
		return this.surgeonRefreshmentTimeTxf
	}
});