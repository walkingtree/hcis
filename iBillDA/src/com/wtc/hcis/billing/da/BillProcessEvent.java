package com.wtc.hcis.billing.da;

import java.util.Date;

/**
 * BillProcessEvent entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BillProcessEvent implements java.io.Serializable {

	// Fields

	private Long eventSequenceNbr;
	private BillRegister billRegister;
	private BillInfo billInfo;
	private String eventStatus;
	private Date eventCreationTm;
	private Date lastModifiedTime;

	// Constructors

	/** default constructor */
	public BillProcessEvent() {
	}

	/** full constructor */
	public BillProcessEvent(BillRegister billRegister, BillInfo billInfo,
			String eventStatus, Date eventCreationTm, Date lastModifiedTime) {
		this.billRegister = billRegister;
		this.billInfo = billInfo;
		this.eventStatus = eventStatus;
		this.eventCreationTm = eventCreationTm;
		this.lastModifiedTime = lastModifiedTime;
	}

	// Property accessors

	public Long getEventSequenceNbr() {
		return this.eventSequenceNbr;
	}

	public void setEventSequenceNbr(Long eventSequenceNbr) {
		this.eventSequenceNbr = eventSequenceNbr;
	}

	public BillRegister getBillRegister() {
		return this.billRegister;
	}

	public void setBillRegister(BillRegister billRegister) {
		this.billRegister = billRegister;
	}

	public BillInfo getBillInfo() {
		return this.billInfo;
	}

	public void setBillInfo(BillInfo billInfo) {
		this.billInfo = billInfo;
	}

	public String getEventStatus() {
		return this.eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public Date getEventCreationTm() {
		return this.eventCreationTm;
	}

	public void setEventCreationTm(Date eventCreationTm) {
		this.eventCreationTm = eventCreationTm;
	}

	public Date getLastModifiedTime() {
		return this.lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

}