package com.wtc.hcis.da;

import java.util.Date;

/**
 * DocPatientVitalFieldsValueId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DocPatientVitalFieldsValueId implements java.io.Serializable {

	// Fields

	private Integer patientVitalId;
	private String vitalFieldCode;
	private Date    forTime;

	// Constructors

	public Date getForTime() {
		return forTime;
	}

	public void setForTime(Date forTime) {
		this.forTime = forTime;
	}

	/** default constructor */
	public DocPatientVitalFieldsValueId() {
	}

	/** full constructor */
	public DocPatientVitalFieldsValueId(Integer patientVitalId,
			String vitalFieldCode) {
		this.patientVitalId = patientVitalId;
		this.vitalFieldCode = vitalFieldCode;
	}

	// Property accessors

	public Integer getPatientVitalId() {
		return this.patientVitalId;
	}

	public void setPatientVitalId(Integer patientVitalId) {
		this.patientVitalId = patientVitalId;
	}

	public String getVitalFieldCode() {
		return this.vitalFieldCode;
	}

	public void setVitalFieldCode(String vitalFieldCode) {
		this.vitalFieldCode = vitalFieldCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DocPatientVitalFieldsValueId))
			return false;
		DocPatientVitalFieldsValueId castOther = (DocPatientVitalFieldsValueId) other;

		return ((this.getPatientVitalId() == castOther.getPatientVitalId()) || (this
				.getPatientVitalId() != null
				&& castOther.getPatientVitalId() != null && this
				.getPatientVitalId().equals(castOther.getPatientVitalId())))
				&& ((this.getVitalFieldCode() == castOther.getVitalFieldCode()) || (this
						.getVitalFieldCode() != null
						&& castOther.getVitalFieldCode() != null && this
						.getVitalFieldCode().equals(
								castOther.getVitalFieldCode())))
				&& ((this.getForTime() == castOther.getForTime()) || (this
						.getForTime() != null
						&& castOther.getForTime() != null && this
						.getForTime().equals(
								castOther.getForTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getPatientVitalId() == null ? 0 : this.getPatientVitalId()
						.hashCode());
		result = 37
				* result
				+ (getVitalFieldCode() == null ? 0 : this.getVitalFieldCode()
						.hashCode());
		
		result = 37
		* result
		+ (getForTime() == null ? 0 : this.getForTime() 
				.hashCode());
		
		return result;
	}

}