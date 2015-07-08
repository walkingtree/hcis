package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * BedEnvelope entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedEnvelope implements java.io.Serializable {

	// Fields

	private String envelopeName;
	private Integer version;
	private String description;
	private String facilityTypeInd;
	private Set bedEnvelopeHasPools = new HashSet(0);

	// Constructors

	/** default constructor */
	public BedEnvelope() {
	}

	/** minimal constructor */
	public BedEnvelope(String envelopeName) {
		this.envelopeName = envelopeName;
	}

	/** full constructor */
	public BedEnvelope(String envelopeName, String description,
			String facilityTypeInd, Set bedEnvelopeHasPools) {
		this.envelopeName = envelopeName;
		this.description = description;
		this.facilityTypeInd = facilityTypeInd;
		this.bedEnvelopeHasPools = bedEnvelopeHasPools;
	}

	// Property accessors

	public String getEnvelopeName() {
		return this.envelopeName;
	}

	public void setEnvelopeName(String envelopeName) {
		this.envelopeName = envelopeName;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFacilityTypeInd() {
		return this.facilityTypeInd;
	}

	public void setFacilityTypeInd(String facilityTypeInd) {
		this.facilityTypeInd = facilityTypeInd;
	}

	public Set getBedEnvelopeHasPools() {
		return this.bedEnvelopeHasPools;
	}

	public void setBedEnvelopeHasPools(Set bedEnvelopeHasPools) {
		this.bedEnvelopeHasPools = bedEnvelopeHasPools;
	}

}