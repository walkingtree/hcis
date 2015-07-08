package com.wtc.hcis.da;

import java.util.Date;

/**
 * ReferralPayable entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ReferralPayable implements java.io.Serializable {

	// Fields

	private ReferralPayableId id;
	private Integer version;
	private Referral referral;
	private Patient patient;
	private ReferralCommissionProcess referralCommissionProcess;
	private Date eventDtm;
	private Double payableAmt;
	private String accounted;
	private Date processedDtm;
	private String createdBy;

	// Constructors

	/** default constructor */
	public ReferralPayable() {
	}

	/** minimal constructor */
	public ReferralPayable(ReferralPayableId id, Referral referral,
			Patient patient,
			ReferralCommissionProcess referralCommissionProcess, Date eventDtm,
			Double payableAmt, String accounted, String createdBy) {
		this.id = id;
		this.referral = referral;
		this.patient = patient;
		this.referralCommissionProcess = referralCommissionProcess;
		this.eventDtm = eventDtm;
		this.payableAmt = payableAmt;
		this.accounted = accounted;
		this.createdBy = createdBy;
	}

	/** full constructor */
	public ReferralPayable(ReferralPayableId id, Referral referral,
			Patient patient,
			ReferralCommissionProcess referralCommissionProcess, Date eventDtm,
			Double payableAmt, String accounted, Date processedDtm,
			String createdBy) {
		this.id = id;
		this.referral = referral;
		this.patient = patient;
		this.referralCommissionProcess = referralCommissionProcess;
		this.eventDtm = eventDtm;
		this.payableAmt = payableAmt;
		this.accounted = accounted;
		this.processedDtm = processedDtm;
		this.createdBy = createdBy;
	}

	// Property accessors

	public ReferralPayableId getId() {
		return this.id;
	}

	public void setId(ReferralPayableId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Referral getReferral() {
		return this.referral;
	}

	public void setReferral(Referral referral) {
		this.referral = referral;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ReferralCommissionProcess getReferralCommissionProcess() {
		return this.referralCommissionProcess;
	}

	public void setReferralCommissionProcess(
			ReferralCommissionProcess referralCommissionProcess) {
		this.referralCommissionProcess = referralCommissionProcess;
	}

	public Date getEventDtm() {
		return this.eventDtm;
	}

	public void setEventDtm(Date eventDtm) {
		this.eventDtm = eventDtm;
	}

	public Double getPayableAmt() {
		return this.payableAmt;
	}

	public void setPayableAmt(Double payableAmt) {
		this.payableAmt = payableAmt;
	}

	public String getAccounted() {
		return this.accounted;
	}

	public void setAccounted(String accounted) {
		this.accounted = accounted;
	}

	public Date getProcessedDtm() {
		return this.processedDtm;
	}

	public void setProcessedDtm(Date processedDtm) {
		this.processedDtm = processedDtm;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}