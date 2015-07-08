package in.wtc.lims.bm;

import java.util.Date;


public class RequisitionOrderBM {
	
	private Integer requisitionOrderNbr;
	private Integer patientId;
	private String patientName;
	private Integer doctorId;
	private String doctorName;
	private CodeAndDescription status;
	private Double totalCharges;
	private Date createdDate;
	private String referenceType;
	private String referenceNumber;
	
	public Integer getRequisitionOrderNbr() {
		return requisitionOrderNbr;
	}
	public void setRequisitionOrderNbr(Integer requisitionOrderNbr) {
		this.requisitionOrderNbr = requisitionOrderNbr;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public CodeAndDescription getStatus() {
		return status;
	}
	public void setStatus(CodeAndDescription status) {
		this.status = status;
	}
	public Double getTotalCharges() {
		return totalCharges;
	}
	public void setTotalCharges(Double totalCharges) {
		this.totalCharges = totalCharges;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getReferenceType() {
		return referenceType;
	}
	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

}
