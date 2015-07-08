package in.wtc.hcis.ip.test.unittest;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wtc.hcis.ip.da.Admission;

import in.wtc.hcis.bm.ip.PatientBasicDetailBM;
import in.wtc.hcis.ip.bo.insurance.InsuranceManager;
import in.wtc.hcis.ip.bo.order.*;
import in.wtc.hcis.test.unit.Util;

import junit.framework.TestCase;

public class TestAdmissionOrder extends TestCase {

	private AdmissionManager admissionManager;
	
	protected void setUp() throws Exception {
		super.setUp();
		String[] ctxFileArr = {"applicationContextTest.xml","AccountantContextBLTest.xml","HCISCoreContext.xml","IPManagementContext.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);
		admissionManager = (AdmissionManager)ctx.getBean("AdmissionOrder");
	}
	


	public void testGetPatientBasicDetails(){
		
		try{
			PatientBasicDetailBM patientBasicDetailBM = admissionManager.getPatientBasicDetails(1);
			Util.dumpBean(patientBasicDetailBM);
			assertTrue(true);
		}catch(Exception e ){
			assertTrue(false);
		}
		
	}

}
