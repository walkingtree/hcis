package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * PlanHasServices entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PlanHasServices implements java.io.Serializable {

	// Fields

	private PlanHasServicesId id;
	private Integer version;
	private InsurancePlans insurancePlans;
	private String isCoverd;
	private String percentAbsInd;
	private Double coverageAmt;
	private String createdBy;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public PlanHasServices() {
	}

	/** minimal constructor */
	public PlanHasServices(PlanHasServicesId id, InsurancePlans insurancePlans,
			String isCoverd, String createdBy, Date createdDtm) {
		this.id = id;
		this.insurancePlans = insurancePlans;
		this.isCoverd = isCoverd;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public PlanHasServices(PlanHasServicesId id, InsurancePlans insurancePlans,
			String isCoverd, String percentAbsInd, Double coverageAmt,
			String createdBy, Date createdDtm) {
		this.id = id;
		this.insurancePlans = insurancePlans;
		this.isCoverd = isCoverd;
		this.percentAbsInd = percentAbsInd;
		this.coverageAmt = coverageAmt;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public PlanHasServicesId getId() {
		return this.id;
	}

	public void setId(PlanHasServicesId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public InsurancePlans getInsurancePlans() {
		return this.insurancePlans;
	}

	public void setInsurancePlans(InsurancePlans insurancePlans) {
		this.insurancePlans = insurancePlans;
	}

	public String getIsCoverd() {
		return this.isCoverd;
	}

	public void setIsCoverd(String isCoverd) {
		this.isCoverd = isCoverd;
	}

	public String getPercentAbsInd() {
		return this.percentAbsInd;
	}

	public void setPercentAbsInd(String percentAbsInd) {
		this.percentAbsInd = percentAbsInd;
	}

	public Double getCoverageAmt() {
		return this.coverageAmt;
	}

	public void setCoverageAmt(Double coverageAmt) {
		this.coverageAmt = coverageAmt;
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

}