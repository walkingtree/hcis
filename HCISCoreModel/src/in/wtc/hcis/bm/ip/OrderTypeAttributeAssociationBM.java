/**
 * 
 */
package in.wtc.hcis.bm.ip;

import java.util.List;

import in.wtc.hcis.bm.base.CodeAndDescription;

/**
 * @author Bhavesh
 *
 */
public class OrderTypeAttributeAssociationBM {
	
	private String attributeName;
	private String attributeDesc;
	private String type;		//Data type of attribute value 
	private Integer seqNbr;
	/**
	 * This field contains  information from where to get data for the given attribute.
	 * (i.e. in case of combobox in UI, method and class name which returns the require data)
	 */
	private String provider;     
	private String isMandatory;  //Y or N
	
	/*
	 * In case of MVL type, this field will contain List of CodeAndDescription objects to populate the
	 * combobox in UI.
	 */
	private List<CodeAndDescription> MVLvalueList;

	private CodeAndDescription orderType;
	
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getAttributeDesc() {
		return attributeDesc;
	}
	public void setAttributeDesc(String attributeDesc) {
		this.attributeDesc = attributeDesc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getIsMandatory() {
		return isMandatory;
	}
	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
	}
	public List<CodeAndDescription> getMVLvalueList() {
		return MVLvalueList;
	}
	public void setMVLvalueList(List<CodeAndDescription> lvalueList) {
		MVLvalueList = lvalueList;
	}
	public Integer getSeqNbr() {
		return seqNbr;
	}
	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}
	public CodeAndDescription getOrderType() {
		return orderType;
	}
	public void setOrderType(CodeAndDescription orderType) {
		this.orderType = orderType;
	}

}
