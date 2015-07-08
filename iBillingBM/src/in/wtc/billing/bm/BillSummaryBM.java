/**
 * 
 */
package in.wtc.billing.bm;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class BillSummaryBM implements Serializable {
	private String accountId;
	private Long billNumber;
	private Double billAmount;
	private Double dueAmount;
	private Date billDate;
	private Date billDueDate;
	
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

	public Double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
	
}
