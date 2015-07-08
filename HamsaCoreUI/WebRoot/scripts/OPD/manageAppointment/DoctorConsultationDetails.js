function doctorConsultationDetails(config) {
	var selectedServiceName;
	var selectedMedicineType;
	var selectedMedicine;
	var selectedPrescriptionRecord;
	var mainThis = this;
	var medicalPrescriptionGridChk = new Ext.grid.CheckboxSelectionModel({
		listeners:{
			rowselect : function( selectionModel, rowIndex, record){
				deleteBtn.enable();
				setGridButtonsState(selectionModel);
				if( selectionModel.getSelections().length > 1){
					editBtn.disable();
				}else{
					editBtn.enable();
					setGridButtonsState(selectionModel);
				}
			},
			rowdeselect : function( selectionModel, rowIndex, record){
				deleteBtn.disable();
				if( selectionModel.getSelections().length == 1){
					editBtn.enable();
				}else if( selectionModel.getSelections().length > 1){
					editBtn.disable();
					deleteBtn.enable();
				}else{
					deleteBtn.disable();
					editBtn.disable();
				}
				setGridButtonsState(selectionModel);
			}
		}
	});
	
	this.medicalPrescriptionRecordType = Ext.data.Record.create([
	                                                 			{name:"serialNo", type:"int"},
	                                                 		    {name:"medicineName", mapping:"medicine.code", type: "string"},
	                                                 		    {name:"strength", type: "string"},
	                                                 		    {name:"remarks", type: "string"},
	                                                 			{name:"repeats", type: "string"},
	                                                 			{name:"dosage", type: "string"},
	                                                 			{name:"formName", mapping:"form.description", type:"string"},
	                                                 			{name:"prescriptionNumber", type:"string"},
	                                                 			{name:'appointmentNumber'}
	                                                 		]);
	
	
	
	this.gridStore = new Ext.data.Store({
		   proxy: new Ext.data.DWRProxy(MedicalPrescriptionController.getPrescriptionDetails, true),
		   reader: new Ext.data.ListRangeReader( 
				{id:'id', totalProperty:'totalSize'}, this.medicalPrescriptionRecordType),
		   remoteSort: true
		});
	
	this.gridStore.load({params:{start:0, limit:99}, arg:[config.appointmentNumber]});
	
	var editBtn = new Ext.Toolbar.Button({
	     cls:'x-btn-text-icon',
	     text:msg.edit,
	     tooltip:msg.edit,
	     icon:'images/pencil.png',
	     scope: this,
	     disabled: true,
	    handler: function(){
	    var selectedRows = medicalPrescriptionGridPnl.getSelectionModel().getSelections();
	     if(selectedRows.length == 1) {
	     	deleteBtn.disable();
	      	editBtn.disable();
	     	var rowdata = selectedRows[0].data;
	     	var prescription = {
				medicineName:rowdata.medicineName,
				dosage: rowdata.dosage,
				strength: rowdata.strength,
				repeats:rowdata.repeats,
				form: rowdata.formName,
				remarks: rowdata.remarks
			};
				consultationDetailsMainPanel.getForm().setValues(prescription);
					 selectedMedicineType = {code:rowdata.formCode,description:rowdata.formName};
					 selectedMedicine = {code:rowdata.medicineCode,description:rowdata.medicineName};
					if(!Ext.isEmpty(selectedRows) && selectedRows.length>0) {
							medicalPrescriptionGridPnl.stopEditing();
							medicalPrescriptionGridPnl.getStore().remove(selectedRows[0]);
					}
    		}else {
    			Ext.Msg.alert('Medical prescription',msg.defaulterrrormessage);
    		}
		}
	});
 
	var deleteBtn = new Ext.Toolbar.Button({
	    cls:'x-btn-text-icon',
	    text:msg.del,
	    tooltip:msg.del,
	    scope: this,
	    disabled: true,
	    icon:'images/delete.png',
	    handler: function(){
	      var selectedRows = medicalPrescriptionGridPnl.getSelectionModel().getSelections();
	      if(selectedRows.length >0) {
	      	deleteBtn.disable();
	      	editBtn.disable();
	     	for(var i =0;i<selectedRows.length;i++) {
	     		medicalPrescriptionGridPnl.stopEditing();
				medicalPrescriptionGridPnl.getStore().remove(selectedRows[i]);
	     	}
	      }	
		}
	});
 
	var medicalPrescriptionGridPnl = new Ext.grid.GridPanel({
		autoScroll:true,
		stripeRows:true,
		frame: false,
		height:230,
		ds: gridStore,
		viewConfig:{
			forceFit:true
		},
		sm : medicalPrescriptionGridChk,
		cm: new Ext.grid.ColumnModel ([
				medicalPrescriptionGridChk,
				{header: "Medicine Name", width: 75, sortable: true, dataIndex: 'medicineName'},
				{header: "Strength", width: 75, sortable: true, dataIndex: 'strength'},
				{header: "Form", width: 75, sortable: true,dataIndex: 'formName'},
				{header: "Dose (Per Day)", width: 75, sortable: true, dataIndex: 'dosage'},
				{header: "Repeats", width: 75, sortable: true, dataIndex: 'repeats'},
			 	{header: "Remarks", width: 200, sortable: true,dataIndex: 'remarks'}
		]),
		listeners: {
			cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
					setGridButtonsState(thisGrid.getSelectionModel());
		}
		},
		tbar:[ editBtn,
	  		   deleteBtn ]
	});
	
	// For making already existing medicines not able to do any modifications in consultation details
	
	var setGridButtonsState = function( selectionModel ){
		var selectedRows = selectionModel.getSelections();
		editBtn.disable();
		deleteBtn.disable();
		var row = selectedRows[0];
		if( selectedRows.length === 1 && Ext.isEmpty(row.data.prescriptionNumber)){
			editBtn.enable();
			deleteBtn.enable();
			
		} else if( selectedRows.length > 1){
			editBtn.disable();
			if( Ext.isEmpty(row.data.prescriptionNumber) ){
				deleteBtn.enable();
			}
			deleteBtn.disable();
		}
	};
		
	var medicalprescriptionFieldSet= new Ext.form.FieldSet({
		layout:'column',
		border:false,
		height:'100%',
		frame:true,
		style:'padding:0px',
		labelAlign:'left',
		items:[
			{
				layout:'form',
				columnWidth:.5,
				items:[
					{
						xtype:'combo',
						fieldLabel: msg.medicinename,
						mode:'local',
						store: loadMedicineCbx(),
						displayField:'description',
						valueField:'code',
						triggerAction:'all',
					 	name:'medicineName',
						anchor:'80%',
						listeners: {
			   				select:function(thisCombo, record, index) {
					   			selectedMedicine = record.data;
					   			MedicineManagementController.getMedicineDetails(selectedMedicine.code, 
								function(medicineBM){
									if(medicineBM!='undefined' && medicineBM!=null)	{
										medicineDetails = {
											dosage: medicineBM.maximumDosage,
											strength: medicineBM.strength,
											form: medicineBM.medicineType.code
										}
										consultationDetailsMainPanel.getForm().setValues(medicineDetails);
										selectedMedicineType ={
													code: medicineBM.medicineType.code,
													description:medicineBM.medicineType.description
										}
									}
								});
			   		      }
					   } 
					}
				]
			},
			{
				layout:'form',
				columnWidth:.5,
				items:[
					{
						xtype:'textfield',
						fieldLabel: msg.strength,
						name:'strength',
						anchor:'80%'
					}
				]
			},
			{
				layout:'form',
				columnWidth:.5,
				items:[
					{
					   xtype:'combo',
					   fieldLabel:msg.form,
					   name:'form',
					   mode:'local',
					   store:loadMedicineType(),
					   displayField:'description',
					   valueField:'code',
					   triggerAction:'all',
					   anchor:'80%',
					   listeners: {
			   		   		select:function(thisCombo, record, index) {
			   					selectedMedicineType = record.data;
			   				}
					   } 
					}
				]
			},
			{
				layout:'form',
				columnWidth:.5,
				items:[
					{
						xtype:'textfield',
						fieldLabel: msg.dosage,
						name:'dosage',
						anchor:'80%'
					
					}
				]
			},
			{
				layout:'form',
				columnWidth:.5,
				items:[
					{
						xtype:'textfield',
						fieldLabel: msg.repeats,
						name:'repeats',
						anchor:'80%'
					
					}
				]
			},
			{
				layout:'column',
				columnWidth:1,
				items:[
					{
						layout:'form',
						columnWidth:.6,
						items:[
							{
								xtype:'textarea',
								fieldLabel: msg.remarks,
								name:'remarks',
								anchor:'98%'
							
							}
						]
					
					},
					{
						layout:'form',
						columnWidth:.15,
						items:[
							{
								 xtype:'button',
								 cls:'x-btn-text-icon',
		                         text:msg.add,
		                         tooltip:msg.add,
		                         style:'margin-top:40px',
						         icon:'images/add.png',
						         handler: function() {
							         var formValues = consultationDetailsMainPanel.getForm().getValues();
							         if(formValues['medicineName'] != '' ) {
							         	var record = gridStore.recordType;
										if( gridStore.getCount() > 0 ){
											for(var k =0; k<gridStore.getCount(); k++){
												var previousRecord = gridStore.getAt(k);
												if( previousRecord.data.medicineCode == formValues['medicineName'] ){
													Ext.Msg.show({
													   msg: msg.alreadyMedicineExists,
													   buttons: Ext.Msg.OK,
													   icon: Ext.MessageBox.ERROR
													});
													return;
												}
											
											}
										}
										
									 	var prescriptionRecord = new record({
						         			medicineCode: selectedMedicine.code,
						         			medicineName: selectedMedicine.description,
						         			strength: formValues['strength'],
						         			formCode: selectedMedicineType.code,
						         			formName: selectedMedicineType.description,	
						         			dosage: formValues['dosage'],
						         			repeats: formValues['repeats'],
						         			remarks: formValues['remarks']
									  });
									  medicalPrescriptionGridPnl.stopEditing();
									   var insertAt = medicalPrescriptionGridPnl.getStore().data.items.length;		
									  gridStore.insert( insertAt,prescriptionRecord );
				         				selectedServiceName= null;
										 selectedMedicineType = null;
										 selectedMedicine = null;
										 var resetFields = {
										 			dosage:'',
										 			strength:'',
										 			repeats:'',
										 			remarks:'',
										 			medicineName:'',
										 			form:''
										 			}
							 			consultationDetailsMainPanel.getForm().setValues(resetFields);
							         } else {
								         	Ext.Msg.show({
									 		msg: 'Please select medicine',
									 		buttons: Ext.Msg.OK,
									 		icon : Ext.MessageBox.INFO
								 			}); 
							         }
						         }
							}
						]
					
					}
				]
			},
			{
				columnWidth:1,
				layout:'form',
				items:medicalPrescriptionGridPnl
			}
		]
	});
	
	// This is used for getting the doctor consultation details in editing of appointment details
	
	var newConfig = {
			appointmentNumber: config.appointmentNumber,
			typeOfConsultation : 'doctorConsultation'
	};
					
	var clinicalprescriptionPanel = new OPD.billing.AssignedServicePanel(newConfig);
	if(!Ext.isEmpty(config.appointmentDate)){
		
		clinicalprescriptionPanel.assignedServiceDetailsPnl.serviceDateDt.setMinValue(config.appointmentDate);
		
	}
	clinicalprescriptionPanel.assignedServiceGridPnl.assocSvcGrid.setHeight(300);
	
	// For hiddening the actions in consultation details
	
	clinicalprescriptionPanel.assignedServiceGridPnl.action.hidden=true; 
	
	var observationFieldSet = new Ext.form.FieldSet({
		style:'padding:0px',
		border:false,
		height:'100%',
		layout:'column',
		frame:true,
		defaults:{columnWidth:1},
		items:[
			{
				xtype:'htmleditor',
				anchor:'90%',
				height:340,
				fieldLabel:'Observation text',
				name:'observationText'
			}
		]
	});
	
	// here we need to set the what is the observation details already doctor provided with in the same appointment
	
	MedicalPrescriptionController.getObservations(config.appointmentNumber,0,999,null,
			function(observationValue){
				if(!Ext.isEmpty(observationValue.data)){
					observationFieldSet.items.items[0].setValue(observationValue.data[0].observationText);
				}
		}
	);
	
	var consultationTabPanel = new Ext.TabPanel({
		width:'100%',
		height:'100%',
		border:false,	
		activeTab:0,
		layoutOnTabChange :true,
		deferredRender:false,
		hideMode:'offsets',
		items:[
			{
				title:'Observation details',
				height:400,
				border:false,
				frame:true,
				items:observationFieldSet
			},
			
			{
				title:'Clinical prescription details',
				height:400,
				frame:true,
				border:false,
				items:clinicalprescriptionPanel.getPanel()
			},
			{
				title:'Prescription details',
				height:400,
				border:false,
				frame:true,
				items:medicalprescriptionFieldSet
			}
		]
	});	
	clinicalprescriptionPanel.getPanel().doLayout();

	var consultationDetailsMainPanel = new Ext.form.FormPanel( { 
		layout:'column',
		columnWidth:1,
		height:'100%',
		frame:true,
		buttonAlign:'right',
		items:[
			consultationTabPanel
		],
		buttons:[
			{
				text:msg.btn_save,
				cls:'x-btn-text-icon',
				icon:'images/disk.png',
				scope:this,
				handler: function() {
					if(!Ext.isEmpty(config)) {
						var formValues = consultationDetailsMainPanel.getForm().getValues();
						var prescriptionRows = gridStore.data.items;
						var clinicalPrescriptionRows = clinicalprescriptionPanel.
													   assignedServiceGridPnl.
													   assocSvcGrid.getStore().data.items;
						var prescriptionBMList = new Array();
						var observationBMList = new Array();
						
						if( !Ext.isEmpty(prescriptionRows) && prescriptionRows.length>0 ) {
							var prescriptionSetItem =[];
							for(var i=0;i<prescriptionRows.length;i++) {
								var rowData = prescriptionRows[i].data;
								if(Ext.isEmpty(rowData.prescriptionNumber)){
									
									var prescriptionItem = {
											medicineCode:rowData.medicineCode,
											prescriptionDate:new Date(),
											dosage:rowData.dosage,
											repeats:rowData.repeats,
											remarks:rowData.remarks,
											strength:rowData.strength,
											form:{code:rowData.formCode,description:rowData.formName}
									};
									prescriptionSetItem[i] = prescriptionItem;
								}
							}
							
							var prescriptionBM = {
								appointmentNumber:config.appointmentNumber,
								prescriptionLineItemSet:prescriptionSetItem
							}
							
							prescriptionBMList.push( prescriptionBM );
							
						}
						
						var assignedServiceBm = clinicalprescriptionPanel.assignedServiceGridPnl.getData();
						if(! Ext.isEmpty(assignedServiceBm)){
							assignedServiceBm.doctorId = config.doctorCode;
							assignedServiceBm.patientId = config.patientId;
							assignedServiceBm.referenceNumber = config.appointmentNumber;
							assignedServiceBm.referenceType = configs.referenceTypeForOPD;
							assignedServiceBm.createdBy = authorisedUser.userName;
						}
						
						var observationBM = {
							appointmentNumber:config.appointmentNumber,
							doctor:{code: config.doctorCode,description: null},
							observationText: formValues['observationText']
						}
						
						observationBMList.push(observationBM);
						
						if( prescriptionRows.length == 0  && 
								clinicalPrescriptionRows.length == 0 &&
							Ext.isEmpty(formValues['observationText'])){
								Ext.Msg.show({
									msg : msg.saveDoctorConsultationmsgs,
									icon: Ext.MessageBox.INFO,
									buttons : Ext.Msg.OK
								});	
						}
						else{	
						AppointmentManagementController.
							savePrescriptionAndObservation( 
									prescriptionBMList,
									observationBMList,
									assignedServiceBm,
									config.appointmentNumber,
									function(){
										Ext.ux.event.Broadcast.publish('closeDocConsultationDetailsPanel');
									});
						}			
					}
				}
			},
			{
				text:'Reset',
				cls:'x-btn-text-icon',
				icon:'images/delete.png',
				handler:function() {
					consultationDetailsMainPanel.getForm().reset();
					gridStore.removeAll();
//					clinicalprescriptionPanel.assignedServiceGridPnl.resetData();
					
					
				//For loading the actual data after reset the complete data.	
					gridStore.load({params:{start:0, limit:99}, arg:[config.appointmentNumber]});
					MedicalPrescriptionController.getObservations(config.appointmentNumber,0,999,null,
							function(observationValue){
								if(!Ext.isEmpty(observationValue.data)){
									observationFieldSet.items.items[0].setValue(observationValue.data[0].observationText);
								}
						}
					);
				}
			}
		]
									
	});
	
	return consultationDetailsMainPanel;
	
}

