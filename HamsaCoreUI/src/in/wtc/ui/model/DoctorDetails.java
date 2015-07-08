/**
 * 
 */
package in.wtc.ui.model;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.DoctorDetailBM;
import in.wtc.hcis.bm.base.DoctorEspecialtyBM;

/**
 * @author Vinay Kurudi
 *
 */
public class DoctorDetails {
	private Integer doctorId;
	private CodeAndDescription saluationCode;
	private String firstName;
	private String middleName;
	private String lastName;
	private Boolean active;
	private DoctorDetailBM doctorDetail;
	private DoctorEspecialtyBM doctorEspecialty;
	private ContactDetailsBM currentContactDetail;
	private ContactDetailsBM permanentContactDetail;
	private ContactDetailsBM emergencyContactDetail;
	/**
	 * @return the doctorId
	 */
	public Integer getDoctorId() {
		return doctorId;
	}
	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * @return the saluationCode
	 */
	public CodeAndDescription getSaluationCode() {
		return saluationCode;
	}
	/**
	 * @param saluationCode the saluationCode to set
	 */
	public void setSaluationCode(CodeAndDescription saluationCode) {
		this.saluationCode = saluationCode;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the active
	 */
	public Boolean getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
	/**
	 * @return the doctorDetail
	 */
	public DoctorDetailBM getDoctorDetail() {
		return doctorDetail;
	}
	/**
	 * @param doctorDetail the doctorDetail to set
	 */
	public void setDoctorDetail(DoctorDetailBM doctorDetail) {
		this.doctorDetail = doctorDetail;
	}
	/**
	 * @return the doctorEspecialty
	 */
	public DoctorEspecialtyBM getDoctorEspecialty() {
		return doctorEspecialty;
	}
	/**
	 * @param doctorEspecialty the doctorEspecialty to set
	 */
	public void setDoctorEspecialty(DoctorEspecialtyBM doctorEspecialty) {
		this.doctorEspecialty = doctorEspecialty;
	}
	/**
	 * @return the currentContactDetail
	 */
	public ContactDetailsBM getCurrentContactDetail() {
		return currentContactDetail;
	}
	/**
	 * @param currentContactDetail the currentContactDetail to set
	 */
	public void setCurrentContactDetail(ContactDetailsBM currentContactDetail) {
		this.currentContactDetail = currentContactDetail;
	}
	/**
	 * @return the permanentContactDetail
	 */
	public ContactDetailsBM getPermanentContactDetail() {
		return permanentContactDetail;
	}
	/**
	 * @param permanentContactDetail the permanentContactDetail to set
	 */
	public void setPermanentContactDetail(ContactDetailsBM permanentContactDetail) {
		this.permanentContactDetail = permanentContactDetail;
	}
	/**
	 * @return the emergencyContactDetail
	 */
	public ContactDetailsBM getEmergencyContactDetail() {
		return emergencyContactDetail;
	}
	/**
	 * @param emergencyContactDetail the emergencyContactDetail to set
	 */
	public void setEmergencyContactDetail(ContactDetailsBM emergencyContactDetail) {
		this.emergencyContactDetail = emergencyContactDetail;
	}
	/**
	 * 
	 */
	public DoctorDetails() {
		super();
	}

}
