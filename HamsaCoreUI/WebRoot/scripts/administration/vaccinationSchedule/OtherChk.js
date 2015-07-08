Ext.namespace("administration.vaccinationSchedule");

administration.vaccinationSchedule.OtherChk = Ext.extend(Object,{
	constructor: function(){
			this.otherChk = new  Ext.form.Checkbox({
			boxLabel: schldVaccineMsg.others,
			name: schldVaccineMsg.formOthers,
			labelSeparator:''
		});
	},
	getChk: function(){
		return this.otherChk;
	}
});
