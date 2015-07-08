package in.wtc.hcis.bm.base;

@SuppressWarnings("serial")
public class ServiceChargeOverrideInfoBM implements java.io.Serializable {
	
	private String serviceCode;
	private Double overrideAmount;
	private String serviceCharge;
	
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
	public ServiceChargeOverrideInfoBM() {
		super();
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
	 * @return the overrideAmount
	 */
	public Double getOverrideAmount() {
		return overrideAmount;
	}
	/**
	 * @param overrideAmount the overrideAmount to set
	 */
	public void setOverrideAmount(Double overrideAmount) {
		this.overrideAmount = overrideAmount;
	}
	
	
}
