/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Ajit Kumar
 *
 */
@SuppressWarnings("serial")
public class PrescriptionBM implements Cloneable, Serializable 
{
	private String 	prescriptionNumber;
	private String 	appointmentNumber;
	private Date      appointmentDate;
	private String prescriptionText;
	private Set<PrescriptionLineItemBM> prescriptionLineItemSet = new HashSet<PrescriptionLineItemBM>(0);
	
	public PrescriptionBM() {
		
	}

	public String getPrescriptionNumber() {
		return prescriptionNumber;
	}

	public void setPrescriptionNumber(String prescriptionNumber) {
		this.prescriptionNumber = prescriptionNumber;
	}

	public Set<PrescriptionLineItemBM> getPrescriptionLineItemSet() {
		return prescriptionLineItemSet;
	}

	public void setPrescriptionLineItemSet(
			Set<PrescriptionLineItemBM> prescriptionLineItemSet) {
		this.prescriptionLineItemSet = prescriptionLineItemSet;
	}

	public String getAppointmentNumber() {
		return appointmentNumber;
	}

	public void setAppointmentNumber(String appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}

	public String getPrescriptionText() {
		return prescriptionText;
	}

	public void setPrescriptionText(String prescriptionText) {
		this.prescriptionText = prescriptionText;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
}
