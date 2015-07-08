package com.wtc.hcis.da;

import java.util.Date;

/**
 * LabPatientTestChangeHistoryId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabPatientTestChangeHistoryId implements java.io.Serializable {

	// Fields

	private String patientTestId;
	private String attributeCode;
	private Date createdDtm;
	private Integer seqNbr;

	// Constructors

	/** default constructor */
	public LabPatientTestChangeHistoryId() {
	}

	/** full constructor */
	public LabPatientTestChangeHistoryId(String patientTestId,
			String attributeCode, Date createdDtm,Integer seqNbr) {
		this.patientTestId = patientTestId;
		this.attributeCode = attributeCode;
		this.createdDtm = createdDtm;
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

	public Date getCreatedDtm() {
		return this.createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LabPatientTestChangeHistoryId))
			return false;
		LabPatientTestChangeHistoryId castOther = (LabPatientTestChangeHistoryId) other;

		return ((this.getPatientTestId() == castOther.getPatientTestId()) || (this
				.getPatientTestId() != null
				&& castOther.getPatientTestId() != null && this
				.getPatientTestId().equals(castOther.getPatientTestId())))
				&& ((this.getAttributeCode() == castOther.getAttributeCode()) || (this
						.getAttributeCode() != null
						&& castOther.getAttributeCode() != null && this
						.getAttributeCode()
						.equals(castOther.getAttributeCode())))
				&& ((this.getCreatedDtm() == castOther.getCreatedDtm()) || (this
						.getCreatedDtm() != null
						&& castOther.getCreatedDtm() != null && this
						.getCreatedDtm().equals(castOther.getCreatedDtm())));
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
		result = 37
				* result
				+ (getCreatedDtm() == null ? 0 : this.getCreatedDtm()
						.hashCode());
		return result;
	}

	public Integer getSeqNbr() {
		return seqNbr;
	}

	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}

}