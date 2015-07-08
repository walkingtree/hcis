package com.wtc.hcis.ip.da;

/**
 * OrderTypeHasAttributes entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OrderTypeHasAttributes implements java.io.Serializable {

	// Fields

	private OrderTypeHasAttributesId id;
	private Integer version;
	private Attribute attribute;
	private DoctorOrderType doctorOrderType;
	private String isMandatory;
	private Integer sequenceNumber;

	// Constructors

	/** default constructor */
	public OrderTypeHasAttributes() {
	}

	/** minimal constructor */
	public OrderTypeHasAttributes(OrderTypeHasAttributesId id,
			Attribute attribute, DoctorOrderType doctorOrderType) {
		this.id = id;
		this.attribute = attribute;
		this.doctorOrderType = doctorOrderType;
	}

	/** full constructor */
	public OrderTypeHasAttributes(OrderTypeHasAttributesId id,
			Attribute attribute, DoctorOrderType doctorOrderType,
			String isMandatory, Integer sequenceNumber) {
		this.id = id;
		this.attribute = attribute;
		this.doctorOrderType = doctorOrderType;
		this.isMandatory = isMandatory;
		this.sequenceNumber = sequenceNumber;
	}

	// Property accessors

	public OrderTypeHasAttributesId getId() {
		return this.id;
	}

	public void setId(OrderTypeHasAttributesId id) {
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

	public DoctorOrderType getDoctorOrderType() {
		return this.doctorOrderType;
	}

	public void setDoctorOrderType(DoctorOrderType doctorOrderType) {
		this.doctorOrderType = doctorOrderType;
	}

	public String getIsMandatory() {
		return this.isMandatory;
	}

	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
	}

	public Integer getSequenceNumber() {
		return this.sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

}