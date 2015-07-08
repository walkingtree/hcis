package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * DischargeType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DischargeType implements java.io.Serializable {

	// Fields

	private String dischargeTypeCd;
	private String description;
	private String procedure;
	private Set dischargeOrders = new HashSet(0);

	// Constructors

	/** default constructor */
	public DischargeType() {
	}

	/** minimal constructor */
	public DischargeType(String dischargeTypeCd) {
		this.dischargeTypeCd = dischargeTypeCd;
	}

	/** full constructor */
	public DischargeType(String dischargeTypeCd, String description,
			String procedure, Set dischargeOrders) {
		this.dischargeTypeCd = dischargeTypeCd;
		this.description = description;
		this.procedure = procedure;
		this.dischargeOrders = dischargeOrders;
	}

	// Property accessors

	public String getDischargeTypeCd() {
		return this.dischargeTypeCd;
	}

	public void setDischargeTypeCd(String dischargeTypeCd) {
		this.dischargeTypeCd = dischargeTypeCd;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProcedure() {
		return this.procedure;
	}

	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public Set getDischargeOrders() {
		return this.dischargeOrders;
	}

	public void setDischargeOrders(Set dischargeOrders) {
		this.dischargeOrders = dischargeOrders;
	}

}