Ext.namespace("reports.patientVaccinationScheduleReport");

reports.patientVaccinationScheduleReport.Widgets = Ext.extend(Object,{

	constructor: function(){

		this.patientIdTxf = new Ext.form.TextField({
				name : 'patientId',
				fieldLabel : 'Patient ID',
				anchor:'95%',
				required:true,
				allowBlank: false
		});

		this.patientNameTxf = new Ext.form.TextField({
				name : 'patientName',
				fieldLabel : 'Patient name',
				anchor:'95%',
				disabled : true
		});

		this.ageTxf = new Ext.form.TextField({
				name : 'age',
				fieldLabel : 'Age',
				anchor:'95%',
				disabled : true
		});

		this.vaccinationScheduleCombo = new Ext.form.ComboBox({
				fieldLabel : 'Vaccination schedule',
				mode : 'local',
				store : [],
				displayField : 'description',
				valueField : 'code',
				triggerAction : 'all',
				hiddenName : 'vaccinationSchedule',
				anchor : '95%',
				forceSelection : true
		});

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
	}
});
