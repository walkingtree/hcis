/**
 * 
 */
package in.wtc.ui.model;

import java.util.Date;

/**
 * @author Vinay Kurudi
 *
 */
public class PatientSummary {

	/**
	 * 
	 */
	private String firstName;
	private String middleName;
	private String lastName;
	private String name;
	private String age;
	private Date lastVisitedDate;
	private Date registrationDate;
	private String registrationStatus;
	private String registrationType;
	private String registrationTypeCode;
	private String others;
	private String patientId;

	public PatientSummary() {
		// TODO Auto-generated constructor stub
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Date getLastVisitedDate() {
		return lastVisitedDate;
	}
	public void setLastVisitedDate(Date lastVisitedDate) {
		this.lastVisitedDate = lastVisitedDate;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getRegistrationStatus() {
		return registrationStatus;
	}
	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
	public String getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public PatientSummary(String firstName, String age, Date lastVisitedDate,
			Date registrationDate, String registrationStatus,
			String registrationType, String others, String patientId) {
		super();
		this.firstName = firstName;
		this.age = age;
		this.lastVisitedDate = lastVisitedDate;
		this.registrationDate = registrationDate;
		this.registrationStatus = registrationStatus;
		this.registrationType = registrationType;
		this.others = others;
		this.patientId = patientId;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegistrationTypeCode() {
		return registrationTypeCode;
	}

	public void setRegistrationTypeCode(String registrationTypeCode) {
		this.registrationTypeCode = registrationTypeCode;
	}

}
