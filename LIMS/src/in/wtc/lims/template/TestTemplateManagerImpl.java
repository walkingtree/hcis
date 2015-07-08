package in.wtc.lims.template;

import in.wtc.hcis.bm.base.DoctorBM;
import in.wtc.hcis.bm.base.PatientLiteBM;
import in.wtc.hcis.bo.common.CommonDataManager;
import in.wtc.hcis.bo.doctor.DoctorManager;
import in.wtc.hcis.bo.patient.PatientManager;
import in.wtc.lims.bm.CodeAndDescription;
import in.wtc.lims.bm.TemplateWidgetBM;
import in.wtc.lims.bm.TestTemplateBM;
import in.wtc.lims.common.LimsDataModelConvertor;
import in.wtc.lims.common.LimsDataModelManager;
import in.wtc.lims.constant.LimsConstants;
import in.wtc.lims.constant.LimsErrors;
import in.wtc.lims.exception.Fault;
import in.wtc.lims.exception.LimsException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wtc.hcis.da.LabPatientTestAttributeValue;
import com.wtc.hcis.da.LabPatientTestAttributeValueDAO;
import com.wtc.hcis.da.LabPatientTestAttributeValueId;
import com.wtc.hcis.da.LabPatientTestDetail;
import com.wtc.hcis.da.LabTemplateWidget;
import com.wtc.hcis.da.LabTest;
import com.wtc.hcis.da.LabTestAttributeAssociation;
import com.wtc.hcis.da.LabTestDAO;
import com.wtc.hcis.da.LabTestTemplate;
import com.wtc.hcis.da.LabTestTemplateDAO;
import com.wtc.hcis.da.LabTestTemplateDetail;
import com.wtc.hcis.da.LabTestTemplateDetailDAO;
import com.wtc.hcis.da.LabTestTemplateDetailId;
import com.wtc.hcis.da.extn.LabTemplateWidgetDAOExtn;
import com.wtc.hcis.da.extn.LabTestAttributeAssociationDAOExtn;

public class TestTemplateManagerImpl implements TestTemplateManager  {
	
	private LabTemplateWidgetDAOExtn templateWidgetDAO;
	private LimsDataModelConvertor dataModelConverter;
	private LimsDataModelManager dataModelManager;
	private LabTestTemplateDAO testTemplateDAO;
	private LabTestDAO testDAO;
	private LabTestTemplateDetailDAO testTemplateDetailDAO;
	private PatientManager patientManager;
	private DoctorManager doctorManager;
	private LabPatientTestAttributeValueDAO patientTestAttrValueDAO;
	private LabTestAttributeAssociationDAOExtn testAttributeAssociationDAO;

	

