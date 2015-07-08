package com.wtc.hcis.ip.da;

/**
 * OrderAttributeValueId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OrderAttributeValueId implements java.io.Serializable {

	// Fields

	private Integer orderNbr;
	private String attributeName;

	// Constructors

	/** default constructor */
	public OrderAttributeValueId() {
	}

	/** full constructor */
	public OrderAttributeValueId(Integer orderNbr, String attributeName) {
		this.orderNbr = orderNbr;
		this.attributeName = attributeName;
	}

	// Property accessors

	public Integer getOrderNbr() {
		return this.orderNbr;
	}

	public void setOrderNbr(Integer orderNbr) {
		this.orderNbr = orderNbr;
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
		if (!(other instanceof OrderAttributeValueId))
			return false;
		OrderAttributeValueId castOther = (OrderAttributeValueId) other;

		return ((this.getOrderNbr() == castOther.getOrderNbr()) || (this
				.getOrderNbr() != null
				&& castOther.getOrderNbr() != null && this.getOrderNbr()
				.equals(castOther.getOrderNbr())))
				&& ((this.getAttributeName() == castOther.getAttributeName()) || (this
						.getAttributeName() != null
						&& castOther.getAttributeName() != null && this
						.getAttributeName()
						.equals(castOther.getAttributeName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOrderNbr() == null ? 0 : this.getOrderNbr().hashCode());
		result = 37
				* result
				+ (getAttributeName() == null ? 0 : this.getAttributeName()
						.hashCode());
		return result;
	}

}