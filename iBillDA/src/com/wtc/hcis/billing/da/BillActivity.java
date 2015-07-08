package com.wtc.hcis.billing.da;

import java.util.HashSet;
import java.util.Set;

/**
 * BillActivity entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BillActivity implements java.io.Serializable {

	// Fields

	private String activityTypeId;
	private String activityTypeDesc;
	private Integer activityOrder;
	private Set billActivityDetailses = new HashSet(0);

	// Constructors

	/** default constructor */
	public BillActivity() {
	}

	/** minimal constructor */
	public BillActivity(String activityTypeId, String activityTypeDesc) {
		this.activityTypeId = activityTypeId;
		this.activityTypeDesc = activityTypeDesc;
	}

	/** full constructor */
	public BillActivity(String activityTypeId, String activityTypeDesc,
			Integer activityOrder, Set billActivityDetailses) {
		this.activityTypeId = activityTypeId;
		this.activityTypeDesc = activityTypeDesc;
		this.activityOrder = activityOrder;
		this.billActivityDetailses = billActivityDetailses;
	}

	// Property accessors

	public String getActivityTypeId() {
		return this.activityTypeId;
	}

	public void setActivityTypeId(String activityTypeId) {
		this.activityTypeId = activityTypeId;
	}

	public String getActivityTypeDesc() {
		return this.activityTypeDesc;
	}

	public void setActivityTypeDesc(String activityTypeDesc) {
		this.activityTypeDesc = activityTypeDesc;
	}

	public Integer getActivityOrder() {
		return this.activityOrder;
	}

	public void setActivityOrder(Integer activityOrder) {
		this.activityOrder = activityOrder;
	}

	public Set getBillActivityDetailses() {
		return this.billActivityDetailses;
	}

	public void setBillActivityDetailses(Set billActivityDetailses) {
		this.billActivityDetailses = billActivityDetailses;
	}

}