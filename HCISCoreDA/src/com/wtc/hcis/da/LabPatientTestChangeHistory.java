package com.wtc.hcis.da;

/**
 * LabPatientTestChangeHistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabPatientTestChangeHistory implements java.io.Serializable {

	// Fields

	private LabPatientTestChangeHistoryId id;
	private Integer version;
	private LabPatientTestDetail labPatientTestDetail;
	private LabTestAttribute labTestAttribute;
	private String attributeValue;
	private String createdBy;

	// Constructors

	/** default constructor */
	public LabPatientTestChangeHistory() {
	}

	/** minimal constructor */
	public LabPatientTestChangeHistory(LabPatientTestChangeHistoryId id,
			LabPatientTestDetail labPatientTestDetail,
			LabTestAttribute labTestAttribute, String createdBy) {
		this.id = id;
		this.labPatientTestDetail = labPatientTestDetail;
		this.labTestAttribute = labTestAttribute;
		this.createdBy = createdBy;
	}

	/** full constructor */
	public LabPatientTestChangeHistory(LabPatientTestChangeHistoryId id,
			LabPatientTestDetail labPatientTestDetail,
			LabTestAttribute labTestAttribute, String attributeValue,
			String createdBy) {
		this.id = id;
		this.labPatientTestDetail = labPatientTestDetail;
		this.labTestAttribute = labTestAttribute;
		this.attributeValue = attributeValue;
		this.createdBy = createdBy;
	}

	// Property accessors

	public LabPatientTestChangeHistoryId getId() {
		return this.id;
	}

	public void setId(LabPatientTestChangeHistoryId id) {
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

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}