package com.wtc.hcis.ip.da;

/**
 * BedBillHistoryId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedBillHistoryId implements java.io.Serializable {

	// Fields

	private Integer bedAssignmentNbr;
	private Integer billNbr;

	// Constructors

	/** default constructor */
	public BedBillHistoryId() {
	}

	/** full constructor */
	public BedBillHistoryId(Integer bedAssignmentNbr, Integer billNbr) {
		this.bedAssignmentNbr = bedAssignmentNbr;
		this.billNbr = billNbr;
	}

	// Property accessors

	public Integer getBedAssignmentNbr() {
		return this.bedAssignmentNbr;
	}

	public void setBedAssignmentNbr(Integer bedAssignmentNbr) {
		this.bedAssignmentNbr = bedAssignmentNbr;
	}

	public Integer getBillNbr() {
		return this.billNbr;
	}

	public void setBillNbr(Integer billNbr) {
		this.billNbr = billNbr;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BedBillHistoryId))
			return false;
		BedBillHistoryId castOther = (BedBillHistoryId) other;

		return ((this.getBedAssignmentNbr() == castOther.getBedAssignmentNbr()) || (this
				.getBedAssignmentNbr() != null
				&& castOther.getBedAssignmentNbr() != null && this
				.getBedAssignmentNbr().equals(castOther.getBedAssignmentNbr())))
				&& ((this.getBillNbr() == castOther.getBillNbr()) || (this
						.getBillNbr() != null
						&& castOther.getBillNbr() != null && this.getBillNbr()
						.equals(castOther.getBillNbr())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getBedAssignmentNbr() == null ? 0 : this
						.getBedAssignmentNbr().hashCode());
		result = 37 * result
				+ (getBillNbr() == null ? 0 : this.getBillNbr().hashCode());
		return result;
	}

}