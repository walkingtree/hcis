package com.wtc.hcis.da;

import java.util.Date;

/**
 * DefaultReminders entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DefaultReminders implements java.io.Serializable {

	// Fields

	private DefaultRemindersId id;
	private Integer version;
	private String reminderTime;
	private String durationCode;
	private Date createdDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;

	// Constructors

	/** default constructor */
	public DefaultReminders() {
	}

	/** minimal constructor */
	public DefaultReminders(DefaultRemindersId id) {
		this.id = id;
	}

	/** full constructor */
	public DefaultReminders(DefaultRemindersId id, String reminderTime,
			String durationCode, Date createdDtm, String createdBy,
			Date lastModifiedDtm, String modifiedBy) {
		this.id = id;
		this.reminderTime = reminderTime;
		this.durationCode = durationCode;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
	}

	// Property accessors

	public DefaultRemindersId getId() {
		return this.id;
	}

	public void setId(DefaultRemindersId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getReminderTime() {
		return this.reminderTime;
	}

	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}

	public String getDurationCode() {
		return this.durationCode;
	}

	public void setDurationCode(String durationCode) {
		this.durationCode = durationCode;
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

}