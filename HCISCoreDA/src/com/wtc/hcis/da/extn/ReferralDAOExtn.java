/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.Referral;
import com.wtc.hcis.da.ReferralDAO;

/**
 * @author Alok Ranjan
 *
 */
public class ReferralDAOExtn extends ReferralDAO {
	
	public static String REFERRAL_CODE = "referralCode";
	public static String COUNTRY_CODE = "country.countryCode";

	public List<Referral> getActiveReferral() {
		List<Referral> referralList = null;
		DetachedCriteria  criteria = DetachedCriteria.forClass(Referral.class);
		
		criteria.add( Restrictions.eq(ReferralDAO.ACTIVE, "Y" ) );
		criteria.addOrder(Order.asc(ReferralDAO.REFERRAL_NAME));
		referralList = getHibernateTemplate().findByCriteria(criteria);
		
		return referralList;
	}
	
	public List<Referral> getActiveReferralOfType(String referralType) {
		List<Referral> referralList = null;
		DetachedCriteria  criteria = DetachedCriteria.forClass(Referral.class);
		
		criteria.add(Restrictions.eq(ReferralDAO.REFERRAL_TYPE_CODE, referralType))
				.add( Restrictions.eq(ReferralDAO.ACTIVE, "Y" ) )
				.addOrder(Order.asc(ReferralDAO.REFERRAL_NAME));
		referralList = getHibernateTemplate().findByCriteria(criteria);
		
		return referralList;
	}
	public List<Referral> getActiveReferral( String city, 
			                                 String country, 
			                                 String pinCode, 
			                                 String preferredContactTime, 
			                                 String referralAddress,
			                                 Integer referralCode, 
			                                 String referralName,
			                                 String referralType, 
			                                 String state ) {
		List<Referral> referralList = null;
		DetachedCriteria  criteria = DetachedCriteria.forClass(Referral.class);
		
		criteria.add( Restrictions.eq( ReferralDAOExtn.ACTIVE, "Y" ) );
		
		if ( city != null && city.trim().length() > 0 ) {
			criteria.add( Restrictions.ilike( ReferralDAOExtn.CITY, "%" + city.trim() + "%" ) );
		}
		
		if ( country != null && country.trim().length() > 0 ) {
			criteria.add( Restrictions.ilike( ReferralDAOExtn.COUNTRY_CODE, "%" + country.trim() + "%" ) );
		}
		
		if ( state != null && state.trim().length() > 0 ) {
			criteria.add( Restrictions.ilike( ReferralDAOExtn.STATE_CODE, "%" + state.trim() + "%" ) );
		}
		
		if ( pinCode != null && pinCode.trim().length() > 0 ) {
			criteria.add( Restrictions.ilike( ReferralDAOExtn.PIN_CODE, "%" + pinCode.trim() + "%" ) );
		}
		
		if ( preferredContactTime != null && preferredContactTime.trim().length() > 0 ) {
			criteria.add( Restrictions.ilike( ReferralDAOExtn.PREFERRED_CONTACT_TIME, "%" + preferredContactTime.trim() + "%" ) );
		}
		
		if ( referralAddress != null && referralAddress.trim().length() > 0 ) {
			criteria.add( Restrictions.ilike( ReferralDAOExtn.ADDRESS, "%" + referralAddress.trim() + "%" ) );
		}
		
		if ( referralName != null && referralName.trim().length() > 0 ) {
			criteria.add( Restrictions.ilike( ReferralDAOExtn.REFERRAL_NAME, "%" + referralName.trim() + "%" ) );
		}
		
		if ( referralCode != null ) {
			criteria.add( Restrictions.eq( ReferralDAOExtn.REFERRAL_CODE, referralCode ) );
		}
		
		if(referralType != null && referralType.trim().length() > 0){
			criteria.add(Restrictions.eq(ReferralDAOExtn.REFERRAL_TYPE_CODE, referralType));
		}
		
		criteria.addOrder(Order.asc(ReferralDAOExtn.REFERRAL_NAME));
		referralList = getHibernateTemplate().findByCriteria(criteria);
		
		return referralList;
	}
	
	
}
