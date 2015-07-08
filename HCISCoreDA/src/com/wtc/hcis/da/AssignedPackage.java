package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AssignedPackage entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AssignedPackage implements java.io.Serializable {

	// Fields

	private Integer packageAsgmId;
	private Integer version;
	private AssignedPackageStatus assignedPackageStatus;
	private Patient patient;
	private ServicePackage servicePackage;
	private Doctor doctor;
	private Integer serviceOrderNumber;
	private String referenceNumber;
	private String referenceType;
	private Integer requestedUnit;
	private Double effectiveCharge;
	private Date createDate;
	private String createdBy;
	private Date modificationDtm;
	private String modifiedBy;
	private Integer billNbr;
	private Set renderedServices = new HashSet(0);
	private Set assignedServiceses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AssignedPackage() {
	}

	/** minimal constructor */
	public AssignedPackage(AssignedPackageStatus assignedPackageStatus,
			ServicePackage servicePackage, Integer serviceOrderNumber,
			String referenceNumber, String referenceType,
			Integer requestedUnit, Double effectiveCharge, Date createDate) {
		this.assignedPackageStatus = assignedPackageStatus;
		this.servicePackage = servicePackage;
		this.serviceOrderNumber = serviceOrderNumber;
		this.referenceNumber = referenceNumber;
		this.referenceType = referenceType;
		this.requestedUnit = requestedUnit;
		this.effectiveCharge = effectiveCharge;
		this.createDate = createDate;
	}

	/** full constructor */
	public AssignedPackage(AssignedPackageStatus assignedPackageStatus,
			Patient patient, ServicePackage servicePackage, Doctor doctor,
			Integer serviceOrderNumber, String referenceNumber,
			String referenceType, Integer requestedUnit,
			Double effectiveCharge, Date createDate, String createdBy,
			Date modificationDtm, String modifiedBy, Integer billNbr,
			Set renderedServices, Set assignedServiceses) {
		this.assignedPackageStatus = assignedPackageStatus;
		this.patient = patient;
		this.servicePackage = servicePackage;
		this.doctor = doctor;
		this.serviceOrderNumber = serviceOrderNumber;
		this.referenceNumber = referenceNumber;
		this.referenceType = referenceType;
		this.requestedUnit = requestedUnit;
		this.effectiveCharge = effectiveCharge;
		this.createDate = createDate;
		this.createdBy = createdBy;
		this.modificationDtm = modificationDtm;
		this.modifiedBy = modifiedBy;
		this.billNbr = billNbr;
		this.renderedServices = renderedServices;
		this.assignedServiceses = assignedServiceses;
	}

	// Property accessors

	public Integer getPackageAsgmId() {
		return this.packageAsgmId;
	}

	public void setPackageAsgmId(Integer packageAsgmId) {
		this.packageAsgmId = packageAsgmId;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public AssignedPackageStatus getAssignedPackageStatus() {
		return this.assignedPackageStatus;
	}

	public void setAssignedPackageStatus(
			AssignedPackageStatus assignedPackageStatus) {
		this.assignedPackageStatus = assignedPackageStatus;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ServicePackage getServicePackage() {
		return this.servicePackage;
	}

	public void setServicePackage(ServicePackage servicePackage) {
		this.servicePackage = servicePackage;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Integer getServiceOrderNumber() {
		return this.serviceOrderNumber;
	}

	public void setServiceOrderNumber(Integer serviceOrderNumber) {
		this.serviceOrderNumber = serviceOrderNumber;
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

	public Integer getRequestedUnit() {
		return this.requestedUnit;
	}

	public void setRequestedUnit(Integer requestedUnit) {
		this.requestedUnit = requestedUnit;
	}

	public Double getEffectiveCharge() {
		return this.effectiveCharge;
	}

	public void setEffectiveCharge(Double effectiveCharge) {
		this.effectiveCharge = effectiveCharge;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModificationDtm() {
		return this.modificationDtm;
	}

	public void setModificationDtm(Date modificationDtm) {
		this.modificationDtm = modificationDtm;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Integer getBillNbr() {
		return this.billNbr;
	}

	public void setBillNbr(Integer billNbr) {
		this.billNbr = billNbr;
	}

	public Set getRenderedServices() {
		return this.renderedServices;
	}

	public void setRenderedServices(Set renderedServices) {
		this.renderedServices = renderedServices;
	}

	public Set getAssignedServiceses() {
		return this.assignedServiceses;
	}

	public void setAssignedServiceses(Set assignedServiceses) {
		this.assignedServiceses = assignedServiceses;
	}

}