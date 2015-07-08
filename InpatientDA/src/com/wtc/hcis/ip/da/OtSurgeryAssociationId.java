package com.wtc.hcis.ip.da;

/**
 * OtSurgeryAssociationId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtSurgeryAssociationId implements java.io.Serializable {

	// Fields

	private String surgeryCode;
	private String otId;

	// Constructors

	/** default constructor */
	public OtSurgeryAssociationId() {
	}

	/** full constructor */
	public OtSurgeryAssociationId(String surgeryCode, String otId) {
		this.surgeryCode = surgeryCode;
		this.otId = otId;
	}

	// Property accessors

	public String getSurgeryCode() {
		return this.surgeryCode;
	}

	public void setSurgeryCode(String surgeryCode) {
		this.surgeryCode = surgeryCode;
	}

	public String getOtId() {
		return this.otId;
	}

	public void setOtId(String otId) {
		this.otId = otId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OtSurgeryAssociationId))
			return false;
		OtSurgeryAssociationId castOther = (OtSurgeryAssociationId) other;

		return ((this.getSurgeryCode() == castOther.getSurgeryCode()) || (this
				.getSurgeryCode() != null
				&& castOther.getSurgeryCode() != null && this.getSurgeryCode()
				.equals(castOther.getSurgeryCode())))
				&& ((this.getOtId() == castOther.getOtId()) || (this.getOtId() != null
						&& castOther.getOtId() != null && this.getOtId()
						.equals(castOther.getOtId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getSurgeryCode() == null ? 0 : this.getSurgeryCode()
						.hashCode());
		result = 37 * result
				+ (getOtId() == null ? 0 : this.getOtId().hashCode());
		return result;
	}

}