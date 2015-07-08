package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * OtPatientSurgery entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtPatientSurgery implements java.io.Serializable {

	// Fields

	private Long patientSurgeryId;
	private Integer version;
	private OtSurgery otSurgery;
	private OtBooking otBooking;
	private OtDetail otDetail;
	private Integer patientId;
	private Integer doctorId;
	private Integer anesthetistId;
	private String statusCode;
	private Integer coordinatorId;
	private Date surgeryDate;
	private String remarks;
	private String createdBy;
	private Date cratedDtm;
	private String modifiedBy;
	private Date lastModifiedDtm;
	private Set otPatientSurgeryChecklists = new HashSet(0);
	private Set otPatientSurgeryNoteses = new HashSet(0);
	private Set otPatientSurgeryActivities = new HashSet(0);

	// Constructors

	/** default constructor */
	public OtPatientSurgery() {
	}

	/** minimal constructor */
	public OtPatientSurgery(Long patientSurgeryId, OtSurgery otSurgery,
			OtBooking otBooking, OtDetail otDetail, Integer patientId,
			Integer doctorId, String statusCode, String createdBy,
			Date cratedDtm) {
		this.patientSurgeryId = patientSurgeryId;
		this.otSurgery = otSurgery;
		this.otBooking = otBooking;
		this.otDetail = otDetail;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.statusCode = statusCode;
		this.createdBy = createdBy;
		this.cratedDtm = cratedDtm;
	}

	/** full constructor */
	public OtPatientSurgery(Long patientSurgeryId, OtSurgery otSurgery,
			OtBooking otBooking, OtDetail otDetail, Integer patientId,
			Integer doctorId, Integer anesthetistId, String statusCode,
			Integer coordinatorId, Date surgeryDate, String remarks,
			String createdBy, Date cratedDtm, String modifiedBy,
			Date lastModifiedDtm, Set otPatientSurgeryChecklists,
			Set otPatientSurgeryNoteses, Set otPatientSurgeryActivities) {
		this.patientSurgeryId = patientSurgeryId;
		this.otSurgery = otSurgery;
		this.otBooking = otBooking;
		this.otDetail = otDetail;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.anesthetistId = anesthetistId;
		this.statusCode = statusCode;
		this.coordinatorId = coordinatorId;
		this.surgeryDate = surgeryDate;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.cratedDtm = cratedDtm;
		this.modifiedBy = modifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.otPatientSurgeryChecklists = otPatientSurgeryChecklists;
		this.otPatientSurgeryNoteses = otPatientSurgeryNoteses;
		this.otPatientSurgeryActivities = otPatientSurgeryActivities;
	}

	// Property accessors

	public Long getPatientSurgeryId() {
		return this.patientSurgeryId;
	}

	public void setPatientSurgeryId(Long patientSurgeryId) {
		this.patientSurgeryId = patientSurgeryId;
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

	public OtBooking getOtBooking() {
		return this.otBooking;
	}

	public void setOtBooking(OtBooking otBooking) {
		this.otBooking = otBooking;
	}

	public OtDetail getOtDetail() {
		return this.otDetail;
	}

	public void setOtDetail(OtDetail otDetail) {
		this.otDetail = otDetail;
	}

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getAnesthetistId() {
		return this.anesthetistId;
	}

	public void setAnesthetistId(Integer anesthetistId) {
		this.anesthetistId = anesthetistId;
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getCoordinatorId() {
		return this.coordinatorId;
	}

	public void setCoordinatorId(Integer coordinatorId) {
		this.coordinatorId = coordinatorId;
	}

	public Date getSurgeryDate() {
		return this.surgeryDate;
	}

	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
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

	public Date getCratedDtm() {
		return this.cratedDtm;
	}

	public void setCratedDtm(Date cratedDtm) {
		this.cratedDtm = cratedDtm;
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

	public Set getOtPatientSurgeryChecklists() {
		return this.otPatientSurgeryChecklists;
	}

	public void setOtPatientSurgeryChecklists(Set otPatientSurgeryChecklists) {
		this.otPatientSurgeryChecklists = otPatientSurgeryChecklists;
	}

	public Set getOtPatientSurgeryNoteses() {
		return this.otPatientSurgeryNoteses;
	}

	public void setOtPatientSurgeryNoteses(Set otPatientSurgeryNoteses) {
		this.otPatientSurgeryNoteses = otPatientSurgeryNoteses;
	}

	public Set getOtPatientSurgeryActivities() {
		return this.otPatientSurgeryActivities;
	}

	public void setOtPatientSurgeryActivities(Set otPatientSurgeryActivities) {
		this.otPatientSurgeryActivities = otPatientSurgeryActivities;
	}

}