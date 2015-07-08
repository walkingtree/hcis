package com.wtc.hcis.da;

/**
 * ReportParamId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ReportParamId implements java.io.Serializable {

	// Fields

	private String reportCode;
	private String widgetName;

	// Constructors

	/** default constructor */
	public ReportParamId() {
	}

	/** full constructor */
	public ReportParamId(String reportCode, String widgetName) {
		this.reportCode = reportCode;
		this.widgetName = widgetName;
	}

	// Property accessors

	public String getReportCode() {
		return this.reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	public String getWidgetName() {
		return this.widgetName;
	}

	public void setWidgetName(String widgetName) {
		this.widgetName = widgetName;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ReportParamId))
			return false;
		ReportParamId castOther = (ReportParamId) other;

		return ((this.getReportCode() == castOther.getReportCode()) || (this
				.getReportCode() != null
				&& castOther.getReportCode() != null && this.getReportCode()
				.equals(castOther.getReportCode())))
				&& ((this.getWidgetName() == castOther.getWidgetName()) || (this
						.getWidgetName() != null
						&& castOther.getWidgetName() != null && this
						.getWidgetName().equals(castOther.getWidgetName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getReportCode() == null ? 0 : this.getReportCode()
						.hashCode());
		result = 37
				* result
				+ (getWidgetName() == null ? 0 : this.getWidgetName()
						.hashCode());
		return result;
	}

}