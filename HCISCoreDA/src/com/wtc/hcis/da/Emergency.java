package com.wtc.hcis.da;

/**
 * Emergency entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Emergency implements java.io.Serializable {

	// Fields

	private String emergencyCode;
	private String description;
	private Short active;

	// Constructors

	/** default constructor */
	public Emergency() {
	}

	/** full constructor */
	public Emergency(String description, Short active) {
		this.description = description;
		this.active = active;
	}

	// Property accessors

	public String getEmergencyCode() {
		return this.emergencyCode;
	}

	public void setEmergencyCode(String emergencyCode) {
		this.emergencyCode = emergencyCode;
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