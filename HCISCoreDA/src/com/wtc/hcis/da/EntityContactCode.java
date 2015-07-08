package com.wtc.hcis.da;

import java.util.Date;

/**
 * EntityContactCode entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EntityContactCode implements java.io.Serializable {

	// Fields

	private EntityContactCodeId id;
	private Integer version;
	private ContactType contactType;
	private AppEntities appEntities;
	private ContactDetails contactDetails;
	private Short sameAsCurrent;
	private Short active;
	private Date createDtm;
	private String createdBy;
	private Date lastModifiedDtm;
	private String modifiedBy;

	// Constructors

	/** default constructor */
	public EntityContactCode() {
	}

	/** minimal constructor */
	public EntityContactCode(EntityContactCodeId id, ContactType contactType,
			AppEntities appEntities) {
		this.id = id;
		this.contactType = contactType;
		this.appEntities = appEntities;
	}

	/** full constructor */
	public EntityContactCode(EntityContactCodeId id, ContactType contactType,
			AppEntities appEntities, ContactDetails contactDetails,
			Short sameAsCurrent, Short active, Date createDtm,
			String createdBy, Date lastModifiedDtm, String modifiedBy) {
		this.id = id;
		this.contactType = contactType;
		this.appEntities = appEntities;
		this.contactDetails = contactDetails;
		this.sameAsCurrent = sameAsCurrent;
		this.active = active;
		this.createDtm = createDtm;
		this.createdBy = createdBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.modifiedBy = modifiedBy;
	}

	// Property accessors

	public EntityContactCodeId getId() {
		return this.id;
	}

	public void setId(EntityContactCodeId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ContactType getContactType() {
		return this.contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	public AppEntities getAppEntities() {
		return this.appEntities;
	}

	public void setAppEntities(AppEntities appEntities) {
		this.appEntities = appEntities;
	}

	public ContactDetails getContactDetails() {
		return this.contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public Short getSameAsCurrent() {
		return this.sameAsCurrent;
	}

	public void setSameAsCurrent(Short sameAsCurrent) {
		this.sameAsCurrent = sameAsCurrent;
	}

	public Short getActive() {
		return this.active;
	}

	public void setActive(Short active) {
		this.active = active;
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