package com.wtc.hcis.da;

/**
 * RemindersId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class RemindersId implements java.io.Serializable {

	// Fields

	private Integer appointmentNumber;
	private String reminderOptionCode;
	private String reminderNo;
	private String reminderFor;

	// Constructors

	/** default constructor */
	public RemindersId() {
	}

	/** full constructor */
	public RemindersId(Integer appointmentNumber, String reminderOptionCode,
			String reminderNo, String reminderFor) {
		this.appointmentNumber = appointmentNumber;
		this.reminderOptionCode = reminderOptionCode;
		this.reminderNo = reminderNo;
		this.reminderFor = reminderFor;
	}

	// Property accessors

	public Integer getAppointmentNumber() {
		return this.appointmentNumber;
	}

	public void setAppointmentNumber(Integer appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}

	public String getReminderOptionCode() {
		return this.reminderOptionCode;
	}

	public void setReminderOptionCode(String reminderOptionCode) {
		this.reminderOptionCode = reminderOptionCode;
	}

	public String getReminderNo() {
		return this.reminderNo;
	}

	public void setReminderNo(String reminderNo) {
		this.reminderNo = reminderNo;
	}

	public String getReminderFor() {
		return this.reminderFor;
	}

	public void setReminderFor(String reminderFor) {
		this.reminderFor = reminderFor;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RemindersId))
			return false;
		RemindersId castOther = (RemindersId) other;

		return ((this.getAppointmentNumber() == castOther
				.getAppointmentNumber()) || (this.getAppointmentNumber() != null
				&& castOther.getAppointmentNumber() != null && this
				.getAppointmentNumber()
				.equals(castOther.getAppointmentNumber())))
				&& ((this.getReminderOptionCode() == castOther
						.getReminderOptionCode()) || (this
						.getReminderOptionCode() != null
						&& castOther.getReminderOptionCode() != null && this
						.getReminderOptionCode().equals(
								castOther.getReminderOptionCode())))
				&& ((this.getReminderNo() == castOther.getReminderNo()) || (this
						.getReminderNo() != null
						&& castOther.getReminderNo() != null && this
						.getReminderNo().equals(castOther.getReminderNo())))
				&& ((this.getReminderFor() == castOther.getReminderFor()) || (this
						.getReminderFor() != null
						&& castOther.getReminderFor() != null && this
						.getReminderFor().equals(castOther.getReminderFor())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getAppointmentNumber() == null ? 0 : this
						.getAppointmentNumber().hashCode());
		result = 37
				* result
				+ (getReminderOptionCode() == null ? 0 : this
						.getReminderOptionCode().hashCode());
		result = 37
				* result
				+ (getReminderNo() == null ? 0 : this.getReminderNo()
						.hashCode());
		result = 37
				* result
				+ (getReminderFor() == null ? 0 : this.getReminderFor()
						.hashCode());
		return result;
	}

}