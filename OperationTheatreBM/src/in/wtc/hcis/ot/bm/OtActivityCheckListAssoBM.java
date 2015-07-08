/**
 * 
 */
package in.wtc.hcis.ot.bm;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class OtActivityCheckListAssoBM implements Serializable {

	private CodeAndDescription surgerStatus;
	private CodeAndDescription checkListName;
	private String applyWhen;  //'B' for before and 'A' for after
	private String userId;
	private Date createdDtm;

	

	public CodeAndDescription getCheckListName() {
		return checkListName;
	}
	public void setCheckListName(CodeAndDescription checkListName) {
		this.checkListName = checkListName;
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
	public CodeAndDescription getSurgerStatus() {
		return surgerStatus;
	}
	public void setSurgerStatus(CodeAndDescription surgerStatus) {
		this.surgerStatus = surgerStatus;
	}
	public String getApplyWhen() {
		return applyWhen;
	}
	public void setApplyWhen(String applyWhen) {
		this.applyWhen = applyWhen;
	}
	
}
