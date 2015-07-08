package com.wtc.hcis.ip.da;

/**
 * SponsorInsurerAssociationId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SponsorInsurerAssociationId implements java.io.Serializable {

	// Fields

	private String sponsorName;
	private String insurerName;

	// Constructors

	/** default constructor */
	public SponsorInsurerAssociationId() {
	}

	/** full constructor */
	public SponsorInsurerAssociationId(String sponsorName, String insurerName) {
		this.sponsorName = sponsorName;
		this.insurerName = insurerName;
	}

	// Property accessors

	public String getSponsorName() {
		return this.sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public String getInsurerName() {
		return this.insurerName;
	}

	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SponsorInsurerAssociationId))
			return false;
		SponsorInsurerAssociationId castOther = (SponsorInsurerAssociationId) other;

		return ((this.getSponsorName() == castOther.getSponsorName()) || (this
				.getSponsorName() != null
				&& castOther.getSponsorName() != null && this.getSponsorName()
				.equals(castOther.getSponsorName())))
				&& ((this.getInsurerName() == castOther.getInsurerName()) || (this
						.getInsurerName() != null
						&& castOther.getInsurerName() != null && this
						.getInsurerName().equals(castOther.getInsurerName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getSponsorName() == null ? 0 : this.getSponsorName()
						.hashCode());
		result = 37
				* result
				+ (getInsurerName() == null ? 0 : this.getInsurerName()
						.hashCode());
		return result;
	}

}