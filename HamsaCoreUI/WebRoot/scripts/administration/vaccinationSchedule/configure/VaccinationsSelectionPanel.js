Ext.namespace("administration.vaccinationSchedule.configure");

administration.vaccinationSchedule.configure.VaccinationsSelectionPanel = Ext.extend(Ext.form.FieldSet, {
	title : "Vaccinations selection",
	border: true,
	layout : 'column',
	frame:false,
	labelAlign : 'left',
	width:'94%',
	
	initComponent : function() {

		this.widgets = new administration.vaccinationSchedule.Widgets();
		this.widgets.getAgeTxf().setDisabled(false);
		Ext.applyIf(this, {
            items: [
            	{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getVaccinationNameCombo()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getVaccinationTypeCombo()]
				},{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getAgeTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getDosageTxf()]
				},{
					columnWidth:0.927,
	            	layout : 'form',
					items:this.widgets.getPeriodTxf()
				},{
					columnWidth:0.067,
	            	layout : 'form',
					items:[this.widgets.getAddBtn()]
				}
			]            
        });
       administration.vaccinationSchedule.configure.VaccinationsSelectionPanel.superclass.initComponent.apply(this, arguments);
	},
	getWidgets : function(){
		return this.widgets;
	},
	getVaccinationDetails: function(){
		var details = {
			periodInDays: this.widgets.getPeriodTxf().getValue(),
			vaccinationCode:this.widgets.getVaccinationNameCombo().getValue(),
			vaccinationName:this.widgets.getVaccinationNameCombo().getRawValue(),
			dosage: this.widgets.getDosageTxf().getValue(),
			vaccinationTypeCode:this.widgets.getVaccinationTypeCombo().getValue(),
			vaccinationType:this.widgets.getVaccinationTypeCombo().getRawValue()
			
		}
		return details;
	},
	resetData: function(){
		this.widgets.getPeriodTxf().reset();
		this.widgets.getAgeTxf().reset(),
		this.widgets.getVaccinationNameCombo().reset();
		this.widgets.getDosageTxf().reset();
		this.widgets.getVaccinationTypeCombo().reset();
	},
	setData: function( config ){
		if( !Ext.isEmpty( config )){
			this.widgets.getPeriodTxf().setValue( config.periodInDays );
			this.widgets.getAgeTxf().setValue( config.age ),
			this.widgets.getVaccinationNameCombo().setValue(config.vaccinationCode);
			this.widgets.getDosageTxf().setValue(config.dosage);
			this.widgets.getVaccinationTypeCombo().setValue(config.vaccinationTypeCode);
		}
	}
});

Ext.reg('vaccinations-selection-panel',administration.vaccinationSchedule.configure.VaccinationsSelectionPanel);