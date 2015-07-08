package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LabRequisitionOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabRequisitionOrder implements java.io.Serializable {

	// Fields

	private Integer orderNbr;
	private Integer version;
	private Patient patient;
	private Doctor doctor;
	private String statusCode;
	private String createdBy;
	private Date createdDtm;
	private Double totalCharges;
	private Double refundedAmt;
	private Date lastBillingDtm;
	private Double refundableAmt;
	private String referenceNumber;
	private String referenceType;
	private Integer referralCode;
	private String isEmergency;
	private Integer createdDateDim;
	private String lastModifiedBy;
	private Date lastModifiedDtm;
	private Set assignedServiceses = new HashSet(0);

	// Constructors

	/** default constructor */
	public LabRequisitionOrder() {
	}

	/** minimal constructor */
	public LabRequisitionOrder(Patient patient, Doctor doctor,
			String statusCode, String createdBy, Date createdDtm,
			Integer createdDateDim) {
		this.patient = patient;
		this.doctor = doctor;
		this.statusCode = statusCode;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.createdDateDim = createdDateDim;
	}

	/** full constructor */
	public LabRequisitionOrder(Patient patient, Doctor doctor,
			String statusCode, String createdBy, Date createdDtm,
			Double totalCharges, Double refundedAmt, Date lastBillingDtm,
			Double refundableAmt, Integer referralCode, String isEmergency,
			Integer createdDateDim, String lastModifiedBy,
			Date lastModifiedDtm, Set assignedServiceses) {
		this.patient = patient;
		this.doctor = doctor;
		this.statusCode = statusCode;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.totalCharges = totalCharges;
		this.refundedAmt = refundedAmt;
		this.lastBillingDtm = lastBillingDtm;
		this.refundableAmt = refundableAmt;
		this.referralCode = referralCode;
		this.isEmergency = isEmergency;
		this.createdDateDim = createdDateDim;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.assignedServiceses = assignedServiceses;
	}

	// Property accessors

	public Integer getOrderNbr() {
		return this.orderNbr;
	}

	public void setOrderNbr(Integer orderNbr) {
		this.orderNbr = orderNbr;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDtm() {
		return this.createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	public Double getTotalCharges() {
		return this.totalCharges;
	}

	public void setTotalCharges(Double totalCharges) {
		this.totalCharges = totalCharges;
	}

	public Double getRefundedAmt() {
		return this.refundedAmt;
	}

	public void setRefundedAmt(Double refundedAmt) {
		this.refundedAmt = refundedAmt;
	}

	public Date getLastBillingDtm() {
		return this.lastBillingDtm;
	}

	public void setLastBillingDtm(Date lastBillingDtm) {
		this.lastBillingDtm = lastBillingDtm;
	}

	public Double getRefundableAmt() {
		return this.refundableAmt;
	}

	public void setRefundableAmt(Double refundableAmt) {
		this.refundableAmt = refundableAmt;
	}

	public Integer getReferralCode() {
		return this.referralCode;
	}

	public void setReferralCode(Integer referralCode) {
		this.referralCode = referralCode;
	}

	public String getIsEmergency() {
		return this.isEmergency;
	}

	public void setIsEmergency(String isEmergency) {
		this.isEmergency = isEmergency;
	}

	public Integer getCreatedDateDim() {
		return this.createdDateDim;
	}

	public void setCreatedDateDim(Integer createdDateDim) {
		this.createdDateDim = createdDateDim;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public Set getAssignedServiceses() {
		return this.assignedServiceses;
	}

	public void setAssignedServiceses(Set assignedServiceses) {
		this.assignedServiceses = assignedServiceses;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

}