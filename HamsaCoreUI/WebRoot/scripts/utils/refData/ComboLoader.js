
// Referral
var referralTypeStore;
var comissionTypeStore;
var stateStore;
var countryStore;
var stateForSelectedCountryStore;

// SERVICE 
var serviceStatusStore;
var svcPkgStatusStore;
var dscntTypesStore;
var chargeOverrideTypesStore;
var servicePackageStore;
var serviceGroupTypesStore;
var serviceNameStore;
var serviceTypeStore;
var labTestServiceStore;

// SERVICE ASSIGNEMENT
var referenceTypesStore;

//ENTITY MANAGEMENT
var entityStore;

//GENARAL
var departmentsStore;
var especialityStore;
var specialtyForDepartmentStore;
var heightIndicatorStore;
var weightIndicatorStore;

// USER
var userManagerRoleStore;
var userManagerStateStore;
var userManagerCountryStore;

// PHARMACY
var statusTypeStore;
var brandStore;
var medicineStore;
var medicineTypeStore;

//PATIENT REGISTRATION
var titleStore;
var registrationTypeStore;
var registrationStatusStore;
var patientRatingStore;
var patientCategoryStore;
var genderStore;
var bloodGroupStore;
var maritalStatusStore;
var religionStore;
var motherTongueStore;
var idProofStore;
var nationalityStore;
var allergiesStore;
var immunizationsStore;
var relationshipStore;
var patientStore;

//APPOINTMENT
var referralStore;
var appointmentsStatusStore;
var bookingTypeStore;
var doctorsStore;
var cancellationReasonStore;
var appointmentTypeStore;

//ROSTER
var entityType;
var weekDaysStore;
var yearsStore;
var monthsStore;
var fromWeekStore;
var toWeekStore;
var entityNameStore;
var rosterEntityTypeStore;
var rosterModeForCreationStore

//Reports

var reportNameStore

// IPD (ADD AND MANAGE)

var sponsorStore;
var insurerStore;
var activtyStore;
var sponsorTypeStore;
var creditClassStore;
var claimStore;
var admissionEntryPointStore;

// VACCINATION SCHEDULE
var vaccinationTypeStore;
var vaccinationStore;
var periodIndicatorStore;
var diseaseStore;
var activeVaccinationScheduleFlagStore;
var vaccinationScheduleStore;

//BED
var nursingUnitsStore;
var bedFeaturesStore;
var bedTypeStore;
var bedStatusStore;
var nursingUnitTypesStore;
var facilityTypeStore;
var bedPoolStore;
var roomNoStore;
var admRequestNoStore;

//IPD
var doctorOrderGroupStore;
var doctorOrderTemplatesStore;
var doctorOrderTypeStore;
var doctorOrderStatusStore;
var admissionStatusStore;

//ACCOUNTING
var creditCardTypeStore;
var transactionTypeStore;
var paymentModeStore;

var qualificationStore;


function record(){
	var record = Ext.data.Record.create([
	  {name: "code", type: "string"},
	  {name: "description", type: "string"},
	  {name : "isDefault"}
	]);
	return record;
}

function reportsNameStore(){
	if(Ext.isEmpty( reportNameStore )){
		reportNameStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getReportNameDataList, true),
		    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
			
		});
		reportNameStore.load({params:{start:0, limit:8}, arg:[]});
	}
	
	return reportNameStore;
}

function loadTitleCbx(){
	if( Ext.isEmpty( titleStore )){
		titleStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getTitle, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  titleStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return titleStore;
}

function loadDiseaseCbx(){
	if( Ext.isEmpty( diseaseStore ) ){
		diseaseStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(DataModelManager.getDisease, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  diseaseStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return diseaseStore;
}


// Referral
function getReferralTypes(){

	var recordItemType = new record();
	if( Ext.isEmpty( referralTypeStore ) ){


		 referralTypeStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(ReferralManager.getReferralTypes, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, recordItemType),
		    remoteSort: true
	  });
	  referralTypeStore.load({params:{start:0, limit:99}, arg:[]});

	}
	
	return referralTypeStore;
}

function getReferralNameForType(){

	var recordItemType = new record();
	referralNameStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(ReferralManager.getReferralNameOfType, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, recordItemType),
	    remoteSort: true
    });
	return referralNameStore;
}


