/**
 * 
 */
package in.wtc.lims.common;

import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.lims.bm.CodeAndDescription;
import in.wtc.lims.bm.ContactDetailsBM;
import in.wtc.lims.bm.LabDetailBM;
import in.wtc.lims.bm.LabTestAttributeBM;
import in.wtc.lims.bm.LabTestBM;
import in.wtc.lims.bm.PatientTestAttrValueBM;
import in.wtc.lims.bm.PatientTestDetailBM;
import in.wtc.lims.bm.RequisitionOrderBM;
import in.wtc.lims.bm.RequisitionOrderDetailBM;
import in.wtc.lims.bm.SpecimenCollectionPointBM;
import in.wtc.lims.bm.TemplateWidgetBM;
import in.wtc.lims.constant.LimsConstants;
import in.wtc.lims.constant.RequisitionConstant;

import java.util.Date;

import com.wtc.hcis.da.AssignedServices;
import com.wtc.hcis.da.ContactDetails;
import com.wtc.hcis.da.Country;
import com.wtc.hcis.da.Hospital;
import com.wtc.hcis.da.LabCollectionPoint;
import com.wtc.hcis.da.LabDetails;
import com.wtc.hcis.da.LabPatientTestAttributeValue;
import com.wtc.hcis.da.LabPatientTestDetail;
import com.wtc.hcis.da.LabRequisitionOrder;
import com.wtc.hcis.da.LabTechniqueReagent;
import com.wtc.hcis.da.LabTemplateWidget;
import com.wtc.hcis.da.LabTest;
import com.wtc.hcis.da.LabTestAttribute;
import com.wtc.hcis.da.LabTestTemplateDetail;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.State;
import com.wtc.hcis.da.StateId;


/**
 * @author Bhavesh
 *
 */
public class LimsDataModelConvertor {
	
	private LimsDataModelManager dataModelManager;
	private DataModelManager coreDataModelManager;
	
	public RequisitionOrderBM convertLabRequisitionOrderDM2BM(LabRequisitionOrder requisitionOrderDM){
		
		RequisitionOrderBM requisitionOrderBM = new RequisitionOrderBM();
		
		requisitionOrderBM.setPatientId(requisitionOrderDM.getPatient().getPatientId());
		requisitionOrderBM.setPatientName(requisitionOrderDM.getPatient().getFullName());
		if( requisitionOrderDM.getDoctor() != null){
			requisitionOrderBM.setDoctorId(requisitionOrderDM.getDoctor().getDoctorId());
			requisitionOrderBM.setDoctorName(requisitionOrderDM.getDoctor().getFullName());
		}
		requisitionOrderBM.setCreatedDate(requisitionOrderDM.getCreatedDtm());
		requisitionOrderBM.setRequisitionOrderNbr(requisitionOrderDM.getOrderNbr());
		CodeAndDescription status = new CodeAndDescription();
		
		ReferenceDataList requisitionStatus = coreDataModelManager.getReferenceData(
				RequisitionConstant.CONTEXT_TEST_REQUISITION_STATUS,requisitionOrderDM.getStatusCode());
		
		status.setCode( requisitionStatus.getId().getAttrCode());
		status.setDescription( requisitionStatus.getAttrDesc());
		
		requisitionOrderBM.setStatus(status);

		requisitionOrderBM.setTotalCharges(requisitionOrderDM.getTotalCharges());
		requisitionOrderBM.setReferenceNumber(requisitionOrderDM.getReferenceNumber());
		
		
		ReferenceDataList refereceType = coreDataModelManager.getReferenceData(
				RequisitionConstant.CONTEXT_REFERENCE_TYPE, requisitionOrderDM.getReferenceType());
		//TODO: change it to codeAndDescription object
		requisitionOrderBM.setReferenceType(refereceType.getId().getAttrCode());
		
		return requisitionOrderBM;
		
	}
	
	
	public RequisitionOrderDetailBM convertAssignedService2RequisitionDetailBM( AssignedServices assignedServices){
		
		RequisitionOrderDetailBM requisitionOrderDetailBM = new RequisitionOrderDetailBM();
		
		requisitionOrderDetailBM.setTestName(new CodeAndDescription(assignedServices.getService().getServiceCode() , assignedServices.getService().getServiceName()));
		requisitionOrderDetailBM.setTestDate(assignedServices.getServiceDate());
		requisitionOrderDetailBM.setCharges(assignedServices.getServiceCharge());
		requisitionOrderDetailBM.setRequisitionOrderNbr(assignedServices.getLabRequisitionOrder().getOrderNbr());
		requisitionOrderDetailBM.setStatus(new CodeAndDescription(
											assignedServices.getAssignedServiceStatus().getServiceStatusCode(),
											assignedServices.getAssignedServiceStatus().getDescription()));
		
		requisitionOrderDetailBM.setUnit(assignedServices.getRequestedUnits());
		requisitionOrderDetailBM.setServiceUID(assignedServices.getServiceUid());
		requisitionOrderDetailBM.setIsBillable(assignedServices.getIsBillable());
		
		String serviceType = assignedServices.getService().getServiceTypeCd();
		
		requisitionOrderDetailBM.setType( serviceType );
		
		return requisitionOrderDetailBM;
	}
	

	
	public LabDetails convertLabDetailBM2DM(LabDetailBM labDetailBM, LabDetails existingLabDetails ){
		
		LabDetails labDetails = null;
		if ( existingLabDetails != null ) {
			labDetails = existingLabDetails;
		} else {
			labDetails = new LabDetails();
			labDetails.setLabId(labDetailBM.getLabId());
		}
		labDetails.setBranchName(labDetailBM.getBranchName());
		
		if( LimsUtils.isValid(labDetailBM.getLabType())){
			ReferenceDataList referenceDataList = dataModelManager.getReferenceDataList(
												  LimsConstants.CONTEXT_LAB_TYPE,labDetailBM.getLabType().getCode());
			if( null != referenceDataList )
			{
				labDetails.setLabType(labDetailBM.getLabType().getCode());
			}
		}
		labDetails.setLabName(labDetailBM.getLabName());
		labDetails.setLabOperatorId(labDetailBM.getLabOperatorID());
		labDetails.setBusinessName(labDetailBM.getBusinessName());
		labDetails.setDirectionFromKnownPlace(labDetailBM.getDirectionFromKnownPlace());
		
		if( LimsUtils.isValid(labDetailBM.getHospital())){
			
			Hospital hospital = dataModelManager.getHospital(labDetailBM.getHospital().getCode());
			labDetails.setHospital(hospital);
		}
		
		
		return labDetails;
		
	}
	
