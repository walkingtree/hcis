package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * Duration entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Duration implements java.io.Serializable {

	// Fields

	private String durationCode;
	private String description;
	private String active;
	private Set reminderses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Duration() {
	}

	/** full constructor */
	public Duration(String description, String active, Set reminderses) {
		this.description = description;
		this.active = active;
		this.reminderses = reminderses;
	}

	// Property accessors

	public String getDurationCode() {
		return this.durationCode;
	}

	public void setDurationCode(String durationCode) {
		this.durationCode = durationCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Set getReminderses() {
		return this.reminderses;
	}

	public void setReminderses(Set reminderses) {
		this.reminderses = reminderses;
	}

}