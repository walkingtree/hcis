/**
 * 
 */
package com.wtc.hcis.billing.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.billing.da.BillProcessRouting;
import com.wtc.hcis.billing.da.BillProcessRoutingDAO;

/**
 * @author Alok Ranjan
 *
 */
public class BillProcessRoutingDAOExtn extends BillProcessRoutingDAO {
	
	public static final String CLIENT_NAME = "id.clientName";
	public static final String PROCESS_SEQ_NBR = "processSeqNbr";
	
	public List<BillProcessRouting> getActiveProcesses( String clientName ) {

		DetachedCriteria billRegisterCriteria = DetachedCriteria.forClass( BillProcessRouting.class );
		
		billRegisterCriteria.add( Restrictions.eq( BillProcessRoutingDAOExtn.CLIENT_NAME , clientName ) );
		billRegisterCriteria.addOrder( Order.asc( BillProcessRoutingDAOExtn.PROCESS_SEQ_NBR));
		
		List<BillProcessRouting> eligibleProcessList = getHibernateTemplate().findByCriteria( billRegisterCriteria );	
		
		if ( eligibleProcessList != null && !eligibleProcessList.isEmpty() ) {
			return eligibleProcessList;
		} else {
			return null;
		}
	}
}
