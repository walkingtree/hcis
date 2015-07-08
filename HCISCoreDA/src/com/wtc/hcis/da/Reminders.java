package com.wtc.hcis.da;

/**
 * Reminders entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Reminders implements java.io.Serializable {

	// Fields

	private RemindersId id;
	private Integer version;
	private Appointments appointments;
	private AppEntities appEntities;
	private ReminderOptions reminderOptions;
	private Duration duration;
	private String reminderTime;

	// Constructors

	/** default constructor */
	public Reminders() {
	}

	/** minimal constructor */
	public Reminders(RemindersId id, Appointments appointments,
			AppEntities appEntities, ReminderOptions reminderOptions,
			String reminderTime) {
		this.id = id;
		this.appointments = appointments;
		this.appEntities = appEntities;
		this.reminderOptions = reminderOptions;
		this.reminderTime = reminderTime;
	}

	/** full constructor */
	public Reminders(RemindersId id, Appointments appointments,
			AppEntities appEntities, ReminderOptions reminderOptions,
			Duration duration, String reminderTime) {
		this.id = id;
		this.appointments = appointments;
		this.appEntities = appEntities;
		this.reminderOptions = reminderOptions;
		this.duration = duration;
		this.reminderTime = reminderTime;
	}

	// Property accessors

	public RemindersId getId() {
		return this.id;
	}

	public void setId(RemindersId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Appointments getAppointments() {
		return this.appointments;
	}

	public void setAppointments(Appointments appointments) {
		this.appointments = appointments;
	}

	public AppEntities getAppEntities() {
		return this.appEntities;
	}

	public void setAppEntities(AppEntities appEntities) {
		this.appEntities = appEntities;
	}

	public ReminderOptions getReminderOptions() {
		return this.reminderOptions;
	}

	public void setReminderOptions(ReminderOptions reminderOptions) {
		this.reminderOptions = reminderOptions;
	}

	public Duration getDuration() {
		return this.duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public String getReminderTime() {
		return this.reminderTime;
	}

	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}

}