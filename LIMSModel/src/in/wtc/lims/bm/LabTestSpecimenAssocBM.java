/**
 * 
 */
package in.wtc.lims.bm;

import java.io.Serializable;


/**
 * @author Bhavesh
 *
 */
public class LabTestSpecimenAssocBM implements Serializable {

	/**
	 * 
	 */
	
	private CodeAndDescription labSpecimen;
	private Integer quantity;
	private String unit;
	private String isMandatory;
	
	public LabTestSpecimenAssocBM() {
		// TODO Auto-generated constructor stub
	}

	
	public CodeAndDescription getLabSpecimen() {
		return labSpecimen;
	}

	public void setLabSpecimen(CodeAndDescription labSpecimen) {
		this.labSpecimen = labSpecimen;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
	}

}
