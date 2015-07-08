 /**
 * @author Sandeep Kumar
 */
 
 Configs = function(){
 	
 	this.appointmentslot = "15";
 	this.doctorcolor = "GREEN";
 	this.patientcolor = "RED";
 	this.registrationFee = " 50.00";
 	this.registration = "Registration";
 	this.confirmed = "CONFIRMED";
 	this.appointmentServiceType = "Appointment";
 	this.doctor = "DOCTOR";
 	this.appointmentfee = "200.00";
 	this.Patient = "PATIENT";
 	this.appointment = "APPOINTMENT";
 	this.appointmentDisplayValue = "Appointment";
 	this.hospitalPatient = "Hospital Patient";
 	this.registrationTypeForBilling = "DIRECTSERVICEREGISTRATION";
 	this.doctorsAge = "18";
 	this.DoctorHasSeenPatient ="CAPTURED";
 	this.invalidRoster = "INVALID_ROSTER";
 	this.orderStatus_Admission = "IP_ADMISSION";
 	this.packageIndicator = "Y";

 	this.packageIndicatorNo = "N";
 	this.admmissionStatusRequested = "REQUESTED";

 	this.claimFullyApproved = "APPROVED";
 	this.claimPartiallyApproved = "PARTAPPROVED";
 	this.claimCreated = "CREATED";
 	this.claimSubmitted = "SUBMITTED";
 	this.claimMoreinfo = "MOREINFO";
 	this.claimResubmitted = "RESUBMITTED";
 	this.claimRejected = "REJECTED";
 	this.sponsorType = "SELF";
 	
 	this.referenceTypeForOPD ="OPD";
 	this.yes="yes";
 	this.referenceTypeForDirect ="DIR";
 	this.referenceTypeForIPD ="IPD";
 	this.PatientRegistrationType ="PAT";
 	this.ServiceStatus_Suspended ="SUSPENDED";
 	this.discountType_Absolute ="A";
 	this.discountType_Percentage = "P";
 	this.overrideLevel_Package ="P";
 	this.overrideLevel_Service = "S";
 	this.overrideLevel_Package_Lbl ="Package ";
 	this.overrideLevel_Service_Lbl = "Service ";
 	this.defaultServiceGroup ="GENERAL";
 	
 	this.isRosterRequiredName = "IS_ROSTER_REQUIRED";
 	this.isRosterRequiredValue = "Y";
 	
 	this.currencyIndicator = "CURRENCY_INDICATOR";
	
 	this.defailtPaymentReceiptMode ="CASH";
 	
 	this.defaultAmountValue = "0.00";
 	
 	this.emergencyRegistrationType ="EMERGENCY";
 	this.edit = 'edit';
 	this.add = 'add';
 	
 	this.SCHEDULE_STATUS_NOT_STARTED_CODE = "NOT_STARTED";
 	this.SCHEDULE_STATUS_NOT_STARTED_DESC = "Not started";
 	
 	this.QUALIFICATION = "QUALIFICATION";
 	
 	this.ENTITY_TYPE="ENTITY_TYPE";
 	
 	this.admissionOrderStatusConfirm = "ADMITTED";
 	this.admissionOrderStatusRequested ="REQUESTED";
 	this.admissionOrderStatusPreDischarge ="PRE_DISCHARGE";
 	this.admissionOrderStatusDischarge ="DISCHARED";
 	
 	//Doctor order status
 	this.orderStatusCreated = "CREATED";
 	this.orderStatusApproved = "APPROVED";
 	this.orderStatusDisapproved = "DISAPPROVED";
 	this.orderStatusCanceled = "CANCELED";
 	this.defValForAppointmentType = "PRIMARY";
 	this.defValForBookingType = "WALK";
 	this.appointment = 'appointment';
 	this.orderBy = 'ASC';
 	this.admissionEntryPoint = 'OPD';
 	
 	this.statusCompleted = "COMPLETED";
 	
 	//Generic formats for whole application
 	
 	this.dtmFormat = 'd-M-Y h:i:s a';
 }
 
  var configs = new Configs();
