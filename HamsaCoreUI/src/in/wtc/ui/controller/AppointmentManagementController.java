/**
 *
 */
package in.wtc.ui.controller;

import in.wtc.hcis.bm.base.AppointmentBM;
import in.wtc.hcis.bm.base.AssignSvcAndPackageBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactBM;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.DoctorEspecialtyBM;
import in.wtc.hcis.bm.base.ObservationBM;
import in.wtc.hcis.bm.base.PatientInfoDetailBM;
import in.wtc.hcis.bm.base.PersonalDetailsAdditionalBM;
import in.wtc.hcis.bm.base.PersonalDetailsBM;
import in.wtc.hcis.bm.base.PrescriptionBM;
import in.wtc.hcis.bm.base.RosterModel;
import in.wtc.hcis.bo.appointment.AppointmentManager;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.configuration.ConfigurationManager;
import in.wtc.hcis.bo.constants.ConfigurationConstants;
import in.wtc.hcis.bo.doctor.DoctorManager;
import in.wtc.hcis.bo.observations.ObservationManager;
import in.wtc.hcis.bo.patient.PatientManager;
import in.wtc.hcis.bo.prescription.PrescriptionManager;
import in.wtc.hcis.bo.roster.RosterManager;
import in.wtc.hcis.bo.services.ServiceManager;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.ui.model.AppointmentSummaryModel;
import in.wtc.ui.model.DoctorAppointmentRosterModel;
import in.wtc.ui.model.PatientDetailsModel;
import in.wtc.ui.utils.Constants;
import in.wtc.ui.utils.Converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vinay Kurudi
 *
 */
public class AppointmentManagementController {

	private AppointmentManager appointmentManager;
	private PatientManager patientManager;
	private RosterManager rosterManager;
	private PrescriptionManager prescriptionManager;
	private ObservationManager observationManager;
	private ServiceManager serviceManager;
	private DoctorManager doctorManager;
	private ConfigurationManager configurationManager;
	
	private static String ACTIVE_FLAG_YES ="YES";
	private static String DOCTOR_CAPTURED ="CAPTURED";
	/**
	 * TODO Parameters of this method to be replaced by Map
	 * 
	 * This method creates new appointment. This method provides the functionality for Confirm
	 * button of New Appointment window
	 *  
	 * @param patientId
	 * @param departmentId
	 * @param doctorId
	 * @param appointmentdate
	 * @param startTime
	 * @param endTime
	 * @param reasonForAppointment
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @return
	 */
	public AppointmentBM CreateNewAppointment(int patientId, String speacialityId,
			String doctorId,Integer primaryAppointmentNumber, Date appointmentdate, String startTime,
			String endTime, String reasonForAppointment, String firstName,
			String middleName, String lastName, String referredBy,
			String appointmentType, String bookingType,String phone,String email, String createdBy, Double consultationCharge,String remarks) {

			Integer doctorCode = null;

			AppointmentBM appointmentBM = new AppointmentBM();

			appointmentBM.setAppointmentAgenda(Converters.getEntityValue(reasonForAppointment));
			appointmentBM.setAppointmentDate(Converters.getDate(appointmentdate));
			appointmentBM.setAppointmentEndTime(Converters.getTimeWithoutColon(endTime));
			appointmentBM.setAppointmentStartTime(Converters.getTimeWithoutColon(startTime));
			appointmentBM.setModifiedBy(createdBy);
			appointmentBM.setAppointmentCharge(consultationCharge);

			appointmentBM.setPatientId(patientId);

			if (speacialityId != null && speacialityId.equals("")) {
				appointmentBM.setSpeaciality(null);
			} else {
				appointmentBM.setSpeaciality(new CodeAndDescription(speacialityId, null));
			}
			
			if (primaryAppointmentNumber != null && primaryAppointmentNumber.equals("")) {
				appointmentBM.setPrimaryAppointmentNumber(null);
			} else {
				appointmentBM.setPrimaryAppointmentNumber(primaryAppointmentNumber);
			}

			if (doctorId != null && doctorId.equals("")) {
				appointmentBM.setPrimaryDoctor(null);
			} else {
				appointmentBM.setPrimaryDoctor(new CodeAndDescription(doctorId,null));
				doctorCode = new Integer(doctorId);
			}
			
			if(referredBy != null && referredBy.length() == 0){
				appointmentBM.setReferredBy(null);
			}else{
				appointmentBM.setReferredBy(new CodeAndDescription(referredBy,null));
			}
			
			if( bookingType != null && bookingType.length() == 0){
				appointmentBM.setBookingType( null );
			}else{
				appointmentBM.setBookingType( new CodeAndDescription( bookingType, null ));
			}

			if( null != appointmentType && appointmentType.length() == 0){
				appointmentBM.setAppointmentType( null );
			}else{
				appointmentBM.setAppointmentType( new CodeAndDescription( appointmentType, null ));
			}
			
			if( null != phone && phone.length() == 0){
				appointmentBM.setPhone(null);
			}else{
				appointmentBM.setPhone(phone);
			}
			
			if( null != email && email.length() == 0){
				appointmentBM.setEmail(null);
			}else{
				appointmentBM.setEmail(email);
			}
			
			appointmentBM.setFirstName(Converters.getEntityValue(firstName));
			appointmentBM.setMiddleName(Converters.getEntityValue(middleName));
			appointmentBM.setLastName(Converters.getEntityValue(lastName));

			String rosterIdStr = getDoctorRosterCode(	Constants.DOCTOR,
														doctorCode, 
														appointmentdate, 
														appointmentdate, 
														Converters.getTimeWithoutColon(startTime), 
														Converters.getTimeWithoutColon(endTime),
														null);
			
			Integer rosterId = rosterIdStr != null ? Integer.valueOf(rosterIdStr) : null;
			appointmentBM.setRosterCode(rosterId);
			
			appointmentBM.setAppointmentRemarks(remarks);
			
			AppointmentBM createdAppointment = appointmentManager.addAppointment(appointmentBM);

			return createdAppointment;

	}

