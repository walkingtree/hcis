package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * OtSurgeryStatusTime entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtSurgeryStatusTime implements java.io.Serializable {

	// Fields

	private OtSurgeryStatusTimeId id;
	private Integer version;
	private OtSurgery otSurgery;
	private OtSurgeryStatus otSurgeryStatus;
	private Integer time;
	private String surgeonRequired;
	private String createdBy;
	private Date createdDtm;
	private String modifiedBy;
	private Date lastModifiedDtm;

	// Constructors

	/** default constructor */
	public OtSurgeryStatusTime() {
	}

	/** minimal constructor */
	public OtSurgeryStatusTime(OtSurgeryStatusTimeId id, OtSurgery otSurgery,
			OtSurgeryStatus otSurgeryStatus, String surgeonRequired,
			String createdBy, Date createdDtm) {
		this.id = id;
		this.otSurgery = otSurgery;
		this.otSurgeryStatus = otSurgeryStatus;
		this.surgeonRequired = surgeonRequired;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public OtSurgeryStatusTime(OtSurgeryStatusTimeId id, OtSurgery otSurgery,
			OtSurgeryStatus otSurgeryStatus, Integer time,
			String surgeonRequired, String createdBy, Date createdDtm,
			String modifiedBy, Date lastModifiedDtm) {
		this.id = id;
		this.otSurgery = otSurgery;
		this.otSurgeryStatus = otSurgeryStatus;
		this.time = time;
		this.surgeonRequired = surgeonRequired;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.modifiedBy = modifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
	}

	// Property accessors

	public OtSurgeryStatusTimeId getId() {
		return this.id;
	}

	public void setId(OtSurgeryStatusTimeId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public OtSurgery getOtSurgery() {
		return this.otSurgery;
	}

	public void setOtSurgery(OtSurgery otSurgery) {
		this.otSurgery = otSurgery;
	}

	public OtSurgeryStatus getOtSurgeryStatus() {
		return this.otSurgeryStatus;
	}

	public void setOtSurgeryStatus(OtSurgeryStatus otSurgeryStatus) {
		this.otSurgeryStatus = otSurgeryStatus;
	}

	public Integer getTime() {
		return this.time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getSurgeonRequired() {
		return this.surgeonRequired;
	}

	public void setSurgeonRequired(String surgeonRequired) {
		this.surgeonRequired = surgeonRequired;
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

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

}