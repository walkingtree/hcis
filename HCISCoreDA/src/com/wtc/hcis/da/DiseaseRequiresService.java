package com.wtc.hcis.da;

import java.util.Date;

/**
 * DiseaseRequiresService entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DiseaseRequiresService implements java.io.Serializable {

	// Fields

	private DiseaseRequiresServiceId id;
	private Integer version;
	private Disease disease;
	private String createdBy;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public DiseaseRequiresService() {
	}

	/** full constructor */
	public DiseaseRequiresService(DiseaseRequiresServiceId id, Disease disease,
			String createdBy, Date createdDtm) {
		this.id = id;
		this.disease = disease;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public DiseaseRequiresServiceId getId() {
		return this.id;
	}

	public void setId(DiseaseRequiresServiceId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Disease getDisease() {
		return this.disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
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

}