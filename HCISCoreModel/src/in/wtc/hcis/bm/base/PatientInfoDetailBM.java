/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class PatientInfoDetailBM implements Serializable {

	private PersonalDetailsBM personalDetailsBM;
	private PersonalDetailsAdditionalBM personalDetailsAdditionalBM;
	private ContactBM contacts;
	private List<PatientAllergiesBM> patientAllergiesBMList;
	private List<PatientImmunizationsBM> patientImmunizationsBMList; 
	private OtherDetailsBM otherDetailsBM;
	
	private String createdBy;
	private String modifiedBy;
	
	public PatientInfoDetailBM() {
		super();
	}

	public PersonalDetailsBM getPersonalDetailsBM() {
		return personalDetailsBM;
	}

	public void setPersonalDetailsBM(PersonalDetailsBM personalDetailsBM) {
		this.personalDetailsBM = personalDetailsBM;
	}

	public PersonalDetailsAdditionalBM getPersonalDetailsAdditionalBM() {
		return personalDetailsAdditionalBM;
	}

	public void setPersonalDetailsAdditionalBM(
			PersonalDetailsAdditionalBM personalDetailsAdditionalBM) {
		this.personalDetailsAdditionalBM = personalDetailsAdditionalBM;
	}

	public ContactBM getContacts() {
		return contacts;
	}

	public void setContacts(ContactBM contacts) {
		this.contacts = contacts;
	}

	public List<PatientAllergiesBM> getPatientAllergiesBMList() {
		return patientAllergiesBMList;
	}

	public void setPatientAllergiesBMList(List<PatientAllergiesBM> patientAllergiesBMList) {
		this.patientAllergiesBMList = patientAllergiesBMList;
	}

	public OtherDetailsBM getOtherDetailsBM() {
		return otherDetailsBM;
	}

	public void setOtherDetailsBM(OtherDetailsBM otherDetailsBM) {
		this.otherDetailsBM = otherDetailsBM;
	}

	public List<PatientImmunizationsBM> getPatientImmunizationsBMList() {
		return patientImmunizationsBMList;
	}

	public void setPatientImmunizationsBMList(
			List<PatientImmunizationsBM> patientImmunizationsBMList) {
		this.patientImmunizationsBMList = patientImmunizationsBMList;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
