/**
 * 
 */
package in.wtc.lims.bm;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class LabTestAttributeBM implements Serializable {

	private String attributeCode;
	private String attributeName;
	private CodeAndDescription unit;
	private CodeAndDescription type; 	// NUMERIC/TEXT/OBSERVATION
	private Double minValue;
	private Double maxValue;
	private String observationValue;
	private String createdBy;
	private Date createdDate;
	
	
	public String getAttributeCode() {
		return attributeCode;
	}
	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public CodeAndDescription getUnit() {
		return unit;
	}
	public void setUnit(CodeAndDescription unit) {
		this.unit = unit;
	}
	public CodeAndDescription getType() {
		return type;
	}
	public void setType(CodeAndDescription type) {
		this.type = type;
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
	public String getObservationValue() {
		return observationValue;
	}
	public void setObservationValue(String observationValue) {
		this.observationValue = observationValue;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
