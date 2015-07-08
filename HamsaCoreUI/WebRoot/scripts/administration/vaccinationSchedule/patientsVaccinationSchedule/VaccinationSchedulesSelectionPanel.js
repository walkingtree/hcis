Ext.namespace("administration.vaccinationSchedule.patientsVaccinationSchedule");

administration.vaccinationSchedule.patientsVaccinationSchedule.VaccinationSchedulesSelectionPanel = Ext.extend(Ext.form.FieldSet, {
	title : "Vaccination schedules selection",
	border: true,
	layout : 'column',
	frame:false,
	labelAlign : 'left',
	width:'96%',
	labelWidth : 120,
	initComponent : function() {

		this.widgets = new administration.vaccinationSchedule.Widgets();
		
		Ext.applyIf(this, {
            items: [
	            {
					columnWidth:.4,
					labelWidth:130,
	            	layout : 'form',
					items:[this.widgets.getVaccinationScheduleCombo()]
				},{
					columnWidth:.25,
					labelWidth:50,
	            	layout : 'form',
					items:[this.widgets.getDoctorCombo()]
			    },{
					columnWidth:.25,
					labelWidth:70,
	            	layout : 'form',
					items:[this.widgets.getStartDateFld()]
			    },{
					columnWidth:.1,
	            	layout : 'form',
	            	buttonAlign:'right',
					items:[this.widgets.getAddBtn()]
				}
			]            
        });
       administration.vaccinationSchedule.patientsVaccinationSchedule.VaccinationSchedulesSelectionPanel.superclass.initComponent.apply(this, arguments);
	},
	
	getWidgets : function(){
		return this.widgets;
	},
	
	resetData : function(){
		this.widgets.getVaccinationScheduleCombo().reset();
		this.widgets.getStartDateFld().reset();
		this.widgets.getDoctorCombo().reset();
	}
});

Ext.reg('vaccination-schedules-selection-panel',administration.vaccinationSchedule.patientsVaccinationSchedule.VaccinationSchedulesSelectionPanel);