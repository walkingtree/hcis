/**
 * 
 */
package in.wtc.lims.labTest;

import in.wtc.hcis.bm.base.ServiceBM;
import in.wtc.hcis.bo.common.CommonDataManager;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.common.WtcUtils;
import in.wtc.hcis.bo.services.ServiceManager;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.lims.bm.CodeAndDescription;
import in.wtc.lims.bm.LabTestAttributeAssocBM;
import in.wtc.lims.bm.LabTestBM;
import in.wtc.lims.bm.LabTestSpecimenAssocBM;
import in.wtc.lims.bm.ListRange;
import in.wtc.lims.bm.PatientTestAttrValueBM;
import in.wtc.lims.bm.PatientTestDetailBM;
import in.wtc.lims.bm.TemplateWidgetBM;
import in.wtc.lims.bm.TestResultChangeHistoryBM;
import in.wtc.lims.bm.TestTemplateBM;
import in.wtc.lims.common.LimsDataModelConvertor;
import in.wtc.lims.common.LimsDataModelManager;
import in.wtc.lims.common.LimsUtils;
import in.wtc.lims.constant.LimsConstants;
import in.wtc.lims.constant.LimsErrors;
import in.wtc.lims.exception.Fault;
import in.wtc.lims.exception.LimsException;
import in.wtc.lims.template.TestTemplateManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wtc.hcis.da.AssignedServiceStatus;
import com.wtc.hcis.da.AssignedServices;
import com.wtc.hcis.da.AssignedServicesDAO;
import com.wtc.hcis.da.DateDim;
import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.LabPatientTestAttributeValue;
import com.wtc.hcis.da.LabPatientTestAttributeValueDAO;
import com.wtc.hcis.da.LabPatientTestAttributeValueId;
import com.wtc.hcis.da.LabPatientTestChangeHistory;
import com.wtc.hcis.da.LabPatientTestChangeHistoryId;
import com.wtc.hcis.da.LabPatientTestDetail;
import com.wtc.hcis.da.LabPatientTestDetailDAO;
import com.wtc.hcis.da.LabSpecimen;
import com.wtc.hcis.da.LabTest;
import com.wtc.hcis.da.LabTestAttributeAssociation;
import com.wtc.hcis.da.LabTestAttributeAssociationDAO;
import com.wtc.hcis.da.LabTestAttributeAssociationId;
import com.wtc.hcis.da.LabTestDAO;
import com.wtc.hcis.da.LabTestSpecimenAssociation;
import com.wtc.hcis.da.LabTestSpecimenAssociationDAO;
import com.wtc.hcis.da.LabTestSpecimenAssociationId;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.extn.LabPatientTestChangeHistoryDAOExtn;
import com.wtc.hcis.da.extn.LabTestAttributeAssociationDAOExtn;

/**
 * @author Bhavesh
 *
 */
public class LabTestManagerImpl implements LabTestManager {

	/* (non-Javadoc)
	 * @see in.wtc.lims.labTest.LabTestManager#addLabTest(in.wtc.lims.bm.LabTestBM, in.wtc.hcis.bm.base.ServiceBM)
	 */
	
	private LimsDataModelConvertor convertor;
	private LimsDataModelManager dataModelManager;
	private ServiceManager serviceManager;
	private LabTestDAO labTestDAO;
	private LabTestAttributeAssociationDAOExtn testAttributeAssociationDAO;
	private LabTestSpecimenAssociationDAO testSpecimenAssociationDAO;
	private LimsDataModelConvertor dataModelConverter;
	private LabPatientTestDetailDAO patientTestDetailDAO;
	private LabPatientTestAttributeValueDAO patientTestAttrValueDAO;
	private LabPatientTestChangeHistoryDAOExtn patientTestChangeHistoryDAO;
	private CommonDataManager commonDataManager;
	private DataModelManager coreDataModelManager;
	private TestTemplateManager testTemplateManager;
	private AssignedServicesDAO assignedServicesDAO;
	
	
	/*
	 * Configures new lab test into the system.
	 * Since every test in the system is configured as service so
	 * it takes ServiceBM as primary argument at the same time it 
	 * expects  labTestBM which contains addition information about
	 * service which is required to configure it as Lab test.
	 * 
	 * A default template(which contains all the available widgets) is assigned
	 * Automatically.  
	 * @see in.wtc.lims.labTest.LabTestManager#addLabTest(in.wtc.lims.bm.LabTestBM, in.wtc.hcis.bm.base.ServiceBM)
	 */
	
