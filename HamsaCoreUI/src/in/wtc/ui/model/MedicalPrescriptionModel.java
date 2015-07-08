/**
 * 
 */
package in.wtc.ui.model;

import java.util.Date;

/**
 * @author Sandeep Kumar
 *
 */
public class MedicalPrescriptionModel {
	public String patientId ;
	public String patientName ;
	public String doctorName ;
	public String patientType ;
	public String consultationDate ;
	public String departmentName ;
	public String doctorAddress ;
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getPatientType() {
		return patientType;
	}
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDoctorAddress() {
		return doctorAddress;
	}
	public void setDoctorAddress(String doctorAddress) {
		this.doctorAddress = doctorAddress;
	}
	public MedicalPrescriptionModel(String patientId, String patientName,
			String doctorName, String patientType, String consultationDate,
			String departmentName, String doctorAddress) {
		this.patientId = patientId;
		this.patientName = patientName;
		this.doctorName = doctorName;
		this.patientType = patientType;
		this.consultationDate = consultationDate;
		this.departmentName = departmentName;
		this.doctorAddress = doctorAddress;
	}
	
	public MedicalPrescriptionModel() {
	}
	public String getConsultationDate() {
		return consultationDate;
	}
	public void setConsultationDate(String consultationDate) {
		this.consultationDate = consultationDate;
	}
	
}
