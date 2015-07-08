/**
 * 
 */
package in.wtc.hcis.ip.test.unittest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.ip.BedBookingDetails;
import in.wtc.hcis.bm.ip.BedEnvelopeBM;
import in.wtc.hcis.bm.ip.BedEnvelopePoolAsgmBM;
import in.wtc.hcis.bm.ip.BedMasterBM;
import in.wtc.hcis.bm.ip.BedPoolBM;
import in.wtc.hcis.bm.ip.BedPoolUnitTypeAsgmBM;
import in.wtc.hcis.bm.ip.BedReservationBM;
import in.wtc.hcis.bm.ip.BedSpecialFeatureAvailability;
import in.wtc.hcis.bm.ip.PreferredBedBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ip.bo.bed.BedManager;
import in.wtc.hcis.ip.bo.bed.EnumBedOrganizationType;
import in.wtc.hcis.test.unit.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Bhavesh
 *
 */
public class TestBedManagerImpl {

	BedManager bedManager;
	
	@Before
	public  void   setUp() throws Exception {
//		super.setUp();

		String[] ctxFileArr = {"IPapplicationContext.xml","applicationContextReport.xml","AccountantContextBLTest.xml","HCISCoreContext.xml","IPManagementContext.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);
		bedManager = (BedManager)ctx.getBean("BedManager");

	}

	
	
	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#addBed(in.wtc.hcis.bm.ip.BedMasterBM)}.
	 */

	public void testAddBed() {
		try {
			BedMasterBM bedMasterBM= new BedMasterBM();
			bedMasterBM.setBedNumber("040003");
			bedMasterBM.setBedStatus(new CodeAndDescription("AVAILABLE","Bed is available for occupancy"));
//			bedMasterBM.setRoomNbr("100");
//			bedMasterBM.setNursingUnit("NU1");
			bedMasterBM.setBedType(new CodeAndDescription("SINGLE","Single occupany bed"));
			
			BedSpecialFeatureAvailability specialFeatureAvailability= new BedSpecialFeatureAvailability();
			
			specialFeatureAvailability.setFeatureName("Fridge");
			specialFeatureAvailability.setDescription("Fridge");
			List<BedSpecialFeatureAvailability> specialFeatureList = new ArrayList<BedSpecialFeatureAvailability>();
			specialFeatureList.add(specialFeatureAvailability);
			
			bedMasterBM.setSpecialFeatureAvailabilityList(specialFeatureList);
			bedMasterBM.setModifiedBy("Bhavesh");
			
			Util.dumpBean(bedManager.addBed(bedMasterBM));
			assertTrue(true);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#addBedEnvelope(in.wtc.hcis.bm.ip.BedEnvelopeBM)}.
	 */
	
	public void testAddBedEnvelope() {
		try {
			BedEnvelopeBM bedEnvelope = new BedEnvelopeBM();
			
		
			bedEnvelope.setEnvelopeName("ENV5");
			bedEnvelope.setDescription("Env from Test");

			bedManager.addBedEnvelope(bedEnvelope);
			assertTrue(true);
			Util.dumpBean(bedEnvelope);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#addBedPool(in.wtc.hcis.bm.ip.BedPoolBM)}.
	 */
	
	public void testAddBedPool() {
		try {
			BedPoolBM bedPool = new BedPoolBM();
			bedPool.setBedPoolName("BEDPOOL1");
			bedPool.setPoolDesc("Added from Test");
			bedPool.setTotalNumberOfBed(new Integer(100));
			bedPool.setNumberOfAvailableBeds(new Integer(100));
			bedManager.addBedPool(bedPool);
			assertTrue(true);
			Util.dumpBean(bedPool);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#getAllBeds(in.wtc.hcis.ip.bo.bed.EnumBedOrganizationType)}.
	 */
	
	public void testGetAllBeds() {
		try {
			HashMap<String,List<BedMasterBM>> allBedsMap = (HashMap<String, List<BedMasterBM>>) bedManager.getAllBeds(EnumBedOrganizationType.NO_ORDERING);
			Util.dumpBean(allBedsMap.entrySet().iterator().next().getValue().get(0));
			assertTrue(true);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#getBedBookingDetails(java.lang.Integer)}.
	 */
	
	public void testGetBedBookingDetails() {
		try {
			ArrayList<BedBookingDetails> bookingList =(ArrayList<BedBookingDetails>) bedManager.getBedBookingDetails(new Integer(101));
			Util.dumpBean(bookingList.get(0));
			assertTrue(bookingList.size()>0);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#getBedDetails(java.lang.String)}.
	 */
	
	public void testGetBedDetailsString() {
		try {
			BedMasterBM bookingDetalis = bedManager.getBedDetails("020002");
			Util.dumpBean(bookingDetalis);
			assertTrue(bookingDetalis!=null);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#getBedDetails(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String, java.util.Date, java.util.Date)}.
	 */
	
	public void testGetBedDetailsStringStringStringStringIntegerStringIntegerStringDateDate() {
		fail("Not yet implemented");
//		Because of coreDataModelManager object.
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#getBedReservationDetails(java.lang.Integer)}.
	 */
	
	public void testGetBedReservationDetails() {
	try{
		BedBookingDetails bedBookingDetails= bedManager.getBedReservationDetails(new Integer(1));
		Util.dumpBean(bedBookingDetails);
		assertTrue(bedBookingDetails!=null);
	} catch (HCISException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		assertTrue(false);
	}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#getCurrentlyAvailableBeds(java.lang.String, java.lang.String)}.
	 */
	
	public void testGetCurrentlyAvailableBeds() {
		try{
			List<BedMasterBM> CurrentlyAvailableBeds = bedManager.getCurrentlyAvailableBeds("EMERGENCY", null);
			Util.dumpBean(CurrentlyAvailableBeds.get(0));
			assertTrue(CurrentlyAvailableBeds.size()>0);
		} catch (HCISException e) {
			// TODO Auto-generated catch blocknit1
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#getCurrentlyOccupiedBed(java.lang.Integer, java.lang.Integer)}.
	 */
	
	public void testGetCurrentlyOccupiedBed() {
		try{
			BedMasterBM currentlyOccupiedBeds = bedManager.getCurrentlyOccupiedBed(new Integer(101), new Integer(1));
			Util.dumpBean(currentlyOccupiedBeds);
			assertTrue(currentlyOccupiedBeds!=null);
		} catch (HCISException e) {
			// TODO Auto-generated catch blocknit1
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#getPreferredBedAvailability(java.lang.Integer)}.
	 */
	
	public void testGetPreferredBedAvailability() {
		try{
			List<PreferredBedBM>preferredBedBMList  = bedManager.getPreferredBedAvailability(new  Integer(1));
			for(PreferredBedBM bedMasterBM : preferredBedBMList)
			Util.dumpBean(bedMasterBM.getBedMasterBM());
			assertTrue(preferredBedBMList.size()>0);
		} catch (HCISException e) {
			// TODO Auto-generated catch blocknit1
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#modifyAllowedBookingThreshold(java.lang.String, java.lang.String, int)}.
	 */
	
	public void testModifyAllowedBookingThreshold() {
		try {
			bedManager.modifyAllowedBookingThreshold("GENERAL", "SINGLE", new Integer(12));
			System.out.println("Threshold Modified..");
			assertTrue(true);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#modifyBed(in.wtc.hcis.bm.ip.BedMasterBM)}.
	 */
	
	public void testModifyBed() {
		try {
			BedMasterBM bedMasterBM= new BedMasterBM();
			bedMasterBM.setBedNumber("020002");
//			bedMasterBM.setRoomNbr("F01");
//			bedMasterBM.setNursingUnit("EMEREGENCY NU");
			bedMasterBM.setBedType(new CodeAndDescription("DOUBLE","Double occupany bed"));
			bedMasterBM.setFloorNbr("01");

			bedMasterBM.setBedStatus(new CodeAndDescription("DEFECTIVE","Bed is Defective"));
			bedManager.modifyBed(bedMasterBM);
			Util.dumpBean(bedMasterBM);
			assertTrue(true);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#modifyBedEnvelope(in.wtc.hcis.bm.ip.BedEnvelopeBM)}.
	 */
	
	public void testModifyBedEnvelope() {
		try {
			BedEnvelopeBM bedEnvelope = new BedEnvelopeBM();
				
			bedEnvelope.setEnvelopeName("ENV5");
//			bedEnvelope.setDescription("Env  is modified");
			bedEnvelope.setFacilityTypeInd("A");
			bedManager.modifyBedEnvelope(bedEnvelope);
			assertTrue(true);
			Util.dumpBean(bedEnvelope);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#modifyBedPool(in.wtc.hcis.bm.ip.BedPoolBM)}.
	 */
	
	public void testModifyBedPool() {
		try {
			BedPoolBM bedPool = new BedPoolBM();
			bedPool.setBedPoolName("BEDPOOL1");
			bedPool.setPoolDesc("Modified by Test");
//			bedPool.setTotalNumberOfBed(new Integer(101));
//			bedPool.setNumberOfAvailableBeds(new Integer(90));
			bedManager.modifyBedPool(bedPool);
			assertTrue(true);
			Util.dumpBean(bedPool);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#removeBed(java.util.List)}.
	 */
	
	public void testRemoveBed() {
		try {
			List<String> bedList= new ArrayList<String>();
			bedList.add("020003");
			
			assertTrue(bedManager.removeBed(bedList));
			System.out.println("Beds removed "+bedList);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#removeBedEnvelope(java.util.List)}.
	 */
	
	public void testRemoveBedEnvelope() {
		try {
			List<String> bedEnvList= new ArrayList<String>();
			bedEnvList.add("ENV5");
			assertTrue(bedManager.removeBedEnvelope(bedEnvList));
			System.out.println("BedEnvelop removed "+bedEnvList);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.bed.BedManagerImpl#removeBedPool(java.util.List)}.
	 */
	
	public void testRemoveBedPool() {
		List<String> bedPoolList= new ArrayList<String>();
		bedPoolList.add("BEDPOOL10");
		bedManager.removeBedPool(bedPoolList);
		System.out.println("Bedpool removed "+bedPoolList);
	}

//	Custom test cases
	
	/**
	 * Test method for {BedManagerImpl#addBedEnvelope(BedEnvelopeBM)} with bedEnvelopePoolAsgmList.
	 */
	
	public void testAddBedEnvelopeWithAsgmList() {
		try {
			BedEnvelopeBM bedEnvelope = new BedEnvelopeBM();
			
		
			bedEnvelope.setEnvelopeName("ENV2");
			bedEnvelope.setDescription("Envelope from Test");
			bedEnvelope.setFacilityTypeInd("I");
			List<BedEnvelopePoolAsgmBM>bedEnvelopePoolAsgmList= new ArrayList<BedEnvelopePoolAsgmBM>();
			BedEnvelopePoolAsgmBM asgmBM1= new BedEnvelopePoolAsgmBM();
			asgmBM1.setEnvelopeName("ENV2");
			asgmBM1.setPoolName("POOL1");
			
			BedEnvelopePoolAsgmBM asgmBM2= new BedEnvelopePoolAsgmBM();
			asgmBM2.setEnvelopeName("ENV2");
			asgmBM2.setPoolName("Pool2");
			
			bedEnvelopePoolAsgmList.add(asgmBM1);
			bedEnvelopePoolAsgmList.add(asgmBM2);
			
			bedEnvelope.setBedEnvelopePoolAsgmList(bedEnvelopePoolAsgmList);
			
			bedManager.addBedEnvelope(bedEnvelope);
			assertTrue(true);
			Util.dumpBean(bedEnvelope);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {addBedPool with bedPoolUnitType
	 */	
	
	public void testAddBedPoolWithUnitType() {
		try {
			BedPoolBM bedPool = new BedPoolBM();
			bedPool.setBedPoolName("BEDPOOL10");
			bedPool.setPoolDesc("Bed Pool 10 Added from Test");
			bedPool.setTotalNumberOfBed(new Integer(20));
			bedPool.setNumberOfAvailableBeds(new Integer(20));
			
			List<BedPoolUnitTypeAsgmBM>bedPoolUnitTypeAsgmList = new ArrayList<BedPoolUnitTypeAsgmBM>();
			BedPoolUnitTypeAsgmBM asgmBM1= new BedPoolUnitTypeAsgmBM();
			
			asgmBM1.setPoolName("BEDPOOL10");
			asgmBM1.setUnitType(new CodeAndDescription("GENERAL","General Nursing Unit"));
//			asgmBM1.setBedType(new CodeAndDescription("SINGLE","Single occupany bed"));
			
			BedPoolUnitTypeAsgmBM asgmBM2= new BedPoolUnitTypeAsgmBM();
			asgmBM2.setPoolName("BEDPOOL10");
			asgmBM2.setUnitType(new CodeAndDescription("EMERGENCY","Nursing units for emergency department"));
//			asgmBM2.setBedType(new CodeAndDescription("SINGLE","Single occupany bed"));
			
			bedPoolUnitTypeAsgmList.add(asgmBM1);
			bedPoolUnitTypeAsgmList.add(asgmBM2);
			bedPool.setBedPoolUnitTypeAsgm(bedPoolUnitTypeAsgmList);
			
			bedManager.addBedPool(bedPool);
			assertTrue(true);
			Util.dumpBean(bedPool);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	/**
	 * Test method for {BedManagerImpl#getAllBeds() order by floor number
	 */
	
	public void testGetAllBedsOrderByFloor() {
		try {
			HashMap<String,List<BedMasterBM>> allBedsMap = (HashMap<String, List<BedMasterBM>>) bedManager.getAllBeds(EnumBedOrganizationType.FLOOR_NUMBER);

			Iterator<Entry<String,List<BedMasterBM>>> itr =allBedsMap.entrySet().iterator();
			for(int j=0;itr.hasNext();j++){
				Entry<String,List<BedMasterBM>> entry=itr.next();
				System.out.println("\n\nFloor No. :"+entry.getKey().toString());
				for(int i=0;i<entry.getValue().size();i++){
					Util.dumpBean(entry.getValue().get(i));
				}
			}
			assertTrue(true);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		
	}
	
	
	public void testGetAllBedsOrderByUnit() {
		try {
			HashMap<String,List<BedMasterBM>> allBedsMap = (HashMap<String, List<BedMasterBM>>) bedManager.getAllBeds(EnumBedOrganizationType.NURSING_UNITS);

			Iterator<Entry<String,List<BedMasterBM>>> itr =allBedsMap.entrySet().iterator();
			for(int j=0;itr.hasNext();j++){
				Entry<String,List<BedMasterBM>> entry=itr.next();
				System.out.println("\n\nNursing Unit :"+entry.getKey().toString());
				for(int i=0;i<entry.getValue().size();i++){
				
				Util.dumpBean(entry.getValue().get(i));
				}
			}
			assertTrue(true);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
  }
	
	public void testFindBedPools(){
		try {
			ListRange bedPoolsList = bedManager.findBedPools("P", 		//poolName,
															 "lkj",		//nursingUnitTypeCd,
															 new Date(),//effectiveFrom,
															 new Date(),//effectiveTo,
															 0,			//start,
															 10,		//count,
															 null);		//orderBy)
			
			for( Object bedPools : bedPoolsList.getData()){
				Util.dumpBean(bedPools);
				
			}
			assertTrue(bedPoolsList != null);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	public void testCheckBedAvaibilityForBooking(){
		try{
			BedReservationBM bedReservationBM = new BedReservationBM();
			List<CodeAndDescription> requireFaclities = new ArrayList<CodeAndDescription>(0);
			List<CodeAndDescription> desiredFacilities = new ArrayList<CodeAndDescription>(0);
			
			
			bedReservationBM.setBedType(new CodeAndDescription("GENERAL_WARD",""));
			bedReservationBM.setCreatedBy("Bhavesh");
			
			requireFaclities.add(new CodeAndDescription("Adjustable",""));
			requireFaclities.add(new CodeAndDescription("Foldable",""));
			
			desiredFacilities.add(new CodeAndDescription("Television",""));
			desiredFacilities.add(new CodeAndDescription("Fridge",""));
			
			bedReservationBM.setDesiredFacilities(desiredFacilities);
			bedReservationBM.setRequiredFacilities(requireFaclities);
			
			Date reservationFromDtm = new Date();
			reservationFromDtm.setDate(10);
			reservationFromDtm.setMonth( 11 );
			reservationFromDtm.setYear( 2009-1900);
			
			Date reservationToDtm = new Date();
			reservationToDtm.setDate(30);
			reservationToDtm.setMonth( 11 );
			reservationToDtm.setYear( 2009-1900);
			
			bedReservationBM.setReservationFromDtm(reservationFromDtm);
			bedReservationBM.setReservationToDtm(reservationToDtm);
			bedReservationBM.setUnitType( new CodeAndDescription("GENERAL",""));
			
			Util.dumpBean(bedManager.checkBedAvaibilityForBooking(bedReservationBM));
			assertTrue( true );
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	
	public void testBookBed(){
		try{
			BedReservationBM bedReservationBM = new BedReservationBM();
			
			bedReservationBM.setAdmissionReqNbr(3);
			List<CodeAndDescription> requireFaclities = new ArrayList<CodeAndDescription>(0);
			List<CodeAndDescription> desiredFacilities = new ArrayList<CodeAndDescription>(0);
			
			
			bedReservationBM.setBedType(new CodeAndDescription("GENERAL_WARD",""));
			bedReservationBM.setCreatedBy("Bhavesh");
			
			requireFaclities.add(new CodeAndDescription("Adjustable",""));
			requireFaclities.add(new CodeAndDescription("Foldable",""));
			
			desiredFacilities.add(new CodeAndDescription("Television",""));
			desiredFacilities.add(new CodeAndDescription("Fridge",""));
			
			bedReservationBM.setDesiredFacilities(desiredFacilities);
			bedReservationBM.setRequiredFacilities(requireFaclities);
			
			Date reservationFromDtm = new Date();
			reservationFromDtm.setDate(10);
			reservationFromDtm.setMonth( 11 );
			reservationFromDtm.setYear( 2009-1900);
			
			Date reservationToDtm = new Date();
			reservationToDtm.setDate(30);
			reservationToDtm.setMonth( 11 );
			reservationToDtm.setYear( 2009-1900);
			
			bedReservationBM.setReservationFromDtm(reservationFromDtm);
			bedReservationBM.setReservationToDtm(reservationToDtm);
			bedReservationBM.setUnitType( new CodeAndDescription("GENERAL",""));
			
			Util.dumpBean(bedManager.bookBed(bedReservationBM));
			assertTrue( true );
		}catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	public void testFindBedBookings(){
		
		try {
			ListRange bedBookings = bedManager.findBedBookings( null,			//   bedReservationNbr,
																"GENERAL",		    //   unitTypeCode,
																null,		    //   bedTypeCode,
																null,		    //   bedNumber,
																"CONFIRMED",		    //   reservationStatusCd,
																null,		    //   admissionReqNbr,
																new Date(),		    //   reservationFromDt,
																null,		    //   reservationToDt,
														       0, 50, "");
			Util.dumpBean(bedBookings);
			assertTrue(bedBookings != null);
		} catch (HCISException e) {
			
			e.printStackTrace();
			assertTrue(false);
		}
		
		
	}
	
	
	public void modifyBedBooking(){
		try{
		BedReservationBM bedReservationBM = new BedReservationBM();
		
		bedReservationBM.setAdmissionReqNbr(3);
		List<CodeAndDescription> requireFaclities = new ArrayList<CodeAndDescription>(0);
		List<CodeAndDescription> desiredFacilities = new ArrayList<CodeAndDescription>(0);
		bedReservationBM.setBedReservationNbr(4);
		
		bedReservationBM.setBedType(new CodeAndDescription("GENERAL_WARD",""));
		bedReservationBM.setCreatedBy("Manish");
		
//		requireFaclities.add(new CodeAndDescription("Adjustable",""));
		requireFaclities.add(new CodeAndDescription("Foldable",""));
		
		desiredFacilities.add(new CodeAndDescription("Television",""));
		desiredFacilities.add(new CodeAndDescription("Over Bed Table",""));
		
		bedReservationBM.setDesiredFacilities(desiredFacilities);
		bedReservationBM.setRequiredFacilities(requireFaclities);
		bedReservationBM.setReservationStatus(new CodeAndDescription("CONFIRMED",""));
		Date reservationFromDtm = new Date();
		reservationFromDtm.setDate(10);
		reservationFromDtm.setMonth( 11 );
		reservationFromDtm.setYear( 2009-1900);
		
		Date reservationToDtm = new Date();
		reservationToDtm.setDate(30);
		reservationToDtm.setMonth( 11 );
		reservationToDtm.setYear( 2009-1900);
		
		bedReservationBM.setReservationFromDtm(reservationFromDtm);
		bedReservationBM.setReservationToDtm(reservationToDtm);
		bedReservationBM.setUnitType( new CodeAndDescription("GENERAL",""));
		
		Util.dumpBean(bedManager.modifyBedBooking(bedReservationBM));
		assertTrue( true );
	}catch (Exception e) {
		e.printStackTrace();
		assertTrue(false);
	}
	}
	
	
	
}
