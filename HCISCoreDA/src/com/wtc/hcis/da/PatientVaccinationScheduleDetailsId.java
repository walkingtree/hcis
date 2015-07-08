package com.wtc.hcis.da;

/**
 * PatientVaccinationScheduleDetailsId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PatientVaccinationScheduleDetailsId implements
		java.io.Serializable {

	// Fields

	private Integer seqNbr;
	private Integer subSeqNbr;
	private String scheduleName;
	private Integer patientId;

	// Constructors

	/** default constructor */
	public PatientVaccinationScheduleDetailsId() {
	}

	/** full constructor */
	public PatientVaccinationScheduleDetailsId(Integer seqNbr,
			Integer subSeqNbr, String scheduleName, Integer patientId) {
		this.seqNbr = seqNbr;
		this.subSeqNbr = subSeqNbr;
		this.scheduleName = scheduleName;
		this.patientId = patientId;
	}

	// Property accessors

	public Integer getSeqNbr() {
		return this.seqNbr;
	}

	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}

	public Integer getSubSeqNbr() {
		return this.subSeqNbr;
	}

	public void setSubSeqNbr(Integer subSeqNbr) {
		this.subSeqNbr = subSeqNbr;
	}

	public String getScheduleName() {
		return this.scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PatientVaccinationScheduleDetailsId))
			return false;
		PatientVaccinationScheduleDetailsId castOther = (PatientVaccinationScheduleDetailsId) other;

		return ((this.getSeqNbr() == castOther.getSeqNbr()) || (this
				.getSeqNbr() != null
				&& castOther.getSeqNbr() != null && this.getSeqNbr().equals(
				castOther.getSeqNbr())))
				&& ((this.getSubSeqNbr() == castOther.getSubSeqNbr()) || (this
						.getSubSeqNbr() != null
						&& castOther.getSubSeqNbr() != null && this
						.getSubSeqNbr().equals(castOther.getSubSeqNbr())))
				&& ((this.getScheduleName() == castOther.getScheduleName()) || (this
						.getScheduleName() != null
						&& castOther.getScheduleName() != null && this
						.getScheduleName().equals(castOther.getScheduleName())))
				&& ((this.getPatientId() == castOther.getPatientId()) || (this
						.getPatientId() != null
						&& castOther.getPatientId() != null && this
						.getPatientId().equals(castOther.getPatientId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSeqNbr() == null ? 0 : this.getSeqNbr().hashCode());
		result = 37 * result
				+ (getSubSeqNbr() == null ? 0 : this.getSubSeqNbr().hashCode());
		result = 37
				* result
				+ (getScheduleName() == null ? 0 : this.getScheduleName()
						.hashCode());
		result = 37 * result
				+ (getPatientId() == null ? 0 : this.getPatientId().hashCode());
		return result;
	}

}