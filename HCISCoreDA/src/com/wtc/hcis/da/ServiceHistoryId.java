package com.wtc.hcis.da;

import java.util.Date;

/**
 * ServiceHistoryId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ServiceHistoryId implements java.io.Serializable {

	// Fields

	private String serviceCode;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public ServiceHistoryId() {
	}

	/** full constructor */
	public ServiceHistoryId(String serviceCode, Date createdDtm) {
		this.serviceCode = serviceCode;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
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
		if (!(other instanceof ServiceHistoryId))
			return false;
		ServiceHistoryId castOther = (ServiceHistoryId) other;

		return ((this.getServiceCode() == castOther.getServiceCode()) || (this
				.getServiceCode() != null
				&& castOther.getServiceCode() != null && this.getServiceCode()
				.equals(castOther.getServiceCode())))
				&& ((this.getCreatedDtm() == castOther.getCreatedDtm()) || (this
						.getCreatedDtm() != null
						&& castOther.getCreatedDtm() != null && this
						.getCreatedDtm().equals(castOther.getCreatedDtm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getServiceCode() == null ? 0 : this.getServiceCode()
						.hashCode());
		result = 37
				* result
				+ (getCreatedDtm() == null ? 0 : this.getCreatedDtm()
						.hashCode());
		return result;
	}

}