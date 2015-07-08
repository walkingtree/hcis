/**
 * 
 */
package in.wtc.billing.bm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class BillOverviewBM implements Serializable {
	private String accountId;
	private Long billNumber;
	private Double billAmount;
	private Double dueAmount;
	private Double paidAmount;
	private Date billDate;
	private Date billDueDate;
	
	
	private List<FnclChargesBM>fnclChargesList;
	private List<FnclTransctBM>fnclTransactionsList;
	
	
	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Long getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(Long billNumber) {
		this.billNumber = billNumber;
	}
	public Double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}
	public Date getBillDueDate() {
		return billDueDate;
	}
	public void setBillDueDate(Date billDueDate) {
		this.billDueDate = billDueDate;
	}
	public List<FnclChargesBM> getFnclChargesList() {
		return fnclChargesList;
	}
	public void setFnclChargesList(List<FnclChargesBM> fnclChargesList) {
		this.fnclChargesList = fnclChargesList;
	}
	public List<FnclTransctBM> getFnclTransactionsList() {
		return fnclTransactionsList;
	}
	public void setFnclTransactionsList(List<FnclTransctBM> fnclTransactionsList) {
		this.fnclTransactionsList = fnclTransactionsList;
	}
	public Double getDueAmount() {
		return dueAmount;
	}
	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	
	public Date getBillDate() {
		return billDate;
	}
	
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
	
}
