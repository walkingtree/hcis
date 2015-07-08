package com.wtc.hcis.da;

import java.util.Date;

/**
 * DocVitalType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DocVitalType implements java.io.Serializable {

	// Fields

	private Integer vitalTypeId;
	private String name;
	private String createdBy;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public DocVitalType() {
	}

	/** full constructor */
	public DocVitalType(Integer vitalTypeId, String name, String createdBy,
			Date createdDtm) {
		this.vitalTypeId = vitalTypeId;
		this.name = name;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public Integer getVitalTypeId() {
		return this.vitalTypeId;
	}

	public void setVitalTypeId(Integer vitalTypeId) {
		this.vitalTypeId = vitalTypeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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