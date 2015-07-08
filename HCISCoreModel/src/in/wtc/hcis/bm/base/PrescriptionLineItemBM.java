/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sandeep Kumar
 *
 */
public class PrescriptionLineItemBM implements Cloneable, Serializable {
	private String	medicineCode;
	private Date 	prescriptionDate;
	private String 	dosage;
	private String 	repeats;
	private String 	remarks;
	private String  strength;
	private CodeAndDescription 	form;
	
	public PrescriptionLineItemBM() {
	}
	public String getMedicineCode() {
		return medicineCode;
	}
	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}
	public Date getPrescriptionDate() {
		return prescriptionDate;
	}
	public void setPrescriptionDate(Date prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getRepeats() {
		return repeats;
	}
	public void setRepeats(String repeats) {
		this.repeats = repeats;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public CodeAndDescription getForm() {
		return form;
	}
	public void setForm(CodeAndDescription form) {
		this.form = form;
	}
}
