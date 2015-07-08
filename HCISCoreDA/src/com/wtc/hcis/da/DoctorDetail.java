package com.wtc.hcis.da;

import java.util.Date;

/**
 * DoctorDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorDetail implements java.io.Serializable {

	// Fields

	private Integer doctorId;
	private Integer version;
	private Nationality nationality;
	private Religion religion;
	private Gender gender;
	private BloodGroup bloodGroup;
	private IdProofs idProofs;
	private Marital marital;
	private Doctor doctor;
	private String workExperience;
	private String dutyStartTime;
	private String dutyEndTime;
	private Short permanent;
	private Date createdDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;
	private Date joiningDt;
	private Date leavingDt;
	private Date dob;
	private String height;
	private String weight;
	private String fatherHusbandName;
	private String idNumber;
	private Date idValidUpto;
	private String bloodDonorId;
	private String knownLanguages;
	private String qualification;
	private String referredBy;

	// Constructors

	/** default constructor */
	public DoctorDetail() {
	}

	/** minimal constructor */
	public DoctorDetail(Doctor doctor) {
		this.doctor = doctor;
	}

	/** full constructor */
	public DoctorDetail(Nationality nationality, Religion religion,
			Gender gender, BloodGroup bloodGroup, IdProofs idProofs,
			Marital marital, Doctor doctor, String workExperience,
			String dutyStartTime, String dutyEndTime, Short permanent,
			Date createdDtm, String createdBy, Date lastModifiedDtm,
			String modifiedBy, Date joiningDt, Date leavingDt, Date dob,
			String height, String weight, String fatherHusbandName,
			String idNumber, Date idValidUpto, String bloodDonorId,
			String knownLanguages, String qualification, String referredBy) {
		this.nationality = nationality;
		this.religion = religion;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.idProofs = idProofs;
		this.marital = marital;
		this.doctor = doctor;
		this.workExperience = workExperience;
		this.dutyStartTime = dutyStartTime;
		this.dutyEndTime = dutyEndTime;
		this.permanent = permanent;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
		this.joiningDt = joiningDt;
		this.leavingDt = leavingDt;
		this.dob = dob;
		this.height = height;
		this.weight = weight;
		this.fatherHusbandName = fatherHusbandName;
		this.idNumber = idNumber;
		this.idValidUpto = idValidUpto;
		this.bloodDonorId = bloodDonorId;
		this.knownLanguages = knownLanguages;
		this.qualification = qualification;
		this.referredBy = referredBy;
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

	public Nationality getNationality() {
		return this.nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public Religion getReligion() {
		return this.religion;
	}

	public void setReligion(Religion religion) {
		this.religion = religion;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public BloodGroup getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public IdProofs getIdProofs() {
		return this.idProofs;
	}

	public void setIdProofs(IdProofs idProofs) {
		this.idProofs = idProofs;
	}

	public Marital getMarital() {
		return this.marital;
	}

	public void setMarital(Marital marital) {
		this.marital = marital;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getWorkExperience() {
		return this.workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public String getDutyStartTime() {
		return this.dutyStartTime;
	}

	public void setDutyStartTime(String dutyStartTime) {
		this.dutyStartTime = dutyStartTime;
	}

	public String getDutyEndTime() {
		return this.dutyEndTime;
	}

	public void setDutyEndTime(String dutyEndTime) {
		this.dutyEndTime = dutyEndTime;
	}

	public Short getPermanent() {
		return this.permanent;
	}

	public void setPermanent(Short permanent) {
		this.permanent = permanent;
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

	public Date getJoiningDt() {
		return this.joiningDt;
	}

	public void setJoiningDt(Date joiningDt) {
		this.joiningDt = joiningDt;
	}

	public Date getLeavingDt() {
		return this.leavingDt;
	}

	public void setLeavingDt(Date leavingDt) {
		this.leavingDt = leavingDt;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getFatherHusbandName() {
		return this.fatherHusbandName;
	}

	public void setFatherHusbandName(String fatherHusbandName) {
		this.fatherHusbandName = fatherHusbandName;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Date getIdValidUpto() {
		return this.idValidUpto;
	}

	public void setIdValidUpto(Date idValidUpto) {
		this.idValidUpto = idValidUpto;
	}

	public String getBloodDonorId() {
		return this.bloodDonorId;
	}

	public void setBloodDonorId(String bloodDonorId) {
		this.bloodDonorId = bloodDonorId;
	}

	public String getKnownLanguages() {
		return this.knownLanguages;
	}

	public void setKnownLanguages(String knownLanguages) {
		this.knownLanguages = knownLanguages;
	}

	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getReferredBy() {
		return this.referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

}