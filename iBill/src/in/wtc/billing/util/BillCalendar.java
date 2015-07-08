/**
 * 
 */
package in.wtc.billing.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Alok Ranjan
 *
 */
public class BillCalendar  {

	public static Date getDateAfterToday( int numberOfWorkingDays ) {
		Calendar calendar = Calendar.getInstance();
		
		//
		// Ideally this should get calculated based on the calendar being setup by the 
		// corresponding organization
		//
		calendar.add(Calendar.DAY_OF_YEAR, numberOfWorkingDays );
		
		return calendar.getTime();
	}
}
