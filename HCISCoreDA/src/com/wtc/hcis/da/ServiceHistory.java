package com.wtc.hcis.da;

/**
 * ServiceHistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ServiceHistory implements java.io.Serializable {

	// Fields

	private ServiceHistoryId id;
	private Integer version;
	private ServiceStatus serviceStatus;
	private Service service;
	private String createdBy;

	// Constructors

	/** default constructor */
	public ServiceHistory() {
	}

	/** minimal constructor */
	public ServiceHistory(ServiceHistoryId id, ServiceStatus serviceStatus,
			Service service) {
		this.id = id;
		this.serviceStatus = serviceStatus;
		this.service = service;
	}

	/** full constructor */
	public ServiceHistory(ServiceHistoryId id, ServiceStatus serviceStatus,
			Service service, String createdBy) {
		this.id = id;
		this.serviceStatus = serviceStatus;
		this.service = service;
		this.createdBy = createdBy;
	}

	// Property accessors

	public ServiceHistoryId getId() {
		return this.id;
	}

	public void setId(ServiceHistoryId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ServiceStatus getServiceStatus() {
		return this.serviceStatus;
	}

	public void setServiceStatus(ServiceStatus serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}