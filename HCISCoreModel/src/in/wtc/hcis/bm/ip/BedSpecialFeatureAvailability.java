/**
 * 
 */
package in.wtc.hcis.bm.ip;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class BedSpecialFeatureAvailability implements Serializable {
	private String featureName;
	private String description;
	private Boolean availabilityFlag;
	
	public String getFeatureName() {
		return featureName;
	}
	
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getAvailabilityFlag() {
		return availabilityFlag;
	}
	
	public void setAvailabilityFlag(Boolean availabilityFlag) {
		this.availabilityFlag = availabilityFlag;
	}
	
	
}
