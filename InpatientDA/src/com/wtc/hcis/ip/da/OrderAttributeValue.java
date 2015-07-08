package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * OrderAttributeValue entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OrderAttributeValue implements java.io.Serializable {

	// Fields

	private OrderAttributeValueId id;
	private Integer version;
	private Attribute attribute;
	private DoctorOrder doctorOrder;
	private String attributeValue;
	private Date lastModifiedDtm;

	// Constructors

	/** default constructor */
	public OrderAttributeValue() {
	}

	/** minimal constructor */
	public OrderAttributeValue(OrderAttributeValueId id, Attribute attribute,
			DoctorOrder doctorOrder) {
		this.id = id;
		this.attribute = attribute;
		this.doctorOrder = doctorOrder;
	}

	/** full constructor */
	public OrderAttributeValue(OrderAttributeValueId id, Attribute attribute,
			DoctorOrder doctorOrder, String attributeValue, Date lastModifiedDtm) {
		this.id = id;
		this.attribute = attribute;
		this.doctorOrder = doctorOrder;
		this.attributeValue = attributeValue;
		this.lastModifiedDtm = lastModifiedDtm;
	}

	// Property accessors

	public OrderAttributeValueId getId() {
		return this.id;
	}

	public void setId(OrderAttributeValueId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Attribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public DoctorOrder getDoctorOrder() {
		return this.doctorOrder;
	}

	public void setDoctorOrder(DoctorOrder doctorOrder) {
		this.doctorOrder = doctorOrder;
	}

	public String getAttributeValue() {
		return this.attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

}