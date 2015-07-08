package in.wtc.lims.bm;

import java.util.List;

public class TemplateWidgetBM {
	
	private String widgetCode;
	private String widgetLabel;
	private String widgetType;
	private String sectionCode;
	private String valueType;
	private String widgetValueProvider;
	private Integer sequenceNbr;
	
	private List<CodeAndDescription> MVLValueList; //In case of valueType as observation 
	
	private Double minValue;
	private Double maxValue;
	
	private String widgetValue;
	
	private String isMandatory; //Whether this widget is mandatory for test
	
	public String getWidgetCode() {
		return widgetCode;
	}

	public void setWidgetCode(String widgetCode) {
		this.widgetCode = widgetCode;
	}

	public String getWidgetLabel() {
		return widgetLabel;
	}

	public void setWidgetLabel(String widgetLabel) {
		this.widgetLabel = widgetLabel;
	}

	public String getWidgetType() {
		return widgetType;
	}

	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}

	public String getSectionCode() {
		return sectionCode;
	}

	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getWidgetValueProvider() {
		return widgetValueProvider;
	}

	public void setWidgetValueProvider(String widgetValueProvider) {
		this.widgetValueProvider = widgetValueProvider;
	}

	public List<CodeAndDescription> getMVLValueList() {
		return MVLValueList;
	}

	public void setMVLValueList(List<CodeAndDescription> mVLValueList) {
		MVLValueList = mVLValueList;
	}

	public Integer getSequenceNbr() {
		return sequenceNbr;
	}

	public void setSequenceNbr(Integer sequenceNbr) {
		this.sequenceNbr = sequenceNbr;
	}

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public String getWidgetValue() {
		return widgetValue;
	}

	public void setWidgetValue(String widgetValue) {
		this.widgetValue = widgetValue;
	}

	public String getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
	}
	

}
