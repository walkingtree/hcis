package com.wtc.hcis.ip.da;

/**
 * ClaimSponsorSla entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ClaimSponsorSla implements java.io.Serializable {

	// Fields

	private ClaimSponsorSlaId id;
	private Integer version;
	private ActivityType activityType;
	private ClaimSponsor claimSponsor;
	private Double slaPeriod;
	private String periodUnit;

	// Constructors

	/** default constructor */
	public ClaimSponsorSla() {
	}

	/** full constructor */
	public ClaimSponsorSla(ClaimSponsorSlaId id, ActivityType activityType,
			ClaimSponsor claimSponsor, Double slaPeriod, String periodUnit) {
		this.id = id;
		this.activityType = activityType;
		this.claimSponsor = claimSponsor;
		this.slaPeriod = slaPeriod;
		this.periodUnit = periodUnit;
	}

	// Property accessors

	public ClaimSponsorSlaId getId() {
		return this.id;
	}

	public void setId(ClaimSponsorSlaId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ActivityType getActivityType() {
		return this.activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public ClaimSponsor getClaimSponsor() {
		return this.claimSponsor;
	}

	public void setClaimSponsor(ClaimSponsor claimSponsor) {
		this.claimSponsor = claimSponsor;
	}

	public Double getSlaPeriod() {
		return this.slaPeriod;
	}

	public void setSlaPeriod(Double slaPeriod) {
		this.slaPeriod = slaPeriod;
	}

	public String getPeriodUnit() {
		return this.periodUnit;
	}

	public void setPeriodUnit(String periodUnit) {
		this.periodUnit = periodUnit;
	}

}