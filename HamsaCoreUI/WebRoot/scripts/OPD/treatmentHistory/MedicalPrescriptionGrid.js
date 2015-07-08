Ext.namespace("OPD.treatmentHistory");


OPD.treatmentHistory.MedicalPrescriptionGrid = Ext.extend(Object,{
	constructor:function( config ){
		
		this.medicalPrescriptionRecordType = Ext.data.Record.create([
			{name:"serialNo", type:"int"},
		    {name:"medicineCode", mapping:"medicine.code", type: "string"},
		    {name:"strength", type: "string"},
		    {name:"remarks", type: "string"},
			{name:"repeats", type: "string"},
			{name:"dosage", type: "string"},
			{name:"form", mapping:"form.description", type:"string"},
			{name:"prescriptionNumber", type:"string"},
			{name:'appointmentNumber'}
		]);
		
		this.medicalPrescriptionDataStore = new Ext.data.Store({
		   proxy: new Ext.data.DWRProxy(MedicalPrescriptionController.getPrescriptionDetails, true),
		   reader: new Ext.data.ListRangeReader( 
				{id:'id', totalProperty:'totalSize'}, this.medicalPrescriptionRecordType),
		   remoteSort: true
		});
		var mainThis = this;
		this.bbar = new Ext.WTCPagingToolbar({
			store: this.medicalPrescriptionDataStore,
	    	displayMsg:" Displaying prescriptions {0} - {1} of {2}",
	    	emptyMsg:"No prescriptions details to display"
	    });
							
		this.medicalPrescriptionGridPnl = new Ext.grid.EditorGridPanel({
			title: !Ext.isEmpty( config )?"":"Prescription details",
			autoScroll:true,
			stripeRows:true,
			frame: true,
			viewConfig:{forceFit:true},
			height:280,
			ds: this.medicalPrescriptionDataStore,
			cm: new Ext.grid.ColumnModel ([
					{header: "S.No.", width: 35, sortable: true, dataIndex: 'serialNo'},
					{header: "Medicine Name", width: 200, sortable: true, dataIndex: 'medicineCode'},
					{header: "Strength", width: 100, sortable: true, dataIndex: 'strength'},
					{header: "Form", width: 100, sortable: true,dataIndex: 'form'},
					{header: "Dose (Per Day)", width: 90, sortable: true, dataIndex: 'dosage'},
					{header: "Repeats", width: 100, sortable: true, dataIndex: 'repeats'},
				 	{header: "Remarks", width: 100, sortable: true,dataIndex: 'remarks'},
				 	{header: "Prescription No.", hidden:true, dataIndex: 'prescriptionNumber'},
				 	{header: "Appointment number", hidden:true, dataIndex: 'appointmentNumber'}
			]),
			
			bbar:this.bbar
		});
		
	
	},
	getPanel:function(){
		return this.medicalPrescriptionGridPnl;
	}
	
});