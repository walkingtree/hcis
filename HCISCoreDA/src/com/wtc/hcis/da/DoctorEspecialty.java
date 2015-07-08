package com.wtc.hcis.da;

import java.util.Date;

/**
 * DoctorEspecialty entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorEspecialty implements java.io.Serializable {

	// Fields

	private DoctorEspecialtyId id;
	private Integer version;
	private Room room;
	private Especialty especialty;
	private Department department;
	private Doctor doctor;
	private Short active;
	private Date createdDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;
	private Date joiningDt;
	private Date leavingDt;
	private Double consultationCharge;
	private Double followupCharge;
	private Integer followupDay;
	private Integer followupVisit;

	// Constructors

	/** default constructor */
	public DoctorEspecialty() {
	}

	/** minimal constructor */
	public DoctorEspecialty(DoctorEspecialtyId id, Especialty especialty,
			Department department, Doctor doctor) {
		this.id = id;
		this.especialty = especialty;
		this.department = department;
		this.doctor = doctor;
	}

	/** full constructor */
	public DoctorEspecialty(DoctorEspecialtyId id, Room room,
			Especialty especialty, Department department, Doctor doctor,
			Short active, Date createdDtm, String createdBy,
			Date lastModifiedDtm, String modifiedBy, Date joiningDt,
			Date leavingDt, Double consultationCharge) {
		this.id = id;
		this.room = room;
		this.especialty = especialty;
		this.department = department;
		this.doctor = doctor;
		this.active = active;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
		this.joiningDt = joiningDt;
		this.leavingDt = leavingDt;
		this.consultationCharge = consultationCharge;
		this.followupCharge = followupCharge;
		this.followupDay = followupDay;
		this.followupVisit = followupVisit;
	}

	// Property accessors

	public DoctorEspecialtyId getId() {
		return this.id;
	}

	public void setId(DoctorEspecialtyId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Especialty getEspecialty() {
		return this.especialty;
	}

	public void setEspecialty(Especialty especialty) {
		this.especialty = especialty;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
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

	public Date getJoiningDt() {
		return this.joiningDt;
	}

	public void setJoiningDt(Date joiningDt) {
		this.joiningDt = joiningDt;
	}

	public Date getLeavingDt() {
		return this.leavingDt;
	}

	public void setLeavingDt(Date leavingDt) {
		this.leavingDt = leavingDt;
	}

	public Double getConsultationCharge() {
		return this.consultationCharge;
	}

	public void setConsultationCharge(Double consultationCharge) {
		this.consultationCharge = consultationCharge;
	}

	public Double getFollowupCharge() {
		return followupCharge;
	}

	public void setFollowupCharge(Double followupCharge) {
		this.followupCharge = followupCharge;
	}

	public Integer getFollowupDay() {
		return followupDay;
	}

	public void setFollowupDay(Integer followupDay) {
		this.followupDay = followupDay;
	}

	public Integer getFollowupVisit() {
		return followupVisit;
	}

	public void setFollowupVisit(Integer followupVisit) {
		this.followupVisit = followupVisit;
	}

}