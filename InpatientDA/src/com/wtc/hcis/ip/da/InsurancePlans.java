package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * InsurancePlans entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class InsurancePlans implements java.io.Serializable {

	// Fields

	private String planCd;
	private Integer version;
	private Insurer insurer;
	private String planName;
	private Date validFromDt;
	private Date validToDt;
	private Double totalCoverageAmt;
	private String percentAbsInd;
	private String createdBy;
	private Date createdDtm;
	private Date lastModifiedDtm;
	private String modifiedBy;
	private Set admissionClaimRequests = new HashSet(0);
	private Set planHasServiceses = new HashSet(0);

	// Constructors

	/** default constructor */
	public InsurancePlans() {
	}

	/** minimal constructor */
	public InsurancePlans(String planCd, Insurer insurer, Date validFromDt,
			String createdBy, Date createdDtm) {
		this.planCd = planCd;
		this.insurer = insurer;
		this.validFromDt = validFromDt;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public InsurancePlans(String planCd, Insurer insurer, String planName,
			Date validFromDt, Date validToDt, Double totalCoverageAmt,
			String percentAbsInd, String createdBy, Date createdDtm,
			Date lastModifiedDtm, String modifiedBy,
			Set admissionClaimRequests, Set planHasServiceses) {
		this.planCd = planCd;
		this.insurer = insurer;
		this.planName = planName;
		this.validFromDt = validFromDt;
		this.validToDt = validToDt;
		this.totalCoverageAmt = totalCoverageAmt;
		this.percentAbsInd = percentAbsInd;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
		this.admissionClaimRequests = admissionClaimRequests;
		this.planHasServiceses = planHasServiceses;
	}

	// Property accessors

	public String getPlanCd() {
		return this.planCd;
	}

	public void setPlanCd(String planCd) {
		this.planCd = planCd;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Insurer getInsurer() {
		return this.insurer;
	}

	public void setInsurer(Insurer insurer) {
		this.insurer = insurer;
	}

	public String getPlanName() {
		return this.planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Date getValidFromDt() {
		return this.validFromDt;
	}

	public void setValidFromDt(Date validFromDt) {
		this.validFromDt = validFromDt;
	}

	public Date getValidToDt() {
		return this.validToDt;
	}

	public void setValidToDt(Date validToDt) {
		this.validToDt = validToDt;
	}

	public Double getTotalCoverageAmt() {
		return this.totalCoverageAmt;
	}

	public void setTotalCoverageAmt(Double totalCoverageAmt) {
		this.totalCoverageAmt = totalCoverageAmt;
	}

	public String getPercentAbsInd() {
		return this.percentAbsInd;
	}

	public void setPercentAbsInd(String percentAbsInd) {
		this.percentAbsInd = percentAbsInd;
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

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Set getAdmissionClaimRequests() {
		return this.admissionClaimRequests;
	}

	public void setAdmissionClaimRequests(Set admissionClaimRequests) {
		this.admissionClaimRequests = admissionClaimRequests;
	}

	public Set getPlanHasServiceses() {
		return this.planHasServiceses;
	}

	public void setPlanHasServiceses(Set planHasServiceses) {
		this.planHasServiceses = planHasServiceses;
	}

}