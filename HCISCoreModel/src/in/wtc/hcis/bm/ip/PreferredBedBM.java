/**
 * 
 */
package in.wtc.hcis.bm.ip;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class PreferredBedBM implements Serializable {

//	This field is shifted to BedMasteBM
	//	private List<BedSpecialFeatureAvailability>specialFeatureAvailabilityList;
	private Boolean allCriteriaMet;
	private Integer numberOfOptionalCriteriaMet;
	private BedMasterBM bedMasterBM;
	

	public Boolean getAllCriteriaMet() {
		return allCriteriaMet;
	}
	public void setAllCriteriaMet(Boolean allCriteriaMet) {
		this.allCriteriaMet = allCriteriaMet;
	}
	public Integer getNumberOfOptionalCriteriaMet() {
		return numberOfOptionalCriteriaMet;
	}
	public void setNumberOfOptionalCriteriaMet(Integer numberOfOptionalCriteriaMet) {
		this.numberOfOptionalCriteriaMet = numberOfOptionalCriteriaMet;
	}
	public BedMasterBM getBedMasterBM() {
		return bedMasterBM;
	}
	public void setBedMasterBM(BedMasterBM bedMasterBM) {
		this.bedMasterBM = bedMasterBM;
	} 
	
	
	
}
