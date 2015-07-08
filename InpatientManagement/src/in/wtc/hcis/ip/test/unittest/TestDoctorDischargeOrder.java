/**
 * 
 */
package in.wtc.hcis.ip.test.unittest;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.ip.DischargeBM;
import in.wtc.hcis.ip.bo.order.*;
import junit.framework.TestCase;

/**
 * @author Bhavesh
 *
 */
public class TestDoctorDischargeOrder extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	DoctorDischargeOrder dischargeOrder;
	
	protected void setUp() throws Exception {
		super.setUp();
		String[] ctxFileArr = {"applicationContextTest.xml","HCISCoreContext.xml","IPManagementContext.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);
		dischargeOrder = (DoctorDischargeOrder)ctx.getBean("DoctorDischargeOrder");
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorDischargeOrder#createDoctorOrder(in.wtc.hcis.bm.ip.DoctorOrderBM)}.
	 */
	public void testCreateDoctorOrderDoctorOrderBM() {
		
		try {
			DischargeBM dischargeBM = new DischargeBM();
			
			dischargeBM.setPatientId(2);
			dischargeBM.setDoctorId(1);

			dischargeBM.setLastModifiedDtm(new Date());
			dischargeBM.setDoctorOrderStatus(new CodeAndDescription("CREATED","Order has been created by a doctor"));
			
			dischargeBM.setModifiedBy("bhavesh");
			
			dischargeOrder.createDischargeOrder(dischargeBM);
			assertTrue(true);
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
			
		}
		
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorDischargeOrder#createDischargeOrder(in.wtc.hcis.bm.ip.DischargeBM)}.
	 */
	public void testCreateDischargeOrder() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorDischargeOrder#getDischarge(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.util.Date, java.util.Date, java.lang.String, java.lang.String)}.
	 */
	public void testGetDischarge() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorDischargeOrder#modifyDischargeDetails(in.wtc.hcis.bm.ip.DischargeBM)}.
	 */
	public void testModifyDischargeDetails() {
		fail("Not yet implemented");
	}

}
