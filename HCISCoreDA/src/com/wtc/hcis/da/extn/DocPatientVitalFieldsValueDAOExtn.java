package com.wtc.hcis.da.extn;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.DocPatientVitalFieldsValue;
import com.wtc.hcis.da.DocPatientVitalFieldsValueDAO;

public class DocPatientVitalFieldsValueDAOExtn extends
		DocPatientVitalFieldsValueDAO {
	
	public static String PATIENT_VITAL_ID = "id.patientVitalId";
	public static String FOR_TIME = "id.forTime";
	
	public List<DocPatientVitalFieldsValue> getPatientVitalFieldsValueList(
			Integer patientVitalId,
			Date fromDate,
			Date toDate){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(DocPatientVitalFieldsValue.class);
		
		if( patientVitalId != null){
			
			criteria.add(Restrictions.eq(DocPatientVitalFieldsValueDAOExtn.PATIENT_VITAL_ID, patientVitalId));
		}

		if( fromDate != null ){
			criteria.add(Restrictions.ge(DocPatientVitalFieldsValueDAOExtn.FOR_TIME, fromDate));
		}
		
		if( toDate != null ){
			criteria.add(Restrictions.le(DocPatientVitalFieldsValueDAOExtn.FOR_TIME, toDate));
		}	
	
		return getHibernateTemplate().findByCriteria(criteria);
	}

}
