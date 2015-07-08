/**
 * 
 */
package com.wtc.hcis.ip.da.extn;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.BedReservation;
import com.wtc.hcis.ip.da.BedReservationDAO;

/**
 * @author Bhavesh
 *
 */
public class BedReservationDAOExtn extends BedReservationDAO {
	
	public static final String UNIT_TYPE_CD = "nursingUnitType.unitTypeCd";
	public static final String BED_TYPE_CD = "bedType.bedTypeCd";
	public static final String BED_NUMBER = "bedMaster.bedNumber";
	public static final String RESERVAION_STATUS_CD = "reservationStatus.reservationStatusCd";
	public static final String ADMISSION_REQ_NBR = "admission.admissionReqNbr";
	public static final String RESERVATION_FROM_DT = "reservationFromDtm";
	public static final String RESERVATION_TO_DT = "reservationToDtm";

	public List<BedReservation>  getBedReservationDetails( String unitTypeCode,
														   String reservationStatusCd,
														   Date   reservationFromDt,
														   Date   reservationToDt ) {
		//
		// This method is assuming that reservation from date and reservation to date will not be null
		//
		Calendar today = Calendar.getInstance();
		
		DetachedCriteria reservationCriteria = DetachedCriteria.forClass( BedReservation.class );
		reservationCriteria.add( Restrictions.eq( UNIT_TYPE_CD, unitTypeCode ))
		                   .add( Restrictions.eq( RESERVAION_STATUS_CD, reservationStatusCd ) )
		                   .add( Restrictions.not( 
		                		   Restrictions.or( 
		                				   Restrictions.and( 
		                						   Restrictions.ge(BedReservationDAOExtn.RESERVATION_TO_DT , today.getTime() ),
		                						   Restrictions.lt(BedReservationDAOExtn.RESERVATION_TO_DT , reservationFromDt ) ), 
		                				   Restrictions.and( 
		                						   Restrictions.ge( BedReservationDAOExtn.RESERVATION_FROM_DT, today.getTime() ),
		                						   Restrictions.gt( BedReservationDAOExtn.RESERVATION_FROM_DT, reservationToDt ) ) ) ) ) ;
		                		   
		List<BedReservation> bedReservationList = getHibernateTemplate().findByCriteria( reservationCriteria );
		
		return bedReservationList;
	}
	
	public List<BedReservation> getBedReservationDetails(  Integer bedReservationNbr,
														   String unitTypeCode,
														   String bedTypeCode,
														   String bedNumber,
														   String reservationStatusCd,
														   Integer admissionReqNbr,
														   Date   reservationFromDt,
														   Date   reservationToDt ){
		DetachedCriteria criteria = DetachedCriteria.forClass( BedReservation.class );
		
		
		if( bedReservationNbr != null ){
			criteria.add(Restrictions.eq( "bedReservationNbr", bedReservationNbr ));
		}
		if( unitTypeCode != null && !unitTypeCode.isEmpty() ){
			criteria.add(Restrictions.eq( UNIT_TYPE_CD, unitTypeCode ));
		}
		if( bedTypeCode != null && !bedTypeCode.isEmpty() ){
			criteria.add(Restrictions.eq( BED_TYPE_CD, bedTypeCode ));
		}
		if( bedNumber != null && !bedNumber.isEmpty() ){
			criteria.add(Restrictions.eq( BED_NUMBER, bedNumber ));
		}
		if( reservationStatusCd != null && !reservationStatusCd.isEmpty() ){
			criteria.add(Restrictions.eq( RESERVAION_STATUS_CD, reservationStatusCd ));
		}
		if( admissionReqNbr != null ){
			criteria.add(Restrictions.eq( ADMISSION_REQ_NBR , admissionReqNbr ));
		}
		if( reservationFromDt != null ){
			criteria.add(Restrictions.ge( "reservationFromDtm", reservationFromDt ));
		}
		if( reservationToDt != null ){
			criteria.add(Restrictions.le( "reservationToDtm", reservationToDt ));
		}
		
		List<BedReservation> bedReservationList = getHibernateTemplate().findByCriteria(criteria);
		
		return bedReservationList;
	}
}
