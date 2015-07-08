package com.wtc.hcis.da;

/**
 * RenderedService entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class RenderedService implements java.io.Serializable {

	// Fields

	private RenderedServiceId id;
	private Integer version;
	private AssignedServices assignedServices;
	private AssignedPackage assignedPackage;
	private String referenceNbr;
	private String referenceType;
	private Integer renderedQty;
	private Integer billNbr;
	private Double serviceCharge;
	private String renderedBy;

	// Constructors

	/** default constructor */
	public RenderedService() {
	}

	/** minimal constructor */
	public RenderedService(RenderedServiceId id,
			AssignedServices assignedServices, String referenceNbr,
			String referenceType, Integer renderedQty, Double serviceCharge) {
		this.id = id;
		this.assignedServices = assignedServices;
		this.referenceNbr = referenceNbr;
		this.referenceType = referenceType;
		this.renderedQty = renderedQty;
		this.serviceCharge = serviceCharge;
	}

	/** full constructor */
	public RenderedService(RenderedServiceId id,
			AssignedServices assignedServices, AssignedPackage assignedPackage,
			String referenceNbr, String referenceType, Integer renderedQty,
			Integer billNbr, Double serviceCharge, String renderedBy) {
		this.id = id;
		this.assignedServices = assignedServices;
		this.assignedPackage = assignedPackage;
		this.referenceNbr = referenceNbr;
		this.referenceType = referenceType;
		this.renderedQty = renderedQty;
		this.billNbr = billNbr;
		this.serviceCharge = serviceCharge;
		this.renderedBy = renderedBy;
	}

	// Property accessors

	public RenderedServiceId getId() {
		return this.id;
	}

	public void setId(RenderedServiceId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public AssignedServices getAssignedServices() {
		return this.assignedServices;
	}

	public void setAssignedServices(AssignedServices assignedServices) {
		this.assignedServices = assignedServices;
	}

	public AssignedPackage getAssignedPackage() {
		return this.assignedPackage;
	}

	public void setAssignedPackage(AssignedPackage assignedPackage) {
		this.assignedPackage = assignedPackage;
	}

	public String getReferenceNbr() {
		return this.referenceNbr;
	}

	public void setReferenceNbr(String referenceNbr) {
		this.referenceNbr = referenceNbr;
	}

	public String getReferenceType() {
		return this.referenceType;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	public Integer getRenderedQty() {
		return this.renderedQty;
	}

	public void setRenderedQty(Integer renderedQty) {
		this.renderedQty = renderedQty;
	}

	public Integer getBillNbr() {
		return this.billNbr;
	}

	public void setBillNbr(Integer billNbr) {
		this.billNbr = billNbr;
	}

	public Double getServiceCharge() {
		return this.serviceCharge;
	}

	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getRenderedBy() {
		return this.renderedBy;
	}

	public void setRenderedBy(String renderedBy) {
		this.renderedBy = renderedBy;
	}

}