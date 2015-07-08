package com.wtc.hcis.ip.da;

/**
 * OtPatientSurgeryNotesId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtPatientSurgeryNotesId implements java.io.Serializable {

	// Fields

	private Long patientSurgeryId;
	private String fieldCode;

	// Constructors

	/** default constructor */
	public OtPatientSurgeryNotesId() {
	}

	/** full constructor */
	public OtPatientSurgeryNotesId(Long patientSurgeryId, String fieldCode) {
		this.patientSurgeryId = patientSurgeryId;
		this.fieldCode = fieldCode;
	}

	// Property accessors

	public Long getPatientSurgeryId() {
		return this.patientSurgeryId;
	}

	public void setPatientSurgeryId(Long patientSurgeryId) {
		this.patientSurgeryId = patientSurgeryId;
	}

	public String getFieldCode() {
		return this.fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OtPatientSurgeryNotesId))
			return false;
		OtPatientSurgeryNotesId castOther = (OtPatientSurgeryNotesId) other;

		return ((this.getPatientSurgeryId() == castOther.getPatientSurgeryId()) || (this
				.getPatientSurgeryId() != null
				&& castOther.getPatientSurgeryId() != null && this
				.getPatientSurgeryId().equals(castOther.getPatientSurgeryId())))
				&& ((this.getFieldCode() == castOther.getFieldCode()) || (this
						.getFieldCode() != null
						&& castOther.getFieldCode() != null && this
						.getFieldCode().equals(castOther.getFieldCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getPatientSurgeryId() == null ? 0 : this
						.getPatientSurgeryId().hashCode());
		result = 37 * result
				+ (getFieldCode() == null ? 0 : this.getFieldCode().hashCode());
		return result;
	}

}