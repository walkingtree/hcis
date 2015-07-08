package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LabCollectionPoint entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabCollectionPoint implements java.io.Serializable {

	// Fields

	private String collectionPointId;
	private Integer version;
	private ContactDetails contactDetails;
	private String name;
	private String areaCovered;
	private String createdBy;
	private Date createdDtm;
	private String modifiedBy;
	private Date modifiedDtm;
	private Set labPatientTestSpecimens = new HashSet(0);
	private Set labCollectionPointLabAssociations = new HashSet(0);

	// Constructors

	/** default constructor */
	public LabCollectionPoint() {
	}

	/** minimal constructor */
	public LabCollectionPoint(ContactDetails contactDetails, String name,
			String createdBy, Date createdDtm) {
		this.contactDetails = contactDetails;
		this.name = name;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public LabCollectionPoint(ContactDetails contactDetails, String name,
			String areaCovered, String createdBy, Date createdDtm,
			String modifiedBy, Date modifiedDtm, Set labPatientTestSpecimens,
			Set labCollectionPointLabAssociations) {
		this.contactDetails = contactDetails;
		this.name = name;
		this.areaCovered = areaCovered;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.modifiedBy = modifiedBy;
		this.modifiedDtm = modifiedDtm;
		this.labPatientTestSpecimens = labPatientTestSpecimens;
		this.labCollectionPointLabAssociations = labCollectionPointLabAssociations;
	}

	// Property accessors

	public String getCollectionPointId() {
		return this.collectionPointId;
	}

	public void setCollectionPointId(String collectionPointId) {
		this.collectionPointId = collectionPointId;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaCovered() {
		return this.areaCovered;
	}

	public void setAreaCovered(String areaCovered) {
		this.areaCovered = areaCovered;
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

	public Set getLabPatientTestSpecimens() {
		return this.labPatientTestSpecimens;
	}

	public void setLabPatientTestSpecimens(Set labPatientTestSpecimens) {
		this.labPatientTestSpecimens = labPatientTestSpecimens;
	}

	public Set getLabCollectionPointLabAssociations() {
		return this.labCollectionPointLabAssociations;
	}

	public void setLabCollectionPointLabAssociations(
			Set labCollectionPointLabAssociations) {
		this.labCollectionPointLabAssociations = labCollectionPointLabAssociations;
	}

}