/**
 * 
 */
package in.wtc.lims.bm;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Bhavesh
 *
 */
public class PatientTestDetailBM {

	/**
	 * 
	 */
	public PatientTestDetailBM() {
		// TODO Auto-generated constructor stub
	}

	private String patientTestId;
	private CodeAndDescription labTest;
	private CodeAndDescription techniqueReagent;//Test can be assign for different technique if Doctor suggests  
	private Integer patientId;
	private String patientName;
	private Integer doctorId;
	private String doctorName;
	private Date testPerformDtm;
	private CodeAndDescription status;
	private Date approveDate;
	private String approverName;
	private String investigatorId;
	private Date specimenCollectionDtm;
	private String remarks;
	private Integer createdDateDim;
	private String createdBy;
	private Date createdDtm;
	private String reportCollectedBy;
	private Date reportCollectedDtm;
	
	//Test attribute and its corresponding values
	private List<PatientTestAttrValueBM> patientTestAttrValueBMList;

	public String getPatientTestId() {
		return patientTestId;
	}

	public void setPatientTestId(String patientTestId) {
		this.patientTestId = patientTestId;
	}

	public CodeAndDescription getLabTest() {
		return labTest;
	}

	public void setLabTest(CodeAndDescription labTest) {
		this.labTest = labTest;
	}

	public CodeAndDescription getTechniqueReagent() {
		return techniqueReagent;
	}

	public void setTechniqueReagent(CodeAndDescription techniqueReagent) {
		this.techniqueReagent = techniqueReagent;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Date getTestPerformDtm() {
		return testPerformDtm;
	}

	public void setTestPerformDtm(Date testPerformDtm) {
		this.testPerformDtm = testPerformDtm;
	}

	public CodeAndDescription getStatus() {
		return status;
	}

	public void setStatus(CodeAndDescription status) {
		this.status = status;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getInvestigatorId() {
		return investigatorId;
	}

	public void setInvestigatorId(String investigatorId) {
		this.investigatorId = investigatorId;
	}

	public Date getSpecimenCollectionDtm() {
		return specimenCollectionDtm;
	}

	public void setSpecimenCollectionDtm(Date specimenCollectionDtm) {
		this.specimenCollectionDtm = specimenCollectionDtm;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getCreatedDateDim() {
		return createdDateDim;
	}

	public void setCreatedDateDim(Integer createdDateDim) {
		this.createdDateDim = createdDateDim;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDtm() {
		return createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	public String getReportCollectedBy() {
		return reportCollectedBy;
	}

	public void setReportCollectedBy(String reportCollectedBy) {
		this.reportCollectedBy = reportCollectedBy;
	}

	public Date getReportCollectedDtm() {
		return reportCollectedDtm;
	}

	public void setReportCollectedDtm(Date reportCollectedDtm) {
		this.reportCollectedDtm = reportCollectedDtm;
	}

	public List<PatientTestAttrValueBM> getPatientTestAttrValueBMList() {
		return patientTestAttrValueBMList;
	}

	public void setPatientTestAttrValueBMList(
			List<PatientTestAttrValueBM> patientTestAttrValueBMList) {
		this.patientTestAttrValueBMList = patientTestAttrValueBMList;
	}
	
	
	
		
}
