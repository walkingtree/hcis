package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * IdProofs entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class IdProofs implements java.io.Serializable {

	// Fields

	private String idProofsCode;
	private String description;
	private Short active;
	private Set doctorDetails = new HashSet(0);
	private Set patientDetailses = new HashSet(0);

	// Constructors

	/** default constructor */
	public IdProofs() {
	}

	/** full constructor */
	public IdProofs(String description, Short active, Set doctorDetails,
			Set patientDetailses) {
		this.description = description;
		this.active = active;
		this.doctorDetails = doctorDetails;
		this.patientDetailses = patientDetailses;
	}

	// Property accessors

	public String getIdProofsCode() {
		return this.idProofsCode;
	}

	public void setIdProofsCode(String idProofsCode) {
		this.idProofsCode = idProofsCode;
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

	public Set getPatientDetailses() {
		return this.patientDetailses;
	}

	public void setPatientDetailses(Set patientDetailses) {
		this.patientDetailses = patientDetailses;
	}

}