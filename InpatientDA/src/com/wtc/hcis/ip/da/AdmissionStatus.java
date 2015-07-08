package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * AdmissionStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AdmissionStatus implements java.io.Serializable {

	// Fields

	private String admissionStatusCd;
	private String admissionStatusDesc;
	private String activeFlag;
	private Set admissionActivities = new HashSet(0);
	private Set admissions = new HashSet(0);

	// Constructors

	/** default constructor */
	public AdmissionStatus() {
	}

	/** minimal constructor */
	public AdmissionStatus(String admissionStatusCd) {
		this.admissionStatusCd = admissionStatusCd;
	}

	/** full constructor */
	public AdmissionStatus(String admissionStatusCd,
			String admissionStatusDesc, String activeFlag,
			Set admissionActivities, Set admissions) {
		this.admissionStatusCd = admissionStatusCd;
		this.admissionStatusDesc = admissionStatusDesc;
		this.activeFlag = activeFlag;
		this.admissionActivities = admissionActivities;
		this.admissions = admissions;
	}

	// Property accessors

	public String getAdmissionStatusCd() {
		return this.admissionStatusCd;
	}

	public void setAdmissionStatusCd(String admissionStatusCd) {
		this.admissionStatusCd = admissionStatusCd;
	}

	public String getAdmissionStatusDesc() {
		return this.admissionStatusDesc;
	}

	public void setAdmissionStatusDesc(String admissionStatusDesc) {
		this.admissionStatusDesc = admissionStatusDesc;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Set getAdmissionActivities() {
		return this.admissionActivities;
	}

	public void setAdmissionActivities(Set admissionActivities) {
		this.admissionActivities = admissionActivities;
	}

	public Set getAdmissions() {
		return this.admissions;
	}

	public void setAdmissions(Set admissions) {
		this.admissions = admissions;
	}

}