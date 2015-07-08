/**
 * 
 */
package in.wtc.hcis.ot.bm;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class OTDetailBM implements Serializable {

	private String otId;
	private String bedNumber;
	private String name;
	private CodeAndDescription coordinator;
	private String createdBy;
	private Date createdDtm;
	
	private List<OtSurgeryAssoBM> otSurgeryAssoBMList;
	private List<OtActivityCheckListAssoBM> activityAssocBMList;

	public String getOtId() {
		return otId;
	}

	public void setOtId(String otId) {
		this.otId = otId;
	}

	public String getBedNumber() {
		return bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CodeAndDescription getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(CodeAndDescription coordinator) {
		this.coordinator = coordinator;
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

	public List<OtSurgeryAssoBM> getOtSurgeryAssoBMList() {
		return otSurgeryAssoBMList;
	}

	public void setOtSurgeryAssoBMList(List<OtSurgeryAssoBM> otSurgeryAssoBMList) {
		this.otSurgeryAssoBMList = otSurgeryAssoBMList;
	}

	public List<OtActivityCheckListAssoBM> getActivityAssocBMList() {
		return activityAssocBMList;
	}

	public void setActivityAssocBMList(
			List<OtActivityCheckListAssoBM> activityAssocBMList) {
		this.activityAssocBMList = activityAssocBMList;
	}
	
	
}
