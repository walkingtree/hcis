/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.util.Date;

/**
 * @author Rohit
 *
 */
public class PatientInfoSummaryBM {
	final static long serialVersionUID = 200904210230L;
	
	private PersonalDetailsBM personalDetailsBM;
	private ContactDetailsBM contactDetailsBM; //current address
	private Date lastVisited;

	
	public PatientInfoSummaryBM() {
	}

	/**
	 * @return the personalDetailsBM
	 */
	public PersonalDetailsBM getPersonalDetailsBM() {
		return personalDetailsBM;
	}

	/**
	 * @param personalDetailsBM the personalDetailsBM to set
	 */
	public void setPersonalDetailsBM(PersonalDetailsBM personalDetailsBM) {
		this.personalDetailsBM = personalDetailsBM;
	}

	/**
	 * @return the lastVisited
	 */
	public Date getLastVisited() {
		return lastVisited;
	}

	/**
	 * @param lastVisited the lastVisited to set
	 */
	public void setLastVisited(Date lastVisited) {
		this.lastVisited = lastVisited;
	}

	/**
	 * @return the contactDetailsBM
	 */
	public ContactDetailsBM getContactDetailsBM() {
		return contactDetailsBM;
	}

	/**
	 * @param contactDetailsBM the contactDetailsBM to set
	 */
	public void setContactDetailsBM(ContactDetailsBM contactDetailsBM) {
		this.contactDetailsBM = contactDetailsBM;
	}
	
}
