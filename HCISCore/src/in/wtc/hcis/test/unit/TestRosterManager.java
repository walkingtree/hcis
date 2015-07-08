/**
 * 
 */
package in.wtc.hcis.test.unit;

import in.wtc.hcis.bm.base.AppointmentBM;
import in.wtc.hcis.bm.base.RoomBM;
import in.wtc.hcis.bm.base.RosterBM;
import in.wtc.hcis.bm.base.RosterModel;
import in.wtc.hcis.bm.base.RosterTimeBM;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.constants.ApplicationConstants;
import in.wtc.hcis.bo.constants.ApplicationEntities;
import in.wtc.hcis.bo.constants.RosterConstants;
import in.wtc.hcis.bo.roster.RosterManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Alok Ranjan
 *
 */
public class TestRosterManager extends TestCase {

	private RosterManager rosterManager;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		try {
			String[] ctxFileArr = { "applicationContext.xml", "HCISCoreContext.xml" };
		    ApplicationContext ctx = new ClassPathXmlApplicationContext( ctxFileArr );
			rosterManager = (RosterManager)ctx.getBean( "RosterManager" );
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	

//	/**
//	 * In this test case we will give the from date greater than the to date and it is expected to get a exception message
//	 * saying that your from date is greater than to date
//	 * Test method for {@link in.wtc.hcis.bo.roster.RosterManagerImpl#addRoster(in.wtc.hcis.bm.base.RosterBM)}.
//	 */
//	public void testAddDailyRosterWithFromDateGreaterThanToDate() {
//		try {
//			RosterBM rosterBM = new RosterBM();
//			rosterBM.setEntityId(2);
//			rosterBM.setEntityType( ApplicationEntities.DOCTOR );
//			List<RosterTimeBM> rosterTimeBMList = new ArrayList<RosterTimeBM>();
//			
//			RosterTimeBM rosterTimeBM = new RosterTimeBM();
//			rosterTimeBM.setActive( Boolean.TRUE );
//			Calendar workingDate = Calendar.getInstance();
//			workingDate.clear();
//			workingDate.set(2009, 5, 2);
//			rosterTimeBM.setWorkingDate(workingDate.getTime());
//			rosterTimeBM.setStartTime( "1100" );
//			rosterTimeBM.setEndTime( "1200" );
//			RoomBM roomBM = new RoomBM();
//			roomBM.setRoomCode("100");
//			rosterTimeBM.setRoomBM( roomBM );
//			rosterTimeBMList.add( rosterTimeBM );
//			
//			rosterBM.setRosterTimeBMList( rosterTimeBMList );
//			
//			Calendar fromTime = Calendar.getInstance();
//			fromTime.clear();
//			fromTime.set(2009, 5, 10);
//			rosterBM.setFromDate(fromTime.getTime());
//			Calendar toDate = Calendar.getInstance();
//			toDate.clear();
//			toDate.set(2009, 4, 13);
//			rosterBM.setToDate(toDate.getTime());
//			rosterBM.setPeriod( RosterConstants.DAILY );
//			
//			 rosterManager.addRoster(rosterBM);
//			TestUtils.dumpBean( rosterBM.getRosterTimeBMList().get(0) );
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}

//	/**
//	 * Test method for {@link in.wtc.hcis.bo.roster.RosterManagerImpl#addRoster(in.wtc.hcis.bm.base.RosterBM)}.
//	 */
//	public void testAddDailyRoster() {
//		try {
//			RosterBM rosterBM = new RosterBM();
//			rosterBM.setEntityId(2);
//			rosterBM.setEntityType( ApplicationEntities.DOCTOR );
//			List<RosterTimeBM> rosterTimeBMList = new ArrayList<RosterTimeBM>();
//			
//			RosterTimeBM rosterTimeBM = new RosterTimeBM();
//			rosterTimeBM.setActive( Boolean.TRUE );
//			Calendar workingDate = Calendar.getInstance();
//			workingDate.clear();
//			workingDate.set(2009, 10, 12);
//			rosterTimeBM.setWorkingDate(workingDate.getTime());
//			rosterTimeBM.setStartTime( "1100" );
//			rosterTimeBM.setEndTime( "1500" );
//			RoomBM roomBM = new RoomBM();
//			roomBM.setRoomCode("100");
//			rosterTimeBM.setRoomBM( roomBM );
//			rosterTimeBMList.add( rosterTimeBM );
//			
//			rosterBM.setRosterTimeBMList( rosterTimeBMList );
//			
//			Calendar fromTime = Calendar.getInstance();
//			fromTime.clear();
//			fromTime.set(2009, 10, 10);
//			rosterBM.setFromDate(fromTime.getTime());
//			Calendar toDate = Calendar.getInstance();
//			toDate.clear();
//			toDate.set(2009, 10, 15);
//			rosterBM.setToDate(toDate.getTime());
//			rosterBM.setPeriod( RosterConstants.DAILY );
//			
//			 rosterManager.addRoster(rosterBM);
//			TestUtils.dumpBean( rosterBM.getRosterTimeBMList().get(0) );
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}
	
