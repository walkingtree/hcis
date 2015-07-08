package in.wtc.hcis.bm.ip;

import java.util.Date;

public class PastTransactionsBM {

	private Date transactionDate;
	private String transactionType;
	private Double transactionAmt;
	private Double remainingAmt;
	private String transactonRemarks;

	public PastTransactionsBM() {
		
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getTransactionAmt() {
		return transactionAmt;
	}

	public void setTransactionAmt(Double transactionAmt) {
		this.transactionAmt = transactionAmt;
	}

	public Double getRemainingAmt() {
		return remainingAmt;
	}

	public void setRemainingAmt(Double remainingAmt) {
		this.remainingAmt = remainingAmt;
	}

	public String getTransactonRemarks() {
		return transactonRemarks;
	}

	public void setTransactonRemarks(String transactonRemarks) {
		this.transactonRemarks = transactonRemarks;
	}

}
