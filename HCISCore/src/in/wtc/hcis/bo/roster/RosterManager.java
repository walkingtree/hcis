/**
 * 
 */
package in.wtc.hcis.bo.roster;

import in.wtc.hcis.bm.base.AppointmentBM;
import in.wtc.hcis.bm.base.RosterBM;
import in.wtc.hcis.bm.base.RosterModel;
import in.wtc.hcis.exceptions.HCISException;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This interface provides the following APIs
 * 			> Creating a new rooster entry
 * 			> Getting the rooster entry.
 * 			> Modifying an existing rooster entry.
 * 			> Removing an existing rooster entry.
 * @author Alok Ranjan
 *
 */
public interface RosterManager 
{
	/**
	 *  When creating a roster for an entity it will mark its availability and in which room on that particular date.
	 *  The above marks as the working hours for that entity
	 *  When from Date is greater than To date then exception will be thrown
	 *  When we create a roster and it gets conflicted with any other existing roster exception will be thrown
	 *  While creation of roster if there is a overlap of time then also exception will be thrown
	 *  
	 *  When we create a roster for an entity(doctor, nurse, etc) the following has to be specified. 
	 *  1	-> The duration for which the roster has to be created( from date and to date ).
	 *  2	-> The type of roster we are creating is expected from the user(Daily, Weekly, Monthly) and this is mandatory.
	 *  3	-> The roster for the entity, it can have multiple roster entry and it has to be repeated over the duration specified
	 *  4	-> If the roster date given is different  from what is expected then the entries will be created will start from the working date specified 
	 *  		till the TO date.
	 *  @param rosterBM
	 */
	void addRoster( RosterBM rosterBM );
	/**
	 * vinay:  return type  RosterBM changed to RosterModel .
	 * @param entityType
	 * @param entityId
	 * @param workingDate
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<RosterModel> getRoster( String entityType,
								Integer entityId,
								Date fromDate,
								Date toDate,
								String startTime,
								String endTime,
								String entityName,
								String roomNo,
								String weekDay,
								String status);

	
	RosterModel getRosterModel( Integer rosterCode ) throws HCISException;
	
	/**
	 * When modifying the roster it should be ensured that the desired changes are getting reflected in the calendar
	 * So as and when the appointment is given it will be easy to identify in which room the doctor is available
	 * @param rosterBM
	 * @return
	 */
	List<AppointmentBM> modifyRoster( RosterModel rosterModel, Boolean modifyForcefully );
	

	/**
	 * When deleting a roster all the appointments booked for that roster entry should be thrown back
	 * So the necessary changes can be done by the concerned person for that appointments 
	 * @param rosterBMList
	 * @param removeForcefully
	 * @return : A list of AppointmentBM
	 */
	Map<Integer, List<AppointmentBM>> removeRosters( List<RosterModel> rosterModelList, Boolean removeForcefully );

	

}
