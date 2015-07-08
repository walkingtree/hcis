/**
 * 
 */
package in.wtc.hcis.test.unit;

import in.wtc.hcis.bm.base.AppointmentBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bo.appointment.AppointmentManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

/**
 * @author Rohit
 *
 */
public class TestAppointmentManagerImpl extends TestCase {

	AppointmentManager appointmentManager;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {
			BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/HCISCoreContext.xml"));
			appointmentManager = (AppointmentManager)factory.getBean("AppointmentManager");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

//	/**
//	 * Test method for {@link com.wtc.hcis.bo.appointment.AppointmentManagerImpl#addAppointment(com.wtc.hcis.bm.base.AppointmentBM)}.
//	 */
//	@Test
//	public void testAddAppointment() {
//		try{
//			
//			AppointmentBM appointmentsBM = new AppointmentBM();
//			
//			Calendar appointmentDate = Calendar.getInstance();
//			appointmentDate.clear();
//			appointmentDate.set( 2009, 5, 20 );
//			appointmentsBM.setAppointmentDate( appointmentDate.getTime() );
//			appointmentsBM.setAppointmentAgenda( "Stomach Ache" );
//			
//			appointmentsBM.setFirstName( "Rohit" );
//			appointmentsBM.setLastName("Sharma");
//			appointmentsBM.setPatientId(1);
//			Calendar apptStartTime = Calendar.getInstance();
//			apptStartTime.clear();
//			apptStartTime.set( 2009, 5, 11, 10, 23);
//			appointmentsBM.setAppointmentStartTime( "2230" );
//			appointmentsBM.setAppointmentEndTime("2240");
//			appointmentsBM.setDepartment( new CodeAndDescription("GENERAL", "General Department" ) );
//			appointmentsBM.setModifiedBy("aranjan");
//			appointmentsBM.setPrimaryDoctor( new CodeAndDescription("1", "K J Reddy" ) );
//			
//			appointmentsBM.setRosterCode(1);
//			
//			appointmentsBM = appointmentManager.addAppointment( appointmentsBM );
//			
//			TestUtils.dumpBean( appointmentsBM );
//			assertTrue(true);
//		}catch(Exception e) {
//			e.printStackTrace();
//			assertFalse(true);
//		}
//		
//	}

//	/**
//	 * Test method for {@link com.wtc.hcis.bo.appointment.AppointmentManagerImpl#cancellation(com.wtc.hcis.bm.base.AppointmentBM)}.
//	 */
//	@Test
//	public void testCancelAppointment() {
//		try {
//			AppointmentBM appointmentsBM = new AppointmentBM();
//			appointmentsBM.setAppointmentNumber(4);
//			appointmentsBM.setModifiedBy("akumar");
//			boolean result = appointmentManager.cancelAppointment(appointmentsBM);
//			
//			assertTrue(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}
//
//	/**
//	 * Test method for {@link com.wtc.hcis.bo.appointment.AppointmentManagerImpl#getAppointments(java.util.Date, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	public void testGetAppointments() {
//		try {
//			List<AppointmentBM>appointmentsOfTheRoster = appointmentManager.getAppointments( null, 
//					                                                                         null, 
//					                                                                         1, 
//					                                                                         "Rohit", 
//					                                                                         "Sharma", 
//					                                                                         "Reddy", 
//					                                                                         null, 
//					                                                                         null, 
//					                                                                         null, 
//					                                                                         null );
//			
//			if ( appointmentsOfTheRoster != null && !appointmentsOfTheRoster.isEmpty()) {
//				
//				for ( AppointmentBM appointmentBM : appointmentsOfTheRoster ) {
//					TestUtils.dumpBean( appointmentBM );
//				}
//				
//			}
//			
//			assertTrue(true);
//		} catch ( Exception e ) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}

	
	public void testGetAppointmentsForTheRoster() {
		try {
			List<AppointmentBM>appointmentsOfTheRoster = appointmentManager.getAppointmentsForARoster(1);
			
			if ( appointmentsOfTheRoster != null && !appointmentsOfTheRoster.isEmpty()) {
				
				for ( AppointmentBM appointmentBM : appointmentsOfTheRoster ) {
					TestUtils.dumpBean( appointmentBM );
				}
				
			}
			
			assertTrue(true);
		} catch ( Exception e ) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

//	/**
//	 * Test method for {@link com.wtc.hcis.bo.appointment.AppointmentManagerImpl#getAppointments(java.util.Date, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date)}.
//	 */
//	@Test
//	public void testGetAppointmentsDateDateStringStringStringStringStringStringDateDate() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link com.wtc.hcis.bo.appointment.AppointmentManagerImpl#modifyAppointment(com.wtc.hcis.bm.base.AppointmentBM)}.
//	 */
//	@Test
//	public void testModifyAppointment() {
//		try {
//			AppointmentBM newAppointmentBM = appointmentManager.getAppointment( 8 );
//			newAppointmentBM.setConsultantHasSeenPatient(Boolean.TRUE);
//			newAppointmentBM.setModifiedBy( "krathi" );
//			newAppointmentBM.setAppointmentAgenda("Chest Pain");
//			newAppointmentBM.setAppointmentRemarks("The lung was clear");
//			
//			Calendar calendar = Calendar.getInstance();
//			calendar.set(Calendar.DATE, 28 );
//			newAppointmentBM.setNextVisitDate( calendar.getTime() );
//			
//			newAppointmentBM = appointmentManager.modifyAppointment( newAppointmentBM );
//			
//			TestUtils.dumpBean( newAppointmentBM );
//			
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}

//	/**
//	 * Test method for {@link com.wtc.hcis.bo.appointment.AppointmentManagerImpl#removeAppointments(java.util.List)}.
//	 */
//	@Test
//	public void testRemoveAppointments() {
//		try {
//			List<AppointmentBM>appointmentBMlist = new ArrayList<AppointmentBM>();
//			AppointmentBM appointmentBM = new AppointmentBM();
//			appointmentBM.setAppointmentNumber(7);
//			appointmentBMlist.add( appointmentBM );
//			boolean result = appointmentManager.removeAppointments(appointmentBMlist);
//			
//			assertTrue( result );
//		} catch ( Exception e ) {
//			e.printStackTrace();
//			assertTrue( false );
//		}
//	}

//	/**
//	 * Test method for {@link com.wtc.hcis.bo.appointment.AppointmentManagerImpl#rescheduleAppointment(com.wtc.hcis.bm.base.AppointmentBM, com.wtc.hcis.bm.base.AppointmentBM, java.lang.Boolean)}.
//	 */
//	@Test
//	public void testRescheduleAppointmentAtMyTime() {
//		try {
//			AppointmentBM oldAppointmentBM = new AppointmentBM();
//			oldAppointmentBM.setAppointmentNumber(5);
//			oldAppointmentBM.setModifiedBy("akumar");
//			
//			AppointmentBM newAppointmentBM = appointmentManager.getAppointment(5);
//			newAppointmentBM.setAppointmentNumber(null);
//			newAppointmentBM.setModifiedBy("akumar");
//			newAppointmentBM.setAppointmentStartTime("2220");
//			newAppointmentBM.setAppointmentEndTime("2230");
//			
//			newAppointmentBM = appointmentManager.rescheduleAppointment( oldAppointmentBM, newAppointmentBM, false );
//			
//			TestUtils.dumpBean( newAppointmentBM );
//			
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}

}
