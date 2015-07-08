package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Department entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Department implements java.io.Serializable {

	// Fields

	private String departmentCode;
	private String departmentName;
	private String accountNumber;
	private String profitCenterCode;
	private Short active;
	private Date createdDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;
	private Set services = new HashSet(0);
	private Set doctorEspecialties = new HashSet(0);
	private Set deptEspecialityAssocs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** minimal constructor */
	public Department(String departmentName) {
		this.departmentName = departmentName;
	}

	/** full constructor */
	public Department(String departmentName, String accountNumber,
			String profitCenterCode, Short active, Date createdDtm,
			String createdBy, Date lastModifiedDtm, String modifiedBy,
			Set services, Set doctorEspecialties, Set deptEspecialityAssocs) {
		this.departmentName = departmentName;
		this.accountNumber = accountNumber;
		this.profitCenterCode = profitCenterCode;
		this.active = active;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
		this.services = services;
		this.doctorEspecialties = doctorEspecialties;
		this.deptEspecialityAssocs = deptEspecialityAssocs;
	}

	// Property accessors

	public String getDepartmentCode() {
		return this.departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getProfitCenterCode() {
		return this.profitCenterCode;
	}

	public void setProfitCenterCode(String profitCenterCode) {
		this.profitCenterCode = profitCenterCode;
	}

	public Short getActive() {
		return this.active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

	public Date getCreatedDtm() {
		return this.createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
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

	public Set getServices() {
		return this.services;
	}

	public void setServices(Set services) {
		this.services = services;
	}

	public Set getDoctorEspecialties() {
		return this.doctorEspecialties;
	}

	public void setDoctorEspecialties(Set doctorEspecialties) {
		this.doctorEspecialties = doctorEspecialties;
	}

	public Set getDeptEspecialityAssocs() {
		return this.deptEspecialityAssocs;
	}

	public void setDeptEspecialityAssocs(Set deptEspecialityAssocs) {
		this.deptEspecialityAssocs = deptEspecialityAssocs;
	}

}