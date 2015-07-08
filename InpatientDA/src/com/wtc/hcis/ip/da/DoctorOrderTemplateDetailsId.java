package com.wtc.hcis.ip.da;

/**
 * DoctorOrderTemplateDetailsId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderTemplateDetailsId implements java.io.Serializable {

	// Fields

	private String templateId;
	private Integer sequenceNbr;
	private Integer subSequenceNbr;

	// Constructors

	/** default constructor */
	public DoctorOrderTemplateDetailsId() {
	}

	/** full constructor */
	public DoctorOrderTemplateDetailsId(String templateId, Integer sequenceNbr,
			Integer subSequenceNbr) {
		this.templateId = templateId;
		this.sequenceNbr = sequenceNbr;
		this.subSequenceNbr = subSequenceNbr;
	}

	// Property accessors

	public String getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public Integer getSequenceNbr() {
		return this.sequenceNbr;
	}

	public void setSequenceNbr(Integer sequenceNbr) {
		this.sequenceNbr = sequenceNbr;
	}

	public Integer getSubSequenceNbr() {
		return this.subSequenceNbr;
	}

	public void setSubSequenceNbr(Integer subSequenceNbr) {
		this.subSequenceNbr = subSequenceNbr;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DoctorOrderTemplateDetailsId))
			return false;
		DoctorOrderTemplateDetailsId castOther = (DoctorOrderTemplateDetailsId) other;

		return ((this.getTemplateId() == castOther.getTemplateId()) || (this
				.getTemplateId() != null
				&& castOther.getTemplateId() != null && this.getTemplateId()
				.equals(castOther.getTemplateId())))
				&& ((this.getSequenceNbr() == castOther.getSequenceNbr()) || (this
						.getSequenceNbr() != null
						&& castOther.getSequenceNbr() != null && this
						.getSequenceNbr().equals(castOther.getSequenceNbr())))
				&& ((this.getSubSequenceNbr() == castOther.getSubSequenceNbr()) || (this
						.getSubSequenceNbr() != null
						&& castOther.getSubSequenceNbr() != null && this
						.getSubSequenceNbr().equals(
								castOther.getSubSequenceNbr())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getTemplateId() == null ? 0 : this.getTemplateId()
						.hashCode());
		result = 37
				* result
				+ (getSequenceNbr() == null ? 0 : this.getSequenceNbr()
						.hashCode());
		result = 37
				* result
				+ (getSubSequenceNbr() == null ? 0 : this.getSubSequenceNbr()
						.hashCode());
		return result;
	}

}