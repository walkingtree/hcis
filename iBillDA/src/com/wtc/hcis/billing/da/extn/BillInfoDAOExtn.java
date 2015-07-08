/**
 * 
 */
package com.wtc.hcis.billing.da.extn;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.wtc.hcis.billing.da.BillInfo;
import com.wtc.hcis.billing.da.BillInfoDAO;

/**
 * @author Bhavesh
 *
 */
public class BillInfoDAOExtn extends BillInfoDAO {

	private static final Log log = LogFactory.getLog( BillInfoDAOExtn.class );
	
	public List<BillInfo> getBillInfo(String accountId,String clientName) throws Exception{
		
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass( BillInfo.class);
			
			if( accountId != null && accountId.length() >0  ){
				criteria.add( Restrictions.eq("accountId", accountId));
			}
			if( clientName != null && clientName.length() >0  ){
				criteria.add( Restrictions.eq("clientName", clientName));
			}
			return getHibernateTemplate().findByCriteria(criteria);
		} catch (Exception e) {

			log.error("Failed to get billInfo ", e);
			throw e;
		}
	}
	

}