	/**
	 * TODO Use Map in place of below listed parameters
	 * 
	 * This method returns the list of appointments for Search functionality of Manage Appointments
	 *  
	 * @param appointmentFrom
	 * @param appointmentTo
	 * @param patientId
	 * @param patientFirstName
	 * @param patientMiddleName
	 * @param patientLastName
	 * @param doctorName
	 * @param bookingType
	 * @param timeFrom
	 * @param timeTo
	 * @param appointmentStatus
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	public ListRange getAppointments(	
										Date appointmentFrom, 
										Date appointmentTo,
										String patientId, 
										String patientName,
										String doctorName, 
										String bookingType, 
										String timeFrom,
										String timeTo, 
										String appointmentStatus, 
										int start, 
										int count,
										String orderBy) {

		String fromTime = Converters.getTimeWithoutColon(timeFrom);
		String toTime = Converters.getTimeWithoutColon(timeTo);

		List<AppointmentBM> appointmentsList  = appointmentManager
														.getAppointments(
																appointmentFrom, 
																appointmentTo,
																convertIdInStringToInteger(patientId), 
																patientName, 
																doctorName, 
																appointmentStatus,
																bookingType, 
																fromTime, 
																toTime);

		List<AppointmentSummaryModel> appointmentSummaryList  = new ArrayList<AppointmentSummaryModel>();
		List<AppointmentSummaryModel> pageSizeData = new ArrayList<AppointmentSummaryModel>();
		
		ListRange listrange = new ListRange();
		int index = Constants.GRID_ROW_INDEX;
		
		if( appointmentsList != null ){
			for (AppointmentBM appointment : appointmentsList) {
				AppointmentSummaryModel apptmnt = new AppointmentSummaryModel();
				
				apptmnt.setAppointmentNumber(appointment.getAppointmentNumber());
				apptmnt.setAppointmentDate(appointment.getAppointmentDate());
				apptmnt.setAppointmentStartTime(Converters.getTimeWithColon(appointment.getAppointmentStartTime()));
				apptmnt.setAppointmentEndTime(Converters.getTimeWithColon(appointment.getAppointmentEndTime()));
				apptmnt.setRemarks(appointment.getAppointmentRemarks());

				if (appointment.getAppointmentStatus() != null) {
					apptmnt.setAppointmentStatus(appointment.getAppointmentStatus());
				} else {
					apptmnt.setAppointmentStatus(new CodeAndDescription(null,null));
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
				apptmnt.setAmount(doctorManager.getConsultationCharge(new Integer(appointment.getPrimaryDoctor().getCode())).toString());	
				apptmnt.setPatientId(appointment.getPatientId());
				apptmnt.setPatientName( appointment.getFirstName() + " " + 
										appointment.getMiddleName() + " " + 
										appointment.getLastName());
				
				apptmnt.setRosterCode(appointment.getRosterCode());
				apptmnt.setPatientFirstName(appointment.getFirstName());
				apptmnt.setPatientMiddleName(appointment.getMiddleName());
				apptmnt.setPatientLastName(appointment.getLastName());
				apptmnt.setSerialNo(index);
				
				if( appointment.getAppointmentType() != null ){
					apptmnt.setAppointmentType( new CodeAndDescription( appointment.getAppointmentType().getCode(),
												appointment.getAppointmentType().getDescription()));
				}else{
					apptmnt.setAppointmentType( new CodeAndDescription(null,null));
				}
				
				if( appointment.getReferralType() != null ){
					apptmnt.setReferralType( new CodeAndDescription( appointment.getReferralType().getCode(),
										appointment.getReferralType().getDescription()));
				}else{
					apptmnt.setReferralType( new CodeAndDescription( null, null ));
				}
				
				if( appointment.getReferredBy() != null ){
					apptmnt.setReferredBy( new CodeAndDescription( appointment.getReferredBy().getCode(),
										appointment.getReferredBy().getDescription()));
				}else{
					apptmnt.setReferredBy( new CodeAndDescription( null, null));
				}
				
				appointmentSummaryList.add(apptmnt);
				index++;
			}// end of for Loop
			
			int i = 0;
			while( (start+i < start + count) && (appointmentSummaryList.size() > start+i) ){
					pageSizeData.add(appointmentSummaryList.get(start+i));
					i++;
			}
			
			 listrange.setData(pageSizeData.toArray()); 
			 listrange.setTotalSize(appointmentSummaryList.size());
			
		}else{
			
			Object[] obj = new Object[0];
			listrange.setData(obj);
			listrange.setTotalSize(obj.length);
		}
		
		return listrange;
	}
	
	/**
	 * TODO Replacement of parameters by Map
	 * 
	 * This method is called on Confirm button click of Reschedule Appointment Window
	 * 
	 * This method takes the details from the reschedule window and changes the state of the 
	 * appointment to reschedule
	 * 
	 * @param appointmentNo
	 * @param patientId
	 * @param department
	 * @param primarydoctor
	 * @param oldAppointmentDate
	 * @param oldStartTime
	 * @param oldEndTime
	 * @param reschAppointmentDate
	 * @param reschStartTime
	 * @param reschEndTime
	 * @param patientFirstName
	 * @param patientMiddleName
	 * @param patientLastName
	 * @param departmentCode
	 * @param primarydoctorCode
	 * @param rescheduleFlag
	 */
	public void rescheduleAppointment(
										String appointmentNo, 
										String patientId,
										String speciality, 
										String primarydoctor, 
										String oldAppointmentDate,
										String oldStartTime, 
										String oldEndTime,
										String reschAppointmentDate, 
										String reschStartTime,
										String reschEndTime, 
										String patientFirstName,
										String patientMiddleName, 
										String patientLastName,
										String specialityCode, 
										String primarydoctorCode,
										String rescheduleFlag,
										String rosterCode,
										String appointmentTypeCode,
										String bookingTypeCode,
										String referredBy,
										String userName) {

			Boolean scheduleAnywayFlag = false;

			if (rescheduleFlag != null) {
				scheduleAnywayFlag = true;
			}

			AppointmentBM rescheduledAppointment = new AppointmentBM();

			rescheduledAppointment.setAppointmentNumber(convertIdInStringToInteger(appointmentNo));
			rescheduledAppointment.setAppointmentDate(Converters.getDate(reschAppointmentDate));
			rescheduledAppointment.setAppointmentEndTime(Converters.getTimeWithoutColon(reschEndTime));
			rescheduledAppointment.setAppointmentStartTime(Converters.getTimeWithoutColon(reschStartTime));
			rescheduledAppointment.setSpeaciality(new CodeAndDescription(specialityCode, speciality));
			rescheduledAppointment.setPrimaryDoctor(new CodeAndDescription(primarydoctorCode, primarydoctor));
			rescheduledAppointment.setAppointmentType( new CodeAndDescription( appointmentTypeCode, null ));
			rescheduledAppointment.setBookingType( new CodeAndDescription( bookingTypeCode, null ));
			rescheduledAppointment.setReferredBy( new CodeAndDescription( referredBy, null ));
			rescheduledAppointment.setPatientId(convertIdInStringToInteger(patientId));
			rescheduledAppointment.setFirstName(patientFirstName);
			rescheduledAppointment.setMiddleName(patientMiddleName);
			rescheduledAppointment.setLastName(patientLastName);
			rescheduledAppointment.setModifiedBy( userName );
			
			String doctorRosterCode = getDoctorRosterCode(	Constants.DOCTOR,
															new Integer(primarydoctorCode), 
															Converters.getDate(reschAppointmentDate), 
															Converters.getDate(reschAppointmentDate), 
															Converters.getTimeWithoutColon(reschStartTime), 
															Converters.getTimeWithoutColon(reschEndTime),
															null);
			
			Integer rosterId = doctorRosterCode != null ? new Integer( doctorRosterCode ) : null ;
			rescheduledAppointment.setRosterCode(rosterId);

			AppointmentBM oldAppointment = new AppointmentBM();

			oldAppointment.setAppointmentNumber(convertIdInStringToInteger(appointmentNo));
			oldAppointment.setAppointmentDate(Converters.getDate(oldAppointmentDate));
			oldAppointment.setAppointmentEndTime(Converters.getTimeWithoutColon(oldEndTime));
			oldAppointment.setAppointmentStartTime(Converters.getTimeWithoutColon(oldStartTime));
			oldAppointment.setSpeaciality(new CodeAndDescription(specialityCode,speciality));
			oldAppointment.setPrimaryDoctor(new CodeAndDescription(primarydoctorCode, primarydoctor));
			oldAppointment.setPatientId(convertIdInStringToInteger(patientId));
			oldAppointment.setFirstName(patientFirstName);
			oldAppointment.setMiddleName(patientMiddleName);
			oldAppointment.setLastName(patientLastName);
			oldAppointment.setRosterCode(convertIdInStringToInteger(rosterCode));
			oldAppointment.setModifiedBy( userName );
			
			appointmentManager.rescheduleAppointment(oldAppointment,
					rescheduledAppointment, scheduleAnywayFlag);
	}

