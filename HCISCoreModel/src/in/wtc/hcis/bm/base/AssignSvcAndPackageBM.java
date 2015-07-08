/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.List;

/**
 * @author Welcome
 *
 */
@SuppressWarnings("serial")
public class AssignSvcAndPackageBM implements Serializable {
	
	private Integer serviceOrderNumber;
	private Integer doctorId;
	private Integer patientId;
	private String referenceNumber;
	private String referenceType;
	private String createdBy;

	private List<AssignedPackageBM> assignedPackageBMList;
	private List<AssignedServiceBM> assignedServiceBMList;
	
	
	public Integer getServiceOrderNumber() {
		return serviceOrderNumber;
	}
	public void setServiceOrderNumber(Integer serviceOrderNumber) {
		this.serviceOrderNumber = serviceOrderNumber;
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
	
	public String getReferenceType() {
		return referenceType;
	}
	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public List<AssignedPackageBM> getAssignedPackageBMList() {
		return assignedPackageBMList;
	}
	public void setAssignedPackageBMList(
			List<AssignedPackageBM> assignedPackageBMList) {
		this.assignedPackageBMList = assignedPackageBMList;
	}
	public List<AssignedServiceBM> getAssignedServiceBMList() {
		return assignedServiceBMList;
	}
	public void setAssignedServiceBMList(
			List<AssignedServiceBM> assignedServiceBMList) {
		this.assignedServiceBMList = assignedServiceBMList;
	}
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	
	
}
