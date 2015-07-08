
package in.wtc.hcis.bo.accountant;

import in.wtc.hcis.bo.common.ListRange;

import java.util.Map;

/**
 * @author Bhavesh
 *
 */

public interface AccountantManager {
	
	/**
	 * 
	 * @param patientId
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	public ListRange getPatinetReceivable(Integer patientId,int start,int count,String orderBy) ;
		
	Integer createReceipt(  Integer patientId, 
							Double amount, 
							Map<String,String> attributeMap,
							String createdBy,
							String invoiceId );
}
