package com.wtc.hcis.ip.da;

/**
 * PlanHasServicesId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PlanHasServicesId implements java.io.Serializable {

	// Fields

	private String planCd;
	private String serviceCode;

	// Constructors

	/** default constructor */
	public PlanHasServicesId() {
	}

	/** full constructor */
	public PlanHasServicesId(String planCd, String serviceCode) {
		this.planCd = planCd;
		this.serviceCode = serviceCode;
	}

	// Property accessors

	public String getPlanCd() {
		return this.planCd;
	}

	public void setPlanCd(String planCd) {
		this.planCd = planCd;
	}

	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PlanHasServicesId))
			return false;
		PlanHasServicesId castOther = (PlanHasServicesId) other;

		return ((this.getPlanCd() == castOther.getPlanCd()) || (this
				.getPlanCd() != null
				&& castOther.getPlanCd() != null && this.getPlanCd().equals(
				castOther.getPlanCd())))
				&& ((this.getServiceCode() == castOther.getServiceCode()) || (this
						.getServiceCode() != null
						&& castOther.getServiceCode() != null && this
						.getServiceCode().equals(castOther.getServiceCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPlanCd() == null ? 0 : this.getPlanCd().hashCode());
		result = 37
				* result
				+ (getServiceCode() == null ? 0 : this.getServiceCode()
						.hashCode());
		return result;
	}

}