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
public class VaccinationScheduleBM implements Serializable{

	private String scheduleName;
	private String description;
	private String ageGroup;
	private List<VaccinationScheduleDetailBM> vaccinationScheduleDetailList;
	private String userName;
	private Boolean activeFlag;
	
	public String getScheduleName() {
		return scheduleName;
	}
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Boolean getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}
	public List<VaccinationScheduleDetailBM> getVaccinationScheduleDetailList() {
		return vaccinationScheduleDetailList;
	}
	public void setVaccinationScheduleDetailList(
			List<VaccinationScheduleDetailBM> vaccinationScheduleDetailList) {
		this.vaccinationScheduleDetailList = vaccinationScheduleDetailList;
	}
	public VaccinationScheduleBM() {
	}

	
}
