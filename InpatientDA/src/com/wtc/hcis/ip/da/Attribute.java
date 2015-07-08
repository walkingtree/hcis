package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * Attribute entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Attribute implements java.io.Serializable {

	// Fields

	private String name;
	private String description;
	private String type;
	private String provider;
	private Set orderAttributeValues = new HashSet(0);
	private Set orderTypeHasAttributeses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Attribute() {
	}

	/** minimal constructor */
	public Attribute(String name, String description, String type) {
		this.name = name;
		this.description = description;
		this.type = type;
	}

	/** full constructor */
	public Attribute(String name, String description, String type,
			String provider, Set orderAttributeValues,
			Set orderTypeHasAttributeses) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.provider = provider;
		this.orderAttributeValues = orderAttributeValues;
		this.orderTypeHasAttributeses = orderTypeHasAttributeses;
	}

	// Property accessors

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Set getOrderAttributeValues() {
		return this.orderAttributeValues;
	}

	public void setOrderAttributeValues(Set orderAttributeValues) {
		this.orderAttributeValues = orderAttributeValues;
	}

	public Set getOrderTypeHasAttributeses() {
		return this.orderTypeHasAttributeses;
	}

	public void setOrderTypeHasAttributeses(Set orderTypeHasAttributeses) {
		this.orderTypeHasAttributeses = orderTypeHasAttributeses;
	}

}