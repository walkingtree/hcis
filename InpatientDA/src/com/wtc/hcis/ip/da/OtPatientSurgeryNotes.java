package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * OtPatientSurgeryNotes entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtPatientSurgeryNotes implements java.io.Serializable {

	// Fields

	private OtPatientSurgeryNotesId id;
	private Integer version;
	private OtNotesConfiguration otNotesConfiguration;
	private OtPatientSurgery otPatientSurgery;
	private String value;
	private String createdBy;
	private Date createdDtm;
	private String modifiedBy;
	private Date modifiedDtm;

	// Constructors

	/** default constructor */
	public OtPatientSurgeryNotes() {
	}

	/** minimal constructor */
	public OtPatientSurgeryNotes(OtPatientSurgeryNotesId id,
			OtNotesConfiguration otNotesConfiguration,
			OtPatientSurgery otPatientSurgery, String value, String createdBy,
			Date createdDtm) {
		this.id = id;
		this.otNotesConfiguration = otNotesConfiguration;
		this.otPatientSurgery = otPatientSurgery;
		this.value = value;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public OtPatientSurgeryNotes(OtPatientSurgeryNotesId id,
			OtNotesConfiguration otNotesConfiguration,
			OtPatientSurgery otPatientSurgery, String value, String createdBy,
			Date createdDtm, String modifiedBy, Date modifiedDtm) {
		this.id = id;
		this.otNotesConfiguration = otNotesConfiguration;
		this.otPatientSurgery = otPatientSurgery;
		this.value = value;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.modifiedBy = modifiedBy;
		this.modifiedDtm = modifiedDtm;
	}

	// Property accessors

	public OtPatientSurgeryNotesId getId() {
		return this.id;
	}

	public void setId(OtPatientSurgeryNotesId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public OtNotesConfiguration getOtNotesConfiguration() {
		return this.otNotesConfiguration;
	}

	public void setOtNotesConfiguration(
			OtNotesConfiguration otNotesConfiguration) {
		this.otNotesConfiguration = otNotesConfiguration;
	}

	public OtPatientSurgery getOtPatientSurgery() {
		return this.otPatientSurgery;
	}

	public void setOtPatientSurgery(OtPatientSurgery otPatientSurgery) {
		this.otPatientSurgery = otPatientSurgery;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
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

	public Date getModifiedDtm() {
		return this.modifiedDtm;
	}

	public void setModifiedDtm(Date modifiedDtm) {
		this.modifiedDtm = modifiedDtm;
	}

}