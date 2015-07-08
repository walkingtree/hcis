package com.wtc.hcis.billing.da;

import java.util.HashSet;
import java.util.Set;

/**
 * FnclChargeType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class FnclChargeType implements java.io.Serializable {

	// Fields

	private Integer chargeTypeId;
	private String chargeTypeName;
	private String processName;
	private Integer displaySequenceNbr;
	private String displayDetails;
	private Set fnclCharges = new HashSet(0);

	// Constructors

	/** default constructor */
	public FnclChargeType() {
	}

	/** minimal constructor */
	public FnclChargeType(Integer chargeTypeId, String chargeTypeName,
			String processName) {
		this.chargeTypeId = chargeTypeId;
		this.chargeTypeName = chargeTypeName;
		this.processName = processName;
	}

	/** full constructor */
	public FnclChargeType(Integer chargeTypeId, String chargeTypeName,
			String processName, Integer displaySequenceNbr,
			String displayDetails, Set fnclCharges) {
		this.chargeTypeId = chargeTypeId;
		this.chargeTypeName = chargeTypeName;
		this.processName = processName;
		this.displaySequenceNbr = displaySequenceNbr;
		this.displayDetails = displayDetails;
		this.fnclCharges = fnclCharges;
	}

	// Property accessors

	public Integer getChargeTypeId() {
		return this.chargeTypeId;
	}

	public void setChargeTypeId(Integer chargeTypeId) {
		this.chargeTypeId = chargeTypeId;
	}

	public String getChargeTypeName() {
		return this.chargeTypeName;
	}

	public void setChargeTypeName(String chargeTypeName) {
		this.chargeTypeName = chargeTypeName;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Integer getDisplaySequenceNbr() {
		return this.displaySequenceNbr;
	}

	public void setDisplaySequenceNbr(Integer displaySequenceNbr) {
		this.displaySequenceNbr = displaySequenceNbr;
	}

	public String getDisplayDetails() {
		return this.displayDetails;
	}

	public void setDisplayDetails(String displayDetails) {
		this.displayDetails = displayDetails;
	}

	public Set getFnclCharges() {
		return this.fnclCharges;
	}

	public void setFnclCharges(Set fnclCharges) {
		this.fnclCharges = fnclCharges;
	}

}