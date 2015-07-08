/**
 * 
 */
package in.wtc.ui.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import in.wtc.hcis.bm.base.AppointmentBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.RosterBM;
import in.wtc.hcis.bm.base.RosterModel;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.roster.RosterManager;
import in.wtc.ui.model.AppointmentSummaryModel;
import in.wtc.ui.model.DoctorAppointmentRosterModel;
import in.wtc.ui.model.RosterView;
import in.wtc.ui.utils.Constants;
import in.wtc.ui.utils.Converters;
import in.wtc.ui.utils.Messages;

/**
 * @author Vinay Kurudi
 *
 */
public class RosterManagementController {
	
	RosterManager rosterManager;
	/**
	 *  method for saving the Roster.Based on the period values and view information we construct the fromDate and toDate. 
	 * @param rosterBM
	 * @param rosterView
	 * @return String
	 */
	public String createRoster (RosterBM rosterBM, RosterView rosterView) {
		
			Date fromDate = null;
			Date toDate = null;
			if(rosterView!=null) {
			if(rosterView.getPeriod().equals("1")) {
				Calendar fromCalendar = Calendar.getInstance();
				fromCalendar.clear();
				fromCalendar.set(new Integer(rosterView.getFormYear()), new Integer(rosterView.getFormMonth()), 1);
				fromCalendar.set(Calendar.WEEK_OF_YEAR, new Integer(rosterView.getFormWeek()));
				fromCalendar.set(Calendar.DAY_OF_WEEK, 2);
				fromDate = fromCalendar.getTime();
				
				Calendar toCalendar = Calendar.getInstance();
				toCalendar.clear();
				toCalendar.set(new Integer(rosterView.getToYear()), new Integer(rosterView.getToMonth()), 1);
				toCalendar.set(Calendar.WEEK_OF_YEAR, new Integer(rosterView.getToWeek()));
				toCalendar.set(Calendar.DAY_OF_WEEK, 1);
				toDate = toCalendar.getTime();
				
				rosterBM.setFromDate(fromDate);
				rosterBM.setToDate(toDate);
				rosterBM.setPeriod(Constants.WEEKLY);
			}else {
				 Calendar fromCalendar = Calendar.getInstance();
				 fromCalendar.clear();
				 fromCalendar.set(new Integer(rosterView.getFormYear()), new Integer(rosterView.getFormMonth()), 1);
				 fromDate = fromCalendar.getTime();
				 
				 Calendar toCalendar = Calendar.getInstance();
				 toCalendar.clear();
				 toCalendar.set(new Integer(rosterView.getToYear()), new Integer(rosterView.getToMonth()),getLastDateOfMonth(rosterView.getToMonth(),rosterView.getToYear()));
				 toDate = toCalendar.getTime();
				 
				 	rosterBM.setFromDate(fromDate);
					rosterBM.setToDate(toDate);
					rosterBM.setPeriod(Constants.MONTHLY);
			}
			}else {
				rosterBM.setPeriod(Constants.DAILY);
			}
			 rosterManager.addRoster(rosterBM);
			 
			return  Messages.CREATE_MESSAGE;
		
		
		
	}
	/**
	 * 
	 * @param rosterSearch
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return ListRange
	 */
	public ListRange getRosterSummaries( Map<String, String> rosterSearch, int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		
		List<RosterModel> rosterModelList = null;
		
			if ( rosterSearch != null && !rosterSearch.isEmpty() ) {
			String startTime ="";
			if( rosterSearch.get("fromtime")!= null && !rosterSearch.get("fromtime").equals("")  ) {
				 startTime = Converters.getTimeWithoutColon(rosterSearch.get("fromtime"));
			}
			String endTime="";
			if( rosterSearch.get("totime")!=null && !rosterSearch.get("totime").equals("") ) {
				 endTime = Converters.getTimeWithoutColon(rosterSearch.get("totime"));
			}
			 Date fromDate = null;
			 if( rosterSearch.get("fromdate") !=null && !rosterSearch.get("fromdate").equals("") ) {
				 fromDate = Converters.getDate(rosterSearch.get("fromdate"));
			 }
			 Date toDate =null;
			if( rosterSearch.get("todate")!=null && !rosterSearch.get("todate").equals("") ){
				 toDate = Converters.getDate(rosterSearch.get("todate"));
			}
			Integer entityId = null;
			if( rosterSearch.get("entityid") !=null && !rosterSearch.get("entityid").equals("")){
				 entityId =new Integer(rosterSearch.get("entityid"));
			}
			
				rosterModelList = rosterManager.getRoster(rosterSearch.get("entitytype"),entityId , fromDate, 
																		toDate, startTime, endTime, rosterSearch.get("entityname"),
																		rosterSearch.get("roomno"),rosterSearch.get("day"),rosterSearch.get("status"));
			} else {
				rosterModelList = rosterManager.getRoster(null,null, null, 
						null, null, null, null,
						null,null,null);
			}
			
			List<RosterModel> pageSizeData = new ArrayList<RosterModel> ();
			if( rosterModelList!= null && rosterModelList.size()>0 ) {
				for(RosterModel roster : rosterModelList){
					if(roster.getEndTime() != null && roster.getEndTime().length() > 0){
						roster.setEndTime( roster.getEndTime().substring(0, 2)+":"+ roster.getEndTime().substring(2,4));
					}
					if(roster.getStartTime() != null && roster.getStartTime().length() > 0){
						roster.setStartTime( roster.getStartTime().substring(0, 2)+":"+ roster.getStartTime().substring(2,4));
					}
				}
			
				int index = 0;
				while( (start+index < start + count) && (rosterModelList.size() > start+index) ){
					pageSizeData.add(rosterModelList.get(start+index));
						index++;
				}
				
				listRange.setData(pageSizeData.toArray());
				listRange.setTotalSize(rosterModelList.size());
			}else {
				listRange.setData(new Object[0]);
				listRange.setTotalSize(0);
			}
			
			
		
		return listRange;
	}
	/**
	 * @param rosterManager the rosterManager to set
	 */
	public void setRosterManager(RosterManager rosterManager) {
		this.rosterManager = rosterManager;
	}
	
