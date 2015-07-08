package com.wtc.hcis.da;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DocVitalField entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DocVitalField implements java.io.Serializable {

	// Fields

	private String code;
	private String name;
	private String measurementUnitCd;
	private String fieldType;
	private String fieldGroup;
	private String createdBy;
	private Date createdDtm;
	private Set docPatientVitalFieldsValues = new HashSet(0);

	// Constructors

	/** default constructor */
	public DocVitalField() {
	}

	/** minimal constructor */
	public DocVitalField(String code, String name, String measurementUnitCd,
			String fieldType, String fieldGroup, Date createdDtm) {
		this.code = code;
		this.name = name;
		this.measurementUnitCd = measurementUnitCd;
		this.fieldType = fieldType;
		this.fieldGroup = fieldGroup;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public DocVitalField(String code, String name, String measurementUnitCd,
			String fieldType, String fieldGroup, String createdBy,
			Date createdDtm, Set docPatientVitalFieldsValues) {
		this.code = code;
		this.name = name;
		this.measurementUnitCd = measurementUnitCd;
		this.fieldType = fieldType;
		this.fieldGroup = fieldGroup;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.docPatientVitalFieldsValues = docPatientVitalFieldsValues;
	}

	// Property accessors

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMeasurementUnitCd() {
		return this.measurementUnitCd;
	}

	public void setMeasurementUnitCd(String measurementUnitCd) {
		this.measurementUnitCd = measurementUnitCd;
	}

	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldGroup() {
		return this.fieldGroup;
	}

	public void setFieldGroup(String fieldGroup) {
		this.fieldGroup = fieldGroup;
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

	public Set getDocPatientVitalFieldsValues() {
		return this.docPatientVitalFieldsValues;
	}

	public void setDocPatientVitalFieldsValues(Set docPatientVitalFieldsValues) {
		this.docPatientVitalFieldsValues = docPatientVitalFieldsValues;
	}

}