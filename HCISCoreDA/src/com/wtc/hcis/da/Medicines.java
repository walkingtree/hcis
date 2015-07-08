package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * Medicines entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Medicines implements java.io.Serializable {

	// Fields

	private String medicineCode;
	private Integer version;
	private Brand brand;
	private MedicineType medicineType;
	private String medicineName;
	private String strength;
	private String maximumDosage;
	private String active;
	private Set prescriptionMedicineAssocs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Medicines() {
	}

	/** full constructor */
	public Medicines(Brand brand, MedicineType medicineType,
			String medicineName, String strength, String maximumDosage,
			String active, Set prescriptionMedicineAssocs) {
		this.brand = brand;
		this.medicineType = medicineType;
		this.medicineName = medicineName;
		this.strength = strength;
		this.maximumDosage = maximumDosage;
		this.active = active;
		this.prescriptionMedicineAssocs = prescriptionMedicineAssocs;
	}

	// Property accessors

	public String getMedicineCode() {
		return this.medicineCode;
	}

	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public MedicineType getMedicineType() {
		return this.medicineType;
	}

	public void setMedicineType(MedicineType medicineType) {
		this.medicineType = medicineType;
	}

	public String getMedicineName() {
		return this.medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getStrength() {
		return this.strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getMaximumDosage() {
		return this.maximumDosage;
	}

	public void setMaximumDosage(String maximumDosage) {
		this.maximumDosage = maximumDosage;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Set getPrescriptionMedicineAssocs() {
		return this.prescriptionMedicineAssocs;
	}

	public void setPrescriptionMedicineAssocs(Set prescriptionMedicineAssocs) {
		this.prescriptionMedicineAssocs = prescriptionMedicineAssocs;
	}

}