	public ContactDetails convertContactDetailsBM2DM(ContactDetailsBM contactDetailsBM, ContactDetails existingContactDetails){
		
		ContactDetails contactDetails = null;
		if ( existingContactDetails != null ) {
			contactDetails = existingContactDetails;
		} else {
			contactDetails = new ContactDetails();
		}
		
		contactDetails.setAddressLine1(contactDetailsBM.getStreet());
		contactDetails.setAddressLine2(contactDetailsBM.getLocality());
		contactDetails.setCity(contactDetailsBM.getCity());
		contactDetails.setEmail(contactDetailsBM.getEmail());
		contactDetails.setMobileNumber(contactDetailsBM.getMobileNumber());
		contactDetails.setFaxNumber(contactDetailsBM.getFaxNumber());
		
		
		if (LimsUtils.isValid(contactDetailsBM.getCountry())){
	
			contactDetails.setCountryCode(contactDetailsBM.getCountry().getCode());
			
		}
		
		if (LimsUtils.isValid(contactDetailsBM.getCountry())){
			
			contactDetails.setStateCode(contactDetailsBM.getState().getCode());
		}
		
		contactDetails.setFirstName(contactDetailsBM.getPersonName());
		contactDetails.setContactNumber(contactDetailsBM.getPhoneNumber());
		
		return contactDetails;
		
	}
	
	public LabDetailBM convertLabDetailDM2BM(LabDetails labDetails ){
		
		LabDetailBM labDetailBM = new LabDetailBM();
		labDetailBM.setLabId(labDetails.getLabId());
		labDetailBM.setBranchName(labDetails.getBranchName());
		labDetailBM.setBusinessName(labDetails.getBusinessName());
		labDetailBM.setDirectionFromKnownPlace(labDetails.getDirectionFromKnownPlace());
		labDetailBM.setLabOperatorID(labDetails.getLabOperatorId());
		labDetailBM.setLabName(labDetails.getLabName());
		
		if( labDetails.getLabType() != null && !labDetails.getLabType().isEmpty()){
			
			CodeAndDescription labType = new CodeAndDescription();
			ReferenceDataList referenceDataList  = dataModelManager.getReferenceDataList(LimsConstants.CONTEXT_LAB_TYPE,labDetails.getLabType());
			
			if( null != referenceDataList && null != referenceDataList.getId() ){
				
				labType.setCode(referenceDataList.getId().getAttrCode());
				labType.setDescription(referenceDataList.getAttrDesc());
				labDetailBM.setLabType(labType);
			}
			
		}
	
	if( labDetails.getHospital() != null ){
		
		CodeAndDescription hospitalName = new CodeAndDescription();
		Hospital hospital = dataModelManager.getHospital(labDetails.getHospital().getHospitalCode());
		hospitalName.setCode(hospital.getHospitalCode());
		
		hospitalName.setDescription(hospital.getHospitalName());
		labDetailBM.setHospital(hospitalName);
		
	}
	labDetailBM.setCreatedBy(labDetails.getCreatedBy());
	labDetailBM.setModifiedBy(labDetails.getModifiedBy());
	return labDetailBM;
}
	
