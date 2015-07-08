package com.wtc.hcis.da;

import java.util.Date;

/**
 * PatientDetails entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PatientDetails implements java.io.Serializable {

	// Fields

	private Integer patientId;
	private Integer version;
	private Patient patient;
	private IdProofs idProofs;
	private String bloodDonorId;
	private String organDonorId;
	private String organDonatedTo;
	private String idNumber;
	private Date idValidUpto;
	private String visaNumber;
	private Date visaValidUpto;
	private Short smoking;
	private Short drinking;
	private String fitnessActivity;
	private Date createdDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;

	// Constructors

	/** default constructor */
	public PatientDetails() {
	}

	/** minimal constructor */
	public PatientDetails(Patient patient) {
		this.patient = patient;
	}

	/** full constructor */
	public PatientDetails(Patient patient, IdProofs idProofs,
			String bloodDonorId, String organDonorId, String organDonatedTo,
			String idNumber, Date idValidUpto, String visaNumber,
			Date visaValidUpto, Short smoking, Short drinking,
			String fitnessActivity, Date createdDtm, String createdBy,
			Date lastModifiedDtm, String modifiedBy) {
		this.patient = patient;
		this.idProofs = idProofs;
		this.bloodDonorId = bloodDonorId;
		this.organDonorId = organDonorId;
		this.organDonatedTo = organDonatedTo;
		this.idNumber = idNumber;
		this.idValidUpto = idValidUpto;
		this.visaNumber = visaNumber;
		this.visaValidUpto = visaValidUpto;
		this.smoking = smoking;
		this.drinking = drinking;
		this.fitnessActivity = fitnessActivity;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
	}

	// Property accessors

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
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

	public IdProofs getIdProofs() {
		return this.idProofs;
	}

	public void setIdProofs(IdProofs idProofs) {
		this.idProofs = idProofs;
	}

	public String getBloodDonorId() {
		return this.bloodDonorId;
	}

	public void setBloodDonorId(String bloodDonorId) {
		this.bloodDonorId = bloodDonorId;
	}

	public String getOrganDonorId() {
		return this.organDonorId;
	}

	public void setOrganDonorId(String organDonorId) {
		this.organDonorId = organDonorId;
	}

	public String getOrganDonatedTo() {
		return this.organDonatedTo;
	}

	public void setOrganDonatedTo(String organDonatedTo) {
		this.organDonatedTo = organDonatedTo;
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

	public String getVisaNumber() {
		return this.visaNumber;
	}

	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}

	public Date getVisaValidUpto() {
		return this.visaValidUpto;
	}

	public void setVisaValidUpto(Date visaValidUpto) {
		this.visaValidUpto = visaValidUpto;
	}

	public Short getSmoking() {
		return this.smoking;
	}

	public void setSmoking(Short smoking) {
		this.smoking = smoking;
	}

	public Short getDrinking() {
		return this.drinking;
	}

	public void setDrinking(Short drinking) {
		this.drinking = drinking;
	}

	public String getFitnessActivity() {
		return this.fitnessActivity;
	}

	public void setFitnessActivity(String fitnessActivity) {
		this.fitnessActivity = fitnessActivity;
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

}