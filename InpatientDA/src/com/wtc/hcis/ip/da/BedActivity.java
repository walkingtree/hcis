package com.wtc.hcis.ip.da;

/**
 * BedActivity entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedActivity implements java.io.Serializable {

	// Fields

	private BedActivityId id;
	private Integer version;
	private BedMaster bedMaster;
	private ActivityType activityType;
	private BedStatus bedStatus;
	private String createdBy;
	private String remarks;

	// Constructors

	/** default constructor */
	public BedActivity() {
	}

	/** minimal constructor */
	public BedActivity(BedActivityId id, BedMaster bedMaster,
			ActivityType activityType, BedStatus bedStatus) {
		this.id = id;
		this.bedMaster = bedMaster;
		this.activityType = activityType;
		this.bedStatus = bedStatus;
	}

	/** full constructor */
	public BedActivity(BedActivityId id, BedMaster bedMaster,
			ActivityType activityType, BedStatus bedStatus, String createdBy,
			String remarks) {
		this.id = id;
		this.bedMaster = bedMaster;
		this.activityType = activityType;
		this.bedStatus = bedStatus;
		this.createdBy = createdBy;
		this.remarks = remarks;
	}

	// Property accessors

	public BedActivityId getId() {
		return this.id;
	}

	public void setId(BedActivityId id) {
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

	public ActivityType getActivityType() {
		return this.activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public BedStatus getBedStatus() {
		return this.bedStatus;
	}

	public void setBedStatus(BedStatus bedStatus) {
		this.bedStatus = bedStatus;
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