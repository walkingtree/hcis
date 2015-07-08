package com.wtc.hcis.da;

import java.util.Date;

/**
 * RenderedServiceId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class RenderedServiceId implements java.io.Serializable {

	// Fields

	private Integer serviceUid;
	private Date renderedDtm;

	// Constructors

	/** default constructor */
	public RenderedServiceId() {
	}

	/** full constructor */
	public RenderedServiceId(Integer serviceUid, Date renderedDtm) {
		this.serviceUid = serviceUid;
		this.renderedDtm = renderedDtm;
	}

	// Property accessors

	public Integer getServiceUid() {
		return this.serviceUid;
	}

	public void setServiceUid(Integer serviceUid) {
		this.serviceUid = serviceUid;
	}

	public Date getRenderedDtm() {
		return this.renderedDtm;
	}

	public void setRenderedDtm(Date renderedDtm) {
		this.renderedDtm = renderedDtm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RenderedServiceId))
			return false;
		RenderedServiceId castOther = (RenderedServiceId) other;

		return ((this.getServiceUid() == castOther.getServiceUid()) || (this
				.getServiceUid() != null
				&& castOther.getServiceUid() != null && this.getServiceUid()
				.equals(castOther.getServiceUid())))
				&& ((this.getRenderedDtm() == castOther.getRenderedDtm()) || (this
						.getRenderedDtm() != null
						&& castOther.getRenderedDtm() != null && this
						.getRenderedDtm().equals(castOther.getRenderedDtm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getServiceUid() == null ? 0 : this.getServiceUid()
						.hashCode());
		result = 37
				* result
				+ (getRenderedDtm() == null ? 0 : this.getRenderedDtm()
						.hashCode());
		return result;
	}

}