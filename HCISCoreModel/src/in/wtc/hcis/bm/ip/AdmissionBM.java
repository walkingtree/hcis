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
public class AdmissionBM implements Serializable {

	private Integer admissionReqNbr;
	private Integer admissionNbr;
	private CodeAndDescription admissionEntryPoint;
	private CodeAndDescription admissionStatus;
	private String admissionRequestedBy;
	private Integer doctorId;
	private Integer patientId;
	private String patientName;
	private String entryPointReference;
	private String reasonForAdmission;
	private String agenda;
	private Date admissionDt;
	private String treatmentEstimationBy;
	private Double treatmentEstimatedCost;
	private Double treatmentActualCost;
	private Date expectedDischargeDtm;
	private Date dischargeDtm;
	private Integer dischargeByDoctorId;
	private Date nextVisitDt;
	private Date createDtm;
	private Date lastUpdatedDtm;
	private CodeAndDescription nursingUnitType;
	private List<DoctorOrderDetailsBM>doctorOrderDetailsList;
	private Integer doctorOrderNbr;
	private CodeAndDescription doctorOrderStatus;
	private String orderDesc;
	private String orderDictation;
	private String modifiedBy;
	
	private String bedNumber;
	private Date expectedAdmissionDtm;
	
	private Integer seqNbr;
	
	
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
	public String getAdmissionRequestedBy() {
		return admissionRequestedBy;
	}
	public void setAdmissionRequestedBy(String admissionRequestedBy) {
		this.admissionRequestedBy = admissionRequestedBy;
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
	public String getTreatmentEstimationBy() {
		return treatmentEstimationBy;
	}
	public void setTreatmentEstimationBy(String treatmentEstimationBy) {
		this.treatmentEstimationBy = treatmentEstimationBy;
	}
	public Double getTreatmentEstimatedCost() {
		return treatmentEstimatedCost;
	}
	public void setTreatmentEstimatedCost(Double treatmentEstimatedCost) {
		this.treatmentEstimatedCost = treatmentEstimatedCost;
	}
	public Double getTreatmentActualCost() {
		return treatmentActualCost;
	}
	public void setTreatmentActualCost(Double treatmentActualCost) {
		this.treatmentActualCost = treatmentActualCost;
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
	public Integer getDischargeByDoctorId() {
		return dischargeByDoctorId;
	}
	public void setDischargeByDoctorId(Integer dischargeByDoctorId) {
		this.dischargeByDoctorId = dischargeByDoctorId;
	}
	public Date getNextVisitDt() {
		return nextVisitDt;
	}
	public void setNextVisitDt(Date nextVisitDt) {
		this.nextVisitDt = nextVisitDt;
	}
	public Date getCreateDtm() {
		return createDtm;
	}
	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}
	public Date getLastUpdatedDtm() {
		return lastUpdatedDtm;
	}
	public void setLastUpdatedDtm(Date lastUpdatedDtm) {
		this.lastUpdatedDtm = lastUpdatedDtm;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public CodeAndDescription getNursingUnitType() {
		return nursingUnitType;
	}
	public void setNursingUnitType(CodeAndDescription nursingUnitType) {
		this.nursingUnitType = nursingUnitType;
	}
	public List<DoctorOrderDetailsBM> getDoctorOrderDetailsList() {
		return doctorOrderDetailsList;
	}
	public void setDoctorOrderDetailsList(
			List<DoctorOrderDetailsBM> doctorOrderDetailsList) {
		this.doctorOrderDetailsList = doctorOrderDetailsList;
	}
	public Integer getDoctorOrderNbr() {
		return doctorOrderNbr;
	}
	public void setDoctorOrderNbr(Integer doctorOrderNbr) {
		this.doctorOrderNbr = doctorOrderNbr;
	}
	public CodeAndDescription getDoctorOrderStatus() {
		return doctorOrderStatus;
	}
	public void setDoctorOrderStatus(CodeAndDescription doctorOrderStatus) {
		this.doctorOrderStatus = doctorOrderStatus;
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
	public String getBedNumber() {
		return bedNumber;
	}
	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}
	public Date getExpectedAdmissionDtm() {
		return expectedAdmissionDtm;
	}
	public void setExpectedAdmissionDtm(Date expectedAdmissionDtm) {
		this.expectedAdmissionDtm = expectedAdmissionDtm;
	}
	public Integer getSeqNbr() {
		return seqNbr;
	}
	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
}
