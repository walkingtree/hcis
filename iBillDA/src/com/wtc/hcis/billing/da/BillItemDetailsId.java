package com.wtc.hcis.billing.da;

/**
 * BillItemDetailsId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BillItemDetailsId implements java.io.Serializable {

	// Fields

	private Long billNumber;
	private String processName;
	private String subProcessName;
	private Integer itemSequenceNbr;

	// Constructors

	/** default constructor */
	public BillItemDetailsId() {
	}

	/** full constructor */
	public BillItemDetailsId(Long billNumber, String processName,
			String subProcessName, Integer itemSequenceNbr) {
		this.billNumber = billNumber;
		this.processName = processName;
		this.subProcessName = subProcessName;
		this.itemSequenceNbr = itemSequenceNbr;
	}

	// Property accessors

	public Long getBillNumber() {
		return this.billNumber;
	}

	public void setBillNumber(Long billNumber) {
		this.billNumber = billNumber;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getSubProcessName() {
		return this.subProcessName;
	}

	public void setSubProcessName(String subProcessName) {
		this.subProcessName = subProcessName;
	}

	public Integer getItemSequenceNbr() {
		return this.itemSequenceNbr;
	}

	public void setItemSequenceNbr(Integer itemSequenceNbr) {
		this.itemSequenceNbr = itemSequenceNbr;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BillItemDetailsId))
			return false;
		BillItemDetailsId castOther = (BillItemDetailsId) other;

		return ((this.getBillNumber() == castOther.getBillNumber()) || (this
				.getBillNumber() != null
				&& castOther.getBillNumber() != null && this.getBillNumber()
				.equals(castOther.getBillNumber())))
				&& ((this.getProcessName() == castOther.getProcessName()) || (this
						.getProcessName() != null
						&& castOther.getProcessName() != null && this
						.getProcessName().equals(castOther.getProcessName())))
				&& ((this.getSubProcessName() == castOther.getSubProcessName()) || (this
						.getSubProcessName() != null
						&& castOther.getSubProcessName() != null && this
						.getSubProcessName().equals(
								castOther.getSubProcessName())))
				&& ((this.getItemSequenceNbr() == castOther
						.getItemSequenceNbr()) || (this.getItemSequenceNbr() != null
						&& castOther.getItemSequenceNbr() != null && this
						.getItemSequenceNbr().equals(
								castOther.getItemSequenceNbr())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getBillNumber() == null ? 0 : this.getBillNumber()
						.hashCode());
		result = 37
				* result
				+ (getProcessName() == null ? 0 : this.getProcessName()
						.hashCode());
		result = 37
				* result
				+ (getSubProcessName() == null ? 0 : this.getSubProcessName()
						.hashCode());
		result = 37
				* result
				+ (getItemSequenceNbr() == null ? 0 : this.getItemSequenceNbr()
						.hashCode());
		return result;
	}

}