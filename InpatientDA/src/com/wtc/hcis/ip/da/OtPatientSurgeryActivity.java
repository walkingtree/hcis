package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * OtPatientSurgeryActivity entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtPatientSurgeryActivity implements java.io.Serializable {

	// Fields

	private OtPatientSurgeryActivityId id;
	private OtPatientSurgery otPatientSurgery;
	private OtSurgeryStatus otSurgeryStatus;
	private Date activityTime;
	private String remarks;
	private Date createdDtm;
	private String createdBy;

	// Constructors

	/** default constructor */
	public OtPatientSurgeryActivity() {
	}

	/** minimal constructor */
	public OtPatientSurgeryActivity(OtPatientSurgeryActivityId id,
			OtPatientSurgery otPatientSurgery, OtSurgeryStatus otSurgeryStatus,
			Date activityTime, Date createdDtm, String createdBy) {
		this.id = id;
		this.otPatientSurgery = otPatientSurgery;
		this.otSurgeryStatus = otSurgeryStatus;
		this.activityTime = activityTime;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
	}

	/** full constructor */
	public OtPatientSurgeryActivity(OtPatientSurgeryActivityId id,
			OtPatientSurgery otPatientSurgery, OtSurgeryStatus otSurgeryStatus,
			Date activityTime, String remarks, Date createdDtm, String createdBy) {
		this.id = id;
		this.otPatientSurgery = otPatientSurgery;
		this.otSurgeryStatus = otSurgeryStatus;
		this.activityTime = activityTime;
		this.remarks = remarks;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
	}

	// Property accessors

	public OtPatientSurgeryActivityId getId() {
		return this.id;
	}

	public void setId(OtPatientSurgeryActivityId id) {
		this.id = id;
	}

	public OtPatientSurgery getOtPatientSurgery() {
		return this.otPatientSurgery;
	}

	public void setOtPatientSurgery(OtPatientSurgery otPatientSurgery) {
		this.otPatientSurgery = otPatientSurgery;
	}

	public OtSurgeryStatus getOtSurgeryStatus() {
		return this.otSurgeryStatus;
	}

	public void setOtSurgeryStatus(OtSurgeryStatus otSurgeryStatus) {
		this.otSurgeryStatus = otSurgeryStatus;
	}

	public Date getActivityTime() {
		return this.activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreatedDtm() {
		return this.createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}