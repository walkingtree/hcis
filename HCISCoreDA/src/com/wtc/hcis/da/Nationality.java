package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * Nationality entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Nationality implements java.io.Serializable {

	// Fields

	private String nationalityCode;
	private String description;
	private Short active;
	private Short defaultCode;
	private Set entities = new HashSet(0);
	private Set patients = new HashSet(0);
	private Set doctorDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public Nationality() {
	}

	/** full constructor */
	public Nationality(String description, Short active, Short defaultCode,
			Set entities, Set patients, Set doctorDetails) {
		this.description = description;
		this.active = active;
		this.defaultCode = defaultCode;
		this.entities = entities;
		this.patients = patients;
		this.doctorDetails = doctorDetails;
	}

	// Property accessors

	public String getNationalityCode() {
		return this.nationalityCode;
	}

	public void setNationalityCode(String nationalityCode) {
		this.nationalityCode = nationalityCode;
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

	public Set getEntities() {
		return this.entities;
	}

	public void setEntities(Set entities) {
		this.entities = entities;
	}

	public Set getPatients() {
		return this.patients;
	}

	public void setPatients(Set patients) {
		this.patients = patients;
	}

	public Set getDoctorDetails() {
		return this.doctorDetails;
	}

	public void setDoctorDetails(Set doctorDetails) {
		this.doctorDetails = doctorDetails;
	}

}