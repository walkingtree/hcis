package com.wtc.hcis.ip.da;

/**
 * AdmissionClaimActivity entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AdmissionClaimActivity implements java.io.Serializable {

	// Fields

	private AdmissionClaimActivityId id;
	private Integer version;
	private ActivityType activityType;
	private SponsorClaimStatus sponsorClaimStatus;
	private String createdBy;
	private String remarks;

	// Constructors

	/** default constructor */
	public AdmissionClaimActivity() {
	}

	/** minimal constructor */
	public AdmissionClaimActivity(AdmissionClaimActivityId id,
			ActivityType activityType, SponsorClaimStatus sponsorClaimStatus) {
		this.id = id;
		this.activityType = activityType;
		this.sponsorClaimStatus = sponsorClaimStatus;
	}

	/** full constructor */
	public AdmissionClaimActivity(AdmissionClaimActivityId id,
			ActivityType activityType, SponsorClaimStatus sponsorClaimStatus,
			String createdBy, String remarks) {
		this.id = id;
		this.activityType = activityType;
		this.sponsorClaimStatus = sponsorClaimStatus;
		this.createdBy = createdBy;
		this.remarks = remarks;
	}

	// Property accessors

	public AdmissionClaimActivityId getId() {
		return this.id;
	}

	public void setId(AdmissionClaimActivityId id) {
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

	public SponsorClaimStatus getSponsorClaimStatus() {
		return this.sponsorClaimStatus;
	}

	public void setSponsorClaimStatus(SponsorClaimStatus sponsorClaimStatus) {
		this.sponsorClaimStatus = sponsorClaimStatus;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}