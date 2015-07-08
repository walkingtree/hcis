/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Rohit
 *
 */
public class PatientAllergiesBM implements Serializable {
	static final long serialVersionUID = 200904150928L;
	
	private String allergyDescrption;
	private String allergyCode;
	private Date fromDate;
	private Date toDate;
	

	public PatientAllergiesBM() {
		// TODO Auto-generated constructor stub
	}
	
	public PatientAllergiesBM( String allergyCode, String allergyDescrption ) {
		this.allergyCode = allergyCode;
		this.allergyDescrption = allergyDescrption;
	}
	
	/**
	 * @return the allergyDescrption
	 */
	public String getAllergyDescrption() {
		return allergyDescrption;
	}

	/**
	 * @param allergyDescrption the allergyDescrption to set
	 */
	public void setAllergyDescrption(String allergyDescrption) {
		this.allergyDescrption = allergyDescrption;
	}

	/**
	 * @return the allergyCode
	 */
	public String getAllergyCode() {
		return allergyCode;
	}

	/**
	 * @param allergyCode the allergyCode to set
	 */
	public void setAllergyCode(String allergyCode) {
		this.allergyCode = allergyCode;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
		
}
