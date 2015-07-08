package com.wtc.hcis.da;

/**
 * LabTestTemplateDetailId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabTestTemplateDetailId implements java.io.Serializable {

	// Fields

	private Integer templateId;
	private String sectionCode;
	private Integer seqNbr;

	// Constructors

	/** default constructor */
	public LabTestTemplateDetailId() {
	}

	/** full constructor */
	public LabTestTemplateDetailId(Integer templateId, String sectionCode,
			Integer seqNbr) {
		this.templateId = templateId;
		this.sectionCode = sectionCode;
		this.seqNbr = seqNbr;
	}

	// Property accessors

	public Integer getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getSectionCode() {
		return this.sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
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
		if (!(other instanceof LabTestTemplateDetailId))
			return false;
		LabTestTemplateDetailId castOther = (LabTestTemplateDetailId) other;

		return ((this.getTemplateId() == castOther.getTemplateId()) || (this
				.getTemplateId() != null
				&& castOther.getTemplateId() != null && this.getTemplateId()
				.equals(castOther.getTemplateId())))
				&& ((this.getSectionCode() == castOther.getSectionCode()) || (this
						.getSectionCode() != null
						&& castOther.getSectionCode() != null && this
						.getSectionCode().equals(castOther.getSectionCode())))
				&& ((this.getSeqNbr() == castOther.getSeqNbr()) || (this
						.getSeqNbr() != null
						&& castOther.getSeqNbr() != null && this.getSeqNbr()
						.equals(castOther.getSeqNbr())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getTemplateId() == null ? 0 : this.getTemplateId()
						.hashCode());
		result = 37
				* result
				+ (getSectionCode() == null ? 0 : this.getSectionCode()
						.hashCode());
		result = 37 * result
				+ (getSeqNbr() == null ? 0 : this.getSeqNbr().hashCode());
		return result;
	}

}