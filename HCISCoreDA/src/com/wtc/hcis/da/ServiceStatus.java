package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * ServiceStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ServiceStatus implements java.io.Serializable {

	// Fields

	private String serviceStatusCode;
	private String description;
	private Short active;
	private Set services = new HashSet(0);
	private Set serviceHistories = new HashSet(0);

	// Constructors

	/** default constructor */
	public ServiceStatus() {
	}

	/** full constructor */
	public ServiceStatus(String description, Short active, Set services,
			Set serviceHistories) {
		this.description = description;
		this.active = active;
		this.services = services;
		this.serviceHistories = serviceHistories;
	}

	// Property accessors

	public String getServiceStatusCode() {
		return this.serviceStatusCode;
	}

	public void setServiceStatusCode(String serviceStatusCode) {
		this.serviceStatusCode = serviceStatusCode;
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

	public Set getServices() {
		return this.services;
	}

	public void setServices(Set services) {
		this.services = services;
	}

	public Set getServiceHistories() {
		return this.serviceHistories;
	}

	public void setServiceHistories(Set serviceHistories) {
		this.serviceHistories = serviceHistories;
	}

}