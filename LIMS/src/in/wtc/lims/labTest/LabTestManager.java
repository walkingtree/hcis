/**
 * 
 */
package in.wtc.lims.labTest;

import in.wtc.hcis.bm.base.ServiceBM;
import in.wtc.lims.bm.LabTestBM;
import in.wtc.lims.bm.ListRange;
import in.wtc.lims.bm.PatientTestDetailBM;
import in.wtc.lims.bm.TestTemplateBM;

/**
 * @author Bhavesh
 *
 *This manager contains all the methods need to manage lab test related tasks.Incuding
 *configuration, and managing the life cycle of the test. 
 */
public interface LabTestManager {

	/**
	 * This method adds new lab test into the system.
	 * Since every test in the system is configured as service so
	 * it takes ServiceBM as primary argument at the same time it 
	 * expects  labTestBM which contains addition information about
	 * service which is required to configure it as Lab test. 
	 * @param labTestBM
	 * @param serviceBM
	 */
	void addLabTest(LabTestBM labTestBM, ServiceBM serviceBM );
	
	/**
	 * This method modified existing lab test on the system.
	 * Works same as addLabTest method
	 * @param modifiedLabTestBM
	 * @param modifiedServiceBM
	 * @return
	 */
	LabTestBM modifyLabTest(LabTestBM modifiedLabTestBM, ServiceBM modifiedServiceBM);
	
	/**
	 * Returns specific information about particular labtest. Basic intformation
	 * will be available as service.
	 * @param labTestCode
	 * @return LabTestBM
	 */
	LabTestBM getLabTestBM( String labTestCode);
	
	/**
	 * This method creates lab test entry for the test assigned to the patient 
	 * as assigned service, in the system.
	 * 
	 * The method will be invoked while entering test result.
	 * So the first status of the entry will be 'TEST_PERFORMED'.
	 * 
	 * 
	 * 
	 * @param patientTestDetailBM
	 */
	 void savePatientLabTestDetail( PatientTestDetailBM patientTestDetailBM);

	 /**
	  * 
	  * @param serviceUID
	  * @return
	  */
	 TestTemplateBM getTestResultValue( Integer serviceUID );
	 
	 /**
	  * Used to modify the status of Patient Test's status.
	  * Specially useful for UI grid row actions .
	  * @param patientTestId == serviceUID
	  * @param newStatusCode
	  * @param userId
	  * @param remarks
	  */
	 void modifyPatientTestStatus( String patientTestId,String newStatusCode,
			 String userId,String remarks );
	 
	 /**
	  * 
	  * @param patientTestId
	  * @param start
	  * @param limit
	  * @param orderBy
	  * @return TestResultChangeHistoryBM in chronological order
	  */
	 ListRange getTestResultChangeHistory(String patientTestId ,int start,int limit, String orderBy );
}
