package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * BedPoolHasUnitType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedPoolHasUnitType implements java.io.Serializable {

	// Fields

	private BedPoolHasUnitTypeId id;
	private Integer version;
	private NursingUnitType nursingUnitType;
	private BedPool bedPool;
	private Date effectiveFromDt;
	private Date effectiveToDate;

	// Constructors

	/** default constructor */
	public BedPoolHasUnitType() {
	}

	/** minimal constructor */
	public BedPoolHasUnitType(BedPoolHasUnitTypeId id,
			NursingUnitType nursingUnitType, BedPool bedPool) {
		this.id = id;
		this.nursingUnitType = nursingUnitType;
		this.bedPool = bedPool;
	}

	/** full constructor */
	public BedPoolHasUnitType(BedPoolHasUnitTypeId id,
			NursingUnitType nursingUnitType, BedPool bedPool,
			Date effectiveFromDt, Date effectiveToDate) {
		this.id = id;
		this.nursingUnitType = nursingUnitType;
		this.bedPool = bedPool;
		this.effectiveFromDt = effectiveFromDt;
		this.effectiveToDate = effectiveToDate;
	}

	// Property accessors

	public BedPoolHasUnitTypeId getId() {
		return this.id;
	}

	public void setId(BedPoolHasUnitTypeId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public NursingUnitType getNursingUnitType() {
		return this.nursingUnitType;
	}

	public void setNursingUnitType(NursingUnitType nursingUnitType) {
		this.nursingUnitType = nursingUnitType;
	}

	public BedPool getBedPool() {
		return this.bedPool;
	}

	public void setBedPool(BedPool bedPool) {
		this.bedPool = bedPool;
	}

	public Date getEffectiveFromDt() {
		return this.effectiveFromDt;
	}

	public void setEffectiveFromDt(Date effectiveFromDt) {
		this.effectiveFromDt = effectiveFromDt;
	}

	public Date getEffectiveToDate() {
		return this.effectiveToDate;
	}

	public void setEffectiveToDate(Date effectiveToDate) {
		this.effectiveToDate = effectiveToDate;
	}

}