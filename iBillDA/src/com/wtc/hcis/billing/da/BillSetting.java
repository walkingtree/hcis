package com.wtc.hcis.billing.da;

/**
 * BillSetting entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BillSetting implements java.io.Serializable {

	// Fields

	private String attrName;
	private String attrValue;
	private String dataType;
	private String attrDesc;

	// Constructors

	/** default constructor */
	public BillSetting() {
	}

	/** minimal constructor */
	public BillSetting(String attrName, String attrValue, String dataType) {
		this.attrName = attrName;
		this.attrValue = attrValue;
		this.dataType = dataType;
	}

	/** full constructor */
	public BillSetting(String attrName, String attrValue, String dataType,
			String attrDesc) {
		this.attrName = attrName;
		this.attrValue = attrValue;
		this.dataType = dataType;
		this.attrDesc = attrDesc;
	}

	// Property accessors

	public String getAttrName() {
		return this.attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrValue() {
		return this.attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getAttrDesc() {
		return this.attrDesc;
	}

	public void setAttrDesc(String attrDesc) {
		this.attrDesc = attrDesc;
	}

}