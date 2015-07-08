/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import com.wtc.hcis.da.AssignedServices;
import com.wtc.hcis.da.AssignedServicesDAO;

/**
 * @author Alok Ranjan
 *
 */
public class AssignedServicesDAOExtn extends AssignedServicesDAO {
	private static final Log log = LogFactory.getLog(AssignedServicesDAOExtn.class);
	public static final String PACKAGE_ASGM_ID = "assignedPackage.packageAsgmId";
	public static final String SERVICE_UID = "serviceUid";
	public static final String SERVICE_CODE = "service.serviceCode";
	public static final String SERVICE_NAME = "service.serviceName";
	public static final String SERVICE_PACKAGE_CODE = "servicePackage.packageId";
	public static final String SERVICE_PACKAGE_NAME = "servicePackage.name";
	public static final String SERVICE_DATE = "serviceDate";
	public static final String DEPARTMENT_CODE = "department.departmentCode";
	public static final String PATIENT_ID = "patient.patientId";
	public static final String SERVICE_GROUP_CODE = "service.serviceGroup.serviceGroupCode";
	public static final String ASSIGNED_SERVICE_STATUS_CODE = "assignedServiceStatus.serviceStatusCode";
	public static final String CREATED_DTM = "createDtm";
	
	public static final String SERVICE_ASGM_FROM_IPD = "IPD";
	public static final String SERVICE_ORDER_NUMBER = "labRequisitionOrder.orderNbr";
	
	//Fields to sort based on UI request 
	public static final String SORT_DIRECTION_ASC = "ASC";
	public static final String SORT_DIRECTION_DESC = "DESC";
	
	public static final String SERVICE_TYPE_CODE = "service.serviceTypeCd";
	
	public static final String IS_BILLABLE = "isBillable";
	
	private static final String ASSIGNED_SERVICE_STATUS_CANCELED = "CANCELED";
	
	private Map<String,String> UIDBFieldMapForOrder = new HashMap<String, String>();
	
	
	public List<AssignedServices>getProcessedServicesOfPackage( Integer packageAsgmId ) {
		try
		{
			List<AssignedServices> assignedServicesList = null;
			DetachedCriteria assignedServicesCriteria = DetachedCriteria.forClass(AssignedServices.class);
			assignedServicesCriteria.add( Restrictions.eq( AssignedServicesDAOExtn.PACKAGE_ASGM_ID, packageAsgmId ) );
			assignedServicesCriteria.add( Restrictions.ne( "assignedServiceStatus.serviceStatusCode" , "REQUESTED" ) );
			assignedServicesList = getHibernateTemplate().findByCriteria( assignedServicesCriteria );
			return assignedServicesList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve assigned services "  + ex.getMessage() );
			throw ex;
		}
	}
	
	public List<AssignedServices> getUnbilledAssignedServices( String referenceType, String referenceNumber,boolean filterServiceOfPackage, String isBillable ) {
		try
		{
			List<AssignedServices> assignedServicesList = null;
			DetachedCriteria assignedServicesCriteria = DetachedCriteria.forClass(AssignedServices.class);
			
			assignedServicesCriteria.add( Restrictions.eq( AssignedServicesDAOExtn.REFERENCE_NUMBER, referenceNumber ) ) 
						            .add( Restrictions.eq( AssignedServicesDAOExtn.REFERENCE_TYPE, referenceType ) )
						            .add( Restrictions.or( 
						            		Restrictions.and(
						                		Restrictions.eq(AssignedServicesDAOExtn.REFERENCE_TYPE, AssignedServicesDAOExtn.SERVICE_ASGM_FROM_IPD ),
						                		Restrictions.ltProperty(AssignedServicesDAOExtn.BILLED_UNITS , AssignedServicesDAOExtn.RENDERED_UNITS ) ),
						            		Restrictions.and( 
						            			Restrictions.ne(AssignedServicesDAOExtn.REFERENCE_TYPE, AssignedServicesDAOExtn.SERVICE_ASGM_FROM_IPD ),
						            			Restrictions.ltProperty(AssignedServicesDAOExtn.BILLED_UNITS, AssignedServicesDAOExtn.REQUESTED_UNITS ) ) ) );

			assignedServicesCriteria.add( Restrictions.ne(ASSIGNED_SERVICE_STATUS_CODE, ASSIGNED_SERVICE_STATUS_CANCELED));
			
			if( filterServiceOfPackage ){
				assignedServicesCriteria.add(Restrictions.isNull(AssignedServicesDAOExtn.PACKAGE_ASGM_ID));
			}
			
			if( isBillable != null && !isBillable.isEmpty()){
				
				assignedServicesCriteria.add(Restrictions.eq(AssignedServicesDAOExtn.IS_BILLABLE, isBillable));
			}
			
			assignedServicesList = getHibernateTemplate().findByCriteria( assignedServicesCriteria );
			
			return assignedServicesList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve assigned services "  + ex.getMessage() );
			throw ex;
		}
	}
	
