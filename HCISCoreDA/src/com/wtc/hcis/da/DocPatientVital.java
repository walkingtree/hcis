package com.wtc.hcis.da;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DocPatientVital entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DocPatientVital implements java.io.Serializable {

	// Fields

	private Integer patientVitalId;
	private Integer version;
	private Integer patientId;
	private String referenceType;
	private String referenceNumber;
	private Date completionDtm;
	private String createdBy;
	private Date createdDtm;
	private Set docPatientVitalFieldsValues = new HashSet(0);

	// Constructors

	/** default constructor */
	public DocPatientVital() {
	}

	/** minimal constructor */
	public DocPatientVital(Integer patientVitalId, Integer patientId,
			String referenceType, String referenceNumber, String createdBy,
			Date createdDtm) {
		this.patientVitalId = patientVitalId;
		this.patientId = patientId;
		this.referenceType = referenceType;
		this.referenceNumber = referenceNumber;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public DocPatientVital(Integer patientVitalId, Integer patientId,
			String referenceType, String referenceNumber, Date completionDtm,
			String createdBy, Date createdDtm, Set docPatientVitalFieldsValues) {
		this.patientVitalId = patientVitalId;
		this.patientId = patientId;
		this.referenceType = referenceType;
		this.referenceNumber = referenceNumber;
		this.completionDtm = completionDtm;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.docPatientVitalFieldsValues = docPatientVitalFieldsValues;
	}

	// Property accessors

	public Integer getPatientVitalId() {
		return this.patientVitalId;
	}

	public void setPatientVitalId(Integer patientVitalId) {
		this.patientVitalId = patientVitalId;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getReferenceType() {
		return this.referenceType;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Date getCompletionDtm() {
		return this.completionDtm;
	}

	public void setCompletionDtm(Date completionDtm) {
		this.completionDtm = completionDtm;
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

	public Set getDocPatientVitalFieldsValues() {
		return this.docPatientVitalFieldsValues;
	}

	public void setDocPatientVitalFieldsValues(Set docPatientVitalFieldsValues) {
		this.docPatientVitalFieldsValues = docPatientVitalFieldsValues;
	}

}