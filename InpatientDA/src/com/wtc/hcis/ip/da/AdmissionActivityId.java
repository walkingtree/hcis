package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * AdmissionActivityId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AdmissionActivityId implements java.io.Serializable {

	// Fields

	private Integer admissionReqNbr;
	private String activityTypeCd;
	private Date activityDtm;

	// Constructors

	/** default constructor */
	public AdmissionActivityId() {
	}

	/** full constructor */
	public AdmissionActivityId(Integer admissionReqNbr, String activityTypeCd,
			Date activityDtm) {
		this.admissionReqNbr = admissionReqNbr;
		this.activityTypeCd = activityTypeCd;
		this.activityDtm = activityDtm;
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

	public Date getActivityDtm() {
		return this.activityDtm;
	}

	public void setActivityDtm(Date activityDtm) {
		this.activityDtm = activityDtm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AdmissionActivityId))
			return false;
		AdmissionActivityId castOther = (AdmissionActivityId) other;

		return ((this.getAdmissionReqNbr() == castOther.getAdmissionReqNbr()) || (this
				.getAdmissionReqNbr() != null
				&& castOther.getAdmissionReqNbr() != null && this
				.getAdmissionReqNbr().equals(castOther.getAdmissionReqNbr())))
				&& ((this.getActivityTypeCd() == castOther.getActivityTypeCd()) || (this
						.getActivityTypeCd() != null
						&& castOther.getActivityTypeCd() != null && this
						.getActivityTypeCd().equals(
								castOther.getActivityTypeCd())))
				&& ((this.getActivityDtm() == castOther.getActivityDtm()) || (this
						.getActivityDtm() != null
						&& castOther.getActivityDtm() != null && this
						.getActivityDtm().equals(castOther.getActivityDtm())));
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
				+ (getActivityDtm() == null ? 0 : this.getActivityDtm()
						.hashCode());
		return result;
	}

}