/**
 * 
 */
package in.wtc.hcis.bo.document;

import in.wtc.hcis.bm.base.PatientVitalBM;
import in.wtc.hcis.bm.base.VitalFieldBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

import java.util.Date;
import java.util.List;

/**
 * @author Bhavesh
 *
 */
public interface VitalManager {

	/**
	 * 
	 * @return list of VitalFieldBM
	 */
	List<VitalFieldBM> getVitalFields() throws HCISException;
	/**
	 * This method will add patient vital.
	 * @param patientVitalBM
	 * @throws HCISException
	 */
	void addVital(PatientVitalBM patientVitalBM)throws HCISException;
	/**
	 * 
	 * @param referenceNumber
	 * @param referenceType
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws HCISException
	 */
	List<VitalFieldBM> getPatientVitalDetails( String referenceNumber , String referenceType , 
			Date fromDate, Date toDate )throws HCISException;
	
	
}