//	/**
//	 * Test method for {@link in.wtc.hcis.bo.roster.RosterManagerImpl#addRoster(in.wtc.hcis.bm.base.RosterBM)}.
//	 */
//	public void testAddDailyRosterWithOverlappingTimeInInput() {
//		try {
//			RosterBM rosterBM = new RosterBM();
//			rosterBM.setEntityId(2);
//			rosterBM.setEntityType( ApplicationEntities.DOCTOR );
//			List<RosterTimeBM> rosterTimeBMList = new ArrayList<RosterTimeBM>();
//			
//			RosterTimeBM rosterTimeBM = new RosterTimeBM();
//			rosterTimeBM.setActive( Boolean.TRUE );
//			Calendar workingDate = Calendar.getInstance();
//			workingDate.clear();
//			workingDate.set(2009, 10, 12);
//			rosterTimeBM.setWorkingDate(workingDate.getTime());
//			rosterTimeBM.setStartTime( "1100" );
//			rosterTimeBM.setEndTime( "1500" );
//			RoomBM roomBM = new RoomBM();
//			roomBM.setRoomCode("100");
//			rosterTimeBM.setRoomBM( roomBM );
//			rosterTimeBMList.add( rosterTimeBM );
//			
//			RosterTimeBM rosterTimeBM2 = new RosterTimeBM();
//			rosterTimeBM2.setActive( Boolean.TRUE );
//			Calendar workingDate2 = Calendar.getInstance();
//			workingDate2.clear();
//			workingDate2.set(2009, 10, 12);
//			rosterTimeBM2.setWorkingDate(workingDate2.getTime());
//			rosterTimeBM2.setStartTime( "1400" );
//			rosterTimeBM2.setEndTime( "1600" );
//			RoomBM roomBM2 = new RoomBM();
//			roomBM2.setRoomCode("102");
//			rosterTimeBM2.setRoomBM( roomBM2 );
//			rosterTimeBMList.add( rosterTimeBM2 );
//			
//			rosterBM.setRosterTimeBMList( rosterTimeBMList );
//			
//			Calendar fromTime = Calendar.getInstance();
//			fromTime.clear();
//			fromTime.set(2009, 10, 10);
//			rosterBM.setFromDate(fromTime.getTime());
//			Calendar toDate = Calendar.getInstance();
//			toDate.clear();
//			toDate.set(2009, 10, 15);
//			rosterBM.setToDate(toDate.getTime());
//			rosterBM.setPeriod( RosterConstants.DAILY );
//			
//			 rosterManager.addRoster(rosterBM);
//			TestUtils.dumpBean( rosterBM.getRosterTimeBMList().get(0) );
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}
	
//	/**
//	 * Test method for {@link in.wtc.hcis.bo.roster.RosterManagerImpl#addRoster(in.wtc.hcis.bm.base.RosterBM)}.
//	 */
//	public void testAddWeeklyRoster() {
//		try {
//			RosterBM rosterBM = new RosterBM();
//			rosterBM.setEntityId(2);
//			rosterBM.setEntityType( ApplicationEntities.DOCTOR );
//			List<RosterTimeBM> rosterTimeBMList = new ArrayList<RosterTimeBM>();
//			
//			RosterTimeBM rosterTimeBM = new RosterTimeBM();
//			rosterTimeBM.setActive( Boolean.TRUE );
//			Calendar workingDate = Calendar.getInstance();
//			workingDate.clear();
//			workingDate.set(2009, 5, 5);
//			rosterTimeBM.setWorkingDate(workingDate.getTime());
//			rosterTimeBM.setStartTime( "1100" );
//			rosterTimeBM.setEndTime( "1500" );
//			RoomBM roomBM = new RoomBM();
//			roomBM.setRoomCode("100");
//			rosterTimeBM.setRoomBM( roomBM );
//			rosterTimeBMList.add( rosterTimeBM );
//			
//			RosterTimeBM rosterTimeBM2 = new RosterTimeBM();
//			rosterTimeBM2.setActive( Boolean.TRUE );
//			Calendar workingDate2 = Calendar.getInstance();
//			workingDate2.clear();
//			workingDate2.set(2009, 5, 5);
//			rosterTimeBM2.setWorkingDate(workingDate2.getTime());
//			rosterTimeBM2.setStartTime( "1400" );
//			rosterTimeBM2.setEndTime( "1600" );
//			RoomBM roomBM2 = new RoomBM();
//			roomBM2.setRoomCode("102");
//			rosterTimeBM2.setRoomBM( roomBM2 );
//			rosterTimeBMList.add( rosterTimeBM2 );
//			
//			rosterBM.setRosterTimeBMList( rosterTimeBMList );
//			
//			Calendar fromTime = Calendar.getInstance();
//			fromTime.clear();
//			fromTime.set(2009, 5, 4);
//			rosterBM.setFromDate(fromTime.getTime());
//			Calendar toDate = Calendar.getInstance();
//			toDate.clear();
//			toDate.set(2009, 6, 8);
//			rosterBM.setToDate(toDate.getTime());
//			rosterBM.setPeriod( RosterConstants.WEEKLY );
//			
//			 rosterManager.addRoster(rosterBM);
//			TestUtils.dumpBean( rosterBM.getRosterTimeBMList().get(0) );
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}
	
