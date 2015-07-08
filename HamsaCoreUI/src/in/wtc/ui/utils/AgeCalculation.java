package in.wtc.ui.utils;

import java.util.Date;

public class AgeCalculation {

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
		    return (y1 - y2) == 0?( m1 - m2)==0? (d1 - d2)+"days":(m1 - m2)+"months":(y1 - y2)+"years";
		}
	
}
