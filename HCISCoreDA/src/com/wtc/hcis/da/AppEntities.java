package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * AppEntities entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AppEntities implements java.io.Serializable {

	// Fields

	private String entityType;
	private String entityName;
	private String description;
	private Short active;
	private Set reminderses = new HashSet(0);
	private Set entityContactCodes = new HashSet(0);

	// Constructors

	/** default constructor */
	public AppEntities() {
	}

	/** full constructor */
	public AppEntities(String entityName, String description, Short active,
			Set reminderses, Set entityContactCodes) {
		this.entityName = entityName;
		this.description = description;
		this.active = active;
		this.reminderses = reminderses;
		this.entityContactCodes = entityContactCodes;
	}

	// Property accessors

	public String getEntityType() {
		return this.entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getEntityName() {
		return this.entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
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

	public Set getReminderses() {
		return this.reminderses;
	}

	public void setReminderses(Set reminderses) {
		this.reminderses = reminderses;
	}

	public Set getEntityContactCodes() {
		return this.entityContactCodes;
	}

	public void setEntityContactCodes(Set entityContactCodes) {
		this.entityContactCodes = entityContactCodes;
	}

}