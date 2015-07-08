package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * AppointmentStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AppointmentStatus implements java.io.Serializable {

	// Fields

	private String statusCode;
	private String description;
	private Short active;
	private Set appointmentHistories = new HashSet(0);
	private Set appointmentses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AppointmentStatus() {
	}

	/** full constructor */
	public AppointmentStatus(String description, Short active,
			Set appointmentHistories, Set appointmentses) {
		this.description = description;
		this.active = active;
		this.appointmentHistories = appointmentHistories;
		this.appointmentses = appointmentses;
	}

	// Property accessors

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
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

	public Set getAppointmentHistories() {
		return this.appointmentHistories;
	}

	public void setAppointmentHistories(Set appointmentHistories) {
		this.appointmentHistories = appointmentHistories;
	}

	public Set getAppointmentses() {
		return this.appointmentses;
	}

	public void setAppointmentses(Set appointmentses) {
		this.appointmentses = appointmentses;
	}

}