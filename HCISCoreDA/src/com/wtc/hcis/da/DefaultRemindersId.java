package com.wtc.hcis.da;

/**
 * DefaultRemindersId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DefaultRemindersId implements java.io.Serializable {

	// Fields

	private Integer personelId;
	private String reminderFor;
	private String reminderOptionCode;
	private String reminderNo;

	// Constructors

	/** default constructor */
	public DefaultRemindersId() {
	}

	/** full constructor */
	public DefaultRemindersId(Integer personelId, String reminderFor,
			String reminderOptionCode, String reminderNo) {
		this.personelId = personelId;
		this.reminderFor = reminderFor;
		this.reminderOptionCode = reminderOptionCode;
		this.reminderNo = reminderNo;
	}

	// Property accessors

	public Integer getPersonelId() {
		return this.personelId;
	}

	public void setPersonelId(Integer personelId) {
		this.personelId = personelId;
	}

	public String getReminderFor() {
		return this.reminderFor;
	}

	public void setReminderFor(String reminderFor) {
		this.reminderFor = reminderFor;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DefaultRemindersId))
			return false;
		DefaultRemindersId castOther = (DefaultRemindersId) other;

		return ((this.getPersonelId() == castOther.getPersonelId()) || (this
				.getPersonelId() != null
				&& castOther.getPersonelId() != null && this.getPersonelId()
				.equals(castOther.getPersonelId())))
				&& ((this.getReminderFor() == castOther.getReminderFor()) || (this
						.getReminderFor() != null
						&& castOther.getReminderFor() != null && this
						.getReminderFor().equals(castOther.getReminderFor())))
				&& ((this.getReminderOptionCode() == castOther
						.getReminderOptionCode()) || (this
						.getReminderOptionCode() != null
						&& castOther.getReminderOptionCode() != null && this
						.getReminderOptionCode().equals(
								castOther.getReminderOptionCode())))
				&& ((this.getReminderNo() == castOther.getReminderNo()) || (this
						.getReminderNo() != null
						&& castOther.getReminderNo() != null && this
						.getReminderNo().equals(castOther.getReminderNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getPersonelId() == null ? 0 : this.getPersonelId()
						.hashCode());
		result = 37
				* result
				+ (getReminderFor() == null ? 0 : this.getReminderFor()
						.hashCode());
		result = 37
				* result
				+ (getReminderOptionCode() == null ? 0 : this
						.getReminderOptionCode().hashCode());
		result = 37
				* result
				+ (getReminderNo() == null ? 0 : this.getReminderNo()
						.hashCode());
		return result;
	}

}