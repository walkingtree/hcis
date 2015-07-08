package com.wtc.hcis.da;

/**
 * StatusId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class StatusId implements java.io.Serializable {

	// Fields

	private Integer statusCode;
	private String statusDesc;

	// Constructors

	/** default constructor */
	public StatusId() {
	}

	/** full constructor */
	public StatusId(Integer statusCode, String statusDesc) {
		this.statusCode = statusCode;
		this.statusDesc = statusDesc;
	}

	// Property accessors

	public Integer getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDesc() {
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof StatusId))
			return false;
		StatusId castOther = (StatusId) other;

		return ((this.getStatusCode() == castOther.getStatusCode()) || (this
				.getStatusCode() != null
				&& castOther.getStatusCode() != null && this.getStatusCode()
				.equals(castOther.getStatusCode())))
				&& ((this.getStatusDesc() == castOther.getStatusDesc()) || (this
						.getStatusDesc() != null
						&& castOther.getStatusDesc() != null && this
						.getStatusDesc().equals(castOther.getStatusDesc())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getStatusCode() == null ? 0 : this.getStatusCode()
						.hashCode());
		result = 37
				* result
				+ (getStatusDesc() == null ? 0 : this.getStatusDesc()
						.hashCode());
		return result;
	}

}