	@Override
	public void addLabTest(LabTestBM labTestBM, ServiceBM serviceBM) {
		
		try {
	
			serviceManager.addService(serviceBM);
			
			LabTest labTest = convertor.convertLabTestBM2DM(labTestBM, null) ; 
			
			labTest.setTestType("Default");//TODO:Change it
			labTest.setTestCode(serviceBM.getServiceCode());
			labTest.setTestName(serviceBM.getServiceName());
			labTest.setCreatedBy( serviceBM.getPersonId() );
			labTest.setCreatedDtm( new Date());

			labTestDAO.save( labTest );
			
			//Save the associations
			
			List<LabTestAttributeAssocBM> attrAssoBMList = labTestBM.getLabTestAttributeAssocBMList();
			List<LabTestSpecimenAssocBM> specimenAssoBMList = labTestBM.getLabTestSpecimenAssocBMList();
			
			this.saveTestAttributeAssociation(attrAssoBMList, labTest);
			
			this.saveTestSpecimenAssociation(specimenAssoBMList, labTest);

			this.createDefaultTemplate( labTest );
			
		} catch (Exception e) {
			Fault fault = new Fault(LimsErrors.LAB_TEST_ADD_FAILED);
			throw new LimsException( fault, e );
		}
	}

	/**
	 * This private method either creates  a default template for given test or existing template to default.
	 * For this it first gets all available widgets for template and then assign all of them to the template.
	 * @param labTest
	 * @param templateId - If it is existing one
	 */
	private void createDefaultTemplate(LabTest labTest) {
		
		TestTemplateBM templateBM =	testTemplateManager.getWidgetsForTest( labTest.getTestCode() );
		
		if( templateBM != null ){
			
			templateBM.setTestCode( labTest.getTestCode() );
			templateBM.setCreatedBy( labTest.getCreatedBy() );
		
			
			List<TemplateWidgetBM>  doctorWidgetBMList = templateBM.getDoctorWidgetList();
			this.prepareTemplateWidgetBMtoSave(doctorWidgetBMList);
			
			List<TemplateWidgetBM>  patientWidgetBMList = templateBM.getPatientWidgetList();
			this.prepareTemplateWidgetBMtoSave(patientWidgetBMList);
			
			List<TemplateWidgetBM>  otherWidgetBMList = templateBM.getOtherWidgetList();
			this.prepareTemplateWidgetBMtoSave(otherWidgetBMList);
			
			List<TemplateWidgetBM>  testAttributeWidgetList = templateBM.getTestAttributeWidgetList();
			this.prepareTemplateWidgetBMtoSave(testAttributeWidgetList);
			
			if(labTest.getLabTestTemplate() != null ){
			
				//It means it is existing template so go for modify method
				//
				templateBM.setTemplateId(labTest.getLabTestTemplate().getTemplateId());
				testTemplateManager.modifyTestTemplate(templateBM);
			}else{
				
				testTemplateManager.addTestTemplate(templateBM);
			}
		}
	}

	private List<TemplateWidgetBM>  prepareTemplateWidgetBMtoSave( List<TemplateWidgetBM> templateWidgetBMList ){
		
		if( templateWidgetBMList != null && !templateWidgetBMList.isEmpty() ){
			
			int seqNbr = 0;
			
			for( TemplateWidgetBM templateWidgetBM : templateWidgetBMList ){
				
				seqNbr++;
				
				templateWidgetBM.setSequenceNbr( seqNbr );
			}
		}
		return templateWidgetBMList;
	}
	/**
	 * Saves association between test and attribute
	 */
	private void  saveTestAttributeAssociation(List<LabTestAttributeAssocBM> attrAssoBMList, LabTest labTest ){
		 
		if( attrAssoBMList != null && !attrAssoBMList.isEmpty()){
			
			for( LabTestAttributeAssocBM attrAssoBM : attrAssoBMList){
				
				LabTestAttributeAssociation labTestAttributeAssociation = new LabTestAttributeAssociation();
				
				LabTestAttributeAssociationId id = new LabTestAttributeAssociationId(); 
				
				if(LimsUtils.isValid(attrAssoBM.getLabTestAttribute())){
					
					id.setAttributeCode(attrAssoBM.getLabTestAttribute().getCode());
				}
				
				
				//If technique id is assigned explicitly then use it otherwise
				//take it from the test
				
				if( attrAssoBM.getTechniqueId()!= null ){
					
					id.setTechniqueId(attrAssoBM.getTechniqueId());
				}else{
					
					id.setTechniqueId( labTest.getLabTechniqueReagent().getId());
				}
				
				
				id.setTestCode( labTest.getTestCode() );
				labTestAttributeAssociation.setId(id);
				
				labTestAttributeAssociation.setCreatedBy(labTest.getCreatedBy());
				labTestAttributeAssociation.setCreatedDtm( new Date());
				labTestAttributeAssociation.setIsMandatory(attrAssoBM.getIsMandatory());
				labTestAttributeAssociation.setLabTest(labTest);
				labTestAttributeAssociation.setMaxValue( attrAssoBM.getMaxValue() );
				labTestAttributeAssociation.setMinValue( attrAssoBM.getMinValue());
				
				testAttributeAssociationDAO.save(labTestAttributeAssociation);
				
			}
		}
	}
	
