package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * ReservationStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ReservationStatus implements java.io.Serializable {

	// Fields

	private String reservationStatusCd;
	private Integer version;
	private String description;
	private String activeFlag;
	private Set bedReservations = new HashSet(0);
	private Set bedReservationActivities = new HashSet(0);

	// Constructors

	/** default constructor */
	public ReservationStatus() {
	}

	/** minimal constructor */
	public ReservationStatus(String reservationStatusCd) {
		this.reservationStatusCd = reservationStatusCd;
	}

	/** full constructor */
	public ReservationStatus(String reservationStatusCd, String description,
			String activeFlag, Set bedReservations, Set bedReservationActivities) {
		this.reservationStatusCd = reservationStatusCd;
		this.description = description;
		this.activeFlag = activeFlag;
		this.bedReservations = bedReservations;
		this.bedReservationActivities = bedReservationActivities;
	}

	// Property accessors

	public String getReservationStatusCd() {
		return this.reservationStatusCd;
	}

	public void setReservationStatusCd(String reservationStatusCd) {
		this.reservationStatusCd = reservationStatusCd;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Set getBedReservations() {
		return this.bedReservations;
	}

	public void setBedReservations(Set bedReservations) {
		this.bedReservations = bedReservations;
	}

	public Set getBedReservationActivities() {
		return this.bedReservationActivities;
	}

	public void setBedReservationActivities(Set bedReservationActivities) {
		this.bedReservationActivities = bedReservationActivities;
	}

}