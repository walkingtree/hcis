/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Vinay Kurudi
 *
 */
public class ReceiptInfoBM implements Serializable{

	private Double amount;
	private Date transactionDate;
	private String receiptSeqNbr;
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
	public ReceiptInfoBM() {
	}
	public String getReceiptSeqNbr() {
		return receiptSeqNbr;
	}
	public void setReceiptSeqNbr(String receiptSeqNbr) {
		this.receiptSeqNbr = receiptSeqNbr;
	}
}
