/**
 * 
 */
package in.wtc.hcis.ot.bm;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.util.Date;


/**
 * @author Bhavesh
 *
 */
public class OTBookingBM {

	
	private Integer serviceUId;//UI must pass this
	
	private Long otBookingNbr;
	private CodeAndDescription surgery;//Service
	private CodeAndDescription operationTheater;
	private Integer doctorId;
	private String doctorName;
	private Integer patientId;
	private String patientName;
	private CodeAndDescription bookingStatus;
	private Date   bookingFromDtm;
	private Date   bookingToDtm;
	private String userId;
	private String referenceType;
	private String referenceNumber;
	
	
	
	public Integer getServiceUId() {
		return serviceUId;
	}
	public void setServiceUId(Integer serviceUId) {
		this.serviceUId = serviceUId;
	}
	public Long getOtBookingNbr() {
		return otBookingNbr;
	}
	public void setOtBookingNbr(Long otBookingNbr) {
		this.otBookingNbr = otBookingNbr;
	}
	public CodeAndDescription getSurgery() {
		return surgery;
	}
	public void setSurgery(CodeAndDescription surgery) {
		this.surgery = surgery;
	}
	public CodeAndDescription getOperationTheater() {
		return operationTheater;
	}
	public void setOperationTheater(CodeAndDescription operationTheater) {
		this.operationTheater = operationTheater;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
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
	public CodeAndDescription getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(CodeAndDescription bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public Date getBookingFromDtm() {
		return bookingFromDtm;
	}
	public void setBookingFromDtm(Date bookingFromDtm) {
		this.bookingFromDtm = bookingFromDtm;
	}
	public Date getBookingToDtm() {
		return bookingToDtm;
	}
	public void setBookingToDtm(Date bookingToDtm) {
		this.bookingToDtm = bookingToDtm;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