	/**
	 * TODO Replacement of parameters with Map
	 * 
	 * This method gets the details from the Cancel Appointment Window 
	 * and changes the state of the appointment from active to Cancel.
	 * 
	 * This method is invoked on the submit button click of Cancel Appointment window
	 *  
	 * @param appointmentNo
	 * @param patientId
	 * @param department
	 * @param primarydoctor
	 * @param patientname
	 * @param appointmentDate
	 * @param startTime
	 * @param endTime
	 * @param patientFirstName
	 * @param patientMiddleName
	 * @param patientLastName
	 * @param departmentCode
	 * @param primaryDoctorCode
	 * @param appointmentRemarks
	 * @param appointmentStatus
	 * @param appointmentStatusCode
	 * @param bookingType
	 * @param bookingTypeCode
	 * @param cancellationReasonCode
	 */
	public void cancelAppointment(	String appointmentNo, 
									String patientId,
									String speciality, 
									String primarydoctor, 
									String patientname,
									String appointmentDate, 
									String startTime, 
									String endTime,
									String patientFirstName, 
									String patientMiddleName,
									String patientLastName, 
									String specialityCode,
									String primaryDoctorCode, 
									String appointmentRemarks,
									String appointmentStatus, 
									String appointmentStatusCode,
									String bookingType, 
									String bookingTypeCode,
									String cancellationReasonCode,
									String rosterCode,
									String userName) {

		
			AppointmentBM appointment = new AppointmentBM();

			appointment.setPatientId(convertIdInStringToInteger(patientId));
			appointment.setAppointmentNumber(convertIdInStringToInteger(appointmentNo));
			appointment.setAppointmentDate(Converters.getDate(appointmentDate));
			appointment.setAppointmentStartTime(Converters.getTimeWithoutColon(startTime));
			appointment.setAppointmentEndTime(Converters.getTimeWithoutColon(endTime));
			appointment.setAppointmentRemarks(appointmentRemarks);
			appointment.setAppointmentStatus(new CodeAndDescription(appointmentStatus, appointmentStatusCode));
			appointment.setCancellationReason(new CodeAndDescription(cancellationReasonCode, null));
			appointment.setBookingType(new CodeAndDescription(bookingType,bookingTypeCode));
			appointment.setSpeaciality(new CodeAndDescription(specialityCode,speciality));
			appointment.setFirstName(patientFirstName);
			appointment.setMiddleName(patientMiddleName);
			appointment.setLastName(patientLastName);
			appointment.setPrimaryDoctor(new CodeAndDescription(primaryDoctorCode, primarydoctor));
			appointment.setRosterCode(convertIdInStringToInteger(rosterCode));
			appointment.setModifiedBy( userName );
			appointmentManager.cancelAppointment(appointment);
	}

