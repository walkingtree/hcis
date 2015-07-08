/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.util.Date;


/**
 * @author Bhavesh
 *
 */
public class VitalFieldBM {

	private String code;
	private String name;
	private String measurementUnitCd;
	private String fieldType;
	private String fieldGroup;
	
	
	private Date   forTime;
	private String value;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMeasurementUnitCd() {
		return measurementUnitCd;
	}
	public void setMeasurementUnitCd(String measurementUnitCd) {
		this.measurementUnitCd = measurementUnitCd;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldGroup() {
		return fieldGroup;
	}
	public void setFieldGroup(String fieldGroup) {
		this.fieldGroup = fieldGroup;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Date getForTime() {
		return forTime;
	}
	public void setForTime(Date forTime) {
		this.forTime = forTime;
	}
	

}