//	/**
//	 * In this test case we have given the working date as out of the from date and to date so it will thro an exception
//	 * Test method for {@link in.wtc.hcis.bo.roster.RosterManagerImpl#addRoster(in.wtc.hcis.bm.base.RosterBM)}.
//	 */
//	public void testAddWeeklyRosterWithDateOutOfTimePeriod() {
//		try {
//			RosterBM rosterBM = new RosterBM();
//			rosterBM.setEntityId(2);
//			rosterBM.setEntityType( ApplicationEntities.DOCTOR );
//			List<RosterTimeBM> rosterTimeBMList = new ArrayList<RosterTimeBM>();
//			
//			RosterTimeBM rosterTimeBM = new RosterTimeBM();
//			rosterTimeBM.setActive( Boolean.TRUE );
//			Calendar workingDate = Calendar.getInstance();
//			workingDate.clear();
//			workingDate.set(2009, 7, 5);
//			rosterTimeBM.setWorkingDate(workingDate.getTime());
//			rosterTimeBM.setStartTime( "1100" );
//			rosterTimeBM.setEndTime( "1200" );
//			RoomBM roomBM = new RoomBM();
//			roomBM.setRoomCode("100");
//			rosterTimeBM.setRoomBM( roomBM );
//			rosterTimeBMList.add( rosterTimeBM );
//			
//			RosterTimeBM rosterTimeBM2 = new RosterTimeBM();
//			rosterTimeBM2.setActive( Boolean.TRUE );
//			Calendar workingDate2 = Calendar.getInstance();
//			workingDate2.clear();
//			workingDate2.set(2009, 7, 9);
//			rosterTimeBM2.setWorkingDate(workingDate2.getTime());
//			rosterTimeBM2.setStartTime( "1400" );
//			rosterTimeBM2.setEndTime( "1600" );
//			RoomBM roomBM2 = new RoomBM();
//			roomBM2.setRoomCode("102");
//			rosterTimeBM2.setRoomBM( roomBM2 );
//			rosterTimeBMList.add( rosterTimeBM2 );
//			
//			rosterBM.setRosterTimeBMList( rosterTimeBMList );
//			
//			Calendar fromTime = Calendar.getInstance();
//			fromTime.clear();
//			fromTime.set(2009, 5, 4);
//			rosterBM.setFromDate(fromTime.getTime());
//			Calendar toDate = Calendar.getInstance();
//			toDate.clear();
//			toDate.set(2009, 6, 8);
//			rosterBM.setToDate(toDate.getTime());
//			rosterBM.setPeriod( RosterConstants.WEEKLY );
//			
//			 rosterManager.addRoster(rosterBM);
//			TestUtils.dumpBean( rosterBM.getRosterTimeBMList().get(0) );
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}
	
