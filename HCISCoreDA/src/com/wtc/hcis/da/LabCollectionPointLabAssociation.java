package com.wtc.hcis.da;

import java.util.Date;

/**
 * LabCollectionPointLabAssociation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabCollectionPointLabAssociation implements java.io.Serializable {

	// Fields

	private LabCollectionPointLabAssociationId id;
	private Integer version;
	private LabCollectionPoint labCollectionPoint;
	private LabDetails labDetails;
	private Date effectiveFrom;
	private Date effectiveTo;
	private String createdBy;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public LabCollectionPointLabAssociation() {
	}

	/** minimal constructor */
	public LabCollectionPointLabAssociation(
			LabCollectionPointLabAssociationId id,
			LabCollectionPoint labCollectionPoint, LabDetails labDetails,
			String createdBy, Date createdDtm) {
		this.id = id;
		this.labCollectionPoint = labCollectionPoint;
		this.labDetails = labDetails;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public LabCollectionPointLabAssociation(
			LabCollectionPointLabAssociationId id,
			LabCollectionPoint labCollectionPoint, LabDetails labDetails,
			Date effectiveFrom, Date effectiveTo, String createdBy,
			Date createdDtm) {
		this.id = id;
		this.labCollectionPoint = labCollectionPoint;
		this.labDetails = labDetails;
		this.effectiveFrom = effectiveFrom;
		this.effectiveTo = effectiveTo;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public LabCollectionPointLabAssociationId getId() {
		return this.id;
	}

	public void setId(LabCollectionPointLabAssociationId id) {
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

	public LabDetails getLabDetails() {
		return this.labDetails;
	}

	public void setLabDetails(LabDetails labDetails) {
		this.labDetails = labDetails;
	}

	public Date getEffectiveFrom() {
		return this.effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getEffectiveTo() {
		return this.effectiveTo;
	}

	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
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