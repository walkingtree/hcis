package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PatientVaccinationScheduleBM implements Serializable{

	private Integer sequenceNumber;			//Not null -> internally generated
	private Integer patientId;		//Not null
	private CodeAndDescription doctor; 		//Not null
	private CodeAndDescription scheduleName;//Not null
	private Date startDate;					//Not null
	private String userId;				//Not null
	private CodeAndDescription statusCode;	//Not null
	private List<PatientVaccinationScheduleDetailsBM> patientVaccinationScheduleDetailsBMList; //Not null
	
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}	
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public CodeAndDescription getDoctor() {
		return doctor;
	}
	public void setDoctor(CodeAndDescription doctor) {
		this.doctor = doctor;
	}
	public CodeAndDescription getScheduleName() {
		return scheduleName;
	}
	public void setScheduleName(CodeAndDescription scheduleName) {
		this.scheduleName = scheduleName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public CodeAndDescription getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(CodeAndDescription statusCode) {
		this.statusCode = statusCode;
	}
	public PatientVaccinationScheduleBM() {
	}
	public List<PatientVaccinationScheduleDetailsBM> getPatientVaccinationScheduleDetailsBMList() {
		return patientVaccinationScheduleDetailsBMList;
	}
	public void setPatientVaccinationScheduleDetailsBMList(
			List<PatientVaccinationScheduleDetailsBM> patientVaccinationScheduleDetailsBMList) {
		this.patientVaccinationScheduleDetailsBMList = patientVaccinationScheduleDetailsBMList;
	} 
	
}
