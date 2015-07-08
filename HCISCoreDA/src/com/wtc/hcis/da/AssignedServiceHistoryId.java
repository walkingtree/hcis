package com.wtc.hcis.da;

import java.util.Date;

/**
 * AssignedServiceHistoryId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AssignedServiceHistoryId implements java.io.Serializable {

	// Fields

	private Integer serviceUid;
	private String changeStatusCode;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public AssignedServiceHistoryId() {
	}

	/** full constructor */
	public AssignedServiceHistoryId(Integer serviceUid,
			String changeStatusCode, Date createdDtm) {
		this.serviceUid = serviceUid;
		this.changeStatusCode = changeStatusCode;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public Integer getServiceUid() {
		return this.serviceUid;
	}

	public void setServiceUid(Integer serviceUid) {
		this.serviceUid = serviceUid;
	}

	public String getChangeStatusCode() {
		return this.changeStatusCode;
	}

	public void setChangeStatusCode(String changeStatusCode) {
		this.changeStatusCode = changeStatusCode;
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
		if (!(other instanceof AssignedServiceHistoryId))
			return false;
		AssignedServiceHistoryId castOther = (AssignedServiceHistoryId) other;

		return ((this.getServiceUid() == castOther.getServiceUid()) || (this
				.getServiceUid() != null
				&& castOther.getServiceUid() != null && this.getServiceUid()
				.equals(castOther.getServiceUid())))
				&& ((this.getChangeStatusCode() == castOther
						.getChangeStatusCode()) || (this.getChangeStatusCode() != null
						&& castOther.getChangeStatusCode() != null && this
						.getChangeStatusCode().equals(
								castOther.getChangeStatusCode())))
				&& ((this.getCreatedDtm() == castOther.getCreatedDtm()) || (this
						.getCreatedDtm() != null
						&& castOther.getCreatedDtm() != null && this
						.getCreatedDtm().equals(castOther.getCreatedDtm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getServiceUid() == null ? 0 : this.getServiceUid()
						.hashCode());
		result = 37
				* result
				+ (getChangeStatusCode() == null ? 0 : this
						.getChangeStatusCode().hashCode());
		result = 37
				* result
				+ (getCreatedDtm() == null ? 0 : this.getCreatedDtm()
						.hashCode());
		return result;
	}

}