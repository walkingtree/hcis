package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AssignedServices entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AssignedServices implements java.io.Serializable {

	// Fields

	private Integer serviceUid;
	private Integer version;
	private Service service;
	private AssignedPackage assignedPackage;
	private AssignedServiceStatus assignedServiceStatus;
	private Patient patient;
	private LabRequisitionOrder labRequisitionOrder;
	private Integer seqNbr;
	private Integer doctorId;
	private Date serviceDate;
	private Integer requestedUnits;
	private Integer serviceDateDimId;
	private Integer renderedUnits;
	private Integer canceledUnits;
	private String isBillable;
	private Integer billedUnits;
	private Integer lastBillNbr;
	private Double serviceCharge;
	private String referenceNumber;
	private String referenceType;
	private String remarks;
	private Date createDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;
	private Set renderedServices = new HashSet(0);
	private Set assignedServiceHistories = new HashSet(0);
	private Set records = new HashSet(0);

	// Constructors

	/** default constructor */
	public AssignedServices() {
	}

	/** minimal constructor */
	public AssignedServices(LabRequisitionOrder labRequisitionOrder,
			Integer requestedUnits, Integer renderedUnits,
			Integer canceledUnits, Integer billedUnits, Double serviceCharge,
			String referenceNumber, String referenceType) {
		this.labRequisitionOrder = labRequisitionOrder;
		this.requestedUnits = requestedUnits;
		this.renderedUnits = renderedUnits;
		this.canceledUnits = canceledUnits;
		this.billedUnits = billedUnits;
		this.serviceCharge = serviceCharge;
		this.referenceNumber = referenceNumber;
		this.referenceType = referenceType;
	}

	/** full constructor */
	public AssignedServices(Service service, AssignedPackage assignedPackage,
			AssignedServiceStatus assignedServiceStatus, Patient patient,
			LabRequisitionOrder labRequisitionOrder, Integer seqNbr,
			Integer doctorId, Date serviceDate, Integer requestedUnits,
			Integer serviceDateDimId, Integer renderedUnits,
			Integer canceledUnits, Integer billedUnits, Integer lastBillNbr,
			Double serviceCharge, String referenceNumber, String referenceType,
			String remarks, Date createDtm, String createdBy,
			Date lastModifiedDtm, String modifiedBy, Set renderedServices,
			Set assignedServiceHistories, Set records) {
		this.service = service;
		this.assignedPackage = assignedPackage;
		this.assignedServiceStatus = assignedServiceStatus;
		this.patient = patient;
		this.labRequisitionOrder = labRequisitionOrder;
		this.seqNbr = seqNbr;
		this.doctorId = doctorId;
		this.serviceDate = serviceDate;
		this.requestedUnits = requestedUnits;
		this.serviceDateDimId = serviceDateDimId;
		this.renderedUnits = renderedUnits;
		this.canceledUnits = canceledUnits;
		this.billedUnits = billedUnits;
		this.lastBillNbr = lastBillNbr;
		this.serviceCharge = serviceCharge;
		this.referenceNumber = referenceNumber;
		this.referenceType = referenceType;
		this.remarks = remarks;
		this.createDtm = createDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
		this.renderedServices = renderedServices;
		this.assignedServiceHistories = assignedServiceHistories;
		this.records = records;
	}

	// Property accessors

	public Integer getServiceUid() {
		return this.serviceUid;
	}

	public void setServiceUid(Integer serviceUid) {
		this.serviceUid = serviceUid;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public AssignedPackage getAssignedPackage() {
		return this.assignedPackage;
	}

	public void setAssignedPackage(AssignedPackage assignedPackage) {
		this.assignedPackage = assignedPackage;
	}

	public AssignedServiceStatus getAssignedServiceStatus() {
		return this.assignedServiceStatus;
	}

	public void setAssignedServiceStatus(
			AssignedServiceStatus assignedServiceStatus) {
		this.assignedServiceStatus = assignedServiceStatus;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LabRequisitionOrder getLabRequisitionOrder() {
		return this.labRequisitionOrder;
	}

	public void setLabRequisitionOrder(LabRequisitionOrder labRequisitionOrder) {
		this.labRequisitionOrder = labRequisitionOrder;
	}

	public Integer getSeqNbr() {
		return this.seqNbr;
	}

	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Date getServiceDate() {
		return this.serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public Integer getRequestedUnits() {
		return this.requestedUnits;
	}

	public void setRequestedUnits(Integer requestedUnits) {
		this.requestedUnits = requestedUnits;
	}

	public Integer getServiceDateDimId() {
		return this.serviceDateDimId;
	}

	public void setServiceDateDimId(Integer serviceDateDimId) {
		this.serviceDateDimId = serviceDateDimId;
	}

	public Integer getRenderedUnits() {
		return this.renderedUnits;
	}

	public void setRenderedUnits(Integer renderedUnits) {
		this.renderedUnits = renderedUnits;
	}

	public Integer getCanceledUnits() {
		return this.canceledUnits;
	}

	public void setCanceledUnits(Integer canceledUnits) {
		this.canceledUnits = canceledUnits;
	}

	public Integer getBilledUnits() {
		return this.billedUnits;
	}

	public void setBilledUnits(Integer billedUnits) {
		this.billedUnits = billedUnits;
	}

	public Integer getLastBillNbr() {
		return this.lastBillNbr;
	}

	public void setLastBillNbr(Integer lastBillNbr) {
		this.lastBillNbr = lastBillNbr;
	}

	public Double getServiceCharge() {
		return this.serviceCharge;
	}

	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getReferenceType() {
		return this.referenceType;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDtm() {
		return this.createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Set getRenderedServices() {
		return this.renderedServices;
	}

	public void setRenderedServices(Set renderedServices) {
		this.renderedServices = renderedServices;
	}

	public Set getAssignedServiceHistories() {
		return this.assignedServiceHistories;
	}

	public void setAssignedServiceHistories(Set assignedServiceHistories) {
		this.assignedServiceHistories = assignedServiceHistories;
	}

	public Set getRecords() {
		return this.records;
	}

	public void setRecords(Set records) {
		this.records = records;
	}

	public String getIsBillable() {
		return isBillable;
	}

	public void setIsBillable(String isBillable) {
		this.isBillable = isBillable;
	}

}