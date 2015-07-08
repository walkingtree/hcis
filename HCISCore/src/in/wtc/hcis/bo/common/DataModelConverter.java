/**
 * 
 */
package in.wtc.hcis.bo.common;

import in.wtc.hcis.bm.base.AppointmentBM;
import in.wtc.hcis.bm.base.CheckListBM;
import in.wtc.hcis.bm.base.CheckListDetailBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.PatientVitalBM;
import in.wtc.hcis.bm.base.RoomBM;
import in.wtc.hcis.bm.base.RosterBM;
import in.wtc.hcis.bm.base.RosterModel;
import in.wtc.hcis.bm.base.RosterTimeBM;
import in.wtc.hcis.bm.base.VitalFieldBM;
import in.wtc.hcis.bo.appointment.AppointmentConstants;
import in.wtc.hcis.bo.configuration.ConfigurationManager;
import in.wtc.hcis.bo.constants.ApplicationConstants;
import in.wtc.hcis.bo.constants.ApplicationEntities;
import in.wtc.hcis.bo.constants.ConfigurationConstants;
import in.wtc.hcis.bo.constants.ReferenceDataConstants;
import in.wtc.hcis.bo.constants.RosterConstants;
import in.wtc.hcis.exceptions.HCISException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.wtc.hcis.da.AppointmentStatus;
import com.wtc.hcis.da.Appointments;
import com.wtc.hcis.da.BookingType;

import com.wtc.hcis.da.DocCheckList;
import com.wtc.hcis.da.DocCheckListDetail;

import com.wtc.hcis.da.DocPatientVital;
import com.wtc.hcis.da.DocVitalField;

import com.wtc.hcis.da.DocCheckListInstance;
import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.Especialty;
import com.wtc.hcis.da.Patient;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.Referral;
import com.wtc.hcis.da.ReferralDAO;
import com.wtc.hcis.da.Room;
import com.wtc.hcis.da.Roster;

/**
 * @author Alok Ranjan
 *
 */
public class DataModelConverter {
	
