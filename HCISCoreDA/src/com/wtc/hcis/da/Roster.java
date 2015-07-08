package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Roster entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Roster implements java.io.Serializable {

	// Fields

	private Integer rosterCode;
	private Integer version;
	private Room room;
	private Date workingDate;
	private String entityType;
	private Integer entityId;
	private String startTime;
	private String endTime;
	private String active;
	private Date createdDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;
	private Set appointmentses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Roster() {
	}

	/** minimal constructor */
	public Roster(Date workingDate, Integer entityId, String startTime) {
		this.workingDate = workingDate;
		this.entityId = entityId;
		this.startTime = startTime;
	}

	/** full constructor */
	public Roster(Room room, Date workingDate, String entityType,
			Integer entityId, String startTime, String endTime, String active,
			Date createdDtm, String createdBy, Date lastModifiedDtm,
			String modifiedBy, Set appointmentses) {
		this.room = room;
		this.workingDate = workingDate;
		this.entityType = entityType;
		this.entityId = entityId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.active = active;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
		this.appointmentses = appointmentses;
	}

	// Property accessors

	public Integer getRosterCode() {
		return this.rosterCode;
	}

	public void setRosterCode(Integer rosterCode) {
		this.rosterCode = rosterCode;
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

	public Date getWorkingDate() {
		return this.workingDate;
	}

	public void setWorkingDate(Date workingDate) {
		this.workingDate = workingDate;
	}

	public String getEntityType() {
		return this.entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public Integer getEntityId() {
		return this.entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
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

	public Set getAppointmentses() {
		return this.appointmentses;
	}

	public void setAppointmentses(Set appointmentses) {
		this.appointmentses = appointmentses;
	}

}