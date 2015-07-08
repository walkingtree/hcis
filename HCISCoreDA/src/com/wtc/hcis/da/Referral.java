package com.wtc.hcis.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Referral entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Referral implements java.io.Serializable {

	// Fields

	private Integer referralCode;
	private Integer version;
	private Country country;
	private String referralTypeCode;
	private String qualification;
	private String referralName;
	private String preferredContactTime;
	private String address;
	private String city;
	private String stateCode;
	private String pinCode;
	private String phone;
	private String mobile;
	private String fax;
	private String email;
	private String createdBy;
	private Date lastCommissionProcessDtm;
	private Date createDtm;
	private Date lastModifiedDtm;
	private String active;
	private Set referralCommissions = new HashSet(0);
	private Set referralPayables = new HashSet(0);
	private Set patients = new HashSet(0);
	private Set appointmentses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Referral() {
	}

	/** minimal constructor */
	public Referral(String referralTypeCode, String referralName,
			String address, String city, Date createDtm) {
		this.referralTypeCode = referralTypeCode;
		this.referralName = referralName;
		this.address = address;
		this.city = city;
		this.createDtm = createDtm;
	}

	/** full constructor */
	public Referral(Country country, String referralTypeCode,
			String qualification, String referralName,
			String preferredContactTime, String address, String city,
			String stateCode, String pinCode, String phone, String mobile,
			String fax, String email, String createdBy,
			Date lastCommissionProcessDtm, Date createDtm,
			Date lastModifiedDtm, String active, Set referralCommissions,
			Set referralPayables, Set patients, Set appointmentses) {
		this.country = country;
		this.referralTypeCode = referralTypeCode;
		this.qualification = qualification;
		this.referralName = referralName;
		this.preferredContactTime = preferredContactTime;
		this.address = address;
		this.city = city;
		this.stateCode = stateCode;
		this.pinCode = pinCode;
		this.phone = phone;
		this.mobile = mobile;
		this.fax = fax;
		this.email = email;
		this.createdBy = createdBy;
		this.lastCommissionProcessDtm = lastCommissionProcessDtm;
		this.createDtm = createDtm;
		this.lastModifiedDtm = lastModifiedDtm;
		this.active = active;
		this.referralCommissions = referralCommissions;
		this.referralPayables = referralPayables;
		this.patients = patients;
		this.appointmentses = appointmentses;
	}

	// Property accessors

	public Integer getReferralCode() {
		return this.referralCode;
	}

	public void setReferralCode(Integer referralCode) {
		this.referralCode = referralCode;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getReferralTypeCode() {
		return this.referralTypeCode;
	}

	public void setReferralTypeCode(String referralTypeCode) {
		this.referralTypeCode = referralTypeCode;
	}

	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getReferralName() {
		return this.referralName;
	}

	public void setReferralName(String referralName) {
		this.referralName = referralName;
	}

	public String getPreferredContactTime() {
		return this.preferredContactTime;
	}

	public void setPreferredContactTime(String preferredContactTime) {
		this.preferredContactTime = preferredContactTime;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateCode() {
		return this.stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getPinCode() {
		return this.pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastCommissionProcessDtm() {
		return this.lastCommissionProcessDtm;
	}

	public void setLastCommissionProcessDtm(Date lastCommissionProcessDtm) {
		this.lastCommissionProcessDtm = lastCommissionProcessDtm;
	}

	public Date getCreateDtm() {
		return this.createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Set getReferralCommissions() {
		return this.referralCommissions;
	}

	public void setReferralCommissions(Set referralCommissions) {
		this.referralCommissions = referralCommissions;
	}

	public Set getReferralPayables() {
		return this.referralPayables;
	}

	public void setReferralPayables(Set referralPayables) {
		this.referralPayables = referralPayables;
	}

	public Set getPatients() {
		return this.patients;
	}

	public void setPatients(Set patients) {
		this.patients = patients;
	}

	public Set getAppointmentses() {
		return this.appointmentses;
	}

	public void setAppointmentses(Set appointmentses) {
		this.appointmentses = appointmentses;
	}

}