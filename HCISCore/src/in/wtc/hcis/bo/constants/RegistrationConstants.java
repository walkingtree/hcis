/**
 * 
 */
package in.wtc.hcis.bo.constants;

/**
 * @author Bhavesh
 *
 */
public interface RegistrationConstants {

	static String REFERENCE_TYPE_PAT = "PAT";
	static String REFERENCE_TYPE_OPD = "OPD";
	static String REFERENCE_TYPE_DOCTOR = "DOC";
	static String REFERENCE_TYPE_DIR ="DIR";
	static String REFERENCE_TYPE_EMR = "EMR";
	static String REFERENCE_TYPE_REFERRAL = "REFERRAL";
	static String REFERENCE_TYPE_SPONSOR = "SPONSOR";
	static String REFERENCE_TYPE_IPD = "IPD";
	
	static String REGISTRTION_TYPE_DIRECT = "DIRECTSERVICEREGISTRATION";
	static String REGISTRATION_TYPE_EMRGENCY = "EMERGENCY";
	static String REGISTRATION_TYPE_NORMAL ="NORMAL";
	static Double REGISTRATION_FEE = 50.0;
	static String RECEIVABLE_MEMO = "Registration-";
	
	static String HEIGHT_INDICATOR ="HEIGHT";
	static String WEIGHT_INDICATOR = "WEIGHT";
	
	static String REGISTRATION_SVC_CD = "REGISTRATION";
	static String APPOINTMENT_SVC_CD = "APPOINTMENT";
	static String DEFAULT_PATIENT_CATEGORY="DEFAULT_PATIENT_CATEGORY";
	
	//ADDRESS STATUS
	
	static String ADDRESS_STATUS_CURRENT="CURRENT";
	static String ADDRESS_STATUS_PERMANENT="PERMANENT";
	
	
}
