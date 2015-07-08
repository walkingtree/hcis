package com.wtc.hcis.da;

/**
 * PrescriptionMedicineAssoc entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PrescriptionMedicineAssoc implements java.io.Serializable {

	// Fields

	private PrescriptionMedicineAssocId id;
	private Integer version;
	private Medicines medicines;
	private Prescription prescription;
	private String dosage;
	private String repeats;
	private String remarks;

	// Constructors

	/** default constructor */
	public PrescriptionMedicineAssoc() {
	}

	/** minimal constructor */
	public PrescriptionMedicineAssoc(PrescriptionMedicineAssocId id,
			Medicines medicines, Prescription prescription) {
		this.id = id;
		this.medicines = medicines;
		this.prescription = prescription;
	}

	/** full constructor */
	public PrescriptionMedicineAssoc(PrescriptionMedicineAssocId id,
			Medicines medicines, Prescription prescription, String dosage,
			String repeats, String remarks) {
		this.id = id;
		this.medicines = medicines;
		this.prescription = prescription;
		this.dosage = dosage;
		this.repeats = repeats;
		this.remarks = remarks;
	}

	// Property accessors

	public PrescriptionMedicineAssocId getId() {
		return this.id;
	}

	public void setId(PrescriptionMedicineAssocId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Medicines getMedicines() {
		return this.medicines;
	}

	public void setMedicines(Medicines medicines) {
		this.medicines = medicines;
	}

	public Prescription getPrescription() {
		return this.prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public String getDosage() {
		return this.dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getRepeats() {
		return this.repeats;
	}

	public void setRepeats(String repeats) {
		this.repeats = repeats;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}