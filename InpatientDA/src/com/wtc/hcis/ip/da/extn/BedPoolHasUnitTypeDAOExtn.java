/**
 * 
 */
package com.wtc.hcis.ip.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.BedPoolHasUnitType;
import com.wtc.hcis.ip.da.BedPoolHasUnitTypeDAO;

/**
 * @author Alok Ranjan
 *
 */
public class BedPoolHasUnitTypeDAOExtn extends BedPoolHasUnitTypeDAO {
	public boolean deleteUnitTypesAssociatedWithPool( String poolName ) {
		this.getHibernateTemplate().deleteAll( this.findByProperty("id.poolName", poolName ) );
		
		return true;
	}
	
	public List<BedPoolHasUnitType> findBedPoolsByNursintUnitType(String unitTypeCd) {
		DetachedCriteria criteria = DetachedCriteria.forClass( BedPoolHasUnitType.class );
		
		criteria.add(Restrictions.eq("id.unitTypeCd", unitTypeCd));
		
		List<BedPoolHasUnitType> bedPoolHasUnitTypeList= this.getHibernateTemplate().findByCriteria( criteria );
		return bedPoolHasUnitTypeList;
	}

}
