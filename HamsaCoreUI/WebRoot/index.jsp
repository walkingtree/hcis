<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> HaMSa </title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="icon" type="image/png" href="images/WTClogo.png">

	<link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all.css" />
	<!--<link rel="stylesheet" type="text/css" href="extjs/resources/css/xtheme-hamsa.css" /> -->
	
	<!-- dash board css files start -->
	
	<link rel="stylesheet" type="text/css" href="scripts/dashboard/grouptabs/GroupTab.css" />
    <link rel="stylesheet" type="text/css" href="scripts/dashboard/grouptabs/Portal.css" />
    
    <!-- dash board css files end -->
    
	<style type="text/css">
        /* styles for iconCls */
        .x-icon-tickets {
            background-image: url('images/tickets.png');
            background-repeat: no-repeat;
        }
        .x-icon-subscriptions {
            background-image: url('images/subscriptions.png');
            background-repeat: no-repeat;
        }
        .x-icon-users {
            background-image: url('images/group.png');
            background-repeat: no-repeat;
        }
        .x-icon-templates {
            background-image: url('images/templates.png');
            background-repeat: no-repeat;
        }
    </style>
    
    <script src="extjs/adapter/ext/ext-base.js"></script>
    <script src="extjs/ext-all-debug.js"></script>
    
    <script src="dwrproxy.js" type="text/javascript"></script>
   
	<script src="extjs/lovcombo/js/Ext.ux.form.LovCombo.js"></script>

	<script src="scripts/utils/GroupHeaderPlugin.js"></script>
	<link rel="stylesheet" type="text/css" href="css/GroupHeaderPlugin.css" />
	
	<!--  CSS FILES START -->
	<link rel="stylesheet" type="text/css" href="css/hcis.css" />
	<link rel="stylesheet" type="text/css" href="css/userManager.css" />
	<link rel="stylesheet" type="text/css" href="css/Ext.ux.grid.RowActions.css" />
	<link rel="stylesheet" type="text/css" href="scripts/wtccomponents/bubblePanel/css/bubble.css" />
	<link rel="stylesheet" type="text/css" href="scripts/wtccomponents/wtccalendar/Calendar.css">
	<script type="text/javascript" src="scripts/wtccomponents/wtccalendar/Calendar.js"></script>
	<!-- CSS FILES END -->
	
	<!--  USER MANAGEMENT START -->
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/UserManagementController.js'></script>
	
	<script type="text/javascript" src="scripts/administration/userManagement/Messages.js"></script>
	
	<!-- <script type="text/javascript" src="scripts/Login.js"></script> -->
	<script type="text/javascript" src="scripts/WindowCodes.js"></script>
	<script type="text/javascript" src="scripts/login/FrontPage.js"></script>
	<script type="text/javascript" src="scripts/login/LoginForm.js"></script>
	<script type="text/javascript" src="scripts/login/TitleAndLoginForm.js"></script>
	
	<script type="text/javascript" src="scripts/ext-override.js"></script>
	<script type="text/javascript" src="scripts/utils/CookieUtil.js"></script>
	<script src="scripts/wtccomponents/Broadcast.js"></script>
	<script type="text/javascript" src="scripts/utils/Configuration.js"></script>
	<script type="text/javascript" src="scripts/wtccomponents/DWRTreeLoder.js"></script>
	<script type="text/javascript" src="scripts/wtccomponents/DDView.js"></script>
	<script type="text/javascript" src="scripts/wtccomponents/bubblePanel/BubblePanel.js"></script>
	<script src="scripts/wtccomponents/WTCTabPanel.js"></script>
	
	<script type="text/javascript">
	
	Ext.onReady(function(){	
		var appUser = CookieUtil.get('appuser');
		frontPage = '';
		if(Ext.isEmpty(appUser)){
			//new login.FrontPage().render(Ext.getBody());
			frontPage = new login.FrontPage().show();
		}
	});

	</script>
 	<!-- Instance Factory Class -->
	<script type="text/javascript" src = "scripts/InstanceFactory.js"></script>
	
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/ReferenceDataManager.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/RoleManagementController.js'></script>
	<script type="text/javascript" src="scripts/administration/userManagement/addUser/AddUser.js"></script>
	<script type="text/javascript" src="scripts/administration/userManagement/ChangePassword.js"></script>
	<script type="text/javascript" src="scripts/administration/userManagement/searchUser/ManageUser.js"></script>
	<script type="text/javascript" src = "scripts/administration/userManagement/addRole/AddRole.js"></script>
	<script type="text/javascript" src = "scripts/administration/userManagement/searchRole/ManageRoles.js"></script>
	
	<!-- USER MANAGEMENT END -->
			
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/DataModelManager.js'></script>
    <script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/ReferralManager.js'></script>
    <script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/ServiceManager.js'></script>
    <script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/BrandManagementController.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/MedicineManagementController.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/ConfigurationManager.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/PatientManager.js'></script>
    <script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/PatientManagementController.js'></script>
    <script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/AppointmentManagementController.js'></script>
    <script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/AppointmentManager.js'></script>
    <script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/DoctorManagementController.js'></script>
    <script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/TreatmentHistoryController.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/FileUploader.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/MedicalPrescriptionController.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/RosterManagementController.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/RosterManager.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/ScheduleManager.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/AccountantManager.js'></script>
	
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/IPReferenceDataManager.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/IPDataModelManager.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/InsuranceManager.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/OrderManager.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/BedManager.js'></script>
	
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/AdmissionOrder.js'></script>
	
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/engine.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>springapp/hcis/util.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/util.js'></script>


	<!-- I billing Start -->
	
		<!--<script type='text/javascript' src='<%=request.getContextPath()%>/../IBillServices/ibilling/interface/BillManager.js'></script>
		<script type='text/javascript' src='<%=request.getContextPath()%>/../IBillServices/ibilling/engine.js'></script>
		<script type='text/javascript' src='<%=request.getContextPath()%>/../IBillServices/springapp/dwr/util.js'></script>
		  -->
		  <script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/BillManager.js'></script>
	<!-- I billing ends -->

 	<script src="scripts/TitlePanel.js"></script>
 	<script type="text/javascript" src="scripts/WelcomePanel.js"></script>
 	<script src="scripts/ModuleButtonsToolBar.js"></script>
 	<script src="scripts/NorthPanel.js"></script>
 	<script type="text/javascript" src="scripts/wtccomponents/Ext.ux.tree.TreeFilterX.js"></script>
 	<script src="scripts/ModulesTreePanel.js"></script>
 	<script src="scripts/LatestActivitiesGridPanel.js"></script>
 	<script src="scripts/WestPanel.js"></script>
 	<script src="scripts/CenterRegionTabPanel.js"></script>
 	<script src="scripts/ApplicationLayout.js"></script>
 	<script src="scripts/Messages.js"></script>
	
	<!-- UTILITY JAVA SCRIPT FILES START -->
	<script src="scripts/utils/TabCloseMenu.js"></script>
 	<script src="scripts/utils/DashBoardDemo.js"></script>
 	<script src="scripts/utils/ButtonsPanel.js"></script>
 	<script src="scripts/utils/SearchButtonsPanel.js"></script>
 	<script src="scripts/utils/GridToolBar.js"></script>
 	<script src="scripts/utils/Utils.js"></script>
 	<script src="scripts/utils/refData/ComboLoader.js"></script>
 	<script src="scripts/utils/Configs.js"></script>
 	<script src="scripts/utils/FileUploadField.js"></script>
 	<script src="scripts/utils/UploadPhoto.js"></script>
 	<!-- UTILITY JAVA SCRIPT FILES END -->
 	
 	<!-- WTC COMPONENTS START -->
 	<script src="scripts/wtccomponents/WTCAmountField.js"></script>
 	<script src="scripts/wtccomponents/OptComboBox.js"></script>
	<script src="scripts/wtccomponents/Ext.ux.grid.RowActions.js"></script>
	<script src="scripts/wtccomponents/Ext.ux.grid.Search.js"></script>
	<script src="scripts/wtccomponents/WTCPagingToolbar.js"></script>
 	<script src="scripts/wtccomponents/WTCComboBox.js"></script>
	<script src="scripts/wtccomponents/WTCDateTimeField.js"></script>
 	<script src="scripts/wtccomponents/WTCDateField.js"></script>
 	<script src="scripts/wtccomponents/WTCPhoneField.js"></script>
 	<script src="scripts/wtccomponents/Window.js"></script>
	<!--WTC COMPONENTS END -->

	<!--  REFERRAL MANAGEMENT START -->		
	<script src="scripts/administration/referralManagement/Widgets.js"></script>
 	<script src="scripts/administration/referralManagement/AddReferral.js"></script>
 	<script src="scripts/administration/referralManagement/ContactsPanel.js"></script>
 	<script src="scripts/administration/referralManagement/ReferralCommisionConfigGridPanel.js"></script>
 	<script src="scripts/administration/referralManagement/ReferralSearchPanel.js"></script>
 	<script src="scripts/administration/referralManagement/ReferralsListGridPanel.js"></script>
 	<script src="scripts/administration/referralManagement/ReferralsList.js"></script>
 	
 	<script src="scripts/reports/ReferralCommisionReports.js"></script>

	<!--  REFERRAL MANAGEMENT END -->	

	<!--  VACCCINATION SCHEDULE START -->	
 	<script src="scripts/administration/vaccinationSchedule/configure/AddVaccinationSchedule.js"></script>
 	<script src="scripts/administration/vaccinationSchedule/configure/VaccinationsSelectionPanel.js"></script>
 	<script src="scripts/administration/vaccinationSchedule/configure/VaccinationsSelectionGridPanel.js"></script>

	<script src="scripts/administration/vaccinationSchedule/manage/Widgets.js"></script>
 	<script src="scripts/administration/vaccinationSchedule/manage/VaccinationScheduleSearchPanel.js"></script>
 	<script src="scripts/administration/vaccinationSchedule/manage/VaccinationSchedulesList.js"></script>
 	<script src="scripts/administration/vaccinationSchedule/manage/VaccinationSchedulesListGridPanel.js"></script>
	
 	<script src="scripts/administration/vaccinationSchedule/patientsVaccinationSchedule/PatientsVaccinationSchedule.js"></script>
 	<script src="scripts/administration/vaccinationSchedule/patientsVaccinationSchedule/VaccinationSchedulesSelectionPanel.js"></script>
 	<script src="scripts/administration/vaccinationSchedule/patientsVaccinationSchedule/VaccinationSchedulesSelectionGridPanel.js"></script>
 	 	
 	<script src="scripts/administration/vaccinationSchedule/Widgets.js"></script>
 	<script src="scripts/administration/vaccinationSchedule/Messages.js"></script>
	<!--  VACCCINATION SCHEDULE END -->	

	<!--  SERVICE MANAGEMENT START  -->
	
	<script src="scripts/administration/service_group_package/searchService/Widgets.js"></script>
	<script src="scripts/administration/service_group_package/searchService/SearchServices.js"></script>
	
	<script src="scripts/administration/service_group_package/addService/Widgets.js"></script>
	<script src="scripts/administration/service_group_package/addService/ConfigureService.js"></script>
	
	<script src="scripts/administration/service_group_package/addServiceGroup/ConfigureServiceGroup.js"></script>
	<script src="scripts/administration/service_group_package/addServiceGroup/ServiceSelectionPanel.js"></script>
	<script src="scripts/administration/service_group_package/addServiceGroup/AvailableServicesGrid.js"></script>
	<script src="scripts/administration/service_group_package/addServiceGroup/SelectedServicesGrid.js"></script>
	<script src="scripts/administration/service_group_package/addPackage/ConfigurePackage.js"></script>
	<script src="scripts/administration/service_group_package/addPackage/ConfigurePackagePkgOverrideLvl.js"></script>
	<script src="scripts/administration/service_group_package/addPackage/ConfigurePackageSvcOverrideLvl.js"></script>
	<script src="scripts/administration/service_group_package/searchPackage/SearchPackages.js"></script>
	<script src="scripts/administration/service_group_package/searchServiceGroup/SearchServiceGroups.js"></script>
	
	<script src="scripts/administration/service_group_package/Messages.js"></script>
	<script src="scripts/administration/service_group_package/ManageServices.js"></script>
	
	<script src="scripts/administration/service_group_package/priceUpdate/PriceUpdateGridPanel.js"></script>
	<script src="scripts/administration/service_group_package/priceUpdate/UpdataServicePrice.js"></script>
	<script src="scripts/administration/service_group_package/priceUpdate/Widgets.js"></script>
	<script src="scripts/administration/service_group_package/priceUpdate/PriceUpdateOptionPanel.js"></script>
	<script src="scripts/administration/service_group_package/priceUpdate/PriceUpdateSearchPanel.js"></script>
	<script src="scripts/administration/service_group_package/priceUpdate/PriceDetailsPanel.js"></script>
	<script src="scripts/administration/service_group_package/priceUpdate/ServicePriceMessages.js"></script>

	<!--   SERVICE MANAGEMENT END  -->
	
	<!-- USER MANAGEMENT START -->
	
	<script type="text/javascript" src="scripts/administration/userManagement/AddUser.js"></script>
	<script type="text/javascript" src="scripts/administration/userManagement/ChangePassword.js"></script>
	<script type="text/javascript" src="scripts/administration/userManagement/ManageUser.js"></script>
	
	<!--  USER MANAGEMENT END -->
	
	<!-- PHARMACY MANAGEMENT START -->
	<script type="text/javascript" src="scripts/administration/medicines/addBrand/ConfigureBrand.js"></script>
	
	<script type="text/javascript" src="scripts/administration/medicines/addMedicine/ConfigureMedicine.js"></script>
	
	<script type="text/javascript" src="scripts/administration/medicines/manageBrands/SearchBrands.js"></script>
	
	<script type="text/javascript" src="scripts/administration/medicines/manageMedicines/SearchMedicines.js"></script>
	
	<script type="text/javascript" src="scripts/administration/medicines/Messages.js"></script>
	<!--  PHARMACY MANAGEMENT END -->	
	
	<!-- PATIENT REGISTRATION - START -->
	<script type="text/javascript" src="scripts/OPD/registration/AllergiesDetailsPanel.js"></script>
	<script type="text/javascript" src="scripts/OPD/registration/AllergiesGridPanel.js"></script>
	<script type="text/javascript" src="scripts/OPD/registration/ImmunizationDetailsPanel.js"></script>
	<script type="text/javascript" src="scripts/OPD/registration/ImmunizationGridPanel.js"></script>
	<script type="text/javascript" src="scripts/OPD/registration/PatientOtherDetails.js"></script>	
	<script type="text/javascript" src="scripts/OPD/registration/PatientPersonalDetails.js"></script>
	<script type="text/javascript" src="scripts/OPD/registration/PatientRegistration.js"></script>
	<script type="text/javascript" src="scripts/OPD/ContactDetails.js"></script>
	<script type="text/javascript" src="scripts/OPD/EmergencyContactDetails.js"></script>
	
	<script type="text/javascript" src="scripts/OPD/managePatient/PatientMgmnt.js"></script>
	<script type="text/javascript" src="scripts/OPD/managePatient/PatientRecord.js"></script>
	<script type="text/javascript" src="scripts/OPD/managePatient/SearchPatientsGridPanel.js"></script>
	<!-- PATIENT REGISTRATION - END -->
	
	<!-- APPOINTMENT START -->
	<script type="text/javascript" src="scripts/OPD/manageAppointment/ManageAppointmentGrid.js"></script>
	<script type="text/javascript" src="scripts/OPD/manageAppointment/CancelAppointment.js"></script>
	<script type="text/javascript" src="scripts/OPD/manageAppointment/DoctorConsultationDetails.js"></script>
	<script type="text/javascript" src="scripts/OPD/manageAppointment/ManageAppointments.js"></script>
	<script type="text/javascript" src="scripts/OPD/manageAppointment/RescheduleAppointments.js"></script>
	<script type="text/javascript" src="scripts/OPD/manageAppointment/ViewModifyAppointment.js"></script>
	
	<script type="text/javascript" src="scripts/OPD/newAppointment/NewAppointment.js"></script>
	
	<script type="text/javascript" src="scripts/OPD/SearchPatientWindow.js"></script>
	<script type="text/javascript" src="scripts/OPD/ViewPatientDetails.js"></script>
	<!-- APPOINTMENT END -->
	
	<!-- TREATMENT HISTORY START -->
	<script type="text/javascript" src="scripts/OPD/treatmentHistory/MedicalObservation.js"></script>
	<script type="text/javascript" src="scripts/OPD/treatmentHistory/MedicalPrescriptionGrid.js"></script>
	<script type="text/javascript" src="scripts/OPD/treatmentHistory/TreatmentHistory.js"></script>
	<script type="text/javascript" src="scripts/OPD/treatmentHistory/ViewTreatmentDetails.js"></script>
	<!-- TREATMENT HISTORY END -->
	
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/CoreReportManager.js'></script>
	
	<script type="text/javascript" src="scripts/accounting/AccountantStores.js"></script>
	<script type="text/javascript" src="scripts/accounting/CreateReceipt.js"></script>
	<script type="text/javascript" src="scripts/accounting/DepositRequestGridPanel.js"></script>
	<!-- ACCOUNTING END -->
	
	<!-- OPD BILLING START -->
	
	<script type="text/javascript" src="scripts/OPD/billing/AssignedServiceCardPanel.js"></script>
	<script type="text/javascript" src="scripts/OPD/billing/AssignedServiceDetailsPanel.js"></script>
	<script type="text/javascript" src="scripts/OPD/billing/AssignedServiceGrid.js"></script>
	<script type="text/javascript" src="scripts/OPD/billing/AssignedServicePanel.js"></script>
	<script type="text/javascript" src="scripts/OPD/billing/OpBillingMessages.js"></script>
	<script type="text/javascript" src="scripts/OPD/billing/OpBillingPanel.js"></script>
	<script type="text/javascript" src="scripts/OPD/billing/ServiceCombo.js"></script>
	
	<!-- OPD BILLING END -->
	
	<!--  SERVICE ASSIGNEMENT START -->
	<script type="text/javascript" src="scripts/OPD/serviceAssignement/ManageServiceAssignment.js"></script>
	<script type="text/javascript" src="scripts/OPD/serviceAssignement/ManageServiceAssignmentGrid.js"></script>
	<script type="text/javascript" src="scripts/OPD/serviceAssignement/Messages.js"></script>
	<script type="text/javascript" src="scripts/OPD/serviceAssignement/SelectionPanel.js"></script>
	<script type="text/javascript" src="scripts/OPD/serviceAssignement/SelectionPanelWidgets.js"></script>
	<script type="text/javascript" src="scripts/OPD/serviceAssignement/Widgets.js"></script>
	<!--  SERVICE ASSIGNEMENT END -->
	
	<!--  DOCTOR MANAGEMENT START -->
	<script type="text/javascript" src="scripts/administration/addDoctor/CreateDoctor.js"></script>
	<script type="text/javascript" src="scripts/administration/addDoctor/DoctorDepartmentSpeciality.js"></script>
	<script type="text/javascript" src="scripts/administration/addDoctor/DoctorPersonalDetails.js"></script>
	<script type="text/javascript" src="scripts/administration/addDoctor/Qualification.js"></script>
	<script type="text/javascript" src="scripts/administration/addDoctor/DoctorConsultationDetails.js"></script>
	
	<script type="text/javascript" src="scripts/administration/searchDoctor/DoctorDetailsRecordType.js"></script>
	<script type="text/javascript" src="scripts/administration/searchDoctor/SearchDoctor.js"></script>
	<!-- DOCTOR MANAGEMENT END -->
	
	<!-- ROSTER START-->
	<script type="text/javascript" src="scripts/administration/addRoster/CreateRoster.js"></script>
	<script type="text/javascript" src="scripts/administration/manageRoster/ManageRoster.js"></script>
	<!-- ROSTER END-->
	
	<!-- REPORTS START-->
	<script type="text/javascript" src="scripts/reports/patientVaccinationScheduleReport/InputForm.js"></script>
	<script type="text/javascript" src="scripts/reports/patientVaccinationScheduleReport/Widgets.js"></script>
	<script type="text/javascript" src="scripts/reports/ButtonsPanel.js"></script>
	<script type="text/javascript" src="scripts/reports/ReportsPanel.js"></script>
	<script type="text/javascript" src="scripts/reports/ReportsFormPanel.js"></script>

	<!-- REPORTS END-->
	
	<!-- CheckList -->
	
	<script type="text/javascript" src="scripts/administration/checkList/CheckListMessages.js"></script>
	
	<script type="text/javascript" src="scripts/administration/checkList/configure/AssociatedQuesGrid.js"></script>
	<script type="text/javascript" src="scripts/administration/checkList/configure/ConfigureCheckList.js"></script>
	<script type="text/javascript" src="scripts/administration/checkList/configure/QuestionPanel.js"></script>
	
	<script type="text/javascript" src="scripts/administration/checkList/CheckListWidgets.js"></script>
	
	<script type="text/javascript" src="scripts/administration/checkList/CheckListSearchPanel.js"></script>
	<script type="text/javascript" src="scripts/administration/checkList/CheckListSearchGrid.js"></script>
	
	<!-- COMPLETE PATIENT TREATMENT HISTORY START -->
	<script type="text/javascript" src="scripts/OPD/treatmentHistory/PatientHistory/Messages.js"></script>
	<script type="text/javascript" src="scripts/OPD/treatmentHistory/PatientHistory/HistoryTabPanel.js"></script>
	<script type="text/javascript" src="scripts/OPD/treatmentHistory/PatientHistory/History.js"></script>
	<script type="text/javascript" src="scripts/OPD/treatmentHistory/PatientHistory/TreatmentObservationGrid.js"></script>
	<script type="text/javascript" src="scripts/OPD/treatmentHistory/PatientHistory/ClinicalPrescriptionGrid.js"></script>
	<!-- COMPLETE PATIENT TREATMENT HISTORY END -->
	
	<!-- OPD SYSTEM CONFIGURATION START  -->
	
	<script type="text/javascript" src="scripts/administration/systemConfiguration/SystemParamPanel.js"></script>
	<script type="text/javascript" src="scripts/administration/systemConfiguration/SystemParamFormPanel.js"></script>
	

	<!-- OPD SYSTEM CONFIGURATION END  -->
	
	<!-- OPD ISSUE RECEIPT START -->
	<script type="text/javascript" src="scripts/OPD/issueReceipt/IssueReceipt.js"></script>
	<script type="text/javascript" src="scripts/OPD/issueReceipt/BasicDetails.js"></script>
	<script type="text/javascript" src="scripts/OPD/issueReceipt/ReceivablesGridPanel.js"></script>
	<script type="text/javascript" src="scripts/OPD/issueReceipt/PaymentMode.js"></script>
	<script type="text/javascript" src="scripts/OPD/issueReceipt/ReceivablesDetails.js"></script>
	<!-- OPD ISSUE RECEIPT END -->
	
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/CheckListManager.js'></script>
	
	<!--  IPD START -->
	
	<!-- BED START-->
	
	<script type="text/javascript" src="scripts/administration/addBed/Bed.js"></script>
	<script type="text/javascript" src="scripts/administration/addBed/BedFeaturesPanel.js"></script>
	<script type="text/javascript" src="scripts/administration/addBedPool/BedPool.js"></script>
	<script type="text/javascript" src="scripts/administration/addBedEnvelope/BedEnvelop.js"></script>
	<script type="text/javascript" src="scripts/administration/manageBed/AssignBed.js"></script>
	<script type="text/javascript" src="scripts/administration/manageBed/BedBookingGrid.js"></script>
	<script type="text/javascript" src="scripts/administration/manageBed/BedGrid.js"></script>
	<script type="text/javascript" src="scripts/administration/manageBed/BookBed.js"></script>
	<script type="text/javascript" src="scripts/administration/manageBed/ManageBed.js"></script>
	<script type="text/javascript" src="scripts/administration/manageBed/ManageBedBooking.js"></script>
	<script type="text/javascript" src="scripts/administration/manageBed/OverallBedOccupancy.js"></script>
	<script type="text/javascript" src="scripts/administration/manageBed/SearchBeds.js"></script>
	<script type="text/javascript" src="scripts/administration/manageBedPool/SearchBedPool.js"></script>
	<script type="text/javascript" src="scripts/administration/manageBedPool/BedPool.js"></script>
	<script type="text/javascript" src="scripts/administration/manageBedEnvelope/SearchBedEnvelop.js"></script>
	<!-- BED END-->
	
	<!-- ORDER MANAGEMENT START-->
	
	<script type="text/javascript" src="scripts/IPD/addOrder/DoctorOrder.js"></script>
	<script type="text/javascript" src="scripts/IPD/addOrder/DoctorOrderBasicDetailsPanel.js"></script>
	<script type="text/javascript" src="scripts/IPD/addOrder/DoctorOrderDetailsPanel.js"></script>
	<script type="text/javascript" src="scripts/IPD/addOrder/DoctorOrderDetailsGrid.js"></script>
	<script type="text/javascript" src="scripts/IPD/addOrder/DoctorOrderServiceSelectionsGrid.js"></script>
	<script type="text/javascript" src="scripts/IPD/addOrder/DoctorOrderTabPanel.js"></script>
	<script type="text/javascript" src="scripts/IPD/addOrder/SpecificOrderPanel.js"></script>
	<script type="text/javascript" src="scripts/IPD/addOrder/DoctorOrderTemplateServicesPanel.js"></script>
	<script type="text/javascript" src="scripts/IPD/addOrderTemplate/DoctorOrderTemplate.js"></script>
	<script type="text/javascript" src="scripts/IPD/doctorOrderGroup/DoctorOrderGroup.js"></script>
	<script type="text/javascript" src="scripts/IPD/manageDoctorOrderGroup/SearchDoctorOrderGroup.js"></script>
	<script type="text/javascript" src="scripts/IPD/doctorOrderList/SearchDoctorOrder.js"></script>
	<script type="text/javascript" src="scripts/IPD/doctorOrderList/SearchDoctorOrderGrid.js"></script>
	<script type="text/javascript" src="scripts/IPD/doctorOrderList/DoctorOrderRemarksPanel.js"></script>
	<script type="text/javascript" src="scripts/IPD/manageDoctorOrderTemplate/SearchDoctorOrderTemplate.js"></script>
	
	<!-- ORDER MANAGEMENT END-->
	
	<!-- INSURANCE MANAGER START -->
	
	<script type="text/javascript" src="scripts/administration/addSponsor/Sponsor.js"></script>
	<script type="text/javascript" src="scripts/administration/addInsurer/Insurer.js"></script>
	<script type="text/javascript" src="scripts/administration/addInsurer/MediclaimContactDetails.js"></script>
	<script type="text/javascript" src="scripts/administration/addInsurer/InsurerSponsorAssoc.js"></script>
	<script type="text/javascript" src="scripts/administration/manageInsurer/SearchInsuranceCompanies.js"></script>
	<script type="text/javascript" src="scripts/administration/manageInsurer/InsurersGrid.js"></script>
	<script type="text/javascript" src="scripts/administration/manageSponsor/SponsorsGrid.js"></script>
	<script type="text/javascript" src="scripts/administration/manageSponsor/SearchSponsors.js"></script>
	<script type="text/javascript" src="scripts/administration/managePlan/SearchPlans.js"></script>
	<script type="text/javascript" src="scripts/administration/managePlan/PlansGrid.js"></script>
	<script type="text/javascript" src="scripts/administration/insurerPlan/InsurancePlan.js"></script>
	<script type="text/javascript" src="scripts/administration/insurerPlan/PlanInfoGrid.js"></script>
	<script type="text/javascript" src="scripts/administration/addSponsor/SLA.js"></script>
	<script type="text/javascript" src="scripts/IPD/addClaim/Claim.js"></script>
	<script type="text/javascript" src="scripts/IPD/addClaim/PlanPanel.js"></script>
	<script type="text/javascript" src="scripts/IPD/manageClaim/SearchClaims.js"></script>
	<script type="text/javascript" src="scripts/IPD/manageClaim/AdmissionClaimsGrid.js"></script>
	<script type="text/javascript" src="scripts/IPD/manageClaim/Insurance.js"></script>
	<script type="text/javascript" src="scripts/IPD/manageClaim/ClaimStatusPanel.js"></script>
	
	<!-- INSURANCE MANAGEMENT END -->
	
	<!-- ADMISSION START -->
	
	<script type="text/javascript" src="scripts/IPD/admission/BedDetailGrid.js"></script>
	<script type="text/javascript" src="scripts/IPD/admission/DoctorOrderGrid.js"></script>
	<script type="text/javascript" src="scripts/IPD/admission/InsuranceDetailGrid.js"></script>
	<script type="text/javascript" src="scripts/IPD/admission/ServiceDetailGrid.js"></script>
	<script type="text/javascript" src="scripts/IPD/admission/UnbilledUsageGrid.js"></script>
	<script type="text/javascript" src="scripts/IPD/admission/ViewBillsGrid.js"></script>
	<script type="text/javascript" src="scripts/IPD/admission/ViewPastTransactionsGrid.js"></script>
	<script type="text/javascript" src="scripts/IPD/admission/IPBilling.js"></script>
	<script type="text/javascript" src="scripts/IPD/admission/Admission.js"></script>
	
	<script type="text/javascript" src="scripts/IPD/admission/manageAdmissions/AdmissionOrder.js"></script>
	<script type="text/javascript" src="scripts/IPD/admission/manageAdmissions/ManageAdmissions.js"></script>
	
	<script type="text/javascript" src="scripts/IPD/admission/manageAdmissions/DischargePanel.js"></script>
	<!-- ADMISSION END -->
	
	<!--  IPD END -->
	

	<!-- REQUISITION ORDER START	-->
	
	<script type="text/javascript" src="scripts/LIMS/LIMSMessages.js"></script>
	<script type="text/javascript" src="scripts/LIMS/GridRowActions.js"></script>
	<script type="text/javascript" src="scripts/LIMS/requisitionOrder/manage/RequisitionOrderGridToolbar.js"></script>
	<script type="text/javascript" src="scripts/LIMS/requisitionOrder/manage/RequisitionOrderWidgets.js"></script>
	<script type="text/javascript" src="scripts/LIMS/requisitionOrder/manage/RequisitionOrderSearchCriteria.js"></script>
	<script type="text/javascript" src="scripts/LIMS/requisitionOrder/manage/RequisitionOrderSearchGrid.js"></script>
	<script type="text/javascript" src="scripts/LIMS/requisitionOrder/manage/RequisitionOrderSearchPanel.js"></script>
	<script type="text/javascript" src="scripts/LIMS/requisitionOrder/manage/RequisitionOrderDetailGrid.js"></script>
	<script type="text/javascript" src="scripts/LIMS/requisitionOrder/manage/BasicDetailWidgets.js"></script>
	<script type="text/javascript" src="scripts/LIMS/requisitionOrder/manage/RequisitionOrderDetailView.js"></script>
	
	<!-- REQUISITION ORDER END	-->	
	
	
	<!-- LIMS start -->
	
	
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/RequisitionOrderManager.js'></script>

	<script type="text/javascript" src="scripts/LIMS/LIMSComboLoader.js"></script>
	<script type="text/javascript" src="scripts/LIMS/technique/AddTechnique.js"></script>
	<script type="text/javascript" src="scripts/LIMS/technique/Widgets.js"></script>
	<script type="text/javascript" src="scripts/LIMS/LIMSMessages.js"></script>	
	<script type="text/javascript" src="scripts/LIMS/LIMSComboLoader.js"></script>
	
	<script type="text/javascript" src="scripts/LIMS/technique/SearchTechnique.js"></script>
	<script type="text/javascript" src="scripts/LIMS/technique/TechniqueListGridPanel.js"></script>
	<script type="text/javascript" src="scripts/LIMS/technique/TechniqueSearchPanel.js"></script>
	
	<script type="text/javascript" src="scripts/LIMS/labTest/configure/TestSampleAssoPanel.js"></script>
	<script type="text/javascript" src="scripts/LIMS/labTest/configure/TestSampleGridPanel.js"></script>
	<script type="text/javascript" src="scripts/LIMS/labConfiguration/AddLabDetail.js"></script>
	<script type="text/javascript" src="scripts/LIMS/labConfiguration/LabContactDetail.js"></script>
	<script type="text/javascript" src="scripts/LIMS/labConfiguration/LabDetailPanel.js"></script>
	<script type="text/javascript" src="scripts/LIMS/labConfiguration/LaboratoryListGrid.js"></script>
	<script type="text/javascript" src="scripts/LIMS/labConfiguration/LabSearchPanel.js"></script>
	<script type="text/javascript" src="scripts/LIMS/labConfiguration/SearchLaboratory.js"></script>
	<script type="text/javascript" src="scripts/LIMS/labConfiguration/Widgets.js"></script>
	
	<script type="text/javascript" src="scripts/LIMS/labTest/configure/AddLabTest.js"></script>
	<script type="text/javascript" src="scripts/LIMS/labTest/configure/TestAttributeAssoPanel.js"></script>
	<script type="text/javascript" src="scripts/LIMS/labTest/configure/TestAttributeGridPanel.js"></script>
	
	<script type="text/javascript" src="scripts/LIMS/labTest/Widgets.js"></script>
	
	
	<script type="text/javascript" src="scripts/LIMS/collectionPointConfiguration/Widgets.js"></script>
	<script type="text/javascript" src="scripts/LIMS/collectionPointConfiguration/AddCollectionPoint.js"></script>
	<script type="text/javascript" src="scripts/LIMS/collectionPointConfiguration/CollectionPointPanel.js"></script>
	
	
	<script type="text/javascript" src="scripts/LIMS/testAttribute/AddTestAttribute.js"></script>
	
	<script type="text/javascript" src="scripts/LIMS/testAttribute/SearchTestAttribute.js"></script>
	<script type="text/javascript" src="scripts/LIMS/testAttribute/TestAttributeListGridPanel.js"></script>
	<script type="text/javascript" src="scripts/LIMS/testAttribute/TestAttributeSearchPanel.js"></script>
	<script type="text/javascript" src="scripts/LIMS/testAttribute/Widgets.js"></script>
	
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/LabConfigManager.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/LimsReferenceDataManager.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/LabTestManager.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/LimsDataModelManager.js'></script>
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/LabDetailManager.js'></script>
	
	
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/TestTemplateManager.js'></script>

	<script type="text/javascript" src="scripts/LIMS/testTemplate/configure/TestTemplateButtonsPanel.js"></script>
	<script type="text/javascript" src="scripts/LIMS/testTemplate/configure/AvailableListTreePanel.js"></script>
	<script type="text/javascript" src="scripts/LIMS/testTemplate/configure/DynamicWidget.js"></script>
	<script type="text/javascript" src="scripts/LIMS/testTemplate/configure/ConfigureTestTemplate.js"></script>
	

	<script type="text/javascript" src="scripts/LIMS/testTemplate/RemarksWindow.js"></script>
	
	<script type="text/javascript" src="scripts/LIMS/labTest/TestResultChangeHistory.js"></script>
	
	<!-- SPECIMEN COLLECTION POINT  START --> 
	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/CollectionPointManager.js'></script>
	
	<script type="text/javascript" src="scripts/LIMS/specimenCollectionPoint/CollectionPointWidgets.js"></script>
	<script type="text/javascript" src="scripts/LIMS/specimenCollectionPoint/CollectionPointDetailPanel.js"></script>
	<script type="text/javascript" src="scripts/LIMS/specimenCollectionPoint/CollectionPointLabAssociationGrid.js"></script>
	<script type="text/javascript" src="scripts/LIMS/specimenCollectionPoint/ConfigureCollectionPoint.js"></script>
	

	<script type="text/javascript" src="scripts/LIMS/specimenCollectionPoint/manage/CollectionPointSearchCriteria.js"></script>
	<script type="text/javascript" src="scripts/LIMS/specimenCollectionPoint/manage/CollectionPointSearchGrid.js"></script>
	<script type="text/javascript" src="scripts/LIMS/specimenCollectionPoint/manage/CollectionPointSearchPanel.js"></script>
	
	
	<!-- SPECIMEN COLLECTION POINT  END -->
	
	<script type="text/javascript" src="scripts/wtccomponents/DisplayField.js"></script>
	<script type="text/javascript" src="scripts/wtccomponents/PrinterWindow.js"></script>
	
	
	<!-- LIMS end -->
	
	<!-- Entity Start -->
	
	<script type="text/javascript" src="scripts/administration/manageEntity/configure/AddEntity.js"></script>
	<script type="text/javascript" src="scripts/administration/manageEntity/configure/PersonalDetails.js"></script>
	<script type="text/javascript" src="scripts/administration/manageEntity/configure/EntityQualification.js"></script>
	<script type="text/javascript" src="scripts/administration/manageEntity/Widgets.js"></script>
	<script type="text/javascript" src="scripts/administration/manageEntity/Messages.js"></script>
	
	<script type="text/javascript" src="scripts/administration/manageEntity/manage/EntityGridPanel.js"></script>
	<script type="text/javascript" src="scripts/administration/manageEntity/manage/EntityList.js"></script>
	<script type="text/javascript" src="scripts/administration/manageEntity/manage/EntitySearchPanel.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/hcis/interface/EntityManager.js"> </script>
	<!--  Entity End -->
	

	<!-- dash board script files start -->
    
    <script type="text/javascript" src="scripts/dashboard/DashBoardMessages.js"></script>
	<script type="text/javascript" src="scripts/dashboard/Portal.js"></script>
    <script type="text/javascript" src="scripts/dashboard/PortalColumn.js"></script>
    <script type="text/javascript" src="scripts/dashboard/Portlet.js"></script>
    
     <script type="text/javascript" src="scripts/dashboard/grouptabs/GroupTab.js"></script>
    <script type="text/javascript" src="scripts/dashboard/grouptabs/GroupTabPanel.js"></script>
    
    <script type="text/javascript" src="scripts/dashboard/portal/sample-grid.js"></script>
    
    <script type="text/javascript" src="scripts/dashboard/AvailableGadgetLayout.js"></script>
    <script type="text/javascript" src="scripts/dashboard/grouptabs.js"></script>
    
    <!-- dash board script files end -->
    
        
    <!-- OT START -->
    
    <!-- CONFIGURE OT START-->
    
    <script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/OTManager.js'></script>
    <script type="text/javascript" src="scripts/OT/manageOT/ConfigureOT.js"></script>
    <script type="text/javascript" src="scripts/OT/manageOT/OTWidgets.js"></script>
    <script type="text/javascript" src="scripts/OT/manageOT/AssociatedSurgeryGrid.js"></script>
  	<script type="text/javascript" src="scripts/OT/manageOT/CheckListPanel.js"></script>
  	<script type="text/javascript" src="scripts/OT/manageOT/OTSearchPanel.js"></script>
  	<script type="text/javascript" src="scripts/OT/manageOT/OTSearchGrid.js"></script>

    <script type="text/javascript" src="scripts/OT/OTMessages.js"></script>
    <script type="text/javascript" src="scripts/OT/OTComboLoader.js"></script>
    
    <!-- CONFIGURE OT END-->
    
    <!--  CONFIGURE SURGERY START -->
    
    <script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/SurgeryManager.js'></script>
    <script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/OTReferenceDataManager.js'></script>
    <script type="text/javascript" src="scripts/OT/configureSurgery/ConfigureSurgery.js"></script>
    <script type="text/javascript" src="scripts/OT/configureSurgery/AssociatedOTGrid.js"></script>
    <script type="text/javascript" src="scripts/OT/configureSurgery/SurgeryStatusTimeDetailPanel.js"></script>
    <script type="text/javascript" src="scripts/OT/configureSurgery/SurgeryWidgets.js"></script>
    
   	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/VitalManager.js'></script>
    <script type="text/javascript" src="scripts/OPD/vital/configure/ConfigureVital.js"></script>
    <script type="text/javascript" src="scripts/OPD/vital/configure/ConfigureVitalGrid.js"></script>
    <script type="text/javascript" src="scripts/OPD/vital/configure/DynamicPanel.js"></script>
    <script type="text/javascript" src="scripts/OPD/vital/configure/VitalGraph.js"></script>
    <script type="text/javascript" src="scripts/OPD/vital/VitalMessages.js"></script>
   
    
    <!--  CONFIGURE SURGERY END -->
        <!--  BOOKING OT START -->
    

    <script type="text/javascript" src="scripts/OT/booking/OTBooking.js"></script>
    <script type="text/javascript" src="scripts/OT/booking/OTBookingMessages.js"></script>
    <script type="text/javascript" src="scripts/OT/booking/BookingWidgets.js"></script>
    
    
    <script type="text/javascript" src="scripts/OT/booking/OTBookingGrid.js"></script>
    <script type="text/javascript" src="scripts/OT/booking/OTBookingWidgets.js"></script>
    <script type="text/javascript" src="scripts/OT/booking/OTBookingList.js"></script>
  	<script type="text/javascript" src="scripts/OT/booking/OTBookingSearchPanel.js"></script>
  	<script type="text/javascript" src="scripts/OT/register/OTNotes.js"></script>
  	<script type="text/javascript" src="scripts/OT/register/OTNotesFieldsPanel.js"></script>
    
    <!--  BOOKING OT END -->
    
    <!--  OT REGISTER  START -->
    
    <script type="text/javascript" src="scripts/OT/register/OTRegisterGrid.js"></script>
    <script type="text/javascript" src="scripts/OT/register/OTRegisterWidgets.js"></script>
    <script type="text/javascript" src="scripts/OT/register/OTRegisterPanel.js"></script>
  	<script type="text/javascript" src="scripts/OT/register/OTRegisterSearchPanel.js"></script>
  	<script type="text/javascript" src="scripts/OT/register/OTNotesRemarksPanel.js"></script>
  	<script type="text/javascript" src="scripts/OT/register/CheckListPanel.js"></script>
    
    <!--  OT REGISTER  END -->
    <!-- OT END -->
    
    <!-- WORK FLOW MANAGER -->
   	<script type='text/javascript' src='<%=request.getContextPath()%>/hcis/interface/WorkFlowManager.js'></script>
  	<script type="text/javascript" src="scripts/wtccomponents/WorkFlow.js"></script>
  	<script type="text/javascript" src="scripts/utils/raphael-min.js"></script>
    
    
    <!--  WORK FLOW MANAGER -->
	<!--  main java script file (which starts the application)) -->	
	<script type="text/javascript" src="scripts/Application.js"></script>
 	
  </head>
  
  <body>
<!-- 

     	<div id="loading-mask"><img src="extjs/resources/images/default/shared/large-loading.gif" style="margin-left:400px;margin-top:300px;"><a style="font-size:14;color:green;"> Loading ...</a></div>
		<div id="app-loading">
		<div class="loading-indicator"> </div>
		</div>
-->
  </body>


</html>
