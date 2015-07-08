package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ClaimSponsor entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ClaimSponsor implements java.io.Serializable {

	// Fields

	private String sponsorsName;
	private Integer version;
	private SponsorType sponsorType;
	private CreditClass creditClass;
	private String sponsorDesc;
	private String accountNumber;
	private Integer contactCode;
	private String createdBy;
	private Date createdDtm;
	private String lastModifiedBy;
	private Date modifiedDtm;
	private Set claimSponsorSlas = new HashSet(0);
	private Set sponsorInsurerAssociations = new HashSet(0);
	private Set admissionClaimRequests = new HashSet(0);

	// Constructors

	/** default constructor */
	public ClaimSponsor() {
	}

	/** minimal constructor */
	public ClaimSponsor(String sponsorsName, SponsorType sponsorType,
			Integer contactCode, String createdBy, Date createdDtm) {
		this.sponsorsName = sponsorsName;
		this.sponsorType = sponsorType;
		this.contactCode = contactCode;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public ClaimSponsor(String sponsorsName, SponsorType sponsorType,
			CreditClass creditClass, String sponsorDesc, String accountNumber,
			Integer contactCode, String createdBy, Date createdDtm,
			String lastModifiedBy, Date modifiedDtm, Set claimSponsorSlas,
			Set sponsorInsurerAssociations, Set admissionClaimRequests) {
		this.sponsorsName = sponsorsName;
		this.sponsorType = sponsorType;
		this.creditClass = creditClass;
		this.sponsorDesc = sponsorDesc;
		this.accountNumber = accountNumber;
		this.contactCode = contactCode;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.lastModifiedBy = lastModifiedBy;
		this.modifiedDtm = modifiedDtm;
		this.claimSponsorSlas = claimSponsorSlas;
		this.sponsorInsurerAssociations = sponsorInsurerAssociations;
		this.admissionClaimRequests = admissionClaimRequests;
	}

	// Property accessors

	public String getSponsorsName() {
		return this.sponsorsName;
	}

	public void setSponsorsName(String sponsorsName) {
		this.sponsorsName = sponsorsName;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public SponsorType getSponsorType() {
		return this.sponsorType;
	}

	public void setSponsorType(SponsorType sponsorType) {
		this.sponsorType = sponsorType;
	}

	public CreditClass getCreditClass() {
		return this.creditClass;
	}

	public void setCreditClass(CreditClass creditClass) {
		this.creditClass = creditClass;
	}

	public String getSponsorDesc() {
		return this.sponsorDesc;
	}

	public void setSponsorDesc(String sponsorDesc) {
		this.sponsorDesc = sponsorDesc;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getContactCode() {
		return this.contactCode;
	}

	public void setContactCode(Integer contactCode) {
		this.contactCode = contactCode;
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

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getModifiedDtm() {
		return this.modifiedDtm;
	}

	public void setModifiedDtm(Date modifiedDtm) {
		this.modifiedDtm = modifiedDtm;
	}

	public Set getClaimSponsorSlas() {
		return this.claimSponsorSlas;
	}

	public void setClaimSponsorSlas(Set claimSponsorSlas) {
		this.claimSponsorSlas = claimSponsorSlas;
	}

	public Set getSponsorInsurerAssociations() {
		return this.sponsorInsurerAssociations;
	}

	public void setSponsorInsurerAssociations(Set sponsorInsurerAssociations) {
		this.sponsorInsurerAssociations = sponsorInsurerAssociations;
	}

	public Set getAdmissionClaimRequests() {
		return this.admissionClaimRequests;
	}

	public void setAdmissionClaimRequests(Set admissionClaimRequests) {
		this.admissionClaimRequests = admissionClaimRequests;
	}

}