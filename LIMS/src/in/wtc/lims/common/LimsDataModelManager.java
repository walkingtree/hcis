/**
 * 
 */
package in.wtc.lims.common;

import in.wtc.lims.exception.LimsException;

import com.wtc.hcis.da.LabCollectionPoint;
import com.wtc.hcis.da.LabPatientTestDetail;
import com.wtc.hcis.da.LabRequisitionOrder;
import com.wtc.hcis.da.ContactDetails;
import com.wtc.hcis.da.Hospital;
import com.wtc.hcis.da.LabDetails;
import com.wtc.hcis.da.LabSpecimen;
import com.wtc.hcis.da.LabTechniqueReagent;
import com.wtc.hcis.da.LabTemplateWidget;
import com.wtc.hcis.da.LabTest;
import com.wtc.hcis.da.LabTestAttribute;
import com.wtc.hcis.da.LabTestTemplate;
import com.wtc.hcis.da.ReferenceDataList;

/**
 * @author Bhavesh
 *
 */
public interface LimsDataModelManager {

	
	LabTechniqueReagent getLabTechniqueReagent(Integer id );
	
	LabRequisitionOrder getLabRequisitionOrder(Integer id);
	
	Hospital getHospital(String Hospital);
	
	LabDetails getLabDetail(String Id);
	
	ContactDetails getContactDetails(Integer ContactCode);
	ReferenceDataList getReferenceDataList(String context, String  attrCode );
	LabTestAttribute getLabTestAttribute(String attributeCode);
	
	LabTestTemplate getLabTestTemplate(Integer templateId );
	
	LabTest getLabTest(String testCode);

	LabSpecimen getlabSpecimen(Integer specimenId);
	
	LabTemplateWidget getLabTemplateWidget(String widgetCode);
	
	LabPatientTestDetail getLabPatientTestDetail(String patientTestId);
	
	LabCollectionPoint getLabCollectionPoint(String collectionPointId);
	
}
