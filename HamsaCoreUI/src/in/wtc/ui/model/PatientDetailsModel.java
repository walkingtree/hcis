/**
 * 
 */
package in.wtc.ui.model;

import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.OtherDetailsBM;
import in.wtc.hcis.bm.base.PersonalDetailsAdditionalBM;
import in.wtc.hcis.bm.base.PersonalDetailsBM;
import in.wtc.hcis.bm.base.SponsorsBM;

/**
 * @author kamalr
 *
 */
public class PatientDetailsModel {

//	private SponsorsBM sponsorsBM;
	private PersonalDetailsAdditionalBM personalDetailsAdditionalBM;
	private PersonalDetailsBM personalDetailsBM;
	private ContactDetailsBM emergencyPrimaryBM;
	private ContactDetailsBM emergencySecondaryBM;
	private ContactDetailsBM currentBM;
	private ContactDetailsBM permanentBM;
//	private OtherDetailsBM otherDetailsBM;
//	public OtherDetailsBM getOtherDetailsBM() {
//		return otherDetailsBM;
//	}
//	public void setOtherDetailsBM(OtherDetailsBM otherDetailsBM) {
//		this.otherDetailsBM = otherDetailsBM;
//	}
	public PatientDetailsModel() {
		super();
	}
//	public SponsorsBM getSponsorsBM() {
//		return sponsorsBM;
//	}
//	public void setSponsorsBM(SponsorsBM sponsorsBM) {
//		this.sponsorsBM = sponsorsBM;
//	}
	public PersonalDetailsAdditionalBM getPersonalDetailsAdditionalBM() {
		return personalDetailsAdditionalBM;
	}
	public void setPersonalDetailsAdditionalBM(
			PersonalDetailsAdditionalBM personalDetailsAdditionalBM) {
		this.personalDetailsAdditionalBM = personalDetailsAdditionalBM;
	}
	public PersonalDetailsBM getPersonalDetailsBM() {
		return personalDetailsBM;
	}
	public void setPersonalDetailsBM(PersonalDetailsBM personalDetailsBM) {
		this.personalDetailsBM = personalDetailsBM;
	}
	public ContactDetailsBM getEmergencyPrimaryBM() {
		return emergencyPrimaryBM;
	}
	public void setEmergencyPrimaryBM(ContactDetailsBM emergencyPrimaryBM) {
		this.emergencyPrimaryBM = emergencyPrimaryBM;
	}
	public ContactDetailsBM getEmergencySecondaryBM() {
		return emergencySecondaryBM;
	}
	public void setEmergencySecondaryBM(ContactDetailsBM emergencySecondaryBM) {
		this.emergencySecondaryBM = emergencySecondaryBM;
	}
	public ContactDetailsBM getCurrentBM() {
		return currentBM;
	}
	public void setCurrentBM(ContactDetailsBM currentBM) {
		this.currentBM = currentBM;
	}
	public ContactDetailsBM getPermanentBM() {
		return permanentBM;
	}
	public void setPermanentBM(ContactDetailsBM permanentBM) {
		this.permanentBM = permanentBM;
	}
}
