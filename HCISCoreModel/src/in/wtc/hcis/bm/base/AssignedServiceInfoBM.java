/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;

/**
 * @author Ajit Kumar
 *
 */
@SuppressWarnings("serial")
public class AssignedServiceInfoBM implements Cloneable, Serializable 
{
	private String 	serviceCharge;
	private String  amount;
	private String 	serviceCode;
	
	public AssignedServiceInfoBM() {
	}

	public String getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

}
