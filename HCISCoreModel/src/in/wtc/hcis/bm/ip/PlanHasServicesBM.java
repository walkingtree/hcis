/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class PlanHasServicesBM implements Serializable {
	
 private CodeAndDescription planName;              
 private CodeAndDescription serviceName;	//Not null
 private String isCoverdFlag;				//Not null (Y/N)
 private String percetAbsInd;				//(A - Absolute/P - Percentage)
 private Double coverageAmt;
 private Date createdDate;
 private String createdBy;
	
 
public CodeAndDescription getPlanName() {
	return planName;
}
public void setPlanName(CodeAndDescription planName) {
	this.planName = planName;
}
public CodeAndDescription getServiceName() {
	return serviceName;
}
public void setServiceName(CodeAndDescription serviceName) {
	this.serviceName = serviceName;
}
public String getIsCoverdFlag() {
	return isCoverdFlag;
}
public void setIsCoverdFlag(String isCoverdFlag) {
	this.isCoverdFlag = isCoverdFlag;
}
public String getPercetAbsInd() {
	return percetAbsInd;
}
public void setPercetAbsInd(String percetAbsInd) {
	this.percetAbsInd = percetAbsInd;
}
public Double getCoverageAmt() {
	return coverageAmt;
}
public void setCoverageAmt(Double coverageAmt) {
	this.coverageAmt = coverageAmt;
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
