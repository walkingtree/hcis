package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * BedReservationActivityId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedReservationActivityId implements java.io.Serializable {

	// Fields

	private Integer bedReservationNbr;
	private String activityTypeCd;
	private Date createDtm;

	// Constructors

	/** default constructor */
	public BedReservationActivityId() {
	}

	/** full constructor */
	public BedReservationActivityId(Integer bedReservationNbr,
			String activityTypeCd, Date createDtm) {
		this.bedReservationNbr = bedReservationNbr;
		this.activityTypeCd = activityTypeCd;
		this.createDtm = createDtm;
	}

	// Property accessors

	public Integer getBedReservationNbr() {
		return this.bedReservationNbr;
	}

	public void setBedReservationNbr(Integer bedReservationNbr) {
		this.bedReservationNbr = bedReservationNbr;
	}

	public String getActivityTypeCd() {
		return this.activityTypeCd;
	}

	public void setActivityTypeCd(String activityTypeCd) {
		this.activityTypeCd = activityTypeCd;
	}

	public Date getCreateDtm() {
		return this.createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BedReservationActivityId))
			return false;
		BedReservationActivityId castOther = (BedReservationActivityId) other;

		return ((this.getBedReservationNbr() == castOther
				.getBedReservationNbr()) || (this.getBedReservationNbr() != null
				&& castOther.getBedReservationNbr() != null && this
				.getBedReservationNbr()
				.equals(castOther.getBedReservationNbr())))
				&& ((this.getActivityTypeCd() == castOther.getActivityTypeCd()) || (this
						.getActivityTypeCd() != null
						&& castOther.getActivityTypeCd() != null && this
						.getActivityTypeCd().equals(
								castOther.getActivityTypeCd())))
				&& ((this.getCreateDtm() == castOther.getCreateDtm()) || (this
						.getCreateDtm() != null
						&& castOther.getCreateDtm() != null && this
						.getCreateDtm().equals(castOther.getCreateDtm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getBedReservationNbr() == null ? 0 : this
						.getBedReservationNbr().hashCode());
		result = 37
				* result
				+ (getActivityTypeCd() == null ? 0 : this.getActivityTypeCd()
						.hashCode());
		result = 37 * result
				+ (getCreateDtm() == null ? 0 : this.getCreateDtm().hashCode());
		return result;
	}

}