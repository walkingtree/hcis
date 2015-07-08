package com.wtc.hcis.da;

import java.util.Date;

/**
 * ReferralPayableId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ReferralPayableId implements java.io.Serializable {

	// Fields

	private Integer referralCode;
	private String commissionProcessTypeCd;
	private Integer referenceNbr;
	private String processReferenceText;
	private Date payableCreateDtm;

	// Constructors

	/** default constructor */
	public ReferralPayableId() {
	}

	/** full constructor */
	public ReferralPayableId(Integer referralCode,
			String commissionProcessTypeCd, Integer referenceNbr,
			String processReferenceText, Date payableCreateDtm) {
		this.referralCode = referralCode;
		this.commissionProcessTypeCd = commissionProcessTypeCd;
		this.referenceNbr = referenceNbr;
		this.processReferenceText = processReferenceText;
		this.payableCreateDtm = payableCreateDtm;
	}

	// Property accessors

	public Integer getReferralCode() {
		return this.referralCode;
	}

	public void setReferralCode(Integer referralCode) {
		this.referralCode = referralCode;
	}

	public String getCommissionProcessTypeCd() {
		return this.commissionProcessTypeCd;
	}

	public void setCommissionProcessTypeCd(String commissionProcessTypeCd) {
		this.commissionProcessTypeCd = commissionProcessTypeCd;
	}

	public Integer getReferenceNbr() {
		return this.referenceNbr;
	}

	public void setReferenceNbr(Integer referenceNbr) {
		this.referenceNbr = referenceNbr;
	}

	public String getProcessReferenceText() {
		return this.processReferenceText;
	}

	public void setProcessReferenceText(String processReferenceText) {
		this.processReferenceText = processReferenceText;
	}

	public Date getPayableCreateDtm() {
		return this.payableCreateDtm;
	}

	public void setPayableCreateDtm(Date payableCreateDtm) {
		this.payableCreateDtm = payableCreateDtm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ReferralPayableId))
			return false;
		ReferralPayableId castOther = (ReferralPayableId) other;

		return ((this.getReferralCode() == castOther.getReferralCode()) || (this
				.getReferralCode() != null
				&& castOther.getReferralCode() != null && this
				.getReferralCode().equals(castOther.getReferralCode())))
				&& ((this.getCommissionProcessTypeCd() == castOther
						.getCommissionProcessTypeCd()) || (this
						.getCommissionProcessTypeCd() != null
						&& castOther.getCommissionProcessTypeCd() != null && this
						.getCommissionProcessTypeCd().equals(
								castOther.getCommissionProcessTypeCd())))
				&& ((this.getReferenceNbr() == castOther.getReferenceNbr()) || (this
						.getReferenceNbr() != null
						&& castOther.getReferenceNbr() != null && this
						.getReferenceNbr().equals(castOther.getReferenceNbr())))
				&& ((this.getProcessReferenceText() == castOther
						.getProcessReferenceText()) || (this
						.getProcessReferenceText() != null
						&& castOther.getProcessReferenceText() != null && this
						.getProcessReferenceText().equals(
								castOther.getProcessReferenceText())))
				&& ((this.getPayableCreateDtm() == castOther
						.getPayableCreateDtm()) || (this.getPayableCreateDtm() != null
						&& castOther.getPayableCreateDtm() != null && this
						.getPayableCreateDtm().equals(
								castOther.getPayableCreateDtm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getReferralCode() == null ? 0 : this.getReferralCode()
						.hashCode());
		result = 37
				* result
				+ (getCommissionProcessTypeCd() == null ? 0 : this
						.getCommissionProcessTypeCd().hashCode());
		result = 37
				* result
				+ (getReferenceNbr() == null ? 0 : this.getReferenceNbr()
						.hashCode());
		result = 37
				* result
				+ (getProcessReferenceText() == null ? 0 : this
						.getProcessReferenceText().hashCode());
		result = 37
				* result
				+ (getPayableCreateDtm() == null ? 0 : this
						.getPayableCreateDtm().hashCode());
		return result;
	}

}