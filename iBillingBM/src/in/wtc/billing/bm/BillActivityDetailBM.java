/**
 * 
 */
package in.wtc.billing.bm;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class BillActivityDetailBM implements Serializable {
	private Long billNumber;
	private String activityTypeCd;
	private Date activityTime;
	private String remarks;
	
	public Long getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(Long billNumber) {
		this.billNumber = billNumber;
	}
	public String getActivityTypeCd() {
		return activityTypeCd;
	}
	public void setActivityTypeCd(String activityTypeCd) {
		this.activityTypeCd = activityTypeCd;
	}
	public Date getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
