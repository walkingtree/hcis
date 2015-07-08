/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.AppointmentHistory;
import com.wtc.hcis.da.AppointmentHistoryDAO;

/**
 * @author Alok Ranjan
 *
 */
public class AppointmentHistoryDAOExtn extends AppointmentHistoryDAO {

	public static final String APPOINTMENT_NUMBER = "id.appointmentNumber";
	public List<AppointmentHistory> getAppointmentHistory( Integer appointmentNumber ) {
		DetachedCriteria appointmentsHistoryCriteria = DetachedCriteria.forClass( AppointmentHistory.class );
		
		appointmentsHistoryCriteria.add( Restrictions.eq( AppointmentHistoryDAOExtn.APPOINTMENT_NUMBER, appointmentNumber ) );
		
		List<AppointmentHistory>appointmentHistoryList = getHibernateTemplate().findByCriteria( appointmentsHistoryCriteria );	
		
		if ( appointmentHistoryList != null && !appointmentHistoryList.isEmpty() ) {
			
			return appointmentHistoryList;
		} else {
			return null;
		}
		
	}
}
