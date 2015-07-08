/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.ReferralPayable;
import com.wtc.hcis.da.ReferralPayableDAO;

/**
 * @author Alok Ranjan
 *
 */
public class ReferralPayableDAOExtn extends ReferralPayableDAO {
	
	public static String REFERRAL_CODE = "id.referralCode";
	public static String REFERRAL_TO_DTM = "referralToDtm";
	public static String REFERRAL_PAYABLE_CREATE_DTM = "id.payableCreateDtm";
	
	
	public Date getLastProcessDate(  Integer referralCode ){
		String hqlQuery = "select max( processedDtm ) from ReferralPayable where " + ReferralPayableDAOExtn.REFERRAL_CODE + " = " + referralCode;
		
		Query admissionNbrQuery = getSession().createQuery( hqlQuery );
		
		List<Object> resultList = admissionNbrQuery.list();
		
		Date maxProcessedDate = null;
		
		if ( resultList != null && 
			 !resultList.isEmpty() && 
			 resultList.get(0) != null) {
			
			maxProcessedDate = (Date)resultList.get(0);
		}
		
		return maxProcessedDate;
	}
	
	public List<ReferralPayable> getUnAccountedReferralPayable( Integer referralCode, String accountableFlag ) {
		List<ReferralPayable> referralPayableList = null;
		DetachedCriteria  criteria = DetachedCriteria.forClass( ReferralPayable.class );
		
		criteria.add( Restrictions.eq(ReferralPayableDAOExtn.REFERRAL_CODE, referralCode) )
		        .add( Restrictions.eq ( ReferralPayableDAOExtn.ACCOUNTED, accountableFlag ) )  
		        .addOrder( Order.desc(ReferralPayableDAOExtn.REFERRAL_PAYABLE_CREATE_DTM ) );
		
		referralPayableList = getHibernateTemplate().findByCriteria( criteria );
		
		return referralPayableList;
	}
}
