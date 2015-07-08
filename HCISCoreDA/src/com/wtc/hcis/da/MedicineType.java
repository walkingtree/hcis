package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * MedicineType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MedicineType implements java.io.Serializable {

	// Fields

	private String medicineTypeCode;
	private String description;
	private Short active;
	private Set medicineses = new HashSet(0);

	// Constructors

	/** default constructor */
	public MedicineType() {
	}

	/** full constructor */
	public MedicineType(String description, Short active, Set medicineses) {
		this.description = description;
		this.active = active;
		this.medicineses = medicineses;
	}

	// Property accessors

	public String getMedicineTypeCode() {
		return this.medicineTypeCode;
	}

	public void setMedicineTypeCode(String medicineTypeCode) {
		this.medicineTypeCode = medicineTypeCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getActive() {
		return this.active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

	public Set getMedicineses() {
		return this.medicineses;
	}

	public void setMedicineses(Set medicineses) {
		this.medicineses = medicineses;
	}

}