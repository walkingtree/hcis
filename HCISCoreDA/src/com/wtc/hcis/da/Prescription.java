package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Prescription entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Prescription implements java.io.Serializable {

	// Fields

	private Integer prescriptionNumber;
	private Integer version;
	private Integer srcRefNumber;
	private Date prescriptionDate;
	private String prescriptionText;
	private Set prescriptionMedicineAssocs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Prescription() {
	}

	/** minimal constructor */
	public Prescription(Integer srcRefNumber) {
		this.srcRefNumber = srcRefNumber;
	}

	/** full constructor */
	public Prescription(Integer srcRefNumber, Date prescriptionDate,
			String prescriptionText, Set prescriptionMedicineAssocs) {
		this.srcRefNumber = srcRefNumber;
		this.prescriptionDate = prescriptionDate;
		this.prescriptionText = prescriptionText;
		this.prescriptionMedicineAssocs = prescriptionMedicineAssocs;
	}

	// Property accessors

	public Integer getPrescriptionNumber() {
		return this.prescriptionNumber;
	}

	public void setPrescriptionNumber(Integer prescriptionNumber) {
		this.prescriptionNumber = prescriptionNumber;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getSrcRefNumber() {
		return this.srcRefNumber;
	}

	public void setSrcRefNumber(Integer srcRefNumber) {
		this.srcRefNumber = srcRefNumber;
	}

	public Date getPrescriptionDate() {
		return this.prescriptionDate;
	}

	public void setPrescriptionDate(Date prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}

	public String getPrescriptionText() {
		return this.prescriptionText;
	}

	public void setPrescriptionText(String prescriptionText) {
		this.prescriptionText = prescriptionText;
	}

	public Set getPrescriptionMedicineAssocs() {
		return this.prescriptionMedicineAssocs;
	}

	public void setPrescriptionMedicineAssocs(Set prescriptionMedicineAssocs) {
		this.prescriptionMedicineAssocs = prescriptionMedicineAssocs;
	}

}