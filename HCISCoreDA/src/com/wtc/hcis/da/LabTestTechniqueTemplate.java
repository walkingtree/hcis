package com.wtc.hcis.da;

import java.util.Date;

/**
 * LabTestTechniqueTemplate entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabTestTechniqueTemplate implements java.io.Serializable {

	// Fields

	private LabTestTechniqueTemplateId id;
	private LabTechniqueReagent labTechniqueReagent;
	private LabTest labTestByTestCode;
	private LabTest labTestByTechniqueId;
	private Date createdDtm;
	private String createdBy;

	// Constructors

	/** default constructor */
	public LabTestTechniqueTemplate() {
	}

	/** full constructor */
	public LabTestTechniqueTemplate(LabTestTechniqueTemplateId id,
			LabTechniqueReagent labTechniqueReagent, LabTest labTestByTestCode,
			LabTest labTestByTechniqueId, Date createdDtm, String createdBy) {
		this.id = id;
		this.labTechniqueReagent = labTechniqueReagent;
		this.labTestByTestCode = labTestByTestCode;
		this.labTestByTechniqueId = labTestByTechniqueId;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
	}

	// Property accessors

	public LabTestTechniqueTemplateId getId() {
		return this.id;
	}

	public void setId(LabTestTechniqueTemplateId id) {
		this.id = id;
	}

	public LabTechniqueReagent getLabTechniqueReagent() {
		return this.labTechniqueReagent;
	}

	public void setLabTechniqueReagent(LabTechniqueReagent labTechniqueReagent) {
		this.labTechniqueReagent = labTechniqueReagent;
	}

	public LabTest getLabTestByTestCode() {
		return this.labTestByTestCode;
	}

	public void setLabTestByTestCode(LabTest labTestByTestCode) {
		this.labTestByTestCode = labTestByTestCode;
	}

	public LabTest getLabTestByTechniqueId() {
		return this.labTestByTechniqueId;
	}

	public void setLabTestByTechniqueId(LabTest labTestByTechniqueId) {
		this.labTestByTechniqueId = labTestByTechniqueId;
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