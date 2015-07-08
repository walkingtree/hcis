package com.wtc.hcis.ip.da;

/**
 * DoctorOrderGroupHasTemplateId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderGroupHasTemplateId implements java.io.Serializable {

	// Fields

	private String orderGroupName;
	private String orderTemplateId;
	private Integer sequenceNbr;

	// Constructors

	/** default constructor */
	public DoctorOrderGroupHasTemplateId() {
	}

	/** full constructor */
	public DoctorOrderGroupHasTemplateId(String orderGroupName,
			String orderTemplateId, Integer sequenceNbr) {
		this.orderGroupName = orderGroupName;
		this.orderTemplateId = orderTemplateId;
		this.sequenceNbr = sequenceNbr;
	}

	// Property accessors

	public String getOrderGroupName() {
		return this.orderGroupName;
	}

	public void setOrderGroupName(String orderGroupName) {
		this.orderGroupName = orderGroupName;
	}

	public String getOrderTemplateId() {
		return this.orderTemplateId;
	}

	public void setOrderTemplateId(String orderTemplateId) {
		this.orderTemplateId = orderTemplateId;
	}

	public Integer getSequenceNbr() {
		return this.sequenceNbr;
	}

	public void setSequenceNbr(Integer sequenceNbr) {
		this.sequenceNbr = sequenceNbr;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DoctorOrderGroupHasTemplateId))
			return false;
		DoctorOrderGroupHasTemplateId castOther = (DoctorOrderGroupHasTemplateId) other;

		return ((this.getOrderGroupName() == castOther.getOrderGroupName()) || (this
				.getOrderGroupName() != null
				&& castOther.getOrderGroupName() != null && this
				.getOrderGroupName().equals(castOther.getOrderGroupName())))
				&& ((this.getOrderTemplateId() == castOther
						.getOrderTemplateId()) || (this.getOrderTemplateId() != null
						&& castOther.getOrderTemplateId() != null && this
						.getOrderTemplateId().equals(
								castOther.getOrderTemplateId())))
				&& ((this.getSequenceNbr() == castOther.getSequenceNbr()) || (this
						.getSequenceNbr() != null
						&& castOther.getSequenceNbr() != null && this
						.getSequenceNbr().equals(castOther.getSequenceNbr())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getOrderGroupName() == null ? 0 : this.getOrderGroupName()
						.hashCode());
		result = 37
				* result
				+ (getOrderTemplateId() == null ? 0 : this.getOrderTemplateId()
						.hashCode());
		result = 37
				* result
				+ (getSequenceNbr() == null ? 0 : this.getSequenceNbr()
						.hashCode());
		return result;
	}

}