/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class BedPoolUnitTypeAsgmBM implements Serializable {
	private String poolName;
	private CodeAndDescription unitType;
//	private CodeAndDescription bedType;
	private Date effectiveFromDt;
	private Date effectiveToDate;
	
	public String getPoolName() {
		return poolName;
	}
	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}
	public CodeAndDescription getUnitType() {
		return unitType;
	}
	public void setUnitType(CodeAndDescription unitType ) {
		this.unitType = unitType;
	}
	
	public Date getEffectiveFromDt() {
		return effectiveFromDt;
	}
	public void setEffectiveFromDt(Date effectiveFromDt) {
		this.effectiveFromDt = effectiveFromDt;
	}
	public Date getEffectiveToDate() {
		return effectiveToDate;
	}
	public void setEffectiveToDate(Date effectiveToDate) {
		this.effectiveToDate = effectiveToDate;
	}
}
