package com.wtc.hcis.billing.da;

/**
 * BillAccountId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BillAccountId implements java.io.Serializable {

	// Fields

	private String accountId;
	private String clientName;

	// Constructors

	/** default constructor */
	public BillAccountId() {
	}

	/** full constructor */
	public BillAccountId(String accountId, String clientName) {
		this.accountId = accountId;
		this.clientName = clientName;
	}

	// Property accessors

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BillAccountId))
			return false;
		BillAccountId castOther = (BillAccountId) other;

		return ((this.getAccountId() == castOther.getAccountId()) || (this
				.getAccountId() != null
				&& castOther.getAccountId() != null && this.getAccountId()
				.equals(castOther.getAccountId())))
				&& ((this.getClientName() == castOther.getClientName()) || (this
						.getClientName() != null
						&& castOther.getClientName() != null && this
						.getClientName().equals(castOther.getClientName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAccountId() == null ? 0 : this.getAccountId().hashCode());
		result = 37
				* result
				+ (getClientName() == null ? 0 : this.getClientName()
						.hashCode());
		return result;
	}

}