function getComisssionTypes(){
	
	if( Ext.isEmpty( comissionTypeStore ) ){
		var recordItemType = new record();
		comissionTypeStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(ReferralManager.getCommissionTypeInd, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, recordItemType),
		    remoteSort: true
		});
		comissionTypeStore.load({params:{start:0, limit:99}, arg:[]});
	}
	
	return comissionTypeStore;
}

function loadCountryCbx(){
	if( Ext.isEmpty( countryStore )){
		countryStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getCountry, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  countryStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return countryStore;
}

function loadStateCbx(){
	if( Ext.isEmpty( stateStore )){
		stateStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getState, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  stateStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return stateStore;
}

function loadStateForSelectedCountryCbx(){
//	if( Ext.isEmpty( stateForSelectedCountryStore )){
		stateForSelectedCountryStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getStateWithCountry, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
//	}
	return stateForSelectedCountryStore;
}
loadStateForSelectedCountryCbx();

// SERVICE MANAGEMENT START

function loadServiceStatusCbx(){
	if(Ext.isEmpty(serviceStatusStore)){
		serviceStatusStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getServiceStatusList, true),
		    reader: new Ext.data.ListRangeReader( {totalProperty:'totalSize'}, record()),
		    remoteSort: true
  		});
  		serviceStatusStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return serviceStatusStore;
}

function loadPackageServiceStatusCbx(){
	if( Ext.isEmpty( svcPkgStatusStore ) ){
		svcPkgStatusStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getServicePackageStatus, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
		  });
		  
		  svcPkgStatusStore.load({params:{start:0, limit:9999}, arg:[]});
	}
  	return svcPkgStatusStore;
}

function loadDiscountTypeCbx(){
	if( Ext.isEmpty( dscntTypesStore ) ){
		dscntTypesStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getDiscountTypesList, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
		  });
		  
		  dscntTypesStore.load({params:{start:0, limit:9999}, arg:[]});
	}
  	return dscntTypesStore;
}

function loadChargeOverrideTypeCbx(){
	if( Ext.isEmpty( chargeOverrideTypesStore ) ){
		chargeOverrideTypesStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getChargeOverrideTypesList, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
		  });
		  
		  chargeOverrideTypesStore.load({params:{start:0, limit:9999}, arg:[]});
	}
  	return chargeOverrideTypesStore;
}

function recordforServicePackage(){
	var record = Ext.data.Record.create([
	  {name: "code", mapping: 'packageId',type: "string"},
	  {name: "description",mapping: 'name', type: "string"},
	  {name: "serviceCharge",mapping: 'effectiveCharge'}
	]);
	return record;
}
function loadServicePackageCbx(){
	if( Ext.isEmpty(servicePackageStore) ){
		 servicePackageStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(ServiceManager.findAvailableServicePackages, true),
		    reader: new Ext.data.ListRangeReader(
		    	{id:'code', totalProperty:'totalSize'}, recordforServicePackage()),
		    remoteSort: true
	  	});
	  	servicePackageStore.load({params:{start:0, limit:999}, arg:[]});
	}
	return servicePackageStore;
}

function loadAddServiceGroupCbx(){
	if( Ext.isEmpty(serviceGroupTypesStore) ){
		 serviceGroupTypesStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getServiceGroupList, true),
		    reader: new Ext.data.ListRangeReader(
		    	{id:'code', totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  	});
	  	serviceGroupTypesStore.load({params:{start:0, limit:999}, arg:[]});
	}
	return serviceGroupTypesStore;
}

function loadServicesCbx(){
	if( Ext.isEmpty(serviceNameStore) ){
		serviceNameStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getService, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  serviceNameStore.load({params:{start:0, limit:999}, arg:[]});
	}
	return serviceNameStore;
}

