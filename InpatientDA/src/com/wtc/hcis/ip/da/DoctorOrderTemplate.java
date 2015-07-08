package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * DoctorOrderTemplate entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderTemplate implements java.io.Serializable {

	// Fields

	private String templateId;
	private Integer version;
	private DoctorOrderType doctorOrderType;
	private String templateDesc;
	private Integer doctorId;
	private String activeFlag;
	private Set doctorOrderGroupHasTemplates = new HashSet(0);
	private Set doctorOrderTemplateDetailses = new HashSet(0);
	private Set doctorOrders = new HashSet(0);

	// Constructors

	/** default constructor */
	public DoctorOrderTemplate() {
	}

	/** minimal constructor */
	public DoctorOrderTemplate(String templateId,
			DoctorOrderType doctorOrderType, String templateDesc) {
		this.templateId = templateId;
		this.doctorOrderType = doctorOrderType;
		this.templateDesc = templateDesc;
	}

	/** full constructor */
	public DoctorOrderTemplate(String templateId,
			DoctorOrderType doctorOrderType, String templateDesc,
			Integer doctorId, String activeFlag,
			Set doctorOrderGroupHasTemplates, Set doctorOrderTemplateDetailses,
			Set doctorOrders) {
		this.templateId = templateId;
		this.doctorOrderType = doctorOrderType;
		this.templateDesc = templateDesc;
		this.doctorId = doctorId;
		this.activeFlag = activeFlag;
		this.doctorOrderGroupHasTemplates = doctorOrderGroupHasTemplates;
		this.doctorOrderTemplateDetailses = doctorOrderTemplateDetailses;
		this.doctorOrders = doctorOrders;
	}

	// Property accessors

	public String getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public DoctorOrderType getDoctorOrderType() {
		return this.doctorOrderType;
	}

	public void setDoctorOrderType(DoctorOrderType doctorOrderType) {
		this.doctorOrderType = doctorOrderType;
	}

	public String getTemplateDesc() {
		return this.templateDesc;
	}

	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Set getDoctorOrderGroupHasTemplates() {
		return this.doctorOrderGroupHasTemplates;
	}

	public void setDoctorOrderGroupHasTemplates(Set doctorOrderGroupHasTemplates) {
		this.doctorOrderGroupHasTemplates = doctorOrderGroupHasTemplates;
	}

	public Set getDoctorOrderTemplateDetailses() {
		return this.doctorOrderTemplateDetailses;
	}

	public void setDoctorOrderTemplateDetailses(Set doctorOrderTemplateDetailses) {
		this.doctorOrderTemplateDetailses = doctorOrderTemplateDetailses;
	}

	public Set getDoctorOrders() {
		return this.doctorOrders;
	}

	public void setDoctorOrders(Set doctorOrders) {
		this.doctorOrders = doctorOrders;
	}

}