	public Integer getNextAvailableOrderNbr() {
		try
		{
			String hqlQuery = "select max( serviceOrderNumber ) from AssignedServices ";
			
			Query serviceOrderNumberQuery = getSession().createQuery( hqlQuery );
			
			List<Object> resultList = serviceOrderNumberQuery.list();
			
			Integer nextAvailableNumber = 1;
			
			if ( resultList != null && !resultList.isEmpty() && resultList.get(0) != null) {
				Integer maxOrderNbr = (Integer)resultList.get(0);
				nextAvailableNumber = maxOrderNbr + 1;
			}
			
			return nextAvailableNumber;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve next available service order number "  + ex.getMessage() );
			throw ex;
		}
	}
	
	public List<AssignedServices> getAssignedService( String referenceType, String referenceNumber ) {
		try
		{
			List<AssignedServices> assignedServicesList = null;
			DetachedCriteria assignedServicesCriteria = DetachedCriteria.forClass(AssignedServices.class);
			
			if ( referenceType != null && referenceType.trim().length() > 0 ) {
				assignedServicesCriteria.add( Restrictions.eq( AssignedServicesDAOExtn.REFERENCE_TYPE, referenceType ) );
			}
			
			if ( referenceNumber != null && referenceNumber.trim().length() > 0 ) {
				assignedServicesCriteria.add(Restrictions.eq( AssignedServicesDAOExtn.REFERENCE_NUMBER, referenceNumber ) );
			}
           
            assignedServicesCriteria.addOrder( Order.desc( AssignedServicesDAOExtn.SERVICE_ORDER_NUMBER ) )
                                    .addOrder(Order.desc( AssignedServicesDAOExtn.SERVICE_UID ));

			assignedServicesList = getHibernateTemplate().findByCriteria( assignedServicesCriteria );
			return assignedServicesList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve next available service order number "  + ex.getMessage() );
			throw ex;
		}
	}
	
	public List<AssignedServices> getServicesOfOrder( Integer serviceOrderNumber, Boolean packageIncluded, String serviceTypeCode ) {
		try
		{
			List<AssignedServices> assignedServicesList = null;
			DetachedCriteria assignedServicesCriteria = DetachedCriteria.forClass(AssignedServices.class);
			assignedServicesCriteria.add( Restrictions.eq( AssignedServicesDAOExtn.SERVICE_ORDER_NUMBER, serviceOrderNumber ) );
	
			if ( packageIncluded.equals( Boolean.FALSE ) ) {
				assignedServicesCriteria.add( Restrictions.isNull( AssignedServicesDAOExtn.PACKAGE_ASGM_ID ) );
			}
			
			if( serviceTypeCode != null && !serviceTypeCode.isEmpty() ){
				
				assignedServicesCriteria.createAlias("service", "service");
				assignedServicesCriteria.add(Restrictions.eq(AssignedServicesDAOExtn.SERVICE_TYPE_CODE, serviceTypeCode));
			}
			
			assignedServicesList = getHibernateTemplate().findByCriteria( assignedServicesCriteria );
			return assignedServicesList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve assigned services "  + ex.getMessage() );
			throw ex;
		}
	}
	
