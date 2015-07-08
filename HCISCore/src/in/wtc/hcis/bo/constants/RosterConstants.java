/**
 * 
 */
package in.wtc.hcis.bo.constants;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rohit
 *
 */
public final class RosterConstants {
	public static final String DAILY = "DAILY";
	public static final String WEEKLY = "WEEKLY";
	public static final String MONTHLY = "MONTHLY";
	public static final Map<String, Integer> CALENDER_CONSTANT;
	public static final Integer NO_OF_YEARS = new Integer("200");
	static {
		 CALENDER_CONSTANT = new HashMap<String, Integer>();
		 CALENDER_CONSTANT.put(DAILY, Calendar.DATE);
		 CALENDER_CONSTANT.put(WEEKLY, Calendar.WEEK_OF_YEAR);
		 CALENDER_CONSTANT.put(MONTHLY, Calendar.MONTH);
	}

}
