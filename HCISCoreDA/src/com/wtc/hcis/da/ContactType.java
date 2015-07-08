package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * ContactType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ContactType implements java.io.Serializable {

	// Fields

	private String contactTypeInd;
	private String description;
	private Short active;
	private Set entityContactCodes = new HashSet(0);

	// Constructors

	/** default constructor */
	public ContactType() {
	}

	/** full constructor */
	public ContactType(String description, Short active, Set entityContactCodes) {
		this.description = description;
		this.active = active;
		this.entityContactCodes = entityContactCodes;
	}

	// Property accessors

	public String getContactTypeInd() {
		return this.contactTypeInd;
	}

	public void setContactTypeInd(String contactTypeInd) {
		this.contactTypeInd = contactTypeInd;
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

	public Set getEntityContactCodes() {
		return this.entityContactCodes;
	}

	public void setEntityContactCodes(Set entityContactCodes) {
		this.entityContactCodes = entityContactCodes;
	}

}