package com.wtc.hcis.billing.da;

import java.util.Date;

/**
 * BillActivityDetails entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BillActivityDetails implements java.io.Serializable {

	// Fields

	private Long billingActivityDetailsId;
	private BillActivity billActivity;
	private BillInfo billInfo;
	private Date activityTime;
	private String remarks;

	// Constructors

	/** default constructor */
	public BillActivityDetails() {
	}

	/** minimal constructor */
	public BillActivityDetails(BillActivity billActivity, BillInfo billInfo,
			Date activityTime) {
		this.billActivity = billActivity;
		this.billInfo = billInfo;
		this.activityTime = activityTime;
	}

	/** full constructor */
	public BillActivityDetails(BillActivity billActivity, BillInfo billInfo,
			Date activityTime, String remarks) {
		this.billActivity = billActivity;
		this.billInfo = billInfo;
		this.activityTime = activityTime;
		this.remarks = remarks;
	}

	// Property accessors

	public Long getBillingActivityDetailsId() {
		return this.billingActivityDetailsId;
	}

	public void setBillingActivityDetailsId(Long billingActivityDetailsId) {
		this.billingActivityDetailsId = billingActivityDetailsId;
	}

	public BillActivity getBillActivity() {
		return this.billActivity;
	}

	public void setBillActivity(BillActivity billActivity) {
		this.billActivity = billActivity;
	}

	public BillInfo getBillInfo() {
		return this.billInfo;
	}

	public void setBillInfo(BillInfo billInfo) {
		this.billInfo = billInfo;
	}

	public Date getActivityTime() {
		return this.activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}