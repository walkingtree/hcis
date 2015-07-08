/**
 * 
 */
package in.wtc.hcis.bm.ip;

import java.util.Date;
import java.util.List;

/**
 * @author Bhavesh
 *
 */
public class DiseaseBM {
	
	private String diseaseName;
	private String diseaseDesc;
	private String diseaseGroup;
	private String createdBy;
	private Date   createDtm;
	
	private List<DiseaseRequireServiceBM> diseaseRequireServiceBMList;

	
	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public String getDiseaseDesc() {
		return diseaseDesc;
	}

	public void setDiseaseDesc(String diseaseDesc) {
		this.diseaseDesc = diseaseDesc;
	}

	public String getDiseaseGroup() {
		return diseaseGroup;
	}

	public void setDiseaseGroup(String diseaseGroup) {
		this.diseaseGroup = diseaseGroup;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDtm() {
		return createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}

	public List<DiseaseRequireServiceBM> getDiseaseRequireServiceBMList() {
		return diseaseRequireServiceBMList;
	}

	public void setDiseaseRequireServiceBMList(
			List<DiseaseRequireServiceBM> diseaseRequireServiceBMList) {
		this.diseaseRequireServiceBMList = diseaseRequireServiceBMList;
	}

}
