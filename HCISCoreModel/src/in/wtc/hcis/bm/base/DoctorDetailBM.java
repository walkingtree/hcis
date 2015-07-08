package in.wtc.hcis.bm.base;

import java.util.Date;

@SuppressWarnings("serial")
public class DoctorDetailBM implements java.io.Serializable {

	// Fields

	private CodeAndDescription religion;
	private CodeAndDescription saluation;
	private CodeAndDescription gender;
	private CodeAndDescription bloodGroup;
	private CodeAndDescription idProof;
	private CodeAndDescription marital;
	private CodeAndDescription doctor;
	private String firstName;
	private String middleName;
	private String lastName;
	private String workExperience;
	private String dutyStartTime;
	private String dutyEndTime;
	private Boolean permanent;
	private Boolean active;
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
	private CodeAndDescription nationality;
	private String userId;
	
	private ImagePopertyBM imagePopertyBM;

	// Constructors

	/**
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}

	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	/**
	 * @return the referredBy
	 */
	public String getReferredBy() {
		return referredBy;
	}

	/**
	 * @param referredBy the referredBy to set
	 */
	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	/**
	 * @return the nationality
	 */
	public CodeAndDescription getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(CodeAndDescription nationality) {
		this.nationality = nationality;
	}

	/** default constructor */
	public DoctorDetailBM() {
	}

	public CodeAndDescription getReligion() {
		return religion;
	}

	public void setReligion(CodeAndDescription religion) {
		this.religion = religion;
	}

	public CodeAndDescription getSaluation() {
		return saluation;
	}

	public void setSaluation(CodeAndDescription saluation) {
		this.saluation = saluation;
	}

	public CodeAndDescription getGender() {
		return gender;
	}

	public void setGender(CodeAndDescription gender) {
		this.gender = gender;
	}

	public CodeAndDescription getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(CodeAndDescription bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public CodeAndDescription getIdProof() {
		return idProof;
	}

	public void setIdProof(CodeAndDescription idProof) {
		this.idProof = idProof;
	}

	public CodeAndDescription getMarital() {
		return marital;
	}

	public void setMarital(CodeAndDescription marital) {
		this.marital = marital;
	}

	public CodeAndDescription getDoctor() {
		return doctor;
	}

	public void setDoctor(CodeAndDescription doctor) {
		this.doctor = doctor;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public String getDutyStartTime() {
		return dutyStartTime;
	}

	public void setDutyStartTime(String dutyStartTime) {
		this.dutyStartTime = dutyStartTime;
	}

	public String getDutyEndTime() {
		return dutyEndTime;
	}

	public void setDutyEndTime(String dutyEndTime) {
		this.dutyEndTime = dutyEndTime;
	}

	public Boolean getPermanent() {
		return permanent;
	}

	public void setPermanent(Boolean permanent) {
		this.permanent = permanent;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getJoiningDt() {
		return joiningDt;
	}

	public void setJoiningDt(Date joiningDt) {
		this.joiningDt = joiningDt;
	}

	public Date getLeavingDt() {
		return leavingDt;
	}

	public void setLeavingDt(Date leavingDt) {
		this.leavingDt = leavingDt;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getFatherHusbandName() {
		return fatherHusbandName;
	}

	public void setFatherHusbandName(String fatherHusbandName) {
		this.fatherHusbandName = fatherHusbandName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Date getIdValidUpto() {
		return idValidUpto;
	}

	public void setIdValidUpto(Date idValidUpto) {
		this.idValidUpto = idValidUpto;
	}

	public String getBloodDonorId() {
		return bloodDonorId;
	}

	public void setBloodDonorId(String bloodDonorId) {
		this.bloodDonorId = bloodDonorId;
	}

	public String getKnownLanguages() {
		return knownLanguages;
	}

	public void setKnownLanguages(String knownLanguages) {
		this.knownLanguages = knownLanguages;
	}

	public ImagePopertyBM getImagePopertyBM() {
		return imagePopertyBM;
	}

	public void setImagePopertyBM(ImagePopertyBM imagePopertyBM) {
		this.imagePopertyBM = imagePopertyBM;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}