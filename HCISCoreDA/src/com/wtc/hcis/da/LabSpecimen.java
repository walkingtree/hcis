package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LabSpecimen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabSpecimen implements java.io.Serializable {

	// Fields

	private Integer specimenId;
	private String specimenName;
	private String description;
	private String containerType;
	private String createdBy;
	private Date creationDtm;
	private Set labTestSpecimenAssociations = new HashSet(0);
	private Set labPatientTestSpecimens = new HashSet(0);

	// Constructors

	/** default constructor */
	public LabSpecimen() {
	}

	/** minimal constructor */
	public LabSpecimen(String specimenName, String createdBy, Date creationDtm) {
		this.specimenName = specimenName;
		this.createdBy = createdBy;
		this.creationDtm = creationDtm;
	}

	/** full constructor */
	public LabSpecimen(String specimenName, String description,
			String containerType, String createdBy, Date creationDtm,
			Set labTestSpecimenAssociations, Set labPatientTestSpecimens) {
		this.specimenName = specimenName;
		this.description = description;
		this.containerType = containerType;
		this.createdBy = createdBy;
		this.creationDtm = creationDtm;
		this.labTestSpecimenAssociations = labTestSpecimenAssociations;
		this.labPatientTestSpecimens = labPatientTestSpecimens;
	}

	// Property accessors

	public Integer getSpecimenId() {
		return this.specimenId;
	}

	public void setSpecimenId(Integer specimenId) {
		this.specimenId = specimenId;
	}

	public String getSpecimenName() {
		return this.specimenName;
	}

	public void setSpecimenName(String specimenName) {
		this.specimenName = specimenName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContainerType() {
		return this.containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDtm() {
		return this.creationDtm;
	}

	public void setCreationDtm(Date creationDtm) {
		this.creationDtm = creationDtm;
	}

	public Set getLabTestSpecimenAssociations() {
		return this.labTestSpecimenAssociations;
	}

	public void setLabTestSpecimenAssociations(Set labTestSpecimenAssociations) {
		this.labTestSpecimenAssociations = labTestSpecimenAssociations;
	}

	public Set getLabPatientTestSpecimens() {
		return this.labPatientTestSpecimens;
	}

	public void setLabPatientTestSpecimens(Set labPatientTestSpecimens) {
		this.labPatientTestSpecimens = labPatientTestSpecimens;
	}

}