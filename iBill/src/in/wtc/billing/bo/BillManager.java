/**
 * 
 */
package in.wtc.billing.bo;

import in.wtc.billing.bm.BillDataBM;
import in.wtc.billing.bm.BillObjectBM;
import in.wtc.billing.bm.BillOverviewBM;
import in.wtc.billing.bm.BillSummaryBM;
import in.wtc.billing.exception.BillException;
import in.wtc.billing.util.ListRange;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;



/**
 * @author Alok Ranjan
 *
 */
public interface BillManager {
	
	/**
	 * This method runs end-to-end billing for an account.
	 * Based on the registered processes, this method identifies the items that needs to be part 
	 * of this bill. Example of registered process could be 
	 * 1) Rendered services
	 * 2) Bed utility
	 * 3) Dietary
	 * 4) Pharmacy
	 * 5) Accounting
	 * 6) Blood bank
	 * 
	 * These processes are configurable and every process has its own adapter to allow processes to be 
	 * plugged into the biling system without any issues. The processes must know whether they are 
	 * going to contribute towards the charges and/or towards the transaction. 
	 * 
	 * The processes are responsible for stamping themselves with the bill number sent by the billing
	 * system. The billing number also acts as a reference for the processes to return billing details
	 * on demand. Based on the details (or summary) returned by the processes in the agreed format, the
	 * billing process creates corresponding charges.
	 * 
	 * Optionally, billing process also creates financial transaction details depending on whether process
	 * wants billing to do so or not.
	 * 
	 * Also, it is optional for the billing to create process level item details, which again depend on 
	 * whether registered process wants billing to do so or not.
	 * 
	 * All the processes will be stamping their billable components in a multi-threaded environment. 
	 * Once all the thread ends its execution, the main thread continues further billing steps. The
	 * processes can interact in two modes
	 * 1) Synchronously
	 * 2) Asynchronously
	 * 
	 * If the accounting process is registered with the billing process then once all the processes finish 
	 * stamping of billable items, the billing process instructs accounting to apply residual. Residuals 
	 * are defined as unassigned payments/adjustments. 
	 * 
	 * The billing method also assumes that 
	 *   
	 * @param acAcountId --> accountId in accounting system Ex: PAT-XX
	 * @param clientName, Ex: OPD , IPD
	 * @param referenceNbr,Ex: Appointment number in case of OPD, Admission number in case of IPD 
	 * @return
	 * @throws BillException
	 */
	BillObjectBM runBill( String acAcountId, String clientName, String referenceNbr) throws BillException;
	
	
//	BillObjectBM runBill( String accountId, 
//			              Date billFromDate, 
//			              Date billToDate ) throws BillException;
//	
	/**
	 * For a given bill number, this will return corresponding BillObject. Upto this part, it 
	 * is exactly similar to getBillDetails method. However, this method also does following:
	 * 1) Checks of ACCOUNTING process is registered or not
	 * 2) If the accounting is registered with the billing and DUPLICATE_BILL_PRINT_CHARGE is 
	 *    setup in bill settings then create
	 *    1) Receivable 
	 *    2) Update the duplicate bill print count
	 *   
	 *    Update duplicate bill print count will be useful when business finds that it is okay to 
	 *    print one or two duplicate bill on customer's demand, without charging anything. However,
	 *    if customer comes back again and again then they must pay for the same. Bill Settings
	 *    provides an attribute, FREE_DUPLICATE_BILL_COUNT, to define the free duplicate bill count.
	 * 
	 * @param billNumber
	 * @return
	 * @throws BillException
	 */
	BillObjectBM printDuplicateBill( Long billNumber ) throws BillException;
	
	/**
	 * For a given bill number, this method returns complete bill details, which
	 * includes common information like bill amount, due amount, due date, etc. as 
	 * well as process wise charge details and transaction details.
	 * 
	 * @param billNumber
	 * @return
	 * @throws BillException
	 */
	BillObjectBM getBillDetails( Long billNumber ) throws BillException;
	
	/**
	 * For a given account, this method find out all the bill and returns a list of bill object.
	 * @param accountId
	 * @return
	 * @throws BillException
	 */
	List<BillObjectBM> getBillDetails( String accountId ) throws BillException;
	
	/**
	 * This method provides an overview of the given bill. It does give you 
	 * summary of financial charges and transactions. However, it doesn't tell you
	 * anything about item level details.
	 * 
	 * @param billNumber
	 * @return
	 * @throws BillException
	 */
	BillOverviewBM getBillOverview( Long billNumber  ) throws BillException;
	
	/**
	 * This method just let you know about the basic information about bills for a given account. 
	 * 
	 * @param accountId
	 * @return
	 * @throws BillException
	 */
	List<BillSummaryBM> getBillSummary( String accountId  ) throws BillException;
	
	/**
	 * This method returns name of all the processes registered with the billing
	 *  
	 * @return
	 * @throws BillException
	 */
	List<String>getCurrentlyRegisteredProcesses() throws BillException; 
	
	/**
	 * This method may be useful when you want to show specific details to specific process
	 * owner. Basically, this method is a trimmed down version of getBillDetails, which returns
	 * complete BillObject. 
	 * 
	 * @param billNumber
	 * @param processName
	 * @return
	 * @throws BillException
	 */
	BillDataBM getBillDataForAProcess(  Long billNumber, String processName ) throws BillException; 
	
	ListRange findBillDetails(String referenceNumber, String referenceType, int start, int limit,String orderBy ) throws BillException;
}
