package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * BedPool entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedPool implements java.io.Serializable {

	// Fields

	private String bedPoolName;
	private Integer version;
	private String poolDesc;
	private Integer totalNumberOfBed;
	private Integer numberOfAvailableBeds;
	private Set bedEnvelopeHasPools = new HashSet(0);
	private Set bedPoolHasUnitTypes = new HashSet(0);

	// Constructors

	/** default constructor */
	public BedPool() {
	}

	/** minimal constructor */
	public BedPool(String bedPoolName, Integer totalNumberOfBed,
			Integer numberOfAvailableBeds) {
		this.bedPoolName = bedPoolName;
		this.totalNumberOfBed = totalNumberOfBed;
		this.numberOfAvailableBeds = numberOfAvailableBeds;
	}

	/** full constructor */
	public BedPool(String bedPoolName, String poolDesc,
			Integer totalNumberOfBed, Integer numberOfAvailableBeds,
			Set bedEnvelopeHasPools, Set bedPoolHasUnitTypes) {
		this.bedPoolName = bedPoolName;
		this.poolDesc = poolDesc;
		this.totalNumberOfBed = totalNumberOfBed;
		this.numberOfAvailableBeds = numberOfAvailableBeds;
		this.bedEnvelopeHasPools = bedEnvelopeHasPools;
		this.bedPoolHasUnitTypes = bedPoolHasUnitTypes;
	}

	// Property accessors

	public String getBedPoolName() {
		return this.bedPoolName;
	}

	public void setBedPoolName(String bedPoolName) {
		this.bedPoolName = bedPoolName;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getPoolDesc() {
		return this.poolDesc;
	}

	public void setPoolDesc(String poolDesc) {
		this.poolDesc = poolDesc;
	}

	public Integer getTotalNumberOfBed() {
		return this.totalNumberOfBed;
	}

	public void setTotalNumberOfBed(Integer totalNumberOfBed) {
		this.totalNumberOfBed = totalNumberOfBed;
	}

	public Integer getNumberOfAvailableBeds() {
		return this.numberOfAvailableBeds;
	}

	public void setNumberOfAvailableBeds(Integer numberOfAvailableBeds) {
		this.numberOfAvailableBeds = numberOfAvailableBeds;
	}

	public Set getBedEnvelopeHasPools() {
		return this.bedEnvelopeHasPools;
	}

	public void setBedEnvelopeHasPools(Set bedEnvelopeHasPools) {
		this.bedEnvelopeHasPools = bedEnvelopeHasPools;
	}

	public Set getBedPoolHasUnitTypes() {
		return this.bedPoolHasUnitTypes;
	}

	public void setBedPoolHasUnitTypes(Set bedPoolHasUnitTypes) {
		this.bedPoolHasUnitTypes = bedPoolHasUnitTypes;
	}

}