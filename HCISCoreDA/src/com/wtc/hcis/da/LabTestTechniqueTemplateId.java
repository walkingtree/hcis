package com.wtc.hcis.da;

/**
 * LabTestTechniqueTemplateId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabTestTechniqueTemplateId implements java.io.Serializable {

	// Fields

	private String testCode;
	private Integer techniqueId;

	// Constructors

	/** default constructor */
	public LabTestTechniqueTemplateId() {
	}

	/** full constructor */
	public LabTestTechniqueTemplateId(String testCode, Integer techniqueId) {
		this.testCode = testCode;
		this.techniqueId = techniqueId;
	}

	// Property accessors

	public String getTestCode() {
		return this.testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public Integer getTechniqueId() {
		return this.techniqueId;
	}

	public void setTechniqueId(Integer techniqueId) {
		this.techniqueId = techniqueId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LabTestTechniqueTemplateId))
			return false;
		LabTestTechniqueTemplateId castOther = (LabTestTechniqueTemplateId) other;

		return ((this.getTestCode() == castOther.getTestCode()) || (this
				.getTestCode() != null
				&& castOther.getTestCode() != null && this.getTestCode()
				.equals(castOther.getTestCode())))
				&& ((this.getTechniqueId() == castOther.getTechniqueId()) || (this
						.getTechniqueId() != null
						&& castOther.getTechniqueId() != null && this
						.getTechniqueId().equals(castOther.getTechniqueId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTestCode() == null ? 0 : this.getTestCode().hashCode());
		result = 37
				* result
				+ (getTechniqueId() == null ? 0 : this.getTechniqueId()
						.hashCode());
		return result;
	}

}