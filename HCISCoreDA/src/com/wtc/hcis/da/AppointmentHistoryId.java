package com.wtc.hcis.da;

/**
 * AppointmentHistoryId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AppointmentHistoryId implements java.io.Serializable {

	// Fields

	private Integer appointmentNumber;
	private String statusCode;

	// Constructors

	/** default constructor */
	public AppointmentHistoryId() {
	}

	/** full constructor */
	public AppointmentHistoryId(Integer appointmentNumber, String statusCode) {
		this.appointmentNumber = appointmentNumber;
		this.statusCode = statusCode;
	}

	// Property accessors

	public Integer getAppointmentNumber() {
		return this.appointmentNumber;
	}

	public void setAppointmentNumber(Integer appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AppointmentHistoryId))
			return false;
		AppointmentHistoryId castOther = (AppointmentHistoryId) other;

		return ((this.getAppointmentNumber() == castOther
				.getAppointmentNumber()) || (this.getAppointmentNumber() != null
				&& castOther.getAppointmentNumber() != null && this
				.getAppointmentNumber()
				.equals(castOther.getAppointmentNumber())))
				&& ((this.getStatusCode() == castOther.getStatusCode()) || (this
						.getStatusCode() != null
						&& castOther.getStatusCode() != null && this
						.getStatusCode().equals(castOther.getStatusCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAppointmentNumber() == null ? 0 : this
						.getAppointmentNumber().hashCode());
		result = 37
				* result
				+ (getStatusCode() == null ? 0 : this.getStatusCode()
						.hashCode());
		return result;
	}

}