	 /** Saves association between test and specimen */
	
	private void saveTestSpecimenAssociation(List<LabTestSpecimenAssocBM> specimenAssoBMList, LabTest labTest){
		if( specimenAssoBMList != null && !specimenAssoBMList.isEmpty() ){
			
			for( LabTestSpecimenAssocBM specimenAssociationBM : specimenAssoBMList){
				
				LabTestSpecimenAssociation specimenAssociation = new LabTestSpecimenAssociation();
				
				LabTestSpecimenAssociationId id = new LabTestSpecimenAssociationId();
				
				if(LimsUtils.isValid(specimenAssociationBM.getLabSpecimen())){
					
					id.setSpecimenId(Integer.valueOf(specimenAssociationBM.getLabSpecimen().getCode()));
				}
				id.setTestCode( labTest.getTestCode() );
				
				specimenAssociation.setId(id);
				
				specimenAssociation.setCreatedBy( labTest.getCreatedBy() );
				specimenAssociation.setCreatedDtm( new Date() );
				specimenAssociation.setIsMandatory( specimenAssociationBM.getIsMandatory() );
				
				if(LimsUtils.isValid( specimenAssociationBM.getLabSpecimen())){
					
					LabSpecimen specimen = dataModelManager.getlabSpecimen(Integer.valueOf
											(specimenAssociationBM.getLabSpecimen().getCode()));
					specimenAssociation.setLabSpecimen(specimen);
				}
				specimenAssociation.setUnit(specimenAssociationBM.getUnit());
				specimenAssociation.setQuantity(specimenAssociationBM.getQuantity());
				testSpecimenAssociationDAO.save( specimenAssociation );
			}
		}
	}
	
	
	
	/* 
	 *Modify the service first, then go for modification of test.
	 *If any of the attribute has been changed (removed/added) then reset the 
	 *template to default.
	 * @see in.wtc.lims.labTest.LabTestManager#modifyLabTest(in.wtc.lims.bm.LabTestBM)
	 */
	@Override
	public LabTestBM modifyLabTest(LabTestBM modifiedLabTestBM, ServiceBM modifiedServiceBM) {
		
		try {
			
			if( modifiedServiceBM != null ){
				
				serviceManager.modifyService(modifiedServiceBM);
			}
			
			
			LabTest existingLabTest = dataModelManager.getLabTest( modifiedServiceBM.getServiceCode() );
			
			convertor.convertLabTestBM2DM(modifiedLabTestBM, existingLabTest);
			
			existingLabTest.setModifiedBy( modifiedLabTestBM.getCreatedBy() );
			existingLabTest.setModifiedDtm( new Date() );
			
			labTestDAO.attachDirty(existingLabTest);
			
			//Delete old associations of attribute and specimen and create new 
			
			List<LabTestSpecimenAssociation> specimenAssoList =
				testSpecimenAssociationDAO.findByProperty("id.testCode", existingLabTest.getTestCode());
			
			if( specimenAssoList != null && !specimenAssoList.isEmpty() ){
				 for( LabTestSpecimenAssociation association : specimenAssoList){
					 
					 testSpecimenAssociationDAO.delete( association );
				 }
			}
			
			
			List<LabTestAttributeAssociation> attributeAssociationList = 
				testAttributeAssociationDAO.findByProperty("id.testCode", existingLabTest.getTestCode());

			if (attributeAssociationList != null && !attributeAssociationList.isEmpty()) {

				for (LabTestAttributeAssociation association : attributeAssociationList) {

					testAttributeAssociationDAO.delete( association );
				}
			}			
			
			
			//Save the associations
			
			List<LabTestAttributeAssocBM> attrAssoBMList = modifiedLabTestBM.getLabTestAttributeAssocBMList();
			List<LabTestSpecimenAssocBM> specimenAssoBMList = modifiedLabTestBM.getLabTestSpecimenAssocBMList();
			
			this.saveTestAttributeAssociation(attrAssoBMList, existingLabTest);
			
			this.saveTestSpecimenAssociation(specimenAssoBMList, existingLabTest);
			
			//Reset the template
			createDefaultTemplate(existingLabTest);
			return convertor.convertLabTestDM2BM(existingLabTest);
			
		} catch (Exception e) {
			
			Fault fault = new Fault(LimsErrors.LAB_TEST_MODIFY_FAILED);
			throw new LimsException( fault, e );
		}
		
	}

