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
public class SponsorsBM implements Serializable {
	static final long serialVersionUID = 200904130611L;
	
	private CodeAndDescription 	sponsorType;
	private CodeAndDescription 	sponsorName;
	private CodeAndDescription 	insuranceCompany;
	private CodeAndDescription 	sponsorStatus;
	private CodeAndDescription 	state;
	private CodeAndDescription 	country;
	private String 				policyType;
	private String 				policyNumber;
	private String 				company;
	private String 				SumInsured;
	private String 				premiumAmount;
	private String 				sumExhausted;
	private String 				mediclaimPercentage;
	private String 				houseNumber;
	private String 				street;
	private String 				district;
	private String 				pincode;
	private String 				phoneNumber;
	private String 				mobileNumber;
	private String 				faxNumber;
	private String 				email;
	private Date 				effectiveFrom;
	private Date 				effectiveTo;
	
	public SponsorsBM() {
	}
	/**
	 * @return the sponsorType
	 */
	public CodeAndDescription getSponsorType() {
		return sponsorType;
	}
	/**
	 * @param sponsorType the sponsorType to set
	 */
	public void setSponsorType(CodeAndDescription sponsorType) {
		this.sponsorType = sponsorType;
	}
	/**
	 * @return the sponsorName
	 */
	public CodeAndDescription getSponsorName() {
		return sponsorName;
	}
	/**
	 * @param sponsorName the sponsorName to set
	 */
	public void setSponsorName(CodeAndDescription sponsorName) {
		this.sponsorName = sponsorName;
	}
	/**
	 * @return the insuranceCompany
	 */
	public CodeAndDescription getInsuranceCompany() {
		return insuranceCompany;
	}
	/**
	 * @param insuranceCompany the insuranceCompany to set
	 */
	public void setInsuranceCompany(CodeAndDescription insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	/**
	 * @return the sponsorStatus
	 */
	public CodeAndDescription getSponsorStatus() {
		return sponsorStatus;
	}
	/**
	 * @param sponsorStatus the sponsorStatus to set
	 */
	public void setSponsorStatus(CodeAndDescription sponsorStatus) {
		this.sponsorStatus = sponsorStatus;
	}
	/**
	 * @return the state
	 */
	public CodeAndDescription getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(CodeAndDescription state) {
		this.state = state;
	}
	/**
	 * @return the country
	 */
	public CodeAndDescription getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(CodeAndDescription country) {
		this.country = country;
	}
	/**
	 * @return the policyType
	 */
	public String getPolicyType() {
		return policyType;
	}
	/**
	 * @param policyType the policyType to set
	 */
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	/**
	 * @return the policyNumber
	 */
	public String getPolicyNumber() {
		return policyNumber;
	}
	/**
	 * @param policyNumber the policyNumber to set
	 */
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * @return the sumInsured
	 */
	public String getSumInsured() {
		return SumInsured;
	}
	/**
	 * @param sumInsured the sumInsured to set
	 */
	public void setSumInsured(String sumInsured) {
		SumInsured = sumInsured;
	}
	/**
	 * @return the premiumAmount
	 */
	public String getPremiumAmount() {
		return premiumAmount;
	}
	/**
	 * @param premiumAmount the premiumAmount to set
	 */
	public void setPremiumAmount(String premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	/**
	 * @return the sumExhausted
	 */
	public String getSumExhausted() {
		return sumExhausted;
	}
	/**
	 * @param sumExhausted the sumExhausted to set
	 */
	public void setSumExhausted(String sumExhausted) {
		this.sumExhausted = sumExhausted;
	}
	/**
	 * @return the mediclaimPercentage
	 */
	public String getMediclaimPercentage() {
		return mediclaimPercentage;
	}
	/**
	 * @param mediclaimPercentage the mediclaimPercentage to set
	 */
	public void setMediclaimPercentage(String mediclaimPercentage) {
		this.mediclaimPercentage = mediclaimPercentage;
	}
	/**
	 * @return the houseNumber
	 */
	public String getHouseNumber() {
		return houseNumber;
	}
	/**
	 * @param houseNumber the houseNumber to set
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
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}
	/**
	 * @param pincode the pincode to set
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
	 * @param phoneNumber the phoneNumber to set
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
	 * @param mobileNumber the mobileNumber to set
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
	 * @param faxNumber the faxNumber to set
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
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the effectiveFrom
	 */
	public Date getEffectiveFrom() {
		return effectiveFrom;
	}
	/**
	 * @param effectiveFrom the effectiveFrom to set
	 */
	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}
	/**
	 * @return the effectiveTo
	 */
	public Date getEffectiveTo() {
		return effectiveTo;
	}
	/**
	 * @param effectiveTo the effectiveTo to set
	 */
	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

}
