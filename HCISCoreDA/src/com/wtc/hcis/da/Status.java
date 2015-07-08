package com.wtc.hcis.da;

/**
 * Status entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Status implements java.io.Serializable {

	// Fields

	private StatusId id;

	// Constructors

	/** default constructor */
	public Status() {
	}

	/** full constructor */
	public Status(StatusId id) {
		this.id = id;
	}

	// Property accessors

	public StatusId getId() {
		return this.id;
	}

	public void setId(StatusId id) {
		this.id = id;
	}

}