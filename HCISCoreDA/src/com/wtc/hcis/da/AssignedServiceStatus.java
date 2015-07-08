package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * AssignedServiceStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AssignedServiceStatus implements java.io.Serializable {

	// Fields

	private String serviceStatusCode;
	private String description;
	private Set assignedServiceHistories = new HashSet(0);
	private Set assignedServiceses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AssignedServiceStatus() {
	}

	/** full constructor */
	public AssignedServiceStatus(String description,
			Set assignedServiceHistories, Set assignedServiceses) {
		this.description = description;
		this.assignedServiceHistories = assignedServiceHistories;
		this.assignedServiceses = assignedServiceses;
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

	public Set getAssignedServiceHistories() {
		return this.assignedServiceHistories;
	}

	public void setAssignedServiceHistories(Set assignedServiceHistories) {
		this.assignedServiceHistories = assignedServiceHistories;
	}

	public Set getAssignedServiceses() {
		return this.assignedServiceses;
	}

	public void setAssignedServiceses(Set assignedServiceses) {
		this.assignedServiceses = assignedServiceses;
	}

}