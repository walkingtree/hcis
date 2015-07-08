package com.wtc.hcis.da;

/**
 * TreatmentReason entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TreatmentReason implements java.io.Serializable {

	// Fields

	private String reasonCode;
	private String description;
	private Short active;

	// Constructors

	/** default constructor */
	public TreatmentReason() {
	}

	/** full constructor */
	public TreatmentReason(String description, Short active) {
		this.description = description;
		this.active = active;
	}

	// Property accessors

	public String getReasonCode() {
		return this.reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getActive() {
		return this.active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

}