	/**
	 * 
	 * 
	 * This method returns TemplateWidgetBM which contains various list of TemplateWidgetBM. The list are
	 * patientWidgetList (contains patient specific information), doctorWidgetList (contains patient specific information), 
	 * testAttributeWidgetList(widgets corresponds to test attributes) and  otherWidgetList( test specific attributes).
	 * These different list represents different sections of widgets. 
	 * 
	 * NOTE: It returns all available widgets for the test, so specially useful for populating
	 * tree of all available widgets on UI.
	 * 
	 * Multiple lists are being prepared at back, just to avoid extra calculation on UI.
	 * 
	 * To prepare this apply following approach-
	 * 
	 * 1. Get all the widgets for given labTest.
	 * 2. Start iterating widgets
	 * 		i. 	If it belongs to 'PATIENT_SECTION'then add it to patientWidgetList
	 *  	ii. If it belongs to 'DOCTOR_SECTION'then add it to doctorWidgetList
	 *   	iii.If it belongs to 'ATTRIBUTE_SECTION'then add it to testAttributeWidgetList
	 * 3. Otherwise add to otherWidgetList
	 * 
	 * 
	 */
	public TestTemplateBM getWidgetsForTest(String testCode)throws LimsException{
		try {
		
			//The DAO method should return all the widgets for every section and only those widgets from 'TEST_ATTRIBUTE'
			//section which corresponds to testAttributes associated with given testCode.
			//
			List<LabTemplateWidget> templateWidgetList = templateWidgetDAO.getWidgetsForTest( testCode );
			
			TestTemplateBM testTemplateBM = new TestTemplateBM();
			
			List<TemplateWidgetBM> patientWidgetList = null;
			List<TemplateWidgetBM> doctorWidgetList = null;
			List<TemplateWidgetBM> testAttributeWidgetList = null;
			List<TemplateWidgetBM> otherWidgetList = null;
			
			if( templateWidgetList != null && templateWidgetList.size()>0){
				for( LabTemplateWidget templateWidget : templateWidgetList  ){
					
					List<CodeAndDescription> mvlValueList =null;
					TemplateWidgetBM templateWidgetBM = null;
					
					if( templateWidget.getLabTestAttribute() != null && templateWidget.getLabTestAttribute().getObservationValue() != null){
						
						mvlValueList = new ArrayList<CodeAndDescription>(0);
						String[] valueArray = templateWidget.getLabTestAttribute().getObservationValue().split(",");
						
						for( int i=0 ; i<valueArray.length ; i++){
							mvlValueList.add(new CodeAndDescription(valueArray[i],valueArray[i]));
						}
						
					}
					
					if( templateWidget.getSectionCode().equals(LimsConstants.PATIENT_SECTION)){
						if( patientWidgetList == null){
							patientWidgetList = new ArrayList<TemplateWidgetBM>(0);
						}
					
						templateWidgetBM = dataModelConverter.convertLabTemplateWidgetDM2TemplateWidgetBM(templateWidget);
						templateWidgetBM.setMVLValueList(mvlValueList);
						patientWidgetList.add( templateWidgetBM );
					}
					
					else if( templateWidget.getSectionCode().equals(LimsConstants.DOCTOR_SECTION)){
						if( doctorWidgetList == null){
							doctorWidgetList = new ArrayList<TemplateWidgetBM>(0);
						}
	
						templateWidgetBM = dataModelConverter.convertLabTemplateWidgetDM2TemplateWidgetBM(templateWidget);
						templateWidgetBM.setMVLValueList(mvlValueList);
						doctorWidgetList.add(templateWidgetBM);
					
					}
					else if( templateWidget.getSectionCode().equals(LimsConstants.TEST_ATTRIBUTE_SECTION)){
						if( testAttributeWidgetList == null){
							testAttributeWidgetList = new ArrayList<TemplateWidgetBM>(0);
						}
						
						LabTestAttributeAssociation testAttributeAsso = testAttributeAssociationDAO.getTestAttributeAsso(testCode, templateWidget.getWidgetCode());
						
						templateWidgetBM = dataModelConverter.convertLabTemplateWidgetDM2TemplateWidgetBM(templateWidget);

						templateWidgetBM.setMVLValueList(mvlValueList);

						if( testAttributeAsso != null){
							if( testAttributeAsso.getIsMandatory().equals(LimsConstants.YES )){
								templateWidgetBM.setWidgetLabel( templateWidgetBM.getWidgetLabel() + "*");
							}
							templateWidgetBM.setIsMandatory(testAttributeAsso.getIsMandatory());
						}
						
						testAttributeWidgetList.add(templateWidgetBM);
					}
					else{
						if( otherWidgetList == null){
							otherWidgetList = new ArrayList<TemplateWidgetBM>(0);
						}
						
						templateWidgetBM = dataModelConverter.convertLabTemplateWidgetDM2TemplateWidgetBM(templateWidget);
						templateWidgetBM.setMVLValueList(mvlValueList);
						otherWidgetList.add(templateWidgetBM);
						
					}
					
				}
			}
			
			//Get the widget with code 'REMARKS' and add it to 'testAttributeWidgetList'
			//So that it will be available as default attribute for each test

			LabTemplateWidget templateWidget = dataModelManager.getLabTemplateWidget(LimsConstants.REMARKS);
			TemplateWidgetBM templateWidgetBM = dataModelConverter.convertLabTemplateWidgetDM2TemplateWidgetBM(templateWidget);
			
			if( testAttributeWidgetList == null){
				testAttributeWidgetList = new ArrayList<TemplateWidgetBM>(0);
			}
			testAttributeWidgetList.add(templateWidgetBM);

			testTemplateBM.setTestCode(testCode);
			testTemplateBM.setPatientWidgetList(patientWidgetList);
			testTemplateBM.setDoctorWidgetList(doctorWidgetList);
			testTemplateBM.setTestAttributeWidgetList(testAttributeWidgetList);
			testTemplateBM.setOtherWidgetList(otherWidgetList);
			
			return testTemplateBM;
			
		}
		catch(Exception e){
			Fault fault = new Fault(LimsErrors.GET_WIDGET_FOR_TEST_FAILED);
			throw new LimsException(fault,e);
		}
		
	}

