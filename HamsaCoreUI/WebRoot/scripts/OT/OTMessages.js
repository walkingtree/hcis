Ext.namespace("OT");

OT.OTMessages = function(){
		
	/************************************************************************/
	/**					OT CONFIGURATION MESSAGES START                    **/
	/************************************************************************/	
	
	// Label Messages.
	
	this.lblOtName = "OT name";
	this.lblBedNbr = "Bed no";
	this.lblCoordinator = "Coordinator";
	this.lblSurgery = "Surgery";
	this.lblActvityName = "Activity name";
	this.lblCheckList = "Check list";
	this.lblBeforeActivity = "Before activity";
	this.lblAfterActivity = "After activity";
	this.otCode = "OT code";
	this.associatedService = "Associated surgery";
	this.serviceCode = "Surgery code";
	this.serviceName = "Surgery name";
	this.surgeryStatus = "Status"
	
	// Button messages
	
	this.btnAdd = "Add";

	// TITLE
	
	this.ttlActivityPanel = "Activity checklist";
	this.otName = "Name";
	this.otStatus = "Status";
	this.checkListName = "Check list name";
	this.type = "Type";
	this.addOperationTheatre = "Add operation theatre";
	this.editOperationTheatre = "Edit operation theatre";
	
	
	//Tooltip
	
	this.releasePatient = "Release patient";
	this.admitPatient = "Admit patient";
	this.viewBookingAvailabilityDetail = "View booking/availability detail";
	
	//Messages 
	
	this.otSrchGridMsg = "No operation theatre to display";
	this.otExistMsg = "Operation theatre already exist";
	
	/************************************************************************/
	/**					OT CONFIGURATION MESSAGES END                      **/
	/************************************************************************/	
	
	/************************************************************************/
	/**					OT SURGERY MESSAGES START   	                   **/
	/************************************************************************/	
	
	this.lblSurgeryType = "Surgery type";
	this.lblSpeciality = "Speciality";
	this.lblDoctorRefreshmentTime = "Doctor refershment time";
	this.lblTotalTime = "Total time";
	this.lbListofOTs = "List of OTs";
	this.lblTotal = "Total surgery time";
	this.surgeryTimeDetail = "Surgery time detail";
	this.surgeryPanel = "Surgery panel";
	this.associatedOT = "Associated operation theatre";
	this.surgeonRefreshmentTime = "Surgeon refreshment time";
	
	//CONSTANT
	
	this.surgicalServiceType = "SURGICAL"; 
	this.COORDINATOR_ENTITY = "COORDINATOR";
	this.OT_BED_TYPE = "OT";
	this.SERVICE_TYPE_SURGERY = "SERVICE_TYPE_SURGERY";
	this.existMsg = "Surgery already exist";
	
	
	/************************************************************************/
	/**					OT SURGERY MESSAGES END     	                   **/
	/************************************************************************/	
	
	/************************************************************************/
	/**					OT BOOKING MESSAGES START     	                   **/
	/************************************************************************/
	
     this.bookingFrom = "From";
     this.bookingTo = "To";
     this.surgeon = "Surgeon";
     this.otCodelbl = "OT";
     this.bookingCode = "OT Code";
     this.surgeonName = "Surgeon";
     this.bookingDate = "Date";
     this.bookingFrom = "From";
     this.bookingTo = "To";
     this.OtNamelbl = "OT Name";
     this.lblTotal = "<b>Total surgery time</b>";
     this.availableOTSlot = "Available OT Slot";
     this.otTotalTime = "<b>Total OT Time</b>";
	
	/************************************************************************/
	/**					OT BOOKING MESSAGES END     	                   **/
	/************************************************************************/	
	
     /************************************************************************/
 	/**					OT REGISTER MESSAGES START     	                   **/
 	/************************************************************************/
	
     this.patientName = "Patient Name";
     this.doctorName = "Doctor Name";
     this.otNamelbl = "Operation Theater";
     this.surgeryName = "Surgery Name";
     this.fromDate = "From Date";
     this.toDate = "To Date";
     this.referenceType = "Reference Type";
     this.referenceNumber = "Reference No";
     this.otBookingNo = "OT Booking Nbr";
     this.serviceID = "Service Uid";
     this.serviceName = "Service Name";
     this.patient = "Patient";
     this.patientName = "Patient Name";
     this.doctorName = "Doctor Name";
     this.surgeryTime = "Surgery Time";
     this.action = "Action";
     
     
     /************************************************************************/
 	/**					OT REGISTER MESSAGES END     	                   **/
 	/************************************************************************/	
	
};

var otMsg = new OT.OTMessages();