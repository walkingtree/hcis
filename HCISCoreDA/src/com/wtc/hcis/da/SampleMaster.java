package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * SampleMaster entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SampleMaster implements java.io.Serializable {

	// Fields

	private String sampleCode;
	private Integer version;
	private String sampleDescription;
	private Short active;
	private Set sampleses = new HashSet(0);

	// Constructors

	/** default constructor */
	public SampleMaster() {
	}

	/** full constructor */
	public SampleMaster(String sampleDescription, Short active, Set sampleses) {
		this.sampleDescription = sampleDescription;
		this.active = active;
		this.sampleses = sampleses;
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

	public String getSampleDescription() {
		return this.sampleDescription;
	}

	public void setSampleDescription(String sampleDescription) {
		this.sampleDescription = sampleDescription;
	}

	public Short getActive() {
		return this.active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

	public Set getSampleses() {
		return this.sampleses;
	}

	public void setSampleses(Set sampleses) {
		this.sampleses = sampleses;
	}

}