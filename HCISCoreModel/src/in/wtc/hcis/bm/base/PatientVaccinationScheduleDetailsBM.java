package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;

public class PatientVaccinationScheduleDetailsBM implements Serializable{

	private Integer sequenceNumber; 				//not null - internally generated
	private Integer subSequenceNumber;				//not null - internally generated
	private CodeAndDescription scheduleName;					//not null
	private Integer patientId;						//not null
	private Integer periodInDays;					//not null
	private String age;								//not null
	private CodeAndDescription vaccinationCd;   	//not null
	private CodeAndDescription vaccinationTypeCd;
	private String dosage;							//not null
	private String doctorComments;					
	private String userId;							//not null
	private Date dueDate;					
	private Date givenDate;							
	private CodeAndDescription doctor;				//not null

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Integer getSubSequenceNumber() {
		return subSequenceNumber;
	}

	public void setSubSequenceNumber(Integer subSequenceNumber) {
		this.subSequenceNumber = subSequenceNumber;
	}

	public CodeAndDescription getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(CodeAndDescription scheduleName) {
		this.scheduleName = scheduleName;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getPeriodInDays() {
		return periodInDays;
	}

	public void setPeriodInDays(Integer periodInDays) {
		this.periodInDays = periodInDays;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public CodeAndDescription getVaccinationCd() {
		return vaccinationCd;
	}

	public void setVaccinationCd(CodeAndDescription vaccinationCd) {
		this.vaccinationCd = vaccinationCd;
	}

	public CodeAndDescription getVaccinationTypeCd() {
		return vaccinationTypeCd;
	}

	public void setVaccinationTypeCd(CodeAndDescription vaccinationTypeCd) {
		this.vaccinationTypeCd = vaccinationTypeCd;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getDoctorComments() {
		return doctorComments;
	}

	public void setDoctorComments(String doctorComments) {
		this.doctorComments = doctorComments;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getGivenDate() {
		return givenDate;
	}

	public void setGivenDate(Date givenDate) {
		this.givenDate = givenDate;
	}

	public CodeAndDescription getDoctor() {
		return doctor;
	}

	public void setDoctor(CodeAndDescription doctor) {
		this.doctor = doctor;
	}

	public PatientVaccinationScheduleDetailsBM() {
	}
}
