package in.wtc.hcis.bm.base;


@SuppressWarnings("serial")
public class DoctorSummaryBM implements java.io.Serializable {

	// Fields

	private CodeAndDescription doctor;
	private CodeAndDescription saluation;
	private CodeAndDescription gender;
	private CodeAndDescription especialtyCode;
	private CodeAndDescription departmentCode;
	private CodeAndDescription roomCode;
	private Double consultationCharge;
	private String firstName;
	private String middleName;
	private String lastName;
	private Boolean permanent;
	private String knownLanguages;

	// Constructors

	/** default constructor */
	public DoctorSummaryBM() {
	}

	public CodeAndDescription getDoctor() {
		return doctor;
	}

	public void setDoctor(CodeAndDescription doctor) {
		this.doctor = doctor;
	}

	public CodeAndDescription getSaluation() {
		return saluation;
	}

	public void setSaluation(CodeAndDescription saluation) {
		this.saluation = saluation;
	}

	public CodeAndDescription getGender() {
		return gender;
	}

	public void setGender(CodeAndDescription gender) {
		this.gender = gender;
	}

	public CodeAndDescription getEspecialtyCode() {
		return especialtyCode;
	}

	public void setEspecialtyCode(CodeAndDescription especialtyCode) {
		this.especialtyCode = especialtyCode;
	}

	public CodeAndDescription getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(CodeAndDescription departmentCode) {
		this.departmentCode = departmentCode;
	}

	public CodeAndDescription getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(CodeAndDescription roomCode) {
		this.roomCode = roomCode;
	}

	public Double getConsultationCharge() {
		return consultationCharge;
	}

	public void setConsultationCharge(Double consultationCharge) {
		this.consultationCharge = consultationCharge;
	}

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

	public Boolean getPermanent() {
		return permanent;
	}

	public void setPermanent(Boolean permanent) {
		this.permanent = permanent;
	}

	public String getKnownLanguages() {
		return knownLanguages;
	}

	public void setKnownLanguages(String knownLanguages) {
		this.knownLanguages = knownLanguages;
	}

}