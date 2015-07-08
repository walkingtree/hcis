/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;

/**
 * @author Rohit
 *
 */
public class MedicineBM implements Cloneable, Serializable 
{
	static final long serialVersionUID = 200905261121L;
	
	private String 				medicineCode;
	private String 				medicineName;
	private CodeAndDescription 	brand;
	private String 				strength;
	private CodeAndDescription 	medicineType;
	private String 				maximumDosage;
	private Boolean 			active;
	public String getMedicineCode() {
		return medicineCode;
	}
	public void setMedicineCode(String medicineCode) {
		this.medicineCode = medicineCode;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public CodeAndDescription getBrand() {
		return brand;
	}
	public void setBrand(CodeAndDescription brand) {
		this.brand = brand;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public CodeAndDescription getMedicineType() {
		return medicineType;
	}
	public void setMedicineType(CodeAndDescription medicineType) {
		this.medicineType = medicineType;
	}
	public String getMaximumDosage() {
		return maximumDosage;
	}
	public void setMaximumDosage(String maximumDosage) {
		this.maximumDosage = maximumDosage;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}
