package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * Gender entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Gender implements java.io.Serializable {

	// Fields

	private String genderCode;
	private String description;
	private Set entities = new HashSet(0);
	private Set patients = new HashSet(0);
	private Set contactDetailses = new HashSet(0);
	private Set doctorDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public Gender() {
	}

	/** full constructor */
	public Gender(String description, Set entities, Set patients,
			Set contactDetailses, Set doctorDetails) {
		this.description = description;
		this.entities = entities;
		this.patients = patients;
		this.contactDetailses = contactDetailses;
		this.doctorDetails = doctorDetails;
	}

	// Property accessors

	public String getGenderCode() {
		return this.genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Set getContactDetailses() {
		return this.contactDetailses;
	}

	public void setContactDetailses(Set contactDetailses) {
		this.contactDetailses = contactDetailses;
	}

	public Set getDoctorDetails() {
		return this.doctorDetails;
	}

	public void setDoctorDetails(Set doctorDetails) {
		this.doctorDetails = doctorDetails;
	}

}