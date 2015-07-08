/**
 * 
 */
package in.wtc.hcis.ot.bm;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bhavesh
 *
 */
public class SurgeryStatusTimeBM implements Serializable {

	

	private String surgeryCode;
	private CodeAndDescription surgeryStatus;
	private Integer time;
	private String surgeonRequired;
	private String contributeToScheduling; //Y or N
	private String userId;
	private Date createdDtm;
	
	public String getSurgeryCode() {
		return surgeryCode;
	}
	public void setSurgeryCode(String surgeryCode) {
		this.surgeryCode = surgeryCode;
	}
	public CodeAndDescription getSurgeryStatus() {
		return surgeryStatus;
	}
	public void setSurgeryStatus(CodeAndDescription surgeryStatus) {
		this.surgeryStatus = surgeryStatus;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public String getSurgeonRequired() {
		return surgeonRequired;
	}
	public void setSurgeonRequired(String surgeonRequired) {
		this.surgeonRequired = surgeonRequired;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getCreatedDtm() {
		return createdDtm;
	}
	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}
	public String getContributeToScheduling() {
		return contributeToScheduling;
	}
	public void setContributeToScheduling(String contributeToScheduling) {
		this.contributeToScheduling = contributeToScheduling;
	}

	
}
