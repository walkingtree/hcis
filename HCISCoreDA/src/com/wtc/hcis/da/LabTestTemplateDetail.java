package com.wtc.hcis.da;

import java.util.Date;

/**
 * LabTestTemplateDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabTestTemplateDetail implements java.io.Serializable {

	// Fields

	private LabTestTemplateDetailId id;
	private LabTestTemplate labTestTemplate;
	private String cellId;
	private String widgetCode;
	private Date createdDtm;
	private String createdBy;

	// Constructors

	/** default constructor */
	public LabTestTemplateDetail() {
	}

	/** minimal constructor */
	public LabTestTemplateDetail(LabTestTemplateDetailId id,
			LabTestTemplate labTestTemplate, String widgetCode,
			Date createdDtm, String createdBy) {
		this.id = id;
		this.labTestTemplate = labTestTemplate;
		this.widgetCode = widgetCode;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
	}

	/** full constructor */
	public LabTestTemplateDetail(LabTestTemplateDetailId id,
			LabTestTemplate labTestTemplate, String cellId, String widgetCode,
			Date createdDtm, String createdBy) {
		this.id = id;
		this.labTestTemplate = labTestTemplate;
		this.cellId = cellId;
		this.widgetCode = widgetCode;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
	}

	// Property accessors

	public LabTestTemplateDetailId getId() {
		return this.id;
	}

	public void setId(LabTestTemplateDetailId id) {
		this.id = id;
	}

	public LabTestTemplate getLabTestTemplate() {
		return this.labTestTemplate;
	}

	public void setLabTestTemplate(LabTestTemplate labTestTemplate) {
		this.labTestTemplate = labTestTemplate;
	}

	public String getCellId() {
		return this.cellId;
	}

	public void setCellId(String cellId) {
		this.cellId = cellId;
	}

	public String getWidgetCode() {
		return this.widgetCode;
	}

	public void setWidgetCode(String widgetCode) {
		this.widgetCode = widgetCode;
	}

	public Date getCreatedDtm() {
		return this.createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}