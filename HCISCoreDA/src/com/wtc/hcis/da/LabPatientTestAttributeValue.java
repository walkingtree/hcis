package com.wtc.hcis.da;

import java.util.Date;

/**
 * LabPatientTestAttributeValue entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabPatientTestAttributeValue implements java.io.Serializable {

	// Fields

	private LabPatientTestAttributeValueId id;
	private Integer version;
	private LabPatientTestDetail labPatientTestDetail;
	private LabTestAttribute labTestAttribute;
	private String attributeValue;
	private String remarks;
	private String comparisonInd;
	private String createdBy;
	private Date createdDtm;
	private String lastModifiedBy;
	private Date lastModifiedDtm;

	// Constructors

	/** default constructor */
	public LabPatientTestAttributeValue() {
	}

	/** minimal constructor */
	public LabPatientTestAttributeValue(LabPatientTestAttributeValueId id,
			LabPatientTestDetail labPatientTestDetail,
			LabTestAttribute labTestAttribute, String createdBy, Date createdDtm) {
		this.id = id;
		this.labPatientTestDetail = labPatientTestDetail;
		this.labTestAttribute = labTestAttribute;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public LabPatientTestAttributeValue(LabPatientTestAttributeValueId id,
			LabPatientTestDetail labPatientTestDetail,
			LabTestAttribute labTestAttribute, String attributeValue,
			String remarks, String comparisonInd, String createdBy,
			Date createdDtm, String lastModifiedBy, Date lastModifiedDtm) {
		this.id = id;
		this.labPatientTestDetail = labPatientTestDetail;
		this.labTestAttribute = labTestAttribute;
		this.attributeValue = attributeValue;
		this.remarks = remarks;
		this.comparisonInd = comparisonInd;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
	}

	// Property accessors

	public LabPatientTestAttributeValueId getId() {
		return this.id;
	}

	public void setId(LabPatientTestAttributeValueId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public LabPatientTestDetail getLabPatientTestDetail() {
		return this.labPatientTestDetail;
	}

	public void setLabPatientTestDetail(
			LabPatientTestDetail labPatientTestDetail) {
		this.labPatientTestDetail = labPatientTestDetail;
	}

	public LabTestAttribute getLabTestAttribute() {
		return this.labTestAttribute;
	}

	public void setLabTestAttribute(LabTestAttribute labTestAttribute) {
		this.labTestAttribute = labTestAttribute;
	}

	public String getAttributeValue() {
		return this.attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getComparisonInd() {
		return this.comparisonInd;
	}

	public void setComparisonInd(String comparisonInd) {
		this.comparisonInd = comparisonInd;
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

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

}