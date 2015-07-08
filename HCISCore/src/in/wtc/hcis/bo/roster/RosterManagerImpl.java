/**
 * 
 */
package in.wtc.hcis.bo.roster;

import in.wtc.hcis.bm.base.AppointmentBM;
import in.wtc.hcis.bm.base.RosterBM;
import in.wtc.hcis.bm.base.RosterModel;
import in.wtc.hcis.bm.base.RosterTimeBM;
import in.wtc.hcis.bo.appointment.AppointmentConstants;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.DataModelConverter;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.common.DateUtils;
import in.wtc.hcis.bo.constants.ApplicationConstants;
import in.wtc.hcis.bo.constants.ApplicationEntities;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.wtc.hcis.da.AppointmentHistory;
import com.wtc.hcis.da.AppointmentHistoryId;
import com.wtc.hcis.da.AppointmentStatus;
import com.wtc.hcis.da.Appointments;
import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.Room;
import com.wtc.hcis.da.Roster;
import com.wtc.hcis.da.extn.AppointmentHistoryDAOExtn;
import com.wtc.hcis.da.extn.AppointmentsDAOExtn;
import com.wtc.hcis.da.extn.RosterDAOExtn;



/**
 * @author Rohit
 *
 */
public class RosterManagerImpl implements RosterManager {
	
	private RosterDAOExtn rosterDAO;
	private DataModelConverter converter;
	private DataModelManager dataModelManager;
	private AppointmentsDAOExtn appointmentsDAOExtn;
	private AppointmentHistoryDAOExtn appointmentHistoryDAOExtn;
	

	/* (non-Javadoc)
	 * @see com.wtc.hcis.bo.roster.RosterManager#addRoster(com.wtc.hcis.bm.base.RosterBM)
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
	 *  
	 *  If end time is less than the start time, this duration (start time to end time) will be divided into two parts. First part will contain
	 *  start time to 12:00 AM(fromDate) And second part will contain 12:00 AM to end time(next date of from date).  
	 *  @param rosterBM
	 *  
	 */
	public void addRoster( RosterBM rosterBM ) 
	{
		try {
			
			
			if( rosterBM.getFromDate().compareTo( rosterBM.getToDate() ) > 0 ) {
				throw new Exception( "From Date :"
								+ rosterBM.getFromDate()
								+ " is greater than To Date :"
								+ rosterBM.getToDate() );
			} //end of if block
			
			List<Roster> rosterList = new ArrayList<Roster>(0);
			
			Calendar temprosterBMFromDateCalender = Calendar.getInstance();
			temprosterBMFromDateCalender.setTime( rosterBM.getFromDate() );
			Date tempRosterBMFromDate = new Date(temprosterBMFromDateCalender.getTimeInMillis());
		
			Calendar temprosterBMToDateCalender = Calendar.getInstance();
			temprosterBMToDateCalender.setTime( rosterBM.getToDate() );
			
			List<RosterTimeBM> rosterTimeBMList = rosterBM.getRosterTimeBMList();

			// This code will divide time slot if end time is less than start time.
			
			if( rosterTimeBMList != null && !rosterTimeBMList.isEmpty()){
				RosterTimeBM rosterTimeBM = rosterTimeBMList.get(0);
				Integer startTime = Integer.parseInt(rosterTimeBM.getStartTime());
				String endTime = rosterTimeBM.getEndTime(); 
//				Integer endTime = Integer.parseInt(rosterTimeBM.getEndTime());
				
				// 
				
				if( Integer.parseInt(endTime) < startTime ){
					
					// If startTime is grater than endTime and from date is greater than or equal to toDate then exception will be thrown.  
					
					if( !rosterBM.getToDate().after(rosterBM.getFromDate())){
						throw new Exception( "From Date :"
								+ rosterBM.getFromDate()
								+ " is greater than or equal To Date :"
								+ rosterBM.getToDate() );
					}
					else{
						
						//This from date will be used to create roster  
						
						Calendar cal = Calendar.getInstance();
						cal.setTime(rosterBM.getFromDate());
						Date fromDate = cal.getTime();
						
						// First create roster for from start time to 12:00 AM . 
						
						rosterTimeBM.setEndTime("2400");
						rosterBM.setToDate(DateUtils.previousDate(rosterBM.getToDate()));
						rosterList = converter.convertRosterBM2DM( rosterBM, null );
						
						rosterBM.setFromDate(DateUtils.nextDate(fromDate));
						rosterBM.setToDate(DateUtils.nextDate(rosterBM.getToDate()));
						rosterTimeBM.setStartTime("0000");
						rosterTimeBM.setEndTime(endTime);
						
						rosterList.addAll(converter.convertRosterBM2DM( rosterBM, null ));
						
					}
				}
				else{
					rosterList = converter.convertRosterBM2DM( rosterBM, null );
				}
				
				
			}
			
			
			
			
			if( rosterList != null && !rosterList.isEmpty() ) {
				Iterator<Roster> rosterListIterator = rosterList.iterator();
				while( rosterListIterator.hasNext() ) {
//					Roster roster = new Roster();
					Roster roster = rosterListIterator.next();
					boolean fromDate = roster.getWorkingDate().compareTo( tempRosterBMFromDate ) >= 0;
					boolean toDate = roster.getWorkingDate().compareTo( temprosterBMToDateCalender.getTime() ) <= 0;
//					To get the conflicted rosters for the particular roster entry
					List<Roster> conflictedRosterList = rosterDAO.getConflictedRosters( rosterBM.getEntityType(),
																						rosterBM.getEntityId(),
																						roster.getWorkingDate(),
																						roster.getStartTime(),
																						roster.getEndTime(),
																						ApplicationConstants.ACTIVE_FLAG_YES);
					if( fromDate && toDate ) {
						if( conflictedRosterList == null || conflictedRosterList.isEmpty() ) {
							rosterDAO.save(roster);
						} //end of if block
						else {
							String exceptionMsgString="\nThe roster conflicted are :- ";
							for( Roster tmpRoster : conflictedRosterList) {
								exceptionMsgString +=  " \nworking date = " 
														+ tmpRoster.getWorkingDate()
														+ " start time ="
														+ tmpRoster.getStartTime()
														+ " end time = "
														+ tmpRoster.getEndTime();
							}
							throw new Exception( "The Roster cannot be created because of " 
												+ exceptionMsgString );
						}
					} //end of if block
					else {
						throw new Exception( "For Date = " 
								+ roster.getWorkingDate() 
								+ ", the slot between time " 
					            + roster.getStartTime() 
					            + " -- " 
					            + roster.getEndTime()
					            + " "
					            + " is not in between  "
					            + tempRosterBMFromDate +" - " + rosterBM.getToDate() );
					}  //end of else block
				} // end of while block
			} //end of if block
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.CREATE_ROSTER_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		} //end of catch block
	} //end of public void addRoster( RosterBM rosterBM ) 
	


