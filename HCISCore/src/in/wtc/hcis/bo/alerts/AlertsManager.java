/**
 * 
 */
package in.wtc.hcis.bo.alerts;

import in.wtc.hcis.bm.base.AlertBM;
import in.wtc.hcis.bm.base.AppointmentBM;

import java.util.List;

/**
 * This interface provides the API for the following
 * 			> Adding a new reminder
 * 			> Getting all the reminders
 * 			> Modifying an existing reminder.
 * 			> Removing an existing reminder.
 * @author Rohit
 *
 */
public interface AlertsManager 
{
	void addReminder( AlertBM alertBM );//should go for default settings
	
	List<AlertBM> getReminders( AppointmentBM appointmentBM );//here alertbm
	
	List<AlertBM> modifyReminders( AppointmentBM appointmentBM );
	
	boolean removeAlerts( List<AlertBM> alertBMList );
}
