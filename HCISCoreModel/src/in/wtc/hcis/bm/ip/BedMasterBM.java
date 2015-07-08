/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;
import java.util.List;


/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class BedMasterBM implements Serializable {
	private String bedNumber;
	private CodeAndDescription nursingUnit;
	private CodeAndDescription bedStatus;
	private CodeAndDescription bedType;
	private CodeAndDescription roomNbr;
	private String floorNbr;
	private String siteName;
	private String description;
	private Double dailyCharge;
	private Double hourlyCharge;
	private Double depositAmount;
	private String modifiedBy;
	private List<BedSpecialFeatureAvailability>specialFeatureAvailabilityList;
	
	private Integer seqNbr; // to be used for grid(UI specific)
	
	public String getBedNumber() {
		return bedNumber;
	}
	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}
	public CodeAndDescription getBedStatus() {
		return bedStatus;
	}
	public void setBedStatus(CodeAndDescription bedStatus) {
		this.bedStatus = bedStatus;
	}
	public CodeAndDescription getBedType() {
		return bedType;
	}
	public void setBedType(CodeAndDescription bedType) {
		this.bedType = bedType;
	}
	public String getFloorNbr() {
		return floorNbr;
	}
	public void setFloorNbr(String floorNbr) {
		this.floorNbr = floorNbr;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getDailyCharge() {
		return dailyCharge;
	}
	public void setDailyCharge(Double dailyCharge) {
		this.dailyCharge = dailyCharge;
	}
	public Double getHourlyCharge() {
		return hourlyCharge;
	}
	public void setHourlyCharge(Double hourlyCharge) {
		this.hourlyCharge = hourlyCharge;
	}
	public Double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public List<BedSpecialFeatureAvailability> getSpecialFeatureAvailabilityList() {
		return specialFeatureAvailabilityList;
	}
	public void setSpecialFeatureAvailabilityList(
			List<BedSpecialFeatureAvailability> specialFeatureAvailabilityList) {
		this.specialFeatureAvailabilityList = specialFeatureAvailabilityList;
	}
	public Integer getSeqNbr() {
		return seqNbr;
	}
	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}
	public CodeAndDescription getNursingUnit() {
		return nursingUnit;
	}
	public void setNursingUnit(CodeAndDescription nursingUnit) {
		this.nursingUnit = nursingUnit;
	}
	public CodeAndDescription getRoomNbr() {
		return roomNbr;
	}
	public void setRoomNbr(CodeAndDescription roomNbr) {
		this.roomNbr = roomNbr;
	}
}
