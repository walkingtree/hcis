package com.wtc.hcis.ip.da;

/**
 * BedEnvelopeHasPoolId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedEnvelopeHasPoolId implements java.io.Serializable {

	// Fields

	private String envelopeName;
	private String poolName;

	// Constructors

	/** default constructor */
	public BedEnvelopeHasPoolId() {
	}

	/** full constructor */
	public BedEnvelopeHasPoolId(String envelopeName, String poolName) {
		this.envelopeName = envelopeName;
		this.poolName = poolName;
	}

	// Property accessors

	public String getEnvelopeName() {
		return this.envelopeName;
	}

	public void setEnvelopeName(String envelopeName) {
		this.envelopeName = envelopeName;
	}

	public String getPoolName() {
		return this.poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BedEnvelopeHasPoolId))
			return false;
		BedEnvelopeHasPoolId castOther = (BedEnvelopeHasPoolId) other;

		return ((this.getEnvelopeName() == castOther.getEnvelopeName()) || (this
				.getEnvelopeName() != null
				&& castOther.getEnvelopeName() != null && this
				.getEnvelopeName().equals(castOther.getEnvelopeName())))
				&& ((this.getPoolName() == castOther.getPoolName()) || (this
						.getPoolName() != null
						&& castOther.getPoolName() != null && this
						.getPoolName().equals(castOther.getPoolName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEnvelopeName() == null ? 0 : this.getEnvelopeName()
						.hashCode());
		result = 37 * result
				+ (getPoolName() == null ? 0 : this.getPoolName().hashCode());
		return result;
	}

}