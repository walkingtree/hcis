package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Especialty entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Especialty implements java.io.Serializable {

	// Fields

	private String especialtyCode;
	private String especialtyName;
	private Short active;
	private Short especilalty;
	private Date createdDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;
	private Set doctorEspecialties = new HashSet(0);
	private Set deptEspecialityAssocs = new HashSet(0);
	private Set appointmentses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Especialty() {
	}

	/** full constructor */
	public Especialty(String especialtyName, Short active, Short especilalty,
			Date createdDtm, String createdBy, Date lastModifiedDtm,
			String modifiedBy, Set doctorEspecialties,
			Set deptEspecialityAssocs, Set appointmentses) {
		this.especialtyName = especialtyName;
		this.active = active;
		this.especilalty = especilalty;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
		this.doctorEspecialties = doctorEspecialties;
		this.deptEspecialityAssocs = deptEspecialityAssocs;
		this.appointmentses = appointmentses;
	}

	// Property accessors

	public String getEspecialtyCode() {
		return this.especialtyCode;
	}

	public void setEspecialtyCode(String especialtyCode) {
		this.especialtyCode = especialtyCode;
	}

	public String getEspecialtyName() {
		return this.especialtyName;
	}

	public void setEspecialtyName(String especialtyName) {
		this.especialtyName = especialtyName;
	}

	public Short getActive() {
		return this.active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

	public Short getEspecilalty() {
		return this.especilalty;
	}

	public void setEspecilalty(Short especilalty) {
		this.especilalty = especilalty;
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

	public Set getAppointmentses() {
		return this.appointmentses;
	}

	public void setAppointmentses(Set appointmentses) {
		this.appointmentses = appointmentses;
	}

}