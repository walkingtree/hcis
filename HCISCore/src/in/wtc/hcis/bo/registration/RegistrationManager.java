/**
 * 
 */
package in.wtc.hcis.bo.registration;

import in.wtc.hcis.bm.base.PatientInfoDetailBM;
import in.wtc.hcis.bm.base.PatientLiteBM;
import in.wtc.hcis.exceptions.HCISException;

public interface RegistrationManager {
	/**
	 * This method registers a patient. Here all the details of the patient is
	 * passed.
	 * 
	 * @param patientDetailBM
	 * @throws HCISException
	 */
	PatientLiteBM registerPatient(PatientInfoDetailBM patientInfoDetailBM)
			throws HCISException;

	/**
	 * This method is used to register an emergency patient
	 * 
	 * @param patientDetailBM
	 * @throws HCISException
	 */
	void registerEmergencyPatient(PatientInfoDetailBM patientInfoDetailBM)
			throws HCISException;
}
