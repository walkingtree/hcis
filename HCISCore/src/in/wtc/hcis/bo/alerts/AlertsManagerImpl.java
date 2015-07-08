/**
 * 
 */
package in.wtc.hcis.bo.alerts;

import in.wtc.hcis.bm.base.AlertBM;
import in.wtc.hcis.bm.base.AppointmentBM;

import java.util.List;

/**
 * @author Kamal
 *
 */
public class AlertsManagerImpl implements AlertsManager {

	/* (non-Javadoc)
	 * @see com.wtc.hcis.bo.alerts.AlertsManager#addReminder(com.wtc.hcis.bm.base.AlertBM)
	 */
	public void addReminder(AlertBM alertBM) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.wtc.hcis.bo.alerts.AlertsManager#getReminders(com.wtc.hcis.bm.base.AppointmentBM)
	 */
	public List<AlertBM> getReminders(AppointmentBM appointmentBM) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wtc.hcis.bo.alerts.AlertsManager#modifyReminders(com.wtc.hcis.bm.base.AppointmentBM)
	 */
	public List<AlertBM> modifyReminders(AppointmentBM appointmentBM) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wtc.hcis.bo.alerts.AlertsManager#removeAlerts(java.util.List)
	 */
	public boolean removeAlerts(List<AlertBM> alertBMList) {
		// TODO Auto-generated method stub
		return false;
	}

}
