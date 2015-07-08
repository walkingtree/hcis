package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LabTestTemplate entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabTestTemplate implements java.io.Serializable {

	// Fields

	private Integer templateId;
	private String templateName;
	private String createdBy;
	private Date createdDtm;
	private String lastModifiedBy;
	private Date lastModifiedDtm;
	private Set labTests = new HashSet(0);
	private Set labTestTemplateDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public LabTestTemplate() {
	}

	/** minimal constructor */
	public LabTestTemplate(String templateName, String createdBy,
			Date createdDtm) {
		this.templateName = templateName;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public LabTestTemplate(String templateName, String createdBy,
			Date createdDtm, String lastModifiedBy, Date lastModifiedDtm,
			Set labTests, Set labTestTemplateDetails) {
		this.templateName = templateName;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.labTests = labTests;
		this.labTestTemplateDetails = labTestTemplateDetails;
	}

	// Property accessors

	public Integer getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDtm() {
		return this.createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public Set getLabTests() {
		return this.labTests;
	}

	public void setLabTests(Set labTests) {
		this.labTests = labTests;
	}

	public Set getLabTestTemplateDetails() {
		return this.labTestTemplateDetails;
	}

	public void setLabTestTemplateDetails(Set labTestTemplateDetails) {
		this.labTestTemplateDetails = labTestTemplateDetails;
	}

}