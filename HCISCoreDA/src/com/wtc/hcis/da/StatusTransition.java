package com.wtc.hcis.da;

import java.util.Date;

/**
 * StatusTransition entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class StatusTransition implements java.io.Serializable {

	// Fields

	private Integer id;
	private String context;
	private String input1;
	private String input2;
	private String fromStatus;
	private String toStatus;
	private String createdBy;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public StatusTransition() {
	}

	/** minimal constructor */
	public StatusTransition(String context, String fromStatus, String toStatus,
			String createdBy, Date createdDtm) {
		this.context = context;
		this.fromStatus = fromStatus;
		this.toStatus = toStatus;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public StatusTransition(String context, String input1, String input2,
			String fromStatus, String toStatus, String createdBy,
			Date createdDtm) {
		this.context = context;
		this.input1 = input1;
		this.input2 = input2;
		this.fromStatus = fromStatus;
		this.toStatus = toStatus;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getInput1() {
		return this.input1;
	}

	public void setInput1(String input1) {
		this.input1 = input1;
	}

	public String getInput2() {
		return this.input2;
	}

	public void setInput2(String input2) {
		this.input2 = input2;
	}

	public String getFromStatus() {
		return this.fromStatus;
	}

	public void setFromStatus(String fromStatus) {
		this.fromStatus = fromStatus;
	}

	public String getToStatus() {
		return this.toStatus;
	}

	public void setToStatus(String toStatus) {
		this.toStatus = toStatus;
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