package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * Marital entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Marital implements java.io.Serializable {

	// Fields

	private String maritalCode;
	private String description;
	private String active;
	private Set patients = new HashSet(0);
	private Set entities = new HashSet(0);
	private Set doctorDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public Marital() {
	}

	/** full constructor */
	public Marital(String description, String active, Set patients,
			Set entities, Set doctorDetails) {
		this.description = description;
		this.active = active;
		this.patients = patients;
		this.entities = entities;
		this.doctorDetails = doctorDetails;
	}

	// Property accessors

	public String getMaritalCode() {
		return this.maritalCode;
	}

	public void setMaritalCode(String maritalCode) {
		this.maritalCode = maritalCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Set getPatients() {
		return this.patients;
	}

	public void setPatients(Set patients) {
		this.patients = patients;
	}

	public Set getEntities() {
		return this.entities;
	}

	public void setEntities(Set entities) {
		this.entities = entities;
	}

	public Set getDoctorDetails() {
		return this.doctorDetails;
	}

	public void setDoctorDetails(Set doctorDetails) {
		this.doctorDetails = doctorDetails;
	}

}