	private Integer convertIdInStringToInteger(String id) {
		Integer code = null;

		if (id !=null && !id.equals("")) {
			code = new Integer(id);
		}
		return code;
	}

	/**
	 * This method returns the appointment details corresponding 
	 * to the specified appointment number.
	 * 
	 * This functionality is implemented for view & modify appointment
	 * button handler in manage appointment window.
	 * 
	 * @param appointmentNo
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	public ListRange getAppointmentDetails(String appointmentNo, int start,
			int count, String orderBy) {

		ListRange listrange = new ListRange();
		Object[] appointmentDetailsObj = new Object[1];

		AppointmentBM appointmentDetails = appointmentManager
				.getAppointment(new Integer(appointmentNo));

		if (appointmentDetails.getAppointmentStatus() == null) {
			appointmentDetails.setAppointmentStatus(new CodeAndDescription(null, null));
		}

		if (appointmentDetails.getBookingType() == null) {
			appointmentDetails.setBookingType(new CodeAndDescription(null,null));
		}

		if (appointmentDetails.getSpeaciality() == null) {
			appointmentDetails.setSpeaciality(new CodeAndDescription(null,null));
		}

		if (appointmentDetails.getPrimaryDoctor() == null) {
			appointmentDetails.setPrimaryDoctor(new CodeAndDescription(null, null));
		}
		if( appointmentDetails.getAppointmentType() == null ){
			appointmentDetails.setAppointmentType( new CodeAndDescription( null, null ));
		}
		
		if( appointmentDetails.getReferredBy() == null ){
			appointmentDetails.setReferredBy( new CodeAndDescription( null,null ));
		}
		if( appointmentDetails.getReferralType() == null ){
			appointmentDetails.setReferralType( new CodeAndDescription( null, null ));
		}
		if( appointmentDetails != null ){
			appointmentDetailsObj[0] = appointmentDetails;
			listrange.setData(appointmentDetailsObj);
		}else{
			Object[] obj = new Object[0];
			listrange.setData(obj);
			listrange.setTotalSize(obj.length);
		}

		return listrange;
	}
	
	
	
	

	/**
	 * TODO Replace the parameters with Map
	 * 
	 * This method is implemented for modify the appointment
	 * in the ViewModifyAppointment window which appears on modify button click of 
	 * manage appointment
	 * 
	 * @param appointmentNo
	 * @param patientId
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param appointmentDate
	 * @param appointmentStatusCode
	 * @param bookingDate
	 * @param consultantHasSeenPatient
	 * @param patientCameToVisit
	 * @param patientVisitedConsultant
	 * @param departmentCode
	 * @param doctorCode
	 * @param endTime
	 * @param startTime
	 * @param remarks
	 */
	public void modifyAppointment(
									Integer appointmentNo, 
									Integer patientId,
									String firstName, 
									String middleName, 
									String lastName,
									Date appointmentDate, 
									String appointmentStatusCode,
									Date bookingDate, 
									Boolean patientCameToVisit, 
									Boolean patientVisitedConsultant,
									Boolean doctorHasSeenPatient,
									String specialityCode, 
									String doctorCode, 
									String endTime,
									String startTime, 
									String remarks, 
									Integer rosterCode,
									String referredBy,
									String appointmentType,
									String bookingType,
									String phone,
									String email,
									String userName) {

			AppointmentBM modifiedAppointment = new AppointmentBM();
			
			modifiedAppointment.setPatientId(patientId);
			modifiedAppointment.setAppointmentNumber(appointmentNo);
			modifiedAppointment.setAppointmentDate(appointmentDate);
			modifiedAppointment.setAppointmentStartTime(startTime);
			modifiedAppointment.setAppointmentEndTime(endTime);
			modifiedAppointment.setAppointmentRemarks(remarks);
			modifiedAppointment.setAppointmentStatus(new CodeAndDescription(appointmentStatusCode, null));
			modifiedAppointment.setAppointmentType( new CodeAndDescription(appointmentType,null ));
			modifiedAppointment.setBookingType( new CodeAndDescription( bookingType, null));
			modifiedAppointment.setReferredBy( new CodeAndDescription( referredBy, null ));
			modifiedAppointment.setBookingDate(bookingDate);
			modifiedAppointment.setRosterCode(rosterCode != null ? rosterCode : null);
			modifiedAppointment.setPhone(phone);
			modifiedAppointment.setEmail(email);

			if ( patientCameToVisit ) {
				modifiedAppointment.setPatientCameToVisit(true);
			} else {
				modifiedAppointment.setPatientCameToVisit(false);
			}

			if ( patientVisitedConsultant ) {
				modifiedAppointment.setPatientVisitedConsultant(true);
			} else {
				modifiedAppointment.setPatientVisitedConsultant(false);
			}

			if( doctorHasSeenPatient ){
				modifiedAppointment.setConsultantHasSeenPatient( true );
			}else{
				modifiedAppointment.setConsultantHasSeenPatient( false );
			}
			modifiedAppointment.setPrimaryDoctor(new CodeAndDescription(doctorCode, null));
			modifiedAppointment.setSpeaciality(new CodeAndDescription(specialityCode, null));
			modifiedAppointment.setAppointmentRemarks(remarks);
			modifiedAppointment.setFirstName(firstName);
			modifiedAppointment.setMiddleName(middleName);
			modifiedAppointment.setLastName(lastName);
			modifiedAppointment.setModifiedBy(userName);

			appointmentManager.modifyAppointment(modifiedAppointment);
	}

