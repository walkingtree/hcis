package com.wtc.hcis.da;

import java.util.Date;

/**
 * DeptEspecialityAssoc entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DeptEspecialityAssoc implements java.io.Serializable {

	// Fields

	private DeptEspecialityAssocId id;
	private Integer version;
	private Department department;
	private Especialty especialty;
	private String active;
	private Date lastModifiedDtm;
	private String userId;

	// Constructors

	/** default constructor */
	public DeptEspecialityAssoc() {
	}

	/** full constructor */
	public DeptEspecialityAssoc(DeptEspecialityAssocId id,
			Department department, Especialty especialty, String active,
			Date lastModifiedDtm, String userId) {
		this.id = id;
		this.department = department;
		this.especialty = especialty;
		this.active = active;
		this.lastModifiedDtm = lastModifiedDtm;
		this.userId = userId;
	}

	// Property accessors

	public DeptEspecialityAssocId getId() {
		return this.id;
	}

	public void setId(DeptEspecialityAssocId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Especialty getEspecialty() {
		return this.especialty;
	}

	public void setEspecialty(Especialty especialty) {
		this.especialty = especialty;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}