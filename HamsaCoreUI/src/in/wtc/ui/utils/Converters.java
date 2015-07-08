/**
 * 
 */
package in.wtc.ui.utils;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.util.Date;

/**
 * @author alok
 *
 */
public class Converters {
	
	public static Date getTimeAndDate( String time , Date date){
			
			if(!(time.equals(null) || time.equals(""))){
	
				String [] hrminsec = null;
				hrminsec = time.split(":");
				
				int year = date.getYear();
				int month = date.getMonth();
				int day = date.getDate();
				int hrs = new Integer(hrminsec[0]);
				int min = new Integer(hrminsec[1]);
				int sec = new Integer(hrminsec[2]);
				
				Date newDate = new Date(year + 1900, month, day, hrs, min, sec); 
				
				return newDate;
				
			}else {
				return null;
			}
		}
	
	public static String getTimeWithoutColon(String time){
		if( time != null && ! time.equals("")){
			String[] token = time.split(":");
			
			if( token.length > 1){
				String newTime = token[0]+token[1];
				return newTime;
			}
		}
		
		return time;
	}

	
	public static Date getDate( Date date){
		if( date != null && (date.getYear() + 1900 == 1970)){
			return null;
		}else{
			return date;
		}
	}
	
	public static String getEntityValue( String entity){
		if(entity!=null && entity.equals("")){
			return null;
		}else{
			return entity;
		}
	}
	
	public static Boolean convertChkValueFromString2Boolean(String  chkValue) {
		if(chkValue.equals("on")){
			return true;
		}else {
			return null;
		}
	
	}
	
	public static CodeAndDescription getEntityValue( CodeAndDescription entity){
		if( entity != null && (entity.getCode() == null || entity.getCode().equals(""))){
			return entity = null;
		}else{
			return entity;
		}
	}

	
	public static Date getDate (String date) {
		if(date != null && !date.equals("undefined") && !date.equals("")) {
			String[] tokens = date.split("/");
			int days = new Integer(tokens[0]);
			int months = new Integer(tokens[1]);
			int years = new Integer(tokens[2]);
			Date convertedDate = new Date(years-1900, months-1,days);
			return convertedDate;
		}else {
			return null;
		}
		
	}
	
	public static String getTimeWithColon( String time){
		if(!time.equals("")){
			String hr = time.substring(0, 2);
			String min = time.substring(2,4);
			
			String sec = "00";
			
			if( hr.length()== 1){
				hr = "0" + hr ;
			}
			
			if( min.length() == 1){
				min = "0" + min ;
			}
			
			return hr+":"+min+":"+sec ;
		}else{
			return null;
		}
	}
	
	public static String getDateInStringFormat( Date date){
		if( date != null){
			String year = new Integer(date.getYear() + 1900).toString();
			String month = new Integer(date.getMonth() + 1).toString();
			String day = new Integer(date.getDate()).toString();
			
			if(month.length() == 1){
				month = "0" + month;
			}
			
			if( day.length() == 1){
				day = "0" + day;
			}
			
			return month +"/"+  day +"/"+ year ;
		}else{
			return null;
		}
	}
	
	public static boolean checkLeapYear(String year) {
		Integer yrs = new Integer(year); 
		if(yrs%4 == 0)
		{
			if(yrs% 10 != 0)
			{
				return true;
			}
			else
			{
				if(yrs% 400 == 0)
					return true;
				else
					return false;
			}
		}
	return false;
	}
	/**
	 * it takes the codeAndDescription type object and converts code and description to null 
	 * if the object is null.
	 * @param entity
	 * @return
	 */
	public static CodeAndDescription setCodeAndDescriptionNull(CodeAndDescription entity) {
		if(entity == null) {
			CodeAndDescription codeAndDescription = new CodeAndDescription(" "," ");
			return codeAndDescription;
		}else {
			return entity;
		}
	}

}
