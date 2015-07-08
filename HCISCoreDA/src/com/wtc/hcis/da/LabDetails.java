package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LabDetails entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabDetails implements java.io.Serializable {

	// Fields

	private String labId;
	private Integer version;
	private ContactDetails contactDetails;
	private Hospital hospital;
	private String labName;
	private String labType;
	private String businessName;
	private String branchName;
	private String directionFromKnownPlace;
	private String labOperatorId;
	private String createdBy;
	private Date createdDtm;
	private String modifiedBy;
	private Date modifiedDtm;
	private Set labCollectionPointLabAssociations = new HashSet(0);
	private Set labTests = new HashSet(0);

	// Constructors

	/** default constructor */
	public LabDetails() {
	}

	/** minimal constructor */
	public LabDetails(ContactDetails contactDetails, String labName,
			String labType, String createdBy, Date createdDtm) {
		this.contactDetails = contactDetails;
		this.labName = labName;
		this.labType = labType;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public LabDetails(ContactDetails contactDetails, Hospital hospital,
			String labName, String labType, String businessName,
			String branchName, String directionFromKnownPlace,
			String labOperatorId, String createdBy, Date createdDtm,
			String modifiedBy, Date modifiedDtm,
			Set labCollectionPointLabAssociations, Set labTests) {
		this.contactDetails = contactDetails;
		this.hospital = hospital;
		this.labName = labName;
		this.labType = labType;
		this.businessName = businessName;
		this.branchName = branchName;
		this.directionFromKnownPlace = directionFromKnownPlace;
		this.labOperatorId = labOperatorId;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.modifiedBy = modifiedBy;
		this.modifiedDtm = modifiedDtm;
		this.labCollectionPointLabAssociations = labCollectionPointLabAssociations;
		this.labTests = labTests;
	}

	// Property accessors

	public String getLabId() {
		return this.labId;
	}

	public void setLabId(String labId) {
		this.labId = labId;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ContactDetails getContactDetails() {
		return this.contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public Hospital getHospital() {
		return this.hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public String getLabName() {
		return this.labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getLabType() {
		return this.labType;
	}

	public void setLabType(String labType) {
		this.labType = labType;
	}

	public String getBusinessName() {
		return this.businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDirectionFromKnownPlace() {
		return this.directionFromKnownPlace;
	}

	public void setDirectionFromKnownPlace(String directionFromKnownPlace) {
		this.directionFromKnownPlace = directionFromKnownPlace;
	}

	public String getLabOperatorId() {
		return this.labOperatorId;
	}

	public void setLabOperatorId(String labOperatorId) {
		this.labOperatorId = labOperatorId;
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

	public Set getLabCollectionPointLabAssociations() {
		return this.labCollectionPointLabAssociations;
	}

	public void setLabCollectionPointLabAssociations(
			Set labCollectionPointLabAssociations) {
		this.labCollectionPointLabAssociations = labCollectionPointLabAssociations;
	}

	public Set getLabTests() {
		return this.labTests;
	}

	public void setLabTests(Set labTests) {
		this.labTests = labTests;
	}

}