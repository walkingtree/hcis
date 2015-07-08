Ext.namespace("OPD.serviceAssignement");

OPD.serviceAssignement.Messages = function(){
	
	// grid related messages
	this.pagingBarEmptyMsg ="No assigned services to display";
	this.GridDisplayInfo = " Displaying assigned services {0} - {1} of {2}";
	
	
	// grid header labels
	this.serviceCode ="Service code";
	this.serviceName ="Service name";
	this.packageId = "Package ID";
	this.packageName ="Package name";
	this.serviceStatus ="Service status";
	this.renderedUnits ="Rendered units";
	this.requestedUnits = "Requested units";
	this.billedUnits ="Billed units";
	this.lastBillNbr ="Last bill number";
	this.canceledUnits ="Canceled units";
	this.serviceDate ="Service date";
	this.patientId ="Patient id";
	this.packageAssignementId =" Package assignment Id";
	
	this.alreadyRenderUnits = "Rendered units";
	this.alreadyCalceledUnits = "Canceled units";
	
	this.patient ="Patient"; 
	this.packageName ="Package name";
	this.packageCode ="Package code";
	this.refNbr =" Ref Number";
	this.reftype ="Ref type";
	this.department ="Department";
	this.serviceGroup ="Service Group";
	this.effectiveFrom = "Effective from";
	this.effectiveTo ="Effective to";
	
	this.serviceDateFrom ="Service date from";
	this.serviceDateTo ="Service date to";
	
	this.requestedUnits ="Requested units";
	
	
	// form names
	this.formServiceName ="serviceName";
	this.formPackageName ="PackageName";
	this.formPackageCode ="packageCode";
	this.formServiceCode ="serviceCode";
	this.formPatient ="patient";
	this.formRefNbr ="refNbr";
	this.formRefType ="refType";
	this.formServiceGroup ="serviceGroup";
	this.formDepartment = "department";
	this.formEffectiveFrom = "serviceFromDt";
	this.formEffectiveTo = "serviceToDt";
	this.formRequestedUnits ="requestedUnits";
	this.fromenteredUnits  ="enteredUnits";
	
	// buttons
	this.btnRender ="Render";
	this.btnCancel ="Cancel";
	this.btnViewAssignments ="View assignments";
	this.btnDelete ="Delete";
	this.btnReplace ="Replace";
	this.btnSearch ="Search";
	this.btnOk ="Ok";
	
	// mis
	this.defaultUnits =0;
	this.defaultUnitsLbl ="Enter units";
	
	// tool tips
	this.renderAssignedService = "Render assigned service";
	this.cancelAssignedService = "Cancel assigned service";
	this.repalceAssignedService = "replace assigned service";
	this.deleteAssignedService = "delete assigned service(s)";
	this.viewAssignedService = "view assigned service";
	this.resetSearchCreteria = " reset search creteria";
	this.searchAssignedServices =" search assigned services";
	
	// titles
	this.forRenderingServices ="For rendering services";
	this.forCancelServices ="For canceling Services";
	
	this.enteredUnitsShouldNotZero ="Entered units should not be zero / negative value";
	this.deleteAssignedServiceMsg = "Do you want to delete selected assigned service(s) ?";
	
	// constants
	this.ASSIGNED_SERVICE_APPROVED = "APPROVED";
	this.SERVICE_TYPE_LABORATORY = "LABORATORY";
	
	// ASSIGNED SERVICES STATUS
	
	this.ASSIGNED_SERVICE_STATUS_RENDERED = "RENDERED";
	this.ASSIGNED_SERVICE_STATUS_CANCELED = "CANCELED";
	this.ASSIGNED_SERVICE_STATUS_DISAPPROVED = "DISAPPROVED";
}

var mngSvcAsgntMsg = new OPD.serviceAssignement.Messages();