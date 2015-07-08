package in.wtc.lims.bm;

import java.util.List;

public class TestTemplateBM {
	
	private List<TemplateWidgetBM> patientWidgetList ;
	private List<TemplateWidgetBM> doctorWidgetList ;
	private List<TemplateWidgetBM> testAttributeWidgetList ;
	private List<TemplateWidgetBM> otherWidgetList ;
	
	private String testCode;
	private String createdBy;
	private Integer templateId;
	
	public List<TemplateWidgetBM> getPatientWidgetList() {
		return patientWidgetList;
	}
	public void setPatientWidgetList(List<TemplateWidgetBM> patientWidgetList) {
		this.patientWidgetList = patientWidgetList;
	}
	public List<TemplateWidgetBM> getDoctorWidgetList() {
		return doctorWidgetList;
	}
	public void setDoctorWidgetList(List<TemplateWidgetBM> doctorWidgetList) {
		this.doctorWidgetList = doctorWidgetList;
	}
	public List<TemplateWidgetBM> getTestAttributeWidgetList() {
		return testAttributeWidgetList;
	}
	public void setTestAttributeWidgetList(
			List<TemplateWidgetBM> testAttributeWidgetList) {
		this.testAttributeWidgetList = testAttributeWidgetList;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public List<TemplateWidgetBM> getOtherWidgetList() {
		return otherWidgetList;
	}
	public void setOtherWidgetList(List<TemplateWidgetBM> otherWidgetList) {
		this.otherWidgetList = otherWidgetList;
	}

}
