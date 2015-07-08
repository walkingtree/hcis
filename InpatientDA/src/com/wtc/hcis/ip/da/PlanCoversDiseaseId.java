package com.wtc.hcis.ip.da;

/**
 * PlanCoversDiseaseId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PlanCoversDiseaseId implements java.io.Serializable {

	// Fields

	private String planCd;
	private String diseaseName;

	// Constructors

	/** default constructor */
	public PlanCoversDiseaseId() {
	}

	/** full constructor */
	public PlanCoversDiseaseId(String planCd, String diseaseName) {
		this.planCd = planCd;
		this.diseaseName = diseaseName;
	}

	// Property accessors

	public String getPlanCd() {
		return this.planCd;
	}

	public void setPlanCd(String planCd) {
		this.planCd = planCd;
	}

	public String getDiseaseName() {
		return this.diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PlanCoversDiseaseId))
			return false;
		PlanCoversDiseaseId castOther = (PlanCoversDiseaseId) other;

		return ((this.getPlanCd() == castOther.getPlanCd()) || (this
				.getPlanCd() != null
				&& castOther.getPlanCd() != null && this.getPlanCd().equals(
				castOther.getPlanCd())))
				&& ((this.getDiseaseName() == castOther.getDiseaseName()) || (this
						.getDiseaseName() != null
						&& castOther.getDiseaseName() != null && this
						.getDiseaseName().equals(castOther.getDiseaseName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPlanCd() == null ? 0 : this.getPlanCd().hashCode());
		result = 37
				* result
				+ (getDiseaseName() == null ? 0 : this.getDiseaseName()
						.hashCode());
		return result;
	}

}