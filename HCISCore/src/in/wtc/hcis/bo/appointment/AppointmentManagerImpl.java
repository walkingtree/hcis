package in.wtc.hcis.bo.appointment;

import in.wtc.hcis.bm.base.AppointmentBM;
import in.wtc.hcis.bm.base.AssignSvcAndPackageBM;
import in.wtc.hcis.bm.base.AssignedServiceBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.DoctorBM;
import in.wtc.hcis.bm.base.PatientLiteBM;
import in.wtc.hcis.bm.base.RosterModel;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.ConvertNumberToWordFormat;
import in.wtc.hcis.bo.common.DataModelConverter;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.common.WtcUtils;
import in.wtc.hcis.bo.configuration.ConfigurationManager;
import in.wtc.hcis.bo.constants.ConfigurationConstants;
import in.wtc.hcis.bo.constants.RegistrationConstants;
import in.wtc.hcis.bo.constants.ReportsDetail;
import in.wtc.hcis.bo.doctor.DoctorManager;
import in.wtc.hcis.bo.integration.EagleIntegration;
import in.wtc.hcis.bo.integration.EagleIntegrationImpl;
import in.wtc.hcis.bo.patient.PatientManager;
import in.wtc.hcis.bo.roster.RosterManager;
import in.wtc.hcis.bo.services.ServiceManager;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.Validate;

import com.wtc.hcis.da.AppointmentHistory;
import com.wtc.hcis.da.AppointmentHistoryId;
import com.wtc.hcis.da.AppointmentStatus;
import com.wtc.hcis.da.Appointments;
import com.wtc.hcis.da.BookingType;
import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.Roster;
import com.wtc.hcis.da.Service;
import com.wtc.hcis.da.extn.AppointmentHistoryDAOExtn;
import com.wtc.hcis.da.extn.AppointmentsDAOExtn;
import com.wtc.report.ReportManager;
import com.wtc.report.model.ReportDetail;

public class AppointmentManagerImpl implements AppointmentManager {
	
	private AppointmentsDAOExtn appointmentsDAO;
	private AppointmentHistoryDAOExtn appointmentHistoryDAO;

	private DataModelConverter converter;

	private DoctorManager doctorManager;
	private DataModelManager dataModelManager;
	private EagleIntegration eagleIntegration = new EagleIntegrationImpl();
	private PatientManager patientManager;
	private ReportManager reportManager;
	private RosterManager rosterManager;
	private ConfigurationManager configurationManager;
	private ServiceManager serviceManager;
	
	private ResourceBundle Config = ResourceBundle.getBundle("in.wtc.hcis.bo.properties.Config", Locale.getDefault());
	private ResourceBundle ReportsConfig = ResourceBundle.getBundle("in.wtc.hcis.bo.properties.ReportDetail", Locale.getDefault());

