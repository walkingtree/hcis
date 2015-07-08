/**
 * 
 */
package in.wtc.billing.processes;

import in.wtc.billing.bm.AccountBillData;
import in.wtc.billing.bm.BillDataBM;

/**
 * @author Alok Ranjan
 *
 */
public interface Accounting {
	
	/**
	 * @param referenceType:-  same as client name (i.e. OPD,IPD etc.)
	 * @param accountId:-  corresponding id (patient id in case of OPD or admission number in case of IPD )
	 * @param billNumber:- bill number to be assigned 
	 * @return
	 */
	public BillDataBM runBilling(String acAccountId,String referenceType, String accountId, Long billNumber) throws Exception;
	
	public AccountBillData applyCreditResiduals( String referenceType, String accountId,Long billNumber, boolean evaluateDeposits )throws Exception;
	
	public double createReceivables( double totalChargeAmt, 
					                  Long billNumber, 
					                  String memo,
					                  String referenceType,
					                  String accountId ) ;
	
	public BillDataBM getBillData(Long billNumber) ;
	
	Integer createInvoice( String acAccountId, Double amount,String description ) throws Exception;
}
