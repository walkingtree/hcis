/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.util.Date;

/**
 * @author Sandeep
 *
 */
public class VaccinationBM implements java.io.Serializable {
	private String vaccinationCd;
	private Integer version;
	private String vaccinationName;
	private String ageRange;
	private String substituteFor;
	private String activeFlag;
	private String userId;
	private Date lastModifiedDtm;
	
	public VaccinationBM() {
	}
	public String getVaccinationCd() {
		return vaccinationCd;
	}
	public void setVaccinationCd(String vaccinationCd) {
		this.vaccinationCd = vaccinationCd;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getVaccinationName() {
		return vaccinationName;
	}
	public void setVaccinationName(String vaccinationName) {
		this.vaccinationName = vaccinationName;
	}
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	public String getSubstituteFor() {
		return substituteFor;
	}
	public void setSubstituteFor(String substituteFor) {
		this.substituteFor = substituteFor;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getLastModifiedDtm() {
		return lastModifiedDtm;
	}
	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}
}
