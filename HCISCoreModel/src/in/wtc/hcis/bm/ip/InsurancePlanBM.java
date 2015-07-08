/**
 * 
 */
package in.wtc.hcis.bm.ip;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class InsurancePlanBM implements Serializable{
	
	private String planCode;
	private String insurerName;
	private String planName;
	private Date   validFromDt;
	private Date   validToDt;
	private Double totalCoverageAmt;
	private String percentAbsInd;
	private Date   createDtm;
	private String createdBy;
	private Date   lastModifiedDt;
	private String lastModifiedBy;
	
	private List<PlanHasServicesBM>   planHasServicesBMList;
	private List<PlanCoversDiseaseBM> planCoversDiseaseBMList;
	
	
	public String getPlanCode() {
		return planCode;
	}

	public String getPlanName() {
		return planName;
	}
	public Date getValidFromDt() {
		return validFromDt;
	}
	public Date getValidToDt() {
		return validToDt;
	}
	public Double getTotalCoverageAmt() {
		return totalCoverageAmt;
	}
	public String getPercentAbsInd() {
		return percentAbsInd;
	}
	public Date getLastModifiedDt() {
		return lastModifiedDt;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public List<PlanHasServicesBM> getPlanHasServicesBMList() {
		return planHasServicesBMList;
	}
	public void setPlanHasServicesBMList(
			List<PlanHasServicesBM> planHasServicesBMList) {
		this.planHasServicesBMList = planHasServicesBMList;
	}
	public List<PlanCoversDiseaseBM> getPlanCoversDiseaseBMList() {
		return planCoversDiseaseBMList;
	}
	public void setPlanCoversDiseaseBMList(
			List<PlanCoversDiseaseBM> planCoversDiseaseBMList) {
		this.planCoversDiseaseBMList = planCoversDiseaseBMList;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public void setValidFromDt(Date validFromDt) {
		this.validFromDt = validFromDt;
	}
	public void setValidToDt(Date validToDt) {
		this.validToDt = validToDt;
	}
	public void setTotalCoverageAmt(Double totalCoverageAmt) {
		this.totalCoverageAmt = totalCoverageAmt;
	}
	public void setPercentAbsInd(String percentAbsInd) {
		this.percentAbsInd = percentAbsInd;
	}
	public void setLastModifiedDt(Date lastModifiedDt) {
		this.lastModifiedDt = lastModifiedDt;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public Date getCreateDtm() {
		return createDtm;
	}
	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getInsurerName() {
		return insurerName;
	}

	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}
	

}
