/**
 * 
 */
package in.wtc.hcis.bo.prescription;

import in.wtc.hcis.bm.base.PrescriptionBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

import java.util.List;

/**
 * This interface provides APIs to manager prescriptions
 * @author Ajit Kumar
 *
 */
public interface PrescriptionManager {
	
	/**
	 * This method creates new prescription(s) in the system
	 * @param prescriptionBMList new prescriptions
	 */
	void addPrescription( List<PrescriptionBM> prescriptionBMList );
	
	/**
	 * The method returns the prescriptions associated with an appointment number
	 * @param appointmentNumber appointment number
	 * @return list of prescriptions associated with the appointment number
	 */
	List<PrescriptionBM> getMedicalPrescriptions( Integer appointmentNumber );

	/**
	 * The method updates a prescription in the system 
	 * @param modifiedPrescriptionBM modified prescription
	 * @return updated prescription
	 */
	PrescriptionBM modifyMedicalPrescription( PrescriptionBM modifiedPrescriptionBM );
	
	/**
	 * The method removes the specified prescription(s) from the system
	 * @param prescriptionBMList prescription(s) to be removed from the system
	 */
	void removePrescription( List<PrescriptionBM> prescriptionBMList );
	
	/**
	 *  getting medical prescription details for particular patient
	 * @param patietnId
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 * @throws HCISException
	 */
	List<PrescriptionBM> getMedicalPrescriptionsForPatient( Integer patietnId ) throws HCISException;

}
