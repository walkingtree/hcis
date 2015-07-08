package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * SponsorClaimStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SponsorClaimStatus implements java.io.Serializable {

	// Fields

	private String claimStatusCd;
	private Integer version;
	private String claimStatusDesc;
	private String activeFlag;
	private Set admissionClaimActivities = new HashSet(0);
	private Set admissionClaimRequests = new HashSet(0);

	// Constructors

	/** default constructor */
	public SponsorClaimStatus() {
	}

	/** minimal constructor */
	public SponsorClaimStatus(String claimStatusCd) {
		this.claimStatusCd = claimStatusCd;
	}

	/** full constructor */
	public SponsorClaimStatus(String claimStatusCd, String claimStatusDesc,
			String activeFlag, Set admissionClaimActivities,
			Set admissionClaimRequests) {
		this.claimStatusCd = claimStatusCd;
		this.claimStatusDesc = claimStatusDesc;
		this.activeFlag = activeFlag;
		this.admissionClaimActivities = admissionClaimActivities;
		this.admissionClaimRequests = admissionClaimRequests;
	}

	// Property accessors

	public String getClaimStatusCd() {
		return this.claimStatusCd;
	}

	public void setClaimStatusCd(String claimStatusCd) {
		this.claimStatusCd = claimStatusCd;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getClaimStatusDesc() {
		return this.claimStatusDesc;
	}

	public void setClaimStatusDesc(String claimStatusDesc) {
		this.claimStatusDesc = claimStatusDesc;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Set getAdmissionClaimActivities() {
		return this.admissionClaimActivities;
	}

	public void setAdmissionClaimActivities(Set admissionClaimActivities) {
		this.admissionClaimActivities = admissionClaimActivities;
	}

	public Set getAdmissionClaimRequests() {
		return this.admissionClaimRequests;
	}

	public void setAdmissionClaimRequests(Set admissionClaimRequests) {
		this.admissionClaimRequests = admissionClaimRequests;
	}

}