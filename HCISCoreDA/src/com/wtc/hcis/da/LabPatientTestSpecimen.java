package com.wtc.hcis.da;

import java.util.Date;

/**
 * LabPatientTestSpecimen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabPatientTestSpecimen implements java.io.Serializable {

	// Fields

	private LabPatientTestSpecimenId id;
	private Integer version;
	private LabCollectionPoint labCollectionPoint;
	private LabPatientTestDetail labPatientTestDetail;
	private LabSpecimen labSpecimen;
	private Double quantity;
	private String unit;
	private Date collectionDtm;
	private String createdBy;
	private Date createdDtm;
	private String modifiedBy;
	private Date modifiedDtm;

	// Constructors

	/** default constructor */
	public LabPatientTestSpecimen() {
	}

	/** minimal constructor */
	public LabPatientTestSpecimen(LabPatientTestSpecimenId id,
			LabCollectionPoint labCollectionPoint,
			LabPatientTestDetail labPatientTestDetail, LabSpecimen labSpecimen,
			String createdBy, Date createdDtm) {
		this.id = id;
		this.labCollectionPoint = labCollectionPoint;
		this.labPatientTestDetail = labPatientTestDetail;
		this.labSpecimen = labSpecimen;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public LabPatientTestSpecimen(LabPatientTestSpecimenId id,
			LabCollectionPoint labCollectionPoint,
			LabPatientTestDetail labPatientTestDetail, LabSpecimen labSpecimen,
			Double quantity, String unit, Date collectionDtm, String createdBy,
			Date createdDtm, String modifiedBy, Date modifiedDtm) {
		this.id = id;
		this.labCollectionPoint = labCollectionPoint;
		this.labPatientTestDetail = labPatientTestDetail;
		this.labSpecimen = labSpecimen;
		this.quantity = quantity;
		this.unit = unit;
		this.collectionDtm = collectionDtm;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.modifiedBy = modifiedBy;
		this.modifiedDtm = modifiedDtm;
	}

	// Property accessors

	public LabPatientTestSpecimenId getId() {
		return this.id;
	}

	public void setId(LabPatientTestSpecimenId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public LabCollectionPoint getLabCollectionPoint() {
		return this.labCollectionPoint;
	}

	public void setLabCollectionPoint(LabCollectionPoint labCollectionPoint) {
		this.labCollectionPoint = labCollectionPoint;
	}

	public LabPatientTestDetail getLabPatientTestDetail() {
		return this.labPatientTestDetail;
	}

	public void setLabPatientTestDetail(
			LabPatientTestDetail labPatientTestDetail) {
		this.labPatientTestDetail = labPatientTestDetail;
	}

	public LabSpecimen getLabSpecimen() {
		return this.labSpecimen;
	}

	public void setLabSpecimen(LabSpecimen labSpecimen) {
		this.labSpecimen = labSpecimen;
	}

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getCollectionDtm() {
		return this.collectionDtm;
	}

	public void setCollectionDtm(Date collectionDtm) {
		this.collectionDtm = collectionDtm;
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

}