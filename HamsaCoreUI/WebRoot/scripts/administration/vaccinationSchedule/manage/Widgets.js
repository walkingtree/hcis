Ext.namespace("administration.vaccinationSchedule.manage");

administration.vaccinationSchedule.manage.Widgets = Ext.extend(Object,{
	constructor: function(){
		
		this.scheduleNameTxf = new Ext.form.TextField({
			name: schldVaccineMsg.formScheduleName,
			fieldLabel : schldVaccineMsg.scheduleName,
			anchor:'98%'
		});
		
		this.scheduleDescTxf = new Ext.form.TextField({
			name: schldVaccineMsg.formScheduleDesc,
			fieldLabel : schldVaccineMsg.description,
			anchor:'98%'
		});
		
		this.ageGroupTxf = new Ext.form.TextField({
			name:'ageGroup',
			fieldLabel : "Age group",
			anchor:'98%'
		});
		
		this.ageTxf = new Ext.form.TextField({
			name:'age',
			fieldLabel : "Age",
			anchor:'98%'
		});
		
		this.activeCombo = new Ext.form.OptComboBox({
			fieldLabel : 'Active',
			mode : 'local',
			store : loadActiveVaccinationScheduleFlagStoreCbx(),
			displayField : 'description',
			valueField : 'code',
			triggerAction : 'all',
			hiddenName : 'activeFlag',
			forceSelection : true,
			anchor:'98%'
		});
		
//		this.vaccinationTypeCombo = new Ext.form.OptComboBox({
//				fieldLabel : 'Vaccination type',
//				mode : 'local',
//				store : loadVaccinationTypeStoreCbx(),
//				displayField : 'description',
//				valueField : 'code',
//				triggerAction : 'all',
//				hiddenName : 'vaccinationType',
//				forceSelection : true,
//				anchor:'98%'
//		});
		
		this.vaccinationNameCombo = new Ext.form.OptComboBox({
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
//	getVaccinationTypeCombo: function(){
//		return this.vaccinationTypeCombo;
//	},
	getVaccinationNameCombo : function(){
		return this.vaccinationNameCombo;
	},
	getAgeTxf : function(){
		return this.ageTxf;
	},
	getActiveCombo : function(){
		return this.activeCombo;
	}
});