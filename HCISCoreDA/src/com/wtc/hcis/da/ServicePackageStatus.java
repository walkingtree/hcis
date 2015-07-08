package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * ServicePackageStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ServicePackageStatus implements java.io.Serializable {

	// Fields

	private String statusCode;
	private String description;
	private Set servicePackageHistories = new HashSet(0);
	private Set servicePackages = new HashSet(0);

	// Constructors

	/** default constructor */
	public ServicePackageStatus() {
	}

	/** full constructor */
	public ServicePackageStatus(String description,
			Set servicePackageHistories, Set servicePackages) {
		this.description = description;
		this.servicePackageHistories = servicePackageHistories;
		this.servicePackages = servicePackages;
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

	public Set getServicePackageHistories() {
		return this.servicePackageHistories;
	}

	public void setServicePackageHistories(Set servicePackageHistories) {
		this.servicePackageHistories = servicePackageHistories;
	}

	public Set getServicePackages() {
		return this.servicePackages;
	}

	public void setServicePackages(Set servicePackages) {
		this.servicePackages = servicePackages;
	}

}