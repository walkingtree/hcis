function getMedicalObservation(config){
	Ext.QuickTips.init();
	
	var actions = new Ext.ux.grid.RowActions({
       	header:'Actions',
		keepSelection:true,
		widthSlope : 50,
		actions:[{
			iconCls:'view-icon',
			hideIndex:'hide1',
			tooltip :'View observations'
			
			
		}]
     });
     
     actions.on('action',function(grid, record, action, row, col){
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
     
	
	var medicalObservationDetailsPnl = new Ext.form.FieldSet({
        layout:'column',
        height:'100%',
        labelAlign:'left',
        items:[
               {
                columnWidth:.3,
                layout:'form',
                border:false,
                items:[{
                       xtype:'textfield',
                       fieldLabel:msg.patientid,
                       anchor:'90%'
                      },
                      {
                       xtype:'combo',
                       fieldLabel:msg.patienttype,
                       anchor:'90%'
                      }]
              },
                {
                columnWidth:.3,
                layout:'form',
                border:false,
                items:[{
                       xtype:'textfield',
                       fieldLabel:msg.patientname,
                       anchor:'90%'
                      },
                      {
                       xtype:'combo',
                       fieldLabel:msg.departmentname,
                       anchor:'90%'
                      }]
              },
              {
                columnWidth:.3,
                layout:'form',
                border:false,
                items:[{
                       xtype:'textfield',
                       fieldLabel:msg.consultationdate,
                       anchor:'90%'
                      },{
                       xtype:'combo',
                       fieldLabel:msg.doctorname,
                       anchor:'90%'
                      }]
              }
          ]
	});

	var observationRecordType = Ext.data.Record.create([
		{name: "serialNo", mapping:"observationSeqNo", type: "string"},
		{name: "observation",mapping:"observationText", type: "string"},
		{name: "dateSince", mapping : 'date', type: "date"},
		{name: "doctor",mapping:"doctor.description",   type: "string"},
		{name: "department", type: "string"},
		{name : 'hide1'}
		
//		{name: "remarks", mapping:"remarks", type: "string"}
	]);
			  
	var medicalObservationDateStore = new Ext.data.Store({
		proxy: new Ext.data.DWRProxy(MedicalPrescriptionController.getObservations,true),
		reader: new Ext.data.ListRangeReader( 
			{id:'id', totalProperty:'totalSize'}, observationRecordType),
		remoteSort: true
	});	
			  
	var bbar = new Ext.WTCPagingToolbar({
	    store: medicalObservationDateStore,
	    displayMsg:" Displaying observations {0} - {1} of {2}",
	    emptyMsg:"No observations to display"
	});
			
	var medicalobservationpnl = new Ext.grid.GridPanel({
		autoScroll:true,
		stripeRows:true,
		frame: true,
		height:280,
		viewConfig:{forceFit:true},
		ds: medicalObservationDateStore,
		plugins : actions,
		cm: new Ext.grid.ColumnModel ([
			{header: "Serial Number", dataIndex: 'serialNo'},
//			{header: "Observation", dataIndex: 'observation'},
			{header: "Observation date", dataIndex: 'dateSince',renderer: Ext.util.Format.dateRenderer('d/m/Y')},
			{header: "Doctor", dataIndex: 'doctor'},
			{header: "Department", hidden: true,dataIndex: 'department'},
//			{header: "Remarks", dataIndex: 'remarks'},
			actions
		]),
		bbar:bbar
	});
	
	var medicalObservationMainpnl = new Ext.form.FormPanel({
	    height:'100%',
	    width:'100%',
	    border:false,
	    frame:true,
	    items:[
	 		medicalObservationDetailsPnl,
	 		medicalobservationpnl
		]
	});
	
	return medicalObservationMainpnl;
}

    