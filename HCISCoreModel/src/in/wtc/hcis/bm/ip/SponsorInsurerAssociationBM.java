/**
 * 
 */
package in.wtc.hcis.bm.ip;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class SponsorInsurerAssociationBM implements Serializable {
	
	private String sponsorName;
	private String insurerName;
	private Date   effectiveFromDate;
	private Date   effectiveToDate;
	private Date createdDate;
	private String createdBy;
	
	
	
	public String getSponsorName() {
		return sponsorName;
	}
	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}
	public String getInsurerName() {
		return insurerName;
	}
	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}
	public Date getEffectiveFromDate() {
		return effectiveFromDate;
	}
	public void setEffectiveFromDate(Date effectiveFromDate) {
		this.effectiveFromDate = effectiveFromDate;
	}
	public Date getEffectiveToDate() {
		return effectiveToDate;
	}
	public void setEffectiveToDate(Date effectiveToDate) {
		this.effectiveToDate = effectiveToDate;
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
