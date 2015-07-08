package com.wtc.hcis.da;

/**
 * ReferenceDataListId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ReferenceDataListId implements java.io.Serializable {

	// Fields

	private String context;
	private String attrCode;

	// Constructors

	/** default constructor */
	public ReferenceDataListId() {
	}

	/** full constructor */
	public ReferenceDataListId(String context, String attrCode) {
		this.context = context;
		this.attrCode = attrCode;
	}

	// Property accessors

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getAttrCode() {
		return this.attrCode;
	}

	public void setAttrCode(String attrCode) {
		this.attrCode = attrCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ReferenceDataListId))
			return false;
		ReferenceDataListId castOther = (ReferenceDataListId) other;

		return ((this.getContext() == castOther.getContext()) || (this
				.getContext() != null
				&& castOther.getContext() != null && this.getContext().equals(
				castOther.getContext())))
				&& ((this.getAttrCode() == castOther.getAttrCode()) || (this
						.getAttrCode() != null
						&& castOther.getAttrCode() != null && this
						.getAttrCode().equals(castOther.getAttrCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getContext() == null ? 0 : this.getContext().hashCode());
		result = 37 * result
				+ (getAttrCode() == null ? 0 : this.getAttrCode().hashCode());
		return result;
	}

}