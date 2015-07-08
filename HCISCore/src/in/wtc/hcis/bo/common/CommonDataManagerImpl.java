/**
 * 
 */
package in.wtc.hcis.bo.common;



import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.wtc.hcis.da.AssignedServiceStatus;
import com.wtc.hcis.da.AssignedServiceStatusDAO;
import com.wtc.hcis.da.AssignedServices;
import com.wtc.hcis.da.AssignedServicesDAO;
import com.wtc.hcis.da.DateDim;
import com.wtc.hcis.da.DocCheckList;
import com.wtc.hcis.da.Entity;
import com.wtc.hcis.da.EntityDAO;
import com.wtc.hcis.da.Especialty;
import com.wtc.hcis.da.EspecialtyDAO;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.ReferenceDataListDAO;
import com.wtc.hcis.da.ReferenceDataListId;
import com.wtc.hcis.da.Referral;
import com.wtc.hcis.da.StatusTransition;
import com.wtc.hcis.da.Vaccination;
import com.wtc.hcis.da.VaccinationDAO;
import com.wtc.hcis.da.VaccinationSchedule;
import com.wtc.hcis.da.VaccinationScheduleDAO;
import com.wtc.hcis.da.extn.DateDimDAOExtn;
import com.wtc.hcis.da.extn.DocCheckListDAOExtn;
import com.wtc.hcis.da.extn.ReferralDAOExtn;
import com.wtc.hcis.da.extn.StatusTransitionDAOExtn;

/**
 * @author Bhavesh
 *
 */
public class CommonDataManagerImpl implements CommonDataManager {

	private ReferralDAOExtn referralDAO;
	private ReferenceDataListDAO referenceDataListDAO;
	private VaccinationDAO vaccinationDAO;
	private EspecialtyDAO especialtyDAO;
	private VaccinationScheduleDAO vaccinationScheduleDAO;
	private AssignedServicesDAO assignedServicesDAO;
	private StatusTransitionDAOExtn statusTransitionDAO;
	private AssignedServiceStatusDAO assignedServiceStatusDAO;
	
	private DateDimDAOExtn dateDimDAO;
	private EntityDAO entityDAO;
	private DocCheckListDAOExtn docCheckListDAO;
	
