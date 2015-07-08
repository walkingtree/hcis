package in.wtc.hcis.bm.base;

import java.util.Date;
import java.util.List;

public class PatientVitalBM {
	
	private Integer patientId;
	private String referenceType;
	private String referenceNumber;
	private String userId;
	private Date createdDtm;
	
	List<VitalFieldBM> vitalFieldBMList;
	
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
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
	public List<VitalFieldBM> getVitalFieldBMList() {
		return vitalFieldBMList;
	}
	public void setVitalFieldBMList(List<VitalFieldBM> vitalFieldBMList) {
		this.vitalFieldBMList = vitalFieldBMList;
	}
	public String getUserId() {
		return userId;
	}
	public Date getCreatedDtm() {
		return createdDtm;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}
	

}
