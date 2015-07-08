package com.wtc.hcis.da;

import java.util.Date;

/**
 * ServicePriceDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ServicePriceDetail implements java.io.Serializable {

	// Fields

	private ServicePriceDetailId id;
	private Integer version;
	private Service service;
	private Double serviceCharge;
	private Date createdDtm;
	private Date effectiveTo;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;
	private String processed;

	// Constructors

	/** default constructor */
	public ServicePriceDetail() {
	}

	/** minimal constructor */
	public ServicePriceDetail(ServicePriceDetailId id, Service service,
			Double serviceCharge, Date createdDtm, String createdBy) {
		this.id = id;
		this.service = service;
		this.serviceCharge = serviceCharge;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
	}

	/** full constructor */
	public ServicePriceDetail(ServicePriceDetailId id, Service service,
			Double serviceCharge, Date createdDtm, Date effectiveTo,
			String createdBy, Date lastModifiedDtm, String modifiedBy) {
		this.id = id;
		this.service = service;
		this.serviceCharge = serviceCharge;
		this.createdDtm = createdDtm;
		this.effectiveTo = effectiveTo;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
	}

	// Property accessors

	public ServicePriceDetailId getId() {
		return this.id;
	}

	public void setId(ServicePriceDetailId id) {
		this.id = id;
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

	public Double getServiceCharge() {
		return this.serviceCharge;
	}

	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public Date getCreatedDtm() {
		return this.createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	public Date getEffectiveTo() {
		return this.effectiveTo;
	}

	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
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

	public String getProcessed() {
		return processed;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}

}