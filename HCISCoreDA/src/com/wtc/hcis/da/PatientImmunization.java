package com.wtc.hcis.da;

import java.util.Date;

/**
 * PatientImmunization entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PatientImmunization implements java.io.Serializable {

	// Fields

	private Integer code;
	private Integer version;
	private Patient patient;
	private Immunization immunization;
	private Date immunizationDtm;
	private Date createDt;

	// Constructors

	/** default constructor */
	public PatientImmunization() {
	}

	/** full constructor */
	public PatientImmunization(Patient patient, Immunization immunization,
			Date immunizationDtm, Date createDt) {
		this.patient = patient;
		this.immunization = immunization;
		this.immunizationDtm = immunizationDtm;
		this.createDt = createDt;
	}

	// Property accessors

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
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

	public Immunization getImmunization() {
		return this.immunization;
	}

	public void setImmunization(Immunization immunization) {
		this.immunization = immunization;
	}

	public Date getImmunizationDtm() {
		return this.immunizationDtm;
	}

	public void setImmunizationDtm(Date immunizationDtm) {
		this.immunizationDtm = immunizationDtm;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

}