	/**
	 * This method returns specific  information about lab test along with
	 * associate attributes and specimens. LabTestBM BM is incomplete without
	 * serviceBM as the basic information is configured as service in the system.
	 */
	public LabTestBM getLabTestBM( String labTestCode){
		
		LabTest labTest = dataModelManager.getLabTest(labTestCode);
		
		LabTestBM labTestBM = convertor.convertLabTestDM2BM(labTest);
		
		List<LabTestSpecimenAssociation> specimenAssoList =
					testSpecimenAssociationDAO.findByProperty("id.testCode", labTestCode);
		
		List<LabTestAttributeAssocBM> attrAssoBMList = new ArrayList<LabTestAttributeAssocBM>(0);
		
		List<LabTestSpecimenAssocBM> specimenAssoBMList = new ArrayList<LabTestSpecimenAssocBM>(0);
		
		if( specimenAssoList != null && !specimenAssoList.isEmpty() ){
			 for( LabTestSpecimenAssociation association : specimenAssoList){
				 
				 LabTestSpecimenAssocBM specimenAssocBM = new LabTestSpecimenAssocBM();
				 specimenAssocBM.setIsMandatory( association.getIsMandatory() );
				 
				 specimenAssocBM.setLabSpecimen( new CodeAndDescription(association.getLabSpecimen().getSpecimenId().toString()
						 ,association.getLabSpecimen().getDescription()));
				 
				 specimenAssocBM.setQuantity(association.getQuantity());
				 specimenAssocBM.setUnit( association.getUnit());
				 
				 specimenAssoBMList.add(specimenAssocBM);
			 }
		}
		
		labTestBM.setLabTestSpecimenAssocBMList(specimenAssoBMList);
		
		 
		
		List<LabTestAttributeAssociation> attributeAssociationList = 
							testAttributeAssociationDAO.findByProperty("id.testCode", labTestCode);
		
		if( attributeAssociationList != null && !attributeAssociationList.isEmpty() ){
			
			for( LabTestAttributeAssociation association : attributeAssociationList){
				
				LabTestAttributeAssocBM attributeAssocBM = new LabTestAttributeAssocBM();
				attributeAssocBM.setIsMandatory( association.getIsMandatory() );
				attributeAssocBM.setLabTestAttribute( new CodeAndDescription( association.getLabTestAttribute().getAttributeCode(),
						association.getLabTestAttribute().getAttributeName()));
				attributeAssocBM.setMinValue(association.getMinValue());
				attributeAssocBM.setMaxValue( association.getMaxValue() );
				attributeAssocBM.setTechniqueId( association.getId().getTechniqueId());
				
				ReferenceDataList attributeType = dataModelManager.getReferenceDataList(
						LimsConstants.CONTEXT_TEST_ATTRIBUTE_TYPE, association.getLabTestAttribute().getType());
				attributeAssocBM.setAttributeType(attributeType.getAttrDesc());
				
				attrAssoBMList.add(attributeAssocBM);
			}
		}
		
		labTestBM.setLabTestAttributeAssocBMList( attrAssoBMList ) ;
		
		return labTestBM;
	}

