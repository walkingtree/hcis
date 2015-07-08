/**
 * 
 */
package com.wtc.hcis.billing.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.billing.da.BillRegister;
import com.wtc.hcis.billing.da.BillRegisterDAO;

/**
 * @author Alok Ranjan
 *
 */
public class BillRegisterDAOExtn extends BillRegisterDAO {
	
	public static final String BILL_PROCESS_NAME = "processName";
	
	public List<BillRegister> getActiveProcesses() {

		DetachedCriteria billRegisterCriteria = DetachedCriteria.forClass( BillRegister.class );
		
		billRegisterCriteria.add( Restrictions.eq( BillRegisterDAOExtn.ACTIVE_FLAG , "Y" ) );
		
		billRegisterCriteria.addOrder(Order.asc( BillRegisterDAOExtn.BILL_PROCESS_NAME ));
		
		List<BillRegister> eligibleProcessList = getHibernateTemplate().findByCriteria( billRegisterCriteria );	
		
		if ( eligibleProcessList != null && !eligibleProcessList.isEmpty() ) {
			return eligibleProcessList;
		} else {
			return null;
		}
	}
}
