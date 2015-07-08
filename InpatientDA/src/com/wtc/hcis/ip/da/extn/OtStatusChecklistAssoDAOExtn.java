package com.wtc.hcis.ip.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.OtStatusChecklistAsso;
import com.wtc.hcis.ip.da.OtStatusChecklistAssoDAO;

public class OtStatusChecklistAssoDAOExtn extends OtStatusChecklistAssoDAO {
	
	private static String STATUS_CODE = "id.statusCode";
	private static String APPLY_WHEN = "applyWhen";

	public List<OtStatusChecklistAsso> getStatusCheckListAssoList( String statusCode,
												String applyWhen){
		DetachedCriteria criteria = DetachedCriteria.forClass(OtStatusChecklistAsso.class);
		
		if( statusCode != null && !statusCode.isEmpty()){
			criteria.add(Restrictions.eq(OtStatusChecklistAssoDAOExtn.STATUS_CODE, statusCode));
		}
		
		if( applyWhen != null && !applyWhen.isEmpty()){
			criteria.add(Restrictions.eq(OtStatusChecklistAssoDAOExtn.APPLY_WHEN, applyWhen));
		}
		
		return getHibernateTemplate().findByCriteria(criteria);
	}
}
