/**
 * 
 */
package com.wtc.hcis.billing.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.billing.da.BillItemDetails;
import com.wtc.hcis.billing.da.BillItemDetailsDAO;

/**
 * @author Alok Ranjan
 *
 */
public class BillItemDetailsDAOExtn extends BillItemDetailsDAO {
	public static final String BILL_NUMBER = "id.billNumber";
	public static final String BILL_PROCESS_NAME = "id.processName";
	public static final String BILL_SUB_PROCESS_NAME = "id.subProcessName";
	public static final String BILL_ITEM_SEQUENCE_NBR = "id.itemSequenceNbr";
	
	public List<BillItemDetails> getBillItemDetails( Long billNumber, String processName ) {

		DetachedCriteria billItemsCriteria = DetachedCriteria.forClass( BillItemDetails.class );
		
		billItemsCriteria.add( Restrictions.eq( BillItemDetailsDAOExtn.BILL_NUMBER, billNumber ) );
		
		if ( processName != null && processName.length() > 0 ) {
			billItemsCriteria.add( Restrictions.eq( BillItemDetailsDAOExtn.BILL_PROCESS_NAME, processName ) );
		}
		
		billItemsCriteria.addOrder(Order.asc( BillItemDetailsDAOExtn.BILL_PROCESS_NAME ));
		billItemsCriteria.addOrder(Order.asc( BillItemDetailsDAOExtn.BILL_SUB_PROCESS_NAME ));
		billItemsCriteria.addOrder(Order.asc( BillItemDetailsDAOExtn.BILL_ITEM_SEQUENCE_NBR ));
		
		List<BillItemDetails> billedItemsList = getHibernateTemplate().findByCriteria( billItemsCriteria );	
		
		if ( billedItemsList != null && !billedItemsList.isEmpty() ) {
			return billedItemsList;
		} else {
			return null;
		}
	}
}