	/**
	 * Creates new testTemplate in the system for given test, For now one test can
	 * have only one template associated with it and template will be configured at the
	 * time of test creation itself(There is a scope of configuring multiple template for single test). 
	 * 
	 * TestTemplateBM contains four lists of similar type of BMs(TemplateWidgetBM), 
	 * The TemplateWidgetBM represents various widgets on the template.
	 * 
	 * The lists are -
	 * patientWidgetList (contains patient specific information),
	 * doctorWidgetList (contains patient specific information), 
	 * testAttributeWidgetList(widgets corresponds to test attributes) and 
	 * otherWidgetList( test specific attributes).
	 * 
	 * This method will be called after calling above method 'getWidgetsForTest'
	 * so expectation from UI is to set only the sequenceNumber on these 'TestTemplateBMs'
	 * so that their exact position on UI panel will be persisted.
	 *  
	 * For this, first save template and then save all the TemplateWidgetBMs
	 * from all four lists  and finally stamp the templateId to labTest.
	 */
	public void addTestTemplate( TestTemplateBM testTemplateBM) throws LimsException{
		try{
			
			LabTest labTest = dataModelManager.getLabTest(testTemplateBM.getTestCode());

			LabTestTemplate testTemplate = new LabTestTemplate();
			testTemplate.setTemplateName(LimsConstants.TEAMPLATE +"-"+ labTest.getTestCode());
			testTemplate.setCreatedBy(testTemplateBM.getCreatedBy());
			testTemplate.setCreatedDtm(new Date());
			
			testTemplateDAO.save(testTemplate);
			
			labTest.setLabTestTemplate(testTemplate);
			
			testDAO.attachDirty(labTest);
			
			this.addTestTemplateDetail(testTemplateBM.getPatientWidgetList(), testTemplate);
			
			this.addTestTemplateDetail(testTemplateBM.getDoctorWidgetList(), testTemplate);

			this.addTestTemplateDetail(testTemplateBM.getTestAttributeWidgetList(), testTemplate);
			
			this.addTestTemplateDetail( testTemplateBM.getOtherWidgetList(), testTemplate);
			
		}
		catch(Exception e){
			Fault fault = new Fault(LimsErrors.ADD_TEST_TEMPLATE_FAILED);
			throw new LimsException(fault,e);
		}
	}
	
