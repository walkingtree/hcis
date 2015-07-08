/**
 * 
 */
package in.wtc.hcis.bo.constants;

/**
 * @author Sandeep
 *
 */
public interface ConfigurationConstants {
	static String IS_ROSTER_REQUIRED_NAME = "IS_ROSTER_REQUIRED";
	static String IS_ROSTER_REQUIRED_VALUE = "Y";
	static String ATTR_TYPE_MVL = "MVL";

	//Bed billing related stuffs
	static String BED_FIXED_CHECKOUT_TIME ="BED_FIXED_CHECKOUT_TIME";
	static String BED_CHECKOUT_TIME_VALUE = "BED_CHECKOUT_TIME_VALUE";
	
	//Order needs to be approve immediately after creation
	static String ORDER_APPROVE_AT_CREATION = "ORDER_APPROVE_AT_CREATION";
	
	static String FOLLOWUP_CHARGE = "FOLLOWUP_CHARGE";
	static String FOLLOWUP_DAY = "FOLLOWUP_DAY";
	static String FOLLOWUP_VISIT = "FOLLOWUP_VISIT";
	
	
	static String PARAM_VALUE_YES = "Y";
	static String PARAM_VALUE_NO = "N";
	
}
