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
public class FnclChargesBM implements Serializable {
	private Long billNbr;
	private String processName;
	private String chargeTypeName;	
	private String accountId;
	private Double chargeAmount;
	private Date creationDate;
	private String remarks;
	
	public Long getBillNbr() {
		return billNbr;
	}
	public void setBillNbr(Long billNbr) {
		this.billNbr = billNbr;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getChargeTypeName() {
		return chargeTypeName;
	}
	public void setChargeTypeName(String chargeTypeName) {
		this.chargeTypeName = chargeTypeName;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Double getChargeAmount() {
		return chargeAmount;
	}
	public void setChargeAmount(Double chargeAmount) {
		this.chargeAmount = chargeAmount;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
