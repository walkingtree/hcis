package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Appointments entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Appointments implements java.io.Serializable {

	// Fields

	private Integer appointmentNumber;
	private Integer version;
	private Referral referral;
	private Roster roster;
	private Especialty especialty;
	private BookingType bookingType;
	private String phone;
	private String email;
	private AppointmentStatus appointmentStatus;
	private Patient patient;
	private Doctor doctor;
	private Date appointmentDate;
	private String appointmentTypeCode;
	private String apptStartTime;
	private String apptEndTime;
	private String appointmentAgenda;
	private Double consultationCharge;
	private Date capturedDtm;
	private String appointmentRemarks;
	private Integer primaryAppointmentNumber;
	private Date nextVisitDt;
	private Integer dateDimId;
	private Date createDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;
	private Set observationses = new HashSet(0);
	private Set appointmentHistories = new HashSet(0);
	private Set reminderses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Appointments() {
	}

	/** minimal constructor */
	public Appointments(Especialty especialty, Date appointmentDate,
			String appointmentTypeCode) {
		this.especialty = especialty;
		this.appointmentDate = appointmentDate;
		this.appointmentTypeCode = appointmentTypeCode;
	}

	/** full constructor */
	public Appointments(Referral referral, Roster roster,
			Especialty especialty, BookingType bookingType,String phone,String email,
			AppointmentStatus appointmentStatus, Patient patient,
			Doctor doctor, Date appointmentDate, String appointmentTypeCode,
			String apptStartTime, String apptEndTime, String appointmentAgenda,
			Double consultationCharge, Date capturedDtm,Integer primaryAppointmentNumber,
			String appointmentRemarks, Date nextVisitDt, Integer dateDimId,
			Date createDtm, String createdBy, Date lastModifiedDtm,
			String modifiedBy, Set observationses, Set appointmentHistories,
			Set reminderses) {
		this.referral = referral;
		this.roster = roster;
		this.especialty = especialty;
		this.bookingType = bookingType;
		this.phone = phone;
		this.email = email;
		this.appointmentStatus = appointmentStatus;
		this.patient = patient;
		this.doctor = doctor;
		this.appointmentDate = appointmentDate;
		this.appointmentTypeCode = appointmentTypeCode;
		this.apptStartTime = apptStartTime;
		this.apptEndTime = apptEndTime;
		this.primaryAppointmentNumber = primaryAppointmentNumber;
		this.appointmentAgenda = appointmentAgenda;
		this.consultationCharge = consultationCharge;
		this.capturedDtm = capturedDtm;
		this.appointmentRemarks = appointmentRemarks;
		this.nextVisitDt = nextVisitDt;
		this.dateDimId = dateDimId;
		this.createDtm = createDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
		this.observationses = observationses;
		this.appointmentHistories = appointmentHistories;
		this.reminderses = reminderses;
	}

	// Property accessors

	public Integer getAppointmentNumber() {
		return this.appointmentNumber;
	}

	public void setAppointmentNumber(Integer appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Referral getReferral() {
		return this.referral;
	}

	public void setReferral(Referral referral) {
		this.referral = referral;
	}

	public Roster getRoster() {
		return this.roster;
	}

	public void setRoster(Roster roster) {
		this.roster = roster;
	}

	public Especialty getEspecialty() {
		return this.especialty;
	}

	public void setEspecialty(Especialty especialty) {
		this.especialty = especialty;
	}

	public BookingType getBookingType() {
		return this.bookingType;
	}

	public void setBookingType(BookingType bookingType) {
		this.bookingType = bookingType;
	}

	public AppointmentStatus getAppointmentStatus() {
		return this.appointmentStatus;
	}

	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
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

	public Date getAppointmentDate() {
		return this.appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTypeCode() {
		return this.appointmentTypeCode;
	}

	public void setAppointmentTypeCode(String appointmentTypeCode) {
		this.appointmentTypeCode = appointmentTypeCode;
	}

	public String getApptStartTime() {
		return this.apptStartTime;
	}

	public void setApptStartTime(String apptStartTime) {
		this.apptStartTime = apptStartTime;
	}

	public String getApptEndTime() {
		return this.apptEndTime;
	}

	public void setApptEndTime(String apptEndTime) {
		this.apptEndTime = apptEndTime;
	}

	public String getAppointmentAgenda() {
		return this.appointmentAgenda;
	}

	public void setAppointmentAgenda(String appointmentAgenda) {
		this.appointmentAgenda = appointmentAgenda;
	}

	public Double getConsultationCharge() {
		return this.consultationCharge;
	}

	public void setConsultationCharge(Double consultationCharge) {
		this.consultationCharge = consultationCharge;
	}

	public Date getCapturedDtm() {
		return this.capturedDtm;
	}

	public void setCapturedDtm(Date capturedDtm) {
		this.capturedDtm = capturedDtm;
	}

	public String getAppointmentRemarks() {
		return this.appointmentRemarks;
	}

	public void setAppointmentRemarks(String appointmentRemarks) {
		this.appointmentRemarks = appointmentRemarks;
	}

	public Date getNextVisitDt() {
		return this.nextVisitDt;
	}

	public void setNextVisitDt(Date nextVisitDt) {
		this.nextVisitDt = nextVisitDt;
	}

	public Integer getDateDimId() {
		return this.dateDimId;
	}

	public void setDateDimId(Integer dateDimId) {
		this.dateDimId = dateDimId;
	}

	public Date getCreateDtm() {
		return this.createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
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

	public Set getObservationses() {
		return this.observationses;
	}

	public void setObservationses(Set observationses) {
		this.observationses = observationses;
	}

	public Set getAppointmentHistories() {
		return this.appointmentHistories;
	}

	public void setAppointmentHistories(Set appointmentHistories) {
		this.appointmentHistories = appointmentHistories;
	}

	public Set getReminderses() {
		return this.reminderses;
	}

	public void setReminderses(Set reminderses) {
		this.reminderses = reminderses;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPrimaryAppointmentNumber() {
		return this.primaryAppointmentNumber;
	}

	public void setPrimaryAppointmentNumber(Integer primaryAppointmentNumber) {
		this.primaryAppointmentNumber = primaryAppointmentNumber;
	}

}