	public Referral getReferral( Integer referralCode) {
		
		try {
			Referral referral = referralDAO.findById( referralCode );
			
			if ( referral == null ) {
				throw new Exception( " There is no referral configured for REFERRAL_CODE = " + referralCode );
			}
			
			return referral;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.REFERRAL_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	public ReferenceDataList getReferenceData(String context, String attrCode)throws HCISException{
		try {
			ReferenceDataListId id = new ReferenceDataListId();
			id.setContext(context);
			id.setAttrCode(attrCode);
			
			ReferenceDataList referenceData = referenceDataListDAO.findById(id);
			if( null == referenceData ){
				throw new Exception( "Reference data with " + attrCode + " does not exist");
			}
			return referenceData;
		} catch (Exception exception) {
			Fault fault = new Fault( ApplicationErrors.REFERENCE_DATA_READ_FAILED);
			throw new HCISException( fault, exception);
		}
	}
	
	public Vaccination getVaccination(  String vaccinationCd ) throws HCISException{
		try {
			Vaccination vaccination = null;
			if( ! vaccinationCd.isEmpty() ){
				vaccination = vaccinationDAO.findById( vaccinationCd );
				
				if( vaccination == null ){
					throw new Exception( "Vaccination with code " + vaccinationCd + " does not exist");
				}
			}
			
			return vaccination;
		} catch (Exception exception) {
			Fault fault = new Fault( ApplicationErrors.VACCINATION_READ_FAILED);
			throw new HCISException( fault, exception);
		}
	}

	public Especialty getEspeciality( String especialityCode ) throws HCISException {
		try {
			Especialty espeaciality = null;
			if( !especialityCode.isEmpty() ){
				espeaciality = especialtyDAO.findById( especialityCode );
			}else{
				throw new Exception("speciality with code" + especialityCode + "does not exist");
			}
			return espeaciality;
		} catch (Exception exception) {
			Fault fault = new Fault( ApplicationErrors.SPECIALITY_READ_FAILED);
			throw new HCISException( fault, exception );
		}
	}
	
	public VaccinationSchedule getVaccinationSchedule( String vaccinationScheduleCode ) throws HCISException{
		try {
			VaccinationSchedule vaccinationSchedule = null;
			if( !vaccinationScheduleCode.isEmpty() ){
				vaccinationSchedule = vaccinationScheduleDAO.findById(vaccinationScheduleCode);
			}else{
				throw new Exception("vaccination schedule with code" + vaccinationScheduleCode + "does not exist");
			}
			return vaccinationSchedule;
		} catch (Exception exception) {
			Fault fault = new Fault( ApplicationErrors.VACCINATION_SCHEDULE_READ_FAILED);
			throw new HCISException( fault, exception );
		}
	}
	
	
	public AssignedServices getAssignedServices( Integer serviceUid ) {
		try {
			AssignedServices assignedServices = assignedServicesDAO.findById( serviceUid );
			
			if ( assignedServices == null ) {
				throw new Exception(" Assigned services with SERVICE_UID = " + serviceUid + " does not exist" );
			}
			
			return assignedServices;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.ASSIGNED_SERVICE_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	public DateDim getDateDim( Date date){
		
		if (date != null ) {
			try {

				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				
				String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
				if (month.length() == 1) {
					month = 0 + month;
				}

				String dateValue = String.valueOf(cal.get(Calendar.DATE));

				if (dateValue.length() == 1) {
					dateValue = 0 + dateValue;
				}

				String yearMonthDate = String.valueOf(cal.get(Calendar.YEAR))
						+ month + dateValue;

				DateDim dateDim = dateDimDAO.getDateDimForDate(Integer
						.parseInt(yearMonthDate));

				return dateDim;
			} catch (Exception e) {

				Fault fault = new Fault(ApplicationErrors.DATE_DIM_READ_FAILED);
				throw new HCISException(fault, e);
			}
			
		}
		return null;
		
	}
	
	public DateDim getDateDim( Integer dateDimId){
		
		try {
			
			DateDim dateDim = dateDimDAO.findById(dateDimId);
			
			if( dateDim == null ){
				 throw new Exception("Date Dim with id -"+ dateDimId +" does not exists.");
			}
			return dateDim;
		} catch ( Exception e) {
			
			Fault fault = new Fault( ApplicationErrors.DATE_DIM_READ_FAILED);
			throw new HCISException( fault, e );
		}
		
	}
	
	public AssignedServiceStatus getAssignedServiceStatus( String assignedServiceStatusCode ) {
		try {
			AssignedServiceStatus assignedServiceStatus = assignedServiceStatusDAO.findById( assignedServiceStatusCode );
			
			if ( assignedServiceStatus == null ) {
				throw new Exception(" Assigned service status with STATUS_CODE = " + assignedServiceStatusCode + " does not exist ") ;
			}
			
			return assignedServiceStatus;
		} catch (Exception e) {
			Fault fault = new Fault( in.wtc.hcis.bo.constants.ApplicationErrors.ASSIGNED_SERVICE_STATUS_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}

	/*
	 * Validate the transition for given context and inputs if any.
	 */
	public boolean isValidTransition(final String context, final String input1,
			final String input2,final String fromStatus,final String toStatus ){
		

		List<StatusTransition> statusTransitionList = statusTransitionDAO.getStatusTransitionDA(context, input1,
														input2, fromStatus, toStatus);

		if( WtcUtils.isValid(statusTransitionList)){
		
			return true;
		}
		
		return false;
	}
	
	
	public Entity getEntity( Integer entityId){
		
		try {
			
			Entity entity = entityDAO.findById(entityId);
			
			if( entity == null ){
				 throw new Exception("Entity with id -"+ entity +" does not exists.");
			}
			return entity;
		} catch ( Exception e) {
			
			Fault fault = new Fault( ApplicationErrors.DATE_DIM_READ_FAILED);
			throw new HCISException( fault, e );
		}
		
	}

	public DocCheckList getCheckList( Integer checkListId){
		
		try {
			
			DocCheckList checkList = docCheckListDAO.findById( checkListId );
			
			if( checkList == null ){
				 throw new Exception("Check-List  with id -"+ checkListId +" does not exists.");
			}
			return checkList;
		} catch ( Exception e) {
			
			Fault fault = new Fault( ApplicationErrors.CHECK_LIST_ADD_FAILED);
			throw new HCISException( fault, e );
		}
	}
	
	public List<String> getToStatusList( final String context,final String fromStatus){
		try{
			List<String> toStatusList = statusTransitionDAO.getToStatusList(context, fromStatus);
			return toStatusList;
		}
		catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.TRANSITION_STATUS_READ_FAILED);
			throw new HCISException( fault, e );
		}
	}

	public void setReferralDAO(ReferralDAOExtn referralDAO) {
		this.referralDAO = referralDAO;
	}

	public void setReferenceDataListDAO(ReferenceDataListDAO referenceDataListDAO) {
		this.referenceDataListDAO = referenceDataListDAO;
	}

	public void setVaccinationDAO(VaccinationDAO vaccinationDAO) {
		this.vaccinationDAO = vaccinationDAO;
	}

	public void setEspecialtyDAO(EspecialtyDAO especialtyDAO) {
		this.especialtyDAO = especialtyDAO;
	}

	public void setVaccinationScheduleDAO(VaccinationScheduleDAO vaccinationScheduleDAO) {
		this.vaccinationScheduleDAO = vaccinationScheduleDAO;
	}

	public void setAssignedServicesDAO(AssignedServicesDAO assignedServicesDAO) {
		this.assignedServicesDAO = assignedServicesDAO;
	}

	public void setDateDimDAO(DateDimDAOExtn dateDimDAO) {
		this.dateDimDAO = dateDimDAO;
	}

	public void setStatusTransitionDAO(StatusTransitionDAOExtn statusTransitionDAO) {
		this.statusTransitionDAO = statusTransitionDAO;
	}

	public void setAssignedServiceStatusDAO(
			AssignedServiceStatusDAO assignedServiceStatusDAO) {
		this.assignedServiceStatusDAO = assignedServiceStatusDAO;
	}

	public void setEntityDAO(EntityDAO entityDAO) {
		this.entityDAO = entityDAO;
	}

	public void setDocCheckListDAO(DocCheckListDAOExtn docCheckListDAO) {
		this.docCheckListDAO = docCheckListDAO;
	}

	
}