	public List<AssignedServices>getProcessedServicesOfOrder( Integer serviceOrderNumber ) {
		try
		{
			List<AssignedServices> assignedServicesList = null;
			DetachedCriteria assignedServicesCriteria = DetachedCriteria.forClass(AssignedServices.class);
			assignedServicesCriteria.add( Restrictions.eq( AssignedServicesDAOExtn.SERVICE_ORDER_NUMBER, serviceOrderNumber ) );
			assignedServicesCriteria.add( Restrictions.ne( "assignedServiceStatus.serviceStatusCode" , "REQUESTED" ) );
			assignedServicesList = getHibernateTemplate().findByCriteria( assignedServicesCriteria );
			return assignedServicesList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve assigned services "  + ex.getMessage() );
			throw ex;
		}
	}
	
	public List<AssignedServices>getServicesOfPackage( Integer packageAsgmId ) {
		try
		{
			List<AssignedServices> assignedServicesList = null;
			DetachedCriteria assignedServicesCriteria = DetachedCriteria.forClass(AssignedServices.class);
			assignedServicesCriteria.add( Restrictions.eq( AssignedServicesDAOExtn.PACKAGE_ASGM_ID, packageAsgmId ) );
			assignedServicesList = getHibernateTemplate().findByCriteria( assignedServicesCriteria );
			return assignedServicesList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve assigned services "  + ex.getMessage() );
			throw ex;
		}
	}
	
	public List<AssignedServices> getAssignedServicesForBill( Integer billNumber){
		try
		{
			List<AssignedServices> assignedServicesList = null;
			DetachedCriteria assignedServicesCriteria = DetachedCriteria.forClass(AssignedServices.class);
			assignedServicesCriteria.add( Restrictions.eq( AssignedServicesDAOExtn.LAST_BILL_NBR, billNumber ) );
			assignedServicesList = getHibernateTemplate().findByCriteria( assignedServicesCriteria );
			return assignedServicesList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve assigned services "  + ex.getMessage() );
			throw ex;
		}
		
	}
	
	public List<AssignedServices> findAssignedServices( String serviceCode,String serviceName,String packageCode,String packageName,
													    Date serviceFromDt,Date serviceToDt, String departmetnCode,
													    String serviceGroupCode, String referenceType,String referenceNumber,
													    Integer patientId, String serviceType,String orderByClause, String sortDir ){
	
		
		try {
			Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
			Criteria criteria = session.createCriteria(AssignedServices.class);
			
			criteria.createAlias("service","service");
			criteria.createAlias("service.serviceGroup", "serviceGroup");
			criteria.createAlias("service.department", "department");

			criteria.createAlias("assignedPackage","assignedPackage", criteria.LEFT_JOIN).createAlias("assignedPackage.servicePackage", "servicePackage", criteria.LEFT_JOIN);

			if( serviceCode != null && serviceCode.length() > 0 ){
				criteria.add(Restrictions.eq( AssignedServicesDAOExtn.SERVICE_CODE, serviceCode));
			}
			
			if( serviceName != null && serviceName.length() > 0 ){
				
				criteria.add(Restrictions.ilike(AssignedServicesDAOExtn.SERVICE_NAME, "%" + serviceName + "%" ));
			}
			
			if( packageCode != null && packageCode.length() > 0 ){
				
				criteria.add(Restrictions.eq( AssignedServicesDAOExtn.SERVICE_PACKAGE_CODE, packageCode));
			}
			
			if( packageName != null && packageName.length() > 0 ){
				
				criteria.add(Restrictions.ilike(AssignedServicesDAOExtn.SERVICE_PACKAGE_NAME, "%" + packageName + "%"));
			}
			
			if( serviceFromDt != null  ){
				criteria.add(Restrictions.ge( AssignedServicesDAOExtn.SERVICE_DATE, serviceFromDt));
			}
			if( serviceToDt != null ){
				criteria.add(Restrictions.le( AssignedServicesDAOExtn.SERVICE_DATE, serviceToDt));
			}
			
			if( departmetnCode != null && departmetnCode.length() > 0 ){
				
				criteria.add(Restrictions.eq( AssignedServicesDAOExtn.DEPARTMENT_CODE, departmetnCode));
			}
			
			if( serviceGroupCode != null && serviceGroupCode.length() > 0 ){
				
				criteria.add(Restrictions.eq(AssignedServicesDAOExtn.SERVICE_GROUP_CODE, serviceGroupCode));
			}
			
			if( referenceType != null && referenceType.length() > 0 ){
				criteria.add(Restrictions.eq(AssignedServicesDAOExtn.REFERENCE_TYPE, referenceType));
			}
			
			if( referenceNumber != null && referenceNumber.length() > 0 ){
				criteria.add(Restrictions.eq(AssignedServicesDAOExtn.REFERENCE_NUMBER, referenceNumber));
			}
			
			if( patientId != null ){
				criteria.add(Restrictions.eq(AssignedServicesDAOExtn.PATIENT_ID, patientId));
			}
			
			if (orderByClause != null && orderByClause.length() > 0) {
				if (sortDir.equals(AssignedServicesDAOExtn.SORT_DIRECTION_ASC))
					criteria.addOrder(Order.asc(UIDBFieldMapForOrder.get(orderByClause)));
				else
					criteria.addOrder(Order.desc(UIDBFieldMapForOrder.get(orderByClause)));
			}
			
			if( serviceType != null && !serviceType.isEmpty()){
//				criteria.createAlias("service", "service");
				criteria.add(Restrictions.eq(AssignedServicesDAOExtn.SERVICE_TYPE_CODE, serviceType));
			}
			
			criteria.add(Restrictions.ne(AssignedServicesDAOExtn.SERVICE_TYPE_CODE, "ADMINISTRATIVE"));
			
			 List<AssignedServices> assignedServicesList = criteria.list();
			 
			 return assignedServicesList;
		} catch (RuntimeException ex ){
			
			log.error("Failed to retrieve assigned services "  + ex.getMessage() );
			throw ex;
		}
		
	}
	
