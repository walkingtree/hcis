package in.wtc.hcis.bm.base;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class DoctorBM implements java.io.Serializable {

	// Fields

	private Integer doctorId;
	private CodeAndDescription saluationCode;
	private String firstName;
	private String middleName;
	private String lastName;
	private Boolean active;
	private DoctorDetailBM doctorDetail;
	private List<DoctorEspecialtyBM> doctorEspecialtyList;
	private List<ContactDetailsBM> contactDetailList = new ArrayList<ContactDetailsBM>(0);

	// Constructors

	/** default constructor */
	public DoctorBM() {
	}

	/** minimal constructor */
	public DoctorBM(Integer doctorId) {
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

	public DoctorDetailBM getDoctorDetail() {
		return doctorDetail;
	}

	public void setDoctorDetail(DoctorDetailBM doctorDetail) {
		this.doctorDetail = doctorDetail;
	}

	public CodeAndDescription getSaluationCode() {
		return saluationCode;
	}

	public void setSaluationCode(CodeAndDescription saluationCode) {
		this.saluationCode = saluationCode;
	}

	public List<ContactDetailsBM> getContactDetailList() {
		return contactDetailList;
	}

	public void setContactDetailList(List<ContactDetailsBM> contactDetailList) {
		this.contactDetailList = contactDetailList;
	}

	public List<DoctorEspecialtyBM> getDoctorEspecialtyList() {
		return doctorEspecialtyList;
	}

	public void setDoctorEspecialtyList(
			List<DoctorEspecialtyBM> doctorEspecialtyList) {
		this.doctorEspecialtyList = doctorEspecialtyList;
	}

}