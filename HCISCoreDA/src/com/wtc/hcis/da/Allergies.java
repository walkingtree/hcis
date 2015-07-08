package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * Allergies entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Allergies implements java.io.Serializable {

	// Fields

	private Integer allergiesCode;
	private String allergryDescription;
	private Short active;
	private Set patientAllergieses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Allergies() {
	}

	/** full constructor */
	public Allergies(String allergryDescription, Short active,
			Set patientAllergieses) {
		this.allergryDescription = allergryDescription;
		this.active = active;
		this.patientAllergieses = patientAllergieses;
	}

	// Property accessors

	public Integer getAllergiesCode() {
		return this.allergiesCode;
	}

	public void setAllergiesCode(Integer allergiesCode) {
		this.allergiesCode = allergiesCode;
	}

	public String getAllergryDescription() {
		return this.allergryDescription;
	}

	public void setAllergryDescription(String allergryDescription) {
		this.allergryDescription = allergryDescription;
	}

	public Short getActive() {
		return this.active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

	public Set getPatientAllergieses() {
		return this.patientAllergieses;
	}

	public void setPatientAllergieses(Set patientAllergieses) {
		this.patientAllergieses = patientAllergieses;
	}

}