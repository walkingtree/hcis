package com.wtc.hcis.da.extn;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.DocPatientVital;
import com.wtc.hcis.da.DocPatientVitalDAO;

public class DocPatientVitalDAOExtn extends DocPatientVitalDAO {
	public List<DocPatientVital> getPatientVitalList(String referenceNbr,
													String referenceType){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(DocPatientVital.class);
		
		
		if( referenceNbr != null && !referenceNbr.isEmpty()){
			criteria.add(Restrictions.eq(DocPatientVitalDAOExtn.REFERENCE_NUMBER, referenceNbr));
		}
		
		if( referenceType != null && !referenceType.isEmpty()){
			criteria.add(Restrictions.eq(DocPatientVitalDAOExtn.REFERENCE_TYPE, referenceType));
		}
		
		
		return getHibernateTemplate().findByCriteria(criteria);
	}
}
