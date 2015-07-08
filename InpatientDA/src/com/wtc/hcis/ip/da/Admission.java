package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Admission entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Admission implements java.io.Serializable {

	// Fields

	private Integer admissionReqNbr;
	private Integer version;
	private AdmissionStatus admissionStatus;
	private AdmissionEntryPoint admissionEntryPoint;
	private NursingUnitType nursingUnitType;
	private Integer admissionNbr;
	private String admissionRequestedBy;
	private Integer doctorId;
	private Integer patientId;
	private String entryPointReference;
	private String reasonForAdmission;
	private String agenda;
	private Date admissionDt;
	private String treatmentEstimationBy;
	private Double treatmentEstimatedCost;
	private Double treatmentActualCost;
	private Date expectedDischargeDtm;
	private Date dischargeDtm;
	private Integer dischargeByDoctorId;
	private Date nextVisitDt;
	private Date createDtm;
	private Date lastUpdatedDtm;
	private String modifiedBy;
	private Set dischargeOrders = new HashSet(0);
	private Set bedMasters = new HashSet(0);
	private Set admissionActivities = new HashSet(0);
	private Set dischargeActivities = new HashSet(0);
	private Set bedUsageHistories = new HashSet(0);
	private Set doctorOrders = new HashSet(0);
	private Set admissionClaimRequests = new HashSet(0);
	private Set bedReservations = new HashSet(0);

	// Constructors

	/** default constructor */
	public Admission() {
	}

	/** minimal constructor */
	public Admission(Integer admissionReqNbr, String admissionRequestedBy,
			Integer doctorId, Integer patientId, Date createDtm) {
		this.admissionReqNbr = admissionReqNbr;
		this.admissionRequestedBy = admissionRequestedBy;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.createDtm = createDtm;
	}

	/** full constructor */
	public Admission(Integer admissionReqNbr, AdmissionStatus admissionStatus,
			AdmissionEntryPoint admissionEntryPoint,
			NursingUnitType nursingUnitType, Integer admissionNbr,
			String admissionRequestedBy, Integer doctorId, Integer patientId,
			String entryPointReference, String reasonForAdmission,
			String agenda, Date admissionDt, String treatmentEstimationBy,
			Double treatmentEstimatedCost, Double treatmentActualCost,
			Date expectedDischargeDtm, Date dischargeDtm,
			Integer dischargeByDoctorId, Date nextVisitDt, Date createDtm,
			Date lastUpdatedDtm, String modifiedBy, Set dischargeOrders,
			Set bedMasters, Set admissionActivities, Set dischargeActivities,
			Set bedUsageHistories, Set doctorOrders,
			Set admissionClaimRequests, Set bedReservations) {
		this.admissionReqNbr = admissionReqNbr;
		this.admissionStatus = admissionStatus;
		this.admissionEntryPoint = admissionEntryPoint;
		this.nursingUnitType = nursingUnitType;
		this.admissionNbr = admissionNbr;
		this.admissionRequestedBy = admissionRequestedBy;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.entryPointReference = entryPointReference;
		this.reasonForAdmission = reasonForAdmission;
		this.agenda = agenda;
		this.admissionDt = admissionDt;
		this.treatmentEstimationBy = treatmentEstimationBy;
		this.treatmentEstimatedCost = treatmentEstimatedCost;
		this.treatmentActualCost = treatmentActualCost;
		this.expectedDischargeDtm = expectedDischargeDtm;
		this.dischargeDtm = dischargeDtm;
		this.dischargeByDoctorId = dischargeByDoctorId;
		this.nextVisitDt = nextVisitDt;
		this.createDtm = createDtm;
		this.lastUpdatedDtm = lastUpdatedDtm;
		this.modifiedBy = modifiedBy;
		this.dischargeOrders = dischargeOrders;
		this.bedMasters = bedMasters;
		this.admissionActivities = admissionActivities;
		this.dischargeActivities = dischargeActivities;
		this.bedUsageHistories = bedUsageHistories;
		this.doctorOrders = doctorOrders;
		this.admissionClaimRequests = admissionClaimRequests;
		this.bedReservations = bedReservations;
	}

	// Property accessors

	public Integer getAdmissionReqNbr() {
		return this.admissionReqNbr;
	}

	public void setAdmissionReqNbr(Integer admissionReqNbr) {
		this.admissionReqNbr = admissionReqNbr;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public AdmissionStatus getAdmissionStatus() {
		return this.admissionStatus;
	}

	public void setAdmissionStatus(AdmissionStatus admissionStatus) {
		this.admissionStatus = admissionStatus;
	}

	public AdmissionEntryPoint getAdmissionEntryPoint() {
		return this.admissionEntryPoint;
	}

	public void setAdmissionEntryPoint(AdmissionEntryPoint admissionEntryPoint) {
		this.admissionEntryPoint = admissionEntryPoint;
	}

	public NursingUnitType getNursingUnitType() {
		return this.nursingUnitType;
	}

	public void setNursingUnitType(NursingUnitType nursingUnitType) {
		this.nursingUnitType = nursingUnitType;
	}

	public Integer getAdmissionNbr() {
		return this.admissionNbr;
	}

	public void setAdmissionNbr(Integer admissionNbr) {
		this.admissionNbr = admissionNbr;
	}

	public String getAdmissionRequestedBy() {
		return this.admissionRequestedBy;
	}

	public void setAdmissionRequestedBy(String admissionRequestedBy) {
		this.admissionRequestedBy = admissionRequestedBy;
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

	public String getEntryPointReference() {
		return this.entryPointReference;
	}

	public void setEntryPointReference(String entryPointReference) {
		this.entryPointReference = entryPointReference;
	}

	public String getReasonForAdmission() {
		return this.reasonForAdmission;
	}

	public void setReasonForAdmission(String reasonForAdmission) {
		this.reasonForAdmission = reasonForAdmission;
	}

	public String getAgenda() {
		return this.agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public Date getAdmissionDt() {
		return this.admissionDt;
	}

	public void setAdmissionDt(Date admissionDt) {
		this.admissionDt = admissionDt;
	}

	public String getTreatmentEstimationBy() {
		return this.treatmentEstimationBy;
	}

	public void setTreatmentEstimationBy(String treatmentEstimationBy) {
		this.treatmentEstimationBy = treatmentEstimationBy;
	}

	public Double getTreatmentEstimatedCost() {
		return this.treatmentEstimatedCost;
	}

	public void setTreatmentEstimatedCost(Double treatmentEstimatedCost) {
		this.treatmentEstimatedCost = treatmentEstimatedCost;
	}

	public Double getTreatmentActualCost() {
		return this.treatmentActualCost;
	}

	public void setTreatmentActualCost(Double treatmentActualCost) {
		this.treatmentActualCost = treatmentActualCost;
	}

	public Date getExpectedDischargeDtm() {
		return this.expectedDischargeDtm;
	}

	public void setExpectedDischargeDtm(Date expectedDischargeDtm) {
		this.expectedDischargeDtm = expectedDischargeDtm;
	}

	public Date getDischargeDtm() {
		return this.dischargeDtm;
	}

	public void setDischargeDtm(Date dischargeDtm) {
		this.dischargeDtm = dischargeDtm;
	}

	public Integer getDischargeByDoctorId() {
		return this.dischargeByDoctorId;
	}

	public void setDischargeByDoctorId(Integer dischargeByDoctorId) {
		this.dischargeByDoctorId = dischargeByDoctorId;
	}

	public Date getNextVisitDt() {
		return this.nextVisitDt;
	}

	public void setNextVisitDt(Date nextVisitDt) {
		this.nextVisitDt = nextVisitDt;
	}

	public Date getCreateDtm() {
		return this.createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}

	public Date getLastUpdatedDtm() {
		return this.lastUpdatedDtm;
	}

	public void setLastUpdatedDtm(Date lastUpdatedDtm) {
		this.lastUpdatedDtm = lastUpdatedDtm;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Set getDischargeOrders() {
		return this.dischargeOrders;
	}

	public void setDischargeOrders(Set dischargeOrders) {
		this.dischargeOrders = dischargeOrders;
	}

	public Set getBedMasters() {
		return this.bedMasters;
	}

	public void setBedMasters(Set bedMasters) {
		this.bedMasters = bedMasters;
	}

	public Set getAdmissionActivities() {
		return this.admissionActivities;
	}

	public void setAdmissionActivities(Set admissionActivities) {
		this.admissionActivities = admissionActivities;
	}

	public Set getDischargeActivities() {
		return this.dischargeActivities;
	}

	public void setDischargeActivities(Set dischargeActivities) {
		this.dischargeActivities = dischargeActivities;
	}

	public Set getBedUsageHistories() {
		return this.bedUsageHistories;
	}

	public void setBedUsageHistories(Set bedUsageHistories) {
		this.bedUsageHistories = bedUsageHistories;
	}

	public Set getDoctorOrders() {
		return this.doctorOrders;
	}

	public void setDoctorOrders(Set doctorOrders) {
		this.doctorOrders = doctorOrders;
	}

	public Set getAdmissionClaimRequests() {
		return this.admissionClaimRequests;
	}

	public void setAdmissionClaimRequests(Set admissionClaimRequests) {
		this.admissionClaimRequests = admissionClaimRequests;
	}

	public Set getBedReservations() {
		return this.bedReservations;
	}

	public void setBedReservations(Set bedReservations) {
		this.bedReservations = bedReservations;
	}

}