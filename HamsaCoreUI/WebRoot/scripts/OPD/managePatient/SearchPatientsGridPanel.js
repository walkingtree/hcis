Ext.namespace("OPD.managePatient");

function showRegisterEditPatientWindow( parameters ) {
	var patientRegistrationPnl = 
		new OPD.registration.PatientRegistrationPanel(
				parameters.isPopUp,
				parameters.config, 
				parameters.currAddConfig, 
				parameters.emrgPrimaryAddConfig,
				parameters.emrgSecondaryAddConfig,
				parameters.premanentAddConfig,
				parameters.emrgPrimaryDetailsConfig,
				parameters.emrgSecondaryDetailsConfig);
	
	var panelToAdd = patientRegistrationPnl.getPanel();
	panelToAdd.frame=true;
	panelToAdd.title = parameters.title; 
	panelToAdd.closable = true;
	panelToAdd.height = 420;


	var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
		
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
	
	Ext.ux.event.Broadcast.subscribe('closeRegisterPatientWindow',function(){
//		layout.getCenterRegionTabPanel().remove( panel, true );
		Ext.ux.event.Broadcast.publish('closeRegisterPatientPnl');
	},this, null , parameters.mainThis.getPanel());
}

OPD.managePatient.SearchPatientsGridPanel =  Ext.extend(Object, {
	constructor : function(config) {
		
		var mainThis = this;
		
		this.recordType = Ext.data.Record.create( [ 
											{name :"patientId",type :"string"},
											{name :"name",type :"string"}, 
											{name :"firstName",type :"string"}, 
											{name :"middleName",type :"string"}, 
											{name :"lastName",type :"string"}, 
											{name :"age",type :"string"}, 
											{name :"lastVisitedDate",type :"string"}, 
											{name :"registrationDate",type :"date"}, 
											{name :"registrationStatus",type :"string"}, 
											{name :"registrationType",type :"string"}, 
											{name :"registrationTypeCode",type :"string"}, 
											{name :"others",type :"string"} 
											]
										);										
		
		this.dataStore = new Ext.data.Store({
			proxy :new Ext.data.DWRProxy(PatientManagementController.getPatientsDetails, true),
			reader :new Ext.data.ListRangeReader( {id :'id',totalProperty :'totalSize'}, this.recordType),
			remoteSort :true		
		});

		this.pagingBar = new Ext.WTCPagingToolbar({
				pageSize : 10,
				store : this.dataStore,
				displayInfo : true,
				displayMsg : 'Displaying patients {0} - {1} of {2}',
				emptyText : "No patients to display"
			});
			
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.generatePatientCardBtn.disable();
					mainThis.generateReceiptSlipBtn.disable();
					mainThis.generateAttendantCardBtn.disable();
					mainThis.viewAppointmentsBtn.disable();
					mainThis.patientHistoryBtn.disable();
					mainThis.transferEmergency2NormalBtn.disable();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.generatePatientCardBtn.disable();
					mainThis.generateReceiptSlipBtn.disable();
					mainThis.generateAttendantCardBtn.disable();
					mainThis.viewAppointmentsBtn.disable();
					mainThis.patientHistoryBtn.disable();
					mainThis.transferEmergency2NormalBtn.disable();
				}
			}
		});

		this.addBtn = new Ext.Toolbar.Button({
			iconCls : 'add_btn',
			text : msg.add,
			scope:this,
			handler : function() {
				this.addBtnClicked();
			}
		});

		this.editBtn = new Ext.Toolbar.Button({
			iconCls : 'edit_btn',
			text : msg.edit,
			scope:this,
			disabled:true,
			handler : function() {
				this.editBtnClicked();
			}
		});
		
		this.generatePatientCardBtn = new Ext.Toolbar.Button({
			iconCls : 'print_btn',
			text : "Registration Card",
			scope:this,
			disabled:true,
			handler : function() {
				var patientId = this.infoGrid.getSelectionModel().getSelections()[0].data.patientId;
				PatientManager.generatePatientCard(parseInt(patientId), function(reportURL){
									       		window.open(getBaseURL() + reportURL);
									       }
								       );
				
			}
		});
		
		this. generateReceiptSlipBtn = new Ext.Button({
			iconCls : 'print_btn',
			text : msg.receiptSlip,
			scope:this,
			disabled:true,
			handler : function() {
				var patientId = this.infoGrid.getSelectionModel().getSelections()[0].data.patientId;
				
				
			}
		});

		this.generateAttendantCardBtn = new Ext.Toolbar.Button({
			iconCls : 'print_btn',
			text : "Attendant Card",
			scope:this,
			disabled:true,
			handler : function() {
				var patientId = this.infoGrid.getSelectionModel().getSelections()[0].data.patientId;
				PatientManager.generateAttendantCard(parseInt(patientId), function(reportURL){
									       		window.open(getBaseURL() + reportURL);
									       }
								       );
				
			}
		});
		
		this.viewAppointmentsBtn = new Ext.Button({
			iconCls : 'view-icon',
			text :msg.viewAppointments,
			scope:this,
			disabled:true
		});
		
		this.transferEmergency2NormalBtn = new Ext.Button({
			text :msg.transferemergency2Normal,
			scope:this,
			disabled:true
		});
		this.patientHistoryBtn = new Ext.Button({
			text :msg.viewHistory,
			scope:this,
			disabled:true,
			iconCls:'view-icon',
			handler: function(){
				this.patientHistoryBtnClicked();
				
			}
		});
		
		this.newAppointmentsBtn = new Ext.Button({
			iconCls : 'add_btn',
			text :msg.newAppointMent,
			scope:this,
			disabled:false,
			handler : function(){
				var newAppointmentPnl = new newAppointmentPanel( {
					isPopup:true
				} );
				
				var selectedRows = this.infoGrid.getSelectionModel().getSelections();
				var patientData = null;
				if( !Ext.isEmpty( selectedRows ) && selectedRows.length === 1){
					patientData = {
							patientId : selectedRows[0].data.patientId,
							firstName : selectedRows[0].data.firstName,
							middleName : selectedRows[0].data.middleName,
							lastName : selectedRows[0].data.lastName
						};
				}
				
				var appointmentPanel = newAppointmentPnl.loadPanel();
				newAppointmentPnl.loadPatientData( patientData );
				appointmentPanel.frame = true;
				appointmentPanel.title = "New appointment"; 
				appointmentPanel.closable = true;
				appointmentPanel.height = 420;
				
				
				var panel = layout.getCenterRegionTabPanel().add(appointmentPanel);
				
				appointmentPanel.defaultValues();
				
				layout.getCenterRegionTabPanel().setActiveTab( panel );
				layout.getCenterRegionTabPanel().doLayout();
				
//				Ext.ux.event.Broadcast.subscribe('AppointmentAddWindowClose',function(){
//					layout.getCenterRegionTabPanel().remove( panel, true );
////					layout.getCenterRegionTabPanel().setActiveTab( manageAppointmentsMainPnl );
//					layout.getCenterRegionTabPanel().doLayout();
////					manageAppointmentGridPnl.getStore().reload();
//				});
				
			}
		});

		
		var gridButtonsBar = new Ext.Toolbar({
			enableOverflow:true,
			autoScroll :true,
			items: [
					'-',this.addBtn,
					'-',this.editBtn,
					'-',this.generatePatientCardBtn,
//					'-',this.generateAttendantCardBtn,
					'-',this.generateReceiptSlipBtn,
					'-',this.newAppointmentsBtn,
					'-',this.viewAppointmentsBtn,
					'-',this.patientHistoryBtn/*,
					'-',this.transferEmergency2NormalBtn*/]
		});
		
		this.infoGrid = new Ext.grid.GridPanel({
				frame : false,
				stripeRows : true,
				height: 300,
				border : true,
				autoWidth : true,
				cloumnLines:true,
				store : this.dataStore,
				bbar : this.pagingBar,
				sm:this.gridChk,
				viewConfig:{
					forceFit : true
				},
				tbar : gridButtonsBar,
				columns : [this.gridChk, 
							{header :"Patient ID",dataIndex :'patientId'}, 
							{header :"Patients name",dataIndex :'name',width :175}, 
							{header :"First name",hidden :true,dataIndex :'firstName'}, 
							{header :"Middle name",hidden :true,dataIndex :'middleName'}, 
							{header :"Last name",hidden :true,dataIndex :'lastName'}, 
							{header :"Age",dataIndex :'age',width :75}, 
							{header :"Last visited",dataIndex :'lastVisitedDate',renderer :Ext.util.Format.dateRenderer('d/m/Y')}, 
							{header :"Registration date",dataIndex :'registrationDate',renderer :Ext.util.Format.dateRenderer('d/m/Y')}, 
							{header :"Registration status",dataIndex :'registrationStatus'}, 
							{header :"Registration type",dataIndex :'registrationType',width :135}, 
							{header :msg.phonenumber,dataIndex :'others',width :100} 
				]
			});
			
		Ext.ux.event.Broadcast.subscribe('loadPatientsGrid',function(){
			if(this.infoGrid.getStore().data.items == 0){
					this.infoGrid.getStore().load(
					{params:{start:0, limit:this.infoGrid.pageSize}, 
					 arg:[null, null, null, null, null,null, null, null, null, null,null]
					});
				}else{
					this.infoGrid.getStore().reload();
				}
			this.setGridButtonsInitialState();
		}, this, null, this.getPanel() );
	},
	
	addBtnClicked : function(){
		var mainThis =this;
		showRegisterEditPatientWindow({
						 mode:'add',
						 title: 'Register patient',
						 isPopUp : true,
						 mainThis : mainThis});
	},

	editBtnClicked : function(){
       var patientId = this.infoGrid.getSelectionModel().getSelections()[0].data.patientId;
       var mainThis = this;
       PatientManager.getPatientInfoDetailBM(
       		parseInt(patientId),
	        function ( patientInfoDetailBM ){
	        	mainThis.editBtnCallBack( patientInfoDetailBM );
	        });
	},
	setGridButtonsInitialState: function(){
		
		this.getEditBtn().disable();
		this.getPrintCardBtn().disable();
		this.getAttendantCardBtn().disable();
		this.viewAppointmentsBtn.disable();
		this.patientHistoryBtn.disable();
		this.transferEmergency2NormalBtn.disable();
	},
	
	editBtnCallBack : function( patientInfoDetailBM ){
	   var patientBM = patientInfoDetailBM;
	   
	   var allergiesCount = patientBM.patientAllergiesBMList.length;
	   var isPatientHaveAnyAllergies =  (allergiesCount == 0) ? false : true;

	   var immunizationsCount = patientBM.patientImmunizationsBMList.length;
	   var isPatientHaveAnyImmunizations =  (immunizationsCount == 0) ? false : true;

	   var config = {
         mode: 'edit',
         
         // Personal details tab
         registrationNumber : patientBM.personalDetailsBM.registrationNumber,
		 registrationDate : (null == patientBM.personalDetailsBM.registrationDate) ? null :patientBM.personalDetailsBM.registrationDate, 
         registrationType : (null == patientBM.personalDetailsBM.registrationType) ? null :patientBM.personalDetailsBM.registrationType.code,
         registrationStatus : (null == patientBM.personalDetailsBM.registrationStatus) ? null :patientBM.personalDetailsBM.registrationStatus.code,
         patientRating : (null == patientBM.personalDetailsBM.patientRating) ? null :patientBM.personalDetailsBM.patientRating.code,
         patientCategory : (null == patientBM.personalDetailsBM.patientCategory) ? null :patientBM.personalDetailsBM.patientCategory.code,
         
         title : (null == patientBM.personalDetailsBM.title) ? null :patientBM.personalDetailsBM.title.code,
         firstName : (null == patientBM.personalDetailsBM.firstName) ? null :patientBM.personalDetailsBM.firstName,
         middleName : (null == patientBM.personalDetailsBM.middleName) ? null :patientBM.personalDetailsBM.middleName,
         lastName : (null == patientBM.personalDetailsBM.lastName) ? null :patientBM.personalDetailsBM.lastName,
         dateOfBirth : (null == patientBM.personalDetailsBM.dateOfBirth) ? null :patientBM.personalDetailsBM.dateOfBirth,
         gender : (null == patientBM.personalDetailsBM.gender) ? null :patientBM.personalDetailsBM.gender.code,
         
         height : patientBM.personalDetailsAdditionalBM.height,
         weight : patientBM.personalDetailsAdditionalBM.weight,
         bloodGroup : (null == patientBM.personalDetailsAdditionalBM.bloodGroup) ? null :patientBM.personalDetailsAdditionalBM.bloodGroup.code,
         maritalStatus : (null == patientBM.personalDetailsAdditionalBM.maritalStatus) ? null :patientBM.personalDetailsAdditionalBM.maritalStatus.code,
         fatherHusband : patientBM.personalDetailsAdditionalBM.fatherHusband,

         religion : (null == patientBM.personalDetailsAdditionalBM.religion) ? null :patientBM.personalDetailsAdditionalBM.religion.code,
         motherTongue : (null == patientBM.personalDetailsAdditionalBM.motherTongue) ? null :patientBM.personalDetailsAdditionalBM.motherTongue.code,
         bloodDonorId : patientBM.personalDetailsAdditionalBM.bloodDonorId,
         idProof : (null == patientBM.personalDetailsAdditionalBM.idProof) ? null :patientBM.personalDetailsAdditionalBM.idProof.code,
         idNumber : patientBM.personalDetailsAdditionalBM.idNumber,
         idValidUpto : (null == patientBM.personalDetailsAdditionalBM.idValidUpto) ? null :patientBM.personalDetailsAdditionalBM.idValidUpto,
         nationality : (null == patientBM.personalDetailsAdditionalBM.nationality) ? null :patientBM.personalDetailsAdditionalBM.nationality.code,
         patientOccupation : patientBM.personalDetailsAdditionalBM.patientOccupation,
         monthlyIncome : patientBM.personalDetailsAdditionalBM.monthlyIncome,
         referredBy : (null == patientBM.personalDetailsAdditionalBM.referredBy) ? null :patientBM.personalDetailsAdditionalBM.referredBy.code,
         heightIndicator : ( null == patientBM.personalDetailsAdditionalBM.heightIndicator) ? null : patientBM.personalDetailsAdditionalBM.heightIndicator.code,
         weightIndicator : ( null == patientBM.personalDetailsAdditionalBM.weightIndicator ) ? null : patientBM.personalDetailsAdditionalBM.weightIndicator.code,
         
         // Other details tab
         smoking : patientBM.otherDetailsBM.smokingHabitFlag,
         drinking : patientBM.otherDetailsBM.drinksAlcohol,
         
         // Allergies tab
         isPatientHaveAnyAllergies : isPatientHaveAnyAllergies,
         patientAllergiesList : patientBM.patientAllergiesBMList,
         
         // Immunizations tab
         isPatientHaveAnyImmunizations : isPatientHaveAnyImmunizations,
         patientImmunizationsList : patientBM.patientImmunizationsBMList,
         
         imagePropertyBM : patientBM.personalDetailsBM.imagePopertyBM
         
       };
      
       var currAddConfig = {}; 
	   var premanentAddConfig = {};
	   var emrgPrimaryAddConfig = {};
	   var emrgSecondaryAddConfig = {};

	   var emrgPrimaryDetailsConfig = {};
	   var emrgSecondaryDetailsConfig = {};
      
       var contactDetails = patientBM.contacts.contactDetailList;
       for(var i =0; i<contactDetails.length; i++){
		   var tmpContact = contactDetails[i];
		   var tmpConfig = {
			 	 mode: 'edit',
			 	 addressType:'',
               	 selectedContactDetailsCode :tmpContact.contactDetailsCode,
				 selectedPersonelId :tmpContact.personelId,
				 selectedHouseNumber :tmpContact.houseNumber,
				 selectedStreet :tmpContact.street,
				 selectedCity :tmpContact.city,
				 selectedPincode : tmpContact.pincode,
				 selectedPhoneNumber :tmpContact.phoneNumber,
				 selectedMobileNumber :tmpContact.mobileNumber,
				 selectedFaxNumber :tmpContact.faxNumber,
				 selectedEmail :tmpContact.email,
				 selectedStayDuration :tmpContact.stayDuration,
				 selectedCountryCode : (null == tmpContact.country) ? null : tmpContact.country.code,
				 selectedCountry : (null == tmpContact.country) ? null : tmpContact.country.description,
				 selectedStateCode : (null == tmpContact.state) ? null : tmpContact.state.code,
				 selectedState : (null == tmpContact.state) ? null : tmpContact.state.description,
				 selectedContactType : (null == tmpContact.contactType) ? null : tmpContact.contactType,

				 selectedEmergencySalutationCode : (null == tmpContact.salutation) ? null :tmpContact.salutation.code,				 
				 selectedEmergencySalutation : (null == tmpContact.salutation) ? null :tmpContact.salutation.description,				 
               	 selectedEmergencyFirstName :tmpContact.firstName,
                 selectedEmergencyMiddleName : tmpContact.middleName,
				 selectedEmergencyLastName :tmpContact.lastName,
				 selectedEmergencyGenderCode : (null == tmpContact.gender) ? null :tmpContact.gender.code,	
				 selectedEmergencyGender : (null == tmpContact.gender) ? null :tmpContact.gender.description,	
				 selectedEmergencyRelationCode: (null == tmpContact.relationCode) ? null :tmpContact.relationCode.code,
				 selectedEmergencyRelation: (null == tmpContact.relationCode) ? null :tmpContact.relationCode.description,
				 selectedEmergencyPhoneNumber:tmpContact.phoneNumber,
				 selectedEmergencyMobileNumber: tmpContact.mobileNumber,
				 selectedEmergencyFaxNumber:tmpContact.faxNumber,
				 selectedEmergencyEmail: tmpContact.email
	       }
	       
	       if(tmpContact.contactType.code == "CURRENT"){
		       	tmpConfig.addressType = 'current';
		       	currAddConfig = tmpConfig;
	       }
	       
	       if(tmpContact.contactType.code == "PERMANENT"){
		       	tmpConfig.addressType = 'permanent';
		       	premanentAddConfig = tmpConfig;
	       }

//	       if(tmpContact.contactType.code == "EMERGENCY_PRIMARY"){
//		       	tmpConfig.addressType = 'emergency';
//		       	emrgPrimaryAddConfig = tmpConfig;
//		       	emrgPrimaryDetailsConfig = tmpConfig;
//	       }
//
//	       if(tmpContact.contactType.code == "EMERGENCY_SECONDARY"){
//		       	tmpConfig.addressType = 'emergency';
//		       	emrgSecondaryAddConfig = tmpConfig;
//		       	emrgSecondaryDetailsConfig = tmpConfig;
//	       }
       	}
       var mainThis = this;

	   showRegisterEditPatientWindow({
						 title: 'Edit patient',
						 isPopUp : true,
						 config : config,
						 currAddConfig : currAddConfig,
						 emrgPrimaryAddConfig : emrgPrimaryAddConfig,
						 emrgSecondaryAddConfig : emrgSecondaryAddConfig,
						 premanentAddConfig : premanentAddConfig,
						 emrgPrimaryDetailsConfig : emrgPrimaryDetailsConfig,
						 emrgSecondaryDetailsConfig : emrgSecondaryDetailsConfig,
						 mainThis : mainThis});
	},
	patientHistoryBtnClicked: function(){
		 var patientHistory = new OPD.treatmentHistory.PatientHistory.History();
		 var addPanel = layout.getCenterRegionTabPanel().add({
			frame:true,
			title : msg.viewpatienthistory, 
			closable : true,
			height : 420,
			items : [ patientHistory.historyTabPanel ],
			buttons:[
				{
					xtype:'button',
					iconCls:'cross_icon',
					text:msg.close,
					scope:this,
					handler: function(){
						Ext.ux.event.Broadcast.publish('closeTreatmentHistory');
						Ext.ux.event.Broadcast.publish('closeRegisterPatientPnl');
					}
				}
			]
			
		});
		
		layout.getCenterRegionTabPanel().setActiveTab( addPanel );
		 var patientId = this.infoGrid.getSelectionModel().getSelections()[0].data.patientId;
		 
		var prescriptionGrid = patientHistory.historyTabPanel.getPrescriptionGrid();
		prescriptionGrid.getStore().load({params:{start:0, limit:9999}, arg:[ patientId ]});
		
		var observationGrid =  patientHistory.historyTabPanel.getObservationGrid();
		observationGrid.getStore().load({params:{start:0, limit:9999}, arg:[ patientId ]});
		
		var clinicalPrescriptionGrid = patientHistory.historyTabPanel.getClinicalPrescriptionGrid();
		clinicalPrescriptionGrid.store.load({params:{start:0, limit:9999}, arg:[ patientId ]});
		
		layout.getCenterRegionTabPanel().doLayout();
		
	     Ext.ux.event.Broadcast.subscribe('closeTreatmentHistory',function(){
	     	var tmpAddPanel = addPanel;
			layout.getCenterRegionTabPanel().remove( tmpAddPanel, true );
			layout.getCenterRegionTabPanel().doLayout();
	     }, this, null, this.getPanel() );
	},
	getPanel : function() {
		return this.infoGrid;
	},
	
	getAddBtn : function(){
		return this.addBtn;
	},
	
	getEditBtn : function(){
		return this.editBtn;
	},
	
	getPrintCardBtn: function() {
		return this.generatePatientCardBtn;
	},

	getAttendantCardBtn: function() {
		return this.generateAttendantCardBtn;
	},
	
	getReceiptSlipBtn : function() {
		return this.generateReceiptSlipBtn;
	}
});
