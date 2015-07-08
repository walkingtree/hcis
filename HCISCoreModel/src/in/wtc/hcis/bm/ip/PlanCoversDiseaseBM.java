/**
 * 
 */
package in.wtc.hcis.bm.ip;

import java.util.Date;

import in.wtc.hcis.bm.base.CodeAndDescription;

/**
 * @author Bhavesh
 *
 */
public class PlanCoversDiseaseBM {
	
	private CodeAndDescription planName;
	private CodeAndDescription diseaceName; 		//Not null
	private String percentAbsInd;					//(A - Absolute/P - Percentage)
	private Double coverageAmt;
	private String isCoverdFlag;					//Not null(Y/N)
	private Date createdDate;
	private String createdBy;
	
	
	public CodeAndDescription getPlanName() {
		return planName;
	}
	public void setPlanName(CodeAndDescription planName) {
		this.planName = planName;
	}
	public CodeAndDescription getDiseaceName() {
		return diseaceName;
	}
	public void setDiseaceName(CodeAndDescription diseaceName) {
		this.diseaceName = diseaceName;
	}
	public String getPercentAbsInd() {
		return percentAbsInd;
	}
	public void setPercentAbsInd(String percentAbsInd) {
		this.percentAbsInd = percentAbsInd;
	}
	public Double getCoverageAmt() {
		return coverageAmt;
	}
	public void setCoverageAmt(Double coverageAmt) {
		this.coverageAmt = coverageAmt;
	}
	public String getIsCoverdFlag() {
		return isCoverdFlag;
	}
	public void setIsCoverdFlag(String isCoverdFlag) {
		this.isCoverdFlag = isCoverdFlag;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


}
