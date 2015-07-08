package com.wtc.hcis.da;

import java.util.Date;

/**
 * RegistrationHistoryId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class RegistrationHistoryId implements java.io.Serializable {

	// Fields

	private Integer patientId;
	private Date changeDate;

	// Constructors

	/** default constructor */
	public RegistrationHistoryId() {
	}

	/** full constructor */
	public RegistrationHistoryId(Integer patientId, Date changeDate) {
		this.patientId = patientId;
		this.changeDate = changeDate;
	}

	// Property accessors

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Date getChangeDate() {
		return this.changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RegistrationHistoryId))
			return false;
		RegistrationHistoryId castOther = (RegistrationHistoryId) other;

		return ((this.getPatientId() == castOther.getPatientId()) || (this
				.getPatientId() != null
				&& castOther.getPatientId() != null && this.getPatientId()
				.equals(castOther.getPatientId())))
				&& ((this.getChangeDate() == castOther.getChangeDate()) || (this
						.getChangeDate() != null
						&& castOther.getChangeDate() != null && this
						.getChangeDate().equals(castOther.getChangeDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPatientId() == null ? 0 : this.getPatientId().hashCode());
		result = 37
				* result
				+ (getChangeDate() == null ? 0 : this.getChangeDate()
						.hashCode());
		return result;
	}

}