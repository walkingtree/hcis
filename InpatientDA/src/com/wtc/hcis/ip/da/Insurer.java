package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Insurer entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Insurer implements java.io.Serializable {

	// Fields

	private String insurerName;
	private Integer version;
	private String insurerDesc;
	private Integer contactCode;
	private String createdBy;
	private Date createdDtm;
	private String lastModifiedBy;
	private Date modifiedDtm;
	private Set sponsorInsurerAssociations = new HashSet(0);
	private Set admissionClaimRequests = new HashSet(0);
	private Set insurancePlanses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Insurer() {
	}

	/** minimal constructor */
	public Insurer(String insurerName, Integer contactCode, String createdBy,
			Date createdDtm) {
		this.insurerName = insurerName;
		this.contactCode = contactCode;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public Insurer(String insurerName, String insurerDesc, Integer contactCode,
			String createdBy, Date createdDtm, String lastModifiedBy,
			Date modifiedDtm, Set sponsorInsurerAssociations,
			Set admissionClaimRequests, Set insurancePlanses) {
		this.insurerName = insurerName;
		this.insurerDesc = insurerDesc;
		this.contactCode = contactCode;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.lastModifiedBy = lastModifiedBy;
		this.modifiedDtm = modifiedDtm;
		this.sponsorInsurerAssociations = sponsorInsurerAssociations;
		this.admissionClaimRequests = admissionClaimRequests;
		this.insurancePlanses = insurancePlanses;
	}

	// Property accessors

	public String getInsurerName() {
		return this.insurerName;
	}

	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getInsurerDesc() {
		return this.insurerDesc;
	}

	public void setInsurerDesc(String insurerDesc) {
		this.insurerDesc = insurerDesc;
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

	public Set getInsurancePlanses() {
		return this.insurancePlanses;
	}

	public void setInsurancePlanses(Set insurancePlanses) {
		this.insurancePlanses = insurancePlanses;
	}

}