package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LabTest entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabTest implements java.io.Serializable {

	// Fields

	private String testCode;
	private Integer version;
	private Service service;
	private LabTestTemplate labTestTemplate;
	private LabDetails labDetails;
	private LabTechniqueReagent labTechniqueReagent;
	private String testName;
	private String testType;
	private String availableForGender;
	private String preRequisite;
	private String minTimeRequired;
	private String reviewRequired;
	private String defaultReviewerId;
	private String createdBy;
	private Date createdDtm;
	private String modifiedBy;
	private Date modifiedDtm;
	private Set labTestTechniqueTemplatesForTechniqueId = new HashSet(0);
	private Set labTestAttributeAssociations = new HashSet(0);
	private Set labTestSpecimenAssociations = new HashSet(0);
	private Set labPatientTestDetails = new HashSet(0);
	private Set labTestTechniqueTemplatesForTestCode = new HashSet(0);

	// Constructors

	/** default constructor */
	public LabTest() {
	}

	/** minimal constructor */
	public LabTest(Service service, LabDetails labDetails, String testName,
			String testType, String availableForGender, String minTimeRequired,
			String reviewRequired, String createdBy, Date createdDtm) {
		this.service = service;
		this.labDetails = labDetails;
		this.testName = testName;
		this.testType = testType;
		this.availableForGender = availableForGender;
		this.minTimeRequired = minTimeRequired;
		this.reviewRequired = reviewRequired;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public LabTest(Service service, LabTestTemplate labTestTemplate,
			LabDetails labDetails, LabTechniqueReagent labTechniqueReagent,
			String testName, String testType, String availableForGender,
			String preRequisite, String minTimeRequired, String reviewRequired,
			String defaultReviewerId, String createdBy, Date createdDtm,
			String modifiedBy, Date modifiedDtm,
			Set labTestTechniqueTemplatesForTechniqueId,
			Set labTestAttributeAssociations, Set labTestSpecimenAssociations,
			Set labPatientTestDetails, Set labTestTechniqueTemplatesForTestCode) {
		this.service = service;
		this.labTestTemplate = labTestTemplate;
		this.labDetails = labDetails;
		this.labTechniqueReagent = labTechniqueReagent;
		this.testName = testName;
		this.testType = testType;
		this.availableForGender = availableForGender;
		this.preRequisite = preRequisite;
		this.minTimeRequired = minTimeRequired;
		this.reviewRequired = reviewRequired;
		this.defaultReviewerId = defaultReviewerId;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.modifiedBy = modifiedBy;
		this.modifiedDtm = modifiedDtm;
		this.labTestTechniqueTemplatesForTechniqueId = labTestTechniqueTemplatesForTechniqueId;
		this.labTestAttributeAssociations = labTestAttributeAssociations;
		this.labTestSpecimenAssociations = labTestSpecimenAssociations;
		this.labPatientTestDetails = labPatientTestDetails;
		this.labTestTechniqueTemplatesForTestCode = labTestTechniqueTemplatesForTestCode;
	}

	// Property accessors

	public String getTestCode() {
		return this.testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public LabTestTemplate getLabTestTemplate() {
		return this.labTestTemplate;
	}

	public void setLabTestTemplate(LabTestTemplate labTestTemplate) {
		this.labTestTemplate = labTestTemplate;
	}

	public LabDetails getLabDetails() {
		return this.labDetails;
	}

	public void setLabDetails(LabDetails labDetails) {
		this.labDetails = labDetails;
	}

	public LabTechniqueReagent getLabTechniqueReagent() {
		return this.labTechniqueReagent;
	}

	public void setLabTechniqueReagent(LabTechniqueReagent labTechniqueReagent) {
		this.labTechniqueReagent = labTechniqueReagent;
	}

	public String getTestName() {
		return this.testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestType() {
		return this.testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public String getAvailableForGender() {
		return this.availableForGender;
	}

	public void setAvailableForGender(String availableForGender) {
		this.availableForGender = availableForGender;
	}

	public String getPreRequisite() {
		return this.preRequisite;
	}

	public void setPreRequisite(String preRequisite) {
		this.preRequisite = preRequisite;
	}

	public String getMinTimeRequired() {
		return this.minTimeRequired;
	}

	public void setMinTimeRequired(String minTimeRequired) {
		this.minTimeRequired = minTimeRequired;
	}

	public String getReviewRequired() {
		return this.reviewRequired;
	}

	public void setReviewRequired(String reviewRequired) {
		this.reviewRequired = reviewRequired;
	}

	public String getDefaultReviewerId() {
		return this.defaultReviewerId;
	}

	public void setDefaultReviewerId(String defaultReviewerId) {
		this.defaultReviewerId = defaultReviewerId;
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

	public Set getLabTestTechniqueTemplatesForTechniqueId() {
		return this.labTestTechniqueTemplatesForTechniqueId;
	}

	public void setLabTestTechniqueTemplatesForTechniqueId(
			Set labTestTechniqueTemplatesForTechniqueId) {
		this.labTestTechniqueTemplatesForTechniqueId = labTestTechniqueTemplatesForTechniqueId;
	}

	public Set getLabTestAttributeAssociations() {
		return this.labTestAttributeAssociations;
	}

	public void setLabTestAttributeAssociations(Set labTestAttributeAssociations) {
		this.labTestAttributeAssociations = labTestAttributeAssociations;
	}

	public Set getLabTestSpecimenAssociations() {
		return this.labTestSpecimenAssociations;
	}

	public void setLabTestSpecimenAssociations(Set labTestSpecimenAssociations) {
		this.labTestSpecimenAssociations = labTestSpecimenAssociations;
	}

	public Set getLabPatientTestDetails() {
		return this.labPatientTestDetails;
	}

	public void setLabPatientTestDetails(Set labPatientTestDetails) {
		this.labPatientTestDetails = labPatientTestDetails;
	}

	public Set getLabTestTechniqueTemplatesForTestCode() {
		return this.labTestTechniqueTemplatesForTestCode;
	}

	public void setLabTestTechniqueTemplatesForTestCode(
			Set labTestTechniqueTemplatesForTestCode) {
		this.labTestTechniqueTemplatesForTestCode = labTestTechniqueTemplatesForTestCode;
	}

}