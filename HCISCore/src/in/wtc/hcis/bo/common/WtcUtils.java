/**
 * 
 */
package in.wtc.hcis.bo.common;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Bhavesh
 *
 *This class contains general utility method
 */
public class WtcUtils {

	public static ListRange convertListToListRange(List listObject){
		
		ListRange listRange = new ListRange();
		if( listObject != null && !listObject.isEmpty() ){
			
			Object[] objects = listObject.toArray();
			listRange.setData( objects );
			listRange.setTotalSize(listObject.size());
		}else{
			
			listRange.setTotalSize(0);
			listRange.setData( new Object[0]);
		}
		
		return listRange;
	}
	
	
	public static ListRange convertListToListRange(List listObject , int start, int count){
		
		ListRange listRange = new ListRange();
		int index = 0;
		
		List<Object> pageSizeData = new ArrayList<Object>(0); 
		
		if( listObject != null && !listObject.isEmpty() ){
			
			while( (start+index < start + count) && (listObject.size() > start+index) ){
				
				pageSizeData.add( listObject.get(start+index) );
				index++;
			}
				listRange.setData(pageSizeData.toArray());
				listRange.setTotalSize(listObject.size());
			}else {
				listRange.setData(new Object[0]);
				listRange.setTotalSize(0);
			}
			
			return listRange;
	}
		
		
		
	/**
	 * This method checks whether code is valid or not for given  CodeAndDescription object
	 * @param codeAndDescription
	 * @return
	 */
	public static boolean isValidCode( CodeAndDescription codeAndDescription){
		
		if( codeAndDescription != null && codeAndDescription.getCode()!= null
				&& !codeAndDescription.getCode().isEmpty()){
			
			return true;
		}else{
			return false;
		}
	}
/**
 * This static method checks whether the given object is valid for manipulation or not.
 * @param obj
 * @return
 */
	public static boolean isValid(Object obj){
	
		if( obj instanceof java.lang.String  ){
			String stringObj = (String) obj;
	
			if( stringObj != null && stringObj.length() >0  ){
				return true;
			}
			
			return false;
		}
		
		if( obj instanceof java.util.List  ){
			List listObj = (List) obj;
	
			if( listObj != null && !listObj.isEmpty()  ){
				return true;
			}
			
			return false;
		}
	
		if( obj instanceof java.util.Map  ){
			Map mapObj = (Map) obj;
	
			if( mapObj != null && !mapObj.isEmpty()  ){
				return true;
			}
			
			return false;
		}
	
	//For other objects i.e. Integer,Double or Custom objects		
		if( obj != null ){
			return true;
		}
	
		return false;
	
	}
	
	/*
	 * This method is for Age calculation.
	 */
	


	 public static String calculateDOB( Date dob){
		 
		 	if(dob == null){
		 		return null;
		 	}
		 	
		 	Date dat = new Date();
		 	int curday = dat.getDate();
		 	int curmon = dat.getMonth() + 1;
		 	int curyear = dat.getYear();
		 	
		 	
		 	int dobDays = dob.getDate();
		 	int dobmonths = dob.getMonth() + 1;
		 	int dobyear = dob.getYear();
		 	
		 	String age =null;
		 	
		 	if (curday == 0 || curmon == 0 || curyear == 0 || dobDays == 0 || dobmonths == 0 || dobyear == 0) {
		 		System.out.println("PatientManagementController.calculateDOB()"+"please fill all the values and click go -");
		 	}
		 	else {
		 		Date curd = new Date(curyear, curmon - 1, curday);
		 		Date cald = new Date(dobyear, dobmonths - 1, dobDays);
		 		
		 		Long diff = Date.UTC(curyear, curmon, curday, 0, 0, 0) - Date.UTC(dobyear, dobmonths, dobDays, 0, 0, 0);
		 		
		 		age = datediff(curd, cald);
				
		 	}
			return age;
		 }
		 
		 private static boolean checkleapyear(int year){
			if(year%4 == 0)
			{
				if(year% 10 != 0)
				{
					return true;
				}
				else
				{
					if(year% 400 == 0)
						return true;
					else
						return false;
				}
			}
		return false;
		}

		private static int DaysInMonth(int year, int month) {
			
			int days = 0;
			if(month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11){
				days = 31;
			}
			else if(month == 3 || month == 5 || month == 8 || month == 10){
				 days = 30;
				}
			else {
				boolean isLeafYear = checkleapyear(year); 
				if(isLeafYear ){
					days = 29;
				}else {
					days = 28;
				}
			}
	         return days;
		}
		/**
		 * 
		 * @param date1
		 * @param date2
		 * @return based on the condition we are returning just years or just months or just days.
		 *  condition is like: 
		 *  1.if years gr than zero we just return years of that patient, if years equals to zero then we check
		 *  months.
		 *  2. if months gr than zero we just return months of the patient, if months equals to zero we return just days of the patient 
		 */
		private static String datediff(Date date1, Date date2) {
		    int y1 = date1.getYear(), m1 = date1.getMonth(), d1 = date1.getDate(),
			 y2 = date2.getYear(), m2 = date2.getMonth(), d2 = date2.getDate();

		    if (d1 < d2) {
		        m1--;
		        d1 += DaysInMonth(y2, m2);
		    }
		    if (m1 < m2) {
		        y1--;
		        m1 += 12;
		    }
		    
//		    return ""+(y1 - y2)+"years" +(m1 - m2)+"months"+(d1 - d2)+"days";
		    return (y1 - y2) == 0?( m1 - m2)==0? (d1 - d2)+"days":(m1 - m2)+"months":(y1 - y2)+" years";
		}
	

	
	
}
