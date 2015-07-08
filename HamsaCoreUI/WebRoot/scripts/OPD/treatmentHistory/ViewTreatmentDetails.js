/**
 * @author Sandeep Kumar
 */

 function getViewTreatmentDetailsMainPnl(config){ 	

 	if(Ext.isEmpty(config)){
		config = {
		}
	}
	var viewTreatmentDetailsTopPnl = new Ext.form.FormPanel({
		layout:'column',
		labelAlign:'left',
		border:false,
		frame:true,
		defaults:{columnWidth:.50},
		items:[
			{
				layout:'form',
				items:[
					{
						xtype:'textfield',
						readOnly:true,
					 	cls:'textfield-displayonly',
						fieldLabel:msg.patientname,
						name:'patientName',
						anchor:'90%',
						value: ((config.mode == 'edit') ? config.selectedPatientName : "" )
					}
				]
			},
			{
				layout:'form',
				items:[
					{
						xtype:'wtcdatefield',
						readOnly:true,
						hideTrigger:true,
					 	cls:'textfield-displayonly',
						fieldLabel:msg.consultationdate,
						name:'consultationdate',
						anchor:'90%',
						value: ((config.mode == 'edit') ? config.selectedAppointmentDate : "" )
					}
				]
			},
			{
				layout:'form',
				items:[
					{
						xtype:'textfield',
						readOnly:true,
					 	cls:'textfield-displayonly',
						fieldLabel:msg.patientid,
						name:'patientId',
						anchor:'90%',
						value: ((config.mode == 'edit') ? config.selectedPatientId : "" )
					}
				]
			},
//			{
//				layout:'form',
//				items:[
//					{
//
//						xtype:'textfield',
//						readOnly:true,
//					 	cls:'textfield-displayonly',
//						fieldLabel:msg.admissiondate,
//						name:'admissionDate',
//						anchor:'90%',
//						value: ((config.mode == 'edit') ? config.selectedAdmissiondate : "" )
//					}
//				]
//			},
			{
				layout:'form',
				items:[
					{
						xtype:'textfield',
						readOnly:true,
					 	cls:'textfield-displayonly',
						fieldLabel:msg.doctorsname,
						name:'doctorsName',
						anchor:'90%',
						value: ((config.mode == 'edit') ? config.selectedPrimaryDoctor : "" )
					}
				]
			},
//			{
//				layout:'form',
//				items:[
//					{
//
//						xtype:'textfield',
//						readOnly:true,
//					 	cls:'textfield-displayonly',
//						fieldLabel:msg.dischargedate,
//						name:'dischargeDate',
//						anchor:'90%',
//						value: ((config.mode == 'edit') ? config.selectedDischargeDate : "" )
//					}
//				]
//			},
			{
				columnWidth:.5,
				labelAlign:'top',
				layout:'form',
				items:[
					{
						fieldLabel:msg.reasonfortreatment,
						readOnly:true,
					 	cls:'textfield-displayonly',
					 	name:'reasonForTreatment',
						xtype:'label',
						anchor:'90%',
						value: ((config.mode == 'edit') ? config.selectedAppointmentAgenda : "" )
					}
				]
			},
			{
				columnWidth:.5,
				layout:'form',
				labelAlign:'top',
				items:[
					{
						fieldLabel:msg.doctorsremark,
						readOnly:true,
					 	cls:'textfield-displayonly',
					 	name:'doctorsRemark',
						xtype:'label',
						anchor:'90%',
						value: ((config.mode == 'edit') ? config.selectedAppointmentRemarks : "" )
					}
				]
			}
		]
	});
	
	var obdservationGrid = getMedicalObservation(config).items.items[1];
	
	var cilnicalPrescriptiondetails = new OPD.billing.AssignedServiceGrid({ hideAction : true,
																			hideTotalFld : true});
	
	cilnicalPrescriptiondetails.getPanel().getTopToolbar().hide();
	cilnicalPrescriptiondetails.getPanel().setHeight( 280 );
	var tabPanel= new Ext.TabPanel({
		width:'100%',
		border:false,	
		activeTab:0,	
		layoutOnTabChange :true,
		deferredRender:false,
		hideMode:'offsets',
		items:[
		{
			title:msg.medicalprescriptiondetails,
			autoScroll:true,
			height:300,
			border:false,
			frame:true,
			items:getMedicalPrescriptionDetails(config)
			
		},{
			title:msg.medicalobservationdetails,
			autoScroll:true,
			height:300,
			border:false,
			frame:true,
			items:obdservationGrid
		},{
			title:msg.clinicalPrescriptionDetails,
			autoScroll:true,
			height:300,
			border:false,
			frame:true,
			items:cilnicalPrescriptiondetails.getPanel()
		}/*,{
			title:msg.diagnosisdetailsreports,
			autoScroll:true,
			height:300,
			border:false,
			frame:true,
			items:[]
		},
		{
			title:msg.proceduresfortreatment,
			autoScroll:true,
			height:300,
			border:false,
			frame:true,
			items:[]
		}*/]
	});
	
	obdservationGrid.getStore().load({params:{start:0, limit:8}, 
 									  arg:[config.selectedAppointmentNumber]});
	
	ServiceManager.getAssignedServiceAndPackageList(config.selectedAppointmentNumber,
													configs.referenceTypeForOPD, 
													false,
													function(assignedServiceAndPackageBM){
		if( assignedServiceAndPackageBM!=null && assignedServiceAndPackageBM.length >0 ){
			for( var k = 0; k < assignedServiceAndPackageBM.length; k++ ){
				cilnicalPrescriptiondetails.loadData(assignedServiceAndPackageBM[k]);
			}
		}
	});
	
	var viewTreatmentDetailsMainPnl = new Ext.Panel({
		frame : false,
		border : false,
		height:'100%',
		width:'100%',
		modal:true,
		resizable:false,
		items:[
				viewTreatmentDetailsTopPnl,
				tabPanel
			  ]
	});
	
    return	viewTreatmentDetailsMainPnl;
 }

