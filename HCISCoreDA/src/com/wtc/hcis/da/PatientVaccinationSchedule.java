package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PatientVaccinationSchedule entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PatientVaccinationSchedule implements java.io.Serializable {

	// Fields

	private Integer seqNbr;
	private Integer version;
	private VaccinationSchedule vaccinationSchedule;
	private Patient patient;
	private Doctor doctor;
	private Date startDt;
	private String statusCd;
	private String userId;
	private Date lastModifiedDt;
	private Set patientVaccinationScheduleDetailses = new HashSet(0);

	// Constructors

	/** default constructor */
	public PatientVaccinationSchedule() {
	}

	/** minimal constructor */
	public PatientVaccinationSchedule(VaccinationSchedule vaccinationSchedule,
			Patient patient, Doctor doctor, Date startDt, String statusCd,
			String userId, Date lastModifiedDt) {
		this.vaccinationSchedule = vaccinationSchedule;
		this.patient = patient;
		this.doctor = doctor;
		this.startDt = startDt;
		this.statusCd = statusCd;
		this.userId = userId;
		this.lastModifiedDt = lastModifiedDt;
	}

	/** full constructor */
	public PatientVaccinationSchedule(VaccinationSchedule vaccinationSchedule,
			Patient patient, Doctor doctor, Date startDt, String statusCd,
			String userId, Date lastModifiedDt,
			Set patientVaccinationScheduleDetailses) {
		this.vaccinationSchedule = vaccinationSchedule;
		this.patient = patient;
		this.doctor = doctor;
		this.startDt = startDt;
		this.statusCd = statusCd;
		this.userId = userId;
		this.lastModifiedDt = lastModifiedDt;
		this.patientVaccinationScheduleDetailses = patientVaccinationScheduleDetailses;
	}

	// Property accessors

	public Integer getSeqNbr() {
		return this.seqNbr;
	}

	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public VaccinationSchedule getVaccinationSchedule() {
		return this.vaccinationSchedule;
	}

	public void setVaccinationSchedule(VaccinationSchedule vaccinationSchedule) {
		this.vaccinationSchedule = vaccinationSchedule;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public String getStatusCd() {
		return this.statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLastModifiedDt() {
		return this.lastModifiedDt;
	}

	public void setLastModifiedDt(Date lastModifiedDt) {
		this.lastModifiedDt = lastModifiedDt;
	}

	public Set getPatientVaccinationScheduleDetailses() {
		return this.patientVaccinationScheduleDetailses;
	}

	public void setPatientVaccinationScheduleDetailses(
			Set patientVaccinationScheduleDetailses) {
		this.patientVaccinationScheduleDetailses = patientVaccinationScheduleDetailses;
	}

}