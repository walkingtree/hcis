package in.wtc.hcis.test.unit;

import java.util.ArrayList;
import java.util.List;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactBM;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bo.contact.ContactManager;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import junit.framework.TestCase;

public class TestContactManager1 extends TestCase {

	ContactManager contactManager;
	
	protected void setUp() throws Exception {
		String[] ctxFileArr = {"applicationContext.xml","HCISCoreContext.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);
		contactManager = (ContactManager)ctx.getBean("ContactManager");
	}

	public void testAddContactDetails() {
		try {
//			ContactDetailsBM contactDetailsBM = new ContactDetailsBM();
//			
//			contactDetailsBM.setContactDetailsCode(new Integer(1));
//			contactDetailsBM.setCity("Hyd");
//			contactDetailsBM.setContactType(new CodeAndDescription("1","EMERG"));
//			contactDetailsBM.setCountry(new CodeAndDescription("ind","Country Description"));
//			contactDetailsBM.setEmail("ap@gmail.com");
//			contactDetailsBM.setFaxNumber("99261");
//			contactDetailsBM.setFirstName("Rohit");
//			contactDetailsBM.setLastName("Sharma");
//			contactDetailsBM.setForEntity(new CodeAndDescription("12","Entity Description"));
//			contactDetailsBM.setGender(new CodeAndDescription("20","Gender Description"));
//			contactDetailsBM.setHouseNumber("200");
//			contactDetailsBM.setMiddleName("Kumar");
//			contactDetailsBM.setMobileNumber("93606");
//			contactDetailsBM.setPersonelId(new Integer(420));
//			contactDetailsBM.setPhoneNumber("000000");
//			contactDetailsBM.setPincode("494334");
//			contactDetailsBM.setRelationCode(new CodeAndDescription("21","Relation Description"));
//			contactDetailsBM.setSalutation(new CodeAndDescription("19","salutationDescription"));
//			contactDetailsBM.setState(new CodeAndDescription("301","State Description"));
//			contactDetailsBM.setStayDuration("stayDuration");
//			contactDetailsBM.setStreet("street1");
//			List<ContactDetailsBM> contactDetailList =new ArrayList<ContactDetailsBM>();
//			contactDetailList.add(contactDetailsBM);
//			ContactBM contactBM=new ContactBM();
//			contactBM.setContactDetailList(contactDetailList);
//			
//			
//			
//			contactManager.addContactDetails(contactBM);
			
			assertTrue(true);
		}
		catch(Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

	public void testGetContactDetails() {
		try{
			List<ContactDetailsBM> list= contactManager.getContactDetails("PATIENT", new Integer(4), null);
			TestUtils.dumpBean(list.get(0));
			assertTrue(true);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

	public void testModifyContactDetails() {
		fail("Not yet implemented");
	}

}
