package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * BookingType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BookingType implements java.io.Serializable {

	// Fields

	private String bookingTypeCode;
	private String description;
	private Short active;
	private Set appointmentses = new HashSet(0);

	// Constructors

	/** default constructor */
	public BookingType() {
	}

	/** full constructor */
	public BookingType(String description, Short active, Set appointmentses) {
		this.description = description;
		this.active = active;
		this.appointmentses = appointmentses;
	}

	// Property accessors

	public String getBookingTypeCode() {
		return this.bookingTypeCode;
	}

	public void setBookingTypeCode(String bookingTypeCode) {
		this.bookingTypeCode = bookingTypeCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getActive() {
		return this.active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

	public Set getAppointmentses() {
		return this.appointmentses;
	}

	public void setAppointmentses(Set appointmentses) {
		this.appointmentses = appointmentses;
	}

}