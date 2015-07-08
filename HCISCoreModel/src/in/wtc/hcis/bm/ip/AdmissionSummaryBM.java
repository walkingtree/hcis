/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class AdmissionSummaryBM implements Serializable {
	private Integer admissionNbr;
	private CodeAndDescription admissionEntryPoint;
	private CodeAndDescription admissionStatus;
	private Integer doctorId;
	private Integer patientId;
	private String entryPointReference;
	private String reasonForAdmission;
	private String agenda;
	private Date admissionDt;
	private Date expectedDischargeDtm;
	private Date dischargeDtm;
	private Date lastUpdatedDtm;
	private CodeAndDescription nursingUnitType;
	private String modifiedBy;
	
	public Integer getAdmissionNbr() {
		return admissionNbr;
	}
	public void setAdmissionNbr(Integer admissionNbr) {
		this.admissionNbr = admissionNbr;
	}
	public CodeAndDescription getAdmissionEntryPoint() {
		return admissionEntryPoint;
	}
	public void setAdmissionEntryPoint(CodeAndDescription admissionEntryPoint) {
		this.admissionEntryPoint = admissionEntryPoint;
	}
	public CodeAndDescription getAdmissionStatus() {
		return admissionStatus;
	}
	public void setAdmissionStatus(CodeAndDescription admissionStatus) {
		this.admissionStatus = admissionStatus;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public String getEntryPointReference() {
		return entryPointReference;
	}
	public void setEntryPointReference(String entryPointReference) {
		this.entryPointReference = entryPointReference;
	}
	public String getReasonForAdmission() {
		return reasonForAdmission;
	}
	public void setReasonForAdmission(String reasonForAdmission) {
		this.reasonForAdmission = reasonForAdmission;
	}
	public String getAgenda() {
		return agenda;
	}
	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}
	public Date getAdmissionDt() {
		return admissionDt;
	}
	public void setAdmissionDt(Date admissionDt) {
		this.admissionDt = admissionDt;
	}
	public Date getExpectedDischargeDtm() {
		return expectedDischargeDtm;
	}
	public void setExpectedDischargeDtm(Date expectedDischargeDtm) {
		this.expectedDischargeDtm = expectedDischargeDtm;
	}
	public Date getDischargeDtm() {
		return dischargeDtm;
	}
	public void setDischargeDtm(Date dischargeDtm) {
		this.dischargeDtm = dischargeDtm;
	}
	public Date getLastUpdatedDtm() {
		return lastUpdatedDtm;
	}
	public void setLastUpdatedDtm(Date lastUpdatedDtm) {
		this.lastUpdatedDtm = lastUpdatedDtm;
	}
	public CodeAndDescription getNursingUnitType() {
		return nursingUnitType;
	}
	public void setNursingUnitType(CodeAndDescription nursingUnitType) {
		this.nursingUnitType = nursingUnitType;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
