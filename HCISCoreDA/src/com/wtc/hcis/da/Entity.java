package com.wtc.hcis.da;

import java.util.Date;

/**
 * Entity entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Entity implements java.io.Serializable {

	// Fields

	private Integer entityId;
	private Integer version;
	private Nationality nationality;
	private Saluation saluation;
	private Gender gender;
	private ContactDetails contactDetails;
	private BloodGroup bloodGroup;
	private Marital marital;
	private String name;
	private String type;
	private Date dob;
	private String image;
	private String knownLanguages;
	private String qualification;
	private String isPermanent;
	private Date joiningDt;
	private Date leavingDt;
	private String experience;
	private String referredBy;
	private String createdBy;
	private Date createdDtm;
	private String modifiedBy;
	private Date lastModifiedDtm;

	// Constructors

	/** default constructor */
	public Entity() {
	}

	/** minimal constructor */
	public Entity(String name, String type, String isPermanent,
			String createdBy, Date createdDtm) {
		this.name = name;
		this.type = type;
		this.isPermanent = isPermanent;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public Entity(Nationality nationality, Saluation saluation, Gender gender,
			ContactDetails contactDetails, BloodGroup bloodGroup,
			Marital marital, String name, String type, Date dob, String image,
			String knownLanguages, String qualification, String isPermanent,
			Date joiningDt, Date leavingDt, String experience,
			String referredBy, String createdBy, Date createdDtm,
			String modifiedBy, Date lastModifiedDtm) {
		this.nationality = nationality;
		this.saluation = saluation;
		this.gender = gender;
		this.contactDetails = contactDetails;
		this.bloodGroup = bloodGroup;
		this.marital = marital;
		this.name = name;
		this.type = type;
		this.dob = dob;
		this.image = image;
		this.knownLanguages = knownLanguages;
		this.qualification = qualification;
		this.isPermanent = isPermanent;
		this.joiningDt = joiningDt;
		this.leavingDt = leavingDt;
		this.experience = experience;
		this.referredBy = referredBy;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.modifiedBy = modifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
	}

	// Property accessors

	public Integer getEntityId() {
		return this.entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Nationality getNationality() {
		return this.nationality;
	}

	public void setNationality(Nationality nationality) {
		this.nationality = nationality;
	}

	public Saluation getSaluation() {
		return this.saluation;
	}

	public void setSaluation(Saluation saluation) {
		this.saluation = saluation;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public ContactDetails getContactDetails() {
		return this.contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public BloodGroup getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Marital getMarital() {
		return this.marital;
	}

	public void setMarital(Marital marital) {
		this.marital = marital;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getKnownLanguages() {
		return this.knownLanguages;
	}

	public void setKnownLanguages(String knownLanguages) {
		this.knownLanguages = knownLanguages;
	}

	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getIsPermanent() {
		return this.isPermanent;
	}

	public void setIsPermanent(String isPermanent) {
		this.isPermanent = isPermanent;
	}

	public Date getJoiningDt() {
		return this.joiningDt;
	}

	public void setJoiningDt(Date joiningDt) {
		this.joiningDt = joiningDt;
	}

	public Date getLeavingDt() {
		return this.leavingDt;
	}

	public void setLeavingDt(Date leavingDt) {
		this.leavingDt = leavingDt;
	}

	public String getExperience() {
		return this.experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getReferredBy() {
		return this.referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
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

}