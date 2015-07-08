package com.wtc.hcis.da;

/**
 * BusinessRuleId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BusinessRuleId implements java.io.Serializable {

	// Fields

	private String businessRuleCode;
	private String ruleForCode;

	// Constructors

	/** default constructor */
	public BusinessRuleId() {
	}

	/** full constructor */
	public BusinessRuleId(String businessRuleCode, String ruleForCode) {
		this.businessRuleCode = businessRuleCode;
		this.ruleForCode = ruleForCode;
	}

	// Property accessors

	public String getBusinessRuleCode() {
		return this.businessRuleCode;
	}

	public void setBusinessRuleCode(String businessRuleCode) {
		this.businessRuleCode = businessRuleCode;
	}

	public String getRuleForCode() {
		return this.ruleForCode;
	}

	public void setRuleForCode(String ruleForCode) {
		this.ruleForCode = ruleForCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BusinessRuleId))
			return false;
		BusinessRuleId castOther = (BusinessRuleId) other;

		return ((this.getBusinessRuleCode() == castOther.getBusinessRuleCode()) || (this
				.getBusinessRuleCode() != null
				&& castOther.getBusinessRuleCode() != null && this
				.getBusinessRuleCode().equals(castOther.getBusinessRuleCode())))
				&& ((this.getRuleForCode() == castOther.getRuleForCode()) || (this
						.getRuleForCode() != null
						&& castOther.getRuleForCode() != null && this
						.getRuleForCode().equals(castOther.getRuleForCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getBusinessRuleCode() == null ? 0 : this
						.getBusinessRuleCode().hashCode());
		result = 37
				* result
				+ (getRuleForCode() == null ? 0 : this.getRuleForCode()
						.hashCode());
		return result;
	}

}