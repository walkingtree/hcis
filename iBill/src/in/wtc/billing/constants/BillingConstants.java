/**
 * 
 */
package in.wtc.billing.constants;

/**
 * @author Alok Ranjan
 *
 */
public interface BillingConstants {
	
	
	// Bill Setting attribute names
	String BILL_DUE_DATE_PERIOD = "BILL_DUE_DATE_PERIOD";
	String DUE_DATE_FROM_CALENDAR = "DUE_DATE_FROM_CALENDAR";
	String EVALUATE_DEPOSIT_REFUND = "EVALUATE_DEPOSIT_REFUND";
	String CREATE_TRNSCT_DETAILS_FLAG = "CREATE_TRNSCT_DETAILS_FLAG";
	String FREE_DUPLICATE_BILL_COUNT = "FREE_DUPLICATE_BILL_COUNT";
	String DUPLICATE_BILL_PRINT_CHARGE = "DUPLICATE_BILL_PRINT_CHARGE";
	
	
	// Billing Processes
	String PROCESS_ACCOUNTING = "ACCOUNTING";
	
	
	// Generic constants
	String FLAG_VALUE_YES = "Y";
	String FLAG_VALUE_NO = "N";
	
	// Billing errors
	String FNCL_CHARGE_TYPE_DOES_NOT_EXIST = "FNCL_CHARGE_TYPE_DOES_NOT_EXIST";
	String BILL_DOES_NOT_EXIST = "BILL_DOES_NOT_EXIST";
	String CHARGE_TYPE_DOES_NOT_EXIST = "CHARGE_TYPE_DOES_NOT_EXIST";
	String PROCESS_NOT_REGISTERED = "PROCESS_NOT_REGISTERED";
	String PROCESS_INSTANTIATION_FAILED = "PROCESS_INSTANTIATION_FAILED";
	String BILL_ACTIVITY_NOT_FOUND = "BILL_ACTIVITY_NOT_FOUND";
	
	// Billing Activities
	String BILLING_STARTED_FOR_PROCESS = "STARTED";
	String BILLING_COMPLETED_FOR_PROCESS = "COMPLETED";
	String BILLING_ERRORED_FOR_PROCESS = "ERRORED";
	
	// Messages
	String DUPLICATE_BILL_MEMO = "Duplicate Bill Charge";
	String FNCL_CHARGES = "Financial Charges";
}