	private DataModelManager dataModelManager;
	private ConfigurationManager configurationManager;
	private CommonDataManager commonDataManager;
	
	
	public void setReferralDAO(ReferralDAO referralDAO) {
	}
	
	
	/**
	 * If endTime is less than startTime then this method  understand that the endTime is the the next date Time.
	 * And this method will create two roster between this startTime and endTime.   
	 * One is for the current Date 
	 */
	
	
	public 	List<Roster> convertRosterBM2DM( RosterBM rosterBM, Roster existingRoster ) {
		List<Roster> rosterList = new ArrayList<Roster>();
		
		if( rosterBM.getRosterTimeBMList() != null && !rosterBM.getRosterTimeBMList().isEmpty() ) {
			Integer count = 0;
			
			for( Date tmpDate = rosterBM.getFromDate(); tmpDate.compareTo( rosterBM.getToDate() ) <= 0;) {
				Calendar workingDateCalender = Calendar.getInstance();
				workingDateCalender.setTime(tmpDate);
				
				boolean isRosterEligible = true;
				
				for( RosterTimeBM tmpRosterTimeBM : rosterBM.getRosterTimeBMList() ) {
					Roster roster = null;
										
					roster = existingRoster;
					if ( roster == null ) {
						roster = new Roster();
					}
					
					roster.setEntityType(rosterBM.getEntityType());
					roster.setEntityId(rosterBM.getEntityId());
					roster.setStartTime( tmpRosterTimeBM.getStartTime() );
					roster.setEndTime( tmpRosterTimeBM.getEndTime() );
					if( tmpRosterTimeBM.getRoomBM() != null ) {
						Room room = new Room();
						room.setRoomCode( tmpRosterTimeBM.getRoomBM().getRoomCode() );
						room.setDescription( tmpRosterTimeBM.getRoomBM().getDescription() );
						roster.setRoom( room );
					}
					if( tmpRosterTimeBM.getActive() != null ) {
						roster.setActive( tmpRosterTimeBM.getActive() ? "Y" : "N" );
					}
					Date workingDate = new Date(workingDateCalender.getTimeInMillis());
					if( rosterBM.getPeriod() != null ) {
						if( rosterBM.getPeriod().equalsIgnoreCase( RosterConstants.DAILY ) ) {
//							Calendar tempWorkingDateCalender = Calendar.getInstance();
//							tempWorkingDateCalender.setTime(tmpRosterTimeBM.getWorkingDate());
//							tempWorkingDateCalender.add(Calendar.DATE, 1);
//							roster.setWorkingDate(tempWorkingDateCalender.getTime());
							roster.setWorkingDate( workingDate );
						}else if(rosterBM.getPeriod().equalsIgnoreCase( RosterConstants.WEEKLY ) ) {
//							Calendar tempWorkingDateCalender = Calendar.getInstance();
//							tempWorkingDateCalender.setTime(tmpRosterTimeBM.getWorkingDate());
//							tempWorkingDateCalender.add(Calendar.DATE, (7*count));
//							Date workingDate = new Date(workingDateCalender.getTimeInMillis());
							roster.setWorkingDate( workingDate );
							if( !rosterBM.getDaysList().contains(new Integer( roster.getWorkingDate().getDay()))){
								isRosterEligible = false;
							}
						}else if( rosterBM.getPeriod().equalsIgnoreCase( RosterConstants.MONTHLY ) ) {
//							Calendar tempWorkingDateCalender = Calendar.getInstance();
//							tempWorkingDateCalender.setTime(tmpRosterTimeBM.getWorkingDate());
//							tempWorkingDateCalender.add(Calendar.MONTH, count);
							roster.setWorkingDate( workingDate );
							if( !rosterBM.getDaysList().contains(new Integer(workingDateCalender.get(Calendar.DAY_OF_MONTH)))){
								isRosterEligible = false;
							}
							
						}
					}
					if( isRosterEligible ){	
						rosterList.add( roster );
					}	
				}
				count++;
				if( rosterBM.getPeriod() != null ) {
					workingDateCalender.add(Calendar.DATE, 1);
				}
				tmpDate.setTime(workingDateCalender.getTimeInMillis());
				if( existingRoster != null ) {
					return rosterList;
				}
			}
		}
				
		return rosterList;
	}

	
	
	
	/**
	 *  Here the RosterBM will have only one entry in the RosterTimeBM 
	 * @param roster
	 * @return RosterBM - This has the list of RosterTimeBM inside it
	 */
	public 	RosterBM convertRosterDM2BM( Roster roster ) 
	{
		RosterBM rosterBM = new RosterBM();
	
		String entityType = roster.getEntityType();
		if ( entityType != null && entityType.length() > 0 && roster.getEntityId() != null ) {
			String entityFirstName = null;
			String entityLastName = null;
			String entityMiddleName = null;
			
			if ( entityType.equals( ApplicationEntities.DOCTOR ) ) {
				Doctor doctor = dataModelManager.getDoctor( roster.getEntityId() );
				entityFirstName = doctor.getFirstName();
				entityLastName = doctor.getLastName();
				entityMiddleName = doctor.getMiddleName();
			}
			
			rosterBM.setEntityFirstName(entityFirstName);
			rosterBM.setEntityLastName(entityLastName);
			rosterBM.setEntityMiddleName(entityMiddleName);
		}
		
		rosterBM.setEntityId( roster.getEntityId() );
		rosterBM.setEntityType( entityType );
		List<RosterTimeBM> rosterTimeBMList = new ArrayList<RosterTimeBM>();
		RosterTimeBM rosterTimeBM = new RosterTimeBM();
		rosterTimeBM.setActive( new Boolean(roster.getActive().toString()));
		rosterTimeBM.setEndTime( roster.getEndTime() );
		rosterTimeBM.setRosterCode( roster.getRosterCode() );
		rosterTimeBM.setStartTime( roster.getStartTime() );
		rosterTimeBM.setWorkingDate( roster.getWorkingDate() );
		rosterTimeBMList.add( rosterTimeBM );
		rosterBM.setRosterTimeBMList( rosterTimeBMList );
		return rosterBM;
	}
	
