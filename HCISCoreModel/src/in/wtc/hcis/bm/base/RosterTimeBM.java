/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Rohit
 *
 */
public class RosterTimeBM implements Cloneable, Serializable 
{
	private Integer rosterCode;
	private Date	workingDate;
	private String 	startTime;
	private String 	endTime;
	private RoomBM 	roomBM;
	private Boolean active;
	
	public Integer getRosterCode() {
		return rosterCode;
	}
	public void setRosterCode(Integer rosterCode) {
		this.rosterCode = rosterCode;
	}
	public Date getWorkingDate() {
		return workingDate;
	}
	public void setWorkingDate(Date workingDate) {
		this.workingDate = workingDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public RoomBM getRoomBM() {
		return roomBM;
	}
	public void setRoomBM(RoomBM roomBM) {
		this.roomBM = roomBM;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}