	int getLastDateOfMonth(String month,String year) {
		if(month.equals("0")|| month.equals("2") || month.equals("4") || month.equals("6") || month.equals("7") ||month.equals("9") ||month.equals("11") ) {
			return 31;
		} else if(month.equals("3") || month.equals("5") || month.equals("8") || month.equals("10")) {
			return 30;
		} else {
			if(Converters.checkLeapYear(year)) {
				return 29;
			}else {
				return 28;
			}
		}
	}
	
	public ListRange removeRoster(List<RosterModel> rosterModelList,Boolean flag,int start, int count, String orderBy) {
			ListRange listRange = new ListRange();
			List<AppointmentSummaryModel> appointmentsList = new ArrayList<AppointmentSummaryModel>();
			Map<Integer, List<AppointmentBM>> rosterMap = rosterManager.removeRosters(rosterModelList,flag);
				if(rosterMap!=null && rosterMap.size()>0) {
					int index = Constants.GRID_ROW_INDEX;
					for(Map.Entry<Integer, List<AppointmentBM>> appointmentMapEntry :rosterMap.entrySet() ) {
						List<AppointmentBM> AppointmentList = appointmentMapEntry.getValue();
						if(AppointmentList!=null && AppointmentList.size()>0) {
							for (AppointmentBM appointment : AppointmentList) {
								AppointmentSummaryModel apptmnt = new AppointmentSummaryModel();

								apptmnt.setAmount(null);
								apptmnt.setAppointmentNumber(appointment.getAppointmentNumber());
								apptmnt.setAppointmentDate(appointment.getAppointmentDate());
								apptmnt.setAppointmentStartTime(appointment.getAppointmentStartTime());
								apptmnt.setAppointmentEndTime(appointment.getAppointmentEndTime());

								if (appointment.getAppointmentStatus() != null) {
									apptmnt.setAppointmentStatus(appointment.getAppointmentStatus());
								} else {
									apptmnt.setAppointmentStatus(new CodeAndDescription(null, null));
								}

								if (appointment.getBookingType() != null) {
									apptmnt.setBookingType(appointment.getBookingType());
								} else {
									apptmnt.setBookingType(new CodeAndDescription(null, null));
								}

								if (appointment.getSpeaciality() != null) {
									apptmnt.setSpeaciality(appointment.getSpeaciality());
								} else {
									apptmnt.setSpeaciality(new CodeAndDescription(null, null));
								}

								if (appointment.getPrimaryDoctor() != null) {
									apptmnt.setPrimaryDoctor(appointment.getPrimaryDoctor());
								} else {
									apptmnt.setPrimaryDoctor(new CodeAndDescription(null, null));
								}
								
								if( appointment.getReferralType() != null ){
									apptmnt.setReferralType( appointment.getReferralType());
								}else{
									apptmnt.setReferralType( new CodeAndDescription( null, null ));
								}
								
								if( appointment.getReferredBy() != null ){
									apptmnt.setReferredBy( appointment.getReferredBy());
								}else{
									apptmnt.setReferredBy( new CodeAndDescription( null, null ));
								}
								
								if( appointment.getAppointmentType() != null ){
									apptmnt.setAppointmentType(appointment.getAppointmentType());
								}else{
									apptmnt.setAppointmentType( new CodeAndDescription( null, null ));
								}
								if( appointment.getBookingType() != null ){
									apptmnt.setBookingType( appointment.getBookingType());
									
								}else{
									apptmnt.setBookingType( new CodeAndDescription( null, null ));
								}
								apptmnt.setPatientId(appointment.getPatientId());
								apptmnt.setPatientName(appointment.getFirstName() + " "
										+ appointment.getMiddleName() + " "
										+ appointment.getLastName());
								apptmnt.setPatientFirstName(appointment.getFirstName());
								apptmnt.setPatientMiddleName(appointment.getMiddleName());
								apptmnt.setPatientLastName(appointment.getLastName());
								apptmnt.setSerialNo(index);

								appointmentsList.add(apptmnt);
								index++;
					}
						}
						
					}
					listRange.setData(appointmentsList.toArray());
					listRange.setTotalSize(appointmentsList.size());
					return listRange;	
				}else {
					Object [] obj = new Object[0];
					listRange.setData(obj);
					listRange.setTotalSize(0);
					return listRange;
				}
			
		
	}
	
