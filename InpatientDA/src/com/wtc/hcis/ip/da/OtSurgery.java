package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * OtSurgery entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtSurgery implements java.io.Serializable {

	// Fields

	private String surgeryCode;
	private Integer version;
	private String surgeryName;
	private String typeCode;
	private String specialtyCode;
	private Integer doctorRefreshmentTime;
	private Integer totalTimeRequired;
	private String createdBy;
	private Date createdDtm;
	private String modifiedBy;
	private Date lastModifiedDtm;
	private Set otSurgeryAssociations = new HashSet(0);
	private Set otBookings = new HashSet(0);
	private Set otPatientSurgeries = new HashSet(0);
	private Set otSurgeryStatusTimes = new HashSet(0);
	private Set otPatientSurgeryChecklists = new HashSet(0);

	// Constructors

	/** default constructor */
	public OtSurgery() {
	}

	/** minimal constructor */
	public OtSurgery(String surgeryCode, String surgeryName, String typeCode,
			String specialtyCode, String createdBy, Date createdDtm) {
		this.surgeryCode = surgeryCode;
		this.surgeryName = surgeryName;
		this.typeCode = typeCode;
		this.specialtyCode = specialtyCode;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public OtSurgery(String surgeryCode, String surgeryName, String typeCode,
			String specialtyCode, Integer doctorRefreshmentTime,
			Integer totalTimeRequired, String createdBy, Date createdDtm,
			String modifiedBy, Date lastModifiedDtm, Set otSurgeryAssociations,
			Set otBookings, Set otPatientSurgeries, Set otSurgeryStatusTimes,
			Set otPatientSurgeryChecklists) {
		this.surgeryCode = surgeryCode;
		this.surgeryName = surgeryName;
		this.typeCode = typeCode;
		this.specialtyCode = specialtyCode;
		this.doctorRefreshmentTime = doctorRefreshmentTime;
		this.totalTimeRequired = totalTimeRequired;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.modifiedBy = modifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.otSurgeryAssociations = otSurgeryAssociations;
		this.otBookings = otBookings;
		this.otPatientSurgeries = otPatientSurgeries;
		this.otSurgeryStatusTimes = otSurgeryStatusTimes;
		this.otPatientSurgeryChecklists = otPatientSurgeryChecklists;
	}

	// Property accessors

	public String getSurgeryCode() {
		return this.surgeryCode;
	}

	public void setSurgeryCode(String surgeryCode) {
		this.surgeryCode = surgeryCode;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getSurgeryName() {
		return this.surgeryName;
	}

	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getSpecialtyCode() {
		return this.specialtyCode;
	}

	public void setSpecialtyCode(String specialtyCode) {
		this.specialtyCode = specialtyCode;
	}

	public Integer getDoctorRefreshmentTime() {
		return this.doctorRefreshmentTime;
	}

	public void setDoctorRefreshmentTime(Integer doctorRefreshmentTime) {
		this.doctorRefreshmentTime = doctorRefreshmentTime;
	}

	public Integer getTotalTimeRequired() {
		return this.totalTimeRequired;
	}

	public void setTotalTimeRequired(Integer totalTimeRequired) {
		this.totalTimeRequired = totalTimeRequired;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDtm() {
		return this.createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public Set getOtSurgeryAssociations() {
		return this.otSurgeryAssociations;
	}

	public void setOtSurgeryAssociations(Set otSurgeryAssociations) {
		this.otSurgeryAssociations = otSurgeryAssociations;
	}

	public Set getOtBookings() {
		return this.otBookings;
	}

	public void setOtBookings(Set otBookings) {
		this.otBookings = otBookings;
	}

	public Set getOtPatientSurgeries() {
		return this.otPatientSurgeries;
	}

	public void setOtPatientSurgeries(Set otPatientSurgeries) {
		this.otPatientSurgeries = otPatientSurgeries;
	}

	public Set getOtSurgeryStatusTimes() {
		return this.otSurgeryStatusTimes;
	}

	public void setOtSurgeryStatusTimes(Set otSurgeryStatusTimes) {
		this.otSurgeryStatusTimes = otSurgeryStatusTimes;
	}

	public Set getOtPatientSurgeryChecklists() {
		return this.otPatientSurgeryChecklists;
	}

	public void setOtPatientSurgeryChecklists(Set otPatientSurgeryChecklists) {
		this.otPatientSurgeryChecklists = otPatientSurgeryChecklists;
	}

}