	/**
	 * This method gets the list of Rosters of a doctor. This method is called after selecting the appointment date
	 * and doctor in New Appointment Window for displaying the rosters in the calander along with the booked appointments. 
	 * 
	 * @param fromDate
	 * @param toDate TODO
	 * @param doctorId
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	public List<DoctorAppointmentRosterModel> getDoctorRoster(Date fromDate, Date toDate, String doctorId) {

		List<DoctorAppointmentRosterModel> rosterModelList = new ArrayList<DoctorAppointmentRosterModel>();

			Integer doctorCode = null;
			if (doctorId != null && !doctorId.equals("")) {
				doctorCode = new Integer(doctorId);
			}

			List<RosterModel> rosterList = rosterManager.getRoster(
					Constants.DOCTOR, doctorCode, fromDate,
					toDate, null, null, null, null, null, null);

			
			int recordId = 0;

			if (rosterList != null) {
				for (RosterModel roster : rosterList) {
					if(roster.getActive()){
						
						DoctorAppointmentRosterModel doctorRoster = new DoctorAppointmentRosterModel();

						doctorRoster.setAgenda(null);
						doctorRoster.setEndTime(roster.getEndTime());
						doctorRoster.setRecordId(recordId);
						doctorRoster.setRemarks(null);
						doctorRoster.setStartTime( roster.getStartTime());
						doctorRoster.setEntityName(roster.getEntityName());
						doctorRoster.setEntityId(roster.getEntityId());

						doctorRoster.setEntityId(roster.getEntityId());
						doctorRoster.setRosterCode(roster.getRosterCode());
						doctorRoster.setWorkingDate(roster.getWorkingDate());
						doctorRoster.setRoomBM(roster.getRoomBM());
						recordId++;

						rosterModelList.add(doctorRoster);
						
						List<AppointmentBM> appointmentList = appointmentManager.getAppointmentsForARoster(roster.getRosterCode());

						if (appointmentList != null) {
							for (AppointmentBM appointment : appointmentList) {

								DoctorAppointmentRosterModel appointmentRoster = new DoctorAppointmentRosterModel();

								appointmentRoster.setAgenda(appointment.getAppointmentAgenda());
								appointmentRoster.setRecordId(recordId);
								appointmentRoster.setRemarks(appointment.getAppointmentRemarks());
								appointmentRoster.setEndTime(appointment.getAppointmentEndTime());
								appointmentRoster.setStartTime(appointment.getAppointmentStartTime());
								
								appointmentRoster.setEntityName(appointment.getFirstName()+ 
										" "+ 
										appointment.getMiddleName()+ 
										" " + 
										appointment.getLastName());

								appointmentRoster.setEntityId(appointment.getPatientId());
								appointmentRoster.setRosterCode(appointment.getRosterCode());
								appointmentRoster.setRoomBM(roster.getRoomBM());
								appointmentRoster.setWorkingDate(appointment.getAppointmentDate());
								appointmentRoster.setIsAppointment(true);
								appointmentRoster.setPatientId( appointment.getPatientId().toString());
								if( null != appointment.getBookingType() ){
									appointmentRoster.setBookingType( appointment.getBookingType().getDescription());
								}
								appointmentRoster.setAppointmentNumber( appointment.getAppointmentNumber() );
								recordId++;

								rosterModelList.add(appointmentRoster);
							}
						}
					}
				}
			}

		return rosterModelList;
	}


	/**
	 * TODO
	 * This method should be optimized, all the parameters should be passed to back-end and it will become the part of query.
	 * @param entityType
	 * @param entityId
	 * @param fromDate
	 * @param toDate
	 * @param startTime
	 * @param endTime
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @return
	 */
	public String getDoctorRosterCode(	String entityType, 
										Integer entityId,
										Date fromDate, 
										Date toDate, 
										String startTime, 
										String endTime,
										String entityName) {
		
		String rosterCode = null;
		
		String isRosterRequired = configurationManager.getSystemConfiguration(ConfigurationConstants.IS_ROSTER_REQUIRED_NAME);
		if(isRosterRequired.equals(ConfigurationConstants.IS_ROSTER_REQUIRED_VALUE)){
			List<RosterModel> rosterList = rosterManager.getRoster( entityType,
																	entityId, 
																	fromDate, 
																	toDate, 
																	null, 
																	null, 
																	entityName,
																	null,
																	null,
																	null);
				
				if (rosterList != null) {
					for (RosterModel roster : rosterList) {
					if ((new Integer(startTime) >= new Integer(roster.getStartTime()))&& 
							(new Integer(endTime) <= new Integer(roster.getEndTime())) && 
								roster.getActive()) {
							return roster.getRosterCode().toString();
						}
					}
				}
				
				if (null == rosterCode) {
					Fault fault = new Fault( ApplicationErrors.NO_ROSTER_FOUND );
					
					throw new HCISException( fault.getFaultMessage(),
					fault.getFaultCode(),
					fault.getFaultType() );
			}
		}
		return rosterCode;
	}
	