	/**
	 * This method is used for entering or modifying test result detail for the
	 * patient.If modification is being made on test result then it maintains
	 * the change history of each and every attributes value.
	 * 
	 * The method will be invoked while entering test result. So the first
	 * status of the entry should be 'TEST_PERFORMED'.
	 * 
	 * This method works for modifying the test result also, so allow user
	 * to modify the result as long as it is in 'RESULT_ENTERED' status.
	 * 
	 * TODO:After entering testResult for all the status of patientTestDetai will be
	 * changed to 'RESULT_ENTERED' status only if all the value of mandatory
	 * attribute has been entered.
	 * 
	 * This method does not allow to modify the status of method directly (it
	 * will be some other method which will be invoked as rowaction from UI,
	 * status transition will be allowed based on the transition rule only ).
	 * 
	 * TODO:Additionally this method should consider the flag 'reviewRequired' if it
	 * is 'N' then automatically move to 'APPROVED'status.
	 * 
	 * @param patientTestDetailBM
	 */
	public void savePatientLabTestDetail( PatientTestDetailBM patientTestDetailBM){
		
		try{
				
			String patientTestId = patientTestDetailBM.getPatientTestId();
			String userId = patientTestDetailBM.getCreatedBy();
			
			LabPatientTestDetail patientTestDetail = dataModelManager.getLabPatientTestDetail(patientTestId);
			
			if(LimsConstants.PATIENT_TEST_STATUS_TEST_PERFORMED.equals(patientTestDetail.getStatusCode()) ||
					LimsConstants.PATIENT_TEST_STATUS_RESULT_ENTERED.equals(patientTestDetail.getStatusCode())){
				
				this.savePatientTestAttrValue(patientTestDetailBM.getPatientTestAttrValueBMList(),patientTestDetail , userId);
				
				//TODO: Check whether values for all the mandatory attribute has been entered.
//			this.modifyPatientTestStatus(patientTestId, LimsConstants.PATIENT_TEST_STATUS_RESULT_ENTERED, userId, null);
				
			}else{
				throw new Exception(" Entering test result is allowed +" +
						" when test is on "+patientTestDetail.getStatusCode()+" status");
			}
			
			
			
		}
		catch (Exception e) {
			Fault fault = new Fault(LimsErrors.CREATE_PATIENT_LAB_TEST_DETAIL_FAILED);
			throw new LimsException( fault, e );
		}
		
	}
	

	/**
	 * This method will save value for the test attribute. First it check
	 * whether the attribute with same code and seqenceNumber is already
	 * exist, if yes then move this entry to PatientTestChangeHistory and
	 * modify its value with new value.If attribute with same
	 * code and seqenceNumber is not exists then create new entry.
	 * 
	 * Method expects that only modified test attribute and its value will be 
	 * sent with PatientTestAttrValueBM.
	 * @param patientTestAttrValueBMList
	 * @param patientTestDetail
	 */
	
	private void savePatientTestAttrValue( List<PatientTestAttrValueBM> patientTestAttrValueBMList, 
											LabPatientTestDetail patientTestDetail,
											String userId){
		
		if( patientTestAttrValueBMList != null && patientTestAttrValueBMList.size() > 0){
			
			
			String patientTestId = patientTestDetail.getPatientTestId();
			
			for( PatientTestAttrValueBM patientTestAttrValueBM : patientTestAttrValueBMList){
			
				LabPatientTestAttributeValueId patientTestAttributeId = new LabPatientTestAttributeValueId();
				patientTestAttributeId.setPatientTestId( patientTestId );
				patientTestAttributeId.setAttributeCode(patientTestAttrValueBM.getTestAttribute().getCode());
				patientTestAttributeId.setSeqNbr(patientTestAttrValueBM.getSeqNbr());
				
				LabPatientTestAttributeValue patientTestAttrValueDM  = patientTestAttrValueDAO.findById(patientTestAttributeId);
				
				
				if( patientTestAttrValueDM == null){
					//
					//This is new entry
					patientTestAttrValueDM = new LabPatientTestAttributeValue();
					
					patientTestAttrValueDM.setId(patientTestAttributeId);
					patientTestAttrValueDM.setLabPatientTestDetail(patientTestDetail);
					patientTestAttrValueDM.setAttributeValue(patientTestAttrValueBM.getAttrValue());
					patientTestAttrValueDM.setRemarks(patientTestAttrValueBM.getRemarks());
					patientTestAttrValueDM.setCreatedBy(patientTestDetail.getCreatedBy());
					patientTestAttrValueDM.setCreatedDtm(new Date());
					patientTestAttrValueDAO.save(patientTestAttrValueDM);
				}
				else{
					//Already exist entry, so first create history 
					//then modify the existing record
					this.createPatientTestChangeHistory(patientTestAttrValueDM, patientTestId);
					patientTestAttrValueDM.setAttributeValue(patientTestAttrValueBM.getAttrValue());
					patientTestAttrValueDM.setLastModifiedBy(userId);
					patientTestAttrValueDM.setLastModifiedDtm(new Date());
					patientTestAttrValueDM.setRemarks(patientTestAttrValueBM.getRemarks());
					patientTestAttrValueDAO.attachDirty(patientTestAttrValueDM);
				}
				
			}
		}
	}
	

