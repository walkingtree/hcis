package com.wtc.hcis.da;

import java.util.Date;

/**
 * RegistrationHistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class RegistrationHistory implements java.io.Serializable {

	// Fields

	private RegistrationHistoryId id;
	private Integer version;
	private Patient patient;
	private ChangeReason changeReason;
	private String registrationStatus;
	private Date createdDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;

	// Constructors

	/** default constructor */
	public RegistrationHistory() {
	}

	/** minimal constructor */
	public RegistrationHistory(RegistrationHistoryId id, Patient patient) {
		this.id = id;
		this.patient = patient;
	}

	/** full constructor */
	public RegistrationHistory(RegistrationHistoryId id, Patient patient,
			ChangeReason changeReason, String registrationStatus,
			Date createdDtm, String createdBy, Date lastModifiedDtm,
			String modifiedBy) {
		this.id = id;
		this.patient = patient;
		this.changeReason = changeReason;
		this.registrationStatus = registrationStatus;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
	}

	// Property accessors

	public RegistrationHistoryId getId() {
		return this.id;
	}

	public void setId(RegistrationHistoryId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ChangeReason getChangeReason() {
		return this.changeReason;
	}

	public void setChangeReason(ChangeReason changeReason) {
		this.changeReason = changeReason;
	}

	public String getRegistrationStatus() {
		return this.registrationStatus;
	}

	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
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

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}