	/*
	 * Saves the detail for template. Get all fields from BM and set to DM object.
	 */
	private void addTestTemplateDetail( List<TemplateWidgetBM> templateWidgetBMList , LabTestTemplate testTemplate){
		
		if( templateWidgetBMList != null && templateWidgetBMList.size() > 0){
			for( TemplateWidgetBM templateWidgetBM : templateWidgetBMList ){
	
				LabTemplateWidget templateWidget = dataModelManager.getLabTemplateWidget(templateWidgetBM.getWidgetCode());
				
				LabTestTemplateDetail testTemplateDetail = new LabTestTemplateDetail();
				testTemplateDetail.setLabTestTemplate(testTemplate);
	
				testTemplateDetail.setWidgetCode(templateWidget.getWidgetCode());
				
				LabTestTemplateDetailId testTemplateDetailId = new LabTestTemplateDetailId();
				testTemplateDetailId.setSectionCode(templateWidget.getSectionCode());
				testTemplateDetailId.setSeqNbr(templateWidgetBM.getSequenceNbr());
				testTemplateDetailId.setTemplateId(testTemplate.getTemplateId());
				
				testTemplateDetail.setId(testTemplateDetailId);
				
				testTemplateDetail.setCreatedBy(testTemplate.getCreatedBy());
				testTemplateDetail.setCreatedDtm(new Date());
				
				
				testTemplateDetailDAO.save( testTemplateDetail );
				
			}
		}
	}
	/*
	 * Removes testTemplate details, used while modifying template.
	 */
	private void removeTestTemplateDetail(Integer testTemplateId){
		
		
		List<LabTestTemplateDetail> testTemplateDetailList = testTemplateDetailDAO.findByProperty("id.templateId", testTemplateId);
		
		if( testTemplateDetailList != null && testTemplateDetailList.size() > 0){
			for( LabTestTemplateDetail testTemplateDetail : testTemplateDetailList ){
				testTemplateDetailDAO.delete(testTemplateDetail);
			}
		}	
	}
	
	/**
	 * Used to get templateId for given template.
	 * Specially created for UI use. 
	 */
	public Integer getTemplateId(String testCode)throws LimsException{
		try{
			
			LabTest labTest = testDAO.findById( testCode );
			if( labTest.getLabTestTemplate() != null ){
				return labTest.getLabTestTemplate().getTemplateId();
			}
			else{
				return null;
			}

		}
		catch( Exception e){
			Fault fault = new Fault(LimsErrors.TEMPLATE_READ_FAILED);
			throw new LimsException(fault,e);
		}
	}
	
	/**
	 * 
	 * Returns widgets of the template for given testCode.
	 * 
	 * This method will be used while modifying the test template. 
	 *  
	 *  Create TestTemplateBM by getting all Template Widget from
	 *  LabTestTemplateDetail table and start iterating through it 
	 *  convert It TemplateWidgetBM to set it TestTemplateBM in 
	 *  similar way  the method 'getWidgetsForTest' is doing.
	 */
	public TestTemplateBM getWidgetsForTemplate(String testCode)throws LimsException{
		
		try{
			
			TestTemplateBM testTemplateBM = this.getTemplate(testCode, null);
			return testTemplateBM;
			
		}catch(Exception e){
			Fault fault = new Fault(LimsErrors.READ_WIDGET_FOR_TEMPLATE_FAILED);
			throw new LimsException(fault,e);
			
		}
		
	}
	
	/**
	 * Method allows to modify the template details only, for this it deletes all entry from
	 * details table first and then creates new entries  
	 */
	public void modifyTestTemplate( TestTemplateBM testTemplateBM ) throws LimsException{
		try{
			LabTestTemplate testTemplate = testTemplateDAO.findById(testTemplateBM.getTemplateId());
			
			testTemplate.setLastModifiedBy(testTemplateBM.getCreatedBy());
			testTemplate.setLastModifiedDtm(new Date());
			
			testTemplateDAO.attachDirty(testTemplate);
			
			this.removeTestTemplateDetail(testTemplateBM.getTemplateId());
			
			this.addTestTemplateDetail(testTemplateBM.getPatientWidgetList(), testTemplate);
			
			this.addTestTemplateDetail(testTemplateBM.getDoctorWidgetList(), testTemplate);
			
			this.addTestTemplateDetail(testTemplateBM.getTestAttributeWidgetList(), testTemplate);
			
			this.addTestTemplateDetail( testTemplateBM.getOtherWidgetList(), testTemplate);
		}
		catch(Exception e){
			Fault fault = new Fault(LimsErrors.MODIFY_TEST_TEMPLATE_FAILED);
			throw new LimsException(fault,e);
			
		}
	}
	
