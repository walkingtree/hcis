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
public class ServicePackageSummaryBM implements Serializable {
	private String packageId;
	private CodeAndDescription servicePackageStatus;
	private String name;
	private String description;
	private String chargeOverrideLevel;
	private Double effectiveCharge;
	private Date effectiveFromDt;
	private Date effectiveToDt;
	private Date suspendedFromDt;
	private Date suspendedToDt;
	
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public CodeAndDescription getServicePackageStatus() {
		return servicePackageStatus;
	}
	public void setServicePackageStatus(CodeAndDescription servicePackageStatus) {
		this.servicePackageStatus = servicePackageStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getEffectiveCharge() {
		return effectiveCharge;
	}
	public void setEffectiveCharge(Double effectiveCharge) {
		this.effectiveCharge = effectiveCharge;
	}
	public Date getEffectiveFromDt() {
		return effectiveFromDt;
	}
	public void setEffectiveFromDt(Date effectiveFromDt) {
		this.effectiveFromDt = effectiveFromDt;
	}
	public Date getEffectiveToDt() {
		return effectiveToDt;
	}
	public void setEffectiveToDt(Date effectiveToDt) {
		this.effectiveToDt = effectiveToDt;
	}
	public Date getSuspendedFromDt() {
		return suspendedFromDt;
	}
	public void setSuspendedFromDt(Date suspendedFromDt) {
		this.suspendedFromDt = suspendedFromDt;
	}
	public Date getSuspendedToDt() {
		return suspendedToDt;
	}
	public void setSuspendedToDt(Date suspendedToDt) {
		this.suspendedToDt = suspendedToDt;
	}
	public String getChargeOverrideLevel() {
		return chargeOverrideLevel;
	}
	public void setChargeOverrideLevel(String chargeOverrideLevel) {
		this.chargeOverrideLevel = chargeOverrideLevel;
	}
	
	
}
