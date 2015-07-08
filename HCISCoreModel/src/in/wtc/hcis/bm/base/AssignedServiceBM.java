/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Rohit
 *
 */
@SuppressWarnings("serial")
public class AssignedServiceBM implements Cloneable, Serializable 
{
	private Integer serviceUid;
	private CodeAndDescription service;
	private CodeAndDescription servicePackage; //set if service is assigned through package
	private CodeAndDescription assignedServiceStatus;
	private Integer patientId;
	private Integer serviceOrderNumber;
	private Integer packageAsgmId;
	private Integer doctorId;
	private Date serviceDate;
	private Integer requestedUnits;
	private Integer renderedUnits;
	private Integer canceledUnits;
	private Integer billedUnits;
	private Double serviceCharge;
	private String referenceNumber;
	private String referenceType;
	private Integer lastBillNumber;
	private String remarks;
	private Date createDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;
	private String isBillable;
	private CodeAndDescription serviceType; 
	
	private String patientName; 
	private String doctorName; 
	
	
	//This field is very specific for UI need; used while showing treatment history
	//Set only if service is assigned through appointment.
	
	private Date appointmentDate;
	
	public Integer getServiceUid() {
		return serviceUid;
	}
	public void setServiceUid(Integer serviceUid) {
		this.serviceUid = serviceUid;
	}
	public CodeAndDescription getService() {
		return service;
	}
	public void setService(CodeAndDescription service) {
		this.service = service;
	}
	public CodeAndDescription getAssignedServiceStatus() {
		return assignedServiceStatus;
	}
	public void setAssignedServiceStatus(CodeAndDescription assignedServiceStatus) {
		this.assignedServiceStatus = assignedServiceStatus;
	}
	
	public Integer getServiceOrderNumber() {
		return serviceOrderNumber;
	}
	public void setServiceOrderNumber(Integer serviceOrderNumber) {
		this.serviceOrderNumber = serviceOrderNumber;
	}
	public Integer getPackageAsgmId() {
		return packageAsgmId;
	}
	public void setPackageAsgmId(Integer packageAsgmId) {
		this.packageAsgmId = packageAsgmId;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public Date getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}
	public Integer getRequestedUnits() {
		return requestedUnits;
	}
	public void setRequestedUnits(Integer requestedUnits) {
		this.requestedUnits = requestedUnits;
	}
	public Integer getRenderedUnits() {
		return renderedUnits;
	}
	public void setRenderedUnits(Integer renderedUnits) {
		this.renderedUnits = renderedUnits;
	}
	public Integer getCanceledUnits() {
		return canceledUnits;
	}
	public void setCanceledUnits(Integer canceledUnits) {
		this.canceledUnits = canceledUnits;
	}
	public Integer getBilledUnits() {
		return billedUnits;
	}
	public void setBilledUnits(Integer billedUnits) {
		this.billedUnits = billedUnits;
	}
	public Double getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getCreateDtm() {
		return createDtm;
	}
	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getLastModifiedDtm() {
		return lastModifiedDtm;
	}
	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getLastBillNumber() {
		return lastBillNumber;
	}
	public void setLastBillNumber(Integer lastBillNumber) {
		this.lastBillNumber = lastBillNumber;
	}
	public CodeAndDescription getServicePackage() {
		return servicePackage;
	}
	public void setServicePackage(CodeAndDescription servicePackage) {
		this.servicePackage = servicePackage;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getIsBillable() {
		return isBillable;
	}
	public void setIsBillable(String isBillable) {
		this.isBillable = isBillable;
	}
	public CodeAndDescription getServiceType() {
		return serviceType;
	}
	public void setServiceType(CodeAndDescription serviceType) {
		this.serviceType = serviceType;
	}
	public String getPatientName() {
		return patientName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
}
