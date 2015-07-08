package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * DischargeOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DischargeOrder implements java.io.Serializable {

	// Fields

	private Integer admissionReqNbr;
	private Integer version;
	private DoctorOrderStatus doctorOrderStatus;
	private DischargeType dischargeType;
	private Admission admission;
	private DoctorOrder doctorOrder;
	private Date orderCreationDtm;
	private String orderRequestedBy;
	private String approvedBy;
	private Date approvalTime;
	private String modifiedBy;
	private Date lastModifiedDtm;
	private Date expectedDischargeDtm;
	private String dischargeSummary;
	private Date actualDischargeDtm;

	// Constructors

	/** default constructor */
	public DischargeOrder() {
	}

	/** minimal constructor */
	public DischargeOrder(Integer admissionReqNbr, DischargeType dischargeType,
			Admission admission, DoctorOrder doctorOrder, Date orderCreationDtm) {
		this.admissionReqNbr = admissionReqNbr;
		this.dischargeType = dischargeType;
		this.admission = admission;
		this.doctorOrder = doctorOrder;
		this.orderCreationDtm = orderCreationDtm;
	}

	/** full constructor */
	public DischargeOrder(Integer admissionReqNbr,
			DoctorOrderStatus doctorOrderStatus, DischargeType dischargeType,
			Admission admission, DoctorOrder doctorOrder,
			Date orderCreationDtm, String orderRequestedBy, String approvedBy,
			Date approvalTime, String modifiedBy, Date lastModifiedDtm,
			Date expectedDischargeDtm, String dischargeSummary,
			Date actualDischargeDtm) {
		this.admissionReqNbr = admissionReqNbr;
		this.doctorOrderStatus = doctorOrderStatus;
		this.dischargeType = dischargeType;
		this.admission = admission;
		this.doctorOrder = doctorOrder;
		this.orderCreationDtm = orderCreationDtm;
		this.orderRequestedBy = orderRequestedBy;
		this.approvedBy = approvedBy;
		this.approvalTime = approvalTime;
		this.modifiedBy = modifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.expectedDischargeDtm = expectedDischargeDtm;
		this.dischargeSummary = dischargeSummary;
		this.actualDischargeDtm = actualDischargeDtm;
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

	public DoctorOrderStatus getDoctorOrderStatus() {
		return this.doctorOrderStatus;
	}

	public void setDoctorOrderStatus(DoctorOrderStatus doctorOrderStatus) {
		this.doctorOrderStatus = doctorOrderStatus;
	}

	public DischargeType getDischargeType() {
		return this.dischargeType;
	}

	public void setDischargeType(DischargeType dischargeType) {
		this.dischargeType = dischargeType;
	}

	public Admission getAdmission() {
		return this.admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public DoctorOrder getDoctorOrder() {
		return this.doctorOrder;
	}

	public void setDoctorOrder(DoctorOrder doctorOrder) {
		this.doctorOrder = doctorOrder;
	}

	public Date getOrderCreationDtm() {
		return this.orderCreationDtm;
	}

	public void setOrderCreationDtm(Date orderCreationDtm) {
		this.orderCreationDtm = orderCreationDtm;
	}

	public String getOrderRequestedBy() {
		return this.orderRequestedBy;
	}

	public void setOrderRequestedBy(String orderRequestedBy) {
		this.orderRequestedBy = orderRequestedBy;
	}

	public String getApprovedBy() {
		return this.approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Date getApprovalTime() {
		return this.approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
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

	public Date getExpectedDischargeDtm() {
		return this.expectedDischargeDtm;
	}

	public void setExpectedDischargeDtm(Date expectedDischargeDtm) {
		this.expectedDischargeDtm = expectedDischargeDtm;
	}

	public String getDischargeSummary() {
		return this.dischargeSummary;
	}

	public void setDischargeSummary(String dischargeSummary) {
		this.dischargeSummary = dischargeSummary;
	}

	public Date getActualDischargeDtm() {
		return this.actualDischargeDtm;
	}

	public void setActualDischargeDtm(Date actualDischargeDtm) {
		this.actualDischargeDtm = actualDischargeDtm;
	}

}