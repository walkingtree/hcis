/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;

/**
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class SponsorSlaBM implements Serializable {
	
	private String sponsorName;
	private CodeAndDescription activityType;
	private Double slaPeriod;
	private String slaUnit;
	
	
	public String getSponsorName() {
		return sponsorName;
	}
	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}
	public CodeAndDescription getActivityType() {
		return activityType;
	}
	public void setActivityType(CodeAndDescription activityType) {
		this.activityType = activityType;
	}
	public Double getSlaPeriod() {
		return slaPeriod;
	}
	public void setSlaPeriod(Double slaPeriod) {
		this.slaPeriod = slaPeriod;
	}
	public String getSlaUnit() {
		return slaUnit;
	}
	public void setSlaUnit(String slaUnit) {
		this.slaUnit = slaUnit;
	}

}
