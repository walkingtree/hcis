package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DocCheckListDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DocCheckListDetail implements java.io.Serializable {

	// Fields

	private Long checkListDetailId;
	private Integer version;
	private DocCheckList docCheckList;
	private String question;
	private String groupName;
	private String roleCode;
	private String createdBy;
	private Date createdDtm;
	private Set docCheckListInstanceDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public DocCheckListDetail() {
	}

	/** minimal constructor */
	public DocCheckListDetail(DocCheckList docCheckList, String question,
			String groupName, String roleCode, String createdBy, Date createdDtm) {
		this.docCheckList = docCheckList;
		this.question = question;
		this.groupName = groupName;
		this.roleCode = roleCode;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public DocCheckListDetail(DocCheckList docCheckList, String question,
			String groupName, String roleCode, String createdBy,
			Date createdDtm, Set docCheckListInstanceDetails) {
		this.docCheckList = docCheckList;
		this.question = question;
		this.groupName = groupName;
		this.roleCode = roleCode;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.docCheckListInstanceDetails = docCheckListInstanceDetails;
	}

	// Property accessors

	public Long getCheckListDetailId() {
		return this.checkListDetailId;
	}

	public void setCheckListDetailId(Long checkListDetailId) {
		this.checkListDetailId = checkListDetailId;
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

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
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

	public Set getDocCheckListInstanceDetails() {
		return this.docCheckListInstanceDetails;
	}

	public void setDocCheckListInstanceDetails(Set docCheckListInstanceDetails) {
		this.docCheckListInstanceDetails = docCheckListInstanceDetails;
	}

}