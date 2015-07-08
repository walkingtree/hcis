package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.DateDim;
import com.wtc.hcis.da.DateDimDAO;
import com.wtc.hcis.da.Doctor;

public class DateDimDAOExtn extends DateDimDAO {

	public DateDim  getDateDimForDate(Integer yearMonthDate){
		DateDim dateDim = null; 
		DetachedCriteria dateDimCriteria = DetachedCriteria.forClass(DateDim.class);
		
		if( yearMonthDate != null){
			dateDimCriteria.add(Restrictions.eq(DateDimDAOExtn.YEAR_MONTH_DATE, yearMonthDate));
		}
		
		
		List<DateDim> result = getHibernateTemplate().findByCriteria(dateDimCriteria);
		
		if( result != null && !result.isEmpty()){
			
			dateDim = result.get(0);
		}
		 
		return dateDim;
		
	}
}
