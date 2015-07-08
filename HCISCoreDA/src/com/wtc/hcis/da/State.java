package com.wtc.hcis.da;

/**
 * State entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class State implements java.io.Serializable {

	// Fields

	private StateId id;
	private Integer version;
	private String description;
	private Short active;

	// Constructors

	/** default constructor */
	public State() {
	}

	/** minimal constructor */
	public State(StateId id) {
		this.id = id;
	}

	/** full constructor */
	public State(StateId id, String description, Short active) {
		this.id = id;
		this.description = description;
		this.active = active;
	}

	// Property accessors

	public StateId getId() {
		return this.id;
	}

	public void setId(StateId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

}