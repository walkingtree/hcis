package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * OtNotesConfiguration entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtNotesConfiguration implements java.io.Serializable {

	// Fields

	private String fieldCode;
	private String fieldName;
	private Integer fieldType;
	private String description;
	private Integer seqNbr;
	private String active;
	private String createdBy;
	private Date createdDtm;
	private Set otPatientSurgeryNoteses = new HashSet(0);

	// Constructors

	/** default constructor */
	public OtNotesConfiguration() {
	}

	/** minimal constructor */
	public OtNotesConfiguration(String fieldCode, String fieldName,
			Integer fieldType, Integer seqNbr, String active, String createdBy,
			Date createdDtm) {
		this.fieldCode = fieldCode;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.seqNbr = seqNbr;
		this.active = active;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public OtNotesConfiguration(String fieldCode, String fieldName,
			Integer fieldType, String description, Integer seqNbr,
			String active, String createdBy, Date createdDtm,
			Set otPatientSurgeryNoteses) {
		this.fieldCode = fieldCode;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.description = description;
		this.seqNbr = seqNbr;
		this.active = active;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.otPatientSurgeryNoteses = otPatientSurgeryNoteses;
	}

	// Property accessors

	public String getFieldCode() {
		return this.fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Integer getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSeqNbr() {
		return this.seqNbr;
	}

	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
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

	public Set getOtPatientSurgeryNoteses() {
		return this.otPatientSurgeryNoteses;
	}

	public void setOtPatientSurgeryNoteses(Set otPatientSurgeryNoteses) {
		this.otPatientSurgeryNoteses = otPatientSurgeryNoteses;
	}

}