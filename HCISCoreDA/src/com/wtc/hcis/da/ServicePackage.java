package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ServicePackage entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ServicePackage implements java.io.Serializable {

	// Fields

	private String packageId;
	private Integer version;
	private ServicePackageStatus servicePackageStatus;
	private String name;
	private String description;
	private Date creationDate;
	private String createdBy;
	private Double packageCharge;
	private String chargeOverrideLevel;
	private Double discountAmtPct;
	private String discountType;
	private Double effectiveCharge;
	private Date effectiveFromDt;
	private Date effectiveToDt;
	private Date suspendedFromDt;
	private Date suspendedToDt;
	private String suspendLevelFlag;
	private String modifiedBy;
	private Date modificationDtm;
	private Set servicePackageHistories = new HashSet(0);
	private Set packageHasServices = new HashSet(0);
	private Set assignedPackages = new HashSet(0);

	// Constructors

	/** default constructor */
	public ServicePackage() {
	}

	/** minimal constructor */
	public ServicePackage(ServicePackageStatus servicePackageStatus,
			String name, String description, Date creationDate,
			Double packageCharge, String chargeOverrideLevel,
			Double discountAmtPct, String discountType, Double effectiveCharge,
			Date effectiveFromDt) {
		this.servicePackageStatus = servicePackageStatus;
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.packageCharge = packageCharge;
		this.chargeOverrideLevel = chargeOverrideLevel;
		this.discountAmtPct = discountAmtPct;
		this.discountType = discountType;
		this.effectiveCharge = effectiveCharge;
		this.effectiveFromDt = effectiveFromDt;
	}

	/** full constructor */
	public ServicePackage(ServicePackageStatus servicePackageStatus,
			String name, String description, Date creationDate,
			String createdBy, Double packageCharge, String chargeOverrideLevel,
			Double discountAmtPct, String discountType, Double effectiveCharge,
			Date effectiveFromDt, Date effectiveToDt, Date suspendedFromDt,
			Date suspendedToDt, String suspendLevelFlag, String modifiedBy,
			Date modificationDtm, Set servicePackageHistories,
			Set packageHasServices, Set assignedPackages) {
		this.servicePackageStatus = servicePackageStatus;
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.packageCharge = packageCharge;
		this.chargeOverrideLevel = chargeOverrideLevel;
		this.discountAmtPct = discountAmtPct;
		this.discountType = discountType;
		this.effectiveCharge = effectiveCharge;
		this.effectiveFromDt = effectiveFromDt;
		this.effectiveToDt = effectiveToDt;
		this.suspendedFromDt = suspendedFromDt;
		this.suspendedToDt = suspendedToDt;
		this.suspendLevelFlag = suspendLevelFlag;
		this.modifiedBy = modifiedBy;
		this.modificationDtm = modificationDtm;
		this.servicePackageHistories = servicePackageHistories;
		this.packageHasServices = packageHasServices;
		this.assignedPackages = assignedPackages;
	}

	// Property accessors

	public String getPackageId() {
		return this.packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ServicePackageStatus getServicePackageStatus() {
		return this.servicePackageStatus;
	}

	public void setServicePackageStatus(
			ServicePackageStatus servicePackageStatus) {
		this.servicePackageStatus = servicePackageStatus;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Double getPackageCharge() {
		return this.packageCharge;
	}

	public void setPackageCharge(Double packageCharge) {
		this.packageCharge = packageCharge;
	}

	public String getChargeOverrideLevel() {
		return this.chargeOverrideLevel;
	}

	public void setChargeOverrideLevel(String chargeOverrideLevel) {
		this.chargeOverrideLevel = chargeOverrideLevel;
	}

	public Double getDiscountAmtPct() {
		return this.discountAmtPct;
	}

	public void setDiscountAmtPct(Double discountAmtPct) {
		this.discountAmtPct = discountAmtPct;
	}

	public String getDiscountType() {
		return this.discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Double getEffectiveCharge() {
		return this.effectiveCharge;
	}

	public void setEffectiveCharge(Double effectiveCharge) {
		this.effectiveCharge = effectiveCharge;
	}

	public Date getEffectiveFromDt() {
		return this.effectiveFromDt;
	}

	public void setEffectiveFromDt(Date effectiveFromDt) {
		this.effectiveFromDt = effectiveFromDt;
	}

	public Date getEffectiveToDt() {
		return this.effectiveToDt;
	}

	public void setEffectiveToDt(Date effectiveToDt) {
		this.effectiveToDt = effectiveToDt;
	}

	public Date getSuspendedFromDt() {
		return this.suspendedFromDt;
	}

	public void setSuspendedFromDt(Date suspendedFromDt) {
		this.suspendedFromDt = suspendedFromDt;
	}

	public Date getSuspendedToDt() {
		return this.suspendedToDt;
	}

	public void setSuspendedToDt(Date suspendedToDt) {
		this.suspendedToDt = suspendedToDt;
	}

	public String getSuspendLevelFlag() {
		return this.suspendLevelFlag;
	}

	public void setSuspendLevelFlag(String suspendLevelFlag) {
		this.suspendLevelFlag = suspendLevelFlag;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModificationDtm() {
		return this.modificationDtm;
	}

	public void setModificationDtm(Date modificationDtm) {
		this.modificationDtm = modificationDtm;
	}

	public Set getServicePackageHistories() {
		return this.servicePackageHistories;
	}

	public void setServicePackageHistories(Set servicePackageHistories) {
		this.servicePackageHistories = servicePackageHistories;
	}

	public Set getPackageHasServices() {
		return this.packageHasServices;
	}

	public void setPackageHasServices(Set packageHasServices) {
		this.packageHasServices = packageHasServices;
	}

	public Set getAssignedPackages() {
		return this.assignedPackages;
	}

	public void setAssignedPackages(Set assignedPackages) {
		this.assignedPackages = assignedPackages;
	}

}