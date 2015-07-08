/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.AssignedPackage;
import com.wtc.hcis.da.AssignedPackageDAO;
import com.wtc.hcis.da.AssignedServices;
import com.wtc.hcis.da.Brand;

/**
 * @author Alok Ranjan
 *
 */
public class AssignedPackageDAOExtn extends AssignedPackageDAO {
	private static final Log log = LogFactory.getLog(AssignedPackageDAOExtn.class);
	
	public List<AssignedPackage> getAssignedPackages( String packageId ) {
		try
		{
			List<AssignedPackage> assignedPackageList = null;
			DetachedCriteria assignedPackageCriteria = DetachedCriteria.forClass(AssignedPackage.class);
			assignedPackageCriteria.add( Restrictions.eq( "servicePackage.packageId", packageId ) );
			assignedPackageList = getHibernateTemplate().findByCriteria( assignedPackageCriteria );
			return assignedPackageList;
		}catch (RuntimeException ex ) {
			log.error("Failed to retrieve assigned packages "  + ex.getMessage() );
			throw ex;
		}
	}
	
	public List<AssignedPackage> getUnbilledAssignedPackages( String referenceType, String referenceNumber ) {
		try
		{
			List<AssignedPackage> assignedPackageList = null;
			DetachedCriteria assignedPackageCriteria = DetachedCriteria.forClass(AssignedPackage.class);
			assignedPackageCriteria.add( Restrictions.eq( AssignedPackageDAOExtn.REFERENCE_NUMBER , referenceNumber ) )
			                       .add( Restrictions.eq( AssignedPackageDAOExtn.REFERENCE_TYPE , referenceType ) )
			                       .add( Restrictions.isNull( AssignedPackageDAOExtn.BILL_NBR ) );
			
			assignedPackageList = getHibernateTemplate().findByCriteria( assignedPackageCriteria );
			return assignedPackageList;
		}catch (RuntimeException ex ) {
			log.error("Failed to retrieve assigned packages. "  + ex.getMessage() );
			throw ex;
		}
	}

	public List<AssignedPackage> getAssignedPackagesForBill( Integer billNumber ){
		try
		{
			List<AssignedPackage> assignedPackageList = null;
			DetachedCriteria assignedPackageCriteria = DetachedCriteria.forClass(AssignedPackage.class);
			assignedPackageCriteria.add( Restrictions.eq( AssignedPackageDAOExtn.BILL_NBR , billNumber ) );
			
			assignedPackageList = getHibernateTemplate().findByCriteria( assignedPackageCriteria );
			return assignedPackageList;
		}catch (RuntimeException ex ) {
			log.error("Failed to retrieve assigned packages for bill: "+ billNumber +". " + ex.getMessage() );
			throw ex;
		}
	}
	
}
