/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



/**
 * @author Alok Ranjan
 *
 */
public class ServicePackageBM implements Serializable {

	private String packageId;
	private String name;
	private Date effectiveFromDt;
	private Date effectiveToDt;
	private CodeAndDescription servicePackageStatus;
	private String description;

	private String chargeOverrideLevel;

	private List<PackageHasServiceBM> serviceBMList;
	private Double packageCharge;
	
	private String discountType;
	private Double discountAmountPct;
	private Double effectiveCharge;
	
	private Date creationDate;
	private String createdBy;
	private String modifiedBy;
	private Date modificationDtm;


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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Double getPackageCharge() {
		return packageCharge;
	}

	public void setPackageCharge(Double packageCharge) {
		this.packageCharge = packageCharge;
	}

	public String getChargeOverrideLevel() {
		return chargeOverrideLevel;
	}

	public void setChargeOverrideLevel(String chargeOverrideLevel) {
		this.chargeOverrideLevel = chargeOverrideLevel;
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

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModificationDtm() {
		return modificationDtm;
	}

	public void setModificationDtm(Date modificationDtm) {
		this.modificationDtm = modificationDtm;
	}

	public List<PackageHasServiceBM> getServiceBMList() {
		return serviceBMList;
	}

	public void setServiceBMList(List<PackageHasServiceBM> serviceBMList) {
		this.serviceBMList = serviceBMList;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Double getDiscountAmountPct() {
		return discountAmountPct;
	}

	public void setDiscountAmountPct(Double discountAmountPct) {
		this.discountAmountPct = discountAmountPct;
	}
}
