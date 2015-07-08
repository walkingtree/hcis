package in.wtc.utils;


import java.util.Date;

import com.wtc.usermanager.bm.CodeAndDescription;

public class Converters {
	
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
	
	public static CodeAndDescription setCodeAndDescriptionNull(CodeAndDescription entity) {
		if(entity == null) {
			CodeAndDescription codeAndDescription = new CodeAndDescription(" "," ");
			return codeAndDescription;
		}else {
			return entity;
		}
	}


}
