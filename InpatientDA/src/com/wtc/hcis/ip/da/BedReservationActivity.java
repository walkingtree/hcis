package com.wtc.hcis.ip.da;

/**
 * BedReservationActivity entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedReservationActivity implements java.io.Serializable {

	// Fields

	private BedReservationActivityId id;
	private Integer version;
	private ActivityType activityType;
	private BedReservation bedReservation;
	private ReservationStatus reservationStatus;
	private String remarks;
	private String createdBy;

	// Constructors

	/** default constructor */
	public BedReservationActivity() {
	}

	/** minimal constructor */
	public BedReservationActivity(BedReservationActivityId id,
			ActivityType activityType, BedReservation bedReservation,
			ReservationStatus reservationStatus, String createdBy) {
		this.id = id;
		this.activityType = activityType;
		this.bedReservation = bedReservation;
		this.reservationStatus = reservationStatus;
		this.createdBy = createdBy;
	}

	/** full constructor */
	public BedReservationActivity(BedReservationActivityId id,
			ActivityType activityType, BedReservation bedReservation,
			ReservationStatus reservationStatus, String remarks,
			String createdBy) {
		this.id = id;
		this.activityType = activityType;
		this.bedReservation = bedReservation;
		this.reservationStatus = reservationStatus;
		this.remarks = remarks;
		this.createdBy = createdBy;
	}

	// Property accessors

	public BedReservationActivityId getId() {
		return this.id;
	}

	public void setId(BedReservationActivityId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ActivityType getActivityType() {
		return this.activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public BedReservation getBedReservation() {
		return this.bedReservation;
	}

	public void setBedReservation(BedReservation bedReservation) {
		this.bedReservation = bedReservation;
	}

	public ReservationStatus getReservationStatus() {
		return this.reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
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