/**
 * 
 */
package in.wtc.ui.model;

import in.wtc.hcis.bm.base.CodeAndDescription;

/**
 * @author Sandeep Kumar
 *
 */
public class MedicalPrescriptionSummaryModel {
	private int serialNo;
	private String 	prescriptionNumber;
	private CodeAndDescription medicine;
	private String remarks;
	private String repeats;
	private String dosage;
	private String strength;
	private CodeAndDescription form;
	private String appointmentNumber;

	public CodeAndDescription getForm() {
		return form;
	}
	public void setForm(CodeAndDescription form) {
		this.form = form;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	
	public CodeAndDescription getMedicine() {
		return medicine;
	}
	public void setMedicine(CodeAndDescription medicine) {
		this.medicine = medicine;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRepeats() {
		return repeats;
	}
	public void setRepeats(String repeats) {
		this.repeats = repeats;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public MedicalPrescriptionSummaryModel() {
	}
	public String getPrescriptionNumber() {
		return prescriptionNumber;
	}
	public void setPrescriptionNumber(String prescriptionNumber) {
		this.prescriptionNumber = prescriptionNumber;
	}
	public String getAppointmentNumber() {
		return appointmentNumber;
	}
	public void setAppointmentNumber(String appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}
}
