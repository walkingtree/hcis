package com.wtc.hcis.ip.da;

/**
 * BedReservationSpecialFeaturesId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedReservationSpecialFeaturesId implements java.io.Serializable {

	// Fields

	private Integer reservationNbr;
	private String featureName;

	// Constructors

	/** default constructor */
	public BedReservationSpecialFeaturesId() {
	}

	/** full constructor */
	public BedReservationSpecialFeaturesId(Integer reservationNbr,
			String featureName) {
		this.reservationNbr = reservationNbr;
		this.featureName = featureName;
	}

	// Property accessors

	public Integer getReservationNbr() {
		return this.reservationNbr;
	}

	public void setReservationNbr(Integer reservationNbr) {
		this.reservationNbr = reservationNbr;
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
		if (!(other instanceof BedReservationSpecialFeaturesId))
			return false;
		BedReservationSpecialFeaturesId castOther = (BedReservationSpecialFeaturesId) other;

		return ((this.getReservationNbr() == castOther.getReservationNbr()) || (this
				.getReservationNbr() != null
				&& castOther.getReservationNbr() != null && this
				.getReservationNbr().equals(castOther.getReservationNbr())))
				&& ((this.getFeatureName() == castOther.getFeatureName()) || (this
						.getFeatureName() != null
						&& castOther.getFeatureName() != null && this
						.getFeatureName().equals(castOther.getFeatureName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getReservationNbr() == null ? 0 : this.getReservationNbr()
						.hashCode());
		result = 37
				* result
				+ (getFeatureName() == null ? 0 : this.getFeatureName()
						.hashCode());
		return result;
	}

}