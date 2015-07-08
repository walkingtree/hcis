package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * Immunization entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Immunization implements java.io.Serializable {

	// Fields

	private String name;
	private String description;
	private Short active;
	private Set patientImmunizations = new HashSet(0);

	// Constructors

	/** default constructor */
	public Immunization() {
	}

	/** full constructor */
	public Immunization(String description, Short active,
			Set patientImmunizations) {
		this.description = description;
		this.active = active;
		this.patientImmunizations = patientImmunizations;
	}

	// Property accessors

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Set getPatientImmunizations() {
		return this.patientImmunizations;
	}

	public void setPatientImmunizations(Set patientImmunizations) {
		this.patientImmunizations = patientImmunizations;
	}

}