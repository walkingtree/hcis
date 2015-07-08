/**
 * 
 */
package in.wtc.hcis.test.unit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ReferralBM;
import in.wtc.hcis.bm.base.ReferralCommissionBM;
import in.wtc.hcis.bm.base.ReferralLightBM;
import in.wtc.hcis.bo.admin.AdminManager;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.referral.ReferralManager;
import in.wtc.hcis.exceptions.HCISException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Bhavesh
 *
 */
public class TestReferralManagerImpl {

	/**
	 * @throws java.lang.Exception
	 */
	
	ReferralManager referralManager = null;
	@Before
	public void setUp() throws Exception {
		//Amazing! but its working
		String[] ctxFileArr = {"in\\wtc\\hcis\\test\\unit\\applicationContextTest.xml","AccountantContextBLTest.xml","applicationContextReport.xml","HCISCoreContext.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);
		referralManager = (ReferralManager)ctx.getBean("ReferralManager");
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.referral.ReferralManagerImpl#deleteReferral(java.lang.Integer)}.
	 */
	@Test
	public void testDeleteReferral() {
		
		try {
			referralManager.deleteReferral(8);
			assertTrue(true);
		} catch (HCISException e) {
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.referral.ReferralManagerImpl#getActiveReferrals(int, int, java.lang.String)}.
	 */
	@Test
	public void testGetActiveReferralsIntIntString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.referral.ReferralManagerImpl#getActiveReferrals(in.wtc.hcis.bm.base.ReferralLightBM, int, int, java.lang.String)}.
	 */
	@Test
	public void testGetActiveReferralsReferralLightBMIntIntString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.referral.ReferralManagerImpl#getActiveReferralsListRange(in.wtc.hcis.bm.base.ReferralLightBM, int, int, java.lang.String)}.
	 */
	@Test
	public void testGetActiveReferralsListRange() {
		
		try {
			ReferralLightBM referralLightBM = new ReferralLightBM();
			referralLightBM.setCity("Hyd");
			ListRange listRange = referralManager.getActiveReferralsListRange(referralLightBM, 0, 100, "");
			
			Util.dumpBean(listRange.getData());
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.referral.ReferralManagerImpl#getReferralDetails(java.lang.Integer)}.
	 */
	@Test
	public void testGetReferralDetails() {
		
		try {
			ReferralBM commissionBM = referralManager.getReferralDetails(1);
			Util.dumpBean( commissionBM );
			
			for( ReferralCommissionBM referralCommissionBM : commissionBM.getReferralCommissionList())
			Util.dumpBean(referralCommissionBM);
			assertTrue(true);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.referral.ReferralManagerImpl#getReferralTypes()}.
	 */
	@Test
	public void testGetReferralTypes() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.referral.ReferralManagerImpl#runCommissionReport(java.lang.Integer, java.util.Date, java.util.Date, java.lang.String)}.
	 */
	@Test
	public void testRunCommissionReport() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.referral.ReferralManagerImpl#processCommission(java.lang.Integer, java.lang.String)}.
	 */
	@Test
	public void testProcessCommission() {
		
		try {
			referralManager.processCommission(1, "Bhavesh");
			assertTrue(true);
		} catch (HCISException e) {
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.referral.ReferralManagerImpl#saveReferral(in.wtc.hcis.bm.base.ReferralBM)}.
	 */
	@Test
	public void testSaveReferral() {

		try {
			ReferralBM referralBM = new ReferralBM();
			referralBM.setReferralType(new CodeAndDescription("CLINIC",""));
			referralBM.setReferralAddress("Huda Colony");
			referralBM.setCountry(new CodeAndDescription("IN",""));
			referralBM.setState(new CodeAndDescription("AP",""));
			referralBM.setCreatedBy("Bhavesh");
			referralBM.setCity("Hyderabad");
			referralBM.setReferralName("Subhasis");
			
			ArrayList<ReferralCommissionBM> referralCommissionList = new ArrayList<ReferralCommissionBM>(0);
			referralBM.setReferralCommissionList(referralCommissionList);
			
			ReferralCommissionBM referralCommissionBM = new ReferralCommissionBM();
			
			referralCommissionBM.setCommissionValue(150.0);
			referralCommissionBM.setEffectiveFromDate(new Date());
			referralCommissionBM.setEntityType(new CodeAndDescription("SERVICES",""));

			ReferralCommissionBM referralCommissionBM1 = new ReferralCommissionBM();
			referralCommissionBM1.setCommissionTypeInd(new CodeAndDescription("PERCENTAGE",""));
			referralCommissionBM1.setCommissionValue(22.5);
			referralCommissionBM1.setEffectiveFromDate(new Date());
			referralCommissionBM1.setEntityType(new CodeAndDescription("APPOINTMENTS",""));

			referralCommissionList.add(referralCommissionBM);
			referralCommissionList.add(referralCommissionBM);
			referralManager.saveReferral(referralBM);
			assertTrue(true);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.referral.ReferralManagerImpl#modifyReferral(in.wtc.hcis.bm.base.ReferralBM)}.
	 */
	@Test
	public void testModifyReferral() {
		try {
			ReferralBM referralBM = new ReferralBM();
			referralBM.setReferralCode(1);
			referralBM.setReferralType(new CodeAndDescription("CLINIC",""));
			referralBM.setReferralAddress("Near net link");
			referralBM.setCountry(new CodeAndDescription("IN",""));
			referralBM.setState(new CodeAndDescription("MP",""));
			referralBM.setCreatedBy("Rajesh");
			referralBM.setCity("Bhopal");
			referralBM.setReferralName("Mohajesh Kumar");
			
			ArrayList<ReferralCommissionBM> referralCommissionList = new ArrayList<ReferralCommissionBM>(0);
			referralBM.setReferralCommissionList(referralCommissionList);
			
			ReferralCommissionBM referralCommissionBM = new ReferralCommissionBM();
			
			referralCommissionBM.setCommissionTypeInd(new CodeAndDescription("ABSOLUTE",""));
			referralCommissionBM.setCommissionValue(0.0);
			referralCommissionBM.setEffectiveFromDate(new Date());
			referralCommissionBM.setEntityType(new CodeAndDescription("SERVICES",""));

			ReferralCommissionBM referralCommissionBM1 = new ReferralCommissionBM();
			referralCommissionBM1.setCommissionTypeInd( new CodeAndDescription("PERCENTAGE",""));
			referralCommissionBM1.setCommissionValue(12.5);
			referralCommissionBM1.setEffectiveFromDate(new Date());
			referralCommissionBM1.setEntityType(new CodeAndDescription("SERVICES",""));

			referralCommissionList.add(referralCommissionBM);
			referralCommissionList.add(referralCommissionBM);
			referralManager.modifyReferral(referralBM);
			assertTrue(true);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.bo.referral.ReferralManagerImpl#getReferralCommissionProcesses(int, int, java.lang.String)}.
	 */
	@Test
	public void testGetReferralCommissionProcesses() {
		try {
			ListRange listRange = referralManager.getReferralCommissionProcesses(1, 10, "");
			Util.dumpBean(listRange.getData()[0]);
			assertTrue(true);
			
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testGetCommissionTypeInd(){
		ListRange list = referralManager.getCommissionTypeInd(0,100,"");
		
		for( int i=0 ; i< list.getTotalSize(); i++){
			Util.dumpBean(list.getData()[i]);
		}
	}
	
}