function getMedicalPrescriptionDetails(config) {
	var selectedRecord;
	var prescriptionRecordType = Ext.data.Record.create([
     	{name: "serialNumber", type: "string"},
      	{name: "date", type: "date"},
       	{name: "doctorName", type: "string"},
       	{name: "department", type: "string"}
	]);
	
	var medicalprescriptionStore = new Ext.data.Store({
		data:[],
		reader: new Ext.data.ArrayReader({id:'id'},
		                   prescriptionRecordType)
	});	

	var managePrescriptionGridPnl = new Ext.grid.GridPanel({
		title:'Doctor details',
		autoScroll:true,
		stripeRows:true,
		frame:true,
		height:280,
		viewConfig:{forceFit:true},
		ds: medicalprescriptionStore ,
		cm: new Ext.grid.ColumnModel ([
				{header: "S.No", width: 50, sortable: true, dataIndex:'serialNumber'},
				{header: "Date", width: 60, sortable: true, dataIndex:'date',renderer: Ext.util.Format.dateRenderer('d/m/Y')},
				{header: "DoctorName", width:100, sortable: true, dataIndex:'doctorName'},
				{header: "Department", width: 75, sortable: true, dataIndex:'department'}
		]),
		
		listeners: {
			cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
				selectedRecord = thisGrid.getStore().getAt(rowIndex).data; 
				var store = gridPanel.getStore();
				store.load({params:{start:0, limit:10}, 
						    arg:[config.selectedAppointmentNumber]});
			}
		}
	});
	
	var medicalPrescriptionGrid = new OPD.treatmentHistory.MedicalPrescriptionGrid();
	var gridPanel = medicalPrescriptionGrid.getPanel();
	
	var medicalPrescriptionPnl = new Ext.Panel({
        layout:'column',
		height:'100%',
//		defaults:{style:'padding:5px'},
	    items:[
	          {
		          columnWidth:.4,
		          layout:'form',
		          items:managePrescriptionGridPnl
	           },
	           {
		          columnWidth:.58,
		          layout:'form',
		          items:gridPanel
	           }
          ]
	});
		
	if(!Ext.isEmpty(config) ){
		 var record = medicalprescriptionStore.recordType;
	 
		 var medicalPrescriptionRecord = new record({
		    serialNumber: 1 ,
			date : config.selectedAppointmentDate,
			doctorName : config.selectedPrimaryDoctor,
			department: config.selectedDepartment
		  });
		  
		  managePrescriptionGridPnl.stopEditing();
		  medicalprescriptionStore.insert(0,medicalPrescriptionRecord);
	}
		
	return medicalPrescriptionPnl;
}