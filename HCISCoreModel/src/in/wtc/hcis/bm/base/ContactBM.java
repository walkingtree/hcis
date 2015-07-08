package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ContactBM implements Serializable {
	Boolean isEmergencyPriContactSameAsCurrent;
	Boolean isPermanentContactSameAsCurrent;
	Boolean isEmergencySecContactSameAsCurrent;
	
	List<ContactDetailsBM> contactDetailList = new ArrayList<ContactDetailsBM>(0);

	public ContactBM() {
		this.isEmergencyPriContactSameAsCurrent = Boolean.FALSE;
		this.isEmergencySecContactSameAsCurrent = Boolean.FALSE;
		this.isPermanentContactSameAsCurrent = Boolean.FALSE;
	}
	
	public Boolean getIsPermanentContactSameAsCurrent() {
		return isPermanentContactSameAsCurrent;
	}

	public void setIsPermanentContactSameAsCurrent(
			Boolean isPermanentContactSameAsCurrent) {
		this.isPermanentContactSameAsCurrent = isPermanentContactSameAsCurrent;
	}

	public List<ContactDetailsBM> getContactDetailList() {
		return contactDetailList;
	}

	public void setContactDetailList(List<ContactDetailsBM> contactDetailList) {
		this.contactDetailList = contactDetailList;
	}

	public Boolean getIsEmergencyPriContactSameAsCurrent() {
		return isEmergencyPriContactSameAsCurrent;
	}

	public void setIsEmergencyPriContactSameAsCurrent(
			Boolean isEmergencyPriContactSameAsCurrent) {
		this.isEmergencyPriContactSameAsCurrent = isEmergencyPriContactSameAsCurrent;
	}

	public Boolean getIsEmergencySecContactSameAsCurrent() {
		return isEmergencySecContactSameAsCurrent;
	}

	public void setIsEmergencySecContactSameAsCurrent(
			Boolean isEmergencySecContactSameAsCurrent) {
		this.isEmergencySecContactSameAsCurrent = isEmergencySecContactSameAsCurrent;
	}
}
