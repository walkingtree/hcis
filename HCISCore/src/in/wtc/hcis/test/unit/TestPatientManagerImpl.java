package in.wtc.hcis.test.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactBM;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.OtherDetailsBM;
import in.wtc.hcis.bm.base.PatientAllergiesBM;
import in.wtc.hcis.bm.base.PatientInfoDetailBM;
import in.wtc.hcis.bm.base.PatientInfoSummaryBM;
import in.wtc.hcis.bm.base.PatientLiteBM;
import in.wtc.hcis.bm.base.PersonalDetailsAdditionalBM;
import in.wtc.hcis.bm.base.PersonalDetailsBM;
import in.wtc.hcis.bm.base.SponsorsBM;
import in.wtc.hcis.bo.patient.PatientManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wtc.hcis.da.extn.PatientDAOExtn;

public class TestPatientManagerImpl {

	PatientManager patientManager;
	PatientDAOExtn patientDAOExtn;
	@Before
	public void setUp() throws Exception {
		try {
			String[] ctxFileArr = {"applicationContext.xml","HCISCoreContext.xml"};
			ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);

			patientManager = (PatientManager)ctx.getBean("PatientManager");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	public void testEmergencyToNormal() {
		try {
			List<Integer> patientIdList = new ArrayList<Integer>();
			
			Integer patientId = new Integer(2);
			patientIdList.add( patientId );
			
			patientManager.emergencyToNormal(patientIdList);
			assertTrue(true);
		} catch (Exception exception) {
			exception.printStackTrace();
			assertFalse(true);
		}
	}
	
	@Test
	public void testGetPatientLiteBMInteger() {
		try {
			Integer patientId = new Integer(4);
			
			PatientLiteBM patientLiteBM = patientManager.getPatientLiteBM( patientId );
			TestUtils.dumpBean( patientLiteBM );
			assertTrue(true);
		} catch (Exception exception) {
			exception.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testGetPatientDetailsPatientInfoSummaryBM() {
		
		try {
			PatientInfoSummaryBM patientInfoSummaryBM = new PatientInfoSummaryBM();
			PersonalDetailsBM personalDetailsBM = new PersonalDetailsBM();
			ContactDetailsBM contactDetailsBM = new ContactDetailsBM();
			personalDetailsBM.setRegistrationNumber(4);
			
			patientInfoSummaryBM.setPersonalDetailsBM(personalDetailsBM);
			patientInfoSummaryBM.setContactDetailsBM(contactDetailsBM);
			Calendar lastVisited = Calendar.getInstance();
			lastVisited.clear();
			lastVisited.set( 2009, 01, 01);
			patientInfoSummaryBM.setLastVisited( lastVisited.getTime() );
			PatientInfoDetailBM patientInfoDetailBM = patientManager.getPatientDetails(patientInfoSummaryBM);
			System.out.println("TestPatientManager.testGetPatientDetailsPatientInfoSummaryBM()"+patientInfoDetailBM.getPersonalDetailsBM().getFirstName());
			assertTrue(true);
		} catch( Exception exception ) {
			exception.printStackTrace();
			assertFalse(true);
		}
	
	}

	@Test
	public void testGetPatientHistory() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyPatientDetails() {
		try {
			PatientInfoDetailBM patientInfoDetailBM = new PatientInfoDetailBM();
			PersonalDetailsBM personalDetailsBM = new PersonalDetailsBM();
			PersonalDetailsAdditionalBM personalDetailsAdditionalBM = new PersonalDetailsAdditionalBM();
			SponsorsBM sponsorsBM = new SponsorsBM();
			OtherDetailsBM otherDetailsBM = new OtherDetailsBM();
			ContactBM contactBM = new ContactBM();
			List<ContactDetailsBM> contactDetailList = new ArrayList<ContactDetailsBM>(0);

			personalDetailsBM.setRegistrationNumber(3);
			personalDetailsBM.setRegistrationDate( Calendar.getInstance().getTime() );
			personalDetailsBM.setRegistrationType( new CodeAndDescription( "EMERGENCY","Emergency" ) );
			personalDetailsBM.setRegistrationStatus( new CodeAndDescription( "ACTIVE","Active" ) );
			personalDetailsBM.setTitle( new CodeAndDescription("MR","MR.") );
			personalDetailsBM.setFirstName( "Rohit" );
			personalDetailsBM.setMiddleName( null );
			personalDetailsBM.setLastName( "Sharma" );
			personalDetailsBM.setGender( new CodeAndDescription( "MALE","Male") );
			personalDetailsBM.setPatientRating( new CodeAndDescription( "GOLD","Gold") );
			personalDetailsBM.setPatientCategory( new CodeAndDescription( "SELF","Self") );
			Calendar dateOfBirth =  Calendar.getInstance();
			dateOfBirth.clear();
			dateOfBirth.set(1985, 7, 5);
			personalDetailsBM.setDateOfBirth( dateOfBirth.getTime());
			personalDetailsAdditionalBM.setFatherHusband( "A.K. Sharma" );
			personalDetailsAdditionalBM.setBloodDonorId( "123456789" );
			personalDetailsAdditionalBM.setHeight("171 cms");
			
			
			ContactDetailsBM permanentContactDetailsBM = new ContactDetailsBM();
			permanentContactDetailsBM.setContactDetailsCode(21);
			permanentContactDetailsBM.setFirstName("Rohit");
			permanentContactDetailsBM.setLastName("Sharma");
			permanentContactDetailsBM.setGender( new CodeAndDescription("MALE","M") );
			permanentContactDetailsBM.setEmail( "rohit.sh@gmail.com");
			permanentContactDetailsBM.setPersonelId(new Integer(1));
			permanentContactDetailsBM.setForEntity(new CodeAndDescription("PATIENT","PATIENT"));
			permanentContactDetailsBM.setContactType(new CodeAndDescription("PERMANENT","PERMANENT"));
			
			contactDetailList.add(permanentContactDetailsBM);
			contactBM.setContactDetailList(contactDetailList);
			
			List<PatientAllergiesBM> otherAllergiesBMList = new ArrayList<PatientAllergiesBM>();
			PatientAllergiesBM otherAllergiesBM = new PatientAllergiesBM();
			otherAllergiesBM.setAllergyCode("1");
			otherAllergiesBM.setAllergyDescrption("A& D Emollient");
			PatientAllergiesBM otherAllergiesBM2 = new PatientAllergiesBM();
			otherAllergiesBM2.setAllergyCode("2");
			otherAllergiesBM2.setAllergyDescrption("A& D Zinc Oxide Cream");
			
			otherAllergiesBMList.add(otherAllergiesBM);
			otherAllergiesBMList.add(otherAllergiesBM2);
			
			//otherDetailsBM.setAllergiesList(otherAllergiesBMList);
			
			patientInfoDetailBM.setPersonalDetailsBM(personalDetailsBM);
			patientInfoDetailBM.setPersonalDetailsAdditionalBM( personalDetailsAdditionalBM );
			patientInfoDetailBM.setOtherDetailsBM( otherDetailsBM );
			patientInfoDetailBM.setContacts(contactBM);
			
			patientManager.modifyPatientDetails(patientInfoDetailBM);
			
			assertTrue(true);
		}catch(Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}


	@Test
	public void testGetPatientDetailsInteger() {
		try{
			PatientInfoDetailBM patientInfoDetailBM = patientManager.getPatientInfoDetailBM(new Integer(4));
			System.out.println("TestPatientManager.testGetPatientDetails()"+patientInfoDetailBM.getPersonalDetailsBM().getDateOfBirth());
			assertTrue(true);
		}catch(Exception exception){
			exception.printStackTrace();
			assertFalse(true);
		}
	}
	
	@Test
	public void testGetPatientDetailsIntegerDoesNotExist() {
		try{
//			This patient id does not exist com the database
			Integer patientId = new Integer(12);
			PatientInfoDetailBM patientInfoDetailBM = patientManager.getPatientInfoDetailBM(patientId);
			System.out.println("TestPatientManager.testGetPatientDetailsIntegerDoesNotExist()"+patientInfoDetailBM.getPersonalDetailsBM().getDateOfBirth());
			assertFalse(true);
		}catch(Exception exception){
			exception.printStackTrace();
			assertTrue( true);
		}
	}
	
	@Test
	public void testGetPatientsNoParameters() 
	{
		try
		{
			List<PatientInfoSummaryBM> patientInfoSummaryBMList = patientManager.getPatients(	null, 
																								null, 
																								null, 
																								null, 
																								null, 
																								null, 
																								null, 
																								null, 
																								null, 
																								null, 
																								null, 
																								null, null );
//			System.out.println("TestPatientManagerImpl.testGetPatients()" + patientInfoSummaryBMList.get(0));
			Util.dumpBean(patientInfoSummaryBMList);
			assertTrue(true);
		}catch( Exception exception ) {
			exception.printStackTrace();
			assertFalse(true);
		}
	}
	
	@Test
	public void testGetPatientsWithOptionalNumberOfParameters() 
	{
		try
		{
			List<PatientInfoSummaryBM> patientInfoSummaryBMList = patientManager.getPatients(	null, 
																								null, 
																								null, 
																								null, 
																								null, 
																								null, 
																								null, 
																								null, 
																								null, 
																								null, 
																								null, 
																								null, null );
//			System.out.println("TestPatientManagerImpl.testGetPatients()" + patientInfoSummaryBMList.get(0));
			Util.dumpBean(patientInfoSummaryBMList);
			assertTrue(true);
		}catch( Exception exception ) {
			exception.printStackTrace();
			assertFalse(true);
		}
	}
}
