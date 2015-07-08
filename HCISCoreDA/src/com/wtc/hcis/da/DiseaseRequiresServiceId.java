package com.wtc.hcis.da;

/**
 * DiseaseRequiresServiceId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DiseaseRequiresServiceId implements java.io.Serializable {

	// Fields

	private String diseaseName;
	private String serviceCode;

	// Constructors

	/** default constructor */
	public DiseaseRequiresServiceId() {
	}

	/** full constructor */
	public DiseaseRequiresServiceId(String diseaseName, String serviceCode) {
		this.diseaseName = diseaseName;
		this.serviceCode = serviceCode;
	}

	// Property accessors

	public String getDiseaseName() {
		return this.diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
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
		if (!(other instanceof DiseaseRequiresServiceId))
			return false;
		DiseaseRequiresServiceId castOther = (DiseaseRequiresServiceId) other;

		return ((this.getDiseaseName() == castOther.getDiseaseName()) || (this
				.getDiseaseName() != null
				&& castOther.getDiseaseName() != null && this.getDiseaseName()
				.equals(castOther.getDiseaseName())))
				&& ((this.getServiceCode() == castOther.getServiceCode()) || (this
						.getServiceCode() != null
						&& castOther.getServiceCode() != null && this
						.getServiceCode().equals(castOther.getServiceCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getDiseaseName() == null ? 0 : this.getDiseaseName()
						.hashCode());
		result = 37
				* result
				+ (getServiceCode() == null ? 0 : this.getServiceCode()
						.hashCode());
		return result;
	}

}