/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.StatusTransition;
import com.wtc.hcis.da.StatusTransitionDAO;

/**
 * @author Bhavesh
 *
 */
public class StatusTransitionDAOExtn extends StatusTransitionDAO {

	/**
	 * Method returns result matching to given parameter as criteria. input1 and input2 are optional parameter.
	 *  
	 * @param context
	 * @param input1--> Optional
	 * @param input2--> Optional
	 * @param fromStatus
	 * @param toStatus
	 * @return
	 */
	public List<StatusTransition> getStatusTransitionDA( String context, String input1,
													   	 String input2, String fromStatus,
													   	 String toStatus){
		
		DetachedCriteria criteria =  DetachedCriteria.forClass( StatusTransition.class );
		
		criteria.add( Restrictions.eq( StatusTransitionDAOExtn.CONTEXT, context))
				.add( Restrictions.eq( StatusTransitionDAOExtn.FROM_STATUS, fromStatus))
				.add( Restrictions.eq( StatusTransitionDAOExtn.TO_STATUS, toStatus));
		
		if(	input1 != null && !input1.isEmpty() ){
			criteria.add( Restrictions.eq( StatusTransitionDAOExtn.INPUT1, input1));
		}
		if( input2 != null && !input2.isEmpty()  ){
			criteria.add( Restrictions.eq( StatusTransitionDAOExtn.INPUT2, input2));
		}
		
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	public List<String> getToStatusList( String context, String fromStatus){
		
		try{
			String hql = "select toStatus from StatusTransition where context='"+context+"'AND fromStatus='"+fromStatus+"'";
			return getSession().createQuery(hql).list();
		}
		catch (RuntimeException e) {
			throw e;

		}
	}

}
