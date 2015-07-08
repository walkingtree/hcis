package com.wtc.hcis.da;

import java.util.Date;

/**
 * VaccinationScheduleDetails entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaccinationScheduleDetails implements java.io.Serializable {

	// Fields

	private VaccinationScheduleDetailsId id;
	private Integer version;
	private VaccinationSchedule vaccinationSchedule;
	private Vaccination vaccination;
	private Integer periodInDays;
	private String vaccinationTypeCd;
	private String dosage;
	private String age;
	private String deletedFlag;
	private String userId;
	private Date lastModifiedDt;

	// Constructors

	/** default constructor */
	public VaccinationScheduleDetails() {
	}

	/** minimal constructor */
	public VaccinationScheduleDetails(VaccinationScheduleDetailsId id,
			VaccinationSchedule vaccinationSchedule, Vaccination vaccination,
			Integer periodInDays, String userId, Date lastModifiedDt) {
		this.id = id;
		this.vaccinationSchedule = vaccinationSchedule;
		this.vaccination = vaccination;
		this.periodInDays = periodInDays;
		this.userId = userId;
		this.lastModifiedDt = lastModifiedDt;
	}

	/** full constructor */
	public VaccinationScheduleDetails(VaccinationScheduleDetailsId id,
			VaccinationSchedule vaccinationSchedule, Vaccination vaccination,
			Integer periodInDays, String vaccinationTypeCd, String dosage,
			String age, String deletedFlag, String userId, Date lastModifiedDt) {
		this.id = id;
		this.vaccinationSchedule = vaccinationSchedule;
		this.vaccination = vaccination;
		this.periodInDays = periodInDays;
		this.vaccinationTypeCd = vaccinationTypeCd;
		this.dosage = dosage;
		this.age = age;
		this.deletedFlag = deletedFlag;
		this.userId = userId;
		this.lastModifiedDt = lastModifiedDt;
	}

	// Property accessors

	public VaccinationScheduleDetailsId getId() {
		return this.id;
	}

	public void setId(VaccinationScheduleDetailsId id) {
		this.id = id;
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

	public Vaccination getVaccination() {
		return this.vaccination;
	}

	public void setVaccination(Vaccination vaccination) {
		this.vaccination = vaccination;
	}

	public Integer getPeriodInDays() {
		return this.periodInDays;
	}

	public void setPeriodInDays(Integer periodInDays) {
		this.periodInDays = periodInDays;
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

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDeletedFlag() {
		return this.deletedFlag;
	}

	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
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

}