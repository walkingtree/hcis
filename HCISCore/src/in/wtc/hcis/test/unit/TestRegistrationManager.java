package in.wtc.hcis.test.unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactBM;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.OtherDetailsBM;
import in.wtc.hcis.bm.base.PatientInfoDetailBM;
import in.wtc.hcis.bm.base.PatientInfoSummaryBM;
import in.wtc.hcis.bm.base.PersonalDetailsAdditionalBM;
import in.wtc.hcis.bm.base.PersonalDetailsBM;
import in.wtc.hcis.bm.base.SponsorsBM;
import in.wtc.hcis.bo.registration.RegistrationManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestRegistrationManager {

	RegistrationManager registrationManager;
	@Before
	public void setUp() throws Exception {
		try {
			String[] ctxFileArr = {"applicationContext.xml","HCISCoreContext.xml"};
			ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);

			registrationManager = (RegistrationManager)ctx.getBean("RegistrationManager");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Test
	public void testRegisterWithMinimumPatientDetailsFromUI() {
		try {
			PatientInfoDetailBM patientInfoDetailBM = new PatientInfoDetailBM();
			PersonalDetailsBM personalDetailsBM = new PersonalDetailsBM();
			PersonalDetailsAdditionalBM personalDetailsAdditionalBM = new PersonalDetailsAdditionalBM();
			ContactBM contactBM = new ContactBM();
			SponsorsBM sponsorsBM = new SponsorsBM();
			OtherDetailsBM otherDetailsBM = new OtherDetailsBM();
			
			personalDetailsBM.setRegistrationDate( Calendar.getInstance().getTime() );
			personalDetailsBM.setRegistrationType( new CodeAndDescription( "EMERGENCY","Emergency" ) );
			personalDetailsBM.setRegistrationStatus( new CodeAndDescription( "ACTIVE","Active" ) );
			personalDetailsBM.setFirstName( "Rohit" );
			personalDetailsBM.setPatientRating( new CodeAndDescription( "REGULAR","Regular") );
			personalDetailsBM.setPatientCategory( new CodeAndDescription( "SELF","Self") );
			
			patientInfoDetailBM.setPersonalDetailsBM(personalDetailsBM);
			patientInfoDetailBM.setPersonalDetailsAdditionalBM( personalDetailsAdditionalBM );
			patientInfoDetailBM.setContacts( contactBM );
			patientInfoDetailBM.setOtherDetailsBM( otherDetailsBM );
			
			registrationManager.registerEmergencyPatient(patientInfoDetailBM);
			
			assertTrue(true);
		}catch(Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testRegisterEmergencyPatient() {
		try {
			PatientInfoDetailBM patientInfoDetailBM = new PatientInfoDetailBM();
			PersonalDetailsBM personalDetailsBM = new PersonalDetailsBM();
			PersonalDetailsAdditionalBM personalDetailsAdditionalBM = new PersonalDetailsAdditionalBM();
			ContactBM contactBM = new ContactBM();
			SponsorsBM sponsorsBM = new SponsorsBM();
			OtherDetailsBM otherDetailsBM = new OtherDetailsBM();
			
//			personalDetailsBM.setRegistrationNumber(1233);
			personalDetailsBM.setRegistrationDate( Calendar.getInstance().getTime() );
			personalDetailsBM.setRegistrationType( new CodeAndDescription( "EMERGENCY","Emergency" ) );
			personalDetailsBM.setRegistrationStatus( new CodeAndDescription( "ACTIVE","Active" ) );
			personalDetailsBM.setFirstName( "Rohit" );
			personalDetailsBM.setGender( new CodeAndDescription( "MALE","Male") );
			personalDetailsBM.setPatientRating( new CodeAndDescription( "REGULAR","Regular") );
			personalDetailsBM.setPatientCategory( new CodeAndDescription( "SELF","Self") );
			Calendar dateOfBirth =  Calendar.getInstance();
			dateOfBirth.clear();
			dateOfBirth.set(1985, 7, 5);
			personalDetailsBM.setDateOfBirth( dateOfBirth.getTime() );
			
//			personalDetailsAdditionalBM.setReferredBy( new CodeAndDescription( "11111","Kamla Rathi") );
			
//			List<OtherAllergiesBM> otherAllergiesBMList = new ArrayList<OtherAllergiesBM>();
//			OtherAllergiesBM otherAllergiesBM = new OtherAllergiesBM();
//			otherAllergiesBM.setAllergyCode("11111");
//			otherAllergiesBM.setAllergyDescrption("Itchy ears");
//			OtherAllergiesBM otherAllergiesBM2 = new OtherAllergiesBM();
//			otherAllergiesBM2.setAllergyCode("11112");
//			otherAllergiesBM2.setAllergyDescrption("Itchy throat");
//			
//			otherAllergiesBMList.add(otherAllergiesBM);
//			otherAllergiesBMList.add(otherAllergiesBM2);
//			
//			otherDetailsBM.setOtherAllergiesList(otherAllergiesBMList);
			
			patientInfoDetailBM.setPersonalDetailsBM(personalDetailsBM);
			patientInfoDetailBM.setPersonalDetailsAdditionalBM( personalDetailsAdditionalBM );
			patientInfoDetailBM.setContacts( contactBM );
			patientInfoDetailBM.setOtherDetailsBM( otherDetailsBM );
			
			registrationManager.registerEmergencyPatient(patientInfoDetailBM);
			
			assertTrue(true);
		}catch(Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testRegisterPatientWithMaximumDetails() {
		try {
			PatientInfoDetailBM patientInfoDetailBM = new PatientInfoDetailBM();
			
			ContactBM contacts = new ContactBM();
			PersonalDetailsBM personalDetailsBM = new PersonalDetailsBM();
			PersonalDetailsAdditionalBM personalDetailsAdditionalBM = new PersonalDetailsAdditionalBM();
			SponsorsBM sponsorsBM = new SponsorsBM();
			OtherDetailsBM otherDetailsBM = new OtherDetailsBM();
			
//--------------------------------------------------------
//to add the details of the patient
//--------------------------------------------------------
			personalDetailsBM.setRegistrationDate( Calendar.getInstance().getTime() );
			personalDetailsBM.setRegistrationType( new CodeAndDescription( "NORMAL","Normal" ) );
			personalDetailsBM.setRegistrationStatus( new CodeAndDescription( "ACTIVE","Active" ) );
			personalDetailsBM.setTitle( new CodeAndDescription("MR","MR.") );
			personalDetailsBM.setFirstName( "Rohit" );
			personalDetailsBM.setMiddleName( null );
			personalDetailsBM.setLastName( "Sharma" );
			personalDetailsBM.setGender( new CodeAndDescription( "MALE","Male") );
			personalDetailsBM.setPatientRating( new CodeAndDescription( "REGULAR","Regular") );
			personalDetailsBM.setPatientCategory( new CodeAndDescription( "SELF","Self") );
			
			Calendar dateOfBirth =  Calendar.getInstance();
			dateOfBirth.clear();
			dateOfBirth.set(1985, 7, 5);
			personalDetailsBM.setDateOfBirth( dateOfBirth.getTime());
			
			personalDetailsAdditionalBM.setMaritalStatus( new CodeAndDescription( "SINGLE","Single" ) );
			personalDetailsAdditionalBM.setFatherHusband( null );
			personalDetailsAdditionalBM.setIdProof( new CodeAndDescription( "PASSPORT","Passport") );
			personalDetailsAdditionalBM.setIdNumber( "38723" );
			Calendar idValidUpto =  Calendar.getInstance();
			idValidUpto.clear();
			idValidUpto.set(2015, 7, 5);
			personalDetailsAdditionalBM.setIdValidUpto( idValidUpto.getTime() );
			personalDetailsAdditionalBM.setVisaNumber( "12323" );
			Calendar visaValidUpto =  Calendar.getInstance();
			visaValidUpto.clear();
			visaValidUpto.set(2015, 7, 5);
			personalDetailsAdditionalBM.setVisaValidUpto( visaValidUpto.getTime() );
			personalDetailsAdditionalBM.setNationality( new CodeAndDescription( "IN","Indian" ) );
			personalDetailsAdditionalBM.setPatientOccupation( "Software Engineer" );
			personalDetailsAdditionalBM.setMonthlyIncome( "111" );
			personalDetailsAdditionalBM.setHeight( "167 cms" );
			personalDetailsAdditionalBM.setWeight( "74 kgs" );
			personalDetailsAdditionalBM.setBloodDonorId("12324334");
			personalDetailsAdditionalBM.setBloodGroup( new CodeAndDescription( "APOS","A +ve" ) );
			personalDetailsAdditionalBM.setReligion( new CodeAndDescription( "HINDU","Hindu" ) );
			personalDetailsAdditionalBM.setMotherTongue( null );
			personalDetailsAdditionalBM.setReferredBy( new CodeAndDescription( "11111","Kamal Rathi" ) );
			
//--------------------------------------------------------
//to add the current address of the patient
//--------------------------------------------------------			
			List<ContactDetailsBM> contactDetails = new ArrayList<ContactDetailsBM>(0);
			
			ContactDetailsBM currentContactDetails = new ContactDetailsBM();
			
			currentContactDetails.setSalutation( new CodeAndDescription( "MR","Mr.") );
			currentContactDetails.setForEntity(new CodeAndDescription("PATIENT","PATIENT"));
			currentContactDetails.setContactType(new CodeAndDescription("CURRENT","CURRENT"));
			currentContactDetails.setFirstName( "Rohit" );
			currentContactDetails.setLastName( "Sharma" );
			currentContactDetails.setHouseNumber( "Qtr No D-38" );
			currentContactDetails.setStreet( "Nalco Township" );
			currentContactDetails.setCity( "Angul" );
			currentContactDetails.setState( new CodeAndDescription( "OR","Orissa") );
			currentContactDetails.setCountry(new CodeAndDescription("IN","INDIA") );
			currentContactDetails.setPincode( "759145" );
			currentContactDetails.setPhoneNumber( "06764-220733" );
			currentContactDetails.setMobileNumber( "9703503319" );
			currentContactDetails.setFaxNumber( "55377822" );
			currentContactDetails.setEmail( "sdss" );
			currentContactDetails.setStayDuration( "4" );
			
//--------------------------------------------------------
//to add the permanent address of the patient
//--------------------------------------------------------			
			ContactDetailsBM permanentContactDetails = new ContactDetailsBM();

			permanentContactDetails.setSalutation( new CodeAndDescription( "MR","Mr.") );
			permanentContactDetails.setForEntity(new CodeAndDescription("PATIENT","PATIENT"));
			permanentContactDetails.setContactType(new CodeAndDescription("PERMANENT", "PERMANENT"));
			permanentContactDetails.setFirstName( "Kamal" );
			permanentContactDetails.setLastName( "Rathi" );
			permanentContactDetails.setHouseNumber( "Qtr No D-38" );
			permanentContactDetails.setStreet( "West Maredpally" );
			permanentContactDetails.setCity( "Secunderabad" );
			permanentContactDetails.setState( new CodeAndDescription( "AP","Andhra Pradesh") );
			permanentContactDetails.setCountry(new CodeAndDescription("IN","India") );
			permanentContactDetails.setPincode( "500072" );
			permanentContactDetails.setPhoneNumber( "06764-220733" );
			permanentContactDetails.setMobileNumber( "9703503319" );
			permanentContactDetails.setFaxNumber( "55377822" );
			permanentContactDetails.setEmail( "kamal.osho@gmail.com" );
			permanentContactDetails.setStayDuration( "4" );

//--------------------------------------------------------
//to add the emergency primary address of the patient
//--------------------------------------------------------			
			ContactDetailsBM primaryEmergencyContactDetails	= new ContactDetailsBM();
			
			primaryEmergencyContactDetails.setSalutation( new CodeAndDescription( "MR","Mr.") );
			primaryEmergencyContactDetails.setForEntity(new CodeAndDescription("PATIENT","PATIENT"));
			primaryEmergencyContactDetails.setContactType(new CodeAndDescription("EMERGENCY_PRIMARY","EMERGENCY_PRIMARY"));
			primaryEmergencyContactDetails.setFirstName( "Ajit" );
			primaryEmergencyContactDetails.setLastName( "Kumar" );
			primaryEmergencyContactDetails.setHouseNumber( "Qtr No D-38" );
			primaryEmergencyContactDetails.setStreet( "West Maredpally" );
			primaryEmergencyContactDetails.setCity( "Secunderabad" );
			primaryEmergencyContactDetails.setState( new CodeAndDescription( "AP","Andhra Pradesh") );
			primaryEmergencyContactDetails.setCountry(new CodeAndDescription("IN","India") );
			primaryEmergencyContactDetails.setPincode( "500072" );
			primaryEmergencyContactDetails.setPhoneNumber( "06764-220733" );
			primaryEmergencyContactDetails.setMobileNumber( "9703503319" );
			primaryEmergencyContactDetails.setFaxNumber( "55377822" );
			primaryEmergencyContactDetails.setEmail( "ajit.kumar.azad@gmail.com" );
			primaryEmergencyContactDetails.setStayDuration( "4" );
			primaryEmergencyContactDetails.setRelationCode( new CodeAndDescription( "EMPLOYER","Employer" ) );

			contactDetails.add( currentContactDetails );
			contactDetails.add( permanentContactDetails );
			contactDetails.add( primaryEmergencyContactDetails );
			
			contacts.setContactDetailList(contactDetails);
			
			patientInfoDetailBM.setPersonalDetailsBM( personalDetailsBM );
			patientInfoDetailBM.setPersonalDetailsAdditionalBM( personalDetailsAdditionalBM );
			patientInfoDetailBM.setOtherDetailsBM( otherDetailsBM );
			patientInfoDetailBM.setContacts(contacts);
			
			registrationManager.registerPatient( patientInfoDetailBM );
			
			System.out.println("TestRegistrationManager.testRegisterPatientWithMaximumDetails()"+ patientInfoDetailBM.getPersonalDetailsBM().getDateOfBirth());
			assertTrue(true);
		}catch(Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
		
	}

}
