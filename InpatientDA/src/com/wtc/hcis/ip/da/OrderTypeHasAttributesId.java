package com.wtc.hcis.ip.da;

/**
 * OrderTypeHasAttributesId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OrderTypeHasAttributesId implements java.io.Serializable {

	// Fields

	private String orderTypeCd;
	private String attributeName;

	// Constructors

	/** default constructor */
	public OrderTypeHasAttributesId() {
	}

	/** full constructor */
	public OrderTypeHasAttributesId(String orderTypeCd, String attributeName) {
		this.orderTypeCd = orderTypeCd;
		this.attributeName = attributeName;
	}

	// Property accessors

	public String getOrderTypeCd() {
		return this.orderTypeCd;
	}

	public void setOrderTypeCd(String orderTypeCd) {
		this.orderTypeCd = orderTypeCd;
	}

	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrderTypeHasAttributesId))
			return false;
		OrderTypeHasAttributesId castOther = (OrderTypeHasAttributesId) other;

		return ((this.getOrderTypeCd() == castOther.getOrderTypeCd()) || (this
				.getOrderTypeCd() != null
				&& castOther.getOrderTypeCd() != null && this.getOrderTypeCd()
				.equals(castOther.getOrderTypeCd())))
				&& ((this.getAttributeName() == castOther.getAttributeName()) || (this
						.getAttributeName() != null
						&& castOther.getAttributeName() != null && this
						.getAttributeName()
						.equals(castOther.getAttributeName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getOrderTypeCd() == null ? 0 : this.getOrderTypeCd()
						.hashCode());
		result = 37
				* result
				+ (getAttributeName() == null ? 0 : this.getAttributeName()
						.hashCode());
		return result;
	}

}