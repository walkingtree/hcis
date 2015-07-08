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
public class PersonalDetailsBM implements Serializable {
	static final long serialVersionUID = 200904130510L;
	
	private Integer 			registrationNumber;
	private Date 				registrationDate;
	private CodeAndDescription 	registrationType;
	private CodeAndDescription 	registrationStatus;
	private CodeAndDescription 	patientRating;
	private CodeAndDescription 	patientCategory;
	
	private String 				firstName;
	private String 				middleName;
	private String 				lastName;
	private Date 				dateOfBirth;
	private CodeAndDescription 	gender;
	private CodeAndDescription 	title;
	private ImagePopertyBM		imagePopertyBM;

	
	
	public PersonalDetailsBM() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Integer getRegistrationNumber() {
		return registrationNumber;
	}


	public void setRegistrationNumber(Integer registrationNumber) {
		this.registrationNumber = registrationNumber;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the registrationDate
	 */
	public Date getRegistrationDate() {
		return registrationDate;
	}
	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the title
	 */
	public CodeAndDescription getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(CodeAndDescription title) {
		this.title = title;
	}
	/**
	 * @return the registrationType
	 */
	public CodeAndDescription getRegistrationType() {
		return registrationType;
	}
	/**
	 * @param registrationType the registrationType to set
	 */
	public void setRegistrationType(CodeAndDescription registrationType) {
		this.registrationType = registrationType;
	}
	/**
	 * @return the registrationStatus
	 */
	public CodeAndDescription getRegistrationStatus() {
		return registrationStatus;
	}
	/**
	 * @param registrationStatus the registrationStatus to set
	 */
	public void setRegistrationStatus(CodeAndDescription registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
	/**
	 * @return the patientRating
	 */
	public CodeAndDescription getPatientRating() {
		return patientRating;
	}
	/**
	 * @param patientRating the patientRating to set
	 */
	public void setPatientRating(CodeAndDescription patientRating) {
		this.patientRating = patientRating;
	}
	/**
	 * @return the patientCategory
	 */
	public CodeAndDescription getPatientCategory() {
		return patientCategory;
	}
	/**
	 * @param patientCategory the patientCategory to set
	 */
	public void setPatientCategory(CodeAndDescription patientCategory) {
		this.patientCategory = patientCategory;
	}
	/**
	 * @return the gender
	 */
	public CodeAndDescription getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(CodeAndDescription gender) {
		this.gender = gender;
	}


	public ImagePopertyBM getImagePopertyBM() {
		return imagePopertyBM;
	}


	public void setImagePopertyBM(ImagePopertyBM imagePopertyBM) {
		this.imagePopertyBM = imagePopertyBM;
	}
	
}
