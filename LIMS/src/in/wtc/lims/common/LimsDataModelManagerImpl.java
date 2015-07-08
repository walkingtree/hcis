/**
 * 
 */
package in.wtc.lims.common;

import in.wtc.lims.constant.LimsErrors;
import in.wtc.lims.exception.Fault;
import in.wtc.lims.exception.LimsException;

import com.wtc.hcis.da.ContactDetails;
import com.wtc.hcis.da.ContactDetailsDAO;
import com.wtc.hcis.da.Hospital;
import com.wtc.hcis.da.HospitalDAO;
import com.wtc.hcis.da.LabCollectionPoint;
import com.wtc.hcis.da.LabCollectionPointDAO;
import com.wtc.hcis.da.LabDetails;
import com.wtc.hcis.da.LabDetailsDAO;
import com.wtc.hcis.da.LabPatientTestDetail;
import com.wtc.hcis.da.LabPatientTestDetailDAO;
import com.wtc.hcis.da.LabRequisitionOrder;
import com.wtc.hcis.da.LabRequisitionOrderDAO;
import com.wtc.hcis.da.LabSpecimen;
import com.wtc.hcis.da.LabSpecimenDAO;
import com.wtc.hcis.da.LabTechniqueReagent;
import com.wtc.hcis.da.LabTechniqueReagentDAO;
import com.wtc.hcis.da.LabTemplateWidget;
import com.wtc.hcis.da.LabTemplateWidgetDAO;
import com.wtc.hcis.da.LabTest;
import com.wtc.hcis.da.LabTestAttribute;
import com.wtc.hcis.da.LabTestAttributeDAO;
import com.wtc.hcis.da.LabTestDAO;
import com.wtc.hcis.da.LabTestTemplate;
import com.wtc.hcis.da.LabTestTemplateDAO;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.ReferenceDataListDAO;
import com.wtc.hcis.da.ReferenceDataListId;
import com.wtc.hcis.da.extn.LabCollectionPointDAOExtn;

/**
 * @author Bhavesh
 *
 */
public class LimsDataModelManagerImpl implements LimsDataModelManager {

	private LabTechniqueReagentDAO techniqueReagentDAO;
	private LabRequisitionOrderDAO requisitionOrderDAO;
	
	private HospitalDAO hospitalDAO;
	
	private LabDetailsDAO labDetailsDAO;
	private ReferenceDataListDAO referenceDataListDAO;
	
	private ContactDetailsDAO contactDetailsDAO;
	
	private LabTestAttributeDAO testAttributeDAO;
	private LabTestTemplateDAO labTestTemplateDAO;
	private LabTestDAO labTestDAO;
	private LabSpecimenDAO labSpecimenDAO;
	private LabTemplateWidgetDAO labTemplateWidgetDAO;
	private LabPatientTestDetailDAO patientTestDetailDAO;
	
	private LabCollectionPointDAOExtn collectionPointDAO;

	/* (non-Javadoc)
	 * @see in.wtc.lims.common.LimsDataModelManager#getLabTechniqueReagent(java.lang.Integer)
	 */
	@Override
public LabTechniqueReagent getLabTechniqueReagent(Integer id) throws LimsException{
		
		try {
			LabTechniqueReagent techniqueReagent = techniqueReagentDAO.findById(id);
			
			if ( techniqueReagent == null ){
				throw new Exception();
			}
			
			return techniqueReagent;
		} catch (Exception e) {
			
			Fault fault = new Fault(LimsErrors.TECHNIQUE_READ_FAILED);
			throw new LimsException( fault );
		}
	}
	
	
	public LabRequisitionOrder getLabRequisitionOrder(Integer id) throws LimsException{
		
		try {
			LabRequisitionOrder requisitionOrder = requisitionOrderDAO.findById(id);
			
			if ( requisitionOrder == null ){
				throw new Exception();
			}
			
			return requisitionOrder;
		} catch (Exception e) {
			
			Fault fault = new Fault(LimsErrors.REQUISITION_ORDER_READ_FAILED);
			throw new LimsException( fault );
		}
	}	
	
	
	
	
	public LabDetails getLabDetail(String labId) throws LimsException
	{
		try{
			 LabDetails labDetails = labDetailsDAO.findById(labId);
			 if (labDetails == null ){
				 throw new Exception();
			 }
			 return  labDetails;
		}catch (Exception e) {
			
			Fault fault = new Fault(LimsErrors.LAB_DETAILS_READ_FAILED);
			throw new LimsException( fault );
		}
		
	}
	public Hospital getHospital(String hospitalCode) throws LimsException{
		
		try {
			Hospital hospital = hospitalDAO.findById(hospitalCode);
			
			if ( hospital == null ){
				throw new Exception();
			}
			 
			return hospital;
		}catch (Exception e) {
			
			Fault fault = new Fault(LimsErrors.HOSPITAL_DETAILS_READ_FAILED);
			throw new LimsException( fault );
		}
		
	}
	
	public ContactDetails getContactDetails(Integer ContactCode) throws LimsException{
		
		try{
			ContactDetails contactDetails = contactDetailsDAO.findById(ContactCode);
			
			if ( contactDetails == null ){
				throw new Exception();
			}
			
		return contactDetails;
	}catch (Exception e) {
		
		Fault fault = new Fault(LimsErrors.CONTACT_DETAILS_READ_FAILED);
		throw new LimsException( fault );
	}
	}
	
	public LabTestTemplate getLabTestTemplate(Integer templateId ){

		try{ 
			LabTestTemplate labTestTemplate = labTestTemplateDAO.findById( templateId );
			
			if ( labTestTemplate == null ){
				throw new Exception();
			}
			
			return labTestTemplate;
		} catch (Exception e) {
			Fault fault = new Fault(LimsErrors.REFERENCE_DATA_READ_FAILED);
			throw new LimsException( fault );
			
		}
	}	
	
