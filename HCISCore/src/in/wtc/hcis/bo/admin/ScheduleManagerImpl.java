package in.wtc.hcis.bo.admin;


import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.PatientVaccinationScheduleBM;
import in.wtc.hcis.bm.base.PatientVaccinationScheduleDetailsBM;
import in.wtc.hcis.bm.base.VaccinationBM;
import in.wtc.hcis.bm.base.VaccinationScheduleBM;
import in.wtc.hcis.bm.base.VaccinationScheduleDetailBM;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.CommonDataManager;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.constants.ScheduleManagerConstants;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.Validate;

import com.wtc.hcis.da.Disease;
import com.wtc.hcis.da.DiseaseDAO;
import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.Patient;
import com.wtc.hcis.da.PatientVaccinationSchedule;
import com.wtc.hcis.da.PatientVaccinationScheduleDAO;
import com.wtc.hcis.da.PatientVaccinationScheduleDetails;
import com.wtc.hcis.da.PatientVaccinationScheduleDetailsDAO;
import com.wtc.hcis.da.PatientVaccinationScheduleDetailsId;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.Vaccination;
import com.wtc.hcis.da.VaccinationDAO;
import com.wtc.hcis.da.VaccinationSchedule;
import com.wtc.hcis.da.VaccinationScheduleDetails;
import com.wtc.hcis.da.VaccinationScheduleDetailsDAO;
import com.wtc.hcis.da.VaccinationScheduleDetailsId;
import com.wtc.hcis.da.extn.PatientVaccinationScheduleDetailsDAOExtn;
import com.wtc.hcis.da.extn.VaccinationScheduleDAOExtn;
import com.wtc.hcis.da.extn.VaccinationScheduleDetailsDAOExtn;

/**
 * @author Vinay Kurudi
 *
 */
public class ScheduleManagerImpl implements ScheduleManager {
	
	private VaccinationScheduleDAOExtn vaccinationScheduleDAO;
	private VaccinationScheduleDetailsDAOExtn vaccinationScheduleDetailsDAO;
	private PatientVaccinationScheduleDAO patientVaccinationScheduleDAO;
	private PatientVaccinationScheduleDetailsDAOExtn patientVaccinationScheduleDetailsDAO;
	private VaccinationDAO vaccinationDAO;
	private static String SCHEDULE_NAME = "id.scheduleName";
	private DataModelManager dataModelManager;
	private CommonDataManager commonDataManager;
	
