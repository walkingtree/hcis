package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * OtPatientSurgeryChecklist entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtPatientSurgeryChecklist implements java.io.Serializable {

	// Fields

	private OtPatientSurgeryChecklistId id;
	private Integer version;
	private OtSurgeryStatus otSurgeryStatus;
	private OtPatientSurgery otPatientSurgery;
	private String createdBy;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public OtPatientSurgeryChecklist() {
	}

	/** minimal constructor */
	public OtPatientSurgeryChecklist(OtPatientSurgeryChecklistId id,
			OtSurgeryStatus otSurgeryStatus, OtPatientSurgery otPatientSurgery) {
		this.id = id;
		this.otSurgeryStatus = otSurgeryStatus;
		this.otPatientSurgery = otPatientSurgery;
	}

	/** full constructor */
	public OtPatientSurgeryChecklist(OtPatientSurgeryChecklistId id,
			OtSurgeryStatus otSurgeryStatus, OtPatientSurgery otPatientSurgery,
			String createdBy, Date createdDtm) {
		this.id = id;
		this.otSurgeryStatus = otSurgeryStatus;
		this.otPatientSurgery = otPatientSurgery;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public OtPatientSurgeryChecklistId getId() {
		return this.id;
	}

	public void setId(OtPatientSurgeryChecklistId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}



	public OtPatientSurgery getOtPatientSurgery() {
		return this.otPatientSurgery;
	}

	public void setOtPatientSurgery(OtPatientSurgery otPatientSurgery) {
		this.otPatientSurgery = otPatientSurgery;
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

	public OtSurgeryStatus getOtSurgeryStatus() {
		return otSurgeryStatus;
	}

	public void setOtSurgeryStatus(OtSurgeryStatus otSurgeryStatus) {
		this.otSurgeryStatus = otSurgeryStatus;
	}

}