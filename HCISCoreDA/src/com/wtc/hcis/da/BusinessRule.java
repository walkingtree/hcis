package com.wtc.hcis.da;

/**
 * BusinessRule entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BusinessRule implements java.io.Serializable {

	// Fields

	private BusinessRuleId id;
	private Integer version;
	private String rule;
	private Short active;

	// Constructors

	/** default constructor */
	public BusinessRule() {
	}

	/** minimal constructor */
	public BusinessRule(BusinessRuleId id) {
		this.id = id;
	}

	/** full constructor */
	public BusinessRule(BusinessRuleId id, String rule, Short active) {
		this.id = id;
		this.rule = rule;
		this.active = active;
	}

	// Property accessors

	public BusinessRuleId getId() {
		return this.id;
	}

	public void setId(BusinessRuleId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public Short getActive() {
		return this.active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

}