package com.wtc.hcis.da;

import java.util.Date;

/**
 * DocCheckListInstanceDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DocCheckListInstanceDetail implements java.io.Serializable {

	// Fields

	private DocCheckListInstanceDetailId id;
	private Integer version;
	private DocCheckListInstance docCheckListInstance;
	private DocCheckListDetail docCheckListDetail;
	private String answer;
	private String createdBy;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public DocCheckListInstanceDetail() {
	}

	/** full constructor */
	public DocCheckListInstanceDetail(DocCheckListInstanceDetailId id,
			DocCheckListInstance docCheckListInstance,
			DocCheckListDetail docCheckListDetail, String answer,
			String createdBy, Date createdDtm) {
		this.id = id;
		this.docCheckListInstance = docCheckListInstance;
		this.docCheckListDetail = docCheckListDetail;
		this.answer = answer;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public DocCheckListInstanceDetailId getId() {
		return this.id;
	}

	public void setId(DocCheckListInstanceDetailId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public DocCheckListInstance getDocCheckListInstance() {
		return this.docCheckListInstance;
	}

	public void setDocCheckListInstance(
			DocCheckListInstance docCheckListInstance) {
		this.docCheckListInstance = docCheckListInstance;
	}

	public DocCheckListDetail getDocCheckListDetail() {
		return this.docCheckListDetail;
	}

	public void setDocCheckListDetail(DocCheckListDetail docCheckListDetail) {
		this.docCheckListDetail = docCheckListDetail;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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

}