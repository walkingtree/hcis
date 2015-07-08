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
public class OrderTemplateSummaryBM implements Serializable {
	private String templateId;
	private CodeAndDescription doctorOrderType;
	private String templateDesc;
	private Integer doctorId;
	
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public CodeAndDescription getDoctorOrderType() {
		return doctorOrderType;
	}
	public void setDoctorOrderType(CodeAndDescription doctorOrderType) {
		this.doctorOrderType = doctorOrderType;
	}
	public String getTemplateDesc() {
		return templateDesc;
	}
	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
}
