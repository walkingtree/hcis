package com.wtc.hcis.da;

/**
 * ServicePackageHistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ServicePackageHistory implements java.io.Serializable {

	// Fields

	private ServicePackageHistoryId id;
	private Integer version;
	private ServicePackage servicePackage;
	private ServicePackageStatus servicePackageStatus;
	private String createdBy;

	// Constructors

	/** default constructor */
	public ServicePackageHistory() {
	}

	/** minimal constructor */
	public ServicePackageHistory(ServicePackageHistoryId id,
			ServicePackage servicePackage,
			ServicePackageStatus servicePackageStatus) {
		this.id = id;
		this.servicePackage = servicePackage;
		this.servicePackageStatus = servicePackageStatus;
	}

	/** full constructor */
	public ServicePackageHistory(ServicePackageHistoryId id,
			ServicePackage servicePackage,
			ServicePackageStatus servicePackageStatus, String createdBy) {
		this.id = id;
		this.servicePackage = servicePackage;
		this.servicePackageStatus = servicePackageStatus;
		this.createdBy = createdBy;
	}

	// Property accessors

	public ServicePackageHistoryId getId() {
		return this.id;
	}

	public void setId(ServicePackageHistoryId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ServicePackage getServicePackage() {
		return this.servicePackage;
	}

	public void setServicePackage(ServicePackage servicePackage) {
		this.servicePackage = servicePackage;
	}

	public ServicePackageStatus getServicePackageStatus() {
		return this.servicePackageStatus;
	}

	public void setServicePackageStatus(
			ServicePackageStatus servicePackageStatus) {
		this.servicePackageStatus = servicePackageStatus;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}