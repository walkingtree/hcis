Ext.namespace("OPD.treatmentHistory.PatientHistory");

OPD.treatmentHistory.PatientHistory.TreatmentObservationGrid = Ext.extend(Ext.grid.GridPanel,{
	initComponent: function(){
		
		this.observationRecordType = Ext.data.Record.create([
			{name: "serialNo", mapping:"observationSeqNo", type: "string"},
			{name: "observation",mapping:"observationText", type: "string"},
			{name: "dateSince", mapping : 'date', type: "date"},
			{name: "doctor",mapping:"doctor.description",   type: "string"},
			{name: "department", type: "string"},
			{ name:'appointmentNumber'},
			{name : 'hideViewObservations'}
		
		]);
		
		this.dataStore = new Ext.data.GroupingStore({
			proxy: new Ext.data.DWRProxy(MedicalPrescriptionController.getObservationsForPatient,true),
			reader: new Ext.data.ListRangeReader( 
					{id:'id', totalProperty:'totalSize'}, this.observationRecordType ),
        	groupField:'appointmentNumber',
        	sortInfo: {field: 'appointmentNumber', direction: "ASC"},
        	remoteSort: true
		});
		
		this.actions = new Ext.ux.grid.RowActions({
	       	header:'Actions',
			keepSelection:true,
			widthSlope : 50,
			actions:[{
				iconCls:'view-icon',
				hideIndex:'hideViewObservations',
				tooltip :'View observations'
			}]
	     });
	     
     	this.initialListeners();
     
	     Ext.apply(this,{
	     	autoScroll:true,
			stripeRows:true,
			height:450,
			viewConfig:{forceFit:true},
			ds: this.dataStore,
			plugins : this.actions,
			cm: new Ext.grid.ColumnModel ([
				{header: "Serial Number",width:60, dataIndex: 'serialNo'},
				{header: "Observation date", dataIndex: 'dateSince',renderer: Ext.util.Format.dateRenderer('d/m/Y'),width:250},
				{header: "Doctor",width:300, dataIndex: 'doctor'},
				{header: "Department", hidden: true,dataIndex: 'department'},
				{header: "Appointment number", hidden: true,dataIndex: 'appointmentNumber'},
				this.actions
			]),
			view:  new Ext.grid.GroupingView({
			        viewConfig:{forceFit:true},
			        hideGroupedColumn:true,
			        enableGroupingMenu: false,
			        // custom grouping text template to display the number of items per group
			        groupTextTpl: '{[getPrescriptionGroupText(values.text)]}'
			    })
	     
	     });
	     
	     OPD.treatmentHistory.PatientHistory.TreatmentObservationGrid.superclass.initComponent.apply(this, arguments);
	},
	initialListeners: function(){
     	this.actions.on('action',function(grid, record, action, row, col){
     	var htmlEditor = new Ext.form.HtmlEditor({
     		height : '100%',
     		anchor:'90%'
     	});
     	var observationWindow = new Ext.Window({
			title: 'Observations',
			items:htmlEditor,
			frame:true,
			height:'100%',
			width:'50%'
     	});
     	observationWindow.show();
     	htmlEditor.setValue(record.data.observation);
     },this);
     },
     getGrid: function(){
     	return this;
     }
});