package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Doctor entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Doctor implements java.io.Serializable {

	// Fields

	private Integer doctorId;
	private Integer version;
	private Saluation saluation;
	private String firstName;
	private String middleName;
	private String lastName;
	private String fullName;
	private String active;
	private Date createdDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;
	private Set patientVaccinationScheduleDetailses = new HashSet(0);
	private Set observationses = new HashSet(0);
	private Set labPatientTestDetails = new HashSet(0);
	private Set patientVaccinationSchedules = new HashSet(0);
	private Set labRequisitionOrders = new HashSet(0);
	private Set doctorEspecialties = new HashSet(0);
	private Set assignedPackages = new HashSet(0);
	private Set doctorDetails = new HashSet(0);
	private Set appointmentses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Doctor() {
	}

	/** minimal constructor */
	public Doctor(String fullName) {
		this.fullName = fullName;
	}

	/** full constructor */
	public Doctor(Saluation saluation, String firstName, String middleName,
			String lastName, String fullName, String active, Date createdDtm,
			String createdBy, Date lastModifiedDtm, String modifiedBy,
			Set patientVaccinationScheduleDetailses, Set observationses,
			Set labPatientTestDetails, Set patientVaccinationSchedules,
			Set labRequisitionOrders, Set doctorEspecialties,
			Set assignedPackages, Set doctorDetails, Set appointmentses) {
		this.saluation = saluation;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.active = active;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
		this.patientVaccinationScheduleDetailses = patientVaccinationScheduleDetailses;
		this.observationses = observationses;
		this.labPatientTestDetails = labPatientTestDetails;
		this.patientVaccinationSchedules = patientVaccinationSchedules;
		this.labRequisitionOrders = labRequisitionOrders;
		this.doctorEspecialties = doctorEspecialties;
		this.assignedPackages = assignedPackages;
		this.doctorDetails = doctorDetails;
		this.appointmentses = appointmentses;
	}

	// Property accessors

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Saluation getSaluation() {
		return this.saluation;
	}

	public void setSaluation(Saluation saluation) {
		this.saluation = saluation;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public Set getPatientVaccinationScheduleDetailses() {
		return this.patientVaccinationScheduleDetailses;
	}

	public void setPatientVaccinationScheduleDetailses(
			Set patientVaccinationScheduleDetailses) {
		this.patientVaccinationScheduleDetailses = patientVaccinationScheduleDetailses;
	}

	public Set getObservationses() {
		return this.observationses;
	}

	public void setObservationses(Set observationses) {
		this.observationses = observationses;
	}

	public Set getLabPatientTestDetails() {
		return this.labPatientTestDetails;
	}

	public void setLabPatientTestDetails(Set labPatientTestDetails) {
		this.labPatientTestDetails = labPatientTestDetails;
	}

	public Set getPatientVaccinationSchedules() {
		return this.patientVaccinationSchedules;
	}

	public void setPatientVaccinationSchedules(Set patientVaccinationSchedules) {
		this.patientVaccinationSchedules = patientVaccinationSchedules;
	}

	public Set getLabRequisitionOrders() {
		return this.labRequisitionOrders;
	}

	public void setLabRequisitionOrders(Set labRequisitionOrders) {
		this.labRequisitionOrders = labRequisitionOrders;
	}

	public Set getDoctorEspecialties() {
		return this.doctorEspecialties;
	}

	public void setDoctorEspecialties(Set doctorEspecialties) {
		this.doctorEspecialties = doctorEspecialties;
	}

	public Set getAssignedPackages() {
		return this.assignedPackages;
	}

	public void setAssignedPackages(Set assignedPackages) {
		this.assignedPackages = assignedPackages;
	}

	public Set getDoctorDetails() {
		return this.doctorDetails;
	}

	public void setDoctorDetails(Set doctorDetails) {
		this.doctorDetails = doctorDetails;
	}

	public Set getAppointmentses() {
		return this.appointmentses;
	}

	public void setAppointmentses(Set appointmentses) {
		this.appointmentses = appointmentses;
	}

}