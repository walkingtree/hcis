package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Disease entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Disease implements java.io.Serializable {

	// Fields

	private String diseaseName;
	private Integer version;
	private String description;
	private String diseaseGroup;
	private String createdBy;
	private Date createdDtm;
	private String modifiedBy;
	private Date lastModifiedDtm;
	private Set diseaseRequiresServices = new HashSet(0);

	// Constructors

	/** default constructor */
	public Disease() {
	}

	/** minimal constructor */
	public Disease(String createdBy, Date createdDtm) {
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public Disease(String description, String diseaseGroup, String createdBy,
			Date createdDtm, String modifiedBy, Date lastModifiedDtm,
			Set diseaseRequiresServices) {
		this.description = description;
		this.diseaseGroup = diseaseGroup;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.modifiedBy = modifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.diseaseRequiresServices = diseaseRequiresServices;
	}

	// Property accessors

	public String getDiseaseName() {
		return this.diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDiseaseGroup() {
		return this.diseaseGroup;
	}

	public void setDiseaseGroup(String diseaseGroup) {
		this.diseaseGroup = diseaseGroup;
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

	public Set getDiseaseRequiresServices() {
		return this.diseaseRequiresServices;
	}

	public void setDiseaseRequiresServices(Set diseaseRequiresServices) {
		this.diseaseRequiresServices = diseaseRequiresServices;
	}

}