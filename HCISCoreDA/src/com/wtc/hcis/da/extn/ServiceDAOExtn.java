package com.wtc.hcis.da.extn;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.Service;
import com.wtc.hcis.da.ServiceDAO;

public class ServiceDAOExtn extends ServiceDAO {

	private static final Log log = LogFactory.getLog( ServiceDAOExtn.class );
	
	public static String SERVICE_STATUS_CODE_ACTIVE = "ACTIVE";
	public static String SERVICE_TYPE_ADMINISTRATIVE = "ADMINISTRATIVE";
	
	public static final String SERVICE_STATUS = "serviceStatus.serviceStatusCode";
	public static final String EFFECTIVE_FROM_DATE = "effectiveFromDt";
	public static final String EFFECTIVE_TO_DATE = "effectiveToDt";
	public static final String CREATION_DT = "createDtm";
	public static final String PACKAGE_ID  = "packageHasServices.id.packageId";
	
	public void changeGroupNameOfServicesForAGroup( String currentGroupCode, 
			                                         String newGroupCode, 
			                                         String modifiedBy ) {
		try {
			String hqlQuery = "update Service s set s.serviceGroup.serviceGroupCode = :newGroupCode , s.modifiedBy = :modifiedBy , " 
				            + " s.lastModifiedDtm = :currentTime  where s.serviceGroup.serviceGroupCode = :currentGroupCode ";
	
			
			
			int updatedEntities = getSession().createQuery( hqlQuery )
										      .setString( "newGroupCode", newGroupCode )
										      .setString( "modifiedBy", modifiedBy )
										      .setDate( "currentTime", new Date() )
										      .setString( "currentGroupCode", currentGroupCode )
										      .executeUpdate();
			
		} catch (RuntimeException ex ) {
			log.error(" Failed to update service "  + ex.getMessage() );
			throw ex;
		}
	}
	
	
	public List<Service> findAllActiveServices() {
		try {
			Calendar cal = Calendar.getInstance();
			List<Service> servicesList = null;
			DetachedCriteria serviceCriteria = DetachedCriteria.forClass(Service.class);
			serviceCriteria.add( Restrictions.eq( ServiceDAOExtn.SERVICE_STATUS, ServiceDAOExtn.SERVICE_STATUS_CODE_ACTIVE ) );
			serviceCriteria.add( Restrictions.le( ServiceDAOExtn.EFFECTIVE_FROM_DATE, cal.getTime() ) );
			serviceCriteria.add( Restrictions.or( Restrictions.isNull( ServiceDAOExtn.EFFECTIVE_TO_DATE ), 
					                              Restrictions.ge( ServiceDAOExtn.EFFECTIVE_TO_DATE, cal.getTime() ) ) );
	
			//Administrative services are meant for Implicit system use only, so
			//remove them from list
			
			serviceCriteria.add( Restrictions.ne(SERVICE_TYPE_CD, SERVICE_TYPE_ADMINISTRATIVE));
			
			servicesList = getHibernateTemplate().findByCriteria( serviceCriteria );
			
			return servicesList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve services "  + ex.getMessage() );
			throw ex;
		}
	}
	
	public List<Service> findServicesOfGroup( String serviceGroupCode ) {
		try {
			List<Service> servicesList = null;
			Calendar cal = Calendar.getInstance();
			DetachedCriteria serviceCriteria = DetachedCriteria.forClass(Service.class);
			
			serviceCriteria.add(Restrictions.eq( "serviceGroup.serviceGroupCode", serviceGroupCode));
			serviceCriteria.add( Restrictions.eq( ServiceDAOExtn.SERVICE_STATUS, ServiceDAOExtn.SERVICE_STATUS_CODE_ACTIVE ) );
			serviceCriteria.add( Restrictions.le( ServiceDAOExtn.EFFECTIVE_FROM_DATE, cal.getTime() ) );
			serviceCriteria.add( Restrictions.or( Restrictions.isNull( ServiceDAOExtn.EFFECTIVE_TO_DATE ), 
					                              Restrictions.ge( ServiceDAOExtn.EFFECTIVE_TO_DATE, cal.getTime() ) ) );
			
			servicesList = getHibernateTemplate().findByCriteria( serviceCriteria );
			
			return servicesList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve services "  + ex.getMessage() );
			throw ex;
		}
		
	}
	
