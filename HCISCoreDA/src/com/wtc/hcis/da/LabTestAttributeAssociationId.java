package com.wtc.hcis.da;

/**
 * LabTestAttributeAssociationId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabTestAttributeAssociationId implements java.io.Serializable {

	// Fields

	private String testCode;
	private String attributeCode;
	private Integer techniqueId;

	// Constructors

	/** default constructor */
	public LabTestAttributeAssociationId() {
	}

	/** full constructor */
	public LabTestAttributeAssociationId(String testCode, String attributeCode,
			Integer techniqueId) {
		this.testCode = testCode;
		this.attributeCode = attributeCode;
		this.techniqueId = techniqueId;
	}

	// Property accessors

	public String getTestCode() {
		return this.testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public String getAttributeCode() {
		return this.attributeCode;
	}

	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
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
		if (!(other instanceof LabTestAttributeAssociationId))
			return false;
		LabTestAttributeAssociationId castOther = (LabTestAttributeAssociationId) other;

		return ((this.getTestCode() == castOther.getTestCode()) || (this
				.getTestCode() != null
				&& castOther.getTestCode() != null && this.getTestCode()
				.equals(castOther.getTestCode())))
				&& ((this.getAttributeCode() == castOther.getAttributeCode()) || (this
						.getAttributeCode() != null
						&& castOther.getAttributeCode() != null && this
						.getAttributeCode()
						.equals(castOther.getAttributeCode())))
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
				+ (getAttributeCode() == null ? 0 : this.getAttributeCode()
						.hashCode());
		result = 37
				* result
				+ (getTechniqueId() == null ? 0 : this.getTechniqueId()
						.hashCode());
		return result;
	}

}