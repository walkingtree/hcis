/**
 * 
 */
package in.wtc.billing.processes;

import in.wtc.billing.bm.BillDataBM;

/**
 * @author Alok Ranjan
 *
 */
public interface BillProcess {
	/**
	 * 
	 * @param referenceType:-  same as client name (i.e. OPD,IPD etc.)
	 * @param accountId:-  corresponding id (patient id in case of OPD or admission number in case of IPD )
	 * @param billNumber:- bill number to be assigned 
	 * @return
	 */
	BillDataBM runBilling( String acAccountId,String referenceType,String accountId, Long billNumber );
	
	BillDataBM getBillData( Long billNumber );
}
