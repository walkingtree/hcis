/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;

public class PatientImmunizationsBM implements Serializable {
	static final long serialVersionUID = 200904150928L;
	
	private String immunizationDescrption;
	private String immunizationCode;
	private Date vaccinationDate;
	

	public PatientImmunizationsBM() {
		// TODO Auto-generated constructor stub
	}


	public PatientImmunizationsBM(String immunizationDescrption,
			String immunizationCode, Date vaccinationDate) {
		super();
		this.immunizationDescrption = immunizationDescrption;
		this.immunizationCode = immunizationCode;
		this.vaccinationDate = vaccinationDate;
	}


	public String getImmunizationDescrption() {
		return immunizationDescrption;
	}


	public void setImmunizationDescrption(String immunizationDescrption) {
		this.immunizationDescrption = immunizationDescrption;
	}


	public String getImmunizationCode() {
		return immunizationCode;
	}


	public void setImmunizationCode(String immunizationCode) {
		this.immunizationCode = immunizationCode;
	}


	public Date getVaccinationDate() {
		return vaccinationDate;
	}


	public void setVaccinationDate(Date vaccinationDate) {
		this.vaccinationDate = vaccinationDate;
	}
	
		
}