	private static String SEQ_NBR ="seqNbr"; 
	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.schedule.ScheduleManager#addSchedule(in.wtc.hcis.bm.ip.ScheduleBM)
	 */
	public VaccinationScheduleBM addSchedule(VaccinationScheduleBM vaccinationScheduleBM ) throws HCISException {
		try {
			
			Validate.notNull(vaccinationScheduleBM, " vaccinationScheduleBM object should not be null");

			VaccinationSchedule vaccinationSchedule = convertVaccinationScheduleBM2DM( vaccinationScheduleBM, null );
			
			vaccinationScheduleDAO.save( vaccinationSchedule );
			
			if( vaccinationScheduleBM.getVaccinationScheduleDetailList() != null && 
					vaccinationScheduleBM.getVaccinationScheduleDetailList().size() > 0 ){
				List<VaccinationScheduleDetailBM> vaccinationScheduledetailsBMList = vaccinationScheduleBM.getVaccinationScheduleDetailList();
				
				Integer seqNumber = 1;
				
				for( VaccinationScheduleDetailBM vaccinationSchlDetailsBM : vaccinationScheduledetailsBMList ){
					
					VaccinationScheduleDetails vaccinationScheduleDetails = convertVaccinationScheduleDetailsBM2DM( vaccinationSchlDetailsBM, null );
					
					VaccinationScheduleDetailsId vaccinationScheduleDetailsId = new VaccinationScheduleDetailsId();
					vaccinationScheduleDetailsId.setScheduleName( vaccinationSchedule.getScheduleName());
					vaccinationScheduleDetailsId.setSeqNbr(seqNumber);
					
					vaccinationScheduleDetails.setId( vaccinationScheduleDetailsId );
					
					vaccinationScheduleDetailsDAO.save( vaccinationScheduleDetails );
					seqNumber++;
				}
			}
			
			return convertVaccinationScheduleDM2BM( vaccinationSchedule );
			
		} catch (Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.ADD_SCHEDULE_FAILED);
			throw new HCISException( fault, exception );
		}
	}
	
	private VaccinationSchedule convertVaccinationScheduleBM2DM( VaccinationScheduleBM vaccinationScheduleBM , VaccinationSchedule vaccinationSchedule){
		
		if( null == vaccinationSchedule ){
			vaccinationSchedule = new VaccinationSchedule();
		}
		
		if( null != vaccinationScheduleBM.getActiveFlag() ){
			vaccinationSchedule.setActiveFlag(  vaccinationScheduleBM.getActiveFlag()? 
											ScheduleManagerConstants.IS_ACTIVE_TRUE :
											ScheduleManagerConstants.IS_ACTIVE_FALSE);
		}
		vaccinationSchedule.setAgeGroup( vaccinationScheduleBM.getAgeGroup());
		vaccinationSchedule.setLastModifiedDt( Calendar.getInstance().getTime());
		vaccinationSchedule.setScheduleDesc( vaccinationScheduleBM.getDescription());
		vaccinationSchedule.setScheduleName( vaccinationScheduleBM.getScheduleName());
		vaccinationSchedule.setUserId( vaccinationScheduleBM.getUserName());
		
		return vaccinationSchedule;
	}
	

	private VaccinationScheduleDetails convertVaccinationScheduleDetailsBM2DM( VaccinationScheduleDetailBM vaccinationScheduleDetailBM,
													VaccinationScheduleDetails vaccinationScheduleDetails ){
		
		if( vaccinationScheduleDetails == null ){
			vaccinationScheduleDetails = new VaccinationScheduleDetails();
		}
		
		vaccinationScheduleDetails.setAge(vaccinationScheduleDetailBM.getAge());
		vaccinationScheduleDetails.setDosage( vaccinationScheduleDetailBM.getDosage());
		vaccinationScheduleDetails.setLastModifiedDt( Calendar.getInstance().getTime());
		vaccinationScheduleDetails.setUserId( vaccinationScheduleDetailBM.getUserName());
		vaccinationScheduleDetails.setPeriodInDays( vaccinationScheduleDetailBM.getPeriodInDays());
		
		if( null != vaccinationScheduleDetailBM.getVaccinationName() &&
				null != vaccinationScheduleDetailBM.getVaccinationName().getCode() && 
				!vaccinationScheduleDetailBM.getVaccinationName().getCode().isEmpty() ){
			Vaccination vacc = commonDataManager.getVaccination(vaccinationScheduleDetailBM.getVaccinationName().getCode());
			if( null != vacc && ! vacc.getVaccinationCd().isEmpty()){
				vaccinationScheduleDetails.setVaccination( vacc );
			}
		}
		
		if( null != vaccinationScheduleDetailBM.getVaccinationType() && 
				null != vaccinationScheduleDetailBM.getVaccinationType().getCode() &&
			!vaccinationScheduleDetailBM.getVaccinationType().getCode().isEmpty()){
			ReferenceDataList referenceDataList = commonDataManager.getReferenceData( ScheduleManagerConstants.VACCINATION_TYPE, 
					vaccinationScheduleDetailBM.getVaccinationType().getCode() );
			if( null != referenceDataList )
				vaccinationScheduleDetails.setVaccinationTypeCd( 
						vaccinationScheduleDetailBM.getVaccinationType().getCode());
		}
		
		return vaccinationScheduleDetails;
	}
	
	
	private VaccinationScheduleBM convertVaccinationScheduleDM2BM( VaccinationSchedule vaccinationSchedule ){
		
		VaccinationScheduleBM vaccinationScheduleBM = new VaccinationScheduleBM();
		vaccinationScheduleBM.setAgeGroup( vaccinationSchedule.getAgeGroup());
		vaccinationScheduleBM.setDescription( vaccinationSchedule.getScheduleDesc());
		vaccinationScheduleBM.setActiveFlag( vaccinationSchedule.getActiveFlag().
										   equals(ScheduleManagerConstants.IS_ACTIVE_TRUE )?true : false);
		vaccinationScheduleBM.setScheduleName( vaccinationSchedule.getScheduleName());
		vaccinationScheduleBM.setUserName(vaccinationSchedule.getUserId());
		
		List<VaccinationScheduleDetails> vaccinationScheduleDetailsList = null;
		try {
			vaccinationScheduleDetailsList = vaccinationScheduleDetailsDAO.getActiveVaccinationsFor( vaccinationSchedule.getScheduleName() );
		} catch (Exception exception) {
			Fault fault = new Fault( ApplicationErrors.VACCINATION_SCHEDULE_DETAILS_READ_FAILED);
			throw new HCISException( fault , exception);
		}
		
		List<VaccinationScheduleDetailBM> scheduleDetailsList = new ArrayList<VaccinationScheduleDetailBM>(0); 
		if( null != vaccinationScheduleDetailsList  ){
			for( VaccinationScheduleDetails vaccinationScheduleDetails : vaccinationScheduleDetailsList){
				scheduleDetailsList.add( convertVaccinationScheduleDetailsDM2BM(vaccinationScheduleDetails));
			}
		}
		
		vaccinationScheduleBM.setVaccinationScheduleDetailList( scheduleDetailsList );
		
		return vaccinationScheduleBM;
	}
	
	private VaccinationScheduleDetailBM convertVaccinationScheduleDetailsDM2BM( VaccinationScheduleDetails vaccinationScheduleDetails ){
		
		VaccinationScheduleDetailBM vaccinationScheduleDetailBM = new VaccinationScheduleDetailBM();
		vaccinationScheduleDetailBM.setAge( vaccinationScheduleDetails.getAge());
		
		if(null != vaccinationScheduleDetails.getDeletedFlag()){
			vaccinationScheduleDetailBM.setDeletedFlag(vaccinationScheduleDetails.getDeletedFlag().
						equals(ScheduleManagerConstants.IS_ACTIVE_TRUE )?true : false);
		}

		vaccinationScheduleDetailBM.setDosage( vaccinationScheduleDetails.getDosage());
		vaccinationScheduleDetailBM.setScheduleName(vaccinationScheduleDetails.getId().getScheduleName());
		vaccinationScheduleDetailBM.setUserName(vaccinationScheduleDetails.getUserId());
		
		if( null != vaccinationScheduleDetails.getPeriodInDays()){
			vaccinationScheduleDetailBM.setPeriodInDays(vaccinationScheduleDetails.getPeriodInDays());
		}
		
		if( null != vaccinationScheduleDetails.getVaccinationTypeCd() &&
				!vaccinationScheduleDetails.getVaccinationTypeCd().isEmpty()){
			
			CodeAndDescription vaccinationType = new CodeAndDescription();
			
			ReferenceDataList referenceDataList = commonDataManager.getReferenceData(
													ScheduleManagerConstants.VACCINATION_TYPE, 
													vaccinationScheduleDetails.getVaccinationTypeCd() );
			
			vaccinationType.setCode( referenceDataList.getId().getAttrCode() );
			vaccinationType.setDescription( referenceDataList.getAttrDesc() );

			
			vaccinationScheduleDetailBM.setVaccinationType(getReferenceData(
					ScheduleManagerConstants.VACCINATION_TYPE, 
					vaccinationScheduleDetails.getVaccinationTypeCd()));
		}
		
		if(  null != vaccinationScheduleDetails.getVaccination() &&
				null != vaccinationScheduleDetails.getVaccination().getVaccinationCd() &&
				!vaccinationScheduleDetails.getVaccination().getVaccinationCd().isEmpty()){
			
			vaccinationScheduleDetailBM.setVaccinationName(
					new CodeAndDescription( 
							vaccinationScheduleDetails.getVaccination().getVaccinationCd() , 
							vaccinationScheduleDetails.getVaccination().getVaccinationName()));
		}
		vaccinationScheduleDetailBM.setSeqNbr( vaccinationScheduleDetails.getId().getSeqNbr());
		
		return vaccinationScheduleDetailBM;
	}
	
	private CodeAndDescription getReferenceData( String Context, String Code ){
		
		ReferenceDataList referenceDataList = commonDataManager.getReferenceData(Context, 
																				Code );
		CodeAndDescription codeAndDesc = new CodeAndDescription();
		codeAndDesc.setCode( referenceDataList.getId().getAttrCode() );
		codeAndDesc.setDescription( referenceDataList.getAttrDesc() );
		
		return codeAndDesc;
	}
	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.schedule.ScheduleManager#findSchedules(java.lang.String, 
	 * java.lang.String, 
	 * java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
	 */
	 public ListRange findSchedules( String scheduleName,
									 String description,
									 String vacciantionCode,
									 String ageGroup,
									 String age,
									 String activeFlag,
									 int start,
									 int count,
									 String orderBy) {

		 List<VaccinationSchedule> vaccinationScheduleList = 
			 							vaccinationScheduleDAO.getVaccinationSchedule(
			 											scheduleName, 
			 											description, 
			 											vacciantionCode, 
			 											ageGroup,
			 											age,
			 											activeFlag);
	
		 ListRange listRange = new ListRange();
	
		 if( vaccinationScheduleList != null && !vaccinationScheduleList.isEmpty() ){
	
			 Object[] data = new Object[vaccinationScheduleList.size()];
			 for (int i = 0; i < count && vaccinationScheduleList.size() > start + i ; i++) {
		
				 VaccinationScheduleBM vaccinationScheduleBM =
				 this.convertVaccinationScheduleDM2BM(vaccinationScheduleList.get( start + i));
				 data[i] = vaccinationScheduleBM;
			 }
			 
		 	 listRange.setData(data);
			 listRange.setTotalSize( vaccinationScheduleList.size() );
		 }else{
		
			 listRange.setData( new Object[0]);
			 listRange.setTotalSize(0);
		 }
	
		 return listRange;
	 }


	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.schedule.ScheduleManager#modifySchedule(in.wtc.hcis.bm.ip.ScheduleBM)
	 */
	public VaccinationScheduleBM modifyVaccinationSchedule( VaccinationScheduleBM modifiedScheduleBM )throws HCISException {
		try {
			
			Validate.notNull( modifiedScheduleBM ,"ModifiedScheduleBM object should not be null" );
			VaccinationSchedule vaccinationSchedule = vaccinationScheduleDAO.findById( modifiedScheduleBM.getScheduleName());
			VaccinationSchedule modifiedVaccinationSchedule = convertVaccinationScheduleBM2DM(modifiedScheduleBM, vaccinationSchedule);
			vaccinationScheduleDAO.attachDirty( modifiedVaccinationSchedule );
			
			List<VaccinationScheduleDetails> vaccinationScheduleDetailsList = 
									vaccinationScheduleDetailsDAO.findByProperty( ScheduleManagerImpl.SCHEDULE_NAME, 
																				  modifiedScheduleBM.getScheduleName());

			List<VaccinationScheduleDetailBM> vaccSchDetList = modifiedScheduleBM.getVaccinationScheduleDetailList();
			
			// deleted list 
			if( vaccSchDetList != null && vaccSchDetList.size() > 0){
				for ( VaccinationScheduleDetailBM vaccinationSccheduleDetailsBM : vaccSchDetList ){	
					
					if( vaccinationSccheduleDetailsBM.getRecordStatus() != null &&
							vaccinationSccheduleDetailsBM.getRecordStatus().length() > 0){
						
						if( vaccinationSccheduleDetailsBM.getRecordStatus().equals("delete") ){
							// stamping deleted flag as "Y".
							VaccinationScheduleDetailsId vaccinationScheduleDetailsId = new VaccinationScheduleDetailsId();
							vaccinationScheduleDetailsId.setScheduleName( modifiedScheduleBM.getScheduleName() );
							vaccinationScheduleDetailsId.setSeqNbr( vaccinationSccheduleDetailsBM.getSeqNbr() );
							
							VaccinationScheduleDetails vaccinationScheduleDetails = 
								vaccinationScheduleDetailsDAO.findById( vaccinationScheduleDetailsId );
							
							if( null != vaccinationScheduleDetails ){
								vaccinationScheduleDetails.setDeletedFlag( ScheduleManagerConstants.DELETE_FLAG_YES );
								vaccinationScheduleDetails.setUserId(vaccinationSccheduleDetailsBM.getUserName());
								vaccinationScheduleDetails.setLastModifiedDt(new Date());
								vaccinationScheduleDetailsDAO.attachDirty( vaccinationScheduleDetails );
							}
							
							//finding related entries in the patient vaccination schedule details table.
							List<PatientVaccinationScheduleDetails> patVaccSchdDetlsList = 
									patientVaccinationScheduleDetailsDAO.
									getPatientVaccinationScheduleDetails( modifiedScheduleBM.getScheduleName(), 
																		  vaccinationSccheduleDetailsBM.getVaccinationName().getCode(), 
																		  vaccinationSccheduleDetailsBM.getPeriodInDays());
							
							if( null != patVaccSchdDetlsList && patVaccSchdDetlsList.size() > 0 ){
								for( PatientVaccinationScheduleDetails patVaccSchdDetls : patVaccSchdDetlsList ){
									if( null == patVaccSchdDetls.getGivenDate() ){
										patientVaccinationScheduleDetailsDAO.delete( patVaccSchdDetls );
									}
								}
							}
						}else if( vaccinationSccheduleDetailsBM.getRecordStatus().equals("edit") ){
							
							VaccinationScheduleDetailsId id = new VaccinationScheduleDetailsId();
							id.setScheduleName( modifiedScheduleBM.getScheduleName());
							id.setSeqNbr(vaccinationSccheduleDetailsBM.getSeqNbr() );
							VaccinationScheduleDetails vaccinationScheduleDetailsDM = vaccinationScheduleDetailsDAO.findById(id);
							
							vaccinationScheduleDetailsDAO.attachDirty( convertVaccinationScheduleDetailsBM2DM(vaccinationSccheduleDetailsBM, vaccinationScheduleDetailsDM));
							
						}else if( vaccinationSccheduleDetailsBM.getRecordStatus().equals("add") ){

							// add case
							Integer seqNbr = vaccinationScheduleDetailsDAO.getMaxSeqNbr( modifiedScheduleBM.getScheduleName() );
							
							VaccinationScheduleDetails vaccinationdetailsToBeAdded = 
								convertVaccinationScheduleDetailsBM2DM(vaccinationSccheduleDetailsBM, null);
							VaccinationScheduleDetailsId vaccinationScheduleDetailsId = new VaccinationScheduleDetailsId();
							vaccinationScheduleDetailsId.setScheduleName( modifiedScheduleBM.getScheduleName());
							vaccinationScheduleDetailsId.setSeqNbr( seqNbr );
							
							vaccinationdetailsToBeAdded.setId( vaccinationScheduleDetailsId );
							
							vaccinationScheduleDetailsDAO.save( vaccinationdetailsToBeAdded );
							
							// adding entry into patient vaccination schedule details.
							
							List<PatientVaccinationSchedule> patVaccSchdList = 
								patientVaccinationScheduleDAO.findByProperty( "vaccinationSchedule.scheduleName", 
																			  modifiedScheduleBM.getScheduleName());
							for( PatientVaccinationSchedule patVaccSchd : patVaccSchdList ){
								
								PatientVaccinationScheduleDetails patVaccSchdDetls = 
									this.createPatientVaccinationScheduleDetail(vaccinationSccheduleDetailsBM, 
																				patVaccSchd.getStartDt() );
								if( null != patVaccSchd.getStatusCd() && 
										patVaccSchd.getStatusCd().equals(ScheduleManagerConstants.PAT_VACC_SCHEDULE_STATUS_NOT_STARTED)){
									
									Integer subSeqNbr = patientVaccinationScheduleDetailsDAO.
											getMaxSubSeqNbr(patVaccSchd.getSeqNbr(), 
															patVaccSchd.getPatient().getPatientId());

									PatientVaccinationScheduleDetailsId patVaccSchdDetlsId = new PatientVaccinationScheduleDetailsId();
									patVaccSchdDetlsId.setPatientId( patVaccSchd.getPatient().getPatientId());
									patVaccSchdDetlsId.setScheduleName( patVaccSchd.getVaccinationSchedule().getScheduleName());
									patVaccSchdDetlsId.setSeqNbr( patVaccSchd.getSeqNbr());
									patVaccSchdDetlsId.setSubSeqNbr( subSeqNbr );
									
									patVaccSchdDetls.setId( patVaccSchdDetlsId );
									patVaccSchdDetls.setUserId( modifiedScheduleBM.getUserName() );
									
									patientVaccinationScheduleDetailsDAO.save( patVaccSchdDetls );
						    	
								}else if( null != patVaccSchd.getStatusCd() && 
										  patVaccSchd.getStatusCd().equals(ScheduleManagerConstants.PAT_VACC_SCHEDULE_STATUS_PARTIALLY_COMPLETED)){
									
									if( null != patVaccSchdDetls ){
										if( null != patVaccSchdDetls.getDueDate() && 
											patVaccSchdDetls.getDueDate().getTime() >= Calendar.getInstance().getTimeInMillis() ){
											
											Integer subSeqNbr = patientVaccinationScheduleDetailsDAO.getMaxSubSeqNbr(patVaccSchd.getSeqNbr(), 
													 patVaccSchd.getPatient().getPatientId());

											PatientVaccinationScheduleDetailsId patVaccSchdDetlsId = new PatientVaccinationScheduleDetailsId();
											patVaccSchdDetlsId.setPatientId( patVaccSchd.getPatient().getPatientId());
											patVaccSchdDetlsId.setScheduleName( patVaccSchd.getVaccinationSchedule().getScheduleName());
											patVaccSchdDetlsId.setSeqNbr( patVaccSchd.getSeqNbr());
											patVaccSchdDetlsId.setSubSeqNbr( subSeqNbr );
											
											patVaccSchdDetls.setId( patVaccSchdDetlsId );
											patVaccSchdDetls.setUserId( modifiedScheduleBM.getUserName() );
											
											patientVaccinationScheduleDetailsDAO.save( patVaccSchdDetls );
										}
									}
								}
							}
						}
					}
				}
			}
			
			return convertVaccinationScheduleDM2BM( modifiedVaccinationSchedule );
		} catch (Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.MODIFY_VACCINATION_SCHEDULE_FAILED);
			throw new HCISException( fault, exception );
		}
	}
	
	private PatientVaccinationScheduleDetails createPatientVaccinationScheduleDetail( VaccinationScheduleDetailBM vaccinationScheduleDetailBM, Date startDate  ){
		Long oneDayInMilliSec = new Long( 24* 60* 60* 1000);
		Long endDateInMilliSec = new Long(0);
		
		if( null != vaccinationScheduleDetailBM.getPeriodInDays() ){
			endDateInMilliSec = vaccinationScheduleDetailBM.getPeriodInDays() * oneDayInMilliSec;
		}
		Date dueDate = new Date(startDate.getTime() + endDateInMilliSec);
		
		PatientVaccinationScheduleDetails patVaccSchdDetails = new PatientVaccinationScheduleDetails();
		patVaccSchdDetails.setAge( vaccinationScheduleDetailBM.getAge());
		patVaccSchdDetails.setDosage( vaccinationScheduleDetailBM.getDosage());
		patVaccSchdDetails.setDueDate(dueDate);
		patVaccSchdDetails.setPeriodInDays( vaccinationScheduleDetailBM.getPeriodInDays());
		
		if( null != vaccinationScheduleDetailBM.getVaccinationName() && 
			null != vaccinationScheduleDetailBM.getVaccinationName().getCode() &&
			!vaccinationScheduleDetailBM.getVaccinationName().getCode().equals("")){
			Vaccination vaccination = commonDataManager.getVaccination( vaccinationScheduleDetailBM.getVaccinationName().getCode());
			if( null != vaccination ){
				patVaccSchdDetails.setVaccination(vaccination);
			}
		}
		
		if( null != vaccinationScheduleDetailBM.getVaccinationType() &&
			null != vaccinationScheduleDetailBM.getVaccinationType().getCode() && 
			!vaccinationScheduleDetailBM.getVaccinationType().getCode().equals("")){
			patVaccSchdDetails.setVaccinationTypeCd( vaccinationScheduleDetailBM.getVaccinationType().getCode());
		}
		
		return patVaccSchdDetails;
	}
	public void removeVaccinationSchedules( List<String> scheduleNameList )
		throws HCISException {
		try {
			if( null != scheduleNameList ){
				for( String scheduleName : scheduleNameList ){
					List<PatientVaccinationSchedule> patientVaccinationScheduleList = 
					 patientVaccinationScheduleDAO.findByProperty("vaccinationSchedule.scheduleName", scheduleName );
					if( null != patientVaccinationScheduleList && patientVaccinationScheduleList.size() > 0 ){
						VaccinationSchedule vaccinationSchedule = vaccinationScheduleDAO.findById( scheduleName );
						vaccinationSchedule.setActiveFlag( ScheduleManagerConstants.IS_ACTIVE_FALSE);
						vaccinationScheduleDAO.attachDirty( vaccinationSchedule );
					}else{
						List<VaccinationScheduleDetails> vaccinationScheduleDetailsList = 
							vaccinationScheduleDetailsDAO.findByProperty( ScheduleManagerImpl.SCHEDULE_NAME, 
																		  scheduleName );
						if( null != vaccinationScheduleDetailsList ){
							for( VaccinationScheduleDetails vaccinationScheduleDetails : vaccinationScheduleDetailsList ){
								vaccinationScheduleDetailsDAO.delete( vaccinationScheduleDetails );
							}
						}
						VaccinationSchedule vaccinationSchedule = vaccinationScheduleDAO.findById( scheduleName );
						vaccinationScheduleDAO.delete( vaccinationSchedule );
					}
				}
			}
		} catch (Exception exception ) {
			Fault fault = new Fault(ApplicationErrors.REMOVE_VACCINATION_SCHEDULE_FAILED);
			throw new HCISException( fault, exception );
		}
	}	
	
	public boolean isVaccinationScheduleValid( String scheduleName ){
		boolean flag = true;
		
		VaccinationSchedule vaccinationSchedule = vaccinationScheduleDAO.findById( scheduleName ); 
		
		if( null != vaccinationSchedule)
			flag = false;
		return flag;
	}
	
	public List<VaccinationScheduleDetailBM> getVaccinationScheduleDetails( String vaccSchCode ) 
			throws HCISException{
		try {
			List< VaccinationScheduleDetails > vaccinationScheduleDetailsList = vaccinationScheduleDetailsDAO.findByProperty("id.scheduleName",vaccSchCode);
			List< VaccinationScheduleDetailBM > vaccSchDetList = new ArrayList<VaccinationScheduleDetailBM>();
			
			if (vaccinationScheduleDetailsList != null && vaccinationScheduleDetailsList.size() > 0) {
				for (int i = 0; i < vaccinationScheduleDetailsList.size(); i++) {
					VaccinationScheduleDetails tmp = vaccinationScheduleDetailsList.get(i);
					VaccinationScheduleDetailBM vaccinationScheduleDetailBM = convertVaccinationScheduleDetailsDM2BM( tmp );
					vaccSchDetList.add(vaccinationScheduleDetailBM);
				}
			}
			return vaccSchDetList;
		} catch (Exception exception) {
			Fault fault = new Fault( ApplicationErrors.READ_VACCINATION_SCHEDULE_DETAILS_FAILED );
			throw new HCISException( fault, exception );
		}
	}
	
	public List<PatientVaccinationScheduleBM> getPatientVaccinationSchedules( Integer patientId ) 
			throws HCISException{
		try {
			List< PatientVaccinationSchedule > patVaccSchedulesDMList = 
					patientVaccinationScheduleDAO.findByProperty("patient.patientId",patientId);
			
			List< PatientVaccinationScheduleBM> patVaccSchBMList = new ArrayList<PatientVaccinationScheduleBM>();
			
			if( patVaccSchedulesDMList != null && patVaccSchedulesDMList.size() > 0 ){
				for( PatientVaccinationSchedule patVaccSchDM : patVaccSchedulesDMList){
					
					PatientVaccinationScheduleBM patVaccSchBM = 
									convertPatientVaccinationScheduleDM2BM( patVaccSchDM );
					
					List<PatientVaccinationScheduleDetailsBM> patVaccSchDetBMList = 
									getPatientVaccinationScheduleDetailsBMList( patVaccSchDM.getSeqNbr());
					
					patVaccSchBM.setPatientVaccinationScheduleDetailsBMList(patVaccSchDetBMList);
					patVaccSchBMList.add(patVaccSchBM);
				}
			}
			return patVaccSchBMList;
		} catch (Exception exception) {
			Fault fault = new Fault( ApplicationErrors.READ_PAT_VACC_SCHEDULE_FAILED);
			throw new HCISException( fault, exception );
		}
	}
	
	private PatientVaccinationScheduleBM convertPatientVaccinationScheduleDM2BM( 
				PatientVaccinationSchedule patVaccSchDM){

		PatientVaccinationScheduleBM patVaccSchBM = new PatientVaccinationScheduleBM();
			if( patVaccSchDM != null ){
				
				if( patVaccSchDM.getDoctor() != null ){
					CodeAndDescription doctor = new CodeAndDescription();
					doctor.setCode(patVaccSchDM.getDoctor().getDoctorId().toString());
					doctor.setDescription(patVaccSchDM.getDoctor().getFullName());
					
					patVaccSchBM.setDoctor(doctor);
				}
				
				if( patVaccSchDM.getPatient() != null ){
					patVaccSchBM.setPatientId(patVaccSchDM.getPatient().getPatientId());
				}
				
				if( patVaccSchDM.getVaccinationSchedule() != null ){
					CodeAndDescription vaccinationSchedule = new CodeAndDescription();
					vaccinationSchedule.setCode(patVaccSchDM.getVaccinationSchedule().getScheduleName());
					vaccinationSchedule.setDescription(patVaccSchDM.getVaccinationSchedule().getScheduleDesc());
					
					patVaccSchBM.setScheduleName(vaccinationSchedule);
				}
				
				if( patVaccSchDM.getStatusCd() != null && patVaccSchDM.getStatusCd().length() > 0 ){
					CodeAndDescription scheduleStatus = new CodeAndDescription();
					
					ReferenceDataList dataList = commonDataManager.getReferenceData(
							ScheduleManagerConstants.PAT_VACC_SCHEDULE_STATUS,patVaccSchDM.getStatusCd());
					
					scheduleStatus.setCode(dataList.getId().getAttrCode());
					scheduleStatus.setDescription(dataList.getAttrDesc());
					
					patVaccSchBM.setStatusCode(scheduleStatus);
				}
				
				patVaccSchBM.setSequenceNumber(patVaccSchDM.getSeqNbr());
				patVaccSchBM.setStartDate(patVaccSchDM.getStartDt());
				patVaccSchBM.setUserId(patVaccSchDM.getUserId());
				
				
			}
			
			return patVaccSchBM;
	}
	
	private List<PatientVaccinationScheduleDetailsBM> getPatientVaccinationScheduleDetailsBMList(
			Integer patVaccSchSeqNbr) throws HCISException{
		try {
			List< PatientVaccinationScheduleDetails > patVaccSchedulesDMList = 
						patientVaccinationScheduleDetailsDAO.findByProperty("id.seqNbr",patVaccSchSeqNbr);
			
			List< PatientVaccinationScheduleDetailsBM > patVaccSchDetBMList = 
													new ArrayList<PatientVaccinationScheduleDetailsBM>();
			
			if( patVaccSchedulesDMList != null && patVaccSchedulesDMList.size() > 0 ){
				
				for( PatientVaccinationScheduleDetails patVaccSchDetDM : patVaccSchedulesDMList ){
					
					PatientVaccinationScheduleDetailsBM patVaccSchDetBM = 
											 convertPatientVaccinationScheduleDetailsDM2BM( patVaccSchDetDM );
					
					patVaccSchDetBMList.add(patVaccSchDetBM);
				}
			}
			return patVaccSchDetBMList;
		} catch (Exception exception) {
			Fault fault = new Fault( ApplicationErrors.READ_PAT_VACC_SCHEDULE_DETAILS_FAILED);
			throw new HCISException( fault, exception );
		}
	}
	
	private PatientVaccinationScheduleDetailsBM convertPatientVaccinationScheduleDetailsDM2BM(
			PatientVaccinationScheduleDetails patVaccSchDetDM ){
		
		PatientVaccinationScheduleDetailsBM patVaccSchDetBM = new PatientVaccinationScheduleDetailsBM();
		
		if( patVaccSchDetDM != null ){
			patVaccSchDetBM.setAge(patVaccSchDetDM.getAge());
			patVaccSchDetBM.setDoctorComments(patVaccSchDetDM.getDoctorComments());
			patVaccSchDetBM.setDosage(patVaccSchDetDM.getDosage());
			patVaccSchDetBM.setDueDate(patVaccSchDetDM.getDueDate());
			patVaccSchDetBM.setGivenDate(patVaccSchDetDM.getGivenDate());
			
			if( patVaccSchDetDM.getPatient() != null ){
				patVaccSchDetBM.setPatientId(patVaccSchDetDM.getPatient().getPatientId());
			}
			
			if( patVaccSchDetDM.getDoctor() != null ){
				CodeAndDescription doctor = new CodeAndDescription();
				doctor.setCode(patVaccSchDetDM.getDoctor().getDoctorId().toString());
				doctor.setDescription(patVaccSchDetDM.getDoctor().getFullName());
				
				patVaccSchDetBM.setDoctor(doctor);
			}
			
			patVaccSchDetBM.setPeriodInDays(patVaccSchDetDM.getPeriodInDays());
			
			if( patVaccSchDetDM.getVaccinationSchedule() != null ){
				CodeAndDescription vaccinationSchedule = new CodeAndDescription();
				vaccinationSchedule.setCode(patVaccSchDetDM.getVaccinationSchedule().getScheduleName());
				vaccinationSchedule.setDescription(patVaccSchDetDM.getVaccinationSchedule().getScheduleDesc());
				
				patVaccSchDetBM.setScheduleName(vaccinationSchedule);
			}
			
			patVaccSchDetBM.setSequenceNumber(patVaccSchDetDM.getId().getSeqNbr());
			patVaccSchDetBM.setSubSequenceNumber(patVaccSchDetDM.getId().getSubSeqNbr());
			patVaccSchDetBM.setUserId(patVaccSchDetDM.getUserId());
			
			if( patVaccSchDetDM.getVaccinationTypeCd() != null && 
					patVaccSchDetDM.getVaccinationTypeCd().length() > 0){
				
				CodeAndDescription vaccinationType = new CodeAndDescription();
				
				ReferenceDataList dataList = commonDataManager.getReferenceData(
						ScheduleManagerConstants.VACCINATION_TYPE,patVaccSchDetDM.getVaccinationTypeCd());
				
				vaccinationType.setCode(dataList.getId().getAttrCode());
				vaccinationType.setDescription(dataList.getAttrDesc());
				
				patVaccSchDetBM.setVaccinationTypeCd(vaccinationType);
			}
			
			if( patVaccSchDetDM.getVaccination() != null ){
				
				CodeAndDescription vaccination = new CodeAndDescription();
				
				ReferenceDataList dataList = commonDataManager.getReferenceData(
						ScheduleManagerConstants.VACCINATION,patVaccSchDetDM.getVaccination().getVaccinationCd());
				
				vaccination.setCode(dataList.getId().getAttrCode());
				vaccination.setDescription(dataList.getAttrDesc());
				
				patVaccSchDetBM.setVaccinationCd(vaccination);
			}
		}
		
		return patVaccSchDetBM;
	}

	public void savePatientVaccinationSchedule(
				PatientVaccinationScheduleBM[] patienVaccinationScheduleDetailsBMList)
					throws HCISException {
		try {
			if( patienVaccinationScheduleDetailsBMList != null && 
					patienVaccinationScheduleDetailsBMList.length > 0){
				
				for( PatientVaccinationScheduleBM patVaccSchBM : patienVaccinationScheduleDetailsBMList){
					
					PatientVaccinationSchedule patVaccSchDM = convertPatientVaccinationScheduleBM2DM( patVaccSchBM );
					
					patientVaccinationScheduleDAO.save(patVaccSchDM);
					
					if( patVaccSchBM.getPatientVaccinationScheduleDetailsBMList() != null &&
							patVaccSchBM.getPatientVaccinationScheduleDetailsBMList().size() > 0){
						
						List< PatientVaccinationScheduleDetailsBM> patientVaccinationScheduleBMList = 
							patVaccSchBM.getPatientVaccinationScheduleDetailsBMList();
						
						int subSeqNbr = 0;
						for( PatientVaccinationScheduleDetailsBM patVaccSchDetBM : 
														patientVaccinationScheduleBMList){
							
							PatientVaccinationScheduleDetails patVaccSchDetDM = 
											convertPatientVaccinationScheduleDetailBM2DM( patVaccSchDetBM );
							
							PatientVaccinationScheduleDetailsId id = new PatientVaccinationScheduleDetailsId();
							id.setPatientId(patVaccSchDetBM.getPatientId());
							id.setScheduleName(patVaccSchDetBM.getScheduleName().getCode());
							id.setSeqNbr(patVaccSchDM.getSeqNbr());
							id.setSubSeqNbr( subSeqNbr );
							
							patVaccSchDetDM.setId(id);
							patVaccSchDetDM.setPatientVaccinationSchedule(patVaccSchDM);
							
							patientVaccinationScheduleDetailsDAO.save(patVaccSchDetDM);
							subSeqNbr++;
						}
					}
				}
			}
		} catch (Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.ADD_PAT_VACC_SCHEDULE_FAILED);
			throw new HCISException( fault, exception );
		}
	}
	
	private PatientVaccinationSchedule convertPatientVaccinationScheduleBM2DM( 
			PatientVaccinationScheduleBM patVaccSchBM ){
		PatientVaccinationSchedule patVaccSchDM = new PatientVaccinationSchedule();
		
		if( patVaccSchBM != null ){

			if( patVaccSchBM.getDoctor() != null && 
					patVaccSchBM.getDoctor().getCode() != null &&
					patVaccSchBM.getDoctor().getCode().length() > 0){
				Doctor doctor = dataModelManager.getDoctor(new Integer(patVaccSchBM.getDoctor().getCode()));
				if( doctor != null){
					patVaccSchDM.setDoctor(doctor);
				}
			}
			
			if( patVaccSchBM.getPatientId() != null ){
				Patient patient = dataModelManager.getPatient( patVaccSchBM.getPatientId() );
				if( patient != null){
					patVaccSchDM.setPatient(patient);
				}
			}
			
			if( patVaccSchBM.getScheduleName() != null ){
				VaccinationSchedule vaccinationSchedule = 
						commonDataManager.getVaccinationSchedule( patVaccSchBM.getScheduleName().getCode() );
				if( vaccinationSchedule != null){
					patVaccSchDM.setVaccinationSchedule(vaccinationSchedule);
				}
			}

			String statusCd = getPatientVaccinationScheduleStatus( patVaccSchBM );
			patVaccSchDM.setStatusCd(statusCd);
			patVaccSchDM.setUserId( patVaccSchBM.getUserId());
			patVaccSchDM.setStartDt(patVaccSchBM.getStartDate());
			patVaccSchDM.setLastModifiedDt(new Date());
		}
		
		
		return patVaccSchDM;
	}
	
	private String getPatientVaccinationScheduleStatus( PatientVaccinationScheduleBM patVaccSchBM ){
		
		ReferenceDataList refData = null;
		
		List<PatientVaccinationScheduleDetailsBM> patVaccSchDetBMList =
			patVaccSchBM.getPatientVaccinationScheduleDetailsBMList();
		
		List<PatientVaccinationScheduleDetailsBM> x =
				new ArrayList<PatientVaccinationScheduleDetailsBM>();
		
		for( PatientVaccinationScheduleDetailsBM patVaccSchDetBM : patVaccSchDetBMList){
			if( patVaccSchDetBM.getGivenDate() != null ){
				x.add(patVaccSchDetBM);
			}
		}
		
		if( x != null && x.size() == 0){
			
			refData = commonDataManager.getReferenceData(
					ScheduleManagerConstants.PAT_VACC_SCHEDULE_STATUS,
					ScheduleManagerConstants.PAT_VACC_SCHEDULE_STATUS_NOT_STARTED
			);
			
			
		}else if( x != null && x.size() != 0 && x.size() < patVaccSchDetBMList.size()){
			
			refData = commonDataManager.getReferenceData(
					ScheduleManagerConstants.PAT_VACC_SCHEDULE_STATUS,
					ScheduleManagerConstants.PAT_VACC_SCHEDULE_STATUS_PARTIALLY_COMPLETED
			);
			
		}else if( x != null && x.size() != 0 && x.size() == patVaccSchDetBMList.size()){
			
			refData = commonDataManager.getReferenceData(
					ScheduleManagerConstants.PAT_VACC_SCHEDULE_STATUS,
					ScheduleManagerConstants.PAT_VACC_SCHEDULE_STATUS_COMPLETED
			);
		}
		
		return refData.getId().getAttrCode();
	}
	
	private PatientVaccinationScheduleDetails convertPatientVaccinationScheduleDetailBM2DM(
			PatientVaccinationScheduleDetailsBM patVaccSchDetBM){
		
		PatientVaccinationScheduleDetails patVaccSchDetDM = new PatientVaccinationScheduleDetails();
		
		if( patVaccSchDetBM!= null ){
			
			if( patVaccSchDetBM.getDoctor() != null && 
					patVaccSchDetBM.getDoctor().getCode() != null &&
					patVaccSchDetBM.getDoctor().getCode().length() > 0){
				Doctor doctor = dataModelManager.getDoctor(new Integer(patVaccSchDetBM.getDoctor().getCode()));
				if( doctor != null){
					patVaccSchDetDM.setDoctor(doctor);
				}
			}
			
			if( patVaccSchDetBM.getPatientId() != null ){
				Patient patient = dataModelManager.getPatient( patVaccSchDetBM.getPatientId() );
				if( patient != null){
					patVaccSchDetDM.setPatient(patient);
				}
			}
			
			if( patVaccSchDetBM.getVaccinationCd() != null ){
				Vaccination vaccination = commonDataManager.getVaccination( 
											patVaccSchDetBM.getVaccinationCd().getCode() );
				if( vaccination != null){
					patVaccSchDetDM.setVaccination(vaccination);
				}
			}
			
			if( patVaccSchDetBM.getScheduleName() != null ){
				VaccinationSchedule vaccinationSchedule = 
						commonDataManager.getVaccinationSchedule( patVaccSchDetBM.getScheduleName().getCode() );
				if( vaccinationSchedule != null){
					patVaccSchDetDM.setVaccinationSchedule(vaccinationSchedule);
				}
			}
			
			if( patVaccSchDetBM.getVaccinationTypeCd() != null  ){
				patVaccSchDetDM.setVaccinationTypeCd(patVaccSchDetBM.getVaccinationTypeCd().getCode());
			}
			
			patVaccSchDetDM.setPeriodInDays(patVaccSchDetBM.getPeriodInDays());
			patVaccSchDetDM.setUserId(patVaccSchDetBM.getUserId());
			patVaccSchDetDM.setAge(patVaccSchDetBM.getAge());
			patVaccSchDetDM.setDoctorComments(patVaccSchDetBM.getDoctorComments());
			patVaccSchDetDM.setDosage(patVaccSchDetBM.getDosage());
			patVaccSchDetDM.setDueDate(patVaccSchDetBM.getDueDate());
			patVaccSchDetDM.setGivenDate(patVaccSchDetBM.getGivenDate());
		}
		return patVaccSchDetDM;
	}
	
	public void removePatientVaccinationSchedule( Integer patientId ) throws HCISException{
		try {
			List<PatientVaccinationScheduleDetails> patVaccSchDetDMList = 
							patientVaccinationScheduleDetailsDAO.findByProperty("id.patientId", patientId);
			if( patVaccSchDetDMList != null && patVaccSchDetDMList.size() > 0){
				for( PatientVaccinationScheduleDetails patVaccSchDetDM : patVaccSchDetDMList){
					patientVaccinationScheduleDetailsDAO.delete( patVaccSchDetDM );
				}
			}
		} catch (Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.DELETE_PAT_VACC_SCHEDULE_DETAILS_FAILED);
			throw new HCISException( fault, exception );
		}
			
		try {
			List<PatientVaccinationSchedule> patVaccSchDMList = 
				patientVaccinationScheduleDAO.findByProperty("patient.patientId", patientId);
			if( patVaccSchDMList != null && patVaccSchDMList.size() > 0){
				for( PatientVaccinationSchedule patVaccSchDM : patVaccSchDMList){
					patientVaccinationScheduleDAO.delete( patVaccSchDM );
				}
			}
		} catch (Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.DELETE_PAT_VACC_SCHEDULE_FAILED);
			throw new HCISException( fault, exception );
		}
	} 
	
	public void cancelPatientVaccinationScheduleDetail( PatientVaccinationScheduleDetailsBM patVaccSchDetBM) 
			throws HCISException{
		
		try {
			PatientVaccinationScheduleDetailsId id = new PatientVaccinationScheduleDetailsId();
			
			if( patVaccSchDetBM != null  ){
				id.setPatientId(patVaccSchDetBM.getPatientId());
				id.setScheduleName(patVaccSchDetBM.getScheduleName().getCode());
				id.setSeqNbr(patVaccSchDetBM.getSequenceNumber());
				id.setSubSeqNbr(patVaccSchDetBM.getSubSequenceNumber());
			}
			PatientVaccinationScheduleDetails patVaccSchDetDM = 
							patientVaccinationScheduleDetailsDAO.findById(id);
			
			if( patVaccSchDetDM != null ){
				patientVaccinationScheduleDetailsDAO.delete(patVaccSchDetDM);
			}
		} catch (Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.CANCEL_PAT_VACC_SCHEDULE_DETAIL_FAILED);
			throw new HCISException( fault, exception );
		}
	}
	
	public void cancelPatientVaccinationSchedule( Integer seqNbr ) 
			throws HCISException{
		try {
			PatientVaccinationSchedule patVaccSchDM = 
							patientVaccinationScheduleDAO.findById(seqNbr);
			
			if( patVaccSchDM != null ){
				List<PatientVaccinationScheduleDetails> patVaccSchDetDMList = 
					patientVaccinationScheduleDetailsDAO.findByProperty("id.seqNbr", seqNbr);
				
				if( patVaccSchDetDMList != null && patVaccSchDetDMList.size() > 0){
					for( PatientVaccinationScheduleDetails patVaccSchDet : patVaccSchDetDMList){
						patientVaccinationScheduleDetailsDAO.delete( patVaccSchDet );
					}
				}
				
				patientVaccinationScheduleDAO.delete( patVaccSchDM );
			}
		} catch (Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.CANCEL_PAT_VACC_SCHEDULE_FAILED);
			throw new HCISException( fault, exception );
		}
	}
	
	public VaccinationBM getVaccination(  String vaccinationCd ) throws HCISException{
		try {
			VaccinationBM vaccinationBM = new VaccinationBM();
			
			if( ! vaccinationCd.isEmpty() ){
				Vaccination vaccinationDM = vaccinationDAO.findById( vaccinationCd );
				
				if( vaccinationDM == null ){
					throw new Exception( "Vaccination with code " + vaccinationCd + " does not exist");
				}else{
					vaccinationBM.setActiveFlag(vaccinationDM.getActiveFlag());
					vaccinationBM.setAgeRange(vaccinationDM.getAgeRange());
					vaccinationBM.setLastModifiedDtm(vaccinationDM.getLastModifiedDtm());
					vaccinationBM.setSubstituteFor(vaccinationDM.getSubstituteFor());
					vaccinationBM.setUserId(vaccinationDM.getUserId());
					vaccinationBM.setVaccinationCd(vaccinationDM.getVaccinationCd());
					vaccinationBM.setVaccinationName(vaccinationDM.getVaccinationName());
				}
			}
			
			return vaccinationBM;
		} catch (Exception exception) {
			Fault fault = new Fault( ApplicationErrors.VACCINATION_READ_FAILED);
			throw new HCISException( fault, exception);
		}
	}
	
	public void setDataModelManager(DataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public void setVaccinationScheduleDAO(
			VaccinationScheduleDAOExtn vaccinationScheduleDAO) {
		this.vaccinationScheduleDAO = vaccinationScheduleDAO;
	}

	public void setPatientVaccinationScheduleDAO(
			PatientVaccinationScheduleDAO patientVaccinationScheduleDAO) {
		this.patientVaccinationScheduleDAO = patientVaccinationScheduleDAO;
	}

	public void setCommonDataManager(CommonDataManager commonDataManager) {
		this.commonDataManager = commonDataManager;
	}

	public void setPatientVaccinationScheduleDetailsDAO(
			PatientVaccinationScheduleDetailsDAOExtn patientVaccinationScheduleDetailsDAO) {
		this.patientVaccinationScheduleDetailsDAO = patientVaccinationScheduleDetailsDAO;
	}

	public void setVaccinationScheduleDetailsDAO(
			VaccinationScheduleDetailsDAOExtn vaccinationScheduleDetailsDAO) {
		this.vaccinationScheduleDetailsDAO = vaccinationScheduleDetailsDAO;
	}

	public void setVaccinationDAO(VaccinationDAO vaccinationDAO) {
		this.vaccinationDAO = vaccinationDAO;
	}
}
