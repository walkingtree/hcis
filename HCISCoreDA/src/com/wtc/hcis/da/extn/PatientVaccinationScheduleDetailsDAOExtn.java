package com.wtc.hcis.da.extn;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.PatientVaccinationScheduleDetails;
import com.wtc.hcis.da.PatientVaccinationScheduleDetailsDAO;

public class PatientVaccinationScheduleDetailsDAOExtn extends
		PatientVaccinationScheduleDetailsDAO {
	private static String  SCHEDULE_NAME ="id.scheduleName";
	private static String  PERIOD_IN_DAYS ="periodInDays";
	private static String  VACCINATION_CODE ="vaccination.vaccinationCd";
	
	 public List<PatientVaccinationScheduleDetails> getPatientVaccinationScheduleDetails( String ScheduleName,
																				  String VaccinationCode,
																				  Integer periodIndays ) throws Exception{
		List<PatientVaccinationScheduleDetails> patVacScheDetlsList = new ArrayList<PatientVaccinationScheduleDetails>(0);
		try {
			
			DetachedCriteria criteria = DetachedCriteria.forClass( PatientVaccinationScheduleDetails.class );
			if( null != ScheduleName && ScheduleName.length() > 0 ){
				criteria.add( Restrictions.eq( PatientVaccinationScheduleDetailsDAOExtn.SCHEDULE_NAME, ScheduleName));
			}
			
			if( null != periodIndays ){
				criteria.add( Restrictions.eq(PatientVaccinationScheduleDetailsDAOExtn.PERIOD_IN_DAYS, periodIndays));
			}
			
			if( null != VaccinationCode && VaccinationCode.length() > 0 ){
				criteria.add( Restrictions.eq(PatientVaccinationScheduleDetailsDAOExtn.VACCINATION_CODE, VaccinationCode));
			}
			
			patVacScheDetlsList = getHibernateTemplate().findByCriteria(criteria);
			
			return patVacScheDetlsList;
		} catch (Exception exception ) {
			throw exception;
		}
		
	}
	 public Integer  getMaxSubSeqNbr( Integer seqNbr, Integer patientId ) throws Exception{
		try {
			Integer maxSubSeqNbr = 1;
			DetachedCriteria criteria = DetachedCriteria.forClass( PatientVaccinationScheduleDetails.class);
			
			criteria.add( Restrictions.eq("id.seqNbr", seqNbr));
			criteria.add( Restrictions.eq("id.patientId", patientId));
			
			criteria.setProjection(Projections.max("id.subSeqNbr"));
			List result = getHibernateTemplate().findByCriteria(criteria);
			
			if( null != result && !result.isEmpty() && null != result.get(0)){
				maxSubSeqNbr = (Integer)result.get( 0 )+1;
			}
			return maxSubSeqNbr;
		} catch (Exception e) {
			throw e;
		}
	}
}
