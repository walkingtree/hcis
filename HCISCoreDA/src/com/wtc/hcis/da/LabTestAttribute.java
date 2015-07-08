package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LabTestAttribute entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabTestAttribute implements java.io.Serializable {

	// Fields

	private String attributeCode;
	private String attributeName;
	private String type;
	private Double minValue;
	private Double maxValue;
	private String unit;
	private String observationValue;
	private String createdBy;
	private Date createdDate;
	private Set labPatientTestChangeHistories = new HashSet(0);
	private Set labTemplateWidgets = new HashSet(0);
	private Set labPatientTestAttributeValues = new HashSet(0);
	private Set labTestAttributeAssociations = new HashSet(0);

	// Constructors

	/** default constructor */
	public LabTestAttribute() {
	}

	/** minimal constructor */
	public LabTestAttribute(String attributeName, String type,
			String createdBy, Date createdDate) {
		this.attributeName = attributeName;
		this.type = type;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	/** full constructor */
	public LabTestAttribute(String attributeName, String type, Double minValue,
			Double maxValue, String unit, String observationValue,
			String createdBy, Date createdDate,
			Set labPatientTestChangeHistories, Set labTemplateWidgets,
			Set labPatientTestAttributeValues, Set labTestAttributeAssociations) {
		this.attributeName = attributeName;
		this.type = type;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.unit = unit;
		this.observationValue = observationValue;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.labPatientTestChangeHistories = labPatientTestChangeHistories;
		this.labTemplateWidgets = labTemplateWidgets;
		this.labPatientTestAttributeValues = labPatientTestAttributeValues;
		this.labTestAttributeAssociations = labTestAttributeAssociations;
	}

	// Property accessors

	public String getAttributeCode() {
		return this.attributeCode;
	}

	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}

	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getObservationValue() {
		return this.observationValue;
	}

	public void setObservationValue(String observationValue) {
		this.observationValue = observationValue;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Set getLabPatientTestChangeHistories() {
		return this.labPatientTestChangeHistories;
	}

	public void setLabPatientTestChangeHistories(
			Set labPatientTestChangeHistories) {
		this.labPatientTestChangeHistories = labPatientTestChangeHistories;
	}

	public Set getLabTemplateWidgets() {
		return this.labTemplateWidgets;
	}

	public void setLabTemplateWidgets(Set labTemplateWidgets) {
		this.labTemplateWidgets = labTemplateWidgets;
	}

	public Set getLabPatientTestAttributeValues() {
		return this.labPatientTestAttributeValues;
	}

	public void setLabPatientTestAttributeValues(
			Set labPatientTestAttributeValues) {
		this.labPatientTestAttributeValues = labPatientTestAttributeValues;
	}

	public Set getLabTestAttributeAssociations() {
		return this.labTestAttributeAssociations;
	}

	public void setLabTestAttributeAssociations(Set labTestAttributeAssociations) {
		this.labTestAttributeAssociations = labTestAttributeAssociations;
	}

}