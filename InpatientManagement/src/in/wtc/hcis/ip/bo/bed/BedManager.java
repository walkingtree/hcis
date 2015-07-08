/**
 * 
 */
package in.wtc.hcis.ip.bo.bed;

import in.wtc.billing.bm.BillDataBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.ip.BedBookingDetails;
import in.wtc.hcis.bm.ip.BedEnvelopeBM;
import in.wtc.hcis.bm.ip.BedMasterBM;
import in.wtc.hcis.bm.ip.BedPoolBM;
import in.wtc.hcis.bm.ip.BedReservationBM;
import in.wtc.hcis.bm.ip.BedSpecialFeatureAvailability;
import in.wtc.hcis.bm.ip.PreferredBedBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Alok Ranjan
 * 
 * Hospitals have a daily task of balancing the demands of treating an unknown 
 * and a variable number of emergency patients, at the same time admitting 
 * and treating scheduled elective patients. In doing this, hospitals have to ensure 
 * that sufficient, but not excessive, beds are available to meet the demands of new and 
 * existing patients and to coordinate their admission. 
 * 
 * The Patient�s elective operation should not be canceled by the hospital on or after 
 * admission, for non-medical reasons including unavailability of beds. Thus effective
 * bed management is crucial for any health care organization.
 * 
 */
public interface BedManager {
	
	/**
	 * This method adds bed into the bed master.
	 * In addition, it also checks if the health care unit is using bed scheduling 
	 * mechanism using the bed pooling concepts. In that case -- it increases the total 
	 * bed count for following entities
	 * 1) The unit type and bed type combination
	 * 2) All the pools which include the corresponding unit type and bed type
	 * 3) All the envelops which include above pools
	 * 
	 * @param bedMasterBM
	 * @return
	 * @throws HCISException
	 */
	BedMasterBM addBed( BedMasterBM bedMasterBM ) throws HCISException;
	
	/**
	 * This method creates a bed pool. Additionally, you can also provide a list 
	 * of unit types that may be associated with the bed pool. Internally, it 
	 * calculates the total number of beds that belong to this pool.
	 * 
	 * @param bedPoolBM
	 * @return
	 * @throws HCISException
	 */
	void addBedPool( BedPoolBM bedPoolBM ) throws HCISException;
	
	/**
	 * 
	 * @param bedEnvelopeBM
	 * @return
	 * @throws HCISException
	 */
	void addBedEnvelope( BedEnvelopeBM bedEnvelopeBM ) throws HCISException;
	
	/**
	 * This method allows user to modify bed details including special feature assignments.
	 * If user decides to update bed type or unit type of the bed then system also updates
	 * corresponding pool information. For example it increases pool count if its new bed type and unit type
	 * combination is part of a given pool and decreases for the ones where it was part of. In nutshell it
	 * modifies following bed pooling entities:
	 * 1) The unit type and bed type combination
	 * 2) All the pools which include the corresponding unit type and bed type
	 * 3) All the envelops which include above pools
	 * 
	 * @param bedMasterBM
	 * @return
	 * @throws HCISException
	 */
	BedMasterBM modifyBed( BedMasterBM bedMasterBM ) throws HCISException;
	
	/**
	 * This method allows user to modify bed pool details. Also, it allows you to 
	 * modify bed pool's association with the unit type.
	 * 
	 * @param bedPoolBM
	 * @return
	 * @throws HCISException
	 */
	BedPoolBM modifyBedPool( BedPoolBM bedPoolBM ) throws HCISException;
	
	/**
	 * This method allows user to modify envelope details. Also, it allows you to 
	 * modify envelop's association with the bed pool. 
	 * 
	 * @param bedEnvelopeBM
	 * @return
	 * @throws HCISException
	 */
	BedEnvelopeBM modifyBedEnvelope( BedEnvelopeBM bedEnvelopeBM ) throws HCISException;
	
	/**
	 * This method provides functionality to allow bed manager to update the threshold based
	 * on his/her experience to facilitate effective bed booking. 
	 * Usually hospitals maintain a balance between scheduled admission and emergency admissions. However, 
	 * it is also important that the beds are utilized as much as possible. Hence, a configurable limit 
	 * being set through this method for every unit type and bed type allows us to decide when to
	 * stop further scheduled booking -- i.e. once the threshold is reached, system must not 
	 * confirm the admission booking.
	 * 
	 * @param unitTypeCd
	 * @param bedTypeCd
	 * @param newThresholdValue
	 * @return
	 * @throws HCISException
	 */
	void modifyAllowedBookingThreshold( String unitTypeCd, 
										   String bedTypeCd,
										   int newThresholdValue ) throws HCISException;
	
