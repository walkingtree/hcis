/**
 * 
 */
package in.wtc.hcis.bo.common;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import in.wtc.hcis.exceptions.HCISException;

import com.wtc.hcis.da.AssignedServiceStatus;
import com.wtc.hcis.da.AssignedServices;
import com.wtc.hcis.da.DateDim;
import com.wtc.hcis.da.DocCheckList;
import com.wtc.hcis.da.Especialty;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.Referral;
import com.wtc.hcis.da.Vaccination;
import com.wtc.hcis.da.VaccinationSchedule;

/**
 * @author Bhavesh
 */
 /**This manager proved all the DA level model to the other managers.
 *Intended to put the all commonly used method for getting datamodels
 *from DA layer.
 *
 */
public interface CommonDataManager {

	/**
	 * 
	 * @param referralCode
	 * @return
	 * @throws HCISException
	 */
	Referral getReferral( Integer referralCode) throws HCISException;
	/**
	 * 
	 * @param context
	 * @param attrCode
	 * @return
	 * @throws HCISException
	 */
	ReferenceDataList getReferenceData(String context, String attrCode) throws HCISException;
	/**
	 * 
	 * @param vaccinationCd
	 * @return
	 * @throws HCISException
	 */
	Vaccination getVaccination(  String vaccinationCd ) throws HCISException;
	/**
	 * 
	 * @param especialityCode
	 * @return
	 * @throws HCISException
	 */
	Especialty getEspeciality( String especialityCode ) throws HCISException;
	/**
	 * 
	 * @param vaccinationScheduleCode
	 * @return
	 * @throws HCISException
	 */
	VaccinationSchedule getVaccinationSchedule( String vaccinationScheduleCode ) throws HCISException;
	/**
	 * 
	 * @param serviceUid
	 * @return
	 * @throws HCISException
	 */
	AssignedServices getAssignedServices( Integer serviceUid ) throws HCISException;
	/**
	 * 
	 * @param date
	 * @return
	 * @throws HCISException
	 */
	DateDim getDateDim( Date date )throws HCISException;
	/**
	 * 
	 * @param dateDimId
	 * @return
	 * @throws HCISException
	 */
	DateDim getDateDim( Integer dateDimId)throws HCISException;
	
	/**
	 * Validate the transition for given context and inputs if any.
	 * (Need to find some better place(Manager) for this method )
	 * @param context
	 * @param input1--> Optional
	 * @param input2--> Optional
	 * @param fromStatus
	 * @param toStatus
	 * @return
	 * @throws HCISException
	 */
	boolean isValidTransition(final String context, final String input1,
							  final String input2,final String fromStatus,
							  final String toStatus ) throws HCISException;
	/**
	 * 
	 * @param assignedServiceStatusCode
	 * @return
	 * @throws HCISException
	 */
	
	List<String> getToStatusList( final String context,final String fromStatus);
	
	AssignedServiceStatus getAssignedServiceStatus( String assignedServiceStatusCode ) throws HCISException;
	
	DocCheckList getCheckList( Integer checkListId) throws HCISException;
}
