/**
 * 
 */
package in.wtc.billing.bm;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class BillDataBM implements Serializable {
	private String processName;
	private Double processTotalBillAmount;
	
	/**
	 * Key for the map will be the subprocess name
	 * The subprocess will be useful when a process has more than one subprocesses 
	 * that needs to be reported and highlighted in the bill details. For example, if
	 * you consider accounting as a process then following will be the sub-processes:
	 * 1) Payments
	 * 2) Receipts
	 * 3) Deposits
	 * 4) Receivables
	 * 5) Payable
	 * 6) Adjustments
	 * 
	 * If a process doesn't have any sub-process then the key must have a value "All".
	 * The subprocess names are mapped to CHARGE_TYPE_NAME field on fncl_charge_type, if 
	 * the billed sub-process contributes to the financial charges.
	 * 
	 */
	Map<String, BillingSubprocessBM>billDetailsMap = new HashMap<String, BillingSubprocessBM>(0);

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Double getProcessTotalBillAmount() {
		return processTotalBillAmount;
	}

	public void setProcessTotalBillAmount(Double processTotalBillAmount) {
		this.processTotalBillAmount = processTotalBillAmount;
	}

	public Map<String, BillingSubprocessBM> getBillDetailsMap() {
		return billDetailsMap;
	}

	public void setBillDetailsMap(
			Map<String, BillingSubprocessBM> billDetailsMap) {
		this.billDetailsMap = billDetailsMap;
	}

	public BillDataBM() {

	}

}
