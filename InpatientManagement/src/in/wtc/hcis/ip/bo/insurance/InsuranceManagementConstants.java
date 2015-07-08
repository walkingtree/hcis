/**
 * 
 */
package in.wtc.hcis.ip.bo.insurance;

/**
 * @author Bhavesh
 *
 */
public interface InsuranceManagementConstants {
	static final String PERCENT_ABS_IND_PRECENT = "P";
	static final String PERCENT_ABS_IND_ABSOLUTE = "A";
	
	
	static final String CLAIM_STATUS_CREATED =      "CREATED";
	static final String CLAIM_STATUS_SUBMITTED =    "SUBMITTED";
	static final String CLAIM_STATUS_APPROVED =     "APPROVED";
	static final String CLAIM_STATUS_PARTAPPROVED = "PARTAPPROVED";
	static final String CLAIM_STATUS_REJECTED =     "REJECTED";
	static final String CLAIM_STATUS_MOREINFO =     "MOREINFO";
	static final String CLAIM_STATUS_RESUBMITTED =  "RESUBMITTED";
	
	static final String CLAIM_REQ_ACTIVITY_PREFIX = "CLM_REQ_";
	
	static final String CP_MUST_COLLECT_PATIENT_AMOUNT = "CP_MUST_COLLECT_PATIENT_AMOUNT";
	
	// Financial transaction types
	static final String FTT_CLAIMED_BY_PATIENT = "CBP";
	static final String FTT_CLAIMED_AGAINST_SPONSORS = "CAS";
	
	// Adjustment types
	static final String ADJ_TYPE_CLAIMED_BY_PATIENT = "ADJCP";
	static final String ADJ_TYPE_CLAIMED_AGAINST_SPONSORS = "ADJCS";
	static final String ACCOUNTING_SUBPROCESS_DEBITADJ = "DEBITADJ";
	
	static final String CLAIMED_AGAINST_SPONSORS = "Claim against sponsor";
	
	static final String BILLING_PERSON_ID = "BILLING";
	
	static final String ACCOUNT_ID_PREFIX_SPONSOR = "SPONSOR";
	static final String ACCOUNT_ENTITY_SPONSOR = "SPONSOR";
	
	static final String BILL_PROCESS_NAME_ACCOUNTING = "ACCOUNTING";
	static final String BILL_SUBPROCESS_NAME_MEDICLAIM = "MEDICLAIM";
	
	static String REFERENCE_TYPE_SPONSOR = "SPONSOR";
	static String SPONSOR_CONTACT_TYPE = "CURRENT";
	static String SPONSOR_ENTITY_CATEGORY_CODE = "CURRENT";

	static String SPONSOR_DEFAULT_COUNTRY_CD = "208";
}
