/**
 * 
 */
package in.wtc.billing.bm;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author Alok Ranjan
 *
 */
public class BillObjectBM {
	private Double billAmount;
	private Date billDueDate;
	private Date billDate;
	private Long billNumber;
	private String accountId;
	
	
	private List<BillDataBM>billDataList;
	
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
	
	public Long getBillNumber() {
		return billNumber;
	}
	
	public void setBillNumber(Long billNumber) {
		this.billNumber = billNumber;
	}
	
	public List<BillDataBM> getBillDataList() {
		return billDataList;
	}
	
	public void setBillDataList(List<BillDataBM> billDataList) {
		this.billDataList = billDataList;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public BillObjectBM() {
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
	
	
}
