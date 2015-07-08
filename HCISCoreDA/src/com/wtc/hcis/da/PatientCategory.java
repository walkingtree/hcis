package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * PatientCategory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PatientCategory implements java.io.Serializable {

	// Fields

	private String categoryCode;
	private String description;
	private Short active;
	private Short defaultCode;
	private Set patients = new HashSet(0);

	// Constructors

	/** default constructor */
	public PatientCategory() {
	}

	/** full constructor */
	public PatientCategory(String description, Short active, Short defaultCode,
			Set patients) {
		this.description = description;
		this.active = active;
		this.defaultCode = defaultCode;
		this.patients = patients;
	}

	// Property accessors

	public String getCategoryCode() {
		return this.categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getActive() {
		return this.active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

	public Short getDefaultCode() {
		return this.defaultCode;
	}

	public void setDefaultCode(Short defaultCode) {
		this.defaultCode = defaultCode;
	}

	public Set getPatients() {
		return this.patients;
	}

	public void setPatients(Set patients) {
		this.patients = patients;
	}

}