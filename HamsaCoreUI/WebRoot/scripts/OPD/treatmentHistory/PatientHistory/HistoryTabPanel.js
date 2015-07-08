Ext.namespace("OPD.treatmentHistory.PatientHistory");

OPD.treatmentHistory.PatientHistory.HistoryTabPanel = Ext.extend(Ext.TabPanel,{
	initComponent: function(){
		
		this.observationGrid = new OPD.treatmentHistory.PatientHistory.TreatmentObservationGrid();
		this.prescriptionGrid = new OPD.treatmentHistory.MedicalPrescriptionGrid({ });
		this.clinicalPrescriptionGrid = new OPD.treatmentHistory.PatientHistory.ClinicalPrescriptionGrid();
		
		this.medicalPrescriptionRecordType = this.prescriptionGrid.getPanel().getStore().recordType;
		
		this.medicalPrescriptionDataStore = new Ext.data.GroupingStore({
		   proxy: new Ext.data.DWRProxy(MedicalPrescriptionController.getPrescriptionDetailsForPatient, true),
		   reader: new Ext.data.ListRangeReader( 
				{id:'id', totalProperty:'totalSize'}, this.medicalPrescriptionRecordType),
		   remoteSort: true,
		   groupField:'appointmentNumber',
           sortInfo: {field: 'appointmentNumber', direction: "ASC"}
		});
		
		this.prescriptionGrid.getPanel().store = this.medicalPrescriptionDataStore;
		this.prescriptionGrid.getPanel().setHeight( 450 );
		this.prescriptionGrid.getPanel().view =  new Ext.grid.GroupingView({
			        viewConfig:{forceFit:true},
			        hideGroupedColumn:true,
			        enableGroupingMenu: false,
			        // custom grouping text template to display the number of items per group
			        groupTextTpl: '{[getPrescriptionGroupText(values.text)]}'
			    }),
		
		this.prescriptionGrid.getPanel().getBottomToolbar().hide(); 
		Ext.apply(this,{
			autoHeight: true,
			activeTab: 0,
			items:[
				{
					autoHeight: true,
					title: msg.medicalprescriptiondetails,
					items: this.prescriptionGrid.getPanel()
				},
				{
					autoHeight: true,
					title: patientHistoryMsg.prescriptionDetails,
					items: this.observationGrid
				},
				{
					autoHeight: true,
					title: msg.clinicalPrescriptionDetails,
					items: this.clinicalPrescriptionGrid
//					items: this.Clinical
				}
				
			]
		});
		 OPD.treatmentHistory.PatientHistory.HistoryTabPanel.superclass.initComponent.apply(this, arguments);
	},
	getPrescriptionGrid: function(){
		return  this.prescriptionGrid.getPanel();
	},
	getObservationGrid: function(){
		return this.observationGrid.getGrid();
	},
	getClinicalPrescriptionGrid: function(){
		return this.clinicalPrescriptionGrid;
	}
});

function getPrescriptionGroupText ( text ){
 return text;
 }