	public ContactDetailsBM convertContactDetailsDM2BM(ContactDetails contactDetails ){
		
		ContactDetailsBM contactDetailsBM = new ContactDetailsBM();
		contactDetailsBM.setStreet(contactDetails.getAddressLine1());
		contactDetailsBM.setLocality(contactDetails.getAddressLine2());
		contactDetailsBM.setCity(contactDetails.getCity());
		contactDetailsBM.setEmail(contactDetails.getEmail());
		contactDetailsBM.setFaxNumber(contactDetails.getFaxNumber());
		contactDetailsBM.setMobileNumber(contactDetails.getMobileNumber());
		contactDetailsBM.setPhoneNumber(contactDetails.getContactNumber());
		contactDetailsBM.setPersonName(contactDetails.getFirstName());
		
//		contactDetails.getCountryCode()
		if (contactDetails.getCountryCode()!= null && !contactDetails.getCountryCode().isEmpty())
		{
			CodeAndDescription country= new CodeAndDescription();
			
			Country countryCode=coreDataModelManager.getCountrybyCode(contactDetails.getCountryCode());
			country.setCode(countryCode.getCountryCode());
			country.setDescription(countryCode.getDescription());
			contactDetailsBM.setCountry(country);
			
		}
		if(contactDetails.getStateCode()!=null &&  !contactDetails.getStateCode().isEmpty())
		{
			StateId stateId = new StateId();
			stateId.setCountryCode(contactDetails.getCountryCode());
			stateId.setStateCode(contactDetails.getStateCode());
			State state = coreDataModelManager.getStateByCode(stateId);
			
			contactDetailsBM.setState(new CodeAndDescription( 
									state.getId().getStateCode(),state.getDescription()));
		}

		
		return contactDetailsBM;
		
	}

	public LabTestAttributeBM convertLabTestAttributeDM2BM(
			LabTestAttribute labTestAttribute) {

		LabTestAttributeBM attributeBM = new LabTestAttributeBM();


		attributeBM.setAttributeCode(labTestAttribute.getAttributeCode());
		attributeBM.setAttributeName(labTestAttribute.getAttributeName());
		
		attributeBM.setMaxValue(labTestAttribute.getMaxValue());
		attributeBM.setMinValue(labTestAttribute.getMinValue());
		attributeBM.setObservationValue(labTestAttribute.getObservationValue());
		
		ReferenceDataList attributeType = dataModelManager.getReferenceDataList(LimsConstants.CONTEXT_TEST_ATTRIBUTE_TYPE,
											labTestAttribute.getType());
		if( attributeType != null ){
			
			attributeBM.setType( new CodeAndDescription(attributeType.getId().getAttrCode(),
					attributeType.getAttrDesc()));
		}
		
		if (labTestAttribute.getUnit() != null) {

			ReferenceDataList attributeUnit = dataModelManager.getReferenceDataList(
					LimsConstants.CONTEXT_LAB_MEASUREMENT_UNIT,labTestAttribute.getUnit());
			
			attributeBM.setUnit(new CodeAndDescription(attributeUnit.getId().getAttrCode(),
					attributeUnit.getAttrDesc()));
		}
		attributeBM.setCreatedBy(labTestAttribute.getCreatedBy());
		attributeBM.setCreatedDate(labTestAttribute.getCreatedDate());

		return attributeBM;
	}	
	
