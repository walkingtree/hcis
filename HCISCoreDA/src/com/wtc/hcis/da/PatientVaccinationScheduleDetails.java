package com.wtc.hcis.da;

import java.util.Date;

/**
 * PatientVaccinationScheduleDetails entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PatientVaccinationScheduleDetails implements java.io.Serializable {

	// Fields

	private PatientVaccinationScheduleDetailsId id;
	private Integer version;
	private PatientVaccinationSchedule patientVaccinationSchedule;
	private VaccinationSchedule vaccinationSchedule;
	private Vaccination vaccination;
	private Patient patient;
	private Doctor doctor;
	private Integer periodInDays;
	private String age;
	private String vaccinationTypeCd;
	private String dosage;
	private String doctorComments;
	private String userId;
	private Date dueDate;
	private Date givenDate;

	// Constructors

	/** default constructor */
	public PatientVaccinationScheduleDetails() {
	}

	/** minimal constructor */
	public PatientVaccinationScheduleDetails(
			PatientVaccinationScheduleDetailsId id,
			PatientVaccinationSchedule patientVaccinationSchedule,
			VaccinationSchedule vaccinationSchedule, Vaccination vaccination,
			Patient patient, Integer periodInDays, String age, String dosage,
			String userId) {
		this.id = id;
		this.patientVaccinationSchedule = patientVaccinationSchedule;
		this.vaccinationSchedule = vaccinationSchedule;
		this.vaccination = vaccination;
		this.patient = patient;
		this.periodInDays = periodInDays;
		this.age = age;
		this.dosage = dosage;
		this.userId = userId;
	}

	/** full constructor */
	public PatientVaccinationScheduleDetails(
			PatientVaccinationScheduleDetailsId id,
			PatientVaccinationSchedule patientVaccinationSchedule,
			VaccinationSchedule vaccinationSchedule, Vaccination vaccination,
			Patient patient, Doctor doctor, Integer periodInDays, String age,
			String vaccinationTypeCd, String dosage, String doctorComments,
			String userId, Date dueDate, Date givenDate) {
		this.id = id;
		this.patientVaccinationSchedule = patientVaccinationSchedule;
		this.vaccinationSchedule = vaccinationSchedule;
		this.vaccination = vaccination;
		this.patient = patient;
		this.doctor = doctor;
		this.periodInDays = periodInDays;
		this.age = age;
		this.vaccinationTypeCd = vaccinationTypeCd;
		this.dosage = dosage;
		this.doctorComments = doctorComments;
		this.userId = userId;
		this.dueDate = dueDate;
		this.givenDate = givenDate;
	}

	// Property accessors

	public PatientVaccinationScheduleDetailsId getId() {
		return this.id;
	}

	public void setId(PatientVaccinationScheduleDetailsId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public PatientVaccinationSchedule getPatientVaccinationSchedule() {
		return this.patientVaccinationSchedule;
	}

	public void setPatientVaccinationSchedule(
			PatientVaccinationSchedule patientVaccinationSchedule) {
		this.patientVaccinationSchedule = patientVaccinationSchedule;
	}

	public VaccinationSchedule getVaccinationSchedule() {
		return this.vaccinationSchedule;
	}

	public void setVaccinationSchedule(VaccinationSchedule vaccinationSchedule) {
		this.vaccinationSchedule = vaccinationSchedule;
	}

	public Vaccination getVaccination() {
		return this.vaccination;
	}

	public void setVaccination(Vaccination vaccination) {
		this.vaccination = vaccination;
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

	public Integer getPeriodInDays() {
		return this.periodInDays;
	}

	public void setPeriodInDays(Integer periodInDays) {
		this.periodInDays = periodInDays;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getVaccinationTypeCd() {
		return this.vaccinationTypeCd;
	}

	public void setVaccinationTypeCd(String vaccinationTypeCd) {
		this.vaccinationTypeCd = vaccinationTypeCd;
	}

	public String getDosage() {
		return this.dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getDoctorComments() {
		return this.doctorComments;
	}

	public void setDoctorComments(String doctorComments) {
		this.doctorComments = doctorComments;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getGivenDate() {
		return this.givenDate;
	}

	public void setGivenDate(Date givenDate) {
		this.givenDate = givenDate;
	}

}