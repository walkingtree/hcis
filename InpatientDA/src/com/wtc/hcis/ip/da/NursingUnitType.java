package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * NursingUnitType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class NursingUnitType implements java.io.Serializable {

	// Fields

	private String unitTypeCd;
	private String unitTypeDesc;
	private String parentUnitTypeCd;
	private Integer totalBedCount;
	private Integer availableBedCount;
	private Integer thresholdForConfirmation;
	private Integer thresholdForWaitlist;
	private String modifiedBy;
	private Date lastModifiedDtm;
	private Date createDate;
	private Set admissions = new HashSet(0);
	private Set nursingUnits = new HashSet(0);
	private Set bedReservations = new HashSet(0);
	private Set bedPoolHasUnitTypes = new HashSet(0);

	// Constructors

	/** default constructor */
	public NursingUnitType() {
	}

	/** minimal constructor */
	public NursingUnitType(String unitTypeCd, Integer totalBedCount,
			Integer availableBedCount, Date createDate) {
		this.unitTypeCd = unitTypeCd;
		this.totalBedCount = totalBedCount;
		this.availableBedCount = availableBedCount;
		this.createDate = createDate;
	}

	/** full constructor */
	public NursingUnitType(String unitTypeCd, String unitTypeDesc,
			String parentUnitTypeCd, Integer totalBedCount,
			Integer availableBedCount, Integer thresholdForConfirmation,
			Integer thresholdForWaitlist, String modifiedBy,
			Date lastModifiedDtm, Date createDate, Set admissions,
			Set nursingUnits, Set bedReservations, Set bedPoolHasUnitTypes) {
		this.unitTypeCd = unitTypeCd;
		this.unitTypeDesc = unitTypeDesc;
		this.parentUnitTypeCd = parentUnitTypeCd;
		this.totalBedCount = totalBedCount;
		this.availableBedCount = availableBedCount;
		this.thresholdForConfirmation = thresholdForConfirmation;
		this.thresholdForWaitlist = thresholdForWaitlist;
		this.modifiedBy = modifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.createDate = createDate;
		this.admissions = admissions;
		this.nursingUnits = nursingUnits;
		this.bedReservations = bedReservations;
		this.bedPoolHasUnitTypes = bedPoolHasUnitTypes;
	}

	// Property accessors

	public String getUnitTypeCd() {
		return this.unitTypeCd;
	}

	public void setUnitTypeCd(String unitTypeCd) {
		this.unitTypeCd = unitTypeCd;
	}

	public String getUnitTypeDesc() {
		return this.unitTypeDesc;
	}

	public void setUnitTypeDesc(String unitTypeDesc) {
		this.unitTypeDesc = unitTypeDesc;
	}

	public String getParentUnitTypeCd() {
		return this.parentUnitTypeCd;
	}

	public void setParentUnitTypeCd(String parentUnitTypeCd) {
		this.parentUnitTypeCd = parentUnitTypeCd;
	}

	public Integer getTotalBedCount() {
		return this.totalBedCount;
	}

	public void setTotalBedCount(Integer totalBedCount) {
		this.totalBedCount = totalBedCount;
	}

	public Integer getAvailableBedCount() {
		return this.availableBedCount;
	}

	public void setAvailableBedCount(Integer availableBedCount) {
		this.availableBedCount = availableBedCount;
	}

	public Integer getThresholdForConfirmation() {
		return this.thresholdForConfirmation;
	}

	public void setThresholdForConfirmation(Integer thresholdForConfirmation) {
		this.thresholdForConfirmation = thresholdForConfirmation;
	}

	public Integer getThresholdForWaitlist() {
		return this.thresholdForWaitlist;
	}

	public void setThresholdForWaitlist(Integer thresholdForWaitlist) {
		this.thresholdForWaitlist = thresholdForWaitlist;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Set getAdmissions() {
		return this.admissions;
	}

	public void setAdmissions(Set admissions) {
		this.admissions = admissions;
	}

	public Set getNursingUnits() {
		return this.nursingUnits;
	}

	public void setNursingUnits(Set nursingUnits) {
		this.nursingUnits = nursingUnits;
	}

	public Set getBedReservations() {
		return this.bedReservations;
	}

	public void setBedReservations(Set bedReservations) {
		this.bedReservations = bedReservations;
	}

	public Set getBedPoolHasUnitTypes() {
		return this.bedPoolHasUnitTypes;
	}

	public void setBedPoolHasUnitTypes(Set bedPoolHasUnitTypes) {
		this.bedPoolHasUnitTypes = bedPoolHasUnitTypes;
	}

}