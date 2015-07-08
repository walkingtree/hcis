package com.wtc.hcis.da;

import java.util.Date;

/**
 * ServicePackageHistoryId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ServicePackageHistoryId implements java.io.Serializable {

	// Fields

	private String packageId;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public ServicePackageHistoryId() {
	}

	/** full constructor */
	public ServicePackageHistoryId(String packageId, Date createdDtm) {
		this.packageId = packageId;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public String getPackageId() {
		return this.packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public Date getCreatedDtm() {
		return this.createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ServicePackageHistoryId))
			return false;
		ServicePackageHistoryId castOther = (ServicePackageHistoryId) other;

		return ((this.getPackageId() == castOther.getPackageId()) || (this
				.getPackageId() != null
				&& castOther.getPackageId() != null && this.getPackageId()
				.equals(castOther.getPackageId())))
				&& ((this.getCreatedDtm() == castOther.getCreatedDtm()) || (this
						.getCreatedDtm() != null
						&& castOther.getCreatedDtm() != null && this
						.getCreatedDtm().equals(castOther.getCreatedDtm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPackageId() == null ? 0 : this.getPackageId().hashCode());
		result = 37
				* result
				+ (getCreatedDtm() == null ? 0 : this.getCreatedDtm()
						.hashCode());
		return result;
	}

}