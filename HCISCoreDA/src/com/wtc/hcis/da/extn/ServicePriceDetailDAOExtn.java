/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import com.wtc.hcis.da.ServicePriceDetail;
import com.wtc.hcis.da.ServicePriceDetailDAO;

/**
 * @author Bhavesh
 *
 */
public class ServicePriceDetailDAOExtn extends ServicePriceDetailDAO {

	private static final Log log = LogFactory.getLog( ServicePriceDetailDAOExtn.class );
	
	
	public static final String SERVICE_CODE = "id.serviceCd";
	public static final String EFFECTIVE_FROM_DT = "id.effectiveFrom";
	public static final String EFFECTIVE_TO_DT = "effectiveTo";
	public static final String PROCESSED = "processed";
	public static final String PROCESSED_NO = "N";
	
	
	public List<ServicePriceDetail> getConflictedPriceList(String serviceCode, Date effectiveFromDt, Date effectiveToDt ) throws Exception{
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass( ServicePriceDetail.class );
			
			criteria.add( Restrictions.eq(SERVICE_CODE, serviceCode))
					.add( Restrictions.eq(EFFECTIVE_FROM_DT, effectiveFromDt));
			
			return getHibernateTemplate().findByCriteria(criteria);
		} catch ( Exception e) {
			
			log.error(e.getMessage());
			
			throw e;
		}
	}

	
	public List<ServicePriceDetail> getLatestPriceDetail(String serviceCode) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass( ServicePriceDetail.class );
		
		try {
			criteria.add( Restrictions.eq(SERVICE_CODE, serviceCode))
					.add( Restrictions.isNull(EFFECTIVE_TO_DT));//TODO: Implement this
			
			return getHibernateTemplate().findByCriteria(criteria);
		} catch (RuntimeException e) {

			log.error(e.getMessage());
			throw e;
		}
	}
	
	
	public List<ServicePriceDetail> getEarlierServicePriceDetail( String serviceCode , Date currentEffectiveDt ){
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass( ServicePriceDetail.class );
			
			criteria.add( Restrictions.eq(SERVICE_CODE, serviceCode))
					.add( Restrictions.lt(EFFECTIVE_FROM_DT, currentEffectiveDt))
					.addOrder( Order.desc(EFFECTIVE_FROM_DT));
			
			return getHibernateTemplate().findByCriteria(criteria);
		} catch (RuntimeException e) {
			
			log.error(e.getMessage());
			throw e;
		}
	}
	
	/*
	 * Get all the record which are not processed and whose effective from date is 
	 * less then processForDate.
	 * 
	 */
	public List<ServicePriceDetail> getServicePriceForProcessing( String serviceCode, Date processForDate){
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass( ServicePriceDetail.class );
			
			if( serviceCode != null && !serviceCode.isEmpty() ){
				criteria.add( Restrictions.eq(SERVICE_CODE, serviceCode));
			}
			criteria.add( Restrictions.eq(ServicePriceDetailDAOExtn.PROCESSED, PROCESSED_NO))
					.add( Restrictions.le(EFFECTIVE_FROM_DT,processForDate))
					.addOrder(Order.asc(EFFECTIVE_FROM_DT));
			
			return getHibernateTemplate().findByCriteria(criteria);
		} catch (RuntimeException e) {
			
			log.error(e.getMessage());
			throw e;
		}
	}
}
