package com.wtc.hcis.ip.da;

/**
 * DischargeActivity entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DischargeActivity implements java.io.Serializable {

	// Fields

	private DischargeActivityId id;
	private Integer version;
	private DoctorOrderStatus doctorOrderStatus;
	private ActivityType activityType;
	private Admission admission;
	private String createdBy;
	private String remarks;

	// Constructors

	/** default constructor */
	public DischargeActivity() {
	}

	/** minimal constructor */
	public DischargeActivity(DischargeActivityId id,
			DoctorOrderStatus doctorOrderStatus, ActivityType activityType,
			Admission admission) {
		this.id = id;
		this.doctorOrderStatus = doctorOrderStatus;
		this.activityType = activityType;
		this.admission = admission;
	}

	/** full constructor */
	public DischargeActivity(DischargeActivityId id,
			DoctorOrderStatus doctorOrderStatus, ActivityType activityType,
			Admission admission, String createdBy, String remarks) {
		this.id = id;
		this.doctorOrderStatus = doctorOrderStatus;
		this.activityType = activityType;
		this.admission = admission;
		this.createdBy = createdBy;
		this.remarks = remarks;
	}

	// Property accessors

	public DischargeActivityId getId() {
		return this.id;
	}

	public void setId(DischargeActivityId id) {
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

	public Admission getAdmission() {
		return this.admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
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