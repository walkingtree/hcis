package com.wtc.hcis.billing.da;

import java.util.HashSet;
import java.util.Set;

/**
 * BillRegister entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BillRegister implements java.io.Serializable {

	// Fields

	private String processName;
	private String createDetailsFlag;
	private String syncAsyncType;
	private String activeFlag;
	private Integer displaySequenceNbr;
	private String displayDescription;
	private String implClassName;
	private Set billProcessEvents = new HashSet(0);
	private Set billItemDetailses = new HashSet(0);
	private Set billProcessRoutings = new HashSet(0);

	// Constructors

	/** default constructor */
	public BillRegister() {
	}

	/** minimal constructor */
	public BillRegister(String processName, String createDetailsFlag,
			String syncAsyncType, String activeFlag,
			Integer displaySequenceNbr, String displayDescription,
			String implClassName) {
		this.processName = processName;
		this.createDetailsFlag = createDetailsFlag;
		this.syncAsyncType = syncAsyncType;
		this.activeFlag = activeFlag;
		this.displaySequenceNbr = displaySequenceNbr;
		this.displayDescription = displayDescription;
		this.implClassName = implClassName;
	}

	/** full constructor */
	public BillRegister(String processName, String createDetailsFlag,
			String syncAsyncType, String activeFlag,
			Integer displaySequenceNbr, String displayDescription,
			String implClassName, Set billProcessEvents, Set billItemDetailses,
			Set billProcessRoutings) {
		this.processName = processName;
		this.createDetailsFlag = createDetailsFlag;
		this.syncAsyncType = syncAsyncType;
		this.activeFlag = activeFlag;
		this.displaySequenceNbr = displaySequenceNbr;
		this.displayDescription = displayDescription;
		this.implClassName = implClassName;
		this.billProcessEvents = billProcessEvents;
		this.billItemDetailses = billItemDetailses;
		this.billProcessRoutings = billProcessRoutings;
	}

	// Property accessors

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getCreateDetailsFlag() {
		return this.createDetailsFlag;
	}

	public void setCreateDetailsFlag(String createDetailsFlag) {
		this.createDetailsFlag = createDetailsFlag;
	}

	public String getSyncAsyncType() {
		return this.syncAsyncType;
	}

	public void setSyncAsyncType(String syncAsyncType) {
		this.syncAsyncType = syncAsyncType;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getDisplaySequenceNbr() {
		return this.displaySequenceNbr;
	}

	public void setDisplaySequenceNbr(Integer displaySequenceNbr) {
		this.displaySequenceNbr = displaySequenceNbr;
	}

	public String getDisplayDescription() {
		return this.displayDescription;
	}

	public void setDisplayDescription(String displayDescription) {
		this.displayDescription = displayDescription;
	}

	public String getImplClassName() {
		return this.implClassName;
	}

	public void setImplClassName(String implClassName) {
		this.implClassName = implClassName;
	}

	public Set getBillProcessEvents() {
		return this.billProcessEvents;
	}

	public void setBillProcessEvents(Set billProcessEvents) {
		this.billProcessEvents = billProcessEvents;
	}

	public Set getBillItemDetailses() {
		return this.billItemDetailses;
	}

	public void setBillItemDetailses(Set billItemDetailses) {
		this.billItemDetailses = billItemDetailses;
	}

	public Set getBillProcessRoutings() {
		return this.billProcessRoutings;
	}

	public void setBillProcessRoutings(Set billProcessRoutings) {
		this.billProcessRoutings = billProcessRoutings;
	}

}