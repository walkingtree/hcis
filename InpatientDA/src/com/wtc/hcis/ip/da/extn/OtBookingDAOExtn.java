/**
 * 
 */
package com.wtc.hcis.ip.da.extn;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.OtBooking;
import com.wtc.hcis.ip.da.OtBookingDAO;

/**
 * @author Bhavesh
 *
 */
public class OtBookingDAOExtn extends OtBookingDAO {

	public List<OtBooking> getBookingDetail(String otId,String surgeryCode, Date   bookingFromDtm,Date   bookingToDtm){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(OtBooking.class);
		
		if( otId != null && !otId.isEmpty()){
			
			criteria.add(Restrictions.eq("otDetail.otId", otId));
		}
		
		if( surgeryCode != null && !surgeryCode.isEmpty()){
			
			criteria.add(Restrictions.eq(surgeryCode, surgeryCode));
		}
		
		if( bookingFromDtm != null ){
			criteria.add( Restrictions.ge("bookingFromDtm", bookingFromDtm));
		}
		
		if( bookingToDtm != null ){
			criteria.add( Restrictions.le("bookingToDtm", bookingToDtm));
		}
		
		criteria.addOrder(Order.asc("bookingFromDtm"));
		
		return getHibernateTemplate().findByCriteria(criteria);
	}
}