function loadServiceTypeCbx(){
	if( Ext.isEmpty(serviceTypeStore) ){
		serviceTypeStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getServiceTypes, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  serviceTypeStore.load({params:{start:0, limit:999}, arg:[]});
	}
	return serviceTypeStore;
}

function loadLabTestServiceCbx(){
	
	
		var record = Ext.data.Record.create([
             {name: "code", mapping : "serviceCode", type: "string"},
             {name: "description", mapping :"serviceName" , type: "string"},
             {name : "serviceCharge",mapping :"serviceCharge" }
         ]);
		labTestServiceStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getServiceByServiceTypeCode, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record),
		    remoteSort: true
		});
		labTestServiceStore.load({params:{start:0, limit:999}, arg:[limsMsg.laboratory]});
		return labTestServiceStore;
}


// SERVICE MANAGEMENT END LabTestServiceCbxStore

// GENERAL START

function loadDepartmentsCbx(){
	if( Ext.isEmpty( departmentsStore ) ){
		departmentsStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getDepartments, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  departmentsStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return departmentsStore;
}

function loadEspecialityCbx(){
	if(Ext.isEmpty(especialityStore)) {
		especialityStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getEspectiality, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  especialityStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return especialityStore;
}

function loadSpecialtyForDepartmentCbx(){
	specialtyForDepartmentStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(DataModelManager.getSpeacialityForDepartmant, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
    });
	return specialtyForDepartmentStore;
}
loadSpecialtyForDepartmentCbx();

function loadHeightIndicatorStore() {
	if( Ext.isEmpty(heightIndicatorStore) ){
		heightIndicatorStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getHeightIndicators, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  heightIndicatorStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return heightIndicatorStore;
}

function loadWeightIndicatorStore() {
	if( Ext.isEmpty(weightIndicatorStore) ){
		weightIndicatorStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getWeightIndicators, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  weightIndicatorStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return weightIndicatorStore;
}

function loadRoomName( doctorId ) {
//	if( Ext.isEmpty(roomNoStore) ){
		roomNoStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getRoomsForDoctor, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  roomNoStore.load({params:{start:0, limit:9999}, arg:[ doctorId ]});
//	}
	return roomNoStore;
}
//GENERAL END

//USER MANAGEMENT START 

function UserManagerloadCountryCbx(){
	if( Ext.isEmpty( userManagerCountryStore )){
		userManagerCountryStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(ReferenceDataManager.getCountry, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  userManagerCountryStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return userManagerCountryStore;
}

function UserManagerloadStateCbx(){
	if( Ext.isEmpty( userManagerStateStore )){
		userManagerStateStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(ReferenceDataManager.getState, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	}
	return userManagerStateStore;
}

function UserManagerloadRoleCbx(){
	if( Ext.isEmpty( userManagerRoleStore )){
		userManagerRoleStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(ReferenceDataManager.getRoles, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  userManagerRoleStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return userManagerRoleStore;
}

// USER MANAGEMENT END

// PHARMACY MANAGEMENT START
function loadStatusType(){
	if( Ext.isEmpty( statusTypeStore ) ){
		statusTypeStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(DataModelManager.getStatus, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  statusTypeStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return statusTypeStore;
}
loadStatusType();

function loadBrand(){
	if( Ext.isEmpty( brandStore ) ){
		brandStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getBrand, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  brandStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return brandStore;
}

function loadMedicineCbx(){
	if( Ext.isEmpty( medicineStore ) ){
		medicineStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getMedicines, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  medicineStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return medicineStore;
}

function loadMedicineType(){
	if( Ext.isEmpty( medicineTypeStore ) ){
		medicineTypeStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getMedicineType, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  medicineTypeStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return medicineTypeStore;
}
// PHARMACY MANAGEMENT END

//ENTITY MANAGEMENT START

function loadEntityCbx(){
	if( Ext.isEmpty( entityStore ) ){
		entityStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(DataModelManager.getReferenceDataList, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
		entityStore.load({params:{start:0, limit:9999}, arg:[configs.ENTITY_TYPE]});
	}
	return entityStore;
}

//ENTITY MANAGEMENT END


//PATIENT REGISTRATION START
function loadRegistrationTypeCbx(){
  if( Ext.isEmpty( registrationTypeStore )){
  		registrationTypeStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getRegistrationType, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  registrationTypeStore.load({params:{start:0, limit:8}, arg:[]});
  }
  return registrationTypeStore;
}

function loadRegistrationStatusCbx(){
	if( Ext.isEmpty( registrationStatusStore )){
		registrationStatusStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getRegistrationStatus, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  registrationStatusStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return registrationStatusStore;
}

function loadPatientRatingCbx(){
	if( Ext.isEmpty( patientRatingStore )){
		patientRatingStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getPatientRating, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  patientRatingStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return patientRatingStore;
}

function loadPatientCategoryCbx(){
	if( Ext.isEmpty( patientCategoryStore )){
		patientCategoryStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getPatientCategory, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  patientCategoryStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return patientCategoryStore;
}

function loadGenderCbx(){
	if( Ext.isEmpty( genderStore )){
		genderStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getGender, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  genderStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return genderStore;
}

function loadBloodGroupCbx(){
	if( Ext.isEmpty( bloodGroupStore )){
		bloodGroupStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getBloodGroup, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  bloodGroupStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return bloodGroupStore;
}

function loadMaritalStatusCbx(){
	if( Ext.isEmpty( maritalStatusStore )){
		maritalStatusStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getMaritalStatus, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  maritalStatusStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return maritalStatusStore;
}

function loadReligionCbx(){
	if( Ext.isEmpty( religionStore )){
		religionStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getReligion, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  religionStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return religionStore;
}

function loadMotherTongueCbx(){
	if( Ext.isEmpty( motherTongueStore )){
		motherTongueStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getMotherTongue, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  motherTongueStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return motherTongueStore;
}

function loadIdProofCbx(){
	if( Ext.isEmpty( idProofStore )){
		idProofStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getIdProof, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  idProofStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return idProofStore;
}

function loadNationalityCbx(){
	if(Ext.isEmpty(nationalityStore)) {
		nationalityStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getNationality, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
		  });
		  nationalityStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return nationalityStore;
}

function loadAllergies() {
	if( Ext.isEmpty( allergiesStore )){
		allergiesStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getAllergies, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
		  });
		  allergiesStore.load({params:{start:0, limit:3000}, arg:[]});
	}
	return allergiesStore;
}

function loadImmunizations() {
	if( Ext.isEmpty( immunizationsStore )){
		immunizationsStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getImmunizations, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
		  });
		  immunizationsStore.load({params:{start:0, limit:3000}, arg:[]});
	}
	return immunizationsStore;
}

function loadRelationshipCbx(){
	if( Ext.isEmpty( relationshipStore )){
		relationshipStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getRelationship, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  relationshipStore.load({params:{start:0, limit:8}, arg:[]});
	}
	  return relationshipStore;
}

function loadPatientCbx(){
	if( Ext.isEmpty( patientStore ) ){
		patientStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(DataModelManager.getPatients, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  patientStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return patientStore;
}

loadPatientCbx();
//PATIENT REGISTRATION END

// SERVICE ASSIGNEMENT START
function loadReferenceTypeCbx(){
	if( Ext.isEmpty( referenceTypesStore ) ){
		referenceTypesStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getReferenceTypeList, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
		  });
		  
		  referenceTypesStore.load({params:{start:0, limit:9999}, arg:[]});
	}
  	return referenceTypesStore;
}
// SERVICE ASSIGNEMENT END

//APPOINTMENT START
function loadReferralsCbx(){
	if( Ext.isEmpty( referralStore )){
		referralStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getReferral, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  referralStore.load({params:{start:0, limit:8}, arg:[]});
	}
	  return referralStore;
}

function loadAppointmentsStatusCbx() {
	if( Ext.isEmpty( appointmentsStatusStore )  ){
		appointmentsStatusStore = new Ext.data.Store({
				proxy : new Ext.data.DWRProxy(DataModelManager.getAppointmentWithStatus, true),
				reader : new Ext.data.ListRangeReader({totalProperty : 'totalSize'}, record()),
				remoteSort : true
		});
		appointmentsStatusStore.load({params : {start:0, limit:8}, arg:[] });
	}
	return appointmentsStatusStore;
}

function loadBookingTypesCbx() {
	if( Ext.isEmpty( bookingTypeStore ) ){
		bookingTypeStore = new Ext.data.Store({
			proxy : new Ext.data.DWRProxy(DataModelManager.getBookingTypes,true),
			reader : new Ext.data.ListRangeReader(
				{totalProperty : 'totalSize'}, record()),
			remoteSort : true
		});
		bookingTypeStore.load({params : {start : 0,limit : 8},arg : []});
	}
	return bookingTypeStore;
}

function loadDoctorsCbx(){
//	if( Ext.isEmpty( doctorsStore ) ){
		doctorsStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getDoctors, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  	});
	  	doctorsStore.load({params:{start:0, limit:8}, arg:[]});
//	}
	return doctorsStore;
}
loadDoctorsCbx();
function loadCancellationReasonCbx(){
	if(Ext.isEmpty(cancellationReasonStore)) {
		cancellationReasonStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getCancellationReason, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
		  });
	  
	  	cancellationReasonStore.load({params:{start:0, limit:8}, arg:[]});
	}
	  return cancellationReasonStore;
}

function loadAppointmentTypeCbx(){
	if(Ext.isEmpty( appointmentTypeStore )) {
		appointmentTypeStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getAppointmentType, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
		  });
	  
	  	appointmentTypeStore.load({params:{start:0, limit:8}, arg:[]});
	}
	  return appointmentTypeStore;
}



loadDoctorsCbx();

function loadQualificationCbx(){
	if( Ext.isEmpty( qualificationStore ) ){
		qualificationStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(DataModelManager.getReferenceDataList, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
		qualificationStore.load({params:{start:0, limit:9999}, arg:[configs.QUALIFICATION]});
	}
	return qualificationStore;
}
//APPOINTMENT END

//ROSTER
function loadEntityType() {
	if( Ext.isEmpty(entityType) ){
		entityType = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getEntityType, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
  	  	entityType.load({params:{start:0, limit:8}, arg:[]});
	}
  	return entityType;
}


function loadWeekDays() {
	if( Ext.isEmpty(weekDaysStore) ){
		weekDaysStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getWeekDays, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  weekDaysStore.load({params:{start:0, limit:999}, arg:[]});
	}
	  return weekDaysStore;
}

function loadYears() {
	if(  Ext.isEmpty(yearsStore) ){
		yearsStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getYears, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  yearsStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return yearsStore;
}

function loadMonths() {
	if( Ext.isEmpty(monthsStore) ){
		monthsStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getMonths, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  monthsStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return monthsStore;
}

function loadFromWeeks(){
	if(Ext.isEmpty(fromWeekStore)){
		fromWeekStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getWeeks, true),
		    reader: new Ext.data.ListRangeReader( {totalProperty:'totalSize'}, record()),
		    remoteSort: true
  		});
	}
	return fromWeekStore;
}

function loadToWeeks(){
	if(Ext.isEmpty(toWeekStore)){
		toWeekStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getWeeks, true),
		    reader: new Ext.data.ListRangeReader( {totalProperty:'totalSize'}, record()),
		    remoteSort: true
  		});
	}
	return toWeekStore;
}

function loadEntityName(){
	if(Ext.isEmpty(entityNameStore)){
		entityNameStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getIdsForEntity, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	    });
	}
	return entityNameStore;
}

function loadRosterEntities(){
	if(Ext.isEmpty( rosterEntityTypeStore )){
		rosterEntityTypeStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getRosterEntities, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	    });
	    rosterEntityTypeStore.load({params:{start:0, limit:10}, arg:[]});
	}
	return rosterEntityTypeStore;
}

function loadRosterModeStore(){
	if(Ext.isEmpty( rosterModeForCreationStore )){
		rosterModeForCreationStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getReferenceDataList, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	    });
		rosterModeForCreationStore.load({params:{start:0, limit:10}, arg:[msg.rosterMode]});
	}
	return rosterModeForCreationStore;
}

//ROSTER END

//VACCINATION START 
function loadVaccinationTypeStoreCbx(){
	if( Ext.isEmpty( vaccinationTypeStore )){
		vaccinationTypeStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getVaccinationType, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  vaccinationTypeStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return vaccinationTypeStore;
}

function loadActiveVaccinationScheduleFlagStoreCbx(){
	if( Ext.isEmpty( activeVaccinationScheduleFlagStore )){
		activeVaccinationScheduleFlagStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getActiveVaccinationScheduleFlag, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  activeVaccinationScheduleFlagStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return activeVaccinationScheduleFlagStore;
}

function loadVaccinationStoreCbx(){
	if( Ext.isEmpty( vaccinationStore )){
		vaccinationStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getVaccinations, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  vaccinationStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return vaccinationStore;
}

function loadPeriodIndicatorStoreCbx(){
	if( Ext.isEmpty( periodIndicatorStore )){
		periodIndicatorStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getPeriodIndictors, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  periodIndicatorStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return periodIndicatorStore;
}

function loadVaccinationScheduleCbx(){
	if( Ext.isEmpty( vaccinationScheduleStore )){
		vaccinationScheduleStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getActiveVaccinationSchedule, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
	  vaccinationScheduleStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return vaccinationScheduleStore;
}
//VACCINATION END

// START IPD

function loadSponsorsCbx(){
	if( Ext.isEmpty( sponsorStore ) ){
	 sponsorStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getClaimSponsors, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  sponsorStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return sponsorStore;
}

function loadInsurerCbx(){
	if( Ext.isEmpty( insurerStore ) ){
		insurerStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getInsurer, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	   });
	   insurerStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	  return insurerStore;
}

function loadActivityCbx(){
	if( Ext.isEmpty( activtyStore ) ){
		activtyStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getActivityTypeForGroup, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  activtyStore.load({params:{start:0, limit:9999}, arg:['MEDICLAIM']});
	}
	return activtyStore;
}

function loadSponsorTypeCbx(){
	if( Ext.isEmpty( sponsorTypeStore ) ){
		sponsorTypeStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getSponsorType, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  sponsorTypeStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return sponsorTypeStore;
}

function loadCreditClassCbx(){
	if( Ext.isEmpty( creditClassStore ) ){
		creditClassStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getCreditClass, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  creditClassStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return creditClassStore;
}

function loadClaimStatusCbx(){
	if( Ext.isEmpty( claimStore ) ){
		claimStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getSponsorClaimStatus, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  claimStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return claimStore;
}

function loadDoctorOrderGroup(){
	if( Ext.isEmpty( doctorOrderGroupStore ) ){
		doctorOrderGroupStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getDoctorOrderGroup, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  doctorOrderGroupStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return doctorOrderGroupStore;
}



// IPD END( MEDICLAIM )


//BED START
function bedSpecialityRecord(){
	var record = Ext.data.Record.create([
	  {name: "featureName", type: "string"},
	  {name: "description", type: "string"},
	  {name: "availabilityFlag", type: "boolean"}
	]);
	return record;
}

function loadBedFeaturesList(){
	if( Ext.isEmpty( bedFeaturesStore ) ){
		bedFeaturesStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getBedSpecialFeatures, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, bedSpecialityRecord()),
	    remoteSort: true
	  });
	  bedFeaturesStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return bedFeaturesStore;
}

loadBedFeaturesList();

function loadNursingUnits(){
	if( Ext.isEmpty( nursingUnitsStore ) ){
		nursingUnitsStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getNursingUnits, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  nursingUnitsStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return nursingUnitsStore;
}

function loadBedTypes(){
	if( Ext.isEmpty( bedTypeStore ) ){
		bedTypeStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getBedType, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  bedTypeStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return bedTypeStore;
}

function loadBedStatus(){
	if( Ext.isEmpty( bedStatusStore ) ){
		bedStatusStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getBedStatus, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  bedStatusStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return bedStatusStore;
}

function loadNursingUnitTypes(){
	if( Ext.isEmpty( nursingUnitTypesStore ) ){
		nursingUnitTypesStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getNursingUnitType, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  nursingUnitTypesStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return nursingUnitTypesStore;
}

function loadFacilityType(){
	facilityTypeStore = new Ext.data.SimpleStore({
	   		fields: ['code', 'description'],
			data : [
				['I','Internal'],
				['E','External']
			]
	  });
	  return facilityTypeStore;
}

function loadBedPools(){
	if( Ext.isEmpty( bedPoolStore ) ){
		bedPoolStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getBedPools, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  bedPoolStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return bedPoolStore;
}

function loadAdmReqNoCbx(){
	if( Ext.isEmpty( admRequestNoStore ) ){
		admRequestNoStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getAdmissionReqNbr, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
		  });
		  
		  admRequestNoStore.load({params:{start:0, limit:9999}, arg:[]});
	}
  	return admRequestNoStore;
}

//BED END

//IPD STARTS
function loadDoctorOrderTemplate(){
	if( Ext.isEmpty( doctorOrderTemplatesStore ) ){
		doctorOrderTemplatesStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getDoctorOrderTemplate, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  doctorOrderTemplatesStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return doctorOrderTemplatesStore;
}
loadDoctorOrderTemplate();

function loadDoctorOrderType(){
	if( Ext.isEmpty( doctorOrderTypeStore ) ){
		doctorOrderTypeStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getDoctorOrderType, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  doctorOrderTypeStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return doctorOrderTypeStore;
}

loadDoctorOrderType();

function loadDoctorOrderStatus(){
	if( Ext.isEmpty( doctorOrderStatusStore ) ){
		doctorOrderStatusStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getDoctorOrderStatus, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  doctorOrderStatusStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return doctorOrderStatusStore;
}

//IPD END

//MANAGE ADMISSION ORDER START

function loadAdmissionStatusCbx(){
	if( Ext.isEmpty( admissionStatusStore ) ){
		admissionStatusStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getAdmissionStatus, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  admissionStatusStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return admissionStatusStore;
}

function loadAdmissionEntryPointCbx(){
	if( Ext.isEmpty( admissionEntryPointStore ) ){
		admissionEntryPointStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getAdmissionEntryPoints, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
	  admissionEntryPointStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return admissionEntryPointStore;
}
//MANAGE ADMISSION ORDER END


//ACCOUNTING START
function loadCreditCardTypeCbx(){
	creditCardTypeStore = new Ext.data.Store({
		reader: new Ext.data.ArrayReader({idIndex: 0},record()),
		data:[
			['A','Amex'],
			['D','Diners'],
			['N','Discover'],
			['M','MasterCard'],
			['P','Purchase Card'],
			['V','Visa']
		]
	});
	return creditCardTypeStore;
}

function loadTransactionTypeCbx(){
	transactionTypeStore = new Ext.data.Store({
		reader: new Ext.data.ArrayReader({idIndex: 0},record()),
		data:[
			['A','Authorization'],
			['C','Credit (Payment)'],
			['D','Delayed Capture'],
			['S','Sales'],
			['F','Voice Authorization'],
			['V','Void']
		]
	});
	return transactionTypeStore;
}

function loadPaymentModeCbx(){
	paymentModeStore = new Ext.data.Store({
		reader: new Ext.data.ArrayReader({idIndex: 0},record()),
		data:[
			['K','Cheque'],
			['C','Credit cards'],
			['X','Cash']
		]
	});
	return paymentModeStore;
}
//ACCOUNTING END
