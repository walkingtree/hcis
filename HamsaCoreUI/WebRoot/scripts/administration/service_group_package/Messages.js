Ext.namespace("administration.service_group_package");

administration.service_group_package.Messages= function(){
	
	//Titles
	this.addService = "Add service";
	this.editService = "Edit service";
	
	this.addServiceGroup = "Add service group";
	this.editServiceGroup = "Edit service group";
	
	this.addPackage = "Add package";
	this.editPackage = "Edit package";
	
	this.availableServices = "Avaialble Services";
	this.selectedServices = "Selected Services";
	this.associatedServices = "Associated Services";
	
	//Labels
	this.code = "Code";
	this.name = "Name";
	this.description = "Description";
	this.charge = "Charge";
	this.department = "Department";
	this.status = "Status";
	this.serviceGroup = "Service group";
	this.serviceName = "Service name";
	this.effectiveFrom = "Effective from";
	this.effectiveTo = "Effective to";
	this.suspendFrom = "Suspend from";
	this.suspendTo = "Suspend to";
	this.procedure = "Procedure";
	this.depositAmount = "Deposit amount";
	this.noOfUnits = "No.of units";
	this.chargeIntoUnits = "Charge * No.of units in(Rs)";
	
	this.parentGroup = "Parent group";

	this.discountType = "Discount type";
	this.discountAmt = "Discount amount";
	this.discountPct = "Discount percentage";
	this.effectiveCharge = "Effective charge";
	this.discount ="Discount";
	
	this.packageCharge = "Package charge";
	this.serviceCharge = "Service charge";
	this.chargeAfterDscnt = "Charge after discount";
	
	this.createdAfter = "Created after";
	this.createdBefore = "Created before";
	
	this.chargesFrom = "Charges from";
	this.chargesTo = "Charges to";
	
	this.total = "TOTAL";
	this.chargeOverrideLevel = "Charge override level";
	this.overrideLevel ="Override level";
	this.priceOverrideLevel =" Price override level";
	this.serviceType = "Service type";
			
	//Buttons
	this.btnSearch = "Search";
	this.btnAdd = "Add";
	this.btnEdit = "Edit";
	this.btnSave = "Save";
	this.btnReset = "Reset";
	this.btnDelete = "Delete";
	
	this.associateToPkgBtn = "Associate to Package";
	
	this.addButton =       "    Add >    ";
	this.addAllButton =    "   Add All >>";	
	this.removeButton =    "  < Remove   ";
	this.removeAllButton = "<< Remove All";	 
	
	//Tool tips
	this.saveService ="Save service";
	this.saveServiceGroup ="Save serviceGroup";
	this.savePackage ="Save Package";
	
	this.resetService ="Reset service";
	this.resetServiceGroup ="Reset service group";
	this.resetPackage = "Reset Package";
	
	this.addEntryIntoGrid ="Add entry into grid";
	this.editGridEntry ="Edit entry from grid";
	this.deleteEntryFormGrid ="Delete Entry(s) from grid";
	
	this.addService ="Add service";
	this.editService ="Edit service";
	this.deleteService ="Delete service(s)";
	
	this.addServiceGroup ="Add service group";
	this.editServiceGroup ="Edit service group";
	this.deleteServiceGroup ="Delete service group(s)";
	
	this.addServicePackage ="Add service package";
	this.editServicePackage ="Edit service package";
	this.deleteServicePackage ="Delete service package(s)";
	
	this.searchService ="Search for services";
	this.searchServiceGroup ="Search for service groups";
	this.searchServicePackage ="Search for service packages";
	
	this.addIntoAssignedServices = "Add Service(s) Into assigned services";
	this.removeFromAssignedServices = "Remove service(s) from assigned services";
	
	this.resetSearchCreteria =" Reset search criteria";
	this.configurePriceDetail = " Configure price detail";
	//Miscellaneous
	this.activeStatus = "1";
	this.inactiveStatus = "0";
	this.isActive = "Is active?";
	
	this.addMode = 'add';
	this.editMode = 'edit';
	
	this.displayingServicesMsg = "Displaying services {0} - {1} of {2}";
	this.noServicesMsg = "No services found";
	this.addServiceSuccessMsg = "Service has been created successfully..!";
	this.editServiceSuccessMsg = "Service has been updated successfully..!";
	this.deleteServiceSuccessMsg = "Service(s) has been deleted successfully..!";

	this.displayingServiceGroupsMsg = "Displaying service groups {0} - {1} of {2}";
	this.noServiceGroupsMsg = "No service groups found";
	this.addServiceGroupSuccessMsg = "Service group has been created successfully..!";
	this.editServiceGroupSuccessMsg = "Service group has been updated successfully..!";
	this.deleteServiceGroupSuccessMsg = "Service group(s) has been deleted successfully..!";
	
	this.displayingServicePackagesMsg = "Displaying packages {0} - {1} of {2}";
	this.noServicePackageMsg = "No packages found";
	this.addServicePackageSuccessMsg = "Service package has been created successfully..!";
	this.editServicePackageSuccessMsg = "Service package has been updated successfully..!";
	this.deleteServicePackageSuccessMsg = "Service package(s) has been deleted successfully..!";

	this.deleteServiceMsg = "Do you want to delete selected service(s)?";
	this.deleteServiceGroupMsg =" Do you want to delete selected service group(s)?";
	this.deleteServicePackageMsg =" Do you want to delete selected package(s)?";
	
	this.invalidData = "Data entered is valid. Please re-enter..!";
	this.effectiveChargeInvalid ="Effective charge should not be negative value";
	
	this.atleastOneServiceAssociationIsRequired = "While creating a package atleast one service must be associate with package";
	this.effectiveChargeNotEmpty = "Effective charge should not be Empty..!";
	this.serviceAlreadyExists = " selected service already exist in Grid. Please select another service and retry..!";
	
	this.serviceAlreadyExistsWithPackage = " selected service already associated with package. Please select another service and retry..!";
	
	this.serviceExists = " service code already exist in system. Please type another service code and retry..!";
	this.serviceGroupExists = " service group code already exist in system. Please type another service group code and retry..!";;
	this.packageExists =" package code  already exist in system. Please type another package code and retry..!"
	this.resetSearchCreteria ="reset search creteria";
	
	this.doUWantToSwitchFromPacToSvc = "If you switch service level earlier details will be lose.Do you want to switch?";
	this.doUWantToSwitchFromSvcToPac = "If you switch package level earlier details will be lose.Do you want to switch?";
	this.suspendedStatus = "SUSPENDED";
	this.selectMsg = "Select...";
	this.defaultDateFormat ="dd/mm/YYYY";
	this.defaultValueForUnits ="1";
	this.defaultValueForAmount = "0.00";
	this.minValueForAmount = 0;
	
	this.serviceTypeLaboratory="LABORATORY";
	this.editTemplate = "Edit Template";
	
	// Surgery Messages
	
	this.serviceTypeSurgery = "SURGICAL";

}
 
var svcAndGrpAndPkgMsg = new administration.service_group_package.Messages();
