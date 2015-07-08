package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * DischargeActivityId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DischargeActivityId implements java.io.Serializable {

	// Fields

	private Integer admissionReqNbr;
	private String activityTypeCd;
	private Date creationDtm;

	// Constructors

	/** default constructor */
	public DischargeActivityId() {
	}

	/** full constructor */
	public DischargeActivityId(Integer admissionReqNbr, String activityTypeCd,
			Date creationDtm) {
		this.admissionReqNbr = admissionReqNbr;
		this.activityTypeCd = activityTypeCd;
		this.creationDtm = creationDtm;
	}

	// Property accessors

	public Integer getAdmissionReqNbr() {
		return this.admissionReqNbr;
	}

	public void setAdmissionReqNbr(Integer admissionReqNbr) {
		this.admissionReqNbr = admissionReqNbr;
	}

	public String getActivityTypeCd() {
		return this.activityTypeCd;
	}

	public void setActivityTypeCd(String activityTypeCd) {
		this.activityTypeCd = activityTypeCd;
	}

	public Date getCreationDtm() {
		return this.creationDtm;
	}

	public void setCreationDtm(Date creationDtm) {
		this.creationDtm = creationDtm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DischargeActivityId))
			return false;
		DischargeActivityId castOther = (DischargeActivityId) other;

		return ((this.getAdmissionReqNbr() == castOther.getAdmissionReqNbr()) || (this
				.getAdmissionReqNbr() != null
				&& castOther.getAdmissionReqNbr() != null && this
				.getAdmissionReqNbr().equals(castOther.getAdmissionReqNbr())))
				&& ((this.getActivityTypeCd() == castOther.getActivityTypeCd()) || (this
						.getActivityTypeCd() != null
						&& castOther.getActivityTypeCd() != null && this
						.getActivityTypeCd().equals(
								castOther.getActivityTypeCd())))
				&& ((this.getCreationDtm() == castOther.getCreationDtm()) || (this
						.getCreationDtm() != null
						&& castOther.getCreationDtm() != null && this
						.getCreationDtm().equals(castOther.getCreationDtm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAdmissionReqNbr() == null ? 0 : this.getAdmissionReqNbr()
						.hashCode());
		result = 37
				* result
				+ (getActivityTypeCd() == null ? 0 : this.getActivityTypeCd()
						.hashCode());
		result = 37
				* result
				+ (getCreationDtm() == null ? 0 : this.getCreationDtm()
						.hashCode());
		return result;
	}

}