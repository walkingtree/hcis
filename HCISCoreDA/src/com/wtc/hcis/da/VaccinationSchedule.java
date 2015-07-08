package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * VaccinationSchedule entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaccinationSchedule implements java.io.Serializable {

	// Fields

	private String scheduleName;
	private Integer version;
	private String scheduleDesc;
	private String ageGroup;
	private String userId;
	private Date lastModifiedDt;
	private String activeFlag;
	private Set patientVaccinationSchedules = new HashSet(0);
	private Set patientVaccinationScheduleDetailses = new HashSet(0);
	private Set vaccinationScheduleDetailses = new HashSet(0);

	// Constructors

	/** default constructor */
	public VaccinationSchedule() {
	}

	/** minimal constructor */
	public VaccinationSchedule(String userId, Date lastModifiedDt,
			String activeFlag) {
		this.userId = userId;
		this.lastModifiedDt = lastModifiedDt;
		this.activeFlag = activeFlag;
	}

	/** full constructor */
	public VaccinationSchedule(String scheduleDesc, String ageGroup,
			String userId, Date lastModifiedDt, String activeFlag,
			Set patientVaccinationSchedules,
			Set patientVaccinationScheduleDetailses,
			Set vaccinationScheduleDetailses) {
		this.scheduleDesc = scheduleDesc;
		this.ageGroup = ageGroup;
		this.userId = userId;
		this.lastModifiedDt = lastModifiedDt;
		this.activeFlag = activeFlag;
		this.patientVaccinationSchedules = patientVaccinationSchedules;
		this.patientVaccinationScheduleDetailses = patientVaccinationScheduleDetailses;
		this.vaccinationScheduleDetailses = vaccinationScheduleDetailses;
	}

	// Property accessors

	public String getScheduleName() {
		return this.scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getScheduleDesc() {
		return this.scheduleDesc;
	}

	public void setScheduleDesc(String scheduleDesc) {
		this.scheduleDesc = scheduleDesc;
	}

	public String getAgeGroup() {
		return this.ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
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

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Set getPatientVaccinationSchedules() {
		return this.patientVaccinationSchedules;
	}

	public void setPatientVaccinationSchedules(Set patientVaccinationSchedules) {
		this.patientVaccinationSchedules = patientVaccinationSchedules;
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