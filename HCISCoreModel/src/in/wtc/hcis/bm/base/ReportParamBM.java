/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.util.List;

/**
 * @author Bhavesh
 *
 */
public class ReportParamBM {
	
	private CodeAndDescription reportName;
	private String widgetName;
	private String widgetLabel;
	private Integer widgetLength;
	private Integer widgetSeqNbr;
	private String xtype;		//Data type of attribute value 
	
	/**
	 * This field contains  information from where to get data for the given attribute.
	 * (i.e. in case of combobox in UI, method and class name which returns the require data)
	 */
	private String dataProviderMethod;     
	private String requiredFlag;  //Y or N
	
	/**
	 * In case of MVL type, this field will contain List of CodeAndDescription objects to populate the
	 * combobox in UI.
	 */
	private List<CodeAndDescription> MVLvalueList;
	

	
	public ReportParamBM() {
		super();
	}
	
	public CodeAndDescription getReportName() {
		return reportName;
	}
	public void setReportName(CodeAndDescription reportName) {
		this.reportName = reportName;
	}
	public String getWidgetName() {
		return widgetName;
	}
	public void setWidgetName(String widgetName) {
		this.widgetName = widgetName;
	}
	public String getWidgetLabel() {
		return widgetLabel;
	}
	public void setWidgetLabel(String widgetLabel) {
		this.widgetLabel = widgetLabel;
	}
	public Integer getWidgetLength() {
		return widgetLength;
	}
	public void setWidgetLength(Integer widgetLength) {
		this.widgetLength = widgetLength;
	}
	public Integer getWidgetSeqNbr() {
		return widgetSeqNbr;
	}
	public void setWidgetSeqNbr(Integer widgetSeqNbr) {
		this.widgetSeqNbr = widgetSeqNbr;
	}
	public String getXtype() {
		return xtype;
	}
	public void setXtype(String xtype) {
		this.xtype = xtype;
	}
	public String getDataProviderMethod() {
		return dataProviderMethod;
	}
	public void setDataProviderMethod(String dataProviderMethod) {
		this.dataProviderMethod = dataProviderMethod;
	}
	public String getRequiredFlag() {
		return requiredFlag;
	}
	public void setRequiredFlag(String requiredFlag) {
		this.requiredFlag = requiredFlag;
	}

	public List<CodeAndDescription> getMVLvalueList() {
		return MVLvalueList;
	}

	public void setMVLvalueList(List<CodeAndDescription> lvalueList) {
		MVLvalueList = lvalueList;
	}

}
