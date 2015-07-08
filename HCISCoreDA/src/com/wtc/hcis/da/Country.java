package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * Country entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Country implements java.io.Serializable {

	// Fields

	private String countryCode;
	private String description;
	private String isDefault;
	private Short active;
	private Set referrals = new HashSet(0);

	// Constructors

	/** default constructor */
	public Country() {
	}

	/** full constructor */
	public Country(String description, Short active, Set referrals) {
		this.description = description;
		this.active = active;
		this.referrals = referrals;
	}

	// Property accessors

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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

	public Set getReferrals() {
		return this.referrals;
	}

	public void setReferrals(Set referrals) {
		this.referrals = referrals;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

}