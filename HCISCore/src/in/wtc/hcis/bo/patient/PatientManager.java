/**
 * 
 */
package in.wtc.hcis.bo.patient;

import in.wtc.hcis.bm.base.PatientInfoDetailBM;
import in.wtc.hcis.bm.base.PatientInfoSummaryBM;
import in.wtc.hcis.bm.base.PatientLiteBM;
import in.wtc.hcis.exceptions.HCISException;

import java.util.Date;
import java.util.List;

/**
 * This interface provides the following APIs
 * 			> Registering a patient.
 * 			> Modifying patient records.
 * 			> Fetching patient details.
 * 			> Deleting a patient.
 * 			> Printing the registration card.
 * 			> Converting a registration from emergency to normal registration.
 * 			> Fetching a patients history. 
 * 			> Fetching appointments.
 * 			> Fetching the reminders.
 *
 */
public interface PatientManager {
	/**
	 * This method is used to get the patient details on the basis of the Patient ID 
	 * @param patientId ---> Patient ID
	 * @return
	 * @throws HCISException
	 */
	PatientInfoDetailBM getPatientInfoDetailBM(Integer patientId)
			throws HCISException;
	
	/**
	 * This method is used to get the patient details on the basis of the patients summary information
	 * When most of the details are passed this method helps com getting the details.
	 * @param patientInfoSummaryBM  ---> the summary information of the patient.
	 * @return PatientInfoDetailBM  ---> the complete details of the patient
	 * @throws HCISException
	 */
	PatientInfoDetailBM getPatientDetails(
			PatientInfoSummaryBM patientInfoSummaryBM) throws HCISException;
	
	/**
	 * This method is used to search the patients on the attributes defined below.
	 *  
	 * @param registrationFromDate  		---> registration date should be greater than equal to this date.
	 * @param registrationToDate  			---> registration date should be less than equal to this date .
	 * @param patientLastVisitedFromDate  	---> This is the date patient had last visited for an appointment.
	 * @param patientLastVisitedToDate  	---> This is the date patient had last visited for an appointment.
	 * @param maritalStatusCode  			---> This defines the marital status of the patient
	 * @param genderCode  					---> This defines the gender of the patient.
	 * @param registrationStatus  			---> This defines the registration status of the patient.
	 * 											Example :- active,suspended
	 * @param patientId  					---> This defines the registration number of the patient.
	 * @param patientName  					---> This defines the name of the patient.
	 * @param fromAge  						---> This defines the age of the patient
	 * @param toAge  						---> This defines the age of the patient
	 * @param phoneNumber  					---> This defines the Contact number of the patient
	 * @return  							---> It returns a list of patient matching the search criteria
	 * @throws HCISException
	 * 
	 * 1. if a registration from date is given, a user can leave the registration to date blank.
	 * 2. if a registration to date is given the from date has to be specified.
	 * 3. if PATIENT LAST VISITED date is selected then the beforeAfterInd should be specified.
	 */
	List<PatientInfoSummaryBM> getPatients(Integer patientId,
			String patientName, String phoneNumber, String genderCode,
			String maritalStatusCode, String fatherHusbandName,
			String registrationStatus, Date registrationFromDate,
			Date registrationToDate, Date fromAge, Date toAge,
			Date patientLastVisitedFromDate, Date patientLastVisitedToDate)
			throws HCISException;
	
	/**
	 * The method returns a very light weight information which returns a very
	 * basic information of the patient along with the current address. If the
	 * patient doesn't exist in the system it return null.
	 * @param patientId
	 *            Unique patient Id whose information is to be read
	 * @return Returns the basic patient information
	 * @throws HCISException
	 */
	PatientLiteBM getPatientLiteBM(Integer patientId) throws HCISException;

	/**
	 * @param patientIdList
	 * @return
	 * @throws HCISException
	 */
	List<PatientInfoSummaryBM> emergencyToNormal( List<Integer> patientIdList ) throws HCISException;

	/**
	 * @param patientId
	 * @return
	 * @throws HCISException
	 */

	List<PatientInfoSummaryBM> getPatientHistory(Integer patientId)
			throws HCISException; 
	
	/**
	 * This method allows user to modify details for outpatients. If you need to modify complete patient
	 * details then use the modifyPatientDetails API provided by InPatientManager module.
	 * @param patientInfoDetailBM
	 * @return
	 */
	PatientInfoDetailBM modifyPatientDetails(
			PatientInfoDetailBM patientInfoDetailBM) throws HCISException;
	
	String generatePatientCard(Integer patientId) throws HCISException;
	String generateAttendantCard(Integer patientId) throws HCISException;
	
	/**
	 * this method generates registration receipt slip.
	 * @param patientId
	 * @return
	 * @throws HCISException
	 */
	String generateRegistrationReceiptSlip ( Integer patientId ) throws HCISException;
	/**
	 * This method return Business partner id (account id )for given patient
	 * @param pateintId
	 * @return
	 * @throws HCISException
	 */
	Integer getBusinessPartnerId(Integer pateintId) throws HCISException;
	
	/**
	 * 
	 * @param patientId
	 * @param amount
	 * @return
	 * @throws HCISException
	 */
	String generateRecieptSlip(Integer patientId, Double amount) throws HCISException;
}
