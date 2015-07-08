package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * Religion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Religion implements java.io.Serializable {

	// Fields

	private String religionCode;
	private String description;
	private Short active;
	private Set doctorDetails = new HashSet(0);
	private Set patients = new HashSet(0);

	// Constructors

	/** default constructor */
	public Religion() {
	}

	/** full constructor */
	public Religion(String description, Short active, Set doctorDetails,
			Set patients) {
		this.description = description;
		this.active = active;
		this.doctorDetails = doctorDetails;
		this.patients = patients;
	}

	// Property accessors

	public String getReligionCode() {
		return this.religionCode;
	}

	public void setReligionCode(String religionCode) {
		this.religionCode = religionCode;
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

	public Set getDoctorDetails() {
		return this.doctorDetails;
	}

	public void setDoctorDetails(Set doctorDetails) {
		this.doctorDetails = doctorDetails;
	}

	public Set getPatients() {
		return this.patients;
	}

	public void setPatients(Set patients) {
		this.patients = patients;
	}

}