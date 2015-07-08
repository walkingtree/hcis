/**
 * 
 */
package in.wtc.ui.model;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.util.Date;

/**
 * @author Sandeep Kumar
 *
 */
public class AppointmentSummaryModel {
	private int serialNo;
	private int appointmentNumber;
	private String patientName;
	private String patientFirstName;
	private String patientMiddleName;
	private String patientLastName;
	private int patientId;
	private Date appointmentDate;
	private String appointmentStartTime;
	private String appointmentEndTime;
	private CodeAndDescription appointmentStatus;
	private String amount;
	private CodeAndDescription speaciality;
	private CodeAndDescription bookingType;
	private CodeAndDescription primaryDoctor;
	private CodeAndDescription appointmentType;
	private CodeAndDescription referredBy;
	private CodeAndDescription referralType;
	private Integer rosterCode;
	private String remarks;
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getAppointmentStartTime() {
		return appointmentStartTime;
	}
	public void setAppointmentStartTime(String appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}
	public String getAppointmentEndTime() {
		return appointmentEndTime;
	}
	public void setAppointmentEndTime(String appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

	public CodeAndDescription getSpeaciality() {
		return speaciality;
	}
	public void setSpeaciality(CodeAndDescription speaciality) {
		this.speaciality = speaciality;
	}
	public CodeAndDescription getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(CodeAndDescription appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	public CodeAndDescription getBookingType() {
		return bookingType;
	}
	public void setBookingType(CodeAndDescription bookingType) {
		this.bookingType = bookingType;
	}
	public CodeAndDescription getPrimaryDoctor() {
		return primaryDoctor;
	}
	public void setPrimaryDoctor(CodeAndDescription primaryDoctor) {
		this.primaryDoctor = primaryDoctor;
	}
	public AppointmentSummaryModel() {
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	public String getPatientMiddleName() {
		return patientMiddleName;
	}
	public void setPatientMiddleName(String patientMiddleName) {
		this.patientMiddleName = patientMiddleName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getAppointmentNumber() {
		return appointmentNumber;
	}
	public void setAppointmentNumber(int appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}
	public Integer getRosterCode() {
		return rosterCode;
	}
	public void setRosterCode(Integer rosterCode) {
		this.rosterCode = rosterCode;
	}
	public CodeAndDescription getAppointmentType() {
		return appointmentType;
	}
	public void setAppointmentType(CodeAndDescription appointmentType) {
		this.appointmentType = appointmentType;
	}
	public CodeAndDescription getReferredBy() {
		return referredBy;
	}
	public void setReferredBy(CodeAndDescription referredBy) {
		this.referredBy = referredBy;
	}
	public CodeAndDescription getReferralType() {
		return referralType;
	}
	public void setReferralType(CodeAndDescription referralType) {
		this.referralType = referralType;
	}
}
