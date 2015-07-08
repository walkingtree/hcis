/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.ServiceGroup;
import com.wtc.hcis.da.ServicePackage;
import com.wtc.hcis.da.ServicePackageDAO;
import com.wtc.hcis.da.ServicePackageStatus;

/**
 * @author Alok Ranjan
 *
 */
public class ServicePackageDAOExtn extends ServicePackageDAO {
	private static final Log log = LogFactory.getLog( ServicePackageDAOExtn.class );
	
	public static final String EFFECTIVE_FROM_DT = "effectiveFromDt";
	public static final String EFFECTIVE_TO_DT =  "effectiveToDt";
	public static final String SUSPENDED_FROM_DT = "suspendedFromDt";
	public static final String SUSPENDED_TO_DT =  "suspendedToDt";
	public static final String SERVICE_PACKAGE_STATUS_CODE =  "servicePackageStatus.statusCode";
	public static final String SERVICE_PACKAGE_STATUS_CODE_ACTIVE =  "ACTIVE";
	public static final String SERVICE_PACKAGE_STATUS_CODE_SUSPENDED =  "SUSPENDED";
	public static final String SERVICE_CODE = "packageHasServices.id.serviceCode";
	public static final String CREATION_DATE = "creationDate";
	public static final String SUSPEND_LEVEL_FLAG_SERVICE = "S";
	
	public List<ServicePackage> getAvailablePackages( String serviceCode ) {
		try {
			Calendar cal = Calendar.getInstance();
			List<ServicePackage> servicePkgList = null;
			DetachedCriteria servicePkgCriteria = DetachedCriteria.forClass( ServicePackage.class );
			
			servicePkgCriteria.add( Restrictions.eq( ServicePackageDAOExtn.SERVICE_PACKAGE_STATUS_CODE , ServicePackageDAOExtn.SERVICE_PACKAGE_STATUS_CODE_ACTIVE ))
			                  .add( Restrictions.le( ServicePackageDAOExtn.EFFECTIVE_FROM_DT, cal.getTime() ) );
			
			servicePkgCriteria.add( Restrictions.or( Restrictions.isNull( ServicePackageDAOExtn.EFFECTIVE_TO_DT ), 
					                                 Restrictions.ge( ServicePackageDAOExtn.EFFECTIVE_TO_DT, cal.getTime() ) ) );
			if( serviceCode != null && !serviceCode.equals("")){
				servicePkgCriteria.createAlias("packageHasServices", "packageHasServices");
				servicePkgCriteria.add( Restrictions.eq( ServicePackageDAOExtn.SERVICE_CODE, serviceCode));
			}
			servicePkgList = getHibernateTemplate().findByCriteria( servicePkgCriteria );
			
			return servicePkgList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve service packages "  + ex.getMessage() );
			throw ex;
		}
	}
	
	public List<ServicePackage> getServicePackages(  String packageName,
													 String packageId, 
										             String packageStatus, 
										             String description,
										             String chargeOverrideLevel,
										             String ServiceCode,
										             Date   createdAfter,
										             Date   createdBefore,
										             Date 	effectiveFromDt, 
										             Date 	effectiveToDt )  {
		try {
			Calendar cal = Calendar.getInstance();
			List<ServicePackage> servicePkgList = null;
			DetachedCriteria servicePkgCriteria = DetachedCriteria.forClass( ServicePackage.class );
			
			if ( packageName != null && packageName.length() > 0 ) {
				servicePkgCriteria.add( Restrictions.ilike( ServicePackageDAOExtn.NAME,"%" + packageName + "%" ) );
			}
			
			if ( packageId != null && !packageId.equals("") ) {
				servicePkgCriteria.add( Restrictions.ilike( "packageId", "%" + packageId + "%" ) );
			}
			
			if ( packageStatus != null && packageStatus.length() > 0 ) {
				servicePkgCriteria.add( Restrictions.eq( ServicePackageDAOExtn.SERVICE_PACKAGE_STATUS_CODE , packageStatus ));
			}
			
			if ( description != null && description.length() > 0 ) {
				servicePkgCriteria.add( Restrictions.ilike( ServicePackageDAOExtn.DESCRIPTION,"%" + description + "%" ) );
			}
			
			if ( chargeOverrideLevel != null && !chargeOverrideLevel.equals("") ) {
				servicePkgCriteria.add( Restrictions.eq( "chargeOverrideLevel", chargeOverrideLevel ) );
			}
			
			if ( ServiceCode != null && !ServiceCode.equals("") ) {
				servicePkgCriteria.createAlias("packageHasServices","packageHasServices" );
				servicePkgCriteria.add( Restrictions.eq( ServicePackageDAOExtn.SERVICE_CODE, ServiceCode ) );
			}
			
			if ( createdAfter != null ) {
				servicePkgCriteria.add( Restrictions.ge( ServicePackageDAOExtn.CREATION_DATE, createdAfter ) );
			}
			
			if ( createdBefore != null ) {
				servicePkgCriteria.add( Restrictions.le( ServicePackageDAOExtn.CREATION_DATE, createdBefore ) );
			}
			
			if ( effectiveToDt != null ) {
				servicePkgCriteria.add( Restrictions.le( ServicePackageDAOExtn.EFFECTIVE_TO_DT, effectiveToDt ) );
			}
			
			if ( effectiveFromDt != null ) {
				servicePkgCriteria.add( Restrictions.ge( ServicePackageDAOExtn.EFFECTIVE_FROM_DT, effectiveFromDt ) );
			}
			
			servicePkgList = getHibernateTemplate().findByCriteria( servicePkgCriteria );
			
			return servicePkgList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve service packages "  + ex.getMessage() );
			throw ex;
		}
	}
	
	public List<ServicePackage> getServiceLevelSuspendedPackages(String serviceCode ){
		List<ServicePackage> servicePkgList = null;
		Calendar cal = Calendar.getInstance();
		
		DetachedCriteria servicePkgCriteria = DetachedCriteria.forClass( ServicePackage.class );
		
		servicePkgCriteria.add(Restrictions.eq(ServicePackageDAOExtn.SERVICE_PACKAGE_STATUS_CODE, 
											   ServicePackageDAOExtn.SERVICE_PACKAGE_STATUS_CODE_SUSPENDED))
						   .add(Restrictions.eq(ServicePackageDAOExtn.SUSPEND_LEVEL_FLAG, ServicePackageDAOExtn.SUSPEND_LEVEL_FLAG_SERVICE))
						   .add(Restrictions.or( Restrictions.isNull( ServicePackageDAOExtn.EFFECTIVE_TO_DT ), 
	                                 		   Restrictions.ge( ServicePackageDAOExtn.EFFECTIVE_TO_DT, cal.getTime() )));
		
		if( serviceCode != null && !serviceCode.equals("") ){
			
			servicePkgCriteria.createAlias("packageHasServices", "packageHasServices");
			servicePkgCriteria.add(Restrictions.eq( ServicePackageDAOExtn.SERVICE_CODE, serviceCode));
		}
		
		servicePkgList = getHibernateTemplate().findByCriteria(servicePkgCriteria);
		
		return servicePkgList;
	}
} 
