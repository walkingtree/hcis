/**
 * 
 */
package in.wtc.hcis.ot.bm;

import java.util.Date;

/**
 * @author Bhavesh
 *
 */
public class OTSurgeonAvaibilityBM {

	private String otId;
	private String otName;
	private Integer doctorId;
	private String doctorName;
	
	private String availableOTSlot;  //used for grouping 
	private Date   availableFromDtm;
	private Date   availableToDTM;
	
	
	public String getOtId() {
		return otId;
	}
	public void setOtId(String otId) {
		this.otId = otId;
	}
	public String getOtName() {
		return otName;
	}
	public void setOtName(String otName) {
		this.otName = otName;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getAvailableOTSlot() {
		return availableOTSlot;
	}
	public void setAvailableOTSlot(String availableOTSlot) {
		this.availableOTSlot = availableOTSlot;
	}
	public Date getAvailableFromDtm() {
		return availableFromDtm;
	}
	public void setAvailableFromDtm(Date availableFromDtm) {
		this.availableFromDtm = availableFromDtm;
	}
	public Date getAvailableToDTM() {
		return availableToDTM;
	}
	public void setAvailableToDTM(Date availableToDTM) {
		this.availableToDTM = availableToDTM;
	}
	
}
