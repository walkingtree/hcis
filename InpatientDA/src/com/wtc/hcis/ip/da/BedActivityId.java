package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * BedActivityId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedActivityId implements java.io.Serializable {

	// Fields

	private String bedNumber;
	private Date createDtm;
	private String activityTypeCd;

	// Constructors

	/** default constructor */
	public BedActivityId() {
	}

	/** full constructor */
	public BedActivityId(String bedNumber, Date createDtm, String activityTypeCd) {
		this.bedNumber = bedNumber;
		this.createDtm = createDtm;
		this.activityTypeCd = activityTypeCd;
	}

	// Property accessors

	public String getBedNumber() {
		return this.bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
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
		if (!(other instanceof BedActivityId))
			return false;
		BedActivityId castOther = (BedActivityId) other;

		return ((this.getBedNumber() == castOther.getBedNumber()) || (this
				.getBedNumber() != null
				&& castOther.getBedNumber() != null && this.getBedNumber()
				.equals(castOther.getBedNumber())))
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

		result = 37 * result
				+ (getBedNumber() == null ? 0 : this.getBedNumber().hashCode());
		result = 37 * result
				+ (getCreateDtm() == null ? 0 : this.getCreateDtm().hashCode());
		result = 37
				* result
				+ (getActivityTypeCd() == null ? 0 : this.getActivityTypeCd()
						.hashCode());
		return result;
	}

}