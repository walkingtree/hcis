/**
 * 
 */
package in.wtc.billing.bm;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alok Ranjan
 *
 */
public class FnclTransctBM implements Serializable {
	private Long  billNumber;
	private String transactionType;
	private String transactionReference;
	private String creditDebitInd;
	private Double amount;
	private Date transactionDate;
	
	public Long getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(Long billNumber) {
		this.billNumber = billNumber;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionReference() {
		return transactionReference;
	}
	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}
	public String getCreditDebitInd() {
		return creditDebitInd;
	}
	public void setCreditDebitInd(String creditDebitInd) {
		this.creditDebitInd = creditDebitInd;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
}
