/**
 * 
 */
package in.wtc.ui.model;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.RoomBM;

import java.util.Date;

/**
 * @author Sandeep Kumar
 *
 */
public class DoctorAppointmentRosterModel {
	 private String startTime;
	 private String endTime;
	 private int recordId;
	 private String agenda;
	 private String remarks; 
	 private RoomBM roomBM;
	 private Date	workingDate;
	 private Boolean active;
	 private String	entityType;
	 private Integer entityId;
	 private String entityName;
	 private Integer rosterCode;
	 private String weekDay;
	 private Boolean isAppointment;
	 private String	isDoctorActive;
	 private String patientId;
	 private String bookingType;
	 private Integer appointmentNumber;
	 
	 
	public DoctorAppointmentRosterModel() {
	}
	
	public DoctorAppointmentRosterModel(String startTime, String endTime,
			int recordId, String agenda, String remarks, RoomBM roomBM,
			Date workingDate, Boolean active, String entityType,
			Integer entityId, String entityName, Integer rosterCode,
			String weekDay, String isDoctorActive) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
		this.recordId = recordId;
		this.agenda = agenda;
		this.remarks = remarks;
		this.roomBM = roomBM;
		this.workingDate = workingDate;
		this.active = active;
		this.entityType = entityType;
		this.entityId = entityId;
		this.entityName = entityName;
		this.rosterCode = rosterCode;
		this.weekDay = weekDay;
		this.isDoctorActive = isDoctorActive;
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
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public String getAgenda() {
		return agenda;
	}
	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public RoomBM getRoomBM() {
		return roomBM;
	}
	public void setRoomBM(RoomBM roomBM) {
		this.roomBM = roomBM;
	}
	public Date getWorkingDate() {
		return workingDate;
	}
	public void setWorkingDate(Date workingDate) {
		this.workingDate = workingDate;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
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
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	public String getIsDoctorActive() {
		return isDoctorActive;
	}
	public void setIsDoctorActive(String isDoctorActive) {
		this.isDoctorActive = isDoctorActive;
	}
	public Boolean getIsAppointment() {
		return isAppointment;
	}
	public void setIsAppointment(Boolean isAppointment) {
		this.isAppointment = isAppointment;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	public Integer getAppointmentNumber() {
		return appointmentNumber;
	}

	public void setAppointmentNumber(Integer appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}
	 
}
