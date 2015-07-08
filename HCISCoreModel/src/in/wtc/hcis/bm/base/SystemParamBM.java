/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.util.List;

/**
 * @author Bhavesh
 *
 */
public class SystemParamBM {

	/**
	 * 
	 */
	public SystemParamBM() {

	}

	// Fields

	private String attrName;
	private String attrLabel;
	private String attrValue;
	private String dataType;
	private String attrDesc;
	private String attrWidth;
	
	/**
	 * In case of MVL type, this field will contain List of CodeAndDescription objects to populate the
	 * combobox in UI.
	 */
	private List<CodeAndDescription> MVLvalueList;
	
	
	public String getAttrName() {
		return attrName;
	}
	
	
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getAttrDesc() {
		return attrDesc;
	}
	public void setAttrDesc(String attrDesc) {
		this.attrDesc = attrDesc;
	}


	public List<CodeAndDescription> getMVLvalueList() {
		return MVLvalueList;
	}


	public void setMVLvalueList(List<CodeAndDescription> lvalueList) {
		MVLvalueList = lvalueList;
	}


	public String getAttrLabel() {
		return attrLabel;
	}


	public void setAttrLabel(String attrLabel) {
		this.attrLabel = attrLabel;
	}


	public String getAttrWidth() {
		return attrWidth;
	}


	public void setAttrWidth(String attrWidth) {
		this.attrWidth = attrWidth;
	}

	
}
