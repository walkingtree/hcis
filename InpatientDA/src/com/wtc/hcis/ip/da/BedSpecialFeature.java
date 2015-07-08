package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BedSpecialFeature entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedSpecialFeature implements java.io.Serializable {

	// Fields

	private String featureName;
	private String description;
	private String locationWrtBedInd;
	private Date effectiveFromDtm;
	private Date effectiveToDtm;
	private Set bedHasSpecialFeatureses = new HashSet(0);
	private Set bedReservationSpecialFeatureses = new HashSet(0);

	// Constructors

	/** default constructor */
	public BedSpecialFeature() {
	}

	/** minimal constructor */
	public BedSpecialFeature(String featureName) {
		this.featureName = featureName;
	}

	/** full constructor */
	public BedSpecialFeature(String featureName, String description,
			String locationWrtBedInd, Date effectiveFromDtm,
			Date effectiveToDtm, Set bedHasSpecialFeatureses,
			Set bedReservationSpecialFeatureses) {
		this.featureName = featureName;
		this.description = description;
		this.locationWrtBedInd = locationWrtBedInd;
		this.effectiveFromDtm = effectiveFromDtm;
		this.effectiveToDtm = effectiveToDtm;
		this.bedHasSpecialFeatureses = bedHasSpecialFeatureses;
		this.bedReservationSpecialFeatureses = bedReservationSpecialFeatureses;
	}

	// Property accessors

	public String getFeatureName() {
		return this.featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocationWrtBedInd() {
		return this.locationWrtBedInd;
	}

	public void setLocationWrtBedInd(String locationWrtBedInd) {
		this.locationWrtBedInd = locationWrtBedInd;
	}

	public Date getEffectiveFromDtm() {
		return this.effectiveFromDtm;
	}

	public void setEffectiveFromDtm(Date effectiveFromDtm) {
		this.effectiveFromDtm = effectiveFromDtm;
	}

	public Date getEffectiveToDtm() {
		return this.effectiveToDtm;
	}

	public void setEffectiveToDtm(Date effectiveToDtm) {
		this.effectiveToDtm = effectiveToDtm;
	}

	public Set getBedHasSpecialFeatureses() {
		return this.bedHasSpecialFeatureses;
	}

	public void setBedHasSpecialFeatureses(Set bedHasSpecialFeatureses) {
		this.bedHasSpecialFeatureses = bedHasSpecialFeatureses;
	}

	public Set getBedReservationSpecialFeatureses() {
		return this.bedReservationSpecialFeatureses;
	}

	public void setBedReservationSpecialFeatureses(
			Set bedReservationSpecialFeatureses) {
		this.bedReservationSpecialFeatureses = bedReservationSpecialFeatureses;
	}

}