package com.wtc.hcis.da;

/**
 * LabTemplateWidget entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabTemplateWidget implements java.io.Serializable {

	// Fields

	private String widgetCode;
	private LabTestAttribute labTestAttribute;
	private String widgetLabel;
	private String widgetType;
	private String sectionCode;
	private String valueType;
	private String widgetValueProvider;

	// Constructors

	/** default constructor */
	public LabTemplateWidget() {
	}

	/** minimal constructor */
	public LabTemplateWidget(String widgetLabel, String widgetType,
			String sectionCode, String valueType) {
		this.widgetLabel = widgetLabel;
		this.widgetType = widgetType;
		this.sectionCode = sectionCode;
		this.valueType = valueType;
	}

	/** full constructor */
	public LabTemplateWidget(LabTestAttribute labTestAttribute,
			String widgetLabel, String widgetType, String sectionCode,
			String valueType, String widgetValueProvider) {
		this.labTestAttribute = labTestAttribute;
		this.widgetLabel = widgetLabel;
		this.widgetType = widgetType;
		this.sectionCode = sectionCode;
		this.valueType = valueType;
		this.widgetValueProvider = widgetValueProvider;
	}

	// Property accessors

	public String getWidgetCode() {
		return this.widgetCode;
	}

	public void setWidgetCode(String widgetCode) {
		this.widgetCode = widgetCode;
	}

	public LabTestAttribute getLabTestAttribute() {
		return this.labTestAttribute;
	}

	public void setLabTestAttribute(LabTestAttribute labTestAttribute) {
		this.labTestAttribute = labTestAttribute;
	}

	public String getWidgetLabel() {
		return this.widgetLabel;
	}

	public void setWidgetLabel(String widgetLabel) {
		this.widgetLabel = widgetLabel;
	}

	public String getWidgetType() {
		return this.widgetType;
	}

	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}

	public String getSectionCode() {
		return this.sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public String getValueType() {
		return this.valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getWidgetValueProvider() {
		return this.widgetValueProvider;
	}

	public void setWidgetValueProvider(String widgetValueProvider) {
		this.widgetValueProvider = widgetValueProvider;
	}

}