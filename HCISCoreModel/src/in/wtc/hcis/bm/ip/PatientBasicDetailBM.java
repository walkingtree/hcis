/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.PatientLiteBM;

import java.io.Serializable;

/**
 * This Business Model contains information related to Patient which will be
 * frequently used while displaying any patient/admission related information.
 * This model also contains some accounting related informations.
 * 
 * @author Bhavesh
 * 
 */
public class PatientBasicDetailBM extends PatientLiteBM implements Serializable {

	String bedNumber;
	Double totalAmount;
	Double totalRemngAmount;
	Double depositAmount;
	String patientName;

	public String getBedNumber() {
		return bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalRemngAmount() {
		return totalRemngAmount;
	}

	public void setTotalRemngAmount(Double totalRemngAmount) {
		this.totalRemngAmount = totalRemngAmount;
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getPatientName() {
		StringBuffer patientName = new StringBuffer();
		if(null != getTitle()){
			patientName.append(getTitle().getDescription() + " ");
		}

		if(null != getFirstName()){
			patientName.append(getFirstName().toUpperCase() + " ");
		}

		if(null != getMiddleName()){
			patientName.append(getMiddleName().toUpperCase() + " ");
		}

		if(null != getLastName()){
			patientName.append(getLastName().toUpperCase());
		}
		
		return patientName.toString();
	}

}
