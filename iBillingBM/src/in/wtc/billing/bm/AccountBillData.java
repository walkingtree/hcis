/**
 * 
 */
package in.wtc.billing.bm;

/**
 * @author Bhavesh
 *
 * This model is used for sending the mediclaim related data to bill manager so that
 * adjustment related to mediclaim will be marked as bill item.If there is no data for
 * billDataBM then set this property as 'null' and set totalAssignedAmount.
 *
 * The system needs this model because claim process is some what different than other
 * processes.
 */
public class AccountBillData {

	Double totalAssignedAmount = 0.0;
	BillDataBM billDataBM;
	
	public Double getTotalAssignedAmount() {
		return totalAssignedAmount;
	}
	public void setTotalAssignedAmount(Double totalAssignedAmount) {
		this.totalAssignedAmount = totalAssignedAmount;
	}
	public BillDataBM getBillDataBM() {
		return billDataBM;
	}
	public void setBillDataBM(BillDataBM billDataBM) {
		this.billDataBM = billDataBM;
	}
	public AccountBillData() {
	}
	
}
