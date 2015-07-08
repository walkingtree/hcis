package com.wtc.hcis.da;

/**
 * LabPatientTestSpecimenId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabPatientTestSpecimenId implements java.io.Serializable {

	// Fields

	private String patientTestId;
	private Integer specimenId;

	// Constructors

	/** default constructor */
	public LabPatientTestSpecimenId() {
	}

	/** full constructor */
	public LabPatientTestSpecimenId(String patientTestId, Integer specimenId) {
		this.patientTestId = patientTestId;
		this.specimenId = specimenId;
	}

	// Property accessors

	public String getPatientTestId() {
		return this.patientTestId;
	}

	public void setPatientTestId(String patientTestId) {
		this.patientTestId = patientTestId;
	}

	public Integer getSpecimenId() {
		return this.specimenId;
	}

	public void setSpecimenId(Integer specimenId) {
		this.specimenId = specimenId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LabPatientTestSpecimenId))
			return false;
		LabPatientTestSpecimenId castOther = (LabPatientTestSpecimenId) other;

		return ((this.getPatientTestId() == castOther.getPatientTestId()) || (this
				.getPatientTestId() != null
				&& castOther.getPatientTestId() != null && this
				.getPatientTestId().equals(castOther.getPatientTestId())))
				&& ((this.getSpecimenId() == castOther.getSpecimenId()) || (this
						.getSpecimenId() != null
						&& castOther.getSpecimenId() != null && this
						.getSpecimenId().equals(castOther.getSpecimenId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getPatientTestId() == null ? 0 : this.getPatientTestId()
						.hashCode());
		result = 37
				* result
				+ (getSpecimenId() == null ? 0 : this.getSpecimenId()
						.hashCode());
		return result;
	}

}