package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * ReferralCommissionProcess entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ReferralCommissionProcess implements java.io.Serializable {

	// Fields

	private String commissionTypeCode;
	private String commissionTypeDesc;
	private String active;
	private Set referralPayables = new HashSet(0);
	private Set referralCommissions = new HashSet(0);

	// Constructors

	/** default constructor */
	public ReferralCommissionProcess() {
	}

	/** minimal constructor */
	public ReferralCommissionProcess(String commissionTypeDesc, String active) {
		this.commissionTypeDesc = commissionTypeDesc;
		this.active = active;
	}

	/** full constructor */
	public ReferralCommissionProcess(String commissionTypeDesc, String active,
			Set referralPayables, Set referralCommissions) {
		this.commissionTypeDesc = commissionTypeDesc;
		this.active = active;
		this.referralPayables = referralPayables;
		this.referralCommissions = referralCommissions;
	}

	// Property accessors

	public String getCommissionTypeCode() {
		return this.commissionTypeCode;
	}

	public void setCommissionTypeCode(String commissionTypeCode) {
		this.commissionTypeCode = commissionTypeCode;
	}

	public String getCommissionTypeDesc() {
		return this.commissionTypeDesc;
	}

	public void setCommissionTypeDesc(String commissionTypeDesc) {
		this.commissionTypeDesc = commissionTypeDesc;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Set getReferralPayables() {
		return this.referralPayables;
	}

	public void setReferralPayables(Set referralPayables) {
		this.referralPayables = referralPayables;
	}

	public Set getReferralCommissions() {
		return this.referralCommissions;
	}

	public void setReferralCommissions(Set referralCommissions) {
		this.referralCommissions = referralCommissions;
	}

}