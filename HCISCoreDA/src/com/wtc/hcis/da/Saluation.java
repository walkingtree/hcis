package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * Saluation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Saluation implements java.io.Serializable {

	// Fields

	private String saluationCode;
	private String description;
	private Short active;
	private Set doctors = new HashSet(0);
	private Set contactDetailses = new HashSet(0);
	private Set patients = new HashSet(0);
	private Set entities = new HashSet(0);

	// Constructors

	/** default constructor */
	public Saluation() {
	}

	/** full constructor */
	public Saluation(String description, Short active, Set doctors,
			Set contactDetailses, Set patients, Set entities) {
		this.description = description;
		this.active = active;
		this.doctors = doctors;
		this.contactDetailses = contactDetailses;
		this.patients = patients;
		this.entities = entities;
	}

	// Property accessors

	public String getSaluationCode() {
		return this.saluationCode;
	}

	public void setSaluationCode(String saluationCode) {
		this.saluationCode = saluationCode;
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

	public Set getDoctors() {
		return this.doctors;
	}

	public void setDoctors(Set doctors) {
		this.doctors = doctors;
	}

	public Set getContactDetailses() {
		return this.contactDetailses;
	}

	public void setContactDetailses(Set contactDetailses) {
		this.contactDetailses = contactDetailses;
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

}