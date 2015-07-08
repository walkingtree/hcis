package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Hospital entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Hospital implements java.io.Serializable {

	// Fields

	private String hospitalCode;
	private ContactDetails contactDetails;
	private String hospitalName;
	private Date createdDtm;
	private String createdBy;
	private Set labDetailses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Hospital() {
	}

	/** minimal constructor */
	public Hospital(ContactDetails contactDetails, String hospitalName,
			Date createdDtm, String createdBy) {
		this.contactDetails = contactDetails;
		this.hospitalName = hospitalName;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
	}

	/** full constructor */
	public Hospital(ContactDetails contactDetails, String hospitalName,
			Date createdDtm, String createdBy, Set labDetailses) {
		this.contactDetails = contactDetails;
		this.hospitalName = hospitalName;
		this.createdDtm = createdDtm;
		this.createdBy = createdBy;
		this.labDetailses = labDetailses;
	}

	// Property accessors

	public String getHospitalCode() {
		return this.hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public ContactDetails getContactDetails() {
		return this.contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getHospitalName() {
		return this.hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Date getCreatedDtm() {
		return this.createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Set getLabDetailses() {
		return this.labDetailses;
	}

	public void setLabDetailses(Set labDetailses) {
		this.labDetailses = labDetailses;
	}

}