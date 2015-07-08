/**
 * 
 */
package in.wtc.lims.bm;

import java.io.Serializable;

/**
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class LabTestAttributeAssocBM implements Serializable {

	/**
	 * 
	 */
	
	private CodeAndDescription labTestAttribute;
	private Double minValue;
	private Double maxValue;
	private String isMandatory;
	private Integer techniqueId;
	private String attributeType;

	
	
	public String getAttributeType() {
		return attributeType;
	}



	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}



	public LabTestAttributeAssocBM() {
		// TODO Auto-generated constructor stub
	}



	public CodeAndDescription getLabTestAttribute() {
		return labTestAttribute;
	}



	public void setLabTestAttribute(CodeAndDescription labTestAttribute) {
		this.labTestAttribute = labTestAttribute;
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



	public String getIsMandatory() {
		return isMandatory;
	}



	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
	}



	public Integer getTechniqueId() {
		return techniqueId;
	}



	public void setTechniqueId(Integer techniqueId) {
		this.techniqueId = techniqueId;
	}

}