	/**
	 * Removes a bed from the bed master details. 
	 * Additionally, it also updates bed pool information to make sure that total bed 
	 * available count is correct. It decreases the total bed count for following entities
	 * 1) The unit type and bed type combination
	 * 2) All the pools in which include the corresponding unit type and bed type
	 * 3) All the envelops which include above pools
	 * 
	 * If the bed being deleted has ever been used (even booked) then deletion will not be allowed.
	 *  
	 * @param bedNumberList
	 * @return
	 * @throws HCISException
	 */
	boolean removeBed( List<String> bedNumberList ) throws HCISException;
	
	/**
	 * For a given list of pool names, this method will delete the bed pools.
	 * Additionally, it also deletes any association between the bed pools and bed unit type.
	 * 
	 * @param bedPoolNameList
	 * @return
	 * @throws HCISException
	 */
	boolean removeBedPool( List<String> bedPoolNameList ) throws HCISException;
	
	/**
	 * For a given list of envelope names, this method will delete all the envelopes.
	 * Additionally, it also deletes any association between the envelope and bed pools.
	 * 
	 * @param bedEnvelopeNameList
	 * @return
	 * @throws HCISException
	 */
	boolean removeBedEnvelope( List<String> bedEnvelopeNameList ) throws HCISException;
	
	/**
	 * For a given bed number, this method returns bed details. It assumes that
	 * user is querying for an existing bed. If bed doesn't exist then it throws HCISException. 
	 * @param bedNumber
	 * @return
	 * @throws HCISException
	 */
	BedMasterBM getBedDetails( String bedNumber ) throws HCISException;
	
	/**
	 * This method matches zero or more criteria in the parameter list and returns 
	 * the filtered BedMasterBM object to the caller.
	 * For parameters like nursingUnitName and patientName it matches the part of the 
	 * string. Additionally, for patient name, it matches part of the name with 
	 * first, last and middle name of the patient.
	 * 
	 * @param bedNumber
	 * @param roomNumber
	 * @param floorNumber
	 * @param nursingUnitName
	 * @param patientId
	 * @param patientName
	 * @param patientAdmissionNumber
	 * @param bedStatusCode
	 * @param fromDischargeDate
	 * @param toDischargeDate
	 * @param bedFeatures // it performs partial look up 
	 * @return
	 * @throws HCISException
	 */
	List<BedMasterBM> getBedDetails( String bedNumber, 
			                         String roomNumber, 
			                         String floorNumber,
			                         String nursingUnitName,
			                         Integer patientId,
			                         String patientName,
			                         Integer patientAdmissionNumber,
			                         String bedStatusCode,
			                         Date fromDischargeDate,
			                         Date toDischargeDate,
			                         List<String> bedFeatures,
										String orderByClause,
										String sortDir ) throws HCISException;
	
	/**
	 * This method matches zero or more criteria in the parameter list and returns 
	 * the filtered BedMasterBM object to the caller.
	 * For parameters like nursingUnitName and patientName it matches the part of the 
	 * string. Additionally, for patient name, it matches part of the name with 
	 * first, last and middle name of the patient.
	 * 
	 * @param bedNumber
	 * @param roomNumber
	 * @param floorNumber
	 * @param nursingUnitName
	 * @param patientId
	 * @param patientName
	 * @param patientAdmissionNumber
	 * @param bedStatusCode
	 * @param fromDischargeDate
	 * @param toDischargeDate
	 * @param bedFeatures // it performs partial look up 
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 * @throws HCISException
	 */
	ListRange findBeds( String bedNumber, 
			                         String roomNumber, 
			                         String floorNumber,
			                         String nursingUnitName,
			                         Integer patientId,
			                         String patientName,
			                         Integer patientAdmissionNumber,
			                         String bedStatusCode,
			                         Date fromDischargeDate,
			                         Date toDischargeDate,
			                         List<String> bedFeatures,
			                         int start, 
			                         int count, 
			                         String orderBy) throws HCISException;

	
	/**
	 * This is a light weight method provided for filtering out the list of available 
	 * beds for a given unit. Available beds are those beds which are ready for the occupancy
	 * by the patient.
	 * 
	 * @param nursingUnitTypeCd
	 * @param nursingUnitName
	 * @return
	 * @throws HCISException
	 */
	List<BedMasterBM> getCurrentlyAvailableBeds( String nursingUnitTypeCd, 
			                                     String nursingUnitName ) throws HCISException;
	
