/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.Appointments;
import com.wtc.hcis.da.AppointmentsDAO;

/**
 * @author Kamal
 *
 */
public class AppointmentsDAOExtn extends AppointmentsDAO  {
	
	private static final Log log = LogFactory.getLog(AppointmentsDAOExtn.class);
	
	public static final String APPOINTMENT_NUMBER = "appointmentNumber";
	public static final String ROSTER_CODE = "roster.rosterCode";
	public static final String APPOINTMENT_DATE = "appointmentDate";
	public static final String APPOINTMENT_START_TIME = "apptStartTime";
	public static final String APPOINTMENT_END_TIME = "apptEndTime";
	public static final String PATIENT_FULL_NAME = "patient.fullName";
	public static final String DOCTOR_FULL_NAME = "doctor.fullName";
	public static final String PATIENT_ID = "patient.patientId";
	public static final String APPOINTMENT_STATUS_CODE = "appointmentStatus.statusCode";
	public static final String BOOKING_TYPE_ID = "bookingType.bookingTypeCode";
	public static final String CANCELED_APPOINTMENT = "CANCELLED";
	public static final String RESCHEDULED_APPOINTMENT = "RESCHEDULED";
	public static final String CAPTURED_DTM = "capturedDtm";
	public static final String REFERRAL_CODE = "referral.referralCode";
	public static final String APPOINTMENT_TYPE_CODE_PRIMARY = "PRIMARY";
	public static final String APPOINTMENT_STATUS_CODE_CAPTURED = "CAPTURED";
	public static final String DOCTOR_ID = "doctor.doctorId";
	
	public List<Appointments> findAppointments( Date appointmentFromDate,
												Date appointmentToDate, 
												Integer patientId, 
												String patientName,
												String doctorName, 
												String appointmentStatus,
												String bookingType, 
												String timeFrom, 
												String timeTo ) {
		
		DetachedCriteria appointmentsCriteria = DetachedCriteria.forClass( Appointments.class );
		
		
		if ( patientId != null ) {
			appointmentsCriteria.add( Restrictions.eq( AppointmentsDAOExtn.PATIENT_ID, patientId ) );
		}
		
		if ( appointmentFromDate != null  ) {
			appointmentsCriteria.add( Restrictions.ge( AppointmentsDAOExtn.APPOINTMENT_DATE, appointmentFromDate ) );
		}
		
		if ( appointmentToDate != null  ) {
			appointmentsCriteria.add( Restrictions.le( AppointmentsDAOExtn.APPOINTMENT_DATE, appointmentToDate ) );
		}
		
		if ( patientName != null && patientName.length() > 0 ) {
			appointmentsCriteria.createAlias("patient", "patient" );
			appointmentsCriteria.add( Restrictions.ilike( AppointmentsDAOExtn.PATIENT_FULL_NAME, "%" + patientName + "%" ) );
		}
		
		if ( doctorName != null && doctorName.length() > 0 ) {
			
			appointmentsCriteria.createAlias("doctor", "doctor" );
			
				appointmentsCriteria.add(  Restrictions.ilike( AppointmentsDAOExtn.DOCTOR_FULL_NAME, "%" + doctorName + "%") );
		}
		
		if ( appointmentStatus != null && appointmentStatus.length()>0) {
			appointmentsCriteria.add( Restrictions.eq( AppointmentsDAOExtn.APPOINTMENT_STATUS_CODE, appointmentStatus ) );
		}
		
		if ( bookingType != null && bookingType.length()>0 ) {
			appointmentsCriteria.add( Restrictions.eq( AppointmentsDAOExtn.BOOKING_TYPE_ID, bookingType ) );
		}
		
		if ( timeFrom != null && timeFrom.length() > 0 ) {
			appointmentsCriteria.add( Restrictions.ge(AppointmentsDAOExtn.APPOINTMENT_START_TIME, timeFrom ) );
		}
		
		if ( timeTo != null && timeTo.length() > 0 ) {
			appointmentsCriteria.add( Restrictions.le(AppointmentsDAOExtn.APPOINTMENT_END_TIME, timeTo ) );
		}
		
		List<Appointments>appointmentList = getHibernateTemplate().findByCriteria( appointmentsCriteria );	
		
		if ( appointmentList != null && !appointmentList.isEmpty() ) {
			return appointmentList;
		} else {
			return null;
		}
	}
	
	
	public Map<String, Appointments> getBookedAppointments( Integer rosterCode, 
											   Date appointmentDate,
											   String appointmentStartTime,
											   String appointmentEndTime ) {
		
		DetachedCriteria appointmentsCriteria = DetachedCriteria.forClass( Appointments.class );
		
		if ( rosterCode != null ) {
			appointmentsCriteria.add( Restrictions.eq( AppointmentsDAOExtn.ROSTER_CODE, rosterCode ) );
		}
		
		if ( appointmentDate != null ) {
			appointmentsCriteria.add( Restrictions.eq( AppointmentsDAOExtn.APPOINTMENT_DATE, appointmentDate ) );
		}
		
		if ( appointmentStartTime != null && appointmentEndTime.length() > 0 ) {
			appointmentsCriteria.add( Restrictions.ge( AppointmentsDAOExtn.APPOINTMENT_START_TIME, appointmentStartTime ) );
		}
		
		if ( appointmentEndTime != null && appointmentEndTime.length() > 0 ) {
			appointmentsCriteria.add( Restrictions.le( AppointmentsDAOExtn.APPOINTMENT_END_TIME, appointmentEndTime ) );
		}
		
		appointmentsCriteria.add(Restrictions.and( Restrictions.ne( AppointmentsDAOExtn.APPOINTMENT_STATUS_CODE, AppointmentsDAOExtn.RESCHEDULED_APPOINTMENT ) , 
				                                   Restrictions.ne( AppointmentsDAOExtn.APPOINTMENT_STATUS_CODE, AppointmentsDAOExtn.CANCELED_APPOINTMENT ) ) );
		
		appointmentsCriteria.addOrder(Order.asc( AppointmentsDAOExtn.APPOINTMENT_START_TIME ));
		
		List<Appointments>existingAppointmentList = getHibernateTemplate().findByCriteria( appointmentsCriteria );	
		
		if ( existingAppointmentList != null && !existingAppointmentList.isEmpty() ) {
			Map<String, Appointments>appointmentsMap = new HashMap<String, Appointments>(); 
			
			for ( Appointments appointments : existingAppointmentList ) {
				appointmentsMap.put( appointments.getApptStartTime(), appointments );
			}
			
			return appointmentsMap;
		} else {
			return null;
		}
	}
	
