package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * BloodGroup entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BloodGroup implements java.io.Serializable {

	// Fields

	private String bloodGroupCode;
	private String description;
	private Short active;
	private Set patients = new HashSet(0);
	private Set entities = new HashSet(0);
	private Set doctorDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public BloodGroup() {
	}

	/** full constructor */
	public BloodGroup(String description, Short active, Set patients,
			Set entities, Set doctorDetails) {
		this.description = description;
		this.active = active;
		this.patients = patients;
		this.entities = entities;
		this.doctorDetails = doctorDetails;
	}

	// Property accessors

	public String getBloodGroupCode() {
		return this.bloodGroupCode;
	}

	public void setBloodGroupCode(String bloodGroupCode) {
		this.bloodGroupCode = bloodGroupCode;
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