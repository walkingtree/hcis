package com.wtc.hcis.da;

import java.util.Date;

/**
 * Observations entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Observations implements java.io.Serializable {

	// Fields

	private ObservationsId id;
	private Integer version;
	private Appointments appointments;
	private Doctor doctor;
	private String observations;
	private String remarks;
	private Date createDtm;
	private String creadtedBy;
	private Date lastModifiedDtm;
	private String modifiedBy;

	// Constructors

	/** default constructor */
	public Observations() {
	}

	/** minimal constructor */
	public Observations(ObservationsId id, Appointments appointments) {
		this.id = id;
		this.appointments = appointments;
	}

	/** full constructor */
	public Observations(ObservationsId id, Appointments appointments,
			Doctor doctor, String observations, String remarks, Date createDtm,
			String creadtedBy, Date lastModifiedDtm, String modifiedBy) {
		this.id = id;
		this.appointments = appointments;
		this.doctor = doctor;
		this.observations = observations;
		this.remarks = remarks;
		this.createDtm = createDtm;
		this.creadtedBy = creadtedBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
	}

	// Property accessors

	public ObservationsId getId() {
		return this.id;
	}

	public void setId(ObservationsId id) {
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

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getObservations() {
		return this.observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDtm() {
		return this.createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}

	public String getCreadtedBy() {
		return this.creadtedBy;
	}

	public void setCreadtedBy(String creadtedBy) {
		this.creadtedBy = creadtedBy;
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