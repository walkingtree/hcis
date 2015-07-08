/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;

/**
 * @author Welcome
 *
 */
@SuppressWarnings("serial")
public class ServiceSummaryBM implements Serializable {
	private String serviceCode;
	private CodeAndDescription serviceStatus;
	private String serviceName;
	private Double serviceCharge;
	
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
	public CodeAndDescription getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(CodeAndDescription serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	
}
