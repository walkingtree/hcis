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
public class BedPoolBM implements Serializable {
	private String bedPoolName;
	private String poolDesc;
	private Integer totalNumberOfBed;
	private Integer numberOfAvailableBeds;
	private List<BedPoolUnitTypeAsgmBM>bedPoolUnitTypeAsgm;
	
	public String getBedPoolName() {
		return bedPoolName;
	}
	public void setBedPoolName(String bedPoolName) {
		this.bedPoolName = bedPoolName;
	}
	public String getPoolDesc() {
		return poolDesc;
	}
	public void setPoolDesc(String poolDesc) {
		this.poolDesc = poolDesc;
	}
	public List<BedPoolUnitTypeAsgmBM> getBedPoolUnitTypeAsgm() {
		return bedPoolUnitTypeAsgm;
	}
	public void setBedPoolUnitTypeAsgm(
			List<BedPoolUnitTypeAsgmBM> bedPoolUnitTypeAsgm) {
		this.bedPoolUnitTypeAsgm = bedPoolUnitTypeAsgm;
	}

	public Integer getTotalNumberOfBed() {
		return totalNumberOfBed;
	}

	public void setTotalNumberOfBed(Integer totalNumberOfBed) {
		this.totalNumberOfBed = totalNumberOfBed;
	}

	public Integer getNumberOfAvailableBeds() {
		return numberOfAvailableBeds;
	}

	public void setNumberOfAvailableBeds(Integer numberOfAvailableBeds) {
		this.numberOfAvailableBeds = numberOfAvailableBeds;
	}
}