	/**
	 * This method removes the list appointments. 
	 * @param appointmentBMlist
	 */
	public void deleteAppointments( List<AppointmentBM> appointmentBMlist){
		appointmentManager.removeAppointments(appointmentBMlist);
	}
	
	public ListRange getPatientDetails(String patientId, int start, int count, String orderBy) {
		   Object[] patientObj = new Object[1];
		   ListRange listrange = new ListRange();
		   Integer patientCode = null;
		   PatientInfoDetailBM patientDetails = null;

			if (!patientId.equals("")) {
				patientCode = new Integer(patientId);
				patientDetails = patientManager.getPatientInfoDetailBM(patientCode);
				
				PatientDetailsModel patientDetailsBM = new PatientDetailsModel();
				
				if( patientDetails != null){
					
//	PresonalAdditionalDetails
					
					patientDetailsBM.setPersonalDetailsAdditionalBM(patientDetails.getPersonalDetailsAdditionalBM());
					
					if( patientDetails.getPersonalDetailsAdditionalBM() == null ){
						PersonalDetailsAdditionalBM patientPersonalAddintionalDetails = new PersonalDetailsAdditionalBM();
						
						patientPersonalAddintionalDetails.setBloodGroup(new CodeAndDescription(null,null));
						patientPersonalAddintionalDetails.setIdProof(new CodeAndDescription(null,null));
						patientPersonalAddintionalDetails.setMaritalStatus(new CodeAndDescription(null,null));
						patientPersonalAddintionalDetails.setMotherTongue(new CodeAndDescription(null,null));
						patientPersonalAddintionalDetails.setNationality(new CodeAndDescription(null,null));
						patientPersonalAddintionalDetails.setReferredBy(new CodeAndDescription(null,null));
						patientPersonalAddintionalDetails.setReligion(new CodeAndDescription(null,null));
						
						patientDetailsBM.setPersonalDetailsAdditionalBM(patientPersonalAddintionalDetails);
					}else{
						if( patientDetails.getPersonalDetailsAdditionalBM().getBloodGroup() == null ){
							patientDetailsBM.getPersonalDetailsAdditionalBM().setBloodGroup( new CodeAndDescription( null, null ));
						}
						
						if( patientDetails.getPersonalDetailsAdditionalBM().getIdProof() == null){
							patientDetailsBM.getPersonalDetailsAdditionalBM().setIdProof( new CodeAndDescription( null, null ));
						}
						
						if( patientDetails.getPersonalDetailsAdditionalBM().getMaritalStatus() == null){
							patientDetailsBM.getPersonalDetailsAdditionalBM().setMaritalStatus( new CodeAndDescription( null, null ));
						}
						
						if( patientDetails.getPersonalDetailsAdditionalBM().getMotherTongue() == null){
							patientDetailsBM.getPersonalDetailsAdditionalBM().setMotherTongue( new CodeAndDescription( null, null ));
						}
						
						if( patientDetails.getPersonalDetailsAdditionalBM().getNationality() == null){
							patientDetailsBM.getPersonalDetailsAdditionalBM().setNationality( new CodeAndDescription( null, null ));
						}
						
						if( patientDetails.getPersonalDetailsAdditionalBM().getReferredBy() == null){
							patientDetailsBM.getPersonalDetailsAdditionalBM().setReferredBy( new CodeAndDescription( null, null ));
						}
						
						if( patientDetails.getPersonalDetailsAdditionalBM().getReligion() == null){
							patientDetailsBM.getPersonalDetailsAdditionalBM().setReligion( new CodeAndDescription( null, null ));
						}
					}
					
					
//		PresonalDetails
					
					patientDetailsBM.setPersonalDetailsBM(patientDetails.getPersonalDetailsBM());
					
					if( patientDetails.getPersonalDetailsBM() == null ){
						PersonalDetailsBM patientPersonalDetails = new PersonalDetailsBM();
						
						patientPersonalDetails.setGender(new CodeAndDescription(null,null));
						patientPersonalDetails.setPatientCategory(new CodeAndDescription(null,null));
						patientPersonalDetails.setPatientRating(new CodeAndDescription(null,null));
						patientPersonalDetails.setRegistrationStatus(new CodeAndDescription(null,null));
						patientPersonalDetails.setRegistrationType(new CodeAndDescription(null,null));
						patientPersonalDetails.setTitle(new CodeAndDescription(null,null));
						
						patientDetailsBM.setPersonalDetailsBM(patientPersonalDetails);
					}else{
						if( patientDetails.getPersonalDetailsBM().getGender() == null){
							patientDetailsBM.getPersonalDetailsBM().setGender( new CodeAndDescription( null, null ));
						}
						
						if( patientDetails.getPersonalDetailsBM().getPatientCategory() == null){
							patientDetailsBM.getPersonalDetailsBM().setPatientCategory( new CodeAndDescription( null, null ));
						}
						
						if( patientDetails.getPersonalDetailsBM().getPatientRating() == null){
							patientDetailsBM.getPersonalDetailsBM().setPatientRating( new CodeAndDescription( null, null ));
						}
						
						
						if( patientDetails.getPersonalDetailsBM().getRegistrationStatus() == null){
							patientDetailsBM.getPersonalDetailsBM().setRegistrationStatus( new CodeAndDescription( null, null ));
						}
						
						if( patientDetails.getPersonalDetailsBM().getRegistrationType() == null){
							patientDetailsBM.getPersonalDetailsBM().setRegistrationType( new CodeAndDescription( null, null ));
						}
						
						if( patientDetails.getPersonalDetailsBM().getTitle() == null){
							patientDetailsBM.getPersonalDetailsBM().setTitle( new CodeAndDescription( null, null ));
						}
					}

//		Contact details
					
					ContactBM contactBM = patientDetails.getContacts();
					
					List<ContactDetailsBM> contactDetailList = null;
					
					if( contactBM != null){
						contactDetailList = contactBM.getContactDetailList();
					}
					
					if( contactDetailList != null && contactDetailList.size() > 0){
							Iterator<ContactDetailsBM> contactDetailsIter = contactDetailList.iterator();
							while(contactDetailsIter.hasNext()) {
								ContactDetailsBM contactDetailsBM = contactDetailsIter.next();
								
								setCodeAndDescriptionTonull( contactDetailsBM );
								
								if(contactDetailsBM.getContactType().getCode().equals(Constants.CURRENT)) {
									patientDetailsBM.setCurrentBM(contactDetailsBM);
								}else if(contactDetailsBM.getContactType().getCode().equals(Constants.PERMANENT)) {
									patientDetailsBM.setPermanentBM(contactDetailsBM);
								}else if(contactDetailsBM.getContactType().getCode().equals(Constants.EMERGENCY_PRIMARY)){
									patientDetailsBM.setEmergencyPrimaryBM(contactDetailsBM);
								}else if(contactDetailsBM.getContactType().getCode().equals(Constants.EMERGENCY_SECONDARY)){
									patientDetailsBM.setEmergencySecondaryBM(contactDetailsBM);
								}
							}
							
							if( patientDetailsBM.getCurrentBM() == null){
								patientDetailsBM.setCurrentBM( setCodeAndDescriptionTonull( patientDetailsBM.getCurrentBM()) );
							}
							
							if( patientDetailsBM.getEmergencyPrimaryBM() == null){
								patientDetailsBM.setEmergencyPrimaryBM( setCodeAndDescriptionTonull( patientDetailsBM.getEmergencyPrimaryBM()) );
							}
							
							if( patientDetailsBM.getPermanentBM() == null){
								patientDetailsBM.setPermanentBM( setCodeAndDescriptionTonull( patientDetailsBM.getPermanentBM()) );
							}
							
							if( patientDetailsBM.getEmergencySecondaryBM() == null){
								patientDetailsBM.setEmergencySecondaryBM( setCodeAndDescriptionTonull( patientDetailsBM.getEmergencySecondaryBM()) );
							}
						}
					}
				
				patientObj[0] = patientDetailsBM;
				listrange.setData(patientObj);
				listrange.setTotalSize(patientObj.length);
			}else{
				
				Object[] obj = new Object[0];
				listrange.setData(obj);
				listrange.setTotalSize(obj.length);
			}
			return listrange;
	}
	
