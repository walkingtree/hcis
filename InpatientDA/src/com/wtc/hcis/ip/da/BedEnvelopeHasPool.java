package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * BedEnvelopeHasPool entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedEnvelopeHasPool implements java.io.Serializable {

	// Fields

	private BedEnvelopeHasPoolId id;
	private Integer version;
	private BedEnvelope bedEnvelope;
	private BedPool bedPool;
	private Date effectiveFromDt;
	private Date effectiveToDt;

	// Constructors

	/** default constructor */
	public BedEnvelopeHasPool() {
	}

	/** minimal constructor */
	public BedEnvelopeHasPool(BedEnvelopeHasPoolId id, BedEnvelope bedEnvelope,
			BedPool bedPool) {
		this.id = id;
		this.bedEnvelope = bedEnvelope;
		this.bedPool = bedPool;
	}

	/** full constructor */
	public BedEnvelopeHasPool(BedEnvelopeHasPoolId id, BedEnvelope bedEnvelope,
			BedPool bedPool, Date effectiveFromDt, Date effectiveToDt) {
		this.id = id;
		this.bedEnvelope = bedEnvelope;
		this.bedPool = bedPool;
		this.effectiveFromDt = effectiveFromDt;
		this.effectiveToDt = effectiveToDt;
	}

	// Property accessors

	public BedEnvelopeHasPoolId getId() {
		return this.id;
	}

	public void setId(BedEnvelopeHasPoolId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public BedEnvelope getBedEnvelope() {
		return this.bedEnvelope;
	}

	public void setBedEnvelope(BedEnvelope bedEnvelope) {
		this.bedEnvelope = bedEnvelope;
	}

	public BedPool getBedPool() {
		return this.bedPool;
	}

	public void setBedPool(BedPool bedPool) {
		this.bedPool = bedPool;
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