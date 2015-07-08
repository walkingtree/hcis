package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LabPatientTestDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabPatientTestDetail implements java.io.Serializable {

	// Fields

	private String patientTestId;
	private Integer version;
	private LabTest labTest;
	private LabTechniqueReagent labTechniqueReagent;
	private Patient patient;
	private Doctor doctor;
	private Date testPerformDtm;
	private String statusCode;
	private Date approveDate;
	private String approverName;
	private String investigatorId;
	private Date specimenCollectionDtm;
	private String remarks;
	private Integer createdDateDim;
	private String createdBy;
	private Date createdDtm;
	private String modifiedBy;
	private Date modifiedDtm;
	private String reportCollectedBy;
	private Date reportCollectedDtm;
	private Set labPatientTestChangeHistories = new HashSet(0);
	private Set labPatientTestSpecimens = new HashSet(0);
	private Set labPatientTestAttributeValues = new HashSet(0);

	// Constructors

	/** default constructor */
	public LabPatientTestDetail() {
	}

	/** minimal constructor */
	public LabPatientTestDetail(LabTest labTest, Patient patient,
			Doctor doctor, Date testPerformDtm, String statusCode,
			Integer createdDateDim, String createdBy, Date createdDtm,
			String reportCollectedBy, Date reportCollectedDtm) {
		this.labTest = labTest;
		this.patient = patient;
		this.doctor = doctor;
		this.testPerformDtm = testPerformDtm;
		this.statusCode = statusCode;
		this.createdDateDim = createdDateDim;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.reportCollectedBy = reportCollectedBy;
		this.reportCollectedDtm = reportCollectedDtm;
	}

	/** full constructor */
	public LabPatientTestDetail(LabTest labTest,
			LabTechniqueReagent labTechniqueReagent, Patient patient,
			Doctor doctor, Date testPerformDtm, String statusCode,
			Date approveDate, String approverName, String investigatorId,
			Date specimenCollectionDtm, String remarks, Integer createdDateDim,
			String createdBy, Date createdDtm, String modifiedBy,
			Date modifiedDtm, String reportCollectedBy,
			Date reportCollectedDtm, Set labPatientTestChangeHistories,
			Set labPatientTestSpecimens, Set labPatientTestAttributeValues) {
		this.labTest = labTest;
		this.labTechniqueReagent = labTechniqueReagent;
		this.patient = patient;
		this.doctor = doctor;
		this.testPerformDtm = testPerformDtm;
		this.statusCode = statusCode;
		this.approveDate = approveDate;
		this.approverName = approverName;
		this.investigatorId = investigatorId;
		this.specimenCollectionDtm = specimenCollectionDtm;
		this.remarks = remarks;
		this.createdDateDim = createdDateDim;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.modifiedBy = modifiedBy;
		this.modifiedDtm = modifiedDtm;
		this.reportCollectedBy = reportCollectedBy;
		this.reportCollectedDtm = reportCollectedDtm;
		this.labPatientTestChangeHistories = labPatientTestChangeHistories;
		this.labPatientTestSpecimens = labPatientTestSpecimens;
		this.labPatientTestAttributeValues = labPatientTestAttributeValues;
	}

	// Property accessors

	public String getPatientTestId() {
		return this.patientTestId;
	}

	public void setPatientTestId(String patientTestId) {
		this.patientTestId = patientTestId;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public LabTest getLabTest() {
		return this.labTest;
	}

	public void setLabTest(LabTest labTest) {
		this.labTest = labTest;
	}

	public LabTechniqueReagent getLabTechniqueReagent() {
		return this.labTechniqueReagent;
	}

	public void setLabTechniqueReagent(LabTechniqueReagent labTechniqueReagent) {
		this.labTechniqueReagent = labTechniqueReagent;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Date getTestPerformDtm() {
		return this.testPerformDtm;
	}

	public void setTestPerformDtm(Date testPerformDtm) {
		this.testPerformDtm = testPerformDtm;
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Date getApproveDate() {
		return this.approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getApproverName() {
		return this.approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getInvestigatorId() {
		return this.investigatorId;
	}

	public void setInvestigatorId(String investigatorId) {
		this.investigatorId = investigatorId;
	}

	public Date getSpecimenCollectionDtm() {
		return this.specimenCollectionDtm;
	}

	public void setSpecimenCollectionDtm(Date specimenCollectionDtm) {
		this.specimenCollectionDtm = specimenCollectionDtm;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getCreatedDateDim() {
		return this.createdDateDim;
	}

	public void setCreatedDateDim(Integer createdDateDim) {
		this.createdDateDim = createdDateDim;
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

	public Date getModifiedDtm() {
		return this.modifiedDtm;
	}

	public void setModifiedDtm(Date modifiedDtm) {
		this.modifiedDtm = modifiedDtm;
	}

	public String getReportCollectedBy() {
		return this.reportCollectedBy;
	}

	public void setReportCollectedBy(String reportCollectedBy) {
		this.reportCollectedBy = reportCollectedBy;
	}

	public Date getReportCollectedDtm() {
		return this.reportCollectedDtm;
	}

	public void setReportCollectedDtm(Date reportCollectedDtm) {
		this.reportCollectedDtm = reportCollectedDtm;
	}

	public Set getLabPatientTestChangeHistories() {
		return this.labPatientTestChangeHistories;
	}

	public void setLabPatientTestChangeHistories(
			Set labPatientTestChangeHistories) {
		this.labPatientTestChangeHistories = labPatientTestChangeHistories;
	}

	public Set getLabPatientTestSpecimens() {
		return this.labPatientTestSpecimens;
	}

	public void setLabPatientTestSpecimens(Set labPatientTestSpecimens) {
		this.labPatientTestSpecimens = labPatientTestSpecimens;
	}

	public Set getLabPatientTestAttributeValues() {
		return this.labPatientTestAttributeValues;
	}

	public void setLabPatientTestAttributeValues(
			Set labPatientTestAttributeValues) {
		this.labPatientTestAttributeValues = labPatientTestAttributeValues;
	}

}