	public LabTest convertLabTestBM2DM(LabTestBM labTestBM, LabTest existingLabTest ){
		
		LabTest labTest = null;
		 
		if(existingLabTest != null ){
			
			labTest = existingLabTest;
		}else{
			
			labTest = new LabTest();
		}
		
		
		if(labTestBM.getAvailableForGender() != null && labTestBM.getAvailableForGender().getCode() != null
												&& !labTestBM.getAvailableForGender().getCode().isEmpty()){
			
			labTest.setAvailableForGender(labTestBM.getAvailableForGender().getCode());
		}
		
		labTest.setDefaultReviewerId(labTestBM.getDefaultReviewerId());
		
		labTest.setLabDetails(dataModelManager.getLabDetail(labTestBM.getLab().getCode()));
		
		CodeAndDescription techniqueRegId = labTestBM.getTechniqueReagent();
		
		if( LimsUtils.isValid(techniqueRegId)){
			
			labTest.setLabTechniqueReagent(dataModelManager.getLabTechniqueReagent(
											Integer.valueOf(techniqueRegId.getCode())));
		}
		
		if( labTestBM.getTestTemplateId() != null ){
			
			labTest.setLabTestTemplate(dataModelManager.getLabTestTemplate(labTestBM.getTestTemplateId()));
		}
		labTest.setMinTimeRequired(labTestBM.getMinTimeRequired());
		labTest.setPreRequisite(labTestBM.getPreRequisite());
		labTest.setReviewRequired(labTestBM.getReviewRequired());
		
//		labTest.setService(service)
//		
//		labTest.setTestName(labTestBM.gett)
		
		if( LimsUtils.isValid( labTestBM.getTestType() )){
			
			labTest.setTestType(labTestBM.getTestType().getCode());
		}
		
		return labTest;
	}
	
	public LabTestBM convertLabTestDM2BM(LabTest labTest) {
		
		LabTestBM labTestBM = new LabTestBM();
		
		ReferenceDataList forGender = dataModelManager.getReferenceDataList(LimsConstants.CONTEXT_TEST_FOR_GENDER,
										labTest.getAvailableForGender());
		
		if(forGender != null ){
			
			labTestBM.setAvailableForGender(new CodeAndDescription(forGender.getId().getAttrCode(),
									forGender.getAttrDesc()));
		}
		labTestBM.setCreatedBy( labTest.getCreatedBy() );
		labTestBM.setDefaultReviewerId( labTest.getDefaultReviewerId() );
		
		LabDetails labDetails = labTest.getLabDetails();
		
		if( labDetails != null ){
			
			labTestBM.setLab( new CodeAndDescription( labDetails.getLabId(),labDetails.getLabName()));
		}
		labTestBM.setMinTimeRequired( labTest.getMinTimeRequired() );
		labTestBM.setPreRequisite( labTest.getPreRequisite() );
		labTestBM.setReviewRequired( labTest.getReviewRequired());
		LabTechniqueReagent techniqueReagent = labTest.getLabTechniqueReagent();
		
		if(techniqueReagent != null){
			
			labTestBM.setTechniqueReagent( new CodeAndDescription(techniqueReagent.getId().toString(),
											techniqueReagent.getName()));
			
			labTestBM.setIsTechnique(techniqueReagent.getIsTechnique());
		}
//		labTestBM.setTestTemplateId(labTest.getlabte);
		
		return labTestBM;
	}
	
	public TemplateWidgetBM convertLabTemplateWidgetDM2TemplateWidgetBM(LabTemplateWidget labTemplateWidget){
		TemplateWidgetBM templateWidgetBM = new TemplateWidgetBM();
		
		templateWidgetBM.setWidgetCode(labTemplateWidget.getWidgetCode());
		templateWidgetBM.setWidgetLabel(labTemplateWidget.getWidgetLabel());
		templateWidgetBM.setWidgetType(labTemplateWidget.getWidgetType());
		templateWidgetBM.setValueType(labTemplateWidget.getValueType());
		
		LabTestAttribute attribute = labTemplateWidget.getLabTestAttribute();
		
		if(attribute != null){
			templateWidgetBM.setMinValue(attribute.getMinValue());
			templateWidgetBM.setMaxValue( attribute.getMaxValue() );
		}
		
		templateWidgetBM.setSectionCode(labTemplateWidget.getSectionCode());
		templateWidgetBM.setWidgetValueProvider(labTemplateWidget.getWidgetValueProvider());
		
		return templateWidgetBM;
		
	}

