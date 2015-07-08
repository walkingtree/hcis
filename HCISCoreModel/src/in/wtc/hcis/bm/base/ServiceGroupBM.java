package in.wtc.hcis.bm.base;

import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class ServiceGroupBM implements java.io.Serializable {

	// Fields

	private String serviceGroupCode;
	private String groupName;
	private Boolean isActive;
	private CodeAndDescription parentGroup;
	private CodeAndDescription groupStatus;
	private String createdBy;
	private Date creationDate;
	private String modifiedBy;
	private Date lastModificationDate;
	
	private List<ServiceSummaryBM> serviceSummaryList;
	
	public String getServiceGroupCode() {
		return serviceGroupCode;
	}
	public void setServiceGroupCode(String serviceGroupCode) {
		this.serviceGroupCode = serviceGroupCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getLastModificationDate() {
		return lastModificationDate;
	}
	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}
	public CodeAndDescription getParentGroup() {
		return parentGroup;
	}
	public void setParentGroup(CodeAndDescription parentGroup) {
		this.parentGroup = parentGroup;
	}
	public CodeAndDescription getGroupStatus() {
		return groupStatus;
	}
	public void setGroupStatus(CodeAndDescription groupStatus) {
		this.groupStatus = groupStatus;
	}
	public List<ServiceSummaryBM> getServiceSummaryList() {
		return serviceSummaryList;
	}
	public void setServiceSummaryList(List<ServiceSummaryBM> serviceSummaryList) {
		this.serviceSummaryList = serviceSummaryList;
	}
	

}