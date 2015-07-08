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
public class OtSurgeryAssoBM implements Serializable {

	private CodeAndDescription otName;
	private CodeAndDescription surgeryName;
	private String createdBy;
	private Date createdDate;
	
	public CodeAndDescription getOtName() {
		return otName;
	}
	public void setOtName(CodeAndDescription otName) {
		this.otName = otName;
	}
	public CodeAndDescription getSurgeryName() {
		return surgeryName;
	}
	public void setSurgeryName(CodeAndDescription surgeryName) {
		this.surgeryName = surgeryName;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