	public RosterModel convertRosterModelDM2BM( Roster rosterDM ) {
		RosterModel rosterModel = new RosterModel();
		rosterModel.setEntityId( rosterDM.getEntityId() );
		rosterModel.setEntityType( rosterDM.getEntityType() );
		rosterModel.setEndTime( rosterDM.getEndTime() );
		
		if( rosterDM.getRoom() != null && !rosterDM.getRoom().equals("") ) {
			RoomBM roomBM = new RoomBM();
			roomBM.setRoomCode( rosterDM.getRoom().getRoomCode() );
			roomBM.setDescription( rosterDM.getRoom().getDescription() );
			rosterModel.setRoomBM( roomBM );
		}
		
		if ( rosterDM.getEntityType().equals( ApplicationEntities.DOCTOR ) ) {
			Doctor doctor = dataModelManager.getDoctor( rosterDM.getEntityId() );
			if( doctor != null ) {
			rosterModel.setEntityName( doctor.getFirstName() + " " + doctor.getMiddleName() + " " + doctor.getLastName() );
			}
		}
		
		rosterModel.setRosterCode( rosterDM.getRosterCode() );
		rosterModel.setStartTime( rosterDM.getStartTime() );
		rosterModel.setWorkingDate( rosterDM.getWorkingDate() );
		
		if( rosterDM.getActive().toString().equals(ApplicationConstants.ACTIVE_FLAG_NO) ) {
			rosterModel.setActive( new Boolean( false ) );
			rosterModel.setIsDoctorActive(ApplicationConstants.NO);
		}else {
			rosterModel.setActive( new Boolean( true ) );
			rosterModel.setIsDoctorActive(ApplicationConstants.YES);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( rosterDM.getWorkingDate() );
		rosterModel.setWeekDay( getWeekDayName( calendar.get( Calendar.DAY_OF_WEEK ) ) );
		
		return rosterModel;
	}
	
	public Roster convertRosterModelBM2DM( RosterModel rosterModelBM ) 
	{
		Roster rosterDM = new Roster();
		rosterDM.setWorkingDate( rosterModelBM.getWorkingDate() );
		rosterDM.setEntityType( rosterModelBM.getEntityType() );
		rosterDM.setEntityId( rosterModelBM.getEntityId() );
		rosterDM.setStartTime( rosterModelBM.getStartTime() );
		rosterDM.setEndTime( rosterModelBM.getEndTime() );
		if( rosterModelBM.getRoomBM() != null ) {
			Room room = new Room();
			room.setRoomCode( rosterModelBM.getRoomBM().getRoomCode() );
			room.setDescription( rosterModelBM.getRoomBM().getDescription() );
			rosterDM.setRoom(room);
		}
		rosterDM.setActive( rosterModelBM.getActive() ? "Y" : "N" );
		
		return rosterDM;
	}
	
	public AppointmentBM convertAppointmentsDM2BM( Appointments appointmentsDM ) throws HCISException {
		AppointmentBM appointmentBM = new AppointmentBM();
		if ( appointmentsDM.getAppointmentNumber() != null ) {
				appointmentBM.setAppointmentNumber( appointmentsDM.getAppointmentNumber() );
		}
			
		Patient patient = appointmentsDM.getPatient();
			
		appointmentBM.setAppointmentDate( appointmentsDM.getAppointmentDate() );
		appointmentBM.setPatientId( appointmentsDM.getPatient().getPatientId() );
		appointmentBM.setFirstName( patient.getFirstName() );
		appointmentBM.setMiddleName( patient.getMiddleName() );
		appointmentBM.setLastName( patient.getLastName() );
		appointmentBM.setAppointmentStartTime( appointmentsDM.getApptStartTime() );
		appointmentBM.setAppointmentEndTime( appointmentsDM.getApptEndTime() );
		appointmentBM.setBookingDate( appointmentsDM.getCreateDtm() );
		appointmentBM.setPhone(appointmentsDM.getPhone());
		appointmentBM.setEmail(appointmentsDM.getEmail());
		appointmentBM.setAppointmentCharge(appointmentsDM.getConsultationCharge());
		
		if( appointmentsDM.getRoster() != null ){
			appointmentBM.setRosterCode( appointmentsDM.getRoster().getRosterCode() );
		}
		
		BookingType bookingType = appointmentsDM.getBookingType();
		
		appointmentBM.setBookingType( new CodeAndDescription( bookingType.getBookingTypeCode(), 
				                                              bookingType.getDescription() ) );
		
		appointmentBM.setAppointmentRemarks( appointmentsDM.getAppointmentRemarks() );
		
		AppointmentStatus appointmentStatus = appointmentsDM.getAppointmentStatus();
		appointmentBM.setAppointmentStatus( new CodeAndDescription( appointmentStatus.getStatusCode(), 
																	appointmentStatus.getDescription() ) );
		
		appointmentBM.setAppointmentAgenda( appointmentsDM.getAppointmentAgenda() );
		appointmentBM.setNextVisitDate( appointmentsDM.getNextVisitDt() );
		
		Doctor doctor = appointmentsDM.getDoctor();
		appointmentBM.setPrimaryDoctor( new CodeAndDescription( doctor.getDoctorId().toString(), doctor.getFirstName() +  " " +
																doctor.getMiddleName()+ " "  + doctor.getLastName() ) );
		
		appointmentBM.setModifiedBy( appointmentsDM.getModifiedBy() );
		
		Especialty especialty = appointmentsDM.getEspecialty();
		
//		appointmentBM.setCancellationReason( cancellationReason );  TODO: Where are we capturing cancellation reason
		appointmentBM.setSpeaciality( new CodeAndDescription( especialty.getEspecialtyCode(), especialty.getEspecialtyName())); 
		
		if( appointmentsDM.getAppointmentTypeCode() != null && 
				!appointmentsDM.getAppointmentTypeCode().isEmpty()){
			ReferenceDataList referenceDataList = commonDataManager.getReferenceData(AppointmentConstants.APPONITMENT_TYPE, 
																					 appointmentsDM.getAppointmentTypeCode());
			appointmentBM.setAppointmentType( new CodeAndDescription( referenceDataList.getId().getAttrCode(),
																	referenceDataList.getAttrDesc()));
		}
		
		if( appointmentsDM.getReferral() != null ){
			Referral referral = appointmentsDM.getReferral();
			StringBuilder referralDesc = new StringBuilder( referral.getReferralName() );
	    	referralDesc.append(" ( ");
	    	referralDesc.append( referral.getAddress() );
	    	referralDesc.append(", ");
	    	referralDesc.append( referral.getCity() );
	    	referralDesc.append( " ) " );
			appointmentBM.setReferredBy( new CodeAndDescription( 
										referral.getReferralCode().toString(), referralDesc.toString()));
			
			appointmentBM.setReferralType( new CodeAndDescription( referral.getReferralTypeCode(),null ));
		}
		return appointmentBM;
	}
	
	public Appointments convertAppointmentsBM2DM( AppointmentBM appointmentBM, Appointments existingAppointment ) {
		Appointments appointmentsDM = null;
		
		if ( existingAppointment != null ) {
			appointmentsDM = existingAppointment;
		} else {
			appointmentsDM = new Appointments();
		}
		
		if( null != appointmentBM.getReferredBy() && null != appointmentBM.getReferredBy().getCode()
				&& !appointmentBM.getReferredBy().getCode().isEmpty()){
			Referral referral = commonDataManager.getReferral(
					new Integer(appointmentBM.getReferredBy().getCode()));
			appointmentsDM.setReferral(referral);
		}
		appointmentsDM.setAppointmentDate( appointmentBM.getAppointmentDate() );
		appointmentsDM.setApptStartTime( appointmentBM.getAppointmentStartTime() );
		appointmentsDM.setApptEndTime( appointmentBM.getAppointmentEndTime() );
		appointmentsDM.setAppointmentAgenda( appointmentBM.getAppointmentAgenda() );
		appointmentsDM.setAppointmentRemarks( appointmentBM.getAppointmentRemarks() );
		if(appointmentBM.getPhone() != null){
			appointmentsDM.setPhone(appointmentBM.getPhone());
		}
		
		if(appointmentBM.getEmail() != null){
			appointmentsDM.setEmail(appointmentBM.getEmail());
		}
		
		if ( appointmentBM.getAppointmentStatus() != null ) {
			AppointmentStatus appointmentStatus = dataModelManager.getAppointmentStatus( appointmentBM.getAppointmentStatus().getCode() ); 
			appointmentsDM.setAppointmentStatus( appointmentStatus );
		}
		
		if( appointmentBM.getBookingType() != null && 
				!appointmentBM.getBookingType().getCode().isEmpty()) {
			BookingType bookingType = dataModelManager.getBookingType( appointmentBM.getBookingType().getCode() );
			appointmentsDM.setBookingType( bookingType );
		}
		
		if( null != appointmentBM.getAppointmentType() &&
				!appointmentBM.getAppointmentType().getCode().isEmpty()){
			ReferenceDataList referenceDataList = dataModelManager.getReferenceData( 
															AppointmentConstants.APPONITMENT_TYPE, 
															appointmentBM.getAppointmentType().getCode() );
			if( null != referenceDataList ){
				appointmentsDM.setAppointmentTypeCode( appointmentBM.getAppointmentType().getCode() ); 
			}
		}
		
		if( appointmentBM.getSpeaciality() != null ) {
			Especialty especialty = commonDataManager.getEspeciality(appointmentBM.getSpeaciality().getCode());
			appointmentsDM.setEspecialty(especialty);
		}
		
		Doctor doctor = dataModelManager.getDoctor( Integer.parseInt( appointmentBM.getPrimaryDoctor().getCode() ) );
		appointmentsDM.setDoctor( doctor );
		appointmentsDM.setNextVisitDt( appointmentBM.getNextVisitDate() );
		
		Patient patient = dataModelManager.getPatient( appointmentBM.getPatientId() );
		appointmentsDM.setPatient( patient);
		
		if(appointmentBM.getRosterCode() != null){
			String isRosterRequired = configurationManager.getSystemConfiguration(ConfigurationConstants.IS_ROSTER_REQUIRED_NAME);
			
			if(isRosterRequired.equals(ConfigurationConstants.IS_ROSTER_REQUIRED_VALUE)){
				Roster roster = dataModelManager.getRoster( appointmentBM.getRosterCode() );
				appointmentsDM.setRoster( roster );
			}
		}
		return appointmentsDM;
	}

	
	public DocCheckList convertCheckListBM2DM( CheckListBM checkListBM , DocCheckList existingCheckList){
		DocCheckList checkList = null;
		if( existingCheckList != null){
			checkList = existingCheckList;
		}
		else {
			checkList = new DocCheckList();
		}
		if( checkListBM.getCheckListType() != null){
			checkList.setCheckListType(checkListBM.getCheckListType().getCode());
		}
		checkList.setName(checkListBM.getName());
		checkList.setPrerequisite(checkListBM.getPrerequisite());
		
		return checkList;
	}
	
	public CheckListBM convertCheckListDM2BM( DocCheckList checkList){
		CheckListBM checkListBM = new CheckListBM();
		
		checkListBM.setCheckListId(checkList.getCheckListId());
		
		if(WtcUtils.isValid(checkList.getCheckListType())){
			
			ReferenceDataList  checkListType = commonDataManager.getReferenceData(ReferenceDataConstants.CONTEXT_CHECKLIST_TYPE, 
												checkList.getCheckListType());
			checkListBM.setCheckListType(new CodeAndDescription(checkListType.getId().getAttrCode(),checkListType.getAttrDesc()));
		}
		checkListBM.setName(checkList.getName());
		checkListBM.setUserId(checkList.getCreatedBy());
		checkListBM.setCreatedDtm(checkList.getCreatedDtm());
		checkListBM.setPrerequisite(checkList.getPrerequisite());
		
		return checkListBM;
		
	}
	
	public DocCheckListDetail convertCheckListDetailBM2DM( CheckListDetailBM checkListDetailBM ){
		DocCheckListDetail checkListDetail = new DocCheckListDetail();
		
		checkListDetail.setGroupName(checkListDetailBM.getGroup());
		checkListDetail.setQuestion(checkListDetailBM.getQuestion());
		if( WtcUtils.isValidCode(checkListDetailBM.getRole())){
			checkListDetail.setRoleCode(checkListDetailBM.getRole().getCode());
		}
		
		return checkListDetail;
	}
	
	public CheckListDetailBM convertCheckListDetailDM2BM( DocCheckListDetail checkListDetail){
		CheckListDetailBM checkListDetailBM = new CheckListDetailBM();
		
		checkListDetailBM.setCheckListDetailId(checkListDetail.getCheckListDetailId());
		checkListDetailBM.setGroup(checkListDetail.getGroupName());
		checkListDetailBM.setQuestion(checkListDetail.getQuestion());
		
		ReferenceDataList  roleForCheck = commonDataManager.getReferenceData(ReferenceDataConstants.CONTEXT_CHECKLIST_ROLE, 
										   checkListDetail.getRoleCode());
		
		checkListDetailBM.setRole(new CodeAndDescription(roleForCheck.getId().getAttrCode(),roleForCheck.getAttrDesc()));
		
		return checkListDetailBM;
	}

	
	
	public VitalFieldBM convertVitalFieldDM2BM( DocVitalField vitalField){
		VitalFieldBM vitalFieldBM = new VitalFieldBM();
		
		vitalFieldBM.setCode(vitalField.getCode());
		vitalFieldBM.setName(vitalField.getName());
		vitalFieldBM.setFieldGroup(vitalField.getFieldGroup());
		vitalFieldBM.setFieldType(vitalField.getFieldType());
		vitalFieldBM.setMeasurementUnitCd(vitalField.getMeasurementUnitCd());
		return vitalFieldBM;
	}
	
	public DocPatientVital convertPatientVitalBM2DM( PatientVitalBM patientVitalBM , DocPatientVital existingPatientVital){
		DocPatientVital patientVital;
		if( existingPatientVital != null){
			patientVital = existingPatientVital;
		}
		else{
			patientVital = new DocPatientVital();
		}
		
		patientVital.setReferenceNumber(patientVitalBM.getReferenceNumber());
		patientVital.setReferenceType(patientVitalBM.getReferenceType());
		
		return patientVital;
	}
	
	public DocCheckListInstance convertCheckListInstanceBM2DM( CheckListBM checkListBM, DocCheckListInstance existingCheckListInstance){
		
		DocCheckListInstance checkListInstance = null;
		
		if( existingCheckListInstance != null){
			checkListInstance = existingCheckListInstance;
		}
		else{
			checkListInstance = new DocCheckListInstance();
		}
		
		checkListInstance.setReferenceNumber(checkListBM.getReferenceNumber());
		checkListInstance.setReferenceType(checkListBM.getReferenceType());
		checkListInstance.setRemarks(checkListBM.getRemarks());
		if( checkListBM.getCheckListId() != null ){
			checkListInstance.setDocCheckList(dataModelManager.getCheckList(checkListBM.getCheckListId()));
		}
		
		return checkListInstance;
	}
	
	
	/**
	 * @param dataModelManager the dataModelManager to set
	 */
	public void setDataModelManager(DataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}
	
	public void setConfigurationManager(ConfigurationManager configurationManager) {
		this.configurationManager = configurationManager;
	}
	private String getWeekDayName (int weekDayNo) {
		String weekDayName=null;
		switch (weekDayNo) {
		case 1:
		weekDayName = "Sunday";
			break;
			
		case 2:
			weekDayName = "Monday";
				break;
		case 3:
			weekDayName = "Tuesday";
				break;
		case 4:
			weekDayName = "Wednesday";
				break;
		case 5:
			weekDayName = "Thursday";
				break;
		case 6:
			weekDayName = "Friday";
			break;
		case 7:
			weekDayName = "Saturday";
				break;
		default:
			break;
		}
		return weekDayName;
	}
	public void setCommonDataManager(CommonDataManager commonDataManager) {
		this.commonDataManager = commonDataManager;
	}
	
}
