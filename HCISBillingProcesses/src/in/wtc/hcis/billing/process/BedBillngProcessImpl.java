/**
 * 
 */
package in.wtc.hcis.billing.process;

import in.wtc.billing.bm.BillDataBM;
import in.wtc.billing.processes.BillProcess;
import in.wtc.hcis.bo.context.AppContext;
import in.wtc.hcis.bo.services.ServiceManager;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ip.bo.bed.BedManager;

/**
 * @author Bhavesh
 *
 */
public class BedBillngProcessImpl implements BillProcess{

	BedManager bedManager;
	public BillDataBM getBillData(Long billNumber) {
		
		return null;
	}

	public BillDataBM runBilling(String acAccountId, String referenceType,
			String accountId, Long billNumber) {
		try {
			bedManager =(BedManager) AppContext.getApplicationContext().getBean("BedManager");
			
			//Here nothing to do with acAccountId, so leave it
			//
			
			BillDataBM billDataBM = bedManager.billBedAssignments(Integer.parseInt(accountId),
									billNumber.intValue(), "Billing Process");
			return billDataBM;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Fault fault = new Fault(e.getMessage()); 
			throw new HCISException(fault);
		}

	}

}
