package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * AssignedPackageStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AssignedPackageStatus implements java.io.Serializable {

	// Fields

	private String statusCode;
	private String description;
	private Set assignedPackages = new HashSet(0);

	// Constructors

	/** default constructor */
	public AssignedPackageStatus() {
	}

	/** full constructor */
	public AssignedPackageStatus(String description, Set assignedPackages) {
		this.description = description;
		this.assignedPackages = assignedPackages;
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

	public Set getAssignedPackages() {
		return this.assignedPackages;
	}

	public void setAssignedPackages(Set assignedPackages) {
		this.assignedPackages = assignedPackages;
	}

}