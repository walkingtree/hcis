
Ext.namespace("administration.vaccinationSchedule");

administration.vaccinationSchedule.vaccinationCombo = Ext.extend(Object,{
	constructor: function( config ){
		this.vaccinationCbx = new Ext.ux.form.LovCombo({
			hiddenName: config.name,
			fieldLabel: config.label,
			mode: 'local',
//			store:
			displayField: 'description',
			valueField: 'code',
			triggerAction: 'all',
			hideOnSelect:false,
			hideTrigger :true,
			readOnly:false,
			anchor:'98%'
		});
	},
	getCombo: function(){
		return this.vaccinationCbx;
	}
});
