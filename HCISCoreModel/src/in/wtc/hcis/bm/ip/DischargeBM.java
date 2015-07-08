/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class DischargeBM implements Serializable {
	private Integer admissionReqNbr;
	private Integer doctorOrderNbr;
	private CodeAndDescription dischargeType;
	private CodeAndDescription doctorOrderStatus;
	private Date orderCreationDtm;
	private String orderRequestedBy;
	private String approvedBy;
	private Date approvalTime;
	private String modifiedBy;
	private Date lastModifiedDtm;
	private Date expectedDischargeDate;
	private Date actualDischargeDtm;
	private List<DoctorOrderDetailsBM>doctorOrderDetailsList;
	private String orderDesc;
	private String orderDictation;
	private Integer patientId;
	private Integer doctorId;
	private String dischargeSummary;

	
	
	public DischargeBM() {
		
	}
	public Integer getAdmissionReqNbr() {
		return admissionReqNbr;
	}
	public void setAdmissionReqNbr(Integer admissionReqNbr) {
		this.admissionReqNbr = admissionReqNbr;
	}
	public Integer getDoctorOrderNbr() {
		return doctorOrderNbr;
	}
	public void setDoctorOrderNbr(Integer doctorOrderNbr) {
		this.doctorOrderNbr = doctorOrderNbr;
	}
	public CodeAndDescription getDischargeType() {
		return dischargeType;
	}
	public void setDischargeType(CodeAndDescription dischargeType) {
		this.dischargeType = dischargeType;
	}
	public CodeAndDescription getDoctorOrderStatus() {
		return doctorOrderStatus;
	}
	public void setDoctorOrderStatus(CodeAndDescription doctorOrderStatus) {
		this.doctorOrderStatus = doctorOrderStatus;
	}
	public Date getOrderCreationDtm() {
		return orderCreationDtm;
	}
	public void setOrderCreationDtm(Date orderCreationDtm) {
		this.orderCreationDtm = orderCreationDtm;
	}
	public String getOrderRequestedBy() {
		return orderRequestedBy;
	}
	public void setOrderRequestedBy(String orderRequestedBy) {
		this.orderRequestedBy = orderRequestedBy;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public Date getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getLastModifiedDtm() {
		return lastModifiedDtm;
	}
	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}
	public Date getExpectedDischargeDate() {
		return expectedDischargeDate;
	}
	public void setExpectedDischargeDate(Date expectedDischargeDate) {
		this.expectedDischargeDate = expectedDischargeDate;
	}
	public Date getActualDischargeDtm() {
		return actualDischargeDtm;
	}
	public void setActualDischargeDtm(Date actualDischargeDtm) {
		this.actualDischargeDtm = actualDischargeDtm;
	}
	public List<DoctorOrderDetailsBM> getDoctorOrderDetailsList() {
		return doctorOrderDetailsList;
	}
	public void setDoctorOrderDetailsList(
			List<DoctorOrderDetailsBM> doctorOrderDetailsList) {
		this.doctorOrderDetailsList = doctorOrderDetailsList;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	public String getOrderDictation() {
		return orderDictation;
	}
	public void setOrderDictation(String orderDictation) {
		this.orderDictation = orderDictation;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public String getDischargeSummary() {
		return dischargeSummary;
	}
	public void setDischargeSummary(String dischargeSummary) {
		this.dischargeSummary = dischargeSummary;
	}
	
	
}
