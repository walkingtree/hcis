package com.wtc.hcis.billing.da;

/**
 * BillProcessRoutingId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BillProcessRoutingId implements java.io.Serializable {

	// Fields

	private String clientName;
	private String processName;

	// Constructors

	/** default constructor */
	public BillProcessRoutingId() {
	}

	/** full constructor */
	public BillProcessRoutingId(String clientName, String processName) {
		this.clientName = clientName;
		this.processName = processName;
	}

	// Property accessors

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BillProcessRoutingId))
			return false;
		BillProcessRoutingId castOther = (BillProcessRoutingId) other;

		return ((this.getClientName() == castOther.getClientName()) || (this
				.getClientName() != null
				&& castOther.getClientName() != null && this.getClientName()
				.equals(castOther.getClientName())))
				&& ((this.getProcessName() == castOther.getProcessName()) || (this
						.getProcessName() != null
						&& castOther.getProcessName() != null && this
						.getProcessName().equals(castOther.getProcessName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getClientName() == null ? 0 : this.getClientName()
						.hashCode());
		result = 37
				* result
				+ (getProcessName() == null ? 0 : this.getProcessName()
						.hashCode());
		return result;
	}

}