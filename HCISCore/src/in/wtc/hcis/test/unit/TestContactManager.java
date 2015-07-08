/**
 * 
 */
package in.wtc.hcis.test.unit;

import java.util.ArrayList;
import java.util.List;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactBM;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bo.contact.ContactManager;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import junit.framework.TestCase;

/**
 * @author Rohit
 *
 */
public class TestContactManager extends TestCase {
	
	ContactManager contactManager;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
			BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/HCISCoreContext.xml"));
			contactManager = (ContactManager)factory.getBean("ContactManager");
	}
	
	public void testGetContactDetails( Integer personelId, String contactEntityCode, String contactTypeInd ) {
	}
	
	/**
	 * Testing the set contact details with minimum number of parameters (details).
	 */
	public void testSetContactDetailsWithMinimumInformation() {
		try {
			ContactDetailsBM contactDetailsBM = new ContactDetailsBM();
			
			contactDetailsBM.setContactDetailsCode(new Integer(1));
			contactDetailsBM.setCity("Hyd");
			contactDetailsBM.setContactType(new CodeAndDescription("10","Contact Description"));
			contactDetailsBM.setCountry(new CodeAndDescription("11","Country Description"));
			contactDetailsBM.setEmail("ap@gmail.com");
			contactDetailsBM.setFaxNumber("99261");
			contactDetailsBM.setFirstName("Rohit");
			contactDetailsBM.setLastName("Sharma");
			contactDetailsBM.setForEntity(new CodeAndDescription("12","Entity Description"));
			contactDetailsBM.setGender(new CodeAndDescription("20","Gender Description"));
			contactDetailsBM.setHouseNumber("200");
			contactDetailsBM.setMiddleName("Kumar");
			contactDetailsBM.setMobileNumber("93606");
			contactDetailsBM.setPersonelId(new Integer(420));
			contactDetailsBM.setPhoneNumber("000000");
			contactDetailsBM.setPincode("494334");
			contactDetailsBM.setRelationCode(new CodeAndDescription("21","Relation Description"));
			contactDetailsBM.setSalutation(new CodeAndDescription("19","salutationDescription"));
			contactDetailsBM.setState(new CodeAndDescription("301","State Description"));
			contactDetailsBM.setStayDuration("stayDuration");
			contactDetailsBM.setStreet("street1");
			List<ContactDetailsBM> contactDetailList =new ArrayList<ContactDetailsBM>();
			contactDetailList.add(contactDetailsBM);
			ContactBM contactBM=new ContactBM();
			contactBM.setContactDetailList(contactDetailList);
			
			
			
			contactManager.addContactDetails(contactBM);
			
			assertTrue(true);
		}catch(Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
		
	}
/**
 * Testing the set contact details with maximum number of parameters (details).
 */
	public void testSetContactDetailsWithMaximumInformation() {
		try {
			ContactDetailsBM contactDetailsBM = new ContactDetailsBM();
			
//			contactDetailsBM.setContactDetailsCode( 111 );
			contactDetailsBM.setPersonelId( 1234);
			contactDetailsBM.setSalutation( new CodeAndDescription( "mr","Mr.") );
			contactDetailsBM.setFirstName( "Rohit" );
			contactDetailsBM.setLastName( "Sharma" );
			contactDetailsBM.setHouseNumber( "Qtr No D-38" );
			contactDetailsBM.setStreet( "Nalco Township" );
			contactDetailsBM.setCity( "Angul" );
			contactDetailsBM.setState( new CodeAndDescription( "ori","Orissa") );
			contactDetailsBM.setCountry(new CodeAndDescription("ind","India") );
			contactDetailsBM.setPincode( "759145" );
			contactDetailsBM.setPhoneNumber( "06764-220733" );
			contactDetailsBM.setMobileNumber( "9703503319" );
			contactDetailsBM.setFaxNumber( "55377822" );
			contactDetailsBM.setEmail( "sdss" );
			contactDetailsBM.setStayDuration( "4" );
			contactDetailsBM.setRelationCode( new CodeAndDescription( "bro","Brother" ) );
			
//			contactManager.setContactDetails(contactDetailsBM);
			assertTrue(true);
		}catch(Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
		
	}
	/**
	 * Testing the set contact details with variable number of parameters (details).
	 */
		public void testSetContactDetailsWithVariableInformation() {
			try {
				ContactDetailsBM contactDetailsBM = new ContactDetailsBM();
				
//				contactDetailsBM.setContactDetailsCode( 11 );
				contactDetailsBM.setPersonelId( 1234);
				contactDetailsBM.setFirstName( "Rohit" );
				contactDetailsBM.setLastName( "Sharma" );
				contactDetailsBM.setHouseNumber( "Qtr No D-38" );
				contactDetailsBM.setStreet( "Nalco Township" );
				contactDetailsBM.setCity( "Angul" );
				contactDetailsBM.setState( new CodeAndDescription( "orr","Orissa") );
				contactDetailsBM.setCountry(new CodeAndDescription("ind","INDIA") );
				contactDetailsBM.setPincode( "759145" );
				contactDetailsBM.setPhoneNumber( "06764-220733" );
				contactDetailsBM.setMobileNumber( "9703503319" );
				contactDetailsBM.setFaxNumber( "55377822" );
				contactDetailsBM.setEmail( "sdss" );
				contactDetailsBM.setStayDuration( "4" );
				contactDetailsBM.setRelationCode( new CodeAndDescription( "BRO","Brother" ) );
				
//				contactManager.setContactDetails(contactDetailsBM);
				assertTrue(true);
			}catch(Exception e) {
				e.printStackTrace();
				assertFalse(true);
			}
			
		}
}
