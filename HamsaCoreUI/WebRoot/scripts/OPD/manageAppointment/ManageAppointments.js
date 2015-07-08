/**
 * @author Sandeep Kumar
 */

function getManageAppointmentsPnl() {
	
	loadBookingTypesCbx();
	loadAppointmentTypeCbx();
	getReferralTypes();
	loadEspecialityCbx();
	
	var fromDate = new Ext.form.WTCDateField({
		name : 'appointmentFrom',
		fieldLabel : msg.appointmentfrom,
		anchor : '97%',
		listeners:{
			change: function( date, newValue, oldValue ){
				if(!Ext.isEmpty(date.getValue())){
					toDate.setMinValue(date.getValue());
				}
			}
		}

	});
	var toDate = new Ext.form.WTCDateField({
		name : 'appointmentTo',
		fieldLabel : msg.appointmentto,
		anchor : '97%',
		listeners:{
			change: function( date, newValue, oldValue ){
				if(!Ext.isEmpty(date.getValue())){
					fromDate.setMaxValue(date.getValue());
				}
			}
		}
	});
	var selectedAppointmentRecord;
	var manageAppointmentsSearchPnl = new Ext.form.FormPanel({
		layout : 'column',
		labelAlign : 'left',
		buttonAlign:'right',
		defaults:{
			columnWidth:.33,
			labelWidth:145//140
		},
		items : [
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
				items:[
					{
						xtype : 'optcombo',
						name : 'appointmentStatus',
						hiddenName:'appointmentStatus',
						fieldLabel : msg.appointmentstatus,
						store : loadAppointmentsStatusCbx(),
						displayField : 'description',
						valueField:'code',
						triggerAction : 'all',
						mode : 'local',
						forceSelection : true,
						anchor : '97%'
					}
				]
			},
			{
				layout:'form',
				items:[
					{
						xtype : 'textfield',
						name : 'patientName',
						fieldLabel : msg.patientname,
						vtype : 'alpha',
						anchor : '97%'
					}
				]
			},
			{
				layout:'form',
				items:[
					{
						xtype : 'numberfield',
						fieldLabel : msg.patientid,
						name : 'patientId',
						anchor : '97%'
					}
				]
			},
			{
				layout:'form',
				items:[
					{
						xtype : 'optcombo',
						name : 'bookingType',
						fieldLabel : msg.bookingtype,
						mode : 'local',
						store : loadBookingTypesCbx(),
						displayField : 'description',
						valueField : 'code',
						triggerAction : 'all',
						forceSelection : true,
						anchor : '97%'
					}
				]
			},
			{
				layout:'form',
				items:[
					{
						xtype : 'optcombo',
						name : 'doctorName',
						fieldLabel : msg.doctorsname,
						store : loadDoctorsCbx(),
						displayField : 'description',
						valueField : 'description',
						triggerAction : 'all',
						mode : 'local',
						forceSelection : true,
						anchor : '97%'
					}
				]
			},
			{
				layout:'form',
				items:[
					{
						xtype : 'timefield',
						name : 'timeFrom',
						fieldLabel : msg.timefrom,
						format : 'H:i:s',
						anchor : '97%'
					}
				]
			},
			{
				layout:'form',
				items:[
					{
						xtype : 'timefield',
						name : 'timeTo',
						fieldLabel : msg.timeto,
						anchor : '97%',
						format : 'H:i:s'
					}
				]
			}
		],
		buttons:[
			{
				cls : 'x-btn-text-icon',
				icon : 'images/find.png',
				text : msg.btn_search,
				tooltip : msg.findappointment,
				handler : function() {

					var formValues = manageAppointmentsSearchPnl
							.getForm().getValues();
					if(manageAppointmentsSearchPnl.getForm().isValid()){
						manageAppointmentDataStore.removeAll();
						manageAppointmentDataStore.load({
									params : {
										start : 0,
										limit : 10
									},
									arg : [
										getStringAsWtcDateFormat(formValues['appointmentFrom']),
										getStringAsWtcDateFormat(formValues['appointmentTo']),
										formValues['patientId'],
										formValues['patientName'],
										formValues['doctorName'],
										formValues['bookingType'],
										formValues['timeFrom'],
										formValues['timeTo'],
										formValues['appointmentStatus']
									]
								});
						fromDate.setMaxValue(null);
						toDate.setMinValue(null);
						
						getPanelInitialState();
					}
				}
			},
			{
				text:'Reset',
				iconCls : 'cancel_btn',
				handler:function(){
					manageAppointmentsSearchPnl.getForm().reset();
					var formValues = manageAppointmentsSearchPnl.getForm().getValues();
		 			manageAppointmentDataStore.removeAll();
		 			manageAppointmentDataStore.load({params:{start:0, limit:10}, 
		 							arg:[	getStringAsWtcDateFormat(formValues['appointmentFrom']),
											getStringAsWtcDateFormat(formValues['appointmentTo']),
											formValues['patientId'],
											formValues['patientName'],
											formValues['doctorName'],
											formValues['bookingType'],
											formValues['timeFrom'],
											formValues['timeTo'],
											formValues['appointmentStatus']]});
					getPanelInitialState();
				}
			}
		]
	});

	var manageAppointmentGridChk = new Ext.grid.CheckboxSelectionModel({
		 listeners:{
			rowselect : function( selectionModel, rowIndex, record){
				var selectedRows = selectionModel.getSelections();
				handleButtonState(toolBar,selectedRows);
			},
			rowdeselect : function( selectionModel, rowIndex, record){
				editBtn.disable();
				generateVisitSlipBtn.disable();
				generateReceiptBtn.disable();
				viewBtn.disable();
				deleteBtn.disable();
				viewTreatmentDetailsBtn.disable();
				addAdmissionOrderBtn.disable();
				generateReceiptSlipBtn.disable();
				rescheduleBtn.disable();
				cancelBtn.disable();
				followUpBtn.disable();
			}
		}
	});
	 
	var pagingBar = new Ext.WTCPagingToolbar({
            store: manageAppointmentDataStore,
            displayInfo: true,
        	displayMsg: 'Displaying appointments {0} - {1} of {2}',
        	emptyMsg: "No appointments to display"
	});
	
	var addBtn = new Ext.Toolbar.Button({
		cls : 'x-btn-text-icon',
		text : msg.add,
		icon : 'images/add.png',
		handler : function() {
			var newAppointmentPnl = new newAppointmentPanel( {
				isPopup:true
			} );
			
			var appointmentPanel = newAppointmentPnl.loadPanel();
			appointmentPanel.frame = true;
			appointmentPanel.title = "New appointment"; 
			appointmentPanel.closable = true;
			appointmentPanel.height = 420;
			
			
			
			var panel = layout.getCenterRegionTabPanel().add(appointmentPanel);
			
			appointmentPanel.defaultValues();
			
			layout.getCenterRegionTabPanel().setActiveTab( panel );
			layout.getCenterRegionTabPanel().doLayout();
			
			Ext.ux.event.Broadcast.subscribe('AppointmentAddWindowClose',function(){
//				layout.getCenterRegionTabPanel().remove( panel, true );
				layout.getCenterRegionTabPanel().setActiveTab( manageAppointmentsMainPnl );
				layout.getCenterRegionTabPanel().doLayout();
				manageAppointmentGridPnl.getStore().reload();
			}, this, null, appointmentPanel);
		}
	});
	
	var followUpBtn = new Ext.Toolbar.Button({
		cls : 'x-btn-text-icon',
		text : 'Take FollowUp',
		disabled:true,
		icon : 'images/add.png',
		handler : function() {
			var row = manageAppointmentGridPnl.getSelectionModel().getSelections();
			if(row[0].data.appointmentStatusCode == 'CAPTURED' && row[0].data.appointmentTypeCode == 'PRIMARY'){
				var patientId = parseInt(row[0].data.patientId);
				var docotorId = parseInt(row[0].data.primaryDoctorCode);
				var appointmentDate = new Date(row[0].data.appointmentDate);
				var appointmentNo = parseInt(row[0].data.appointmentNumber);
				
				AppointmentManager.getNumberOfFollowUps(patientId,docotorId,appointmentNo,appointmentDate
											,function(followupDays){
					if(followupDays>0){
						var newAppointmentPnl = new newAppointmentPanel( {
							isPopup:true
						} );
						
						var appointmentPanel = newAppointmentPnl.loadPanel();
						appointmentPanel.frame = true;
						appointmentPanel.title = "New appointment"; 
						appointmentPanel.closable = true;
						appointmentPanel.height = 420;
						
						
						
						var panel = layout.getCenterRegionTabPanel().add(appointmentPanel);
						var row = manageAppointmentGridPnl.getSelectionModel().getSelections();
						
						var config = {
								 mode : 'followUpData',
								 firstName : row[0].data.patientName,
								 patientId : row[0].data.patientId,
								 appointmentNumber : row[0].data.appointmentNumber,
//								 appointmentDate : row[0].data.appointmentDate,
//								 startTime : row[0].data.appointmentStartTime,
//								 endTime : row[0].data.appointmentEndTime,
								 speciality : row[0].data.speaciality,
								 primaryDoctor : row[0].data.primaryDoctor,
								 patientFirstName : row[0].data.patientFirstName,
								 patientMiddleName : row[0].data.patientMiddleName,
								 patientLastName : row[0].data.patientLastName,
								 speacialityCode : row[0].data.speacialityCode,
								 primaryDoctorCode : row[0].data.primaryDoctorCode,
								 bookingType : row[0].data.bookingType,
								 Phoneno : row[0].data.phoneNo,
								 Email : row[0].data.emailId,
								 bookingTypeCode : row[0].data.bookingTypeCode,
								 appointmentStatus : row[0].data.appointmentStatus,
								 appointmentStatusCode : row[0].data.appointmentStatusCode,
								 rosterCode: row[0].data.rosterCode,
								 appointmentTypeCode: row[0].data.appointmentTypeCode,
								 appointmentType: row[0].data.appointmentType,
								 referredBy: row[0].data.referredBy,
								 referredByCode: row[0].data.referredByCode,
								 referralType: row[0].data.referralType,
								 referralTypeCode: row[0].data.referralTypeCode,
								 remarks: row[0].data.remarks
//								 appointmentFee: row[0].data.amount
							};
						
					    newAppointmentPnl.loadPatientData(config);
						
						layout.getCenterRegionTabPanel().setActiveTab( panel );
						layout.getCenterRegionTabPanel().doLayout();
						
						Ext.ux.event.Broadcast.subscribe('AppointmentAddWindowClose',function(){
//						layout.getCenterRegionTabPanel().remove( panel, true );
							layout.getCenterRegionTabPanel().setActiveTab( manageAppointmentsMainPnl );
							layout.getCenterRegionTabPanel().doLayout();
							manageAppointmentGridPnl.getStore().reload();
						}, this, null, appointmentPanel);

					}else{
						Ext.Msg.alert('Alert','You are not eligible for Followup');
					}
				}
			);
				
							
			}else{
				Ext.Msg.alert('Alert','You are not eligible for Followup');
			}
	}
		
	});
	
	var editBtn = new Ext.Toolbar.Button({
		cls : 'x-btn-text-icon',
		text : msg.edit,
		icon : 'images/pencil.png',
		disabled:true,
		handler : function() {
			handleMangApptViewModifyBtn( 
				manageAppointmentGridPnl.getSelectionModel(), 
				false, 
				manageAppointmentsMainPnl);
			
			getPanelInitialState();
		}
	});
	
	var viewBtn = new Ext.Toolbar.Button({
		cls : 'x-btn-text-icon',
		icon : 'images/zoom.png',
		disabled:true,
		text : msg.view,
		handler : function() {
			handleMangApptViewModifyBtn( 
				manageAppointmentGridPnl.getSelectionModel(), 
				true, 
				manageAppointmentsMainPnl);
			
			getPanelInitialState();
		}
	});
	
	var deleteBtn = new Ext.Toolbar.Button({
		cls : 'x-btn-text-icon',
		text : msg.del,
		disabled:true,
		icon : 'images/delete.png',
		handler : function() {
		
			var AppointmentBM = function( appointmentNo ){
				this.appointmentNumber = appointmentNo;
			};
			
			var rows = manageAppointmentGridPnl.getSelectionModel().getSelections();
			var appointmentList = [];
			
			for( var rowCount = 0 ; rowCount < rows.length ; rowCount++){
				appointmentList[rowCount]  = new AppointmentBM( rows[rowCount].data.appointmentNumber );
			}
			
			Ext.Msg.show({
				msg: "Do you delete selected entries ?",
				icon : Ext.MessageBox.QUESTION,
				buttons: Ext.Msg.YESNO,
				fn: function( btnValue ){
					if( btnValue == configs.yes ){
						AppointmentManagementController.deleteAppointments( 
							appointmentList , 
							function(){
								for( var rowCount = 0 ; rowCount < rows.length ; rowCount++){
									manageAppointmentGridPnl.stopEditing();
									manageAppointmentGridPnl.getStore().remove(rows[rowCount]);
								}
								
								getPanelInitialState();
						});
					}
		   		}
			});
		}
	});
	
	var generateVisitSlipBtn = new Ext.Button({
		iconCls : 'print_btn',
		text : "Visit Slip",
		scope:this,
		disabled:true,
		handler : function() {
			var appointmentNumber = manageAppointmentGridPnl.
									getSelectionModel().
									getSelections()[0].data.appointmentNumber;
									
			AppointmentManager.generateVisitSlip(
				parseInt(appointmentNumber), 
				function(reportURL){
					window.open(getBaseURL() + reportURL);
		        });
		}
	});
	
	var generateReceiptSlipBtn = new Ext.Button({
		iconCls : 'print_btn',
		text : "Receipt slip",
		scope:this,
		disabled:true,
		handler : function() {
			var appointmentNumber = manageAppointmentGridPnl.
									getSelectionModel().
									getSelections()[0].data.appointmentNumber;
									
			AppointmentManager.generatePatientConsultationReceiptSlip(
				parseInt(appointmentNumber), 
				function(reportURL){
		       		window.open(getBaseURL() + reportURL);
		        });
		}
	});
	var generateReceiptBtn = new Ext.Button({
		iconCls:'money-icon',
		text:'Issue receipt',
		scope: this,
		disabled: true,
		handler: function(){
			var config = getManageAppointmenConfig( manageAppointmentGridPnl.getSelectionModel() );
			var receiptConfig = {selectedAccountHolderId : config.patientId,makeReadOnly : true};
			showReceiptWindow( receiptConfig );
		}
	});
	
	var rescheduleBtn = new Ext.Button({
		icon : 'images/pencil.png',
		text : msg.btn_reschedule,
		disabled: true,
		handler : function() {
		
			var rowCount = manageAppointmentGridPnl.getSelectionModel().getCount();
		
			if (rowCount === 1) {
				var config = getManageAppointmenConfig( manageAppointmentGridPnl.getSelectionModel() );
				var reschedulePanel = new OPD.manageAppointment.RescheduleAppointmentPanel(config); 
                 	
             	var panel = layout.getCenterRegionTabPanel().add({
					frame:true,
					title : msg.rescheduleappointment, 
					closable : true,
					height : 420,
					items : [ reschedulePanel.loadPanel() ],
					listeners:{
						beforeshow:function(comp){
							reschedulePanel.loadData(config);
						}
					}
				});
					
				layout.getCenterRegionTabPanel().setActiveTab( panel );
				layout.getCenterRegionTabPanel().doLayout();
				
				Ext.ux.event.Broadcast.subscribe('closeRescheduleWindow',function(){
					layout.getCenterRegionTabPanel().remove( panel, true );
					layout.getCenterRegionTabPanel().setActiveTab( manageAppointmentsMainPnl );
					layout.getCenterRegionTabPanel().doLayout();
					getPanelInitialState();
				}, this, null, manageAppointmentsMainPnl);
					
			}
		}
	});
	
	var cancelBtn = new Ext.Button({
		cls : 'x-btn-text-icon',
		icon : 'images/cancel.png',
		text : 'Cancel',
		disabled: true,
		handler : function() {
			var rowCount = manageAppointmentGridPnl.getSelectionModel().getCount();
		
			if (rowCount === 1) {
				var config = getManageAppointmenConfig( manageAppointmentGridPnl.getSelectionModel() );
				
				var canclePanel = new OPD.manageAppointment.CancelAppointmentPanel(config); 
				var cancleAppointmemntWindow = new Ext.Window({
						title:msg.cancleappointment,
						modal:true,
						height:'30%',
						width:'60%',
						resizable:true,
						items:canclePanel.loadPanel(),
						listeners:{
							beforeshow:function(comp){
								canclePanel.loadData(config);
							}
						}
					});
					
					cancleAppointmemntWindow.show();
					Ext.ux.event.Broadcast.subscribe('closeCancelWindow', function(){
						cancleAppointmemntWindow.close();
						getPanelInitialState();
					}, this, null, manageAppointmentsMainPnl);
				
			}
		}
	});
	
	var viewTreatmentDetailsBtn = new Ext.Button({
		cls : 'x-btn-text-icon',
		icon : 'images/zoom.png',
		text : msg.viewtreatmentdetails,
		disabled:true,
		handler : function() {
			var selectedRowModel = manageAppointmentGridPnl.getSelectionModel();
			var rowCount =  selectedRowModel.getCount();
			
			if(rowCount === 1){
				
				var selectedRow = selectedRowModel.getSelections();
				var selectedRowData = selectedRow[0].data;
				
				var treatmentDetailsPanel = getViewTreatmentDetailsMainPnl({
											 mode:'edit',
											 selectedPatientName: selectedRowData.patientName ,
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
	
	var addAdmissionOrderBtn = new Ext.Button({
		cls : 'x-btn-text-icon',
		icon : 'images/add.png',
		text : msg.addAdmissionOrder,
		disabled:true,
		handler : function() {
			var selectedRowModel = manageAppointmentGridPnl.getSelectionModel();
			var rowCount =  selectedRowModel.getCount();
			
			if(rowCount === 1){
				
				var selectedRow = selectedRowModel.getSelections();
				var selectedRowData = selectedRow[0].data;
				
				var patientInfo = {
			        patientId: selectedRowData.patientId,
			        orderTypeCd: configs.orderStatus_Admission,
			        doctorId: selectedRowData.primaryDoctorCode
				};
				OrderManager.getOrderTypeHasAttribute(configs.orderStatus_Admission, 0 , 999 , configs.orderBy ,function(list){ 
					showAddEditDoctorOrderWindow({
												 mode:configs.edit,
												 title:msg.addAdmissionOrder,
												 source: configs.appointment,
												 values: patientInfo,
												 attributeList : list.data,
												 hideServiceSelectionFieldSet:true
											});
					
					});
			/*	Ext.ux.event.Broadcast.subscribe('AppointmentAddWindowClose',function(){
				layout.getCenterRegionTabPanel().remove( panel, true );
				layout.getCenterRegionTabPanel().setActiveTab( manageAppointmentsMainPnl );
				layout.getCenterRegionTabPanel().doLayout();
				manageAppointmentGridPnl.getStore().reload();
			});*/
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
	
	if(getIntegratedIPD() == 'Y'){
		this.toolbarItems = [
				addBtn,'-',
				editBtn,'-', 
				viewBtn,'-',
				deleteBtn,'-',
				generateVisitSlipBtn,'-',
				generateReceiptSlipBtn,'-',
				viewTreatmentDetailsBtn,'-',
				addAdmissionOrderBtn,'-',
				generateReceiptBtn,'-',
				rescheduleBtn,'-',
				cancelBtn,
				followUpBtn
			];
	}else{
		this.toolbarItems = [
				addBtn,'-',
				editBtn,'-', 
				viewBtn,'-',
				deleteBtn,'-',
				generateVisitSlipBtn,'-',
				generateReceiptSlipBtn,'-',
				viewTreatmentDetailsBtn,'-',
				generateReceiptBtn,'-',
				rescheduleBtn,'-',
				cancelBtn,
				followUpBtn
			];
	}
	
	var toolBar = new Ext.Toolbar({
		items:this.toolbarItems
	});
	var manageAppointmentGridPnl = new Ext.grid.GridPanel({
		autoScroll : true,
		stripeRows : true,
		height : 305,
		border : false,
		frame : false,
		width:'100%',
		viewConfig:{forceFit:true},
		sm: manageAppointmentGridChk, 
		ds : manageAppointmentDataStore,
		cm : new Ext.grid.ColumnModel([manageAppointmentGridChk, {
					header : "S.No.",
					width : 30,
					sortable : true,
					hidden: true,
					dataIndex : 'serialNo'
				}, {
					header : "Appointment No.",
					width : 50,
					sortable : true,
					dataIndex : 'appointmentNumber'
				}, {
					header : "Patient Name",
					width : 100,
					sortable : true,
					dataIndex : 'patientName'
				}, {
					header : "Patient First Name",
					width : 150,
					hidden : true,
					sortable : true,
					dataIndex : 'patientFirstName'
				}, {
					header : "Patient Middle Name",
					width : 150,
					hidden : true,
					sortable : true,
					dataIndex : 'patientMiddleName'
				}, {
					header : "Patient Last Name",
					width : 150,
					hidden : true,
					sortable : true,
					dataIndex : 'patientLastName'
				}, {
					header : "Patient ID",
					width : 60,
					sortable : true,
					dataIndex : 'patientId'
				}, {
					header : "Appointment Date",
					dataIndex : 'appointmentDate',
					renderer : Ext.util.Format.dateRenderer('d/m/Y')
				}, {
					header : "From Time",
					width : 70,
					sortable : true,
					dataIndex : 'appointmentStartTime'
				}, {
					header : "To Time",
					width : 50,
					sortable : true,
					dataIndex : 'appointmentEndTime'
				}, {
					header : "Appointment Status",
					width : 140,
					sortable : true,
					dataIndex : 'appointmentStatus'
				}, {
					header : "Appointment Status Code",
					width : 100,
					hidden : true,
					sortable : true,
					dataIndex : 'appointmentStatusCode'
				}, {
					header : "Amount",
					width : 50,
					sortable : true,
					dataIndex : 'amount'
				}, {
					header : "Speaciality Name",
					width : 100,
					sortable : true,
					dataIndex : 'speaciality'
				}, {
					header : "Speaciality Code",
					width : 125,
					hidden : true,
					sortable : true,
					dataIndex : 'speacialityCode'
				}, {
					header : "Booking Type",
					width : 75,
					sortable : true,
					dataIndex : 'bookingType'
				}, {
					header : "Booking Type Code",
					width : 100,
					hidden : true,
					sortable : true,
					dataIndex : 'bookingTypeCode'
				}, {
					header : "Doctor Name",
					width : 100,
					sortable : true,
					dataIndex : 'primaryDoctor'
				}, {
					header : "Doctor Code",
					width : 75,
					hidden : true,
					sortable : true,
					dataIndex : 'primaryDoctorCode'
				},{
					header : "Roster Code",
					width : 100,
					hidden : true,
					sortable : true,
					dataIndex : 'rosterCode'
				},{
					header : "Appointment Remarks",
					width : 100,
					hidden : true,
					sortable : true,
					dataIndex : 'remarks'
				}]),

		bbar : pagingBar,
		tbar : toolBar,
		listeners:{
			cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
				var selectedAppointmentRecords = thisGrid.getSelectionModel().getSelections();
				handleButtonState(toolBar , selectedAppointmentRecords);
			} 
		}
	});
				
		
	var manageAppointmentsMainPnl = new Ext.Panel({
		autoHeight : true
//		style: 'padding-top:5px;'
	});

	manageAppointmentsMainPnl.add(manageAppointmentsSearchPnl,
			manageAppointmentGridPnl);
	
			// initially loading 10 records of the grid panel.
	manageAppointmentGridPnl.on("render", function(thisForm){
			manageSearchBtnHandler(manageAppointmentsSearchPnl);
		}, this);
	
	
	var getPanelInitialState = function(){
		editBtn.disable();
		viewBtn.disable();
		generateReceiptBtn.disable();		
		deleteBtn.disable();
		generateVisitSlipBtn.disable();
		generateReceiptSlipBtn.disable();
		viewTreatmentDetailsBtn.disable();
		rescheduleBtn.disable();
		cancelBtn.disable();
		addAdmissionOrderBtn.disable();
		followUpBtn.disable();
		manageAppointmentGridPnl.getSelectionModel().clearSelections();
	};
	
	var handleButtonState = function( toolBar , gridRows){
		var buttons = toolBar.items.items;
		
		if( gridRows.length == 1 && ( gridRows[0].data.appointmentStatusCode == "CONFIRMED" || gridRows[0].data.appointmentStatusCode == "TOVISIT" 
								||gridRows[0].data.appointmentStatusCode == "VISITED"  )){
			for(var i = 0; i < buttons.length ; i++){
				if(buttons[i].text != msg.viewtreatmentdetails && buttons[i].text != msg.addAdmissionOrder){
					buttons[i].enable();
				}
				else{
					buttons[i].disable();
				}
				
			}
		}else if(gridRows.length == 1 && (gridRows[0].data.appointmentStatusCode != "TOVISIT" && gridRows[0].data.appointmentStatusCode != "CONFIRMED" 
												&&gridRows[0].data.appointmentStatusCode != "VISITED")){
			for(var i = 0; i < buttons.length ; i++){
				if(buttons[i].text != msg.add){
					buttons[i].disable();
				}
				
				if(buttons[i].text == msg.viewtreatmentdetails || buttons[i].text == msg.addAdmissionOrder ){
					buttons[i].enable();
					rescheduleBtn.disable();
					cancelBtn.disable();
				}
				
				if(buttons[i].text == msg.view || buttons[i].text == "Receipt slip" ||  buttons[i].text == "Visit Slip" 
 						|| buttons[i].text == 'Issue receipt' ){
					buttons[i].enable();
					rescheduleBtn.disable();
					cancelBtn.disable();
				}
			}
		}else if( gridRows.length > 1){
			var apptStatus = null; 
			for(var i = 0 ; i < gridRows.length ; i++){
				if( gridRows[i].data.appointmentStatusCode != "CONFIRMED" || gridRows[0].data.appointmentStatusCode != "TOVISIT" 
													||gridRows[0].data.appointmentStatusCode == "VISITED"){
					apptStatus = gridRows[i].data.appointmentStatusCode;
					break;
				}
			}
			
			if( apptStatus == null ){
				for(var j = 0; j < buttons.length ; j++){
					if( buttons[j].text == msg.add ||
						buttons[j].text == msg.del ){
						buttons[j].enable();
						rescheduleBtn.disable();
						cancelBtn.disable();
					}else{
						buttons[j].disable();
					}
				}
			}else if( apptStatus != "CONFIRMED" || apptStatus != "TOVISIT" || apptStatus != "VISITED"){
				for(var j = 0; j < buttons.length ; j++){
					if( buttons[j].text == msg.add ){
						buttons[j].enable();
					}else{
						buttons[j].disable();
					}
				}
			}
		}
		if( gridRows.length == 1 && ( gridRows[0].data.appointmentStatusCode == "CAPTURED" )){
			editBtn.enable();
			followUpBtn.enable();
			rescheduleBtn.disable();
			cancelBtn.disable();
		}
	};
	
	manageAppointmentsMainPnl.on('destroy',function( comp ){
		InstanceFactory.removeInstance( comp.windowCode );
	},this);

	
	return manageAppointmentsMainPnl;
}
function manageSearchBtnHandler(manageAppointmentsSearchPnl){
	var formValues = manageAppointmentsSearchPnl.getForm().getValues();
		manageAppointmentDataStore.removeAll();
		manageAppointmentDataStore.load({
					params : {
						start : 0,
						limit : 10
					},
					arg : [
						getStringAsWtcDateFormat(formValues['appointmentFrom']),
						getStringAsWtcDateFormat(formValues['appointmentTo']),
						formValues['patientId'],
						formValues['patientName'],
						formValues['doctorName'],
						formValues['bookingType'],
						formValues['timeFrom'],
						formValues['timeTo'],
						formValues['appointmentStatus']
					]
				});
}

function handleMangApptViewModifyBtn( selectedRowModel , viewMode, manageAppointmentsMainPnl ) {
	
	var rowCount = selectedRowModel.getCount();
	var row = selectedRowModel.getSelections();

	if (rowCount === 1) {

		var appointmentNo = row[0].data.appointmentNumber;

		var viewModifyAppointmentRecord = Ext.data.Record.create([
				{ name : "firstName", type : "string" }, 
				{ name : "middleName", type : "string" }, 
				{ name : "lastName", type : "string" }, 
				{ name : "patientId", type : "int" }, 
				{ name : "appointmentNumber", type : "int" }, 
				{ name : "appointmentDate", type : "date" }, 
				{ name : "appointmentStartTime", type : "string" },
				{ name : "appointmentEndTime", type : "string" }, 
				{ name : "appointmentStatus", mapping : 'appointmentStatus.description', type : "string" }, 
				{ name : "appointmentStatusCode", mapping : 'appointmentStatus.code', type : "string" }, 
				{ name : "speaciality", mapping : 'speaciality.description', type : "string" }, 
				{ name : "speacialityCode", mapping : 'speaciality.code', type : "string" }, 
				{ name : "bookingType", mapping : 'bookingType.description', type : "string" }, 
				{ name : "bookingTypeCode", mapping : 'bookingType.code', type : "string" }, 
				{ name : "phone", mapping : 'phone', type : "string" }, 
				{ name : "email", mapping : 'email', type : "string" }, 
				{ name : "primaryDoctor", mapping : 'primaryDoctor.description', type : "string" }, 
				{ name : "primaryDoctorCode", mapping : 'primaryDoctor.code', type : "string" }, 
				{ name : "patientCameToVisit", type : "bool" }, 
				{ name : "patientVisitedConsultant", type : "bool" }, 
				{ name : "consultantHasSeenPatient", type : "bool" }, 
				{ name : "appointmentRemarks", type : "string" }, 
				{ name : "bookingDate", type : "date" },
				{ name : "appointmentType", mapping : 'appointmentType.description', type : "string" }, 
				{ name : "appointmentTypeCode", mapping : 'appointmentType.code', type : "string" }, 
				{ name : "referredBy", mapping : 'referredBy.description', type : "string" }, 
				{ name : "referredByCode", mapping : 'referredBy.code', type : "string" }, 
				{ name : "referralType", mapping : 'referralType.description', type : "string" }, 
				{ name : "referralTypeCode", mapping : 'referralType.code', type : "string" }, 
				{ name : "rosterCode", type : "int" }]);

		var viewModifyAppointmentDataStore = new Ext.data.Store({
			proxy : new Ext.data.DWRProxy(AppointmentManagementController.getAppointmentDetails, true),
			reader : new Ext.data.ListRangeReader({
						id : 'id',
						totalProperty : 'totalSize'
					}, viewModifyAppointmentRecord),
			remoteSort : true
		});

		viewModifyAppointmentDataStore.load({
					params : {
						start : 0,
						limit : 5
					},
					arg : [appointmentNo]
				});
				
		
		viewModifyAppointmentDataStore.on({
					'load' : {
						fn : function() {
							var appointmentDetails = viewModifyAppointmentDataStore.getAt(0).data;

							var config = {
								mode: 'edit',
								flag: viewMode,
								speacialityCode:appointmentDetails.speacialityCode,
								speacialityName:appointmentDetails.speaciality,
								lastName: appointmentDetails.lastName,
								middleName: appointmentDetails.middleName,
								firstName: appointmentDetails.firstName,
								appointmentNo: appointmentDetails.appointmentNumber,
								consultantHasSeenPatient: appointmentDetails.consultantHasSeenPatient,
								patientVisitedConsultant: appointmentDetails.patientVisitedConsultant,
								patientCameToVisit: appointmentDetails.patientCameToVisit,
								appointmentStatusName: appointmentDetails.appointmentStatus,
								appointmentStatusCode: appointmentDetails.appointmentStatusCode,
								remarks: appointmentDetails.appointmentRemarks,
								endTime: appointmentDetails.appointmentEndTime,
								appointmentDate: appointmentDetails.appointmentDate,
								startTime: appointmentDetails.appointmentStartTime,
								bookingDate: appointmentDetails.bookingDate,
							 	patientName: appointmentDetails.firstName + " " + appointmentDetails.middleName + " " +  
							 				 appointmentDetails.lastName,
								doctorName: appointmentDetails.primaryDoctor,
								doctorCode: appointmentDetails.primaryDoctorCode,
								patientId: appointmentDetails.patientId,
								referralTypeCode: appointmentDetails.referralTypeCode,
								bookingTypeCode: appointmentDetails.bookingTypeCode,
								phone : appointmentDetails.phone,
								email : appointmentDetails.email,
								appointmentTypeCode: appointmentDetails.appointmentTypeCode,
								referredByCode: appointmentDetails.referredByCode
							};
//							if( config.patientVisitedConsultant == true ){
//								config.patientCameToVisit = true;
//							}
							var viewModifyAppointmentPnl = new OPD.manageAppointment.ViewModifyAppointmentPanel(config);
							
							var panel = layout.getCenterRegionTabPanel().add({
								frame:true,
								title : msg.modifyappointment, 
								closable : true,
								height : 420,
								items : [ viewModifyAppointmentPnl.loadPanel() ],
								listeners:{
									beforeshow:function(comp){
										viewModifyAppointmentPnl.loadData(config);
									}
								}
							});
								
							layout.getCenterRegionTabPanel().setActiveTab( panel );
							layout.getCenterRegionTabPanel().doLayout();
							
							Ext.ux.event.Broadcast.subscribe('closeViewModifyWindow',function(){
//								layout.getCenterRegionTabPanel().remove( panel, true );
								layout.getCenterRegionTabPanel().setActiveTab( manageAppointmentsMainPnl );
								layout.getCenterRegionTabPanel().doLayout();
							}, this, null, manageAppointmentsMainPnl);
							
							var form = viewModifyAppointmentPnl.viewModifyAppointmentFormPanel;
							if (viewMode) {
								panel.setTitle(msg.viewappointment);
//								getViewMode(form);
							}
						},
						scope : this
					}
				});
	}
}


function handleMangApptConfigureReminderBtn() {
	getManagePatientPanel(7);
}

var manageAppointmentRecordType = Ext.data.Record.create([
		{ name : "serialNo", type : "int" }, 
		{ name : "appointmentNumber", type : "int" }, 
		{ name : "patientName", type : "string" }, 
		{ name : "patientFirstName", type : "string" }, 
		{ name : "patientLastName", type : "string" }, 
		{ name : "patientMiddleName", type : "string" }, 
		{ name : "patientId", type : "int" }, 
		{ name : "appointmentDate", type : "date" }, 
		{ name : "appointmentStartTime", type : "string" }, 
		{ name : "appointmentEndTime", type : "string" }, 
		{ name : "appointmentStatus", mapping : 'appointmentStatus.description', type : "string" }, 
		{ name : "appointmentStatusCode", mapping : 'appointmentStatus.code', type : "string" }, 
		{ name : "amount", type : "string" }, 
		{ name : "speaciality", mapping : 'speaciality.description', type : "string" }, 
		{ name : "speacialityCode", mapping : 'speaciality.code', type : "string" }, 
		{ name : "bookingType", mapping : 'bookingType.description', type : "string" }, 
		{ name : "bookingTypeCode", mapping : 'bookingType.code', type : "string" }, 
		{ name : "primaryDoctor", mapping : 'primaryDoctor.description', type : "string" }, 
		{ name : "primaryDoctorCode", mapping : 'primaryDoctor.code', type : "string" },
		{ name : "appointmentType", mapping : 'appointmentType.description', type : "string" }, 
		{ name : "appointmentTypeCode", mapping : 'appointmentType.code', type : "string" }, 
		{ name : "referredBy", mapping : 'referredBy.description', type : "string" }, 
		{ name : "referredByCode", mapping : 'referredBy.code', type : "string" }, 
		{ name : "referralType", mapping : 'referralType.description', type : "string" }, 
		{ name : "referralTypeCode", mapping : 'referralType.code', type : "string" }, 
		{ name : "rosterCode", type : "int" },
		{ name : "remarks", type : "string" }]);

var manageAppointmentDataStore = new Ext.data.Store({
	proxy : new Ext.data.DWRProxy(
			AppointmentManagementController.getAppointments, true),
	reader : new Ext.data.ListRangeReader({id : 'id',totalProperty : 'totalSize'}, 
										  manageAppointmentRecordType),
	remoteSort : true
});

function reloadManageAppointmentDataStore() {
	manageAppointmentDataStore.removeAll();
	manageAppointmentDataStore.reload();
}

function getManageAppointmenConfig( gridSelectionModel ){
	
	var rowCount = gridSelectionModel.getCount();
	var row = gridSelectionModel.getSelections();
	// converting data field to string format. because in reschedule appointments we are using textfield for dispalying the appointment date
	var manageApptmntAppointmentDate = "";
	
	if ((row[0].data.appointmentDate != "")) {
			manageApptmntAppointmentDate = row[0].data.appointmentDate.format('d/m/Y');
	}
		
	var config = {
		 patientName : row[0].data.patientName,
		 patientId : row[0].data.patientId,
		 appointmentNo : row[0].data.appointmentNumber,
		 appointmentDate : row[0].data.appointmentDate,
		 fromTime : row[0].data.appointmentStartTime,
		 toTime : row[0].data.appointmentEndTime,
		 appointmentDateAsString: manageApptmntAppointmentDate,
		 speaciality : row[0].data.speaciality,
		 primaryDoctor : row[0].data.primaryDoctor,
		 patientFirstName : row[0].data.patientFirstName,
		 patientMiddleName : row[0].data.patientMiddleName,
		 patientLastName : row[0].data.patientLastName,
		 speacialityCode : row[0].data.speacialityCode,
		 primaryDoctorCode : row[0].data.primaryDoctorCode,
		 bookingType : row[0].data.bookingType,
		 bookingTypeCode : row[0].data.bookingTypeCode,
		 appointmentStatus : row[0].data.appointmentStatus,
		 appointmentStatusCode : row[0].data.appointmentStatusCode,
		 rosterCode: row[0].data.rosterCode,
		 appointmentTypeCode: row[0].data.appointmentTypeCode,
		 appointmentType: row[0].data.appointmentType,
		 referredBy: row[0].data.referredBy,
		 referredByCode: row[0].data.referredByCode,
		 referralType: row[0].data.referralType,
		 referralTypeCode: row[0].data.referralTypeCode,
		 remarks: row[0].data.remarks,
		 amount: row[0].data.amount
	};

	return config;
}
