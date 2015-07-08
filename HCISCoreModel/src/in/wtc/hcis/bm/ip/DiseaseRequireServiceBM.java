/**
 * 
 */
package in.wtc.hcis.bm.ip;

import java.util.Date;

/**
 * @author Bhavesh
 *
 */
public class DiseaseRequireServiceBM {
	
	String diseaseName;
	String deseaseDesc;
	String serviceCode;
	String serviceName;
	String createdBy;
	Date   createDtm;
	
	
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getDeseaseDesc() {
		return deseaseDesc;
	}
	public void setDeseaseDesc(String deseaseDesc) {
		this.deseaseDesc = deseaseDesc;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
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
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}
