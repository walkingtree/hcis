/**
 * 
 */
package in.wtc.lims.exception;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Rohit
 *
 */
public class Fault {
	private ResourceBundle RESULTS = ResourceBundle.getBundle("in.wtc.lims.exception.Faults", Locale.getDefault());

	private String faultType; //ERROR OR WARNING OR FATAL
	private String faultCode;
	private String faultMessage;

	public String getFaultCode() {
		return faultCode;
	}
	public String getFaultType() {
		return faultType;
	}
	public String getFaultMessage() {
		return faultMessage;
	}
	
	public Fault() {
		// Do nothing
	}
	
	public Fault(String faultID) {
		String tmp = RESULTS.getString(faultID);
		String[] strArr = tmp.split("\\|");
		this.faultType = strArr[0];
		this.faultCode = strArr[1];
		this.faultMessage = strArr[2];
	}
	
	public String toString() {
		String retStr = new String();
		retStr = "Fault type: " + this.faultType + " :Fault code: " + this.faultCode + " :Fault message: " + this.faultMessage;
		return retStr;
	}
}
