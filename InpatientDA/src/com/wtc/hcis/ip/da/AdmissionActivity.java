package com.wtc.hcis.ip.da;

/**
 * AdmissionActivity entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AdmissionActivity implements java.io.Serializable {

	// Fields

	private AdmissionActivityId id;
	private Integer version;
	private ActivityType activityType;
	private AdmissionStatus admissionStatus;
	private Admission admission;
	private String remarks;
	private String createdBy;

	// Constructors

	/** default constructor */
	public AdmissionActivity() {
	}

	/** minimal constructor */
	public AdmissionActivity(AdmissionActivityId id, ActivityType activityType,
			AdmissionStatus admissionStatus, Admission admission) {
		this.id = id;
		this.activityType = activityType;
		this.admissionStatus = admissionStatus;
		this.admission = admission;
	}

	/** full constructor */
	public AdmissionActivity(AdmissionActivityId id, ActivityType activityType,
			AdmissionStatus admissionStatus, Admission admission,
			String remarks, String createdBy) {
		this.id = id;
		this.activityType = activityType;
		this.admissionStatus = admissionStatus;
		this.admission = admission;
		this.remarks = remarks;
		this.createdBy = createdBy;
	}

	// Property accessors

	public AdmissionActivityId getId() {
		return this.id;
	}

	public void setId(AdmissionActivityId id) {
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

	public AdmissionStatus getAdmissionStatus() {
		return this.admissionStatus;
	}

	public void setAdmissionStatus(AdmissionStatus admissionStatus) {
		this.admissionStatus = admissionStatus;
	}

	public Admission getAdmission() {
		return this.admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}