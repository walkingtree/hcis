package com.wtc.hcis.da;
import java.util.Date;

/**
 * DocPatientVitalFieldsValue entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DocPatientVitalFieldsValue implements java.io.Serializable {

	// Fields

	private DocPatientVitalFieldsValueId id;
	private Integer version;
	private DocVitalField docVitalField;
	private DocPatientVital docPatientVital;
	private String value;
	private String remarks;
	private String modifiedBy;
	private Date lastModifiedDtm;
	private String createdBy;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public DocPatientVitalFieldsValue() {
	}

	/** minimal constructor */
	public DocPatientVitalFieldsValue(DocPatientVitalFieldsValueId id,
			DocVitalField docVitalField, DocPatientVital docPatientVital,
			String value, String remarks, String createdBy, Date createdDtm) {
		this.id = id;
		this.docVitalField = docVitalField;
		this.docPatientVital = docPatientVital;
		this.value = value;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public DocPatientVitalFieldsValue(DocPatientVitalFieldsValueId id,
			DocVitalField docVitalField, DocPatientVital docPatientVital,
			String value, String remarks, String modifiedBy,
			Date lastModifiedDtm, String createdBy, Date createdDtm) {
		this.id = id;
		this.docVitalField = docVitalField;
		this.docPatientVital = docPatientVital;
		this.value = value;
		this.remarks = remarks;
		this.modifiedBy = modifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public DocPatientVitalFieldsValueId getId() {
		return this.id;
	}

	public void setId(DocPatientVitalFieldsValueId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public DocVitalField getDocVitalField() {
		return this.docVitalField;
	}

	public void setDocVitalField(DocVitalField docVitalField) {
		this.docVitalField = docVitalField;
	}

	public DocPatientVital getDocPatientVital() {
		return this.docPatientVital;
	}

	public void setDocPatientVital(DocPatientVital docPatientVital) {
		this.docPatientVital = docPatientVital;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
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