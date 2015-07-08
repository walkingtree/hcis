/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.VaccinationSchedule;
import com.wtc.hcis.da.VaccinationScheduleDAO;

/**
 * @author Bhavesh
 *
 */
public class VaccinationScheduleDAOExtn extends VaccinationScheduleDAO {

	public static final String  VACCINATION_TYPE_CODE = "vaccinationScheduleDetailses.vaccinationTypeCd";
	public static final String  SCHEDULE_NAME = "scheduleName";
	public static final String  VACCINATION_CODE = "vaccinationScheduleDetailses.vacciantionCd";
	public static final String  ACTIVE_FLAG ="activeFlag";
	public static final String  AGE ="age";
	
	public List<VaccinationSchedule> getVaccinationSchedule( String scheduleName,
															 String description,
															 String vacciantionCd,
															 String ageGroup,
															 String age,
															 String activeFlag){
		
		List<VaccinationSchedule> vaccinationScheduleList = null; 
		
		DetachedCriteria criteria = DetachedCriteria.forClass( VaccinationSchedule.class );
		
		boolean vaccinationJoin = false;
		
		if( age!= null && age.length() > 0 ){
			criteria.createAlias("vaccinationScheduleDetailses", "vaccinationScheduleDetailses");
			vaccinationJoin = true;
			
			criteria.add( Restrictions.ilike(VaccinationScheduleDAOExtn.AGE,"%"+ age + "%"));
		}
		
		if( scheduleName!= null && scheduleName.length() > 0 ){
			criteria.add( Restrictions.ilike(VaccinationScheduleDAOExtn.SCHEDULE_NAME, "%" + scheduleName + "%"));
		}
		
		if( description!= null && description.length() > 0 ){
			criteria.add( Restrictions.ilike(VaccinationScheduleDAOExtn.SCHEDULE_DESC, "%" + description + "%" ));
		}
		
		if( vacciantionCd!= null && vacciantionCd.length() > 0 ){
			if( !vaccinationJoin ){
				criteria.createAlias("vaccinationScheduleDetailses", "vaccinationScheduleDetailses");
			}
			criteria.add( Restrictions.eq(VaccinationScheduleDAOExtn.VACCINATION_CODE, vacciantionCd));
		}
		
		if( ageGroup!= null && ageGroup.length() > 0 ){
			criteria.add( Restrictions.ilike(VaccinationScheduleDAOExtn.AGE_GROUP,"%"+ ageGroup + "%"));
		}
		
		if( activeFlag!= null && activeFlag.length() > 0 ){
			criteria.add( Restrictions.ilike(VaccinationScheduleDAOExtn.ACTIVE_FLAG, "%" + activeFlag + "%"));
		}
		
	 vaccinationScheduleList = getHibernateTemplate().findByCriteria(criteria);
	 return vaccinationScheduleList;
	}
}
