package com.wtc.hcis.da;

/**
 * SystemParameter entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SystemParameter implements java.io.Serializable {

	// Fields

	private String attrName;
	private String attrLabel;
	private String attrValue;
	private String dataType;
	private String attrWidth;
	private String dataProvider;
	private String attrDesc;

	// Constructors

	/** default constructor */
	public SystemParameter() {
	}

	/** minimal constructor */
	public SystemParameter(String attrLabel, String attrValue, String dataType) {
		this.attrLabel = attrLabel;
		this.attrValue = attrValue;
		this.dataType = dataType;
	}

	/** full constructor */
	public SystemParameter(String attrLabel, String attrValue, String dataType,
			String attrWidth, String dataProvider, String attrDesc) {
		this.attrLabel = attrLabel;
		this.attrValue = attrValue;
		this.dataType = dataType;
		this.attrWidth = attrWidth;
		this.dataProvider = dataProvider;
		this.attrDesc = attrDesc;
	}

	// Property accessors

	public String getAttrName() {
		return this.attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrLabel() {
		return this.attrLabel;
	}

	public void setAttrLabel(String attrLabel) {
		this.attrLabel = attrLabel;
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

	public String getAttrWidth() {
		return this.attrWidth;
	}

	public void setAttrWidth(String attrWidth) {
		this.attrWidth = attrWidth;
	}

	public String getDataProvider() {
		return this.dataProvider;
	}

	public void setDataProvider(String dataProvider) {
		this.dataProvider = dataProvider;
	}

	public String getAttrDesc() {
		return this.attrDesc;
	}

	public void setAttrDesc(String attrDesc) {
		this.attrDesc = attrDesc;
	}

}