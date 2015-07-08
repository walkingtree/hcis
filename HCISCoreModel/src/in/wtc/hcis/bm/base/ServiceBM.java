package in.wtc.hcis.bm.base;

import java.util.Date;

@SuppressWarnings("serial")
public class ServiceBM implements java.io.Serializable {

	private String serviceCode;
	private CodeAndDescription serviceStatus;
	private CodeAndDescription department;
	private String serviceName;
	private String serviceDesc;
	private String serviceProcedure;
	private Double serviceCharge;
	private CodeAndDescription serviceGroup;
	private Date createDtm;
	private String personId;
	private Date lastModifiedDtm;
	private Date effectiveFromDate;
	private Date effectiveToDate;

	private Double depositAmount;
	
	private CodeAndDescription serviceType;

	private String departmentName;//For grouping data on UI grid
	
	public CodeAndDescription getServiceType() {
		return serviceType;
	}

	public void setServiceType(CodeAndDescription serviceType) {
		this.serviceType = serviceType;
	}

	public ServiceBM() {
		super();
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public CodeAndDescription getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(CodeAndDescription serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public CodeAndDescription getDepartment() {
		return department;
	}

	public void setDepartment(CodeAndDescription department) {
		this.department = department;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getServiceProcedure() {
		return serviceProcedure;
	}

	public void setServiceProcedure(String serviceProcedure) {
		this.serviceProcedure = serviceProcedure;
	}

	public Double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public CodeAndDescription getServiceGroup() {
		return serviceGroup;
	}

	public void setServiceGroup(CodeAndDescription serviceGroup) {
		this.serviceGroup = serviceGroup;
	}

	public Date getCreateDtm() {
		return createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}

	public Date getLastModifiedDtm() {
		return lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public Date getEffectiveFromDate() {
		return effectiveFromDate;
	}

	public void setEffectiveFromDate(Date effectiveFromDate) {
		this.effectiveFromDate = effectiveFromDate;
	}

	public Date getEffectiveToDate() {
		return effectiveToDate;
	}

	public void setEffectiveToDate(Date effectiveToDate) {
		this.effectiveToDate = effectiveToDate;
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}