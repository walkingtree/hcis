/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.LabPatientTestChangeHistory;
import com.wtc.hcis.da.LabPatientTestChangeHistoryDAO;

/**
 * @author Bhavesh
 *
 */
public class LabPatientTestChangeHistoryDAOExtn extends
		LabPatientTestChangeHistoryDAO {

	private static final String PATIENT_TEST_ID = "id.patientTestId"; 
	private static final String CREATION_DATE = "id.createdDtm";
	
	public List<LabPatientTestChangeHistory> getHistory(String patientTestId){
		
		DetachedCriteria criteria = DetachedCriteria.forClass( LabPatientTestChangeHistory.class);
		
		criteria.add( Restrictions.eq(PATIENT_TEST_ID, patientTestId))
				.addOrder(Order.asc(CREATION_DATE));

		return getHibernateTemplate().findByCriteria(criteria);
	}
	

}
