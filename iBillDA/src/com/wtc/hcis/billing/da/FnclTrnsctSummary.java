package com.wtc.hcis.billing.da;

import java.util.Date;

/**
 * FnclTrnsctSummary entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class FnclTrnsctSummary implements java.io.Serializable {

	// Fields

	private FnclTrnsctSummaryId id;
	private BillInfo billInfo;
	private Date transactionDate;
	private String creditDebitInd;
	private Double amount;

	// Constructors

	/** default constructor */
	public FnclTrnsctSummary() {
	}

	/** full constructor */
	public FnclTrnsctSummary(FnclTrnsctSummaryId id, BillInfo billInfo,
			Date transactionDate, String creditDebitInd, Double amount) {
		this.id = id;
		this.billInfo = billInfo;
		this.transactionDate = transactionDate;
		this.creditDebitInd = creditDebitInd;
		this.amount = amount;
	}

	// Property accessors

	public FnclTrnsctSummaryId getId() {
		return this.id;
	}

	public void setId(FnclTrnsctSummaryId id) {
		this.id = id;
	}

	public BillInfo getBillInfo() {
		return this.billInfo;
	}

	public void setBillInfo(BillInfo billInfo) {
		this.billInfo = billInfo;
	}

	public Date getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getCreditDebitInd() {
		return this.creditDebitInd;
	}

	public void setCreditDebitInd(String creditDebitInd) {
		this.creditDebitInd = creditDebitInd;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}