/**
 * 
 */
package in.wtc.hcis.ip.bo.order;

/**
 * @author Alok Ranjan
 *
 */
public interface DoctorOrderConstants {
	static final String ORDER_TYPE_IP_ADMISSION = "IP_ADMISSION";
	static final String ORDER_TYPE_GENERAL = "GENERAL";
	static final String ORDER_TYPE_DISCHARGE = "DISCHARGE";
	static final String ORDER_TYPE_TRANSFER = "TRANSFER";
	static final String ORDER_TYPE_MEDICATION = "MEDICATION";
	static final String ORDER_TYPE_TESTS = "TESTS";
	
	static final String ACTION_STATUS_CREATED = "CREATED";
	static final String ACTION_STATUS_COMPLETED = "COMPLETED";
	static final String ACTION_STATUS_NOT_APPLICABLE = "NA";
	static final String ACTION_STATUS_REJECTED = "REJECTED";
	static final String ACTION_STATUS_INPROGRESS = "INPROGRESS";
	static final String ACTION_STATUS_ASSIGNED = "ASSIGNED";
	static final String ACTION_STATUS_HOLD = "HOLD";
	
	static final String ACTIVITY_TYPE_ORDER_CREATED = "ORDER_CREATED";
	static final String ACTIVITY_TYPE_ORDER_MODIFIED = "ORDER_MODIFIED";
	static final String ACTIVITY_TYPE_ORDER_APPROVED = "ORDER_APPROVED";
	static final String ACTIVITY_TYPE_ORDER_DISAPPROVED = "ORDER_DISAPPROVED";
	static final String ACTIVITY_TYPE_ORDER_CANCELED = "ORDER_CANCELED";
	
	static final String ORDER_DICTATION_SELF = "SELF";
	static final String ORDER_DICTATION_TEMPLATE = "TEMPLATE";
	static final String ORDER_DICTATION_PHONE = "PHONE";
	
	static final String ORDER_STATUS_CREATED = "CREATED";
	static final String ORDER_STATUS_APPROVED = "APPROVED";
	static final String ORDER_STATUS_DISAPPROVED = "DISAPPROVED";
	static final String ORDER_STATUS_COMPLETED = "COMPLETED";
	static final String ORDER_STATUS_CANCELED = "CANCELED";
	
	// Admission status
	static final String ADMISSION_STATUS_REQUESTED = "REQUESTED";
	static final String ADMISSION_STATUS_ADMITTED ="ADMITTED";
	static final String ADMISSION_STATUS_PREDISCHARGE="PRE_DISCHARGE";
	static final String ADMISSION_STATUS_DISCHARGE = "DISCHARED";
	
	// Admission Order specific attributes
	static final String NURSING_UNIT_TYPE_CD_ATTR = "unitType";
	static final String ADMISSION_ENTRY_POINT_CD_ATTR = "entryPoint";
	static final String ADMISSION_ENTRY_POINT_REFERENCE_ATTR = "entryPointReference";
	static final String ADMISSION_AGENDA_ATTR = "agenda";
	static final String ESTIMATED_TREATMENT_COST_ATTR = "estimatedCost";
	
	static final String ADMISSION_STATUS_ATTR = "admissionStatus";
	static final String EXPECTED_DISCHARGE_DT_ATTR = "expectedDischargeDate";
	static final String REASON_FOR_ADMISSION_ATTR = "reasonForAdmission";
	
	static final String DEFAULT_ADMISSION_AGENDA = "DEFAULT_ADMISSION_AGENDA";
	
	
	// Discharge Order specific attributes
	static final String DISCHARGE_TYPE_CD_ATTR = "dischargeType";
	
	// Discharge Status #same as order status#
	
	static final String DISCHARGE_STATUS_CREATED =  ORDER_STATUS_CREATED;
	static final String DISCHARGE_STATUS_COMPLETED= ORDER_STATUS_COMPLETED;
	
	/**
	 * Discharge type
	 * The default discharge type is HOME, which means patient has been discharged to home.
	 * For the sake of records, other frequently used discharge types are
	 * -- ANOTHER_FACILITY
	 * -- HOME_WITH_ASSISTANCE
	 * -- AGAINST_MEDICAL_ADVICE
	 * -- EXPIRATION 
	 */ 
	static final String DEFAULT_DISCHARGE_TYPE = "HOME";
	
	static final String ADMISSION_ACTIVITY_PREFIX = "ADM_";
	static final String DISCHARGE_ACTIVITY_PREFIX = "DISC_";
	
	static final String ATTRIBUTE_IS_MANDATORY_YES = "Y";
	static final String ATTRIBUTE_IS_MANDATORY_NO = "N";
	static final String ATTRIBUTE_TYPE_MVL = "MVL";
	
	static final String PACKAGE_INDICATOR = "Y";
	
	static final String PACKAGE_INDICATOR_NO = "N";
	
	static final String REFERENCE_TYPE_IPD ="IPD";
	static final String REFERENCE_TYPE_PAT ="PAT";
}
