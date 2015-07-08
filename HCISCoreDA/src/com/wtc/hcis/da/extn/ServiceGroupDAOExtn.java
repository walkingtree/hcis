package com.wtc.hcis.da.extn;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.ServiceGroup;
import com.wtc.hcis.da.ServiceGroupDAO;

public class ServiceGroupDAOExtn extends ServiceGroupDAO {

	private static final Log log = LogFactory.getLog(ServiceGroupDAOExtn.class);
	
	public List<ServiceGroup> findAllServiceGroups() {
		try {
			List<ServiceGroup> serviceGroupsList = null;
			DetachedCriteria serviceGrpCriteria = DetachedCriteria.forClass(ServiceGroup.class);
			
			serviceGroupsList = getHibernateTemplate().findByCriteria(serviceGrpCriteria);
			
			return serviceGroupsList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve service groups "  + ex.getMessage() );
			throw ex;
		}
	}
	
	public List<ServiceGroup> findServiceGroups(  String serviceGroupCode, String serviceGroupName,
												  String parentGroupCode, String serviceCode ) {
		try {
			List<ServiceGroup> serviceGroupsList = null;
			DetachedCriteria serviceGrpCriteria = DetachedCriteria.forClass(ServiceGroup.class);
			
			if ( serviceGroupCode != null && !serviceGroupCode.equals("")) {
				serviceGrpCriteria.add(Restrictions.ilike("serviceGroupCode" ,"%" + serviceGroupCode + "%"));
			}
			if ( serviceGroupName != null && !serviceGroupName.equals("")) {
				serviceGrpCriteria.add(Restrictions.ilike( ServiceGroupDAOExtn.GROUP_NAME, "%" + serviceGroupName + "%"));
			}
			
			if (parentGroupCode != null && !parentGroupCode.equals("")) {
				serviceGrpCriteria.add(Restrictions.ilike("serviceGroup.serviceGroupCode", parentGroupCode));
			}
			
			if ( serviceCode != null && !serviceCode.equals("")) {
				
				serviceGrpCriteria.createAlias("services", "services");
				serviceGrpCriteria.add(Restrictions.eq( "services.serviceCode", serviceCode));
			}
			
			serviceGroupsList = getHibernateTemplate().findByCriteria(serviceGrpCriteria);
			
			return serviceGroupsList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve service groups "  + ex.getMessage() );
			throw ex;
		}
	}
	public List<ServiceGroup> findByParentGroupCode(String parentGroupCode ){
		
		return this.findByProperty("serviceGroup.serviceGroupCode", parentGroupCode );
	}
}
