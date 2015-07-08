/**
 * 
 */
package in.wtc.hcis.bm.ip;

import java.io.Serializable;

/**
 * @author Bhavesh
 *
 *
 *The purpose of this light BM is to provide some information about admission
 *which is needed
 */
@SuppressWarnings("serial")
public class AdmissionLightBM implements Serializable {

	private Integer admissionReqNbr;
	private Integer admissionNbr;
	private Integer patientId;
	private String  patientName;
	private Double  estimatedTreatmentAmnt;
	private String  estimatedBy;
	
	
	public Integer getAdmissionReqNbr() {
		return admissionReqNbr;
	}
	public void setAdmissionReqNbr(Integer admissionReqNbr) {
		this.admissionReqNbr = admissionReqNbr;
	}
	public Integer getAdmissionNbr() {
		return admissionNbr;
	}
	public void setAdmissionNbr(Integer admissionNbr) {
		this.admissionNbr = admissionNbr;
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
	public Double getEstimatedTreatmentAmnt() {
		return estimatedTreatmentAmnt;
	}
	public void setEstimatedTreatmentAmnt(Double estimatedTreatmentAmnt) {
		this.estimatedTreatmentAmnt = estimatedTreatmentAmnt;
	}
	public String getEstimatedBy() {
		return estimatedBy;
	}
	public void setEstimatedBy(String estimatedBy) {
		this.estimatedBy = estimatedBy;
	}
}
