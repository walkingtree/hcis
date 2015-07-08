/**
 * 
 */
package in.wtc.hcis.test.unit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.DoctorBM;
import in.wtc.hcis.bm.base.DoctorDetailBM;
import in.wtc.hcis.bm.base.DoctorEspecialtyBM;
import in.wtc.hcis.bm.base.DoctorLiteBM;
import in.wtc.hcis.bm.base.DoctorSummaryBM;
import in.wtc.hcis.bo.doctor.DoctorManager;
import in.wtc.hcis.bo.roster.RosterManager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import com.wtc.hcis.da.DoctorDetail;

/**
 * @author Vinay Kurudi
 *
 */
public class TestDoctorManagerImpl {

	DoctorManager doctorManager;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {
			String[] ctxFileArr = {"applicationContext.xml","applicationContextReport.xml","AccountantContextDA.xml", "AccountantContextBL.xml", "HCISCoreContext.xml"};
			ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);
			doctorManager = (DoctorManager)ctx.getBean("DoctorManager");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.doctor.DoctorManagerImpl#addDoctor(in.wtc.hcis.bm.base.DoctorBM)}.
	 */
	@Test
	public void testAddDoctor() {
		try {
			DoctorBM doctorBM = new DoctorBM();
			doctorBM.setActive(true);
			doctorBM.setFirstName("Vinay");
			doctorBM.setLastName("Shetty");
			doctorBM.setMiddleName("Kurudi");
			doctorBM.setSaluationCode(new CodeAndDescription("MR","Mr."));

			DoctorDetailBM doctorDetailsBM = new DoctorDetailBM();
			doctorDetailsBM.setActive(true);
			doctorDetailsBM.setBloodDonorId("Ajit");
			doctorDetailsBM.setBloodGroup(new CodeAndDescription("ANEG","A-ve"));
			doctorDetailsBM.setDob(Calendar.getInstance().getTime());
			doctorDetailsBM.setDutyEndTime("18:00:00");
			doctorDetailsBM.setDutyStartTime("8:00:00");
			doctorDetailsBM.setFatherHusbandName("Sathya");
			doctorDetailsBM.setGender(new CodeAndDescription("MALE","Male"));
			doctorDetailsBM.setFirstName("Vinay");
			doctorDetailsBM.setHeight("165");
			doctorDetailsBM.setIdNumber("121");
			doctorDetailsBM.setIdProof(new CodeAndDescription("DRIVING","Driving Licence"));
			doctorDetailsBM.setPermanent(true);
			doctorBM.setDoctorDetail(doctorDetailsBM);
			
			List<ContactDetailsBM> conList = new ArrayList<ContactDetailsBM>();
			ContactDetailsBM contactDetailsBM = new ContactDetailsBM();
			contactDetailsBM.setCity("Hyd");
			contactDetailsBM.setContactType(new CodeAndDescription("CURRENT","CURRENT"));
			contactDetailsBM.setCountry(new CodeAndDescription("IN","India"));
			contactDetailsBM.setEmail("vinaykurudi@gmail.com");
			contactDetailsBM.setFaxNumber("123141551");
			contactDetailsBM.setForEntity(new CodeAndDescription("DOCTOR","DOCTOR"));
			contactDetailsBM.setHouseNumber("19/10");
			contactDetailsBM.setFirstName("Vinay");
			contactDetailsBM.setGender(new CodeAndDescription("MALE","Male"));
			contactDetailsBM.setMobileNumber("9985566410");
			contactDetailsBM.setSalutation(new CodeAndDescription("MR","Mr."));
			contactDetailsBM.setPincode("515565");
			contactDetailsBM.setStreet("Main Bazar");
			contactDetailsBM.setStayDuration("20Y");
			contactDetailsBM.setRelationCode(new CodeAndDescription("BROTHER","Brother"));
			conList.add(contactDetailsBM);
			
			ContactDetailsBM contactDetailsBMParmanent = new ContactDetailsBM();
			contactDetailsBMParmanent.setFirstName("Vinay");
			contactDetailsBMParmanent.setGender(new CodeAndDescription("MALE","Male"));
			contactDetailsBMParmanent.setMobileNumber("9985566410");
			contactDetailsBMParmanent.setSalutation(new CodeAndDescription("MR","Mr."));
			contactDetailsBMParmanent.setPincode("515565");
			contactDetailsBMParmanent.setStreet("Main Bazar");
			contactDetailsBMParmanent.setStayDuration("20Y");
			contactDetailsBMParmanent.setRelationCode(new CodeAndDescription("BROTHER","Brother"));
			contactDetailsBMParmanent.setCity("Hyd");
			contactDetailsBMParmanent.setContactType(new CodeAndDescription("PERMANENT","PERMANENT"));
			contactDetailsBMParmanent.setCountry(new CodeAndDescription("IN","India"));
			contactDetailsBMParmanent.setEmail("vinaykurudi@gmail.com");
			contactDetailsBMParmanent.setFaxNumber("123141551");
			contactDetailsBMParmanent.setForEntity(new CodeAndDescription("DOCTOR","DOCTOR"));
			contactDetailsBMParmanent.setHouseNumber("19/10");
			conList.add(contactDetailsBMParmanent);
			
			doctorBM.setContactDetailList(conList);
			
			DoctorEspecialtyBM doctorEspecialtyBM = new DoctorEspecialtyBM();
			doctorEspecialtyBM.setActive(true);
			doctorEspecialtyBM.setConsultationCharge(new Double(350.25));
			doctorEspecialtyBM.setDepartmentCode(new CodeAndDescription("CARDIOLOGY","Cardialogy"));
			doctorEspecialtyBM.setEspecialtyCode(new CodeAndDescription("CARDIOLOGISTS","Cardiologists"));
			doctorEspecialtyBM.setJoiningDt(Calendar.getInstance().getTime());
			doctorEspecialtyBM.setLeavingDt(Calendar.getInstance().getTime());
//			doctorEspecialtyBM.setRoomCode(roomCode)
			List<DoctorEspecialtyBM> doctorEspecialtyList = new ArrayList<DoctorEspecialtyBM>();
			doctorEspecialtyList.add(doctorEspecialtyBM);
			
			doctorBM.setDoctorEspecialtyList(doctorEspecialtyList);
			
			doctorManager.addDoctor(doctorBM);
			assertTrue(true);
		}catch(Exception exception) {
			exception.printStackTrace();
			assertFalse(true);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.doctor.DoctorManagerImpl#findDoctors(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.Double, java.util.Date, java.util.Date)}.
	 */
	@Test
	public void testFindDoctors() {
		try {
			List<DoctorSummaryBM> doctList = doctorManager.findDoctors(null, null, null, null, null, null, null, null, null, 0, 9999, null);
			Util.dumpBean(doctList);
			assertTrue(true);
		} catch (Exception e) {
                 e.printStackTrace();
                 assertFalse(true);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.doctor.DoctorManagerImpl#getDoctorAddress(java.lang.Integer)}.
	 */
	@Test
	public void testGetDoctorAddress() {
		try {
			doctorManager.getDoctorAddress(new Integer(11));
			assertTrue(true);
		} catch (Exception e) {
          e.printStackTrace();
          assertFalse(true);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.doctor.DoctorManagerImpl#getDoctorDetail(java.lang.Integer)}.
	 */
	@Test
	public void testGetDoctorDetail() {
           try {
			DoctorBM doctorBm =doctorManager.getDoctorDetail(new Integer(14));
			Util.dumpBean(doctorBm);
			assertTrue(true);
		} catch (Exception e) {
                e.printStackTrace();
                assertFalse(true);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.doctor.DoctorManagerImpl#getDoctorLiteInfo(java.lang.Integer)}.
	 */
	@Test
	public void testGetDoctorLiteInfo() {
		try {
			doctorManager.getDoctorLiteInfo(new Integer(11));
			assertTrue(true);
		} catch (Exception e) {
                e.printStackTrace();
                assertFalse(true);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.doctor.DoctorManagerImpl#modifyDoctor(in.wtc.hcis.bm.base.DoctorBM)}.
	 */
	@Test
	public void testModifyDoctor() {
             try {
            	DoctorBM doctorBM = new DoctorBM();
            	doctorBM.setDoctorId(new Integer("11"));
     			doctorBM.setActive(true);
     			doctorBM.setFirstName("Vinnu");
     			doctorBM.setLastName("Shetty");
     			doctorBM.setMiddleName("Kurudi");
     			doctorBM.setSaluationCode(new CodeAndDescription("mr","Mr."));

     			DoctorDetailBM doctorDetailsBM = new DoctorDetailBM();
     			doctorDetailsBM.setDoctor(new CodeAndDescription("11",null));
     			doctorDetailsBM.setActive(true);
     			doctorDetailsBM.setBloodDonorId("Ajit");
     			doctorDetailsBM.setBloodGroup(new CodeAndDescription("aneg","A-ve"));
     			doctorDetailsBM.setDob(Calendar.getInstance().getTime());
     			doctorDetailsBM.setDutyEndTime("18:00:00");
     			doctorDetailsBM.setDutyStartTime("8:00:00");
     			doctorDetailsBM.setFatherHusbandName("Sathya");
     			doctorDetailsBM.setGender(new CodeAndDescription("m","Male"));
     			doctorDetailsBM.setFirstName("Vinay");
     			doctorDetailsBM.setHeight("165");
     			doctorDetailsBM.setIdNumber("121");
     			doctorDetailsBM.setIdProof(new CodeAndDescription("dl","Driving Licence"));
     			doctorDetailsBM.setPermanent(true);
     			doctorBM.setDoctorDetail(doctorDetailsBM);
     			
     			ContactDetailsBM contactDetailsBM = new ContactDetailsBM();
     			contactDetailsBM.setContactDetailsCode(new Integer("6"));
     			contactDetailsBM.setPersonelId(new Integer("11"));
     			contactDetailsBM.setCity("Hyd");
     			contactDetailsBM.setContactType(new CodeAndDescription("CURRENT","CURRENT"));
     			contactDetailsBM.setCountry(new CodeAndDescription("ind","India"));
     			contactDetailsBM.setEmail("vinaykurudi@gmail.com");
     			contactDetailsBM.setFaxNumber("123141551");
     			contactDetailsBM.setForEntity(new CodeAndDescription("DOCTOR","DOCTOR"));
     			contactDetailsBM.setHouseNumber("19/10");
     			List<ContactDetailsBM> conList = new ArrayList<ContactDetailsBM>();
     			conList.add(contactDetailsBM);
     			
     			doctorBM.setContactDetailList(conList);
     			
     			DoctorEspecialtyBM doctorEspecialtyBM = new DoctorEspecialtyBM();
     			doctorEspecialtyBM.setActive(true);
     			doctorEspecialtyBM.setConsultationCharge(new Double(350.25));
     			doctorEspecialtyBM.setDepartmentCode(new CodeAndDescription("1","Cardialogy"));
     			doctorEspecialtyBM.setEspecialtyCode(new CodeAndDescription("1","Cardiologists"));
     			doctorEspecialtyBM.setJoiningDt(Calendar.getInstance().getTime());
     			doctorEspecialtyBM.setLeavingDt(Calendar.getInstance().getTime());
//     			doctorEspecialtyBM.setRoomCode(roomCode)
     			List<DoctorEspecialtyBM> doctorEspecialtyList = new ArrayList<DoctorEspecialtyBM>();
     			doctorEspecialtyList.add(doctorEspecialtyBM);
     			
     			doctorBM.setDoctorEspecialtyList(doctorEspecialtyList);
     			doctorManager.modifyDoctor(doctorBM);
				assertTrue(true);
			} catch (Exception e) {
                e.printStackTrace();
                assertFalse(true);
			}
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.doctor.DoctorManagerImpl#removeDoctor(java.util.List)}.
	 */
	@Test
	public void testRemoveDoctor() {
		try {
			List<Integer> doctorIdList = new ArrayList<Integer>();
			doctorIdList.add(new Integer("11"));
			doctorIdList.add(new Integer("9"));
			doctorManager.removeDoctor(doctorIdList);
			assertTrue(true);
		} catch (Exception e) {
           e.printStackTrace();
           assertFalse(true);
		}
	}
	/**
	 * Test method for {@link in.wtc.hcis.bo.doctor.DoctorManagerImpl#getConsultationCharge(java.lang.Integer)}.
	 */
	@Test
	public void testGetConsultationCharge() {
		try {
			Double consultationCharges = doctorManager.getConsultationCharge(new Integer("2"));
			Util.dumpBean(consultationCharges);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(false);
		}
	}

}
