/**
 * 
 */
package com.wtc.hcis.ip.da.extn;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import sun.awt.geom.AreaOp.AddOp;

import com.wtc.hcis.ip.da.BedUsageHistory;
import com.wtc.hcis.ip.da.BedUsageHistoryDAO;
import com.wtc.hcis.ip.da.extn.util.DAUtil;

/**
 * @author Bhavesh
 *
 */
public class BedUsageHistoryDAOExtn extends BedUsageHistoryDAO {
	
	private static final Log log = LogFactory.getLog( BedUsageHistoryDAOExtn.class );
	
	public static String BED_NUMBER = "bedMaster.bedNumber";
	public static String ADMISSION_REQ_NUMBER = "admission.admissionReqNbr";
	public static String CHECK_OUT_DTM = "checkOutDtm";
	public static String CHECK_IN_DTM = "checkInDtm"; 
	public static String LAST_BILL_DTM = "lastBillDtm";
	public static String CREATE_DTM = "createDtm";
	
	//Method returns those bed assignment (histories) which are still in occupied(hence check for "checkOutDtm" is null).
	//It is also sure that for the given bed number against given admission request number it can be only one unclosed History.
	
	public BedUsageHistory getUnclosedHistory(String bedNumber,Integer admissionReqNumber) throws Exception {
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass( BedUsageHistory.class );
			criteria.add(Restrictions.eq(BedUsageHistoryDAOExtn.BED_NUMBER, bedNumber))
					.add(Restrictions.eq(BedUsageHistoryDAOExtn.ADMISSION_REQ_NUMBER, admissionReqNumber))
					.add(Restrictions.isNull(BedUsageHistoryDAOExtn.CHECK_OUT_DTM));
			
			List<BedUsageHistory> bedUsageHistoryList = getHibernateTemplate().findByCriteria(criteria);
			BedUsageHistory bedUsageHistory = null ;
			if( DAUtil.isValid(bedUsageHistoryList)){
				bedUsageHistory = bedUsageHistoryList.get(0);
			}
			
			return bedUsageHistory;
		} catch ( Exception e) {
			log.error("Error to get bed usage history.");
			throw e;
		}
	}

	public List<BedUsageHistory> getBillableBedUsageHistory(Integer admissionReqNbr ){
		
		try {
			Calendar today = Calendar.getInstance();
			
			DetachedCriteria criteria = DetachedCriteria.forClass( BedUsageHistory.class );
			criteria.add( Restrictions.eq(BedUsageHistoryDAOExtn.ADMISSION_REQ_NUMBER, admissionReqNbr))
			 		.add(Restrictions.le(BedUsageHistoryDAOExtn.CHECK_IN_DTM, today.getTime()))
			 		.add(Restrictions.or(Restrictions.isNull(BedUsageHistoryDAOExtn.LAST_BILL_NBR),Restrictions.or(
			 							 	Restrictions.ltProperty(BedUsageHistoryDAOExtn.LAST_BILL_DTM,BedUsageHistoryDAOExtn.CHECK_OUT_DTM),
			 							 	Restrictions.isNull(BedUsageHistoryDAOExtn.CHECK_OUT_DTM))))
			 		.addOrder(Order.asc(BedUsageHistoryDAOExtn.CREATE_DTM));
			 							                
			return getHibernateTemplate().findByCriteria(criteria);
		} catch (DataAccessException e) {
			log.error("Error to get bed usage history.");
			throw e;
		}
	}
	
	public BedUsageHistory getLatestBedForAdmission( Integer AdmissionReqNbr ) throws Exception{
		try {
			Calendar today = Calendar.getInstance();
			
			DetachedCriteria criteria = DetachedCriteria.forClass( BedUsageHistory.class );
			
			criteria.add( Restrictions.eq( BedUsageHistoryDAOExtn.ADMISSION_REQ_NUMBER, AdmissionReqNbr))
					.add(  Restrictions.le( BedUsageHistoryDAOExtn.CHECK_IN_DTM, today.getTime()))
					.add( Restrictions.isNull( BedUsageHistoryDAOExtn.CHECK_OUT_DTM))
					.addOrder( Order.desc( BedUsageHistoryDAOExtn.CHECK_IN_DTM));
			
			List<BedUsageHistory> bedUsageHistoryList = getHibernateTemplate().findByCriteria(criteria);
			
			BedUsageHistory bedUsageHistory = null;
			
			if( null != bedUsageHistoryList && bedUsageHistoryList.size() > 0 ){
				bedUsageHistory = bedUsageHistoryList.get( 0 );
			}
			return bedUsageHistory;
			
		} catch (Exception e) {
			throw e;
		}
	}
}
