package com.wtc.hcis.da;

import java.util.Date;

/**
 * ReferenceDataList entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ReferenceDataList implements java.io.Serializable {

	// Fields

	private ReferenceDataListId id;
	private Integer version;
	private String attrDesc;
	private String isDefault;
	private Integer seqNbr;
	private String createdBy;
	private Date createDtm;
	private String modifiedBy;
	private Date modifiedDtm;

	// Constructors

	/** default constructor */
	public ReferenceDataList() {
	}

	/** minimal constructor */
	public ReferenceDataList(ReferenceDataListId id, String createdBy,
			Date createDtm) {
		this.id = id;
		this.createdBy = createdBy;
		this.createDtm = createDtm;
	}

	/** full constructor */
	public ReferenceDataList(ReferenceDataListId id, String attrDesc,
			Integer seqNbr, String createdBy, Date createDtm,
			String modifiedBy, Date modifiedDtm) {
		this.id = id;
		this.attrDesc = attrDesc;
		this.seqNbr = seqNbr;
		this.createdBy = createdBy;
		this.createDtm = createDtm;
		this.modifiedBy = modifiedBy;
		this.modifiedDtm = modifiedDtm;
	}

	// Property accessors

	public ReferenceDataListId getId() {
		return this.id;
	}

	public void setId(ReferenceDataListId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getAttrDesc() {
		return this.attrDesc;
	}

	public void setAttrDesc(String attrDesc) {
		this.attrDesc = attrDesc;
	}

	public Integer getSeqNbr() {
		return this.seqNbr;
	}

	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDtm() {
		return this.createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
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

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

}