	/**
	 * For a given admission number, this method returns booking details for all the beds booked
	 * using the admission number. Initially, patient may not be admitted and hence he/she may
	 * not have admission numbers. In such cases  bed reservation number can be used to retrieve
	 * the booking details.
	 * 
	 * Although, a patient will usually occupy one bed at a time (and occasionally two bed bed 
	 * -- for example patient may be occupying a general bed and currently he/she may have been
	 * shifted to ICU), he/she can book one or more beds during the admission.
	 * 
	 * @param admissionNumber
	 * @return
	 * @throws HCISException
	 */
	List<BedBookingDetails>getBedBookingDetails( Integer admissionNumber ) throws HCISException;
	
	/**
	 * This method returns reservation details for a given bed reservation number.
	 * 
	 * @param bedReservationNumber
	 * @return
	 * @throws HCISException
	 */
	BedBookingDetails getBedReservationDetails( Integer bedReservationNumber ) throws HCISException;
	
	ListRange findPreferredBedAvailability(Integer bedReservationNumber, int start, int count, String orderBy);
	
	/**
	 * This method returns possible matches for a given bed reservation. This method checks if any
	 * special feature is marked as mandatory. If rooms with all the mandatory features are available
	 * then this method returns all such rooms. Additionally, this method also checks how many optional
	 * requested special features are available in a given room.
	 * 
	 * For a given bed this method returns PreferredBedBM which internally contains 
	 * BedSpecialFeatureAvailability object. The BedSpecialFeatureAvailability object has a 
	 * mandatoryFlag ( Boolean Object ) attribute to indicate whether a feature was mandatory or 
	 * optional. If this mandatoryFlag object is null then it is assumed that the special 
	 * feature was not desired during the bed reservation. However, since the feature is anyway 
	 * available with the bed, it is good to show them along with the desired features.
	 * 
	 * @param bedReservationNumber
	 * @return
	 * @throws HCISException
	 */
	List<PreferredBedBM>getPreferredBedAvailability( Integer bedReservationNumber ) throws HCISException;
	
	/**
	 * For a given patient identifier or admission number, this method returns the currently occupied
	 * bed by the patient.
	 * @param admissionNumber
	 * @param patientId
	 * @return
	 * @throws HCISException
	 */
	BedMasterBM getCurrentlyOccupiedBed( Integer admissionNumber, Integer patientId ) throws HCISException;
	
	/**
	 * This method returns current status of all the beds in the hospital.
	 * The beds are organised in the map in following ways
	 * 1) Map's key is the unit name
	 * 2) Value is the details of the list of beds in the unit
	 * 
	 * @return
	 * @throws HCISException
	 */
	Map<String, List<BedMasterBM>> getAllBeds( EnumBedOrganizationType bedOrganizationType ) throws HCISException;
	
	
	/**
     * This method assigns a bed to an admitted patient. In case a reservation details has been
     * provided, this method also updates corresponding reservation information.
     * Bed reservation number and assigned to date are optional parameters.  
     *   
     * @param bedNumber
	 * @param admissionReqNumber
	 * @param bedReservationNumber 
	 * @param allowMultipleAssignment TODO
     * @return BedMasterBM
     * @throws HCISException
     */

    BedMasterBM assignBed( String bedNumber,Integer admissionReqNumber,Integer bedReservationNumber, String createdBy, boolean allowMultipleAssignment ) throws HCISException;
    
	/**
     * This method returns BedSpecialFeatureAvailability BM object containing all the bed special features
     * available in the database, it also sets the availability flag based on that the feature is effective
     * at current date or not.   
     *   
     * 
     * @param none 
     * @return List<BedSpecialFeatureAvailability>
     * @throws HCISException
     */

	List<BedSpecialFeatureAvailability> getAllBedSpecialFeatures() throws HCISException ;
	
	/**
	 * This method returns the bed pools matching with the specified search criteria. If there are no matching
	 * records found, this method returns an empty list 
	 * @param poolName full or partial pool name to be used in search - optional
	 * @param nursingUnitTypeCd nursing unit type code - optional
	 * @param effectiveFrom - date (from) which the bed was present in the system - unused
	 * @param effectiveTo - date (to) until when the bed pool is present in the system. 
	 *                      If specified, this value must be >= effective from date - unused
	 * @param start - starting position - useful in page wise query - required
	 * @param count - number of records to be returned per page - required
	 * @param orderBy - order by clause used in page wise reading - required
	 * @return
	 * @throws HCISException
	 */
	List<BedPoolBM> getBedPools(String poolName, 
								String nursingUnitTypeCd, 
								Date effectiveFrom, 
								Date effectiveTo) throws HCISException;
	
