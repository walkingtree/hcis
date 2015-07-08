/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Bhavesh
 * 
 * This model represent data for both CheckList and CheckListInstance
 * 
 * In case of checklistInstance it will contain some addition infromatino like- 
 * 		checkInstanceId, referenceNbr and referenceType etc.
 * And at the same time CheckListDetailBM will contain answer value also.
 *
 */
@SuppressWarnings("serial")
public class CheckListBM implements Serializable {


	private Integer checkListId;
	private String name;
	private String prerequisite;
	private CodeAndDescription checkListType;
	private String userId;
	private Date createdDtm;
	
	List<CheckListDetailBM> checkListDetailBMList;
	
	private Long checkListInstanceId;
	private String referenceType;
	private String referenceNumber;
	private String remarks;
	
	public Integer getCheckListId() {
		return checkListId;
	}
	
	public void setCheckListId(Integer checkListId) {
		this.checkListId = checkListId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
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

	public CodeAndDescription getCheckListType() {
		return checkListType;
	}

	public void setCheckListType(CodeAndDescription checkListType) {
		this.checkListType = checkListType;
	}

	public List<CheckListDetailBM> getCheckListDetailBMList() {
		return checkListDetailBMList;
	}

	public void setCheckListDetailBMList(
			List<CheckListDetailBM> checkListDetailBMList) {
		this.checkListDetailBMList = checkListDetailBMList;
	}

	public Long getCheckListInstanceId() {
		return checkListInstanceId;
	}

	public void setCheckListInstanceId(Long checkListInstanceId) {
		this.checkListInstanceId = checkListInstanceId;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