	public List<Service> findServices  ( String serviceName,
							             String serviceCode,
									     String serviceGroup,
									     String departmentCode,
									     String serviceDescription,
									     String procedure,
									     String serviceStatusCode,
									     Date createdBefore,
									     Date createdAfter,
									     Double chargesFrom,
									     Double chargesTo  ) {
		try {
			List<Service> servicesList = null;
			DetachedCriteria serviceCriteria = DetachedCriteria.forClass(Service.class);
			
			if (serviceName != null && !serviceName.equals("")) {
				serviceCriteria.add(Restrictions.ilike(ServiceDAOExtn.SERVICE_NAME, "%" + serviceName + "%"));
			}
			
			if ( serviceCode != null && !serviceCode.equals("")) {
				serviceCriteria.add(Restrictions.ilike("serviceCode","%" + serviceCode + "%" ));
			}
			
			if (serviceGroup != null && !serviceGroup.equals("")) {
				serviceCriteria.add( Restrictions.eq("serviceGroup.serviceGroupCode", serviceGroup ));
			} 

			if ( departmentCode != null && !departmentCode.equals("") ) {
				serviceCriteria.add(Restrictions.eq("department.departmentCode",departmentCode ));
			}
			
			if ( serviceDescription != null && !serviceDescription.equals("")) {
				serviceCriteria.add(Restrictions.ilike(ServiceDAOExtn.SERVICE_DESC, "%" + serviceDescription + "%"));
			}
			
			if ( procedure != null && !procedure.equals("")) {
				serviceCriteria.add(Restrictions.ilike(ServiceDAOExtn.SERVICE_PROCEDURE, "%" + procedure + "%"));
			}
			
			if ( serviceStatusCode != null && !serviceStatusCode.equals("") ) {
				serviceCriteria.add(Restrictions.eq("serviceStatus.serviceStatusCode", serviceStatusCode ));
			}
			
			if ( createdBefore != null ) {
				if ( createdAfter != null ) {
					serviceCriteria.add(Restrictions.between( ServiceDAOExtn.CREATION_DT, createdAfter, createdBefore ) );
				} else {
					serviceCriteria.add(Restrictions.le(ServiceDAOExtn.CREATION_DT, createdBefore) ); 
				}
			} else {
				if ( createdAfter != null ) {
					serviceCriteria.add(Restrictions.ge(ServiceDAOExtn.CREATION_DT, createdAfter ) ); 
				}
			}
			
			if ( chargesFrom != null) {
				if ( chargesTo != null ) {
					serviceCriteria.add(Restrictions.between(ServiceDAOExtn.SERVICE_CHARGE, chargesFrom, chargesTo));
				} else {
					serviceCriteria.add(Restrictions.ge(ServiceDAOExtn.SERVICE_CHARGE, chargesFrom));
				}
			} else {
				if ( chargesTo != null ) {
					serviceCriteria.add(Restrictions.le(ServiceDAOExtn.SERVICE_CHARGE, chargesTo ) );
				}
			}
			
			
			
			servicesList = getHibernateTemplate().findByCriteria(serviceCriteria);
			
			return servicesList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve services "  + ex.getMessage() );
			throw ex;
		}
	}
	
	public List<Service> findActiveServiceOfPackage(String packageId){
		try {
			Calendar cal = Calendar.getInstance();
			List<Service> servicesList = null;
			DetachedCriteria serviceCriteria = DetachedCriteria.forClass(Service.class);
			
			serviceCriteria.createAlias("packageHasServices", "packageHasServices");
			
			serviceCriteria.add(Restrictions.eq( ServiceDAOExtn.PACKAGE_ID, packageId))
							.add( Restrictions.eq( ServiceDAOExtn.SERVICE_STATUS, ServiceDAOExtn.SERVICE_STATUS_CODE_ACTIVE ) )
							.add( Restrictions.le( ServiceDAOExtn.EFFECTIVE_FROM_DATE, cal.getTime() ) )
							.add( Restrictions.or( Restrictions.isNull( ServiceDAOExtn.EFFECTIVE_TO_DATE ), 
					                              Restrictions.ge( ServiceDAOExtn.EFFECTIVE_TO_DATE, cal.getTime() ) ) );
			
			servicesList = getHibernateTemplate().findByCriteria( serviceCriteria );
			
			return servicesList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve services "  + ex.getMessage() );
			throw ex;
		}		
	}
	
	public List<Service> findAllServiceOfPackage(String packageId){
		try {
			Calendar cal = Calendar.getInstance();
			List<Service> servicesList = null;
			DetachedCriteria serviceCriteria = DetachedCriteria.forClass(Service.class);
			
			serviceCriteria.createAlias("packageHasServices", "packageHasServices");
			
			serviceCriteria.add(Restrictions.eq( ServiceDAOExtn.PACKAGE_ID, packageId));
			
			servicesList = getHibernateTemplate().findByCriteria( serviceCriteria );
			
			return servicesList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve services "  + ex.getMessage() );
			throw ex;
		}		
	}
	
	public List<Service> getActiveServiceByServiceTypeCode( String ServiceTypeCode ){

		Calendar cal = Calendar.getInstance();
		DetachedCriteria serviceCriteria = DetachedCriteria.forClass(Service.class);
		
		serviceCriteria.add(Restrictions.eq(ServiceDAOExtn.SERVICE_STATUS, ServiceDAOExtn.SERVICE_STATUS_CODE_ACTIVE))
						.add(Restrictions.eq(ServiceDAOExtn.SERVICE_TYPE_CD, ServiceTypeCode));
		
		serviceCriteria.add( Restrictions.le( ServiceDAOExtn.EFFECTIVE_FROM_DATE, cal.getTime() ) );
		serviceCriteria.add( Restrictions.or( Restrictions.isNull( ServiceDAOExtn.EFFECTIVE_TO_DATE ), 
				                              Restrictions.ge( ServiceDAOExtn.EFFECTIVE_TO_DATE, cal.getTime())));
		
		List<Service> serviceList = getHibernateTemplate().findByCriteria(serviceCriteria);
		
		return serviceList;
		
		
	}
}