	public ListRange modifyRosters (RosterModel modifiedroster, Boolean flag,int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		List<AppointmentBM> appointmentBmList = rosterManager.modifyRoster(modifiedroster,flag);
		List<AppointmentSummaryModel> appointmentsList = new ArrayList<AppointmentSummaryModel>();
		int index = Constants.GRID_ROW_INDEX;
		if(appointmentBmList!=null && appointmentBmList.size()>0) {
			for (AppointmentBM appointment : appointmentBmList) {
				AppointmentSummaryModel apptmnt = new AppointmentSummaryModel();

				apptmnt.setAmount(null);
				apptmnt.setAppointmentNumber(appointment.getAppointmentNumber());
				apptmnt.setAppointmentDate(appointment.getAppointmentDate());
				apptmnt.setAppointmentStartTime(appointment.getAppointmentStartTime());
				apptmnt.setAppointmentEndTime(appointment
						.getAppointmentEndTime());

				if (appointment.getAppointmentStatus() != null) {
					apptmnt.setAppointmentStatus(appointment.getAppointmentStatus());
				} else {
					apptmnt.setAppointmentStatus(new CodeAndDescription(null,
							null));
				}

				if (appointment.getBookingType() != null) {
					apptmnt.setBookingType(appointment.getBookingType());
				} else {
					apptmnt.setBookingType(new CodeAndDescription(null, null));
				}

				if (appointment.getSpeaciality() != null) {
					apptmnt.setSpeaciality(appointment.getSpeaciality());
				} else {
					apptmnt.setSpeaciality(new CodeAndDescription(null, null));
				}

				if (appointment.getPrimaryDoctor() != null) {
					apptmnt.setPrimaryDoctor(appointment.getPrimaryDoctor());
				} else {
					apptmnt.setPrimaryDoctor(new CodeAndDescription(null, null));
				}

				apptmnt.setPatientId(appointment.getPatientId());
				apptmnt.setPatientName(appointment.getFirstName() + " "
						+ appointment.getMiddleName() + " "
						+ appointment.getLastName());
				apptmnt.setPatientFirstName(appointment.getFirstName());
				apptmnt.setPatientMiddleName(appointment.getMiddleName());
				apptmnt.setPatientLastName(appointment.getLastName());
				apptmnt.setSerialNo(index);

				appointmentsList.add(apptmnt);
				index++;
	}
			listRange.setData(appointmentsList.toArray());
			listRange.setTotalSize(appointmentsList.size());
			return listRange;
		} else {
			Object [] obj = new Object[0];
			listRange.setData(obj);
			listRange.setTotalSize(0);
			return listRange;
		}
		
//		AppointmentSummaryModel appointmentSummaryModel =new AppointmentSummaryModel();
//		appointmentSummaryModel.setAmount(null);
//		appointmentSummaryModel.setAppointmentDate(new Date());
//		appointmentSummaryModel.setAppointmentEndTime("12:30");
//		appointmentSummaryModel.setAppointmentNumber(new Integer(1));
//		appointmentSummaryModel.setAppointmentStartTime("9:30");
//		appointmentSummaryModel.setAppointmentStatus(new CodeAndDescription("BOOKED","BOOKED"));
//		appointmentSummaryModel.setBookingType(new CodeAndDescription("WALK","Walk-In"));
//		appointmentSummaryModel.setDepartment(new CodeAndDescription("Cardialogy","Cardialogy"));
//		appointmentSummaryModel.setPatientFirstName("Vinay");
//		appointmentSummaryModel.setPatientId(1);
//		appointmentSummaryModel.setPatientLastName("Kurudi");
//		appointmentSummaryModel.setPatientMiddleName("Kumar");
//		appointmentSummaryModel.setPatientName("Vinay Kurudi");
//		appointmentSummaryModel.setPrimaryDoctor(new CodeAndDescription("1","Alok Ranjan"));
//		appointmentSummaryModel.setSerialNo(1);
//		Object [] obj = new Object[1];
//		obj[0] = appointmentSummaryModel;
//		listRange.setData(obj);
//		listRange.setTotalSize(1);
		
		
		}
	
