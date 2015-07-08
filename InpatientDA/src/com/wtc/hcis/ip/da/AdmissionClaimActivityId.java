package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * AdmissionClaimActivityId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AdmissionClaimActivityId implements java.io.Serializable {

	// Fields

	private Long requestSequenceNbr;
	private Date createDtm;
	private String activityTypeCd;

	// Constructors

	/** default constructor */
	public AdmissionClaimActivityId() {
	}

	/** full constructor */
	public AdmissionClaimActivityId(Long requestSequenceNbr, Date createDtm,
			String activityTypeCd) {
		this.requestSequenceNbr = requestSequenceNbr;
		this.createDtm = createDtm;
		this.activityTypeCd = activityTypeCd;
	}

	// Property accessors

	public Long getRequestSequenceNbr() {
		return this.requestSequenceNbr;
	}

	public void setRequestSequenceNbr(Long requestSequenceNbr) {
		this.requestSequenceNbr = requestSequenceNbr;
	}

	public Date getCreateDtm() {
		return this.createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}

	public String getActivityTypeCd() {
		return this.activityTypeCd;
	}

	public void setActivityTypeCd(String activityTypeCd) {
		this.activityTypeCd = activityTypeCd;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AdmissionClaimActivityId))
			return false;
		AdmissionClaimActivityId castOther = (AdmissionClaimActivityId) other;

		return ((this.getRequestSequenceNbr() == castOther
				.getRequestSequenceNbr()) || (this.getRequestSequenceNbr() != null
				&& castOther.getRequestSequenceNbr() != null && this
				.getRequestSequenceNbr().equals(
						castOther.getRequestSequenceNbr())))
				&& ((this.getCreateDtm() == castOther.getCreateDtm()) || (this
						.getCreateDtm() != null
						&& castOther.getCreateDtm() != null && this
						.getCreateDtm().equals(castOther.getCreateDtm())))
				&& ((this.getActivityTypeCd() == castOther.getActivityTypeCd()) || (this
						.getActivityTypeCd() != null
						&& castOther.getActivityTypeCd() != null && this
						.getActivityTypeCd().equals(
								castOther.getActivityTypeCd())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getRequestSequenceNbr() == null ? 0 : this
						.getRequestSequenceNbr().hashCode());
		result = 37 * result
				+ (getCreateDtm() == null ? 0 : this.getCreateDtm().hashCode());
		result = 37
				* result
				+ (getActivityTypeCd() == null ? 0 : this.getActivityTypeCd()
						.hashCode());
		return result;
	}

}