	/* (non-Javadoc)
	 * Appointment will be created in the status supplied by the caller or the default status decided by the 
	 * hospital. 
	 * @see in.wtc.hcis.bo.appointment.AppointmentManager#addAppointment(in.wtc.hcis.bm.base.AppointmentBM)
	 */
	public AppointmentBM addAppointment(AppointmentBM appointmentBM) {
		try {

			Doctor doctor = dataModelManager.getDoctor(Integer.parseInt(appointmentBM.getPrimaryDoctor().getCode()));
			Appointments appointments = converter.convertAppointmentsBM2DM(appointmentBM, null);

			AppointmentStatus appointmentStatus = dataModelManager.getAppointmentStatus(
					Config.getString(AppointmentConstants.DEFAULT_APPOINTMENT_STATUS));

			// if ( appointmentBM.getAppointmentStatus() != null &&
			// appointmentBM.getAppointmentStatus().getCode() != null ) {
			// appointmentStatus = dataModelManager.getAppointmentStatus(
			// appointmentBM.getAppointmentStatus().getCode() );
			// } else {
			// appointmentStatus = dataModelManager.getAppointmentStatus(
			// Config.getString( AppointmentConstants.DEFAULT_APPOINTMENT_STATUS
			// ) );
			// }

			if (appointments.getBookingType() == null) {
				String bookingTypeCd = Config.getString(AppointmentConstants.DEFAULT_BOOKING_TYPE);
				BookingType bookingType = dataModelManager.getBookingType(bookingTypeCd);
				appointments.setBookingType(bookingType);
			}

			appointments.setPhone(appointmentBM.getPhone());
			appointments.setEmail(appointmentBM.getEmail());
			appointments.setPrimaryAppointmentNumber(appointmentBM.getPrimaryAppointmentNumber());
			
			appointments.setAppointmentStatus(appointmentStatus);
			appointments.setCreateDtm(new Date());
			appointments.setCreatedBy(appointmentBM.getModifiedBy());

			// Before persisting the appointment details, ensure that the same
			// slot is not already booked
			Map<String, Appointments> existingAppointmentsMap = 
				appointmentsDAO.getBookedAppointments(appointmentBM.getRosterCode(),
							appointmentBM.getAppointmentDate(), appointmentBM.getAppointmentStartTime(), 
							appointmentBM.getAppointmentEndTime());

			if (existingAppointmentsMap != null
					&& !existingAppointmentsMap.isEmpty()) {
				throw new Exception("For doctor = " + doctor.getFirstName()
						+ " " + doctor.getMiddleName() + " "
						+ doctor.getLastName() + ", the slot between time "
						+ appointmentBM.getAppointmentStartTime() + " -- "
						+ appointmentBM.getAppointmentEndTime() + " "
						+ " is not available on "
						+ appointmentBM.getAppointmentDate());
			}

			appointmentsDAO.save(appointments);

			this.createAppointmentHistory(appointments);
			
			// Assign appointment service to patient and render it.
			appointmentBM.setAppointmentNumber(appointments.getAppointmentNumber());
			Service assginedSvc = this.assignAppointmentService(appointmentBM);

			//
			//TODO: Now After this billing method should get invoked ( from UI itself)
			//Billing will create receivable for this service
			
//			// Create a receivable
//			Integer businessPartnerId = eagleIntegration.getBusinessPartnerId(appointmentBM.getPatientId(), RegistrationConstants.REFERENCE_TYPE_PAT);
//			eagleIntegration.createInvoice(businessPartnerId, assginedSvc.getServiceCharge());
			
			return converter.convertAppointmentsDM2BM(appointments);
			
		} catch (HCISException e) {
			throw e;
			
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CREATE_APPOINTMENT_FAILED);

			throw new HCISException(fault.getFaultMessage() + e.getMessage(),
					fault.getFaultCode(), fault.getFaultType());
		}

	}
	
	
	private void createAppointmentHistory( Appointments appointments ) throws HCISException {
		try {
			AppointmentHistory appointmentHistory = new AppointmentHistory();
			AppointmentHistoryId appointmentHistoryId = new AppointmentHistoryId();
			appointmentHistoryId.setAppointmentNumber( appointments.getAppointmentNumber() );
			appointmentHistoryId.setStatusCode( appointments.getAppointmentStatus().getStatusCode() );
			appointmentHistory.setId( appointmentHistoryId );
			
			appointmentHistory.setAppointmentStatus( appointments.getAppointmentStatus() );
			appointmentHistory.setAppointments( appointments );
						
			appointmentHistory.setAppointmentStatus( appointments.getAppointmentStatus() );
			appointmentHistory.setCreatedBy( appointments.getModifiedBy() );
			appointmentHistory.setCreatedDtm( new Date() );
			appointmentHistoryDAO.save( appointmentHistory );
		} catch ( Exception e ) {
			Fault fault = new Fault( ApplicationErrors.CREATE_APPOINTMENT_HISTORY_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		
	}

	public String generateVisitSlip(Integer appointmentNbr) throws HCISException{
		try {
			AppointmentBM appointmentBM = getAppointment(appointmentNbr);
			RosterModel rosterModel = null;
			if (appointmentBM == null) {
				throw new Exception("Appointment with appointment number = " + appointmentNbr + " does not exist.");
			}
			
			PatientLiteBM patientLiteBM = patientManager.getPatientLiteBM(appointmentBM.getPatientId());
			
			String isRosterRequired = configurationManager.getSystemConfiguration(ConfigurationConstants.IS_ROSTER_REQUIRED_NAME);
			if(isRosterRequired.equals(ConfigurationConstants.IS_ROSTER_REQUIRED_VALUE)){
				rosterModel = rosterManager.getRosterModel(appointmentBM.getRosterCode());
			}
			
			DoctorBM doctorBM = doctorManager.getDoctorDetail(Integer.valueOf(appointmentBM.getPrimaryDoctor().getCode()));
			
			Map<String, Object> reportParamMap = new HashMap<String, Object>();
			reportParamMap.put("patientName", appointmentBM.getFirstName() + " " + appointmentBM.getMiddleName() + " " + appointmentBM.getLastName());
			reportParamMap.put("patientId", appointmentBM.getPatientId().toString());
			reportParamMap.put("gender", patientLiteBM.getGender() != null ? patientLiteBM.getGender().getDescription() : "");
			reportParamMap.put("age", patientLiteBM.getDateOfBirth() != null ? WtcUtils.calculateDOB(patientLiteBM.getDateOfBirth()) : "");
			reportParamMap.put("consultationDate", appointmentBM.getAppointmentDate().toString());
			reportParamMap.put("doctorName", doctorBM.getFirstName() + " " + doctorBM.getMiddleName() + " " + doctorBM.getLastName());
			reportParamMap.put("roomNo", rosterModel != null ? (rosterModel.getRoomBM() != null ? rosterModel.getRoomBM().getDescription(): "") : "");
			
			List<ContactDetailsBM> contacts = doctorBM.getContactDetailList();
			ContactDetailsBM contactDetailsBM = null;
			if (contacts != null) {
				contactDetailsBM = contacts.get(0);
			}
			if (contactDetailsBM != null) {
				reportParamMap.put("mobileNo", contactDetailsBM.getMobileNumber());
				reportParamMap.put("emailId", contactDetailsBM.getEmail());
			}
			
			ReportDetail reportDetail = new ReportDetail();
			reportDetail.setName(ReportsConfig.getString(ReportsDetail.PATIENT_VISIT_SLIP_REPORT_NAME));
			reportDetail.setFileName(ReportsConfig.getString(ReportsDetail.PATIENT_VISIT_SLIP_REPORT_FILENAME));
			reportDetail.setReportsDirPath(ReportsConfig.getString(ReportsDetail.REPORTS_DIR_PATH));
			String retStr = reportManager.generateReport(reportDetail, reportParamMap, null);
			
			return retStr;
				
		} catch (Exception exception) {
			Fault fault = new Fault(ApplicationErrors.ERROR_RUNNING_REPORT);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault
					.getFaultType());
		}
		
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.appointment.AppointmentManager#getAppointments(java.util.Date, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<AppointmentBM> getAppointments(	Date appointmentFromDate,
												Date appointmentToDate, 
												Integer patientId, 
												String patientName,												String doctorName, 
												String appointmentStatus,
												String bookingType, 
												String timeFrom, 
												String timeTo ) 
	{
		
		try {
			List<AppointmentBM> appointmentBMList = new ArrayList<AppointmentBM>();

			List<Appointments>appointmentsDMList = appointmentsDAO.findAppointments(appointmentFromDate, 
																					appointmentToDate, 
																					patientId, 
																					patientName, 
																					doctorName, 
																					appointmentStatus, 
																					bookingType, 
																					timeFrom, 
																					timeTo );
			if( appointmentsDMList!= null ){
				Iterator<Appointments> appointmentsIter = appointmentsDMList.iterator();
				
				while(appointmentsIter.hasNext()){
					AppointmentBM appointmentBM = converter.convertAppointmentsDM2BM(appointmentsIter.next());
					appointmentBMList.add( appointmentBM );
				}
				
				return appointmentBMList;
			}
			else{
				return null;
			}
			
		} catch( Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.READ_APPOINTMENT_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + exception.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		

	}

	public boolean cancelAppointment( AppointmentBM appointmentBM ) throws HCISException {
		
		try {
			Appointments appointments = dataModelManager.getAppointment( appointmentBM.getAppointmentNumber() );
			
			// An appointment can be cancelled only when it is in BOOKED or CONFIRMED status.
			AppointmentStatus currentAppointmentStatus = appointments.getAppointmentStatus();
			
			if ( !currentAppointmentStatus.getStatusCode().equals( AppointmentConstants.BOOKED_APPOINTMENT )  && 
				 !currentAppointmentStatus.getStatusCode().equals( AppointmentConstants.CONFIRMED_APPOINTMENT )	) {
				StringBuilder stringBuilder = new StringBuilder(". Current appointment with APPOINTMENT_NBR = ");
				stringBuilder.append( appointmentBM.getAppointmentNumber() )
							 .append(" is in status, ")
							 .append( currentAppointmentStatus.getDescription() )
							 .append(", which does not allow cancellation.");
				
				throw new Exception( stringBuilder.toString() );
			}
			
			AppointmentStatus appointmentStatus = dataModelManager.getAppointmentStatus( AppointmentConstants.CANCELED_APPOINTMENT );
			
			appointments.setAppointmentStatus(appointmentStatus);
			appointments.setModifiedBy( appointmentBM.getModifiedBy() );
			appointments.setLastModifiedDtm( new Date() );
			
			appointments.setAppointmentRemarks( appointments.getAppointmentRemarks() == null ? "" : appointments.getAppointmentRemarks() + " -- Cancelled " );
			
			appointmentsDAO.attachDirty( appointments );
			
			this.createAppointmentHistory(appointments);
			
			cancelAppointmentService(appointmentBM);
			
			return true;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.CANCEL_APPOINTMENT_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}

	

	public AppointmentBM modifyAppointment( AppointmentBM modifiedAppointmentBM )
			throws HCISException {
		Appointments existingAppointment = dataModelManager.getAppointment( modifiedAppointmentBM.getAppointmentNumber() ); 
		Appointments appointments = converter.convertAppointmentsBM2DM( modifiedAppointmentBM, existingAppointment );
		AppointmentStatus appointmentStatus = null;
		
		boolean consultationOverrideFlag = false;
		
		if ( modifiedAppointmentBM.getConsultantHasSeenPatient() != null && 
			 modifiedAppointmentBM.getConsultantHasSeenPatient().equals( Boolean.TRUE ) ) {
			consultationOverrideFlag = true;
			appointmentStatus = dataModelManager.getAppointmentStatus( AppointmentConstants.DOCTOR_CAPTURED );
			appointments.setAppointmentStatus( appointmentStatus );
			
			AppointmentHistory appointmentHistory = this.getAppointmentsHistory( appointments.getAppointmentNumber(), 
					                                                             AppointmentConstants.DOCTOR_CAPTURED );
			
			if ( appointmentHistory == null ) {
				this.createAppointmentHistory( appointments );
				appointments.setCapturedDtm(new Date());
			}
		}
		
		if ( !consultationOverrideFlag && 
			 modifiedAppointmentBM.getPatientVisitedConsultant() != null &&	
			 modifiedAppointmentBM.getPatientVisitedConsultant().equals( Boolean.TRUE ) ) {
			
			consultationOverrideFlag = true;
			appointmentStatus = dataModelManager.getAppointmentStatus( AppointmentConstants.PATIENT_VISITED );
			appointments.setAppointmentStatus( appointmentStatus );
			
			AppointmentHistory appointmentHistory = this.getAppointmentsHistory( appointments.getAppointmentNumber(), AppointmentConstants.PATIENT_VISITED );
			
			if ( appointmentHistory == null ) {
				this.createAppointmentHistory( appointments );
			}
		}
		
		if ( !consultationOverrideFlag && 
			 modifiedAppointmentBM.getPatientCameToVisit() != null && 
			 modifiedAppointmentBM.getPatientCameToVisit().equals( Boolean.TRUE ) ) {
			
			appointmentStatus = dataModelManager.getAppointmentStatus( AppointmentConstants.PATIENT_CAME_TO_VISIT );
			appointments.setAppointmentStatus( appointmentStatus );
			
			AppointmentHistory appointmentHistory = this.getAppointmentsHistory( appointments.getAppointmentNumber(), AppointmentConstants.PATIENT_CAME_TO_VISIT );
			
			if ( appointmentHistory == null ) {
				this.createAppointmentHistory( appointments );
			}
		}
		appointments.setModifiedBy( modifiedAppointmentBM.getModifiedBy());
		
		appointmentsDAO.attachDirty( appointments );
		
		return converter.convertAppointmentsDM2BM(appointments);
	}

	public boolean removeAppointments(List<AppointmentBM> appointmentBMlist)
			throws HCISException {
		try {
			if ( appointmentBMlist != null ) {
				String defaultAppointmentStatus = Config.getString( AppointmentConstants.DEFAULT_APPOINTMENT_STATUS );
				for ( AppointmentBM appointmentBM : appointmentBMlist ) {
					Appointments appointments = dataModelManager.getAppointment( appointmentBM.getAppointmentNumber() );
					
					AppointmentHistory appointmentHistory = this.getAppointmentsHistory( appointments.getAppointmentNumber(), defaultAppointmentStatus  );
					
					appointmentHistoryDAO.delete( appointmentHistory );
					
					appointmentsDAO.delete( appointments );
				}
			}
			
			return true;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.REMOVE_APPOINTMENTS_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	private AppointmentHistory getAppointmentsHistory( Integer appointmentNumber,
			                                           String appointmentStatusCode ) throws HCISException {
		
		AppointmentHistoryId appointmentHistoryId = new AppointmentHistoryId();
		appointmentHistoryId.setAppointmentNumber( appointmentNumber );
		appointmentHistoryId.setStatusCode( appointmentStatusCode );
	
		AppointmentHistory appointmentHistory = appointmentHistoryDAO.findById( appointmentHistoryId );
		
		return appointmentHistory;
	}

	public AppointmentBM rescheduleAppointment( AppointmentBM oldAppointmentBM, 
			                                    AppointmentBM newAppointmentBM, 
			                                    Boolean       scheduleAnywayFlag ) throws HCISException {
		try {
			Appointments oldAppointment = dataModelManager.getAppointment( oldAppointmentBM.getAppointmentNumber() );
			AppointmentStatus rescheduledAppointmentStatus = dataModelManager.getAppointmentStatus( AppointmentConstants.RESCHEDULED_APPOINTMENT );
			oldAppointment.setAppointmentStatus( rescheduledAppointmentStatus );
			oldAppointment.setModifiedBy( oldAppointmentBM.getModifiedBy() );
			oldAppointment.setLastModifiedDtm( new Date() );
			appointmentsDAO.attachDirty( oldAppointment );
			
			this.createAppointmentHistory( oldAppointment );
			
			AppointmentBM addedAppointmentBM = null;
			
			// Create new appointment for the patient with the doctor. If the scheduleAnywayFlag is set 
			// to true then any available slot within the specified roster will be picked up for 
			// fixing the appointment.
			Map<String, Appointments>bookedAppointments = appointmentsDAO.getBookedAppointments( newAppointmentBM.getRosterCode(), 
					                                                                             null, 
					                                                                             null, 
					                                                                             null );
			
			if ( scheduleAnywayFlag.equals( Boolean.TRUE ) ) {
				//
				// Find out the first available slot
				//
				String appointmentSlotInterval = Config.getString( AppointmentConstants.APPOINTMENT_SLOT_INTERVAL );
				
				
				Roster roster = null;
				String isRosterRequired = configurationManager.getSystemConfiguration(ConfigurationConstants.IS_ROSTER_REQUIRED_NAME);
				if(isRosterRequired.equals(ConfigurationConstants.IS_ROSTER_REQUIRED_VALUE)){
					roster = dataModelManager.getRoster( newAppointmentBM.getRosterCode() );
				}
				
				if(roster != null){
					String rosterStartTime = roster.getStartTime();
					String rosterEndTime = roster.getEndTime();
					String appointmentStartTime = rosterStartTime;
					Boolean slotFound = Boolean.FALSE;
					
					if ( bookedAppointments != null ) {
						while ( appointmentStartTime.compareTo( rosterEndTime ) > 0  && slotFound.equals( Boolean.FALSE ) ) {
							if ( bookedAppointments.containsKey( appointmentStartTime ) ) {
								appointmentStartTime = this.getNewAppointmentTime( appointmentStartTime, appointmentSlotInterval );
							} else {
								slotFound = Boolean.TRUE;
							}
						}
					} else {
						slotFound = Boolean.TRUE;
						appointmentStartTime = newAppointmentBM.getAppointmentStartTime();
					}
					
					if ( slotFound.equals( Boolean.TRUE ) ) {
						newAppointmentBM.setAppointmentStartTime( appointmentStartTime );
						String appointmentEndTime = this.getNewAppointmentTime( appointmentStartTime, appointmentSlotInterval );
						newAppointmentBM.setAppointmentEndTime(appointmentEndTime);
						
						addedAppointmentBM = this.addAppointment( newAppointmentBM );
					} else {
						StringBuilder stringBuilder = new StringBuilder( " The slot for roster with ROSTER_CODE = " );
						stringBuilder.append( newAppointmentBM.getRosterCode()  )
						             .append( " is not available " );
						
						throw new Exception( stringBuilder.toString() );
					}
				}else{
					addedAppointmentBM = this.addAppointment( newAppointmentBM );
				}

			} else {
				if ( bookedAppointments != null && bookedAppointments.containsKey( newAppointmentBM.getAppointmentStartTime() ) ) {
					StringBuilder stringBuilder = new StringBuilder( " The slot for roster with ROSTER_CODE = " );
					stringBuilder.append( newAppointmentBM.getRosterCode()  )
					             .append( " starting at time = " )
					             .append( newAppointmentBM.getAppointmentStartTime() )
					             .append( " is already booked. " );
					
					throw new Exception( stringBuilder.toString() );
				}
				
				addedAppointmentBM = this.addAppointment( newAppointmentBM );
			}
			
			
			// Cancel appointment service and create a new service.
			cancelAppointmentService(oldAppointmentBM);
			addedAppointmentBM.setModifiedBy( newAppointmentBM.getModifiedBy());
			
			// Assign appointment service to patient and render it.
			// As rescheduling happens only for the same doctor, no need to create a receivable.
			// But if any reschedules charges are there, we need to create a receivable for that.
			assignAppointmentService(addedAppointmentBM);
			
			return addedAppointmentBM;

			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.RESCHEDULE_APPOINTMENTS_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	private String getNewAppointmentTime( String existingStartTime, String slotInterval ) {
		Integer startTime = Integer.parseInt( existingStartTime );
		startTime += Integer.parseInt( slotInterval );
		
		int beginIndex = startTime.toString().length() - 2 ;
		int endIndex = startTime.toString().length() - 1 ;
		
		if ( startTime.toString().substring(beginIndex, endIndex ).compareTo("60") > 0 ) {
			startTime -= 60;
			startTime += 100;
		}
		
		String resultStartTime = startTime.toString();
		
		if ( resultStartTime.length() < 4 ) {
			int appendZeroCount = 4 - resultStartTime.length();
			
			for ( int i = 0; i < appendZeroCount ; i++ ) {
				resultStartTime = "0" + resultStartTime;
			}
		}
		
		return resultStartTime;
	}

	public List<AppointmentBM> getAppointmentsForARoster( Integer rosterId )
			throws HCISException {
		List<Appointments> bookedAppointments = appointmentsDAO.getBookedAppointments( rosterId );
		
		if ( bookedAppointments != null && !bookedAppointments.isEmpty() ) {
			List<AppointmentBM> appointmentBMList = new ArrayList<AppointmentBM>( 0 );
			
			Collection<Appointments> appointmentsCollection = bookedAppointments;
			
			for ( Appointments appointments : appointmentsCollection ) {
				appointmentBMList.add( converter.convertAppointmentsDM2BM( appointments ) );
			}
			
			return appointmentBMList;
		} else {
			return null;
		}
	}

	public AppointmentBM getAppointment(Integer appointmentNumber) throws HCISException {
		try{
		
		Appointments appointments = dataModelManager.getAppointment(appointmentNumber);

		List<AppointmentHistory>appointmentHistoryList = appointmentHistoryDAO.getAppointmentHistory(appointmentNumber);
		
		AppointmentBM appointmentBM = converter.convertAppointmentsDM2BM( appointments );
		
		if ( appointmentHistoryList != null && !appointmentHistoryList.isEmpty() ) {
			for ( AppointmentHistory history : appointmentHistoryList ) {
				if ( history.getAppointmentStatus().getStatusCode().equals( AppointmentConstants.PATIENT_CAME_TO_VISIT ) ) {
					appointmentBM.setPatientCameToVisit( Boolean.TRUE );
				} else if ( history.getAppointmentStatus().getStatusCode().equals( AppointmentConstants.PATIENT_VISITED ) ) {
					appointmentBM.setPatientVisitedConsultant( Boolean.TRUE );
				} else if ( history.getAppointmentStatus().getStatusCode().equals( AppointmentConstants.DOCTOR_CAPTURED ) ) {
					appointmentBM.setConsultantHasSeenPatient( Boolean.TRUE );
				} 
			}
		}
		
		return appointmentBM;
		}catch(Exception exception){
			Fault fault = new Fault(ApplicationErrors.ERROR_RUNNING_REPORT);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault
					.getFaultType());
		}
	}

	public String generatePatientConsultationReceiptSlip( Integer appointmentNumber )throws HCISException{
		try {
			AppointmentBM appointmentBM = this.getAppointment(appointmentNumber);
			
			PatientLiteBM patientLiteBM = patientManager.getPatientLiteBM( appointmentBM.getPatientId() );
			
			Integer businessPartnerId = eagleIntegration.getBusinessPartnerId(appointmentBM.getPatientId(), RegistrationConstants.REFERENCE_TYPE_PAT);
			Double appointmentCharge = doctorManager.getConsultationCharge(Integer.valueOf(appointmentBM.getPrimaryDoctor().getCode()));
			appointmentBM.setAppointmentCharge(appointmentCharge);
			
			Integer recordID =  
				eagleIntegration.createPayment(businessPartnerId, appointmentCharge, "", null);
				
			Map<String, Object> reportParamMap = new HashMap<String, Object>();
			
			reportParamMap.put("patientId", appointmentBM.getPatientId()== null?"":appointmentBM.getPatientId().toString());
			reportParamMap.put("doctorName", appointmentBM.getPrimaryDoctor()== null? "" :appointmentBM.getPrimaryDoctor().getDescription());
			reportParamMap.put("patientName", patientLiteBM == null ?"": patientLiteBM.getFullName());
			reportParamMap.put("serialNbr", "1");
			
			
			if( recordID != null ){
				reportParamMap.put("receiptDate", appointmentBM.getAppointmentDate().toString());
				reportParamMap.put("amountNumeric", appointmentBM.getAppointmentCharge().toString());

				String amountWordFormat = ConvertNumberToWordFormat.convert((long)appointmentCharge.doubleValue());
				reportParamMap.put("amountInWords", amountWordFormat );
			}
			
			ReportDetail reportDetail = new ReportDetail();
			reportDetail.setName(ReportsConfig.getString(ReportsDetail.PATIENT_CONSULTATION_FEE_REPORT_NAME));
			reportDetail.setFileName(ReportsConfig.getString(ReportsDetail.PATIENT_CONSULTATION_FEE_REPORT_FILENAME));
			reportDetail.setReportsDirPath(ReportsConfig.getString(ReportsDetail.REPORTS_DIR_PATH));
			String retStr = reportManager.generateReport(reportDetail, reportParamMap, null);
			
			return retStr;
		} catch ( Exception exception ) {
			
			Fault fault = new Fault(ApplicationErrors.ERROR_RUNNING_REPORT);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault
					.getFaultType());
		}
	}
	
	public void UpdateAppointmentStatus( Integer appointmentNumber, String newStatus)throws HCISException{
		try {
			Validate.notNull( appointmentNumber, "Appointment number should not be null");
			Validate.notNull( newStatus, "status should not be null");
			
			AppointmentStatus appointmentStatus = dataModelManager.getAppointmentStatus( newStatus );
			
			Appointments appointments = appointmentsDAO.findById( appointmentNumber );
			
			if( null != appointments && appointmentStatus!= null ){
				
				appointments.setAppointmentStatus( appointmentStatus );
				
				if( newStatus == AppointmentConstants.DOCTOR_CAPTURED){
					appointments.setCapturedDtm(new Date());
				}
				appointmentsDAO.attachDirty( appointments );
				
				AppointmentHistory appointmentHistory = this.getAppointmentsHistory( 
																appointments.getAppointmentNumber(), 
																appointmentStatus.getStatusCode() );
				
				if ( appointmentHistory == null ) {
				this.createAppointmentHistory( appointments );
				}
			}
			
		} catch (Exception exception) {
			Fault fault = new Fault(ApplicationErrors.UPDATE_APPOINTMENT_STATUS_FAILED);
			throw new HCISException( fault, exception);
		}
	}
	
	
	
	/**
	 * @param appointmentsDAO the appointmentsDAO to set
	 */
	public void setAppointmentsDAO(AppointmentsDAOExtn appointmentsDAO) {
		this.appointmentsDAO = appointmentsDAO;
	}

	/**
	 * @param converter the converter to set
	 */
	public void setConverter(DataModelConverter converter) {
		this.converter = converter;
	}

	/**
	 * @param dataModelManager the dataModelManager to set
	 */
	public void setDataModelManager(DataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	/**
	 * @param appointmentHistoryDAO the appointmentHistoryDAO to set
	 */
	public void setAppointmentHistoryDAO(AppointmentHistoryDAOExtn appointmentHistoryDAO) {
		this.appointmentHistoryDAO = appointmentHistoryDAO;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(ResourceBundle config) {
		Config = config;
	}

	public void setDoctorManager(DoctorManager doctorManager) {
		this.doctorManager = doctorManager;
	}

	public void setPatientManager(PatientManager patientManager) {
		this.patientManager = patientManager;
	}

	public void setReportManager(ReportManager reportManager) {
		this.reportManager = reportManager;
	}

	public void setRosterManager(RosterManager rosterManager) {
		this.rosterManager = rosterManager;
	}

	public void setConfigurationManager(ConfigurationManager configurationManager) {
		this.configurationManager = configurationManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
	
	private Service assignAppointmentService(AppointmentBM appointmentBM){
		Service service = null;
		try{
			service = dataModelManager.getServiceByCode(RegistrationConstants.APPOINTMENT_SVC_CD);
			if(null != service){

				AssignedServiceBM assignedServiceBM = new AssignedServiceBM();
				assignedServiceBM.setService(new CodeAndDescription(service.getServiceCode(), service.getServiceName()));
				assignedServiceBM.setServiceDate(new Date());
				assignedServiceBM.setRequestedUnits(Integer.valueOf("1"));
				assignedServiceBM.setServiceCharge(appointmentBM.getAppointmentCharge()/*service.getServiceCharge()*/);
				assignedServiceBM.setCreateDtm(new Date());
				assignedServiceBM.setCreatedBy(appointmentBM.getModifiedBy());
				
				
				List<AssignedServiceBM> assignedServiceBMList = new ArrayList<AssignedServiceBM>();
				assignedServiceBMList.add(assignedServiceBM);

				AssignSvcAndPackageBM assignSvcAndPackageBM = new AssignSvcAndPackageBM();
				assignSvcAndPackageBM.setPatientId(appointmentBM.getPatientId());
				assignSvcAndPackageBM.setReferenceNumber(appointmentBM.getAppointmentNumber().toString());
				assignSvcAndPackageBM.setReferenceType(RegistrationConstants.REFERENCE_TYPE_OPD);
				assignSvcAndPackageBM.setCreatedBy(appointmentBM.getModifiedBy());
				assignSvcAndPackageBM.setAssignedServiceBMList(assignedServiceBMList);
				
				serviceManager.assignSvcAndPackages(assignSvcAndPackageBM);
				
				List<AssignedServiceBM> svcList = serviceManager.getAssignedServicesList(
						String.valueOf(appointmentBM.getAppointmentNumber()), RegistrationConstants.REFERENCE_TYPE_OPD);
				Iterator<AssignedServiceBM> iter = svcList.iterator();
				while(iter.hasNext()){
					AssignedServiceBM tmpBM = (AssignedServiceBM) iter.next();
					if(tmpBM.getService().getCode().equals(RegistrationConstants.APPOINTMENT_SVC_CD)){
						serviceManager.modifyAssignedServiceToRendered(tmpBM.getServiceUid(), new Integer(1), appointmentBM.getModifiedBy());
					}
				}
			}
			
			return service;
			
		} catch (Exception exception) {
			Fault fault = new Fault(in.wtc.hcis.bo.constants.ApplicationErrors.SERVICE_ASSIGNMENT_FAILED);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault.getFaultType());
		}
	}
	
	private void cancelAppointmentService(AppointmentBM appointmentBM){
		List<AssignedServiceBM> assignedServiceBMList = serviceManager.getAssignedServicesList(RegistrationConstants.APPOINTMENT_SVC_CD +"-"+appointmentBM.getAppointmentNumber(), RegistrationConstants.REFERENCE_TYPE_OPD);
		if(null != assignedServiceBMList){
			int size = assignedServiceBMList.size();
			Integer[] svcUIDArray = new Integer[size];
			for(int i=0; i<size; i++){
				svcUIDArray[i] = assignedServiceBMList.get(i).getServiceUid(); 
			}
			serviceManager.cancelAssignedServices(svcUIDArray, appointmentBM.getModifiedBy());
			
			// If patient has paid the consultation fee, "Refund the appointment consultation fee; Apply appointment cancellation charge"
			// If patient did not pay the consultation fee, Apply appointment cancellation charge		
		}
	}
	
	
	/**
	 *  This method returns the Appointment numbers corresponding to the
	 *  patient and doctor that have follow ups for that appointment.
	 *  Appointment number is eligible for the followups we need to check the following conditions
	 *  we need to get the Followup days and Visits for given docotr id
	 *  Then we need to collect the all the appointment numbers and appointment date for the docotorid an patientid
	 *  that having status as CAPTURED or FOLLOWUP
	 *  By using this appointment numbers we need to count how many primary appointments match .
	 *  By using the appointment date and current we need to get how many days before that appointment happen
	 *  Now we need to check 3 conditions based on doctor information 
	 *  1. Doctor having only followup days
	 *  2. Doctor having only followup visits
	 *  3.Doctor having both  followup days and followup visits
	 *  In Case one we need to check the Appointment taken days less than followupday
	 *  In case two we need to compare the primary appointment match count with Docotr visits. 
	 *  In this case Visits must be more than the Primary appointments
	 *  In case three we need to check both case one and case two conditions 
	 *  
	 *  
	 *  Create one arraylist of CodeAndDescripion (appointmentNumbner,appointmnetNumner).
	 *  add the appointments which are eligible for followUP to this list.
	 *  Convert this list to listRange by Calling WTCUtil.convertListToListRange method and return.
	 *  In this getting of appointment numbers we also check if the doctor not specified any followups 
	 *  we consider the hospital level followups.
	 *     
	 */
	
	public ListRange getAppointmentForFollowUps(Integer patientId, Integer doctorId,int start, int count,String orderBy) {
		
		try{

		DoctorBM doctorFollowupObj = doctorManager.getDoctorDetail(doctorId);
		List<Appointments> appointmentNumbers = appointmentsDAO.getFollowUpEligibleAppointments(patientId,doctorId); 
		List<CodeAndDescription> appointmentNo =  new ArrayList();
		final Integer hospitalFollowUpDays = Integer.valueOf(configurationManager.getSystemConfiguration(ConfigurationConstants.FOLLOWUP_DAY));
		final Integer hospitalFollowUpVisits = Integer.valueOf(configurationManager.getSystemConfiguration(ConfigurationConstants.FOLLOWUP_VISIT));
		
		for(int i=0;i<appointmentNumbers.size();i++){
			int Totalcount = appointmentsDAO.getFollowUpCountsOfAppointment(appointmentNumbers.get(i).getAppointmentNumber());
			Date OldDate = appointmentNumbers.get(i).getAppointmentDate();
			Date TodaysDate = new Date();
			long mills_per_day = 1000 * 60 * 60 * 24;
			long noOfDays = ( TodaysDate.getTime() - OldDate.getTime() ) / mills_per_day;
			
			if(doctorFollowupObj.getDoctorEspecialtyList().get(0).getFollowupDay()!=null && doctorFollowupObj.getDoctorEspecialtyList().get(0).getFollowupVisit()==null){
				if(doctorFollowupObj.getDoctorEspecialtyList().get(0).getFollowupDay()>=noOfDays){
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(String.valueOf(appointmentNumbers.get(i).getAppointmentNumber()));
					codeAndDescription.setDescription(String.valueOf(appointmentNumbers.get(i).getAppointmentNumber()));
					appointmentNo.add(codeAndDescription);
				}
				
			}else if(doctorFollowupObj.getDoctorEspecialtyList().get(0).getFollowupDay()==null && doctorFollowupObj.getDoctorEspecialtyList().get(0).getFollowupVisit()!=null){
				if(doctorFollowupObj.getDoctorEspecialtyList().get(0).getFollowupVisit()>Totalcount){
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(String.valueOf(appointmentNumbers.get(i).getAppointmentNumber()));
					codeAndDescription.setDescription(String.valueOf(appointmentNumbers.get(i).getAppointmentNumber()));
					appointmentNo.add(codeAndDescription);
				}
				
			}else if(doctorFollowupObj.getDoctorEspecialtyList().get(0).getFollowupDay()!=null && doctorFollowupObj.getDoctorEspecialtyList().get(0).getFollowupVisit()!=null){
				if(doctorFollowupObj.getDoctorEspecialtyList().get(0).getFollowupDay()>=noOfDays && doctorFollowupObj.getDoctorEspecialtyList().get(0).getFollowupVisit()>Totalcount){
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(String.valueOf(appointmentNumbers.get(i).getAppointmentNumber()));
					codeAndDescription.setDescription(String.valueOf(appointmentNumbers.get(i).getAppointmentNumber()));
					appointmentNo.add(codeAndDescription);
				}
				
			}else if(hospitalFollowUpDays!=0 && hospitalFollowUpVisits==0){
				if(hospitalFollowUpDays>=noOfDays){
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(String.valueOf(appointmentNumbers.get(i).getAppointmentNumber()));
					codeAndDescription.setDescription(String.valueOf(appointmentNumbers.get(i).getAppointmentNumber()));
					appointmentNo.add(codeAndDescription);
				}
				
			}else if(hospitalFollowUpDays==0 && hospitalFollowUpVisits!=0){
				if(hospitalFollowUpVisits>Totalcount){
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(String.valueOf(appointmentNumbers.get(i).getAppointmentNumber()));
					codeAndDescription.setDescription(String.valueOf(appointmentNumbers.get(i).getAppointmentNumber()));
					appointmentNo.add(codeAndDescription);
				}
				
			}else if(hospitalFollowUpDays!=0 && hospitalFollowUpVisits!=0){
				if(hospitalFollowUpDays>=noOfDays && hospitalFollowUpVisits>Totalcount){
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(String.valueOf(appointmentNumbers.get(i).getAppointmentNumber()));
					codeAndDescription.setDescription(String.valueOf(appointmentNumbers.get(i).getAppointmentNumber()));
					appointmentNo.add(codeAndDescription);
				}
				
			}
			 
		}
		
		 ListRange appointments = WtcUtils.convertListToListRange(appointmentNo);
		
		return appointments;
	}catch(Exception exception){
		Fault fault = new Fault(ApplicationErrors.ERROR_RUNNING_REPORT);

		throw new HCISException(fault.getFaultMessage()
				+ exception.getMessage(), fault.getFaultCode(), fault
				.getFaultType());
	}
	
	}
	
	
	/**
	 *  This method returns the Number of Followupdays or Number of followup visits  
	 *  for the given doctor id, patient id, appointment number and appointment date
	 */
	
public Integer getNumberOfFollowUps(Integer patientId, Integer doctorId,Integer appointmentNo,Date appointmentDate) {
		
		try{

		DoctorBM doctorFollowupObj = doctorManager.getDoctorDetail(doctorId);
		
		
			int totalFollowUpTaken = appointmentsDAO.getFollowUpCountsOfAppointment(appointmentNo);

			Date todaysDate = new Date();
			final Long mills_per_day = 1000 * 60 * 60 * 24l;
			Integer noOfDays = Long.valueOf(( todaysDate.getTime() - appointmentDate.getTime() ) / mills_per_day).intValue();
			int availableFollowups = 0;
			
			final Integer doctorFollowUpDay = doctorFollowupObj.getDoctorEspecialtyList().get(0).getFollowupDay();
			final Integer doctoFollowUpVisits =  doctorFollowupObj.getDoctorEspecialtyList().get(0).getFollowupVisit();
			final Integer hospitalFollowUpDays = Integer.valueOf(configurationManager.getSystemConfiguration(ConfigurationConstants.FOLLOWUP_DAY));
			final Integer hospitalFollowUpVisits = Integer.valueOf(configurationManager.getSystemConfiguration(ConfigurationConstants.FOLLOWUP_VISIT));
			
			if(doctorFollowUpDay!=null && doctoFollowUpVisits==null){
				if(doctorFollowUpDay>=noOfDays){
					availableFollowups = doctorFollowUpDay - noOfDays;
				}
				
			}else if(doctorFollowUpDay==null && doctoFollowUpVisits!=null){
				if(doctoFollowUpVisits>totalFollowUpTaken){
					availableFollowups = doctoFollowUpVisits - totalFollowUpTaken;
				}
				
			}else if(doctorFollowUpDay!=null && doctoFollowUpVisits!=null){
				if(doctorFollowUpDay>=noOfDays && doctoFollowUpVisits>totalFollowUpTaken){
					availableFollowups = doctoFollowUpVisits - totalFollowUpTaken;
				}
				
			}else if(hospitalFollowUpDays!=0 && hospitalFollowUpVisits==0){
				if(hospitalFollowUpDays>=noOfDays){
					availableFollowups = hospitalFollowUpDays - noOfDays;
				}
				
			}else if(hospitalFollowUpDays==0 && hospitalFollowUpVisits!=0){
				if(hospitalFollowUpVisits>totalFollowUpTaken){
					availableFollowups = hospitalFollowUpVisits - totalFollowUpTaken;
				}				
			}else if(hospitalFollowUpDays!=0 && hospitalFollowUpVisits!=0){
				if(hospitalFollowUpDays>=noOfDays && hospitalFollowUpVisits>totalFollowUpTaken){
					availableFollowups = hospitalFollowUpVisits - totalFollowUpTaken;
				}				
			}
			
			 
		return availableFollowups;
		
	}catch(Exception exception){
		Fault fault = new Fault(ApplicationErrors.ERROR_RUNNING_REPORT);

		throw new HCISException(fault.getFaultMessage()
				+ exception.getMessage(), fault.getFaultCode(), fault
				.getFaultType());
	}
	
	}

}
