Ext.namespace("administration.vaccinationSchedule");

administration.vaccinationSchedule.ScheduleVaccinationPanel = Ext.extend(Ext.Panel, {
	frame : true,
	autoHeight : true,
	border : false,
	width:'100%',
	layout:'column',

	initComponent: function(){
		
		this.widgets = new administration.vaccinationSchedule.Widgets();
		
		this.vaccinationNameCbx = new administration.vaccinationSchedule.vaccinationCombo({
																	name:schldVaccineMsg.formVaccinationName,
																	label:schldVaccineMsg.vaccinationName
																					});
		this.afterVaccinatioNameCbx = new administration.vaccinationSchedule.vaccinationCombo({
																	name:schldVaccineMsg.formAfterVaccinationName,
																	label:schldVaccineMsg.afterVaccinationName
																					});
		
		this.vaccinationChk = new administration.vaccinationSchedule.OtherChk();
		this.afterVaccinationChk = new administration.vaccinationSchedule.OtherChk();
		
		this.gridPanel = new administration.vaccinationSchedule.VaccinationGridPanel();
		
		this.vaccinationCardPanel = new Ext.Panel({
			frame: false,
			autoHeight: true,
			border: false,
			layout:'card',
			activeItem: 0,
			defaults: {
				layout: 'form'
			},
			items:[
					{items:this.vaccinationNameCbx.getCombo()},
					{items:this.widgets.getOtherVaccineTxf()}
			]
		});
		
		this.afterVaccinationCardPanel = new Ext.Panel({
			frame: false,
			autoHeight: true,
			border: false,
			layout:'card',
			activeItem: 0,
			defaults: {
				layout: 'form'
			},
			items:[
				{items:this.afterVaccinatioNameCbx.getCombo()},
				{items:this.widgets.getAfterOtherVaccineTxf()}
			]
		});
		
		this.vaccinationDetailsPanel = new Ext.form.FieldSet({
			frame: false,
			title:schldVaccineMsg.vaccinationDetails,
			defaults: {
				layout : 'column'
			},
			items: [
				{
					defaults: {
						border: false,
						layout: 'form',
						columnWidth : .33
					},
					items: [{
						layout:'column',
						defaults: {
							layout: 'form'
						},
						items: [{
							columnWidth:.65,
							labelWidth:50,
							items:this.widgets.getPeriodTxf()
						},{
							labelWidth:.01,
							columnWidth:.35,
							items:this.widgets.getPeriodCbx()
						}]
					},{
					    items:this.widgets.getDiseaseCbx()
				    },{
						layout:'column',
							items: [{
								columnWidth : .75,
								items:this.vaccinationCardPanel 
							},{
								columnWidth : .25,
								labelWidth:.01,
								items: this.vaccinationChk.getChk()
							}
						]
					}]
				},
				{
					defaults: {
						border: false,
						layout: 'form',
						columnWidth : .33
					},
					items: [
						{
							labelWidth:50,
						    items:this.widgets.getDosageTxf()
					    },{
							layout:'column',
							items: [{
									columnWidth : .75,
									items:this.afterVaccinationCardPanel 
								},{
									columnWidth : .25,
									labelWidth:.01,
									items: this.afterVaccinationChk.getChk()
								}
							]
						},{
							layout:'column',
							defaults: {
								layout: 'form'
							},
							items: [{
									columnWidth:.65,
									items: this.widgets.getAfterPeriodTxf()
								},{
									labelWidth:.01,
									columnWidth:.35,
									items: this.widgets.getAfterPeriodCbx()
								}
							]
						}
					]
				},{
					defaults: {
						layout: 'form'
					},
					items: [{
							columnWidth:.90,
							labelWidth:50,
							items: this.widgets.getRemarksTxa()
						},{
							labelWidth:.01,
							columnWidth:.10,
							items: this.widgets.getAddBtn()
						}
					]
				}
			]
		});
		
		this.buttonsPanel = new utils.ButtonsPanel();

		this.formPanel = new Ext.form.FormPanel({
				frame : false,
				autoHeight : true,
				border : false,
				monitorValid: true,
				width:'97%',	
				style:'padding-top:5px;',
				items: [
					{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : .50,
						},
						items: [{items: this.widgets.getScheduleNameTxf()},
								{items: this.widgets.getScheduleDescTxf()}
						]
					},
					this.vaccinationDetailsPanel,
					this.gridPanel.getPanel(),
					this.buttonsPanel
			    ]
		});
		
		
		this.formPanel.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.widgets.getsaveBtn().enable();
			} else {
				this.widgets.getsaveBtn().disable();
			}
		}, this);

        Ext.applyIf(this, {
            items: [this.formPanel]
        });

		administration.vaccinationSchedule.ScheduleVaccinationPanel.superclass.initComponent.apply(this, arguments);
	},
	
	initialListeners: function(){
		 this.vaccinationChk.getChk().on('check', function( comp, checked ){
		 	if( checked ){
		 		this.widgets.getOtherVaccineTxf().reset();
 				this.vaccinationCardPanel.layout.setActiveItem( 1 );
				this.vaccinationCardPanel.layout.activeItem.doLayout();
		 	}else{
		 		this.vaccinationNameCbx.getCombo().clearValue();
 				this.vaccinationCardPanel.layout.setActiveItem( 0 );
				this.vaccinationCardPanel.layout.activeItem.doLayout();
		 	}
		 },this);
		 
		 this.afterVaccinationChk.getChk().on('check', function( comp, checked ){
		 	if( checked ){
		 		this.widgets.getAfterOtherVaccineTxf().reset();
 				this.afterVaccinationCardPanel.layout.setActiveItem( 1 );
				this.afterVaccinationCardPanel.layout.activeItem.doLayout();
		 	}else{
		 		this.afterVaccinatioNameCbx.getCombo().clearValue();
 				this.afterVaccinationCardPanel.layout.setActiveItem( 0 );
				this.afterVaccinationCardPanel.layout.activeItem.doLayout();
		 	}
		 },this);
		 
		 this.widgets.getAddBtn().on('click', function(){
		 	this.addBtnListener();
		 },this);
		 
		 this.gridPanel.widgets.getEditBtn().on('click', function(){
		 	this.editListeer();
		 	this.gridPanel.initialStatusForGridButtons();
		 },this);
		 
		 this.gridPanel.widgets.getDeleteBtn().on('click', function(){
		 	this.gridPanel.deleteListener();
		 	this.gridPanel.initialStatusForGridButtons();
		 },this);
		 
		 administration.vaccinationSchedule.ScheduleVaccinationPanel.superclass.initialListeners.apply(this, arguments);
	},
	
	addBtnListener: function(){
		var recordType = this.gridPanel.getPanel().getStore().recordType;
		
		var period = this.widgets.getPeriodTxf().getValue();
		var periodInd = this.widgets.getPeriodCbx().getValue();
		if( Ext.isEmpty( periodInd ) ){
			error( 'Please specify the period indicator' );
			return;
		}
		if(  Ext.isEmpty( period ) ){
			error( 'Please specify the period');
			return;
		}
		var ItemOthersFlag = this.vaccinationChk.getChk().getValue();
		var vaccinationCode = this.vaccinationNameCbx.getCombo().getValue();
		var vaccinationName = this.vaccinationNameCbx.getCombo().getRawValue();
		if( Ext.isEmpty( vaccinationCode ) ){
			vaccinationCode = this.widgets.getOtherVaccineTxf().getValue();
			vaccinationName = vaccinationCode;
			if( Ext.isEmpty( vaccinationCode ) ){
				error('Please specify the Vaccination(s)');
				return;
			}
		}
		var ItemAfterOthersFlag = this.afterVaccinationChk.getChk().getValue();
		var afterVaccinationCode = this.afterVaccinatioNameCbx.getCombo().getValue();
		var afterVaccinationName = this.afterVaccinatioNameCbx.getCombo().getRawValue();
		if( Ext.isEmpty( afterVaccinationCode ) ){
			if( Ext.isEmpty( ItemAfterOthersFlag ) && ItemAfterOthersFlag == true){
				afterVaccinationCode = this.widgets.getAfterOtherVaccineTxf().getValue();
				afterVaccinationName = afterVaccinationCode;
			}
		}
		var diseaseCode = this.widgets.getDiseaseCbx().getValue();
		var diseaseName = this.widgets.getDiseaseCbx().getRawValue();
		
		if( Ext.isEmpty( diseaseCode ) ){
			error('Please specify the Disease(s)');
			return;
		}
		var dosage = this.widgets.getDosageTxf().getValue();
		var afterPeriod = this.widgets.getAfterPeriodTxf().getValue();
		var afterPeriodInd = this.widgets.getAfterPeriodCbx().getValue();
		var remarks = this.widgets.getRemarksTxa().getValue();
		
		var recordData = new recordType({
			vaccinationName: vaccinationName,
			vaccinationCode: vaccinationCode,
			diseaceCode: diseaseCode,
			diseaceName: diseaseName,
			period: period,
			periodInd : periodInd,
			dosage: dosage,
			afterVaccinationName: afterVaccinationName,
			afterVaccinationCode: afterVaccinationCode,
			remarks: remarks,
			afterPeriod: afterPeriod,
			afterPeriodInd : afterPeriodInd,
			ItemOthersFlag: ItemOthersFlag,
			ItemAfterOthersFlag: ItemAfterOthersFlag
		});
		
		this.gridPanel.getPanel().getStore().add( recordData );
		
		this.resetDataAfterAddingEntryIntoGrid();
	},
	// for resetting the details panel.
	resetDataAfterAddingEntryIntoGrid: function(){
		var values = this.formPanel.getForm().getValues();
		this.formPanel.getForm().reset();
		
		this.widgets.getScheduleNameTxf().setValue( values['scheduleName'] );
		this.widgets.getScheduleDescTxf().setValue( values['description'] );
	},
	
	editListeer: function(){
		var recordData = this.gridPanel.getSelectedRows();
		this.gridPanel.deleteListener();
	}
});