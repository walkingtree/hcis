package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ServiceGroup entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ServiceGroup implements java.io.Serializable {

	// Fields

	private String serviceGroupCode;
	private Integer version;
	private ServiceGroup serviceGroup;
	private String groupName;
	private Date createDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;
	private Set serviceGroups = new HashSet(0);
	private Set services = new HashSet(0);

	// Constructors

	/** default constructor */
	public ServiceGroup() {
	}

	/** full constructor */
	public ServiceGroup(ServiceGroup serviceGroup, String groupName,
			Date createDtm, String createdBy, Date lastModifiedDtm,
			String modifiedBy, Set serviceGroups, Set services) {
		this.serviceGroup = serviceGroup;
		this.groupName = groupName;
		this.createDtm = createDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
		this.serviceGroups = serviceGroups;
		this.services = services;
	}

	// Property accessors

	public String getServiceGroupCode() {
		return this.serviceGroupCode;
	}

	public void setServiceGroupCode(String serviceGroupCode) {
		this.serviceGroupCode = serviceGroupCode;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ServiceGroup getServiceGroup() {
		return this.serviceGroup;
	}

	public void setServiceGroup(ServiceGroup serviceGroup) {
		this.serviceGroup = serviceGroup;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Date getCreateDtm() {
		return this.createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Set getServiceGroups() {
		return this.serviceGroups;
	}

	public void setServiceGroups(Set serviceGroups) {
		this.serviceGroups = serviceGroups;
	}

	public Set getServices() {
		return this.services;
	}

	public void setServices(Set services) {
		this.services = services;
	}

}