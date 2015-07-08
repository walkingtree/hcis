/**
 * 
 */
package in.wtc.lims.bm;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class LabTestBM implements Serializable {

	/**
	 * 
	 */
	
	private CodeAndDescription testType;
	private CodeAndDescription availableForGender;
	private CodeAndDescription techniqueReagent;
	private CodeAndDescription lab;
	private String preRequisite;
	private String minTimeRequired;
	private String reviewRequired;
	private String defaultReviewerId;
	private String createdBy;
	private Date createdDtm;
	private Integer testTemplateId;
	private String isTechnique;
	
	private List <LabTestSpecimenAssocBM> labTestSpecimenAssocBMList;
	private List <LabTestAttributeAssocBM> labTestAttributeAssocBMList;
	
	public LabTestBM() {
		// TODO Auto-generated constructor stub
		
		
	}

	public CodeAndDescription getTestType() {
		return testType;
	}

	public void setTestType(CodeAndDescription testType) {
		this.testType = testType;
	}

	public CodeAndDescription getAvailableForGender() {
		return availableForGender;
	}

	public void setAvailableForGender(CodeAndDescription availableForGender) {
		this.availableForGender = availableForGender;
	}

	public CodeAndDescription getTechniqueReagent() {
		return techniqueReagent;
	}

	public void setTechniqueReagent(CodeAndDescription techniqueReagent) {
		this.techniqueReagent = techniqueReagent;
	}

	public String getPreRequisite() {
		return preRequisite;
	}

	public void setPreRequisite(String preRequisite) {
		this.preRequisite = preRequisite;
	}

	public String getMinTimeRequired() {
		return minTimeRequired;
	}

	public void setMinTimeRequired(String minTimeRequired) {
		this.minTimeRequired = minTimeRequired;
	}

	public String getReviewRequired() {
		return reviewRequired;
	}

	public void setReviewRequired(String reviewRequired) {
		this.reviewRequired = reviewRequired;
	}

	public String getDefaultReviewerId() {
		return defaultReviewerId;
	}

	public void setDefaultReviewerId(String defaultReviewerId) {
		this.defaultReviewerId = defaultReviewerId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDtm() {
		return createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	public List<LabTestSpecimenAssocBM> getLabTestSpecimenAssocBMList() {
		return labTestSpecimenAssocBMList;
	}

	public void setLabTestSpecimenAssocBMList(
			List<LabTestSpecimenAssocBM> labTestSpecimenAssocBMList) {
		this.labTestSpecimenAssocBMList = labTestSpecimenAssocBMList;
	}

	public List<LabTestAttributeAssocBM> getLabTestAttributeAssocBMList() {
		return labTestAttributeAssocBMList;
	}

	public void setLabTestAttributeAssocBMList(
			List<LabTestAttributeAssocBM> labTestAttributeAssocBMList) {
		this.labTestAttributeAssocBMList = labTestAttributeAssocBMList;
	}

	public Integer getTestTemplateId() {
		return testTemplateId;
	}

	public void setTestTemplateId(Integer testTemplateId) {
		this.testTemplateId = testTemplateId;
	}

	public CodeAndDescription getLab() {
		return lab;
	}

	public void setLab(CodeAndDescription lab) {
		this.lab = lab;
	}

	public String getIsTechnique() {
		return isTechnique;
	}

	public void setIsTechnique(String isTechnique) {
		this.isTechnique = isTechnique;
	}

}
