package in.wtc.hcis.ot.bm;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.util.Date;
import java.util.List;


/**
 * 
 * @author Bhavesh
 *
 *This model will act as OT-Register. Contains complete information about surgery of patient
 */
public class PatientSurgeryBM {

	private Long patientSurgeryId;  //Assigned service UID
	private CodeAndDescription surgery;//Service
	private CodeAndDescription operationTheater;

	private Integer patientId;
	private String patientName;
	
	private Integer doctorId;
	private String doctorName;
	
	private Integer anesthetistId;
	private String anesthetistName;
	
	private Integer coordinatorId;
	private String coordinatorName;

	private CodeAndDescription statusCode;
	private Long otBookingNbr;
	private Date surgeryDate;
	private String remarks;
	private String userId;
	private Date cratedDtm;
	
	private String referenceNbr;
	private String referenceType;

//	private Set otPatientSurgeryNoteses;
	
	private List<CodeAndDescription> bookingStatusList;
	private List<CodeAndDescription> surgeryStatusList;
	
	private List<PatientSurgeryActivityBM> patientSurgeryActivityBMList;

	
	
	public Long getPatientSurgeryId() {
		return patientSurgeryId;
	}

	public void setPatientSurgeryId(Long patientSurgeryId) {
		this.patientSurgeryId = patientSurgeryId;
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

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Integer getAnesthetistId() {
		return anesthetistId;
	}

	public void setAnesthetistId(Integer anesthetistId) {
		this.anesthetistId = anesthetistId;
	}

	public String getAnesthetistName() {
		return anesthetistName;
	}

	public void setAnesthetistName(String anesthetistName) {
		this.anesthetistName = anesthetistName;
	}

	public Integer getCoordinatorId() {
		return coordinatorId;
	}

	public void setCoordinatorId(Integer coordinatorId) {
		this.coordinatorId = coordinatorId;
	}

	public String getCoordinatorName() {
		return coordinatorName;
	}

	public void setCoordinatorName(String coordinatorName) {
		this.coordinatorName = coordinatorName;
	}

	public CodeAndDescription getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(CodeAndDescription statusCode) {
		this.statusCode = statusCode;
	}

	public Long getOtBookingNbr() {
		return otBookingNbr;
	}

	public void setOtBookingNbr(Long otBookingNbr) {
		this.otBookingNbr = otBookingNbr;
	}

	public Date getSurgeryDate() {
		return surgeryDate;
	}

	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCratedDtm() {
		return cratedDtm;
	}

	public void setCratedDtm(Date cratedDtm) {
		this.cratedDtm = cratedDtm;
	}

	public List<PatientSurgeryActivityBM> getPatientSurgeryActivityBMList() {
		return patientSurgeryActivityBMList;
	}

	public void setPatientSurgeryActivityBMList(
			List<PatientSurgeryActivityBM> patientSurgeryActivityBMList) {
		this.patientSurgeryActivityBMList = patientSurgeryActivityBMList;
	}

	public String getReferenceNbr() {
		return referenceNbr;
	}

	public String getReferenceType() {
		return referenceType;
	}

	public void setReferenceNbr(String referenceNbr) {
		this.referenceNbr = referenceNbr;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	public List<CodeAndDescription> getBookingStatusList() {
		return bookingStatusList;
	}

	public List<CodeAndDescription> getSurgeryStatusList() {
		return surgeryStatusList;
	}

	public void setBookingStatusList(List<CodeAndDescription> bookingStatusList) {
		this.bookingStatusList = bookingStatusList;
	}

	public void setSurgeryStatusList(List<CodeAndDescription> surgeryStatusList) {
		this.surgeryStatusList = surgeryStatusList;
	}

	}
