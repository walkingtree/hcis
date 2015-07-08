package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ContactDetails entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ContactDetails implements java.io.Serializable {

	// Fields

	private Integer contactCode;
	private Integer version;
	private Saluation saluation;
	private Gender gender;
	private Relation relation;
	private String firstName;
	private String middleName;
	private String lastName;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String countryCode;
	private String stateCode;
	private String pincode;
	private String contactNumber;
	private String mobileNumber;
	private String faxNumber;
	private String email;
	private String stayDuration;
	private Date createDtm;
	private String createdBy;
	private Date modifiedDtm;
	private String modifiedBy;
	private Set entities = new HashSet(0);
	private Set hospitals = new HashSet(0);
	private Set labDetailses = new HashSet(0);
	private Set labCollectionPoints = new HashSet(0);
	private Set entityContactCodes = new HashSet(0);

	// Constructors

	/** default constructor */
	public ContactDetails() {
	}

	/** full constructor */
	public ContactDetails(Saluation saluation, Gender gender,
			Relation relation, String firstName, String middleName,
			String lastName, String addressLine1, String addressLine2,
			String city, String countryCode, String stateCode, String pincode,
			String contactNumber, String mobileNumber, String faxNumber,
			String email, String stayDuration, Date createDtm,
			String createdBy, Date modifiedDtm, String modifiedBy,
			Set entities, Set hospitals, Set labDetailses,
			Set labCollectionPoints, Set entityContactCodes) {
		this.saluation = saluation;
		this.gender = gender;
		this.relation = relation;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.countryCode = countryCode;
		this.stateCode = stateCode;
		this.pincode = pincode;
		this.contactNumber = contactNumber;
		this.mobileNumber = mobileNumber;
		this.faxNumber = faxNumber;
		this.email = email;
		this.stayDuration = stayDuration;
		this.createDtm = createDtm;
		this.createdBy = createdBy;
		this.modifiedDtm = modifiedDtm;
		this.modifiedBy = modifiedBy;
		this.entities = entities;
		this.hospitals = hospitals;
		this.labDetailses = labDetailses;
		this.labCollectionPoints = labCollectionPoints;
		this.entityContactCodes = entityContactCodes;
	}

	// Property accessors

	public Integer getContactCode() {
		return this.contactCode;
	}

	public void setContactCode(Integer contactCode) {
		this.contactCode = contactCode;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public Relation getRelation() {
		return this.relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStateCode() {
		return this.stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getPincode() {
		return this.pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFaxNumber() {
		return this.faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStayDuration() {
		return this.stayDuration;
	}

	public void setStayDuration(String stayDuration) {
		this.stayDuration = stayDuration;
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

	public Date getModifiedDtm() {
		return this.modifiedDtm;
	}

	public void setModifiedDtm(Date modifiedDtm) {
		this.modifiedDtm = modifiedDtm;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Set getEntities() {
		return this.entities;
	}

	public void setEntities(Set entities) {
		this.entities = entities;
	}

	public Set getHospitals() {
		return this.hospitals;
	}

	public void setHospitals(Set hospitals) {
		this.hospitals = hospitals;
	}

	public Set getLabDetailses() {
		return this.labDetailses;
	}

	public void setLabDetailses(Set labDetailses) {
		this.labDetailses = labDetailses;
	}

	public Set getLabCollectionPoints() {
		return this.labCollectionPoints;
	}

	public void setLabCollectionPoints(Set labCollectionPoints) {
		this.labCollectionPoints = labCollectionPoints;
	}

	public Set getEntityContactCodes() {
		return this.entityContactCodes;
	}

	public void setEntityContactCodes(Set entityContactCodes) {
		this.entityContactCodes = entityContactCodes;
	}

}