	/**
	 * This method returns the count of assigned services with the given package assignmentId, the
	 * second parameter serviceStatusCode is optional
	 * 
	 */
	public Integer getCountOfServiceAssignedFromPkg( Integer packageAsgmId , String serviceStatusCode ){
		
		try{
		
			Integer rowcount = 0;
			
			DetachedCriteria criteria = DetachedCriteria.forClass( AssignedServices.class );
			
			criteria.add( Restrictions.eq(AssignedServicesDAOExtn.PACKAGE_ASGM_ID, packageAsgmId));
			
			if( serviceStatusCode != null && serviceStatusCode.length() > 0 ){
				criteria.add( Restrictions.eq(AssignedServicesDAOExtn.ASSIGNED_SERVICE_STATUS_CODE, serviceStatusCode));
			}
			
			criteria.setProjection( Projections.rowCount() );
			
			List result = getHibernateTemplate().findByCriteria(criteria);
			
			if( result != null && !result.isEmpty()){
				
				rowcount = (Integer) result.get(0);
			}
			
			return rowcount;
			
		}catch (RuntimeException ex ){
			
			log.error("Failed to retrieve assigned services "  + ex.getMessage() );
			throw ex;
		}
		
	}

	public List<AssignedServices> getAssignedServicesForPatient(Integer patientId ){
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass( AssignedServices.class );
			
			criteria.add( Restrictions.eq( AssignedServicesDAOExtn.PATIENT_ID , patientId))
			              .addOrder(Order.desc( AssignedServicesDAOExtn.CREATED_DTM ))
			              .addOrder( Order.asc( AssignedServicesDAOExtn.REFERENCE_TYPE))
			              .addOrder( Order.desc( AssignedServicesDAOExtn.REFERENCE_NUMBER));
			
			List<AssignedServices> assignedServicesList = getHibernateTemplate().findByCriteria(criteria);
			
			return assignedServicesList;
		} catch (DataAccessException e) {
			log.error("Failed to retrieve assigned services "  + e.getMessage() );
			throw e;
		}
	}
	
	public void setUIDBFieldMapForOrder(Map<String, String> fieldMapForOrder) {
		UIDBFieldMapForOrder = fieldMapForOrder;
	}
	
	
}
