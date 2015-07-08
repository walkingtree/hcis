package com.wtc.hcis.ip.da;

/**
 * OtSurgeryStatusTimeId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtSurgeryStatusTimeId implements java.io.Serializable {

	// Fields

	private String surgeryCode;
	private String surgeryStatusCode;

	// Constructors

	/** default constructor */
	public OtSurgeryStatusTimeId() {
	}

	/** full constructor */
	public OtSurgeryStatusTimeId(String surgeryCode, String surgeryStatusCode) {
		this.surgeryCode = surgeryCode;
		this.surgeryStatusCode = surgeryStatusCode;
	}

	// Property accessors

	public String getSurgeryCode() {
		return this.surgeryCode;
	}

	public void setSurgeryCode(String surgeryCode) {
		this.surgeryCode = surgeryCode;
	}

	public String getSurgeryStatusCode() {
		return this.surgeryStatusCode;
	}

	public void setSurgeryStatusCode(String surgeryStatusCode) {
		this.surgeryStatusCode = surgeryStatusCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OtSurgeryStatusTimeId))
			return false;
		OtSurgeryStatusTimeId castOther = (OtSurgeryStatusTimeId) other;

		return ((this.getSurgeryCode() == castOther.getSurgeryCode()) || (this
				.getSurgeryCode() != null
				&& castOther.getSurgeryCode() != null && this.getSurgeryCode()
				.equals(castOther.getSurgeryCode())))
				&& ((this.getSurgeryStatusCode() == castOther
						.getSurgeryStatusCode()) || (this
						.getSurgeryStatusCode() != null
						&& castOther.getSurgeryStatusCode() != null && this
						.getSurgeryStatusCode().equals(
								castOther.getSurgeryStatusCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getSurgeryCode() == null ? 0 : this.getSurgeryCode()
						.hashCode());
		result = 37
				* result
				+ (getSurgeryStatusCode() == null ? 0 : this
						.getSurgeryStatusCode().hashCode());
		return result;
	}

}