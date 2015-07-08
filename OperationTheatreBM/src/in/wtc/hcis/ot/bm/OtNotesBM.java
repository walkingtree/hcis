/**
 * 
 */
package in.wtc.hcis.ot.bm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import in.wtc.hcis.bm.base.CodeAndDescription;


/**
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class OtNotesBM implements Serializable{

	private Long patientSurgeryId;
	private CodeAndDescription surgery;
	private CodeAndDescription operationTheatre;
	private Integer patientId;
	private String patientName;
	private Integer surgeonId;
	private String surgeonName;
	private String referenceType;
	private String referenceNumber;
	private Date surgeryDate;
	private String userId;
	
	List<OtNotesFieldsBM> otNotesFieldsBMList;

	public Long getPatientSurgeryId() {
		return patientSurgeryId;
	}

	public void setPatientSurgeryId(Long patientSurgeryId) {
		this.patientSurgeryId = patientSurgeryId;
	}

	public CodeAndDescription getSurgery() {
		return surgery;
	}

	public void setSurgery(CodeAndDescription surgery) {
		this.surgery = surgery;
	}

	public CodeAndDescription getOperationTheatre() {
		return operationTheatre;
	}

	public void setOperationTheatre(CodeAndDescription operationTheatre) {
		this.operationTheatre = operationTheatre;
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

	public Integer getSurgeonId() {
		return surgeonId;
	}

	public void setSurgeonId(Integer surgeonId) {
		this.surgeonId = surgeonId;
	}

	public String getSurgeonName() {
		return surgeonName;
	}

	public void setSurgeonName(String surgeonName) {
		this.surgeonName = surgeonName;
	}

	public String getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Date getSurgeryDate() {
		return surgeryDate;
	}

	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}

	public List<OtNotesFieldsBM> getOtNotesFieldsBMList() {
		return otNotesFieldsBMList;
	}

	public void setOtNotesFieldsBMList(List<OtNotesFieldsBM> otNotesFieldsBMList) {
		this.otNotesFieldsBMList = otNotesFieldsBMList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
