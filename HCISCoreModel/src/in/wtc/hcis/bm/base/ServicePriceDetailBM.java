/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.util.Date;

/**
 * @author Bhavesh
 *
 */
public class ServicePriceDetailBM {

	/**
	 * 
	 */
	
	private String serviceCode;
	private String serviceName;
	private Double serviceCharge;
	private Date effectiveFromDt;
	private Date effectiveToDt;
	private Date createdDt;
	private String createdBy;
	private String processed;
	
	public ServicePriceDetailBM() {
		// TODO Auto-generated constructor stub
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Double getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(Double serviceCharge) {
		this.serviceCharge = serviceCharge;
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

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getProcessed() {
		return processed;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}

}
