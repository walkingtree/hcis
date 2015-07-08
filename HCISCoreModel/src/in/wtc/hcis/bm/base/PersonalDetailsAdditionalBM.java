/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Rohit
 *
 */
public class PersonalDetailsAdditionalBM implements Serializable {
	static final long serialVersionUID = 200904130523L;
	
	private String 				registrationNumber;
	private String 				height;
	private String 				weight;
	private CodeAndDescription  heightIndicator;
	private CodeAndDescription  weightIndicator;
	private CodeAndDescription 	bloodGroup;
	private CodeAndDescription 	maritalStatus;
	private String 				fatherHusband;

	private CodeAndDescription 	religion;
	private CodeAndDescription 	motherTongue;
	private String 				bloodDonorId;
	private CodeAndDescription 	idProof;
	private String 				idNumber;
	private Date 				idValidUpto;
	private CodeAndDescription 	nationality;
	private String 				patientOccupation;
	private String 				monthlyIncome;
	private CodeAndDescription 	referredBy;
	
	private String 				visaNumber;
	private Date 				visaValidUpto;

	
	public PersonalDetailsAdditionalBM() {
	}
	/**
	 * @return the registrationNumber
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	/**
	 * @param registrationNumber the registrationNumber to set
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}
	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}
	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}
	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	/**
	 * @return the bloodDonorId
	 */
	public String getBloodDonorId() {
		return bloodDonorId;
	}
	/**
	 * @param bloodDonorId the bloodDonorId to set
	 */
	public void setBloodDonorId(String bloodDonorId) {
		this.bloodDonorId = bloodDonorId;
	}
	/**
	 * @return the patientOccupation
	 */
	public String getPatientOccupation() {
		return patientOccupation;
	}
	/**
	 * @param patientOccupation the patientOccupation to set
	 */
	public void setPatientOccupation(String patientOccupation) {
		this.patientOccupation = patientOccupation;
	}
	/**
	 * @return the monthlyIncome
	 */
	public String getMonthlyIncome() {
		return monthlyIncome;
	}
	/**
	 * @param monthlyIncome the monthlyIncome to set
	 */
	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	/**
	 * @return the fatherHusband
	 */
	public String getFatherHusband() {
		return fatherHusband;
	}
	/**
	 * @param fatherHusband the fatherHusband to set
	 */
	public void setFatherHusband(String fatherHusband) {
		this.fatherHusband = fatherHusband;
	}
	/**
	 * @return the visaNumber
	 */
	public String getVisaNumber() {
		return visaNumber;
	}
	/**
	 * @param visaNumber the visaNumber to set
	 */
	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}
	/**
	 * @return the idValidUpto
	 */
	public Date getIdValidUpto() {
		return idValidUpto;
	}
	/**
	 * @param idValidUpto the idValidUpto to set
	 */
	public void setIdValidUpto(Date idValidUpto) {
		this.idValidUpto = idValidUpto;
	}
	/**
	 * @return the visaValidUpto
	 */
	public Date getVisaValidUpto() {
		return visaValidUpto;
	}
	/**
	 * @param visaValidUpto the visaValidUpto to set
	 */
	public void setVisaValidUpto(Date visaValidUpto) {
		this.visaValidUpto = visaValidUpto;
	}
	/**
	 * @return the religion
	 */
	public CodeAndDescription getReligion() {
		return religion;
	}
	/**
	 * @param religion the religion to set
	 */
	public void setReligion(CodeAndDescription religion) {
		this.religion = religion;
	}
	/**
	 * @return the maritalStatus
	 */
	public CodeAndDescription getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(CodeAndDescription maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	/**
	 * @return the idProof
	 */
	public CodeAndDescription getIdProof() {
		return idProof;
	}
	/**
	 * @param idProof the idProof to set
	 */
	public void setIdProof(CodeAndDescription idProof) {
		this.idProof = idProof;
	}
	/**
	 * @return the bloodGroup
	 */
	public CodeAndDescription getBloodGroup() {
		return bloodGroup;
	}
	/**
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(CodeAndDescription bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	/**
	 * @return the motherTongue
	 */
	public CodeAndDescription getMotherTongue() {
		return motherTongue;
	}
	/**
	 * @param motherTongue the motherTongue to set
	 */
	public void setMotherTongue(CodeAndDescription motherTongue) {
		this.motherTongue = motherTongue;
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
	/**
	 * @return the referredBy
	 */
	public CodeAndDescription getReferredBy() {
		return referredBy;
	}
	/**
	 * @param referredBy the referredBy to set
	 */
	public void setReferredBy(CodeAndDescription referredBy) {
		this.referredBy = referredBy;
	}
	public CodeAndDescription getHeightIndicator() {
		return heightIndicator;
	}
	public void setHeightIndicator(CodeAndDescription heightIndicator) {
		this.heightIndicator = heightIndicator;
	}
	public CodeAndDescription getWeightIndicator() {
		return weightIndicator;
	}
	public void setWeightIndicator(CodeAndDescription weightIndicator) {
		this.weightIndicator = weightIndicator;
	}
}