	/**
	 * This method returns 'TestTemplateBM' for given patientTestId which will be used to draw panel
	 * on UI for entering test result, so this method populates all possible values for each of widget.
	 * 
	 * First get the corresponding template object form LabPateintTestDetail object and retrieve all the
	 * widgets associated to the template. While converting widgetsDM to BM keep setting the corresponding values
	 * also.
	 * 
	 * As at UI template is divided into four major sections- 1.Patient 2.Doctor 3.Test attribute
	 * and 4.others( patientTestDetail specific attribute), for these sections  TestTemplateBM has
	 * corresponding list of widgtBM. Each widget has one 'value-provider method name' associated with it.
	 * Name can be successive methods separated by (.)  To get the value by calling this method keep calling the method
	 * upon the resultant object obtained from previous method call(use reflections getMethod() and invoke() methods ).
	 * It is assumed that all the method will not take any parameter.
	 * 
	 *  Now to set the values for each of the BMList apply following approach-
	 * 
	 * 1. First get the PatientLiteBM from patientManger and doctorBM from DoctoManager
	 * 2. Start iterating the widgets,
	 * 3. if it belongs to patient's section then call the value provider method
	 * 	  on  PatientLiteBM,
	 * 4. if it belongs to doctor's section then call value provider method on doctorBM.
	 * 
	 * TODO: 5. if it belongs to otherDetail's section then call value provider method on LabPateintTestDetail object itself.
	 *  
	 * 6. Set  the value to widgetValue property of TemplateWidgetBM.
	 * 
	 * 7. If widget belongs to TEST_ATTRIBUTE_SECTION then get the attribute code from widgets object and 
	 * 	  query the table 'lab_patient_test_attribute_value'  and get the value for the attribute and set it 
	 * 	  to widgetValue property of TemplateWidgetBM. ( set null if attribute is not exist there)
	 * 
	 * 
	 * 
	 */
	
