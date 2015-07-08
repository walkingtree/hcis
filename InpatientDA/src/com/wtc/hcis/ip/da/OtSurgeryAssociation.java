package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * OtSurgeryAssociation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtSurgeryAssociation implements java.io.Serializable {

	// Fields

	private OtSurgeryAssociationId id;
	private OtSurgery otSurgery;
	private OtDetail otDetail;
	private String createdBy;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public OtSurgeryAssociation() {
	}

	/** full constructor */
	public OtSurgeryAssociation(OtSurgeryAssociationId id, OtSurgery otSurgery,
			OtDetail otDetail, String createdBy, Date createdDtm) {
		this.id = id;
		this.otSurgery = otSurgery;
		this.otDetail = otDetail;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public OtSurgeryAssociationId getId() {
		return this.id;
	}

	public void setId(OtSurgeryAssociationId id) {
		this.id = id;
	}

	public OtSurgery getOtSurgery() {
		return this.otSurgery;
	}

	public void setOtSurgery(OtSurgery otSurgery) {
		this.otSurgery = otSurgery;
	}

	public OtDetail getOtDetail() {
		return this.otDetail;
	}

	public void setOtDetail(OtDetail otDetail) {
		this.otDetail = otDetail;
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