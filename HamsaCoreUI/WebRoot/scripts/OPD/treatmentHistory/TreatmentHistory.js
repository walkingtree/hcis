/**
 * @author Vinay Kurudi
 */

 function getTreatmentHistoryMainPnl(){
 Ext.QuickTips.init();
 var selectedTreatmentHistoyRecord;	
 
 	var fromDate = new Ext.form.WTCDateField({
		fieldLabel: msg.treatmentfrom,
		name:'tretamentForm',
		anchor:'98%',
		listeners:{
			change: function( date, newValue, oldValue ){
				if(!Ext.isEmpty(date.getValue())){
					toDate.setMinValue(date.getValue());
				}
			}
		}
	});
	
	var toDate = new Ext.form.WTCDateField({
		fieldLabel:msg.treatmentto,
		name:'treatmentTo',
		anchor:'98%',
		listeners:{
			change: function( date , newValue, oldValue ){
				if(!Ext.isEmpty(date.getValue())){
					fromDate.setMaxValue(date.getValue());
				}
			}
		}
	});
	
 	var outPatientTreatmentHistorySearchPnl = new Ext.form.FormPanel({
		layout:'column',
		buttonAlign:'right',
		defaults:{ columnWidth:.3, labelAlign:'left',labelWidth:130},
		items:[
			{
				layout:'form',
				items:fromDate
			},
			{
				layout:'form',
				items:toDate
			},
			{
				layout:'form',
				labelWidth:65,
				items:[
					{
						xtype:'numberfield',
						fieldLabel:msg.patientid,
						name:'patientId',
						anchor:'90%'
					}
				]
			},
			{
				layout:'form',
				items:[
					{
						xtype:'textfield',
						fieldLabel:msg.patientname,
						name:'patientName',
						vtype:'alpha',
						anchor:'98%'
					}
				]
			},
			{
				layout:'form',
				items:[
					{
						xtype:'textfield',
						fieldLabel:msg.doctorsname,
						name:'doctorName',
						anchor:'98%'
					}
				]
/************************************************************************************/				
/*    This code is commented because this code ll be use in case of In Patient.     */
/************************************************************************************/	
				
//			},
//			{
//				layout:'form',
//				items:[
//					{
//						layout:'column',
//						defaults:{columnWidth:.48,labelWidth:.01},
//						items:[
//							{
//								layout:'form',
//								items:{
//									xtype:'checkbox',
//									boxLabel: msg.outpatient,
//									labelSeparator:'',
//									name:'outPatient'
//								}
//							},{
//								layout:'form',
//								items:{
//									xtype:'checkbox',
//									boxLabel: msg.inpatient,
//									labelSeparator:'',
//									name:'inPatient'
//								}
//							}
//						]
//					}
//				]
			}
		],
		buttons:[
			{
				xtype:'button',
				cls:'x-btn-text-icon',
				text:'Search',
	   			icon:'images/find.png',
	   			tooltip : msg.treatmenthistory,
				handler:function(){
					var formValues = outPatientTreatmentHistorySearchPnl.getForm().getValues();
					treatmentHistoryDataStore.load({params:{start:0, limit:10}, 
                            arg:[formValues ]});
                     fromDate.setMaxValue(null);
                     toDate.setMinValue(null);
                     
//                     treatmentHistoryGridPnl.getStore().on('load',function(){
//					      	if( treatmentHistoryGridPnl.getStore().getCount() > 0){
//					      		treatmentHistoryGridPnl.show();
//					      	}else{
//					      		treatmentHistoryGridPnl.hide();
//					      	
//					      	}
//					      },this);
				}
			},
			{
				xtype :'button',
				cls :'x-btn-text-icon',
				text :'Reset',
				icon :'images/arrow_undo.png',
				handler : function() {
					resetButtonClicked();
				}
			}
		]
	});
 	
	var trmntHistotyGridChk = new Ext.grid.CheckboxSelectionModel({
		listeners:{
			rowselect : function( selectionModel, rowIndex, record){
					viewTreatmentDetailsBtn.disable();
			},
			rowdeselect : function( selectionModel, rowIndex, record){
				viewTreatmentDetailsBtn.disable();
			}
		}
	});
	
	var bbar = new Ext.WTCPagingToolbar({
		store: treatmentHistoryDataStore,
    	displayMsg:"Displaying treatment details {0} - {1} of {2}",
    	emptyMsg:"No treatment details to display"
    });
    
	var viewTreatmentDetailsBtn = new Ext.Toolbar.Button({
		cls : 'x-btn-text-icon',
		icon : 'images/zoom.png',
		disabled:true,
		text : msg.viewtreatmentdetails,
		handler : function() {
			var selectedRowModel = treatmentHistoryGridPnl.getSelectionModel();
			var rowCount =  selectedRowModel.getCount();
			
			if(rowCount === 1){
				
				var selectedRow = selectedRowModel.getSelections();
				var selectedRowData = selectedRow[0].data;
				
				var treatmentDetailsPanel = getViewTreatmentDetailsMainPnl({
											 mode:'edit',
											 selectedPatientName: selectedRowData.name ,
				                             selectedPatientId: selectedRowData.patientId,
				                             selectedPrimaryDoctor: selectedRowData.primaryDoctor,
				                             selectedAppointmentDate: selectedRowData.appointmentDate,
				                             selectedAdmissiondate: selectedRowData.admissiondate,
				                             selectedDischargeDate: selectedRowData.dischargeDate,
				                             selectedAppointmentAgenda: selectedRowData.appointmentAgenda,
				                             selectedAppointmentRemarks: selectedRowData.appointmentRemarks,
				                             selectedPrimaryDoctorId: selectedRowData.primaryDoctorId,
				                             selectedDepartment: selectedRowData.department,
				                             selectedAppointmentNumber: selectedRowData.appointmentNumber
										});
				
				var x = layout.getCenterRegionTabPanel().add({
						frame:true,
						title : 'Treatment history details', 
						closable : true,
						height : 420,
						items : [treatmentDetailsPanel]
					}
				);
				layout.getCenterRegionTabPanel().setActiveTab(x);
				layout.getCenterRegionTabPanel().doLayout();
				
				
			 }
			 else{
			 	if(rowCount > 1){
					 Ext.Msg.alert('',msg.multiplerowselection);
			 	}else{
			  		Ext.Msg.alert('',msg.singleselection);
			 	}
			 }
		}
	});	
	
	var gridButtonsBar = new Ext.Toolbar({
		items: [viewTreatmentDetailsBtn]
	});
		
	var treatmentHistoryGridPnl = new Ext.grid.GridPanel({
		autoScroll:true,
		stripeRows:true,
		border:false,
		viewConfig:{forceFit:true},
		frame:false,
		height:300,
		sm:trmntHistotyGridChk,
		ds: treatmentHistoryDataStore,
		cm: new Ext.grid.ColumnModel ([
				trmntHistotyGridChk,
				{header: "Appointment number",width:50, sortable: true, dataIndex: 'appointmentNumber'},
				{header: "Patient ID", width:75,  sortable: true, dataIndex: 'patientId'},
				{header: "Patient Name",width:85,  sortable: true, dataIndex: 'name'},
				{header: "Primary Doctor Name",width:100,  sortable: true, dataIndex: 'primaryDoctor'},
				{header: "Primary Doctor Id",  hidden:true, sortable: true, dataIndex: 'primaryDoctorId'},
				{header: "Appointment Date",width:85,   sortable: true, dataIndex: 'appointmentDate', renderer: Ext.util.Format.dateRenderer('d/m/Y')},

// AdmisiionDate is not being used in case of OP But in the case of IP it ll be.
				
//			 	{header: "Admission Date", width:100, dataIndex: 'admissiondate',renderer: Ext.util.Format.dateRenderer('d/m/Y')},
				{header: "Reason for tratment", width:100,  sortable: true, dataIndex: 'appointmentAgenda'},
				{header: "Doctor remarks", width:85,  sortable: true, dataIndex: 'appointmentRemarks'},
				{header: "Specialty name",width:85,   sortable: true,dataIndex: 'specialty'},
				{header: "specialty id",width:85,   sortable: true, hidden:true, dataIndex: 'specialtyCode'}

//Discharge is not being used in case of OP But in the case of IP it ll be.

//				{header: "Discharge date",width:85,   sortable: true, dataIndex: 'dischargeDate',renderer: Ext.util.Format.dateRenderer('d/m/Y')}
		]),
		
		bbar:bbar,
		listeners: {
				cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
					selectedTreatmentHistoyRecord = thisGrid.getStore().getAt(rowIndex).data; 
					var selectedRows = thisGrid.getSelectionModel().getSelections();
					viewTreatmentDetailsBtn.disable();
					if( selectedRows.length == 1){
						viewTreatmentDetailsBtn.enable();
						
					} else if( selectedRows.length > 1){
						viewTreatmentDetailsBtn.disable();
					}
				}
			},
		tbar : gridButtonsBar
	});
	// initially loading data to the store.
	treatmentHistoryDataStore.load({params:{start:0, limit:10}, 
		                            arg:[{}]});
	var outPatientTreatmentHistoryMainPnl= new Ext.Panel({
		height:'100%',
		style:'padding-top:5px',
		items:[
				outPatientTreatmentHistorySearchPnl,
				treatmentHistoryGridPnl
			]
	});
	
	treatmentHistoryDataStore.on('load',function(){
		viewTreatmentDetailsBtn.disable();
	},this);
	
	function resetButtonClicked(){
		outPatientTreatmentHistorySearchPnl.getForm().reset();
		treatmentHistoryDataStore.load({params:{start:0, limit:10}, 
		                            arg:[{}]});
	}
	
	return outPatientTreatmentHistoryMainPnl;
 }
 
 var treatmentHistoryRecordType = Ext.data.Record.create([
 			{name:"serialNo", type:"int"},
		    {name:"patientId", type: "int"},
		    {name:"name", type: "string"},
			{name:"ioPatient", type: "string"},
			{name:"primaryDoctor", type: "string"},
			{name:"primaryDoctorId", type: "string"},
			{name:"appointmentDate", type:"date"},
			{name:"admissiondate", type:"date"},
			{name:"appointmentNumber", type:"int"},
			{name:"dischargeDate", type:"date"},
			{name:"appointmentAgenda",type:"string"},
			{name:"appointmentRemarks",type:"string"},
			{name:"specialty", type: "string"},
			{name:"specialtyCode", type: "string"}
		  ]);
		  
 var treatmentHistoryDataStore = new Ext.data.Store({
	   proxy: new Ext.data.DWRProxy(TreatmentHistoryController.getTreatmentDetails, true),
	   reader: new Ext.data.ListRangeReader( 
			{id:'id', totalProperty:'totalSize'}, treatmentHistoryRecordType),
	   remoteSort: true
 });
 