	public TestTemplateBM getTemplate( String patientTestId )throws LimsException{
		try{

			TestTemplateBM testTemplateBM = this.getTemplate(null, patientTestId);
			return testTemplateBM;
			
		}
		catch (Exception e) {
			
			Fault fault = new Fault(LimsErrors.TEMPLATE_READ_FAILED);
			throw new LimsException(fault,e);
		}
	}
	
	
	/**
	 * For the sake of code re-usability this method is written. Although this method
	 * is being used for  all to gather different tasks.
	 * 
	 *   First while getting template details to modify the template, and second to
	 *   draw the template for entering test results.So in first case it is not require to
	 *   set the values for widgets while at second time, for entering the test result it
	 *   is require to set all possible value to widgets.
	 *   
	 * To distinguish the context it is assuming that if patientTestId is given as parameter and
	 * so it is being invoked for entering test result.
	 *   
	 */
	private TestTemplateBM getTemplate(String testCode , String patientTestId){
	
		try{
			PatientLiteBM patientLiteBM = null;
			
			DoctorBM doctorBM = null;
			
			LabPatientTestDetail patientTestDetail = null;
			
			if( patientTestId != null){
				
				patientTestDetail = dataModelManager.getLabPatientTestDetail(patientTestId);
				
								
				Integer patientId  = patientTestDetail.getPatient().getPatientId();
				
				patientLiteBM = patientManager.getPatientLiteBM(patientId);
				
				if( patientTestDetail.getDoctor() != null ){
					
					Integer doctorId = patientTestDetail.getDoctor().getDoctorId() ;
					doctorBM = doctorManager.getDoctorDetail(doctorId);
					
				}
				
				if( testCode == null){
					testCode = patientTestDetail.getLabTest().getTestCode();
				}
			}
			
			
			LabTest labTest = dataModelManager.getLabTest( testCode );
			
			List<LabTestTemplateDetail> labTestTemplateDetailList = null;
			
			if( labTest.getLabTestTemplate() != null ) {
				labTestTemplateDetailList = testTemplateDetailDAO.findByProperty("id.templateId",
											labTest.getLabTestTemplate().getTemplateId());
			}
			
			
			if( labTestTemplateDetailList != null && labTestTemplateDetailList.size() > 0){
				
				TestTemplateBM testTemplateBM = new TestTemplateBM();
				List<TemplateWidgetBM> patientWidgetList = null;
				List<TemplateWidgetBM> doctorWidgetList = null;
				List<TemplateWidgetBM> testAttributeWidgetList = null;
				List<TemplateWidgetBM> otherWidgetList = null;
				for( LabTestTemplateDetail testTemplateDetail : labTestTemplateDetailList){
					
					LabTemplateWidget templateWidget = templateWidgetDAO.findById(testTemplateDetail.getWidgetCode());
					
					List<CodeAndDescription> mvlValueList = null;
					
					if( templateWidget.getLabTestAttribute() != null && templateWidget.getLabTestAttribute().getObservationValue() != null){
						
						mvlValueList = new ArrayList<CodeAndDescription>(0);
						String[] valueArray = templateWidget.getLabTestAttribute().getObservationValue().split(",");
						
						for( int i=0 ; i<valueArray.length ; i++){
							mvlValueList.add(new CodeAndDescription(valueArray[i],valueArray[i]));
						}
						
					}

					
					TemplateWidgetBM templateWidgetBM = dataModelConverter.convertLabTemplateWidgetDM2TemplateWidgetBM(templateWidget);
					
					templateWidgetBM.setMVLValueList(mvlValueList);
					templateWidgetBM.setSequenceNbr(testTemplateDetail.getId().getSeqNbr());
					
					if( templateWidget.getSectionCode().equals(LimsConstants.PATIENT_SECTION)){
						
						if( patientWidgetList == null){
							patientWidgetList = new ArrayList<TemplateWidgetBM>(0);
						}
						
						if( patientLiteBM != null){
							String widgetValue = this.getValueFromObject(patientLiteBM,templateWidget.getWidgetValueProvider());
							templateWidgetBM.setWidgetValue(widgetValue);
						}
							
						patientWidgetList.add(templateWidgetBM);
					}
					
					else if( templateWidget.getSectionCode().equals(LimsConstants.DOCTOR_SECTION)){
						
						if( doctorWidgetList == null){
							doctorWidgetList = new ArrayList<TemplateWidgetBM>(0);
						}
						
						if( doctorBM != null ){
							String widgetValue = this.getValueFromObject(doctorBM , templateWidget.getWidgetValueProvider());
							templateWidgetBM.setWidgetValue(widgetValue);
							
						}
							
						doctorWidgetList.add(templateWidgetBM);
					}
					else if (templateWidget.getSectionCode().equals(LimsConstants.TEST_ATTRIBUTE_SECTION)){
						
						if( testAttributeWidgetList == null){
							testAttributeWidgetList = new ArrayList<TemplateWidgetBM>(0);
						}
						
						//Get corresponding attribute value for widget
						
						LabPatientTestAttributeValueId patientTestAttributeId = new LabPatientTestAttributeValueId();
						
						patientTestAttributeId.setPatientTestId( patientTestId );
						patientTestAttributeId.setAttributeCode(templateWidgetBM.getWidgetCode());
						patientTestAttributeId.setSeqNbr(templateWidgetBM.getSequenceNbr());
						
						LabTestAttributeAssociation testAttributeAsso = testAttributeAssociationDAO.getTestAttributeAsso(testCode, templateWidget.getWidgetCode());
						
						if( testAttributeAsso != null){
							templateWidgetBM.setIsMandatory(testAttributeAsso.getIsMandatory());
						}

						
						LabPatientTestAttributeValue patientTestAttrValueDM  = patientTestAttrValueDAO.findById(patientTestAttributeId);
						
						if(patientTestAttrValueDM != null ){
							templateWidgetBM.setWidgetValue( patientTestAttrValueDM.getAttributeValue() );
						}
						
						testAttributeWidgetList.add(templateWidgetBM);
						
						
					}
					
					else{
						if( otherWidgetList == null){
							otherWidgetList = new ArrayList<TemplateWidgetBM>(0);
						}
						
						templateWidgetBM.setWidgetValue( this.getValueFromObject(patientTestDetail, 
										templateWidget.getWidgetValueProvider()));
						
						otherWidgetList.add(templateWidgetBM);
					}
				}
				
				testTemplateBM.setTestCode(testCode);
				testTemplateBM.setPatientWidgetList(patientWidgetList);
				testTemplateBM.setDoctorWidgetList(doctorWidgetList);
				testTemplateBM.setTestAttributeWidgetList(testAttributeWidgetList);
				testTemplateBM.setOtherWidgetList(otherWidgetList);
				testTemplateBM.setTemplateId(labTest.getLabTestTemplate().getTemplateId());
				return testTemplateBM;	
			}
			else{
				return null;
			}
			
		}
		catch (Exception e) {
			Fault fault = new Fault(LimsErrors.TEMPLATE_READ_FAILED);
			throw new LimsException(fault,e);
		}
		
	}
	
