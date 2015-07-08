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
public class DoctorOrderDetailsBM implements Serializable {
	private Integer doctorOrderNumber;
	private Integer sequenceNbr;
	private Integer subSequenceNbr;
	private CodeAndDescription actionStatus;
	private CodeAndDescription responsibleDepartment;
	private String serviceCode;
	private String serviceName;
	private CodeAndDescription servicePackage;
	private String packageIndicator;
	private String actionDesc;
	private String actionRemarks;
	private Date   actionDtm;
	private String actionTakenBy;
	
	
	private CodeAndDescription orderType;//this will be used while returning detail of line items
	
	public CodeAndDescription getOrderType() {
		return orderType;
	}
	public void setOrderType(CodeAndDescription orderType) {
		this.orderType = orderType;
	}
	public Integer getDoctorOrderNumber() {
		return doctorOrderNumber;
	}
	public void setDoctorOrderNumber(Integer doctorOrderNumber) {
		this.doctorOrderNumber = doctorOrderNumber;
	}
	
	public CodeAndDescription getActionStatus() {
		return actionStatus;
	}
	public void setActionStatus(CodeAndDescription actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String getActionDesc() {
		return actionDesc;
	}
	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}
	public String getActionRemarks() {
		return actionRemarks;
	}
	public void setActionRemarks(String actionRemarks) {
		this.actionRemarks = actionRemarks;
	}
	public Date getActionDtm() {
		return actionDtm;
	}
	public void setActionDtm(Date actionDtm) {
		this.actionDtm = actionDtm;
	}
	public String getActionTakenBy() {
		return actionTakenBy;
	}
	public void setActionTakenBy(String actionTakenBy) {
		this.actionTakenBy = actionTakenBy;
	}
	public Integer getSequenceNbr() {
		return sequenceNbr;
	}
	public void setSequenceNbr(Integer sequenceNbr) {
		this.sequenceNbr = sequenceNbr;
	}
	public Integer getSubSequenceNbr() {
		return subSequenceNbr;
	}
	public void setSubSequenceNbr(Integer subSequenceNbr) {
		this.subSequenceNbr = subSequenceNbr;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public CodeAndDescription getResponsibleDepartment() {
		return responsibleDepartment;
	}
	public void setResponsibleDepartment(CodeAndDescription responsibleDepartment) {
		this.responsibleDepartment = responsibleDepartment;
	}
	/**
	 * @return the packageIndicator
	 */
	public String getPackageIndicator() {
		return packageIndicator;
	}
	/**
	 * @param packageIndicator the packageIndicator to set
	 */
	public void setPackageIndicator(String packageIndicator) {
		this.packageIndicator = packageIndicator;
	}
	/**
	 * @return the servicePackage
	 */
	public CodeAndDescription getServicePackage() {
		return servicePackage;
	}
	/**
	 * @param servicePackage the servicePackage to set
	 */
	public void setServicePackage(CodeAndDescription servicePackage) {
		this.servicePackage = servicePackage;
	}
}
