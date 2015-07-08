/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;

/**
 * @author Alok Ranjan
 * 
 */
@SuppressWarnings("serial")
public class OrderTemplateDetailsBM implements Serializable {

	private String templateId;  	//NotNull field

	private Integer sequenceNbr;	//NotNull field
	private Integer subSequenceNbr; //NotNull field
	private String serviceCode;
	private String serviceName;
	private CodeAndDescription servicePackage;
	private String packageIndicator;
	
	private CodeAndDescription responsibleDepartment;
	private String actionDesc;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
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

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public CodeAndDescription getResponsibleDepartment() {
		return responsibleDepartment;
	}

	public void setResponsibleDepartment(
			CodeAndDescription responsibleDepartment) {
		this.responsibleDepartment = responsibleDepartment;
	}

	public String getActionDesc() {
		return actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	public CodeAndDescription getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(CodeAndDescription servicePackage) {
		this.servicePackage = servicePackage;
	}

	public String getPackageIndicator() {
		return packageIndicator;
	}

	public void setPackageIndicator(String packageIndicator) {
		this.packageIndicator = packageIndicator;
	}


}
