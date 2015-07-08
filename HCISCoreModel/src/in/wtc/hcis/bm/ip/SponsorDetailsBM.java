/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactDetailsBM;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @author Alok Ranjan
 *This business model will be used to for Both Sponsor and Insurer.
 *In case of insurer Use all sponsor prefixed fields  (i.e. sonsorName) as for Insurer.
 */
public class SponsorDetailsBM implements Serializable {
	static final long serialVersionUID = 200907311213l;
	
	private String sponsorsName;												//Not Null
	private CodeAndDescription sponsorType;										//Not Null
	private CodeAndDescription creditClass;
	private String sponsorDesc;
	private String accountNumber;
	private ContactDetailsBM contactDetailsBM;									//Not Null
	private Date createdDate;
	private String createdBy;													//Not Null
	private String modifiedBy;
	private List<SponsorInsurerAssociationBM> sponsorInsurerAssociationBMList;
	private List<SponsorSlaBM> sponsorSlaBMList; 
	
	private Integer seqNbr; //for UI grid
	
	public String getSponsorsName() {
		return sponsorsName;
	}
	public void setSponsorsName(String sponsorsName) {
		this.sponsorsName = sponsorsName;
	}
	public CodeAndDescription getSponsorType() {
		return sponsorType;
	}
	public void setSponsorType(CodeAndDescription sponsorType) {
		this.sponsorType = sponsorType;
	}
	public CodeAndDescription getCreditClass() {
		return creditClass;
	}
	public void setCreditClass(CodeAndDescription creditClass) {
		this.creditClass = creditClass;
	}
	public String getSponsorDesc() {
		return sponsorDesc;
	}
	public void setSponsorDesc(String sponsorDesc) {
		this.sponsorDesc = sponsorDesc;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public ContactDetailsBM getContactDetailsBM() {
		return contactDetailsBM;
	}
	public void setContactDetailsBM(ContactDetailsBM contactDetailsBM) {
		this.contactDetailsBM = contactDetailsBM;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
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
	public List<SponsorSlaBM> getSponsorSlaBMList() {
		return sponsorSlaBMList;
	}
	public void setSponsorSlaBMList(List<SponsorSlaBM> sponsorSlaBMList) {
		this.sponsorSlaBMList = sponsorSlaBMList;
	}
	public Integer getSeqNbr() {
		return seqNbr;
	}
	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
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
