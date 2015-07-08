/**
 * 
 */
package in.wtc.hcis.test.unit;

import static org.junit.Assert.*;

import java.util.List;

import in.wtc.hcis.bm.ip.DiseaseBM;
import in.wtc.hcis.bo.admin.AdminManager;
import in.wtc.hcis.bo.contact.ContactManager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Bhavesh
 *
 */
public class AdminManagerImplTest {

	AdminManager adminManager;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//Amazing! but its working
		String[] ctxFileArr = {"in\\wtc\\hcis\\test\\unit\\applicationContextTest.xml","AccountantContextBLTest.xml","applicationContextReport.xml","HCISCoreContext.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);
		adminManager = (AdminManager)ctx.getBean("AdminManager");
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.admin.AdminManagerImpl#addDisease(in.wtc.hcis.bm.ip.DiseaseBM)}.
	 */
	@Test
	public void testAddDisease() {
//		adminManager.addDisease(diseaseBM);
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.admin.AdminManagerImpl#getDisease(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetDisease() {
		try {
			List<DiseaseBM> diseaseBMList = adminManager.getDisease("", "", "", "");
			for( DiseaseBM diseaseBM : diseaseBMList )
				Util.dumpBean(diseaseBM);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.admin.AdminManagerImpl#modifyDisease(in.wtc.hcis.bm.ip.DiseaseBM)}.
	 */
	@Test
	public void testModifyDisease() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.admin.AdminManagerImpl#removeDiseases(java.util.List)}.
	 */
	@Test
	public void testRemoveDiseases() {
		fail("Not yet implemented");
	}

}
