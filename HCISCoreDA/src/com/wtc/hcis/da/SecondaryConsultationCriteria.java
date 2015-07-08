package com.wtc.hcis.da;

/**
 * SecondaryConsultationCriteria entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SecondaryConsultationCriteria implements java.io.Serializable {

	// Fields

	private String secondaryConsultationCriteriaCode;
	private Integer version;
	private String numberOfDays;
	private String numberOfVisits;
	private Integer doctorId;
	private Short active;

	// Constructors

	/** default constructor */
	public SecondaryConsultationCriteria() {
	}

	/** full constructor */
	public SecondaryConsultationCriteria(String numberOfDays,
			String numberOfVisits, Integer doctorId, Short active) {
		this.numberOfDays = numberOfDays;
		this.numberOfVisits = numberOfVisits;
		this.doctorId = doctorId;
		this.active = active;
	}

	// Property accessors

	public String getSecondaryConsultationCriteriaCode() {
		return this.secondaryConsultationCriteriaCode;
	}

	public void setSecondaryConsultationCriteriaCode(
			String secondaryConsultationCriteriaCode) {
		this.secondaryConsultationCriteriaCode = secondaryConsultationCriteriaCode;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getNumberOfDays() {
		return this.numberOfDays;
	}

	public void setNumberOfDays(String numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public String getNumberOfVisits() {
		return this.numberOfVisits;
	}

	public void setNumberOfVisits(String numberOfVisits) {
		this.numberOfVisits = numberOfVisits;
	}

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Short getActive() {
		return this.active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

}