package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * RegistrationStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class RegistrationStatus implements java.io.Serializable {

	// Fields

	private String registrationStatusCode;
	private String description;
	private Short active;
	private Set patients = new HashSet(0);

	// Constructors

	/** default constructor */
	public RegistrationStatus() {
	}

	/** full constructor */
	public RegistrationStatus(String description, Short active, Set patients) {
		this.description = description;
		this.active = active;
		this.patients = patients;
	}

	// Property accessors

	public String getRegistrationStatusCode() {
		return this.registrationStatusCode;
	}

	public void setRegistrationStatusCode(String registrationStatusCode) {
		this.registrationStatusCode = registrationStatusCode;
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

	public Set getPatients() {
		return this.patients;
	}

	public void setPatients(Set patients) {
		this.patients = patients;
	}

}