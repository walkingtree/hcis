package in.wtc.ui.model;

import java.util.Date;

/**
 * @author Sandeep Kumar
 *
 */
public class TreatmentSummary {
	private int serialNo;
	private int patientId;
	private String name;
	private String ioPatient;
	private String primaryDoctor;
	private String primaryDoctorId;
	private Date appointmentDate;
	private Date admissiondate;
	private Date dischargeDate;
	private String appointmentAgenda;
	private String appointmentRemarks;
	private String specialty;
	private String specialtyCode;
	private Integer appointmentNumber;
	
	public TreatmentSummary() {
	}
	
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIoPatient() {
		return ioPatient;
	}
	public void setIoPatient(String ioPatient) {
		this.ioPatient = ioPatient;
	}
	public String getPrimaryDoctor() {
		return primaryDoctor;
	}
	public void setPrimaryDoctor(String primaryDoctor) {
		this.primaryDoctor = primaryDoctor;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public Date getAdmissiondate() {
		return admissiondate;
	}
	public void setAdmissiondate(Date admissiondate) {
		this.admissiondate = admissiondate;
	}
	public Date getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public String getAppointmentAgenda() {
		return appointmentAgenda;
	}
	public void setAppointmentAgenda(String appointmentAgenda) {
		this.appointmentAgenda = appointmentAgenda;
	}
	public String getAppointmentRemarks() {
		return appointmentRemarks;
	}
	public void setAppointmentRemarks(String appointmentRemarks) {
		this.appointmentRemarks = appointmentRemarks;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getPrimaryDoctorId() {
		return primaryDoctorId;
	}

	public void setPrimaryDoctorId(String primaryDoctorId) {
		this.primaryDoctorId = primaryDoctorId;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getSpecialtyCode() {
		return specialtyCode;
	}

	public void setSpecialtyCode(String specialtyCode) {
		this.specialtyCode = specialtyCode;
	}

	public Integer getAppointmentNumber() {
		return appointmentNumber;
	}

	public void setAppointmentNumber(Integer appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}


}
