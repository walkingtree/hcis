/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Rohit
 *
 */
public class PatientLiteBM implements Cloneable, Serializable 
{
	static final long serialVersionUID = 200905271121L;
	
	private Integer 			patientId;
	private String 				firstName;
	private String 				middleName;
	private String 				lastName;
	private String              fullName;
	private Date 				dateOfBirth;
	private CodeAndDescription 	title;
	private CodeAndDescription 	gender;
	private ContactDetailsBM 	contactDetailsBM;
	private Integer businessPartnerId;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public CodeAndDescription getGender() {
		return gender;
	}
	public void setGender(CodeAndDescription gender) {
		this.gender = gender;
	}
	public CodeAndDescription getTitle() {
		return title;
	}
	public void setTitle(CodeAndDescription title) {
		this.title = title;
	}
	public ContactDetailsBM getContactDetailsBM() {
		return contactDetailsBM;
	}
	public void setContactDetailsBM(ContactDetailsBM contactDetailsBM) {
		this.contactDetailsBM = contactDetailsBM;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public PatientLiteBM() {
		super();
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getBusinessPartnerId() {
		return businessPartnerId;
	}
	public void setBusinessPartnerId(Integer businessPartnerId) {
		this.businessPartnerId = businessPartnerId;
	}
	
}
