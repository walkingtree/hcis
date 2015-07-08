package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * OtBookingActivityId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtBookingActivityId implements java.io.Serializable {

	// Fields

	private Long otBookingNbr;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public OtBookingActivityId() {
	}

	/** full constructor */
	public OtBookingActivityId(Long otBookingNbr, Date createdDtm) {
		this.otBookingNbr = otBookingNbr;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public Long getOtBookingNbr() {
		return this.otBookingNbr;
	}

	public void setOtBookingNbr(Long otBookingNbr) {
		this.otBookingNbr = otBookingNbr;
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
		if (!(other instanceof OtBookingActivityId))
			return false;
		OtBookingActivityId castOther = (OtBookingActivityId) other;

		return ((this.getOtBookingNbr() == castOther.getOtBookingNbr()) || (this
				.getOtBookingNbr() != null
				&& castOther.getOtBookingNbr() != null && this
				.getOtBookingNbr().equals(castOther.getOtBookingNbr())))
				&& ((this.getCreatedDtm() == castOther.getCreatedDtm()) || (this
						.getCreatedDtm() != null
						&& castOther.getCreatedDtm() != null && this
						.getCreatedDtm().equals(castOther.getCreatedDtm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getOtBookingNbr() == null ? 0 : this.getOtBookingNbr()
						.hashCode());
		result = 37
				* result
				+ (getCreatedDtm() == null ? 0 : this.getCreatedDtm()
						.hashCode());
		return result;
	}

}