	/**
	 * This method returns the bed pools matching with the specified search criteria. If there are no matching
	 * records found, this method returns an empty list. It is being used by the UI layer 
	 * @param poolName full or partial pool name to be used in search - optional
	 * @param nursingUnitTypeCd nursing unit type code - optional
	 * @param effectiveFrom - date (from) which the bed was present in the system - unused
	 * @param effectiveTo - date (to) until when the bed pool is present in the system. 
	 *                      If specified, this value must be >= effective from date - unused
	 * @param start - starting position - useful in page wise query - required
	 * @param count - number of records to be returned per page - required
	 * @param orderBy - order by clause used in page wise reading - required
	 * @return
	 * @throws HCISException
	 */
	ListRange findBedPools(String poolName, String nursingUnitTypeCd,
							Date effectiveFrom, Date effectiveTo, int start,
							int count, String orderBy) throws HCISException;
	
	List<BedEnvelopeBM> getBedEnvelopes(String envelopeName,
										String facilityTypeInd,
										String poolName,
										Date effectiveFrom,
										Date effectiveTo) throws HCISException;

	ListRange findBedEnvelopes(String envelopeName,
			String facilityTypeInd,
			String poolName,
			Date effectiveFrom,
			Date effectiveTo,int start,
			int count, String orderBy) throws HCISException;

	
	/**
	 * 
	 * @param bedReservationBM
	 * @return
	 * @throws HCISException
	 */
	CodeAndDescription checkBedAvaibilityForBooking( BedReservationBM bedReservationBM ) throws HCISException;
	
	/**
	 * 
	 * @param bedReservationBM
	 * @return
	 * @throws HCISException
	 */
	BedReservationBM bookBed( BedReservationBM bedReservationBM ) throws HCISException;
	
	/**
	 * 
	 * @param bedReservationNbr
	 * @param unitTypeCode
	 * @param bedTypeCode
	 * @param bedNumber
	 * @param reservationStatusCd
	 * @param admissionReqNbr
	 * @param reservationFromDt
	 * @param reservationToDt
	 * @return
	 */
	List<BedReservationBM> getBedBookings(  Integer bedReservationNbr,
										   String unitTypeCode,
										   String bedTypeCode,
										   String bedNumber,
										   String reservationStatusCd,
										   Integer admissionReqNbr,
										   Date   reservationFromDt,
										   Date   reservationToDt);
			   
	/**
	 * 
	 * @param bedReservationNbr
	 * @param unitTypeCode
	 * @param bedTypeCode
	 * @param bedNumber
	 * @param reservationStatusCd
	 * @param admissionReqNbr
	 * @param reservationFromDt
	 * @param reservationToDt
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 * @throws HCISException
	 */
	ListRange findBedBookings(  Integer bedReservationNbr,
							   String unitTypeCode,
							   String bedTypeCode,
							   String bedNumber,
							   String reservationStatusCd,
							   Integer admissionReqNbr,
							   Date   reservationFromDt,
							   Date   reservationToDt,
							   int start, int count, String orderBy) throws HCISException;
	
	/**
	 * This method modified the existing bed reservation/booking record.
	 * If the reservation status is modified then it checks for validation for
	 * the status transition and proceed only if it is valid.
	 * It creates bedReservationActivity record if reservation status is modified.   
	 * @param modifiedBedReservationBM
	 * @return
	 */
	BedReservationBM modifyBedBooking( BedReservationBM modifiedBedReservationBM ) throws HCISException;
	
	/** This method CANCELS the existing booking only if it is eligible for cancellation.
	 * Creates bedReservation activity as well as.
	 * @param bedReservationNbr
	 * @return
	 */
	boolean cancelBooking( Integer bedReservationNbr , String cancelledBy) throws HCISException;
	
	BillDataBM getUnbilledBedUsage(Integer admissionReqNbr) throws HCISException;
	
	BillDataBM billBedAssignments(Integer admissionReqNbr,Integer billNumber,String billedBy) throws HCISException;
	
	boolean isBedExist( String bedNumber ) throws HCISException;
	
	public List<CodeAndDescription> getOccupiedBedFromPatient(Integer admissionReqNbr, Integer patientId ) throws HCISException;
	
	BedMasterBM releaseBed( String bedNumber,String bedNewStatus, String releasedBy) throws HCISException;
	
	BedMasterBM transferPatientToNewBed(String currentBedNumber,String newBedNumber, String transferredBy) throws HCISException;
}