	/**
	 *  This method will create history for any modification of the test result attribute entry.
	 */
	
	private void createPatientTestChangeHistory( LabPatientTestAttributeValue patientTestAttrValue , String patientTestId){
		LabPatientTestChangeHistory patientTestChangeHistory = new LabPatientTestChangeHistory();
	
		LabPatientTestChangeHistoryId patientTestChangeHistoryId = new LabPatientTestChangeHistoryId();
		
		patientTestChangeHistoryId.setPatientTestId( patientTestId );
		patientTestChangeHistoryId.setCreatedDtm(new Date());
		patientTestChangeHistoryId.setAttributeCode( patientTestAttrValue.getId().getAttributeCode());
		patientTestChangeHistoryId.setSeqNbr(patientTestAttrValue.getId().getSeqNbr());
		patientTestChangeHistory.setId( patientTestChangeHistoryId );
		patientTestChangeHistory.setAttributeValue(patientTestAttrValue.getAttributeValue());
		patientTestChangeHistory.setCreatedBy(patientTestAttrValue.getCreatedBy());
		
		patientTestChangeHistoryDAO.save(patientTestChangeHistory);
		
	}
	
	
	
	
	/**
	 * This method is mainly used for getting complete test detail for patient
	 * for given serviceUID, which is directly corresponds to 'patientTestId'.
	 * 
	 * From UI prospective this method will be used for drawing panel to enter the
	 * result or to modify the existing result values.
	 * 
	 *  TODO:There should be some way to create the LabPatientTestDetail before
	 *  this method is called.And status of the testDetail will be 'TEST_PERFORMED'
	 *  
	 *  
	 * @param serviceUID
	 * @return
	 */
	public TestTemplateBM getTestResultValue( Integer serviceUID ){
		
		
		
		try {
			TestTemplateBM testTemplateBM = testTemplateManager.getTemplate( serviceUID.toString() );
			
			return testTemplateBM;
		
		} catch (LimsException e) {
			Fault fault = new Fault(LimsErrors.PATIENT_LAB_TEST_DETAIL_READ_FAILED);
			throw new LimsException(fault, e);
		}
		
	}

	/*
	 * This method modifies the status of 'patientTestDetail' and stamps corresponding
	 * fields.
	 * 
	 * Useful for row actions
	
	 * Parameter patientTestId == serviceUID of assigned service 

	 *  
	 * If LabPatientTestDetail for given serviceUID is not exist then create it first.
	 * As this may be first entry point for the LabPatientTestDetail.
	 *
	 *	Before making any status change first, It validates the transition by using 
	 *	generic transition validater method.At the same time it sets the status of 
	 *	corresponding assigned service entry.
	 * If new status is 'RESULT_ENTERED' and review is not require for the test
	 * then move the status directly to 'APPROVED'.
	 *		In case of 'APPROVED' status it moves corresponding assigned service entry 
	 *	to 'RENDERED' status (by calling renderService method of 'ServiceManager') 
	 */
	