	/**
	 * This method will return value for this method.
	 * This mehtod will take first parameter as a Object in which we want to call a method.
	 * This method will take second parameter as a methodName. This methodName will be generally getter 
	 * methodName Which is not accepting any parameter.
	 * 
	 * @param object
	 * @param methodName
	 * @return
	 * @throws LimsException
	 */

	
	private String getValueFromObject( Object object ,String methodName ) throws LimsException{
		try{
		

			String value = null;
			
			if (object != null && methodName != null) {
				
				String[] methodArray = methodName.split("\\.");
				for (int i = 0; i < methodArray.length; i++) {

					methodName = methodArray[i];

					Class currentClass = object.getClass();

					Method method = currentClass.getMethod(methodName,new Class[0]);

					Object resultObject = (Object) method.invoke(object);

					if (resultObject == null) {

						return null;
					}
					object = resultObject;

					value = resultObject.toString();
				}
			}
			return value;
			  
		}
		catch(Exception e){
			Fault fault = new Fault(LimsErrors.GET_VALUE_FOR_PROPERTY_FAILED);
			throw new LimsException(fault,e);
		}
		
		
	}

	
	
	public void setTemplateWidgetDAO(LabTemplateWidgetDAOExtn templateWidgetDAO) {
		this.templateWidgetDAO = templateWidgetDAO;
	}

	public void setDataModelConverter(LimsDataModelConvertor dataModelConverter) {
		this.dataModelConverter = dataModelConverter;
	}


	public void setDataModelManager(LimsDataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}


	public void setTestTemplateDAO(LabTestTemplateDAO testTemplateDAO) {
		this.testTemplateDAO = testTemplateDAO;
	}


	public void setTestDAO(LabTestDAO testDAO) {
		this.testDAO = testDAO;
	}


	public void setTestTemplateDetailDAO(
			LabTestTemplateDetailDAO testTemplateDetailDAO) {
		this.testTemplateDetailDAO = testTemplateDetailDAO;
	}


	public void setCommonDataManager(CommonDataManager commonDataManager) {
	}


	public void setPatientManager(PatientManager patientManager) {
		this.patientManager = patientManager;
	}


	public void setDoctorManager(DoctorManager doctorManager) {
		this.doctorManager = doctorManager;
	}

	public void setPatientTestAttrValueDAO(
			LabPatientTestAttributeValueDAO patientTestAttrValueDAO) {
		this.patientTestAttrValueDAO = patientTestAttrValueDAO;
	}
	
	public void setTestAttributeAssociationDAO(
			LabTestAttributeAssociationDAOExtn testAttributeAssociationDAO) {
		this.testAttributeAssociationDAO = testAttributeAssociationDAO;
	}

}
