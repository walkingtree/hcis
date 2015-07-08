package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LabTechniqueReagent entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabTechniqueReagent implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer version;
	private String name;
	private String isTechnique;
	private String description;
	private String createdBy;
	private Date createdDtm;
	private Set labTests = new HashSet(0);
	private Set labPatientTestDetails = new HashSet(0);
	private Set labTestTechniqueTemplates = new HashSet(0);

	// Constructors

	/** default constructor */
	public LabTechniqueReagent() {
	}

	/** minimal constructor */
	public LabTechniqueReagent(String name, String isTechnique,
			String createdBy, Date createdDtm) {
		this.name = name;
		this.isTechnique = isTechnique;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public LabTechniqueReagent(String name, String isTechnique,
			String description, String createdBy, Date createdDtm,
			Set labTests, Set labPatientTestDetails,
			Set labTestTechniqueTemplates) {
		this.name = name;
		this.isTechnique = isTechnique;
		this.description = description;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.labTests = labTests;
		this.labPatientTestDetails = labPatientTestDetails;
		this.labTestTechniqueTemplates = labTestTechniqueTemplates;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsTechnique() {
		return this.isTechnique;
	}

	public void setIsTechnique(String isTechnique) {
		this.isTechnique = isTechnique;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Set getLabTests() {
		return this.labTests;
	}

	public void setLabTests(Set labTests) {
		this.labTests = labTests;
	}

	public Set getLabPatientTestDetails() {
		return this.labPatientTestDetails;
	}

	public void setLabPatientTestDetails(Set labPatientTestDetails) {
		this.labPatientTestDetails = labPatientTestDetails;
	}

	public Set getLabTestTechniqueTemplates() {
		return this.labTestTechniqueTemplates;
	}

	public void setLabTestTechniqueTemplates(Set labTestTechniqueTemplates) {
		this.labTestTechniqueTemplates = labTestTechniqueTemplates;
	}

}