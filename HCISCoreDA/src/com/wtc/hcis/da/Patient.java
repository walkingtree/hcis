package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Patient entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Patient implements java.io.Serializable {

	// Fields

	private Integer patientId;
	private Integer version;
	private Referral referral;
	private Nationality nationality;
	private MotherTongue motherTongue;
	private PatientCategory patientCategory;
	private Religion religion;
	private Saluation saluation;
	private RegistrationType registrationType;
	private PatientRating patientRating;
	private Gender gender;
	private BloodGroup bloodGroup;
	private RegistrationStatus registrationStatus;
	private Marital marital;
	private Date registrationDate;
	private String firstName;
	private String middleName;
	private String lastName;
	private String fullName;
	private Date dateOfBirth;
	private String monthlyIncome;
	private String occupation;
	private String height;
	private String heightInd;
	private String weight;
	private String weightInd;
	private String fatherHusband;
	private String image;
	private Integer currentContactDetailId;
	private Integer permanentContactDetailId;
	private Date createDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;
	private Set labRequisitionOrders = new HashSet(0);
	private Set labPatientTestDetails = new HashSet(0);
	private Set assignedPackages = new HashSet(0);
	private Set referralPayables = new HashSet(0);
	private Set registrationHistories = new HashSet(0);
	private Set appointmentses = new HashSet(0);
	private Set patientVaccinationSchedules = new HashSet(0);
	private Set assignedServiceses = new HashSet(0);
	private Set patientVaccinationScheduleDetailses = new HashSet(0);
	private Set patientAllergieses = new HashSet(0);
	private Set patientImmunizations = new HashSet(0);
	private Set patientDetailses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Patient() {
	}

	/** minimal constructor */
	public Patient(Date registrationDate, String fullName,
			Integer currentContactDetailId) {
		this.registrationDate = registrationDate;
		this.fullName = fullName;
		this.currentContactDetailId = currentContactDetailId;
	}

	/** full constructor */
	public Patient(Referral referral, Nationality nationality,
			MotherTongue motherTongue, PatientCategory patientCategory,
			Religion religion, Saluation saluation,
			RegistrationType registrationType, PatientRating patientRating,
			Gender gender, BloodGroup bloodGroup,
			RegistrationStatus registrationStatus, Marital marital,
			Date registrationDate, String firstName, String middleName,
			String lastName, String fullName, Date dateOfBirth,
			String monthlyIncome, String occupation, String height,
			String heightInd, String weight, String weightInd,
			String fatherHusband, String image, Integer currentContactDetailId,
			Integer permanentContactDetailId, Date createDtm, String createdBy,
			Date lastModifiedDtm, String modifiedBy, Set labRequisitionOrders,
			Set labPatientTestDetails, Set assignedPackages,
			Set referralPayables, Set registrationHistories,
			Set appointmentses, Set patientVaccinationSchedules,
			Set assignedServiceses, Set patientVaccinationScheduleDetailses,
			Set patientAllergieses, Set patientImmunizations,
			Set patientDetailses) {
		this.referral = referral;
		this.nationality = nationality;
		this.motherTongue = motherTongue;
		this.patientCategory = patientCategory;
		this.religion = religion;
		this.saluation = saluation;
		this.registrationType = registrationType;
		this.patientRating = patientRating;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.registrationStatus = registrationStatus;
		this.marital = marital;
		this.registrationDate = registrationDate;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.monthlyIncome = monthlyIncome;
		this.occupation = occupation;
		this.height = height;
		this.heightInd = heightInd;
		this.weight = weight;
		this.weightInd = weightInd;
		this.fatherHusband = fatherHusband;
		this.image = image;
		this.currentContactDetailId = currentContactDetailId;
		this.permanentContactDetailId = permanentContactDetailId;
		this.createDtm = createDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
		this.labRequisitionOrders = labRequisitionOrders;
		this.labPatientTestDetails = labPatientTestDetails;
		this.assignedPackages = assignedPackages;
		this.referralPayables = referralPayables;
		this.registrationHistories = registrationHistories;
		this.appointmentses = appointmentses;
		this.patientVaccinationSchedules = patientVaccinationSchedules;
		this.assignedServiceses = assignedServiceses;
		this.patientVaccinationScheduleDetailses = patientVaccinationScheduleDetailses;
		this.patientAllergieses = patientAllergieses;
		this.patientImmunizations = patientImmunizations;
		this.patientDetailses = patientDetailses;
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

	public Referral getReferral() {
		return this.referral;
	}

	public void setReferral(Referral referral) {
		this.referral = referral;
	}

	public Nationality getNationality() {
		return this.nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public MotherTongue getMotherTongue() {
		return this.motherTongue;
	}

	public void setMotherTongue(MotherTongue motherTongue) {
		this.motherTongue = motherTongue;
	}

	public PatientCategory getPatientCategory() {
		return this.patientCategory;
	}

	public void setPatientCategory(PatientCategory patientCategory) {
		this.patientCategory = patientCategory;
	}

	public Religion getReligion() {
		return this.religion;
	}

	public void setReligion(Religion religion) {
		this.religion = religion;
	}

	public Saluation getSaluation() {
		return this.saluation;
	}

	public void setSaluation(Saluation saluation) {
		this.saluation = saluation;
	}

	public RegistrationType getRegistrationType() {
		return this.registrationType;
	}

	public void setRegistrationType(RegistrationType registrationType) {
		this.registrationType = registrationType;
	}

	public PatientRating getPatientRating() {
		return this.patientRating;
	}

	public void setPatientRating(PatientRating patientRating) {
		this.patientRating = patientRating;
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

	public RegistrationStatus getRegistrationStatus() {
		return this.registrationStatus;
	}

	public void setRegistrationStatus(RegistrationStatus registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public Marital getMarital() {
		return this.marital;
	}

	public void setMarital(Marital marital) {
		this.marital = marital;
	}

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMonthlyIncome() {
		return this.monthlyIncome;
	}

	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getHeightInd() {
		return this.heightInd;
	}

	public void setHeightInd(String heightInd) {
		this.heightInd = heightInd;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWeightInd() {
		return this.weightInd;
	}

	public void setWeightInd(String weightInd) {
		this.weightInd = weightInd;
	}

	public String getFatherHusband() {
		return this.fatherHusband;
	}

	public void setFatherHusband(String fatherHusband) {
		this.fatherHusband = fatherHusband;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getCurrentContactDetailId() {
		return this.currentContactDetailId;
	}

	public void setCurrentContactDetailId(Integer currentContactDetailId) {
		this.currentContactDetailId = currentContactDetailId;
	}

	public Integer getPermanentContactDetailId() {
		return this.permanentContactDetailId;
	}

	public void setPermanentContactDetailId(Integer permanentContactDetailId) {
		this.permanentContactDetailId = permanentContactDetailId;
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

	public Set getLabRequisitionOrders() {
		return this.labRequisitionOrders;
	}

	public void setLabRequisitionOrders(Set labRequisitionOrders) {
		this.labRequisitionOrders = labRequisitionOrders;
	}

	public Set getLabPatientTestDetails() {
		return this.labPatientTestDetails;
	}

	public void setLabPatientTestDetails(Set labPatientTestDetails) {
		this.labPatientTestDetails = labPatientTestDetails;
	}

	public Set getAssignedPackages() {
		return this.assignedPackages;
	}

	public void setAssignedPackages(Set assignedPackages) {
		this.assignedPackages = assignedPackages;
	}

	public Set getReferralPayables() {
		return this.referralPayables;
	}

	public void setReferralPayables(Set referralPayables) {
		this.referralPayables = referralPayables;
	}

	public Set getRegistrationHistories() {
		return this.registrationHistories;
	}

	public void setRegistrationHistories(Set registrationHistories) {
		this.registrationHistories = registrationHistories;
	}

	public Set getAppointmentses() {
		return this.appointmentses;
	}

	public void setAppointmentses(Set appointmentses) {
		this.appointmentses = appointmentses;
	}

	public Set getPatientVaccinationSchedules() {
		return this.patientVaccinationSchedules;
	}

	public void setPatientVaccinationSchedules(Set patientVaccinationSchedules) {
		this.patientVaccinationSchedules = patientVaccinationSchedules;
	}

	public Set getAssignedServiceses() {
		return this.assignedServiceses;
	}

	public void setAssignedServiceses(Set assignedServiceses) {
		this.assignedServiceses = assignedServiceses;
	}

	public Set getPatientVaccinationScheduleDetailses() {
		return this.patientVaccinationScheduleDetailses;
	}

	public void setPatientVaccinationScheduleDetailses(
			Set patientVaccinationScheduleDetailses) {
		this.patientVaccinationScheduleDetailses = patientVaccinationScheduleDetailses;
	}

	public Set getPatientAllergieses() {
		return this.patientAllergieses;
	}

	public void setPatientAllergieses(Set patientAllergieses) {
		this.patientAllergieses = patientAllergieses;
	}

	public Set getPatientImmunizations() {
		return this.patientImmunizations;
	}

	public void setPatientImmunizations(Set patientImmunizations) {
		this.patientImmunizations = patientImmunizations;
	}

	public Set getPatientDetailses() {
		return this.patientDetailses;
	}

	public void setPatientDetailses(Set patientDetailses) {
		this.patientDetailses = patientDetailses;
	}

}