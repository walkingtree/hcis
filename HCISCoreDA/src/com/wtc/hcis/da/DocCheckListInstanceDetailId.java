package com.wtc.hcis.da;

/**
 * DocCheckListInstanceDetailId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DocCheckListInstanceDetailId implements java.io.Serializable {

	// Fields

	private Long checkListInstanceId;
	private Long checkListDetailId;

	// Constructors

	/** default constructor */
	public DocCheckListInstanceDetailId() {
	}

	/** full constructor */
	public DocCheckListInstanceDetailId(Long checkListInstanceId,
			Long checkListDetailId) {
		this.checkListInstanceId = checkListInstanceId;
		this.checkListDetailId = checkListDetailId;
	}

	// Property accessors

	public Long getCheckListInstanceId() {
		return this.checkListInstanceId;
	}

	public void setCheckListInstanceId(Long checkListInstanceId) {
		this.checkListInstanceId = checkListInstanceId;
	}

	public Long getCheckListDetailId() {
		return this.checkListDetailId;
	}

	public void setCheckListDetailId(Long checkListDetailId) {
		this.checkListDetailId = checkListDetailId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DocCheckListInstanceDetailId))
			return false;
		DocCheckListInstanceDetailId castOther = (DocCheckListInstanceDetailId) other;

		return ((this.getCheckListInstanceId() == castOther
				.getCheckListInstanceId()) || (this.getCheckListInstanceId() != null
				&& castOther.getCheckListInstanceId() != null && this
				.getCheckListInstanceId().equals(
						castOther.getCheckListInstanceId())))
				&& ((this.getCheckListDetailId() == castOther
						.getCheckListDetailId()) || (this
						.getCheckListDetailId() != null
						&& castOther.getCheckListDetailId() != null && this
						.getCheckListDetailId().equals(
								castOther.getCheckListDetailId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCheckListInstanceId() == null ? 0 : this
						.getCheckListInstanceId().hashCode());
		result = 37
				* result
				+ (getCheckListDetailId() == null ? 0 : this
						.getCheckListDetailId().hashCode());
		return result;
	}

}