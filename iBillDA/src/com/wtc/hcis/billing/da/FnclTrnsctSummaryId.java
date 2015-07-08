package com.wtc.hcis.billing.da;

/**
 * FnclTrnsctSummaryId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class FnclTrnsctSummaryId implements java.io.Serializable {

	// Fields

	private Long billNbr;
	private String transactionType;
	private String transactionReference;

	// Constructors

	/** default constructor */
	public FnclTrnsctSummaryId() {
	}

	/** full constructor */
	public FnclTrnsctSummaryId(Long billNbr, String transactionType,
			String transactionReference) {
		this.billNbr = billNbr;
		this.transactionType = transactionType;
		this.transactionReference = transactionReference;
	}

	// Property accessors

	public Long getBillNbr() {
		return this.billNbr;
	}

	public void setBillNbr(Long billNbr) {
		this.billNbr = billNbr;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionReference() {
		return this.transactionReference;
	}

	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FnclTrnsctSummaryId))
			return false;
		FnclTrnsctSummaryId castOther = (FnclTrnsctSummaryId) other;

		return ((this.getBillNbr() == castOther.getBillNbr()) || (this
				.getBillNbr() != null
				&& castOther.getBillNbr() != null && this.getBillNbr().equals(
				castOther.getBillNbr())))
				&& ((this.getTransactionType() == castOther
						.getTransactionType()) || (this.getTransactionType() != null
						&& castOther.getTransactionType() != null && this
						.getTransactionType().equals(
								castOther.getTransactionType())))
				&& ((this.getTransactionReference() == castOther
						.getTransactionReference()) || (this
						.getTransactionReference() != null
						&& castOther.getTransactionReference() != null && this
						.getTransactionReference().equals(
								castOther.getTransactionReference())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBillNbr() == null ? 0 : this.getBillNbr().hashCode());
		result = 37
				* result
				+ (getTransactionType() == null ? 0 : this.getTransactionType()
						.hashCode());
		result = 37
				* result
				+ (getTransactionReference() == null ? 0 : this
						.getTransactionReference().hashCode());
		return result;
	}

}