//	/**
//	 * Test method for {@link in.wtc.hcis.bo.roster.RosterManagerImpl#addRoster(in.wtc.hcis.bm.base.RosterBM)}.
//	 */
//	public void testAddMonthlyRoster() {
//		try {
//			RosterBM rosterBM = new RosterBM();
//			rosterBM.setEntityId(2);
//			rosterBM.setEntityType( ApplicationEntities.DOCTOR );
//			List<RosterTimeBM> rosterTimeBMList = new ArrayList<RosterTimeBM>();
//			
//			RosterTimeBM rosterTimeBM = new RosterTimeBM();
//			rosterTimeBM.setActive( Boolean.TRUE );
//			Calendar workingDate = Calendar.getInstance();
//			workingDate.clear();
//			workingDate.set(2009, 5, 2);
//			rosterTimeBM.setWorkingDate(workingDate.getTime());
//			rosterTimeBM.setStartTime( "1100" );
//			rosterTimeBM.setEndTime( "1200" );
//			RoomBM roomBM = new RoomBM();
//			roomBM.setRoomCode("100");
//			rosterTimeBM.setRoomBM( roomBM );
//			rosterTimeBMList.add( rosterTimeBM );
//			
//			RosterTimeBM rosterTimeBM2 = new RosterTimeBM();
//			rosterTimeBM2.setActive( Boolean.TRUE );
//			Calendar workingDate2 = Calendar.getInstance();
//			workingDate2.clear();
//			workingDate2.set(2009, 5, 25);
//			rosterTimeBM2.setWorkingDate(workingDate2.getTime());
//			rosterTimeBM2.setStartTime( "1400" );
//			rosterTimeBM2.setEndTime( "1600" );
//			RoomBM roomBM2 = new RoomBM();
//			roomBM2.setRoomCode("102");
//			rosterTimeBM2.setRoomBM( roomBM2 );
//			rosterTimeBMList.add( rosterTimeBM2 );
//			
//			rosterBM.setRosterTimeBMList( rosterTimeBMList );
//			
//			Calendar fromTime = Calendar.getInstance();
//			fromTime.clear();
//			fromTime.set(2009, 5, 1);
//			rosterBM.setFromDate(fromTime.getTime());
//			Calendar toDate = Calendar.getInstance();
//			toDate.clear();
//			toDate.set(2009, 10, 15);
//			rosterBM.setToDate(toDate.getTime());
//			rosterBM.setPeriod( RosterConstants.MONTHLY );
//			
//			 rosterManager.addRoster(rosterBM);
//			TestUtils.dumpBean( rosterBM.getRosterTimeBMList().get(0) );
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}

//	/**
//	 * Test method for {@link in.wtc.hcis.bo.roster.RosterManagerImpl#getRoster(java.lang.String, java.lang.Integer, java.util.Date, java.util.Date, java.util.Date, java.util.Date, java.lang.String, java.lang.String, java.lang.String)}.
//	 */
//	public void testGetRoster() {
//		try {
//			List<RosterModel>rosterBMList = rosterManager.getRoster( null, 
//					                                              2, 
//					                                              null, 
//					                                              null, 
//					                                              null, 
//					                                              null, 
//					                                              null, 
//					                                              null, 
//					                                              null,
//					                                              null,
//					                                              null,
//					                                              null );
//			
//			if ( rosterBMList != null && !rosterBMList.isEmpty()) {
//				for ( RosterModel rosterBM : rosterBMList ) {
//					TestUtils.dumpBean( rosterBM );
//				}
//				
//				assertTrue(true);
//			} else {
//				assertTrue(false);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}
	
