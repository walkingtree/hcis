Ext.namespace("OPD.treatmentHistory.PatientHistory");

OPD.treatmentHistory.PatientHistory.History = Ext.extend(Ext.Panel,{
	initComponent: function( ){
		
		this.historyTabPanel = new OPD.treatmentHistory.PatientHistory.HistoryTabPanel();
		
		this.treatmentHistoryBtn  = new Ext.Button({
			text: patientHistoryMsg.getTreatmentHistory,
			disabled: true
		});;
		
		this.patientIdNbr = new Ext.form.NumberField({
			anchor:'95%',
			fieldLabel: patientHistoryMsg.patientId,
			name:'patientId',
			emptyText: patientHistoryMsg.enterPatientId
		
		});
		
		this.basicPanel = new Ext.Panel({
			height:'100%',
			width:'100%',
			layout:'column',
			items:[
				{
					layout:'form',
					columnWidth:'0.5',
					items:this.patientIdNbr
				},
				{
					layout:'form',
					columnWidth:'0.3',
					items:this.treatmentHistoryBtn
				}
			]
		});
		this.treatmentDetailsPanel = new Ext.Panel({
			title:patientHistoryMsg.treatmentDetails,
			border: false,
			height:'100%',
			width:'100%',
			items:this.historyTabPanel
		});
		
		if( !Ext.isEmpty( this.initialConfig.directFlag )){
			this.initListeners();
		}
		
		Ext.apply(this,{
			height:'100%',
			width:'100%',
			items:[
				this.basicPanel,
				this.treatmentDetailsPanel
			]
		});
		OPD.treatmentHistory.PatientHistory.History.superclass.initComponent.apply(this, arguments);
	},
	initListeners: function(){
		this.treatmentDetailsPanel.hide();
		
		this.getTreatmentHistoryBtn().on('click', function( ){
			if( !Ext.isEmpty( this.getpatientIdNbr().getValue() ) &&
				this.getpatientIdNbr().isValid()){
				var patientId = this.getpatientIdNbr().getValue();
			
				var prescriptionGrid = this.historyTabPanel.getPrescriptionGrid();
				prescriptionGrid.getStore().load({params:{start:0, limit:9999}, arg:[ patientId ]});
				
				var observationGrid =  this.historyTabPanel.getObservationGrid();
				observationGrid.getStore().load({params:{start:0, limit:9999}, arg:[ patientId ]});
				
				var clinicalPrescriptionGrid = this.historyTabPanel.getClinicalPrescriptionGrid();
				clinicalPrescriptionGrid.store.load({params:{start:0, limit:9999}, arg:[ patientId ]});
				this.treatmentDetailsPanel.show();
			}
		},this);
		
		this.getpatientIdNbr().on('blur', function( comp ){
			if( !Ext.isEmpty( comp.getValue() )){
				this.getTreatmentHistoryBtn().enable();
			}else{
				this.getTreatmentHistoryBtn().disable();
				this.clearGrids();
				this.treatmentDetailsPanel.hide();
			}
		},this);
		
	},
	getTreatmentHistoryBtn: function(){
		return this.treatmentHistoryBtn;
	},
	getHistoryTabPanel: function(){
		return this.historyTabPanel;
	},
	getpatientIdNbr: function(){
		return this.patientIdNbr;
	},
	clearGrids: function(){
		this.historyTabPanel.getPrescriptionGrid().getStore().removeAll();
		this.historyTabPanel.getObservationGrid().getStore().removeAll();
		this.historyTabPanel.getClinicalPrescriptionGrid().store.removeAll();
	}
});