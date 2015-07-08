/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;

/**
 * @author Rohit
 *
 */
public class ServiceMasterBM implements Cloneable, Serializable 
{
	static final long serialVersionUID = 200904220659L;

	private String serviceCode;
	private String serviceName;
	private String serviceCharge;
	private String profitCenterCode;	
	private String isActive;
	private CodeAndDescription serviceGroupCode;
	
	public ServiceMasterBM() {
	}
	/**
	 * @return the serviceCode
	 */
	public String getServiceCode() {
		return serviceCode;
	}
	/**
	 * @param serviceCode the serviceCode to set
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * @return the serviceCharge
	 */
	public String getServiceCharge() {
		return serviceCharge;
	}
	/**
	 * @param serviceCharge the serviceCharge to set
	 */
	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	/**
	 * @return the profitCenterCode
	 */
	public String getProfitCenterCode() {
		return profitCenterCode;
	}
	/**
	 * @param profitCenterCode the profitCenterCode to set
	 */
	public void setProfitCenterCode(String profitCenterCode) {
		this.profitCenterCode = profitCenterCode;
	}
	/**
	 * @return the isActive
	 */
	public String getIsActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the serviceGroupCode
	 */
	public CodeAndDescription getServiceGroupCode() {
		return serviceGroupCode;
	}
	/**
	 * @param serviceGroupCode the serviceGroupCode to set
	 */
	public void setServiceGroupCode(CodeAndDescription serviceGroupCode) {
		this.serviceGroupCode = serviceGroupCode;
	}

}
