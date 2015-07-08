package com.wtc.hcis.da;

/**
 * AssignedServiceHistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AssignedServiceHistory implements java.io.Serializable {

	// Fields

	private AssignedServiceHistoryId id;
	private Integer version;
	private AssignedServices assignedServices;
	private AssignedServiceStatus assignedServiceStatus;
	private String createdBy;

	// Constructors

	/** default constructor */
	public AssignedServiceHistory() {
	}

	/** minimal constructor */
	public AssignedServiceHistory(AssignedServiceHistoryId id,
			AssignedServices assignedServices,
			AssignedServiceStatus assignedServiceStatus) {
		this.id = id;
		this.assignedServices = assignedServices;
		this.assignedServiceStatus = assignedServiceStatus;
	}

	/** full constructor */
	public AssignedServiceHistory(AssignedServiceHistoryId id,
			AssignedServices assignedServices,
			AssignedServiceStatus assignedServiceStatus, String createdBy) {
		this.id = id;
		this.assignedServices = assignedServices;
		this.assignedServiceStatus = assignedServiceStatus;
		this.createdBy = createdBy;
	}

	// Property accessors

	public AssignedServiceHistoryId getId() {
		return this.id;
	}

	public void setId(AssignedServiceHistoryId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public AssignedServices getAssignedServices() {
		return this.assignedServices;
	}

	public void setAssignedServices(AssignedServices assignedServices) {
		this.assignedServices = assignedServices;
	}

	public AssignedServiceStatus getAssignedServiceStatus() {
		return this.assignedServiceStatus;
	}

	public void setAssignedServiceStatus(
			AssignedServiceStatus assignedServiceStatus) {
		this.assignedServiceStatus = assignedServiceStatus;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}