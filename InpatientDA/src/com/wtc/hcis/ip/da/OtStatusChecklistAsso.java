package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * OtStatusChecklistAsso entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtStatusChecklistAsso implements java.io.Serializable {

	// Fields

	private OtStatusChecklistAssoId id;
	private OtSurgeryStatus otSurgeryStatus;
	private String applyWhen;
	private String createdBy;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public OtStatusChecklistAsso() {
	}

	/** full constructor */
	public OtStatusChecklistAsso(OtStatusChecklistAssoId id,
			OtSurgeryStatus otSurgeryStatus, String applyWhen,
			String createdBy, Date createdDtm) {
		this.id = id;
		this.otSurgeryStatus = otSurgeryStatus;
		this.applyWhen = applyWhen;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public OtStatusChecklistAssoId getId() {
		return this.id;
	}

	public void setId(OtStatusChecklistAssoId id) {
		this.id = id;
	}

	public OtSurgeryStatus getOtSurgeryStatus() {
		return this.otSurgeryStatus;
	}

	public void setOtSurgeryStatus(OtSurgeryStatus otSurgeryStatus) {
		this.otSurgeryStatus = otSurgeryStatus;
	}

	public String getApplyWhen() {
		return this.applyWhen;
	}

	public void setApplyWhen(String applyWhen) {
		this.applyWhen = applyWhen;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDtm() {
		return this.createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

}