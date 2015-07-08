package com.wtc.hcis.da;

import java.util.Date;

/**
 * PatientAllergies entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PatientAllergies implements java.io.Serializable {

	// Fields

	private Integer allergySeqNum;
	private Integer version;
	private Patient patient;
	private Allergies allergies;
	private Date startDate;
	private Date endDate;

	// Constructors

	/** default constructor */
	public PatientAllergies() {
	}

	/** minimal constructor */
	public PatientAllergies(Patient patient, Allergies allergies, Date startDate) {
		this.patient = patient;
		this.allergies = allergies;
		this.startDate = startDate;
	}

	/** full constructor */
	public PatientAllergies(Patient patient, Allergies allergies,
			Date startDate, Date endDate) {
		this.patient = patient;
		this.allergies = allergies;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	// Property accessors

	public Integer getAllergySeqNum() {
		return this.allergySeqNum;
	}

	public void setAllergySeqNum(Integer allergySeqNum) {
		this.allergySeqNum = allergySeqNum;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Allergies getAllergies() {
		return this.allergies;
	}

	public void setAllergies(Allergies allergies) {
		this.allergies = allergies;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}