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
public class PatientSurgeryActivityBM {

	private Long patientSurgeryId;
	private CodeAndDescription surgeryStatus;
	private Date activityTime;
	private String remarks;
	private String entryCreatedBy;
	
	public Long getPatientSurgeryId() {
		return patientSurgeryId;
	}
	public void setPatientSurgeryId(Long patientSurgeryId) {
		this.patientSurgeryId = patientSurgeryId;
	}
	public CodeAndDescription getSurgeryStatus() {
		return surgeryStatus;
	}
	public void setSurgeryStatus(CodeAndDescription surgeryStatus) {
		this.surgeryStatus = surgeryStatus;
	}
	public Date getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getEntryCreatedBy() {
		return entryCreatedBy;
	}
	public void setEntryCreatedBy(String entryCreatedBy) {
		this.entryCreatedBy = entryCreatedBy;
	}
}
