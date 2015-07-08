/**
 * 
 */
package in.wtc.lims.bm;

import java.io.Serializable;

/**
 * @author Asha
 * 
 */
public class ContactDetailsBM implements Serializable {
	static final long serialVersionUID = 200904130636L;
	
	
	private String personName ;
	private Integer contactDetailsCode;
	private String street;
	private String locality;
	private String city;
	private String phoneNumber;
	private String mobileNumber;
	private String faxNumber;
	private String email;
	private CodeAndDescription country;
	private CodeAndDescription state;
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


	/**
	 * @return the houseNumber
	 */
	public String getLocality() {
		return locality;
	}

	/**
	 * @param houseNumber
	 *            the houseNumber to set
	 */
	public void setLocality(String locality) {
		this.locality = locality;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

}
