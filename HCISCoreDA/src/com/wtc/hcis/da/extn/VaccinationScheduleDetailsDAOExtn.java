package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.VaccinationScheduleDetails;
import com.wtc.hcis.da.VaccinationScheduleDetailsDAO;

public class VaccinationScheduleDetailsDAOExtn extends VaccinationScheduleDetailsDAO {

	
	public Integer  getMaxSeqNbr( String scheduleName ) throws Exception{
		try {
			Integer maxSubSeqNbr = 1;
			DetachedCriteria criteria = DetachedCriteria.forClass( VaccinationScheduleDetails.class);
			
			if( null != scheduleName && !scheduleName.isEmpty() ){
				criteria.add( Restrictions.eq("id.scheduleName", scheduleName ));
			}
			criteria.setProjection(Projections.max("id.seqNbr"));
			
			List result = getHibernateTemplate().findByCriteria(criteria);
			
			if( null != result && !result.isEmpty() && null != result.get(0)){
				maxSubSeqNbr = (Integer)result.get( 0 )+1;
			}
			return maxSubSeqNbr;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<VaccinationScheduleDetails> getActiveVaccinationsFor( String scheduleName )throws Exception{
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass( VaccinationScheduleDetails.class);
			
			if( null != scheduleName && !scheduleName.isEmpty() ){
				criteria.add( Restrictions.eq("id.scheduleName", scheduleName ));
				criteria.add( Restrictions.or(Restrictions.eq("deletedFlag", "N" ),Restrictions.isNull("deletedFlag")));
			}
			
			List<VaccinationScheduleDetails> result = getHibernateTemplate().findByCriteria(criteria);
			
			return result;
			
		} catch (Exception e) {
			throw e;
		}
	}
}
