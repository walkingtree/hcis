/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;

/**
 * @author Rohit
 * 
 */
public class ContactDetailsBM implements Serializable {
	static final long serialVersionUID = 200904130636L;

	private Integer contactDetailsCode;
	private Integer personelId;
	private String houseNumber;
	private String street;
	private String city;
	private String pincode;
	private String phoneNumber;
	private String mobileNumber;
	private String faxNumber;
	private String email;
	private String stayDuration;
	private String firstName;
	private String middleName;
	private String lastName;
	private CodeAndDescription forEntity;
	private CodeAndDescription salutation;
	private CodeAndDescription country;
	private CodeAndDescription state;
	private CodeAndDescription gender;
	private CodeAndDescription contactType;
	private CodeAndDescription relationCode;
	private String createdBy;

	public ContactDetailsBM() {
	}

	/**
	 * @return the contactDetailsCode
	 */
	public Integer getContactDetailsCode() {
		return contactDetailsCode;
	}

	/**
	 * @param contactDetailsCode
	 *            the contactDetailsCode to set
	 */
	public void setContactDetailsCode(Integer contactDetailsCode) {
		this.contactDetailsCode = contactDetailsCode;
	}

	public Integer getPersonelId() {
		return personelId;
	}

	public void setPersonelId(Integer personelId) {
		this.personelId = personelId;
	}

	/**
	 * @return the houseNumber
	 */
	public String getHouseNumber() {
		return houseNumber;
	}

	/**
	 * @param houseNumber
	 *            the houseNumber to set
	 */
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode
	 *            the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *            the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the faxNumber
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * @param faxNumber
	 *            the faxNumber to set
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the stayDuration
	 */
	public String getStayDuration() {
		return stayDuration;
	}

	/**
	 * @param stayDuration
	 *            the stayDuration to set
	 */
	public void setStayDuration(String stayDuration) {
		this.stayDuration = stayDuration;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
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
	 * @param middleName
	 *            the middleName to set
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
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the forEntity
	 */
	public CodeAndDescription getForEntity() {
		return forEntity;
	}

	/**
	 * @param forEntity
	 *            the forEntity to set
	 */
	public void setForEntity(CodeAndDescription forEntity) {
		this.forEntity = forEntity;
	}

	/**
	 * @return the salutation
	 */
	public CodeAndDescription getSalutation() {
		return salutation;
	}

	/**
	 * @param salutation
	 *            the salutation to set
	 */
	public void setSalutation(CodeAndDescription salutation) {
		this.salutation = salutation;
	}

	/**
	 * @return the country
	 */
	public CodeAndDescription getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(CodeAndDescription country) {
		this.country = country;
	}

	/**
	 * @return the state
	 */
	public CodeAndDescription getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(CodeAndDescription state) {
		this.state = state;
	}

	public CodeAndDescription getGender() {
		return gender;
	}

	public void setGender(CodeAndDescription gender) {
		this.gender = gender;
	}

	/**
	 * @return the contactType
	 */
	public CodeAndDescription getContactType() {
		return contactType;
	}

	/**
	 * @param contactType
	 *            the contactType to set
	 */
	public void setContactType(CodeAndDescription contactType) {
		this.contactType = contactType;
	}

	/**
	 * @return the relationCode
	 */
	public CodeAndDescription getRelationCode() {
		return relationCode;
	}

	/**
	 * @param relationCode
	 *            the relationCode to set
	 */
	public void setRelationCode(CodeAndDescription relationCode) {
		this.relationCode = relationCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
