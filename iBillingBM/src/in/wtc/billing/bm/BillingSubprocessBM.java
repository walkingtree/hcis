/**
 * 
 */
package in.wtc.billing.bm;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class BillingSubprocessBM implements Serializable {
	private String subProcessName;
	
	/**
	 * The variable, contributeToFnclCharge indicate whether the billed items will be contributing towards 
	 * the financial charges. For example, if services are billed then it will be
	 * contributed as financial charges, while a receipt will not be considered as 
	 * financial charge.
	 * 
	 */
	private Boolean contributeToFnclCharge = Boolean.FALSE;
	
	private List<BillDetailsBM>billDetailsList;
	
	private Double subProcessTotals;
	
	private String remarks;

	public String getSubProcessName() {
		return subProcessName;
	}

	public void setSubProcessName(String subProcessName) {
		this.subProcessName = subProcessName;
	}

	public Boolean getContributeToFnclCharge() {
		return contributeToFnclCharge;
	}

	public void setContributeToFnclCharge(Boolean contributeToFnclCharge) {
		this.contributeToFnclCharge = contributeToFnclCharge;
	}

	public List<BillDetailsBM> getBillDetailsList() {
		return billDetailsList;
	}

	public void setBillDetailsList(List<BillDetailsBM> billDetailsList) {
		this.billDetailsList = billDetailsList;
	}

	public Double getSubProcessTotals() {
		return subProcessTotals;
	}

	public void setSubProcessTotals(Double subProcessTotals) {
		this.subProcessTotals = subProcessTotals;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BillingSubprocessBM() {
	}
}
