/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.util.Date;

/**
 * @author Vinay Kurudi
 *
 */
public class RosterModel {

	private String		entityType;
	private Integer		entityId;
	private String 		entityName;
	private Integer 	rosterCode;
	private Date		workingDate;
	private String 		startTime;
	private String 		endTime;
	private RoomBM 		roomBM;
	private Boolean 	active;	
	private String 		weekDay;
	private String		isDoctorActive;
	
	
	public String getIsDoctorActive() {
		return isDoctorActive;
	}

	public void setIsDoctorActive(String isDoctorActive) {
		this.isDoctorActive = isDoctorActive;
	}

	/**
	 * 
	 */
	public RosterModel() {
		// TODO Auto-generated constructor stub
	}


	public String getEntityType() {
		return entityType;
	}


	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}


	public Integer getEntityId() {
		return entityId;
	}


	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}


	public String getEntityName() {
		return entityName;
	}


	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}


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


	public String getWeekDay() {
		return weekDay;
	}


	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	
}
