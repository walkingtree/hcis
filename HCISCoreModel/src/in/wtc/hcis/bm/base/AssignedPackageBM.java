/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class AssignedPackageBM implements Serializable {
	private Integer packageAsgmId;
	private CodeAndDescription assignedPackageStatus;
	private CodeAndDescription servicePackage;
	private Integer serviceOrderNumber;
	private Integer doctorId;
	private Integer patientId;
	private String referenceNumber;
	private String referenceType;
	private Integer requestedUnit;
	private Double effectiveCharge;
	private Date createDate;
	private String createdBy;
	private Date modificationDtm;
	private String modifiedBy;
	private Integer billNbr;
	
	public Integer getPackageAsgmId() {
		return packageAsgmId;
	}
	public void setPackageAsgmId(Integer packageAsgmId) {
		this.packageAsgmId = packageAsgmId;
	}
	public CodeAndDescription getAssignedPackageStatus() {
		return assignedPackageStatus;
	}
	public void setAssignedPackageStatus(CodeAndDescription assignedPackageStatus) {
		this.assignedPackageStatus = assignedPackageStatus;
	}
	public CodeAndDescription getServicePackage() {
		return servicePackage;
	}
	public void setServicePackage(CodeAndDescription servicePackage) {
		this.servicePackage = servicePackage;
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
	public Integer getRequestedUnit() {
		return requestedUnit;
	}
	public void setRequestedUnit(Integer requestedUnit) {
		this.requestedUnit = requestedUnit;
	}
	public Double getEffectiveCharge() {
		return effectiveCharge;
	}
	public void setEffectiveCharge(Double effectiveCharge) {
		this.effectiveCharge = effectiveCharge;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModificationDtm() {
		return modificationDtm;
	}
	public void setModificationDtm(Date modificationDtm) {
		this.modificationDtm = modificationDtm;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Integer getBillNbr() {
		return billNbr;
	}
	public void setBillNbr(Integer billNbr) {
		this.billNbr = billNbr;
	}
	public Integer getServiceOrderNumber() {
		return serviceOrderNumber;
	}
	public void setServiceOrderNumber(Integer serviceOrderNumber) {
		this.serviceOrderNumber = serviceOrderNumber;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	
}
