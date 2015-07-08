package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * NursingUnit entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class NursingUnit implements java.io.Serializable {

	// Fields

	private String unitName;
	private Integer version;
	private NursingUnitType nursingUnitType;
	private String unitDescription;
	private String unitCoordinatorUserId;
	private Set bedMasters = new HashSet(0);

	// Constructors

	/** default constructor */
	public NursingUnit() {
	}

	/** minimal constructor */
	public NursingUnit(String unitName) {
		this.unitName = unitName;
	}

	/** full constructor */
	public NursingUnit(String unitName, NursingUnitType nursingUnitType,
			String unitDescription, String unitCoordinatorUserId, Set bedMasters) {
		this.unitName = unitName;
		this.nursingUnitType = nursingUnitType;
		this.unitDescription = unitDescription;
		this.unitCoordinatorUserId = unitCoordinatorUserId;
		this.bedMasters = bedMasters;
	}

	// Property accessors

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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

	public String getUnitDescription() {
		return this.unitDescription;
	}

	public void setUnitDescription(String unitDescription) {
		this.unitDescription = unitDescription;
	}

	public String getUnitCoordinatorUserId() {
		return this.unitCoordinatorUserId;
	}

	public void setUnitCoordinatorUserId(String unitCoordinatorUserId) {
		this.unitCoordinatorUserId = unitCoordinatorUserId;
	}

	public Set getBedMasters() {
		return this.bedMasters;
	}

	public void setBedMasters(Set bedMasters) {
		this.bedMasters = bedMasters;
	}

}