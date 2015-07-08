package com.wtc.hcis.da;

/**
 * PrescriptionMedicineAssocId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PrescriptionMedicineAssocId implements java.io.Serializable {

	// Fields

	private Integer prescriptionNumber;
	private String medicineCode;

	// Constructors

	/** default constructor */
	public PrescriptionMedicineAssocId() {
	}

	/** full constructor */
	public PrescriptionMedicineAssocId(Integer prescriptionNumber,
			String medicineCode) {
		this.prescriptionNumber = prescriptionNumber;
		this.medicineCode = medicineCode;
	}

	// Property accessors

	public Integer getPrescriptionNumber() {
		return this.prescriptionNumber;
	}

	public void setPrescriptionNumber(Integer prescriptionNumber) {
		this.prescriptionNumber = prescriptionNumber;
	}

	public String getMedicineCode() {
		return this.medicineCode;
	}

	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PrescriptionMedicineAssocId))
			return false;
		PrescriptionMedicineAssocId castOther = (PrescriptionMedicineAssocId) other;

		return ((this.getPrescriptionNumber() == castOther
				.getPrescriptionNumber()) || (this.getPrescriptionNumber() != null
				&& castOther.getPrescriptionNumber() != null && this
				.getPrescriptionNumber().equals(
						castOther.getPrescriptionNumber())))
				&& ((this.getMedicineCode() == castOther.getMedicineCode()) || (this
						.getMedicineCode() != null
						&& castOther.getMedicineCode() != null && this
						.getMedicineCode().equals(castOther.getMedicineCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getPrescriptionNumber() == null ? 0 : this
						.getPrescriptionNumber().hashCode());
		result = 37
				* result
				+ (getMedicineCode() == null ? 0 : this.getMedicineCode()
						.hashCode());
		return result;
	}

}