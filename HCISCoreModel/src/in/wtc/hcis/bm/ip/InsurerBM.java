/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.ContactDetailsBM;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class InsurerBM implements Serializable{

	private String insurerName;
	private String insurerDesc;
	private ContactDetailsBM contactDetailsBM;	
	private Date createdDate;
	private String createdBy;													//Not Null
	private String modifiedBy;
	private Date modifiedDate;
	private List<SponsorInsurerAssociationBM> sponsorInsurerAssociationBMList;
	
	private Integer seqNbr;  //For UI grid
	
	public String getInsurerName() {
		return insurerName;
	}
	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}
	public String getInsurerDesc() {
		return insurerDesc;
	}
	public void setInsurerDesc(String insurerDesc) {
		this.insurerDesc = insurerDesc;
	}
	public ContactDetailsBM getContactDetailsBM() {
		return contactDetailsBM;
	}
	public void setContactDetailsBM(ContactDetailsBM contactDetailsBM) {
		this.contactDetailsBM = contactDetailsBM;
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
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public List<SponsorInsurerAssociationBM> getSponsorInsurerAssociationBMList() {
		return sponsorInsurerAssociationBMList;
	}
	public void setSponsorInsurerAssociationBMList(
			List<SponsorInsurerAssociationBM> sponsorInsurerAssociationBMList) {
		this.sponsorInsurerAssociationBMList = sponsorInsurerAssociationBMList;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Integer getSeqNbr() {
		return seqNbr;
	}
	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}
}
