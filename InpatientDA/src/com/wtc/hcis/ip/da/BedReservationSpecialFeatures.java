package com.wtc.hcis.ip.da;

/**
 * BedReservationSpecialFeatures entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedReservationSpecialFeatures implements java.io.Serializable {

	// Fields

	private BedReservationSpecialFeaturesId id;
	private Integer version;
	private BedReservation bedReservation;
	private BedSpecialFeature bedSpecialFeature;
	private String requiredFlag;

	// Constructors

	/** default constructor */
	public BedReservationSpecialFeatures() {
	}

	/** minimal constructor */
	public BedReservationSpecialFeatures(BedReservationSpecialFeaturesId id,
			BedReservation bedReservation, BedSpecialFeature bedSpecialFeature) {
		this.id = id;
		this.bedReservation = bedReservation;
		this.bedSpecialFeature = bedSpecialFeature;
	}

	/** full constructor */
	public BedReservationSpecialFeatures(BedReservationSpecialFeaturesId id,
			BedReservation bedReservation, BedSpecialFeature bedSpecialFeature,
			String requiredFlag) {
		this.id = id;
		this.bedReservation = bedReservation;
		this.bedSpecialFeature = bedSpecialFeature;
		this.requiredFlag = requiredFlag;
	}

	// Property accessors

	public BedReservationSpecialFeaturesId getId() {
		return this.id;
	}

	public void setId(BedReservationSpecialFeaturesId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public BedReservation getBedReservation() {
		return this.bedReservation;
	}

	public void setBedReservation(BedReservation bedReservation) {
		this.bedReservation = bedReservation;
	}

	public BedSpecialFeature getBedSpecialFeature() {
		return this.bedSpecialFeature;
	}

	public void setBedSpecialFeature(BedSpecialFeature bedSpecialFeature) {
		this.bedSpecialFeature = bedSpecialFeature;
	}

	public String getRequiredFlag() {
		return this.requiredFlag;
	}

	public void setRequiredFlag(String requiredFlag) {
		this.requiredFlag = requiredFlag;
	}

}