	public void modifyPatientTestStatus( String patientTestId,String newStatusCode,
										 String userId,String remarks ){
		
		try {
			
			Integer serviceUID = Integer.valueOf(patientTestId);
			
			LabPatientTestDetail patientTestDetail = this.createPatinetTestDetail(patientTestId);
			
			boolean isValidTransition = commonDataManager.isValidTransition(
												LimsConstants.PATIENT_TEST_DETAIL,null,null,
												patientTestDetail.getStatusCode(),
												newStatusCode);
			
			if( isValidTransition ){
				
				
				if(LimsConstants.PATIENT_TEST_STATUS_RESULT_ENTERED.equals(newStatusCode)){
					
						
						LabTest labTest = patientTestDetail.getLabTest();
					
						List<LabTestAttributeAssociation> testAttributeAssoList = testAttributeAssociationDAO.getTestAttributeAssoList(labTest.getTestCode(), patientTestId);
						
						if( testAttributeAssoList != null && testAttributeAssoList.size() > 0){
							throw new Exception(testAttributeAssoList.get(0).getLabTestAttribute().getAttributeName() + " is mandatory attribute. " +
									"Please enter value for this attribute.");
						}
						
//					check, auto approval is applicable or not
						if(LimsConstants.NO.equals( labTest.getReviewRequired() )){
							
							newStatusCode = LimsConstants.PATIENT_TEST_STATUS_APPROVED;
						}
					}
				
				if(LimsConstants.PATIENT_TEST_STATUS_APPROVED.equals(newStatusCode)){
					
					patientTestDetail.setApproveDate( new Date() );
					patientTestDetail.setApproverName( userId );
					
					//Mark assigned service as rendered 
					serviceManager.modifyAssignedServiceToRendered(serviceUID, 1, userId);
					
				}else if(LimsConstants.PATIENT_TEST_STATUS_SPECIMEN_COLLECTED.equals(newStatusCode)){
					
					patientTestDetail.setSpecimenCollectionDtm(new Date());
					
				}else if(LimsConstants.PATIENT_TEST_STATUS_RESULT_COLLECTED.equals(newStatusCode)){
					
					patientTestDetail.setReportCollectedDtm( new Date() );
//				patientTestDetail.setReportCollectedBy(null);TODO: How to get this
					
				} 
				
				//make sure that given newStaus exists
				ReferenceDataList dataList = this.dataModelManager.getReferenceDataList( 
											  LimsConstants.CONTEXT_PATIENT_TEST_DETAIL_STATUS, newStatusCode);
				
				patientTestDetail.setStatusCode( dataList.getId().getAttrCode());
				patientTestDetail.setModifiedBy(userId);
				patientTestDetail.setModifiedDtm( new Date());
				patientTestDetail.setRemarks(remarks);
				patientTestDetailDAO.attachDirty(patientTestDetail);
				
				//Modify the corresponding assigned service entry
				
				AssignedServices assignedServices = commonDataManager.getAssignedServices(serviceUID);
				
				//TODO: move this logic to serviceManager and expose as generic  method 
				AssignedServiceStatus assignedServiceStatus = commonDataManager.getAssignedServiceStatus(newStatusCode);
				assignedServices.setAssignedServiceStatus(assignedServiceStatus);
				assignedServices.setModifiedBy(userId);
				assignedServices.setLastModifiedDtm( new Date() );
				
				assignedServicesDAO.attachDirty(assignedServices);
				
			}else{
				
				throw new Exception("Status transition from '"+patientTestDetail.getStatusCode()
						+ "' to '"+ newStatusCode +"' is not allowed ");
			}
		} catch (Exception e) {
			Fault fault = new Fault(LimsErrors.PATIENT_LAB_TEST_DETAIL_MODIFY_FAILED);
			throw new LimsException( fault, e );
		}
		
	}
	
	/**
	 * Creates patient lab detail entry if not exists.
	 * 
	 * Convention being followed here is to keep the patientTestId same as serviceUID
	 *  
	 * @param serviceUID
	 * @return
	 */
	private LabPatientTestDetail createPatinetTestDetail( String serviceUID){
		
		try {
			
			String patientTestId = serviceUID;
			
			//Because patient test id is same as serviceUID(AssignedService id)
			LabPatientTestDetail patientTestDetail = patientTestDetailDAO.findById( patientTestId );
			
			if( patientTestDetail == null ){
			
				AssignedServices assignedServices = commonDataManager.getAssignedServices(Integer.valueOf(serviceUID));
				
				patientTestDetail = new LabPatientTestDetail();
				patientTestDetail.setPatientTestId( patientTestId );
				patientTestDetail.setPatient(assignedServices.getPatient());
				
				if (assignedServices.getDoctorId() != null) {

					Doctor doctor = coreDataModelManager.getDoctor(assignedServices
							.getDoctorId());
					patientTestDetail.setDoctor(doctor);
				}
				DateDim dateDim = commonDataManager.getDateDim(new Date());
				if (dateDim != null) {
					patientTestDetail.setCreatedDateDim(dateDim.getDateId());
				}
				
				String testCode = assignedServices.getService().getServiceCode();
				
				LabTest labTest = dataModelManager.getLabTest(testCode);
				
				patientTestDetail.setLabTest(labTest);
				
				patientTestDetail.setCreatedDtm(new Date());
				patientTestDetail.setCreatedBy(assignedServices.getCreatedBy()!= null ?assignedServices.getCreatedBy():" ");
				patientTestDetail.setStatusCode(LimsConstants.PATIENT_TEST_STATUS_CREATED);
				
				patientTestDetailDAO.save(patientTestDetail);
			}
			
			return patientTestDetail;
		} catch (HCISException e) {
			Fault fault = new Fault(LimsErrors.CREATE_PATIENT_LAB_TEST_DETAIL_FAILED);
			throw new LimsException( fault, e );
		}
	}
	
