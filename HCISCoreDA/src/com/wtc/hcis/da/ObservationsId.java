package com.wtc.hcis.da;

/**
 * ObservationsId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ObservationsId implements java.io.Serializable {

	// Fields

	private String observationSeqNo;
	private Integer appointmentNumber;

	// Constructors

	/** default constructor */
	public ObservationsId() {
	}

	/** full constructor */
	public ObservationsId(String observationSeqNo, Integer appointmentNumber) {
		this.observationSeqNo = observationSeqNo;
		this.appointmentNumber = appointmentNumber;
	}

	// Property accessors

	public String getObservationSeqNo() {
		return this.observationSeqNo;
	}

	public void setObservationSeqNo(String observationSeqNo) {
		this.observationSeqNo = observationSeqNo;
	}

	public Integer getAppointmentNumber() {
		return this.appointmentNumber;
	}

	public void setAppointmentNumber(Integer appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ObservationsId))
			return false;
		ObservationsId castOther = (ObservationsId) other;

		return ((this.getObservationSeqNo() == castOther.getObservationSeqNo()) || (this
				.getObservationSeqNo() != null
				&& castOther.getObservationSeqNo() != null && this
				.getObservationSeqNo().equals(castOther.getObservationSeqNo())))
				&& ((this.getAppointmentNumber() == castOther
						.getAppointmentNumber()) || (this
						.getAppointmentNumber() != null
						&& castOther.getAppointmentNumber() != null && this
						.getAppointmentNumber().equals(
								castOther.getAppointmentNumber())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getObservationSeqNo() == null ? 0 : this
						.getObservationSeqNo().hashCode());
		result = 37
				* result
				+ (getAppointmentNumber() == null ? 0 : this
						.getAppointmentNumber().hashCode());
		return result;
	}

}