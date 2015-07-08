package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * ClaimDocumentId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ClaimDocumentId implements java.io.Serializable {

	// Fields

	private Long requestSequenceNbr;
	private Date createDtm;
	private String documentName;

	// Constructors

	/** default constructor */
	public ClaimDocumentId() {
	}

	/** full constructor */
	public ClaimDocumentId(Long requestSequenceNbr, Date createDtm,
			String documentName) {
		this.requestSequenceNbr = requestSequenceNbr;
		this.createDtm = createDtm;
		this.documentName = documentName;
	}

	// Property accessors

	public Long getRequestSequenceNbr() {
		return this.requestSequenceNbr;
	}

	public void setRequestSequenceNbr(Long requestSequenceNbr) {
		this.requestSequenceNbr = requestSequenceNbr;
	}

	public Date getCreateDtm() {
		return this.createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}

	public String getDocumentName() {
		return this.documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ClaimDocumentId))
			return false;
		ClaimDocumentId castOther = (ClaimDocumentId) other;

		return ((this.getRequestSequenceNbr() == castOther
				.getRequestSequenceNbr()) || (this.getRequestSequenceNbr() != null
				&& castOther.getRequestSequenceNbr() != null && this
				.getRequestSequenceNbr().equals(
						castOther.getRequestSequenceNbr())))
				&& ((this.getCreateDtm() == castOther.getCreateDtm()) || (this
						.getCreateDtm() != null
						&& castOther.getCreateDtm() != null && this
						.getCreateDtm().equals(castOther.getCreateDtm())))
				&& ((this.getDocumentName() == castOther.getDocumentName()) || (this
						.getDocumentName() != null
						&& castOther.getDocumentName() != null && this
						.getDocumentName().equals(castOther.getDocumentName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getRequestSequenceNbr() == null ? 0 : this
						.getRequestSequenceNbr().hashCode());
		result = 37 * result
				+ (getCreateDtm() == null ? 0 : this.getCreateDtm().hashCode());
		result = 37
				* result
				+ (getDocumentName() == null ? 0 : this.getDocumentName()
						.hashCode());
		return result;
	}

}