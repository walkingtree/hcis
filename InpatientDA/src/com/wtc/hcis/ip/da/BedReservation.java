package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BedReservation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedReservation implements java.io.Serializable {

	// Fields

	private Integer bedReservationNbr;
	private Integer version;
	private BedMaster bedMaster;
	private ReservationStatus reservationStatus;
	private Admission admission;
	private BedType bedType;
	private NursingUnitType nursingUnitType;
	private Date requestValidTill;
	private String requestCreatedBy;
	private String gotPreferredRoom;
	private Date reservationFromDtm;
	private Date reservationToDtm;
	private Integer patientId;
	private Date requestCreationDtm;
	private String modifiedBy;
	private Date lastModifiedTime;
	private Set bedReservationActivities = new HashSet(0);
	private Set bedReservationSpecialFeatureses = new HashSet(0);
	private Set otBookings = new HashSet(0);

	// Constructors

	/** default constructor */
	public BedReservation() {
	}

	/** minimal constructor */
	public BedReservation(Integer bedReservationNbr,
			ReservationStatus reservationStatus, Admission admission,
			BedType bedType, NursingUnitType nursingUnitType,
			Date requestValidTill, String requestCreatedBy,
			Date reservationFromDtm) {
		this.bedReservationNbr = bedReservationNbr;
		this.reservationStatus = reservationStatus;
		this.admission = admission;
		this.bedType = bedType;
		this.nursingUnitType = nursingUnitType;
		this.requestValidTill = requestValidTill;
		this.requestCreatedBy = requestCreatedBy;
		this.reservationFromDtm = reservationFromDtm;
	}

	/** full constructor */
	public BedReservation(Integer bedReservationNbr, BedMaster bedMaster,
			ReservationStatus reservationStatus, Admission admission,
			BedType bedType, NursingUnitType nursingUnitType,
			Date requestValidTill, String requestCreatedBy,
			String gotPreferredRoom, Date reservationFromDtm,
			Date reservationToDtm, Integer patientId, Date requestCreationDtm,
			String modifiedBy, Date lastModifiedTime,
			Set bedReservationActivities, Set bedReservationSpecialFeatureses,
			Set otBookings) {
		this.bedReservationNbr = bedReservationNbr;
		this.bedMaster = bedMaster;
		this.reservationStatus = reservationStatus;
		this.admission = admission;
		this.bedType = bedType;
		this.nursingUnitType = nursingUnitType;
		this.requestValidTill = requestValidTill;
		this.requestCreatedBy = requestCreatedBy;
		this.gotPreferredRoom = gotPreferredRoom;
		this.reservationFromDtm = reservationFromDtm;
		this.reservationToDtm = reservationToDtm;
		this.patientId = patientId;
		this.requestCreationDtm = requestCreationDtm;
		this.modifiedBy = modifiedBy;
		this.lastModifiedTime = lastModifiedTime;
		this.bedReservationActivities = bedReservationActivities;
		this.bedReservationSpecialFeatureses = bedReservationSpecialFeatureses;
		this.otBookings = otBookings;
	}

	// Property accessors

	public Integer getBedReservationNbr() {
		return this.bedReservationNbr;
	}

	public void setBedReservationNbr(Integer bedReservationNbr) {
		this.bedReservationNbr = bedReservationNbr;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public BedMaster getBedMaster() {
		return this.bedMaster;
	}

	public void setBedMaster(BedMaster bedMaster) {
		this.bedMaster = bedMaster;
	}

	public ReservationStatus getReservationStatus() {
		return this.reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public Admission getAdmission() {
		return this.admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public BedType getBedType() {
		return this.bedType;
	}

	public void setBedType(BedType bedType) {
		this.bedType = bedType;
	}

	public NursingUnitType getNursingUnitType() {
		return this.nursingUnitType;
	}

	public void setNursingUnitType(NursingUnitType nursingUnitType) {
		this.nursingUnitType = nursingUnitType;
	}

	public Date getRequestValidTill() {
		return this.requestValidTill;
	}

	public void setRequestValidTill(Date requestValidTill) {
		this.requestValidTill = requestValidTill;
	}

	public String getRequestCreatedBy() {
		return this.requestCreatedBy;
	}

	public void setRequestCreatedBy(String requestCreatedBy) {
		this.requestCreatedBy = requestCreatedBy;
	}

	public String getGotPreferredRoom() {
		return this.gotPreferredRoom;
	}

	public void setGotPreferredRoom(String gotPreferredRoom) {
		this.gotPreferredRoom = gotPreferredRoom;
	}

	public Date getReservationFromDtm() {
		return this.reservationFromDtm;
	}

	public void setReservationFromDtm(Date reservationFromDtm) {
		this.reservationFromDtm = reservationFromDtm;
	}

	public Date getReservationToDtm() {
		return this.reservationToDtm;
	}

	public void setReservationToDtm(Date reservationToDtm) {
		this.reservationToDtm = reservationToDtm;
	}

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Date getRequestCreationDtm() {
		return this.requestCreationDtm;
	}

	public void setRequestCreationDtm(Date requestCreationDtm) {
		this.requestCreationDtm = requestCreationDtm;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getLastModifiedTime() {
		return this.lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public Set getBedReservationActivities() {
		return this.bedReservationActivities;
	}

	public void setBedReservationActivities(Set bedReservationActivities) {
		this.bedReservationActivities = bedReservationActivities;
	}

	public Set getBedReservationSpecialFeatureses() {
		return this.bedReservationSpecialFeatureses;
	}

	public void setBedReservationSpecialFeatureses(
			Set bedReservationSpecialFeatureses) {
		this.bedReservationSpecialFeatureses = bedReservationSpecialFeatureses;
	}

	public Set getOtBookings() {
		return this.otBookings;
	}

	public void setOtBookings(Set otBookings) {
		this.otBookings = otBookings;
	}

}