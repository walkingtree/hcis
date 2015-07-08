Ext.namespace("reports.patientVaccinationScheduleReport");

reports.patientVaccinationScheduleReport.InputForm = Ext.extend(Ext.form.FormPanel, {
	title : 'Report Criteria',
	border:false,
	layout : 'column',
	border : false,
	frame:true,
	height:135,
	width:'97%',
	labelWidth : 125,	
	
	initComponent : function() {

		this.widgets = new reports.patientVaccinationScheduleReport.Widgets();
		this.buttonsPanel = new reports.ButtonsPanel();
		
		Ext.applyIf(this, {
            items: [
	            {
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getPatientIdTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getPatientNameTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getVaccinationScheduleCombo()]
				},{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getAgeTxf()]
			    },{
	            	layout : 'form',
					columnWidth:1,
					items:[this.buttonsPanel]
			    }
			    		
			]            
        });
        reports.patientVaccinationScheduleReport.InputForm.superclass.initComponent.apply(this, arguments);
	},
	
	getButtonPanel : function(){
		return this.buttonsPanel;
	},
	
	getValues : function(){
		return this.getForm().getValues();
	},
	getReset : function(){
		return this.getForm().reset();
	}
	
	
});

Ext.reg('patientvaccinationschedulereport-criteria-panel', reports.patientVaccinationScheduleReport.InputForm);
