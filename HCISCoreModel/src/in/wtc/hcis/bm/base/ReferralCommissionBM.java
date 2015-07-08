/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class ReferralCommissionBM implements Serializable {
	private Integer referralCode;
	private CodeAndDescription entityType;
	private CodeAndDescription commissionTypeInd;
	private Double commissionValue;
	private Date effectiveFromDate;
	private Date effectiveToDate;

	public ReferralCommissionBM() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CodeAndDescription getEntityType() {
		return entityType;
	}

	public void setEntityType(CodeAndDescription entityType) {
		this.entityType = entityType;
	}

	public CodeAndDescription getCommissionTypeInd() {
		return commissionTypeInd;
	}

	public void setCommissionTypeInd(CodeAndDescription commissionTypeInd) {
		this.commissionTypeInd = commissionTypeInd;
	}

	public Double getCommissionValue() {
		return commissionValue;
	}

	public void setCommissionValue(Double commissionValue) {
		this.commissionValue = commissionValue;
	}

	public Date getEffectiveFromDate() {
		return effectiveFromDate;
	}

	public void setEffectiveFromDate(Date effectiveFromDate) {
		this.effectiveFromDate = effectiveFromDate;
	}

	public Date getEffectiveToDate() {
		return effectiveToDate;
	}

	public void setEffectiveToDate(Date effectiveToDate) {
		this.effectiveToDate = effectiveToDate;
	}

	public Integer getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(Integer referralCode) {
		this.referralCode = referralCode;
	}

	
	
	
}
