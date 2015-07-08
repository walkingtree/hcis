package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Record entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Record implements java.io.Serializable {

	// Fields

	private String recordId;
	private Integer version;
	private AssignedServices assignedServices;
	private Date serviceDate;
	private Short active;
	private Date createDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;
	private Set sampleses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Record() {
	}

	/** full constructor */
	public Record(AssignedServices assignedServices, Date serviceDate,
			Short active, Date createDtm, String createdBy,
			Date lastModifiedDtm, String modifiedBy, Set sampleses) {
		this.assignedServices = assignedServices;
		this.serviceDate = serviceDate;
		this.active = active;
		this.createDtm = createDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
		this.sampleses = sampleses;
	}

	// Property accessors

	public String getRecordId() {
		return this.recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
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

	public Date getServiceDate() {
		return this.serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public Short getActive() {
		return this.active;
	}

	public void setActive(Short active) {
		this.active = active;
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

	public Set getSampleses() {
		return this.sampleses;
	}

	public void setSampleses(Set sampleses) {
		this.sampleses = sampleses;
	}

}