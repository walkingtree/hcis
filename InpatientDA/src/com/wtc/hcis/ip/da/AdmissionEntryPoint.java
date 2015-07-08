package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * AdmissionEntryPoint entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AdmissionEntryPoint implements java.io.Serializable {

	// Fields

	private String entryPointName;
	private String entryPointDesc;
	private Set admissions = new HashSet(0);

	// Constructors

	/** default constructor */
	public AdmissionEntryPoint() {
	}

	/** minimal constructor */
	public AdmissionEntryPoint(String entryPointName) {
		this.entryPointName = entryPointName;
	}

	/** full constructor */
	public AdmissionEntryPoint(String entryPointName, String entryPointDesc,
			Set admissions) {
		this.entryPointName = entryPointName;
		this.entryPointDesc = entryPointDesc;
		this.admissions = admissions;
	}

	// Property accessors

	public String getEntryPointName() {
		return this.entryPointName;
	}

	public void setEntryPointName(String entryPointName) {
		this.entryPointName = entryPointName;
	}

	public String getEntryPointDesc() {
		return this.entryPointDesc;
	}

	public void setEntryPointDesc(String entryPointDesc) {
		this.entryPointDesc = entryPointDesc;
	}

	public Set getAdmissions() {
		return this.admissions;
	}

	public void setAdmissions(Set admissions) {
		this.admissions = admissions;
	}

}