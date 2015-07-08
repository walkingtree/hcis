package com.wtc.hcis.ip.da;

/**
 * BedPoolHasUnitTypeId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedPoolHasUnitTypeId implements java.io.Serializable {

	// Fields

	private String poolName;
	private String unitTypeCd;

	// Constructors

	/** default constructor */
	public BedPoolHasUnitTypeId() {
	}

	/** full constructor */
	public BedPoolHasUnitTypeId(String poolName, String unitTypeCd) {
		this.poolName = poolName;
		this.unitTypeCd = unitTypeCd;
	}

	// Property accessors

	public String getPoolName() {
		return this.poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public String getUnitTypeCd() {
		return this.unitTypeCd;
	}

	public void setUnitTypeCd(String unitTypeCd) {
		this.unitTypeCd = unitTypeCd;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BedPoolHasUnitTypeId))
			return false;
		BedPoolHasUnitTypeId castOther = (BedPoolHasUnitTypeId) other;

		return ((this.getPoolName() == castOther.getPoolName()) || (this
				.getPoolName() != null
				&& castOther.getPoolName() != null && this.getPoolName()
				.equals(castOther.getPoolName())))
				&& ((this.getUnitTypeCd() == castOther.getUnitTypeCd()) || (this
						.getUnitTypeCd() != null
						&& castOther.getUnitTypeCd() != null && this
						.getUnitTypeCd().equals(castOther.getUnitTypeCd())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPoolName() == null ? 0 : this.getPoolName().hashCode());
		result = 37
				* result
				+ (getUnitTypeCd() == null ? 0 : this.getUnitTypeCd()
						.hashCode());
		return result;
	}

}