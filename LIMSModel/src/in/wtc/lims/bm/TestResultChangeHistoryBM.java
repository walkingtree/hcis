/**
 * 
 */
package in.wtc.lims.bm;

import java.util.Date;

/**
 * @author Bhavesh
 *
 * Conveys the test results value change history
 */
public class TestResultChangeHistoryBM {


	private String patientTestId;
	private Integer seqNbr;
	private String attributeCode;
	private String attributeName;
	private Date   createdDtm;
	private String attributeOldValue;
	private String attributeValue;
	private String createdBy;
	
	public String getPatientTestId() {
		return patientTestId;
	}
	public void setPatientTestId(String patientTestId) {
		this.patientTestId = patientTestId;
	}
	public Integer getSeqNbr() {
		return seqNbr;
	}
	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}
	public String getAttributeCode() {
		return attributeCode;
	}
	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}
	public String getAttributeOldValue() {
		return attributeOldValue;
	}
	public void setAttributeOldValue(String attributeOldValue) {
		this.attributeOldValue = attributeOldValue;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public Date getCreatedDtm() {
		return createdDtm;
	}
	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

}
