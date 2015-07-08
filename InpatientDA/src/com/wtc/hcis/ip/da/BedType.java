package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * BedType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedType implements java.io.Serializable {

	// Fields

	private String bedTypeCd;
	private String bedTypeDesc;
	private String activeFlag;
	private Set bedReservations = new HashSet(0);
	private Set bedMasters = new HashSet(0);

	// Constructors

	/** default constructor */
	public BedType() {
	}

	/** minimal constructor */
	public BedType(String bedTypeCd) {
		this.bedTypeCd = bedTypeCd;
	}

	/** full constructor */
	public BedType(String bedTypeCd, String bedTypeDesc, String activeFlag,
			Set bedReservations, Set bedMasters) {
		this.bedTypeCd = bedTypeCd;
		this.bedTypeDesc = bedTypeDesc;
		this.activeFlag = activeFlag;
		this.bedReservations = bedReservations;
		this.bedMasters = bedMasters;
	}

	// Property accessors

	public String getBedTypeCd() {
		return this.bedTypeCd;
	}

	public void setBedTypeCd(String bedTypeCd) {
		this.bedTypeCd = bedTypeCd;
	}

	public String getBedTypeDesc() {
		return this.bedTypeDesc;
	}

	public void setBedTypeDesc(String bedTypeDesc) {
		this.bedTypeDesc = bedTypeDesc;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Set getBedReservations() {
		return this.bedReservations;
	}

	public void setBedReservations(Set bedReservations) {
		this.bedReservations = bedReservations;
	}

	public Set getBedMasters() {
		return this.bedMasters;
	}

	public void setBedMasters(Set bedMasters) {
		this.bedMasters = bedMasters;
	}

}