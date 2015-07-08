package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * OtBooking entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtBooking implements java.io.Serializable {

	// Fields

	private Long otBookingNbr;
	private Integer version;
	private OtSurgery otSurgery;
	private BedReservation bedReservation;
	private OtDetail otDetail;
	private Integer doctorId;
	private Integer patientId;
	private String referenceType;
	private String referenceNumber;
	private Double depositAmount;
	private Date bookingFromDtm;
	private Date bookingToDtm;
	private String bookingStatus;
	private String createdBy;
	private Date cretedDtm;
	private String modifiedBy;
	private Date lastModifiedDtm;
	private Set otPatientSurgeries = new HashSet(0);
	private Set otBookingActivities = new HashSet(0);

	// Constructors

	/** default constructor */
	public OtBooking() {
	}

	/** minimal constructor */
	public OtBooking(Long otBookingNbr, OtSurgery otSurgery, OtDetail otDetail,
			Integer doctorId, Integer patientId, Date bookingFromDtm,
			Date bookingToDtm, String bookingStatus, String createdBy,
			Date cretedDtm) {
		this.otBookingNbr = otBookingNbr;
		this.otSurgery = otSurgery;
		this.otDetail = otDetail;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.bookingFromDtm = bookingFromDtm;
		this.bookingToDtm = bookingToDtm;
		this.bookingStatus = bookingStatus;
		this.createdBy = createdBy;
		this.cretedDtm = cretedDtm;
	}

	/** full constructor */
	public OtBooking(Long otBookingNbr, OtSurgery otSurgery,
			BedReservation bedReservation, OtDetail otDetail, Integer doctorId,
			Integer patientId, String referenceType, String referenceNumber,
			Double depositAmount, Date bookingFromDtm, Date bookingToDtm,
			String bookingStatus, String createdBy, Date cretedDtm,
			String modifiedBy, Date lastModifiedDtm, Set otPatientSurgeries,
			Set otBookingActivities) {
		this.otBookingNbr = otBookingNbr;
		this.otSurgery = otSurgery;
		this.bedReservation = bedReservation;
		this.otDetail = otDetail;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.referenceType = referenceType;
		this.referenceNumber = referenceNumber;
		this.depositAmount = depositAmount;
		this.bookingFromDtm = bookingFromDtm;
		this.bookingToDtm = bookingToDtm;
		this.bookingStatus = bookingStatus;
		this.createdBy = createdBy;
		this.cretedDtm = cretedDtm;
		this.modifiedBy = modifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.otPatientSurgeries = otPatientSurgeries;
		this.otBookingActivities = otBookingActivities;
	}

	// Property accessors

	public Long getOtBookingNbr() {
		return this.otBookingNbr;
	}

	public void setOtBookingNbr(Long otBookingNbr) {
		this.otBookingNbr = otBookingNbr;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public OtSurgery getOtSurgery() {
		return this.otSurgery;
	}

	public void setOtSurgery(OtSurgery otSurgery) {
		this.otSurgery = otSurgery;
	}

	public BedReservation getBedReservation() {
		return this.bedReservation;
	}

	public void setBedReservation(BedReservation bedReservation) {
		this.bedReservation = bedReservation;
	}

	public OtDetail getOtDetail() {
		return this.otDetail;
	}

	public void setOtDetail(OtDetail otDetail) {
		this.otDetail = otDetail;
	}

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getReferenceType() {
		return this.referenceType;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Double getDepositAmount() {
		return this.depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Date getBookingFromDtm() {
		return this.bookingFromDtm;
	}

	public void setBookingFromDtm(Date bookingFromDtm) {
		this.bookingFromDtm = bookingFromDtm;
	}

	public Date getBookingToDtm() {
		return this.bookingToDtm;
	}

	public void setBookingToDtm(Date bookingToDtm) {
		this.bookingToDtm = bookingToDtm;
	}

	public String getBookingStatus() {
		return this.bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCretedDtm() {
		return this.cretedDtm;
	}

	public void setCretedDtm(Date cretedDtm) {
		this.cretedDtm = cretedDtm;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public Set getOtPatientSurgeries() {
		return this.otPatientSurgeries;
	}

	public void setOtPatientSurgeries(Set otPatientSurgeries) {
		this.otPatientSurgeries = otPatientSurgeries;
	}

	public Set getOtBookingActivities() {
		return this.otBookingActivities;
	}

	public void setOtBookingActivities(Set otBookingActivities) {
		this.otBookingActivities = otBookingActivities;
	}

}