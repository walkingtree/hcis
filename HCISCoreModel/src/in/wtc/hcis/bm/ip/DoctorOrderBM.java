/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class DoctorOrderBM implements Serializable {
	
	private Integer admissionNbr;//this field contains admission request number
	private Integer creationSeqNbr;//For Back-end need, to group the orders
	private Integer patientId;
	private Integer doctorId;
	private String orderDictation;
	private String modifiedBy;
	private Date lastModifiedTm;
	private String createdBy;
	private Date orderCreationDate;
	private String patientName;  
	private String doctorName;   

	private Integer doctorOrderNbr;
	private CodeAndDescription doctorOrderType;
	private CodeAndDescription doctorOrderStatus;
	private String orderDesc;
	private CodeAndDescription doctorOrderTemplate;
	private CodeAndDescription doctorOrderGroup;
	
	private List<DoctorOrderDetailsBM> doctorOrderDetailsList;
	
	/**
	 * The purpose of order specific attributes map is to avoid having multiple business 
	 * models for different orders, with only difference being different attribute names.
	 * The values for this map can come from two sources
	 * 1) User input from the UI
	 * 2) Doctor order template when order is created using the template
	 * 
	 */
	private Map<String, String>orderSpecificAttributes; 
	
	
	private Integer seqNbr; // to be used for UI grid as sequence number
	
	public Integer getDoctorOrderNbr() {
		return doctorOrderNbr;
	}
	public void setDoctorOrderNbr(Integer doctorOrderNbr) {
		this.doctorOrderNbr = doctorOrderNbr;
	}
	public CodeAndDescription getDoctorOrderType() {
		return doctorOrderType;
	}
	public void setDoctorOrderType(CodeAndDescription doctorOrderType) {
		this.doctorOrderType = doctorOrderType;
	}
	public CodeAndDescription getDoctorOrderTemplate() {
		return doctorOrderTemplate;
	}
	public void setDoctorOrderTemplate(CodeAndDescription doctorOrderTemplate) {
		this.doctorOrderTemplate = doctorOrderTemplate;
	}
	public Integer getAdmissionNbr() {
		return admissionNbr;
	}
	public void setAdmissionNbr(Integer admissionNbr) {
		this.admissionNbr = admissionNbr;
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
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getLastModifiedTm() {
		return lastModifiedTm;
	}
	public void setLastModifiedTm(Date lastModifiedTm) {
		this.lastModifiedTm = lastModifiedTm;
	}
	public List<DoctorOrderDetailsBM> getDoctorOrderDetailsList() {
		return doctorOrderDetailsList;
	}
	public void setDoctorOrderDetailsList(
			List<DoctorOrderDetailsBM> doctorOrderDetailsList) {
		this.doctorOrderDetailsList = doctorOrderDetailsList;
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
	public Map<String, String> getOrderSpecificAttributes() {
		return orderSpecificAttributes;
	}
	public void setOrderSpecificAttributes(
			Map<String, String> orderSpecificAttributes) {
		this.orderSpecificAttributes = orderSpecificAttributes;
	}
	public CodeAndDescription getDoctorOrderGroup() {
		return doctorOrderGroup;
	}
	public void setDoctorOrderGroup(CodeAndDescription doctorOrderGroup) {
		this.doctorOrderGroup = doctorOrderGroup;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Date getOrderCreationDate() {
		return orderCreationDate;
	}
	public void setOrderCreationDate(Date orderCreationDate) {
		this.orderCreationDate = orderCreationDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public Integer getSeqNbr() {
		return seqNbr;
	}
	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}
	public Integer getCreationSeqNbr() {
		return creationSeqNbr;
	}
	public void setCreationSeqNbr(Integer creationSeqNbr) {
		this.creationSeqNbr = creationSeqNbr;
	}
	
}
