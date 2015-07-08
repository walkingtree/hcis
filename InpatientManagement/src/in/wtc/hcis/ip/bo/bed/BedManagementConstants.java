/**
 * 
 */
package in.wtc.hcis.ip.bo.bed;

/**
 * @author Alok Ranjan
 *
 */
public interface BedManagementConstants {
	static final String BED_STATUS_AVAILABLE = "AVAILABLE";
	static final String BED_STATUS_OCCUPIED="OCCUPIED";
	static final String BED_STATUS_RETIRED="RETIRED";
	
	static final String BED_ACTIVITY_PREFIX = "BED_";
	static final String BED_ACTIVITY_AVAILABLE = "BED_AVAILABLE";
	static final String BED_ACTIVITY_OCCUPIED = "BED_OCCUPIED";
	static final String BED_ACTIVITY_ADDED="BED_ADDED";
	static final String BED_ACTIVITY_RETIRED="BED_RETIRED";
	static final String BED_ACTIVITY_UNIT_CHANGED="BED_UNIT_CHANGED";
	
	static final String BED_ORGANIZATION_ORDER_TYPE_EMPTY = "EMPTY_ORDER_TYPE";
	
	static final String BED_TRANSFER_IND = "T";
	static final String BED_DISCHARGE_IND = "D";
	
	static final String RESERVATION_STATUS_CONFIRMED = "CONFIRMED";
	static final String RESERVATION_STATUS_WAITLIST = "WAITLIST";
	static final String RESERVATION_STATUS_ASSIGNED = "ASSIGNED";
	static final String RESERVATION_STATUS_CANCELED = "CANCELED";
	static final String RESERVATION_ACTIVITY_PREFIX = "BED_RES_";
	
	static final String RESERVATION_STATUS_REGRET = "REGRET";
	static final String REQUIRED_FLAG_YES = "Y";
	static final String REQUIRED_FLAG_NO = "N";
	
	static final String BED_DEFAULT_FLOOR = "Uncategorised";
	static final String BILLING_PROCESS_BED = "BED";
	static final String BILLING_SUBPROCESS_BED = "BED_USAGE";
	
	
}
