package com.wtc.hcis.ip.da;

/**
 * BedHasSpecialFeaturesId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedHasSpecialFeaturesId implements java.io.Serializable {

	// Fields

	private String bedNumber;
	private String featureName;

	// Constructors

	/** default constructor */
	public BedHasSpecialFeaturesId() {
	}

	/** full constructor */
	public BedHasSpecialFeaturesId(String bedNumber, String featureName) {
		this.bedNumber = bedNumber;
		this.featureName = featureName;
	}

	// Property accessors

	public String getBedNumber() {
		return this.bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}

	public String getFeatureName() {
		return this.featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BedHasSpecialFeaturesId))
			return false;
		BedHasSpecialFeaturesId castOther = (BedHasSpecialFeaturesId) other;

		return ((this.getBedNumber() == castOther.getBedNumber()) || (this
				.getBedNumber() != null
				&& castOther.getBedNumber() != null && this.getBedNumber()
				.equals(castOther.getBedNumber())))
				&& ((this.getFeatureName() == castOther.getFeatureName()) || (this
						.getFeatureName() != null
						&& castOther.getFeatureName() != null && this
						.getFeatureName().equals(castOther.getFeatureName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBedNumber() == null ? 0 : this.getBedNumber().hashCode());
		result = 37
				* result
				+ (getFeatureName() == null ? 0 : this.getFeatureName()
						.hashCode());
		return result;
	}

}