	public List<DoctorAppointmentRosterModel> getDoctorRoster(Date date, String doctorId){
		Integer doctorCode = null;
		if (doctorId != null && !doctorId.equals("")) {
			doctorCode = new Integer(doctorId);
		}
		
		List<RosterModel> rosterList = rosterManager.getRoster(
				Constants.DOCTOR, doctorCode, date,
				null, null, null, null, null, null, null);
		
		List<DoctorAppointmentRosterModel> rosterModelList = new ArrayList<DoctorAppointmentRosterModel>();
		int recordId = 0;
		
		if (rosterList != null) {
			for (RosterModel roster : rosterList) {

				DoctorAppointmentRosterModel doctorRoster = new DoctorAppointmentRosterModel();

				doctorRoster.setAgenda(null);
				doctorRoster.setEndTime(roster.getEndTime());
				doctorRoster.setRecordId(recordId);
				doctorRoster.setRemarks(null);
				doctorRoster.setStartTime( roster.getStartTime());
				doctorRoster.setEntityName(roster.getEntityName());

				doctorRoster.setEntityId(roster.getEntityId());
				doctorRoster.setRosterCode(roster.getRosterCode());
				doctorRoster.setWorkingDate(roster.getWorkingDate());
				doctorRoster.setRoomBM(roster.getRoomBM());
				
				recordId++;
				rosterModelList.add(doctorRoster);
			}
		}
		return rosterModelList;
	}

}


