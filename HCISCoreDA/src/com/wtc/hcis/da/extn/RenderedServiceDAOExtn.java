/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.RenderedService;
import com.wtc.hcis.da.RenderedServiceDAO;

/**
 * @author Alok Ranjan
 *
 */
public class RenderedServiceDAOExtn extends RenderedServiceDAO {
	private static final Log log = LogFactory.getLog( RenderedServiceDAOExtn.class );
	
	public  static final String PACKAGE_ASGM_ID = "assignedPackage.packageAsgmId";
	public static final String REDERED_DTM = "id.renderedDtm";
	public static final String REFERENCE_TYPE = "assignedServices.referenceType";
	public static final String REFERRAL_CODE_PATIENT = "patientReferral.referralCode";
	public static final String REFERRAL_CODE_APPOINTMENT = "apptReferral.referralCode";
	public static final String REFERENCE_TYPE_DIR = "DIR";
	
	public List<RenderedService> getUnbilledRenderedServicesOfPackage( Integer packageAsgmId ) {
		try {
			List<RenderedService> renderedServiceList = null;
			DetachedCriteria renderedServiceCriteria = DetachedCriteria.forClass( RenderedService.class );
			
			renderedServiceCriteria.add( Restrictions.eq( RenderedServiceDAOExtn.PACKAGE_ASGM_ID, packageAsgmId ) )
			                       .add( Restrictions.isNull( RenderedServiceDAOExtn.BILL_NBR ) );
			
			renderedServiceList = getHibernateTemplate().findByCriteria( renderedServiceCriteria );
			
			return renderedServiceList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve rendered service "  + ex.getMessage() );
			throw ex;
		}
	}
	
	/**
	 * This method returns two categories of rendered services-
	 * 1. Services assigned to the patient who has appointment through referral 
	 * 2. Services assigned to the Direct patient who came to referral
	 * @param referralCode
	 * @param lastProcessedDate
	 * @return
	 */
	public List<RenderedService> getRenderedServicesOfReferral( Integer referralCode,Date lastProcessedDate){
	try {
			List<RenderedService> renderedServiceList = null;
			
			Criteria criteria = getSession().createCriteria( RenderedService.class );
			
			criteria.createAlias("assignedServices", "assignedServices")
					.createAlias("assignedServices.patient", "patient")
					.createAlias("patient.referral", "patientReferral", Criteria.LEFT_JOIN)
					.createAlias("patient.appointmentses", "appointments",Criteria.LEFT_JOIN)
					.createAlias("appointments.referral", "apptReferral", Criteria.LEFT_JOIN);
			
			criteria.add(Restrictions.gt(RenderedServiceDAOExtn.REDERED_DTM, lastProcessedDate))
					.add(Restrictions.or(Restrictions.and(Restrictions.eq(RenderedServiceDAOExtn.REFERENCE_TYPE, RenderedServiceDAOExtn.REFERENCE_TYPE_DIR),
														 (Restrictions.eq(RenderedServiceDAOExtn.REFERRAL_CODE_PATIENT, referralCode))),	
										 Restrictions.eq(RenderedServiceDAOExtn.REFERRAL_CODE_APPOINTMENT, referralCode)));

			
			criteria.addOrder( Order.asc( RenderedServiceDAOExtn.REDERED_DTM ) );
			
			renderedServiceList = criteria.list();
	
			
			return renderedServiceList;
		}catch(RuntimeException ex){
			log.error("Failed to retrieve rendered service "  + ex.getMessage() );
			throw ex;
		}
	}
}
