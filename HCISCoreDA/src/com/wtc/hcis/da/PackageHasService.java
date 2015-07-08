package com.wtc.hcis.da;

/**
 * PackageHasService entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PackageHasService implements java.io.Serializable {

	// Fields

	private PackageHasServiceId id;
	private Integer version;
	private Service service;
	private ServicePackage servicePackage;
	private Integer numberOfUnits;
	private Double serviceCharge;
	private String discountType;
	private Double discountAmtPct;
	private Double effectiveCharge;

	// Constructors

	/** default constructor */
	public PackageHasService() {
	}

	/** full constructor */
	public PackageHasService(PackageHasServiceId id, Service service,
			ServicePackage servicePackage, Integer numberOfUnits,
			Double serviceCharge, String discountType, Double discountAmtPct,
			Double effectiveCharge) {
		this.id = id;
		this.service = service;
		this.servicePackage = servicePackage;
		this.numberOfUnits = numberOfUnits;
		this.serviceCharge = serviceCharge;
		this.discountType = discountType;
		this.discountAmtPct = discountAmtPct;
		this.effectiveCharge = effectiveCharge;
	}

	// Property accessors

	public PackageHasServiceId getId() {
		return this.id;
	}

	public void setId(PackageHasServiceId id) {
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

	public ServicePackage getServicePackage() {
		return this.servicePackage;
	}

	public void setServicePackage(ServicePackage servicePackage) {
		this.servicePackage = servicePackage;
	}

	public Integer getNumberOfUnits() {
		return this.numberOfUnits;
	}

	public void setNumberOfUnits(Integer numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}

	public Double getServiceCharge() {
		return this.serviceCharge;
	}

	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getDiscountType() {
		return this.discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Double getDiscountAmtPct() {
		return this.discountAmtPct;
	}

	public void setDiscountAmtPct(Double discountAmtPct) {
		this.discountAmtPct = discountAmtPct;
	}

	public Double getEffectiveCharge() {
		return this.effectiveCharge;
	}

	public void setEffectiveCharge(Double effectiveCharge) {
		this.effectiveCharge = effectiveCharge;
	}

}