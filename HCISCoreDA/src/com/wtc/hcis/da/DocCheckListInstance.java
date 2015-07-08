package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DocCheckListInstance entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DocCheckListInstance implements java.io.Serializable {

	// Fields

	private Long checkListInstanceId;
	private Integer version;
	private DocCheckList docCheckList;
	private String referenceType;
	private String referenceNumber;
	private String remarks;
	private String createdBy;
	private Date createdDtm;
	private String modifiedBy;
	private Date lastModifiedDtm;
	private Set docCheckListInstanceDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public DocCheckListInstance() {
	}

	/** minimal constructor */
	public DocCheckListInstance(DocCheckList docCheckList,
			String referenceType, String referenceNumber, String remarks,
			String createdBy, Date createdDtm) {
		this.docCheckList = docCheckList;
		this.referenceType = referenceType;
		this.referenceNumber = referenceNumber;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public DocCheckListInstance(DocCheckList docCheckList,
			String referenceType, String referenceNumber, String remarks,
			String createdBy, Date createdDtm, String modifiedBy,
			Date lastModifiedDtm, Set docCheckListInstanceDetails) {
		this.docCheckList = docCheckList;
		this.referenceType = referenceType;
		this.referenceNumber = referenceNumber;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.modifiedBy = modifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.docCheckListInstanceDetails = docCheckListInstanceDetails;
	}

	// Property accessors

	public Long getCheckListInstanceId() {
		return this.checkListInstanceId;
	}

	public void setCheckListInstanceId(Long checkListInstanceId) {
		this.checkListInstanceId = checkListInstanceId;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public DocCheckList getDocCheckList() {
		return this.docCheckList;
	}

	public void setDocCheckList(DocCheckList docCheckList) {
		this.docCheckList = docCheckList;
	}

	public String getReferenceType() {
		return this.referenceType;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDtm() {
		return this.createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public Set getDocCheckListInstanceDetails() {
		return this.docCheckListInstanceDetails;
	}

	public void setDocCheckListInstanceDetails(Set docCheckListInstanceDetails) {
		this.docCheckListInstanceDetails = docCheckListInstanceDetails;
	}

}