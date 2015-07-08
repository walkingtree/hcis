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
public class AppointmentBM implements Serializable 
{
	static final long serialVersionUID = 200904220122L;

	private Integer 			appointmentNumber;
	private Integer 			patientId;
	private String 				firstName;
	private String				middleName;
	private String				lastName;
	private Date 				appointmentDate;
	private Date 				bookingDate;
	private String 				appointmentStartTime;
	private String 				appointmentEndTime;
	private String 				appointmentRemarks;
	private String 				appointmentAgenda;
	private Date 				nextVisitDate;
	private Integer 			rosterCode;
	private CodeAndDescription 	speaciality;
	private CodeAndDescription 	primaryDoctor;
	private CodeAndDescription 	bookingType;
	private String 				phone;
	private String 				email;
	private CodeAndDescription 	appointmentStatus;
	private CodeAndDescription 	cancellationReason;
	private String 				modifiedBy;
	private Boolean             patientCameToVisit;
	private Boolean             patientVisitedConsultant;
	private Boolean             consultantHasSeenPatient;
	private CodeAndDescription 	referredBy;
	private CodeAndDescription  referralType;
	private CodeAndDescription  appointmentType;
	private Double 			    appointmentCharge;
	private Integer 			primaryAppointmentNumber;
	
	public CodeAndDescription getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(CodeAndDescription referredBy) {
		this.referredBy = referredBy;
	}

	/**
	 * @return the appointmentNumber
	 */
	public Integer getAppointmentNumber() {
		return appointmentNumber;
	}
	
	/**
	 * @param appointmentNumber the appointmentNumber to set
	 */
	public void setAppointmentNumber(Integer appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}
	
	/**
	 * @return the patientId
	 */
	public Integer getPatientId() {
		return patientId;
	}
	
	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the appointmentDate
	 */
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	/**
	 * @param appointmentDate the appointmentDate to set
	 */
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	/**
	 * @return the bookingDate
	 */
	public Date getBookingDate() {
		return bookingDate;
	}
	/**
	 * @param bookingDate the bookingDate to set
	 */
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	/**
	 * @return the appointmentStartTime
	 */
	public String getAppointmentStartTime() {
		return appointmentStartTime;
	}
	/**
	 * @param appointmentStartTime the appointmentStartTime to set
	 */
	public void setAppointmentStartTime(String appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}
	/**
	 * @return the appointmentEndTime
	 */
	public String getAppointmentEndTime() {
		return appointmentEndTime;
	}
	/**
	 * @param appointmentEndTime the appointmentEndTime to set
	 */
	public void setAppointmentEndTime(String appointmentEndTime) {
		this.appointmentEndTime = appointmentEndTime;
	}
	/**
	 * @return the appointmentRemarks
	 */
	public String getAppointmentRemarks() {
		return appointmentRemarks;
	}
	/**
	 * @param appointmentRemarks the appointmentRemarks to set
	 */
	public void setAppointmentRemarks(String appointmentRemarks) {
		this.appointmentRemarks = appointmentRemarks;
	}
	/**
	 * @return the appointmentAgenda
	 */
	public String getAppointmentAgenda() {
		return appointmentAgenda;
	}
	/**
	 * @param appointmentAgenda the appointmentAgenda to set
	 */
	public void setAppointmentAgenda(String appointmentAgenda) {
		this.appointmentAgenda = appointmentAgenda;
	}
	/**
	 * @return the nextVisitDate
	 */
	public Date getNextVisitDate() {
		return nextVisitDate;
	}
	/**
	 * @param nextVisitDate the nextVisitDate to set
	 */
	public void setNextVisitDate(Date nextVisitDate) {
		this.nextVisitDate = nextVisitDate;
	}
	/**
	 * @return the rosterCode
	 */
	public Integer getRosterCode() {
		return rosterCode;
	}
	/**
	 * @param rosterCode the rosterCode to set
	 */
	public void setRosterCode(Integer rosterCode) {
		this.rosterCode = rosterCode;
	}
	/**
	 * @return the primaryDoctor
	 */
	public CodeAndDescription getPrimaryDoctor() {
		return primaryDoctor;
	}
	/**
	 * @param primaryDoctor the primaryDoctor to set
	 */
	public void setPrimaryDoctor(CodeAndDescription primaryDoctor) {
		this.primaryDoctor = primaryDoctor;
	}
	/**
	 * @return the bookingType
	 */
	public CodeAndDescription getBookingType() {
		return bookingType;
	}
	/**
	 * @param bookingType the bookingType to set
	 */
	public void setBookingType(CodeAndDescription bookingType) {
		this.bookingType = bookingType;
	}
	/**
	 * @return the appointmentStatus
	 */
	public CodeAndDescription getAppointmentStatus() {
		return appointmentStatus;
	}
	/**
	 * @param appointmentStatus the appointmentStatus to set
	 */
	public void setAppointmentStatus(CodeAndDescription appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	/**
	 * @return the cancellationReason
	 */
	public CodeAndDescription getCancellationReason() {
		return cancellationReason;
	}
	/**
	 * @param cancellationReason the cancellationReason to set
	 */
	public void setCancellationReason(CodeAndDescription cancellationReason) {
		this.cancellationReason = cancellationReason;
	}
	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @return the patientCameToVisit
	 */
	public Boolean getPatientCameToVisit() {
		return patientCameToVisit;
	}

	/**
	 * @param patientCameToVisit the patientCameToVisit to set
	 */
	public void setPatientCameToVisit(Boolean patientCameToVisit) {
		this.patientCameToVisit = patientCameToVisit;
	}

	/**
	 * @return the patientVisitedConsultant
	 */
	public Boolean getPatientVisitedConsultant() {
		return patientVisitedConsultant;
	}

	/**
	 * @param patientVisitedConsultant the patientVisitedConsultant to set
	 */
	public void setPatientVisitedConsultant(Boolean patientVisitedConsultant) {
		this.patientVisitedConsultant = patientVisitedConsultant;
	}

	/**
	 * @return the consultantHasSeenPatient
	 */
	public Boolean getConsultantHasSeenPatient() {
		return consultantHasSeenPatient;
	}

	/**
	 * @param consultantHasSeenPatient the consultantHasSeenPatient to set
	 */
	public void setConsultantHasSeenPatient(Boolean consultantHasSeenPatient) {
		this.consultantHasSeenPatient = consultantHasSeenPatient;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public CodeAndDescription getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(CodeAndDescription appointmentType) {
		this.appointmentType = appointmentType;
	}

	public CodeAndDescription getReferralType() {
		return referralType;
	}

	public void setReferralType(CodeAndDescription referralType) {
		this.referralType = referralType;
	}

	public CodeAndDescription getSpeaciality() {
		return speaciality;
	}

	public void setSpeaciality(CodeAndDescription speaciality) {
		this.speaciality = speaciality;
	}

	public Double getAppointmentCharge() {
		return appointmentCharge;
	}

	public void setAppointmentCharge(Double appointmentCharge) {
		this.appointmentCharge = appointmentCharge;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPrimaryAppointmentNumber() {
		return primaryAppointmentNumber;
	}

	public void setPrimaryAppointmentNumber(Integer primaryAppointmentNumber) {
		this.primaryAppointmentNumber = primaryAppointmentNumber;
	}
	
	
	 

}
