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

/**
 * @author Alok Ranjan
 * 
 */
public class ServiceProcessImpl implements BillProcess {

	private ServiceManager serviceManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.billing.processes.BillProcess#getBillData(java.lang.Long)
	 */
	public BillDataBM getBillData(Long billNumber) {
		serviceManager = (ServiceManager) AppContext.getApplicationContext()
				.getBean("ServiceManager");
		BillDataBM billDataBM = serviceManager.getBillData(billNumber
				.intValue());
		return billDataBM;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see in.wtc.billing.processes.BillProcess#runBilling(java.lang.String,
	 *      java.lang.Long)
	 */
	public BillDataBM runBilling(String acAccountId, String referenceType,
			String accountId, Long billNumber) {

		try {
			serviceManager = (ServiceManager) AppContext
					.getApplicationContext().getBean("ServiceManager");

			// Here nothing to do with acAccountId, so leave it
			BillDataBM billDataBM = serviceManager.billServiceDetails(
					referenceType, accountId, billNumber.intValue(),
					"Billing Process");
			return billDataBM;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Fault fault = new Fault(e.getMessage());
			throw new HCISException(fault);
		}
	}

}