	public List<Appointments> getBookedAppointments( Integer rosterCode ) {

		DetachedCriteria appointmentsCriteria = DetachedCriteria.forClass( Appointments.class );
		
		if ( rosterCode != null ) {
			appointmentsCriteria.add( Restrictions.eq( AppointmentsDAOExtn.ROSTER_CODE, rosterCode ) );
		}
		
		appointmentsCriteria.add(Restrictions.and( Restrictions.ne( AppointmentsDAOExtn.APPOINTMENT_STATUS_CODE, AppointmentsDAOExtn.RESCHEDULED_APPOINTMENT ) , 
		                Restrictions.ne( AppointmentsDAOExtn.APPOINTMENT_STATUS_CODE, AppointmentsDAOExtn.CANCELED_APPOINTMENT ) ) );
		
		appointmentsCriteria.addOrder(Order.asc( AppointmentsDAOExtn.APPOINTMENT_START_TIME ));
		
		List<Appointments> existingAppointmentList = getHibernateTemplate().findByCriteria( appointmentsCriteria );	
		
		if ( existingAppointmentList != null && !existingAppointmentList.isEmpty() ) {
		
			return existingAppointmentList;
		} else {
			return null;
		}
	}
	
	public Boolean checkPatientHasAppointment(String appointmentNumber, String patientId) {
		
		DetachedCriteria appointmentsCriteria = DetachedCriteria.forClass(Appointments.class);
		
		if( appointmentNumber != null ) {
			appointmentsCriteria.add(Restrictions.eq(AppointmentsDAOExtn.APPOINTMENT_NUMBER, new Integer(appointmentNumber)));
		}
		
		if( patientId != null ) {
			appointmentsCriteria.add(Restrictions.eq(AppointmentsDAOExtn.PATIENT_ID, new Integer(patientId)));
		}
		List<Appointments> patientHasAppointmentList = getHibernateTemplate().findByCriteria(appointmentsCriteria);
		
		if(patientHasAppointmentList!= null && patientHasAppointmentList.size()>0) {
		
			return Boolean.TRUE;
		} else {
			
			return Boolean.FALSE;
		}
			
	}
	
	public List<Appointments> getAppointmentsForReferral( Integer referralCode, Date lastProcessedDate ) {
		
		DetachedCriteria appointmentsCriteria = DetachedCriteria.forClass( Appointments.class );
		List<Appointments>appointmentsList = null;
		
		appointmentsCriteria.add( Restrictions.isNotNull( AppointmentsDAOExtn.REFERRAL_CODE ) )
		                    .add( Restrictions.eq( AppointmentsDAOExtn.REFERRAL_CODE, referralCode ) )
		                    .add( Restrictions.gt( AppointmentsDAOExtn.CAPTURED_DTM, lastProcessedDate ) )
		                    .addOrder( Order.asc( AppointmentsDAOExtn.CAPTURED_DTM ) );
		
		
		appointmentsList = getHibernateTemplate().findByCriteria( appointmentsCriteria );
		
		return appointmentsList;
	}
	
	public List<Appointments> getFollowUpEligibleAppointments(Integer patientId,Integer doctorId) {
		
		DetachedCriteria appointmentsCriteria = DetachedCriteria.forClass( Appointments.class );
		List<Appointments>appointmentsList = null;
		
		appointmentsCriteria.add( Restrictions.eq( AppointmentsDAOExtn.PATIENT_ID, patientId) )
							.add( Restrictions.eq( AppointmentsDAOExtn.DOCTOR_ID, doctorId) )
							.add( Restrictions.eq( AppointmentsDAOExtn.APPOINTMENT_STATUS_CODE,AppointmentsDAOExtn.APPOINTMENT_STATUS_CODE_CAPTURED) )
		                    .add( Restrictions.eq( AppointmentsDAOExtn.APPOINTMENT_TYPE_CODE, AppointmentsDAOExtn.APPOINTMENT_TYPE_CODE_PRIMARY) )
		                    .addOrder( Order.asc( AppointmentsDAOExtn.CAPTURED_DTM ) );
		
		
		appointmentsList = getHibernateTemplate().findByCriteria( appointmentsCriteria );
		
		return appointmentsList;
	}
	
	public Integer getFollowUpCountsOfAppointment(Integer appointmentNumber) {
		String hqlQuery = "select count( primaryAppointmentNumber ) from Appointments where  primaryAppointmentNumber="+appointmentNumber;
		Query serviceOrderNumberQuery = getSession().createQuery( hqlQuery );
		
		List<Long> resultList = serviceOrderNumberQuery.list();
		Integer count = 0 ;
		
		if( resultList != null && !resultList.isEmpty() && resultList.get(0) != null){
			count = resultList.get(0).intValue();
		}
		
		return count;
	}
}
