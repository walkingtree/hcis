package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * DoctorOrderGroupHasTemplate entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderGroupHasTemplate implements java.io.Serializable {

	// Fields

	private DoctorOrderGroupHasTemplateId id;
	private Integer version;
	private DoctorOrderGroup doctorOrderGroup;
	private DoctorOrderTemplate doctorOrderTemplate;
	private Date effectiveFromDt;
	private Date effectiveToDt;

	// Constructors

	/** default constructor */
	public DoctorOrderGroupHasTemplate() {
	}

	/** minimal constructor */
	public DoctorOrderGroupHasTemplate(DoctorOrderGroupHasTemplateId id,
			DoctorOrderGroup doctorOrderGroup,
			DoctorOrderTemplate doctorOrderTemplate) {
		this.id = id;
		this.doctorOrderGroup = doctorOrderGroup;
		this.doctorOrderTemplate = doctorOrderTemplate;
	}

	/** full constructor */
	public DoctorOrderGroupHasTemplate(DoctorOrderGroupHasTemplateId id,
			DoctorOrderGroup doctorOrderGroup,
			DoctorOrderTemplate doctorOrderTemplate, Date effectiveFromDt,
			Date effectiveToDt) {
		this.id = id;
		this.doctorOrderGroup = doctorOrderGroup;
		this.doctorOrderTemplate = doctorOrderTemplate;
		this.effectiveFromDt = effectiveFromDt;
		this.effectiveToDt = effectiveToDt;
	}

	// Property accessors

	public DoctorOrderGroupHasTemplateId getId() {
		return this.id;
	}

	public void setId(DoctorOrderGroupHasTemplateId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public DoctorOrderGroup getDoctorOrderGroup() {
		return this.doctorOrderGroup;
	}

	public void setDoctorOrderGroup(DoctorOrderGroup doctorOrderGroup) {
		this.doctorOrderGroup = doctorOrderGroup;
	}

	public DoctorOrderTemplate getDoctorOrderTemplate() {
		return this.doctorOrderTemplate;
	}

	public void setDoctorOrderTemplate(DoctorOrderTemplate doctorOrderTemplate) {
		this.doctorOrderTemplate = doctorOrderTemplate;
	}

	public Date getEffectiveFromDt() {
		return this.effectiveFromDt;
	}

	public void setEffectiveFromDt(Date effectiveFromDt) {
		this.effectiveFromDt = effectiveFromDt;
	}

	public Date getEffectiveToDt() {
		return this.effectiveToDt;
	}

	public void setEffectiveToDt(Date effectiveToDt) {
		this.effectiveToDt = effectiveToDt;
	}

}