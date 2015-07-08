/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.Roster;
import com.wtc.hcis.da.RosterDAO;

/**
 * @author Kamal Rathi
 *
 */
public class RosterDAOExtn extends RosterDAO {
	
	private static final String START_TIME = "startTime";
	private static final String END_TIME = "endTime";
	private static final String ROSTER_DATE = "workingDate";
	private static final String ROOM_NUMBER = "room.roomCode";
	private static final String ACTIVE = "active";
	
	/**
	 * 
	 * @param entityType
	 * @param entityId
	 * @param workingDate
	 * @param startTime
	 * @param endTime
	 * @return It returns the conflicted rosters for given entity, date, and time period.
	 */
	@SuppressWarnings("unchecked")
	public List<Roster> getConflictedRosters( String entityType, Integer entityId , Date workingDate , String startTime , String endTime, String activeFlag )
	{
		List<Roster> rosters = new ArrayList<Roster>();
		DetachedCriteria  criteria = DetachedCriteria.forClass(Roster.class);
		if( entityType != null ) {
			criteria.add( Restrictions.eq( RosterDAOExtn.ENTITY_TYPE, entityType ) );
		}
		if( entityId != null ) {
			criteria.add(Restrictions.eq( RosterDAOExtn.ENTITY_ID , entityId));
		}
		if( workingDate != null ) {
			criteria.add(Restrictions.eq( RosterDAOExtn.ROSTER_DATE, workingDate));
		}
		if( startTime != null ) {
			if( endTime != null){
				criteria.add(Restrictions.or(
					Restrictions.or(
							Restrictions.and(
								Restrictions.gt(RosterDAOExtn.START_TIME, startTime),
									Restrictions.lt(RosterDAOExtn.END_TIME, endTime)), 
										Restrictions.and(Restrictions.le(RosterDAOExtn.START_TIME, startTime),
											Restrictions.ge(RosterDAOExtn.END_TIME, startTime))), 
												Restrictions.and(Restrictions.le(RosterDAOExtn.START_TIME, endTime),
													Restrictions.ge(RosterDAOExtn.END_TIME, endTime))));
			}
			else{
				criteria.add( Restrictions.and( Restrictions.ge(RosterDAOExtn.START_TIME, startTime),
						Restrictions.le(RosterDAOExtn.END_TIME, startTime)));
			}
		}else if( endTime != null ) {
			criteria.add( Restrictions.and( Restrictions.ge(RosterDAOExtn.START_TIME, endTime),
				Restrictions.le(RosterDAOExtn.END_TIME, endTime)));
		}
		if(activeFlag !=null){
			criteria.add(Restrictions.eq(RosterDAOExtn.ACTIVE, activeFlag));
		}
		rosters = getHibernateTemplate().findByCriteria(criteria);
		return rosters;
	}
	
	public List<Roster>getRosters ( String entityType, 
						            Integer entityId, 
						            Date fromDate, 
						            Date toDate,
						            String startTime, 
						            String endTime,
						            String roomNo,
						            String status) 
	{
		
		List<Roster> rosters = null;
		DetachedCriteria  criteria = DetachedCriteria.forClass(Roster.class);
		
		if ( entityType != null && entityType.length() > 0 ) {
			criteria.add( Restrictions.eq( RosterDAOExtn.ENTITY_TYPE , entityType ) );
		}
		if ( entityId != null ) {
			criteria.add( Restrictions.eq( RosterDAOExtn.ENTITY_ID , entityId) );
		}
		if ( fromDate != null ) {
			criteria.add( Restrictions.ge( RosterDAOExtn.ROSTER_DATE , fromDate) );
		}
		if ( toDate != null ) {
			criteria.add( Restrictions.le( RosterDAOExtn.ROSTER_DATE , toDate) );
		}
		if ( startTime != null && !startTime.equals("")) {
			criteria.add( Restrictions.ge( RosterDAOExtn.START_TIME , startTime ) );
		}
		if ( endTime != null  && !endTime.equals("")) {
			criteria.add( Restrictions.le( RosterDAOExtn.END_TIME , endTime ) );
		}
		if(roomNo!=null && !roomNo.equals("")) {
			criteria.add(Restrictions.eq(RosterDAOExtn.ROOM_NUMBER, roomNo));
		}
		if(status!=null && !status.equals("")) {
			status = status.equals("1")? "Y" : "N";
			criteria.add(Restrictions.eq(RosterDAOExtn.ACTIVE, status));
		}
		rosters = getHibernateTemplate().findByCriteria(criteria);
		
		return rosters;
	}
}
