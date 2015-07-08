/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;

import com.wtc.hcis.da.ReferralCommission;
import com.wtc.hcis.da.ReferralCommissionDAO;
import com.wtc.hcis.da.ReferralCommissionProcessDAO;

/**
 * @author Alok Ranjan
 *
 */
public class ReferralCommissionDAOExtn extends ReferralCommissionDAO {
	
	private static final Log log = LogFactory.getLog( ReferralCommissionDAOExtn.class );
	public static String REFERRAL_CODE = "referral.referralCode";
	public static String REFERRAL_CREATE_DTM = "createdDtm";
	public static String COMMISSION_TYPE_CODE = "referralCommissionProcess.commissionTypeCode";
	
	/**
	 * All parameter are mandatory. Returns the ReferralCommission which is less than the
	 *  'afterDate' parameter but maximum in other lesser values (similar to 'ceiling' to the date)
	 * @param referralCode
	 * @param commissionType
	 * @param afterDate
	 * @return
	 */
	public ReferralCommission getApplicableReferralCommission( Integer referralCode, String commissionType ,Date afterDate ) {
		try {
			List<ReferralCommission>referralCommissionList = null;
			ReferralCommission referralCommission = null;
			DetachedCriteria  criteria = DetachedCriteria.forClass( ReferralCommission.class );
			
			criteria.add( Restrictions.eq( ReferralCommissionDAOExtn.REFERRAL_CODE, referralCode ) )
					.add( Restrictions.eq( ReferralCommissionDAOExtn.COMMISSION_TYPE_CODE, commissionType ) )
					.add( Restrictions.ge(ReferralCommissionDAOExtn.PCT_ABS_VALUE, 0.0 ))
					.add( Restrictions.le(ReferralCommissionDAOExtn.REFERRAL_CREATE_DTM, afterDate ))
					.addOrder(Order.desc(ReferralCommissionDAOExtn.REFERRAL_CREATE_DTM));

			referralCommissionList = getHibernateTemplate().findByCriteria(criteria);
			
			if( referralCommissionList != null && !referralCommissionList.isEmpty()
										&& referralCommissionList.get(0) != null ){
				
				referralCommission = referralCommissionList.get(0);
			}
			
			return referralCommission;
		} catch ( RuntimeException e) {
			log.error(" Failed to read ReferralCommission "  + e.getMessage() );
			throw e;
		}
	}
	
	public List<ReferralCommission> getReferralCommissionOfReferral( Integer referralCode ){
		
		try {
			List<ReferralCommission>referralCommissionList = null;
			DetachedCriteria  criteria = DetachedCriteria.forClass( ReferralCommission.class );
			
			criteria.add( Restrictions.eq(ReferralCommissionDAOExtn.REFERRAL_CODE, referralCode))
					.addOrder( Order.desc(ReferralCommissionDAOExtn.REFERRAL_CREATE_DTM));
			
			referralCommissionList = getHibernateTemplate().findByCriteria(criteria);
			
			return referralCommissionList;
		} catch ( RuntimeException e) {
			log.error(" Failed to  ReferralCommission "  + e.getMessage() );
			throw e;
		}
		
	}
		
}
