package com.wtc.hcis.ip.da;

/**
 * ClaimSponsorSlaId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ClaimSponsorSlaId implements java.io.Serializable {

	// Fields

	private String sponsorName;
	private String activityTypeCd;

	// Constructors

	/** default constructor */
	public ClaimSponsorSlaId() {
	}

	/** full constructor */
	public ClaimSponsorSlaId(String sponsorName, String activityTypeCd) {
		this.sponsorName = sponsorName;
		this.activityTypeCd = activityTypeCd;
	}

	// Property accessors

	public String getSponsorName() {
		return this.sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
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
		if (!(other instanceof ClaimSponsorSlaId))
			return false;
		ClaimSponsorSlaId castOther = (ClaimSponsorSlaId) other;

		return ((this.getSponsorName() == castOther.getSponsorName()) || (this
				.getSponsorName() != null
				&& castOther.getSponsorName() != null && this.getSponsorName()
				.equals(castOther.getSponsorName())))
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
				+ (getSponsorName() == null ? 0 : this.getSponsorName()
						.hashCode());
		result = 37
				* result
				+ (getActivityTypeCd() == null ? 0 : this.getActivityTypeCd()
						.hashCode());
		return result;
	}

}