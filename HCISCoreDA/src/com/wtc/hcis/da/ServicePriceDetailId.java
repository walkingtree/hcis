package com.wtc.hcis.da;

import java.util.Date;

/**
 * ServicePriceDetailId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ServicePriceDetailId implements java.io.Serializable {

	// Fields

	private String serviceCd;
	private Date effectiveFrom;

	// Constructors

	/** default constructor */
	public ServicePriceDetailId() {
	}

	/** full constructor */
	public ServicePriceDetailId(String serviceCd, Date effectiveFrom) {
		this.serviceCd = serviceCd;
		this.effectiveFrom = effectiveFrom;
	}

	// Property accessors

	public String getServiceCd() {
		return this.serviceCd;
	}

	public void setServiceCd(String serviceCd) {
		this.serviceCd = serviceCd;
	}

	public Date getEffectiveFrom() {
		return this.effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ServicePriceDetailId))
			return false;
		ServicePriceDetailId castOther = (ServicePriceDetailId) other;

		return ((this.getServiceCd() == castOther.getServiceCd()) || (this
				.getServiceCd() != null
				&& castOther.getServiceCd() != null && this.getServiceCd()
				.equals(castOther.getServiceCd())))
				&& ((this.getEffectiveFrom() == castOther.getEffectiveFrom()) || (this
						.getEffectiveFrom() != null
						&& castOther.getEffectiveFrom() != null && this
						.getEffectiveFrom().equals(castOther.getEffectiveFrom())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getServiceCd() == null ? 0 : this.getServiceCd().hashCode());
		result = 37
				* result
				+ (getEffectiveFrom() == null ? 0 : this.getEffectiveFrom()
						.hashCode());
		return result;
	}

}