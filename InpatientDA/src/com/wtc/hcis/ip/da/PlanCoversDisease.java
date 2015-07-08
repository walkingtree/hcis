package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * PlanCoversDisease entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PlanCoversDisease implements java.io.Serializable {

	// Fields

	private PlanCoversDiseaseId id;
	private Integer version;
	private String isCoverd;
	private String percentAbsInd;
	private Double coverageAmt;
	private Date createdDtm;
	private String createdBy;

	// Constructors

	/** default constructor */
	public PlanCoversDisease() {
	}

	/** minimal constructor */
	public PlanCoversDisease(PlanCoversDiseaseId id, String isCoverd,
			String createdBy) {
		this.id = id;
		this.isCoverd = isCoverd;
		this.createdBy = createdBy;
	}

	/** full constructor */
	public PlanCoversDisease(PlanCoversDiseaseId id, String isCoverd,
			String percentAbsInd, Double coverageAmt, Date createdDtm,
			String createdBy) {
		this.id = id;
		this.isCoverd = isCoverd;
		this.percentAbsInd = percentAbsInd;
		this.coverageAmt = coverageAmt;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
	}

	// Property accessors

	public PlanCoversDiseaseId getId() {
		return this.id;
	}

	public void setId(PlanCoversDiseaseId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public Date getCreatedDtm() {
		return this.createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}