	/* (non-Javadoc)
	 * @see com.wtc.hcis.bo.roster.RosterManager#getRoster(java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.util.Date, java.util.Date, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<RosterModel> getRoster( String entityType, 
			                         Integer entityId, 
			                         Date fromDate, 
			                         Date toDate,
			                         String startTime, 
			                         String endTime, 
			                         String entityName,
			                         String roomNo,
			                         String weekDay,
			                         String status) {

		try {
			List<Roster> rosterList = rosterDAO.getRosters( entityType, 
					                                        entityId, 
					                                        fromDate, 
					                                        toDate, 
					                                        startTime, 
					                                        endTime,
					                                        roomNo,
					                                        status);

			
			if ( rosterList != null && !rosterList.isEmpty() ) {
				List<RosterModel>rosterBMList = new ArrayList<RosterModel>();
				Map<Integer, Object>entityMap = new HashMap<Integer, Object>(0);
				
				for ( int i =0 ; i < rosterList.size() ; i++ ) {
					
					Roster roster = rosterList.get(i);
					if( roster == null){
						continue;
					}
					//
					// If the entity type is a doctor and any of the name fields have been queried then 
					// read doctor table and filter such records.
					//
					if ( ( entityName != null  && entityName.length() > 0 ) ) {
						
						if ( roster.getEntityType().equals( ApplicationEntities.DOCTOR ) ) {
							Doctor doctor = null;
							
							if ( entityMap.containsKey( roster.getEntityId() ) ) {
								doctor = (Doctor) entityMap.get( roster.getEntityId() );
							} else {
								doctor = dataModelManager.getDoctor( roster.getEntityId() );
								entityMap.put( roster.getEntityId(), doctor );
							} //end of else block
							
							if ( ( entityName != null  && entityName.length() > 0 && !doctor.getFullName().toLowerCase().contains( entityName.toLowerCase() ) ) )  {
								continue;
							}
						} //end of if block
					} //end of if block
					
					// if the weekDay field have been queried then each record will filter by
					// weekDay.  
					if(weekDay!= null && weekDay.length()>0) {
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(roster.getWorkingDate());
						int day = calendar.get(Calendar.DAY_OF_WEEK);
						if(new Integer(weekDay)!=day){
							continue;
						}
					}
					rosterBMList.add( converter.convertRosterModelDM2BM( roster ) );
				}
				
				return rosterBMList;
			} else {
				return null;
			}
			
		} catch ( Exception e ) {
			Fault fault = new Fault( ApplicationErrors.READ_ROSTER_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		}
	}

	/* (non-Javadoc)
	 * @see com.wtc.hcis.bo.roster.RosterManager#modifyRoster(com.wtc.hcis.bm.base.RosterModel)
	 */
	public List<AppointmentBM> modifyRoster( RosterModel modifiedRosterBM, Boolean modifyForcefully ) 
	{
		Roster existingRosterDM = dataModelManager.getRoster( modifiedRosterBM.getRosterCode() );
		try
		{
			List<AppointmentBM> appointmentBMList = new ArrayList<AppointmentBM>();
			List<Appointments> appointmentsList = new ArrayList<Appointments>();
			appointmentsList = appointmentsDAOExtn.getBookedAppointments( existingRosterDM.getRosterCode() );
			boolean workingDate = existingRosterDM.getWorkingDate().compareTo( modifiedRosterBM.getWorkingDate() ) == 0;// roster invalid
			boolean startTime = existingRosterDM.getStartTime().equals( modifiedRosterBM.getStartTime() );// roster invalid
			boolean endTime = existingRosterDM.getEndTime().equals( modifiedRosterBM.getEndTime() );// roster invalid
			
			if((appointmentsList == null && modifyForcefully==null) || modifyForcefully == Boolean.TRUE ) {
				if( !workingDate || !startTime  || !endTime ) {
					existingRosterDM.setActive( "N" );
					rosterDAO.attachDirty( existingRosterDM );
					Roster newRosterDM = converter.convertRosterModelBM2DM( modifiedRosterBM );
					List<Roster> rosterList = rosterDAO.getConflictedRosters( modifiedRosterBM.getEntityType(), 
																			modifiedRosterBM.getEntityId(), 
																			newRosterDM.getWorkingDate(), 
																			newRosterDM.getStartTime(), 
																			newRosterDM.getEndTime(),
																			ApplicationConstants.ACTIVE_FLAG_YES);
					if( rosterList == null || rosterList.isEmpty() ) {
						rosterDAO.save( newRosterDM );
					}else {
						String exceptionMsgString="\nThe roster conflicted are :- ";
						for( Roster tmpRoster : rosterList) {
							exceptionMsgString +=  " \nworking date = " 
								+ tmpRoster.getWorkingDate()
								+ " start time ="
								+ tmpRoster.getStartTime()
								+ " end time = "
								+ tmpRoster.getEndTime();
						}
						throw new Exception( "The Roster cannot be created because of " 
								+ exceptionMsgString );
					}
				}else {
					Room room = new Room();
					if( modifiedRosterBM.getRoomBM() != null && !modifiedRosterBM.equals("") ) {
						room.setRoomCode( modifiedRosterBM.getRoomBM().getRoomCode() );
						room.setDescription( modifiedRosterBM.getRoomBM().getDescription() );
					}
					existingRosterDM.setRoom(room);
					rosterDAO.attachDirty( existingRosterDM );
				}
				
			}
			if( appointmentsList != null && !appointmentsList.isEmpty() ) {
				Iterator<Appointments> appointmentsIterator = appointmentsList.iterator();
				while( appointmentsIterator.hasNext() ) {
					Appointments tmpAppointmentsDM = appointmentsIterator.next();
					if( modifyForcefully == Boolean.TRUE ) {
						AppointmentStatus appointmentStatus = new AppointmentStatus();
						if( !workingDate || !startTime  || !endTime ) {
							appointmentStatus.setStatusCode( AppointmentConstants.INVALID_ROSTER );
						}
						else {
							appointmentStatus.setStatusCode( AppointmentConstants.ROSTER_MODIFIED);
						}
						tmpAppointmentsDM.setAppointmentStatus(appointmentStatus);
						appointmentsDAOExtn.attachDirty(tmpAppointmentsDM);
						
						AppointmentHistory appointmentHistoryDM = new AppointmentHistory();
						AppointmentHistoryId appointmentHistoryId = new AppointmentHistoryId();
						appointmentHistoryId.setAppointmentNumber( tmpAppointmentsDM.getAppointmentNumber() );
						if( !workingDate || !startTime  || !endTime ) {
							appointmentHistoryId.setStatusCode( AppointmentConstants.INVALID_ROSTER );
						}
						else {
							appointmentHistoryId.setStatusCode( AppointmentConstants.ROSTER_MODIFIED);
						}
						appointmentHistoryDM.setId( appointmentHistoryId );
						appointmentHistoryDAOExtn.save( appointmentHistoryDM );
					}
					AppointmentBM appointmentBM = converter.convertAppointmentsDM2BM( tmpAppointmentsDM );
					appointmentBMList.add( appointmentBM );
				}
				/**
				 * TODO 
				 */
//			roster.setLastModifiedDtm( new Date());
//			roster.setModifiedBy( null );
				
				if( modifyForcefully == Boolean.TRUE ) {
					return null;
				}else {
					return appointmentBMList;
				}
			}
		}catch ( Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.UPDATE_ROSTER_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + exception.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.wtc.hcis.bo.roster.RosterManager#removeRosters(java.util.Map)
	 * 
	 */

	public Map<Integer, List<AppointmentBM>> removeRosters( List<RosterModel> rosterModelList, Boolean removeForcefully ) 
	{
		try
		{
			if( rosterModelList != null && !rosterModelList.isEmpty() ) {
				Map<Integer, List<AppointmentBM>> appointmentBMap = new HashMap<Integer, List<AppointmentBM>>();
				Iterator<RosterModel> rosterModelIterator = rosterModelList.iterator();
				boolean rosterHasAppointment = false;
				while( rosterModelIterator.hasNext() ) {
					Roster roster = dataModelManager.getRoster( rosterModelIterator.next().getRosterCode() );
					
					if (roster.getActive().equals(ApplicationConstants.ACTIVE_FLAG_NO)) {
						return null;
					}
					
					List<AppointmentBM> appointmentBMList = new ArrayList<AppointmentBM>();
					List<Appointments> appointmentsList = new ArrayList<Appointments>();
					appointmentsList = appointmentsDAOExtn.getBookedAppointments( roster.getRosterCode() );
					if( appointmentsList != null && !appointmentsList.isEmpty() ) {
						rosterHasAppointment = true;
						Iterator<Appointments> appointmentsIterator = appointmentsList.iterator();
						while( appointmentsIterator.hasNext() ) {
							Appointments tmpAppointmentsDM = appointmentsIterator.next();
							
							if( removeForcefully == Boolean.TRUE ) {
								AppointmentStatus appointmentStatus = new AppointmentStatus();
								appointmentStatus.setStatusCode( AppointmentConstants.INVALID_ROSTER );
								tmpAppointmentsDM.setAppointmentStatus(appointmentStatus);
								appointmentsDAOExtn.attachDirty(tmpAppointmentsDM);
								AppointmentHistory appointmentHistoryDM = new AppointmentHistory();
								AppointmentHistoryId appointmentHistoryId = new AppointmentHistoryId();
								appointmentHistoryId.setAppointmentNumber( tmpAppointmentsDM.getAppointmentNumber() );
								appointmentHistoryId.setStatusCode( AppointmentConstants.INVALID_ROSTER );
								appointmentHistoryDM.setId( appointmentHistoryId );
								appointmentHistoryDAOExtn.save( appointmentHistoryDM );
							}
							
							AppointmentBM appointmentBM = converter.convertAppointmentsDM2BM( tmpAppointmentsDM );
							appointmentBMList.add( appointmentBM );
						}
					}
					if( removeForcefully == Boolean.TRUE ) {
						roster.setActive( "N" );
						rosterDAO.attachDirty( roster );
					}
					appointmentBMap.put(roster.getRosterCode(), appointmentBMList);
				}
				if(!rosterHasAppointment && removeForcefully == null){
					for(RosterModel rosterMdl : rosterModelList){
						Roster roster = dataModelManager.getRoster( rosterMdl.getRosterCode() );
						roster.setActive("N");
						rosterDAO.attachDirty( roster );
					}
				}
				if( removeForcefully == Boolean.TRUE ) {
					return null;
				}else {
					return appointmentBMap;
				}
			}
			
		}catch( Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.REMOVE_ROSTER_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + exception.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		return null;
	}

	public RosterModel getRosterModel( Integer rosterCode ) throws HCISException {
		Roster rosterDM = dataModelManager.getRoster( rosterCode );
		return converter.convertRosterModelDM2BM(rosterDM);
	}

	public void setRosterDAO(RosterDAOExtn rosterDAO) {
		this.rosterDAO = rosterDAO;
	}

	public void setConverter(DataModelConverter converter) {
		this.converter = converter;
	}

	public void setDataModelManager(DataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public void setAppointmentsDAOExtn(AppointmentsDAOExtn appointmentsDAOExtn) {
		this.appointmentsDAOExtn = appointmentsDAOExtn;
	}

	public void setAppointmentHistoryDAOExtn(
			AppointmentHistoryDAOExtn appointmentHistoryDAOExtn) {
		this.appointmentHistoryDAOExtn = appointmentHistoryDAOExtn;
	}
	
}
