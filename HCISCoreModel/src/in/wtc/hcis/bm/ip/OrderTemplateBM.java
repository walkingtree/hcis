/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;
import java.util.List;


/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class OrderTemplateBM implements Serializable {
	
	private String templateId;
	
	private String templateDesc;
	private Integer doctorId;
	private String doctorName;
	
	private CodeAndDescription doctorOrderType;
	private String activeFlag;
	
	private List<OrderTemplateDetailsBM> orderTemplateDetailsList;
	
	private Integer seqNbr; // to be used for UI grid as sequence number

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateDesc() {
		return templateDesc;
	}

	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}

	public CodeAndDescription getDoctorOrderType() {
		return doctorOrderType;
	}

	public void setDoctorOrderType(CodeAndDescription doctorOrderType) {
		this.doctorOrderType = doctorOrderType;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String string) {
		this.activeFlag = string;
	}

	public List<OrderTemplateDetailsBM> getOrderTemplateDetailsList() {
		return orderTemplateDetailsList;
	}

	public void setOrderTemplateDetailsList(
			List<OrderTemplateDetailsBM> orderTemplateDetailsList) {
		this.orderTemplateDetailsList = orderTemplateDetailsList;
	}

	public Integer getSeqNbr() {
		return seqNbr;
	}

	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}

}
