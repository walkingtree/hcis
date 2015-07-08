/**
 * 
 */
package com.wtc.hcis.billing.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.billing.da.FnclChargeType;
import com.wtc.hcis.billing.da.FnclChargeTypeDAO;

/**
 * @author Alok Ranjan
 *
 */
public class FnclChargeTypeDAOExtn extends FnclChargeTypeDAO {
	
	public FnclChargeType getFnclChargeType( String processName, String chargeTypeName ) {

		DetachedCriteria fnclChargeTypeCriteria = DetachedCriteria.forClass( FnclChargeType.class );
		
		fnclChargeTypeCriteria.add( Restrictions.eq( FnclChargeTypeDAOExtn.PROCESS_NAME, processName ) );
		fnclChargeTypeCriteria.add( Restrictions.eq( FnclChargeTypeDAOExtn.CHARGE_TYPE_NAME, chargeTypeName ) );
		
		List<FnclChargeType> fnclChargeTypeList = getHibernateTemplate().findByCriteria( fnclChargeTypeCriteria );	
		
		if ( fnclChargeTypeList != null && !fnclChargeTypeList.isEmpty() ) {
			return fnclChargeTypeList.get(0);
		} else {
			return null;
		}
	}
}
