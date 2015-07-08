/**
 * 
 */
package in.wtc.hcis.bo.common;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Alok Ranjan
 *
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {
	
	public static String getTimeHHMMFromDate( Date inputDate ) {
		StringBuilder time = new StringBuilder();
		
		if ( inputDate.getHours() < 10 ) {
			time.append( "0" ).append( inputDate.getHours() );
		} else {
			time.append( inputDate.getHours() );
		}
		
		if  ( inputDate.getMinutes() < 10 ) {
			time.append( "0" ).append( inputDate.getMinutes() );
		} else {
			time.append( inputDate.getMinutes() );
		}
		
		return time.toString();
	}
	/**
	 * 
	 * This method updates the given date to make it applicable for search as 'To Date'.
	 * 
	 * @param inputDate
	 */
	public static void updateDateForSearch( Date inputDate ){
		
		if ( inputDate != null ) {
			inputDate.setHours(0);
			inputDate.setMinutes(0);
			inputDate.setSeconds(0);
			long time = 0;
			time = inputDate.getTime();
			time = time + 24 * 60 * 60 * 1000 ;
			inputDate.setTime(time);
		}
	}
	
	/**
	 * Moves given date object to previous date
	 * @param dateObject
	 * @return
	 */
	public static Date previousDate( Date dateObject ){
		
		
		Calendar calendar = Calendar.getInstance();
	
		calendar.setTime( dateObject );
		
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 0);
		
		//now move it to one day back
		
		calendar.setTimeInMillis( calendar.getTimeInMillis() - DateUtils.MILLIS_IN_DAY  );
		
		return calendar.getTime();
	}
	
	/**
	 * Moves given date object to next date
	 * @param dateObject
	 * @return
	 */
	public static Date nextDate( Date dateObject ){
		
		
		Calendar calendar = Calendar.getInstance();
	
		calendar.setTime( dateObject );
		
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 0);
		
//		calendar.add( Calendar.DATE, 1); TODO: try this also
		
		//now move it to next day
		
		calendar.setTimeInMillis( calendar.getTimeInMillis() + DateUtils.MILLIS_IN_DAY );
		
		return calendar.getTime();
	}
	
	/**
	 * Set time factor of give Date object to 0.
	 * 
	 * 
	 * @param dateObject
	 * @return
	 */
	public static Date truncateTime( Date dateObject ){
			
			Calendar calendar = Calendar.getInstance();
		
			calendar.setTime( dateObject );
			
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.HOUR, 0);
			
			return calendar.getTime();
	}
}
