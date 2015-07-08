/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class ReferralLightBM implements Serializable {

	private CodeAndDescription referralType;
	
	private Integer referralCode;
	private String referralName;

	private String referralAddress;
	
	private String city;
	private CodeAndDescription country;
	private CodeAndDescription state;
	private String pincode;
	private String preferredContactTime;
	
	public ReferralLightBM() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CodeAndDescription getReferralType() {
		return referralType;
	}

	public void setReferralType(CodeAndDescription referralType) {
		this.referralType = referralType;
	}

	public Integer getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(Integer referralCode) {
		this.referralCode = referralCode;
	}

	public String getReferralName() {
		return referralName;
	}

	public void setReferralName(String referralName) {
		this.referralName = referralName;
	}

	public String getReferralAddress() {
		return referralAddress;
	}

	public void setReferralAddress(String referralAddress) {
		this.referralAddress = referralAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public CodeAndDescription getCountry() {
		return country;
	}

	public void setCountry(CodeAndDescription country) {
		this.country = country;
	}

	public CodeAndDescription getState() {
		return state;
	}

	public void setState(CodeAndDescription state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPreferredContactTime() {
		return preferredContactTime;
	}

	public void setPreferredContactTime(String preferredContactTime) {
		this.preferredContactTime = preferredContactTime;
	}

	
}