	private ContactDetailsBM setCodeAndDescriptionTonull( ContactDetailsBM contactDetailsBM){
		ContactDetailsBM contact = null;
		
		if( contactDetailsBM == null){
			contact =  new ContactDetailsBM();
			
			contact.setContactType(new CodeAndDescription(null,null));
			contact.setCountry(new CodeAndDescription(null,null));
			contact.setForEntity(new CodeAndDescription(null,null));
			contact.setGender(new CodeAndDescription(null,null));
			contact.setRelationCode(new CodeAndDescription(null,null));
			contact.setSalutation(new CodeAndDescription(null,null));
			contact.setState(new CodeAndDescription(null,null));
			
			contactDetailsBM = contact;
			
		}else{
			if(contactDetailsBM.getContactType() == null){
				contactDetailsBM.setContactType( new CodeAndDescription( null, null ));
			}
			
			if(contactDetailsBM.getCountry() == null){
				contactDetailsBM.setCountry( new CodeAndDescription( null, null ));
			}
			
			if(contactDetailsBM.getForEntity() == null){
				contactDetailsBM.setForEntity( new CodeAndDescription( null, null ));
			}
			
			if(contactDetailsBM.getGender() == null){
				contactDetailsBM.setGender( new CodeAndDescription( null, null ));
			}
			
			if(contactDetailsBM.getRelationCode() == null){
				contactDetailsBM.setRelationCode( new CodeAndDescription( null, null ));
			}
			
			if(contactDetailsBM.getSalutation() == null){
				contactDetailsBM.setSalutation( new CodeAndDescription( null, null ));
			}
			
			if(contactDetailsBM.getState() == null){
				contactDetailsBM.setState( new CodeAndDescription( null, null ));
			}
		}
		return contactDetailsBM;
	}
	
