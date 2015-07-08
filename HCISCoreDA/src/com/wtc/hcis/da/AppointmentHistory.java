package com.wtc.hcis.da;

import java.util.Date;

/**
 * AppointmentHistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AppointmentHistory implements java.io.Serializable {

	// Fields

	private AppointmentHistoryId id;
	private Integer version;
	private Appointments appointments;
	private AppointmentStatus appointmentStatus;
	private Date createdDtm;
	private String createdBy;

	// Constructors

	/** default constructor */
	public AppointmentHistory() {
	}

	/** minimal constructor */
	public AppointmentHistory(AppointmentHistoryId id,
			Appointments appointments, AppointmentStatus appointmentStatus) {
		this.id = id;
		this.appointments = appointments;
		this.appointmentStatus = appointmentStatus;
	}

	/** full constructor */
	public AppointmentHistory(AppointmentHistoryId id,
			Appointments appointments, AppointmentStatus appointmentStatus,
			Date createdDtm, String createdBy) {
		this.id = id;
		this.appointments = appointments;
		this.appointmentStatus = appointmentStatus;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
	}

	// Property accessors

	public AppointmentHistoryId getId() {
		return this.id;
	}

	public void setId(AppointmentHistoryId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Appointments getAppointments() {
		return this.appointments;
	}

	public void setAppointments(Appointments appointments) {
		this.appointments = appointments;
	}

	public AppointmentStatus getAppointmentStatus() {
		return this.appointmentStatus;
	}

	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
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

}