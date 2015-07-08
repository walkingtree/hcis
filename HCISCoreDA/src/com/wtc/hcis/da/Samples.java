package com.wtc.hcis.da;

import java.util.Date;

/**
 * Samples entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Samples implements java.io.Serializable {

	// Fields

	private String sampleCode;
	private Integer version;
	private Record record;
	private SampleMaster sampleMaster;
	private Integer doctorId;
	private Date collectedDtm;
	private String remarks;
	private Date createDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;

	// Constructors

	/** default constructor */
	public Samples() {
	}

	/** minimal constructor */
	public Samples(SampleMaster sampleMaster) {
		this.sampleMaster = sampleMaster;
	}

	/** full constructor */
	public Samples(Record record, SampleMaster sampleMaster, Integer doctorId,
			Date collectedDtm, String remarks, Date createDtm,
			String createdBy, Date lastModifiedDtm, String modifiedBy) {
		this.record = record;
		this.sampleMaster = sampleMaster;
		this.doctorId = doctorId;
		this.collectedDtm = collectedDtm;
		this.remarks = remarks;
		this.createDtm = createDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
	}

	// Property accessors

	public String getSampleCode() {
		return this.sampleCode;
	}

	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Record getRecord() {
		return this.record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public SampleMaster getSampleMaster() {
		return this.sampleMaster;
	}

	public void setSampleMaster(SampleMaster sampleMaster) {
		this.sampleMaster = sampleMaster;
	}

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Date getCollectedDtm() {
		return this.collectedDtm;
	}

	public void setCollectedDtm(Date collectedDtm) {
		this.collectedDtm = collectedDtm;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDtm() {
		return this.createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}