	public LabTestAttribute getLabTestAttribute(String attributeCode){

		try{ 
			LabTestAttribute labTestAttribute = testAttributeDAO.findById( attributeCode );
			
			if ( labTestAttribute == null ){
				throw new Exception();
			}
			
			return labTestAttribute;
		} catch (Exception e) {
			Fault fault = new Fault(LimsErrors.LAB_TEST_ATTRIBUTE_READ_FAILED);
			throw new LimsException( fault );
			
		}
	}
	
	public ReferenceDataList getReferenceDataList(String context, String  attrCode ){
		
		try {
			ReferenceDataListId id = new ReferenceDataListId();
			id.setAttrCode(attrCode);
			id.setContext(context);
			
			ReferenceDataList referenceDataList = referenceDataListDAO.findById(id);
			
			if ( referenceDataList == null ){
				throw new Exception();
			}
			return referenceDataList;
		} catch (Exception e) {
			Fault fault = new Fault(LimsErrors.REFERENCE_DATA_READ_FAILED);
			throw new LimsException( fault );
			
		}
		
	}

	public LabTest getLabTest(String testCode){
		try {
			LabTest labTest = labTestDAO.findById(testCode);
			
			if ( labTest == null ){
				throw new Exception();
			}
			 
			return labTest;
		}catch (Exception e) {
			
			Fault fault = new Fault(LimsErrors.LAB_TEST_READ_FAILED);
			throw new LimsException( fault );
		}
	}
	
	
	@Override
	public LabSpecimen getlabSpecimen(Integer specimenId) {
		
		try {
			LabSpecimen specimen = labSpecimenDAO.findById( specimenId );
			
			if ( specimen == null ){
				throw new Exception();
			}
			 
			return specimen;
		
		}catch (Exception e) {
			
			Fault fault = new Fault(LimsErrors.LAB_SPECIMEN_READ_FAILED);
			throw new LimsException( fault );
		}
		
		
	}

	
	
	public LabTemplateWidget getLabTemplateWidget(String widgetCode) {
		
		try {
			LabTemplateWidget templateWidget = labTemplateWidgetDAO.findById( widgetCode );
			
			if ( templateWidget == null ){
				throw new Exception();
			}
			 
			return templateWidget;
		
		}catch (Exception e) {
			
			Fault fault = new Fault(LimsErrors.LAB_TEMPLATE_WIDGET_READ_FAILED);
			throw new LimsException( fault );
		}
		
		
	}
	
	public LabPatientTestDetail getLabPatientTestDetail(String patientTestId) {
		
		try {
			LabPatientTestDetail labPatientTestDetail = patientTestDetailDAO.findById(patientTestId);
			
			
			if ( labPatientTestDetail == null ){
				throw new Exception();
			}
			 
			return labPatientTestDetail;
		
		}catch (Exception e) {
			
			Fault fault = new Fault(LimsErrors.LAB_PATIENT_TEST_DETAIL_READ_FAILED);
			throw new LimsException( fault );
		}
		
		
	}
	
	public LabCollectionPoint getLabCollectionPoint(String collectionPointId) {
		
		try {
			LabCollectionPoint collectionPoint = collectionPointDAO.findById(collectionPointId);
			
			
			if ( collectionPoint == null ){
				throw new Exception();
			}
			 
			return collectionPoint;
		
		}catch (Exception e) {
			
			Fault fault = new Fault(LimsErrors.COLLECTION_POINT_READ_FAILED);
			throw new LimsException( fault );
		}
		
		
	}
	
	
	public void setTechniqueReagentDAO(LabTechniqueReagentDAO techniqueReagentDAO) {
		this.techniqueReagentDAO = techniqueReagentDAO;
	}
	
	public void setLabDetailsDAO(LabDetailsDAO labDetailsDAO) {
		this.labDetailsDAO = labDetailsDAO;
	}
	
	public void setHospitalDAO(HospitalDAO hospitalDAO) {
		this.hospitalDAO = hospitalDAO;
	}
	

	public void setRequisitionOrderDAO(LabRequisitionOrderDAO requisitionOrderDAO) {
		this.requisitionOrderDAO = requisitionOrderDAO;
	}

	
	
	public void setReferenceDataListDAO(ReferenceDataListDAO referenceDataListDAO) {
		this.referenceDataListDAO = referenceDataListDAO;
	}

	public void setContactDetailsDAO(ContactDetailsDAO contactDetailsDAO) {
		this.contactDetailsDAO = contactDetailsDAO;
	}



	public void setTestAttributeDAO(LabTestAttributeDAO testAttributeDAO) {
		this.testAttributeDAO = testAttributeDAO;
	}


	public void setLabTestTemplateDAO(LabTestTemplateDAO labTestTemplateDAO) {
		this.labTestTemplateDAO = labTestTemplateDAO;
	}


	public void setLabTestDAO(LabTestDAO labTestDAO) {
		this.labTestDAO = labTestDAO;
	}


	public void setLabSpecimenDAO(LabSpecimenDAO labSpecimenDAO) {
		this.labSpecimenDAO = labSpecimenDAO;
	}


	public void setLabTemplateWidgetDAO(LabTemplateWidgetDAO labTemplateWidgetDAO) {
		this.labTemplateWidgetDAO = labTemplateWidgetDAO;
	}


	public void setPatientTestDetailDAO(LabPatientTestDetailDAO patientTestDetailDAO) {
		this.patientTestDetailDAO = patientTestDetailDAO;
	}


	public void setCollectionPointDAO(LabCollectionPointDAOExtn collectionPointDAO) {
		this.collectionPointDAO = collectionPointDAO;
	}



	
}
