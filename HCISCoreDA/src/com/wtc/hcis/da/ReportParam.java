package com.wtc.hcis.da;

/**
 * ReportParam entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ReportParam implements java.io.Serializable {

	// Fields

	private ReportParamId id;
	private String widgetLabel;
	private Integer widgetLength;
	private String xtype;
	private String dataProviderMethod;
	private String requiredFlag;
	private Integer widgetSeqNbr;

	// Constructors

	/** default constructor */
	public ReportParam() {
	}

	/** minimal constructor */
	public ReportParam(ReportParamId id, String xtype, String requiredFlag) {
		this.id = id;
		this.xtype = xtype;
		this.requiredFlag = requiredFlag;
	}

	/** full constructor */
	public ReportParam(ReportParamId id, String widgetLabel,
			Integer widgetLength, String xtype, String dataProviderMethod,
			String requiredFlag, Integer widgetSeqNbr) {
		this.id = id;
		this.widgetLabel = widgetLabel;
		this.widgetLength = widgetLength;
		this.xtype = xtype;
		this.dataProviderMethod = dataProviderMethod;
		this.requiredFlag = requiredFlag;
		this.widgetSeqNbr = widgetSeqNbr;
	}

	// Property accessors

	public ReportParamId getId() {
		return this.id;
	}

	public void setId(ReportParamId id) {
		this.id = id;
	}

	public String getWidgetLabel() {
		return this.widgetLabel;
	}

	public void setWidgetLabel(String widgetLabel) {
		this.widgetLabel = widgetLabel;
	}

	public Integer getWidgetLength() {
		return this.widgetLength;
	}

	public void setWidgetLength(Integer widgetLength) {
		this.widgetLength = widgetLength;
	}

	public String getXtype() {
		return this.xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	public String getDataProviderMethod() {
		return this.dataProviderMethod;
	}

	public void setDataProviderMethod(String dataProviderMethod) {
		this.dataProviderMethod = dataProviderMethod;
	}

	public String getRequiredFlag() {
		return this.requiredFlag;
	}

	public void setRequiredFlag(String requiredFlag) {
		this.requiredFlag = requiredFlag;
	}

	public Integer getWidgetSeqNbr() {
		return this.widgetSeqNbr;
	}

	public void setWidgetSeqNbr(Integer widgetSeqNbr) {
		this.widgetSeqNbr = widgetSeqNbr;
	}

}