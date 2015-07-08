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
public class AlertBM implements Cloneable, Serializable 
{
	static final long serialVersionUID = 200904220418L;
	
	private String 				appointmentNumber;
	private String 				appEntity;
	private String 				reminderNumber;
	private Date 				reminderTime;
//	private String personelId;      should it be captured over here
//	private String personelName;    should it be captured over here
	private CodeAndDescription 	optionCode;
	private CodeAndDescription 	durationCode;
	public AlertBM() {
	}
	/**
	 * @return the appointmentNumber
	 */
	public String getAppointmentNumber() {
		return appointmentNumber;
	}
	/**
	 * @param appointmentNumber the appointmentNumber to set
	 */
	public void setAppointmentNumber(String appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}
	/**
	 * @return the appEntity
	 */
	public String getAppEntity() {
		return appEntity;
	}
	/**
	 * @param appEntity the appEntity to set
	 */
	public void setAppEntity(String appEntity) {
		this.appEntity = appEntity;
	}
	/**
	 * @return the reminderNumber
	 */
	public String getReminderNumber() {
		return reminderNumber;
	}
	/**
	 * @param reminderNumber the reminderNumber to set
	 */
	public void setReminderNumber(String reminderNumber) {
		this.reminderNumber = reminderNumber;
	}
	/**
	 * @return the reminderTime
	 */
	public Date getReminderTime() {
		return reminderTime;
	}
	/**
	 * @param reminderTime the reminderTime to set
	 */
	public void setReminderTime(Date reminderTime) {
		this.reminderTime = reminderTime;
	}
	/**
	 * @return the optionCode
	 */
	public CodeAndDescription getOptionCode() {
		return optionCode;
	}
	/**
	 * @param optionCode the optionCode to set
	 */
	public void setOptionCode(CodeAndDescription optionCode) {
		this.optionCode = optionCode;
	}
	/**
	 * @return the durationCode
	 */
	public CodeAndDescription getDurationCode() {
		return durationCode;
	}
	/**
	 * @param durationCode the durationCode to set
	 */
	public void setDurationCode(CodeAndDescription durationCode) {
		this.durationCode = durationCode;
	}
	
	
}
