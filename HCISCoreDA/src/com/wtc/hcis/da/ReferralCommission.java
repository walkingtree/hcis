package com.wtc.hcis.da;

import java.util.Date;

/**
 * ReferralCommission entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ReferralCommission implements java.io.Serializable {

	// Fields

	private Integer seqNbr;
	private Referral referral;
	private ReferralCommissionProcess referralCommissionProcess;
	private String pctAbsInd;
	private Double pctAbsValue;
	private String createdBy;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public ReferralCommission() {
	}

	/** full constructor */
	public ReferralCommission(Referral referral,
			ReferralCommissionProcess referralCommissionProcess,
			String pctAbsInd, Double pctAbsValue, String createdBy,
			Date createdDtm) {
		this.referral = referral;
		this.referralCommissionProcess = referralCommissionProcess;
		this.pctAbsInd = pctAbsInd;
		this.pctAbsValue = pctAbsValue;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public Integer getSeqNbr() {
		return this.seqNbr;
	}

	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}

	public Referral getReferral() {
		return this.referral;
	}

	public void setReferral(Referral referral) {
		this.referral = referral;
	}

	public ReferralCommissionProcess getReferralCommissionProcess() {
		return this.referralCommissionProcess;
	}

	public void setReferralCommissionProcess(
			ReferralCommissionProcess referralCommissionProcess) {
		this.referralCommissionProcess = referralCommissionProcess;
	}

	public String getPctAbsInd() {
		return this.pctAbsInd;
	}

	public void setPctAbsInd(String pctAbsInd) {
		this.pctAbsInd = pctAbsInd;
	}

	public Double getPctAbsValue() {
		return this.pctAbsValue;
	}

	public void setPctAbsValue(Double pctAbsValue) {
		this.pctAbsValue = pctAbsValue;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDtm() {
		return this.createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

}