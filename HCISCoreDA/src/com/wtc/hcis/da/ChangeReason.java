package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * ChangeReason entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ChangeReason implements java.io.Serializable {

	// Fields

	private String changeReasonCode;
	private String description;
	private Short active;
	private Set registrationHistories = new HashSet(0);

	// Constructors

	/** default constructor */
	public ChangeReason() {
	}

	/** full constructor */
	public ChangeReason(String description, Short active,
			Set registrationHistories) {
		this.description = description;
		this.active = active;
		this.registrationHistories = registrationHistories;
	}

	// Property accessors

	public String getChangeReasonCode() {
		return this.changeReasonCode;
	}

	public void setChangeReasonCode(String changeReasonCode) {
		this.changeReasonCode = changeReasonCode;
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

	public Set getRegistrationHistories() {
		return this.registrationHistories;
	}

	public void setRegistrationHistories(Set registrationHistories) {
		this.registrationHistories = registrationHistories;
	}

}