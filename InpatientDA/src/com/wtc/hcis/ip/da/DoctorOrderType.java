package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * DoctorOrderType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderType implements java.io.Serializable {

	// Fields

	private String orderTypeCd;
	private String orderTypeDesc;
	private Set orderTypeHasAttributeses = new HashSet(0);
	private Set doctorOrderTemplates = new HashSet(0);
	private Set doctorOrders = new HashSet(0);

	// Constructors

	/** default constructor */
	public DoctorOrderType() {
	}

	/** minimal constructor */
	public DoctorOrderType(String orderTypeCd) {
		this.orderTypeCd = orderTypeCd;
	}

	/** full constructor */
	public DoctorOrderType(String orderTypeCd, String orderTypeDesc,
			Set orderTypeHasAttributeses, Set doctorOrderTemplates,
			Set doctorOrders) {
		this.orderTypeCd = orderTypeCd;
		this.orderTypeDesc = orderTypeDesc;
		this.orderTypeHasAttributeses = orderTypeHasAttributeses;
		this.doctorOrderTemplates = doctorOrderTemplates;
		this.doctorOrders = doctorOrders;
	}

	// Property accessors

	public String getOrderTypeCd() {
		return this.orderTypeCd;
	}

	public void setOrderTypeCd(String orderTypeCd) {
		this.orderTypeCd = orderTypeCd;
	}

	public String getOrderTypeDesc() {
		return this.orderTypeDesc;
	}

	public void setOrderTypeDesc(String orderTypeDesc) {
		this.orderTypeDesc = orderTypeDesc;
	}

	public Set getOrderTypeHasAttributeses() {
		return this.orderTypeHasAttributeses;
	}

	public void setOrderTypeHasAttributeses(Set orderTypeHasAttributeses) {
		this.orderTypeHasAttributeses = orderTypeHasAttributeses;
	}

	public Set getDoctorOrderTemplates() {
		return this.doctorOrderTemplates;
	}

	public void setDoctorOrderTemplates(Set doctorOrderTemplates) {
		this.doctorOrderTemplates = doctorOrderTemplates;
	}

	public Set getDoctorOrders() {
		return this.doctorOrders;
	}

	public void setDoctorOrders(Set doctorOrders) {
		this.doctorOrders = doctorOrders;
	}

}