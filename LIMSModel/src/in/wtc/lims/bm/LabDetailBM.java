/**

 * 
 */
package in.wtc.lims.bm;


import java.io.Serializable;
import java.util.Date;



/**
 * @author Asha
 *
 */
public class LabDetailBM implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String labId;
	private String labName;
	private CodeAndDescription labType;
	private String businessName;
	private String branchName;
	private CodeAndDescription hospital;
	private String directionFromKnownPlace;
	private String labOperatorID;
	private String createdBy;
	private String userId;
	private Date createdDtm;
	private String modifiedBy;
	private Date modifiedDtm;
	private ContactDetailsBM contactDetail;
	
	
	public LabDetailBM() {
	}


	public String getLabId() {
		return labId;
	}


	public void setLabId(String labId) {
		this.labId = labId;
	}


	public String getLabName() {
		return labName;
	}


	public void setLabName(String labName) {
		this.labName = labName;
	}

	public CodeAndDescription getLabType() {
		return labType;
	}


	public void setLabType(CodeAndDescription labType) {
		this.labType = labType;
	}


	public String getBusinessName() {
		return businessName;
	}


	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}


	public String getBranchName() {
		return branchName;
	}


	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}


	public CodeAndDescription getHospital() {
		return hospital;
	}


	public void setHospital(CodeAndDescription hospital) {
		this.hospital = hospital;
	}


	public String getDirectionFromKnownPlace() {
		return directionFromKnownPlace;
	}


	public void setDirectionFromKnownPlace(String directionFromKnownPlace) {
		this.directionFromKnownPlace = directionFromKnownPlace;
	}


	public String getLabOperatorID() {
		return labOperatorID;
	}


	public void setLabOperatorID(String labOperatorID) {
		this.labOperatorID = labOperatorID;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Date getCreatedDtm() {
		return createdDtm;
	}


	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public Date getModifiedDtm() {
		return modifiedDtm;
	}


	public void setModifiedDtm(Date modifiedDtm) {
		this.modifiedDtm = modifiedDtm;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public ContactDetailsBM getContactDetail() {
		return contactDetail;
	}


	public void setContactDetail(ContactDetailsBM contactDetail) {
		this.contactDetail = contactDetail;
	}


	

	


	
	
	
}
