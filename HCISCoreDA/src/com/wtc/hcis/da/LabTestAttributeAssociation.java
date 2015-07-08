package com.wtc.hcis.da;

import java.util.Date;

/**
 * LabTestAttributeAssociation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabTestAttributeAssociation implements java.io.Serializable {

	// Fields

	private LabTestAttributeAssociationId id;
	private Integer version;
	private LabTestAttribute labTestAttribute;
	private LabTest labTest;
	private Double minValue;
	private Double maxValue;
	private String isMandatory;
	private String createdBy;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public LabTestAttributeAssociation() {
	}

	/** minimal constructor */
	public LabTestAttributeAssociation(LabTestAttributeAssociationId id,
			LabTestAttribute labTestAttribute, LabTest labTest,
			String createdBy, Date createdDtm) {
		this.id = id;
		this.labTestAttribute = labTestAttribute;
		this.labTest = labTest;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public LabTestAttributeAssociation(LabTestAttributeAssociationId id,
			LabTestAttribute labTestAttribute, LabTest labTest,
			Double minValue, Double maxValue, String isMandatory,
			String createdBy, Date createdDtm) {
		this.id = id;
		this.labTestAttribute = labTestAttribute;
		this.labTest = labTest;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.isMandatory = isMandatory;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public LabTestAttributeAssociationId getId() {
		return this.id;
	}

	public void setId(LabTestAttributeAssociationId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public LabTestAttribute getLabTestAttribute() {
		return this.labTestAttribute;
	}

	public void setLabTestAttribute(LabTestAttribute labTestAttribute) {
		this.labTestAttribute = labTestAttribute;
	}

	public LabTest getLabTest() {
		return this.labTest;
	}

	public void setLabTest(LabTest labTest) {
		this.labTest = labTest;
	}

	public Double getMinValue() {
		return this.minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return this.maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public String getIsMandatory() {
		return this.isMandatory;
	}

	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
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