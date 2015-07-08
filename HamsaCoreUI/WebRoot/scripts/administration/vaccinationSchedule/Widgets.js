Ext.namespace("administration.vaccinationSchedule");

administration.vaccinationSchedule.Widgets = Ext.extend(Object,{
	constructor: function(){
		
		this.scheduleNameTxf = new Ext.form.TextField({
			name: schldVaccineMsg.formScheduleName,
			fieldLabel : schldVaccineMsg.scheduleName,
			anchor:'98%',
			required:true,
			allowBlank: false
		});
		
		this.scheduleDescTxf = new Ext.form.TextField({
			name: schldVaccineMsg.formScheduleDesc,
			fieldLabel : schldVaccineMsg.description,
			anchor:'98%'
		});
		
		this.ageGroupTxf = new Ext.form.TextField({
			name: 'ageGroup',
			fieldLabel : "Age group",
			anchor:'98%'
		});

		this.vaccinationTypeCombo = new Ext.form.ComboBox({
			fieldLabel : 'Vaccination type',
			mode : 'local',
			store : loadVaccinationTypeStoreCbx(),
			displayField : 'description',
			valueField : 'code',
			triggerAction : 'all',
			hiddenName : 'vaccinationType',
			allowBlank:false,
			required:true,
			forceSelection : true,
			anchor:'98%'
		});

		this.periodTxf = new Ext.form.NumberField({
			name: schldVaccineMsg.formRangePeriod,
			fieldLabel : schldVaccineMsg.Period,
			anchor:'53%'
		});

		this.vaccinationNameCombo = new Ext.form.ComboBox({
			fieldLabel : 'Vaccination name',
			mode : 'local',
			store : loadVaccinationStoreCbx(),
			displayField : 'description',
			valueField : 'code',
			triggerAction : 'all',
			hiddenName : 'vaccinationName',
			forceSelection : true,
			anchor:'98%'
		});

		this.dosageTxf = new Ext.form.TextField({
			name: schldVaccineMsg.formDosage,
			fieldLabel : schldVaccineMsg.dosage,
			anchor:'98%'
		});

		this.remarksTxf = new Ext.form.TextField({
			name: schldVaccineMsg.formRemarks,
			fieldLabel: schldVaccineMsg.remarks,
			anchor:'98%'
		});

		this.patientIdTxf = new Ext.form.NumberField({
			name: "patientID",
			fieldLabel: "Patient ID",
			anchor:'98%',
			allowBlank:false,
			required:true
		});

		this.patientNameTxf = new Ext.form.TextField({
			name: "patientName",
			fieldLabel: "Patient name",
			disabled : true,
			anchor:'98%'
		});

		this.ageTxf = new Ext.form.TextField({
			name: "age",
			fieldLabel: "Age",
			disabled : true,
			anchor:'98%'
		});

		this.vaccinationScheduleCombo = new Ext.form.ComboBox({
			fieldLabel : 'Vaccination schedule',
			mode : 'local',
			store : loadVaccinationScheduleCbx(),
			displayField : 'description',
			valueField : 'code',
			triggerAction : 'all',
			hiddenName : 'vaccinationSchedule',
			forceSelection : true,
			anchor:'98%'
		});

		this.startDateFld = new Ext.form.WTCDateField({
			name: "startDate",
			fieldLabel : "Start date",
			anchor:'98%',
			minValue:new Date().clearTime(true)
		});		
		
		this.doctorCombo = new Ext.form.ComboBox({
			fieldLabel : 'Doctor',
			mode : 'local',
			store : loadDoctorsCbx(),
			displayField : 'description',
			valueField : 'code',
			triggerAction : 'all',
			hiddenName : 'doctor',
			forceSelection : true,
			anchor:'98%'
		});
		
		this.isActiveChk = new Ext.form.Checkbox({
			boxLabel: 'active?',
			labelSeperator :'',
			name:'activeFlag',
			checked:true
		});
		
	    this.showVaccDtlsBtn = new Ext.Button({
			text: "Show vaccination details",
			scope:this
		});

	    this.addBtn = new Ext.Toolbar.Button({
			iconCls : 'add_btn',
			text : schldVaccineMsg.btnAdd,
			scope: this
		});
		
	},
	getScheduleNameTxf: function(){
		return this.scheduleNameTxf;
	},

	getScheduleDescTxf: function(){
		return this.scheduleDescTxf;
	},

	getAgeGroupTxf: function(){
		return this.ageGroupTxf;
	},
	
	getVaccinationTypeCombo: function(){
		return this.vaccinationTypeCombo;
	},

	getPeriodTxf: function(){
		return this.periodTxf;
	},

	getVaccinationNameCombo : function(){
		return this.vaccinationNameCombo;
	},
	
	getDosageTxf: function(){
		return this.dosageTxf;
	},

	getRemarksTxf: function(){
		return this.remarksTxf;
	},
	
	getPatientIdTxf : function(){
		return this.patientIdTxf;
	},
	
	getPatientNameTxf : function(){
		return this.patientNameTxf;
	},

	getAgeTxf : function(){
		return this.ageTxf;
	},
	
	getVaccinationScheduleCombo : function(){
		return this.vaccinationScheduleCombo;
	},

	getStartDateFld : function(){
		return this.startDateFld;
	},
	
	getDoctorCombo : function(){
		return this.doctorCombo;
	},
	
	getShowVaccinationDetailsBtn : function(){
		return this.showVaccDtlsBtn;
	},
	
	getAddBtn:function(){
		return this.addBtn;
	},
	getIsActiveChk: function(){
		return this.isActiveChk;
	}
	
});