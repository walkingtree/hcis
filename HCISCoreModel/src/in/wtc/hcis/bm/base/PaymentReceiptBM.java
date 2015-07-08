package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class PaymentReceiptBM implements Serializable {

	public PaymentReceiptBM() {
		// TODO Auto-generated constructor stub
	}

	private String transactionId;
	private String transactionType;
	private Double amount;
	private Double remngAmount;
	private Date transactionDate;
	
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getRemngAmount() {
		return remngAmount;
	}
	public void setRemngAmount(Double remngAmount) {
		this.remngAmount = remngAmount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
}
