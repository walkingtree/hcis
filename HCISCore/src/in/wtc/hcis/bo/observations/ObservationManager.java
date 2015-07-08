/**
 * 
 */
package in.wtc.hcis.bo.observations;

import in.wtc.hcis.bm.base.ObservationBM;
import in.wtc.hcis.exceptions.HCISException;

import java.util.List;

/**
 * This interface provides the API for the following
 * 			> Appointment ObservationBM
 * 
 * The interface  uses only to get the details from the database and for update. 
 * This interface does not remove the entry from the database. 
 * @author Ajit Kumar
 *
 */
public interface ObservationManager 
{
	void addObservations(List<ObservationBM> observationsList) throws HCISException;
	List<ObservationBM> getObservations( Integer appointmentNumber ) throws HCISException;
	
	List<ObservationBM> getObservationsForPatient( Integer patientId ) throws HCISException;
	
	ObservationBM modifyObservationDetails( ObservationBM observationsBM ) throws HCISException;
}
