package com.wtc.hcis.billing.da;

import java.util.Date;

/**
 * FnclCharge entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class FnclCharge implements java.io.Serializable {

	// Fields

	private Long fnclChargesId;
	private FnclChargeType fnclChargeType;
	private BillInfo billInfo;
	private String accountId;
	private Double chargeAmount;
	private Date creationDate;
	private String remarks;

	// Constructors

	/** default constructor */
	public FnclCharge() {
	}

	/** minimal constructor */
	public FnclCharge(FnclChargeType fnclChargeType, String accountId,
			Double chargeAmount, Date creationDate) {
		this.fnclChargeType = fnclChargeType;
		this.accountId = accountId;
		this.chargeAmount = chargeAmount;
		this.creationDate = creationDate;
	}

	/** full constructor */
	public FnclCharge(FnclChargeType fnclChargeType, BillInfo billInfo,
			String accountId, Double chargeAmount, Date creationDate,
			String remarks) {
		this.fnclChargeType = fnclChargeType;
		this.billInfo = billInfo;
		this.accountId = accountId;
		this.chargeAmount = chargeAmount;
		this.creationDate = creationDate;
		this.remarks = remarks;
	}

	// Property accessors

	public Long getFnclChargesId() {
		return this.fnclChargesId;
	}

	public void setFnclChargesId(Long fnclChargesId) {
		this.fnclChargesId = fnclChargesId;
	}

	public FnclChargeType getFnclChargeType() {
		return this.fnclChargeType;
	}

	public void setFnclChargeType(FnclChargeType fnclChargeType) {
		this.fnclChargeType = fnclChargeType;
	}

	public BillInfo getBillInfo() {
		return this.billInfo;
	}

	public void setBillInfo(BillInfo billInfo) {
		this.billInfo = billInfo;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Double getChargeAmount() {
		return this.chargeAmount;
	}

	public void setChargeAmount(Double chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}