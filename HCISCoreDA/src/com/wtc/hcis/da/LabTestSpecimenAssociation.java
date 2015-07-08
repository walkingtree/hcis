package com.wtc.hcis.da;

import java.util.Date;

/**
 * LabTestSpecimenAssociation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabTestSpecimenAssociation implements java.io.Serializable {

	// Fields

	private LabTestSpecimenAssociationId id;
	private Integer version;
	private LabTest labTest;
	private LabSpecimen labSpecimen;
	private Integer quantity;
	private String unit;
	private String isMandatory;
	private String createdBy;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public LabTestSpecimenAssociation() {
	}

	/** minimal constructor */
	public LabTestSpecimenAssociation(LabTestSpecimenAssociationId id,
			LabTest labTest, LabSpecimen labSpecimen, String isMandatory,
			String createdBy, Date createdDtm) {
		this.id = id;
		this.labTest = labTest;
		this.labSpecimen = labSpecimen;
		this.isMandatory = isMandatory;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public LabTestSpecimenAssociation(LabTestSpecimenAssociationId id,
			LabTest labTest, LabSpecimen labSpecimen, Integer quantity,
			String unit, String isMandatory, String createdBy, Date createdDtm) {
		this.id = id;
		this.labTest = labTest;
		this.labSpecimen = labSpecimen;
		this.quantity = quantity;
		this.unit = unit;
		this.isMandatory = isMandatory;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public LabTestSpecimenAssociationId getId() {
		return this.id;
	}

	public void setId(LabTestSpecimenAssociationId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public LabTest getLabTest() {
		return this.labTest;
	}

	public void setLabTest(LabTest labTest) {
		this.labTest = labTest;
	}

	public LabSpecimen getLabSpecimen() {
		return this.labSpecimen;
	}

	public void setLabSpecimen(LabSpecimen labSpecimen) {
		this.labSpecimen = labSpecimen;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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