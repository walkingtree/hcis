package in.wtc.hcis.bo.admin;

import java.util.Date;
import java.util.List;

import in.wtc.hcis.bm.base.PatientVaccinationScheduleBM;
import in.wtc.hcis.bm.base.PatientVaccinationScheduleDetailsBM;
import in.wtc.hcis.bm.base.VaccinationBM;
import in.wtc.hcis.bm.base.VaccinationScheduleBM;
import in.wtc.hcis.bm.base.VaccinationScheduleDetailBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

/**
 * 
 * @author Vinay Kurudi
 * 
 *	this interface provides the following API
 *	--> Create  new schedule into the system.
 *	--> Getting schedule information from the system.
 *	--> Modifying an existing schedule information.
 *	--> Removing an existing schedule information. 
 */
public interface ScheduleManager {

	/**
	 *  this method configures schedule  entry into the system
	 *  
	 * @param scheduleBM it contains schedule information and 
	 * details information of that schedule
	 * 
	 * @return vaccinationScheduleBM it returns  complete schedule information. 
	 */
	VaccinationScheduleBM addSchedule( VaccinationScheduleBM vaccinationScheduleBM ) throws HCISException;
	
	/**
	 * 
	 * @param vaccinationType
	 * @param scheduleName
	 * @param description
	 * @param vaccineCode
	 * @param diseaseCode
	 * @param ageGroup
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	ListRange findSchedules (String scheduleName,
							 String description,
							 String vaccineCode,
							 String ageGroup,
							 String age,
							 String activeFlag,
							 int start, 
						     int count, 
						     String orderBy );
	/**
	 * this method modifies schedule information as well as 
	 * schedule detail information
	 * 
	 * @param modifiedvaccinationScheduleBM it contains schedule information and 
	 * details information of that schedule
	 * @return
	 */
	VaccinationScheduleBM modifyVaccinationSchedule ( VaccinationScheduleBM modifiedvaccinationScheduleBM ) throws HCISException;
	
	/**
	 *  this method removes the schedule(s) from the system. 
	 *  this method deletes the vacciantion schedule,if the vaccination schedule 
	 *  didn't assigned to any patient in the system.if the vaccination schedule assigned to 
	 *  any patient it stamp as in active. 
	 * @param scheduleIdList
	 */
	void removeVaccinationSchedules ( List<String> scheduleNameList );
	
	/**
	 * for finding schedule name is already in the system or not.
	 * @param scheduleName
	 * @return
	 */
	 boolean isVaccinationScheduleValid( String scheduleName );
	 
	 /**
	  * 1. This method actually save schedule information of patient.
	  * 2. This method saves lsit of schedules at a one time.
	  * 
	  * @param patienVaccinationScheduleDetailsBMList
	  * @return
	  * @throws HCISException
	  */
	 void savePatientVaccinationSchedule( PatientVaccinationScheduleBM [] patienVaccinationScheduleDetailsBMList 
			 							)throws HCISException;
	 
	 /**
	  * 1. This method provides the information about the patient vacciantion schedules.
	  * @param patientId
	  * @return
	  * @throws HCISException
	  */
	 List<PatientVaccinationScheduleBM> getPatientVaccinationSchedules( Integer patientId )throws HCISException;
	 
	 /**
	  * This method returns the list of vaccination schedule details of a particular 
	  * vaccination schedule
	  *  
	  * @param vaccSchCode
	  * @param start
	  * @param count
	  * @param orderBy
	  * @return
	  * @throws HCISException
	  */
	 List<VaccinationScheduleDetailBM> getVaccinationScheduleDetails( String vaccSchCode ) throws HCISException;
	 
	 /**
	  * This method removes all the schedules and related schedule details of a given patient
	  * @param patientId
	  * @throws HCISException
	  */
	 void removePatientVaccinationSchedule( Integer patientId ) throws HCISException;

	 /*
	  * This method removes the entry from patient vaccination schedule details table if 
	  * given date for the patient vaccination schedule details is null i.e. if the vaccination
	  * schedule has not been assigned to a patient
	  */
	 void cancelPatientVaccinationScheduleDetail( PatientVaccinationScheduleDetailsBM patVaccSchDetBM) throws HCISException;
	 
	 /**
	  * This method removes patient vaccination schedule if the schedule is in Not Started state
	  * @param patVaccSchDetBM
	  * @throws HCISException
	  */
	 void cancelPatientVaccinationSchedule( Integer seqNbr ) throws HCISException;
	 
	 /**
	  * This method returns Vaccination information of a given vaccination code
	  * @param vaccinationCd
	  * @return
	  * @throws HCISException
	  */
	 VaccinationBM getVaccination(  String vaccinationCd ) throws HCISException;
}
