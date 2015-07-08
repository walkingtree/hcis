/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;



/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class PackageHasServiceBM implements Serializable {
	
	private CodeAndDescription service;
	private CodeAndDescription servicePackage;
	private Integer numberOfUnits;
	private Double effectiveCharge;
	private Double serviceCharge;
	private String discountType;
	private Double discountAmtPct;
	
	public CodeAndDescription getService() {
		return service;
	}
	public void setService(CodeAndDescription service) {
		this.service = service;
	}
	public CodeAndDescription getServicePackage() {
		return servicePackage;
	}
	public void setServicePackage(CodeAndDescription servicePackage) {
		this.servicePackage = servicePackage;
	}
	public Integer getNumberOfUnits() {
		return numberOfUnits;
	}
	public void setNumberOfUnits(Integer numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}
	public Double getEffectiveCharge() {
		return effectiveCharge;
	}
	public void setEffectiveCharge(Double effectiveCharge) {
		this.effectiveCharge = effectiveCharge;
	}
	public Double getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public Double getDiscountAmtPct() {
		return discountAmtPct;
	}
	public void setDiscountAmtPct(Double discountAmtPct) {
		this.discountAmtPct = discountAmtPct;
	}
	
	
}
