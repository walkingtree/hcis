package com.wtc.hcis.ip.da;

/**
 * DoctorOrderDetailsId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderDetailsId implements java.io.Serializable {

	// Fields

	private Integer orderNbr;
	private Integer sequenceNbr;
	private Integer subSequenceNbr;

	// Constructors

	/** default constructor */
	public DoctorOrderDetailsId() {
	}

	/** full constructor */
	public DoctorOrderDetailsId(Integer orderNbr, Integer sequenceNbr,
			Integer subSequenceNbr) {
		this.orderNbr = orderNbr;
		this.sequenceNbr = sequenceNbr;
		this.subSequenceNbr = subSequenceNbr;
	}

	// Property accessors

	public Integer getOrderNbr() {
		return this.orderNbr;
	}

	public void setOrderNbr(Integer orderNbr) {
		this.orderNbr = orderNbr;
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
		if (!(other instanceof DoctorOrderDetailsId))
			return false;
		DoctorOrderDetailsId castOther = (DoctorOrderDetailsId) other;

		return ((this.getOrderNbr() == castOther.getOrderNbr()) || (this
				.getOrderNbr() != null
				&& castOther.getOrderNbr() != null && this.getOrderNbr()
				.equals(castOther.getOrderNbr())))
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

		result = 37 * result
				+ (getOrderNbr() == null ? 0 : this.getOrderNbr().hashCode());
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