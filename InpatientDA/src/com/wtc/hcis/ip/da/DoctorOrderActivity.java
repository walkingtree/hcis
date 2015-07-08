package com.wtc.hcis.ip.da;

/**
 * DoctorOrderActivity entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderActivity implements java.io.Serializable {

	// Fields

	private DoctorOrderActivityId id;
	private Integer version;
	private DoctorOrderStatus doctorOrderStatus;
	private ActivityType activityType;
	private DoctorOrder doctorOrder;
	private String createdBy;
	private String remarks;

	// Constructors

	/** default constructor */
	public DoctorOrderActivity() {
	}

	/** minimal constructor */
	public DoctorOrderActivity(DoctorOrderActivityId id,
			ActivityType activityType, DoctorOrder doctorOrder) {
		this.id = id;
		this.activityType = activityType;
		this.doctorOrder = doctorOrder;
	}

	/** full constructor */
	public DoctorOrderActivity(DoctorOrderActivityId id,
			DoctorOrderStatus doctorOrderStatus, ActivityType activityType,
			DoctorOrder doctorOrder, String createdBy, String remarks) {
		this.id = id;
		this.doctorOrderStatus = doctorOrderStatus;
		this.activityType = activityType;
		this.doctorOrder = doctorOrder;
		this.createdBy = createdBy;
		this.remarks = remarks;
	}

	// Property accessors

	public DoctorOrderActivityId getId() {
		return this.id;
	}

	public void setId(DoctorOrderActivityId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public DoctorOrderStatus getDoctorOrderStatus() {
		return this.doctorOrderStatus;
	}

	public void setDoctorOrderStatus(DoctorOrderStatus doctorOrderStatus) {
		this.doctorOrderStatus = doctorOrderStatus;
	}

	public ActivityType getActivityType() {
		return this.activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public DoctorOrder getDoctorOrder() {
		return this.doctorOrder;
	}

	public void setDoctorOrder(DoctorOrder doctorOrder) {
		this.doctorOrder = doctorOrder;
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