package com.wtc.hcis.ip.da;

/**
 * OtStatusChecklistAssoId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtStatusChecklistAssoId implements java.io.Serializable {

	// Fields

	private String statusCode;
	private Integer checkListId;

	// Constructors

	/** default constructor */
	public OtStatusChecklistAssoId() {
	}

	/** full constructor */
	public OtStatusChecklistAssoId(String statusCode, Integer checkListId) {
		this.statusCode = statusCode;
		this.checkListId = checkListId;
	}

	// Property accessors

	public String getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getCheckListId() {
		return this.checkListId;
	}

	public void setCheckListId(Integer checkListId) {
		this.checkListId = checkListId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OtStatusChecklistAssoId))
			return false;
		OtStatusChecklistAssoId castOther = (OtStatusChecklistAssoId) other;

		return ((this.getStatusCode() == castOther.getStatusCode()) || (this
				.getStatusCode() != null
				&& castOther.getStatusCode() != null && this.getStatusCode()
				.equals(castOther.getStatusCode())))
				&& ((this.getCheckListId() == castOther.getCheckListId()) || (this
						.getCheckListId() != null
						&& castOther.getCheckListId() != null && this
						.getCheckListId().equals(castOther.getCheckListId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getStatusCode() == null ? 0 : this.getStatusCode()
						.hashCode());
		result = 37
				* result
				+ (getCheckListId() == null ? 0 : this.getCheckListId()
						.hashCode());
		return result;
	}

}