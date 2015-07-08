package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * ReminderOptions entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ReminderOptions implements java.io.Serializable {

	// Fields

	private String reminderOptionsCode;
	private Integer version;
	private String description;
	private Short active;
	private Set reminderses = new HashSet(0);

	// Constructors

	/** default constructor */
	public ReminderOptions() {
	}

	/** full constructor */
	public ReminderOptions(String description, Short active, Set reminderses) {
		this.description = description;
		this.active = active;
		this.reminderses = reminderses;
	}

	// Property accessors

	public String getReminderOptionsCode() {
		return this.reminderOptionsCode;
	}

	public void setReminderOptionsCode(String reminderOptionsCode) {
		this.reminderOptionsCode = reminderOptionsCode;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

}