	/**
	 * Returns Test result change history object,
	 * 
	 * The way it works is, first takes history DA objects, ascending order by creation date.
	 * 
	 * So in this way it finds the old value by previous record
	 * 
	 */
	
	public ListRange getTestResultChangeHistory(String patientTestId ,int start,int limit, String orderBy ){
		
		try {
			LabPatientTestDetail patientTestDetail = dataModelManager.getLabPatientTestDetail(patientTestId);
			
			List<LabPatientTestChangeHistory> resultChangeHistoryList = patientTestChangeHistoryDAO.getHistory(patientTestId);
			
			List<TestResultChangeHistoryBM> changeHistoryBMList = new ArrayList<TestResultChangeHistoryBM>(0);
			
			if( WtcUtils.isValid(resultChangeHistoryList)){
				
				Map<String,String> oldValueMap = new HashMap<String, String>();
				
				for( LabPatientTestChangeHistory changeHistory : resultChangeHistoryList){
					
					TestResultChangeHistoryBM changeHistoryBM = new TestResultChangeHistoryBM();
					
					changeHistoryBM.setPatientTestId(patientTestDetail.getPatientTestId());
					changeHistoryBM.setSeqNbr( changeHistory.getId().getSeqNbr() );
					
					changeHistoryBM.setAttributeValue( changeHistory.getAttributeValue() );
					changeHistoryBM.setAttributeCode(changeHistory.getId().getAttributeCode());
					changeHistoryBM.setAttributeName( changeHistory.getLabTestAttribute().getAttributeName());
					changeHistoryBM.setCreatedBy( changeHistory.getCreatedBy() );
					changeHistoryBM.setCreatedDtm( changeHistory.getId().getCreatedDtm());
					
					//Try to get old entry, it any
					//To maintain uniqueness of different attribute with same name create combined key
					String key = changeHistory.getId().getSeqNbr() + changeHistory.getId().getAttributeCode();
					changeHistoryBM.setAttributeOldValue(oldValueMap.get(key));
					
					oldValueMap.put(key, changeHistory.getAttributeValue() );
					
					changeHistoryBMList.add( changeHistoryBM );
					
				}
			}
			
			return LimsUtils.convertListToListRange(changeHistoryBMList, null);
		} catch (Exception e) {
			 
			Fault fault = new Fault(LimsErrors.READ_TEST_RESULT_HISTORYL_FAILED);
			throw new LimsException( fault, e );
		}
	}
	
	
	
	public void setConvertor(LimsDataModelConvertor convertor) {
		this.convertor = convertor;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public void setLabTestDAO(LabTestDAO labTestDAO) {
		this.labTestDAO = labTestDAO;
	}

	public void setTestAttributeAssociationDAO(
			LabTestAttributeAssociationDAOExtn testAttributeAssociationDAO) {
		this.testAttributeAssociationDAO = testAttributeAssociationDAO;
	}

	public void setTestSpecimenAssociationDAO(
			LabTestSpecimenAssociationDAO testSpecimenAssociationDAO) {
		this.testSpecimenAssociationDAO = testSpecimenAssociationDAO;
	}

	public void setDataModelManager(LimsDataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public void setDataModelConverter(LimsDataModelConvertor dataModelConverter) {
		this.dataModelConverter = dataModelConverter;
	}


	public void setPatientTestDetailDAO(LabPatientTestDetailDAO patientTestDetailDAO) {
		this.patientTestDetailDAO = patientTestDetailDAO;
	}

	public void setPatientTestAttrValueDAO(
			LabPatientTestAttributeValueDAO patientTestAttrValueDAO) {
		this.patientTestAttrValueDAO = patientTestAttrValueDAO;
	}



	public void setCommonDataManager(CommonDataManager commonDataManager) {
		this.commonDataManager = commonDataManager;
	}

	public void setCoreDataModelManager(DataModelManager coreDataModelManager) {
		this.coreDataModelManager = coreDataModelManager;
	}

	public void setTestTemplateManager(TestTemplateManager testTemplateManager) {
		this.testTemplateManager = testTemplateManager;
	}

	public void setAssignedServicesDAO(AssignedServicesDAO assignedServicesDAO) {
		this.assignedServicesDAO = assignedServicesDAO;
	}

	public void setPatientTestChangeHistoryDAO(
			LabPatientTestChangeHistoryDAOExtn patientTestChangeHistoryDAO) {
		this.patientTestChangeHistoryDAO = patientTestChangeHistoryDAO;
	}

}
