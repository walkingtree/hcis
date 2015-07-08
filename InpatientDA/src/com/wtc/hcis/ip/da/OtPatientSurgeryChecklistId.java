package com.wtc.hcis.ip.da;

/**
 * OtPatientSurgeryChecklistId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtPatientSurgeryChecklistId implements java.io.Serializable {

	// Fields

	private Long patientSurgeryId;
	private Long checklistInstanceId;
	private String surgeryStatusCode;

	// Constructors

	/** default constructor */
	public OtPatientSurgeryChecklistId() {
	}

	/** full constructor */
	public OtPatientSurgeryChecklistId(Long patientSurgeryId,
			Long checklistInstanceId, String surgeryStatusCode) {
		this.patientSurgeryId = patientSurgeryId;
		this.checklistInstanceId = checklistInstanceId;
		this.surgeryStatusCode = surgeryStatusCode;
	}

	// Property accessors

	public Long getPatientSurgeryId() {
		return this.patientSurgeryId;
	}

	public void setPatientSurgeryId(Long patientSurgeryId) {
		this.patientSurgeryId = patientSurgeryId;
	}

	public Long getChecklistInstanceId() {
		return this.checklistInstanceId;
	}

	public void setChecklistInstanceId(Long checklistInstanceId) {
		this.checklistInstanceId = checklistInstanceId;
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
		if (!(other instanceof OtPatientSurgeryChecklistId))
			return false;
		OtPatientSurgeryChecklistId castOther = (OtPatientSurgeryChecklistId) other;

		return ((this.getPatientSurgeryId() == castOther.getPatientSurgeryId()) || (this
				.getPatientSurgeryId() != null
				&& castOther.getPatientSurgeryId() != null && this
				.getPatientSurgeryId().equals(castOther.getPatientSurgeryId())))
				&& ((this.getChecklistInstanceId() == castOther
						.getChecklistInstanceId()) || (this
						.getChecklistInstanceId() != null
						&& castOther.getChecklistInstanceId() != null && this
						.getChecklistInstanceId().equals(
								castOther.getChecklistInstanceId())))
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
				+ (getPatientSurgeryId() == null ? 0 : this
						.getPatientSurgeryId().hashCode());
		result = 37
				* result
				+ (getChecklistInstanceId() == null ? 0 : this
						.getChecklistInstanceId().hashCode());
		result = 37
				* result
				+ (getSurgeryStatusCode() == null ? 0 : this
						.getSurgeryStatusCode().hashCode());
		return result;
	}

}