package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * DoctorOrderActivityId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderActivityId implements java.io.Serializable {

	// Fields

	private Integer doctorOrderNumber;
	private String activityTypeCd;
	private Date activityDtm;

	// Constructors

	/** default constructor */
	public DoctorOrderActivityId() {
	}

	/** full constructor */
	public DoctorOrderActivityId(Integer doctorOrderNumber,
			String activityTypeCd, Date activityDtm) {
		this.doctorOrderNumber = doctorOrderNumber;
		this.activityTypeCd = activityTypeCd;
		this.activityDtm = activityDtm;
	}

	// Property accessors

	public Integer getDoctorOrderNumber() {
		return this.doctorOrderNumber;
	}

	public void setDoctorOrderNumber(Integer doctorOrderNumber) {
		this.doctorOrderNumber = doctorOrderNumber;
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
		if (!(other instanceof DoctorOrderActivityId))
			return false;
		DoctorOrderActivityId castOther = (DoctorOrderActivityId) other;

		return ((this.getDoctorOrderNumber() == castOther
				.getDoctorOrderNumber()) || (this.getDoctorOrderNumber() != null
				&& castOther.getDoctorOrderNumber() != null && this
				.getDoctorOrderNumber()
				.equals(castOther.getDoctorOrderNumber())))
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
				+ (getDoctorOrderNumber() == null ? 0 : this
						.getDoctorOrderNumber().hashCode());
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