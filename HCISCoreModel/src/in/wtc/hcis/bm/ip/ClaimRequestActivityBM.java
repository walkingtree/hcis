/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;
import java.util.Date;



/**
 * @author Alok Ranjan
 *
 */
public class ClaimRequestActivityBM implements Serializable {
	static final long serialVersionUID = 200907311557l;
	
	private Long requestSequenceNbr;
	private CodeAndDescription activityType;
	private Date creationDtm;
	private CodeAndDescription sponsorClaimStatus;
	private String createdBy;
	private String remarks;
	
	


	public Long getRequestSequenceNbr() {
		return requestSequenceNbr;
	}
	public void setRequestSequenceNbr(Long requestSequenceNbr) {
		this.requestSequenceNbr = requestSequenceNbr;
	}
	public Date getCreationDtm() {
		return creationDtm;
	}
	public void setCreationDtm(Date creationDtm) {
		this.creationDtm = creationDtm;
	}
	public CodeAndDescription getSponsorClaimStatus() {
		return sponsorClaimStatus;
	}
	public void setSponsorClaimStatus(CodeAndDescription sponsorClaimStatus) {
		this.sponsorClaimStatus = sponsorClaimStatus;
	}
	public CodeAndDescription getActivityType() {
		return activityType;
	}
	public void setActivityType(CodeAndDescription activityType) {
		this.activityType = activityType;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
