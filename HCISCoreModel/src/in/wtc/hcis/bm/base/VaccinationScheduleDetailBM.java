/**
 * 
 */
package in.wtc.hcis.bm.base;


import java.io.Serializable;
import java.util.List;

/**
 * @author Vinay Kurudi
 *
 */
public class VaccinationScheduleDetailBM implements Serializable {
	
	private String scheduleName;
	private Integer periodInDays;
	private CodeAndDescription vaccinationName;
	private CodeAndDescription vaccinationType;
	private String age;
	private String dosage;
	private String userName;
	private Boolean deletedFlag;
	private Integer seqNbr;
	private String recordStatus;

	public Integer getPeriodInDays() {
		return periodInDays;
	}
	public void setPeriodInDays(Integer periodInDays) {
		this.periodInDays = periodInDays;
	}	
	public CodeAndDescription getVaccinationName() {
		return vaccinationName;
	}
	public void setVaccinationName(CodeAndDescription vaccinationName) {
		this.vaccinationName = vaccinationName;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getSeqNbr() {
		return seqNbr;
	}
	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}
	public CodeAndDescription getVaccinationType() {
		return vaccinationType;
	}
	public void setVaccinationType(CodeAndDescription vaccinationType) {
		this.vaccinationType = vaccinationType;
	}
	public String getScheduleName() {
		return scheduleName;
	}
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}
	public Boolean getDeletedFlag() {
		return deletedFlag;
	}
	public void setDeletedFlag(Boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}	
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public VaccinationScheduleDetailBM() {
	}
	
}
