package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * BedStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedStatus implements java.io.Serializable {

	// Fields

	private String bedStatusCd;
	private String description;
	private String activeFlag;
	private Set bedActivities = new HashSet(0);
	private Set bedMasters = new HashSet(0);

	// Constructors

	/** default constructor */
	public BedStatus() {
	}

	/** minimal constructor */
	public BedStatus(String bedStatusCd) {
		this.bedStatusCd = bedStatusCd;
	}

	/** full constructor */
	public BedStatus(String bedStatusCd, String description, String activeFlag,
			Set bedActivities, Set bedMasters) {
		this.bedStatusCd = bedStatusCd;
		this.description = description;
		this.activeFlag = activeFlag;
		this.bedActivities = bedActivities;
		this.bedMasters = bedMasters;
	}

	// Property accessors

	public String getBedStatusCd() {
		return this.bedStatusCd;
	}

	public void setBedStatusCd(String bedStatusCd) {
		this.bedStatusCd = bedStatusCd;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Set getBedActivities() {
		return this.bedActivities;
	}

	public void setBedActivities(Set bedActivities) {
		this.bedActivities = bedActivities;
	}

	public Set getBedMasters() {
		return this.bedMasters;
	}

	public void setBedMasters(Set bedMasters) {
		this.bedMasters = bedMasters;
	}

}