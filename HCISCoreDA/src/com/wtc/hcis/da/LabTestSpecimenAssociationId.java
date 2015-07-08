package com.wtc.hcis.da;

/**
 * LabTestSpecimenAssociationId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabTestSpecimenAssociationId implements java.io.Serializable {

	// Fields

	private String testCode;
	private Integer specimenId;

	// Constructors

	/** default constructor */
	public LabTestSpecimenAssociationId() {
	}

	/** full constructor */
	public LabTestSpecimenAssociationId(String testCode, Integer specimenId) {
		this.testCode = testCode;
		this.specimenId = specimenId;
	}

	// Property accessors

	public String getTestCode() {
		return this.testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public Integer getSpecimenId() {
		return this.specimenId;
	}

	public void setSpecimenId(Integer specimenId) {
		this.specimenId = specimenId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LabTestSpecimenAssociationId))
			return false;
		LabTestSpecimenAssociationId castOther = (LabTestSpecimenAssociationId) other;

		return ((this.getTestCode() == castOther.getTestCode()) || (this
				.getTestCode() != null
				&& castOther.getTestCode() != null && this.getTestCode()
				.equals(castOther.getTestCode())))
				&& ((this.getSpecimenId() == castOther.getSpecimenId()) || (this
						.getSpecimenId() != null
						&& castOther.getSpecimenId() != null && this
						.getSpecimenId().equals(castOther.getSpecimenId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTestCode() == null ? 0 : this.getTestCode().hashCode());
		result = 37
				* result
				+ (getSpecimenId() == null ? 0 : this.getSpecimenId()
						.hashCode());
		return result;
	}

}