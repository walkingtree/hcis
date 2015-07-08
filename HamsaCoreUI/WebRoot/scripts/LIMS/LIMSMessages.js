Ext.namespace("LIMS");

LIMS.LIMSMessages = function(){
	
	//labels
	this.lblTechnique = "Technique";
	this.lblReagent = "Reagent";
	this.lblTechniqueCode = "Code";
	this.lblTechniqueName = "Name";
	this.lblTechniqueDesc = "Description";
	this.lblTestName = "Test name";
	this.lblTechReagent = "Technique/Reagent?";
	this.lblTechniqueId = "Technique Id";
	
	this.lblTestType = "Test Type";
	this.lblTestApplicableFor = "Applicable for";
	this.lblLabName = "Lab Name";
	this.lblPreRequisites = "Pre-requisites";
	this.lblTimeRequires = "Time Required";
	this.lblReviewRequires = "Review Required?";
	this.lblDefaultReviewer = "Default Reviewer";
	
	this.lblSpecimen = "Specimen";
	this.lblSpecimenQtty = "Quantity";
	this.lblUnit = "Unit";
	this.lblIsMendatory = "Is Mandatory ?";
	
	this.lblAttributeName = "Attribute Name";
	this.lblAttributeCode = "Attribute Code";
	this.lblAttributeType = "Type";
	this.lblMinValue = "Min. Value";
	this.lblMaxVal = "Max. Value";
	this.lblPossibleValue="Possible Values";
	this.lblNumeric = "Numeric";
	this.lblText = "Text";
	this.lblObservation = "Observation";
	
	this.hospitalName = "Hospital Name";  
	this.laboratoryType = "Type";
	this.labId = "Lab. ID";
	this.laboratoryName = "Name";
	this.businessName = "Business name";
	this.branchName = "Branch name";
	this.directionFromKnownPlace = "Direction";
	this.labOperatorID = "Lab operator id";
	this.isLabinsidehospital =" Is lab inside hospital";
	this.street = "Street";
	this.locality = "Locality";
	this.city = "City";
	this.emailID = "Email ID";
	this.phoneNumber = "Phone Number";
	this.mobileNumber = "Mobile Number";
	this.faxNumber = "Fax";
	this.state = "State";
	this.country = "Country";
	
	// grid headers 

	this.techniqueList = "Technique/Reagent list";
	this.gridDefaultTitle = "Grid panel";
	
	// titels
	
	this.ttlAttributes = "Attributes";
	this.ttlSamples = "Specimens";
	this.ttlAddAttributes = "Add Attributes";
	this.ttlEditAttributes = "Edit Attributes";
	this.ttltestDetail = "Test Details";
	// general

	this.techniqueCode = "techniqueCode";
	this.technique = "technique";
	this.reagent = "reagent";
	this.techniqueName = "techniqueName";
	this.techniqueDesc = "techniqueDesc";
	this.defaultReviewerId = "defaultReviewerId";
	//lab test attribute
	this.attributeCode = "attributeCode";
	this.attributeName = "attributeName";
	this.attrUnit = "unit";
	this.attrType = "type";
	this.minValue = "minValue";
	this.maxValue = "maxValue";
	this.observationValue = "observationValue";
	
	this.editMode ="edit";
	this.addMode ="add";
	
	
	//buttons
	this.btnReset ="Reset";
	this.btnAdd ="Add";
	this.btnEdit ="Edit";
	this.btnDelete ="Delete";
	this.btnViewDetails ="View details";
	this.btnAddTemplate = "Associate Template";
	
	// titles
	
	this.ttlEditTechnique="Edit Technique/Reagent";
	this.ttlAddTechnique="Add Technique/Reagent";
	
	this.labdetails = "Lab information";
	this.resetMessage ="Do you want to reset?";
	this.contactDetail = "Contact Detail";
	this.deleteLabMsg = "Do you want to delete selected laboratory?";
	this.addLab = "Add Laboratory";
	this.viewDetails= "view Details";
	this.editLabDetail = "Edit lab detail";
	this.enterTestResult = "Enter test result";
	
	this.ttlResultChangeHistory = "Result value change history";
	
	
	//Messages
	
	this.deleteAttributeMsg = "Do you want to delete selected Attribute(s)?"
	this.deleteMessage ="Do you want to delete selected Technique/Reagent(s)?";
	this.noTechinqueMsg = "No technique/reagent found";
	
	this.msgAttributeAlreadyExist = "Attribute already exist !";
	
	this.msgSpecimenAlreadyExist = "Specimen already exist !"

	
	
	//REQUISITION ORDER MESSAGES
	
	this.testRequisitionStatus = "TEST_REQUISITION_STATUS";
	this.refType = "REFERENCE_TYPE";
	
	
	this.srchRequiOrder = "Search requisition order";
	this.srchCriteria = "Search criteria";
	this.srchRequiGrid = "Requisition list";
	this.requiDetails = "Detail for requisition- ";
	this.editRequisitionOrder = "Edit requisition order";
	this.addRequisitionOrder = "Add requsition order";

	
	//LABELS
	
	this.patientName = "Patient name";
	this.patientId = "Patient id";
	this.referenceType = "Reference type";
	this.doctorName = "Doctor name";
	this.doctorId = "Doctor id";
	this.requisitionFromDate = "Requisition(form date)";
	this.requisitionToDate = "Requisition(to date)";
	this.testName ="Test name";
	this.testStatus = "Test status";
	this.gender = "Gender";
	this.dob = "DOB";
	this.street ="Street";
	this.city = "City";
	this.country = "Country";
	this.state = "State";
	this.pinCode = "Pin code";
	this.phoneNbr = "Phone nbr";
	this.emailId = "Email id";
	this.contactPerson = "Contact person";
	this.relationship = "Relationship";
	
	//BUTTONS LABEL
	
	this.add = "Add";
	this.del = "Delete";
	this.view = "View";
	this.generateBill = "Generate bill";
	this.createReceipt = "Create receipt";
	
	
	//GRID HEADER
	
	this.number = "Number";
	this.testDate = "Test date";
	this.charges = "Charges";
	this.status = "Status";
	
	this.requisitionNbr = "Requisition Nbr";
	this.referringDoctorName = "Referring doctor name";
	this.requisitionDate = "Requisition date";
	this.totalCharges ="Total charges";
	
	//STATUS CONSTANTS
	
	this.SERVICE_STATUS_REQUESTED = "REQUESTED";
	this.SERVICE_STATUS_CANCELLED = "CANCELED";
	this.SERVICE_STATUS_TEST_PERFORMED = "TEST_PERFORMED";
	this.SERVICE_STATUS_SPECIMEN_COLLECTED= "SPECIMEN_COLLECTED";
	this.SERVICE_STATUS_RESULT_ENTERED = "RESULT_ENTERED";
	this.SERVICE_STATUS_APPROVED = "APPROVED";
	this.SERVICE_STATUS_DISAPPROVED = "DISAPPROVED";

	
	this.TEST_REQUISITION_STATUS_CREATED = "CREATED";
	this.TEST_REQUISITION_STATUS_CANCELLED = "CANCELED";
	this.TEST_REQUISITION_STATUS_COMPLETED = "COMPLETED";

	
	this.displayingTechniqueMsg = "Displaying Technique/Reagent(s) {0} - {1} of {2}";;
	this.displayingLabDetailMsg = "Display lab detail(s) {0} - {1} of {2}";
	this.noLabDetailMsg = "No lab detail found";
	this.requisitionSrchGridMsg =  "No requsition to display";
	this.requisitionDetailGridMsg = "No requsition details to display";
	this.attributeGridMsg = "Display attribute(s) {0} - {1} of {2}";
	this.noAttributeGridMsg = "No attribute found";
	
	
	this.laboratory = "LABORATORY";
	this.isFromRequsition = "isFromRequsition";
	
	//OTHER MESSAGES
	
	this.selectMsg = "Select...";
	
	//TEST TEMPLATE MESSAGES
	this.addTestTemplate = "Add test template";
	this.editTestTemplate = "Edit test template";
	this.availList = "Available list";
	this.patientDetail = "Patient detail";
	this.doctorDetail = "Doctor detail";
	this.testAttribute = "Test attribute";
	
	this.preview = "Preview";
	
	//CONSTANTS 
	this.SECTION_CODE_1 = "SECTION_1"; 
	this.SECTION_CODE_2 = "SECTION_2";
	this.SECTION_CODE_3 = "SECTION_3";
	this.SECTION_CODE_4 = "SECTION_4";
	this.SECTION_CODE_5 = "SECTION_5";
	
	
	this.ATTR_TYPE_NUMERIC = "NUMERIC";
	this.ATTR_TYPE_TEXT = "TEXT";
	this.ATTR_TYPE_OBSERVATION = "OBSERVATION";
	
		//TEST TEMPLATE CONSTANTS
	this.MODE = "MODE";
	this.MODE_EDIT = "EDIT";
	this.MODE_VIEW = "VIEW";
	this.MODE_APPROVE = "APPROVE";
	
	//WORNING MESSAGES
	
	this.msgSelectAttribute = " Please select attribute first.... ";
	
	this.mandatoryWidgetMsg = " Please select mandatory widgets....";
	
	// COLLECTION POINT
	
	this.collectionPointId = "Collection point id";
	this.collectionPointName = "Collection point name";
	this.areaCovered = "Area covered";
	this.labList = "Lab list";
	this.associatedLab = "Associated lab"; 
	this.addCollectionPoint = "Add collection point";
	this.editCollectionPoint = "Edit collection point";
	
	this.collectionPointSearchGridMsg = "No collection point to display";
	
	this.pagingbarDisplayMsg = "Displaying records {0} - {1} of {2}";
	
	
};

 var limsMsg = new LIMS.LIMSMessages();