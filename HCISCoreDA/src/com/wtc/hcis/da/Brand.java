package com.wtc.hcis.da;

import java.util.HashSet;
import java.util.Set;

/**
 * Brand entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Brand implements java.io.Serializable {

	// Fields

	private String brandCode;
	private String description;
	private Short active;
	private Set medicineses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Brand() {
	}

	/** full constructor */
	public Brand(String description, Short active, Set medicineses) {
		this.description = description;
		this.active = active;
		this.medicineses = medicineses;
	}

	// Property accessors

	public String getBrandCode() {
		return this.brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getActive() {
		return this.active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

	public Set getMedicineses() {
		return this.medicineses;
	}

	public void setMedicineses(Set medicineses) {
		this.medicineses = medicineses;
	}

}