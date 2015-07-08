package in.wtc.hcis.bm.base;


@SuppressWarnings("serial")
public class DoctorLiteBM implements java.io.Serializable {

	// Fields

	private Integer doctorId;
	private CodeAndDescription saluationCode;
	private String firstName;
	private String middleName;
	private String lastName;
	private Boolean active;

	// Constructors

	/** default constructor */
	public DoctorLiteBM() {
	}

	/** minimal constructor */
	public DoctorLiteBM(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public CodeAndDescription getSaluationCode() {
		return saluationCode;
	}

	public void setSaluationCode(CodeAndDescription saluationCode) {
		this.saluationCode = saluationCode;
	}

}