	/**
	 * This method is used to save the prescription, clinical prescription details[services] and observation details 
	 * at the time when doctor has seen the patient.
	 * 
	 * This functionality is being implemented for the save button click
	 * in Consultation details window which pops up when Consultant has seen patient 
	 * check box is checked in View Modify Appointment.
	 */
	public void savePrescriptionAndObservation( PrescriptionBM[] prescriptionBMList ,
												ObservationBM[] observationBMList, 
												AssignSvcAndPackageBM assignSvcAndPackageBM, 
												Integer appointmentNumber){
		
		if( prescriptionBMList!= null && prescriptionBMList.length > 0 ){
			prescriptionManager.addPrescription(Arrays.asList(prescriptionBMList));
		}
		if( observationBMList != null && observationBMList.length > 0){
			
			//Observation will be always one for an appointment 
			observationManager.modifyObservationDetails(observationBMList[0]);
			
		}
		if(assignSvcAndPackageBM != null && ( 
				(assignSvcAndPackageBM.getAssignedPackageBMList() != null
				&& !assignSvcAndPackageBM.getAssignedPackageBMList().isEmpty())
				|| (assignSvcAndPackageBM.getAssignedServiceBMList() != null 
				&& !assignSvcAndPackageBM.getAssignedServiceBMList().isEmpty()))){
			serviceManager.assignSvcAndPackages(assignSvcAndPackageBM);
		}
	}
	
	/**
	 * This method returns consultation charge of the doctor selected in new appointment window
	 * @param doctorId
	 * @return
	 */
	public String getConsutationCharge( String doctorId){
		if( doctorId != null && doctorId.length() > 0){
			Double consultationCharge = doctorManager.getConsultationCharge(new Integer(doctorId));
			if( consultationCharge != null){
				return consultationCharge.toString();
			}
		}
		return null;
	}
	
	public String getConsutationChargeForFolloeUp( String doctorId){
		if( doctorId != null && doctorId.length() > 0){
			Double followUpCharge = doctorManager.getConsultationForFollowUpCharge(new Integer(doctorId));
			if( followUpCharge != null){
				return followUpCharge.toString();
			}
		}
		return null;
	}
	
	public void setAppointmentManager(AppointmentManager appointmentManager) {
		this.appointmentManager = appointmentManager;
	}

	public void setRosterManager(RosterManager rosterManager) {
		this.rosterManager = rosterManager;
	}

	public void setPatientManager(PatientManager patientManager) {
		this.patientManager = patientManager;
	}

	public void setPrescriptionManager(PrescriptionManager prescriptionManager) {
		this.prescriptionManager = prescriptionManager;
	}

	public void setObservationManager(ObservationManager observationManager) {
		this.observationManager = observationManager;
	}

	/**
	 * @param serviceManager the serviceManager to set
	 */
	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public void setDoctorManager(DoctorManager doctorManager) {
		this.doctorManager = doctorManager;
	}

	public void setConfigurationManager(ConfigurationManager configurationManager) {
		this.configurationManager = configurationManager;
	}

}
