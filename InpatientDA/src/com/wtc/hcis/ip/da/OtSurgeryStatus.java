package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * OtSurgeryStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtSurgeryStatus implements java.io.Serializable {

	// Fields

	private String statusCode;
	private String description;
	private Integer seqNo;
	private String captureTime;
	private String contributeToScheduling;
	private String createdBy;
	private Date createdDtm;
	private Set otStatusChecklistAssos = new HashSet(0);
	private Set otSurgeryStatusTimes = new HashSet(0);
	private Set otPatientSurgeryActivities = new HashSet(0);

	// Constructors

	/** default constructor */
	public OtSurgeryStatus() {
	}

	/** minimal constructor */
	public OtSurgeryStatus(String statusCode, String description,
			Integer seqNo, String captureTime, String contributeToScheduling,
			String createdBy, Date createdDtm) {
		this.statusCode = statusCode;
		this.description = description;
		this.seqNo = seqNo;
		this.captureTime = captureTime;
		this.contributeToScheduling = contributeToScheduling;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public OtSurgeryStatus(String statusCode, String description,
			Integer seqNo, String captureTime, String contributeToScheduling,
			String createdBy, Date createdDtm, Set otStatusChecklistAssos,
			Set otSurgeryStatusTimes, Set otPatientSurgeryActivities) {
		this.statusCode = statusCode;
		this.description = description;
		this.seqNo = seqNo;
		this.captureTime = captureTime;
		this.contributeToScheduling = contributeToScheduling;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.otStatusChecklistAssos = otStatusChecklistAssos;
		this.otSurgeryStatusTimes = otSurgeryStatusTimes;
		this.otPatientSurgeryActivities = otPatientSurgeryActivities;
	}

	// Property accessors

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getCaptureTime() {
		return this.captureTime;
	}

	public void setCaptureTime(String captureTime) {
		this.captureTime = captureTime;
	}

	public String getContributeToScheduling() {
		return this.contributeToScheduling;
	}

	public void setContributeToScheduling(String contributeToScheduling) {
		this.contributeToScheduling = contributeToScheduling;
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

	public Set getOtStatusChecklistAssos() {
		return this.otStatusChecklistAssos;
	}

	public void setOtStatusChecklistAssos(Set otStatusChecklistAssos) {
		this.otStatusChecklistAssos = otStatusChecklistAssos;
	}

	public Set getOtSurgeryStatusTimes() {
		return this.otSurgeryStatusTimes;
	}

	public void setOtSurgeryStatusTimes(Set otSurgeryStatusTimes) {
		this.otSurgeryStatusTimes = otSurgeryStatusTimes;
	}

	public Set getOtPatientSurgeryActivities() {
		return this.otPatientSurgeryActivities;
	}

	public void setOtPatientSurgeryActivities(Set otPatientSurgeryActivities) {
		this.otPatientSurgeryActivities = otPatientSurgeryActivities;
	}

}