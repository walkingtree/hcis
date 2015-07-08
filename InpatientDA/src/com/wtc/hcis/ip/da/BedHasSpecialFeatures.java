package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * BedHasSpecialFeatures entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedHasSpecialFeatures implements java.io.Serializable {

	// Fields

	private BedHasSpecialFeaturesId id;
	private Integer version;
	private BedMaster bedMaster;
	private BedSpecialFeature bedSpecialFeature;
	private Date effectiveFromDate;
	private Date effectiveToDate;

	// Constructors

	/** default constructor */
	public BedHasSpecialFeatures() {
	}

	/** minimal constructor */
	public BedHasSpecialFeatures(BedHasSpecialFeaturesId id,
			BedMaster bedMaster, BedSpecialFeature bedSpecialFeature) {
		this.id = id;
		this.bedMaster = bedMaster;
		this.bedSpecialFeature = bedSpecialFeature;
	}

	/** full constructor */
	public BedHasSpecialFeatures(BedHasSpecialFeaturesId id,
			BedMaster bedMaster, BedSpecialFeature bedSpecialFeature,
			Date effectiveFromDate, Date effectiveToDate) {
		this.id = id;
		this.bedMaster = bedMaster;
		this.bedSpecialFeature = bedSpecialFeature;
		this.effectiveFromDate = effectiveFromDate;
		this.effectiveToDate = effectiveToDate;
	}

	// Property accessors

	public BedHasSpecialFeaturesId getId() {
		return this.id;
	}

	public void setId(BedHasSpecialFeaturesId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public BedMaster getBedMaster() {
		return this.bedMaster;
	}

	public void setBedMaster(BedMaster bedMaster) {
		this.bedMaster = bedMaster;
	}

	public BedSpecialFeature getBedSpecialFeature() {
		return this.bedSpecialFeature;
	}

	public void setBedSpecialFeature(BedSpecialFeature bedSpecialFeature) {
		this.bedSpecialFeature = bedSpecialFeature;
	}

	public Date getEffectiveFromDate() {
		return this.effectiveFromDate;
	}

	public void setEffectiveFromDate(Date effectiveFromDate) {
		this.effectiveFromDate = effectiveFromDate;
	}

	public Date getEffectiveToDate() {
		return this.effectiveToDate;
	}

	public void setEffectiveToDate(Date effectiveToDate) {
		this.effectiveToDate = effectiveToDate;
	}

}