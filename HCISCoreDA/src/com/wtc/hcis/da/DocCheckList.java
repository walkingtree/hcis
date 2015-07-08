package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DocCheckList entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DocCheckList implements java.io.Serializable {

	// Fields

	private Integer checkListId;
	private Integer version;
	private String name;
	private String prerequisite;
	private String checkListType;
	private String createdBy;
	private Date createdDtm;
	private String modifiedBy;
	private Date lastModifiedDtm;
	private Set docCheckListDetails = new HashSet(0);
	private Set docCheckListInstances = new HashSet(0);

	// Constructors

	/** default constructor */
	public DocCheckList() {
	}

	/** minimal constructor */
	public DocCheckList(String name, String checkListType, String createdBy,
			Date createdDtm) {
		this.name = name;
		this.checkListType = checkListType;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public DocCheckList(String name, String prerequisite, String checkListType,
			String createdBy, Date createdDtm, String modifiedBy,
			Date lastModifiedDtm, Set docCheckListDetails,
			Set docCheckListInstances) {
		this.name = name;
		this.prerequisite = prerequisite;
		this.checkListType = checkListType;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.modifiedBy = modifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.docCheckListDetails = docCheckListDetails;
		this.docCheckListInstances = docCheckListInstances;
	}

	// Property accessors

	public Integer getCheckListId() {
		return this.checkListId;
	}

	public void setCheckListId(Integer checkListId) {
		this.checkListId = checkListId;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrerequisite() {
		return this.prerequisite;
	}

	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}

	public String getCheckListType() {
		return this.checkListType;
	}

	public void setCheckListType(String checkListType) {
		this.checkListType = checkListType;
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

	public Set getDocCheckListDetails() {
		return this.docCheckListDetails;
	}

	public void setDocCheckListDetails(Set docCheckListDetails) {
		this.docCheckListDetails = docCheckListDetails;
	}

	public Set getDocCheckListInstances() {
		return this.docCheckListInstances;
	}

	public void setDocCheckListInstances(Set docCheckListInstances) {
		this.docCheckListInstances = docCheckListInstances;
	}

}