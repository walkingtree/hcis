/**
 * 
 */
package com.wtc.hcis.ip.da.extn;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.Admission;
import com.wtc.hcis.ip.da.AdmissionDAO;

/**
 * @author Alok Ranjan
 *
 */
public class AdmissionDAOExtn extends AdmissionDAO {
	
	private static final Log log = LogFactory.getLog( AdmissionDAOExtn.class );
	
	public static final String ADMISSION_NBR = "admissionNbr";
	public static final String ADMISSION_STATUS_CD = "admissionStatus.admissionStatusCd";
	public static final String ADMISSION_DT = "admissionDt";
	public static final String ADMISSION_ENTRY_POINT_NAME = "admissionEntryPoint.entryPointName";
	public static final String EXPECTED_DISCHARGE_DTM = "expectedDischargeDtm";
	public static final String DISCHARGE_DTM = "dischargeDtm";
	public static final String UNIT_TYPE_CD = "nursingUnitType.unitTypeCd";
	
	/**
	 * An admission is considered active - if there exist an admission
	 * record in following status:
	 * 	1) REQUESTED
	 *  2) ADMITTED
	 *  3) APPROVED
	 *  4) EMERGENCY_REQUEST
	 *  5) PRE_DISCHARGE 
	 *  
	 * @param patientId
	 * @return
	 */
	public List<Admission>getActiveAdmissions( Integer patientId ) {
		
		DetachedCriteria admissionCriteria = DetachedCriteria.forClass( Admission.class );
		String[] activeAdmissionStatus = {"REQUESTED", "ADMITTED", "APPROVED", "EMERGENCY_REQUEST", "PRE_DISCHARGE", "BED_ASSIGNED"};
		admissionCriteria.add( Restrictions.eq( AdmissionDAOExtn.PATIENT_ID , patientId ) );
		admissionCriteria.add( Restrictions.in( AdmissionDAOExtn.ADMISSION_STATUS_CD , activeAdmissionStatus ) );
		admissionCriteria.add(Restrictions.le( AdmissionDAOExtn.ADMISSION_DT, new Date() ) );
		
		List<Admission>admissionsList = getHibernateTemplate().findByCriteria( admissionCriteria );	
		return admissionsList;
	}
	
	/**
	 * All the parameters of this method are optional. 
	 * Following fields also supports partial lookup
	 * 1) entryPointName
	 * 2) admissionReason
	 * 3) agenda
	 * 
	 */
	public List<Admission> getAdmissions (  Integer admissionNbr,
											Integer doctorId, 
								            Integer patientId,
								            String admissionStatusCd, 
								            Date admissionFromDt,
								            Date admissionToDate, 
								            String entryPointName,
								            String admissionReason, 
								            String agenda,
								            Date expectedDischargeFromDate, 
								            Date expectedDischargeToDate,
								            Date dischargeFromDate, 
								            Date dischargeToDate,
								            String nursingUnitTypeCd ) {
		
		DetachedCriteria admissionCriteria = DetachedCriteria.forClass( Admission.class );
		
		if ( admissionNbr != null){
			admissionCriteria.add( Restrictions.eq( AdmissionDAOExtn.ADMISSION_NBR , admissionNbr ) );
			
		}
		if ( doctorId != null ) {
			admissionCriteria.add( Restrictions.eq( AdmissionDAOExtn.DOCTOR_ID , doctorId ) );
		}
		
		if (patientId != null ){
			admissionCriteria.add( Restrictions.eq(AdmissionDAOExtn.PATIENT_ID, patientId));
		}
		
		if (admissionStatusCd != null && admissionStatusCd.length() > 0 ){
			admissionCriteria.createAlias("admissionStatus", "admissionStatus");
			
			admissionCriteria.add( Restrictions.eq(AdmissionDAOExtn.ADMISSION_STATUS_CD, admissionStatusCd));
		}
		
		if ( admissionFromDt != null  ){
			
				admissionCriteria.add(Restrictions.ge(AdmissionDAOExtn.ADMISSION_DT, admissionFromDt));
			}
	    if ( admissionToDate != null ){
			admissionCriteria.add(Restrictions.le(AdmissionDAOExtn.ADMISSION_DT, admissionToDate));
		}
		
		if ( entryPointName != null && !entryPointName.isEmpty()){
			
			admissionCriteria.createAlias("admissionEntryPoint", "admissionEntryPoint");
			admissionCriteria.add(Restrictions.eq(AdmissionDAOExtn.ADMISSION_ENTRY_POINT_NAME, entryPointName));
		}
		
		if ( admissionReason != null && !admissionReason.isEmpty()){
			admissionCriteria.add(Restrictions.ilike(AdmissionDAOExtn.REASON_FOR_ADMISSION, "%" + admissionReason + "%"));
		}
		
		if ( agenda != null && !agenda.isEmpty()){
			admissionCriteria.add(Restrictions.ilike(AdmissionDAOExtn.AGENDA, "%" + agenda + "%"));
		}
		
		if ( expectedDischargeFromDate != null  ){
				admissionCriteria.add(Restrictions.ge(AdmissionDAOExtn.EXPECTED_DISCHARGE_DTM, expectedDischargeFromDate));
		}
		if ( expectedDischargeToDate != null ){
			admissionCriteria.add(Restrictions.le(AdmissionDAOExtn.EXPECTED_DISCHARGE_DTM, expectedDischargeToDate));
		}
		
		if ( dischargeFromDate != null  ){
					admissionCriteria.add(Restrictions.ge(AdmissionDAOExtn.DISCHARGE_DTM, dischargeFromDate));
			}
		if ( dischargeToDate != null ){
			admissionCriteria.add(Restrictions.le(AdmissionDAOExtn.DISCHARGE_DTM, dischargeToDate));
		}
		
		if ( nursingUnitTypeCd != null && !nursingUnitTypeCd.isEmpty()){
			
			admissionCriteria.createAlias("nursingUnitType", "nursingUnitType");
			admissionCriteria.add(Restrictions.eq(AdmissionDAOExtn.UNIT_TYPE_CD, nursingUnitTypeCd));
		}
		
		List<Admission>admissionsList = getHibernateTemplate().findByCriteria( admissionCriteria );	
		return admissionsList;
	}
	
	public Integer getNextAdmissionNbr(){
		try
		{
			String hqlQuery = "select max( admissionNbr ) from Admission ";
			
			Query admissionNbrQuery = getSession().createQuery( hqlQuery );
			
			List<Object> resultList = admissionNbrQuery.list();
			
			Integer nextAvailableNumber = 1;
			
			if ( resultList != null && !resultList.isEmpty() && resultList.get(0) != null) {
				Integer maxOrderNbr = (Integer)resultList.get(0);
				nextAvailableNumber = maxOrderNbr + 1;
			}
			
			return nextAvailableNumber;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve next admission number "  + ex.getMessage() );
			throw ex;
		}
	}
}
