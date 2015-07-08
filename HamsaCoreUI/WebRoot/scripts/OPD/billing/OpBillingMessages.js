Ext.namespace("OPD.billing");

OPD.billing.OpBillingMessages= function(){
	
	//Labels
	this.code = "Code";
	this.name = "Name";
	this.description = "Description";
	this.charge = "Charge";
	this.department = "Department";
	this.status = "Status";
	this.serviceGroup = "Service group";
	this.serviceName = "Service name";
	this.servicePackage = "Service Package";
	this.depositAmount = "Deposit amount";
	this.noOfUnits = "No.of units";
	this.chargeIntoUnits = "Charge * NoindividualServiceCbx.of units";
	this.packageIndicator =" Package indicator";
	this.generateBillAndReceipt = "Bill and Receipt";
	
	this.patientid = "Patient id";
	this.gender = "Gendar";
	this.title = "Title";
	this.firstName = "First name";
	this.middleName = "Middle name";
	this.lastName = "Last name";
	this.dateofbirth = "Date of birth";
	this.houseNo = "House no";
	this.placeStreet = "Place/Street";
	this.cityDistrict = "City/District";
	this.state = "State";
	this.country = "Country";
	this.pincode = "Pincode";
	this.phoneNumber = "Phone number";
	this.mobileNumber = "Moblie Number";
	this.serviceDate = "Service date";
	this.appointmentNumber ="Appointment No";
	
	this.referralType = "Referral type";
	this.referralName = "Referral name";
	
	// buttons
	this.edit = "Edit";
	this.del = "Delete";
	this.add = "Add";
	this.generateBill = "Generate bill";
	this.reset ="Reset";
	this.ok ="Ok";
	this.cancel ="Cancel";
	this.getServices = "Get services";
	this.createReceipt = "Create receipt";
	
	//titles
	this.assignementOfServices = "Assignement of Services/Packages";
	this.patientPersonalDetails = "Patient personal details";
	this.patientContactDetails = "Patient contact details";
	this.opBillingWindow = "Op billing ";
	this.appointmentDetails = "Appointment details";
	
	//prompt messages
	this.selectPatient ="Please specify patient id and retry..!";
	this.resetMessage ="Do you want to reset ?";
	this.selectServiceOrPackage ="Please select any service/package and retry..! ";
	
	//tooltips
	this.addEntryIntoGrid ="Add entry into grid";
	this.editGridEntry ="Edit entry from grid";
	this.deleteEntryFormGrid ="Delete Entry(s) from grid";
	this.findAppointment ="Find appointments";
	this.generateBill ="Generate bill";
	this.retetData ="Reset data";
	
	//label for Radio button
	this.individualService = "Individual service";
	this.serviceGroup = "Service group";
	this.servicePackage = "Service Package";
	this.test = "Test";
	this.expectedDateAndTime = "Expected date & time";
	this.emergency = "Emergency?"
	this.selectTest ="Please select any test and retry..! ";
	
	this.billNumber = "Bill number";
	
}

var opBillingMsg = new OPD.billing.OpBillingMessages();