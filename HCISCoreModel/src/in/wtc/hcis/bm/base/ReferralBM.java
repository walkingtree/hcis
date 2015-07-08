/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Alok Ranjan
 * 
 */
@SuppressWarnings("serial")
public class ReferralBM extends ReferralLightBM implements Serializable {
	private String qualification;
	private String phoneNumber;
	private String mobileNumber;
	private String faxNumber;
	private String email;

	private List<ReferralCommissionBM> referralCommissionList;

	private String createdBy;
	private Date createDtm;

	public ReferralBM() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDtm() {
		return createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ReferralCommissionBM> getReferralCommissionList() {
		return referralCommissionList;
	}

	public void setReferralCommissionList(
			List<ReferralCommissionBM> referralCommissionList) {
		this.referralCommissionList = referralCommissionList;
	}

}
