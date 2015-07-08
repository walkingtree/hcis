package com.wtc.hcis.ip.da;

/**
 * OtPatientSurgeryActivityId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtPatientSurgeryActivityId implements java.io.Serializable {

	// Fields

	private Long patientSurgeryId;
	private String surgeryStatus;

	// Constructors

	/** default constructor */
	public OtPatientSurgeryActivityId() {
	}

	/** full constructor */
	public OtPatientSurgeryActivityId(Long patientSurgeryId,
			String surgeryStatus) {
		this.patientSurgeryId = patientSurgeryId;
		this.surgeryStatus = surgeryStatus;
	}

	// Property accessors

	public Long getPatientSurgeryId() {
		return this.patientSurgeryId;
	}

	public void setPatientSurgeryId(Long patientSurgeryId) {
		this.patientSurgeryId = patientSurgeryId;
	}

	public String getSurgeryStatus() {
		return this.surgeryStatus;
	}

	public void setSurgeryStatus(String surgeryStatus) {
		this.surgeryStatus = surgeryStatus;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OtPatientSurgeryActivityId))
			return false;
		OtPatientSurgeryActivityId castOther = (OtPatientSurgeryActivityId) other;

		return ((this.getPatientSurgeryId() == castOther.getPatientSurgeryId()) || (this
				.getPatientSurgeryId() != null
				&& castOther.getPatientSurgeryId() != null && this
				.getPatientSurgeryId().equals(castOther.getPatientSurgeryId())))
				&& ((this.getSurgeryStatus() == castOther.getSurgeryStatus()) || (this
						.getSurgeryStatus() != null
						&& castOther.getSurgeryStatus() != null && this
						.getSurgeryStatus()
						.equals(castOther.getSurgeryStatus())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getPatientSurgeryId() == null ? 0 : this
						.getPatientSurgeryId().hashCode());
		result = 37
				* result
				+ (getSurgeryStatus() == null ? 0 : this.getSurgeryStatus()
						.hashCode());
		return result;
	}

}