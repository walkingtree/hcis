package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * SponsorType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SponsorType implements java.io.Serializable {

	// Fields

	private String sponsorTypeCd;
	private String sponsorDesc;
	private Set claimSponsors = new HashSet(0);

	// Constructors

	/** default constructor */
	public SponsorType() {
	}

	/** minimal constructor */
	public SponsorType(String sponsorTypeCd) {
		this.sponsorTypeCd = sponsorTypeCd;
	}

	/** full constructor */
	public SponsorType(String sponsorTypeCd, String sponsorDesc,
			Set claimSponsors) {
		this.sponsorTypeCd = sponsorTypeCd;
		this.sponsorDesc = sponsorDesc;
		this.claimSponsors = claimSponsors;
	}

	// Property accessors

	public String getSponsorTypeCd() {
		return this.sponsorTypeCd;
	}

	public void setSponsorTypeCd(String sponsorTypeCd) {
		this.sponsorTypeCd = sponsorTypeCd;
	}

	public String getSponsorDesc() {
		return this.sponsorDesc;
	}

	public void setSponsorDesc(String sponsorDesc) {
		this.sponsorDesc = sponsorDesc;
	}

	public Set getClaimSponsors() {
		return this.claimSponsors;
	}

	public void setClaimSponsors(Set claimSponsors) {
		this.claimSponsors = claimSponsors;
	}

}