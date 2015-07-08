package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * CreditClass entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CreditClass implements java.io.Serializable {

	// Fields

	private String creditClassCd;
	private String description;
	private Set claimSponsors = new HashSet(0);

	// Constructors

	/** default constructor */
	public CreditClass() {
	}

	/** minimal constructor */
	public CreditClass(String creditClassCd) {
		this.creditClassCd = creditClassCd;
	}

	/** full constructor */
	public CreditClass(String creditClassCd, String description,
			Set claimSponsors) {
		this.creditClassCd = creditClassCd;
		this.description = description;
		this.claimSponsors = claimSponsors;
	}

	// Property accessors

	public String getCreditClassCd() {
		return this.creditClassCd;
	}

	public void setCreditClassCd(String creditClassCd) {
		this.creditClassCd = creditClassCd;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getClaimSponsors() {
		return this.claimSponsors;
	}

	public void setClaimSponsors(Set claimSponsors) {
		this.claimSponsors = claimSponsors;
	}

}