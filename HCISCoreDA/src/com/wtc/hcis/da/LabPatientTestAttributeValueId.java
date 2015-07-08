package com.wtc.hcis.da;

/**
 * LabPatientTestAttributeValueId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabPatientTestAttributeValueId implements java.io.Serializable {

	// Fields

	private String patientTestId;
	private String attributeCode;
	private Integer seqNbr;

	// Constructors

	/** default constructor */
	public LabPatientTestAttributeValueId() {
	}

	/** full constructor */
	public LabPatientTestAttributeValueId(String patientTestId,
			String attributeCode, Integer seqNbr) {
		this.patientTestId = patientTestId;
		this.attributeCode = attributeCode;
		this.seqNbr = seqNbr;
	}

	// Property accessors

	public String getPatientTestId() {
		return this.patientTestId;
	}

	public void setPatientTestId(String patientTestId) {
		this.patientTestId = patientTestId;
	}

	public String getAttributeCode() {
		return this.attributeCode;
	}

	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}

	public Integer getSeqNbr() {
		return this.seqNbr;
	}

	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LabPatientTestAttributeValueId))
			return false;
		LabPatientTestAttributeValueId castOther = (LabPatientTestAttributeValueId) other;

		return ((this.getPatientTestId() == castOther.getPatientTestId()) || (this
				.getPatientTestId() != null
				&& castOther.getPatientTestId() != null && this
				.getPatientTestId().equals(castOther.getPatientTestId())))
				&& ((this.getAttributeCode() == castOther.getAttributeCode()) || (this
						.getAttributeCode() != null
						&& castOther.getAttributeCode() != null && this
						.getAttributeCode()
						.equals(castOther.getAttributeCode())))
				&& ((this.getSeqNbr() == castOther.getSeqNbr()) || (this
						.getSeqNbr() != null
						&& castOther.getSeqNbr() != null && this.getSeqNbr()
						.equals(castOther.getSeqNbr())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getPatientTestId() == null ? 0 : this.getPatientTestId()
						.hashCode());
		result = 37
				* result
				+ (getAttributeCode() == null ? 0 : this.getAttributeCode()
						.hashCode());
		result = 37 * result
				+ (getSeqNbr() == null ? 0 : this.getSeqNbr().hashCode());
		return result;
	}

}