//	public void testGetRosterWithNames() {
//		try {
//			List<RosterModel>rosterBMList = rosterManager.getRoster( ApplicationEntities.DOCTOR, 
//					                                              null, 
//					                                              null, 
//					                                              null, 
//					                                              null, 
//					                                              null, 
//					                                              "K", 
//					                                              "J", 
//					                                              "Reddy",
//					                                              null,
//					                                              null,
//					                                              null );
//			
//			if ( rosterBMList != null && !rosterBMList.isEmpty()) {
//				for ( RosterModel rosterBM : rosterBMList ) {
//					TestUtils.dumpBean( rosterBM );
//				}
//				
//				assertTrue(true);
//			} else {
//				assertTrue(false);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.roster.RosterManagerImpl#modifyRosterWorkingDate(in.wtc.hcis.bm.base.RosterBM)}.
	 */
	public void testModifyRosterWorkingDate() {
		try {
			Integer rosterCode = new Integer(3);
			RosterModel rosterBM = rosterManager.getRosterModel(rosterCode); 
			rosterBM.setRosterCode(rosterCode);
			rosterBM.setEntityType( ApplicationEntities.DOCTOR );
			Calendar workingDate = Calendar.getInstance();
			workingDate.clear();
			workingDate.set(2009, 06, 03);
			rosterBM.setWorkingDate(workingDate.getTime());
			rosterBM.setStartTime("1100");
			rosterBM.setEndTime("1500");
			RoomBM roomBM = new RoomBM();
			roomBM.setRoomCode("105");
			rosterBM.setRoomBM(roomBM);
			
			List<AppointmentBM> tmpAppointmentBMList = rosterManager.modifyRoster(rosterBM, Boolean.TRUE );
//			TestUtils.dumpBean( tmpRosterBM );
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
//	/**
//	 * Test method for {@link in.wtc.hcis.bo.roster.RosterManagerImpl#modifyRosterWorkingDate(in.wtc.hcis.bm.base.RosterBM)}.
//	 */
//	public void testModifyRosterStartTime() {
//		try {
//			Integer rosterCode = new Integer(2);
//			RosterModel rosterBM = rosterManager.getRosterModel(rosterCode); 
//			rosterBM.setRosterCode(rosterCode);
//			Calendar workingDate = Calendar.getInstance();
//			workingDate.clear();
//			workingDate.set(2009, 5, 25);
//			rosterBM.setWorkingDate(workingDate.getTime());
//			rosterBM.setStartTime("1200");
//			rosterBM.setEndTime("1600");
//			RoomBM roomBM = new RoomBM();
//			roomBM.setRoomCode("102");
//			rosterBM.setRoomBM(roomBM);
//			
//			List<AppointmentBM> tmpAppointmentBMList = rosterManager.modifyRoster(rosterBM, true );
////			TestUtils.dumpBean( tmpRosterBM );
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}
	
//	/**
//	 * Test method for {@link in.wtc.hcis.bo.roster.RosterManagerImpl#modifyRosterRoomNumber(in.wtc.hcis.bm.base.RosterBM)}.
//	 */
//	public void testModifyRosterRoomNumber() {
//		try {
//			Integer rosterCode = new Integer(1);
//			RosterModel rosterBM = rosterManager.getRosterModel(rosterCode); 
//			rosterBM.setRosterCode(rosterCode);
//			Calendar workingDate = Calendar.getInstance();
//			workingDate.clear();
//			workingDate.set(2009, 5, 2);
//			rosterBM.setWorkingDate(workingDate.getTime());
//			rosterBM.setStartTime("1100");
//			rosterBM.setEndTime("1200");
//			RoomBM roomBM = new RoomBM();
//			roomBM.setRoomCode("105");
//			rosterBM.setRoomBM(roomBM);
//			
//			List<AppointmentBM> tmpAppointmentBMList = rosterManager.modifyRoster(rosterBM, true );
////			TestUtils.dumpBean( tmpRosterBM );
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}
	
//	/**
//	 * Test method for {@link in.wtc.hcis.bo.roster.RosterManagerImpl#modifyRosterRoomNumber(in.wtc.hcis.bm.base.RosterBM)}.
//	 */
//	public void testModifyRosterEndTime() {
//		try {
//			Integer rosterCode = new Integer(1);
//			RosterModel rosterBM = rosterManager.getRosterModel(rosterCode); 
//			rosterBM.setRosterCode(rosterCode);
//			Calendar workingDate = Calendar.getInstance();
//			workingDate.clear();
//			workingDate.set(2009, 5, 2);
//			rosterBM.setWorkingDate(workingDate.getTime());
//			rosterBM.setStartTime("1000");
//			rosterBM.setEndTime("1200");
//			RoomBM roomBM = new RoomBM();
//			roomBM.setRoomCode("100");
//			rosterBM.setRoomBM(roomBM);
//			
//			List<AppointmentBM> tmpAppointmentBMList = rosterManager.modifyRoster(rosterBM, true );
////			TestUtils.dumpBean( tmpRosterBM );
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}

//	/**
//	 * Test method for {@link in.wtc.hcis.bo.roster.RosterManagerImpl#removeRosters(java.util.List)}.
//	 */
//	public void testRemoveRosters() {
//		try {
//			List<RosterModel> rosterList = new ArrayList<RosterModel>();
//			RosterModel rosterModel = new RosterModel();
//			rosterModel.setRosterCode( new Integer(1) );
//			rosterList.add(rosterModel);
//			RosterModel rosterModel2 = new RosterModel();
//			rosterModel2.setRosterCode( new Integer(3) );
//			rosterList.add(rosterModel2);
//			Map<Integer, List<AppointmentBM>> appointmentBMList = rosterManager.removeRosters( rosterList, false );
////			TestUtils.dumpBean( appointmentBMList.get(0) );
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}
	
//	/**
//	 * Test method for {@link in.wtc.hcis.bo.roster.RosterManagerImpl#getRoster(in.wtc.hcis.bm.base.RosterBM)}.
//	 */
//	public void testGetRosterInteger() {
//		try {
//			Integer rosterCode = new Integer(3);
//			
//			RosterBM rosterBM = rosterManager.getRoster(rosterCode);
//			TestUtils.dumpBean(rosterBM.getRosterTimeBMList().get(0).getRosterCode() );
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}

}