	public LabTestAttribute convertLabTestAttributeBM2DM( LabTestAttributeBM attributeBM , 
														  LabTestAttribute exstingAttribute){
		
		LabTestAttribute labTestAttribute;
		
		if( exstingAttribute != null ){
			
			labTestAttribute = exstingAttribute;
		
		}else{
			
			labTestAttribute = new LabTestAttribute();
			labTestAttribute.setAttributeCode( attributeBM.getAttributeCode() );
		}
		
		
		labTestAttribute.setAttributeName( attributeBM.getAttributeName() );
		labTestAttribute.setCreatedBy( attributeBM.getCreatedBy());
		labTestAttribute.setCreatedDate( new Date());
		labTestAttribute.setMaxValue( attributeBM.getMaxValue());
		labTestAttribute.setMinValue( attributeBM.getMinValue() );
		labTestAttribute.setObservationValue( attributeBM.getObservationValue());
		
		if( LimsUtils.isValid( attributeBM.getType() )){
			
			labTestAttribute.setType( attributeBM.getType().getCode() );		
		}
		
		if(LimsUtils.isValid( attributeBM.getUnit())){
			
			labTestAttribute.setUnit( attributeBM.getUnit().getCode() );
		}
		else{
			labTestAttribute.setUnit( null );
		}
		return labTestAttribute;
	}
	
	public LabPatientTestDetail convertpatientLabTestDetailBM2DM(PatientTestDetailBM patientTestDetailBM,
									LabPatientTestDetail existingLabTestDetailDM){
		LabPatientTestDetail labPatientTestDetail = null;
		if( existingLabTestDetailDM != null){
			labPatientTestDetail = existingLabTestDetailDM;
		}
		else{
			labPatientTestDetail = new LabPatientTestDetail();
		}
		
		if( patientTestDetailBM.getLabTest() != null){
			labPatientTestDetail.setLabTest(dataModelManager.getLabTest(patientTestDetailBM.getLabTest().getCode()));
		}
		
		if( patientTestDetailBM.getStatus() != null ){
			ReferenceDataList referenceDataList = coreDataModelManager.getReferenceData(LimsConstants.CONTEXT_PATIENT_TEST_DETAIL_STATUS,patientTestDetailBM.getStatus().getCode());
			labPatientTestDetail.setStatusCode( referenceDataList.getId().getAttrCode());
		}
		
		if( patientTestDetailBM.getTechniqueReagent() != null){
			Integer techniqueReagentId = Integer.parseInt(patientTestDetailBM.getTechniqueReagent().getCode());
			LabTechniqueReagent labTechniqueReagent =  dataModelManager.getLabTechniqueReagent( techniqueReagentId );
			labPatientTestDetail.setLabTechniqueReagent(labTechniqueReagent );
		}
		
		labPatientTestDetail.setPatientTestId(patientTestDetailBM.getPatientTestId());
		
		labPatientTestDetail.setInvestigatorId(patientTestDetailBM.getInvestigatorId());
		labPatientTestDetail.setRemarks(patientTestDetailBM.getRemarks());
		
		return labPatientTestDetail;
	}
	
	public LabCollectionPoint convertSpecimenCollectionPointBM2DM( SpecimenCollectionPointBM specimenCollectionPointBM, 
				LabCollectionPoint existingCollectionPoint){
		
		LabCollectionPoint labCollectionPoint = null;
		
		if( existingCollectionPoint != null ){
			labCollectionPoint = existingCollectionPoint;
		}
		else{
			labCollectionPoint = new LabCollectionPoint();
			labCollectionPoint.setCollectionPointId(specimenCollectionPointBM.getCollectionPointId());
		}
		
		
		labCollectionPoint.setName(specimenCollectionPointBM.getName());
		labCollectionPoint.setAreaCovered(specimenCollectionPointBM.getAreaCovered());
		labCollectionPoint.setCreatedBy(specimenCollectionPointBM.getCreatedBy());
		
		return labCollectionPoint;
		
		
	}
	
	public SpecimenCollectionPointBM convertSpecimenCollectionPointDM2BM( LabCollectionPoint collectionPoint){
		
		SpecimenCollectionPointBM collectionPointBM = new SpecimenCollectionPointBM();
		
		collectionPointBM.setCollectionPointId(collectionPoint.getCollectionPointId());
		
		ContactDetailsBM contactDetails = this.convertContactDetailsDM2BM(collectionPoint.getContactDetails());
		
		collectionPointBM.setContactDetails( contactDetails );
		collectionPointBM.setName(collectionPoint.getName());
		collectionPointBM.setAreaCovered(collectionPoint.getAreaCovered());
		
		return collectionPointBM;
		
	}
	
	
	public void setDataModelManager(LimsDataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public void setCoreDataModelManager(DataModelManager coreDataModelManager) {
		this.coreDataModelManager = coreDataModelManager;
	}



	
}

