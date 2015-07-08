package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Service entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Service implements java.io.Serializable {

	// Fields

	private String serviceCode;
	private Integer version;
	private ServiceStatus serviceStatus;
	private Department department;
	private ServiceGroup serviceGroup;
	private String serviceName;
	private String serviceDesc;
	private String serviceProcedure;
	private Double serviceCharge;
	private String serviceTypeCd;
	private Date createDtm;
	private String createdBy;
	private Date effectiveFromDt;
	private Date effectiveToDt;
	private Date suspendedFromDt;
	private Date suspendedToDt;
	private Double depositAmt;
	private Date lastModifiedDtm;
	private String modifiedBy;
	private Set packageHasServices = new HashSet(0);
	private Set serviceHistories = new HashSet(0);
	private Set labTests = new HashSet(0);
	private Set assignedServiceses = new HashSet(0);
	private Set servicePriceDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public Service() {
	}

	/** minimal constructor */
	public Service(ServiceStatus serviceStatus, Department department,
			String serviceName, Double serviceCharge, String serviceTypeCd,
			Date effectiveFromDt, Double depositAmt) {
		this.serviceStatus = serviceStatus;
		this.department = department;
		this.serviceName = serviceName;
		this.serviceCharge = serviceCharge;
		this.serviceTypeCd = serviceTypeCd;
		this.effectiveFromDt = effectiveFromDt;
		this.depositAmt = depositAmt;
	}

	/** full constructor */
	public Service(ServiceStatus serviceStatus, Department department,
			ServiceGroup serviceGroup, String serviceName, String serviceDesc,
			String serviceProcedure, Double serviceCharge,
			String serviceTypeCd, Date createDtm, String createdBy,
			Date effectiveFromDt, Date effectiveToDt, Date suspendedFromDt,
			Date suspendedToDt, Double depositAmt, Date lastModifiedDtm,
			String modifiedBy, Set packageHasServices, Set serviceHistories,
			Set labTests, Set assignedServiceses, Set servicePriceDetails) {
		this.serviceStatus = serviceStatus;
		this.department = department;
		this.serviceGroup = serviceGroup;
		this.serviceName = serviceName;
		this.serviceDesc = serviceDesc;
		this.serviceProcedure = serviceProcedure;
		this.serviceCharge = serviceCharge;
		this.serviceTypeCd = serviceTypeCd;
		this.createDtm = createDtm;
		this.createdBy = createdBy;
		this.effectiveFromDt = effectiveFromDt;
		this.effectiveToDt = effectiveToDt;
		this.suspendedFromDt = suspendedFromDt;
		this.suspendedToDt = suspendedToDt;
		this.depositAmt = depositAmt;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
		this.packageHasServices = packageHasServices;
		this.serviceHistories = serviceHistories;
		this.labTests = labTests;
		this.assignedServiceses = assignedServiceses;
		this.servicePriceDetails = servicePriceDetails;
	}

	// Property accessors

	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ServiceStatus getServiceStatus() {
		return this.serviceStatus;
	}

	public void setServiceStatus(ServiceStatus serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public ServiceGroup getServiceGroup() {
		return this.serviceGroup;
	}

	public void setServiceGroup(ServiceGroup serviceGroup) {
		this.serviceGroup = serviceGroup;
	}

	public String getServiceName() {
		return this.serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDesc() {
		return this.serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getServiceProcedure() {
		return this.serviceProcedure;
	}

	public void setServiceProcedure(String serviceProcedure) {
		this.serviceProcedure = serviceProcedure;
	}

	public Double getServiceCharge() {
		return this.serviceCharge;
	}

	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getServiceTypeCd() {
		return this.serviceTypeCd;
	}

	public void setServiceTypeCd(String serviceTypeCd) {
		this.serviceTypeCd = serviceTypeCd;
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

	public Double getDepositAmt() {
		return this.depositAmt;
	}

	public void setDepositAmt(Double depositAmt) {
		this.depositAmt = depositAmt;
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

	public Set getPackageHasServices() {
		return this.packageHasServices;
	}

	public void setPackageHasServices(Set packageHasServices) {
		this.packageHasServices = packageHasServices;
	}

	public Set getServiceHistories() {
		return this.serviceHistories;
	}

	public void setServiceHistories(Set serviceHistories) {
		this.serviceHistories = serviceHistories;
	}

	public Set getLabTests() {
		return this.labTests;
	}

	public void setLabTests(Set labTests) {
		this.labTests = labTests;
	}

	public Set getAssignedServiceses() {
		return this.assignedServiceses;
	}

	public void setAssignedServiceses(Set assignedServiceses) {
		this.assignedServiceses = assignedServiceses;
	}

	public Set getServicePriceDetails() {
		return this.servicePriceDetails;
	}

	public void setServicePriceDetails(Set servicePriceDetails) {
		this.servicePriceDetails = servicePriceDetails;
	}

}