/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;


/**
 * Used for generic entity configuration
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class EntityBM implements Serializable {

	private Integer 			entityId;
	private CodeAndDescription 	nationality;
	private CodeAndDescription 	saluation;
	private CodeAndDescription 	gender;
	private ContactDetailsBM 	contactDetailsBM;
	private CodeAndDescription 	bloodGroup;
	private CodeAndDescription 	marital;
	private String 			   	name;
	private CodeAndDescription 	typeCode;	//Entity type
	private Date               	dob;
	private ImagePopertyBM 		image;
	private String 			   	knownLanguages;
	private String 				qualification;
	private String 				isPermanent;
	private Date 				joiningDt;
	private Date 				leavingDt;
	private String 				experience;
	private String 				referredBy;
	private String 				userId;
	private Date 				createdDtm;
	
	public Integer getEntityId() {
		return entityId;
	}
	
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	public CodeAndDescription getNationality() {
		return nationality;
	}
	public void setNationality(CodeAndDescription nationality) {
		this.nationality = nationality;
	}
	public CodeAndDescription getSaluation() {
		return saluation;
	}
	public void setSaluation(CodeAndDescription saluation) {
		this.saluation = saluation;
	}
	public CodeAndDescription getGender() {
		return gender;
	}
	public void setGender(CodeAndDescription gender) {
		this.gender = gender;
	}
	public ContactDetailsBM getContactDetailsBM() {
		return contactDetailsBM;
	}
	public void setContactDetailsBM(ContactDetailsBM contactDetailsBM) {
		this.contactDetailsBM = contactDetailsBM;
	}
	public CodeAndDescription getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(CodeAndDescription bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public CodeAndDescription getMarital() {
		return marital;
	}
	public void setMarital(CodeAndDescription marital) {
		this.marital = marital;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public ImagePopertyBM getImage() {
		return image;
	}
	public void setImage(ImagePopertyBM image) {
		this.image = image;
	}
	public String getKnownLanguages() {
		return knownLanguages;
	}
	public void setKnownLanguages(String knownLanguages) {
		this.knownLanguages = knownLanguages;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getIsPermanent() {
		return isPermanent;
	}
	public void setIsPermanent(String isPermanent) {
		this.isPermanent = isPermanent;
	}
	public Date getJoiningDt() {
		return joiningDt;
	}
	public void setJoiningDt(Date joiningDt) {
		this.joiningDt = joiningDt;
	}
	public Date getLeavingDt() {
		return leavingDt;
	}
	public void setLeavingDt(Date leavingDt) {
		this.leavingDt = leavingDt;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getReferredBy() {
		return referredBy;
	}
	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
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

	public CodeAndDescription getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(CodeAndDescription typeCode) {
		this.typeCode = typeCode;
	}
	
}
