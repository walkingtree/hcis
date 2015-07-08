package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * Relation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Relation implements java.io.Serializable {

	// Fields

	private String relationCode;
	private String description;
	private Short active;
	private Set contactDetailses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Relation() {
	}

	/** full constructor */
	public Relation(String description, Short active, Set contactDetailses) {
		this.description = description;
		this.active = active;
		this.contactDetailses = contactDetailses;
	}

	// Property accessors

	public String getRelationCode() {
		return this.relationCode;
	}

	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
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

	public Set getContactDetailses() {
		return this.contactDetailses;
	}

	public void setContactDetailses(Set contactDetailses) {
		this.contactDetailses = contactDetailses;
	}

}