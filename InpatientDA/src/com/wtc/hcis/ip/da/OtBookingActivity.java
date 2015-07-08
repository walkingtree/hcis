package com.wtc.hcis.ip.da;

/**
 * OtBookingActivity entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtBookingActivity implements java.io.Serializable {

	// Fields

	private OtBookingActivityId id;
	private OtBooking otBooking;
	private String bookingStatus;
	private String remarks;
	private String createdBy;

	// Constructors

	/** default constructor */
	public OtBookingActivity() {
	}

	/** minimal constructor */
	public OtBookingActivity(OtBookingActivityId id, OtBooking otBooking,
			String bookingStatus, String createdBy) {
		this.id = id;
		this.otBooking = otBooking;
		this.bookingStatus = bookingStatus;
		this.createdBy = createdBy;
	}

	/** full constructor */
	public OtBookingActivity(OtBookingActivityId id, OtBooking otBooking,
			String bookingStatus, String remarks, String createdBy) {
		this.id = id;
		this.otBooking = otBooking;
		this.bookingStatus = bookingStatus;
		this.remarks = remarks;
		this.createdBy = createdBy;
	}

	// Property accessors

	public OtBookingActivityId getId() {
		return this.id;
	}

	public void setId(OtBookingActivityId id) {
		this.id = id;
	}

	public OtBooking getOtBooking() {
		return this.otBooking;
	}

	public void setOtBooking(OtBooking otBooking) {
		this.otBooking = otBooking;
	}

	public String getBookingStatus() {
		return this.bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}