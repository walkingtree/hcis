package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Vaccination entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Vaccination implements java.io.Serializable {

	// Fields

	private String vaccinationCd;
	private Integer version;
	private String vaccinationName;
	private String ageRange;
	private String substituteFor;
	private String activeFlag;
	private String userId;
	private Date lastModifiedDtm;
	private Set patientVaccinationScheduleDetailses = new HashSet(0);
	private Set vaccinationScheduleDetailses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Vaccination() {
	}

	/** minimal constructor */
	public Vaccination(String vaccinationName, String ageRange,
			String activeFlag, String userId, Date lastModifiedDtm) {
		this.vaccinationName = vaccinationName;
		this.ageRange = ageRange;
		this.activeFlag = activeFlag;
		this.userId = userId;
		this.lastModifiedDtm = lastModifiedDtm;
	}

	/** full constructor */
	public Vaccination(String vaccinationName, String ageRange,
			String substituteFor, String activeFlag, String userId,
			Date lastModifiedDtm, Set patientVaccinationScheduleDetailses,
			Set vaccinationScheduleDetailses) {
		this.vaccinationName = vaccinationName;
		this.ageRange = ageRange;
		this.substituteFor = substituteFor;
		this.activeFlag = activeFlag;
		this.userId = userId;
		this.lastModifiedDtm = lastModifiedDtm;
		this.patientVaccinationScheduleDetailses = patientVaccinationScheduleDetailses;
		this.vaccinationScheduleDetailses = vaccinationScheduleDetailses;
	}

	// Property accessors

	public String getVaccinationCd() {
		return this.vaccinationCd;
	}

	public void setVaccinationCd(String vaccinationCd) {
		this.vaccinationCd = vaccinationCd;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getVaccinationName() {
		return this.vaccinationName;
	}

	public void setVaccinationName(String vaccinationName) {
		this.vaccinationName = vaccinationName;
	}

	public String getAgeRange() {
		return this.ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}

	public String getSubstituteFor() {
		return this.substituteFor;
	}

	public void setSubstituteFor(String substituteFor) {
		this.substituteFor = substituteFor;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public Set getPatientVaccinationScheduleDetailses() {
		return this.patientVaccinationScheduleDetailses;
	}

	public void setPatientVaccinationScheduleDetailses(
			Set patientVaccinationScheduleDetailses) {
		this.patientVaccinationScheduleDetailses = patientVaccinationScheduleDetailses;
	}

	public Set getVaccinationScheduleDetailses() {
		return this.vaccinationScheduleDetailses;
	}

	public void setVaccinationScheduleDetailses(Set vaccinationScheduleDetailses) {
		this.vaccinationScheduleDetailses = vaccinationScheduleDetailses;
	}

}